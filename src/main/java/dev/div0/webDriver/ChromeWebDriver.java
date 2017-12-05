package dev.div0.webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ChromeWebDriver implements IWebDriverProvider {
	private WebDriver webDriver;

	private String webDriverPath = "C:/consultant/browserDrivers/chrome/chromedriver.exe";
	
	@Override
	public WebDriver getDriver() {
		if(webDriver==null){
			System.out.println("ChromeWebDriver creating webDriverPath="+webDriverPath);
			//System.setProperty("webdriver.chrome.driver", "C:/consultant/browserDrivers/chrome/2.19/chromedriver_2_19.exe");
			System.setProperty("webdriver.chrome.driver", webDriverPath);
			webDriver = new ChromeDriver();

			webDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		}
		return webDriver;
	}
}
