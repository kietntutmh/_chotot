package projects.functions.chotot._common;

import desktop.base.BaseTest;
import desktop.components.TopHeader;
import desktop.pages.AdsListing.AdsListing;
import desktop.pages.AdsView.ContactPanel;
import desktop.pages.ChatPage;
import desktop.pages.HomePage;

public class CM_Chat extends BaseTest {

    CM_Login cm_login;
    TopHeader topHeader;
    HomePage homePage;
    AdsListing adListing;
    ContactPanel contactPanel;

    public void goToChatPage() {
        // Initiate object instances
        cm_login = new CM_Login();
        topHeader = new TopHeader();

        // Login to system
        cm_login.doLogin();

        // Click Chat on top header
        topHeader.clickChatLink();

        // Close Request Modal if displayed
        new ChatPage().closeRequestModelSection();
    }

    public void createChatRoom() {
        // Initiate object instances
        cm_login = new CM_Login();
        homePage = new HomePage();
        adListing = new AdsListing();
        contactPanel = new ContactPanel();

        // Login to system
        cm_login.doLogin();

        // Select elt category
        homePage.selectCategoryByIndex(2);

        // Select random AdsListing
        adListing.selectRandomAdOnListing();

        // Click on Chat and switch to Chat page
        contactPanel.clickAndSwitchToChatPage();
    }

    public void goToChatPageOnAdListing() {
        // Initiate object instances
        cm_login = new CM_Login();
        topHeader = new TopHeader();

        // Login to system
        cm_login.doLogin();

        // Select random category
        homePage.selectRandomCategory();

        // Click Chat on top header
        topHeader.clickChatLink();
    }
}
