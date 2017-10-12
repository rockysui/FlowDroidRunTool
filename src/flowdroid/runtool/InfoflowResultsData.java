package flowdroid.runtool;

import soot.jimple.infoflow.results.InfoflowResults;

//Container to hold data from run time such as time elapsed running and maximum memory consumption
//Also stores results of analysis

public class InfoflowResultsData {
	private double maxMemoryConsumption;
	private double runTime;
	public InfoflowResults results;
	
	public InfoflowResultsData(InfoflowResults infoResults) {
		results = infoResults;
		maxMemoryConsumption = 0;
		runTime = 0;
	}
	//Getters
	public double getMaxMemoryConsumption() {
		return maxMemoryConsumption;
	}
	
	public double getRunTime() {
		return runTime;
	}
	
	//Setters
	public void setMaxMemoryConsumption(double maxMemory) {
		maxMemoryConsumption = maxMemory;
	}
	
	public void setRunTime(double time) {
		runTime = time;
	}
}
