package dev.div0;

import dev.div0.steps.ElementSearchType;
import dev.div0.steps.StepAction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class FakeTaskBuilder {

	private String login = "kaitoriparts";
	private String pass = "37fmv563";

	public String build(){

		JSONObject settings = new JSONObject();
		settings.put("id", "1");
		settings.put("isManualFinish", true);

		JSONArray array = new JSONArray();
		JSONObject step1 = new JSONObject();
		JSONObject step2 = new JSONObject();
		JSONObject step3 = new JSONObject();
		JSONObject step4 = new JSONObject();

		step1.put("id", 0);
		step1.put("action", StepAction.TASK_SETTING.toString());
		step1.put("description", "settings");
		step1.put("payload", settings.toString());


		step2.put("id", 1);
		step2.put("action", StepAction.OPEN_URL.toString());
		step2.put("description", "open site");
		//step1.put("data", "http://www.polandvisa-ukraine.com/scheduleappointment.html");
		step2.put("elementSearchData", "https://login.yahoo.co.jp/config/login");
		
		step3.put("id", 2);
		step3.put("action", StepAction.SET_TEXT.toString());
		step3.put("elementSearchType", ElementSearchType.BY_ID.toString());
		step3.put("elementSearchData", "username");
		step3.put("description", "inserting login "+login);
		step3.put("payload", login);
		
		step4.put("id", 3);
		step4.put("action", StepAction.CLICK_ELEMENT.toString());
		step4.put("elementSearchType", ElementSearchType.BY_ID.toString());
		step4.put("elementSearchData", "btnNext");
		step4.put("description", "clicking NEXT button");

		
		array.add(step1);
		array.add(step2);
		array.add(step3);
		array.add(step4);
		return array.toString();
	}
}
