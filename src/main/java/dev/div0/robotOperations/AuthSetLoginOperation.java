package dev.div0.robotOperations;


public class AuthSetLoginOperation extends SetTextOperation{
    @Override
    protected boolean doOperation() throws OperationException{
        log("Im AuthSetLoginOperation  payload = "+operationData.getPayload());
        webElement.sendKeys(operationData.getPayload());
        return true;
    }
}
