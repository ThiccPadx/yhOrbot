package dev.div0.events;


public class ApplicationEvent extends BaseEvent {

	public static String OPEN_URL = "OPEN_URL";
	public static String CLICK_LINK = "CLICK_LINK";
	
	public ApplicationEvent(String type) {
		super(type);
	}

}
