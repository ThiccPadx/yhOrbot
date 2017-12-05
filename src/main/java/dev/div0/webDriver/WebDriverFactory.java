package dev.div0.webDriver;

public class WebDriverFactory {
	
	public IWebDriverProvider getDriver(BrowserType type){
		
		if(type.equals(BrowserType.FIREFOX)){
			return new FirefoxWebDriver();
		}
		else if(type.equals(BrowserType.PHANTOM_JS)){
			return new PhantomJsWebDriver();
		}
		else if(type.equals(BrowserType.CHROME)){
			return new ChromeWebDriver();
		}
		else if(type.equals(BrowserType.OPERA)){
			return new OperaWebDriver();
		}
		else if(type.equals(BrowserType.IE)){
			return new IEWebDriver();
		}
		else if(type.equals(BrowserType.HTML_UNIT)){
			return new HTMLUnitWebDriver();
		}
		return null;
	}
}
