package desktop.pages.CP;

import com.vn.chotot.base.BasePage;
import com.vn.chotot.logger.Log4jFactory;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.vn.chotot.configuration.Custom.isUsingCPPage;
import static com.vn.chotot.configuration.WaitTime.maxWaitTime;
import static com.vn.chotot.keywords.selenium.Action.moveAndClickJS;
import static com.vn.chotot.keywords.selenium.Action.setText;
import static com.vn.chotot.keywords.selenium.Page.openURL;
import static com.vn.chotot.keywords.selenium.Wait.waitForElementNotVisible;
import static com.vn.chotot.keywords.selenium.Wait.waitForElementVisible;
import static desktop.configuration.CPConfig.loginCPURL;

public class CP_LoginPage extends BasePage {

    final Logger log = Log4jFactory.instance().createClassLogger(getClass());

    @FindBy(xpath = "//*[@id=\"at-field-username_and_email\"]")
    private WebElement input_Username;

    @FindBy(xpath = "//*[@id=\"at-field-password\"]")
    private WebElement input_Password;

    @FindBy(xpath = "//*[@id=\"at-btn\"]")
    private WebElement btn_Login;

    public void openLoginPage() {
        openURL(loginCPURL);
        waitForElementVisible(input_Username, maxWaitTime * 3);

        // Set test site to CP
        isUsingCPPage = true;
    }

    public void enterUsernameAndPassword(String username, String password) {
        setText(input_Username, username);
        setText(input_Password, password);
    }

    public void clickLogin() {
        moveAndClickJS(btn_Login);
        waitForElementNotVisible(btn_Login, maxWaitTime * 2);
    }

    public void doLogin(String username, String password) {
        log.info("\n----- Login with CP's username: " + username);
        enterUsernameAndPassword(username, password);
        clickLogin();
    }
}
