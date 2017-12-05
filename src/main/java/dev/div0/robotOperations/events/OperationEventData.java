package dev.div0.robotOperations.events;

import org.openqa.selenium.Point;

public class OperationEventData {
	private Point coordinates;
	private String elementType;
	
	
	public OperationEventData(Point coordinates, String elementType){
		this.coordinates = coordinates;
		this.elementType = elementType;
	}
	
	public Point getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(Point coordinates) {
		this.coordinates = coordinates;
	}
	public String getElementType() {
		return elementType;
	}
	public void setElementType(String elementType) {
		this.elementType = elementType;
	}
	
	
}
