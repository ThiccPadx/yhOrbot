package dev.div0.robotOperations.operationData;

import dev.div0.steps.ElementSearchType;

import org.openqa.selenium.Point;

public class OperationData {
	private String  payload;
	private ElementSearchType elementSearchType;
	private boolean overrideHrefTarget = false; // false - as link is, true - always _self 
	private String elementSearchData;
	private int selectionIndex; // lists, dropdowns etc
	private String selectionValue; // lists, dropdowns etc
	private String selectionVisibleText; // lists, dropdowns etc
	private String elementType;
	private Point elementOffsetPoint;
	private boolean isMainFrame;
	private boolean isRecaptchaPayloadFrame;
	private boolean isRecaptchaImNotARobotCheckBoxContainerFrame;
	
	public ElementSearchType getElementSearchType() {
		return elementSearchType;
	}
	public void setElementSearchType(ElementSearchType elementSearchType) {
		this.elementSearchType = elementSearchType;
	}
	public String getElementSearchData() {
		return elementSearchData;
	}
	public void setElementSearchData(String elementSearchData) {
		this.elementSearchData = elementSearchData;
	}
	public boolean isOverrideHrefTarget() {
		return overrideHrefTarget;
	}
	public void setOverrideHrefTarget(boolean overrideHrefTarget) {
		this.overrideHrefTarget = overrideHrefTarget;
	}
	public int getSelectionIndex() {
		return selectionIndex;
	}
	public void setSelectionIndex(int selectionIndex) {
		this.selectionIndex = selectionIndex;
	}
	public String getElementType() {
		return elementType;
	}
	public void setElementType(String elementType) {
		this.elementType = elementType;
	}
	public Point getElementOffsetPoint() {
		return elementOffsetPoint;
	}
	public void setElementOffsetPoint(Point elementOffsetPoint) {
		this.elementOffsetPoint = elementOffsetPoint;
	}
	public boolean isMainFrame() {
		return isMainFrame;
	}
	public void setMainFrame(boolean isMainFrame) {
		this.isMainFrame = isMainFrame;
	}
	public boolean isRecaptchaPayloadFrame() {
		return isRecaptchaPayloadFrame;
	}
	public void setRecaptchaPayloadFrame(boolean isRecaptchaPayloadFrame) {
		this.isRecaptchaPayloadFrame = isRecaptchaPayloadFrame;
	}
	public boolean isRecaptchaImNotARobotCheckBoxContainerFrame() {
		return isRecaptchaImNotARobotCheckBoxContainerFrame;
	}
	public void setRecaptchaImNotARobotCheckBoxContainerFrame(
			boolean isRecaptchaImNotARobotCheckBoxContainerFrame) {
		this.isRecaptchaImNotARobotCheckBoxContainerFrame = isRecaptchaImNotARobotCheckBoxContainerFrame;
	}
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	public String getSelectionValue() {
		return selectionValue;
	}
	public void setSelectionValue(String selectionValue) {
		this.selectionValue = selectionValue;
	}
	public String getSelectionVisibleText() {
		return selectionVisibleText;
	}
	public void setSelectionVisibleText(String selectionVisibleText) {
		this.selectionVisibleText = selectionVisibleText;
	}
}
