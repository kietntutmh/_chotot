package desktop.scenarios_old.register;

import desktop.base.BaseTest;
import desktop.pages.HomePage;
import desktop.pages.LoginPage;
import desktop.pages.RegisterPage;
import org.testng.annotations.Test;

import static desktop.configuration.RegisterConfig.registerExcelFile;
import static desktop.configuration.RegisterConfig.registerSheetName;

public class Register_VerifySuccessfulRegister extends BaseTest {
    private RegisterPage registerPage;

    private void setup() {
        HomePage homePage = new HomePage();
        LoginPage loginPage = new LoginPage();
        registerPage = new RegisterPage();

        excelDataProvider.getExcelFileSheet(registerExcelFile, registerSheetName);

        homePage.openHomePage();
        homePage.clickLoginNow();
        loginPage.clickRegister();
        registerPage.verifyRegisterPageDisplayed();
    }

    @Test(
            groups = {"prod", "uat"},
            description =
                    "UAC >> Register, Verify register passed with valid username and password, Vu Hoang, MABU")
    public void verifySuccessfulRegister() {
        setup();
        registerPage.register(true, true);
        registerPage.confirmOTP(excelDataProvider.getCellData(1, 1));
        registerPage.verifyHomePageAfterRegister();
    }
}
