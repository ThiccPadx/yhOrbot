package dev.div0.robotOperations.ptn;

import dev.div0.events.BaseEvent;

public class PTNErrorEvent extends BaseEvent {

	public static String IS_USED = "IS_USED";
	public static String NOT_FOUND = "NOT_FOUND";
	
	
	public PTNErrorEvent(String type) {
		super(type);
	}

}
