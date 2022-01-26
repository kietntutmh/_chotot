package desktop.pages;

import com.vn.chotot.base.BasePage;
import com.vn.chotot.exception.FailureHandling;
import com.vn.chotot.logger.Log4jFactory;
import desktop.components.Popup;
import desktop.dialog.SelectRegion;
import desktop.pages.AdsListing.AdsListing;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.vn.chotot.configuration.WaitTime.maxWaitTime;
import static com.vn.chotot.configuration.WaitTime.minWaitTime;
import static com.vn.chotot.keywords.selenium.Action.*;
import static com.vn.chotot.keywords.selenium.Element.*;
import static com.vn.chotot.keywords.selenium.Page.*;
import static com.vn.chotot.keywords.selenium.Wait.*;
import static desktop.configuration.HomeConfig.homeURL;

public class HomePage extends BasePage {

    final Logger log = Log4jFactory.instance().createClassLogger(getClass());

    @FindBy(xpath = "//a[contains(.,\"Đăng nhập\") and contains(@href,\"accounts\")]")
    private WebElement btn_LoginNow;

    @FindBy(xpath = "//a[@title='Đăng ký tài khoản']")
    private WebElement btn_Register;

    @FindBy(xpath = "//div[contains(@class,'HomePage__WrapperBanner')]//a[@itemprop='url']")
    private List<WebElement> lst_BannerItem;

    @FindBy(xpath = "//li[contains(@class, 'Categories__Item')]//a")
    private List<WebElement> lst_Category;

    @FindBy(xpath = "//p[@id='contentSeoCat']//p/a")
    private List<WebElement> lst_HyperlinkText;

    @FindBy(xpath = "//li[@itemscope]/a")
    private List<WebElement> lst_CommonKeyword;

    @FindBy(xpath = "//header//div[@class='appWrapper-Layout-container']//a[@id='btnMenuMore']")
    private WebElement btn_SeeMore;

    @FindBy(xpath = "//*[@class='slick-slide-link']")
    private List<WebElement> lst_Shortcut;

    @FindBy(xpath = "//button[@class='slick-arrow slick-next']")
    private WebElement btn_NextShortcut;

    @FindBy(xpath = "//button[@class='slick-arrow slick-prev']")
    private WebElement btnPrevShortcut;

    @FindBy(xpath = "//*[@class='slick-slide-link' and @aria-label='Chợ Tốt Ưu Đãi']")
    private WebElement lnk_Reward;

    @FindBy(xpath = "//*[@class='slick-slide-link' and @aria-label='Nạp Đồng Tốt']")
    private WebElement lnk_GoodPoint;

    @FindBy(xpath = "//*[@class='slick-slide-link' and @aria-label='Vòng quay may mắn']")
    private WebElement lnk_LuckyWheel;

    @FindBy(xpath = "//*[@class='slick-slide-link' and @aria-label='Tin rao đã lưu']")
    private WebElement lnk_AdSaved;

    @FindBy(xpath = "//*[@class='slick-slide-link' and @aria-label='Tìm kiếm đã lưu']")
    private WebElement lnk_SearchSaved;

    public void openHomePage() {
        openURL(homeURL);
        new Popup().closePopup(minWaitTime);
    }

    public void clickLoginNow() {
        waitForElementStaleness(btn_LoginNow, minWaitTime);
        waitForElementVisible(btn_LoginNow, maxWaitTime * 5);
        clickJS(btn_LoginNow);
        new Popup().closePopup(minWaitTime);
    }

    public void verifyLoginNowDisplayed() {
        verifyElementVisible(btn_LoginNow, maxWaitTime * 5);
    }

    public void clickRegister() {
        moveAndClickJS(btn_Register, maxWaitTime * 3);
    }

    public void verifyUserCanSelectBannerItem() {
        waitForAllElementPresent(lst_BannerItem, maxWaitTime);
        String hrefBannerItem, bannerContent;
        for (WebElement webElement : lst_BannerItem) {
            // LOG STEP: Get name of item to print log. Not related to testing steps
            hrefBannerItem = webElement.getAttribute("href");
            if (hrefBannerItem.contains("?"))
                bannerContent =
                        hrefBannerItem
                                .substring(hrefBannerItem.lastIndexOf("/") + 1, hrefBannerItem.lastIndexOf("?"))
                                .replace("-", " ")
                                .toLowerCase();
            else
                bannerContent =
                        hrefBannerItem
                                .substring(hrefBannerItem.lastIndexOf("/") + 1)
                                .replace("-", " ")
                                .toLowerCase();
            log.info("Banner item: " + bannerContent);

            // TESTING STEPs -----------------------
            clickJS(webElement);

            // Wait for page load
            waitForLoadingIconDismissed(maxWaitTime, maxWaitTime * 3, false);

            // Close popup if displayed
            new Popup().closePopup(minWaitTime);

            // Close popup if displayed
            new SelectRegion().clickCloseButton(minWaitTime);
            new Popup().closePopup(minWaitTime);

            // Verify new page displayed
            verifyPageTitleChanged(homeURL, maxWaitTime, FailureHandling.CONTINUE_ON_FAILURE);

            // Back to home page
            backToPreviousURL(homeURL);
        }
    }

