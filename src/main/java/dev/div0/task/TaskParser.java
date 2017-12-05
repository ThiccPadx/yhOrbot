package dev.div0.task;

import java.util.ArrayList;

import dev.div0.application.page.Page;
import org.openqa.selenium.Point;

import dev.div0.logging.BaseLogger;
import dev.div0.steps.ElementSearchType;
import dev.div0.steps.Step;
import dev.div0.steps.StepAction;
import dev.div0.steps.StepData;
import dev.div0.utils.JsonParseUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class TaskParser extends BaseLogger{
	
	private String taskId;
	private boolean isManualTaskFinish;
	private TaskParserEvent taskParserEvent;
	
	
	public JSONObject parse(String taskData){
		
		JSONObject resultObject = new JSONObject();
		
		ArrayList<Step> result = new ArrayList<>();


		JSONArray steps = JsonParseUtil.parseToArray(taskData);

		log("total steps: "+steps.size());

		for(int i=0; i<steps.size(); i++){
			JSONObject currentStepData = (JSONObject)steps.get(i); 
			String stepId = String.valueOf(currentStepData.get("id"));

			log("new step");

			String stepElementSearchData = String.valueOf(currentStepData.get("elementSearchData"));
			
			String stepDescription = String.valueOf(currentStepData.get("description"));
			String stepPayload = String.valueOf(currentStepData.get("payload"));
			
			log("start parsing stepAction step index = "+i);
			String stepActionString = String.valueOf(currentStepData.get("action"));
			
			log("stepActionString="+stepActionString);
			
			String stepElementTypeString = String.valueOf(currentStepData.get("elementType"));
			String stepElementSearchTypeString = String.valueOf(currentStepData.get("elementSearchType"));
			String isMainFrameString = String.valueOf(currentStepData.get("isMainFrame"));
			String isRecaptchaPayloadFrameString = String.valueOf(currentStepData.get("isRecaptchaPayloadFrame"));
			String isRecaptchaImNotARobotCheckBoxContainerFrameString = String.valueOf(currentStepData.get("isRecaptchaImNotARobotCheckBoxContainerFrame"));

			log("stepElementTypeString: "+stepElementTypeString);

			boolean isMainFrame = false;
			boolean isRecaptchaPayloadFrame = false;
			boolean isRecaptchaImNotARobotCheckBoxContainerFrame = false;
			
			//log("step "+stepId+"  data="+stepData+"  isRecaptchaImNotARobotCheckBoxContainerFrameString="+isRecaptchaImNotARobotCheckBoxContainerFrameString);
			
			if(isMainFrameString.equals("1")){
				isMainFrame = true;
				Page.setMainIframeXPath(stepElementSearchData);
			}
			if(isRecaptchaPayloadFrameString.equals("1")){
				isRecaptchaPayloadFrame = true;
				Page.setRecaptchaPayloadIFrameXPath(stepElementSearchData);
			}
			if(isRecaptchaImNotARobotCheckBoxContainerFrameString.equals("1")){
				isRecaptchaImNotARobotCheckBoxContainerFrame = true;
				Page.setImNotARobotCheckBoxContainerIFrameXPath(stepElementSearchData);
			}
			
			Point elementOffsetPoint = new Point(0,0);
			
			String stepElementOffsetPointXString = String.valueOf(currentStepData.get("elementOffsetX"));
			String stepElementOffsetPointYString = String.valueOf(currentStepData.get("elementOffsetY"));
			
			if(stepElementOffsetPointXString!="null" && stepElementOffsetPointYString!="null"){
				elementOffsetPoint = getElementOffsetPoint(stepElementOffsetPointXString, stepElementOffsetPointYString);
			}
			
			boolean overrideHrefTarget = Boolean.parseBoolean(String.valueOf(currentStepData.get("overrideHrefTarget")));
			int selectionIndex = 0;
			String selectionValue = "null";
			String selectionVisibleText = "null";
			
			if(currentStepData.get("selectionIndex")!=null){
				selectionIndex = Integer.parseInt(String.valueOf(currentStepData.get("selectionIndex")));
				//log("selectionIndex="+selectionIndex);
			}
			
			if(currentStepData.get("selectionValue")!=null){
				selectionValue = String.valueOf(currentStepData.get("selectionValue"));
			}
			if(currentStepData.get("selectionVisibleText")!=null){
				selectionVisibleText = String.valueOf(currentStepData.get("selectionVisibleText"));
			}
			
			StepAction stepAction = parseStepAction(stepActionString);
			//StepAction stepAction = (StepAction)currentStepData.get("action");
			
			log("stepAction="+stepAction);

			if(stepAction==null || stepAction==StepAction.UNDEFINED_STEP_ACTION){
				log("Undefined step action - nothing to do");
				return null;
			}

			ElementSearchType elementSearchType = parseElementSearchType(stepElementSearchTypeString); 
			log("elementSearchType="+elementSearchType);
			//log("selectionValue="+selectionValue+"  selectionVisibleText="+selectionVisibleText+" selectionIndex="+selectionIndex);
			
			StepData stepData = new StepData(Integer.parseInt(stepId), stepDescription, stepPayload, stepAction, elementSearchType, stepElementSearchData, overrideHrefTarget, selectionIndex, selectionValue, selectionVisibleText, stepElementTypeString, elementOffsetPoint, isMainFrame, isRecaptchaPayloadFrame, isRecaptchaImNotARobotCheckBoxContainerFrame);
			
			Step step = new Step(stepData);

			if(stepAction.equals(StepAction.TASK_SETTING)!=true){
				result.add(step);
			}
			else{
				log("step is task settings - not adding it to collection.");
				log("step payload: "+stepPayload);
				
				parseTaskSettings(stepPayload);
			}
		}
		
		resultObject.put("id", taskId);
		resultObject.put("isManualTaskFinish", isManualTaskFinish);
		resultObject.put("steps", result);

		log("TASK PARSED OK");

		return resultObject;
	}

	private void parseTaskSettings(String stepPayload) {
		log("parsing task settings...");
		JSONObject taskSettings = JsonParseUtil.parse(stepPayload);
		
		taskId = taskSettings.get("id").toString();
		isManualTaskFinish = Boolean.parseBoolean(taskSettings.get("isManualFinish").toString());
		log("Task id : "+taskId);
		log("Task isManualTaskFinish : "+isManualTaskFinish);	
	}

	private Point getElementOffsetPoint(String stepElementOffsetPointXString, String stepElementOffsetPointYString) {
		
		int pointX = Integer.parseInt(stepElementOffsetPointXString);
		int pointY = Integer.parseInt(stepElementOffsetPointYString);
		return new Point(pointX, pointY);
	}


	private StepAction parseStepAction(String stepActionString) {
		if(stepActionString.equals(StepAction.TASK_SETTING.toString())){
			return StepAction.TASK_SETTING;
		}
		if(stepActionString.equals(StepAction.OPEN_URL.toString())){
			return StepAction.OPEN_URL;
		}
		else if(stepActionString.equals(StepAction.CLICK_LINK.toString())){
			return StepAction.CLICK_LINK;
		}
		else if(stepActionString.equals(StepAction.CLICK_ELEMENT.toString())){
			return StepAction.CLICK_ELEMENT;
		}
		else if(stepActionString.equals(StepAction.SWITCH_TO_IFRAME.toString())){
			return StepAction.SWITCH_TO_IFRAME;
		}
		else if(stepActionString.equals(StepAction.FIND_IFRAME_BY_TAG_AND_TAG_INDEX.toString())){
			return StepAction.FIND_IFRAME_BY_TAG_AND_TAG_INDEX;
		}
		else if(stepActionString.equals(StepAction.GET_DEFAULT_CONTENT.toString())){
			return StepAction.GET_DEFAULT_CONTENT;
		}
		else if(stepActionString.equals(StepAction.SELECT_DROP_DOWN_ITEM.toString())){
			return StepAction.SELECT_DROP_DOWN_ITEM;
		}
		else if(stepActionString.equals(StepAction.TAKE_IFRAME_SCREENSHOT.toString())){
			return StepAction.TAKE_IFRAME_SCREENSHOT;
		}
		else if(stepActionString.equals(StepAction.WAIT.toString())){
			return StepAction.WAIT;
		}
		else if(stepActionString.equals(StepAction.START_RECAPTCHA_ROBOT.toString())){
			return StepAction.START_RECAPTCHA_ROBOT;
		}
		else if(stepActionString.equals(StepAction.GET_RECAPTCHA_INSTRUCTIONS.toString())){
			return StepAction.GET_RECAPTCHA_INSTRUCTIONS;
		}
		else if(stepActionString.equals(StepAction.BRING_BROWSER_TO_FRONT.toString())){
			return StepAction.BRING_BROWSER_TO_FRONT;
		}
		else if(stepActionString.equals(StepAction.SAVE_RECAPTCHA_IMAGE.toString())){
			return StepAction.SAVE_RECAPTCHA_IMAGE;
		}
		else if(stepActionString.equals(StepAction.REFRESH_PAGE.toString())){
			return StepAction.REFRESH_PAGE;
		}
		else if(stepActionString.equals(StepAction.SOLVE_RECAPTCHA.toString())){
			return StepAction.SOLVE_RECAPTCHA;
		}
		else if(stepActionString.equals(StepAction.SOLVE_RECAPTCHA_WITH_ERROR.toString())){
			return StepAction.SOLVE_RECAPTCHA_WITH_ERROR;
		}
		else if(stepActionString.equals(StepAction.DETECT_DATES_AVAILABLE_FOR_SELECTED_CITY.toString())){
			return StepAction.DETECT_DATES_AVAILABLE_FOR_SELECTED_CITY;
		}
		else if(stepActionString.equals(StepAction.DETECT_PTN_IS_CORRCT.toString())){
			return StepAction.DETECT_PTN_IS_CORRCT;
		}
		else if(stepActionString.equals(StepAction.SET_TEXT.toString())){
			return StepAction.SET_TEXT;
		}
		else if(stepActionString.equals(StepAction.GET_TEXT.toString())){
			return StepAction.GET_TEXT;
		}
		else if(stepActionString.equals(StepAction.SELECT_CALENDAR_AVAILABLE_DATE.toString())){
			return StepAction.SELECT_CALENDAR_AVAILABLE_DATE;
		}
		else if(stepActionString.equals(StepAction.SELECT_AVAILABLE_TIME.toString())){
			return StepAction.SELECT_AVAILABLE_TIME;
		}
		else if(stepActionString.equals(StepAction.GET_GOOGLE_SEARCH_RESULTS_TOTAL.toString())){
			return StepAction.GET_GOOGLE_SEARCH_RESULTS_TOTAL;
		}
		else if(stepActionString.equals(StepAction.PRESS_ENTER_KEY.toString())){
			return StepAction.PRESS_ENTER_KEY;
		}
		else if(stepActionString.equals(StepAction.DETECT_PAGE_HAS_TEXT.toString())){
			return StepAction.DETECT_PAGE_HAS_TEXT;
		}
		return StepAction.UNDEFINED_STEP_ACTION;
	}
	
	private ElementSearchType parseElementSearchType(String stepElementSearchTypeString){
		if(stepElementSearchTypeString.equals(ElementSearchType.BY_HREF.toString())){
			return  ElementSearchType.BY_HREF;
		}
		else if(stepElementSearchTypeString.equals(ElementSearchType.BY_ID.toString())){
			return ElementSearchType.BY_ID;
		}
		else if(stepElementSearchTypeString.equals(ElementSearchType.BY_XPATH.toString())){
			return ElementSearchType.BY_XPATH;
		}
		else if(stepElementSearchTypeString.equals(ElementSearchType.BY_NAME.toString())){
			return ElementSearchType.BY_NAME;
		}
		return null;
	}
}
