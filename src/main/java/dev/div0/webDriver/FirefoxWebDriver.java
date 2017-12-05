package dev.div0.webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class FirefoxWebDriver implements IWebDriverProvider {

	private FirefoxDriver webDriver = new FirefoxDriver();
	
	public FirefoxWebDriver(){
		webDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
	}
	
	@Override
	public WebDriver getDriver() {
		return webDriver;
	}
}
