package tests;

import dev.div0.application.page.YahooPage;
import dev.div0.robotOperations.DetectPageHasTextOperation;
import dev.div0.robotOperations.OperationException;
import dev.div0.robotOperations.operationData.OperationData;
import dev.div0.steps.ElementSearchType;

public class DetectAlreadyLoggedInTest extends BaseTest{

    private String login = "kaitoriparts";

    public DetectAlreadyLoggedInTest() {
        super();
    }

    @Override
    protected boolean execute(){
        boolean result = false;
        try {
            result = detectAlreadyLoggedIn();
        } catch (OperationException e) {
            e.printStackTrace();
        }
        return result;
    }

    private boolean detectAlreadyLoggedIn() throws OperationException{

        DetectPageHasTextOperation detectPageHasLoginText = new DetectPageHasTextOperation();
        detectPageHasLoginText.setWebDriver(webDriver);

        OperationData internalOperationData = new OperationData();
        internalOperationData.setElementSearchType(ElementSearchType.BY_CSS_CLASS);
        internalOperationData.setElementSearchData(YahooPage.auth_loggedInputId);
        internalOperationData.setPayload("//*[contains(text(), '"+login+"')]");

        detectPageHasLoginText.setOperationData(internalOperationData);
        return detectPageHasLoginText.execute();
    }
}
