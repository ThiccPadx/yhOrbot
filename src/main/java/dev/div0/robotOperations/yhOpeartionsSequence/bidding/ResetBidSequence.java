package dev.div0.robotOperations.yhOpeartionsSequence.bidding;

import dev.div0.events.EventDispatcher;
import dev.div0.robotOperations.*;
import dev.div0.robotOperations.operationData.OperationData;
import dev.div0.robotOperations.yhOpeartionsSequence.bidding.events.BiddingResultEvent;
import dev.div0.steps.ElementSearchType;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.StringReader;


public class ResetBidSequence extends BaseOperation {

    private int userMoney = 0;
    private GetInputValueOperation getInputValueOperation;
    private int bidCost;
    private DetectPageHasElementOperation detectPageHasElement;


    @Override
    public void setOperationData(OperationData operationData) {
        super.setOperationData(operationData);
        log("ResetBidSequence payload = "+operationData.getPayload());

        JSONObject json;
        JSONParser parser = new JSONParser();

        try {
            json = (JSONObject) parser.parse(new StringReader(operationData.getPayload().toString()));
            String moneyString = json.get("userMoney").toString();
            userMoney = Integer.parseInt(moneyString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean execute() throws OperationException{

        boolean dialogButtonClicked = false;
        boolean bidHasBeenSet = false;

        log("clicking on reset bid button..");
        boolean resetBidButtonClicked = createClick(ElementSearchType.BY_XPATH, "//*[@id=\"l-sub\"]/div[1]/ul/li[2]/div/div[1]/a");
        log("resetBidButtonClicked = "+resetBidButtonClicked);

        if(resetBidButtonClicked){
            // detect new bid cost
            log("detecting bid cost input exists...");
            boolean bidCostExists = detectBidCostInputExists();
            log("bidCostExists="+bidCostExists);

            if(bidCostExists){
                // detect new bid cost
                log("detecting new cost ...");
                boolean getNewBidCostOperation = getBidCost(ElementSearchType.BY_XPATH, "//*[@id=\"BidModals\"]/div/div[2]/div[2]/form/input[6]");
                log("input element value = "+getInputValueOperation.getValue());

                if(getInputValueOperation.getValue()!=null){
                    bidCost = Integer.parseInt(getInputValueOperation.getValue());

                    log("setting new bid cost value "+bidCost);
                    boolean newBidCostSetComplete = setNewBidCost();
                    log("newBidCostSetComplete = "+newBidCostSetComplete);

                    if(newBidCostSetComplete){
                        log("clicking dialog button...");
                        dialogButtonClicked = createClick(ElementSearchType.BY_XPATH, "//*[@id=\"BidModals\"]/div/div[2]/div[2]/form/div[3]/span/input");
                        log("dialogButtonClicked="+dialogButtonClicked);

                        log("clicking append button");
                        boolean appendButtonClicked = createClick(ElementSearchType.BY_XPATH, "//*[@id=\"allContents\"]/div/div[2]/div[2]/form/div[3]/input[1]");
                        log("appendButtonClicked = "+appendButtonClicked);

                        // detect wrong bid cost
                        log("detecting wrong bid cost attention element exists...");
                        boolean attentionWrongCostElementExists = detectWrongCostAttentionElementExists();

                        boolean wrongBidCostAttentionElementExists = detectPageHasElement.isElementVisible();
                        log("wrongBidCostAttentionElementExists="+wrongBidCostAttentionElementExists);

                        log("attentionWrongCostElementExists="+attentionWrongCostElementExists);

                        if(attentionWrongCostElementExists){
                            // wrong cost
                            // get new cost
                            log("getting new bid cost...");
                            boolean getNewCostOperationComplete = getBidCost(ElementSearchType.BY_XPATH, "//*[@id=\"allContents\"]/div/div[2]/div[2]/form/div[1]/div/dl[3]/dd/input");
                            log("getNewCostOperationComplete="+getNewCostOperationComplete);

                            int newBidCost = Integer.parseInt(getInputValueOperation.getValue());
                            if(newBidCost > 0){
                                bidCost = Integer.parseInt(getInputValueOperation.getValue());
                                log("bidCost="+bidCost);

                                if(userMoney>bidCost){
                                    log("clicking final bid button by css selector");
                                    //boolean finalMakeNormalBidButtonClicked = createClick(ElementSearchType.BY_XPATH, "//*[@id=\"allContents\"]/div/div[2]/div[2]/form/div[2]/input[1]");
                                    boolean finalMakeNormalBidButtonClicked = createClick(ElementSearchType.BY_CSS_SELECTOR, "#allContents > div > div.l-contents > div.l-contentsBody > form > div.SubmitBox > input.SubmitBox__button.SubmitBox__button--rebid");
                                    log("finalMakeNormalBidButtonClicked="+finalMakeNormalBidButtonClicked);

                                    if(finalMakeNormalBidButtonClicked){
                                        log("detecting bid has been set");
                                        bidHasBeenSet = detectBidHasBeenSet();

                                        log("bidHasBeenSet="+bidHasBeenSet);

                                        sendResult(bidHasBeenSet);
                                    }
                                    else{
                                        BiddingResultEvent biddingResultEvent = new BiddingResultEvent(BiddingResultEvent.NORMAL_BID_ERROR);
                                        EventDispatcher.getInstance().dispatchEvent(biddingResultEvent);
                                    }
                                }
                                else{
                                    dispatchNotEnoughMoney();
                                }
                            }
                            else{
                                log("Error: new bid cost incorrect");
                            }
                        }
                        else{
                            log("detecting bid has been set");
                            bidHasBeenSet = detectBidHasBeenSet();
                            log("bidHasBeenSet="+bidHasBeenSet);

                            sendResult(bidHasBeenSet);
                        }
                    }
                    else{
                        log("Error setting new bid cost");
                    }
                }
                else{
                    log("Error: undefined new cost input value");
                }
            }
            else{
                log("new bid cost input not exists error");
                BiddingResultEvent biddingResultEvent = new BiddingResultEvent(BiddingResultEvent.NORMAL_BID_ERROR);
                JSONObject data = new JSONObject();
                data.put("errorPayload","RESET_BID_COST_INPUT_NOT_EXISTS");
                biddingResultEvent.setData(data.toString());
                EventDispatcher.getInstance().dispatchEvent(biddingResultEvent);
            }
        }
        else{
            log("resetBidButtonClick error");
            BiddingResultEvent biddingResultEvent = new BiddingResultEvent(BiddingResultEvent.NORMAL_BID_ERROR);
            JSONObject data = new JSONObject();
            data.put("errorPayload","RESET_BID_BUTTON_CLICK_ERROR");
            biddingResultEvent.setData(data.toString());
            EventDispatcher.getInstance().dispatchEvent(biddingResultEvent);
        }
        return true;
    }

    private boolean createClick(ElementSearchType type, String anchor) throws OperationException {
        CreateClick clickOperation = new CreateClick(webDriver, type, anchor);
        return clickOperation.execute();
    }

    private boolean detectBidCostInputExists() throws OperationException{
        DetectPageHasElementOperation elementExistsOperation = new DetectPageHasElementOperation();
        elementExistsOperation.setWebDriver(webDriver);

        OperationData internalOperationData = new OperationData();
        internalOperationData.setElementSearchType(ElementSearchType.BY_XPATH);
        //internalOperationData.setElementSearchData("//*[@id=\"BidModals\"]/div/div[2]/div[2]/form/div[1]/label/input");
        internalOperationData.setElementSearchData("//*[@id=\"BidModals\"]/div/div[2]/div[2]/form/input[6]");

        elementExistsOperation.setOperationData(internalOperationData);
        return elementExistsOperation.execute();

    }

    private boolean getBidCost(ElementSearchType type, String xPath) throws OperationException {
        getInputValueOperation = new GetInputValueOperation();
        getInputValueOperation.setWebDriver(webDriver);

        OperationData internalOperationData = new OperationData();
        internalOperationData.setElementSearchType(type);
        internalOperationData.setElementSearchData(xPath);

        getInputValueOperation.setOperationData(internalOperationData);
        return getInputValueOperation.execute();
    }

    private boolean setNewBidCost() throws OperationException{
        SetTextOperation setTextOperation = new SetTextOperation();
        setTextOperation.setWebDriver(webDriver);

        OperationData internalOperationData = new OperationData();
        internalOperationData.setElementSearchType(ElementSearchType.BY_XPATH);
        internalOperationData.setElementSearchData("//*[@id=\"BidModals\"]/div/div[2]/div[2]/form/div[1]/label/input");
        internalOperationData.setPayload(String.valueOf(bidCost));

        setTextOperation.setOperationData(internalOperationData);

        return setTextOperation.execute();
    }

    private boolean detectWrongCostAttentionElementExists() throws OperationException{
        detectPageHasElement = new DetectPageHasElementOperation();
        detectPageHasElement.setWebDriver(webDriver);

        OperationData internalOperationData = new OperationData();
        internalOperationData.setElementSearchType(ElementSearchType.BY_XPATH);
        internalOperationData.setElementSearchData("//*[@id=\"allContents\"]/div/div[2]/div[2]/div[1]");

        detectPageHasElement.setOperationData(internalOperationData);

        return detectPageHasElement.execute();
    }

    private boolean detectBidHasBeenSet() throws OperationException {
        DetectBidHasBeenSetOperation detectBidHasBeenSetOperation = new DetectBidHasBeenSetOperation();
        detectBidHasBeenSetOperation.setWebDriver(webDriver);

        return detectBidHasBeenSetOperation.execute();
    }

    private void sendResult(boolean bidHasBeenSet){
        if(bidHasBeenSet){
            JSONObject dataObject = new JSONObject();
            dataObject.put("bidValue",bidCost);

            BiddingResultEvent biddingResultEvent = new BiddingResultEvent(BiddingResultEvent.NORMAL_BID_COMPLETE);
            biddingResultEvent.setData(dataObject.toString());
            EventDispatcher.getInstance().dispatchEvent(biddingResultEvent);
        }
        else{
            BiddingResultEvent biddingResultEvent = new BiddingResultEvent(BiddingResultEvent.NORMAL_BID_ERROR);
            EventDispatcher.getInstance().dispatchEvent(biddingResultEvent);
        }
    }

    private void dispatchNotEnoughMoney(){
        BiddingResultEvent biddingResultEvent = new BiddingResultEvent(BiddingResultEvent.NOT_ENOUGH_MONEY);
        EventDispatcher.getInstance().dispatchEvent(biddingResultEvent);
    }
}
