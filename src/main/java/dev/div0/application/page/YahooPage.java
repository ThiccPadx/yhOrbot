package dev.div0.application.page;

public class YahooPage {
    public static String auth_pageUrl = "https://login.yahoo.co.jp/config/login";
    public static String auth_loginInputId = "username";
    public static String auth_loginBtnId = "btnNext";
    public static String auth_loginErrorBoxXPath = "//*[@id=\"errBox\"]/div/div/a";
    public static String auth_passwordInputXPath = "//*[@id=\"passwd\"]";
    public static String auth_submitBtnId = "btnSubmit";
    public static String auth_loggedInputId = "inpUsernameBox";

    public static String bidding_makeBidButtonXpath = "//*[@id=\"l-sub\"]/div[1]/ul/li[2]/div[1]/div[1]/a";
    public static String bidding_makeNormalBidButtonXpath = "//*[@id=\"l-sub\"]/div[1]/ul/li[2]/div/dd/a";
    public static String bidding_bidMoneyValueCSS = "Price Price--current";
    public static String bidding_blitzBidButtonXpath = "//*[@id=\"l-sub\"]/div[1]/ul/li[2]/div[2]/dd/a";
    public static String bidding_blitzBidMoneyValueXPath = "Price Price--buynow";

    public static String bidding_bidModalSubmitButtonXPath = "//*[@id=\"BidModal\"]/div[2]/div[2]/form/div[3]/span/input";
    public static String bidding_normalBidModalSubmitButtonXPath = "//*[@id=\"BidModals\"]/div[1]/div[2]/div[2]/form/div[3]/span/input";
    public static String bidding_bidFinalSubmitButtonXPath = "//*[@id=\"allContents\"]/div[1]/div[2]/div[2]/form/div[3]/input[1]";

    public static String bidding_bidAlreadySetImageXPath = "//*[@id=\"modAlertBox\"]/div/div/div/div/div/div/div/div/div/table/tbody/tr/td[1]/img";

}
