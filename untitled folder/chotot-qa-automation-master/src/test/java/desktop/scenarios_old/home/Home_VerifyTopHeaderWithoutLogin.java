package desktop.scenarios_old.home;

import desktop.base.BaseTest;
import desktop.components.TopHeader;
import desktop.pages.HomePage;
import org.testng.annotations.Test;

public class Home_VerifyTopHeaderWithoutLogin extends BaseTest {

    @Test(
            groups = {"prod", "uat"},
            description = "Home, Verify user can select top header items without login, Quoc Tran, SRE")
    public void verifyTopHeaderWithoutLogin() {
        // Initiate page instances
        HomePage homePage = new HomePage();
        TopHeader topHeader = new TopHeader();

        // Go to login page
        homePage.openHomePage();

        // Verify actions on top header section
        topHeader.verifyUserCanSelectItemOnTopHeaderWithoutLogin();
    }
}
