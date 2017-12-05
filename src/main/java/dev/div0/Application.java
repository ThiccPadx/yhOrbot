package dev.div0;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import dev.div0.application.page.Anchors;
import net.marketer.RuCaptcha;

import org.cometd.bayeux.server.ServerChannel;
import org.cometd.bayeux.server.ServerSession;
import org.openqa.selenium.Point;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import dev.div0.events.BaseEvent;
import dev.div0.events.EventDispatcher;
import dev.div0.events.IEventListener;
import dev.div0.logging.LogEvent;
import dev.div0.logging.SimplestFormatter;

import dev.div0.robotOperations.recaptcha.data.RecaptchaData;
import dev.div0.task.Task;
import dev.div0.task.TaskEvent;
import dev.div0.task.TaskResult;
import org.json.simple.JSONObject;

public class Application implements IEventListener{
	
	private String version = "0.7.3";
	
	//private Tasks tasks = new Tasks();
	private Task currentTask;
	private Thread taskThread;
	private String serverSessionId;
	
	public static Logger logger;
	public static String appName = "ConsultantRobotApplication";
	
	private Point currentCoordinates = new Point(0,0);
	private String format = "jpg";
	private String taskData;
	
	private ServerChannel messagesServerChannel;
	private ServerChannel taskResultServerChannel;
	
	/**
	 * 100 ms
	 */
	public static int baseOperationTimeoutMilliseconds = 100;
	
	/**
	 * 5 seconds
	 */
	public static int baseOperationWaitSecondsUntil = 5; 
	
	//private boolean isTestingRecaptcha = false;
    private boolean isFake = true;
	
	public Application(){		
		createLogger();
		log("start application. version: "+version);
		try{
			log("initing Rucaptcha");

			String rCaptchaValue = "";
			try{
				rCaptchaValue = parseRucaptcha();
			}
			catch(Exception exception){
				logError("parse exception "+exception.getMessage());
			}

			RuCaptcha.API_KEY = rCaptchaValue;
		}
		catch(Exception exception){
			logError("Rucaptcha init error. "+exception.getMessage());
		}
		
		RecaptchaData.init();
		
		RecaptchaData.setImagePath("C:\\consultant\\screenshots\\recaptchaImage."+format);
		RecaptchaData.setImagesFolder("C:\\consultant\\screenshots\\");
		
		EventDispatcher.getInstance().addEventListener(this);
        log("isFake = "+isFake);

		/*
        try
		{
			if(isFake == true){
                ClassLoader classLoader = getClass().getClassLoader();
                File file = new File(classLoader.getResource("anchors_div0.xml").getFile());

                //new Anchors().getAnchors(new File("C:\\consultant\\data\\anchors_div0.xml"));

                log("anchors file is "+file);
                new Anchors().getAnchors(file);
			}
			else{
				new Anchors().getAnchors(new File("C:\\consultant\\data\\anchors_donor.xml"));
			}
		} 
		catch (ParserConfigurationException | SAXException | IOException e) {
			System.out.println("Error parsing anchors");
			e.printStackTrace();
			logError(e.getMessage());
		}
		log("Anchors loaded");
		*/
		//TaskBuilderRecaptchaOnly fakeTaskLoader = new TaskBuilderRecaptchaOnly();

		createTaskData();
		startTask();
	}

	private String parseRucaptcha() throws ParserConfigurationException, IOException, SAXException {
		ClassLoader classLoader = getClass().getClassLoader();
		File settingsFile = new File(classLoader.getResource("settings.xml").getFile());

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = null;
		documentBuilder = documentBuilderFactory.newDocumentBuilder();

		Document settingsDocument = documentBuilder.parse(settingsFile);

		//optional, but recommended
		//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		settingsDocument.getDocumentElement().normalize();

		NodeList rucaptchaNodeList = settingsDocument.getDocumentElement().getElementsByTagName("RuCaptcha_API_KEY");
		Node rCaptchaNode = rucaptchaNodeList.item(0);
		String rCaptchaValue = rCaptchaNode.getTextContent();

		return rCaptchaValue;
	}

	private void startTask(){
		log("starting task...");
		taskThread = new Thread(currentTask);
		taskThread.start();
	}

	private void createTaskData(){
		log("creating task data ... isFake = "+isFake);
		if(isFake){
			System.out.println("creating fake task...");
			taskData=new FakeTaskBuilder().build();
			log("taskData="+taskData);
			createTask(null, taskData);
		}
		else{
			taskData=new TaskBuilder().build();
		}
	}


	public void onCancelTask(ServerSession clientServerSession){
		log("on cancel task. task = "+currentTask);
		destroyTask();
	}

	public void onNewTask(ServerSession clientServerSession, String taskData){
		
		log("on new task. currrent task = "+currentTask);
		String serverSessionId = clientServerSession.getId();
		Server.clientServerSession = clientServerSession;
		
		if(currentTask!=null){
			log("Error: Task already exists");
			//task.sendMessage("Task already exists");
			return;
		}
		else{
			createTask(clientServerSession, taskData);
		}
		startTask();
		/*
		taskThread = new Thread(currentTask);
		taskThread.start();
		*/
	}
	
