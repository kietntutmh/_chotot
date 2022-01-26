package desktop.pages;

import com.vn.chotot.base.BasePage;
import com.vn.chotot.exception.FailureHandling;
import com.vn.chotot.logger.Log4jFactory;
import desktop.components.TopHeader;
import desktop.dialog.SelectRegion;
import desktop.pages.AdsListing.AdsListing;
import desktop.pages.AdsView.ContactPanel;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import projects.functions.chotot._common.CM_Login;

import java.util.List;

import static com.vn.chotot.configuration.WaitTime.maxWaitTime;
import static com.vn.chotot.configuration.WaitTime.minWaitTime;
import static com.vn.chotot.keywords.selenium.Action.*;
import static com.vn.chotot.keywords.selenium.Element.clickJSRandomElementOnList;
import static com.vn.chotot.keywords.selenium.Element.verifyElementVisible;
import static com.vn.chotot.keywords.selenium.Page.*;
import static com.vn.chotot.keywords.selenium.Utils.verifyNotMatch;
import static com.vn.chotot.keywords.selenium.Wait.*;
import static desktop.configuration.HomeConfig.homeURL;

public class SaveAdPage extends BasePage {
    // Initiate object instances
    final CM_Login cm_login = new CM_Login();
    final HomePage homePage = new HomePage();
    final AdsListing adListing = new AdsListing();
    ContactPanel contactPanel = new ContactPanel();
    Logger log = Log4jFactory.instance().createClassLogger(getClass());

    @FindBy(xpath = "//*[contains(text(),'Bắt đầu tìm kiếm')]")
    private WebElement btn_SearchAd;

    @FindBy(xpath = "//div[@class='ctTooltipContent' and text()='Lưu tin rao này để xem lại sau']")
    private WebElement tooltips;

    @FindBy(xpath = "(//button[@id='btn_save_ad'])[last()]")
    private WebElement icon_SaveAd_Profile;

    @FindBy(xpath = "//button[@id='btn_save_ad' and text()='Lưu tin']")
    private WebElement btn_SaveAd;

    @FindBy(xpath = "//button[@id='btn_save_ad' and text()='Đã lưu']")
    private WebElement btn_UnSaveAd;

    @FindBy(xpath = "//*[@class='SaveAd__Wrapper-gjQNKa dcwIrQ' and @id='btn_save_ad']")
    private List<WebElement> iconSaveAd;

    @FindBy(xpath = "//*[contains(@class,\"SavedAdList__SaveAdWrapper\")]//button")
    private List<WebElement> btn_SaveAd_MoreTab;

    @FindBy(xpath = "//div[@id='boxListCate']//a")
    private List<WebElement> lst_Category;

    @FindBy(xpath = "//h1[@itemprop=\"name\"]")
    private WebElement txt_Title;

    @FindBy(xpath = "//p[@class='alert alert-warning']")
    private WebElement alertWarning;

    @FindBy(xpath = "(//*[contains(@class,\"styles__FlexDiv\")])[last()]//button")
    private WebElement btn_Profile;

    @FindBy(xpath = "//*[.='Tin đã được đưa vào danh sách theo dõi']")
    private WebElement msg_SaveAd;

    @FindBy(xpath = "//*[.='Bạn chỉ có thể theo dõi tối đa là 50 tin']")
    private WebElement msg_TotalOfNumberSaveAd;

    @FindBy(xpath = "//*[.='Đã huỷ theo dõi tin này.']")
    private WebElement msg_UnSaveAd;

    @FindBy(xpath = "(//article[@class='itemAdshop'])[last()]")
    private WebElement article_ItemAdShop;

    public void selectRandomCategory() {
        // Login to system
        cm_login.doLogin();

        // Select category
        homePage.selectCategory(3);
    }

    public void clickIconSaveAdInAdviewNonLogin() {
        // open homepage
        homePage.openHomePage();

        // Select category
        homePage.selectCategory(3);

        // Select random AdsListing
        adListing.selectRandomAdOnListing();

        // check adview display
        if (waitForPageTitleContainsText(
                "Không thể tìm thấy tin đăng .Rất tiếc tin đăng có thể đã hết hạn hoặc đã ẩn khỏi hệ thống.",
                maxWaitTime)) {
            // Back to home page
            backToPreviousURL(homeURL);
            // Select category
            homePage.selectCategory(3);
            // Select random AdsListing
            adListing.selectRandomAdOnListing();
        }

        // scroll page to subject ad
        scrollPage(txt_Title);
        clickAction(tooltips);
        waitForElementStaleness(btn_SaveAd, maxWaitTime);
        clickJS(btn_SaveAd);
    }

