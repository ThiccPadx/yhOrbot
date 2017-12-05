package dev.div0.webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class HTMLUnitWebDriver implements IWebDriverProvider {

	private WebDriver webDriver;
	
	@Override
	public WebDriver getDriver() {
		if(webDriver==null){
			webDriver = new HtmlUnitDriver();
			webDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		}
		return webDriver;
	}

}
