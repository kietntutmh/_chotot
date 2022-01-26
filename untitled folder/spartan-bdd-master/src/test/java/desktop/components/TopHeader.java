package desktop.components;

import com.vn.chotot.base.BasePage;
import com.vn.chotot.exception.FailureHandling;
import com.vn.chotot.logger.Log4jFactory;
import desktop.pages.HomePage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.vn.chotot.configuration.WaitTime.maxWaitTime;
import static com.vn.chotot.configuration.WaitTime.minWaitTime;
import static com.vn.chotot.keywords.selenium.Action.*;
import static com.vn.chotot.keywords.selenium.Element.*;
import static com.vn.chotot.keywords.selenium.Page.*;
import static com.vn.chotot.keywords.selenium.Wait.*;

public class TopHeader extends BasePage {

    final Logger log = Log4jFactory.instance().createClassLogger(getClass());

    @FindBy(xpath = "//a[@class='appWrapper-Header-logoAnchor']")
    private WebElement img_Logo;

    @FindBy(xpath = "//a[contains(.,'Tìm rao vặt')]")
    private WebElement lnk_SearchAds;

    @FindBy(xpath = "//input[@placeholder='Tìm kiếm trên Chợ Tốt']")
    private WebElement input_SearchAdsNew;

    @FindBy(xpath = "//span[@id='btnnotification']")
    private WebElement lnk_Notification;

    @FindBy(xpath = "//*[@class='appWrapper-Layout-rightPanel']//a[contains(.,'Chat')]")
    private WebElement lnk_Chat;

    @FindBy(xpath = "//*[@aria-label='notification']//*[text()='HOẠT ĐỘNG']")
    private WebElement lnk_Activities;

    @FindBy(xpath = "//*[@aria-label='notification']//*[text()='TIN MỚI']")
    private WebElement lnk_News;

    @FindBy(xpath = "//a[text()='Đăng ký / Đăng nhập']")
    private WebElement btn_RegisterOrLogin;

    @FindBy(xpath = "//*[@class='appWrapper-Layout-rightPanel']//a[contains(.,'Tôi bán')]")
    private WebElement lnk_IBuy;

    @FindBy(xpath = "//a[@class='appWrapper-Header-navItemLink' and contains(@href,'shops')]")
    private WebElement lnk_GoToShop;

    @FindBy(xpath = "//a[contains(.,'Cửa hàng')]")
    private WebElement lnk_MyShopELTAndVEH;

    @FindBy(xpath = "//a[@class='appWrapper-Header-primaryButton' and contains(.,'Đăng Tin')]")
    private WebElement lnk_InsertAds;

    @FindBy(xpath = "//a[@class='appWrapper-UserMenuItem-above' and contains(.,'Đăng nhập / Đăng ký')]")
    private WebElement lnk_LoginOrRegister;

    @FindBy(xpath = "//a[@class='appWrapper-Header-menuListItem' and contains(.,'Tin đăng đã lưu')]")
    private WebElement lnk_SavedPost;

    @FindBy(xpath = "//a[@class='appWrapper-Header-menuListItem' and normalize-space(.)='Bạn bè']")
    private WebElement lnk_Friend;

    @FindBy(xpath = "//ol[contains(.,'Bạn bè')]")
    private WebElement lnk_Friend_Text;

    @FindBy(xpath = "//a[@class='appWrapper-Header-menuListItem' and contains(.,'Tìm kiếm đã lưu')]")
    private WebElement lnk_SavedSearch;

    @FindBy(xpath = "//a[@class='appWrapper-Header-menuListItem' and contains(.,'Tạo Cửa hàng/Chuyên trang')]")
    private WebElement lnk_CreateStore;

    @FindBy(xpath = "//a[@class='appWrapper-Header-menuListItem' and normalize-space(.)='Thẻ của tôi']")
    private WebElement lnk_MyCard;

    @FindBy(xpath = "//ol[contains(.,'Quản lý tin rao')]")
    private WebElement lnk_MyCard_Text;

