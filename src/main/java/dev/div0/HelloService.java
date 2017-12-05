package dev.div0;


import dev.div0.events.EventDispatcher;
import dev.div0.logging.LogEvent;
import dev.div0.logging.ServerInfoLogger;

import org.cometd.bayeux.server.BayeuxServer;
import org.cometd.bayeux.server.ServerMessage;
import org.cometd.bayeux.server.ServerSession;
import org.cometd.server.AbstractService;

public class HelloService extends AbstractService
{
	//private static Logger log = (Logger)LogManager.getLogger(HelloService.class);
	
	private Application application;
	
    public HelloService(BayeuxServer bayeux)
    {
        super(bayeux, "hello");
        System.out.println("HelloService");
        
       
        Server.bayeux = bayeux;
        Server.serverSession = getServerSession();
        
        createApplication();
        
        addService("/service/newTask", "onNewTask");
        addService("/service/cancelTask", "onCancelTask");
    }

    private void createApplication() 
    {
    	application = new Application();
	}
    
    
    public void onCancelTask(ServerSession remote, ServerMessage message){
    	log("\n\n\n!!!! cancel task request.");
    	application.onCancelTask(remote);
    }
    
    public void onNewTask(ServerSession remote, ServerMessage message)
    {
    	String taskData;
    	log("new task request.");
    	
    	if(message.getData()!=null){
    		taskData = message.getData().toString();
    		log("taskData: "+taskData);
    		application.onNewTask(remote, taskData);
    	}
    	else{
    		logError("Task data is NULL");
    		return;
    	}
    }
    
    private void log(String message){
		//System.out.println(message);
		//Application.logger.info(message);
		LogEvent logEvent = new LogEvent(LogEvent.LOG_MESSAGE);
		logEvent.setData(message);
		EventDispatcher.getInstance().dispatchEvent(logEvent);
	}
    private void logError(String message){
		//System.out.println("ERROR: "+message);
		//Application.logger.info("  Error: "+ message); 
		
		LogEvent errorEvent = new LogEvent(LogEvent.LOG_ERROR);
		errorEvent.setData(message);
		EventDispatcher.getInstance().dispatchEvent(errorEvent);
	}
    

    /*
	private void log(String message){
		//System.out.println(message);
		//Application.logger.info(message);
		
	}
    private void logError(String message){
		//System.out.println("ERROR: "+message);
		//Application.logger.info("  Error: "+ message); 
	}
	*/
}
