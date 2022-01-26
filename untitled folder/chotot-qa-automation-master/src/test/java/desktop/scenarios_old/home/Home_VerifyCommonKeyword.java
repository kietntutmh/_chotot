package desktop.scenarios_old.home;

import desktop.base.BaseTest;
import desktop.pages.HomePage;
import org.testng.annotations.Test;

public class Home_VerifyCommonKeyword extends BaseTest {

    @Test(
            groups = {"prod", "uat", "dev"},
            description = "Home, Verify user can select common keywords, Quoc Tran, SRE")
    public void verifyClickOnCommonKeywords() {
        // Go to home page
        HomePage homePage = new HomePage();
        homePage.openHomePage();

        // Verify actions on common keywords section
        // Include verify paging, format ad and thumbnail status on listing
        homePage.verifyUserCanSelectCommonKeyword();
    }
}
