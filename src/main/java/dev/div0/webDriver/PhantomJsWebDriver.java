package dev.div0.webDriver;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

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
		caps.setCapability( PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cli_args );

		caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "C:/consultant/browserDrivers/phantomJS/phantomjs-2.1.1-windows/bin/phantomjs.exe");
		webDriver =  new PhantomJSDriver( caps );
	}
	
	@Override
	public WebDriver getDriver() {
		return webDriver;
	}
}
