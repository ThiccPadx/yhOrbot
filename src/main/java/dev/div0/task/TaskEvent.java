package dev.div0.task;

import dev.div0.events.BaseEvent;


public class TaskEvent extends BaseEvent {

	public static String TASK_COMPLETE = "TASK_COMPLETE";
	public static String TASK_DESTROY_COMPLETE = "TASK_DESTROY_COMPLETE";
	public static String TASK_ERROR = "TASK_ERROR";
	public static String STEP_COMPLETE = "STEP_COMPLETE";
	
	public TaskEvent(String type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

}
