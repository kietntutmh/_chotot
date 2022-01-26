package desktop.pages.Payment;

import com.vn.chotot.base.BasePage;
import com.vn.chotot.exception.FailureHandling;
import com.vn.chotot.exception.StepFailedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.vn.chotot.configuration.WaitTime.maxWaitTime;
import static com.vn.chotot.exception.ExceptionHandler.exceptionHandler;
import static com.vn.chotot.keywords.selenium.Action.moveAndClick;
import static com.vn.chotot.keywords.selenium.Element.getAttributeValueOfElementOnListByIndex;
import static com.vn.chotot.keywords.selenium.Element.verifyElementVisible;
import static com.vn.chotot.keywords.selenium.Wait.waitForAllElementVisible;

public class PaymentResult extends BasePage {

    @FindBy(xpath = "//h1[text()=\"Thanh toán thành công\"]")
    private WebElement lbl_PaymentSuccessfull;

    @FindBy(xpath = "//table[contains(.,\"Loại giao dịch\")]//tr")
    private List<WebElement> lst_TransactionData;

    @FindBy(xpath = "//a[text()=\"Lịch sử giao dịch\"]")
    private WebElement btn_OrderHistory;

    @FindBy(xpath = "//a[text()=\"Quản lý tin đăng\"]")
    private WebElement btn_ManageAds;

    public void verifySuccessMessageDisplayed() {
        verifyElementVisible(
                lbl_PaymentSuccessfull, maxWaitTime * 5, FailureHandling.CONTINUE_ON_FAILURE);
    }

    public void verifyTransactionResultForRefill(String refillValue) {
        String expectedData = "Nạp Đồng TốtThành công" + refillValue;
        waitForAllElementVisible(lst_TransactionData, maxWaitTime);
        for (int i = 0; i < lst_TransactionData.size(); i++) {
            String actualData =
                    getAttributeValueOfElementOnListByIndex(lst_TransactionData, i, "textContent");
            if (actualData.equals(expectedData)) return;
        }
        exceptionHandler(
                new StepFailedException("<No existing refill data: " + expectedData + ">"),
                FailureHandling.STOP_ON_FAILURE);
    }

    public void verifyTransactionResultForService(String orderValue) {
        String expectedData = "Mua dịch vụThành công-" + orderValue;
        waitForAllElementVisible(lst_TransactionData, maxWaitTime * 2);
        for (int i = 0; i < lst_TransactionData.size(); i++) {
            String actualData =
                    getAttributeValueOfElementOnListByIndex(lst_TransactionData, i, "textContent");
            if (actualData.equals(expectedData)) return;
        }
        exceptionHandler(
                new StepFailedException("No existing order data:" + expectedData),
                FailureHandling.STOP_ON_FAILURE);
    }

    public void clickOrderHistory() {
        moveAndClick(btn_OrderHistory);
    }
}
