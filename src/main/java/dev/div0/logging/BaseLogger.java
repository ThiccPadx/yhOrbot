package dev.div0.logging;

import dev.div0.events.EventDispatcher;


public class BaseLogger {
	protected void log(String message){ 
		LogEvent logEvent = new LogEvent(LogEvent.LOG_MESSAGE);
		logEvent.setData(message);
		EventDispatcher.getInstance().dispatchEvent(logEvent);
	}
	protected void logError(String message){		
		LogEvent logEvent = new LogEvent(LogEvent.LOG_ERROR);
		logEvent.setData(message);
		EventDispatcher.getInstance().dispatchEvent(logEvent);
	}
}
