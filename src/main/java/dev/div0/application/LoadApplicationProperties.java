package dev.div0.application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import dev.div0.logging.BaseLogger;

public class LoadApplicationProperties extends BaseLogger{
	String result = "";
	InputStream inputStream;
	
	public String getPropValues() throws IOException {
		 
		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
 
			Date time = new Date(System.currentTimeMillis());
 
			// get the property value and print it out
			String webDriverPath = prop.getProperty("webDriverPath");
			String appDataBaseDir = prop.getProperty("appDataDir");
 
			log("webDriverPath = " + webDriverPath + ", " + appDataBaseDir + ", " + appDataBaseDir);
		} catch (Exception e) {
			logError("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return result;
	}

}
