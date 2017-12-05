package dev.div0.robotOperations;

import dev.div0.Application;
import dev.div0.application.page.Page;
import dev.div0.steps.ElementSearchType;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class FindElementAndDoOperation extends BaseOperation {
	
	protected ElementSearchType elementSearchType;
	protected WebElement webElement;
	protected boolean isFindElementByHref;
	protected boolean isFindElementById;
	protected boolean isFindElementByCssClass;
	protected boolean isFindElementByXPath;
	protected boolean isFindElementByName;
	int elementSearchInterval = Application.baseOperationWaitSecondsUntil;
	
	private final Object waitMonitor = new Object();
	
	@Override
	public boolean execute() throws OperationException{
		log("FindElementAndDoOperation execute. elementSearchType="+operationData.getElementSearchType()+"  elementSearchData="+operationData.getElementSearchData());
		
		if(operationData.getElementSearchType() == null){
			throw new OperationException("FindElementAndDoOperation element search type not set");
		}
		else if(operationData.getElementSearchData() == null){
			throw new OperationException("FindElementAndDoOperation data not set");
		}
		else{
			try{
				findElement();
				updatePageData();
				boolean operationResult = doOperation();
				return operationResult;
			}
			catch(OperationException exception){
				throw new OperationException(exception.getMessage());
			}
		}
	}
	
	private void updatePageData() {
		if(operationData.isMainFrame()){
			Page.setMainIFrameHandle(webDriver.getWindowHandle());
		}
		if(operationData.isRecaptchaPayloadFrame()){
			Page.setRecaptchaPayloadIFrameHandle(webDriver.getWindowHandle());
		}
	}

	protected boolean findElement() throws OperationException{
		elementSearchType = operationData.getElementSearchType();
		log("elementSearchType="+elementSearchType);
		
		isFindElementByHref = elementSearchType.equals(ElementSearchType.BY_HREF);
		isFindElementById = elementSearchType.equals(ElementSearchType.BY_ID);
		isFindElementByCssClass = elementSearchType.equals(ElementSearchType.BY_CSS_CLASS);
		isFindElementByXPath = elementSearchType.equals(ElementSearchType.BY_XPATH);
		isFindElementByName = elementSearchType.equals(ElementSearchType.BY_NAME);
		
		if(isFindElementByHref == true){
			try{
				findElementByHref();
				return true;
			}
			catch(Exception exception){
				throw new OperationException("FindElementAndDoOperation by Href error. "+exception.getMessage());
			}
		}
		else if(isFindElementById == true){
			try{
				findElementById();
				return true;
			}
			catch(Exception exception){
				throw new OperationException("FindElementAndDoOperation by ID error. "+exception.getMessage());
			}
		}
		else if(isFindElementByCssClass == true){
			try{
				findElementByCssClass();
				return true;
			}
			catch(Exception exception){
				throw new OperationException("FindElementAndDoOperation by cssClass error. "+exception.getMessage());
			}
		}
		else if(isFindElementByXPath == true){
			try{
				findElementByXPath();
				return true;
			}
			catch(Exception exception){
				throw new OperationException("FindElementAndDoOperation by XPath error. "+exception.getMessage());
			}
		}
		else if(isFindElementByName == true){
			try{
				findElementByName();
				return true;
			}
			catch(Exception exception){
				throw new OperationException("FindElementAndDoOperation by name error. "+exception.getMessage());
			}
		}
		else{
			throw new OperationException("FindElementAndDoOperation element search type incorrect. Possible values: 'ElementSearchType.BY_HREF','ElementSearchType.BY_ID', 'ElementSearchType.BY_CSS_CLASS' etc");
		}
	}

	private void findElementByName() throws OperationException {
		String name = operationData.getElementSearchData();
		try{
			webElement = webDriver.findElement(By.name(name));
		}
		catch(Exception exception){
			throw new OperationException(exception.getMessage());
		}
	}

	private void findElementByHref() throws OperationException{
		String selectorRegexp = "a[href*='"+operationData.getElementSearchData()+"']";
		
		//WebDriverWait wait = new WebDriverWait(webDriver, 20);
		//webElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(selectorRegexp)));
		
		try{
			webElement = webDriver.findElement(By.cssSelector(selectorRegexp));
		}
		catch(Exception exception){
			throw new OperationException(exception.getMessage());
		}
	}
	
	private void findElementById() throws OperationException{
		String id = operationData.getElementSearchData();
		log("FindElementAndDoOperation findElementById() id="+id);
		/*
		WebDriverWait wait = new WebDriverWait(webDriver, elementSearchInterval);
		try{
			webElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
		}
		catch(Exception exception){
			throw new OperationException(exception.getMessage());
		}
		*/
		webElement = webDriver.findElement(By.id(id));
	}
	private void findElementByCssClass() throws OperationException{
		String className = operationData.getElementSearchData();
		try{
			webElement = webDriver.findElement(By.className(className));
		}
		catch(Exception exception){
			throw new OperationException(exception.getMessage());
		}
	}
	
	protected void findElementByXPath() throws OperationException{
		String xPath = operationData.getElementSearchData();


		WebDriverWait wait = new WebDriverWait(webDriver, elementSearchInterval);
		
		//log("WAITING 5 seconds");
		//timeout(1);
		
		try{
			webElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
		}
		catch(Exception exception){
			throw new OperationException(exception.getMessage());
		}

		/*webElement = webDriver.findElement(By.xpath(xPath));*/
	}
	
	protected boolean doOperation() throws OperationException{
		return false;
	}
	
	private void timeout(int seconds){
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
