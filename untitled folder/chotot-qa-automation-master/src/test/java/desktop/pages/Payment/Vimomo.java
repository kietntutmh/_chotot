package desktop.pages.Payment;

import com.vn.chotot.base.BasePage;
import com.vn.chotot.data.ExcelProvider;
import com.vn.chotot.exception.FailureHandling;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static api.configuration.DataConfig.*;
import static com.vn.chotot.configuration.WaitTime.maxWaitTime;
import static com.vn.chotot.keywords.selenium.Action.clickJS;
import static com.vn.chotot.keywords.selenium.Action.setText;
import static com.vn.chotot.keywords.selenium.Element.verifyAllElementVisible;
import static com.vn.chotot.keywords.selenium.Element.verifyElementVisible;
import static com.vn.chotot.keywords.selenium.Wait.waitForElementClickable;
import static com.vn.chotot.keywords.selenium.Wait.waitForElementVisible;

public class Vimomo extends BasePage {
    @FindBy(xpath = "//a[@href='https://momo.vn']/img")
    private List<WebElement> lst_Vimomo_Logo;

    @FindBy(xpath = "//a[@id='login']")
    private WebElement lnk_Login;

    @FindBy(xpath = "//input[@id='phone']")
    private WebElement input_Phone;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement input_Password;

    @FindBy(xpath = "//p[@id='msgCaptcha']")
    private WebElement lbl_otp_check;

    @FindBy(xpath = "//button[@id='send']")
    private WebElement btn_Send;

    @FindBy(xpath = "//span[@id='recaptcha-anchor']")
    private WebElement chk_Captcha;

    @FindBy(xpath = "//input[@id='otp']")
    private WebElement input_OTP;

    @FindBy(xpath = "//button[@id='sendOtp']")
    private WebElement btn_Payment;

    @FindBy(xpath = "//h1[contains(.,'Thanh toán thành công')]")
    private WebElement lbl_Payment_Successful;

    public void verifyVimomoPageDisplay() {
        verifyAllElementVisible(lst_Vimomo_Logo, maxWaitTime * 3, FailureHandling.STOP_ON_FAILURE);
    }

    public void loginAndPayVimomo() {
        // Page Vimomo payment is open
        waitForElementVisible(lnk_Login, maxWaitTime);
        clickJS(lnk_Login);

        // Get account from Excel
        ExcelProvider excelProvider = new ExcelProvider();
        excelProvider.getExcelFileSheet(vimomoExcelFile, loginSheetName);
        String phoneNumber = excelProvider.getCellData(3, 1);

        // Input Phone
        setText(input_Phone, phoneNumber);

        // Input Password
        setText(input_Password, VIMOMO_PASS);

        // Check on checkbox confirm human
        clickJS(chk_Captcha);

        // Click to login
        waitForElementClickable(btn_Send, maxWaitTime);
        clickJS(btn_Send);

        if (lbl_otp_check.isDisplayed()) clickJS(btn_Send);

        // Input OTP
        waitForElementVisible(input_OTP, maxWaitTime);
        setText(input_OTP, VIMOMO_OTP);

        // Click to pay
        waitForElementClickable(btn_Payment, maxWaitTime);
        clickJS(btn_Payment);

        // Verify pay successfully
        verifyElementVisible(lbl_Payment_Successful, maxWaitTime, FailureHandling.CONTINUE_ON_FAILURE);
    }
}
