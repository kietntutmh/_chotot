package desktop.scenarios_old.home;

import desktop.base.BaseTest;
import desktop.components.TopHeader;
import org.testng.annotations.Test;
import projects.functions.chotot._common.CM_Login;

public class Home_VerifyTopHeaderWhenLogin extends BaseTest {
    @Test(
            groups = {"prod", "uat"},
            description = "Home, Verify user can select top header items when login, Quoc Tran, SRE")
    public void verifyTopHeaderWhenLogin() {
        // Run setup
        new CM_Login().doLogin();

        // Verify actions on top header section
        new TopHeader().verifyUserCanSelectItemOnTopHeaderWhenLogin();
    }
}
