package flowdroid.runtool;

import java.io.IOException;
import java.io.StringWriter;

import soot.jimple.infoflow.results.ResultSinkInfo;
import soot.jimple.infoflow.results.ResultSourceInfo;
import soot.util.MultiMap;

//Container that holds the details of matched, missed and false positive flows with
//respect to two sets of results of FlowDroid analysis, assuming the first result
//is a baseline compared to the second set of results.

public class ResultComparison {
	private MultiMap<ResultSinkInfo, ResultSourceInfo> missedFlows;
	private MultiMap<ResultSinkInfo, ResultSourceInfo> falsePositiveFlows;
	private MultiMap<ResultSinkInfo, ResultSourceInfo> matchedFlows;
	
	private int baseFlows;
	private int comparisonFlows;
	private int numMissed;
	private int numFalsePositive;
	private int numMatched;
	
	public ResultComparison(int baseNumFlows, int compNumFlows) {
		missedFlows = null;
		falsePositiveFlows = null;
		matchedFlows = null;
		
		baseFlows = baseNumFlows;
		comparisonFlows = compNumFlows;
		
		numMissed = 0;
		numFalsePositive = 0;
		numMatched = 0;
	}
	
	//Getters
	public int getBaseFlows() {
		return baseFlows;
	}
	
	public int getComparisonFlows() {
		return comparisonFlows;
	}
	
	public int getNumMissed() {
		return numMissed;
	}
	
	public int getNumFalsePositive() {
		return numFalsePositive;
	}
	
	public int getNumMatched() {
		return numMatched;
	}
	
	//Prints a short summary of the number of flows from the base result and from the result to compare
	//including number of flows matched, missed and are false positive.
	public void printComparison() {
		System.out.println("Number of reported flows (base): " + baseFlows);
		System.out.println("Number of reported flows (comp): " + comparisonFlows);
		System.out.println("Matching Flows: " + numMatched);
		System.out.println("Missed Flows: " + numMissed);
		System.out.println("False Positive Flows: " + numFalsePositive);
	}
	
	//Prints all matched flows, missed flows and false posiive flows
	public String printComparisonFull() {
		try (StringWriter sw = new StringWriter()) {
			sw.write("Matching Flows:\n");
			if (matchedFlows != null) {
				for (ResultSinkInfo currSink : matchedFlows.keySet()) {
					sw.write("Sink: " + currSink.toString() + "\n");
					for (ResultSourceInfo currSource : matchedFlows.get(currSink)) {
						sw.write("    Source: " + currSource.toString() + "\n");
					}
				}
			}
			
			sw.write("Missed Flows:\n");
			if (missedFlows != null) {
				for (ResultSinkInfo currSink : missedFlows.keySet()) {
					sw.write("Sink: " + currSink.toString() + "\n");
					for (ResultSourceInfo currSource : missedFlows.get(currSink)) {
						sw.write("    Source: " + currSource.toString() + "\n");
					}
				}
			}
			
			sw.write("False Positive Flows:\n");
			if (falsePositiveFlows != null) {
				for (ResultSinkInfo currSink : falsePositiveFlows.keySet()) {
					sw.write("Sink: " + currSink.toString() + "\n");
					for (ResultSourceInfo currSource : falsePositiveFlows.get(currSink)) {
						sw.write("    Source: " + currSource.toString() + "\n");
					}
				}
			}
			
			sw.close();
			return sw.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//Sets results from Multimap determined by comparison
	public void setResult(MultiMap<ResultSinkInfo, ResultSourceInfo> missed, 
						  MultiMap<ResultSinkInfo, ResultSourceInfo> falsePositive, 
						  MultiMap<ResultSinkInfo, ResultSourceInfo> matched) {
		missedFlows = missed;
		falsePositiveFlows = falsePositive;
		matchedFlows = matched;
		
		for (ResultSinkInfo currSink : missedFlows.keySet()) {
			numMissed += missedFlows.get(currSink).size();
		}
		
		for (ResultSinkInfo currSink : falsePositiveFlows.keySet()) {
			numFalsePositive += falsePositiveFlows.get(currSink).size();
		}
		
		for (ResultSinkInfo currSink : matchedFlows.keySet()) {
			numMatched += matchedFlows.get(currSink).size();
		}
	}
}
