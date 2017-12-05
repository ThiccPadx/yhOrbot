package dev.div0.robotOperations;

import java.util.concurrent.TimeUnit;

import dev.div0.task.Task;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class DetectDatesAvailableForSelectedCityOperation extends FindElementAndDoOperation {
	
	private WebElement subFrameErrorElement = null;
	private String subFrameErrorId = "sub-frame-error";
	private WebDriverWait wait;
	
	@Override
	public boolean execute() throws OperationException{
		
		log("DetectDatesAvailableForSelectedCityOperation");
		log("xpath="+operationData.getElementSearchData());
		
		detectInternetConnectionLags();
		
		try{
			findElement();
		}
		catch(OperationException exception){
			log("NO SUCH ELEMENT by xpath "+operationData.getElementSearchData());
		}
		
		if(webElement==null){
			Task.datesAvailable = true;
			log("Dates available for selected city");
		}
		else{
			log("NO dates available for selected city");
		}
		return true;
	}
	
	private void detectInternetConnectionLags() throws OperationException{
		System.out.println("findSubFrameErrorElement");
		try{
			subFrameErrorElement = webDriver.findElement(By.id(subFrameErrorId));
			throw new OperationException("element by id "+subFrameErrorId+"  found ! It is Internet or connection lag.");
		}
		catch(NoSuchElementException exceprion){
			System.out.println("Element by id "+subFrameErrorId+"  not found. Internet lags not detected. All OK");
		}
	}

	@Override
	protected boolean findElement() throws OperationException{
		findElementByXPath();
		return true;
	}
	
	
	@Override
	protected void findElementByXPath() throws OperationException{
		String xPath = operationData.getElementSearchData();
		
		wait = new WebDriverWait(webDriver, elementSearchInterval);
		try{
			webElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
		}
		catch(TimeoutException timeoutException){
			throw new OperationException(timeoutException.getMessage());
		}
	}
}
