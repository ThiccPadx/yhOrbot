package dev.div0.events;

public class BaseEvent {
	private String type;
	private Object data;

	public BaseEvent(String type){
		this.type = type;
	}
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public Object getData() {
		return data;
	}


	public void setData(Object data) {
		this.data = data;
	}
}
