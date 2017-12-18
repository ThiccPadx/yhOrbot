package dev.div0.robotOperations;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ClickElementOperation extends FindElementAndDoOperation {
	
	@Override
	protected boolean doOperation() throws OperationException{
		log("click element operation. webElement="+webElement+" elementSearchInterval="+elementSearchInterval);
		
		//webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		WebDriverWait wait = new WebDriverWait(webDriver, elementSearchInterval);

		try{
			webElement.click();
		}
		catch(TimeoutException timeoutException){
			throw new OperationException("Timed out");
		}
		catch(ElementNotVisibleException elementNotVisibleException){
			log("element not visible");
			throw new OperationException("Element not visible");
		}
		
		return true;
	}
}
