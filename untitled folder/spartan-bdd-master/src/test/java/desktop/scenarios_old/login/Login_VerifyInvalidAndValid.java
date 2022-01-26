package desktop.scenarios_old.login;

import desktop.base.BaseTest;
import desktop.components.TopHeader;
import desktop.pages.HomePage;
import desktop.pages.LoginPage;
import desktop.pages.Profile.PublishProfilePage;
import desktop.pages.Profile.SettingProfilePage;
import org.testng.annotations.Test;

import static desktop.configuration.LoginConfig.*;

public class Login_VerifyInvalidAndValid extends BaseTest {

    private LoginPage loginPage;
    private TopHeader topHeader;
    private PublishProfilePage publishProfilePage;
    private SettingProfilePage settingProfilePage;

    private void setup() {
        // Initiate page instances
        HomePage homePage = new HomePage();
        loginPage = new LoginPage();
        topHeader = new TopHeader();

        settingProfilePage = new SettingProfilePage();
        publishProfilePage = new PublishProfilePage();

        // Go to login page
        homePage.openHomePage();
        homePage.clickLoginNow();
    }

    @Test(
            groups = {"prod", "uat", "dev"},
            description = "UAC >> Login, Verify login passed with valid login, Vu Hoang, MABU")
    public void loginWithValidData() {
        // Run setup
        this.setup();

        // Get data in excel
        excelDataProvider.getExcelFileSheet(loginExcelFile, loginSheetName);
        String validPhone = excelDataProvider.getCellData(4, 0);
        String validPassword = excelDataProvider.getCellData(4, 1);

        // Enter valid data
        loginPage.enterPhonedAndPassword(validPhone, validPassword);
        loginPage.clickSubmit();

        // Verify Profile displays
        topHeader.verifyClickProfileLinkWhenLogin();
        publishProfilePage.clickToOpenProfileEditPage();
        settingProfilePage.verifyProfilePhone(validPhone);
    }

    @Test(
            groups = {"prod", "uat", "dev"},
            description =
                    "UAC >> Login, Verify login failed with valid phone but invalid password, Vu Hoang, MABU")
    public void loginWithInvalidPassword() {
        this.setup();
        excelDataProvider.getExcelFileSheet(loginExcelFile, loginSheetNameInValid);
        String validPhone = excelDataProvider.getCellData(1, 0);
        String invalidPassword = excelDataProvider.getCellData(1, 1);
        loginPage.enterPhonedAndPassword(validPhone, invalidPassword);
        loginPage.clickSubmit();
        loginPage.checkInvalidData();
    }

    @Test(
            groups = {"prod", "uat", "dev"},
            description = "UAC >> Login, Verify login failed with invalid phone, Quoc Tran, MABU")
    public void loginWithInvalidPhone() {
        this.setup();
        excelDataProvider.getExcelFileSheet(loginExcelFile, loginSheetNameInValid);
        String invalidPhone = excelDataProvider.getCellData(2, 0);
        String invalidPassword = excelDataProvider.getCellData(2, 1);
        loginPage.enterPhonedAndPassword(invalidPhone, invalidPassword);
        loginPage.clickSubmit();
        loginPage.checkInvalidData();
    }

    @Test(
            groups = {"prod", "uat"},
            description = "UAC >> Login, Verify login failed with wrong data login, Vu Hoang, MABU")
    public void loginWithWrongData() {
        this.setup();

        excelDataProvider.getExcelFileSheet(loginExcelFile, loginSheetNameInValid);
        String wrongPhone = excelDataProvider.getCellData(3, 0);
        String validPassword = excelDataProvider.getCellData(3, 1);
        loginPage.enterPhonedAndPassword(wrongPhone, validPassword);
        loginPage.clickSubmit();
        loginPage.verifyValidationPhoneWrong();

        // Wrong format Password
        wrongPhone = excelDataProvider.getCellData(4, 0);
        validPassword = excelDataProvider.getCellData(4, 1);
        loginPage.enterPhonedAndPassword(wrongPhone, validPassword);
        loginPage.clickSubmit();
        loginPage.verifyValidationPasswordWrong();

        // Blank Phone, Password
        loginPage.enterPhonedAndPassword("", "");
        loginPage.clickSubmit();
        loginPage.verifyRequiredFields();
    }

    @Test(
            groups = {"prod"},
            description = "UAC >> Login, Verify all required fields, Vu Hoang, MABU")
    public void checkRequiredFields() {
        this.setup();
        loginPage.enterPhonedAndPassword("", "");
        loginPage.clickSubmit();
        loginPage.verifyRequiredFields();
    }
}
