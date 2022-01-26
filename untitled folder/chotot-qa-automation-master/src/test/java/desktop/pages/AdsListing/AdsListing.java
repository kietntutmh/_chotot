package desktop.pages.AdsListing;

import com.vn.chotot.base.BasePage;
import com.vn.chotot.exception.FailureHandling;
import com.vn.chotot.exception.StepFailedException;
import com.vn.chotot.logger.Log4jFactory;
import desktop.components.MainHeader;
import desktop.dialog.SelectRegion;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.vn.chotot.configuration.WaitTime.maxWaitTime;
import static com.vn.chotot.configuration.WaitTime.minWaitTime;
import static com.vn.chotot.exception.ExceptionHandler.exceptionHandler;
import static com.vn.chotot.keywords.selenium.Action.*;
import static com.vn.chotot.keywords.selenium.Element.*;
import static com.vn.chotot.keywords.selenium.Page.*;
import static com.vn.chotot.keywords.selenium.Utils.*;
import static com.vn.chotot.keywords.selenium.Wait.*;
import static projects.functions.chotot._common.api.CM_API_CheckStatusCodeImage.verifyStatusCodeOfImages;

public class AdsListing extends BasePage {

    final Logger log = Log4jFactory.instance().createClassLogger(getClass());

    @FindBy(xpath = "//input[@id=\"__inputItemProps\"]")
    private WebElement input_Search;

    @FindBy(xpath = "//button[@aria-label='Bookmark Button']")
    private WebElement btn_BookmarkTopPage;

    @FindBy(xpath = "//button[contains(text(), 'Lưu tìm kiếm này')]")
    private WebElement btn_BookmarkBottomPage;

    @FindBy(xpath = "//*[@class='alert alert-warning']")
    private WebElement lbl_NoResult;

    @FindBy(xpath = "//ul/li/a[contains(@href,\"page\")]")
    private List<WebElement> lst_PageNumeber;

    @FindBy(xpath = "//div[not(contains(.,\"Tin rao tương tự\")) and contains(@class,\"pgjTolSNq4\")]//li/a/div[not(contains(@class,\"ctAdListingBody\"))]//h3")
    private List<WebElement> lst_AdListing;

    @FindBy(xpath = "//div[not(contains(@class,'suggestAdsWrapper'))]/div[contains(@class,'ctAdlisting') and not(contains(.,'Tin ưu tiên'))]")
    private List<WebElement> lst_AdListingXe;

    @FindBy(xpath = "//div[not(contains(.,\"Tin rao tương tự\")) and contains(@class,\"pgjTolSNq4\")]//li/a/div[@class=\"ctAdListingBody\"]//h3")
    private List<WebElement> lst_StickyAds;

    @FindBy(xpath = "//div[not(contains(.,\"Tin rao tương tự\")) and contains(@class,\"pgjTolSNq4\")]//li/a[//img and //h3[string-length() > 0]]")
    private List<WebElement> lst_ValidAdListing;

    @FindBy(xpath = "//button[contains(@title, 'Dạng lưới')]")
    private WebElement btn_GridView;

    @FindBy(xpath = "//button[contains(@title, 'Dạng danh sách')]")
    private WebElement btn_ListView;

    @FindBy(xpath = "//button[contains(@class,'btn btn-default btn-lg col-xs-5')]")
    private List<WebElement> lst_RegionCate;

    @FindBy(xpath = "//*[contains(@class, 'showUp') and contains(text(),'Bạn sẽ nhận được thông báo khi có tin rao mới cho tìm kiếm này')]")
    private WebElement lbl_BookmarkSuccess;

    @FindBy(xpath = "//*[contains(@class, 'showUp') and contains(text(),'Bạn đã huỷ theo dõi tìm kiếm này')]")
    private WebElement lbl_RemoveBookmarkSuccess;

    @FindBy(xpath = "//*[contains(@class, 'showUp') and contains(text(),'Bạn đã huỷ theo dõi tìm kiếm này')]")
    private WebElement lbl_BookmarkDuplicate;

    @FindBy(xpath = "//*[contains(@class, 'showUp SnackBar__Message') and contains(text(),'Bạn không thể lưu nhiều hơn 5 tìm kiếm')]")
    private WebElement lbl_BookmarkOverLimit;

    @FindBy(xpath = "//div[contains(@class, 'SearchSuggestion__HeadingRightContent')]//b[.='Tìm kiếm đã lưu']")
    private WebElement lbl_BookmarkTitle;

    @FindBy(xpath = "//a[contains(@class, 'SearchSuggestion__RightButton') and contains(text(), 'Cài đặt')]")
    private WebElement btn_Setting;

    @FindBy(xpath = "//div[contains(@class, 'SearchSuggestion__HeadingRightContent')]//b[.='Tìm kiếm gần đây']")
    private WebElement lbl_RecentSearches;

    @FindBy(xpath = "//a[contains(@class, 'SearchSuggestion__RightButton') and contains(text(), 'Xoá')]")
    private WebElement btn_DeleteRecentSearches;

