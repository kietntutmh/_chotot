package desktop.scenarios_old.home;

import desktop.base.BaseTest;
import desktop.pages.HomePage;
import org.testng.annotations.Test;
import projects.functions.chotot._common.CM_Login;

public class Home_VerifyShortcutWhenLogin extends BaseTest {
    @Test(
            groups = {"prod", "uat"},
            description = "Home, Verify user can select shortcut items when login, Tuan Chieu, MABU")
    public void verifyShortcutWhenLogin() {
        // Go to home page
        HomePage homePage = new HomePage();
        homePage.openHomePage();

        // Run setup
        new CM_Login().doLogin();

        // Verify actions on Shortcut section;
        homePage.verifyUserCanSelectShortcutItemWhenLogin();
    }
}
