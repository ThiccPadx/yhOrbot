package dev.div0.robotOperations.recaptcha;

import dev.div0.robotOperations.OperationException;
import dev.div0.robotOperations.recaptcha.data.RecaptchaData;
import dev.div0.robotOperations.recaptcha.solver.RecaptchaSolverException;



public class SolveRecaptchaWithErrorOperation extends SolveRecaptchaOperation {
	
	@Override
	public boolean execute() throws OperationException{
		
		log("       --- SolveRecaptchaWithErrorOperation start ");
		clickImNotARobot();
		solved = checkIsRecaptchaComplete();
		log("Recaptcha solved = "+solved);
		
		if(solved == false){
			getPayloadProperties();
			log("Captcha multiclickable = "+isCaptchaMulticlickable);
			
			if(isCaptchaMulticlickable == true){
				resolve();
			}
			else{
				log("normal recaptcha - going on ...");
				getImage();
				
				try {
					doImagesClick();
				} catch (RecaptchaSolverException e) {
					e.printStackTrace();
					throw new OperationException(e.getMessage());
				}
				
				checkClicksResult();
				
				if(solved==true){
					log("Recaptcha solved !!!!");
					return solved;
				}
				else{
					log("Recaptcha not solved");
					resolve();
					//return true;
				}
			}
		}
		else{
			log("Recaptcha solved !!!!");
			return true;
		}
		return false;
	}
	
	@Override
	protected void doImagesClick() throws RecaptchaSolverException{
		
		clickRecaptchaImages.setWebDriver(webDriver);
		int[] fakeClicks = new int[1];
		fakeClicks[0] = 1;
		RecaptchaData.setClicks(fakeClicks);
		try{
			clickRecaptchaImages.execute();
		}
		catch(OperationException exception){
			throw new RecaptchaSolverException(exception.getMessage());
		}
	}
}
