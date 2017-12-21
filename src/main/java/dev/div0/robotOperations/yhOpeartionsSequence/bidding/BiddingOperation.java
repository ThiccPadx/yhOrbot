package dev.div0.robotOperations.yhOpeartionsSequence.bidding;

import dev.div0.application.page.YahooPage;
import dev.div0.events.BaseEvent;
import dev.div0.events.EventDispatcher;
import dev.div0.events.IEventListener;
import dev.div0.robotOperations.*;
import dev.div0.robotOperations.events.OperationEvent;
import dev.div0.robotOperations.operationData.OperationData;
import dev.div0.robotOperations.yhOpeartionsSequence.bidding.events.BiddingResultEvent;
import dev.div0.steps.ElementSearchType;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.StringReader;

public class BiddingOperation extends BaseOperation implements IEventListener {

    private int currentTryout = 0;
    private int maxTryouts = 5;
    private int[]timeouts = {1,7};

    private int userMoney = 0;
    private String lotUrl;

    private boolean buttonMakeNormalBidExists = false;

    private boolean buttonMakeBidExists = false;
    private boolean buttonBlitzBidExists = false;

    private int normalBidCost = -1;
    private int blitzBidCost = -1;

    private String GETTING_BID_COST_VALUE = "GETTING_BID_COST_VALUE";
    private String GETTING_BLITZ_BID_COST_VALUE = "GETTING_BLITZ_BID_COST_VALUE";
    private String BID_ALREADY_SET_ERROR = "BID_ALREADY_SET_ERROR";

    private String currentState = "" ;

    private IBiddingStrategy strategy;

    public BiddingOperation() {
        super();
        EventDispatcher.getInstance().addEventListener(this);
    }

