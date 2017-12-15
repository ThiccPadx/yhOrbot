package dev.div0.robotOperations.yhOpeartionsSequence.events;

import dev.div0.events.BaseEvent;

public class YahooSequenceEvent extends BaseEvent{

    public static String COMPLETE = "COMPLETE";
    public static String ERROR = "ERROR";

    public YahooSequenceEvent(String type) {
        super(type);
    }
}
