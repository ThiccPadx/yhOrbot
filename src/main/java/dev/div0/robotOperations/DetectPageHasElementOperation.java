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

    public boolean isElementVisible(){

        if(webElement == null){
            return false;
        }

        try {
            log("waiting 2 seconds..");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //webDriver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);

        boolean isVisible = webElement.isDisplayed();

        return isVisible;
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
