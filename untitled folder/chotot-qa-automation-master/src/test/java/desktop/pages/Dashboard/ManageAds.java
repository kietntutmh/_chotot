package desktop.pages.Dashboard;

import com.vn.chotot.base.BasePage;
import com.vn.chotot.exception.FailureHandling;
import com.vn.chotot.logger.Log4jFactory;
import desktop.components.TopHeader;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.vn.chotot.configuration.WaitTime.maxWaitTime;
import static com.vn.chotot.configuration.WaitTime.minWaitTime;
import static com.vn.chotot.keywords.selenium.Action.*;
import static com.vn.chotot.keywords.selenium.Element.*;
import static com.vn.chotot.keywords.selenium.Page.refreshPage;
import static com.vn.chotot.keywords.selenium.Page.verifyPageTitleNotMatch;
import static com.vn.chotot.keywords.selenium.Utils.verifyMatch;
import static com.vn.chotot.keywords.selenium.Utils.verifyNotMatch;
import static com.vn.chotot.keywords.selenium.Wait.waitForAllElementVisible;
import static com.vn.chotot.keywords.selenium.Wait.waitForElementStaleness;

public class ManageAds extends BasePage {
    final Logger log = Log4jFactory.instance().createClassLogger(getClass());

    @FindBy(xpath = "//h1[text()=\"Quản lý tin đăng\"]")
    private WebElement lbl_MangeAds;

    @FindBy(xpath = "//div[@id='boxFocusInfo']/a")
    private WebElement lnk_GoToShop;

    @FindBy(xpath = "//*[@id='userProfileName']//img")
    private WebElement lnk_GoToChotot;

    @FindBy(xpath = "//*[@id=\"cart_bar\"]/a")
    private WebElement btn_Payment;

    @FindBy(xpath = "//a[text()=\"Xem trang cá nhân của bạn\"]")
    private WebElement lnk_ViewPersonalPage;

    @FindBy(xpath = "//*[@id='adTabBar']")
    private WebElement lbl_TabMenu;

    @FindBy(xpath = "//*[@class=\"ctadBody\"]")
    private List<WebElement> lst_Ads;

    // Right Content
    @FindBy(xpath = "//*[contains(@class,\"boxContentRight\")]//li")
    private List<WebElement> lst_RefillOption;

    @FindBy(xpath = "//*[text()=\"Nạp Ngay\"]")
    private WebElement btn_RefillNow;

    public void verifyManageAdPage() {
        waitForElementStaleness(lbl_TabMenu, maxWaitTime);
        verifyElementVisible(lbl_TabMenu, maxWaitTime * 2, FailureHandling.STOP_ON_FAILURE);
    }

    public String getTabMenuInfo() {
        // Get tab menu info
        refreshPage(); // To affect change
        waitForElementStaleness(lbl_TabMenu, maxWaitTime);
        waitForAllElementVisible(lst_Ads, maxWaitTime);
        return getText(lbl_TabMenu, maxWaitTime);
    }

    public void verifyNewAdDisplayedOnPrivateDashboard(String oldTabMenuInfo) {
        // Go to manage ad
        new TopHeader().clickIBuy();

        // Verify tab menu
        String currentTabMenuInfo = getTabMenuInfo();
        verifyNotMatch(currentTabMenuInfo, oldTabMenuInfo, false, FailureHandling.STOP_ON_FAILURE);
    }

    public void verifyNewAdDisplayedOnShopDashboard(List<String> oldTabMenuInfo) {
        // Go to manage ad
        new TopHeader().clickGoToShop();

        // The Shop dashboard should be displayed after insert ad shops
        verifyPageTitleNotMatch(
                "(?i)(.*)Quản lý tin(.*)", true, maxWaitTime, FailureHandling.CONTINUE_ON_FAILURE);
        // Verify tab menu info changed
        String currentTabMenuInfo = getTabMenuInfo();
        verifyNotMatch(
                currentTabMenuInfo, oldTabMenuInfo.get(1), false, FailureHandling.CONTINUE_ON_FAILURE);

        // Verify that private dashboard should not change tab menu info
        goToChoTot();
        currentTabMenuInfo = getTabMenuInfo();
        verifyMatch(currentTabMenuInfo, oldTabMenuInfo.get(0), false, FailureHandling.STOP_ON_FAILURE);
    }

    public void goToShop() {
        waitForElementStaleness(lnk_GoToShop, minWaitTime);
        clickJS(lnk_GoToShop);
        waitForElementStaleness(lnk_GoToShop, maxWaitTime * 2);
    }

    public void goToChoTot() {
        clickJS(lnk_GoToChotot);
        waitForElementStaleness(lnk_GoToChotot, maxWaitTime * 2);
    }

    public String clickPayment() {
        String amount = getText(btn_Payment, maxWaitTime);
        amount = amount.substring(amount.indexOf(" "), amount.indexOf(" -") - 1);
        clickJS(btn_Payment);
        return amount;
    }

    public String selectRefillOptionByIndex(int index) {
        // Select option
        String value =
                selectElementOnListByIndex(lst_RefillOption, index, FailureHandling.STOP_ON_FAILURE);
        value = value.split(" ")[1];
        log.info("\n----- Refill value: " + value);

        // Click refill
        moveAndClickJS(btn_RefillNow);

        // Wait for page load
        waitForElementStaleness(btn_RefillNow, maxWaitTime * 3);

        return value;
    }

    public String selectRandomRefillOptionByIndex() {
        // Select option
        String value = clickJSRandomElementOnList(lst_RefillOption);
        value = value.split(" ")[1];
        log.info("\n----- Refill value: " + value);

        // Click refill
        moveAndClickJS(btn_RefillNow);

        // Wait for page load
        waitForElementStaleness(btn_RefillNow, maxWaitTime * 2);

        return value;
    }
}
