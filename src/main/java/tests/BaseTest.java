package tests;

import dev.div0.webDriver.BrowserType;
import dev.div0.webDriver.CreateWebDriver;
import org.openqa.selenium.WebDriver;

public class BaseTest {

    protected CreateWebDriver webDriverCreator;
    protected WebDriver webDriver;

    public BaseTest() {
        webDriverCreator = new CreateWebDriver();
        webDriver = webDriverCreator.execute(BrowserType.CHROME);
    }

    protected boolean execute(){
        return false;
    }
}
