package dev.div0.robotOperations.yhOpeartionsSequence.bidding;

public class BidCostParser {

    public static int parse(String data) {
        String[] parts = data.split("<span");
        String costData = parts[0];
        String costValue = costData.replaceAll("\\D+","");

        return Integer.parseInt(costValue);
    }
}
