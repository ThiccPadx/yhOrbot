package dev.div0.robotOperations;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DetectPageHasText extends FindElementAndDoOperation{

    private WebDriverWait wait;

    @Override
    public boolean execute() throws OperationException{
        log("DetectPageHasText ");
        log("xpath="+operationData.getElementSearchData());

        try{
            findElement();
        }
        catch(OperationException exception){
            log("NO SUCH ELEMENT by xpath "+operationData.getElementSearchData());
        }

        if(webElement==null){
            log("element NOT found");
            return false;
        }
        else{
            log("element found");
            return true;
        }
    }

    @Override
    protected boolean findElement() throws OperationException{
        findElementByXPath();
        return true;
    }

    @Override
    protected void findElementByXPath() throws OperationException{
        String xPath = operationData.getElementSearchData();

        wait = new WebDriverWait(webDriver, elementSearchInterval);
        try{
            webElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
        }
        catch(TimeoutException timeoutException){
            throw new OperationException(timeoutException.getMessage());
        }
    }
}