    public void clickIconSaveAdInListingNonLogin() {
        homePage.openHomePage();
        homePage.selectCategory(3);
        // Close pop-up region
        new SelectRegion().clickCloseButton(minWaitTime);

        // click icon save ad in listing
        waitForAllElementVisible(iconSaveAd, maxWaitTime);
        clickJSRandomElementOnList(iconSaveAd);
        verifyPageTitleContainsText(
                "Chợ Tốt - Chợ của người Việt - Mua bán rao vặt đơn giản & miễn phí - Đăng Nhập",
                maxWaitTime);
    }

    public void clickIconSaveAdInProfileNonLogin() {
        // open homepage
        homePage.openHomePage();

        // Select category
        homePage.selectCategory(3);

        // Select random AdsListing
        adListing.selectRandomAdOnListing();

        // check adview display
        if (waitForPageTitleContainsText(
                "Không thể tìm thấy tin đăng .Rất tiếc tin đăng có thể đã hết hạn hoặc đã ẩn khỏi hệ thống.",
                maxWaitTime)) {
            // Back to home page
            backToPreviousURL(homeURL);
            // Select category
            homePage.selectCategory(3);
            // Select random AdsListing
            adListing.selectRandomAdOnListing();
        }
        // Click button xem trang
        clickJS(btn_Profile);

        // Swich To Another Window and click icon saveAd
        switchToAnotherWindow();

        // Go to adview and click save-ad icon
        waitForElementStaleness(icon_SaveAd_Profile, maxWaitTime);
        clickJS(icon_SaveAd_Profile);

        // Display login page
        verifyPageTitleContainsText(
                "Chợ Tốt - Chợ của người Việt - Mua bán rao vặt đơn giản & miễn phí - Đăng Nhập",
                maxWaitTime);
    }

    public void clickIconSaveAdInAdview() {
        // Select random AdsListing
        adListing.selectRandomAdOnListing();

        // check adview display
        if (waitForPageTitleContainsText(
                "Không thể tìm thấy tin đăng .Rất tiếc tin đăng có thể đã hết hạn hoặc đã ẩn khỏi hệ thống.",
                maxWaitTime)) {
            // Back to home page
            backToPreviousURL(homeURL);
            // Select category
            homePage.selectCategory(3);
            // Select random AdsListing
            adListing.selectRandomAdOnListing();
        }

        // scroll page to subject ad
        scrollPage(txt_Title);

        // Close tooltips
        clickAction(tooltips);
        waitForElementStaleness(btn_SaveAd, maxWaitTime);

        // click save ad
        clickJS(btn_SaveAd);
        verifyElementVisible(msg_SaveAd, maxWaitTime);
    }

    public void clickIconSaveAdInListing() {
        // Close pop-up region
        new SelectRegion().clickCloseButton(minWaitTime);

        // click icon save ad in listing
        waitForAllElementVisible(iconSaveAd, maxWaitTime);
        //        selectElementOnListByIndex( iconSaveAd, 5, FailureHandling.STOP_ON_FAILURE );
        clickJSRandomElementOnList(iconSaveAd);
        verifyElementVisible(msg_SaveAd, maxWaitTime);
    }

    public void clickIconSaveAdInProfile() {
        // Select random AdsListing
        adListing.selectRandomAdOnListing();

        // check adview display
        if (waitForPageTitleContainsText(
                "Không thể tìm thấy tin đăng .Rất tiếc tin đăng có thể đã hết hạn hoặc đã ẩn khỏi hệ thống.",
                maxWaitTime)) {
            // Back to home page
            backToPreviousURL(homeURL);
            // Select category
            homePage.selectCategory(3);
            // Select random AdsListing
            adListing.selectRandomAdOnListing();
        }

        // Click button xem trang
        clickJS(btn_Profile);

        // Swich To Another Window and click icon saveAd
        switchToAnotherWindow();

        // Go to adview and click save-ad icon
        waitForElementStaleness(icon_SaveAd_Profile, maxWaitTime);
        clickJS(icon_SaveAd_Profile);

        // Verify message display when click icon save ad
        verifyElementVisible(msg_SaveAd, maxWaitTime * 2);
    }

    public void checkUnSaveAdInAdview() {
        // Select random AdsListing
        adListing.selectRandomAdOnListing();

        // check adview display
        if (waitForPageTitleContainsText(
                "Không thể tìm thấy tin đăng .Rất tiếc tin đăng có thể đã hết hạn hoặc đã ẩn khỏi hệ thống.",
                maxWaitTime)) {
            // Back to home page
            backToPreviousURL(homeURL);
            // Select category
            homePage.selectCategory(3);
            // Select random AdsListing
            adListing.selectRandomAdOnListing();
        }

        // scroll page to subject ad
        scrollPage(txt_Title);

        // Close tooltips
        clickAction(tooltips);

        // wait - click - verify msg after click icon save-ad
        waitForElementStaleness(btn_SaveAd, maxWaitTime);
        clickJS(btn_SaveAd);
        verifyElementVisible(btn_UnSaveAd, maxWaitTime);

        // Unsave-ad and check msg
        clickJS(btn_UnSaveAd);
        verifyElementVisible(btn_SaveAd, 10);
    }