    @FindBy(xpath = "//a[@class=\"appWrapper-Header-menuListItem\" and contains(.,\"Quảng cáo Tốt\")]")
    private WebElement lnk_BestAds;

    @FindBy(xpath = "//a[@class='appWrapper-Header-menuListItem' and normalize-space(.)='Chợ Tốt Ưu đãi']")
    private WebElement lnk_ChoTot;

    @FindBy(xpath = "//a[@class=\"appWrapper-Header-menuListItem\" and contains(.,\"Vòng quay may mắn\")]")
    private WebElement lnk_LuckySpinner;

    @FindBy(xpath = "//a[@class=\"appWrapper-Header-menuListItem\" and contains(.,\"Tải ứng dụng miễn phí\")]")
    private WebElement lnk_FreeDownloadApp;

    @FindBy(xpath = "//a[@class=\"appWrapper-Header-menuListItem\" and contains(.,\"Trợ giúp\")]")
    private WebElement lnk_Help;

    @FindBy(xpath = "//a[@class=\"appWrapper-Header-menuListItem\" and contains(.,\"Tài khoản Đồng Tốt\")]")
    private WebElement lnk_GoodVNDAccount;

    @FindBy(xpath = "//a[@class=\"appWrapper-Header-menuListItem\" and contains(.,\"Lịch sử giao dịch\")]")
    private WebElement lnk_OrderHistory;

    @FindBy(xpath = "//a[@class=\"appWrapper-Header-menuListItem\" and contains(.,\"Cài đặt thông tin\")]")
    private WebElement lnk_SetupInfo;

    @FindBy(xpath = "//a[@id='btnmenuMoreundefined']")
    private WebElement btn_MenuMore;

    @FindBy(xpath = "//a[@class=\"appWrapper-Header-menuListItem\" and contains(.,\"Đăng xuất\")]")
    private WebElement lnk_Logout;

    @FindBy(xpath = "//a[normalize-space(text())='Xem trang cá nhân của bạn']")
    private WebElement lnk_GoToProfile;

    @FindBy(xpath = "//div[contains(@class,'text-center')]/strong")
    private WebElement lbl_InsertAdTitle;

    public void verifyUserCanSelectItemOnTopHeaderWithoutLogin() {
        // Get current page url
        String previousURL = getPageURL(maxWaitTime);

        this.verifySearchAdsDisplayWithoutLogin();

        this.verifyClickNotificationWithoutLogin();

        this.verifyClickIBuyWithoutLogin();
        backToPreviousURL(previousURL);

        this.verifyClickInsertAdWithoutLogin();
        backToPreviousURL(previousURL);

        this.verifyClickLoginOrRegisterWithoutLogin();
        backToPreviousURL(previousURL);

        this.verifyClickSavedPostWithoutLogin();
        backToPreviousURL(previousURL);

        this.verifyClickSavedSearchWithoutLogin();
        backToPreviousURL(previousURL);

        this.verifyClickFriendWithoutLogin();
        backToPreviousURL(previousURL);

        this.verifyClickCreateStoreWithoutLogin();
        backToPreviousURL(previousURL);

        this.verifyClickBestAdsWithoutLogin();
        backToPreviousURL(previousURL);

        this.verifyClickChoTotWithoutLogin();
        backToPreviousURL(previousURL);

        this.verifyClickLuckySpinnerWithoutLogin();
        backToPreviousURL(previousURL);

        this.verifyClickFreeDownloadWithoutLogin();
        backToPreviousURL(previousURL);

        this.verifyClickHelpWithoutLogin();
        backToPreviousURL(previousURL);
    }

