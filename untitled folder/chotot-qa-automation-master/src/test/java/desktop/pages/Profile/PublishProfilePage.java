package desktop.pages.Profile;

import com.vn.chotot.base.BasePage;
import com.vn.chotot.exception.FailureHandling;
import com.vn.chotot.exception.StepFailedException;
import com.vn.chotot.logger.Log4jFactory;
import desktop.configuration.ProfileConfig;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.vn.chotot.configuration.WaitTime.maxWaitTime;
import static com.vn.chotot.exception.ExceptionHandler.exceptionHandler;
import static com.vn.chotot.keywords.selenium.Action.*;
import static com.vn.chotot.keywords.selenium.Element.verifyAllElementVisible;
import static com.vn.chotot.keywords.selenium.Element.verifyElementVisible;
import static com.vn.chotot.keywords.selenium.Page.verifyPageTitleContainsText;
import static com.vn.chotot.keywords.selenium.Utils.verifyMatch;
import static com.vn.chotot.keywords.selenium.Wait.*;

public class PublishProfilePage extends BasePage {
    final Logger log = Log4jFactory.instance().createClassLogger(getClass());

    @FindBy(xpath = "//div[@class='InfoWrapper']//span[@class='name']")
    private WebElement lbl_Name;

    @FindBy(
            xpath =
                    "//div[@class='FollowRow']//a[@href='https://www.chotot.org/dashboard/follow/followed']")
    private WebElement lbl_Follower;

    @FindBy(
            xpath =
                    "//div[@class='FollowRow']//a[@href='https://www.chotot.org/dashboard/follow/following']")
    private WebElement lbl_Following;

    @FindBy(xpath = "//div[@class='UltiRow']//button[@class='MainFunctionButton EditProfile']")
    private WebElement btn_EditProfile;

    @FindBy(xpath = "//div[@class='UltiRow']//button[@class='circleButton']")
    private WebElement btn_More;

    @FindBy(xpath = "//div[@role='presentation']//div[@role='menu']")
    private WebElement btn_CopyLink;

    @FindBy(xpath = "//div[@class='AvatarWrapper']//img")
    private WebElement img_Avatar;

    @FindBy(xpath = "//div[contains(@class, 'ratingInfo')]")
    private WebElement lbl_RatingInfo;

    @FindBy(xpath = "//span[not(contains(@class,'icon'))]")
    private List<WebElement> lst_TextInfo;

    @FindBy(xpath = "//img[@src='/user/static/img/contact/facebook_default.png']")
    private WebElement icon_FacebookNotConnect;

    @FindBy(xpath = "//img[@src='/user/static/img/contact/facebook_active.png']")
    private WebElement icon_ConnectedFacebook;

    @FindBy(xpath = "//img[@src='/user/static/img/contact/address_default.png']")
    private WebElement icon_LocationNotFound;

    @FindBy(xpath = "//img[@src='/user/static/img/contact/address_active.png']")
    private WebElement icon_HaveLocation;

    @FindBy(xpath = "//img[@src='/user/static/img/contact/phone_default.png']")
    private WebElement icon_PhoneNotVerify;

    @FindBy(xpath = "//img[@src='/user/static/img/contact/phone_active.png']")
    private WebElement icon_PhoneVerified;

    @FindBy(xpath = "//img[@src='/user/static/img/contact/email_default.png']")
    private WebElement icon_EmailNotVerify;

    @FindBy(xpath = "//img[@src='/user/static/img/contact/email_active.png']")
    private WebElement icon_EmailVerified;

    @FindBy(xpath = "//h4[@class='TitleHeading']")
    private WebElement lbl_TitleShop;

    @FindBy(
            xpath =
                    "//div[@class='PaperContainer']//div[@class='info-box left-panel']//div[@class='avatar']")
    private WebElement img_ShopAvatar;

    @FindBy(
            xpath =
                    "//div[@class='PaperContainer']//div[@class='container PaperShopWrapper']//div[contains(@class, 'item right-panel')]")
    private WebElement btn_GoToShop;

    @FindBy(xpath = "//div[@class='PaperWrapper']//h4[@class='TitleHeading']//span")
    private WebElement lbl_TitleAdsOnSale;

    @FindBy(xpath = "//div[@class='ctAdItemContainer']//img")
    private List<WebElement> lst_AdsOnSale;

    @FindBy(xpath = "//div[@class='notfound']//div[@class='alert alert-warning']")
    private WebElement lbl_NonAd;

    @FindBy(xpath = "//a[@tabindex='0']//div//span[contains(text(), 'Đăng tin')]")
    private WebElement btn_InsertAd;

    @FindBy(xpath = "//div//a[contains(text(), 'Xem tất cả')]")
    private List<WebElement> lst_SeeAll;

    @FindBy()
    private WebElement lbl_AdsSold;

    public void verifyAvatarDisplay() {
        verifyElementVisible(img_Avatar, maxWaitTime);
    }

    public void verifyUserNameDisplay() {
        verifyElementVisible(lbl_Name, maxWaitTime);
    }

    public void verifyFollowerDisplay() {
        verifyElementVisible(lbl_Follower, maxWaitTime);
    }

    public void verifyFollowingDisplay() {
        verifyElementVisible(lbl_Following, maxWaitTime);
    }

    public void verifyButtonEditProfileDisplay() {
        verifyElementVisible(btn_EditProfile, maxWaitTime);
    }

