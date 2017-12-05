package dev.div0.robotOperations;

import dev.div0.logging.BaseLogger;
import dev.div0.robotOperations.operationData.OperationData;

import org.openqa.selenium.WebDriver;

public class BaseOperation extends BaseLogger implements IDestroyable{
	protected WebDriver webDriver;
	protected OperationData operationData;
	protected boolean stopped = false;
	
	public boolean execute() throws OperationException{
		return false;
	}
	
	public void setWebDriver(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	public void setOperationData(OperationData operationData) {
		this.operationData = operationData;
	}
	

	@Override
	public void destroy() {
		stopped = true;
	}
}
