package flowdroid.runtool;

import java.util.ArrayList;
import java.util.List;

import soot.jimple.infoflow.results.ResultSinkInfo;
import soot.jimple.infoflow.results.ResultSourceInfo;
import soot.util.MultiMap;

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
	
	public void printComparison() {
		System.out.println("Number of reported flows (base): " + baseFlows);
		System.out.println("Number of reported flows (optn): " + comparisonFlows);
		System.out.println("Matching Flows: " + numMatched);
		System.out.println("Missed Flows: " + numMissed);
		System.out.println("False Positive Flows: " + numFalsePositive);
	}
	
	public void printComparisonFull() {
		System.out.println("Matching Flows:");
		if (matchedFlows != null) {
			for (ResultSinkInfo currSink : matchedFlows.keySet()) {
				System.out.println("Sink: " + currSink.toString());
				for (ResultSourceInfo currSource : matchedFlows.get(currSink)) {
					System.out.println("    Source: " + currSource.toString());
				}
			}
		}
		
		System.out.println("Missed Flows:");
		if (missedFlows != null) {
			for (ResultSinkInfo currSink : missedFlows.keySet()) {
				System.out.println("Sink: " + currSink.toString());
				for (ResultSourceInfo currSource : missedFlows.get(currSink)) {
					System.out.println("    Source: " + currSource.toString());
				}
			}
		}
		
		System.out.println("False Positive Flows:");
		if (falsePositiveFlows != null) {
			for (ResultSinkInfo currSink : falsePositiveFlows.keySet()) {
				System.out.println("Sink: " + currSink.toString());
				for (ResultSourceInfo currSource : falsePositiveFlows.get(currSink)) {
					System.out.println("    Source: " + currSource.toString());
				}
			}
		}
	}
	
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
