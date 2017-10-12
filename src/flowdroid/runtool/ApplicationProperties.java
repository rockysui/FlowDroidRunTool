package flowdroid.runtool;

//Simple container for storing APK properties detecting during FlowDroid analysis

public class ApplicationProperties {
	private int callGraphEdges;
	private int numSources;
	private int numSinks;
	private int numEntrypoints;
	private int numReachableMethods;
	private int numClasses;
	private int providers;
	private int services;
	private int activities;
	private int receivers;
	
	public ApplicationProperties() {
		callGraphEdges = 0;
		numSources = 0;
		numSinks = 0;
		numEntrypoints = 0;
		numReachableMethods = 0;
		numClasses = 0;
		providers = 0;
		services = 0;
		activities = 0;
		receivers = 0;
	}
	
	//Getters
	public int getCallGraphEdges() {
		return callGraphEdges;
	}
	
	public int getNumSources() {
		return numSources;
	}
	
	public int getNumSinks() {
		return numSinks;
	}
	
	public int getNumEntrypoints() {
		return numEntrypoints;
	}
	
	public int getNumReachableMethods() {
		return numReachableMethods;
	}
	
	public int getNumClasses() {
		return numClasses;
	}
	
	public int getProviders() {
		return providers;
	}
	
	public int getServices() {
		return services;
	}
	
	public int getActivities() {
		return activities;
	}
	
	public int getReceivers() {
		return receivers;
	}
	
	//Setters
	public void setCallGraphEdges(int callGraphEdges_) {
		callGraphEdges = callGraphEdges_;
	}
	
	public void setNumSources(int numSources_) {
		numSources = numSources_;
	}
	
	public void setNumSinks(int numSinks_) {
		numSinks = numSinks_;
	}
	
	public void setNumEntrypoints(int numEntrypoints_) {
		numEntrypoints = numEntrypoints_;
	}
	
	public void setNumReachableMethods(int numReachableMethods_) {
		numReachableMethods = numReachableMethods_;
	}
	
	public void setNumClasses(int numClasses_) {
		numClasses = numClasses_;
	}
	
	public void setServices(int services_) {
		services = services_;
	}
	
	public void setActivities(int activities_) {
		activities = activities_;
	}
	
	public void setReceivers(int receivers_) {
		receivers = receivers_;
	}
	
	public void setProviders(int providers_) {
		providers = providers_;
	}
}
