package dev.div0.application.page;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import dev.div0.logging.BaseLogger;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Anchors extends BaseLogger{
	
	//private File file;
	private Document anchorsDocument;
	
	public void getAnchors(File file) throws ParserConfigurationException, SAXException, IOException{
		//this.file = file;
		parse(file);

		NodeList authNodeList = anchorsDocument.getElementsByTagName("auth");

		Node authNode = authNodeList.item(0);

		if (authNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) authNode;

			YahooPage.auth_loginInputId = eElement.getElementsByTagName("loginInputId").item(0).getTextContent();
			YahooPage.auth_loginBtnId = eElement.getElementsByTagName("loginBtnId").item(0).getTextContent();
			YahooPage.auth_loginErrorBoxXPath = eElement.getElementsByTagName("loginErrorBoxXPath").item(0).getTextContent();
			YahooPage.auth_passwordInputXPath = eElement.getElementsByTagName("passwordInputXPath").item(0).getTextContent();
			YahooPage.auth_submitBtnId = eElement.getElementsByTagName("submitBtnId").item(0).getTextContent();
			YahooPage.auth_loggedInputId = eElement.getElementsByTagName("loggedInputId").item(0).getTextContent();

			log(YahooPage.auth_loginInputId);
			log(YahooPage.auth_loginBtnId);
			log(YahooPage.auth_loginErrorBoxXPath);
			log(YahooPage.auth_passwordInputXPath);
			log(YahooPage.auth_submitBtnId);
			log(YahooPage.auth_loggedInputId);
		}
		else{
			log("auth node is not an element node");
		}


		NodeList beddingNodeList = anchorsDocument.getElementsByTagName("bidding");

		Node beddingNode = beddingNodeList.item(0);

		if (beddingNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) beddingNode;

			YahooPage.bidding_makeBidButtonXpath = eElement.getElementsByTagName("makeBidButtonXpath").item(0).getTextContent();
			YahooPage.bidding_bidMoneyValueCSS = eElement.getElementsByTagName("bidMoneyValueCss").item(0).getTextContent();
			YahooPage.bidding_blitzBidButtonXpath = eElement.getElementsByTagName("blitzBidButtonXpath").item(0).getTextContent();
			YahooPage.bidding_blitzBidMoneyValueXPath = eElement.getElementsByTagName("blitzBidMoneyValueXPath").item(0).getTextContent();

			YahooPage.bidding_bidModalSubmitButtonXPath = eElement.getElementsByTagName("bidModalSubmitButtonXPath").item(0).getTextContent();
			YahooPage.bidding_bidFinalSubmitButtonXPath = eElement.getElementsByTagName("bidFinalSubmitButtonXPath").item(0).getTextContent();

			YahooPage.bidding_bidAlreadySetImageXPath = eElement.getElementsByTagName("bidAlreadySetImageXPath").item(0).getTextContent();

			log("makeBidButtonXpath"+YahooPage.bidding_makeBidButtonXpath);
			log("bidMoneyValueCss"+YahooPage.bidding_bidMoneyValueCSS);
			log("blitzBidButtonXpath"+YahooPage.bidding_blitzBidButtonXpath);
			log("blitzBidMoneyValueXPath"+YahooPage.bidding_blitzBidMoneyValueXPath);
			log("bidModalSubmitButtonXPath"+YahooPage.bidding_bidModalSubmitButtonXPath);
			log("bidFinalSubmitButtonXPath"+YahooPage.bidding_bidFinalSubmitButtonXPath);
			log("bidAlreadySetImageXPath"+YahooPage.bidding_bidAlreadySetImageXPath);
		}
		else{
			log("bidding node is not an element node");
		}
	}
	
	private void parse(File file) throws ParserConfigurationException, SAXException, IOException{
		log("parsing anchors");

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = null;
		documentBuilder = documentBuilderFactory.newDocumentBuilder();

		anchorsDocument = documentBuilder.parse(file);

		//optional, but recommended
		//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		anchorsDocument.getDocumentElement().normalize();
	}
}
