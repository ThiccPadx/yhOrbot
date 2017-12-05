package dev.div0.application;

public class ApplicationProperties {
	private static ApplicationProperties instance;
	
	private static String webDriverPath = "C:/consultant/browserDrivers/chrome/chromedriver.exe";
	
	
	public static synchronized ApplicationProperties getInstance() {
		if (instance == null) {
			instance = new ApplicationProperties();
		}
		return instance;
	}


	public static String getWebDriverPath() {
		return webDriverPath;
	}


	public static void setWebDriverPath(String _webDriverPath) {
		webDriverPath = _webDriverPath;
	}
}
