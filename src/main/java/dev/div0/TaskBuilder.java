package dev.div0;

import dev.div0.steps.ElementSearchType;
import dev.div0.steps.StepAction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class TaskBuilder {
	
	public String build(){
		JSONArray array = new JSONArray();
		JSONObject step1 = new JSONObject();
		JSONObject step2 = new JSONObject();
		JSONObject step2_1 = new JSONObject();
		JSONObject step3 = new JSONObject();
		JSONObject step4 = new JSONObject();
		JSONObject step5 = new JSONObject();
		JSONObject step6 = new JSONObject();
		JSONObject step7 = new JSONObject();
		JSONObject step7_1 = new JSONObject();
		JSONObject step8 = new JSONObject();
		JSONObject step9 = new JSONObject();
		JSONObject step9_1 = new JSONObject();
		JSONObject step10 = new JSONObject();
		JSONObject step10_1 = new JSONObject();
		JSONObject step11 = new JSONObject();
		JSONObject step12 = new JSONObject();
		JSONObject step13 = new JSONObject();
		JSONObject step14 = new JSONObject();
		JSONObject step15 = new JSONObject();
		JSONObject step16 = new JSONObject();
		JSONObject step17 = new JSONObject();
		JSONObject step18 = new JSONObject();
		JSONObject step19 = new JSONObject();
		JSONObject step20 = new JSONObject();
		JSONObject step21 = new JSONObject();
		JSONObject step22 = new JSONObject();
		JSONObject step23 = new JSONObject();
		JSONObject step24 = new JSONObject();
		JSONObject step25 = new JSONObject();
		JSONObject step26 = new JSONObject();
		JSONObject step27 = new JSONObject();
		JSONObject step28 = new JSONObject();
		
		//step1.put("id", 0);
		step1.put("id", 0);
		step1.put("action", StepAction.OPEN_URL.toString());
		step1.put("description", "open site");
		step1.put("elementSearchData", "http://www.polandvisa-ukraine.com/scheduleappointment.html");
		//step1.put("data", "wwwointment.html"); // throws error

		step2.put("id", 1);
		step2.put("action", StepAction.CLICK_LINK.toString());
		step2.put("elementSearchType", ElementSearchType.BY_HREF.toString());
		step2.put("description", "link click");
		step2.put("overrideHrefTarget", true);
		step2.put("elementSearchData", "scheduleappointment_2.html");
		//step2.put("elementSearchData", "abcd.html"); // error element
		
		// switch to main iframe
		step3.put("id", 2);
		step3.put("action", StepAction.SWITCH_TO_IFRAME.toString());
		step3.put("elementSearchType", ElementSearchType.BY_XPATH.toString());
		step3.put("elementType", "mainIFrame");
		step3.put("isMainFrame", "1");
		step3.put("description", "Switch to main iframe. find iframe by xPath /html/body/div/div[4]/table/tbody/tr[1]/td[3]/table/tbody/tr[1]/td/div[2]/iframe");
		step3.put("elementSearchData", "/html/body/div/div[4]/table/tbody/tr[1]/td[3]/table/tbody/tr[1]/td/div[2]/iframe");
		
		step4.put("id", 3);
		step4.put("action", StepAction.CLICK_LINK.toString());
		step4.put("elementSearchType", ElementSearchType.BY_ID.toString());
		step4.put("description", "link click by idctl00_plhMain_lnkSchApp");
		step4.put("elementSearchData", "ctl00_plhMain_lnkSchApp");
		
		// select city
		step5.put("id", 4);
		step5.put("action", StepAction.SELECT_DROP_DOWN_ITEM.toString());
		//step5.put("selectionIndex", 9); // by index
		step5.put("selectionVisibleText", "������ �����");
		step5.put("elementSearchType", ElementSearchType.BY_ID.toString());
		step5.put("description", "selectDropDownItem from drop dowwn with id ctl00_plhMain_cboVAC");
		step5.put("elementSearchData", "ctl00_plhMain_cboVAC");
		
		step6.put("id", 5);
		step6.put("action", StepAction.SELECT_DROP_DOWN_ITEM.toString());
		//step6.put("selectionIndex", 1);
		step6.put("selectionVisibleText", "������ ���������");
		step6.put("elementSearchType", ElementSearchType.BY_ID.toString());
		step6.put("description", "selectDropDownItem from drop dowwn with id ctl00_plhMain_cboPurpose");
		step6.put("elementSearchData", "ctl00_plhMain_cboPurpose");
		
		step7.put("id", 6);
		step7.put("action", StepAction.CLICK_ELEMENT.toString());
		step7.put("elementSearchType", ElementSearchType.BY_ID.toString());
		step7.put("description", "click element by id ctl00_plhMain_btnSubmit");
		step7.put("elementSearchData", "ctl00_plhMain_btnSubmit");
		
		step8.put("id", 7);
		step8.put("action", StepAction.SELECT_DROP_DOWN_ITEM.toString());
		//step8.put("selectionIndex", 1);
		step8.put("selectionVisibleText", "����������� ³��");
		step8.put("elementSearchType", ElementSearchType.BY_ID.toString());
		step8.put("description", "selectDropDownItem from drop dowwn with id ctl00_plhMain_cboVisaCategory");
		step8.put("elementSearchData", "ctl00_plhMain_cboVisaCategory");
		
		step9.put("id", 8);
		step9.put("action", StepAction.SOLVE_RECAPTCHA.toString());
		step9.put("elementSearchType", ElementSearchType.BY_XPATH.toString()); // no information in this case
		step9.put("description", "solve recaptcha."); // no information in this case
		step9.put("elementSearchData", "1");
		
		step10.put("id", 9);
		step10.put("action", StepAction.CLICK_ELEMENT.toString());
		step10.put("elementSearchType", ElementSearchType.BY_ID.toString());
		step10.put("description", "click element by id ctl00_plhMain_btnSubmit");
		step10.put("elementSearchData", "ctl00_plhMain_btnSubmit");
		
		// No date(s) available for appointment ???
		step11.put("id", 10);
		step11.put("action", StepAction.DETECT_DATES_AVAILABLE_FOR_SELECTED_CITY.toString());
		step11.put("elementSearchType", ElementSearchType.BY_XPATH.toString());
		step11.put("description", "detecting dates available fot selected city by xpath //*[contains(text(), 'No date(s) available for appointment')]");
		step11.put("elementSearchData", "//*[contains(text(), 'No date(s) available for appointment')]");
		
		// ���� ���
		step12.put("id", 11);
		step12.put("action", StepAction.SET_TEXT.toString());
		step12.put("elementSearchType", ElementSearchType.BY_ID.toString());
		step12.put("description", "inserting text into element by id ctl00_plhMain_repAppReceiptDetails_ctl01_txtReceiptNumber");
		step12.put("elementSearchData", "ctl00_plhMain_repAppReceiptDetails_ctl01_txtReceiptNumber");
		step12.put("payload", "6827/0167/4291"); // used to send information to enter into input for example
		//step12.put("payload", "6827/0167/4null"); // incorrect information
		
		step13.put("id", 12);
		step13.put("action", StepAction.CLICK_ELEMENT.toString());
		step13.put("elementSearchType", ElementSearchType.BY_ID.toString());
		step13.put("description", "click element by id ctl00_plhMain_btnSubmit");
		step13.put("elementSearchData", "ctl00_plhMain_btnSubmit");
		
		step14.put("id", 13);
		step14.put("action", StepAction.SET_TEXT.toString());
		step14.put("elementSearchType", ElementSearchType.BY_ID.toString());
		step14.put("description", "inserting text into element by id ctl00_plhMain_txtEmailID");
		step14.put("elementSearchData", "ctl00_plhMain_txtEmailID");
		step14.put("payload", "yakovenko.vikusia@gmail.com"); // used to send information to enter into input for example
		
		step15.put("id", 14);
		step15.put("action", StepAction.SET_TEXT.toString());
		step15.put("elementSearchType", ElementSearchType.BY_ID.toString());
		step15.put("description", "inserting text into element by id ctl00_plhMain_txtPassword");
		step15.put("elementSearchData", "ctl00_plhMain_txtPassword");
		step15.put("payload", "123456789"); // used to send information to enter into input for example
		
		step16.put("id", 15);
		step16.put("action", StepAction.CLICK_ELEMENT.toString());
		step16.put("elementSearchType", ElementSearchType.BY_ID.toString());
		step16.put("description", "click element by id ctl00_plhMain_btnSubmitDetails");
		step16.put("elementSearchData", "ctl00_plhMain_btnSubmitDetails");
		
		step17.put("id", 16);
		step17.put("action", StepAction.SET_TEXT.toString());
		step17.put("elementSearchType", ElementSearchType.BY_ID.toString());
		step17.put("description", "inserting text into element by id ctl00_plhMain_repAppVisaDetails_ctl01_tbxPPTEXPDT");
		step17.put("elementSearchData", "ctl00_plhMain_repAppVisaDetails_ctl01_tbxPPTEXPDT");
		step17.put("payload", "26-08-2025"); // used to send information to enter into input for example
		
		step18.put("id", 17);
		step18.put("action", StepAction.SET_TEXT.toString());
		step18.put("elementSearchType", ElementSearchType.BY_ID.toString());
		step18.put("description", "inserting text into element by id ctl00_plhMain_repAppVisaDetails_ctl01_tbxFName");
		step18.put("elementSearchData", "ctl00_plhMain_repAppVisaDetails_ctl01_tbxFName");
		step18.put("payload", "VIKTORIIA"); // used to send information to enter into input for example
		
		step19.put("id", 18);
		step19.put("action", StepAction.SET_TEXT.toString());
		step19.put("elementSearchType", ElementSearchType.BY_ID.toString());
		step19.put("description", "inserting text into element by id ctl00_plhMain_repAppVisaDetails_ctl01_tbxLName");
		step19.put("elementSearchData", "ctl00_plhMain_repAppVisaDetails_ctl01_tbxLName");
		step19.put("payload", "YAKOVENKO"); // used to send information to enter into input for example
		
		step20.put("id", 19);
		step20.put("action", StepAction.SET_TEXT.toString());
		step20.put("elementSearchType", ElementSearchType.BY_ID.toString());
		step20.put("description", "inserting text into element by id ctl00_plhMain_repAppVisaDetails_ctl01_tbxDOB");
		step20.put("elementSearchData", "ctl00_plhMain_repAppVisaDetails_ctl01_tbxDOB");
		step20.put("payload", "02-10-1989"); // used to send information to enter into input for example
		
		step21.put("id", 20);
		step21.put("action", StepAction.SET_TEXT.toString());
		step21.put("elementSearchType", ElementSearchType.BY_ID.toString());
		step21.put("description", "inserting text into element by id ctl00_plhMain_repAppVisaDetails_ctl01_tbxReturn");
		step21.put("elementSearchData", "ctl00_plhMain_repAppVisaDetails_ctl01_tbxReturn");
		step21.put("payload", "30-11-2016"); // used to send information to enter into input for example
		
		// select person status
		step22.put("id", 21);
		step22.put("action", StepAction.SELECT_DROP_DOWN_ITEM.toString());
		step22.put("selectionVisibleText", "Mrs.");
		step22.put("elementSearchType", ElementSearchType.BY_ID.toString());
		step22.put("description", "selecting person status from drop down with id ctl00_plhMain_repAppVisaDetails_ctl01_cboTitle");
		step22.put("elementSearchData", "ctl00_plhMain_repAppVisaDetails_ctl01_cboTitle");
		
		// select person nationality
		step23.put("id", 22);
		step23.put("action", StepAction.SELECT_DROP_DOWN_ITEM.toString());
		step23.put("selectionVisibleText", "UKRAINE");
		step23.put("elementSearchType", ElementSearchType.BY_ID.toString());
		step23.put("description", "selecting person nationality from drop down with id ctl00_plhMain_repAppVisaDetails_ctl01_cboNationality");
		step23.put("elementSearchData", "ctl00_plhMain_repAppVisaDetails_ctl01_cboNationality");
		
		step24.put("id", 23);
		step24.put("action", StepAction.SOLVE_RECAPTCHA.toString());
		step24.put("elementSearchType", ElementSearchType.BY_XPATH.toString()); // no information in this case
		step24.put("description", "solve recaptcha."); // no information in this case
		step24.put("elementSearchData", "1");
		
		step25.put("id", 24);
		step25.put("action", StepAction.CLICK_ELEMENT.toString());
		step25.put("elementSearchType", ElementSearchType.BY_ID.toString());
		step25.put("description", "click element by id ctl00_plhMain_btnSubmit");
		step25.put("elementSearchData", "ctl00_plhMain_btnSubmit");
		
		step26.put("id", 25);
		step26.put("action", StepAction.SELECT_CALENDAR_AVAILABLE_DATE.toString());
		step26.put("elementSearchType", ElementSearchType.BY_ID.toString());
		step26.put("description", "selecting calendar available date");
		step26.put("elementSearchData", "ctl00_plhMain_cldAppointment");
		
		step27.put("id", 26);
		step27.put("action", StepAction.SOLVE_RECAPTCHA.toString());
		step27.put("elementSearchType", ElementSearchType.BY_XPATH.toString()); // no information in this case
		step27.put("description", "solve recaptcha."); // no information in this case
		step27.put("elementSearchData", "1");
		
		step28.put("id", 27);
		step28.put("action", StepAction.SELECT_AVAILABLE_TIME.toString());
		step28.put("elementSearchType", ElementSearchType.BY_ID.toString());
		step28.put("description", "selecting available time");
		step28.put("elementSearchData", "ctl00_plhMain_gvSlot_ctl01_lnktime");
		
		
		
		array.add(step1);
		array.add(step2);
		array.add(step3);
		array.add(step4);
		array.add(step5);
		array.add(step6);
		array.add(step7);
		array.add(step8);
		array.add(step9);
		array.add(step10);
		array.add(step11);
		array.add(step12);
		array.add(step13);
		array.add(step14);
		array.add(step15);
		array.add(step16);
		array.add(step17);
		array.add(step18);
		array.add(step19);
		array.add(step20);
		array.add(step21);
		array.add(step22);
		
		array.add(step23);
		array.add(step24);
		array.add(step25);
		array.add(step26);
		array.add(step27);
		array.add(step28);
		
		return array.toString();
	}
}
