package dev.div0.robotOperations.yhOpeartionsSequence.bidding;

import dev.div0.events.EventDispatcher;
import dev.div0.robotOperations.*;
import dev.div0.robotOperations.operationData.OperationData;
import dev.div0.robotOperations.yhOpeartionsSequence.bidding.events.BiddingResultEvent;
import dev.div0.steps.ElementSearchType;
import org.openqa.selenium.WebDriver;

public class BidCostOnlyStrategy extends BaseStrategy {

    private int userMoney;
    private WebDriver webDriver;

    private int currentBid;
    private int bidCost;
    private GetInputValueOperation getInputValueOperation;
    private DetectPageHasElementOperation detectPageHasElement;

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
                resetBidOperationData.setPayload(String.valueOf(userMoney));

                ResetBidSequence resetBidSequence = new ResetBidSequence();
                resetBidSequence.setWebDriver(webDriver);
                resetBidSequence.setOperationData(resetBidOperationData);

                resetBidSequence.execute();

                /*
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
                */

                return true;
            }
            else{
                log("searching another bid button ...");
                return true;
            }

            /*
            log("Creating normal bid click");
            boolean normalBidButtonClicked = createClick(ElementSearchType.BY_XPATH, "//*[@id=\"l-sub\"]/div[1]/ul/li[2]/div/dd/a");

            log("normalBidButtonClicked="+normalBidButtonClicked);

            log("normalBidButtonClicked = "+normalBidButtonClicked);

            if(normalBidButtonClicked){
                // detect cost
                log("detecting bid cost input exists...");
                boolean bidCostExists = detectBidCostInputExists();
                log("bidCostExists="+bidCostExists);


                log("clicking dialog button...");
                dialogButtonClicked = createClick(ElementSearchType.BY_XPATH, "//*[@id=\"BidModal\"]/div[2]/div[2]/form/div[3]/span/input");
                log("dialogButtonClicked="+dialogButtonClicked);

                boolean finalSubmitButtonClicked = false;

                if(dialogButtonClicked){
                    log("createFinalSubmitButtonClick");
                    finalSubmitButtonClicked = createClick(ElementSearchType.BY_XPATH, "//*[@id=\"allContents\"]/div[1]/div[2]/div[2]/form/div[3]/input[1]");
                    log("finalSubmitButtonClicked = "+finalSubmitButtonClicked);

                    bidHasBeenSet = detectBidHasBeenSet();

                    log("bidHasBeenSet="+bidHasBeenSet);
                    if(bidHasBeenSet){
                        log("Normal Bidding complete");
                        BiddingResultEvent biddingResultEvent = new BiddingResultEvent(BiddingResultEvent.NORMAL_BID_COMPLETE);
                        EventDispatcher.getInstance().dispatchEvent(biddingResultEvent);
                    }
                    else{
                        log("Normal Bidding error");
                        BiddingResultEvent biddingResultEvent = new BiddingResultEvent(BiddingResultEvent.NORMAL_BID_ERROR);
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
            */
        }
        else{
            dispatchNotEnoughMoney();
        }

        return true;
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

    /*
    private boolean createClick(ElementSearchType type, String anchor) throws OperationException {
        CreateClick clickOperation = new CreateClick(webDriver, type, anchor);
        return clickOperation.execute();
    }
    */
}
