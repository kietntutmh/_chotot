package Login;

import AutomationLibrary.Execution.TestBase;
import AutomationLibrary.Execution.data;
import PageObject.Action.page;
import org.testng.annotations.Test;


public class PhoneNumber extends TestBase {


    @Test (description = "Login with valid phone and pw")
    public void Login_With_Valid_Account() {
        page.home().openHomePage();
        page.home().openLoginPage();
        page.login().login(data.validPhone(),data.validPw());
        page.home().verifySuccessLogin(data.profileName());
    }


}
