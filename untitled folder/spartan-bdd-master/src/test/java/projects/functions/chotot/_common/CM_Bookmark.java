package projects.functions.chotot._common;

import desktop.base.BaseTest;
import desktop.components.TopHeader;
import desktop.configuration.SaveConfig;
import desktop.pages.AdsListing.AdsListing;
import desktop.pages.BookmarkPage;
import desktop.pages.HomePage;

public class CM_Bookmark extends BaseTest {
    CM_Login cm_login;
    TopHeader topHeader;
    HomePage homePage;
    AdsListing adsListing;

    public void goToBookmarkPage(boolean isLogged) {
        // Initiate object instances
        cm_login = new CM_Login();
        topHeader = new TopHeader();

        // Login to system if user not login
        if (!isLogged) {
            cm_login.doLogin();
        }

        // Click Saved search page on top header
        topHeader.verifyClickBookmarkWhenLogin();
    }

    public void goToBookmarkAtListing() {
        // Initiate object instances
        adsListing = new AdsListing();

        // Verify element on ad listing display
        adsListing.verifyCommonElementOnAdListing();

        // Click Search text box on listing
        adsListing.openBookmarkOnListing();
    }

    public void BookmarkRandom(Boolean isGridView) {
        // Initiate object instances
        adsListing = new AdsListing();

        // Select a random Region
        adsListing.selectRandomRegion();

        // Verify tooltip save search display and close
        adsListing.closeTooltipBookmark();

        // Select a random Cate
        adsListing.selectRandomCate();

        // Verify element on ad listing display
        adsListing.verifyCommonElementOnAdListing();

        // View ad
        if (isGridView) {
            adsListing.viewAdInGirdView();
        }

        // Save current search
        adsListing.clickBookmarkButtonOnTopPage();

        // Check popup saved search success display
        adsListing.verifyPopupSaveSuccessDisplay();
    }

    public String BookmarkByIndex(int index) {
        // Initiate object instances
        homePage = new HomePage();
        adsListing = new AdsListing();

        if (index == 0) {
            //Click start search in SaveSearchPage
            new BookmarkPage().clickStartSearch();

            //Verify tooltip save search display and close
            adsListing.closeTooltipBookmark();
        } else {
            //Go to home page
            homePage.openHomePage();

            //Random choose a category to save search
            homePage.selectCategory(index);
        }

        // Verify element on ad listing display
        adsListing.verifyCommonElementOnAdListing();

        // Save current search
        return adsListing.clickBookmarkButtonOnTopPage();
    }

    public void BookmarkOverLimit() {
        // Initiate object instances
        adsListing = new AdsListing();

        for (int i = 0; i <= SaveConfig.totalSearchCanSave; i++) {
            if (i < SaveConfig.totalSearchCanSave) {
                BookmarkByIndex(i);
                adsListing.verifyPopupSaveSuccessDisplay();
            } else {
                BookmarkByIndex(i + 1);
                adsListing.verifyPopupBookmarkOverLimitDisplay();
            }
        }
    }

    public void removeBookmarkInListing() {
        // Initiate object instances
        adsListing = new AdsListing();

        // Verify all common element display on ad listing
        adsListing.verifyCommonElementOnAdListing();

        // Verify this search are saved
        adsListing.verifyIconMarkedDisplay();

        // Verify remove Saved search success
        adsListing.clickBookmarkButtonOnTopPage();
        adsListing.verifyPopupRemoveSavedSuccessDisplay();
    }

    public void BookmarkWithButtonBottomPage() {
        // Initiate object instances
        adsListing = new AdsListing();

        // Select a random Region
        adsListing.selectRandomRegion();

        // Verify tooltip save search display and close
        adsListing.closeTooltipBookmark();

        // Select a random Cate
        adsListing.selectRandomCate();

        // Verify element on ad listing display
        adsListing.verifyCommonElementOnAdListing();

        // Search a value
        adsListing.input_Search("du lich");

        // Verify Search Text Box
        adsListing.verifyBookmarkButtonBottomPageDisplay();

        // Save current search
        adsListing.clickBookmarkButtonOnBottomPage();

        // Check popup saved search success display
        adsListing.verifyPopupSaveSuccessDisplay();
    }
}
