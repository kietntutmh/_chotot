package desktop.pages.Profile;

import com.vn.chotot.base.BasePage;
import com.vn.chotot.exception.FailureHandling;
import com.vn.chotot.logger.Log4jFactory;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.vn.chotot.configuration.WaitTime.maxWaitTime;
import static com.vn.chotot.keywords.selenium.Action.moveAndClick;
import static com.vn.chotot.keywords.selenium.Element.verifyElementText;
import static com.vn.chotot.keywords.selenium.Element.verifyElementVisible;
import static com.vn.chotot.keywords.selenium.Wait.waitForElementVisible;

public class SettingProfilePage extends BasePage {

    final Logger log = Log4jFactory.instance().createClassLogger(getClass());

    @FindBy(xpath = "//div[normalize-space(.)='Số điện thoại']/following-sibling::div[1]/span")
    private WebElement txt_ProfilePhone;

    @FindBy(xpath = "//div[@class='col-xs-12']//a[contains(text(), 'Xác nhận email')]")
    private WebElement btn_VerifyMail;

    @FindBy(xpath = "//div[@class='col-xs-12']//span[contains(text(), 'Yêu cầu chấm dứt tài khoản')]")
    private WebElement btn_TerminationAccount;

    @FindBy(xpath = "//div[@class='col-xs-12']//a[contains(text(), 'Kết nối Facebook')]")
    private WebElement btn_ConnectFacebook;

    @FindBy(xpath = "//div[normalize-space(.)='Facebook']/following-sibling::div[1]/span")
    private WebElement txt_Facebook;

    @FindBy(
            xpath =
                    "//div[contains(text(), 'Vui lòng kiểm tra \"Hộp thư\" của bạn để hoàn tất việc xác nhận email.')]")
    private WebElement lbl_CheckMail;

    @FindBy(xpath = "//div[normalize-space(.)='Địa chỉ']/following-sibling::div[1]/span")
    private WebElement txt_ProfileAddress;

    @FindBy(xpath = "//div[normalize-space(.)='Giới tính']/following-sibling::div[1]/span")
    private WebElement txt_Gender;

    @FindBy(xpath = "//div[normalize-space(.)='Ngày sinh']/following-sibling::div[1]/span")
    private WebElement txt_DaysOfBirth;

    @FindBy(xpath = "//div[normalize-space(.)='Danh mục yêu thích']/following-sibling::div[1]/span")
    private WebElement txt_Favorite;

    @FindBy(xpath = "//img[@alt='Đã xác nhận']")
    private WebElement icon_EmailVerified;

    @FindBy(xpath = "//div[normalize-space(.)='Email']/following-sibling::div[1]/span")
    private WebElement txt_Email;

    /**
     * This function is to verify PHONE_NUMBER on Profile Page
     *
     * @param phoneNumber Inputted number to verify Last Updated: 2019/06/20 Change Log:
     */
    public void verifyProfilePhone(String phoneNumber) {
        waitForElementVisible(txt_ProfilePhone, maxWaitTime * 2);
        verifyElementText(txt_ProfilePhone, true, phoneNumber, FailureHandling.STOP_ON_FAILURE);
        log.debug("Phone display " + txt_ProfilePhone.getText());
    }

    public void verifyProfileAddress(String address) {
        waitForElementVisible(txt_ProfileAddress, maxWaitTime * 2);
        verifyElementText(txt_ProfileAddress, true, address, FailureHandling.STOP_ON_FAILURE);
        log.debug("Address display " + txt_ProfileAddress.getText());
    }

    public void verifyProfileGender(String gender) {
        waitForElementVisible(txt_Gender, maxWaitTime * 2);
        verifyElementText(txt_Gender, true, gender, FailureHandling.STOP_ON_FAILURE);
    }

    public void clickToSendVerifyMail() {
        moveAndClick(btn_VerifyMail);
        log.debug("Click on btn_VerifyMail");
        verifyElementVisible(lbl_CheckMail, maxWaitTime);
    }

    public void clickToConnectFacebook() {
        log.debug("Click on btn_ConnectFacebook");
        moveAndClick(btn_ConnectFacebook);
    }

    public void clickToTerminationAccount() {
        waitForElementVisible(btn_TerminationAccount, maxWaitTime);
        moveAndClick(btn_TerminationAccount);
    }

    public void verifyFacebookConnected(boolean isConnected) {
        if (isConnected) {
            verifyElementText(txt_Facebook, true, "Đã kết nối Facebook", FailureHandling.STOP_ON_FAILURE);
        } else {
            verifyElementText(txt_Facebook, true, "Kết nối Facebook", FailureHandling.STOP_ON_FAILURE);
        }
        log.debug("Facebook status: " + txt_Facebook.getText());
    }

    public void verifyEmailVerified(boolean isVerified) {
        if (isVerified) {
            verifyElementVisible(icon_EmailVerified, maxWaitTime);
            log.debug("Email " + txt_Email.getText() + " verified");
        } else {
            verifyElementVisible(btn_VerifyMail, maxWaitTime);
            log.debug("Email " + txt_Email.getText() + " need verified");
        }
    }
}
