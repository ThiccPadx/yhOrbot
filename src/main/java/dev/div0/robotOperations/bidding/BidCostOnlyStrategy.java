package dev.div0.robotOperations.bidding;


import org.openqa.selenium.WebDriver;

public class BidCostOnlyStrategy extends BaseStrategy {

    public BidCostOnlyStrategy(int bidCost, int blitzBidCost) {
        super(bidCost, blitzBidCost);
    }

    @Override
    public boolean execute(int userMoney, WebDriver webDriver) {
        log("BidCostOnlyStrategy execute");
        return true;
    }
}
