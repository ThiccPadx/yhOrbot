package dev.div0.steps;

import dev.div0.robotOperations.BaseOperation;
import dev.div0.robotOperations.OperationException;
import dev.div0.robotOperations.factory.OperationFactory;
import dev.div0.robotOperations.operationData.OperationData;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriverException;

// TODO remove many parameters from constructor - anti pattern !!!. Use OperationData class to send parameters
public class Step extends BaseStep{
	
	private BaseOperation stepOperation;
	
	public Step(StepData stepData) {
		super(stepData);
	}

	public boolean start() throws StepException{
		//sendStepInfo("step "+stepData.getId()+" start. type="+stepData.getAction()+"  "+stepData.getDescription()+"  selectionIndex="+stepData.getSelectionIndex()+"  selectionValue="+stepData.getSelectionValue()+"  selectionVisibleText="+stepData.getSelectionVisibleText());
		log("step "+stepData.getId()+" start.");
		//sendStepInfo("Starting step");
		
		OperationFactory operationFactory = new OperationFactory();
		stepOperation = operationFactory.getOperation(stepData.getAction());
		
		if(stepOperation!=null){
			stepOperation.setWebDriver(webDriver);
			
			OperationData operationData = new OperationData();
			operationData.setPayload(stepData.getPayload());
			operationData.setElementSearchData(stepData.getElementSearchData());
			operationData.setElementSearchType(stepData.getElementSearchType());
			operationData.setOverrideHrefTarget(stepData.isOverrideHrefTarget());
			
			operationData.setSelectionIndex(stepData.getSelectionIndex());
			operationData.setSelectionValue(stepData.getSelectionValue());
			operationData.setSelectionVisibleText(stepData.getSelectionVisibleText());
			
			operationData.setElementType(stepData.getElementType());
			operationData.setElementOffsetPoint(stepData.getElementOffsetPoint());
			operationData.setMainFrame(stepData.isMainFrame());
			operationData.setRecaptchaPayloadFrame(stepData.isRecaptchaPayloadFrame());
			operationData.setRecaptchaImNotARobotCheckBoxContainerFrame(stepData.isRecaptchaImNotARobotCheckBoxContainerFrame());
			stepOperation.setOperationData(operationData);
			
			try{
				boolean operationResult = stepOperation.execute();
				return operationResult;
			}
			catch(OperationException exception){
				System.err.println("Operation id:"+stepData.getId()+" error: "+exception.getMessage());
			}
		}
		else{
			throw new StepException("Step operation not set - nothing to do", stepData.getId());
		}
		return false;
	}
	
	@Override
	public void destroy(){
		//log("current step destroy");
		stepOperation.destroy();
		//log("step destroy OK");
	}
}
