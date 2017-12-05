package dev.div0.steps;

import dev.div0.logging.BaseLogger;
import dev.div0.robotOperations.IDestroyable;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;

public class BaseStep extends BaseLogger implements IDestroyable{
	protected StepData stepData;
	protected WebDriver webDriver;
	
	public BaseStep(StepData stepData){
		this.stepData = stepData;
		log("STEP: id="+stepData.getId()+"  description = "+stepData.getDescription()+"  elementType="+stepData.getElementType());
	}
	
	public int getId() {

		return stepData.getId();
	}
	
	public String getDescription() {
		return stepData.getDescription();
	}
	//public String getPayload() {
		//return stepData.getPa
	//}
	public String getPayload() {
		return stepData.getPayload();
	}
	
	public StepAction getType() {
		return stepData.getAction();
	}

	public ElementSearchType getElementSearchType() {
		return stepData.getElementSearchType();
	}
	
	public String getElementSearchData() {
		return stepData.getElementSearchData();
	}
	
	public void setWebDriver(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	protected void sendMessage(String message){
		log(message);
		//clientServerSession.deliver(Server.serverSession, "/message",message);
	}
	protected void sendStepInfo(String info){
		log(info);
		/*
		if(clientServerSession!=null && Server.serverSession!=null){
			JSONObject sendData = new JSONObject();
	        sendData.put("step", id);
	        sendData.put("info", info);
			clientServerSession.deliver(Server.serverSession, "/stepInfo", sendData.toString());
		}
		*/
	}

	public boolean isOverrideHrefTarget() {
		return stepData.isOverrideHrefTarget();
	}

	public int getSelectionIndex() {
		return stepData.getSelectionIndex();
	}

	public String getElementType() {
		return stepData.getElementType();
	}

	public Point getElementOffsetPoint() {
		return stepData.getElementOffsetPoint();
	}

	public boolean isMainFrame() {
		return stepData.isMainFrame();
	}

	public boolean isRecaptchaPayloadFrame() {
		return stepData.isRecaptchaPayloadFrame();
	}

	public boolean isRecaptchaImNotARobotCheckBoxContainerFrame() {
		return stepData.isRecaptchaImNotARobotCheckBoxContainerFrame();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
