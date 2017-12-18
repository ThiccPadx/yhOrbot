package dev.div0.robotOperations.yhOpeartionsSequence.bidding;


import dev.div0.application.page.YahooPage;
import dev.div0.events.EventDispatcher;
import dev.div0.robotOperations.ClickElementOperation;
import dev.div0.robotOperations.OperationException;
import dev.div0.robotOperations.operationData.OperationData;
import dev.div0.robotOperations.yhOpeartionsSequence.bidding.events.BiddingResultEvent;
import dev.div0.steps.ElementSearchType;
import org.openqa.selenium.WebDriver;

public class BidAndBlitzBidCostsStrategy extends BaseStrategy{

    public BidAndBlitzBidCostsStrategy(int bidCost, int blitzBidCost) {
        super(bidCost, blitzBidCost);
    }
    private int userMoney;
    private WebDriver webDriver;

    @Override
    public boolean execute(int userMoney, WebDriver webDriver) throws OperationException{
        log("BidAndBlitzBidCostsStrategy execute userMoney="+userMoney+" blitzBidCost="+blitzBidCost);
        this.userMoney = userMoney;
        this.webDriver = webDriver;

        if(userMoney >= blitzBidCost){
            // create blitz click
            log("createBlitzButtonClick");
            createClick(ElementSearchType.BY_XPATH, YahooPage.bidding_blitzBidButtonXpath);

            log("createModalSubmitButtonClick");
            createClick(ElementSearchType.BY_XPATH, YahooPage.bidding_bidModalSubmitButtonXPath);

            log("createFinalSubmitButtonClick");
            boolean finalSubmitButtonClicked = createClick(ElementSearchType.BY_XPATH, "//*[@id=\"allContents\"]/div[1]/div[2]/div[2]/form/div[3]/input[1]");

            log("finalSubmitButtonClicked = "+finalSubmitButtonClicked);

            boolean bidHasBeenSet = detectBidHasBeenSet();

            log("bidHasBeenSet="+bidHasBeenSet);
            if(bidHasBeenSet){
                BiddingResultEvent biddingResultEvent = new BiddingResultEvent(BiddingResultEvent.BLITZ_BID_COMPLETE);
                EventDispatcher.getInstance().dispatchEvent(biddingResultEvent);
            }
            else{
                BiddingResultEvent biddingResultEvent = new BiddingResultEvent(BiddingResultEvent.BLITZ_BID_ERROR);
                EventDispatcher.getInstance().dispatchEvent(biddingResultEvent);
            }

            log("Blitz Bidding complete");
        }
        else{
            if(userMoney<bidCost){
                // not enough money for any bid
                log("not enough money for any bid");
                BiddingResultEvent biddingResultEvent = new BiddingResultEvent(BiddingResultEvent.NOT_ENOUGH_MONEY);
                EventDispatcher.getInstance().dispatchEvent(biddingResultEvent);
            }
            else{
                // create bid click
                log("Not enough monet for blitz - creating normal bid click...");
                log("clicking normal bid button");
                createClick(ElementSearchType.BY_XPATH, YahooPage.bidding_makeBidButtonXpath);

                log("clicking normal bid modal dialog button");
                createClick(ElementSearchType.BY_XPATH, YahooPage.bidding_normalBidModalSubmitButtonXPath);

                log("clicking submit button for normal bid");
                boolean finalSubmitButtonClicked = createClick(ElementSearchType.BY_XPATH, "//*[@id=\"allContents\"]/div/div[2]/div[2]/form/div[3]/input[1]");

                boolean bidHasBeenSet = detectBidHasBeenSet();

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
        }

        return true;
    }

    private boolean detectBidHasBeenSet() throws OperationException {
        DetectBidHasBeenSetOperation detectBidHasBeenSetOperation = new DetectBidHasBeenSetOperation();
        detectBidHasBeenSetOperation.setWebDriver(webDriver);

        return detectBidHasBeenSetOperation.execute();
    }

    private boolean createClick(ElementSearchType type, String anchor) throws OperationException {
        ClickElementOperation clickElementOperation = new ClickElementOperation();
        clickElementOperation.setWebDriver(webDriver);

        OperationData internalOperationData = new OperationData();
        internalOperationData.setElementSearchType(type);
        internalOperationData.setElementSearchData(anchor);

        clickElementOperation.setOperationData(internalOperationData);
        return clickElementOperation.execute();
    }
}
