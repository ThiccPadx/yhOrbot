package dev.div0.robotOperations;

import dev.div0.Application;

import dev.div0.application.page.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SwitchToMainFrameOperation extends BaseOperation{
	
	private WebElement mainIframe; 
	private WebDriverWait wait;
	
	@Override
	public boolean execute() throws OperationException{
		switchToRoot();
		
		sleepThread();
		log("switchToMainIFrame by xPath "+ Page.getMainIframeXPath());
		
		wait = new WebDriverWait(webDriver, Application.baseOperationWaitSecondsUntil);
		
		try{
			mainIframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Page.getMainIframeXPath())));
			webDriver.switchTo().frame(mainIframe);
			//log("main frame switch OK");
		}
		catch(StaleElementReferenceException exception){
			log("main frame switch error. StaleElementReferenceException - trying again");
			wait = null;
			sleepThread();
			execute();
		}
		catch(Exception exception){
			throw new OperationException("Exception.  "+exception.getMessage());
		}
		return true;
	}

	private void switchToRoot() {
		log("switch to root");
		webDriver.switchTo().defaultContent();
	}
	
	private void sleepThread() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
