package desktop.scenarios_old.home;

import desktop.base.BaseTest;
import desktop.components.Footer;
import desktop.pages.HomePage;
import org.testng.annotations.Test;

public class Home_VerifyFooter extends BaseTest {

    @Test(
            groups = {"production", "uat"},
            description = "Home, Verify user can select footer items, Quoc Tran, SRE")
    public void verifyFooter() {
        // Initiate page instances
        HomePage homePage = new HomePage();
        Footer footer = new Footer();

        // Go to home page
        homePage.openHomePage();

        // Verify actions on footer section
        footer.verifyUserCanSelectLinkUnderFooter();
    }
}
