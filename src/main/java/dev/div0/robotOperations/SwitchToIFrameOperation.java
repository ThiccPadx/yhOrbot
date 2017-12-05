package dev.div0.robotOperations;

public class SwitchToIFrameOperation extends FindElementAndDoOperation {
	
	@Override
	protected boolean doOperation() throws OperationException{
		//log("switch to iframe title:"+webElement.getAttribute("title"));
		webDriver.switchTo().frame(webElement);
		
		//log("SwitchToIFrameOperation isMainFrame="+operationData.isMainFrame());
		//log("SwitchToIFrameOperation isRecaptchaPayloadFrame="+operationData.isRecaptchaPayloadFrame());
		//log("SwitchToIFrameOperation isRecaptchaImNotARobotCheckBoxContainerFrame="+operationData.isRecaptchaImNotARobotCheckBoxContainerFrame());
		
		/*
		if(operationData.isMainFrame()){
			Page.setMainIFrameHandle(webDriver.getWindowHandle());
		}
		if(operationData.isRecaptchaPayloadFrame()){
			Page.setRecaptchaPayloadIFrameHandle(webDriver.getWindowHandle());
		}
		if(operationData.isRecaptchaImNotARobotCheckBoxContainerFrame()){
			Page.setRecaptchaImNotARobotCheckBoxContainerFrameHandle(webDriver.getWindowHandle());
		}
		*/
		
		//webDriver.switchTo().frame(0);  // assuming this is the first frame on the page
		return true;
	}
}
