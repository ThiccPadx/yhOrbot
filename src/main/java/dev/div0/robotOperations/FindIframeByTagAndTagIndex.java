package dev.div0.robotOperations;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class FindIframeByTagAndTagIndex extends BaseOperation {
	
	@Override
	public boolean execute() throws OperationException{
		
		//webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement iframeSwitch = webDriver.findElement(By.xpath("/html/body/div/div[4]/iframe"));
		webDriver.switchTo().frame(iframeSwitch);
		
		/*
		//webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//log("Selecting 1s iframe");
		//webDriver.switchTo().frame(0);	
		
		//log("find iframe by tag index "+operationData.getData());
		
		List<WebElement> iframes = webDriver.findElements(By.tagName("iframe"));
		log("total frames: "+iframes.size());
		
	    for (WebElement iframe : iframes) {
	        log(" - FRAME - src:"+iframe.getAttribute("src")+"  title: "+iframe.getAttribute("title"));
	    }

	    log("selecting first iframe..");
	    webDriver.switchTo().frame(0);
	    log("inside 1st iframe ");
	    
	    iframes = webDriver.findElements(By.tagName("iframe"));
		log("total frames: "+iframes.size());
		
	    for (WebElement iframe : iframes) {
	        log(" - FRAME - src:"+iframe.getAttribute("src")+"  title:"+iframe.getAttribute("title"));
	    }
	    
		
		//WebDriverWait wait = new WebDriverWait(webDriver, 10);
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt((By.name("kalfrpulpkz2"))));
		
		//log("Frame came visible");
		
		//WebElement iframeSwitch = webDriver.findElement(By.xpath("/html/body/div/div[4]/iframe"));
		//webDriver.switchTo().frame(iframeSwitch);
		*/
		return true;
	}
	
}
