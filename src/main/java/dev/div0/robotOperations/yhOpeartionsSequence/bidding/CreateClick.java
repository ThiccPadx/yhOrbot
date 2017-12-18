package dev.div0.robotOperations.yhOpeartionsSequence.bidding;

import dev.div0.robotOperations.ClickElementOperation;
import dev.div0.robotOperations.OperationException;
import dev.div0.robotOperations.operationData.OperationData;
import dev.div0.steps.ElementSearchType;
import org.openqa.selenium.WebDriver;

public class CreateClick {

    private ElementSearchType type;
    private String anchor;
    private WebDriver webDriver;

    public CreateClick(WebDriver webDriver, ElementSearchType type, String anchor) {
        this.type = type;
        this.anchor = anchor;
        this.webDriver = webDriver;
    }

    public boolean execute() throws OperationException {
        ClickElementOperation clickElementOperation = new ClickElementOperation();
        clickElementOperation.setWebDriver(webDriver);

        OperationData internalOperationData = new OperationData();
        internalOperationData.setElementSearchType(type);
        internalOperationData.setElementSearchData(anchor);

        clickElementOperation.setOperationData(internalOperationData);
        return clickElementOperation.execute();
    }
}
