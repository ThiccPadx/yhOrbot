package dev.div0.robotOperations.yhOpeartionsSequence.bidding;

import dev.div0.events.EventDispatcher;
import dev.div0.robotOperations.*;
import dev.div0.robotOperations.operationData.OperationData;
import dev.div0.robotOperations.yhOpeartionsSequence.bidding.events.BiddingResultEvent;
import dev.div0.steps.ElementSearchType;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;

public class BidCostOnlyStrategy extends BaseStrategy {

    private int userMoney;
    private WebDriver webDriver;

    private int currentBid;
    private int bidCost;
    private GetHtmlOperation getHtml;
    //private GetInputValueOperation getInputValueOperation;
    //private DetectPageHasElementOperation detectPageHasElement;

    public BidCostOnlyStrategy(int bidCost, int blitzBidCost) {
        super(bidCost, blitzBidCost);
    }

    @Override
    public boolean execute(int userMoney, WebDriver webDriver) throws OperationException{
        this.userMoney = userMoney;
        this.webDriver = webDriver;

        boolean dialogButtonClicked = false;
        boolean bidHasBeenSet = false;

        log("BidCostOnlyStrategy execute userMoney="+userMoney+"  bidCost="+bidCost);
        if(userMoney>=bidCost){
            log("detecting reset my bid button exists...");
            boolean resetBidButtonExists = detectResetBidCostInputExists();
            log("resetBidButtonExists="+resetBidButtonExists);

            if(resetBidButtonExists){

                OperationData resetBidOperationData = new OperationData();

                JSONObject dataObject = new JSONObject();
                dataObject.put("userMoney", String.valueOf(userMoney));
                resetBidOperationData.setPayload(dataObject.toString());

                ResetBidSequence resetBidSequence = new ResetBidSequence();
                resetBidSequence.setWebDriver(webDriver);
                resetBidSequence.setOperationData(resetBidOperationData);

                resetBidSequence.execute();

                return true;
            }
            else{
                log("searching another create bid button ...");

                log("Creating normal bid click");
                boolean normalBidButtonClicked = createClick(ElementSearchType.BY_XPATH, "//*[@id=\"l-sub\"]/div[1]/ul/li[2]/div/dd/a");
                log("normalBidButtonClicked="+normalBidButtonClicked);
                if(normalBidButtonClicked){
                    log("Normal bid clicked OK");

                    log("clicking dialog button...");
                    dialogButtonClicked = createClick(ElementSearchType.BY_XPATH, "//*[@id=\"BidModal\"]/div[2]/div[2]/form/div[3]/span/input");
                    log("dialogButtonClicked="+dialogButtonClicked);

                    boolean finalSubmitButtonClicked = false;

                    if(dialogButtonClicked){
                        log("createFinalSubmitButtonClick");

                        boolean getNewCostOperationComplete = getBidCost(ElementSearchType.BY_XPATH, "//*[@id=\"allContents\"]/div/div[2]/div[2]/form/div[1]/div/dl[1]/dd");
                        log("getNewCostOperationComplete="+getNewCostOperationComplete);

                        log("bid cost html = "+getHtml.getText());

                        String newBidCostString = getHtml.getText().replaceAll("[^\\d.]", "");

                        bidCost = Integer.parseInt(newBidCostString);
                        log("parsed bid cost "+bidCost);

                        if(bidCost<=userMoney){
                            finalSubmitButtonClicked = createClick(ElementSearchType.BY_XPATH, "//*[@id=\"allContents\"]/div[1]/div[2]/div[2]/form/div[3]/input[1]");
                            log("finalSubmitButtonClicked = "+finalSubmitButtonClicked);

                            bidHasBeenSet = detectBidHasBeenSet();

                            log("bidHasBeenSet="+bidHasBeenSet);
                            sendResult(bidHasBeenSet);
                        }
                        else{
                            // not enough money
                            BiddingResultEvent biddingResultEvent = new BiddingResultEvent(BiddingResultEvent.NOT_ENOUGH_MONEY);

                            JSONObject dataObject = new JSONObject();
                            dataObject.put("userMoney",userMoney);
                            dataObject.put("bidCost", bidCost);
                            biddingResultEvent.setData(dataObject);
                            EventDispatcher.getInstance().dispatchEvent(biddingResultEvent);
                        }
                    }
                    else{
                        log("error click dialog button");
                    }
                }
                else{
                    log("Error clicking normal bid button");
                }

                return true;
            }
        }
        else{
            dispatchNotEnoughMoney();
        }

        return true;
    }

    private boolean getBidCost(ElementSearchType type, String xPath) throws OperationException {
        getHtml = new GetHtmlOperation();
        getHtml.setWebDriver(webDriver);

        OperationData internalOperationData = new OperationData();
        internalOperationData.setElementSearchType(type);
        internalOperationData.setElementSearchData(xPath);

        getHtml.setOperationData(internalOperationData);
        return getHtml.execute();
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

    private boolean detectBidHasBeenSet() throws OperationException {
        DetectBidHasBeenSetOperation detectBidHasBeenSetOperation = new DetectBidHasBeenSetOperation();
        detectBidHasBeenSetOperation.setWebDriver(webDriver);

        return detectBidHasBeenSetOperation.execute();
    }

    private void dispatchNotEnoughMoney(){
        BiddingResultEvent biddingResultEvent = new BiddingResultEvent(BiddingResultEvent.NOT_ENOUGH_MONEY);
        EventDispatcher.getInstance().dispatchEvent(biddingResultEvent);
    }

    private boolean detectResetBidCostInputExists() throws OperationException{
        DetectPageHasElementOperation elementExistsOperation = new DetectPageHasElementOperation();
        elementExistsOperation.setWebDriver(webDriver);

        OperationData internalOperationData = new OperationData();
        internalOperationData.setElementSearchType(ElementSearchType.BY_XPATH);
        internalOperationData.setElementSearchData("//*[@id=\"l-sub\"]/div[1]/ul/li[2]/div/div[1]/a");

        elementExistsOperation.setOperationData(internalOperationData);
        return elementExistsOperation.execute();
    }

    private boolean createClick(ElementSearchType type, String anchor) throws OperationException {
        CreateClick clickOperation = new CreateClick(webDriver, type, anchor);
        return clickOperation.execute();
    }
}