    public void verifyUserCanSelectItemOnTopHeaderWhenLogin() {
        // Get current page url
        String previousURL = getPageURL(maxWaitTime);

        this.verifySearchAdsDisplayWhenLogin();

        this.verifyClickNotificationWhenLogin();

        this.verifyClickInsertAdWhenLogin();
        backToPreviousURL(previousURL);

        this.verifyClickSavedPostWhenLogin();
        backToPreviousURL(previousURL);

        this.verifyClickBookmarkWhenLogin();
        backToPreviousURL(previousURL);

        this.verifyClickFriendWhenLogin();
        backToPreviousURL(previousURL);

        this.verifyClickGoodVndAccountWhenLogin();
        backToPreviousURL(previousURL);

        this.verifyClickTransactionHistoryWhenLogin();
        backToPreviousURL(previousURL);

        this.verifyClickMyCardWhenLogin();
        backToPreviousURL(previousURL);

        this.verifyClickBestAdsWhenLogin();
        backToPreviousURL(previousURL);

        this.verifyClickChoTotWhenLogin();
        backToPreviousURL(previousURL);

        this.verifyClickLuckySpinnerWhenLogin();
        backToPreviousURL(previousURL);

        this.verifyClickFreeDownloadWhenLogin();
        backToPreviousURL(previousURL);

        this.verifyClickHelpWhenLogin();
        backToPreviousURL(previousURL);

        this.verifyClickSetupInfoWhenLogin();
        backToPreviousURL(previousURL);

        this.verifyClickLogoutWhenLogin();
    }

    // ***************** For login ************************************
    private void openHomePage() {
        new HomePage().openHomePage();
    }

    public void verifySearchAdsDisplayWhenLogin() {
        log.debug("Wait input_SearchAds display");
        waitForElementVisible(input_SearchAdsNew, maxWaitTime);
        waitForElementStaleness(input_SearchAdsNew, maxWaitTime);
    }

    public void verifyClickNotificationWhenLogin() {
        log.debug("Click on lnk_Notification");
        openHomePage();
        waitForElementVisible(lnk_Notification, maxWaitTime);
        moveAndClick(lnk_Notification, maxWaitTime);
        verifyElementVisible(lnk_Activities, maxWaitTime, FailureHandling.CONTINUE_ON_FAILURE);
        verifyElementVisible(lnk_News, maxWaitTime, FailureHandling.CONTINUE_ON_FAILURE);
    }

    public void verifyClickIBuyWhenLogin() {
        log.debug("Click on lnk_IBuy");
        moveAndClickJS(lnk_IBuy, maxWaitTime);
        verifyPageTitleContainsText("Quản lý tin", maxWaitTime);
    }

    public void verifyClickInsertAdWhenLogin() {
        log.debug("Click on lnk_InsertAds");
        clickInsertAds();
        verifyPageTitleContainsText("Đăng tin", maxWaitTime);
    }

    public void verifyClickProfileLinkWhenLogin() {
        moveAndClick(btn_MenuMore, maxWaitTime * 5);
        log.debug("Click on lnk_GoToProfile");
        moveAndClick(lnk_GoToProfile, maxWaitTime * 5);
        waitForElementStaleness(lnk_GoToProfile, maxWaitTime);
        verifyPageTitleContainsText("Trang cá nhân", maxWaitTime * 2);
    }

    public void verifyClickSavedPostWhenLogin() {
        openHomePage();
        moveAndClick(btn_MenuMore, maxWaitTime * 5);
        log.debug("Click on lnk_SavedPost");
        moveAndClickJS(lnk_SavedPost, maxWaitTime * 5);
        waitForElementStaleness(lnk_SavedPost, maxWaitTime);
        verifyPageTitleContainsText("Tin đăng đã lưu", maxWaitTime);
    }

    public void verifyClickBookmarkWhenLogin() {
        openHomePage();
        moveAndClick(btn_MenuMore, maxWaitTime * 5);
        log.debug("Click on lnk_SavedSearch");
        moveAndClick(lnk_SavedSearch, maxWaitTime * 5);
        waitForElementStaleness(lnk_SavedSearch, maxWaitTime);
        verifyPageTitleContainsText("Tìm kiếm đã lưu", maxWaitTime);
    }

    public void verifyClickFriendWhenLogin() {
        clickJS(btn_MenuMore, maxWaitTime * 5);
        log.debug("Click on lnk_Friend");
        waitForElementVisible(lnk_Friend, maxWaitTime * 5);
        clickJS(lnk_Friend, maxWaitTime);
        verifyElementVisible(lnk_Friend_Text, maxWaitTime * 5);
    }

