package desktop.pages;

import com.vn.chotot.base.BasePage;
import com.vn.chotot.exception.FailureHandling;
import com.vn.chotot.logger.Log4jFactory;
import desktop.components.Popup;
import desktop.configuration.RegisterConfig;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.vn.chotot.configuration.WaitTime.maxWaitTime;
import static com.vn.chotot.configuration.WaitTime.minWaitTime;
import static com.vn.chotot.keywords.selenium.Action.moveAndClick;
import static com.vn.chotot.keywords.selenium.Action.setText;
import static com.vn.chotot.keywords.selenium.Element.verifyElementText;
import static com.vn.chotot.keywords.selenium.Element.verifyElementVisible;
import static com.vn.chotot.keywords.selenium.Page.openURL;
import static com.vn.chotot.keywords.selenium.Page.verifyPageTitleChanged;
import static com.vn.chotot.keywords.selenium.Wait.waitForElementVisible;
import static desktop.configuration.HomeConfig.homeURL;
import static desktop.configuration.RegisterConfig.*;

public class RegisterPage extends BasePage {

    Logger log = Log4jFactory.instance().createClassLogger(getClass());

    // Register Fill Form
    @FindBy(xpath = "//div[normalize-space(text())='ĐĂNG KÝ' and @class='form-title']")
    private WebElement lbl_Register;

    @FindBy(xpath = "//input[@type='tel']")
    private WebElement input_Phone;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement input_Password;

    @FindBy(xpath = "//button[normalize-space(text())='ĐĂNG KÝ']")
    private WebElement btn_Register;

    @FindBy(xpath = "//button[@type='submit']/../following-sibling::div[1]")
    private WebElement lbl_Term;

    @FindBy(xpath = "//button[@type='submit']/../following-sibling::div[1]/a")
    private WebElement link_Term;

    @FindBy(xpath = "//button[@type='submit']/../following-sibling::p[1]")
    private WebElement lbl_PhoneConfirm;

    @FindBy(xpath = "//button[@type='submit']/../following-sibling::div[2]/span")
    private WebElement lbl_Or;

    @FindBy(xpath = "//button[@type='submit']/../following-sibling::p[2]")
    private WebElement lbl_AccountQuest;

    @FindBy(xpath = "//button[@type='submit']/../following-sibling::p[2]/a")
    private WebElement link_Login;

    @FindBy(
            xpath =
                    "//div[@class='errorMessage' and normalize-space(text())='Số điện thoại đã tồn tại.']")
    private WebElement err_PhoneExisted;

    @FindBy(
            xpath =
                    "//div[@class='errorMessage' and normalize-space(text())='Vui lòng kiểm tra số điện thoại!']")
    private WebElement err_PhoneInvalid;

    @FindBy(
            xpath =
                    "//div[@class='errorMessage' and normalize-space(text())='Vui lòng kiểm tra mật khẩu!']")
    private WebElement err_PasswordInvalid;

    // Register OTP Form
    @FindBy(xpath = "//input[@type='number']")
    private WebElement input_OtpNumber;

    @FindBy(xpath = "//div[contains(text(),'Mã xác nhận sẽ được gửi đến số điện thoại')]/b")
    private WebElement lbl_Phone;

    @FindBy(xpath = "//button[@type='submit'][normalize-space(text())='XÁC NHẬN MIỄN PHÍ']")
    private WebElement btn_OtpConfirm;

    @FindBy(xpath = "//form/preceding-sibling::div[@class='errorMessage']")
    private WebElement err_OtpInvalid;

    @FindBy(xpath = "//*[@class=\"form-title\" and text()=\"VUI LÒNG XÁC NHẬN SỐ ĐIỆN THOẠI\"]")
    private WebElement lbl_VerifyOTP;

    // Main Action
    public void openRegisterPage() {
        openURL(registerURL);
    }

    public void register(boolean isValidPhone, boolean isValidPassword) {
        // Enter phone
        if (isValidPhone) {
            setText(input_Phone, randomPhone());
        } else {
            setText(input_Phone, "1");
        }

        // Enter password
        if (isValidPassword) {
            setText(input_Password, PASSWORD_DEFAULT);
        } else {
            setText(input_Password, "1");
        }

        // Close popup if displayed
        new Popup().closePopup(minWaitTime);

        // Click register
        moveAndClick(btn_Register);
    }

    public void register(String telephone, String password) {
        setText(input_Phone, telephone);
        setText(input_Password, password);
        moveAndClick(btn_Register);
    }

    public void confirmOTP(String OTP) {
        // Close popup if displayed
        new Popup().closePopup(minWaitTime);
        if (waitForElementVisible(lbl_VerifyOTP, minWaitTime)) {
            setText(input_OtpNumber, OTP);
            moveAndClick(btn_OtpConfirm);
        }
    }

    public void confirmOTPValid() {
        confirmOTP(OTP_VALID);
    }

    /**
     * This function is to verify all elements on Register Page. (If) Add more verification of other
     * elements
     */
    public void verifyRegisterPageDisplayed() {
        verifyElementVisible(lbl_Register, maxWaitTime);
    }

    public void verifyHomePageAfterRegister() {
        verifyPageTitleChanged(homeURL, maxWaitTime, FailureHandling.CONTINUE_ON_FAILURE);
    }

    public void verifyOTPValidationError() {
        verifyElementText(err_OtpInvalid, true, OTP_INVALID_MSG, FailureHandling.CONTINUE_ON_FAILURE);
        verifyElementVisible(err_OtpInvalid, maxWaitTime);
    }

    public void verifyPhoneValidationError() {
        verifyElementVisible(err_PhoneInvalid, maxWaitTime);
    }

    public void verifyPasswordValidationError() {
        verifyElementVisible(err_PasswordInvalid, maxWaitTime);
    }

    public void verifyRequiredPhone() {
        boolean requiredPhone = Boolean.parseBoolean(input_Phone.getAttribute("required"));
        String validationMsgPhone = input_Phone.getAttribute("validationMessage");
        if (!requiredPhone || !validationMsgPhone.equals("Please fill out this field."))
            throw new IllegalArgumentException(RegisterConfig.requiredPhoneMsg);
    }

    public void verifyRequiredPassword() {
        boolean requiredPassword = Boolean.parseBoolean(input_Password.getAttribute("required"));
        String validationMsgPassword = input_Password.getAttribute("validationMessage");
        if (!requiredPassword || !validationMsgPassword.equals("Please fill out this field."))
            throw new IllegalArgumentException(RegisterConfig.requiredPasswordMsg);
    }

    private String randomPhone() {
        String timestamp = "" + System.currentTimeMillis() / 1000;
        String number =
                timestamp.substring(timestamp.length() - phoneValidLength); // Max length of phone is 10
        number = "0" + number.substring(1); // First Number must be 0
        return number;
    }

    public void verifyPhoneValidationExisted() {
        verifyElementVisible(err_PhoneExisted, maxWaitTime);
    }
}
