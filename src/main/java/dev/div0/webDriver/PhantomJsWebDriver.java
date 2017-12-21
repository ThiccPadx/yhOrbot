package dev.div0.webDriver;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class PhantomJsWebDriver implements IWebDriverProvider {

	private WebDriver webDriver;
	
	public PhantomJsWebDriver(){
		
		/* possibly working
		
		String[] cli_args = new String[]{ "--ignore-ssl-errors=true" };
		DesiredCapabilities caps = DesiredCapabilities.phantomjs();
		caps.setCapability( PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cli_args );
		caps.setCapability( PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "/tmp/config/bin/phantomjs");
		driver =  new PhantomJSDriver( caps );
		 */
		
		/*
		Capabilities caps = new DesiredCapabilities();
        ((DesiredCapabilities) caps).setJavascriptEnabled(true);                
        ((DesiredCapabilities) caps).setCapability("takesScreenshot", true);  
        ((DesiredCapabilities) caps).setCapability(
                PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "C:/consultant/browserDrivers/phantomJS/phantomjs-2.1.1-windows/bin/phantomjs.exe");
        webDriver = new  PhantomJSDriver(caps);
        */
		
		String[] cli_args = new String[]{ "--ignore-ssl-errors=true" };

		DesiredCapabilities caps = DesiredCapabilities.phantomjs();
		caps.setCapability("takesScreenshot", true);
		caps.setCapability( PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cli_args );

		caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "C:/consultant/browserDrivers/phantomJS/phantomjs-2.1.1-windows/bin/phantomjs.exe");

		// experimental
		//String[] args = {"--ignore-ssl-errors=yes"};
		//caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, args);
		//caps.setCapability("phantomjs.page.settings.userAgent", "Mozilla/5.0 (Windows NT 5.1; rv:22.0) Gecko/20100101 Firefox/22.0");
		// end of experimental

		webDriver =  new PhantomJSDriver( caps );

		//webDriver.manage().window().setSize(new Dimension(1024,768));

		// experimental
		webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		webDriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		webDriver.manage().timeouts().setScriptTimeout(50, TimeUnit.SECONDS);
		// end of experimental
	}
	
	@Override
	public WebDriver getDriver() {
		return webDriver;
	}
}
