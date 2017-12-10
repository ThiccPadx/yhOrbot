package dev.div0.robotOperations.yhOpeartionsSequence;

import org.json.simple.JSONObject;

/**
 * Created by Ilya on 10.12.2017.
 */
public class ResultEncoder {
    public static String encode(YahooSequenceResult result){
        JSONObject dataObject = new JSONObject();
        dataObject.put("result", result.getResult());
        dataObject.put("errorText", result.getErrorText());
        dataObject.put("id", result.getId());

        return dataObject.toString();
    }
}