    @FindBy(xpath = "//div[contains(@class, 'SaveSearchItem__Wrapper')]")
    private List<WebElement> lst_BookmarkItem;

    @FindBy(xpath = "//div//span[contains(text(), 'Tìm kiếm từ khoá')]")
    private WebElement btn_SearchKeyWord;

    @FindBy(xpath = "//*[@id='Path-2']")
    private List<WebElement> lst_MarkedDisplay;

    @FindBy(xpath = "//li[@itemprop = 'itemListElement' and not(contains(@class,'breadcrumb'))]//a[@itemprop='item']")
    private List<WebElement> lst_ItemSelect;

    @FindBy(xpath = "//button[@class='close']")
    private WebElement btn_CloseListItem;

    @FindBy(xpath = "//div[@class='ctTooltipContent']")
    private WebElement lbl_TooltipBookmark;

    @FindBy(xpath = "//div[@class='ctTooltipClose']")
    private WebElement btn_CloseTooltip;

    @FindBy(xpath = "//button[contains(@class, 'btn btn-default btn-block')]//strong[contains(text(),'BỎ ĐIỀU KIỆN LỌC')]")
    private WebElement btn_UnFilter;

    @FindBy(xpath = "//a[contains(@class, 'btn btn-default btn-block')]//strong[contains(text(),'TÌM TRÊN CHOTOT.COM')]")
    private WebElement btn_FindOnChoTot;

    public void selectRandomAdOnListing() {
        new SelectRegion().clickCloseButton(maxWaitTime);
        waitForAllElementVisible(lst_AdListing, maxWaitTime);
        clickJSRandomElementOnList(lst_AdListing);
    }

    public void verifyAdsListingShownProperly() {
        // Wait for page load
        delayStep(maxWaitTime * 2);

        // Check total current and valid ads are equals
        int totalValidAds = getNumberElement(lst_ValidAdListing);
        int totalAds = getNumberElement(lst_AdListing);
        int totalStickyAds = getNumberElement(lst_StickyAds);
        verifyEquals(totalAds + totalStickyAds, totalValidAds, FailureHandling.CONTINUE_ON_FAILURE);
    }

    public void verifyPagingDisplayed(String pageTitle) {
        // Check page number > 0
        waitForAllElementPresent(lst_PageNumeber, minWaitTime);
        int totalPage = getNumberElement(lst_PageNumeber);
        if (totalPage == 0) {
            int numberAd = getNumberElement(lst_AdListing);
            if (numberAd == 0)
                verifyElementVisible(lbl_NoResult, minWaitTime, FailureHandling.CONTINUE_ON_FAILURE);
            else if (numberAd > 20 && !pageTitle.contains("thú cưng"))
                exceptionHandler(
                        new StepFailedException("The page number is NOT present with " + numberAd + " ads"),
                        FailureHandling.CONTINUE_ON_FAILURE);
            else if (numberAd > 35 && pageTitle.contains("thú cưng"))
                exceptionHandler(
                        new StepFailedException("The page number is NOT present with " + numberAd + " ads"),
                        FailureHandling.CONTINUE_ON_FAILURE);
        } else {
            verifyGreater(totalPage, 0, FailureHandling.CONTINUE_ON_FAILURE);
        }
    }

    public void verifySearchTextBoxDisplayed() {
        verifyElementVisible(input_Search, maxWaitTime * 6);
    }

    public void verifyCommonElementOnAdListing() {
        // Get page title
        String pageTitle = getPageTitile(maxWaitTime);

        // Verify main content, search, listing, paging displayed properly
        if (!pageTitle.equalsIgnoreCase("Vòng Quay May Mắn")) {
            new MainHeader().verifyBreabcrumbItemsDisplayed();
            verifySearchTextBoxDisplayed();
            verifyAdsListingShownProperly();
            verifyPagingDisplayed(pageTitle);
        }

        // Check images loaded successfully
        verifyStatusCodeOfImages();
    }

    public void verifyAdSubjectDisplayed(String adSubject) {
        // Wait for loading data
        for (int i = 0; i < 60; i++) {
            if (verifyTextPresent(adSubject, true, maxWaitTime, FailureHandling.WARNING)) return;
            else {
                refreshPage();
                delayStep(maxWaitTime * 3);
            }
        }

        // Get page url
        String listingURL = getPageURL(maxWaitTime);
        exceptionHandler(
                new StepFailedException(
                        "\nAd subject \""
                                + adSubject
                                + "\" is NOT displayed on listing page \""
                                + listingURL
                                + "\""),
                FailureHandling.STOP_ON_FAILURE);
    }

    public void verifyBookmarkButtonTopPageDisplay() {
        verifyElementVisible(btn_BookmarkTopPage, maxWaitTime * 5);
    }

    public void verifyBookmarkButtonBottomPageDisplay() {
        verifyElementVisible(btn_BookmarkBottomPage, maxWaitTime * 5);
    }

