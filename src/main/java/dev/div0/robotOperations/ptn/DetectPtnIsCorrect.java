package dev.div0.robotOperations.ptn;

import dev.div0.application.page.Page;
import dev.div0.events.EventDispatcher;
import dev.div0.robotOperations.FindElementAndDoOperation;
import dev.div0.robotOperations.OperationException;

public class DetectPtnIsCorrect extends FindElementAndDoOperation {

	@Override
	protected boolean doOperation() throws OperationException{
		
		log("DetectPtnIsCorrect");
		log("webElement = "+webElement+"   text="+webElement.getText());
		
		String elementText = webElement.getText();
		
		boolean ptnIsUsedError = elementText.toLowerCase().contains(Page.getPtnIsUsedErrorText());
		boolean ptnNotFoundError = elementText.toLowerCase().contains(Page.getPtnNotFoundErrorText());
		
		
		if(ptnIsUsedError == true){
			PTNErrorEvent event = new PTNErrorEvent(PTNErrorEvent.IS_USED);
			event.setData(elementText);
			EventDispatcher.getInstance().dispatchEvent(event);
			return false;
		}
		else if(ptnNotFoundError == true){
			PTNErrorEvent event = new PTNErrorEvent(PTNErrorEvent.NOT_FOUND);
			event.setData(elementText);
			EventDispatcher.getInstance().dispatchEvent(event);
			return false;
		}
		
		return true;
	}
}
