package dev.div0.robotOperations;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DetectPageHasTextOperation extends FindElementAndDoOperation{

    protected WebDriverWait wait;

    @Override
    public boolean execute() throws OperationException{
        try{
            findElement();
        }
        catch(OperationException exception){
            log("NO SUCH ELEMENT by search data "+operationData.getElementSearchData());
        }
        /*
        if(webElement==null){
            log("element NOT found");
            return false;
        }
        else{
            log("element found");
            return true;
        }
        */
        return hasElement();
    }

    protected boolean hasElement(){
        log("hasElement() webElement="+webElement+"  operationData="+operationData);
        if(webElement == null){
            return false;
        }
        else{
            return webElement.getText().equals(operationData.getPayload());
        }
    }

    /*
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
            log("Timed out !!!");
            throw new OperationException(timeoutException.getMessage());
        }
    }
    */
}
