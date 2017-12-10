package dev.div0.robotOperations;

public class DetectPageHasElementOperation extends DetectPageHasTextOperation {
    public DetectPageHasElementOperation() {
        super();
    }

    public boolean hasCssClass(String cssClass){
        String classes = webElement.getAttribute("class");
        for (String c : classes.split(" ")) {
            if (c.equals(cssClass)) {
                return true;
            }
        }

        return false;
    }

    @Override
    protected boolean hasElement(){
        if(webElement==null){
            return false;
        }
        else{
            return true;
        }
    }
}
