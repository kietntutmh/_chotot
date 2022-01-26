package desktop.scenarios_old.login;

import desktop.base.BaseTest;
import desktop.components.TopHeader;
import desktop.pages.HomePage;
import desktop.pages.LoginPage;
import org.testng.annotations.Test;

import static desktop.configuration.LoginConfig.*;

public class Login_VerifyLogout extends BaseTest {

    private LoginPage loginPage;
    private TopHeader topHeader;

    private void setup() {
        // Initiate page instances
        HomePage homePage = new HomePage();
        loginPage = new LoginPage();
        topHeader = new TopHeader();

        // Setup test data
        excelDataProvider.getExcelFileSheet(loginExcelFile, loginSheetName);

        // Go to login page
        homePage.openHomePage();
        homePage.clickLoginNow();
    }

    @Test(
            groups = {"prod", "uat", "dev"},
            description = "UAC >> Login, Verify logout successfully, Quoc Tran, MABU")
    public void verifyClickLogout() {
        this.setup();
        String validPhone = excelDataProvider.getCellData(getMainAccountLoginIndex(), 0);
        String validPassword = excelDataProvider.getCellData(getMainAccountLoginIndex(), 1);
        loginPage.enterPhonedAndPassword(validPhone, validPassword);
        loginPage.clickSubmit();
        topHeader.verifyClickLogoutWhenLogin();
    }
}
