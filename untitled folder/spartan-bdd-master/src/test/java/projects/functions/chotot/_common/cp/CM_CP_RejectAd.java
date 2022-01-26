package projects.functions.chotot._common.cp;

import desktop.base.BaseTest;
import desktop.pages.CP.ControlPanel.AdToListing;
import desktop.pages.CP.ControlPanel.SearchForAd;

public class CM_CP_RejectAd extends BaseTest {

    public String rejectNewAd(String adID) {
        // Initialize objects
        CM_CP_Login cm_cp_login = new CM_CP_Login();
        SearchForAd searchForAd = new SearchForAd();
        AdToListing adToListing = new AdToListing();

        // Do login
        cm_cp_login.loginAndGoToSearchAd();

        // Search ad by id
        String adSubject = searchForAd.doSimpleSearch(adID, "ad_id");

        // Set index for new ad
        int index = 0;

        // Click on the first result
        searchForAd.verifyAdStatusByIndex("UNREVIEWED", index);
        searchForAd.selectSearchResultByIndex(index);

        // Accept ad
        adToListing.selectAdReviewAction("Abuse", "Deny");

        // Back to Search For Ad page
        adToListing.backToSearchForAdPage();

        // Verify status is REFUSED
        searchForAd.verifyAdStatusByIndex("REFUSED", index);

        // Verify list ID is blank
        searchForAd.verifyListIDNotExistingByAdIndex(index);

        return adSubject;
    }
}
