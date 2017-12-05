package dev.div0.robotOperations;

import org.openqa.selenium.Keys;

public class PressEnterKeyOperation extends FindElementAndDoOperation {
	
	@Override
	protected boolean doOperation() throws OperationException{
		log("PressEnterKeyOperation webElement="+webElement);
		webElement.sendKeys(Keys.ENTER);
		return true;
	}
}
