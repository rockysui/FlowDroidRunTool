package flowdroid.runtool;

import soot.jimple.infoflow.android.InfoflowAndroidConfiguration;

//Simple class container to store configuration and arguments to obtain config

public class InfoflowAndroidConfigurationArgument {
	public String arguments;
	public InfoflowAndroidConfiguration config;
	
	public InfoflowAndroidConfigurationArgument(String arg, InfoflowAndroidConfiguration cfg) {
		arguments = arg;
		config = cfg;
	}
}