    @Override
    public void setOperationData(OperationData operationData) {
        super.setOperationData(operationData);
        log("BiddingOperation payload = "+operationData.getPayload());

        JSONObject json;
        JSONParser parser = new JSONParser();

        try {
            json = (JSONObject) parser.parse(new StringReader(operationData.getPayload().toString()));
            lotUrl = json.get("lotUrl").toString();
            String moneyString = json.get("userMoney").toString();
            userMoney = Integer.parseInt(moneyString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean execute() throws OperationException {
        log("bidding execute. currentTryout: "+ currentTryout);
        log("lot url "+lotUrl);

        OperationEvent operationEvent = new OperationEvent(OperationEvent.LOT_URL);
        operationEvent.setData(lotUrl);
        EventDispatcher.getInstance().dispatchEvent(operationEvent);

        log("userMoney "+ userMoney);

        log("loading lot page...");
        boolean lotPageOpenResult = loadLotPage();

        // detect bid already set error
        boolean bidAlreadySet = detectBidAlreadySet();
        log("bid already set: "+bidAlreadySet);

        if(bidAlreadySet){
            log("Finish bidding");
            BiddingResultEvent biddingResultEvent = new BiddingResultEvent(BiddingResultEvent.BID_ALREADY_SET);
            EventDispatcher.getInstance().dispatchEvent(biddingResultEvent);
        }
        else{
            log("detecting page has normal bid button...");
            buttonMakeBidExists = detectButtonBidExists();
            log("button make bid exists: "+ buttonMakeBidExists);

            log("detecting page has blitz bid button ...");
            buttonBlitzBidExists = detectButtonBlitzBidExists();
            log("buttonBlitzBidExists: "+ buttonBlitzBidExists);

            log("detecting normal bid button exists...");
            buttonMakeNormalBidExists = detectNormalBidButtonExists();
            log("buttonMakeNormalBidExists: "+ buttonMakeNormalBidExists);

            boolean normalBidCostExists = detectElementExists(ElementSearchType.BY_XPATH, "//*[@id=\"l-sub\"]/div[1]/ul/li[2]/div/dl/dd");
            boolean normalBidForBidAndBlitzCostExists = detectElementExists(ElementSearchType.BY_XPATH, "//*[@id=\"l-sub\"]/div[1]/ul/li[2]/div[1]/dl/dd");
            boolean blitzBidCostExists = detectElementExists(ElementSearchType.BY_XPATH, "//*[@id=\"l-sub\"]/div[1]/ul/li[2]/div[2]/dl/dd");

            if(normalBidCostExists){
                currentState = GETTING_BID_COST_VALUE;
                log("getting normal bid cost");
                getBidCost(ElementSearchType.BY_XPATH, "//*[@id=\"l-sub\"]/div[1]/ul/li[2]/div/dl/dd");
            }
            if(normalBidForBidAndBlitzCostExists){
                log("getting bid cost");
                getBidCost(ElementSearchType.BY_XPATH, "//*[@id=\"l-sub\"]/div[1]/ul/li[2]/div[1]/dl/dd");
            }
            if(blitzBidCostExists){
                currentState = GETTING_BLITZ_BID_COST_VALUE;
                log("getting blitz bid cost");
                getBidCost(ElementSearchType.BY_XPATH, "//*[@id=\"l-sub\"]/div[1]/ul/li[2]/div[2]/dl/dd");
            }

            if(!normalBidCostExists && !normalBidForBidAndBlitzCostExists && !blitzBidCostExists){
                log("Lot closed error");
                JSONObject eventDataObject = new JSONObject();
                eventDataObject.put("errorPayload", "NO_BUTTONS_AVAILABLE");
                BiddingResultEvent biddingResultEvent = new BiddingResultEvent(BiddingResultEvent.LOT_CLOSED);
                biddingResultEvent.setData(eventDataObject.toString());
                EventDispatcher.getInstance().dispatchEvent(biddingResultEvent);
            }
            else{
                createStrategy();
                executeStrategy();
            }
        }

        log("Bidding complete");
        return true;
    }

    private boolean executeStrategy() throws OperationException {
        strategy.execute(userMoney, webDriver);
        return true;
    }

    private void createStrategy(){
        strategy = BiddingStrategyFactory.getStrategy(normalBidCost, blitzBidCost);
    }

    private boolean detectBidAlreadySet() throws OperationException {
        DetectBidHasBeenSetOperation detectBidHasBeenSet = new DetectBidHasBeenSetOperation();
        detectBidHasBeenSet.setWebDriver(webDriver);
        return detectBidHasBeenSet.execute();
    }

    private void onElementTextResult(String elementText){
        if(currentState.equals(GETTING_BID_COST_VALUE)){
            normalBidCost = parseCostData(elementText);
            log("bidCost="+ normalBidCost);
        }
        else if(currentState.equals(GETTING_BLITZ_BID_COST_VALUE)){
            blitzBidCost = parseCostData(elementText);
            log("blitzBidCost="+ blitzBidCost);
        }
        else{
            normalBidCost = -1;
            blitzBidCost = -1;
            log("LOT_ERROR: undefined bidCost and blitzBidCost ");
        }
    }
    private int parseCostData(String data){
        String[] parts = data.split("<span");
        String costData = parts[0];
        String costValue = costData.replaceAll("\\D+","");
        try{
            return Integer.parseInt(costValue);
        }
        catch(NumberFormatException exception){
            return -1;
        }
    }

    private boolean getBidCost(ElementSearchType type, String xPath) throws OperationException {
        GetTextOperation getHtmlOperation = new GetHtmlOperation();
        getHtmlOperation.setWebDriver(webDriver);

        OperationData internalOperationData = new OperationData();
        internalOperationData.setElementSearchType(type);
        internalOperationData.setElementSearchData(xPath);

        //internalOperationData.setElementSearchData("//*[@id=\"l-sub\"]/div[1]/ul/li[2]/div[1]/dl/dd");
        getHtmlOperation.setOperationData(internalOperationData);
        return getHtmlOperation.execute();
    }

    private boolean detectElementExists(ElementSearchType type, String xPath) throws OperationException{
        DetectPageHasElementOperation detectPageHasElement = new DetectPageHasElementOperation();
        detectPageHasElement.setWebDriver(webDriver);

        OperationData internalOperationData = new OperationData();
        internalOperationData.setElementSearchType(type);
        internalOperationData.setElementSearchData(xPath);

        detectPageHasElement.setOperationData(internalOperationData);

        return detectPageHasElement.execute();
    }

    private boolean detectButtonBlitzBidExists() throws OperationException {
        DetectPageHasElementOperation detectPageHasElement = new DetectPageHasElementOperation();
        detectPageHasElement.setWebDriver(webDriver);

        OperationData internalOperationData = new OperationData();
        internalOperationData.setElementSearchType(ElementSearchType.BY_XPATH);
        internalOperationData.setElementSearchData(YahooPage.bidding_blitzBidButtonXpath);

        detectPageHasElement.setOperationData(internalOperationData);

        return detectPageHasElement.execute();
    }

    private boolean detectNormalBidButtonExists() throws OperationException{
        DetectPageHasElementOperation detectPageHasElement = new DetectPageHasElementOperation();
        detectPageHasElement.setWebDriver(webDriver);

        OperationData internalOperationData = new OperationData();
        internalOperationData.setElementSearchType(ElementSearchType.BY_XPATH);
        internalOperationData.setElementSearchData(YahooPage.bidding_makeNormalBidButtonXpath);

        detectPageHasElement.setOperationData(internalOperationData);

        return detectPageHasElement.execute();
    }

    private boolean detectButtonBidExists() throws OperationException {
        DetectPageHasElementOperation detectPageHasElement = new DetectPageHasElementOperation();
        detectPageHasElement.setWebDriver(webDriver);

        OperationData internalOperationData = new OperationData();
        internalOperationData.setElementSearchType(ElementSearchType.BY_XPATH);
        internalOperationData.setElementSearchData(YahooPage.bidding_makeBidButtonXpath);

        detectPageHasElement.setOperationData(internalOperationData);

        return detectPageHasElement.execute();
    }

    private boolean loadLotPage() throws OperationException {
        OpenUrlOperation operation = new OpenUrlOperation();
        operation.setWebDriver(webDriver);

        OperationData internalOperationData = new OperationData();
        internalOperationData.setElementSearchData(lotUrl);

        operation.setOperationData(internalOperationData);

        return operation.execute();
    }

    @Override
    public void eventHandler(BaseEvent event) {
        if(event.getType().equals(OperationEvent.ELEMENT_TEXT_RESULT)){
            onElementTextResult(event.getData().toString());
        }
    }
}