    public void verifyGridViewButtonDisplay() {
        verifyElementVisible(btn_GridView, maxWaitTime);
    }

    public void verifyListViewButtonDisplay() {
        verifyElementVisible(btn_ListView, maxWaitTime);
    }

    public void verifyPopupSaveSuccessDisplay() {
        verifyElementVisible(lbl_BookmarkSuccess, minWaitTime);
        log.debug("Popup display");
    }

    public void verifyPopupRemoveSavedSuccessDisplay() {
        verifyElementVisible(lbl_RemoveBookmarkSuccess, minWaitTime);
    }

    public void verifyPopupSaveDuplicateDisplay() {
        verifyElementVisible(lbl_BookmarkDuplicate, minWaitTime);
    }

    public void verifyPopupBookmarkOverLimitDisplay() {
        verifyElementVisible(lbl_BookmarkOverLimit, minWaitTime);
    }

    public void verifyIconMarkedDisplay() {
        waitForAllElementVisible(lst_MarkedDisplay, maxWaitTime);
    }

    public String getListingRegionInfo() {
        waitForElementStaleness(lst_RegionCate.get(0), maxWaitTime * 2);
        return getText(lst_RegionCate.get(0), maxWaitTime);
    }

    public String getListingCategoryInfo() {
        waitForElementStaleness(lst_RegionCate.get(1), maxWaitTime * 2);
        return getText(lst_RegionCate.get(1), maxWaitTime);
    }

    public void selectRandomRegion() {
        if (!waitForAllElementVisible(lst_ItemSelect, maxWaitTime)) {
            clickJS(lst_RegionCate.get(0), maxWaitTime);
            waitForAllElementVisible(lst_ItemSelect, maxWaitTime);
        }
        clickRandomElementOnList(lst_ItemSelect);
        if (waitForAllElementVisible(lst_ItemSelect, maxWaitTime)) {
            clickRandomElementOnList(lst_ItemSelect);
        }
        if (waitForAllElementVisible(lst_ItemSelect, maxWaitTime)) {
            clickRandomElementOnList(lst_ItemSelect);
        }

        new SelectRegion().clickCloseButton(minWaitTime);
    }

    public void selectRandomCate() {
        if (waitForElementVisible(lst_RegionCate.get(0), minWaitTime)) {
            clickJS(lst_RegionCate.get(1));
            waitForAllElementVisible(lst_ItemSelect, minWaitTime);
        }
        clickRandomElementOnList(lst_ItemSelect);
        lst_ItemSelect.clear();
        if (waitForAllElementVisible(lst_ItemSelect, minWaitTime)) {
            waitForElementStaleness(lst_ItemSelect.get(0), minWaitTime);
            clickRandomElementOnList(lst_ItemSelect);
        }

        new SelectRegion().clickCloseButton(minWaitTime);
    }

    public String clickBookmarkButtonOnTopPage() {
        new SelectRegion().clickCloseButton(minWaitTime);

        verifyBookmarkButtonTopPageDisplay();
        clickJS(btn_BookmarkTopPage);
        return getPageTitile(maxWaitTime * 5);
    }

    public void clickBookmarkButtonOnBottomPage() {
        new SelectRegion().clickCloseButton(minWaitTime);

        verifyBookmarkButtonBottomPageDisplay();
        clickJS(btn_BookmarkBottomPage);
        getPageTitile(maxWaitTime * 5);
    }

    public void viewAdInGirdView() {
        verifyGridViewButtonDisplay();
        clickJS(btn_GridView);
    }

    public void viewAdInListView() {
        verifyListViewButtonDisplay();
        clickJS(btn_ListView);
    }

    public int verifyDisplayBookmkarkOnSearchTextBox() {
        log.debug("Click on input_Search");
        moveAndDoubleClick(input_Search);
        verifyAllElementVisible(lst_BookmarkItem, maxWaitTime * 5, FailureHandling.STOP_ON_FAILURE);

        return lst_BookmarkItem.size();
    }

    public void openBookmarkOnListing() {
        int totalBookmark = verifyDisplayBookmkarkOnSearchTextBox();
        if (totalBookmark > 0) {
            clickRandomElementOnList(lst_BookmarkItem);
        }
    }

    public void verifyTitleMatch(String oldTitle) {
        String currentTitle = getPageTitile(maxWaitTime * 5);
        verifyMatch(oldTitle, currentTitle, false, FailureHandling.STOP_ON_FAILURE);
    }

    public void input_Search(String value) {
        new SelectRegion().clickCloseButton(minWaitTime);
        verifySearchTextBoxDisplayed();
        setText(input_Search, value);
        moveAndClick(btn_SearchKeyWord, maxWaitTime);
    }

    public void closeTooltipBookmark() {
        if (waitForElementVisible(lbl_TooltipBookmark, minWaitTime)) {
            clickJS(btn_CloseTooltip, maxWaitTime);
        }
    }
}