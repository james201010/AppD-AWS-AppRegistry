/**
 * 
 */
package com.appdynamics.cloud.aws.appregistry;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.appregistry.AWSAppRegistry;
import com.amazonaws.services.appregistry.AWSAppRegistryClientBuilder;

/**
 * @author James Schneider
 *
 */
public class AwsAppRegistryManager {

	private AWSAppRegistry appRegistry;
	
	/**
	 * 
	 */
	public AwsAppRegistryManager() throws Throwable {
		
		this.appRegistry = AWSAppRegistryClientBuilder.standard().withRegion(Regions.fromName(Regions.getCurrentRegion().getName())).build();
		
	}
	
	

}
