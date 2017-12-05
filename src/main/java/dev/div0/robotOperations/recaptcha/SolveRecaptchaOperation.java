package dev.div0.robotOperations.recaptcha;

import java.util.List;

import dev.div0.Application;
import dev.div0.application.page.Page;
import dev.div0.robotOperations.BaseOperation;
import dev.div0.robotOperations.ClickElementOperation;
import dev.div0.robotOperations.OperationException;
import dev.div0.robotOperations.SwitchToIFrameOperation;
import dev.div0.robotOperations.SwitchToMainFrameOperation;
import dev.div0.robotOperations.operationData.OperationData;
import dev.div0.robotOperations.recaptcha.data.RecaptchaData;
import dev.div0.robotOperations.recaptcha.solver.RecaptchaSolver;
import dev.div0.robotOperations.recaptcha.solver.RecaptchaSolverException;
import dev.div0.robotOperations.recaptcha.solver.RucaptchaService;
import dev.div0.steps.ElementSearchType;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SolveRecaptchaOperation extends BaseOperation {
	
	private static final Object waitMonitor = new Object();
	
	protected ClickRecaptchaImagesOperation clickRecaptchaImages = new ClickRecaptchaImagesOperation();
	
	private RucaptchaService rucaptchaService = new RucaptchaService();
	private String[] clicksStrings;
	private int[] clicks;
	
	private WebDriverWait wait;
	private RecaptchaSolver solver = new RecaptchaSolver();
	protected boolean solved = false;
	private WebElement imNotARobotCheckBoxElement;
	private WebElement verifyButtonElement;
	
	private String imNotARobotContainerHandle;
	private String payloadContainerHandle;
	private String mainIFrameHandle;
	
	protected boolean isCaptchaMulticlickable = false;
	private String captchaMultyClickableText = "captchaMultiClickable���� ��������� ��� �� ����������";
	private boolean isRectangularImage = false;
	
	@Override
	public boolean execute() throws OperationException{
		
		log("       --- SolveRecaptcha start ");
		clickImNotARobot();

		solved = checkIsRecaptchaComplete();

		log("Recaptcha solved = "+solved);
		
		if(solved == false){
			log("Recaptcha not solved yet");
			getPayloadProperties();
			log("Captcha multiclickable = "+isCaptchaMulticlickable);
			
			if(isCaptchaMulticlickable == true){
				resolve();
			}
			else{
				log("normal recaptcha - going on ...");
				getImage();
				getClicks();
				
				if(clicksStrings.length>0)
				{
					if(stopped == false){
						parseClicks(clicksStrings);
						RecaptchaData.setClicks(clicks);
						
						try{
							doImagesClick();
							clickVerifyButton();
						}
						catch(RecaptchaSolverException exception){
							logError(exception.getMessage());
							throw new OperationException(exception.getMessage());
						}
						checkClicksResult();
						log("solved = "+solved);
						
						if(solved==true){
							log("Recaptcha solved !!!!");
							switchToMainIFrame();
							
							return solved;
						}
						else{
							log("Recaptcha not solved");
							resolve();
						}
					}
				}
				else{
					// � ��� ���� �� ����� ������ �������� ?
					log("No clicks returned ! Nothing to do.");
				}
			}
		}
		else{
			log("Recaptcha solved !!!!");
			switchToMainIFrame();
			return true;
		}
		return false;
	}
	
	@Override
	public void destroy(){
		super.destroy();
		log("Sovle recaptcha operation destroy");
		rucaptchaService.destroy();
	}
	
	protected void checkClicksResult() throws OperationException{
		log("checkClicksResult");
		switchToMainIFrame();
		switchToImNotARobotIframe();
		solved = checkIsRecaptchaComplete();
	}

	private void getClicks() {
		try{
			clicksStrings = rucaptchaService.getClicks(RecaptchaData.getImagePath(), RecaptchaData.getInstructions());
		}
		catch(RecaptchaSolverException exception){
			logError("get Clicks error. "+exception.getMessage());
		}
	}

	protected void getImage() {

		SaveRecaptchaImageOperation saveImage = new SaveRecaptchaImageOperation();
		saveImage.setWebDriver(webDriver);
		
		OperationData operationData = new OperationData();
		
		if(isRectangularImage){
			operationData.setElementSearchData(Page.getRecaptchaRectangularImageXPath());
		}
		else{
			operationData.setElementSearchData(Page.getRecaptchaSquareImageXPath());
		}
		
		operationData.setElementSearchType(ElementSearchType.BY_XPATH);
		saveImage.setOperationData(operationData);
		
		try{
			saveImage.execute();
		}
		catch(OperationException exception){
			logError(exception.getMessage());
		}
	}

	protected void resolve() throws OperationException{
		waitToResolve();
		switchToMainIFrame();
		execute();
	}

	protected void getPayloadProperties() throws OperationException{
		getInstructions();
		getImagesTableProperties();
		getIsCaptchaMultiClickable();
	}

	private void getImagesTableProperties() {
		List<WebElement> imagesTableRows = webDriver.findElements(By.xpath(Page.getRecaptchaImagesTableRowsXPath()));
		List<WebElement> imagesTableColumns = webDriver.findElements(By.xpath(Page.getRecaptchaImagesTableColumnsXPath()));
		
		int rows = imagesTableRows.size();
		int columns = imagesTableColumns.size();

		columns = columns/rows;
		
		log("table rows: "+rows);
		log("table columns: "+columns);
		RecaptchaData.setImagesTableRowsTotal(rows);
		RecaptchaData.setImagesTableColumnsTotal(columns);
		
		isRectangularImage = (rows == columns);
		log("isRectangularImage="+isRectangularImage);
	}

	private void getIsCaptchaMultiClickable() {
		isCaptchaMulticlickable = RecaptchaData.getInstructions().toLowerCase().contains(captchaMultyClickableText.toLowerCase());
	}

	private void getInstructions() throws OperationException{
		switchToPayload();
		getRecaptchaInstructions();
	}

	private void switchToPayload() throws OperationException{
		switchToMainIFrame();
		switchToPayloadIframe();
	}

	private void getRecaptchaInstructions() {
		log("getRecaptchaInstructions()");
		GetRecaptchaInstructionsOperation getInstructionsOperation = new GetRecaptchaInstructionsOperation();
		getInstructionsOperation.setWebDriver(webDriver);
		
		OperationData operationData = new OperationData();
		operationData.setElementSearchData(Page.getRecaptchaInstructionsXPath());
		operationData.setElementSearchType(ElementSearchType.BY_XPATH);
		getInstructionsOperation.setOperationData(operationData);
		
		try{
			getInstructionsOperation.execute();
		}
		catch(OperationException exception){
			logError(exception.getMessage());
		}
	}

	private void switchToPayloadIframe() throws OperationException{
		log("switchToPayloadIframe");
		SwitchToIFrameOperation switchPayloadIframeOperation = new SwitchToIFrameOperation();
		switchPayloadIframeOperation.setWebDriver(webDriver);
		
		OperationData operationData = new OperationData();
		operationData.setElementSearchData(Page.getRecaptchaPayloadIFrameXPath());
		operationData.setElementSearchType(ElementSearchType.BY_XPATH);
		switchPayloadIframeOperation.setOperationData(operationData);
		
		try{
			switchPayloadIframeOperation.execute();
			payloadContainerHandle = webDriver.getWindowHandle();
		}
		catch(OperationException exception){
			logError(exception.getMessage());
			throw new OperationException(exception.getMessage());
		}
	}

	private void switchToMainIFrame() {
		SwitchToMainFrameOperation switchToMainFrameOperation = new SwitchToMainFrameOperation();
		switchToMainFrameOperation.setWebDriver(webDriver);
		try{
			switchToMainFrameOperation.execute();
			mainIFrameHandle = webDriver.getWindowHandle();
		}
		catch(OperationException exception){
			logError("Switch to main frame error. "+exception.getMessage());
		}
	}

	protected boolean checkIsRecaptchaComplete() throws OperationException{

		DetectRecaptchaCompleteOperation detectRecaptchaCompleteOperation = new DetectRecaptchaCompleteOperation();
		detectRecaptchaCompleteOperation.setWebDriver(webDriver);
		detectRecaptchaCompleteOperation.setImNotARobotCheckBoxElement(imNotARobotCheckBoxElement);
		
		return detectRecaptchaCompleteOperation.execute();
	}

	protected void clickImNotARobot() throws OperationException{
		switchToImNotARobotIframe();
		clickImNotARobotCheckBox();
	}

	private void clickImNotARobotCheckBox() {
		log("clickImNotARobotCheckBox");
		ClickElementOperation clickElementOperation = new ClickElementOperation();
		clickElementOperation.setWebDriver(webDriver);
		
		OperationData operationData = new OperationData();
		operationData.setElementSearchData(Page.getRecaptchaImNotARobotCheckBoxXPath());
		operationData.setElementSearchType(ElementSearchType.BY_XPATH);
		clickElementOperation.setOperationData(operationData);
		
		try{
			clickElementOperation.execute();
		}
		catch(OperationException exception){
			logError(exception.getMessage());
		}
	}
	
	private void clickVerifyButton() {
		log("clickVerifyButton");
		verifyButtonElement = webDriver.findElement(By.id(Page.getRecaptchaVerifyButtonID()));
		
		log("verifyButtonElement="+verifyButtonElement);
		Actions builderVerify = new Actions(webDriver);
		builderVerify.moveToElement(verifyButtonElement).perform();
		verifyButtonElement.click();
	}

	protected void switchToImNotARobotIframe() throws OperationException{
		log("switchToImNotARobotIframe");
		SwitchToIFrameOperation switchToImNotARobotIframeOperation = new SwitchToIFrameOperation();
		switchToImNotARobotIframeOperation.setWebDriver(webDriver);
		
		OperationData operationData = new OperationData();
		operationData.setElementSearchData(Page.getImNotARobotCheckBoxContainerIFrameXPath());
		log("ImNotARobotCheckBoxContainerIFrameXPath = "+Page.getImNotARobotCheckBoxContainerIFrameXPath());
		operationData.setElementSearchType(ElementSearchType.BY_XPATH);
		switchToImNotARobotIframeOperation.setOperationData(operationData);
		
		switchToImNotARobotIframeOperation.execute();
		imNotARobotContainerHandle = webDriver.getWindowHandle();
	}
	
	
	private void parseClicks(String[] clicksStrings) {
		
		int i;
		int totalClicks = clicksStrings.length;
		
		clicks = new int[totalClicks];
		
		for(i=0; i < totalClicks; i++){
			String clickString = clicksStrings[i];
			int intClick = Integer.parseInt(clickString);
			clicks[i] = intClick;
		}
	}
	
	protected void doImagesClick() throws RecaptchaSolverException{
		clickRecaptchaImages.setWebDriver(webDriver);
		try{
			clickRecaptchaImages.execute();
		}
		catch(OperationException exception){
			throw new RecaptchaSolverException(exception.getMessage());
		}
	}
	
	private void waitToResolve() {
		log("waiting 120 seconds...");
		try {
			Thread.sleep(120000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void sleepThreed() {
		try {
			Thread.sleep(Application.baseOperationWaitSecondsUntil);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
