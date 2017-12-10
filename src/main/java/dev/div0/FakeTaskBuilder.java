package dev.div0;

import dev.div0.steps.StepAction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class FakeTaskBuilder {

	private String login = "kaitoriparts";
	//private String login = "1234"; // wrong

	private String pass = "37fmv563";
	//private String pass = "1234"; // wrong

	private String lot_1_Url = "https://page.auctions.yahoo.co.jp/jp/auction/w209318651";
	//private String lot_1_Url = "http://yahoorobot/pages/lot_page.html";
	private int money = 670;

	public String build(){

		JSONObject settings = new JSONObject();
		settings.put("id", "1");
		settings.put("isManualFinish", true);

		JSONObject yahooBiddingData = new JSONObject();
		yahooBiddingData.put("login",login);
		yahooBiddingData.put("pass",pass);
		yahooBiddingData.put("lotUrl",lot_1_Url);
		yahooBiddingData.put("userMoney",money);

		/*
		JSONObject authData = new JSONObject();
		authData.put("login",login);
		authData.put("pass",pass);

		JSONObject beddingData = new JSONObject();
		beddingData.put("lotUrl",lot_1_Url);
		beddingData.put("userMoney",money);
		*/

		JSONArray array = new JSONArray();
		JSONObject step1 = new JSONObject();
		JSONObject step2 = new JSONObject();
		JSONObject step3 = new JSONObject();
		JSONObject step4 = new JSONObject();
		JSONObject step5 = new JSONObject();


		step1.put("id", 0);
		step1.put("action", StepAction.TASK_SETTING.toString());
		step1.put("payload", settings.toString());

		/*
		step2.put("id", 1);
		step2.put("action", StepAction.CREATE_AUTH.toString());
		step2.put("payload", authData.toString());

		step3.put("id", 2);
		step3.put("action", StepAction.CREATE_BIDDING.toString());
		step3.put("payload", beddingData.toString());
		*/

		step2.put("id", 1);
		step2.put("action", StepAction.CREATE_YAHOO_BIDDING_SEQUENCE.toString());
		step2.put("payload", yahooBiddingData.toString());

		array.add(step1);
		array.add(step2);
		//array.add(step3);


		/*
		step1.put("id", 0);
		step1.put("action", StepAction.TASK_SETTING.toString());
		step1.put("payload", settings.toString());

		step2.put("id", 1);
		step2.put("action", StepAction.CREATE_BIDDING.toString());
		step2.put("payload", beddingData.toString());

		array.add(step1);
		array.add(step2);
		*/

		return array.toString();
	}
}
