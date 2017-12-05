package dev.div0.robotOperations.recaptcha;

import java.util.List;

import dev.div0.Application;
import dev.div0.application.page.Page;
import dev.div0.robotOperations.BaseOperation;
import dev.div0.robotOperations.OperationException;
import dev.div0.robotOperations.SwitchToMainFrameOperation;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class DetectRecaptchaResultAdditionalInfoExists extends BaseOperation{
	
	private WebDriverWait wait;
	private WebElement recaptchaPayloadIframe;
	private String recaptchaPayloadIframeHandle;
	private WebElement resultTextElement;
	
	@Override
	public boolean execute() throws OperationException{
		
		log("__DetectRecaptchaResultAdditionalInfoExists");
		try{
			switchToRecaptchaPayloadIFrame();
			findResultContainerElement();
		}
		catch(OperationException exception){
			logError("error. "+exception.getMessage());
			sleepThread();
			switchToMainIFrame();
			execute();
		}
		
		return true;
	}
	
	private void findResultContainerElement() {
		log("findResultContainerElement()");
		
		wait = new WebDriverWait(webDriver, Application.baseOperationWaitSecondsUntil);
		resultTextElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("rc-imageselect-incorrect-response")));
		
		log("resultTextElement="+resultTextElement);
		log("result text "+resultTextElement.getText());
	}

	private void switchToRecaptchaPayloadIFrame() throws OperationException{
		log("switchToRecaptchaPayloadIFrame by xPath "+ Page.getRecaptchaPayloadIFrameXPath());
		
		List<WebElement> framesInsideCollection = webDriver.findElements(By.tagName("iframe"));
		
		log("Total frames inside:"+framesInsideCollection.size());
		
		int recaptchaPayloadFrameIndex = -1;
		
		if(framesInsideCollection.size()>1){
			recaptchaPayloadFrameIndex = 1;
			recaptchaPayloadIframe = framesInsideCollection.get(1); // get second iframe
		}
		
		for (WebElement frame : framesInsideCollection) {
			String frameTitle = frame.getAttribute("title");
			log("FRAME -> " + frame.getAttribute("name")+"  title="+frameTitle);
		}
		
		log("switching to recaptchaPayloadIframe. title="+recaptchaPayloadIframe);
		try{
			webDriver.switchTo().frame(recaptchaPayloadFrameIndex); 
			recaptchaPayloadIframeHandle = webDriver.getWindowHandle();
			
			log("Switch OK. iframe. recaptchaPayloadIframeHandle="+recaptchaPayloadIframeHandle);
		}
		catch(StaleElementReferenceException exception){
			logError("StaleElementReferenceException. trying again...");
			wait = null;
			throw new OperationException(exception.getMessage());
		}
	}
	
	private void switchToMainIFrame() throws OperationException{
		SwitchToMainFrameOperation switchToMainFrameOperation = new SwitchToMainFrameOperation();
		switchToMainFrameOperation.setWebDriver(webDriver);
		switchToMainFrameOperation.execute();
	}

	
	private void sleepThread() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
