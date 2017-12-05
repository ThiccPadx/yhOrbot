package dev.div0.webDriver;

public enum BrowserType {
	
	CHROME("chrome"),
	OPERA("opera"),
	FIREFOX("firefox"),
	PHANTOM_JS("phantomJs"),
	HTML_UNIT("htmlUnit"),
	IE("ie");
	
	private final String type;
	
	BrowserType(String type){
		this.type = type;
	}
}
