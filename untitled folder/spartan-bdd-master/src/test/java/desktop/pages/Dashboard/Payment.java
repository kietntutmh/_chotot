package desktop.pages.Dashboard;

import com.vn.chotot.base.BasePage;
import com.vn.chotot.exception.FailureHandling;
import com.vn.chotot.logger.Log4jFactory;
import desktop.components.Popup;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.vn.chotot.configuration.WaitTime.maxWaitTime;
import static com.vn.chotot.configuration.WaitTime.minWaitTime;
import static com.vn.chotot.keywords.selenium.Action.*;
import static com.vn.chotot.keywords.selenium.Element.*;
import static com.vn.chotot.keywords.selenium.Utils.verifyEquals;
import static com.vn.chotot.keywords.selenium.Utils.verifyMatch;
import static com.vn.chotot.keywords.selenium.Wait.*;

public class Payment extends BasePage {
    final Logger log = Log4jFactory.instance().createClassLogger(getClass());

    @FindBy(xpath = "//h1[text()=\"Thanh toán\"]")
    private WebElement lbl_Payment;

    @FindBy(xpath = "//p/*[contains(@class,\"pull-right\")]")
    private WebElement lbl_TotalAmount;

    @FindBy(
            xpath =
                    "//*[contains(@id,\"paymentType\")]//following-sibling::span[string-length(text())>0]")
    private List<WebElement> lst_PaymentType;

    @FindBy(xpath = "//*[@id=\"firstListOfBank\"]//img")
    private List<WebElement> lst_LocalBank;

    @FindBy(xpath = "//a[@data-parent=\"#firstListOfBank\"]")
    private WebElement btn_SeeMoreBank;

    @FindBy(xpath = "//button[@id='btnPayment']")
    private WebElement btn_Payment;

    @FindBy(xpath = "//*[@id=\"RefusalReasonLabel\"]")
    private WebElement lbl_RefusalLabal;

    @FindBy(xpath = "//div[@role=\"document\" and //*[@id=\"RefusalReasonLabel\"]]//li//p")
    private List<WebElement> lst_RefillOption;

    @FindBy(xpath = "//*[text()=\"Nạp ngay\"]")
    private WebElement btn_RefillNow;

    @FindBy(xpath = "//*[@class=\"modal-body\"]//button[text()=\"ĐỒNG Ý\"]")
    private WebElement btn_ConfirmSavePaymentInfo;

    // Private Board
    @FindBy(xpath = "//div[contains(@class,'boxContentRight')]//ul/li")
    private List<WebElement> lst_RefillOption_PBoard;

    @FindBy(xpath = "//*[text()=\"Nạp Ngay\"]")
    private WebElement btn_RefillNow_PBoard;

    @FindBy(xpath = "//*[@id='paymentTypeDefaultBank']")
    private WebElement btn_SeeMore_RememberBank_PBoard;

    @FindBy(xpath = "//*[@id='paymentTypeBank']")
    private WebElement btn_SeeMore_LocalBank_PBoard;

    @FindBy(xpath = "//*[@id='paymentTypeBankCredit']")
    private WebElement btn_SeeMore_ForeignBank_PBoard;

    @FindBy(xpath = "//*[@id='paymentTypeMomo']")
    private WebElement btn_SeeMore_ViMoMo_PBoard;

    @FindBy(xpath = "//*[@id='paymentTypeStores']")
    private WebElement btn_SeeMore_TradingBranch_PBoard;

    @FindBy(xpath = "//*[@id='paymentTypeSCard']")
    private WebElement btn_SeeMore_ScratchCards_PBoard;

    @FindBy(xpath = "//button[@id='btnPayment' and contains(., 'THANH TOÁN NGAY')]")
    private WebElement btn_Pay_PBoard;

    public void verifyTotalAmount(String expectedPrice) {
        try {
            verifyElementVisible(lbl_TotalAmount, maxWaitTime * 3);
        } catch (TimeoutException e) {
            log.info("Dashboard doesn't display!");
        }
        String actualPrice =
                getAttributeValue(lbl_TotalAmount, "innerText", maxWaitTime).trim().replace("&nbsp;", " ");
        actualPrice = actualPrice.substring(0, actualPrice.indexOf(".000")) + ".000";
        verifyMatch(actualPrice, expectedPrice, false, FailureHandling.CONTINUE_ON_FAILURE);
    }

