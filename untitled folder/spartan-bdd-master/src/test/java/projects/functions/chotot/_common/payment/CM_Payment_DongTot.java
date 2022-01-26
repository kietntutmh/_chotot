package projects.functions.chotot._common.payment;

import desktop.base.BaseTest;
import desktop.components.Popup;
import desktop.pages.Dashboard.Payment;
import desktop.pages.Payment.Napas;
import desktop.pages.Payment.PaymentResult;

import static com.vn.chotot.configuration.WaitTime.minWaitTime;
import static desktop.configuration.PaymentConfig.defaultRefillOption;

public class CM_Payment_DongTot extends BaseTest {
    Payment payment;
    Napas napas;
    PaymentResult paymentResult;

    public void paymentWithDongTot(String totalAmount) {
        // Initialize objects
        payment = new Payment();
        napas = new Napas();
        paymentResult = new PaymentResult();
        Popup popup = new Popup();

        // Close popup
        popup.closePopup(minWaitTime);

        // Check total amount
        payment.verifyTotalAmount(totalAmount);

        // Refill if no existing Dong Tot
        if (payment.checkRefillPopupDisplayed()) {
            // Refill Dong Tot
            payment.selectRefillTypeAndClickRefillNow(defaultRefillOption);
        }

        // Select payment type
        payment.selectPaymentTypeAndCheckOut("Đồng Tốt");

        // Verify Payment Result
        paymentResult.verifyTransactionResultForService(totalAmount);
    }
}
