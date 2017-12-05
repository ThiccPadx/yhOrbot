package dev.div0.logging;

import dev.div0.events.BaseEvent;



public class LogEvent extends BaseEvent {

	public static String LOG_MESSAGE = "LOG_MESSAGE";
	public static String LOG_ERROR = "LOG_ERROR";
	
	
	public LogEvent(String type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

}
