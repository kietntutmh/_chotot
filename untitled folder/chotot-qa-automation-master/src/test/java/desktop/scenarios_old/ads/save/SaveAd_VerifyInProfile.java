package desktop.scenarios_old.ads.save;

import desktop.base.BaseTest;
import desktop.configuration.LoginConfig;
import desktop.pages.SaveAdPage;
import org.testng.annotations.Test;

public class SaveAd_VerifyInProfile extends BaseTest {
    private void setupAccount() {
        LoginConfig.setTempAccountLoginIndex(16);
    }

    @Test(description = "Save Ad , (Non Login)Verify SaveAd in Public Profile , Tri Nguyen, GROWTH")
    public void verifySaveAdInPublicProfileNonLogin() {
        SaveAdPage saveAdPage = new SaveAdPage();
        saveAdPage.clickIconSaveAdInProfileNonLogin();
    }

    @Test(description = "Save ad , Verify SaveAd in Profile, Tri Nguyen, GROWTH")
    public void verifySaveAdInProfile() {
        // Go to adview
        SaveAdPage saveAdPage = new SaveAdPage();
        saveAdPage.selectRandomCategory();

        // Click save ad
        saveAdPage.clickIconSaveAdInProfile();
    }

    @Test(description = "Save ad , Verify Un-SaveAd in Profile, Tri Nguyen, GROWTH")
    public void verifyUnSaveAdInProfile() {
        // Go to adview
        SaveAdPage saveAdPage = new SaveAdPage();
        saveAdPage.selectRandomCategory();

        // Click save ad
        saveAdPage.checkUnSaveAdInProfile();
    }
}
