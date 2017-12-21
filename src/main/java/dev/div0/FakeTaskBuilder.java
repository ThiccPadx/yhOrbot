package dev.div0;

import dev.div0.steps.StepAction;
import dev.div0.utils.StringUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class FakeTaskBuilder {

	private String login = "kaitoriparts";
	//private String login = "1234"; // wrong

	private String pass = "37fmv563";
	//private String pass = "1234"; // wrong

	private String lot_1_Url = "https://page.auctions.yahoo.co.jp/jp/auction/o216244767";
	//private String lot_1_Url = "http://yahoorobot/pages/bitAlreadySet_page.html";

	//private String lot_1_Url = "https://page.auctions.yahoo.co.jp/jp/auction/o211389645";
	//private String lot_1_Url = "http://yahoorobot/pages/normalBidPage.html";
	//private String lot_1_Url = "http://yahoorobot/pages/bidHasBeenResetPage.html";
	//private String lot_1_Url = "http://yahoorobot/pages/captchaPage.html";

	private int money = 570;

	public String build(){

		JSONArray array = new JSONArray();
		JSONObject step1 = new JSONObject();
		JSONObject step2 = new JSONObject();
		JSONObject step3 = new JSONObject();
		JSONObject step4 = new JSONObject();
		JSONObject step5 = new JSONObject();

		JSONObject settings = new JSONObject();
		settings.put("id", StringUtil.generateId(36));
		settings.put("isManualFinish", false);

		JSONObject yahooBiddingData = new JSONObject();
		yahooBiddingData.put("login",login);
		yahooBiddingData.put("pass",pass);
		yahooBiddingData.put("lotUrl",lot_1_Url);
		yahooBiddingData.put("userMoney",money);

		step1.put("id", 0);
		step1.put("action", StepAction.TASK_SETTING.toString());
		step1.put("payload", settings.toString());

		step2.put("id", 1);
		step2.put("action", StepAction.CREATE_YAHOO_BIDDING_SEQUENCE.toString());
		step2.put("payload", yahooBiddingData.toString());

		array.add(step1);
		array.add(step2);

		/*
		// LOCAL
		step1.put("id", 0);
		step1.put("action", StepAction.TASK_SETTING.toString());
		step1.put("payload", settings.toString());

		step2.put("id", 2);
		step2.put("action", StepAction.CREATE_BIDDING.toString());
		step2.put("payload", yahooBiddingData.toString());

		array.add(step1);
		array.add(step2);
		*/

		return array.toString();
	}
}