	private void createTask(ServerSession clientServerSession, String taskData) {
		log(" -- creating new task. currentTask= "+currentTask);
		currentTask = new Task(taskData);
	}
	
	
	private void destroyTask() {
		log("destroy task");
		//taskThread.stop();
		//taskThread.destroy();
		taskThread = null;
		currentTask.destroy();
		currentTask = null;
		//removeEventListeners();
		//log("task destroy OK. currentTask = "+currentTask);
	}

	private void removeEventListeners() {
		EventDispatcher.getInstance().removeEventListener(this);
		log("event listeners removed");
	}
	
	private void createLogger() {
		Date currentDate = new Date();
		
		String logFilePostfix = currentDate.getMonth()+"_"+currentDate.getDate()+"-"+currentDate.getHours()+"_"+currentDate.getMinutes()+"_"+currentDate.getSeconds();
		
		logger = Logger.getLogger(appName); 
		logger.setUseParentHandlers(false);
	    FileHandler fh; 

	    SimplestFormatter formatter = new SimplestFormatter();

	    try {  
	        fh = new FileHandler("C:\\consultant\\logs\\log_"+logFilePostfix+".txt");
	        logger.addHandler(fh);
	        fh.setFormatter(formatter);  
	    } 
	    catch (SecurityException e) {  
	        e.printStackTrace(); 
	    } 
	    catch (IOException e) {  
	        e.printStackTrace();  
	    }  
	}

	@Override
	public void eventHandler(BaseEvent event) {
		if(event.getType().equals(TaskEvent.TASK_COMPLETE)){
			TaskResult taskResult = (TaskResult)event.getData();
			onTaskComplete(taskResult);
		}
		if(event.getType().equals(TaskEvent.TASK_DESTROY_COMPLETE)){
			onTaskDestroyComplete();
		}
		if(event.getType().equals(TaskEvent.TASK_ERROR)){
			TaskResult taskResult = (TaskResult)event.getData();
			onTaskError(taskResult);
		}
		else if(event.getType().equals(LogEvent.LOG_MESSAGE)){
			log((String)event.getData());
		}
		else if(event.getType().equals(LogEvent.LOG_ERROR)){
			logError((String)event.getData());
		}
	}
	
	private void onTaskError(TaskResult taskResult) {
		sendTaskResultToClient(taskResult);
		destroyTask();
	}

	private void onTaskDestroyComplete(){
		sendTaskDestroyCompleteToClient();
	}
	
	private void onTaskComplete(TaskResult taskResult) {
		taskResult.setResult(TaskResult.COMPLETE);
		sendTaskResultToClient(taskResult);
		
		if(currentTask.isManualTaskFinish() != true){
			destroyTask();
		}
		else{
			log("Manual task finishing required !!!");
		}
	}

	private void log(String message){
		System.out.println(message);
		logger.info(message);
		sendLogToClient(message);
	}

	private void logError(String message){
		System.err.println("  Error: "+message);
		logger.info("  Error: "+ message); 
		sendLogToClient("  Error: "+ message);
	}
	
	
	private void sendLogToClient(String message) {
		messagesServerChannel = Server.bayeux.getChannel("/message");
		//Server.bayeux.createIfAbsent(channelName);
		
		JSONObject clientMessageData = new JSONObject();
    	clientMessageData.put("data", message);
		
    	sendMessage(clientMessageData);
	}
	
	private void sendTaskDestroyCompleteToClient() {
		
		taskResultServerChannel = Server.bayeux.getChannel("/taskDestroyComplete");
		//log("serverChannel = "+messagesServerChannel.getId());
		
		JSONObject clientMessageData = new JSONObject();
		
    	//log("sending result "+clientMessageData.toString());
    	
    	sendTaskResult(clientMessageData);
	}
	
	private void sendTaskResultToClient(TaskResult taskResult) {
		
		taskResultServerChannel = Server.bayeux.getChannel("/taskResult");
		//log("serverChannel = "+messagesServerChannel.getId());
		
		JSONObject clientMessageData = new JSONObject();
		clientMessageData.put("payload", taskResult.getPayload());
    	clientMessageData.put("month", taskResult.getMonth());
    	clientMessageData.put("day", taskResult.getDay());
    	clientMessageData.put("time", taskResult.getTime());
    	clientMessageData.put("id", taskResult.getId());
    	clientMessageData.put("result", taskResult.getResult());
    	clientMessageData.put("errorText", taskResult.getErrorText());
		
    	//log("sending result "+clientMessageData.toString());
    	
    	sendTaskResult(clientMessageData);
	}

	private void sendMessage(JSONObject clientMessageData) {
		if(messagesServerChannel!=null)
    	{
			//System.out.println("message sent OK. channel="+messagesServerChannel.getId());
    		messagesServerChannel.publish(Server.serverSession, clientMessageData.toString());
    	}
    	else
    	{
    		//System.err.println("CHANNEL " + "/message" + "  IS NULL");
    	}
	}
	private void sendTaskResult(JSONObject clientMessageData) {
		if(taskResultServerChannel!=null)
		{
			System.out.println("result sent OK. channel="+taskResultServerChannel.getId());
			taskResultServerChannel.publish(Server.serverSession, clientMessageData.toString());
		}
		else
		{
			//System.err.println("CHANNEL " + "/taskResult" + "  IS NULL");
		}
	}
}