    public void verifyUserCanSelectCategory() {
        waitForAllElementVisible(lst_Category, maxWaitTime * 2);
        String categoryName;
        for (int i= 0; i< lst_Category.size(); i++) {
            WebElement webElement = lst_Category.get(i);
            // LOG STEP: Get name of item to print log. Not ralated to testing steps
            categoryName = getText(webElement, minWaitTime).toLowerCase();
            log.info("Category item: " + categoryName);

            // TESTING STEPS:
            moveAndClickJS(webElement);

            // Wait for page load
            waitForLoadingIconDismissed(maxWaitTime, maxWaitTime * 3, false);

            // Close popup if displayed
            new Popup().closePopup(minWaitTime);

            // Close popup if displayed
            new SelectRegion().clickCloseButton(minWaitTime);
            new Popup().closePopup(minWaitTime);

            // Verify common elements displayed
            new AdsListing().verifyCommonElementOnAdListing();

            // Back to home page
            backToPreviousURL(homeURL);

            // Wait for page load
            waitForAllElementVisible(lst_BannerItem, maxWaitTime);
        }
    }

    public void verifyUserCanSelectCommonKeyword() {
        waitForAllElementVisible(lst_CommonKeyword, maxWaitTime);
        if (getNumberElement(lst_CommonKeyword) > 0) {
            String selectedKeyword;
            WebElement webElement;
            // Verify selected keyword display after clicking
            for (int i=0;i<lst_CommonKeyword.size(); i++) {
                // Get keyword
                webElement = lst_CommonKeyword.get(i);
                selectedKeyword = getText(webElement, minWaitTime).toLowerCase();
                selectedKeyword = selectedKeyword.replace(",", "");

                // Click keyword
                log.info("Selected keyword: " + selectedKeyword);
                moveAndClickJS(webElement);

                // Wait for page load
                waitForLoadingIconDismissed(maxWaitTime, maxWaitTime * 3, false);

                // Close popup if displayed
                new Popup().closePopup(minWaitTime);

                // Verify common elements displayed
                new AdsListing().verifyCommonElementOnAdListing();

                // Back to home page
                backToPreviousURL(homeURL);
            }
        }
    }

    public void verifyUserCanSelectHyperlinkText() {
        waitForAllElementVisible(lst_HyperlinkText, maxWaitTime);
        String textValue;
        // Verify selected text display after clicking
        for (int i = 0; i < lst_HyperlinkText.size() - 1; i++) {
            // Get hyperlink textOLD
            textValue = getText(lst_HyperlinkText.get(i), minWaitTime).toLowerCase();
            textValue = textValue.replace(",", "");

            // Click on See more button
            moveAndClickJS(btn_SeeMore);

            // Click on hyperlink text
            log.info("Hyperlink text: " + textValue);
            moveAndClickJS(lst_HyperlinkText.get(i), maxWaitTime);

            // Wait for page load
            waitForLoadingIconDismissed(maxWaitTime, maxWaitTime * 3, false);

            // Close popup if displayed
            new Popup().closePopup(minWaitTime);

            // Verify common elements displayed
            new AdsListing().verifyCommonElementOnAdListing();

            // Back to home page
            backToPreviousURL(homeURL);
        }
    }

    public void selectRandomCategory() {
        waitForAllElementVisible(lst_Category, maxWaitTime);
        clickJSRandomElementOnList(lst_Category);
    }

    public void selectCategoryByIndex(int index) {
        waitForAllElementVisible(lst_Category, maxWaitTime);
        clickJS(lst_Category.get(index), maxWaitTime);
    }

    public void selectCategory(int index) {
        waitForAllElementVisible(lst_Category, maxWaitTime);
        selectElementOnListByIndex(lst_Category, index, FailureHandling.STOP_ON_FAILURE);
    }

    public void verifyClickRewardShortcutWithoutLogin() {
        log.debug("Click on lnk_Reward");
        waitForElementVisible(lnk_Reward, maxWaitTime);
        moveAndClick(lnk_Reward, maxWaitTime);
        verifyPageTitleContainsText("Chợ Tốt ưu đãi", maxWaitTime);
    }

