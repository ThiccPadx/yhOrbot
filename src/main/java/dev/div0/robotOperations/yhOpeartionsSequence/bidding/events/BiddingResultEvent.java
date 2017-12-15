package dev.div0.robotOperations.yhOpeartionsSequence.bidding.events;

import dev.div0.events.BaseEvent;

public class BiddingResultEvent extends BaseEvent{

    public static String BLITZ_BID_COMPLETE = "BLITZ_BID_COMPLETE";
    public static String BLITZ_BID_ERROR = "BLITZ_BID_ERROR";
    public static String BID_ALREADY_SET = "BID_ALREADY_SET";

    public BiddingResultEvent(String type) {
        super(type);
    }
}
