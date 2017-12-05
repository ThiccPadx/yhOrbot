package dev.div0.steps;

public enum ElementSearchType {
	BY_HREF("byHref"), 
	BY_ID("byId"), 
	BY_CSS_CLASS("byCssClass"), 
	BY_XPATH("byXPath"), 
	BY_NAME("byName"), 
	BY_TAG("byTag"), 
	BY_TEXT("byText");
	
	private final String type;
	
	private ElementSearchType(String type){
		this.type = type;
	}
}
