package dev.div0.robotOperations.yhOpeartionsSequence;

import dev.div0.events.BaseEvent;
import dev.div0.events.EventDispatcher;
import dev.div0.events.IEventListener;
import dev.div0.robotOperations.BaseOperation;
import dev.div0.robotOperations.OperationException;
import dev.div0.robotOperations.events.OperationEvent;
import dev.div0.robotOperations.yhOpeartionsSequence.bidding.BiddingOperation;
import dev.div0.robotOperations.operationData.OperationData;

import dev.div0.robotOperations.yhOpeartionsSequence.bidding.events.BiddingResultEvent;
import dev.div0.robotOperations.yhOpeartionsSequence.events.YahooSequenceEvent;
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
        else if(event.getType().equals(BiddingResultEvent.BID_ALREADY_SET)){
            result.setResult("error");
            result.setErrorText("BID_ALREADY_SET");
        }
        else if(event.getType().equals(BiddingResultEvent.BLITZ_BID_ERROR)){
            result.setResult("error");
            result.setErrorText("BLITZ_BID_NOT_SET_ERROR");
        }
        else if(event.getType().equals(BiddingResultEvent.BLITZ_BID_COMPLETE)){
            result.setResult("complete");
        }
        else if(event.getType().equals(BiddingResultEvent.NORMAL_BID_ERROR)){
            result.setResult("error");
            JSONObject dataObject = new JSONObject();
            dataObject.put("errorDescription", event.getData());
            dataObject.put("errorText", "NORMAL_BID_NOT_SET_ERROR");
            result.setErrorText(dataObject.toString());
        }
        else if(event.getType().equals(BiddingResultEvent.NORMAL_BID_COMPLETE)){
            JSONObject dataObject = new JSONObject();
            dataObject.put("bidData", event.getData());
            result.setBidData(dataObject.toString());
            result.setResult("complete");
        }
        else if(event.getType().equals(BiddingResultEvent.NOT_ENOUGH_MONEY)){
            result.setResult("error");

            JSONObject dataObject = new JSONObject();
            dataObject.put("errorDescription", event.getData());
            dataObject.put("errorText", "NOT_ENOUGH_MONEY");

            result.setErrorText(dataObject.toString());
        }
        else if(event.getType().equals(BiddingResultEvent.LOT_CLOSED)){
            result.setResult("error");
            JSONObject dataObject = new JSONObject();
            dataObject.put("errorDescription", event.getData());
            dataObject.put("errorText", "LOT_CLOSED");
            result.setErrorText(dataObject.toString());
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
        String resultData = ResultEncoder.encode(result);
        YahooSequenceEvent event = null;

        event = new YahooSequenceEvent(YahooSequenceEvent.ERROR);
        event.setData(resultData);
        EventDispatcher.getInstance().dispatchEvent(event);
    }

    private void onAuthComplete() throws OperationException {
        log("Auth complete with no errors");
        createBidding();
        onSequenceComplete();
    }

    private void onSequenceComplete(){
        String resultData = ResultEncoder.encode(result);
        log("On yahoo sequence complete result="+resultData+"  result.getErrorText() = "+result.getErrorText());

        YahooSequenceEvent event = null;

        if(result.getErrorText()==null){
            event = new YahooSequenceEvent(YahooSequenceEvent.COMPLETE);
            event.setData(resultData);
        }
        else{
            event = new YahooSequenceEvent(YahooSequenceEvent.ERROR);
            event.setData(resultData);
        }
        EventDispatcher.getInstance().dispatchEvent(event);
    }

    private boolean createBidding() throws OperationException {
        BiddingOperation biddingOperation = new BiddingOperation();

        biddingOperation.setWebDriver(webDriver);
        biddingOperation.setOperationData(operationData);
        boolean biddingComplete = biddingOperation.execute();


        return biddingComplete;
    }
}
