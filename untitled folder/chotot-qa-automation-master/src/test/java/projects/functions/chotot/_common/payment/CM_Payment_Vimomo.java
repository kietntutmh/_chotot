package projects.functions.chotot._common.payment;

import desktop.base.BaseTest;
import desktop.pages.Dashboard.Payment;
import desktop.pages.Payment.PaymentResult;
import desktop.pages.Payment.Vimomo;

public class CM_Payment_Vimomo extends BaseTest {
    Payment payment;
    Vimomo vimomo;
    PaymentResult paymentResult;

    @Deprecated
    public void paymentWithVimomoPrivateBoard() {
        // Initialize objects
        payment = new Payment();
        vimomo = new Vimomo();
        paymentResult = new PaymentResult();

        // Select payment type
        payment.selectPaymentTypeAndCheckOut("Ví MoMo");

        // Click to pay
        //        payment.clickPayPrivateBoard();

        // Pay with Vimomo
        vimomo.verifyVimomoPageDisplay();
        vimomo.loginAndPayVimomo();
    }
}
