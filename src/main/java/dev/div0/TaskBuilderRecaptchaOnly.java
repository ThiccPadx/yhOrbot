package dev.div0;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class TaskBuilderRecaptchaOnly {
	
	public String build(){
		JSONArray array = new JSONArray();
		JSONObject step1 = new JSONObject();
		JSONObject step2 = new JSONObject();
		JSONObject step3 = new JSONObject();
		JSONObject step4 = new JSONObject();
		JSONObject step5 = new JSONObject();
		JSONObject step6 = new JSONObject();
		JSONObject step7 = new JSONObject();
		JSONObject step8 = new JSONObject();
		JSONObject step9 = new JSONObject();
		JSONObject step10 = new JSONObject();
		JSONObject step11 = new JSONObject();
		JSONObject step12 = new JSONObject();
		
		step1.put("id", 0);
		step1.put("type", "openUrl");
		step1.put("description", "open site");
		step1.put("data", "http://divisionby0.ru/recaptchaTest.html");
		
		// switch to main iframe
		step2.put("id", 1);
		step2.put("type", "switchToIframe");
		step2.put("elementType", "mainIFrame");
		step2.put("isMainFrame", "1");
		step2.put("elementSearchType", "byXPath");
		step2.put("description", "Switch to main iframe. find iframe by xPath /html/body/iframe");
		step2.put("data", "/html/body/iframe");
		
		step3.put("id", 2);
		step3.put("type", "solveRecaptcha");
		step3.put("elementSearchType", "byXPath"); // no information in this case
		step3.put("description", "solve recaptcha."); // no information in this case
		step3.put("data", "1");
		
		step4.put("id", 3);
		step4.put("type", "selectDropDownItem");
		step4.put("selectionIndex", 2);
		step4.put("elementSearchType", "byId");
		step4.put("description", "selectDropDownItem from drop dowwn with id ctl00_plhMain_cboVisaCategory");
		step4.put("data", "ctl00_plhMain_cboVisaCategory");
		
		step5.put("id", 4);
		step5.put("type", "clickElement");
		step5.put("elementSearchType", "byId");
		step5.put("description", "click element by id ctl00_plhMain_btnSubmit");
		step5.put("data", "ctl00_plhMain_btnSubmit");
		
		// No date(s) available for appointment ???
		step6.put("id", 5);
		step6.put("type", "detectDatesAvailableForSelectedCity");
		step6.put("elementSearchType", "byXPath");
		step6.put("description", "detect element existance by text No date(s) available for appointment");
		step6.put("data", "//*[contains(text(), 'No date(s) available for appointment')]");
		
		
		array.add(step1);
		array.add(step2);
		array.add(step3);
		array.add(step4);
		array.add(step5);
		array.add(step6);
		
		return array.toString();
	}
}
