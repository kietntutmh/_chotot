package projects.functions.chotot._common;

import desktop.base.BaseTest;
import desktop.components.TopHeader;
import desktop.pages.Dashboard.ManageAds;
import desktop.pages.HomePage;
import desktop.pages.InsertAds.AdsGroup;
import desktop.pages.LoginPage;
import desktop.pages.RegisterPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static api.configuration.DataConfig.defaultPassword;
import static api.configuration.DataConfig.newUserPhone;
import static api.feature.register.Register.createNewRandomUser;
import static com.vn.chotot.configuration.Constant.CREATE_NEW_USER;
import static com.vn.chotot.exception.ExceptionHandler.setExceptionDebug;
import static desktop.configuration.LoginConfig.*;

public class CM_Login extends BaseTest {

    private LoginPage loginPage;
    private HomePage homePage;
    private TopHeader topHeader;

    final Logger log = LogManager.getLogger(getClass());

    private void loginStepsFromHomePage(String phone, String password) {
        // Initialize objects
        loginPage = new LoginPage();
        homePage = new HomePage();
        topHeader = new TopHeader();

        // Go to login page
        homePage.openHomePage();
        topHeader.clickLoginOrRegister();

        // Enter valid data
        loginPage.enterPhonedAndPassword(phone, password);
        loginPage.clickSubmit();

        // Enter OTP if needed
        new RegisterPage().confirmOTPValid();
    }

    public void doLogin() {
        String validPhone;
        String validPassword;

        // Create new user or using existing user
        if (CREATE_NEW_USER && !checkTempAccountUsed()) {
            // Use new account info
            validPhone = newUserPhone;
            validPassword = defaultPassword;
        } else {
            // Setup test data
            excelDataProvider.getExcelFileSheet(loginExcelFile, loginSheetName);

            // Get data in excel
            if (checkTempAccountUsed()) {
                validPhone = excelDataProvider.getCellData(getTempAccountLoginIndex(), 0);
                validPassword = excelDataProvider.getCellData(getTempAccountLoginIndex(), 1);

            } else {
                validPhone = excelDataProvider.getCellData(getMainAccountLoginIndex(), 0);
                validPassword = excelDataProvider.getCellData(getMainAccountLoginIndex(), 1);
            }
        }

        // Do login
        loginStepsFromHomePage(validPhone, validPassword);
    }

    public void loginWithNewAccount(boolean doResetPhoneAfterRun) {
        // Get old phone
        String oldPhone = "";
        if (!newUserPhone.isEmpty()) oldPhone = newUserPhone;

        // Create new user
        createNewRandomUser();

        // Use new account info
        String validPhone = newUserPhone;

        // Do login
        loginStepsFromHomePage(validPhone, defaultPassword);

        // Reset to oldPhone
        if (doResetPhoneAfterRun && !oldPhone.isEmpty()) newUserPhone = oldPhone;
    }

    public void loginAndInsertAd() {
        String validPhone;
        String validPassword;

        // Create new user or using existing user
        if (CREATE_NEW_USER && !checkTempAccountUsed()) {
            // Use new account info
            validPhone = newUserPhone;
            validPassword = defaultPassword;
        } else {
            // Setup test data
            excelDataProvider.getExcelFileSheet(loginExcelFile, loginSheetName);

            // Get data in excel
            if (checkTempAccountUsed()) {
                validPhone = excelDataProvider.getCellData(getTempAccountLoginIndex(), 0);
                validPassword = excelDataProvider.getCellData(getTempAccountLoginIndex(), 1);

            } else {
                validPhone = excelDataProvider.getCellData(getMainAccountLoginIndex(), 0);
                validPassword = excelDataProvider.getCellData(getMainAccountLoginIndex(), 1);
            }
        }

        // Initiate page instances
        loginPage = new LoginPage();
        homePage = new HomePage();
        topHeader = new TopHeader();
        AdsGroup adsGroup = new AdsGroup();

        // Go to login page
        homePage.openHomePage();

        // Click Insert Ad on top header
        topHeader.clickInsertAds();

        // Enter valid data
        setExceptionDebug("Login phone (UI): " + validPhone);
        adsGroup.enterPhoneAndPassword(validPhone, validPassword);
    }

    public void loginAndGoToManageAd(boolean isUsingNewUser, boolean isShop) {
        String validPhone;
        String validPassword;

        // Create new user or using existing user
        if (CREATE_NEW_USER && !checkTempAccountUsed()) {
            // Use new account info
            validPhone = newUserPhone;
            validPassword = defaultPassword;
        } else {
            // Setup test data
            excelDataProvider.getExcelFileSheet(loginExcelFile, loginSheetName);

            // Get data in excel
            if (checkTempAccountUsed()) {
                validPhone = excelDataProvider.getCellData(getTempAccountLoginIndex(), 0);
                validPassword = excelDataProvider.getCellData(getTempAccountLoginIndex(), 1);

            } else {
                validPhone = excelDataProvider.getCellData(getMainAccountLoginIndex(), 0);
                validPassword = excelDataProvider.getCellData(getMainAccountLoginIndex(), 1);
            }
        }

        if (isUsingNewUser & !tempUserPhone.isEmpty()) {
            validPhone = tempUserPhone;
        }

        // Do login
        setExceptionDebug("Login phone (UI): " + validPhone);
        loginStepsFromHomePage(validPhone, validPassword);

        // Click "Tôi Bán"
        new TopHeader().clickIBuy();

        // Go to shops
        if (isShop) new ManageAds().goToShop();
    }
}
