package desktop.pages.Dashboard;

import com.vn.chotot.base.BasePage;
import com.vn.chotot.exception.FailureHandling;
import com.vn.chotot.logger.Log4jFactory;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.vn.chotot.configuration.WaitTime.maxWaitTime;
import static com.vn.chotot.configuration.WaitTime.minWaitTime;
import static com.vn.chotot.keywords.selenium.Action.*;
import static com.vn.chotot.keywords.selenium.Element.*;
import static com.vn.chotot.keywords.selenium.Utils.verifyMatch;
import static com.vn.chotot.keywords.selenium.Wait.*;

public class Bump extends BasePage {
    final Logger log = Log4jFactory.instance().createClassLogger(getClass());

    @FindBy(xpath = "//*[@class='bumpPopup']")
    private WebElement lbl_BumpPopup;

    @FindBy(xpath = "//*[@class='bumpPopupCloseButton']")
    private WebElement btn_CloseBumpPopup;

    @FindBy(xpath = "//*[contains(@class,'bumpPopup')]//*[contains(@class,'premiumServiceItemName')]")
    private WebElement lbl_PremiumServiceItemName;

    @FindBy(xpath = "//*[@class='bumpPopup']//*[@class='premiumServiceItemPriceText']")
    private WebElement lbl_PremiumServiceItemPrice;

    @FindBy(xpath = "//*[@class='bumpSelectionName']")
    private List<WebElement> lst_Normal_BumpOption;

    @FindBy(xpath = "//*[@class='bumpPopupTimerListSelection']//*[contains(@class,'timerRange')]")
    private List<WebElement> lst_Timer_BumpOption;

    @FindBy(
            xpath =
                    "//*[contains(@class,'bumpPopup')]//*[contains(@class,'premiumServiceItemDescription')]/strong[1]")
    private WebElement lbl_Timer_Description_BumpCount;

    @FindBy(
            xpath =
                    "//*[contains(@class,'bumpPopup')]//*[contains(@class,'premiumServiceItemDescription')]/strong[2]")
    private WebElement lbl_Timer_Description_BumpDay;

    @FindBy(
            xpath =
                    "//*[contains(@class,'bumpPopup')]//*[contains(@class,'premiumServiceItemDescription')]/strong[3]")
    private WebElement lbl_Timer_Description_BumpTime;

    @FindBy(xpath = "//input[@id='durationSelected']")
    private WebElement input_Timer_Duration_BumpDay;

    @FindBy(xpath = "//*[contains(@class,'addDuration')]")
    private WebElement btn_Timer_Duration_Add;

    @FindBy(xpath = "//*[contains(@class,'subtractDuration')]")
    private WebElement btn_Timer_Duration_Sub;

    @FindBy(xpath = "//*[@class='stickyAdPopupListDuration']//li")
    private List<WebElement> lst_StickyAd_BumpOption;

    @FindBy(xpath = "//*[@class='bumpPopup']//*[@class='premiumServiceItemName']")
    private WebElement lbl_StickyAd_Title;

    @FindBy(xpath = "//*[@class='adFeaturePopupListFeature']//li")
    private List<WebElement> lst_AdFeature_BumpOption;

    @FindBy(xpath = "//*[@class='bumpPopup']//*[@class='premiumServiceItemName']")
    private WebElement lbl_AdFeature_Title;

    @FindBy(xpath = "//*[@class='bundlePopupListBundles']//li")
    private List<WebElement> lst_SavedBundle_BumpOption;

    @FindBy(xpath = "//*[@class='bumpPopup']//*[@class='bundleInsideBumpSelectBox']")
    private WebElement chk_BundleInsideBump;

    @FindBy(xpath = "//*[@class='showMoreTimeButton']")
    private WebElement btn_ViewMore;

    @FindBy(xpath = "//*[@class='buttonInPopupCheckout']")
    private WebElement btn_Checkout;

    @FindBy(xpath = "//*[@class='buttonInPopupBuyMore']")
    private WebElement btn_BuyMore;

    @FindBy(xpath = "//*[@class='confirmButton']")
    private WebElement btn_QuickPaymentWithDongTot;

    @FindBy(xpath = "//*[@class='cancelButton']")
    private WebElement btn_CancelQuickPayment;

    @FindBy(xpath = "//h4[text()='Thanh Toán Thành Công']")
    private WebElement lbl_PaymentSuccessfull;

    @FindBy(xpath = "//*[@class='loadingPageSmall']")
    private WebElement lbl_LoadingIcon;

    public String getPrice() {
        String price = btn_Checkout.getText();
        return price.substring(price.lastIndexOf("-") + 2);
    }

    public void comparePriceOnTitleAndCheckoutButton() {
        String titlePrice = lbl_PremiumServiceItemPrice.getText().toUpperCase().trim();
        String checkoutPrice = getPrice();
        verifyMatch(titlePrice, checkoutPrice, false, FailureHandling.CONTINUE_ON_FAILURE);
    }

