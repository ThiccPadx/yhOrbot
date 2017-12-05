package dev.div0.application.page;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import dev.div0.logging.BaseLogger;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;


public class Anchors extends BaseLogger{
	
	private File file;
	private Document document;
	
	public void getAnchors(File file) throws ParserConfigurationException, SAXException, IOException{
		this.file = file;
		parse(file);

		log("anchors parse complete. document is "+document);

		if(document == null){
			log("document is null nothing to parse");
			log("Anchors complete");
		}
		else{
			Page.setImNotARobotCheckBoxContainerIFrameXPath(document.getElementsByTagName("imNotARobotCheckBoxContainerIFrameXPath").item(0).getTextContent());
			Page.setRecaptchaImNotARobotCheckBoxXPath(document.getElementsByTagName("recaptchaImNotARobotCheckBoxXPath").item(0).getTextContent());
			Page.setRecaptchaCheckBoxID(document.getElementsByTagName("recaptchaCheckBoxID").item(0).getTextContent());
			Page.setRecaptchaCheckBoxCorrectStyle(document.getElementsByTagName("recaptchaCheckBoxCorrectStyle").item(0).getTextContent());
			Page.setRecaptchaPayloadIFrameXPath(document.getElementsByTagName("recaptchaPayloadIFrameXPath").item(0).getTextContent());
			Page.setRecaptchaPayloadImagesTableXPath(document.getElementsByTagName("recaptchaPayloadImagesTableXPath").item(0).getTextContent());
			Page.setRecaptchaPayloadImagesXPath(document.getElementsByTagName("recaptchaPayloadImagesXPath").item(0).getTextContent());
			Page.setRecaptchaInstructionsXPath(document.getElementsByTagName("recaptchaInstructionsXPath").item(0).getTextContent());
			Page.setRecaptchaVerifyButtonXPath(document.getElementsByTagName("recaptchaVerifyButtonXPath").item(0).getTextContent());
			Page.setRecaptchaVerifyButtonID(document.getElementsByTagName("recaptchaVerifyButtonID").item(0).getTextContent());
			Page.setRecaptchaImagesTableRowsXPath(document.getElementsByTagName("recaptchaImagesTableRowsXPath").item(0).getTextContent());
			Page.setRecaptchaImagesTableColumnsXPath(document.getElementsByTagName("recaptchaImagesTableColumnsXPath").item(0).getTextContent());
			Page.setRecaptchaRectangularImageXPath(document.getElementsByTagName("recaptchaRectangularImageXPath").item(0).getTextContent());
			Page.setRecaptchaSquareImageXPath(document.getElementsByTagName("recaptchaSquareImageXPath").item(0).getTextContent());

			Page.setCalendarNextMonthButtonXPath(document.getElementsByTagName("calendarNextMonthButtonXPath").item(0).getTextContent());
			Page.setCalendarPrevMonthButtonXPath(document.getElementsByTagName("calendarPrevMonthButtonXPath").item(0).getTextContent());
			Page.setCalendarOpenDateAllocatedClass(document.getElementsByTagName("calendarOpenDateAllocatedClass").item(0).getTextContent());
			Page.setCalendarSelectedMonthNameXPath(document.getElementsByTagName("calendarSelectedMonthNameXPath").item(0).getTextContent());

			Page.setAvailableTimeClass(document.getElementsByTagName("availableTimeClass").item(0).getTextContent());
			Page.setPtnIsUsedErrorText(document.getElementsByTagName("ptnIsUsed").item(0).getTextContent());
			Page.setPtnNotFoundErrorText(document.getElementsByTagName("ptnNotFound").item(0).getTextContent());

			// google
			Page.setGoogleSearchButtonXPath(document.getElementsByTagName("googleSearchButtonXPath").item(0).getTextContent());
			Page.setGoogleTotalResultsContainerId(document.getElementsByTagName("googleTotalResultsContainerId").item(0).getTextContent());
			Page.setGoogleSearchInputId(document.getElementsByTagName("googleSearchInputId").item(0).getTextContent());
		}
	}
	
	private void parse(File file) throws ParserConfigurationException, SAXException, IOException{
		log("parcing anchors");
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = null;
		log("documentBuilderFactory = "+documentBuilderFactory);

		try 
		{
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			log("documentBuilder="+documentBuilder);
			
		} catch (ParserConfigurationException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
			logError(e1.getMessage());
		}
		
		try 
		{
			document = documentBuilder.parse(file);
			log("Document = "+document);
		} 
		catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logError(e.getMessage());
		}
	}
}
