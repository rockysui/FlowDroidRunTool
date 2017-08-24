package flowdroid.runtool;

import soot.jimple.infoflow.results.InfoflowResults;

public class InfoflowResultsData {
	private double maxMemoryConsumption;
	private double runTime;
	public InfoflowResults results;
	
	public InfoflowResultsData(InfoflowResults infoResults) {
		results = infoResults;
		maxMemoryConsumption = 0;
		runTime = 0;
	}
	
	public void setMaxMemoryConsumption(double maxMemory) {
		maxMemoryConsumption = maxMemory;
	}
	
	public void setRunTime(double time) {
		runTime = time;
	}
	
	public double getMaxMemoryConsumption() {
		return maxMemoryConsumption;
	}
	
	public double getRunTime() {
		return runTime;
	}
}
