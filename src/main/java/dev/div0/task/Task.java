package dev.div0.task;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import dev.div0.events.BaseEvent;
import dev.div0.events.EventDispatcher;
import dev.div0.events.IEventListener;
import dev.div0.logging.BaseLogger;
import dev.div0.robotOperations.OperationException;
import dev.div0.robotOperations.SwitchToDefaultContentOperation;
import dev.div0.robotOperations.events.OperationEvent;
import dev.div0.robotOperations.ptn.PTNErrorEvent;
import dev.div0.robotOperations.yhOpeartionsSequence.events.YahooSequenceEvent;
import dev.div0.steps.Step;
import dev.div0.steps.StepException;
import dev.div0.webDriver.BrowserType;
import dev.div0.webDriver.CreateWebDriver;
import dev.div0.webDriver.DestroyWebDriver;
import org.json.simple.JSONObject;

import org.openqa.selenium.WebDriver;

// TODO PhantomJS http://stackoverflow.com/questions/24138398/how-to-implement-phantomjs-with-selenium-webdriver-using-java
public class Task extends BaseLogger implements IEventListener, Runnable{

	public static boolean datesAvailable = false;
	
	private boolean stopped = false;
	private TaskParser parser = new TaskParser();
	protected ArrayList<Step> steps = new ArrayList<>();
	
	private CreateWebDriver webDriverCreator;
	protected WebDriver webDriver;
	
	protected int currentStepIndex = 0;
	protected Step currentStep;
	protected int currentTryout = 1;
	
	protected TaskResult taskResult;
	
	private String id;
	private boolean isManualTaskFinish = false;
	
	public Task(String taskData){
		log("new task "+taskData);
		initDefaultTaskResult();
		EventDispatcher.getInstance().addEventListener(this);
		JSONObject parsedData = parser.parse(taskData);

		steps = (ArrayList<Step>)parsedData.get("steps");
		
		id = (String)parsedData.get("id");
		isManualTaskFinish = (Boolean)parsedData.get("isManualTaskFinish");
		taskResult.setId(id);
	}
	
	protected void initDefaultTaskResult() {
		taskResult = new TaskResult();
		taskResult.setPayload("no payload");
		taskResult.setErrorText(null);
		taskResult.setResult(TaskResult.IN_PROGRESS);
		taskResult.setMonth("month not set");
		taskResult.setDay("day not set");
		taskResult.setTime("time not set");
	}

	private void start(){
		createWebDriver();
		startStep();
	}

	protected void startStep() {
		
		if(stopped!=true)
		{
			currentStep = steps.get(currentStepIndex);
			currentStep.setWebDriver(webDriver);
			
			boolean stepComplete = false;
			
			try {
				stepComplete = currentStep.start();
			} catch (StepException e) {
				logError(e.getMessage()+"  step id: "+e.getStepId());
				e.printStackTrace();
			}
			
			if(stepComplete){
				log("step complete");
				TaskEvent taskEvent = new TaskEvent(TaskEvent.STEP_COMPLETE);
				taskEvent.setData(currentStepIndex);
				EventDispatcher.getInstance().dispatchEvent(taskEvent);
				
				currentStepIndex++;
				if(currentStepIndex < steps.size()){
					startStep();
				}
				else{
					taskComplete();
				}
			}
			else{
				onStepError();
			}
		}
		else{
			destroyWebDriverIfExists();
		}
	}

	protected void onStepError() {
		if(stopped!=true){
			
			boolean isError = taskResult.getResult().equals(TaskResult.ERROR);
			
			if(isError==true){
				logError("Step not complete. Error text = "+taskResult.getErrorText());
				TaskEvent taskEvent = new TaskEvent(TaskEvent.TASK_ERROR);
				taskEvent.setData(taskResult);
				EventDispatcher.getInstance().dispatchEvent(taskEvent);
			}
			else{
				currentTryout++;
				logError("Step not complete. Restarting task. currentTryout="+currentTryout);
				restart();
			}
		}
	}

	private void destroyWebDriverIfExists() {
		if(webDriver!=null){
			new DestroyWebDriver(webDriver);
		}
	}

