package dev.div0.robotOperations.bidding;

import org.openqa.selenium.WebDriver;

public class NoCostsStrategy extends BaseStrategy {

    public NoCostsStrategy(int bidCost, int blitzBidCost) {
        super(bidCost, blitzBidCost);
    }

    @Override
    public boolean execute(int userMoney, WebDriver webDriver) {
        log("NoCostsStrategy");
        return false;
    }
}
