package dev.div0.robotOperations;

import java.util.List;

import dev.div0.application.page.Page;
import dev.div0.events.EventDispatcher;
import dev.div0.robotOperations.events.OperationEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

public class SelectCalendarAvailableDateOperation extends InfinitlySolveRecaptchaOperation {
	private WebElement nextMonthButton;
	private WebElement prevMonthButton;
	
	private List<WebElement> availableDateElements;
	
	private int maxDepth = 5;
	private int currentDepth = 0;
	
	private WebElement selectedMonthElement;
	private String selectedMonth;
	private String selectedDayNumOfTheMonth;
	
	private WebElement firstAvailableDate;
	private OperationEvent operationEvent;
	private boolean dateSelected = false;
	
	@Override
	protected boolean doOperation() throws OperationException{
		getMonthControls();
		getMonthName();
		log("Selected month: "+selectedMonth);
		
		findAvailableDates();
		
		return true;
	}

	private void getMonthName() {
		selectedMonthElement = webDriver.findElement(By.xpath(Page.getCalendarSelectedMonthNameXPath()));
		selectedMonth = selectedMonthElement.getText();
	}

	private void selectNextMonth() throws OperationException{
		currentDepth++;
		if(currentDepth < maxDepth){
			timeout(2);
			
			log("selecting next month");
			nextMonthButton.click();
			
			execute();
			findAvailableDates();
		}
		else{
			throw new OperationException("No dates available for next "+(maxDepth-1)+" months");
		}
	}

	private void findAvailableDates() throws OperationException{
		
		if(dateSelected == false){
			availableDateElements = webDriver.findElements(By.className(Page.getCalendarOpenDateAllocatedClass()));
			
			int totalAvailableDates = availableDateElements.size();
			
			if(availableDateElements!=null && totalAvailableDates > 0){
				log("Available dates exist. total "+totalAvailableDates);
				
				solveRecaptcha();
				
				selectDate();
				dateSelected = true; // �������. ��������� ��� ���� ���������� ��� !!!
			}
			else{
				log("NO available dates for "+selectedMonth);
				selectNextMonth();
			}
		}
	}

	private void selectDate() throws OperationException{
		firstAvailableDate = availableDateElements.get(0);
		saveSelectedDate();
		clickSelectedDate();
	}

	private void saveSelectedDate() {
		selectedDayNumOfTheMonth = firstAvailableDate.getText();
		
		operationEvent = new OperationEvent(OperationEvent.MONTH_SELECTED);
		operationEvent.setData(selectedMonth);
		EventDispatcher.getInstance().dispatchEvent(operationEvent);
		
		operationEvent = new OperationEvent(OperationEvent.DATE_SELECTED);
		operationEvent.setData(selectedDayNumOfTheMonth);
		EventDispatcher.getInstance().dispatchEvent(operationEvent);
	}

	//TODO ��� ����� ��������� � ClickElementOperation � ��� ������� ������� ��������.
	// ����� ������ ��������� ��� ������ ������ ����� ������. ����� ����.
	private void clickSelectedDate() throws OperationException{
		log("clicking selected date");
		try{
			firstAvailableDate.click();
		}
		catch(TimeoutException timeoutException){
			throw new OperationException("TimedOut");
		}
	}

	private void getMonthControls() throws OperationException{
		getNextMonthButton();
		getPrevMonthButton();
	}

	// No need to throw Operation exception
	private void getPrevMonthButton() {
		try{
			prevMonthButton = webDriver.findElement(By.xpath(Page.getCalendarPrevMonthButtonXPath()));
		}
		catch(NoSuchElementException exception){
			System.out.println("Calendar does not has prev button");
		}
	}

	// Next button must be at any time
	private void getNextMonthButton() throws OperationException{
		try{
			nextMonthButton = webDriver.findElement(By.xpath(Page.getCalendarNextMonthButtonXPath()));
		}
		catch(NoSuchElementException exception){
			throw new OperationException(exception.getMessage());
		}
	}
}
