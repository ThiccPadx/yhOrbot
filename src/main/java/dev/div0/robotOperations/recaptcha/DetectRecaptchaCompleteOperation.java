package dev.div0.robotOperations.recaptcha;

import dev.div0.Application;
import dev.div0.application.page.Page;
import dev.div0.robotOperations.BaseOperation;
import dev.div0.robotOperations.OperationException;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class DetectRecaptchaCompleteOperation extends BaseOperation{
	
	private WebDriverWait wait;
	private WebElement imNotARobotCheckBoxElement;
	
	@Override
	public boolean execute() throws OperationException{
		log("DetectRecaptchaCompleteOperation");

		wait = new WebDriverWait(webDriver, Application.baseOperationWaitSecondsUntil);

		//WebElement imNotARobotCheckBoxElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(Page.getRecaptchaCheckBoxID())));
		//WebElement imNotARobotCheckBoxElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Page.getRecaptchaCheckBoxID())));


		WebElement imNotARobotCheckBoxElement = webDriver.findElement(By.id(Page.getRecaptchaCheckBoxID()));

		log("imNotARobotCheckBoxElement="+imNotARobotCheckBoxElement);

		setImNotARobotCheckBoxElement(imNotARobotCheckBoxElement);
		
		//log("imNotARobotCheckBoxElement="+getImNotARobotCheckBoxElement());
		//log("waiting 5 seconds to get class attr");
		
		//sleepThreed();
		//log("Wait complete. Class = "+imNotARobotCheckBoxElement.getAttribute("class"));

		boolean recaptchaComplete = false;
		wait = new WebDriverWait(webDriver,10);

		try {
			log("Waiting 16 seconds...");
			Thread.sleep(16000);

			return getImNotARobotCheckBoxElement().getAttribute("class").contains(Page.getRecaptchaCheckBoxCorrectStyle());

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try{
			recaptchaComplete = wait.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return getImNotARobotCheckBoxElement().getAttribute("class").contains(Page.getRecaptchaCheckBoxCorrectStyle());
				}
			});
		}
		catch(TimeoutException exception){
			System.out.println("TimedOut - recaptcha not complete"); // no need to throw OperationException because of in this case captcha can be not solved yet
			//throw new OperationException("TimedOut - recaptcha not complete");
		}
		return recaptchaComplete;
	}

	public WebElement getImNotARobotCheckBoxElement() {
		return imNotARobotCheckBoxElement;
	}

	public void setImNotARobotCheckBoxElement(WebElement imNotARobotCheckBoxElement) {
		this.imNotARobotCheckBoxElement = imNotARobotCheckBoxElement;
	}
}
