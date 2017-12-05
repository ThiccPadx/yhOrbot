package dev.div0.robotOperations;

import java.util.concurrent.TimeUnit;

public class WaitOperation extends BaseOperation {
	
	@Override
	public boolean execute() throws OperationException{
		int seconds = Integer.parseInt(operationData.getElementSearchData());
		log("Waiting for "+seconds+"  seconds...");

		/*
		webDriver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
		*/
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log("Wait complete");
		return true;
	}
}
