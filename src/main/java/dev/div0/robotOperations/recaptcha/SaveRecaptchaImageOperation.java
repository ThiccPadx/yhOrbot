package dev.div0.robotOperations.recaptcha;

import java.io.IOException;

import dev.div0.image.ImageSaver;
import dev.div0.robotOperations.FindElementAndDoOperation;
import dev.div0.robotOperations.OperationException;
import dev.div0.robotOperations.recaptcha.data.RecaptchaData;


public class SaveRecaptchaImageOperation extends FindElementAndDoOperation {
	
	private ImageSaver imageSaver = new ImageSaver();
	
	@Override
	protected boolean doOperation() throws OperationException{
		
		String imageSourceAttribute = webElement.getAttribute("src");
		//log("imageSourceAttribute="+imageSourceAttribute);
		
		try 
		{
			log("saving image into "+RecaptchaData.getImagePath());
			imageSaver.saveFromUrl(imageSourceAttribute, RecaptchaData.getImagePath());
		} 
		catch (IOException e) {
			throw new OperationException(e.getMessage());
		}
		
		return true;
	}
}
