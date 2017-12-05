package dev.div0.webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

public class IEWebDriver implements IWebDriverProvider {

	private WebDriver webDriver;
	
	@Override
	public WebDriver getDriver() {
		if(webDriver==null){
			System.setProperty("webdriver.ie.driver", "C:/consultant/browserDrivers/ie/IEDriverServer.exe");
			//System.setProperty("webdriver.chrome.driver", "E:/Tomcat_workspace/SimpleSeleniumSequence/browserDrivers/chrome/chromedriver.exe");
			webDriver = new InternetExplorerDriver();
			webDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			webDriver.manage().window().setPosition(new Point(-2000, 0));
			webDriver.manage().window().setSize(new Dimension(320, 240));
		}
		return webDriver;
	}

}
