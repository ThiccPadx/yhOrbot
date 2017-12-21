package dev.div0.robotOperations;

import dev.div0.robotOperations.screen.TakePageScreenshot;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class DetectPageHasElementOperation extends DetectPageHasTextOperation {
    public DetectPageHasElementOperation() {
        super();
    }

    public boolean hasCssClass(String cssClass){
        if(webElement==null){
            return false;
        }
        else{
            String classes = webElement.getAttribute("class");
            for (String c : classes.split(" ")) {
                if (c.equals(cssClass)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isElementVisible() throws OperationException {

        log("detect page has element create screenshot");
        createScreenshot();

        if(webElement == null){
            return false;
        }

        try {
            log("waiting 2 seconds..");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //webDriver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);

        boolean isVisible = webElement.isDisplayed();

        return isVisible;
    }

    private void createScreenshot() throws OperationException {
        TakePageScreenshot takePageScreenshot = new TakePageScreenshot();
        takePageScreenshot.setWebDriver(webDriver);
        takePageScreenshot.execute();
    }

    @Override
    protected boolean hasElement(){
        if(webElement==null){
            return false;
        }
        else{
            return true;
        }
    }
}
