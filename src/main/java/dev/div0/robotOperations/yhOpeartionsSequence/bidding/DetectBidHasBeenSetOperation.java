package dev.div0.robotOperations.yhOpeartionsSequence.bidding;

import dev.div0.application.page.YahooPage;
import dev.div0.robotOperations.BaseOperation;
import dev.div0.robotOperations.DetectPageHasElementOperation;
import dev.div0.robotOperations.OperationException;
import dev.div0.robotOperations.operationData.OperationData;
import dev.div0.steps.ElementSearchType;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;

import java.util.Iterator;
import java.util.List;

public class DetectBidHasBeenSetOperation extends BaseOperation {
    public DetectBidHasBeenSetOperation() {
        super();
    }

    @Override
    public boolean execute() throws OperationException {
        log("DetectBidHasBeenSetOperation.execute()");

        DetectPageHasElementOperation detectHasBidAlreadySetOperation = new DetectPageHasElementOperation();
        detectHasBidAlreadySetOperation.setWebDriver(webDriver);

        OperationData internalOperationData = new OperationData();
        internalOperationData.setElementSearchType(ElementSearchType.BY_XPATH);
        internalOperationData.setElementSearchData(YahooPage.bidding_bidAlreadySetImageXPath);
        detectHasBidAlreadySetOperation.setOperationData(internalOperationData);

        boolean pageHasElement1 = detectHasBidAlreadySetOperation.execute();
        log("pageHasElement by Xpath "+YahooPage.bidding_bidAlreadySetImageXPath+" : "+pageHasElement1);

        detectHasBidAlreadySetOperation = new DetectPageHasElementOperation();
        detectHasBidAlreadySetOperation.setWebDriver(webDriver);

        internalOperationData = new OperationData();
        internalOperationData.setElementSearchType(ElementSearchType.BY_XPATH);
        internalOperationData.setElementSearchData("//*[@id=\"modWlBtnArea\"]/div/p/a/img");
        detectHasBidAlreadySetOperation.setOperationData(internalOperationData);

        boolean pageHasElement2 = detectHasBidAlreadySetOperation.execute();
        log("pageHasElement by Xpath //" +
                "*[@id=\"modWlBtnArea\"]/div/p/a/img : "+pageHasElement2);

        if(pageHasElement2 == true || pageHasElement1 == true){
            return true;
        }
        else{
            log("Element not found");

            return false;
        }
    }
}