    public void verifyClickGoodVndAccountWhenLogin() {
        moveAndClick(btn_MenuMore, maxWaitTime * 5);
        log.debug("Click on lnk_GoodVNDAccount");
        moveAndClick(lnk_GoodVNDAccount, maxWaitTime * 5);
        waitForElementStaleness(lnk_GoodVNDAccount, maxWaitTime);
        verifyPageTitleContainsText("Tài khoản Đồng Tốt", maxWaitTime);
    }

    public void verifyClickTransactionHistoryWhenLogin() {
        moveAndClick(btn_MenuMore, maxWaitTime * 5);
        log.debug("Click on lnk_OrderHistory");
        moveAndClick(lnk_OrderHistory, maxWaitTime * 5);
        waitForElementStaleness(lnk_OrderHistory, maxWaitTime);
        verifyPageURLContainsText("history", maxWaitTime);
    }

    public void verifyClickMyCardWhenLogin() {
        moveAndClick(btn_MenuMore, maxWaitTime * 5);
        log.debug("Click on lnk_MyCard");
        moveAndClick(lnk_MyCard, maxWaitTime);
        waitForElementStaleness(lnk_MyCard, maxWaitTime);
        verifyElementVisible(lnk_MyCard_Text, maxWaitTime);
    }

    public void verifyClickCreateStoreWhenLogin() {
        moveAndClick(btn_MenuMore, maxWaitTime * 5);
        log.debug("Click on lnk_CreateStore");
        moveAndClick(lnk_CreateStore, maxWaitTime * 5);
        waitForElementStaleness(lnk_CreateStore, maxWaitTime);
        verifyPageURLContainsText("Shopcreate", maxWaitTime);
    }

    public void verifyClickBestAdsWhenLogin() {
        moveAndClick(btn_MenuMore, maxWaitTime * 5);
        log.debug("Click on lnk_BestAds");
        moveAndClick(lnk_BestAds, maxWaitTime * 5);
        waitForElementStaleness(lnk_BestAds, maxWaitTime);
        verifyPageTitleContainsText("Quản lý quảng cáo", maxWaitTime);
    }

    public void verifyClickChoTotWhenLogin() {
        moveAndClick(btn_MenuMore, maxWaitTime * 5);
        log.debug("Click on lnk_ChoTot");
        moveAndClick(lnk_ChoTot, maxWaitTime * 5);
        waitForElementStaleness(lnk_ChoTot, maxWaitTime);
        verifyPageTitleContainsText("Chợ Tốt ưu đãi", maxWaitTime);
    }

    public void verifyClickLuckySpinnerWhenLogin() {
        moveAndClick(btn_MenuMore, maxWaitTime * 5);
        log.debug("Click on lnk_LuckySpinner");
        moveAndClick(lnk_LuckySpinner, maxWaitTime * 5);
        waitForElementStaleness(lnk_LuckySpinner, maxWaitTime);
        verifyPageTitleContainsText("Vòng Quay May Mắn", maxWaitTime);
    }

    public void verifyClickFreeDownloadWhenLogin() {
        moveAndClick(btn_MenuMore, maxWaitTime * 5);
        log.debug("Click on lnk_FreeDownloadApp");
        moveAndClick(lnk_FreeDownloadApp, maxWaitTime * 5);
        waitForElementStaleness(lnk_FreeDownloadApp, maxWaitTime);
        verifyTextPresent("Android và iOS", false, maxWaitTime, FailureHandling.STOP_ON_FAILURE);
    }

    public void verifyClickHelpWhenLogin() {
        moveAndClick(btn_MenuMore, maxWaitTime * 5);
        log.debug("Click on lnk_Help");
        moveAndClick(lnk_Help, maxWaitTime * 5);
        waitForElementStaleness(lnk_Help, maxWaitTime);
        new MainHeader().verifyBreabcrumbItem("Trung tâm trợ giúp");
    }

