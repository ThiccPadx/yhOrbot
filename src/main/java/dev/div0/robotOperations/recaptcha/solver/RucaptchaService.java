package dev.div0.robotOperations.recaptcha.solver;

import java.io.File;

import dev.div0.logging.BaseLogger;
import dev.div0.logging.ServerInfoLogger;
import dev.div0.robotOperations.BaseOperation;
import dev.div0.robotOperations.recaptcha.data.RecaptchaData;
import net.marketer.RuCaptcha;

public class RucaptchaService extends BaseOperation{
	
	private String CAPCHA_ID;
    private String decryption;
    private String response = "";
    private File imgFile;
    private String instructions;
    private String[] resultClicks;
	
    private boolean isContinue = true;
    
	public String[] getClicks(String imagePath, String imageInstructions) throws RecaptchaSolverException{
		createImageFile(imagePath);
		log("captcha table: columns: "+RecaptchaData.getImagesTableColumnsTotal()+"  rows: "+RecaptchaData.getImagesTableRowsTotal());
		
		if(imgFile == null){
			throw new RecaptchaSolverException("Error creating image file by path. imagePath = "+imagePath);
		}
		
		try{
        	response = RuCaptcha.postReCaptcha2(imgFile, imageInstructions, 0, RecaptchaData.getImagesTableColumnsTotal(), RecaptchaData.getImagesTableRowsTotal());
        	
        	log("responce "+response);
        	
        	if (response.startsWith("OK")) {
                CAPCHA_ID = response.substring(3);
                log("CAPCHA_ID="+CAPCHA_ID);
                
                while (isContinue)
                {
                    response = RuCaptcha.getDecryption(CAPCHA_ID);
                    
                    if(response.equals(RuCaptcha.Responses.CAPCHA_NOT_READY.toString())){
                    	log("Not ready yet");
                        Thread.sleep(5000);
                        continue;
                    }
                    else if(response.startsWith("OK"))
                    {
                        decryption = response.substring(3);
                        log("decryption: "+decryption);
                        parseClicks(decryption);
                        
                        return resultClicks;
                    }
                    else 
                    {
                    	//log("error");
                    	throw new RecaptchaSolverException("Error solving recaptcha. Rucaptcha responce incorrect. Responce: "+response);
                    }
                }
                
                //log("Complete");
                //��� ��� ������������� ���������� ����������� �����           
            }
        }
        catch(Exception exception){
        	//logError("Error. "+exception.getMessage());
        	throw new RecaptchaSolverException("Error solving recaptcha. "+exception.getMessage());
        }
		return null;
	}
	
	@Override
	public void destroy(){
		super.destroy();
		log("Rucaptcha service destroy");
		//isContinue = false;
	}
	
	private void createImageFile(String imagePath) {
		imgFile = new File(imagePath);
	}

	private void parseClicks(String decryption) {
		String clicksString = decryption.substring(6);
		log("my clicksString: "+clicksString);
		resultClicks = clicksString.split("/");
		log("total clicks: "+resultClicks.length);
	}
}
