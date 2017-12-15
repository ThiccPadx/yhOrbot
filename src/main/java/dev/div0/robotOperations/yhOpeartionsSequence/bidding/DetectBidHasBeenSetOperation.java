package dev.div0.robotOperations.yhOpeartionsSequence.bidding;

import dev.div0.application.page.YahooPage;
import dev.div0.robotOperations.BaseOperation;
import dev.div0.robotOperations.DetectPageHasElementOperation;
import dev.div0.robotOperations.OperationException;
import dev.div0.robotOperations.operationData.OperationData;
import dev.div0.steps.ElementSearchType;

public class DetectBidHasBeenSetOperation extends BaseOperation {
    public DetectBidHasBeenSetOperation() {
        super();
    }

    @Override
    public boolean execute() throws OperationException {
        DetectPageHasElementOperation detectHasBidAlreadySetOperation = new DetectPageHasElementOperation();
        detectHasBidAlreadySetOperation.setWebDriver(webDriver);

        OperationData internalOperationData = new OperationData();
        internalOperationData.setElementSearchType(ElementSearchType.BY_XPATH);
        internalOperationData.setElementSearchData(YahooPage.bidding_bidAlreadySetImageXPath);
        detectHasBidAlreadySetOperation.setOperationData(internalOperationData);

        return detectHasBidAlreadySetOperation.execute();
    }
}
