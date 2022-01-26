package desktop.scenarios_old.home;

import desktop.base.BaseTest;
import desktop.pages.HomePage;
import org.testng.annotations.Test;

public class Home_VerifyShortcutWithoutLogin extends BaseTest {
    @Test(
            groups = {"prod", "uat"},
            description = "Home, Verify user can select shortcut items without login, Tuan Chieu, MABU")
    public void verifyShortcutWithoutLogin() {
        // Go to home page
        HomePage homePage = new HomePage();
        homePage.openHomePage();

        // Verify actions on Shortcut section;
        homePage.verifyUserCanSelectShortcutItemWithoutLogin();
    }
}
