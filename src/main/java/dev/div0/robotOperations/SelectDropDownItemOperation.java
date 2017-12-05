package dev.div0.robotOperations;


import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.Select;


public class SelectDropDownItemOperation extends FindElementAndDoOperation 
{
	private Select selectElement;
	
	@Override
	protected boolean doOperation() throws OperationException{
		//log("SelectDropDownItem webElement="+webElement+" SelectionIndex="+operationData.getSelectionIndex()+"  visible text = "+operationData.getSelectionVisibleText());
		
		selectElement = new Select(webElement);
		
		boolean isSelectByValue = !operationData.getSelectionValue().equals("null");
		boolean isSelectByVisibleText = !operationData.getSelectionVisibleText().equals("null");
		
		if(isSelectByVisibleText == true){
			selectByVisibleText();
		}
		else{
			selectByIndex();
		}
		return true;
	}

	private void selectByIndex() throws OperationException{
		try{
			selectElement.selectByIndex(operationData.getSelectionIndex());
		}
		catch(NoSuchElementException exception){
			throw new OperationException(exception.getMessage());
		}
	}

	private void selectByVisibleText() throws OperationException{
		try{
			selectElement.selectByVisibleText(operationData.getSelectionVisibleText());
		}
		catch(NoSuchElementException exception){
			throw new OperationException(exception.getMessage());
		}
	}
}
