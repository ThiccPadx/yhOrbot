package dev.div0.steps;

public enum StepAction {
	TASK_SETTING("taskSettings"),
	OPEN_URL("openUrl"),
	CLICK_LINK("clickLink"),
	SWITCH_TO_IFRAME("switchToIframe"),
	FIND_IFRAME_BY_TAG_AND_TAG_INDEX("findIframeByTagAndTagIndex"),
	GET_DEFAULT_CONTENT("getDefaultContent"),
	SELECT_DROP_DOWN_ITEM("selectDropDownItem"),
	TAKE_IFRAME_SCREENSHOT("takeIframeScreenshot"),
	DETECT_PTN_IS_CORRCT("detectPTNIsCorrect"),
	WAIT("wait"),
	START_RECAPTCHA_ROBOT("startRecaptchaRobot"),
	GET_RECAPTCHA_INSTRUCTIONS("getRecaptchaInstructions"),
	SAVE_RECAPTCHA_IMAGE("saveRecaptchaImage"),
	SOLVE_RECAPTCHA("solveRecaptcha"),
	SOLVE_RECAPTCHA_WITH_ERROR("solveREcaptchaWithError"),
	DETECT_DATES_AVAILABLE_FOR_SELECTED_CITY("detectDatesAvailableForSelectedCity"),
	BRING_BROWSER_TO_FRONT("bringBrowserToFront"),
	REFRESH_PAGE("refreshPage"),
	SET_TEXT("setText"),
	GET_TEXT("getText"),
	SELECT_CALENDAR_AVAILABLE_DATE("selectCalendarAvailableDate"),
	DESTROY("destroy"),
	SELECT_AVAILABLE_TIME("selectAvailableTime"),
	GET_GOOGLE_SEARCH_RESULTS_TOTAL("getGoogleSearchResultsTotal"),
	PRESS_ENTER_KEY("pressEnterKey"),
	CLICK_ELEMENT("clickElement"),
	UNDEFINED_STEP_ACTION("undefinedStepAction"),
	DETECT_PAGE_HAS_TEXT("detectPageHasText");
	
	private final String type;
	
	private StepAction(String type){
		this.type = type;
	}
	
	public boolean isEquals(String otherType) {
        return (otherType == null) ? false : type.equals(otherType);
    }
}