    public void verifyClickGoodPointShortcutWithoutLogin() {
        log.debug("Click on lnk_GoodPoint");
        waitForElementVisible(lnk_GoodPoint, maxWaitTime);
        moveAndClick(lnk_GoodPoint, maxWaitTime);
        verifyPageTitleContainsText("Đăng Nhập", maxWaitTime);
    }

    public void verifyClickLuckyWheelShortcutWithoutLogin() {
        log.debug("Click on lnk_LuckyWheel");
        waitForElementVisible(lnk_LuckyWheel, maxWaitTime);
        moveAndClick(lnk_LuckyWheel, maxWaitTime);
        verifyPageTitleContainsText("Vòng Quay May Mắn", maxWaitTime);
    }

    public void verifyClickAdSavedShortcutWithoutLogin() {
        log.debug("Click on lnk_AdSaved");
        waitForElementVisible(lnk_AdSaved, maxWaitTime);
        moveAndClick(lnk_AdSaved, maxWaitTime);
        verifyPageTitleContainsText("Đăng Nhập", maxWaitTime);
    }

    public void verifyClickSearchSavedShortcutWithoutLogin() {
        log.debug("Click on lnk_SearchSaved");
        waitForElementVisible(lnk_SearchSaved, maxWaitTime);
        moveAndClick(lnk_SearchSaved, maxWaitTime);
        verifyPageTitleContainsText("Đăng Nhập", maxWaitTime);
    }

    public void verifyUserCanSelectShortcutItemWithoutLogin() {
        // Get current page url
        String previousURL = getPageURL(maxWaitTime);

        this.verifyClickRewardShortcutWithoutLogin();
        backToPreviousURL(previousURL);

        this.verifyClickGoodPointShortcutWithoutLogin();
        backToPreviousURL(previousURL);

        this.verifyClickLuckyWheelShortcutWithoutLogin();
        backToPreviousURL(previousURL);

        this.verifyClickAdSavedShortcutWithoutLogin();
        backToPreviousURL(previousURL);

        this.verifyClickSearchSavedShortcutWithoutLogin();
        backToPreviousURL(previousURL);
    }

    // ***************** For Login ************************************
    public void verifyClickRewardShortcutWhenLogin() {
        log.debug("Click on lnk_Reward");
        waitForElementVisible(lnk_Reward, maxWaitTime);
        moveAndClick(lnk_Reward, maxWaitTime);
        verifyPageTitleContainsText("Chợ Tốt ưu đãi", maxWaitTime);
    }

    public void verifyClickGoodPointShortcutWhenLogin() {
        log.debug("Click on lnk_GoodPoint");
        waitForElementVisible(lnk_GoodPoint, maxWaitTime);
        moveAndClick(lnk_GoodPoint, maxWaitTime);
        verifyPageTitleContainsText("Tài khoản Đồng Tốt", maxWaitTime);
    }

    public void verifyClickLuckyWheelShortcutWhenLogin() {
        log.debug("Click on lnk_LuckyWheel");
        waitForElementVisible(lnk_LuckyWheel, maxWaitTime);
        moveAndClick(lnk_LuckyWheel, maxWaitTime);
        verifyPageTitleContainsText("Vòng Quay May Mắn", maxWaitTime);
    }

    public void verifyClickAdSavedShortcutWhenLogin() {
        log.debug("Click on lnk_AdSaved");
        waitForElementVisible(lnk_AdSaved, maxWaitTime);
        moveAndClick(lnk_AdSaved, maxWaitTime);
        verifyPageTitleContainsText("Tin đăng đã lưu", maxWaitTime);
    }

    public void verifyClickSearchSavedShortcutWhenLogin() {
        log.debug("Click on lnk_SearchSaved");
        waitForElementVisible(lnk_SearchSaved, maxWaitTime);
        moveAndClick(lnk_SearchSaved, maxWaitTime);
        verifyPageTitleContainsText("Tìm kiếm đã lưu", maxWaitTime);
    }

    public void verifyUserCanSelectShortcutItemWhenLogin() {
        // Get current page url
        String previousURL = getPageURL(maxWaitTime);

        this.verifyClickRewardShortcutWhenLogin();
        backToPreviousURL(previousURL);

        this.verifyClickGoodPointShortcutWhenLogin();
        backToPreviousURL(previousURL);

        this.verifyClickLuckyWheelShortcutWhenLogin();
        backToPreviousURL(previousURL);

        this.verifyClickAdSavedShortcutWhenLogin();
        backToPreviousURL(previousURL);

        this.verifyClickSearchSavedShortcutWhenLogin();
        backToPreviousURL(previousURL);
    }
}
