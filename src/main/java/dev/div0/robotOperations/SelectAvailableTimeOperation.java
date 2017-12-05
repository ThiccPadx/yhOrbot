package dev.div0.robotOperations;

import java.util.Iterator;
import java.util.List;

import dev.div0.events.EventDispatcher;
import dev.div0.robotOperations.events.OperationEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class SelectAvailableTimeOperation extends FindElementAndDoOperation {
	
	private List<WebElement> availableElements;
	private WebElement selectedTimeElement;
	private String selectedTime;
	
	@Override
	protected boolean doOperation() throws OperationException{
		log("SelectAvailableTimeOperation");
		findElements();
		showAllValues();
		
		if(availableElements.size() > 0){
			selectFirstAvailableTime();
			saveSelectedTime();
			
			clickSelectedTime();
			log("selected time "+selectedTime);
		}
		else{
			throw new OperationException("No availble time values");
		}
		
		return true;
	}

	private void clickSelectedTime() {
		log("click selected time. Clicking "+selectedTime);
		//((JavascriptExecutor)webDriver).executeScript("alert('This is debug version. Selected time "+selectedTime+". Click on time not executed now. You cannot see this alert in production version. Selected time click executes also in production version.')"); 
		//webDriver.switchTo().alert();
		
		//selectedTimeElement.click();
	}

	private void saveSelectedTime() {
		OperationEvent operationEvent = new OperationEvent(OperationEvent.TIME_SELECTED);
		operationEvent.setData((String)selectedTime);
		EventDispatcher.getInstance().dispatchEvent(operationEvent);
	}

	private void selectFirstAvailableTime() {
		selectedTimeElement = availableElements.get(0);
		selectedTime = selectedTimeElement.getText();
	}

	private void showAllValues() {
		Iterator<WebElement> iterator = availableElements.iterator();
		while(iterator.hasNext()){
			WebElement element = iterator.next();
			log("Link: "+element.getText());
		}
	}

	private void findElements() {
		availableElements = webDriver.findElements(By.tagName("a"));
	}
}