    public void selectAllPaymentType(String totalPrice) {
        String actualPrice, btnPrice, currency;
        for (WebElement paymentType : lst_PaymentType) {
            String paymentName = getAttributeValue(paymentType, "innerText", maxWaitTime).toUpperCase();
            if (paymentName.contains("QUỐC TẾ") || paymentName.toUpperCase().contains("SMS")) {
                continue; // Can't click
            } else if (paymentName.contains("ĐỒNG TỐT")) {
                currency = "ĐT";
            } else {
                currency = "đ";
            }
            clickAction(paymentType);
            // Verify Price_Label with Total_Bump_Price
            actualPrice = getAttributeValue(lbl_TotalAmount, "innerText", maxWaitTime).trim();
            verifyEquals(actualPrice.endsWith(currency), true, FailureHandling.CONTINUE_ON_FAILURE);
            actualPrice = actualPrice.substring(0, actualPrice.indexOf(".000")) + ".000";
            verifyMatch(actualPrice, totalPrice, false, FailureHandling.CONTINUE_ON_FAILURE);

            // Verify Price_Button with Price_Label
            btnPrice = getAttributeValue(btn_Payment, "innerText", maxWaitTime).trim();
            verifyEquals(btnPrice.contains(currency), true, FailureHandling.CONTINUE_ON_FAILURE);
            btnPrice = btnPrice.substring(0, btnPrice.indexOf(".000")) + ".000";
            verifyMatch(actualPrice, btnPrice, false, FailureHandling.CONTINUE_ON_FAILURE);
        }
    }

    public boolean checkRefillPopupDisplayed() {
        return waitForElementVisible(btn_RefillNow, maxWaitTime);
    }

    public void selectRefillTypeAndClickRefillNow(String refillType) {
        // Wait for page load
        waitForAllElementVisible(lst_RefillOption, maxWaitTime);

        // Select refill now
        clickElementOnListByName(lst_RefillOption, refillType, false, FailureHandling.STOP_ON_FAILURE);

        // Click refill now
        clickRefillNow();
    }

    public void selectAndClickRefillNowPrivateBoard(String refillType) {
        // Wait for page load
        waitForAllElementVisible(lst_RefillOption_PBoard, maxWaitTime);

        // Select refill now
        clickElementOnListByName(
                lst_RefillOption_PBoard, refillType, false, FailureHandling.STOP_ON_FAILURE);

        // Click refill now
        clickRefillNowPrivateBoard();
    }

    public void clickCheckout() {
        clickJS(btn_Payment);
        waitForElementStaleness(btn_Payment, maxWaitTime * 2);
    }

    public void clickRefillNow() {
        waitForElementVisible(btn_RefillNow, maxWaitTime);
        clickJS(btn_RefillNow);
    }

    public void clickRefillNowPrivateBoard() {
        waitForElementVisible(btn_RefillNow_PBoard, maxWaitTime);
        clickJS(btn_RefillNow_PBoard);
    }

    public void clickPayPrivateBoard() {
        waitForElementVisible(btn_Pay_PBoard, maxWaitTime);
        clickJS(btn_Pay_PBoard);
    }

    public void clickConfirmPaymentInfo() {
        moveAndClick(btn_ConfirmSavePaymentInfo);
        waitForElementStaleness(btn_ConfirmSavePaymentInfo, maxWaitTime * 4);
    }

    public void selectPaymentTypeAndCheckOut(String paymentType) {
        selectSpecificPaymentType(paymentType);
        clickCheckout();
    }

    public void selectSpecificPaymentType(String paymentType) {
        clickElementOnListByName(lst_PaymentType, paymentType, false, FailureHandling.STOP_ON_FAILURE);
    }

    public void selectLocalBankAndClickPayment(int bankIndex) {
        // Select local bank
        moveAndClick(btn_SeeMoreBank);
        String bankName =
                selectElementOnListByIndex(lst_LocalBank, bankIndex, FailureHandling.STOP_ON_FAILURE);

        // Click Payment
        moveAndClick(btn_Payment);

        // Click confirm if displayed
        if (waitForElementVisible(btn_ConfirmSavePaymentInfo, maxWaitTime)) clickConfirmPaymentInfo();
    }

    public boolean selectRandomLocalBankAndClickPayment() {
        if (waitForElementVisible(btn_SeeMoreBank, maxWaitTime * 2)) {
            // Click expand button
            clickJS(btn_SeeMoreBank);
            waitForListElementChangedByNumber(lst_LocalBank, 0, minWaitTime);

            // Select local bank
            clickJSRandomElementOnList(lst_LocalBank);

            // Click Payment
            clickJS(btn_Payment);

            // Click confirm if displayed
            if (waitForElementVisible(btn_ConfirmSavePaymentInfo, maxWaitTime * 2))
                clickConfirmPaymentInfo();

            return true;
        }
        return false;
    }

    public void selectRandomLocalBankAndClickPaymentPrivateBoard() {
        // Click expand button
        waitForElementVisible(btn_SeeMore_LocalBank_PBoard, maxWaitTime * 2);
        clickJS(btn_SeeMore_LocalBank_PBoard);
        waitForListElementChangedByNumber(lst_LocalBank, 0, minWaitTime);

        // Select local bank
        clickJSRandomElementOnList(lst_LocalBank);

        // Click Payment
        clickJS(btn_Payment);

        new Popup().closePopup(maxWaitTime);

        // Click confirm if displayed
        if (waitForElementVisible(btn_ConfirmSavePaymentInfo, maxWaitTime)) clickConfirmPaymentInfo();
    }
}
