package dev.div0.robotOperations;

import org.openqa.selenium.InvalidElementStateException;

public class SetTextOperation extends FindElementAndDoOperation {

	@Override
	protected boolean doOperation() throws OperationException{
		try{
			webElement.clear();
			webElement.sendKeys(operationData.getPayload());
			return true;
		}
		catch(InvalidElementStateException exception){
			return false;
		}
	}
}