    public void verifyClickSetupInfoWhenLogin() {
        moveAndClick(btn_MenuMore, maxWaitTime * 5);
        log.debug("Click on lnk_SetupInfo");
        moveAndClick(lnk_SetupInfo, maxWaitTime * 5);
        waitForElementStaleness(lnk_SetupInfo, maxWaitTime);
        verifyPageTitleContainsText("Trang cá nhân", maxWaitTime);
    }

    public void verifyClickLogoutWhenLogin() {
        moveAndClick(btn_MenuMore, maxWaitTime * 5);
        log.debug("Click on lnk_Logout");
        moveAndClick(lnk_Logout, maxWaitTime * 5);
        waitForElementStaleness(lnk_Logout, maxWaitTime);
        new HomePage().verifyLoginNowDisplayed();
        moveAndClick(btn_MenuMore, maxWaitTime * 5);
        verifyElementNotPresent(lnk_GoToProfile, maxWaitTime * 5, FailureHandling.STOP_ON_FAILURE);
    }

    // ***************** For Non-login ************************************

    public void verifySearchAdsDisplayWithoutLogin() {
        log.debug("Wait input_SearchAds display");
        waitForElementStaleness(input_SearchAdsNew, maxWaitTime);
        waitForElementStaleness(input_SearchAdsNew, maxWaitTime);
    }

    public void verifyClickNotificationWithoutLogin() {
        log.debug("Click on lnk_Notification");
        waitForElementStaleness(lnk_Notification, minWaitTime);
        moveAndClick(lnk_Notification, maxWaitTime * 5);
        waitForElementStaleness(lnk_Notification, maxWaitTime);
        verifyElementVisible(lnk_Activities, maxWaitTime);
        verifyElementVisible(lnk_News, maxWaitTime);
        verifyElementVisible(btn_RegisterOrLogin, maxWaitTime);
    }

    public void verifyClickIBuyWithoutLogin() {
        log.debug("Click on lnk_IBuy");
        moveAndClickJS(lnk_IBuy, maxWaitTime);
        waitForElementStaleness(lnk_IBuy, maxWaitTime);
        verifyPageTitleContainsText("Đăng Nhập", maxWaitTime);
    }

    public void verifyClickInsertAdWithoutLogin() {
        log.debug("Click on lnk_InsertAds");
        clickInsertAds();
        verifyPageTitleContainsText("Đăng tin", maxWaitTime * 5);
    }

    public void verifyClickLoginOrRegisterWithoutLogin() {
        moveAndClick(btn_MenuMore, maxWaitTime * 5);
        log.debug("Click on lnk_LoginOrRegister");
        moveAndClick(lnk_LoginOrRegister, maxWaitTime * 5);
        waitForElementStaleness(lnk_LoginOrRegister, maxWaitTime);
        verifyPageTitleContainsText("Đăng Nhập", maxWaitTime);
    }

    public void verifyClickSavedPostWithoutLogin() {
        moveAndClick(btn_MenuMore, maxWaitTime * 5);
        log.debug("Click on lnk_SavedPost");
        moveAndClick(lnk_SavedPost, maxWaitTime * 5);
        verifyPageTitleContainsText("Đăng Nhập", maxWaitTime);
    }

    public void verifyClickSavedSearchWithoutLogin() {
        moveAndClick(btn_MenuMore, maxWaitTime * 5);
        log.debug("Click on lnk_SavedSearch");
        moveAndClick(lnk_SavedSearch, maxWaitTime * 5);
        verifyPageTitleContainsText("Đăng Nhập", maxWaitTime);
    }

    public void verifyClickFriendWithoutLogin() {
        moveAndClick(btn_MenuMore, maxWaitTime * 5);
        log.debug("Click on lnk_Friend");
        moveAndClick(lnk_Friend, maxWaitTime * 5);
        verifyPageTitleContainsText("Đăng Nhập", maxWaitTime);
    }

    public void verifyClickCreateStoreWithoutLogin() {
        moveAndClick(btn_MenuMore, maxWaitTime * 5);
        log.debug("Click on lnk_CreateStore");
        moveAndClick(lnk_CreateStore, maxWaitTime * 5);
        verifyPageTitleContainsText("Đăng Nhập", maxWaitTime);
    }

