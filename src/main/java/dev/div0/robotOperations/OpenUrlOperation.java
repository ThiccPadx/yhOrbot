package dev.div0.robotOperations;

import org.openqa.selenium.WebDriverException;

public class OpenUrlOperation extends BaseOperation {

	public OpenUrlOperation() {
		super();
	}
	
	@Override
	public boolean execute() throws OperationException{
		//log("open url "+operationData);
		
		String url = operationData.getElementSearchData();
		
		if(url==null){
			throw new OperationException("OpenUrlOperation error: url not set");
		}
		else{
			try{
				webDriver.get(url);
				return true;
			}
			catch(WebDriverException webDriverException){
				throw new OperationException("OpenUrlOperation error: "+webDriverException.getMessage());
			}
		}
		
	}

}
