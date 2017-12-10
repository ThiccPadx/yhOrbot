package dev.div0.robotOperations.yhOpeartionsSequence.bidding;


import dev.div0.logging.BaseLogger;
import dev.div0.robotOperations.OperationException;
import org.openqa.selenium.WebDriver;

public class BaseStrategy extends BaseLogger implements IBiddingStrategy {
    protected int bidCost;
    protected int blitzBidCost;

    public BaseStrategy(int bidCost, int blitzBidCost) {
        this.bidCost = bidCost;
        this.blitzBidCost = blitzBidCost;
    }

    @Override
    public boolean execute(int userMoney, WebDriver WebDriver) throws OperationException {
        return true;
    }
}
