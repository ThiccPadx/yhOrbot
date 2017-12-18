package dev.div0.robotOperations.yhOpeartionsSequence.bidding.events;

import dev.div0.events.BaseEvent;

public class BiddingResultEvent extends BaseEvent{

    public static String BLITZ_BID_COMPLETE = "BLITZ_BID_COMPLETE";
    public static String BLITZ_BID_ERROR = "BLITZ_BID_ERROR";

    public static String NORMAL_BID_COMPLETE = "NORMAL_BID_COMPLETE";
    public static String NORMAL_BID_ERROR = "BLITZ_BID_ERROR";

    public static String BID_ALREADY_SET = "BID_ALREADY_SET";
    public static String NOT_ENOUGH_MONEY = "NOT_ENOUGH_MONEY";

    public static String LOT_CLOSED = "LOT_CLOSED";

    public BiddingResultEvent(String type) {
        super(type);
    }
}
