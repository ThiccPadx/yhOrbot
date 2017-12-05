package dev.div0.webDriver;

import java.util.concurrent.TimeUnit;

import dev.div0.logging.BaseLogger;

import org.openqa.selenium.WebDriver;

public class CreateWebDriver extends BaseLogger{
	
	private WebDriverFactory webDriverFactory = new WebDriverFactory();
	private WebDriver webDriver;
	
	public WebDriver execute(BrowserType browserType){
		switch(browserType){
			case CHROME:
				webDriver = webDriverFactory.getDriver(BrowserType.CHROME).getDriver();
				break;
			case OPERA:
				webDriver = webDriverFactory.getDriver(BrowserType.OPERA).getDriver();
				break;
			case FIREFOX:
				webDriver = webDriverFactory.getDriver(BrowserType.FIREFOX).getDriver();
				break;
			case IE:
				webDriver = webDriverFactory.getDriver(BrowserType.IE).getDriver();
				break;
			case PHANTOM_JS:
				webDriver = webDriverFactory.getDriver(BrowserType.PHANTOM_JS).getDriver();
				break;
			case HTML_UNIT:
				webDriver = webDriverFactory.getDriver(BrowserType.HTML_UNIT).getDriver();
				break;
				default:
					logError("No browser type selected.");
					break;
		}
		
		//webDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		return webDriver;
	}
	
	public void destroy(){
		webDriverFactory = null;
	}
}
