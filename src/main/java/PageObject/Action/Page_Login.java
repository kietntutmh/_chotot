package PageObject.Action;

import AutomationLibrary.BaseAction.BaseAction;
import PageObject.Locator.Login;
import org.openqa.selenium.WebDriver;


public class Page_Login extends BaseAction {

    public Page_Login(WebDriver driver) {

        super(driver);
    }

    /**
     * Login function
     * @param usr username
     * @param pw password
     */

    public void login(String usr, String pw){
        System.out.println("-Login with ["+usr+"/"+pw+"]");
        waitFor(Login.txtPhoneNumber,5);
        sendKey(Login.txtPhoneNumber,usr);
        sendKey(Login.txtPassWord,pw);
        click(Login.btnLogin);
    }

}
