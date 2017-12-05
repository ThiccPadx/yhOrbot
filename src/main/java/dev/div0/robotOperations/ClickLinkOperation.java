package dev.div0.robotOperations;

import dev.div0.robotOperations.operationData.OperationData;

import org.openqa.selenium.TimeoutException;

public class ClickLinkOperation extends FindElementAndDoOperation {
	
	private boolean overrideHrefTarget = false;
	
	@Override
	protected boolean doOperation() throws OperationException{
		log("IM ClickLinkOperation");
		try{
			click();
		}
		catch(OperationException exception){
			throw new OperationException("ClickLinkOperation error. "+exception.getMessage());
		}	
		return true;
	}

	private void click() throws OperationException {
		
		// click
		overrideHrefTarget = operationData.isOverrideHrefTarget();
		
		
		log("Clicking on link !!! overrideHrefTarget="+overrideHrefTarget);
		if(overrideHrefTarget == true){
			createOpenUrlOperation();
		}
		else{
			try{
				webElement.click();
			}
			catch(TimeoutException timeoutException){
				throw new OperationException("Timed out.");
			}
		}
	}

	private void createOpenUrlOperation() throws OperationException {
		log("create new open url operation because of link href target override");
		
		String linkLocatin = webElement.getAttribute("href");
		log("linkLocation = "+linkLocatin);
		
		OpenUrlOperation openUrlOperation = new OpenUrlOperation();
		
		OperationData openUrlOperationData = new OperationData();
		openUrlOperationData.setElementSearchData(linkLocatin);
		openUrlOperation.setOperationData(openUrlOperationData);
		
		openUrlOperation.setWebDriver(webDriver);
		openUrlOperation.execute();
	}

	public void setOverrideHrefTarget(boolean overrideHrefTarget) {
		this.overrideHrefTarget = overrideHrefTarget;
	}
}
