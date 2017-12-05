package dev.div0.robotOperations;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.TimeoutException;


public class ClickElementOperation extends FindElementAndDoOperation {
	
	@Override
	protected boolean doOperation() throws OperationException{
		//log("click element operation. webElement="+webElement);
		
		//webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		try{
			webElement.click();
		}
		catch(TimeoutException timeoutException){
			throw new OperationException("Timed out");
		}
		
		return true;
	}
}
