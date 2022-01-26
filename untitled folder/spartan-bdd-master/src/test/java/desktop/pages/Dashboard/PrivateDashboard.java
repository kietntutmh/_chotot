package desktop.pages.Dashboard;

import com.vn.chotot.base.BasePage;
import com.vn.chotot.exception.ExceptionHandler;
import com.vn.chotot.exception.FailureHandling;
import com.vn.chotot.exception.StepFailedException;
import desktop.components.TopHeader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.vn.chotot.configuration.WaitTime.maxWaitTime;
import static com.vn.chotot.configuration.WaitTime.minWaitTime;
import static com.vn.chotot.exception.FailureHandling.STOP_ON_FAILURE;
import static com.vn.chotot.keywords.selenium.Action.*;
import static com.vn.chotot.keywords.selenium.Element.clickJSRandomElementOnList;
import static com.vn.chotot.keywords.selenium.Page.refreshPage;
import static com.vn.chotot.keywords.selenium.Utils.verifyEquals;
import static com.vn.chotot.keywords.selenium.Utils.verifyMatch;
import static com.vn.chotot.keywords.selenium.Wait.*;

public class PrivateDashboard extends BasePage {

    @FindBy(xpath = "//*[@aria-label=\"breadcrumbs\"]")
    private WebElement lbl_BreadCrumb;

    @FindBy(xpath = "//a[@href and contains(.,\"ĐANG BÁN\")]")
    private WebElement mnu_Published;

    @FindBy(xpath = "//a[@href and contains(.,\"BỊ TỪ CHỐI\")]")
    private WebElement mnu_Rejected;

    @FindBy(xpath = "//a[@href and contains(.,\"CẦN THANH TOÁN\")]")
    private WebElement mnu_NeedPayment;

    @FindBy(xpath = "//a[@href and contains(.,\"KHÁC\")]")
    private WebElement mnu_Others;

    @FindBy(xpath = "//button[contains(.,'Lọc tin')]")
    private WebElement btn_FilterAd;

    @FindBy(xpath = "//a[@href and contains(.,'Tin đợi duyệt')]")
    private WebElement sub_mnu_WaitForAcceptAd;

    @FindBy(xpath = "//a[@href and contains(.,'Tin đã ẩn')]")
    private WebElement sub_mnu_HidenAd;

    @FindBy(xpath = "//*[@class=\"loadingPage\"]")
    private WebElement lbl_LoadingIcon;

    @FindBy(xpath = "//div[@class='ctadBody']")
    private List<WebElement> lst_Ads;

    @FindBy(xpath = "//div[@class='ctadBody']//div[@class='ctadTitle']")
    private List<WebElement> lst_Ads_Title;

    @FindBy(xpath = "//*[@class=\"ctadBody\"]//*[@class=\"ctadType\"]")
    private List<WebElement> lst_AdType;

    @FindBy(xpath = "//*[@class=\"ctadBody\"]//*[@class=\"premiumButton\"]")
    private List<WebElement> lst_ButtonPremium;

    @FindBy(xpath = "//*[@class=\"ctadBody\"]//*[@class=\"payAdButton\"]")
    private List<WebElement> lst_ButtonPayment;

    @FindBy(xpath = "//*[@class=\"ctadBody\"]//*[@class=\"editAdButton\"]")
    private List<WebElement> lst_ButtonEditAd;

    @FindBy(
            xpath =
                    "//div[starts-with(@class,'boxContentRight')]//div[starts-with(@class, 'col-md')]/div[2]/span")
    private WebElement lbl_AccountMoney;

    @FindBy(xpath = "//div[contains(@class, 'dashboardAd')]//div[@class='ctadTitle']")
    private List<WebElement> lst_TitleAds;

    @FindBy(xpath = "//div[contains(@class, 'dashboardAd')]//div[@class='payAdButton']/a")
    private List<WebElement> lst_PaymentBtnAds;

    @FindBy(xpath = "//div[@id='cart_bar']/a")
    private WebElement btn_Payment;

    public void goToPrivateDashboard() {
        new TopHeader().clickIBuy();
    }

