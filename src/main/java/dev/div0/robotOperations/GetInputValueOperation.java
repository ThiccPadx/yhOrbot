package dev.div0.robotOperations;


public class GetInputValueOperation extends FindElementAndDoOperation{

    String elementValue;

    @Override
    protected boolean doOperation() throws OperationException{
        findElementValue();
        return true;
    }

    private void findElementValue() {
        elementValue = webElement.getAttribute("value");
    }

    public String getValue(){
        return elementValue;
    }
}
