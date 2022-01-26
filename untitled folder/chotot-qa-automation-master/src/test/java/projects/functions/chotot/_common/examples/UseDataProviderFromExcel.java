package projects.functions.chotot._common.examples;

import desktop.base.BaseTest;
import desktop.pages.HomePage;
import desktop.pages.LoginPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static desktop.configuration.LoginConfig.loginExcelFile;
import static desktop.configuration.LoginConfig.loginSheetName;

/**
 * EXAMPLE OF BASIC STEPS This class is to guide how to load DataProvider from ExcelData
 *
 * @author Quoc Tran Last Update: 2019/06/20
 */
public class UseDataProviderFromExcel extends BaseTest {

    private LoginPage loginPage;

    @DataProvider(name = "invalidLogin")
    public Object[][] createData() {
        return excelDataProvider.getTableArray(loginExcelFile, loginSheetName, 1);
    }

//    @BeforeMethod(firstTimeOnly = true)
    private void setup() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(loginExcelFile, loginSheetName);

        // Initiate page instances
        HomePage homePage = new HomePage();
        loginPage = new LoginPage();

        // Go to login page
        homePage.openHomePage();
        homePage.clickLoginNow();
    }

    @Test(
            dataProvider = "invalidLogin",
            enabled = false,
            description = "Login, Verify invalid login, Quoc Tran, SRE")
    public void loginWithInvalidData(String invalidPhone, String invalidPassword) {
        // login with invalid data
        loginPage.enterPhonedAndPassword(invalidPhone, invalidPassword);
        loginPage.clickSubmit();
    }
}
