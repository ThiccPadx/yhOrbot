package dev.div0.robotOperations.factory;

import dev.div0.robotOperations.*;
import dev.div0.robotOperations.bidding.Bidding;
import dev.div0.robotOperations.google.GetGoogleSearchResultsTotalOperation;
import dev.div0.robotOperations.ptn.DetectPtnIsCorrect;
import dev.div0.robotOperations.recaptcha.GetRecaptchaInstructionsOperation;
import dev.div0.robotOperations.recaptcha.SaveRecaptchaImageOperation;
import dev.div0.robotOperations.recaptcha.SolveRecaptchaOperation;
import dev.div0.robotOperations.recaptcha.SolveRecaptchaWithErrorOperation;
import dev.div0.robotOperations.screen.TakeScreenshot;
import dev.div0.steps.StepAction;

public class OperationFactory {
	public BaseOperation getOperation(StepAction type){
		if(type.equals(StepAction.OPEN_URL)){
			return new OpenUrlOperation();
		}
		if(type.equals(StepAction.CREATE_AUTH)){
			return new AuthOperation();
		}
		if(type.equals(StepAction.CREATE_BEDDING)){
			return new Bidding();
		}
		else if(type.equals(StepAction.CLICK_LINK)){
			return new ClickLinkOperation();
		}
		else if(type.equals(StepAction.SWITCH_TO_IFRAME)){
			return new SwitchToIFrameOperation();
		}
		else if(type.equals(StepAction.FIND_IFRAME_BY_TAG_AND_TAG_INDEX)){
			return new FindIframeByTagAndTagIndex();
		}
		else if(type.equals(StepAction.GET_DEFAULT_CONTENT)){
			return new SwitchToDefaultContentOperation();
		}
		else if(type.equals(StepAction.SELECT_DROP_DOWN_ITEM)){
			return new SelectDropDownItemOperation();
		}
		else if(type.equals(StepAction.CLICK_ELEMENT)){
			return new ClickElementOperation();
		}
		else if(type.equals(StepAction.TAKE_IFRAME_SCREENSHOT)){
			return new TakeScreenshot();
		}
		else if(type.equals(StepAction.WAIT)){
			return new WaitOperation();
		}
		else if(type.equals(StepAction.BRING_BROWSER_TO_FRONT)){
			return new BringBrowserToFrontOperation();
		}
		else if(type.equals(StepAction.START_RECAPTCHA_ROBOT)){
			//return new RecaptchaRobotOperation();
		}
		else if(type.equals(StepAction.SAVE_RECAPTCHA_IMAGE)){
			return new SaveRecaptchaImageOperation();
		}
		else if(type.equals(StepAction.GET_RECAPTCHA_INSTRUCTIONS)){
			return new GetRecaptchaInstructionsOperation();
		}
		else if(type.equals(StepAction.SOLVE_RECAPTCHA)){
			return new SolveRecaptchaOperation();
		}
		else if(type.equals(StepAction.SOLVE_RECAPTCHA_WITH_ERROR)){
			return new SolveRecaptchaWithErrorOperation();
		}
		else if(type.equals(StepAction.REFRESH_PAGE)){
			return new RefreshPageOperation();
		}
		else if(type.equals(StepAction.DETECT_DATES_AVAILABLE_FOR_SELECTED_CITY)){
			return new DetectDatesAvailableForSelectedCityOperation();
		}
		else if(type.equals(StepAction.DETECT_PTN_IS_CORRCT)){
			return new DetectPtnIsCorrect();
		}
		else if(type.equals(StepAction.DETECT_PAGE_HAS_TEXT)){
			return new DetectPageHasTextOperation();
		}
		else if(type.equals(StepAction.SET_TEXT)){
			return new SetTextOperation();
		}
		else if(type.equals(StepAction.GET_TEXT)){
			return new GetTextOperation();
		}
        else if(type.equals(StepAction.GET_HTML)){
            return new GetHtmlOperation();
        }
		else if(type.equals(StepAction.SELECT_CALENDAR_AVAILABLE_DATE)){
			return new SelectCalendarAvailableDateOperation();
		}
		else if(type.equals(StepAction.SELECT_AVAILABLE_TIME)){
			return new SelectAvailableTimeOperation();
		}
		else if(type.equals(StepAction.GET_GOOGLE_SEARCH_RESULTS_TOTAL)){
			return new GetGoogleSearchResultsTotalOperation();
		}
		else if(type.equals(StepAction.PRESS_ENTER_KEY)){
			return new PressEnterKeyOperation();
		}
		else if(type.equals(StepAction.HAS_ELEMENT)){
			return new DetectPageHasElementOperation();
		}
		return null;
	}
}
