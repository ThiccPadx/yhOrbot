package tests;

import dev.div0.robotOperations.OpenUrlOperation;
import dev.div0.robotOperations.OperationException;
import dev.div0.robotOperations.operationData.OperationData;
import dev.div0.webDriver.BrowserType;
import dev.div0.webDriver.CreateWebDriver;
import org.openqa.selenium.WebDriver;

public class TestingGetHtmlOperation extends BaseTest{

    public TestingGetHtmlOperation() {
        super();
    }

    @Override
    protected boolean execute(){
        boolean result = false;
        try {
            result = openLotTest();
        } catch (OperationException e) {
            e.printStackTrace();
        }
        return result;
    }

    private boolean openLotTest() throws OperationException {
        OpenUrlOperation operation = new OpenUrlOperation();
        operation.setWebDriver(webDriver);

        OperationData internalOperationData = new OperationData();
        internalOperationData.setElementSearchData("https://page.auctions.yahoo.co.jp/jp/auction/w208369861");

        operation.setOperationData(internalOperationData);

        return operation.execute();
    }
}