    public String selectNormalBump(String option, boolean doesSelectBundle) {
        // Select option
        clickElementOnListByName(lst_Normal_BumpOption, option, false, FailureHandling.STOP_ON_FAILURE);

        // Select bundle
        //        if (doesSelectBundle)
        //            clickAction(chk_BundleInsideBump);

        // Return price
        return getPrice();
    }

    public String selectAllNormalBump(boolean doesSelectBundle) {
        // Select all option
        String option;
        for (int i = 0; i < lst_Normal_BumpOption.size(); i++) {
            // Select by index
            option =
                    selectElementOnListByIndex(lst_Normal_BumpOption, i, FailureHandling.CONTINUE_ON_FAILURE);

            // Get selected option
            option = option.replace("ngày", "Ngày");
            if (i == 0) option = "Ngay";

            // Verify selected option displayed on title
            log.info("Normal Ad Bump: verify Popup Title - When choose option: " + option);
            verifyElementText(
                    lbl_PremiumServiceItemName, false, option, FailureHandling.CONTINUE_ON_FAILURE);
        }

        // Select bundle
        //        if (doesSelectBundle)
        //            clickAction(chk_BundleInsideBump);

        // Return price
        return getPrice();
    }

    public String selectTimerBump(String option) {
        // Click view more
        moveAndClick(btn_ViewMore);

        // Select option
        clickElementOnListByName(lst_Timer_BumpOption, option, false, FailureHandling.STOP_ON_FAILURE);

        // Return price
        return getPrice();
    }

    public String selectAllTimerBump(String defaultPrice) {
        // Verify default price
        verifyElementVisible(lbl_BumpPopup, maxWaitTime * 3);
        verifyElementText(
                lbl_PremiumServiceItemPrice, false, defaultPrice, FailureHandling.STOP_ON_FAILURE);

        // Click view more
        moveAndClick(btn_ViewMore);
        String option, bumpCount, bumpDayDuration, bumpDayDesc, bumpTime, bumpTimeExpect = "";

        // Check Bump Time
        waitForAllElementVisible(lst_Timer_BumpOption, maxWaitTime);
        for (int i = 0; i < lst_Timer_BumpOption.size(); i++) {
            option =
                    selectElementOnListByIndex(lst_Timer_BumpOption, i, FailureHandling.CONTINUE_ON_FAILURE);
            bumpCount =
                    getAttributeValue(lbl_Timer_Description_BumpCount, "innerText", minWaitTime)
                            .split(" ")[0];
            bumpTime = getAttributeValue(lbl_Timer_Description_BumpTime, "innerText", minWaitTime);
            if (i == 0) bumpTimeExpect = option;
            else bumpTimeExpect += ", " + option;

            log.info(
                    "Timer Ad Bump: verify Popup Description: Bump Count - When choose option: " + option);
            verifyMatch(bumpCount, i + 1 + "", false, FailureHandling.CONTINUE_ON_FAILURE);

            log.info(
                    "Timer Ad Bump: verify Popup Description: Bump Time - When choose option: " + option);
            verifyMatch(bumpTime, bumpTimeExpect, false, FailureHandling.CONTINUE_ON_FAILURE);
        }

        // Check Bump Day
        log.info("Timer Ad Bump: verify Popup Description: Bump Day");
        for (int i = 0; i < 3; i++) {
            bumpDayDesc =
                    getAttributeValue(lbl_Timer_Description_BumpDay, "innerText", minWaitTime).split(" ")[0];
            bumpDayDuration = getAttributeValue(input_Timer_Duration_BumpDay, "value", minWaitTime);
            verifyMatch(bumpDayDesc, bumpDayDuration, false, FailureHandling.CONTINUE_ON_FAILURE);
            if (i % 2 == 0) clickJS(btn_Timer_Duration_Sub);
            else clickJS(btn_Timer_Duration_Add);
        }

        // Return price
        return getPrice();
    }

    public String selectStickyAdBump(String option) {
        // Select option
        clickElementOnListByName(
                lst_StickyAd_BumpOption, option, false, FailureHandling.STOP_ON_FAILURE);

        // Return price
        return getPrice();
    }

