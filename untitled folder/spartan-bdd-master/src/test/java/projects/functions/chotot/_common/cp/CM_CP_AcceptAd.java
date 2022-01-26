package projects.functions.chotot._common.cp;

import desktop.base.BaseTest;
import desktop.pages.AdsView.AdView;
import desktop.pages.CP.ControlPanel.AdToListing;
import desktop.pages.CP.ControlPanel.SearchForAd;

import static com.vn.chotot.keywords.selenium.Page.closeAndSwitchToWindowHandle;
import static com.vn.chotot.keywords.selenium.Page.getWindowHandle;

public class CM_CP_AcceptAd extends BaseTest {

    private static final int adIndex = 0;

    public String acceptNewAd(String adID) {
        // Initialize objects
        CM_CP_Login cm_cp_login = new CM_CP_Login();
        SearchForAd searchForAd = new SearchForAd();
        AdToListing adToListing = new AdToListing();
        AdView adItem = new AdView();

        // Do login
        cm_cp_login.loginAndGoToSearchAd();

        // Search ad by id
        String adSubject = searchForAd.doSimpleSearch(adID, "ad_id");

        // Click on the first result
        searchForAd.verifyAdStatusByIndex("UNREVIEWED", adIndex);
        searchForAd.selectSearchResultByIndex(adIndex);

        // Accept ad
        adToListing.selectAdReviewAction("Accepted", null);

        // Back to Search For Ad page
        adToListing.backToSearchForAdPage();

        // Verify status is PUBLISHED
        searchForAd.verifyAdStatusByIndex("PUBLISHED", adIndex);

        // Go to ad view page
        searchForAd.selectAdListIDByIndex(adIndex);

        // Verify ad subject displayed on ad view and ad listing
        adItem.verifyAdSubjectDisplayed(adSubject);

        return adSubject;
    }

    public String acceptNewAd_ListingFreePTY(String adID) {
        // Initialize objects
        CM_CP_Login cm_cp_login = new CM_CP_Login();
        SearchForAd searchForAd = new SearchForAd();
        AdToListing adToListing = new AdToListing();
        AdView adItem = new AdView();

        // Do login
        cm_cp_login.loginAndGoToSearchAd();

        // Search ad by id
        String adSubject = searchForAd.doSimpleSearch(adID, "ad_id");

        // Set index for new ad
        int index = 0;

        searchForAd.selectSearchResultByIndex(index);

        // Accept ad
        adToListing.selectAdReviewAction("Accepted", null);

        // Back to Search For Ad page
        adToListing.backToSearchForAdPage();

        // Go to ad view page
        searchForAd.selectAdListIDByIndex(index);

        return adSubject;
    }

    public String acceptNewAdAction(String adID) {
        // Initialize objects
        SearchForAd searchForAd = new SearchForAd();
        AdToListing adToListing = new AdToListing();

        // Search ad by id
        String adSubject = searchForAd.doSimpleSearch(adID, "ad_id");

        // Get current window handle
        String searchWindow = getWindowHandle();

        // Click on the first result
        searchForAd.selectSearchResultByIndex(adIndex);

        // Accept ad
        adToListing.selectAdReviewAction("Accepted", null);

        // Back to Search For Ad page
        closeAndSwitchToWindowHandle(searchWindow);

        return adSubject;
    }

    public void loginAndVerifyUnreviewStatus(String adID) {
        // Initialize objects
        CM_CP_Login cm_cp_login = new CM_CP_Login();
        SearchForAd searchForAd = new SearchForAd();

        // Do login
        cm_cp_login.loginAndGoToSearchAd();

        // Search ad by id
        searchForAd.doSimpleSearch(adID, "ad_id");

        // Click on the first result
        searchForAd.verifyAdStatusByIndex("UNREVIEWED", adIndex);
    }

    public void loginAndVerifyUnpaidStatus(String adID) {
        // Initialize objects
        CM_CP_Login cm_cp_login = new CM_CP_Login();
        SearchForAd searchForAd = new SearchForAd();

        // Do login
        cm_cp_login.loginAndGoToSearchAd();

        // Search ad by id
        searchForAd.doSimpleSearch(adID, "ad_id");

        // Click on the first result
        searchForAd.verifyAdStatusByIndex("UNPAID", adIndex);
    }

    public void verifyUnreviewStatus(String adID) {
        // Initialize objects
        SearchForAd searchForAd = new SearchForAd();

        // Search ad by id
        searchForAd.doSimpleSearch(adID, "ad_id");

        // Click on the first result
        searchForAd.verifyAdStatusByIndex("UNREVIEWED", adIndex);
    }
}
