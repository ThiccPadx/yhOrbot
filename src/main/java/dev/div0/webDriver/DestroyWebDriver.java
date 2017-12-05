package dev.div0.webDriver;

import org.openqa.selenium.WebDriver;

import dev.div0.logging.BaseLogger;

public class DestroyWebDriver extends BaseLogger{
	
	public DestroyWebDriver(WebDriver webDriver){
		//log("destroying web driver... webDriver="+webDriver);
		try{
			//log("start command webDriver.quit()");
			webDriver.quit();
			//log("webDriver quit complete");
			//webDriver.close();
			webDriver = null;
			//log("web driver destroy complete");
		}
		catch(Exception exception){
			log("destroy web driver ERROR !!! ");
		}
	}
}
