package desktop.scenarios_old.bookmark;

import desktop.base.BaseTest;
import desktop.configuration.LoginConfig;
import desktop.pages.AdsListing.AdsListing;
import desktop.pages.HomePage;
import desktop.pages.LoginPage;
import org.testng.annotations.Test;

public class Bookmark_VerifyCanNotBookmarkWithoutLogin extends BaseTest {
    private void setupAccount() {
        LoginConfig.setTempAccountLoginIndex(13);
    }

    @Test(
            groups = {"staging", "k8s"},
            description =
                    "Bookmark, Verify can not Bookmark by top button without login, Tuan Chieu, GROWTH")
    public void verifyCanNotBookmarkByTopButtonWithoutLogin() {
        // Setup account test
        setupAccount();

        // Initiate object instances
        HomePage homePage = new HomePage();
        AdsListing adsListing = new AdsListing();

        // Go to home page
        homePage.openHomePage();

        // Go to a random category
        homePage.selectRandomCategory();

        // Verify Search Text Box
        adsListing.verifySearchTextBoxDisplayed();

        // Click Bookmark button
        adsListing.clickBookmarkButtonOnTopPage();

        // Verify when not login will redirect to login page
        new LoginPage().verifyRedirectToLoginPage();
    }

    @Test(
            groups = {"staging", "k8s"},
            description =
                    "Bookmark, Verify can not Bookmark by bottom button without login, Tuan Chieu, GROWTH")
    public void verifyCanNotBookmarkByBottomButtonWithoutLogin() {
        // Setup account test
        setupAccount();

        // Initiate object instances
        HomePage homePage = new HomePage();
        AdsListing adsListing = new AdsListing();

        // Go to home page
        homePage.openHomePage();

        // Go to a random category
        homePage.selectRandomCategory();

        // Verify Search Text Box
        adsListing.verifySearchTextBoxDisplayed();

        // Input search key word
        adsListing.input_Search("du lich");

        // Click bookmark button
        adsListing.clickBookmarkButtonOnBottomPage();

        // Verify when not login will redirect to login page
        new LoginPage().verifyRedirectToLoginPage();
    }
}
