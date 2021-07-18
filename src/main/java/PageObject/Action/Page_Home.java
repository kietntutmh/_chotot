package PageObject.Action;

import AutomationLibrary.BaseAction.BaseAction;
import PageObject.Locator.Home;
import org.openqa.selenium.WebDriver;


public class Page_Home extends BaseAction {
    public Page_Home(WebDriver driver) {

        super(driver);
    }

    /**
     * Open login page
     */
    public void openLoginPage(){
        System.out.println("-Open login page");
        waitFor(Home.btnLogin,5);
        click(Home.btnLogin);

    }

    /**
     * Open home page of cho tot by URL
     */
    public void openHomePage(){
        System.out.println("-Open Chotot home page");
        driver.get("https://www.chotot.com/");
    }

    /**
     * Verify that user is login successfully
     */
    public void verifySuccessLogin(String profile_name){
        System.out.println("-Verify that user is login successfully");
        verifyExistedElement(Home.btnProfile(profile_name),5,"Login is not successfully");
    }
}
