package dev.div0.robotOperations;

import dev.div0.robotOperations.recaptcha.SolveRecaptchaOperation;


public class InfinitlySolveRecaptchaOperation extends FindElementAndDoOperation {

	private static final Object waitMonitor = new Object();
	
	protected void solveRecaptcha(){
		SolveRecaptchaOperation solveRecaptchaOperation = new SolveRecaptchaOperation();
		solveRecaptchaOperation.setWebDriver(webDriver);
		
		try{
			solveRecaptchaOperation.execute();
		}
		catch(OperationException operationException){
			solveRecaptchaOperation = null;
			timeout(120);
			solveRecaptcha();
		}
	}
	
	protected void timeout(int seconds){
		synchronized(waitMonitor){
            try{
                System.out.println("Waiting "+seconds+" seconds ...");
                waitMonitor.wait(seconds * 1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
	}
}
