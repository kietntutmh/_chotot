package desktop.scenarios_old.register;

import desktop.base.BaseTest;
import desktop.pages.HomePage;
import desktop.pages.LoginPage;
import desktop.pages.RegisterPage;
import org.testng.annotations.Test;

import static desktop.configuration.RegisterConfig.registerExcelFile;
import static desktop.configuration.RegisterConfig.registerSheetName;

public class Register_VerifyInvalidValid_PhonePassword extends BaseTest {
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
            description = "UAC >> Register, Verify register failed with invalid phone, Vu Hoang, MABU")
    public void verifyInvalidPhone() {
        setup();
        registerPage.register(false, true);
        registerPage.verifyPhoneValidationError();
    }

    @Test(
            groups = {"prod", "uat"},
            description = "UAC >> Register, Verify register failed with invalid password, Vu Hoang, MABU")
    public void verifyInvalidPassword() {
        setup();
        registerPage.register(true, false);
        registerPage.verifyPasswordValidationError();
    }

    @Test(
            groups = {"prod", "uat"},
            description = "UAC >> Register, Verify phone & password are required, Vu Hoang, MABU")
    public void verifyRequiredFields() {
        setup();
        registerPage.register("", "1");
        registerPage.verifyRequiredPhone();
        registerPage.register("1", "");
        registerPage.verifyRequiredPassword();
    }

    @Test(
            groups = {"prod", "uat"},
            description = "UAC >> Register, Verify register failed with exist phone, Vu Hoang, MABU")
    public void verifyExistedPhone() {
        setup();
        String existPhone = excelDataProvider.getCellData(2, 3);
        String validPassword = excelDataProvider.getCellData(3, 1);
        registerPage.register(existPhone, validPassword);
        registerPage.verifyPhoneValidationExisted();
    }
}
