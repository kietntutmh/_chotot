package desktop.scenarios_old.ads.save;

import desktop.base.BaseTest;
import desktop.configuration.LoginConfig;
import desktop.pages.SaveAdPage;
import org.testng.annotations.Test;

public class SaveAd_VerifyNumberOfSaveAd extends BaseTest {
    private void setupAccount() {
        LoginConfig.setTempAccountLoginIndex(14);
    }

    @Test(description = "Save ad , Verify Total Number Of SaveAd in Profile, Tri Nguyen, GROWTH")
    public void verifyTotalNumberOfSaveAd() {
        // Setup account 50 save-ad 0848123518
        setupAccount();

        // Go to adview
        SaveAdPage saveAdPage = new SaveAdPage();
        saveAdPage.selectRandomCategory();

        // Check total save ad
        saveAdPage.checkTotalNumberOfSaveAd();
    }

    @Test(description = "Save ad , Verify SaveAd Normally , Tri Nguyen, GROWTH")
    public void verifySaveAdSuccessfully() {
        SaveAdPage saveAdPage = new SaveAdPage();
        saveAdPage.checkSaveAdsuccessfully();
    }
}
