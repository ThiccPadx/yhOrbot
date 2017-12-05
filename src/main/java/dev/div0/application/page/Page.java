package dev.div0.application.page;

public class Page {
	private static String mainIFrameHandle;
	private static String recaptchaPayloadIFrameHandle;
	private static String recaptchaImNotARobotCheckBoxContainerFrameHandle;
	private static String mainIframeXPath;
	
	//protected static String imNotARobotCheckBoxContainerIFrameXPath = "//*[@id='ctl00_plhMain_grecaptcha']/div/div/iframe";
	private static String imNotARobotCheckBoxContainerIFrameXPath = "/html/body/div/form/div/div/div/iframe";
	
	//protected static String recaptchaImNotARobotCheckBoxXPath = "//*[@id='recaptcha-anchor']/div[5]";
	private static String recaptchaImNotARobotCheckBoxXPath = "//*[@id='recaptcha-anchor']";
	
	private static String recaptchaCheckBoxID = "recaptcha-anchor";
	private static String recaptchaCheckBoxCorrectStyle = "recaptcha-checkbox-checked";
	
	//protected static String recaptchaPayloadIFrameXPath = "/html/body/div/div[4]/iframe";
	protected static String recaptchaPayloadIFrameXPath = "/html/body/div[2]/div[4]/iframe";
	
	protected static String recaptchaPayloadImagesTableXPath = "//*[@id='rc-imageselect-target']/table";
	protected static String recaptchaPayloadImagesXPath = "//div[contains(@class, 'rc-image-tile-target')]";
	protected static String recaptchaInstructionsXPath = "//*[@id='rc-imageselect']/div[2]/div[1]/div[1]/div[1]";
	protected static String recaptchaVerifyButtonXPath = "//*[@id='recaptcha-anchor']/div[5]";
	private static String recaptchaVerifyButtonID = "recaptcha-verify-button";
	
	protected static String recaptchaImagesTableRowsXPath = "//*[@id='rc-imageselect-target']/table/tbody/tr";
	protected static String recaptchaImagesTableColumnsXPath = "//*[@id='rc-imageselect-target']/table/tbody/tr/td";
	
	//protected static String recaptchaRectangularImageXPath = "//*[@id='rc-imageselect-target']/table/tbody/tr[1]/td[1]/div/div[1]/img";
	//protected static String recaptchaSquareImageXPath = "//*[@id='rc-imageselect-target']/table/tbody/tr[1]/td[3]/div/div[1]/img";
	
	protected static String recaptchaRectangularImageXPath = "//*[@id='rc-imageselect-target']/table/tbody/tr[1]/td[1]/div/div[1]/img";
	protected static String recaptchaSquareImageXPath = "//*[@id='rc-imageselect-target']/table/tbody/tr[1]/td[1]/div/div[1]/img";

	
	private static String calendarNextMonthButtonXPath;
	private static String calendarPrevMonthButtonXPath;
	private static String calendarOpenDateAllocatedClass;
	private static String calendarSelectedMonthNameXPath;
	
	private static String availableTimeClass;
	
	private static String ptnIsUsedErrorText;
	private static String ptnNotFoundErrorText;
	
	private static String googleSearchInputId;
	private static String googleSearchButtonXPath;
	private static String googleTotalResultsContainerId;
	
	public static String getMainIFrameHandle() {
		return mainIFrameHandle;
	}

	public static void setMainIFrameHandle(String mainIFrameHandle) {
		Page.mainIFrameHandle = mainIFrameHandle;
	}

	public static String getRecaptchaPayloadIFrameHandle() {
		return recaptchaPayloadIFrameHandle;
	}

	public static void setRecaptchaPayloadIFrameHandle(String recaptchaPayloadIFrameHandle) {
		Page.recaptchaPayloadIFrameHandle = recaptchaPayloadIFrameHandle;
	}

	public static String getRecaptchaImNotARobotCheckBoxContainerFrameHandle() {
		return recaptchaImNotARobotCheckBoxContainerFrameHandle;
	}

