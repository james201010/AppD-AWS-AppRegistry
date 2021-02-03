package com.appdynamics.cloud.aws.appregistry;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import com.appdynamics.cloud.aws.appregistry.json.Application;
import com.appdynamics.cloud.aws.appregistry.utils.Logger;
import com.appdynamics.cloud.aws.appregistry.utils.StringUtils;
import com.google.gson.Gson;



public class AppdAppRegistryApplication {

	protected static final String APP_CONF_KEY = "appConfigFilePath";
	
	protected static ApplicationConfig APP_CONFIG;
	
	
	
	public static void main(String[] args) {
		
		Logger lgr = new Logger("");
		
		try {
			
			// LOAD CONFIG
			String confPath = System.getProperty(APP_CONF_KEY);
			Yaml yaml = new Yaml(new Constructor(ApplicationConfig.class));
			InputStream inputStream = StringUtils.getFileAsStream(confPath);

			APP_CONFIG = yaml.load(inputStream);
			
			lgr.printBanner(true);
			
			lgr.log("#########################################################################################    STARTING APPDYNAMICS AWS APPREGISTRY UTILITIES    ################################################################################");
			lgr.log("");
			
			lgr.info("");
			lgr.info(" - Initializing connection to AppDynamics Controller");
			
			AppdControllerManager controller = AppdControllerManager.initControllerClient(APP_CONFIG);
			
			List<Application> appsList = controller.getApplicationList();
			
			lgr.info("");
			lgr.info(" - Retrieved " + appsList.size() + " Applications from AppDynamics Controller");
			lgr.info("");
			
			List<Application> appsToPublish = new ArrayList<Application>();
			
			String matchMsg = "   - Searching for matching applications to publish --";
			for (int i = 0; i < appsList.size(); i++) {
				matchMsg = matchMsg + "-";
			}

			for (Application app : appsList) {
				
				lgr.info(matchMsg);
				matchMsg = matchMsg.substring(0, matchMsg.length() - 1);
				Thread.currentThread().sleep(500);
				
				for (String appName : APP_CONFIG.getApplicationNames()) {
						
					if (appName.equals(app.getName())) {
						appsToPublish.add(app);						
					}
				}
				
			}
			
			Thread.currentThread().sleep(500);
			
			lgr.info("");
			lgr.info(" - Found " + appsToPublish.size() + " matching Applications to publish to AppRegistry");
			lgr.info("");
			
			Thread.currentThread().sleep(500);
			
			for (Application app : appsToPublish) {
				
				
				controller.getTiersAndNodesForApplication(app);
				
				
				lgr.info("  ---------------------------------------  Appplication to Publish  ---------------------------------------");
				lgr.info("  App Name: " + app.getName());
				lgr.info("  App Id: " + app.getId());
				lgr.info("  App Description: " + app.getDescription());
				//lgr.info("  Account Guid: " + app.getAccountGuid());
				
				lgr.info("  Number of Tiers: " + app.getNumberOfTiers());
				lgr.info("  Number of Nodes: " + app.getNumberOfNodes());
				
				
				Thread.currentThread().sleep(500);
			}
			
			
			//String json = controller.getNodesAsJson("AD-Financial-Cloud");
			//String json = new Gson().toJson(appsToPublish.get(1));
			
			//System.out.println("*********************************************************************");
			//System.out.println(json);
			//System.out.println("*********************************************************************");	
			
			
			
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
		

		lgr.log("");
		lgr.log("#########################################################################################    FINISHED APPDYNAMICS AWS APPREGISTRY UTILITIES    ################################################################################");
		
	}

	
	
}
