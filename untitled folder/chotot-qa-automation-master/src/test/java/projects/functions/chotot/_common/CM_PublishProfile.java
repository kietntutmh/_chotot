package projects.functions.chotot._common;

import desktop.base.BaseTest;
import desktop.components.TopHeader;
import desktop.pages.Profile.PublishProfilePage;
import desktop.pages.Profile.SettingProfilePage;

import static desktop.configuration.ProfileConfig.Address;
import static desktop.configuration.ProfileConfig.Phone;

public class CM_PublishProfile extends BaseTest {
    CM_Login cm_login;
    TopHeader topHeader;
    PublishProfilePage publishProfilePage;
    SettingProfilePage settingProfilePage;

    public void goToPublishProfilePage(boolean isLogged) {
        // Initiate object instances
        cm_login = new CM_Login();
        topHeader = new TopHeader();

        // Login to system if user not login
        if (!isLogged) {
            cm_login.doLogin();
        }

        // Click Saved search page on top header
        topHeader.verifyClickProfileLinkWhenLogin();
    }

    public void verifyPhoneIconDisplayRight() {
        // Initiate object instances
        publishProfilePage = new PublishProfilePage();
        settingProfilePage = new SettingProfilePage();

        // Check icon display in publish profile page
        Boolean isActive = publishProfilePage.verifyPhoneIconActiveOtNotActive();

        // Go to Setting Profile Page
        publishProfilePage.clickToOpenProfileEditPage();

        // Verify display in setting profile syn with publish profile
        if (isActive) {
            settingProfilePage.verifyProfilePhone(Phone);
        } else {
            settingProfilePage.verifyProfilePhone("");
        }
    }

    public void verifyFacebookIconDisplayRight() {
        // Initiate object instances
        publishProfilePage = new PublishProfilePage();
        settingProfilePage = new SettingProfilePage();

        // Check icon display in publish profile page
        Boolean isActive = publishProfilePage.verifyFacebookIconActiveOrNotActive();

        // Go to Setting Profile Page
        publishProfilePage.clickToOpenProfileEditPage();

        // Verify display in setting profile syn with publish profile
        if (isActive) {
            settingProfilePage.verifyFacebookConnected(true);
        } else {
            settingProfilePage.verifyFacebookConnected(false);
        }
    }

    public void verifyLocationIconDisplayRight() {
        // Initiate object instances
        publishProfilePage = new PublishProfilePage();
        settingProfilePage = new SettingProfilePage();

        // Check icon display in publish profile page
        Boolean isActive = publishProfilePage.verifyLocationIconActiveOrNotActive();

        // Go to Setting Profile Page
        publishProfilePage.clickToOpenProfileEditPage();

        // Verify display in setting profile syn with publish profile
        if (isActive) {
            settingProfilePage.verifyProfileAddress(Address);
        } else {
            settingProfilePage.verifyProfileAddress("");
        }
    }

    public void verifyEmailIconDisplayRight() {
        // Initiate object instances
        publishProfilePage = new PublishProfilePage();
        settingProfilePage = new SettingProfilePage();

        // Check icon display in publish profile page
        Boolean isActive = publishProfilePage.verifyEmailIconActiveOrNotActive();

        // Go to Setting Profile Page
        publishProfilePage.clickToOpenProfileEditPage();

        // Verify display in setting profile syn with publish profile
        if (isActive) {
            settingProfilePage.verifyEmailVerified(true);
        } else {
            settingProfilePage.verifyEmailVerified(false);
        }
    }
}
