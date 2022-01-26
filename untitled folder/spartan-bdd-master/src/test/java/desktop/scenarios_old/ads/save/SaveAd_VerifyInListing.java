package desktop.scenarios_old.ads.save;

import desktop.base.BaseTest;
import desktop.configuration.LoginConfig;
import desktop.pages.SaveAdPage;
import org.testng.annotations.Test;

public class SaveAd_VerifyInListing extends BaseTest {
    private void setupAccount() {
        LoginConfig.setTempAccountLoginIndex(15);
    }

    @Test(description = "Save Ad , (Non Login)Verify SaveAd in Listing, Tri Nguyen, GROWTH")
    public void verifySaveAdInListingNonLogin() {
        SaveAdPage saveAdPage = new SaveAdPage();
        saveAdPage.clickIconSaveAdInListingNonLogin();
    }

    @Test(description = "Save Ad , Verify SaveAd in Listing, Tri Nguyen, GROWTH")
    public void verifySaveAdInListing() {
        // Go to Listing
        SaveAdPage saveAdPage = new SaveAdPage();
        saveAdPage.selectRandomCategory();

        // Click icon save ad
        saveAdPage.clickIconSaveAdInListing();
    }

    @Test(description = "Save ad , Verify Un-SaveAd in Listing, Tri Nguyen, GROWTH")
    public void verifyUnSaveAdInListing() {
        SaveAdPage saveAdPage = new SaveAdPage();
        saveAdPage.selectRandomCategory();
        saveAdPage.checkUnSaveAdInListing();
    }
}
