package dev.div0.webDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by Ilya on 21.12.2017.
 */
public class ChromeHeadlessWebDriver  extends ChromeWebDriver{
    @Override
    public WebDriver getDriver() {
        if(webDriver==null){
            System.out.println("ChromeWebDriver creating webDriverPath="+webDriverPath);
            //System.setProperty("webdriver.chrome.driver", "C:/consultant/browserDrivers/chrome/2.19/chromedriver_2_19.exe");
            System.setProperty("webdriver.chrome.driver", webDriverPath);


            DesiredCapabilities capabilities= DesiredCapabilities.chrome();

            HashMap<String, Object> images = new HashMap<String, Object>();
            images.put("images", 2);
            HashMap<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("profile.default_content_setting_values", images);

            ChromeOptions options= new ChromeOptions();
            options.addArguments("--test-type --no-sandbox");
            options.addArguments("--enable-strict-powerful-feature-restrictions");

            options.setExperimentalOption("prefs", prefs);
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);

            ChromeOptions option = new ChromeOptions();
            option.addArguments("--headless");


            //webDriver = new ChromeDriver();
            webDriver = new ChromeDriver(capabilities);

            webDriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
        }
        return webDriver;
    }
}
