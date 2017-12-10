package dev.div0.robotOperations;

public class SetTextOperation extends FindElementAndDoOperation {

	@Override
	protected boolean doOperation() throws OperationException{
		webElement.sendKeys(operationData.getPayload());
		return true;
	}
}
