package dev.div0.robotOperations.recaptcha;

import dev.div0.Application;
import dev.div0.robotOperations.BaseOperation;
import dev.div0.robotOperations.OperationException;
import dev.div0.robotOperations.recaptcha.data.RecaptchaData;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class GetRecaptchaInstructionsOperation extends BaseOperation {
	
	private String instructions = "";
	
	@Override
	public boolean execute() throws OperationException{
		
		try{
			getInstructions();
		}
		catch(Exception exception){
			throw new OperationException(exception.getMessage());
		}
		
		return true;
	}
	
	private void getInstructions() {
		log("getting instructions...");
		
		String instructionsXPath = operationData.getElementSearchData();
		log("instructionsXPath="+instructionsXPath);

		WebDriverWait wait = new WebDriverWait(webDriver, Application.baseOperationWaitSecondsUntil);
		WebElement instructionsContainer = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(instructionsXPath)));
		
		//WebElement instructionsContainer = webDriver.findElement(By.xpath("//*[@id='rc-imageselect']/div[2]/div[1]/div[1]/div[1]"));
		//log("instructions container = "+instructionsContainer);
		instructions = instructionsContainer.getText();
		log("Instructions = "+instructions);
		RecaptchaData.setInstructions(instructions);
	}
}
