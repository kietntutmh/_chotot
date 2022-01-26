package desktop.pages.Payment;

import com.vn.chotot.base.BasePage;
import desktop.components.Popup;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.vn.chotot.configuration.WaitTime.maxWaitTime;
import static com.vn.chotot.configuration.WaitTime.minWaitTime;
import static com.vn.chotot.keywords.selenium.Action.*;
import static com.vn.chotot.keywords.selenium.Wait.waitForElementStaleness;
import static com.vn.chotot.keywords.selenium.Wait.waitForElementVisible;

public class Napas extends BasePage {

    @FindBy(xpath = "//input[@id=\"napasCardNo\"]")
    private WebElement input_CardNumber;

    @FindBy(xpath = "//input[@id=\"napasNameOnCard\"]")
    private WebElement input_NameOnCard;

    @FindBy(xpath = "//input[@id=\"napasIssueDate\"]")
    private WebElement input_IssueDate;

    @FindBy(xpath = "//input[@id=\"napasOtpCode\"]")
    private WebElement input_OTP;

    @FindBy(xpath = "//*[@id=\"napasMerchantInfo\"]")
    private WebElement lbl_MerchantInfo;

    @FindBy(xpath = "//*[@id=\"napasLoading\"]")
    private WebElement lbl_NapasLoading;

    @FindBy(xpath = "//*[@id=\"napasNotificationResult\"]")
    private WebElement lbl_NapasNotificationResult;

    @FindBy(xpath = "//*[@id=\"napasCancelBtn1\"]")
    private WebElement btn_Cancel;

    @FindBy(xpath = "//button[@id=\"napasProcessBtn1\"]")
    private WebElement btn_Continue1;

    @FindBy(xpath = "//button[@id=\"napasProcessBtn2\"]")
    private WebElement btn_Continue2;

    public String enterNapasInfoAndClickContinue(
            String cardNumber, String ownerName, String issueDate, String otp) {
        // Close popup if displayed
        new Popup().closePopup(minWaitTime);

        // Wait for page load
        waitForElementStaleness(lbl_NapasLoading, maxWaitTime);
        waitForElementVisible(input_CardNumber, maxWaitTime * 4);

        // Enter data
        setText(input_CardNumber, cardNumber);
        setText(input_NameOnCard, ownerName);
        setText(input_IssueDate, issueDate);

        // Click Continue
        waitForElementStaleness(lbl_NapasLoading, maxWaitTime);
        moveAndClickJS(btn_Continue1);

        // Enter OTP
        waitForElementStaleness(lbl_NapasLoading, maxWaitTime);
        setText(input_OTP, otp);

        // Get order information
        String info = getText(lbl_MerchantInfo, maxWaitTime);

        // Click Continue
        moveAndClickJS(btn_Continue2);
        waitForElementStaleness(lbl_NapasLoading, maxWaitTime);

        return info;
    }
}
