package dev.div0.robotOperations.bidding;


public class BiddingStrategyFactory {
    public static IBiddingStrategy getStrategy(int bidCost, int blitzBidCost) {
        if(bidCost == -1 && blitzBidCost == -1){
            return new NoCostsStrategy(bidCost, blitzBidCost);
        }
        else if(bidCost!=-1 && blitzBidCost == -1){
            return new BidCostOnlyStrategy(bidCost, blitzBidCost);
        }
        else if(bidCost != -1 && blitzBidCost != -1){
            return new BidAndBlitzBidCostsStrategy(bidCost, blitzBidCost);
        }
        return null;
    }
}
