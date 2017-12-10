package dev.div0.robotOperations.yhOpeartionsSequence;

import dev.div0.events.BaseEvent;
import dev.div0.events.EventDispatcher;
import dev.div0.events.IEventListener;
import dev.div0.robotOperations.BaseOperation;
import dev.div0.robotOperations.OperationException;
import dev.div0.robotOperations.events.OperationEvent;
import dev.div0.robotOperations.yhOpeartionsSequence.bidding.BiddingOperation;
import dev.div0.robotOperations.operationData.OperationData;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.StringReader;

public class YahooOperationsSequence extends BaseOperation implements IEventListener{

    private String login;
    private String password;
    private String lotUrl;
    private int userMoney;

    private AuthOperation authOperation;
    private BiddingOperation biddingOperation;
    private YahooSequenceResult result = new YahooSequenceResult();

    public YahooOperationsSequence() {
        super();
        result.setResult("inProgress");
        log("Am YahooOperationsSequence ");
        EventDispatcher.getInstance().addEventListener(this);
    }

    @Override
    public void setOperationData(OperationData operationData) {
        super.setOperationData(operationData);
        log("YahooOperationsSequence setOperationData "+operationData);
        log("payload = "+operationData.getPayload());

        JSONObject json;

        JSONParser parser = new JSONParser();

        try {
            json = (JSONObject) parser.parse(new StringReader(operationData.getPayload().toString()));
            login = json.get("login").toString();
            password = json.get("pass").toString();
            lotUrl = json.get("lotUrl").toString();
            String moneyString = json.get("userMoney").toString();
            userMoney = Integer.parseInt(moneyString);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean execute() throws OperationException {
        return createAuth();
    }

    @Override
    public void eventHandler(BaseEvent event) {
        if(event.getType().equals(OperationEvent.PASSWORD_INCORRECT)){
            result.setResult("error");
            result.setErrorText("PASSWORD_INCORRECT");
        }
        else if(event.getType().equals(OperationEvent.LOGIN_INCORRECT)){
            result.setResult("error");
            result.setErrorText("LOGIN_INCORRECT");
        }
    }

    private boolean createAuth() throws OperationException {
        if(login!=null && password!=null){
            authOperation = new AuthOperation();
            authOperation.setWebDriver(webDriver);
            authOperation.setOperationData(operationData);
            boolean authComplete = authOperation.execute();

            if(authComplete){
                if(result.getErrorText()!=null){
                    onAuthError();
                }
                else{
                    onAuthComplete();
                }
            }

            return true;
        }
        else{
            log("undefined login or password error");
            return true;
        }
    }

    private void onAuthError(){
        log("Auth error "+result.getErrorText());
    }

    private void onAuthComplete() throws OperationException {
        log("Auth complete with no errors");
        createBidding();
    }

    private boolean createBidding() throws OperationException {
        BiddingOperation biddingOperation = new BiddingOperation();

        biddingOperation.setWebDriver(webDriver);
        biddingOperation.setOperationData(operationData);
        boolean biddingComplete = biddingOperation.execute();

        return biddingComplete;
    }
}
