package dev.div0.robotOperations;


public class SetTextOperation extends FindElementAndDoOperation {
	@Override
	protected boolean doOperation() throws OperationException{
		log("Im SetTextOperation  payload = "+operationData.getPayload());
		webElement.sendKeys(operationData.getPayload());
		return true;
	}
}
