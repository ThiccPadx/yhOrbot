package dev.div0.image;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.FileImageOutputStream;

import dev.div0.logging.ServerInfoLogger;
import dev.div0.robotOperations.recaptcha.data.RecaptchaData;

import org.apache.commons.io.FileUtils;

// TODO it is bad to save and do file path operations in here !!! Only save operation must be here
public class ImageSaver extends ServerInfoLogger{
	
	private BufferedImage dest;
	private String path;
	String tempPNGPath;
	
	public boolean saveFromBufferedImage(BufferedImage dest, String destinationFilePath, File screen, String format) throws Exception{
		
		this.path = destinationFilePath;
		this.dest = dest;
		
		if(format.equals("png")){
			ImageIO.write(dest, format, screen);
			tempPNGPath = RecaptchaData.getImagesFolder()+RecaptchaData.getImageName()+".png";
			
	        File file = new File(RecaptchaData.getImagePath());
	        FileUtils.copyFile(screen, file);
	        
	        try{
	        	convertToJpeg();
	        }
	        catch(Exception exception){
	        	throw new Exception(exception.getMessage());
	        }
		}
		else if(format.equals("jpeg")){
			JPEGImageWriteParam jpegParams = new JPEGImageWriteParam(null);
	    	jpegParams.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
	    	jpegParams.setCompressionQuality(1f);
	    	
	    	
	    	final ImageWriter writer = ImageIO.getImageWritersByFormatName(format).next();
	    	// specifies where the jpg image has to be written
	    	writer.setOutput(new FileImageOutputStream(new File(destinationFilePath)));

	    	// writes the file with given compression level 
	    	// from your JPEGImageWriteParam instance
	    	
	    	correctRGB();
	    	
	    	writer.write(null, new IIOImage(dest, null, null), jpegParams);
		}
		else{
			logError("Unknown image format to save");
			return false;
		}
		return true;
	}
	
	public boolean saveFromUrl(String imageUrl, String destinationFilePath) throws IOException{
		//log("save from Url "+imageUrl);
		//log("destinationFilePath "+destinationFilePath);
		URL url = new URL(imageUrl);
		InputStream is = url.openStream();
		OutputStream os = new FileOutputStream(destinationFilePath);

		byte[] b = new byte[2048];
		int length;

		while ((length = is.read(b)) != -1) {
			os.write(b, 0, length);
		}

		is.close();
		os.close();
		return true;
	}

	private void convertToJpeg() throws Exception {
		log("converting to JPEG");
		
		BufferedImage bufferedImage = ImageIO.read(new File(tempPNGPath));

		  // create a blank, RGB, same width and height, and a white background
		  BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
		  newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);

		  // write to jpeg file
		  ImageIO.write(newBufferedImage, "jpg", new File(RecaptchaData.getImagesFolder()+RecaptchaData.getImageName()+".jpg"));
	}

	private void correctRGB() {
		CorrectRGB.fixBadJPEG(dest);
	}
}
