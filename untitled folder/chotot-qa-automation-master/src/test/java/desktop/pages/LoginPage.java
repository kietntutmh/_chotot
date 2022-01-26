package desktop.pages;

import com.vn.chotot.base.BasePage;
import com.vn.chotot.exception.FailureHandling;
import com.vn.chotot.logger.Log4jFactory;
import desktop.components.Popup;
import desktop.configuration.LoginConfig;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.vn.chotot.configuration.WaitTime.maxWaitTime;
import static com.vn.chotot.configuration.WaitTime.minWaitTime;
import static com.vn.chotot.keywords.selenium.Action.moveAndClickJS;
import static com.vn.chotot.keywords.selenium.Action.setText;
import static com.vn.chotot.keywords.selenium.Element.verifyElementText;
import static com.vn.chotot.keywords.selenium.Element.verifyElementVisible;
import static com.vn.chotot.keywords.selenium.Page.openURL;
import static com.vn.chotot.keywords.selenium.Page.verifyPageTitle;
import static com.vn.chotot.keywords.selenium.Utils.verifyMatch;
import static com.vn.chotot.keywords.selenium.Wait.waitForElementStaleness;
import static com.vn.chotot.keywords.selenium.Wait.waitForElementVisible;
import static desktop.configuration.LoginConfig.*;

public class LoginPage extends BasePage {

    final Logger log = Log4jFactory.instance().createClassLogger(getClass());

    @FindBy(xpath = "//*[@class=\"form-title\" and text()=\"ĐĂNG NHẬP\"]")
    private WebElement lbl_Login;

    @FindBy(xpath = "//input[@type=\"tel\"]")
    private WebElement input_Phone;

    @FindBy(xpath = "//input[@type=\"password\"]")
    private WebElement input_Password;

    @FindBy(xpath = "//button[@type=\"submit\"]")
    private WebElement btn_Login;

    @FindBy(xpath = "//button[contains(.,\"Đăng nhập bằng\")]")
    private WebElement btn_LoginUsingFB;

    @FindBy(xpath = "//button[normalize-space(text())='Đăng ký']")
    private WebElement btn_Register;

    @FindBy(xpath = "//*[@class=\"errorMessage\"]")
    private WebElement lbl_ErrorMsg;

    @FindBy(xpath = "//div[@class='errorMessage']")
    private WebElement err_Validation;

    public void openLoginPage() {
        openURL(loginURL);
        waitForElementVisible(btn_Login, maxWaitTime * 3);
        new Popup().closePopup(minWaitTime);
    }

    public void verifyRequiredFields() {
        boolean isRequiredPhone = Boolean.parseBoolean(input_Phone.getAttribute("required"));
        boolean isRequiredPassword = Boolean.parseBoolean(input_Password.getAttribute("required"));
        String validationMsgPhone = input_Phone.getAttribute("validationMessage");
        String validationMsgPassword = input_Password.getAttribute("validationMessage");
        if (!isRequiredPhone
                || !isRequiredPassword
                || !validationMsgPhone.equals("Please fill out this field.")
                || !validationMsgPassword.equals("Please fill out this field."))
            throw new IllegalArgumentException(LoginConfig.requiredMessage);
    }

    public void enterPhonedAndPassword(String phone, String password) {
        new Popup().closePopup(minWaitTime);
        log.info("Login with phone number: " + phone);
        setText(input_Phone, phone);
        setText(input_Password, password);
    }

    public void clickSubmit() {
        moveAndClickJS(btn_Login);
        // Wait for page load
        waitForElementStaleness(btn_Login, maxWaitTime * 2);
        new Popup().closePopup(minWaitTime);
    }

    public void clickLoginByFB() {
        btn_LoginUsingFB.click();
    }

    public void clickRegister() {
        new Popup().closePopup(minWaitTime);
        moveAndClickJS(btn_Register);
    }

    public void checkInvalidData() {
        verifyElementText(err_Validation, true, invalidMessage, FailureHandling.CONTINUE_ON_FAILURE);
        log.info(
                String.format(
                        "Text of Error failed: Actual[%s] Expected[%s]",
                        err_Validation.getText(), invalidMessage));
        verifyElementVisible(err_Validation, maxWaitTime);
    }

    public void verifyValidationPhoneWrong() {
        verifyElementText(err_Validation, true, wrongPhone, FailureHandling.CONTINUE_ON_FAILURE);
        log.info(
                String.format(
                        "Text of Error failed: Actual[%s] Expected[%s]", err_Validation.getText(), wrongPhone));
        verifyElementVisible(err_Validation, maxWaitTime);
    }

    public void verifyValidationPasswordWrong() {
        verifyElementText(err_Validation, true, wrongPassword, FailureHandling.CONTINUE_ON_FAILURE);
        log.info(
                String.format(
                        "Text of Error failed: Actual[%s] Expected[%s]",
                        err_Validation.getText(), wrongPassword));
        verifyElementVisible(err_Validation, maxWaitTime);
    }

    public void verifyRedirectToLoginPage() {
        verifyPageTitle(
                "(?i)(.*)Đăng nhập(.*)", true, maxWaitTime, FailureHandling.CONTINUE_ON_FAILURE);
    }

    public void verifyValidationMessage(String message) {
        waitForElementVisible(lbl_ErrorMsg, maxWaitTime);
        String actual_msg = lbl_ErrorMsg.getText();
        verifyMatch(actual_msg, message, false, FailureHandling.STOP_ON_FAILURE);
    }
}