    public void selectRandomPremiumButton() {
        // Select random item
        clickJSRandomElementOnList(lst_ButtonPremium);

        // Wait for page load
        waitForLoadingPageIconDismissed(minWaitTime, maxWaitTime * 2);

        // Click checkout
        clickCheckout();
    }

    public void selectRandomPaymentButton() {
        // Select random item
        clickJSRandomElementOnList(lst_ButtonPayment);

        // Wait for page load
        waitForLoadingPageIconDismissed(minWaitTime, maxWaitTime * 2);

        // Click checkout
        clickCheckout();
    }

    public String clickCheckout() {
        // Get amount
        String amount = getText(btn_Payment, maxWaitTime);
        amount = amount.split(" ")[0].split("\n")[1];

        // Click checkout
        clickJS(btn_Payment, maxWaitTime);
        waitForElementStaleness(btn_Payment, maxWaitTime * 3);

        return amount;
    }

    /**
     * Verify number, status ads
     *
     * @param expectedNumber
     * @param expectedStatus: "Đợi duyệt", "Đã ẩn"
     */
    public void verifyOtherTab(int expectedNumber, String expectedStatus) {
        // Click Other tab
        moveAndClick(mnu_Others);
        waitForElementStaleness(lbl_BreadCrumb, maxWaitTime);

        // Verify current number of ad
        int currentAdNumber = getAdNumberOfTabMenu(mnu_Others);
        verifyEquals(currentAdNumber, expectedNumber, STOP_ON_FAILURE);

        // Verify status
        String currentStatus;
        for (WebElement webElement : lst_AdType) {
            currentStatus = webElement.getText();
            verifyMatch(currentStatus, expectedStatus, false, FailureHandling.CONTINUE_ON_FAILURE);
        }
    }

    public void verifyNeedPaymentTab(int expectedNumber) {
        // Go to Payment tab
        moveAndClick(mnu_NeedPayment);
        waitForElementStaleness(lbl_BreadCrumb, maxWaitTime);

        // Verify current number of ad
        int currentAdNumber = getAdNumberOfTabMenu(mnu_NeedPayment);
        verifyEquals(currentAdNumber, expectedNumber, STOP_ON_FAILURE);
    }

    public void verifyRejectedTab(int expectedNumber) {
        // Go to Rejected tab
        moveAndClick(mnu_Rejected);
        waitForElementStaleness(lbl_BreadCrumb, maxWaitTime);

        // Verify current number of ad
        int currentAdNumber = getAdNumberOfTabMenu(mnu_Rejected);
        verifyEquals(currentAdNumber, expectedNumber, STOP_ON_FAILURE);
    }

    public void verifyPublishedTab(int expectedNumber) {
        // Go to Published tab
        moveAndClick(mnu_Published);
        waitForElementStaleness(lbl_BreadCrumb, maxWaitTime);

        // Verify current number of ad
        int currentAdNumber = getAdNumberOfTabMenu(mnu_Published);
        verifyEquals(currentAdNumber, expectedNumber, STOP_ON_FAILURE);
    }

    public int getAdNumberOfTabMenu(WebElement tabMenu) {
        String value = getText(tabMenu, maxWaitTime);
        String number = value.substring(value.indexOf("(") + 1, value.indexOf(")"));
        return Integer.parseInt(number);
    }

    public void clickOnTabMenu(String menu) {
        switch (menu.toUpperCase()) {
            case "ĐANG BÁN":
                clickJS(mnu_Published);
                break;
            case "BỊ TỪ CHỐI":
                clickJS(mnu_Rejected);
                break;
            case "CẦN THANH TOÁN":
                clickJS(mnu_NeedPayment);
                break;
            case "KHÁC":
                clickJS(mnu_Others);
        }
        waitForElementStaleness(lbl_BreadCrumb, maxWaitTime);
        waitForAllElementVisible(lst_ButtonPremium, maxWaitTime);
    }

