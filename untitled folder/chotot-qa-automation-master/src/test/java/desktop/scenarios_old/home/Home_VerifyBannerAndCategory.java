package desktop.scenarios_old.home;

import desktop.base.BaseTest;
import desktop.pages.HomePage;
import org.testng.annotations.Test;

public class Home_VerifyBannerAndCategory extends BaseTest {

    @Test(
            groups = {"prod", "uat"},
            description = "Home, Verify user can select banner and category, Quoc Tran, SRE")
    public void verifyClickOnBannerAndCategoryAndShortcut() {
        // Go to home page
        HomePage homePage = new HomePage();
        homePage.openHomePage();

        // Verify actions on banner section;
        // Include verify paging, format ad and thumbnail status on listing
        homePage.verifyUserCanSelectBannerItem();

        // Verify actions on category section
        // Include verify paging, format ad and thumbnail status on listing
        homePage.verifyUserCanSelectCategory();
    }
}
