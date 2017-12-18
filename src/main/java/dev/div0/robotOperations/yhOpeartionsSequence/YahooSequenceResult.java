package dev.div0.robotOperations.yhOpeartionsSequence;

public class YahooSequenceResult {
    private String result;
    private String errorText;
    private String bidData;
    private String id;

    public YahooSequenceResult() {

    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBidData() {
        return bidData;
    }

    public void setBidData(String bidData) {
        this.bidData = bidData;
    }
}
