package dev.div0;

import dev.div0.events.EventDispatcher;
import dev.div0.logging.LogEvent;

import org.cometd.bayeux.server.BayeuxServer;
import org.cometd.server.AbstractService;

public class LoggableService extends AbstractService {

	public LoggableService(BayeuxServer bayeux, String name) {
		super(bayeux, name);
	}
	
	protected void log(String message){
		//System.out.println(message);
		//Application.logger.info(message);
		LogEvent logEvent = new LogEvent(LogEvent.LOG_MESSAGE);
		logEvent.setData(message);
		EventDispatcher.getInstance().dispatchEvent(logEvent);
		
	}
	protected void logError(String message){
		//System.out.println("ERROR: "+message);
		//Application.logger.info("  Error: "+ message); 
		
		LogEvent errorEvent = new LogEvent(LogEvent.LOG_ERROR);
		errorEvent.setData(message);
		EventDispatcher.getInstance().dispatchEvent(errorEvent);
	}

}
