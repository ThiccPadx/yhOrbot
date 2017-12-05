package dev.div0.task;

import dev.div0.events.BaseEvent;

public class TaskParserEvent extends BaseEvent {
	
	public static String TASK_ID = "TASK_ID";
	public static String IS_MANUAL_TASK_FINISH = "IS_MANUAL_TASK_FINISH";
	
	public TaskParserEvent(String type) {
		super(type);
	}
}
