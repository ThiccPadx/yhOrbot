package dev.div0.robotOperations;

import dev.div0.events.EventDispatcher;
import dev.div0.robotOperations.events.OperationEvent;

public class GetTextOperation extends FindElementAndDoOperation {
	
	String elementText;
	private OperationEvent operationEvent;
	
	@Override
	protected boolean doOperation() throws OperationException{
		findElementText();
		log("element text "+elementText);
		sendElementText();
		return true;
	}

	private void sendElementText() {
		operationEvent = new OperationEvent(OperationEvent.ELEMENT_TEXT);
		operationEvent.setData(elementText);
		EventDispatcher.getInstance().dispatchEvent(operationEvent);
	}

	private void findElementText() {
		elementText = getElementText();
	}

	private String getElementText() {
		return webElement.getText();
	}
}
