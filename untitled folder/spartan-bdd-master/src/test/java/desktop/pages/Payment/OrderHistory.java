package desktop.pages.Payment;

import com.vn.chotot.base.BasePage;
import com.vn.chotot.exception.ExceptionHandler;
import com.vn.chotot.exception.FailureHandling;
import com.vn.chotot.exception.StepFailedException;
import desktop.components.TopHeader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.vn.chotot.configuration.WaitTime.maxWaitTime;
import static com.vn.chotot.keywords.selenium.Action.getAttributeValue;
import static com.vn.chotot.keywords.selenium.Element.getAttributeValueOfElementOnListByIndex;
import static com.vn.chotot.keywords.selenium.Utils.verifyMatch;
import static com.vn.chotot.keywords.selenium.Wait.waitForAllElementVisible;
import static com.vn.chotot.keywords.selenium.Wait.waitForElementVisible;

public class OrderHistory extends BasePage {

    @FindBy(xpath = "//h1[text()=\"Lịch sử giao dịch\"]")
    private WebElement lbl_OrderHistory;

    @FindBy(
            xpath = "//*[contains(@class,\"redux-infinite-scroll \")]//*[contains(@class,\"col-md\")]")
    private List<WebElement> lst_OrderHistory;

    @FindBy(
            xpath =
                    "//div[contains(@class, 'boxContentRight')]//div[contains(@class, 'col-md-12')][1]//span")
    private WebElement lbl_DongTotAmount;

    public void goToOrderHistory() {
        new TopHeader().clickOrderHistory();
        waitForElementVisible(lbl_OrderHistory, maxWaitTime * 5);
    }

    public void verifyOrderHistoryItemDisplayed(String orderItem) {
        String itemValue;
        waitForAllElementVisible(lst_OrderHistory, maxWaitTime);
        for (int i = 0; i < lst_OrderHistory.size(); i++) {
            itemValue = getAttributeValueOfElementOnListByIndex(lst_OrderHistory, i, "textContent");
            if (itemValue.contains(orderItem)) return;
        }
        ExceptionHandler.exceptionHandler(
                new StepFailedException("\n---- No existing order item \n" + orderItem + "\n on history"),
                FailureHandling.STOP_ON_FAILURE);
    }

    public void verifyOrderAmount(int amount) {
        waitForAllElementVisible(lst_OrderHistory, maxWaitTime);
        verifyMatch(
                String.valueOf(amount),
                String.valueOf(lst_OrderHistory.size()),
                false,
                FailureHandling.CONTINUE_ON_FAILURE);
    }

    public void verifyDongTotAmount(String amount) {
        waitForElementVisible(lbl_DongTotAmount, maxWaitTime);
        String actualAmount =
                getAttributeValue(lbl_DongTotAmount, "innerText", maxWaitTime).replace(".", "");
        verifyMatch(actualAmount, amount, true, FailureHandling.STOP_ON_FAILURE);
    }
}
