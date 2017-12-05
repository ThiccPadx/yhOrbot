package dev.div0.steps;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;

public class StepData {
	
	private int id;
	private String description;
	private String payload;
	private StepAction action;
	private String elementType;
	private ElementSearchType elementSearchType;
	private String elementSearchData;
	private boolean overrideHrefTarget;
	private WebDriver webDriver;
	private int selectionIndex;
	private String selectionValue;
	private String selectionVisibleText;
	private Point elementOffsetPoint;
	private boolean isMainFrame;
	private boolean isRecaptchaPayloadFrame;
	private boolean isRecaptchaImNotARobotCheckBoxContainerFrame;
	
	public StepData(int id, String description, String payload, StepAction action,
			ElementSearchType elementSearchType, String elementSearchData, 
			boolean overrideHrefTarget, int selectionIndex, String selectionValue, String selectionVisibleText, String elementType, 
			Point elementOffsetPoint, boolean isMainFrame, boolean isRecaptchaPayloadFrame, 
			boolean isRecaptchaImNotARobotCheckBoxContainerFrame){
		
		this.id = id;
		this.description = description;
		this.payload = payload;
		this.action = action;
		this.elementSearchType = elementSearchType;
		this.elementSearchData = elementSearchData;
		
		this.overrideHrefTarget = overrideHrefTarget;
		this.selectionIndex = selectionIndex;
		this.selectionValue = selectionValue;
		this.selectionVisibleText = selectionVisibleText;
		
		this.elementType = elementType;
		this.elementOffsetPoint = elementOffsetPoint;
		this.isMainFrame = isMainFrame;
		this.isRecaptchaPayloadFrame = isRecaptchaPayloadFrame;
		this.isRecaptchaImNotARobotCheckBoxContainerFrame = isRecaptchaImNotARobotCheckBoxContainerFrame;
	}
	
	public int getId() {
		return id;
	}
	public String getDescription() {
		return description;
	}
	public StepAction getAction() {
		return action;
	}
	public ElementSearchType getElementSearchType() {
		return elementSearchType;
	}
	public String getElementSearchData() {
		return elementSearchData;
	}
	
	public boolean isOverrideHrefTarget() {
		return overrideHrefTarget;
	}

	public int getSelectionIndex() {
		return selectionIndex;
	}

	public String getElementType() {
		return elementType;
	}

	public Point getElementOffsetPoint() {
		return elementOffsetPoint;
	}
	
	public boolean isMainFrame() {
		return isMainFrame;
	}
	public boolean isRecaptchaPayloadFrame() {
		return isRecaptchaPayloadFrame;
	}

	public boolean isRecaptchaImNotARobotCheckBoxContainerFrame() {
		return isRecaptchaImNotARobotCheckBoxContainerFrame;
	}

	public String getPayload() {
		return payload;
	}

	public String getSelectionValue() {
		return selectionValue;
	}

	public String getSelectionVisibleText() {
		return selectionVisibleText;
	}
}