    public void verifyClickBestAdsWithoutLogin() {
        moveAndClick(btn_MenuMore, maxWaitTime * 5);
        log.debug("Click on lnk_BestAds");
        moveAndClick(lnk_BestAds, maxWaitTime * 5);
        verifyPageTitleContainsText("Đăng Nhập", maxWaitTime);
    }

    public void verifyClickChoTotWithoutLogin() {
        moveAndClick(btn_MenuMore, maxWaitTime * 5);
        log.debug("Click on lnk_ChoTot");
        moveAndClick(lnk_ChoTot, maxWaitTime * 5);
        verifyPageTitleContainsText("Chợ Tốt ưu đãi", maxWaitTime);
    }

    public void verifyClickLuckySpinnerWithoutLogin() {
        moveAndClick(btn_MenuMore, maxWaitTime * 5);
        log.debug("Click on lnk_LuckySpinner");
        moveAndClick(lnk_LuckySpinner, maxWaitTime * 5);
        verifyPageTitleContainsText("Vòng Quay May Mắn", maxWaitTime);
    }

    public void verifyClickFreeDownloadWithoutLogin() {
        moveAndClick(btn_MenuMore, maxWaitTime * 5);
        log.debug("Click on lnk_FreeDownloadApp");
        moveAndClick(lnk_FreeDownloadApp, maxWaitTime * 5);
        verifyTextPresent("Android và iOS", false, maxWaitTime, FailureHandling.STOP_ON_FAILURE);
    }

    public void verifyClickHelpWithoutLogin() {
        moveAndClick(btn_MenuMore, maxWaitTime * 5);
        log.debug("Click on lnk_Help");
        moveAndClick(lnk_Help, maxWaitTime * 5);
        new MainHeader().verifyBreabcrumbItem("Trung tâm trợ giúp");
    }

    public void clickChatLink() {
        moveAndClickJS(lnk_Chat, maxWaitTime * 5);

        // Wait for page loaded
        waitForLoadingPageIconDismissed(maxWaitTime, maxWaitTime * 10);
    }

    public void clickIBuy() {
        waitForLoadingPageIconDismissed(minWaitTime, maxWaitTime);
        clickJS(lnk_IBuy, maxWaitTime * 2);
        // Wait for page loaded
        waitForLoadingPageIconDismissed(maxWaitTime, maxWaitTime * 5);
    }

    public void clickGoToShop() {
        clickJS(lnk_GoToShop, maxWaitTime * 5);
        // Wait for page loaded
        waitForLoadingPageIconDismissed(maxWaitTime, maxWaitTime * 10);
    }

    public void clickInsertAds() {
        moveAndClickJS(lnk_InsertAds, maxWaitTime * 3);
        waitForElementStaleness(lnk_InsertAds, maxWaitTime);

        // Wait for page loaded
        waitForLoadingPageIconDismissed(maxWaitTime, maxWaitTime * 5);
    }

    public void clickOrderHistory() {
        // Click expand menu
        moveAndClickJS(btn_MenuMore, maxWaitTime * 5);

        // Click order history
        moveAndClickJS(lnk_OrderHistory, maxWaitTime * 5);

        // Wait for page loaded
        waitForLoadingPageIconDismissed(maxWaitTime, maxWaitTime * 10);
    }

    public void clickLoginOrRegister() {
        waitForElementVisible(btn_MenuMore, maxWaitTime * 3);
        moveAndClickJS(btn_MenuMore, maxWaitTime);
        log.debug("Click on lnk_LoginOrRegister");
        moveAndClick(lnk_LoginOrRegister, maxWaitTime);
        verifyPageTitleContainsText("Đăng Nhập", maxWaitTime);
    }

    public void verifyLoginSuccessfully() {
        waitForElementVisible(btn_MenuMore, maxWaitTime);
        moveAndClickJS(btn_MenuMore, maxWaitTime);
        verifyElementVisible(lnk_GoToProfile, maxWaitTime * 2);
    }

    public void verifyInsertAdPageTitle(){
        verifyElementVisible(lbl_InsertAdTitle, maxWaitTime * 3);
    }
}
