package dev.div0.robotOperations;

import org.openqa.selenium.JavascriptExecutor;


public class BringBrowserToFrontOperation extends BaseOperation {
	
	@Override
	public boolean execute() throws OperationException{
		
		//Store the current window handle
		String currentWindowHandle = webDriver.getWindowHandle();
		
		//run your javascript and alert code
		((JavascriptExecutor)webDriver).executeScript("alert('Test')"); 
		webDriver.switchTo().alert().accept();

		//Switch back to to the window using the handle saved earlier
		webDriver.switchTo().window(currentWindowHandle);
		
		return true;
	}
}