    public void verifyRatingInfoDisplay() {
        verifyElementVisible(lbl_RatingInfo, maxWaitTime);
    }

    public void verifyProfileInfoDisplay() {
        verifyAllElementVisible(lst_TextInfo, maxWaitTime, FailureHandling.STOP_ON_FAILURE);
    }

    public Boolean verifyFacebookIconActiveOrNotActive() {
        try {
            verifyElementVisible(icon_ConnectedFacebook, maxWaitTime);
            log.debug("Facebook connected icon display");
            return true;
        } catch (Exception ex) {
            verifyElementVisible(icon_FacebookNotConnect, maxWaitTime);
            log.debug("Facebook not connect icon display");
            return false;
        }
    }

    public Boolean verifyLocationIconActiveOrNotActive() {
        try {
            verifyElementVisible(icon_HaveLocation, maxWaitTime);
            log.debug("Location verified icon display");
            return true;
        } catch (Exception ex) {
            verifyElementVisible(icon_LocationNotFound, maxWaitTime);
            log.debug("Location not found icon display");
            return false;
        }
    }

    public Boolean verifyPhoneIconActiveOtNotActive() {
        try {
            verifyElementVisible(icon_PhoneVerified, maxWaitTime);
            log.debug("Phone verified icon display");
            return true;
        } catch (Exception ex) {
            verifyElementVisible(icon_PhoneNotVerify, maxWaitTime);
            log.debug("Phone not verify icon display");
            return false;
        }
    }

    public Boolean verifyEmailIconActiveOrNotActive() {
        try {
            verifyElementVisible(icon_EmailVerified, maxWaitTime);
            log.debug("Email verified icon display");
            return true;
        } catch (Exception ex) {
            verifyElementVisible(icon_EmailNotVerify, maxWaitTime);
            log.debug("Email not verify icon display");
            return false;
        }
    }

    public void verifyShopNameDisplay() {
        verifyElementVisible(lbl_TitleShop, maxWaitTime);
    }

    public void verifyShopAvatarDisplay() {
        verifyElementVisible(img_ShopAvatar, maxWaitTime);
    }

    public void verifyButtonGoToShopDisplay() {
        verifyElementVisible(btn_GoToShop, maxWaitTime);
    }

    public void verifyTitleAdsOnSale() {
        verifyElementVisible(lbl_TitleAdsOnSale, maxWaitTime);
    }

    public void verifyAdsOnSaleDisplay() {
        verifyAllElementVisible(lst_AdsOnSale, maxWaitTime, FailureHandling.STOP_ON_FAILURE);
    }

    public void verifyButtonSeeAllDisplay() {
        verifyAllElementVisible(lst_SeeAll, maxWaitTime, FailureHandling.STOP_ON_FAILURE);
    }

    /**
     * This function is to click on "Chinh sua trang ca nhan" button to navigate Profile Page
     * Pre-condition: Click on TopHeader/More Click on TopHeader/GotoProfile Last Updated: 2019/06/20
     * Change Log:
     */
    public void clickToOpenProfileEditPage() {
        moveAndClick(btn_EditProfile, maxWaitTime);
        waitForElementStaleness(btn_EditProfile, maxWaitTime);
        verifyPageTitleContainsText("Trang cá nhân", maxWaitTime);
    }

    public void verifyNumberAdsOnSale() {
        if (lst_AdsOnSale.size() > ProfileConfig.totalAdsOnSale) {
            exceptionHandler(
                    new StepFailedException(
                            "Actual ads on sale "
                                    + lst_AdsOnSale.size()
                                    + " is OVER expect ads on sale "
                                    + ProfileConfig.totalAdsOnSale),
                    FailureHandling.STOP_ON_FAILURE);
        }
    }

    public void verifyNameMatchSettingProfile(String settingName) {
        waitForElementVisible(lbl_Name, maxWaitTime);
        verifyMatch(lbl_Name.getText(), settingName, false, FailureHandling.STOP_ON_FAILURE);
    }

    public void clickToOpenInsertAd() {
        waitForElementVisible(btn_InsertAd, maxWaitTime);
        clickAction(btn_InsertAd);
    }

    public void clickViewAllAdsOnSale() {
        waitForAllElementVisible(lst_AdsOnSale, maxWaitTime);
        clickAction(lst_AdsOnSale.get(0));
    }

    public void clickToCopyLink() {
        clickAction(btn_More);
        waitForElementVisible(btn_CopyLink, maxWaitTime);
        clickJS(btn_CopyLink);
    }

    public void verifyAvatarMatchWithAvatarAtSettingProfile(String urlAvatar) {
        waitForElementVisible(img_Avatar, maxWaitTime);
        verifyMatch(urlAvatar, img_Avatar.getAttribute("scr"), false, FailureHandling.STOP_ON_FAILURE);
    }

    public void clickToOpenShopPage() {
        waitForElementVisible(btn_GoToShop, maxWaitTime);
        moveAndClick(btn_GoToShop);
    }

    public void clickToOpenSeeAllPage() {
        waitForAllElementVisible(lst_SeeAll, maxWaitTime);
        moveAndClick(lst_SeeAll.get(0));
    }

    public void verifyAvatarShopMatchWithAvatarShopAtSettingProfile(String urlAvatarShop) {
        waitForElementVisible(img_ShopAvatar, maxWaitTime);
        verifyMatch(
                urlAvatarShop, img_ShopAvatar.getAttribute("scr"), false, FailureHandling.STOP_ON_FAILURE);
    }
}
