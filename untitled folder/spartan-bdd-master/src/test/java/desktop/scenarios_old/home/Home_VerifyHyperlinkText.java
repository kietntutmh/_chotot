package desktop.scenarios_old.home;

import desktop.base.BaseTest;
import desktop.pages.HomePage;
import org.testng.annotations.Test;

public class Home_VerifyHyperlinkText extends BaseTest {

    @Test(
            groups = {"prod", "uat"},
            description = "Home, Verify user can select hyperlink texts, Quoc Tran, SRE")
    public void verifyClickOnHyperlinkText() {
        // Go to home page
        HomePage homePage = new HomePage();
        homePage.openHomePage();

        // Verify actions on hyperlink text section
        // Include verify paging, format ad and thumbnail status on listing
        homePage.verifyUserCanSelectHyperlinkText();
    }
}
