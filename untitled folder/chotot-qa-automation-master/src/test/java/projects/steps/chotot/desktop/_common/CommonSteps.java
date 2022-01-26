package projects.steps.chotot.desktop._common;

import desktop.pages.HomePage;
import desktop.pages.LoginPage;
import io.cucumber.java.en.Given;

import static api.configuration.DataConfig.defaultPassword;
import static desktop.configuration.LoginConfig.tempUserPhone;

public class CommonSteps {
    private LoginPage loginPage;
    private HomePage homePage;

    @Given("I login with new account Chotot")
    public void i_login_with_new_account_chotot() {
        // Initialize objects
        loginPage = new LoginPage();
        homePage = new HomePage();

        // Go to login page
        homePage.openHomePage();
        homePage.clickLoginNow();

        // Enter valid data
        loginPage.enterPhonedAndPassword(tempUserPhone, defaultPassword);
        loginPage.clickSubmit();
    }

}