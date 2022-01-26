package projects.functions.chotot._common.cp;

import desktop.base.BaseTest;
import desktop.pages.CP.CP_HomePage;
import desktop.pages.CP.CP_LoginPage;

import static desktop.configuration.CPConfig.*;
import static desktop.configuration.LoginConfig.loginExcelFile;

public class CM_CP_Login extends BaseTest {

    public void loginAndGoToSearchAd() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(loginExcelFile, loginCPSheetName);

        // Get data in excel
        String username;
        String password;
        if (checkTempAccountCPUsed()) {
            username = excelDataProvider.getCellData(getTempAccountCPIndex(), 0);
            password = excelDataProvider.getCellData(getTempAccountCPIndex(), 1);

        } else {
            username = excelDataProvider.getCellData(getMainAccountCPIndex(), 0);
            password = excelDataProvider.getCellData(getMainAccountCPIndex(), 1);
        }

        // Initiate page instances
        CP_LoginPage loginPage = new CP_LoginPage();
        CP_HomePage homePage = new CP_HomePage();

        // Go to login page
        loginPage.openLoginPage();

        // Do login
        loginPage.doLogin(username, password);

        // Go to <Search For Ad> page
        homePage.selectLeftMenu("Control Panel", "Search For Ad");
    }

    public void loginAndGoToChat_FilterKeyword() {
        //Setup test data
        excelDataProvider.getExcelFileSheet(loginExcelFile, loginCPSheetName);

        //Get data in excel
        String username;
        String password;
        if (checkTempAccountCPUsed()) {
            username = excelDataProvider.getCellData(getTempAccountCPIndex(), 0);
            password = excelDataProvider.getCellData(getTempAccountCPIndex(), 1);

        } else {
            username = excelDataProvider.getCellData(getMainAccountCPIndex(), 0);
            password = excelDataProvider.getCellData(getMainAccountCPIndex(), 1);
        }

        //Initiate page instances
        CP_LoginPage loginPage = new CP_LoginPage();
        CP_HomePage homePage = new CP_HomePage();

        // Go to login page
        loginPage.openLoginPage();

        // Do login
        loginPage.doLogin(username, password);

        // Go to <Search For Ad> page
        homePage.selectLeftMenu("Chat", "Filter Keyword");
    }
}