	public static void setRecaptchaImNotARobotCheckBoxContainerFrameHandle(
			String recaptchaImNotARobotCheckBoxContainerFrameHandle) {
		Page.recaptchaImNotARobotCheckBoxContainerFrameHandle = recaptchaImNotARobotCheckBoxContainerFrameHandle;
	}

	public static String getMainIframeXPath() {
		return mainIframeXPath;
	}

	public static void setMainIframeXPath(String mainIframeXPath) {
		Page.mainIframeXPath = mainIframeXPath;
	}

	public static String getImNotARobotCheckBoxContainerIFrameXPath() {
		return imNotARobotCheckBoxContainerIFrameXPath;
	}

	public static void setImNotARobotCheckBoxContainerIFrameXPath(
			String imNotARobotCheckBoxContainerIFrameXPath) {
		Page.imNotARobotCheckBoxContainerIFrameXPath = imNotARobotCheckBoxContainerIFrameXPath;
	}

	public static String getRecaptchaPayloadIFrameXPath() {
		return recaptchaPayloadIFrameXPath;
	}

	public static void setRecaptchaPayloadIFrameXPath(
			String recaptchaPayloadIFrameXPath) {
		Page.recaptchaPayloadIFrameXPath = recaptchaPayloadIFrameXPath;
	}

	public static String getRecaptchaInstructionsXPath() {
		return recaptchaInstructionsXPath;
	}

	public static void setRecaptchaInstructionsXPath(
			String recaptchaInstructionsXPath) {
		Page.recaptchaInstructionsXPath = recaptchaInstructionsXPath;
	}

	public static String getRecaptchaCheckBoxID() {
		return recaptchaCheckBoxID;
	}

	public static void setRecaptchaCheckBoxID(String recaptchaCheckBoxID) {
		Page.recaptchaCheckBoxID = recaptchaCheckBoxID;
	}

	public static String getRecaptchaCheckBoxCorrectStyle() {
		return recaptchaCheckBoxCorrectStyle;
	}

	public static void setRecaptchaCheckBoxCorrectStyle(
			String recaptchaCheckBoxCorrectStyle) {
		Page.recaptchaCheckBoxCorrectStyle = recaptchaCheckBoxCorrectStyle;
	}

	public static String getRecaptchaVerifyButtonXPath() {
		return recaptchaVerifyButtonXPath;
	}

	public static void setRecaptchaVerifyButtonXPath(
			String recaptchaVerifyButtonXPath) {
		Page.recaptchaVerifyButtonXPath = recaptchaVerifyButtonXPath;
	}

	public static String getRecaptchaImNotARobotCheckBoxXPath() {
		return recaptchaImNotARobotCheckBoxXPath;
	}

	public static void setRecaptchaImNotARobotCheckBoxXPath(
			String recaptchaImNotARobotCheckBoxXPath) {
		Page.recaptchaImNotARobotCheckBoxXPath = recaptchaImNotARobotCheckBoxXPath;
	}

	public static String getRecaptchaPayloadImagesTableXPath() {
		return recaptchaPayloadImagesTableXPath;
	}

	public static void setRecaptchaPayloadImagesTableXPath(
			String recaptchaPayloadImagesTableXPath) {
		Page.recaptchaPayloadImagesTableXPath = recaptchaPayloadImagesTableXPath;
	}

	public static String getRecaptchaPayloadImagesXPath() {
		return recaptchaPayloadImagesXPath;
	}

	public static void setRecaptchaPayloadImagesXPath(
			String recaptchaPayloadImagesXPath) {
		Page.recaptchaPayloadImagesXPath = recaptchaPayloadImagesXPath;
	}

	public static String getRecaptchaVerifyButtonID() {
		return recaptchaVerifyButtonID;
	}

	public static void setRecaptchaVerifyButtonID(
			String recaptchaVerifyButtonID) {
		Page.recaptchaVerifyButtonID = recaptchaVerifyButtonID;
	}

	public static String getRecaptchaImagesTableRowsXPath() {
		return recaptchaImagesTableRowsXPath;
	}

