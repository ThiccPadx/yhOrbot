package dev.div0.robotOperations.yhOpeartionsSequence;

import dev.div0.robotOperations.BaseOperation;
import dev.div0.robotOperations.operationData.OperationData;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.StringReader;

public class YahooOperationsSequence extends BaseOperation {

    private String login;
    private String password;
    private String lotUrl;
    private String userMoney;

    public YahooOperationsSequence() {
        super();
        log("Am YahooOperationsSequence ");
    }

    @Override
    public void setOperationData(OperationData operationData) {
        super.setOperationData(operationData);
        log("YahooOperationsSequence setOperationData "+operationData);
        log("payload = "+operationData.getPayload());

        /*
        JSONObject json;

        JSONParser parser = new JSONParser();

        try {
            json = (JSONObject) parser.parse(new StringReader(operationData.getPayload().toString()));
            login = json.get("login").toString();
            pass = json.get("pass").toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
    }
}
