/**
 * 
 */
package com.appdynamics.cloud.aws.appregistry.utils;

import com.appdynamics.cloud.aws.appregistry.json.Application;

/**
 * @author James Schneider
 *
 */
public class AppRegUtils {

	/**
	 * 
	 */
	public AppRegUtils() {
		
	}

	public static String getAppRegistryAppName(Application appdApp) throws Throwable {
		return appdApp.getName() + "_" + appdApp.getAccountGuid();
		
	}
	
	public static String getAppRegistryAttrGroupName(Application appdApp) throws Throwable {
		return getAppRegistryAppName(appdApp) + "_AG";
		
	}	
	
}
