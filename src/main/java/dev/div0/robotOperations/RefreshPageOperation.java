package dev.div0.robotOperations;



public class RefreshPageOperation extends BaseOperation {
	
	@Override
	public boolean execute() throws OperationException{
		log("RefreshPageOperation");
		webDriver.navigate().refresh();
		return true;
	}
}