    public String selectAllStickyAdBump(String defaultPrice) {
        // Verify default price
        verifyElementVisible(lbl_BumpPopup, maxWaitTime);
        verifyElementText(
                lbl_PremiumServiceItemPrice, false, defaultPrice, FailureHandling.STOP_ON_FAILURE);

        String title = "Tin Ưu Tiên %s ngày", actualTitle, expectTitle, option;
        waitForAllElementVisible(lst_StickyAd_BumpOption, maxWaitTime * 2);
        for (int i = 0; i < lst_StickyAd_BumpOption.size(); i++) {
            // Click and getText of option
            option =
                    selectElementOnListByIndex(
                            lst_StickyAd_BumpOption, i, FailureHandling.CONTINUE_ON_FAILURE)
                            .trim();

            // Verify new Title
            expectTitle = String.format(title, option);
            actualTitle = getAttributeValue(lbl_StickyAd_Title, "innerText", maxWaitTime).trim();

            log.info("Sticky Ad Bump: verify Title - When choose option: " + option);
            verifyMatch(
                    actualTitle.toUpperCase(),
                    expectTitle.toUpperCase(),
                    false,
                    FailureHandling.CONTINUE_ON_FAILURE);
        }

        // Return price
        return getPrice();
    }

    public String selectAdFeatureBump(String option) {
        // Select option
        clickElementOnListByName(
                lst_AdFeature_BumpOption, option, false, FailureHandling.STOP_ON_FAILURE);

        // Return price
        return getPrice();
    }

    public String selectAllAdFeatureBump(String defaultPrice) {
        // Verify default price
        verifyElementVisible(lbl_BumpPopup, maxWaitTime);
        verifyElementText(
                lbl_PremiumServiceItemPrice, false, defaultPrice, FailureHandling.STOP_ON_FAILURE);

        String option, titleActual, titleExpect = "";
        String bumpDay = " 7 ngày";

        waitForAllElementVisible(lst_AdFeature_BumpOption, maxWaitTime * 3);

        log.info("Feature Ad Bump: verify Title changes by option");
        for (int i = 0; i < lst_AdFeature_BumpOption.size(); i++) {
            if (i == 0) {
                option = getAttributeValueOfElementOnListByIndex(lst_AdFeature_BumpOption, i, "innerText");
                titleExpect = option + bumpDay;
            } else {
                option =
                        selectElementOnListByIndex(
                                lst_AdFeature_BumpOption, i, FailureHandling.CONTINUE_ON_FAILURE)
                                .trim();
                titleExpect = titleExpect.replace(bumpDay, "").trim() + " + " + option + bumpDay;
            }

            titleActual = getAttributeValue(lbl_AdFeature_Title, "innerText", maxWaitTime).trim();

            log.info("Feature Ad Bump: verify Title - When choose option: " + option);
            verifyMatch(
                    titleActual.toUpperCase(),
                    titleExpect.toUpperCase(),
                    false,
                    FailureHandling.CONTINUE_ON_FAILURE);
        }
        // Return price
        return getPrice();
    }

    public String selectSavedBundleBump(String option) {
        // Select option
        clickElementOnListByName(
                lst_SavedBundle_BumpOption, option, false, FailureHandling.STOP_ON_FAILURE);

        // Return price
        return getPrice();
    }

    public String selectAllSavedBundleBump(String defaultPrice) {
        // Verify default price
        verifyElementVisible(lbl_BumpPopup, maxWaitTime);
        verifyElementText(
                lbl_PremiumServiceItemPrice, false, defaultPrice, FailureHandling.STOP_ON_FAILURE);

        String option, titleActual, titleExpect;
        String bumpDay = "1 ngày";

        waitForAllElementVisible(lst_SavedBundle_BumpOption, maxWaitTime * 3);
        for (int i = 0; i < lst_SavedBundle_BumpOption.size(); i++) {
            if (i == 0)
                option =
                        getAttributeValueOfElementOnListByIndex(lst_SavedBundle_BumpOption, i, "innerText");
            else
                option =
                        selectElementOnListByIndex(
                                lst_SavedBundle_BumpOption, i, FailureHandling.CONTINUE_ON_FAILURE)
                                .trim();

            // Get expected title
            titleExpect = option + " " + bumpDay;

            titleActual = getAttributeValue(lbl_AdFeature_Title, "innerText", maxWaitTime).trim();

            log.info("Saved Ad Bump: verify Title - When choose option: " + option);
            verifyMatch(
                    titleActual.toUpperCase(),
                    titleExpect.toUpperCase(),
                    false,
                    FailureHandling.CONTINUE_ON_FAILURE);
        }

        // Return price
        return getPrice();
    }

    public void clickCloseBump() {
        clickJS(btn_CloseBumpPopup);
    }

    public void clickBuyMore() {
        clickJS(btn_BuyMore);
    }

    public void clickCheckout() {
        // Click checkout
        clickAction(btn_Checkout);

        if (waitForElementVisible(btn_CancelQuickPayment, minWaitTime)) {
            clickJS(btn_CancelQuickPayment);
        }

        // Wait for loading page icon dismissed
        if (waitForElementVisible(lbl_LoadingIcon, maxWaitTime)) {
            waitForElementStaleness(lbl_LoadingIcon, maxWaitTime * 2);
        }
    }

    public void waitForPopupDisappear() {
        waitForElementNotVisible(lbl_BumpPopup, maxWaitTime);
    }
}
