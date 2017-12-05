package dev.div0;

import dev.div0.steps.StepAction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class FakeTaskBuilder {

	private String login = "kaitoriparts";
	private String pass = "37fmv563";

	public String build(){
		JSONArray array = new JSONArray();
		JSONObject step1 = new JSONObject();
		JSONObject step2 = new JSONObject();
		JSONObject step3 = new JSONObject();
		JSONObject step4 = new JSONObject();
		
		step1.put("id", 0);
		step1.put("action", StepAction.OPEN_URL);
		step1.put("description", "open site");
		//step1.put("data", "http://www.polandvisa-ukraine.com/scheduleappointment.html");
		step1.put("elementSearchData", "https://login.yahoo.co.jp/config/login");
		
		step2.put("id", 1);
		step2.put("action", StepAction.SET_TEXT);
		step2.put("elementSearchType", "byId");
		step2.put("elementSearchData", "username");
		step2.put("description", "inserting login "+login);
		step2.put("payload", login);
		
		step3.put("id", 2);
		step3.put("action", StepAction.CLICK_ELEMENT);
		step3.put("elementSearchType", "byId");
		step3.put("elementSearchData", "btnNext");
		step3.put("description", "clicking NEXT button");

		/*
		step4.put("id", 3);
		step4.put("type", "clickLink");
		step4.put("elementSearchType", "byId");
		step4.put("description", "link click by idctl00_plhMain_lnkSchApp");
		step4.put("data", "ctl00_plhMain_lnkSchApp");
		*/
		
		array.add(step1);
		array.add(step2);
		array.add(step3);
		//array.add(step4);
		return array.toString();
	}
}
