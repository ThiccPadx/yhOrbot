package dev.div0.robotOperations;


public class SwitchToDefaultContentOperation extends BaseOperation {
	
	@Override
	public boolean execute() throws OperationException{
		try{
			webDriver.switchTo().defaultContent();
			return true;
		}
		catch(Exception exception){
			throw new OperationException(exception.getMessage());
		}
	}
}
