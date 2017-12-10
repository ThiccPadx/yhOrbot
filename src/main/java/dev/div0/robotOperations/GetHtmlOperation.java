package dev.div0.robotOperations;


public class GetHtmlOperation extends GetTextOperation{

    @Override
    protected String getElementText() {
        String html = webElement.getAttribute("innerHTML");
        return html;
        //return (String)((JavascriptExecutor)webElement).executeScript("return arguments[0].innerHTML;", webElement);
    }
}