    public void checkUnSaveAdInListing() {
        // Close pop-up region
        new SelectRegion().clickCloseButton(minWaitTime);

        // click icon save ad in listing
        waitForAllElementVisible(iconSaveAd, maxWaitTime);
        clickJSRandomElementOnList(iconSaveAd);
        verifyElementVisible(msg_SaveAd, maxWaitTime);

        // go to button more
        TopHeader topHeader = new TopHeader();
        topHeader.verifyClickSavedPostWhenLogin();

        // Click unSaveAd and refresh page
        waitForAllElementVisible(btn_SaveAd_MoreTab, maxWaitTime * 2);
        int numberSaveAd = btn_SaveAd_MoreTab.size();
        if (numberSaveAd > 0) {
            for (int i = numberSaveAd - 1; i >= 0; i--) {
                clickJS(btn_SaveAd_MoreTab.get(i));
                refreshPage();
                waitForAllElementVisible(btn_SaveAd_MoreTab, maxWaitTime * 2);
            }
        }
        verifyElementVisible(btn_SearchAd, maxWaitTime * 2);
    }

    public void checkUnSaveAdInProfile() {
        // Select random AdsListing
        adListing.selectRandomAdOnListing();

        // check adview display
        if (waitForPageTitleContainsText(
                "Không thể tìm thấy tin đăng .Rất tiếc tin đăng có thể đã hết hạn hoặc đã ẩn khỏi hệ thống.",
                maxWaitTime)) {
            // Back to home page
            backToPreviousURL(homeURL);
            // Select category
            homePage.selectCategory(3);
            // Select random AdsListing
            adListing.selectRandomAdOnListing();
        }

        // Click button xem trang
        clickJS(btn_Profile);

        // Swich To Another Window and click icon saveAd
        switchToAnotherWindow();

        // Go to adview and click save-ad icon
        clickJS(icon_SaveAd_Profile);

        // go to button more
        TopHeader topHeader = new TopHeader();
        topHeader.verifyClickSavedPostWhenLogin();

        // Click unSaveAd and refresh page
        waitForAllElementVisible(btn_SaveAd_MoreTab, maxWaitTime * 2);
        int numberSaveAd = btn_SaveAd_MoreTab.size();
        if (numberSaveAd > 0) {
            //            while (numberSaveAd>0){
            //                clickJS( btn_SaveAd_MoreTab.get( numberSaveAd-1));
            //                refreshPage();
            //                waitForAllElementVisible( btn_SaveAd_MoreTab,maxWaitTime*2 );
            //                numberSaveAd--;
            //            }

            for (int i = numberSaveAd - 1; i >= 0; i--) {
                clickJS(btn_SaveAd_MoreTab.get(i));
                refreshPage();
                waitForAllElementVisible(btn_SaveAd_MoreTab, maxWaitTime * 2);
            }
            verifyElementVisible(btn_SearchAd, maxWaitTime * 2);
        }
    }

    public void checkTotalNumberOfSaveAd() {
        // Close pop-up region
        new SelectRegion().clickCloseButton(minWaitTime);

        // click icon save ad in listing
        waitForAllElementVisible(iconSaveAd, maxWaitTime);
        clickJSRandomElementOnList(iconSaveAd);
        verifyElementVisible(msg_TotalOfNumberSaveAd, maxWaitTime);
    }

    public void checkSaveAdsuccessfully() {
        // Login
        cm_login.doLogin();

        // go to button more
        TopHeader topHeader = new TopHeader();
        topHeader.verifyClickSavedPostWhenLogin();

        // Get number of save ad
        String numberSaveAd = String.valueOf(iconSaveAd.size());

        // Back homeURL
        backToPreviousURL(homeURL);

        // Select category
        homePage.selectCategory(3);

        // Close pop-up region
        new SelectRegion().clickCloseButton(minWaitTime);

        // click icon save ad in listing
        waitForAllElementVisible(iconSaveAd, maxWaitTime);
        clickJSRandomElementOnList(iconSaveAd);
        verifyElementVisible(msg_SaveAd, maxWaitTime);

        // Get number after click icon save ad in listing
        String numberSaveAdNew = String.valueOf(iconSaveAd.size());

        // Verify SaveAd successfully
        verifyNotMatch(numberSaveAdNew, numberSaveAd, false, FailureHandling.STOP_ON_FAILURE);
    }
}
