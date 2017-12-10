package dev.div0.robotOperations.yhOpeartionsSequence.bidding;


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
            log("createBlitzButtonClick");
            createClick(ElementSearchType.BY_XPATH, YahooPage.bidding_blitzBidButtonXpath);

            log("createModalSubmitButtonClick");
            createClick(ElementSearchType.BY_XPATH, YahooPage.bidding_bidModalSubmitButtonXPath);
            //createModalSubmitButtonClick();

            log("createFinalSubmitButtonClick");
            boolean finalSubmitButtonClicked = createClick(ElementSearchType.BY_XPATH, "//*[@id=\"allContents\"]/div[1]/div[2]/div[2]/form/div[3]/input[1]");
            //boolean finalSubmitButtonClicked = createFinalSubmitButtonClick();

            log("finalSubmitButtonClicked = "+finalSubmitButtonClicked);
            log("Bidding complete");
        }
        else{
            // create bit click
        }

        return true;
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

    /*
    private boolean createBlitzButtonClick() throws OperationException {
        ClickElementOperation clickElementOperation = new ClickElementOperation();
        clickElementOperation.setWebDriver(webDriver);

        OperationData internalOperationData = new OperationData();
        internalOperationData.setElementSearchType(ElementSearchType.BY_XPATH);
        internalOperationData.setElementSearchData(YahooPage.bidding_blitzBidButtonXpath);

        clickElementOperation.setOperationData(internalOperationData);
        return clickElementOperation.execute();
    }

    private boolean createFinalSubmitButtonClick() throws OperationException {

        ClickElementOperation clickElementOperation = new ClickElementOperation();
        clickElementOperation.setWebDriver(webDriver);

        OperationData internalOperationData = new OperationData();
        internalOperationData.setElementSearchType(ElementSearchType.BY_XPATH);
        internalOperationData.setElementSearchData("//*[@id=\"allContents\"]/div[1]/div[2]/div[2]/form/div[3]/input[1]");

        clickElementOperation.setOperationData(internalOperationData);
        return clickElementOperation.execute();
    }

    private boolean createModalSubmitButtonClick() throws OperationException {

        ClickElementOperation clickElementOperation = new ClickElementOperation();
        clickElementOperation.setWebDriver(webDriver);

        OperationData internalOperationData = new OperationData();
        internalOperationData.setElementSearchType(ElementSearchType.BY_XPATH);
        internalOperationData.setElementSearchData(YahooPage.bidding_bidModalSubmitButtonXPath);

        clickElementOperation.setOperationData(internalOperationData);
        return clickElementOperation.execute();
    }
    */
}