    public void filterAdOnOtherTab(String filter) {
        switch (filter.toUpperCase()) {
            case "ĐÃ ẨN":
                waitForElementVisible(btn_FilterAd, minWaitTime);
                clickJS(btn_FilterAd);
                waitForElementVisible(sub_mnu_HidenAd, maxWaitTime);
                clickJS(sub_mnu_HidenAd);
                break;
            case "ĐỢI DUYỆT":
                waitForElementVisible(btn_FilterAd, minWaitTime);
                clickJS(btn_FilterAd);
                waitForElementVisible(sub_mnu_WaitForAcceptAd, maxWaitTime);
                clickJS(sub_mnu_WaitForAcceptAd);
                break;
        }
        waitForElementStaleness(lbl_BreadCrumb, maxWaitTime);
        waitForAllElementVisible(lst_ButtonPremium, maxWaitTime);
    }

    // ĐANG BÁN / BỊ TỪ CHỐI / CẦN THANH TOÁN / KHÁC / ĐÃ ẨN / ĐỢI DUYỆT
    public boolean verifyAdSubjectDisplayed(String adSubject, String menu) {
        // Refresh Page make sure data is loaded
        refreshPage();

        // Wait for page load
        waitForElementStaleness(lbl_BreadCrumb, maxWaitTime * 3);

        // Go to expected menu
        if (menu.toUpperCase().equals("ĐỢI DUYỆT") || menu.toUpperCase().equals("ĐÃ ẨN")) {
            clickOnTabMenu("KHÁC");
            filterAdOnOtherTab(menu);
        } else clickOnTabMenu(menu);

        // Check ad subject displayed
        waitForAllElementVisible(lst_Ads_Title, maxWaitTime);
        for (WebElement webElement : lst_Ads_Title) {
            String actualSubject = getAttributeValue(webElement, "innerText", maxWaitTime);
            if (verifyMatch(adSubject, actualSubject, false, FailureHandling.WARNING)) return true;
        }
        ExceptionHandler.exceptionHandler(
                new StepFailedException(
                        "Ad subject \"" + adSubject + "\" is not shown on tab menu \"" + menu + "\""),
                STOP_ON_FAILURE);
        return false;
    }

    public void verifyAmountMoneyNotChange(String expectMoney) {
        waitForElementVisible(lbl_AccountMoney, maxWaitTime);
        String actualMoney = getAttributeValue(lbl_AccountMoney, "innerText", maxWaitTime);
        verifyMatch(expectMoney, actualMoney, false, STOP_ON_FAILURE);
    }

    public String getAmountMoney() {
        waitForElementVisible(lbl_AccountMoney, maxWaitTime);
        return getAttributeValue(lbl_AccountMoney, "innerText", maxWaitTime);
    }

    public void clickOnButtonPaidAd(String adSubject) {
        waitForAllElementVisible(lst_TitleAds, maxWaitTime);
        waitForAllElementVisible(lst_PaymentBtnAds, maxWaitTime);
        int elementIndex = 0;
        for (int i = 0; i < lst_TitleAds.size(); i++) {
            String adTitle = getAttributeValue(lst_TitleAds.get(i), "innerText", minWaitTime);
            if (adTitle.trim().equals(adSubject)) {
                elementIndex = i;
                break;
            }
        }
        if (getAttributeValue(lst_PaymentBtnAds.get(elementIndex), "className", minWaitTime)
                .equals("btn")) clickJS(lst_PaymentBtnAds.get(elementIndex));
    }

    public void clickToPayAds() {
        waitForElementVisible(btn_Payment, maxWaitTime);
        clickJS(btn_Payment);
    }

    public boolean waitForAdSubjectDisplayed(String adSubject, String menu) {

        // Refresh Page make sure data is loaded
        refreshPage();

        // Wait for page load
        waitForElementStaleness(lbl_BreadCrumb, maxWaitTime * 3);

        // Go to expected menu
        if (menu.toUpperCase().equals("ĐỢI DUYỆT") || menu.toUpperCase().equals("ĐÃ ẨN")) {
            clickOnTabMenu("KHÁC");
            filterAdOnOtherTab(menu);
        } else clickOnTabMenu(menu);

        // Check ad subject displayed
        waitForAllElementVisible(lst_Ads_Title, maxWaitTime * 5);
        for (WebElement webElement : lst_Ads_Title) {
            String actualSubject = getAttributeValue(webElement, "innerText", maxWaitTime);
            if (verifyMatch(adSubject, actualSubject, false, FailureHandling.WARNING)) return true;
        }
        return false;
    }
}
