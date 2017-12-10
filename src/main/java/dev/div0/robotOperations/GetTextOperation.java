package dev.div0.robotOperations;

import dev.div0.events.EventDispatcher;
import dev.div0.robotOperations.events.OperationEvent;

public class GetTextOperation extends FindElementAndDoOperation {
	
	String elementText;
	private OperationEvent operationEvent;
	
	@Override
	protected boolean doOperation() throws OperationException{
		findElementText();
		sendElementTextResult();
		return true;
	}

	private void sendElementTextResult() {
		operationEvent = new OperationEvent(OperationEvent.ELEMENT_TEXT_RESULT);
		operationEvent.setData(elementText);
		EventDispatcher.getInstance().dispatchEvent(operationEvent);
	}

	private void findElementText() {
		elementText = getElementText();
	}

	protected String getElementText() {
		return webElement.getText();
	}
}
