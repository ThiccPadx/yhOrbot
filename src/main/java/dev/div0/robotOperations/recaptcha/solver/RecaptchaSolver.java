package dev.div0.robotOperations.recaptcha.solver;

import dev.div0.logging.ServerInfoLogger;
import dev.div0.robotOperations.OperationException;
import dev.div0.robotOperations.recaptcha.ClickRecaptchaImagesOperation;
import dev.div0.robotOperations.recaptcha.data.RecaptchaData;

import org.openqa.selenium.WebDriver;


public class RecaptchaSolver extends ServerInfoLogger{
	
	private RucaptchaService rucaptchaService = new RucaptchaService();
	private int[] clicks;
	private ClickRecaptchaImagesOperation clickRecaptchaImages = new ClickRecaptchaImagesOperation();

	public boolean execute(WebDriver webDriver, String imagePath, String imageInstructions) throws RecaptchaSolverException{
		
		log("RecaptchaSolver execute imagePath="+imagePath);
		
		clickRecaptchaImages.setWebDriver(webDriver);
		
		String[] clicksStrings = rucaptchaService.getClicks(imagePath, imageInstructions);
		
		if(clicksStrings.length>0)
		{
			parseClicks(clicksStrings);
			saveClicks();
			log("total clicks: "+RecaptchaData.getClicks().length);
			doImagesClick();
		}
		else{
			// � ��� ���� �� ����� ������ �������� ?
			throw new RecaptchaSolverException("No clicks returned ! Nothing to do.");
		}
		return true;
	}
	
	private void doImagesClick() throws RecaptchaSolverException{
		try{
			clickRecaptchaImages.execute();
		}
		catch(OperationException exception){
			throw new RecaptchaSolverException(exception.getMessage());
		}
	}

	private void saveClicks() {
		RecaptchaData.setClicks(clicks);
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
}
