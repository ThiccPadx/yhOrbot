package dev.div0.robotOperations.recaptcha;

import java.util.List;

import dev.div0.Application;

import dev.div0.application.page.Page;
import dev.div0.robotOperations.BaseOperation;
import dev.div0.robotOperations.OperationException;
import dev.div0.robotOperations.recaptcha.data.RecaptchaData;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ClickRecaptchaImagesOperation extends BaseOperation {

	private WebDriverWait wait;
	 
	private WebElement imagesTable;
	
	private WebElement errorMessageContainerElement;

	private List<WebElement> images;
	
	private String recaptchaResultAdditionalInfo;
	
	public boolean execute() throws OperationException{
		//log("Main iframe handle = "+Page.getMainIFrameHandle()+"  recaptcha iframe handle="+Page.getRecaptchaPayloadIFrameHandle());
		//log("im not a robot check box container iframe xpath="+Page.getImNotARobotCheckBoxContainerIFrameXPath());
		
		getImagesTable();
		getImages();
		
		int i;
		int totalClicks = RecaptchaData.getClicks().length;
		
		for(i = 0; i < totalClicks; i++){
			clickImage(RecaptchaData.getClicks()[i]-1);
			sleepThread();
		}
		return true;
	}
	
	private void getResultAdditionalInfo() throws OperationException{
		//switchToMainIFrame();
		boolean resultAdditionalInfoExists = detectResultAdditionalInfoExists();
		log("resultAdditionalInfoExists="+resultAdditionalInfoExists);
	}
	
	private boolean detectResultAdditionalInfoExists() throws OperationException{
		DetectRecaptchaResultAdditionalInfoExists operation = new DetectRecaptchaResultAdditionalInfoExists();
		operation.setWebDriver(webDriver);
		operation.execute();
		return operation.execute();
	}

	private void getImagesTable() {
		//log("getImagesTable xPath="+Page.getRecaptchaPayloadImagesTableXPath());
		imagesTable = webDriver.findElement(By.xpath(Page.getRecaptchaPayloadImagesTableXPath()));
		//log("imagesTable="+imagesTable+"  class = "+imagesTable.getAttribute("class"));
	}

	private void clickImage(int index) {
		log("click image "+index);
		Actions builderImagem1 = new Actions(webDriver);
		builderImagem1.moveToElement(images.get(index)).perform();
		images.get(index).click();
		builderImagem1 = null;
	}

	private void getImages() {
		wait = new WebDriverWait(webDriver, Application.baseOperationWaitSecondsUntil);
		images = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(Page.getRecaptchaPayloadImagesXPath())));
		log("total images: "+images.size());
	}

	private void sleepThread() {
		try {
			Thread.sleep(Application.baseOperationTimeoutMilliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