	public static void setRecaptchaImagesTableRowsXPath(
			String recaptchaImagesTableRowsXPath) {
		Page.recaptchaImagesTableRowsXPath = recaptchaImagesTableRowsXPath;
	}

	public static String getRecaptchaImagesTableColumnsXPath() {
		return recaptchaImagesTableColumnsXPath;
	}

	public static void setRecaptchaImagesTableColumnsXPath(
			String recaptchaImagesTableColumnsXPath) {
		Page.recaptchaImagesTableColumnsXPath = recaptchaImagesTableColumnsXPath;
	}

	public static String getRecaptchaRectangularImageXPath() {
		return recaptchaRectangularImageXPath;
	}

	public static void setRecaptchaRectangularImageXPath(String recaptchaImageXPath) {
		Page.recaptchaRectangularImageXPath = recaptchaImageXPath;
	}

	public static String getRecaptchaSquareImageXPath() {
		return recaptchaSquareImageXPath;
	}

	public static void setRecaptchaSquareImageXPath(
			String recaptchaSquareImageXPath) {
		Page.recaptchaSquareImageXPath = recaptchaSquareImageXPath;
	}

	public static String getCalendarNextMonthButtonXPath() {
		return calendarNextMonthButtonXPath;
	}

	public static void setCalendarNextMonthButtonXPath(
			String calendarNextMonthButtonXPath) {
		Page.calendarNextMonthButtonXPath = calendarNextMonthButtonXPath;
	}

	public static String getCalendarPrevMonthButtonXPath() {
		return calendarPrevMonthButtonXPath;
	}

	public static void setCalendarPrevMonthButtonXPath(
			String calendarPrevMonthButtonXPath) {
		Page.calendarPrevMonthButtonXPath = calendarPrevMonthButtonXPath;
	}

	public static String getCalendarOpenDateAllocatedClass() {
		return calendarOpenDateAllocatedClass;
	}

	public static void setCalendarOpenDateAllocatedClass(
			String calendarOpenDateAllocatedClass) {
		Page.calendarOpenDateAllocatedClass = calendarOpenDateAllocatedClass;
	}

	public static String getCalendarSelectedMonthNameXPath() {
		return calendarSelectedMonthNameXPath;
	}

	public static void setCalendarSelectedMonthNameXPath(
			String calendarSelectedMonthNameXPath) {
		Page.calendarSelectedMonthNameXPath = calendarSelectedMonthNameXPath;
	}

	public static String getAvailableTimeClass() {
		return availableTimeClass;
	}

	public static void setAvailableTimeClass(String availableTimeClass) {
		Page.availableTimeClass = availableTimeClass;
	}

	public static String getPtnIsUsedErrorText() {
		return ptnIsUsedErrorText;
	}

	public static void setPtnIsUsedErrorText(String ptnIsUsedErrorText) {
		Page.ptnIsUsedErrorText = ptnIsUsedErrorText;
	}

	public static String getPtnNotFoundErrorText() {
		return ptnNotFoundErrorText;
	}

	public static void setPtnNotFoundErrorText(String ptnNotFoundErrorText) {
		Page.ptnNotFoundErrorText = ptnNotFoundErrorText;
	}

	public static String getGoogleSearchInputId() {
		return googleSearchInputId;
	}

	public static void setGoogleSearchInputId(String googleSearchInputId) {
		Page.googleSearchInputId = googleSearchInputId;
	}

	public static String getGoogleSearchButtonXPath() {
		return googleSearchButtonXPath;
	}

	public static void setGoogleSearchButtonXPath(
			String googleSearchButtonXPath) {
		Page.googleSearchButtonXPath = googleSearchButtonXPath;
	}

	public static String getGoogleTotalResultsContainerId() {
		return googleTotalResultsContainerId;
	}

	public static void setGoogleTotalResultsContainerId(
			String googleTotalResultsContainerId) {
		Page.googleTotalResultsContainerId = googleTotalResultsContainerId;
	}
}
