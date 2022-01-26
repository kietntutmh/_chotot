package desktop.scenarios_old.ads.save;

import desktop.base.BaseTest;
import desktop.configuration.LoginConfig;
import desktop.pages.SaveAdPage;
import org.testng.annotations.Test;

public class SaveAd_VerifyInAdview extends BaseTest {
    private void setupAccount() {
        LoginConfig.setTempAccountLoginIndex(14);
    }

    @Test(description = "Save Ad , (Non Login)Verify SaveAd in Adview, Tri Nguyen, GROWTH")
    public void verifySaveAdInAdviewNonLogin() {
        SaveAdPage saveAdPage = new SaveAdPage();
        saveAdPage.clickIconSaveAdInAdviewNonLogin();
    }

    @Test(description = "Save Ad , Verify SaveAd in Adview, Tri Nguyen, GROWTH")
    public void verifySaveAdInAdView() {
        // Go to adview
        SaveAdPage saveAdPage = new SaveAdPage();
        saveAdPage.selectRandomCategory();

        // Click save ad
        saveAdPage.clickIconSaveAdInAdview();
    }

    @Test(description = "Save ad , Verify Un-SaveAd in Adview, Tri Nguyen, GROWTH")
    public void verifyUnSaveAdAdview() {
        SaveAdPage saveAdPage = new SaveAdPage();
        saveAdPage.selectRandomCategory();

        saveAdPage.checkUnSaveAdInAdview();
    }
}
