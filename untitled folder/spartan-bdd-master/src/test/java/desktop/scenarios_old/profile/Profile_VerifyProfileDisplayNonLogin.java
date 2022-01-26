package desktop.scenarios_old.profile;

import desktop.base.BaseTest;
import desktop.configuration.LoginConfig;

public class Profile_VerifyProfileDisplayNonLogin extends BaseTest {
    private void setupAccount() {
        LoginConfig.setTempAccountLoginIndex(13);
    }
}