	protected void taskComplete() {
		log("Task complete !!!");
		taskResult.setId(id);

		log("task result "+taskResult.getResult());
		log("task errorText "+taskResult.getErrorText());
		log("task payload "+taskResult.getPayload());
		
		TaskEvent taskEvent = new TaskEvent(TaskEvent.TASK_COMPLETE);
		taskEvent.setData(taskResult);
		EventDispatcher.getInstance().dispatchEvent(taskEvent);
	}

	public void restart() {
		log("restart task ");
		initDefaultTaskResult();
		
		try{
			setTimeout(4);
			new SwitchToDefaultContentOperation().execute();
		}
		catch(OperationException exception){
			logError("Cannot get default content");
		}
		currentStepIndex = 0;
		startStep();
	}
	
	private void setTimeout(int seconds){
		webDriver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}

	protected void createWebDriver() {
		log("Task creating chrome webDriver");
		webDriverCreator = new CreateWebDriver();
		webDriver = webDriverCreator.execute(BrowserType.CHROME);
		//webDriver = webDriverFactory.getDriver("firefox").getDriver();
		//webDriver = webDriverFactory.getDriver("chrome").getDriver();
		//webDriver = webDriverCreator.execute(BrowserType.IE);
		//webDriver = webDriverCreator.execute(BrowserType.HTML_UNIT);

		//webDriver = webDriverCreator.execute(BrowserType.FIREFOX);
		//webDriver = webDriverCreator.execute(BrowserType.PHANTOM_JS);
		//webDriver = webDriverCreator.execute(BrowserType.HTML_UNIT);
		//webDriver = webDriverCreator.execute(BrowserType.OPERA);
		//webDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		log("webDriver = "+webDriver);
	}

	public int getCurrentStepIndex(){
		return currentStepIndex;
	}
	
	private void destroyWebDriver() {
		new DestroyWebDriver(webDriver);
	}
	
	public void destroy(){
		stopped = true;
		if(currentStep!=null){
			currentStep.destroy();
			currentStep = null;
		}
		
		destroyWebDriver();
		steps.clear();
		steps = null;
		parser = null;
		webDriverCreator.destroy();
		//webDriverFactory = null;
		log(" --- Task destroy complete");
		
		
		TaskEvent taskEvent = new TaskEvent(TaskEvent.TASK_DESTROY_COMPLETE);
		EventDispatcher.getInstance().dispatchEvent(taskEvent);
	}

	@Override
	public void eventHandler(BaseEvent event) {
		if(event.getType().equals(OperationEvent.ELEMENT_TEXT_RESULT)){
			taskResult.setPayload((String)event.getData());
		}
		if(event.getType().equals(OperationEvent.MONTH_SELECTED)){
			taskResult.setMonth((String)event.getData());
		}
		else if(event.getType().equals(OperationEvent.DATE_SELECTED)){
			taskResult.setDay((String)event.getData());
		}
		else if(event.getType().equals(OperationEvent.TIME_SELECTED)){
			taskResult.setTime((String)event.getData());
		}
		else if(event.getType().equals(TaskParserEvent.TASK_ID)){
			log("event TASK ID = "+event.getData().toString());
			id = event.getData().toString();
		}
		else if(event.getType().equals(TaskParserEvent.IS_MANUAL_TASK_FINISH)){
			isManualTaskFinish = (Boolean)event.getData();
		}
		else if(event.getType().equals(PTNErrorEvent.IS_USED)){
			taskResult.setResult(TaskResult.ERROR);
			taskResult.setErrorText(event.getData().toString());
		}
		else if(event.getType().equals(PTNErrorEvent.NOT_FOUND)){
			taskResult.setResult(TaskResult.ERROR);
			taskResult.setErrorText(event.getData().toString());
		}
		else if(event.getType().equals(YahooSequenceEvent.ERROR)){
			taskResult.setResult(TaskResult.ERROR);
			taskResult.setErrorText(event.getData().toString());
		}
		else if(event.getType().equals(YahooSequenceEvent.COMPLETE)){
			log("Task YahooSequenceEvent.COMPLETE");
			taskResult.setResult(TaskResult.COMPLETE);
			taskResult.setPayload(event.getData().toString());
		}
	}

	@Override
	public void run() {
		log("STARTING TASK...");
		start();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isManualTaskFinish() {
		return isManualTaskFinish;
	}

	public void setManualTaskFinish(boolean isManualTaskFinish) {
		this.isManualTaskFinish = isManualTaskFinish;
	}
}
