package dev.div0.robotOperations.screen;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.FileImageOutputStream;

import dev.div0.Application;
import dev.div0.image.ImageSaver;
import dev.div0.robotOperations.BaseOperation;
import dev.div0.robotOperations.OperationException;
import dev.div0.robotOperations.recaptcha.data.RecaptchaData;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TakeScreenshot extends BaseOperation {
	
	private WebElement webElement;
	private String instructions = "";
	private String format = "png";
	
	@Override
	public boolean execute() throws OperationException{
		
		try{
			takeScreenshot();
		}
		catch(Exception exception){
			throw new OperationException(exception.getMessage());
		}
		
		return true;
	}

	private void getImageSrc() {
		
	}

	protected boolean takeScreenshot() throws Exception{
		// //*[@id="rc-imageselect"]/div[2]/div[2]
		log("Taking screenshot...");
		
		Point elementOffsetPoint = operationData.getElementOffsetPoint();
		log("Element offset "+elementOffsetPoint.toString());
		
		WebDriverWait wait = new WebDriverWait(webDriver, Application.baseOperationWaitSecondsUntil);
		webElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='rc-imageselect']/div[2]/div[2]")));
		
		log("imageselect Element class:"+webElement.getAttribute("class"));
		File screen = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		
		Point p = webElement.getLocation();
		
		log("point: "+p.toString());

        int width = webElement.getSize().getWidth();
        int height = webElement.getSize().getHeight();

        BufferedImage img = null;
        
        try{
        	img = ImageIO.read(screen);
        	
        	BufferedImage dest = img.getSubimage(elementOffsetPoint.getX(), elementOffsetPoint.getY(), width, height);
        	
        	boolean saveImageResult = new ImageSaver().saveFromBufferedImage(dest, RecaptchaData.getImagePath(), screen, format);
        	return saveImageResult;
        }
        catch(Exception ioException){
        	throw new OperationException(ioException.getMessage());
        }
	}


	private void bringBrowserToFront() {
		log("Bring browser to front");
		String currentWindowHandle = webDriver.getWindowHandle();
		
		//run your javascript and alert code
		((JavascriptExecutor)webDriver).executeScript("alert('Test')"); 
		webDriver.switchTo().alert().accept();

		//Switch back to to the window using the handle saved earlier
		webDriver.switchTo().window(currentWindowHandle);
	}
}
