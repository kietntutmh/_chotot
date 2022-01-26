package desktop.scenarios_old.ads.payment;

import desktop.base.BaseTest;
import desktop.pages.Dashboard.ManageAds;
import desktop.pages.Dashboard.Payment;
import desktop.pages.Dashboard.PrivateDashboard;
import desktop.pages.Payment.Napas;
import desktop.pages.Payment.OrderHistory;
import desktop.pages.Payment.PaymentResult;
import org.testng.annotations.Test;
import projects.functions.chotot._common.CM_Login;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertVehicle;

import java.util.Date;
import java.util.List;

import static api.configuration.DataConfig.napasExcelFile;
import static com.vn.chotot.helper.DateTime.getDateInStringFormat;

public class Payment_FromPrivateBoard extends BaseTest {
    CM_API_Ads_InsertVehicle cm_api_ads_insertVehicle;
    PrivateDashboard privateDashboard;
    CM_Login cm_login;
    Payment payment;
    Napas napas;
    PaymentResult paymentResult;
    ManageAds manageAds;
    OrderHistory orderHistory;

    @Test(description = "Payment, Verify refill DongTot from local bank, Quoc Tran, ME", enabled = false)
    public void verifyRefillDongTotFromLocalBank() {
        // Initialize objects
        cm_login = new CM_Login();
        privateDashboard = new PrivateDashboard();
        cm_api_ads_insertVehicle = new CM_API_Ads_InsertVehicle();
        payment = new Payment();
        napas = new Napas();
        paymentResult = new PaymentResult();
        manageAds = new ManageAds();
        orderHistory = new OrderHistory();

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(false, false);

        // Select refill option
        String refillValue = manageAds.selectRandomRefillOptionByIndex();

        // Select local bank and checkout
        payment.selectSpecificPaymentType("Thẻ ngân hàng nội địa");
        payment.selectRandomLocalBankAndClickPayment();

        // Get list data from excel
        excelDataProvider.getExcelFileSheet(napasExcelFile, "Napas");
        List<String> napasData = excelDataProvider.getRowData(1);

        // Enter napas information
        napas.enterNapasInfoAndClickContinue(
                napasData.get(0), napasData.get(1), napasData.get(2), napasData.get(3));

        // Verify Payment Result
        paymentResult.verifySuccessMessageDisplayed();
        paymentResult.verifyTransactionResultForRefill(refillValue);

        // Verify order history
        paymentResult.clickOrderHistory();
        String refundItem =
                getDateInStringFormat(new Date(), "dd/MM/yyyy") + "Nạp Đồng Tốt : " + refillValue + "ĐT";
        orderHistory.verifyOrderHistoryItemDisplayed(refundItem);
    }
}
