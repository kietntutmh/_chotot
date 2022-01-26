package desktop.scenarios_old.profile;

import desktop.base.BaseTest;
import desktop.configuration.LoginConfig;
import org.testng.annotations.Test;
import projects.functions.chotot._common.CM_PublishProfile;

public class Profile_VerifyProfileDisplayLogin extends BaseTest {
    private void setupAccount() {
        LoginConfig.setTempAccountLoginIndex(17);
    }

    @Test()
    public void verifyPhoneActiveIconDisplay() {
        // Setup account test
        setupAccount();

        // Initiate object instances
        CM_PublishProfile cm_publishProfile = new CM_PublishProfile();

        // Go to Publish profile page
        cm_publishProfile.goToPublishProfilePage(false);

        // Verify info display both two page success
        cm_publishProfile.verifyPhoneIconDisplayRight();
    }

    @Test
    public void verifyFacebookActiveIconDisplay() {
        // Setup account test
        setupAccount();

        // Initiate object instances
        CM_PublishProfile cm_publishProfile = new CM_PublishProfile();

        // Go to Publish profile page
        cm_publishProfile.goToPublishProfilePage(false);

        // Verify info display both two page success
        cm_publishProfile.verifyFacebookIconDisplayRight();
    }

    @Test
    public void verifyLocationActiveIconDisplay() {
        // Setup account test
        setupAccount();

        // Initiate object instances
        CM_PublishProfile cm_publishProfile = new CM_PublishProfile();

        // Go to Publish profile page
        cm_publishProfile.goToPublishProfilePage(false);

        // Verify info display both two page success
        cm_publishProfile.verifyLocationIconDisplayRight();
    }

    @Test
    public void verifyEmailActiveIconDisplay() {
        // Setup account test
        setupAccount();

        // Initiate object instances
        CM_PublishProfile cm_publishProfile = new CM_PublishProfile();

        // Go to Publish profile page
        cm_publishProfile.goToPublishProfilePage(false);

        // Verify info display both two page success
        cm_publishProfile.verifyEmailIconDisplayRight();
    }
}
