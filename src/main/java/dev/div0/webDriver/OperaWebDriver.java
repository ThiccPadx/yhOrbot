package dev.div0.webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;

public class OperaWebDriver implements IWebDriverProvider {

	private WebDriver webDriver;
	//com.opera.core.systems.OperaDriver old Opera driver for Presto engine
	
	@Override
	public WebDriver getDriver() {
		
		if(webDriver==null){
			System.setProperty("webdriver.opera.driver", "C:/consultant/browserDrivers/opera_chromium/operadriver_x64.exe");
			//System.setProperty("webdriver.chrome.driver", "E:/Tomcat_workspace/SimpleSeleniumSequence/browserDrivers/chrome/chromedriver.exe");
			webDriver = new OperaDriver();
			webDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		}
		return webDriver;
	}
}
