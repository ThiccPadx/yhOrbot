package dev.div0.robotOperations.bidding;


import dev.div0.application.page.YahooPage;
import dev.div0.robotOperations.ClickElementOperation;
import dev.div0.robotOperations.OperationException;
import dev.div0.robotOperations.operationData.OperationData;
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
        log("BidAndBlitzBidCostsStrategy execute userMoney="+userMoney);
        this.userMoney = userMoney;
        this.webDriver = webDriver;

        if(userMoney >= blitzBidCost){
            // create blitz click
            createBlitzButtonClick();
            createModalSubmitButtonClick();
        }
        else{
            // create bet click

        }

        return true;
    }

    private boolean createBlitzButtonClick() throws OperationException {
        log("createBlitzButtonClick");
        ClickElementOperation clickElementOperation = new ClickElementOperation();
        clickElementOperation.setWebDriver(webDriver);

        OperationData internalOperationData = new OperationData();
        internalOperationData.setElementSearchType(ElementSearchType.BY_XPATH);
        internalOperationData.setElementSearchData(YahooPage.bidding_blitzBidButtonXpath);

        clickElementOperation.setOperationData(internalOperationData);
        return clickElementOperation.execute();
    }

    private boolean createModalSubmitButtonClick() throws OperationException {
        log("createModalSubmitButtonClick");
        ClickElementOperation clickElementOperation = new ClickElementOperation();
        clickElementOperation.setWebDriver(webDriver);

        OperationData internalOperationData = new OperationData();
        internalOperationData.setElementSearchType(ElementSearchType.BY_XPATH);
        internalOperationData.setElementSearchData(YahooPage.bidding_bidModalSubmitButtonXPath);

        clickElementOperation.setOperationData(internalOperationData);
        return clickElementOperation.execute();
    }
}
