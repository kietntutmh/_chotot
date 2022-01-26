package projects.functions.chotot.payment;

import api.configuration.GatewayConfig;
import com.vn.chotot.exception.ExceptionHandler;
import com.vn.chotot.exception.FailureHandling;
import com.vn.chotot.exception.StepFailedException;
import com.vn.chotot.logger.Log4jFactory;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import projects.functions.APISupportFunctions;

import java.util.HashMap;
import java.util.Map;

import static api.configuration.DataConfig.tempRetryPayOrder;
import static api.configuration.GatewayConfig.*;
import static com.vn.chotot.configuration.WaitTime.maxWaitTime;
import static com.vn.chotot.helper.Utils.convertMapToJsonString;
import static com.vn.chotot.keywords.selenium.Utils.delayStep;

public class PayOrder_API_Functions extends APISupportFunctions {
    static final Logger log = Log4jFactory.instance().createClassLogger(PayOrder_API_Functions.class);

    private static int amountAfterPayDT = 0;
    private static int amountAfterPayDT4B = 0;
    private static int amountPayDTTotal = 0;      //To count total after pay
    private static int amountPayDT4BTotal = 0;      //To count total after pay
    private static int amountBeforePayDT = 0;
    private static int amountBeforePayDT4B = 0;
    private static int tryGetBalance = 5;

    public static int getAmountAfterPayDT() {
        return amountAfterPayDT;
    }

    public static int getAmountAfterPayDT4B() {
        return amountAfterPayDT4B;
    }

    private static void setAmountAfterPayDT(int amount) {
        amountAfterPayDT = amount;
        // Called from paymentOrderDT
    }

    private static void setAmountAfterPayDT4B(int amount) {
        amountAfterPayDT4B = amount;
        // Called from paymentOrderDT4B
    }

    public static int getAmountPayDTTotal() {
        return amountPayDTTotal;
    }

    public static void setAmountPayDTTotal(int amountPayDTTotal) {
        PayOrder_API_Functions.amountPayDTTotal = amountPayDTTotal;
    }

    public static int getAmountPayDT4BTotal() {
        return amountPayDT4BTotal;
    }

    public static void setAmountPayDT4BTotal(int amountPayDT4BTotal) {
        PayOrder_API_Functions.amountPayDT4BTotal = amountPayDT4BTotal;
    }

    public static int getAmountBeforePayDT() {
        return amountBeforePayDT;
    }

    public static int getAmountBeforePayDT4B() {
        return amountBeforePayDT4B;
    }

    public static void setAmountBeforePayDT(int amountBeforePayDT) {
        PayOrder_API_Functions.amountBeforePayDT = amountBeforePayDT;
    }

    public static void setAmountBeforePayDT4B(int amountBeforePayDT4B) {
        PayOrder_API_Functions.amountBeforePayDT4B = amountBeforePayDT4B;
    }

    //================ FUNCTIONS ================

    public static String paymentOrder() {
        return paymentOrder(global_accessToken, "200");
    }

    public static String paymentOrder(String token) {
        return paymentOrder(token, "200");
    }

    public static String paymentOrder(String token, String responseCode) {
        // Set body data
        Map<Object, Object> mapData = new HashMap<>();
        mapData.put("source", "desktop_web");
        mapData.put("gateway", "credit");
        mapData.put("order_type", "cart");
        mapData.put("title", "Thanh toán cho dịch vụ đẩy tin");
        mapData.put("ip", getGatewayPayOrderBodyIP());
        mapData.put("return_url", gatewayPayOrderReturnURL);
        mapData.put("cart_id", "default");
        String bodyData = convertMapToJsonString(mapData);

        // Retry pay order
        for (int i = 0; i < tempRetryPayOrder; i++) {
            try {
                response = post(token, bodyData, GatewayConfig.gatewayPayOrderWithDongTot);
                verifyStatusCode(response, "PAYMENT", gatewayPayOrderWithDongTot, responseCode);
                break;
            } catch (AssertionError error) {
                waitConstant(5);
            }
        }
        String error = getResponseData(response, "$.error_message");
        String errorMsg = getResponseData(response, "$.message");
        Assert.assertNull(error, "Pay Order Failed: " + error + ", " + errorMsg);
        verifyStatusCode(response, "PAYMENT", gatewayPayOrderWithDongTot, responseCode);

        if (Integer.parseInt(responseCode) < 299) {
            setAmountAfterPayDT(Integer.parseInt(getResponseData("$.amount")));
        }
        //get amount after paid

        return getResponseBodyAsString(response);
    }

    public static String paymentOrderDT4B() {
        return paymentOrderDT4B(global_accessToken);
    }

    public static String paymentOrderDT4B(String ownerToken) {
        return paymentOrderDT4B(ownerToken, "200");
    }

    public static String paymentOrderDT4B(String ownerToken, String responseCode) {
        // Set body data
        Map<Object, Object> mapData = new HashMap<>();
        mapData.put("source", "desktop_web");
        mapData.put("gateway", "credit_biz");
        mapData.put("order_type", "cart");
        mapData.put("unit", "credit");
        mapData.put("title", "Thanh toán dịch vụ");
        mapData.put("ip", getGatewayPayOrderBodyIP());
        String bodyData = convertMapToJsonString(mapData);

        // Retry pay order
        for (int i = 0; i < tempRetryPayOrder; i++) {
            try {
                response = post(ownerToken, bodyData, gatewayPayOrderWithDongTot);
                verifyStatusCode200("PAYMENT-PAYORDER", gatewayPayOrderWithDongTot);
                Assert.assertNotNull(getResponseData(response, "$.status"));
                break;
            } catch (AssertionError error) {
                waitConstant(5);
            }
        }
        debugResponse();
        verifyStatusCode200("PAYMENT-PAYORDER", gatewayPayOrderWithDongTot);
        Assert.assertNotNull(getResponseData(response, "$.status"));

        //get amount after paid
        if (Integer.parseInt(responseCode) < 299) {
            setAmountAfterPayDT4B(Integer.parseInt(getResponseData(response, "$.amount")));
        } else {
            setAmountAfterPayDT4B(0);       //VUHOANG DEBUG ???
        }
        return getResponseBodyAsString(response);
    }

    // Use for pay failed
    public static void paymentOrderDT4B_VerifyError(String ownerToken, String statusCode, String errorMsg) {
        delayStep(maxWaitTime * 2);

        // Set body data
        Map<Object, Object> mapData = new HashMap<>();
        mapData.put("source", "desktop_web");
        mapData.put("gateway", "credit_biz");
        mapData.put("order_type", "cart");
        mapData.put("unit", "credit");
        mapData.put("title", "Thanh toán dịch vụ");
        mapData.put("ip", getGatewayPayOrderBodyIP());
        String bodyData = convertMapToJsonString(mapData);

        // Post data
        log.debug("\n----- PAY ORDER DT4B: Access token: \n" + ownerToken + "\n");

        // Retry pay order
        String actualError = "";
        for (int i = 0; i < tempRetryPayOrder; i++) {
            try {
                response = post(ownerToken, bodyData, GatewayConfig.gatewayPayOrderWithDongTot);
                verifyStatusCode(response, "PAYMENT DT4B", gatewayPayOrderWithDongTot, statusCode);
                actualError = getResponseData(response, "$.error");
                Assert.assertEquals(actualError, errorMsg);
                break;
            } catch (AssertionError error) {
                waitConstant(5);
            }
        }
        debugResponse();
        verifyStatusCode(response, "PAYMENT DT4B", gatewayPayOrderWithDongTot, statusCode);
        Assert.assertEquals(actualError, errorMsg);
    }

    public static void paymentOrderWithDongTot() {
        paymentOrderWithDongTot("200");
    }

    public static void paymentOrderWithDongTot(String statusCode) {
        paymentOrder();
    }

    public static void paymentOrderWithDongTot(FailureHandling failureHandling) {
        String message = paymentOrder();
        if (message.length() > 0) {
            ExceptionHandler.exceptionHandler(new StepFailedException("\n----- Pay order is FAILED: \n" + message), failureHandling);
        }
    }

    @Deprecated // VUHOANG MAINTENANCE
    public static void checkOrderNotExist() {
        delayStep(maxWaitTime * 2);

        // Set body data
        Map<Object, Object> mapData = new HashMap<>();
        mapData.put("source", "desktop_web");
        mapData.put("gateway", "credit");
        mapData.put("order_type", "cart");
        mapData.put("title", "Thanh toán cho dịch vụ đẩy tin");
        mapData.put("ip", getGatewayPayOrderBodyIP());
        mapData.put("return_url", gatewayPayOrderReturnURL);
        mapData.put("cart_id", "default");
        String bodyData = convertMapToJsonString(mapData);

        // Post data
        log.debug("\n----- PAY ORDER: Access token: \n" + global_accessToken + "\n");

        // Retry pay order
        for (int i = 0; i < tempRetryPayOrder; i++) {
            try {
                response = post(global_accessToken, bodyData, GatewayConfig.gatewayPayOrderWithDongTot);
                Assert.assertEquals(getResponseCode(response), "400", "Failed: an ORDER is created");
                Assert.assertEquals(getResponseData(response, "$.error_message"), "Không tìm thấy giỏ hàng", "Failed: Found Giỏ hàng");
                break;
            } catch (AssertionError e) {
                waitConstant(3);
            }
        }
    }

    @Deprecated // VUHOANG MAINTENANCE
    public static void checkOrderExist() {
        delayStep(maxWaitTime * 2);

        // Set body data
        Map<Object, Object> mapData = new HashMap<>();
        mapData.put("source", "desktop_web");
        mapData.put("gateway", "credit");
        mapData.put("order_type", "cart");
        mapData.put("title", "Thanh toán cho dịch vụ đẩy tin");
        mapData.put("ip", getGatewayPayOrderBodyIP());
        mapData.put("return_url", gatewayPayOrderReturnURL);
        mapData.put("cart_id", "default");
        String bodyData = convertMapToJsonString(mapData);

        // Post data
        log.debug("\n----- PAY ORDER: Access token: \n" + global_accessToken + "\n");

        int i = 0;
        while (i <= tempRetryPayOrder) {
            delayStep(maxWaitTime * 2);
            try {
                response = post(global_accessToken, bodyData, GatewayConfig.gatewayPayOrderWithDongTot);
                System.out.println("DEBUG:" + getResponseMessage(response));
                Assert.assertEquals(getResponseCode(response), "200", "Failed: an ORDER isn't created");
                break;
            } catch (AssertionError ignored) {
            }
            i++;
        }
        Assert.assertEquals(getResponseCode(response), "200", "Failed: an ORDER isn't created");
    }


    @Deprecated
    public static int getBalanceDT() {
        return getBalanceDT(global_accessToken);
    }

    @Deprecated
    public static int getBalanceDT(String ownerToken) {
        int i = 0;
        while (i < tryGetBalance) {
            try {
                response = get(ownerToken, gatewayCredit_CheckBalance);
                verifyStatusCode200("GET BALANCE", gatewayCredit_CheckBalance);
                break;
            } catch (AssertionError | IndexOutOfBoundsException assertionError) {
                waitConstant(3);
            }
            i++;
        }

        verifyStatusCode200("GET BALANCE", gatewayCredit_CheckBalance);

        if (getResponseDataListInt("$[*][?(@.type == 'paid')].balance").size() > 0) {
            return getResponseDataListInt("$[*][?(@.type == 'paid')].balance").get(0); // Balance is still or balance runs out
        } else {
            // User never top up, balance is null
            return 0;
        }
    }

    @Deprecated
    public static int getBalanceDTFree() {
        return getBalanceDTFree(global_accessToken);
    }

    @Deprecated
    public static int getBalanceDTFree(String ownerToken) {
        int i = 0;
        int balance = 0;
        while (i < tryGetBalance) {
            response = get(ownerToken, gatewayCredit_CheckBalance);
            verifyStatusCode200("Check Balance DT Free, at try " + (i + 1) + " : " + gatewayCredit_CheckBalance);
            debugResponse();
            try {
                query = "$[?(@.type == 'free')].balance";
                balance = getResponseDataInt(response, query);
                Assert.assertTrue(balance > -1, "CAN NOT GET BALACE DONG TOT FREE");
                break;
            } catch (AssertionError assertionError) {
                waitConstant(4);
            }
            i++;
        }

        // When balance is run out, element "Free DT" is removed
        if (balance <= 0)
            return 0;
        return balance;
    }

    /**
     * @param ownerToken
     * @param typeOfBalance     dt_365, dt_free, dt_expiry
     * @param infoNeedToGet     total_amount, closest_expired_amount, closest_expired_time
     * @update 17Dec, 2021
     * @author Vu Hoang
     */
    private static final String DT_TYPE_365 = "dt_365";
    private static final String DT_TYPE_FREE = "dt_free";
    private static final String DT_TYPE_EXPIRY = "dt_expiry";
    private static final String DT_PAYMENT_PAID = "paid";
    private static final String DT_PAYMENT_FREE = "free";
    private static final String DT_INFO_TOTAL_AMOUNT = "total_amount";
    private static final String DT_INFO_CLOSEST_AMOUNT = "closest_expired_amount";
    private static final String DT_INFO_CLOSEST_EXPIRED_TIME = "closest_expired_time";

    private static String getDTBalance(String ownerToken, String typeOfBalance, String typeOfPayment, String infoNeedToGet) {
        query = "$[*][?(@.type == '" + typeOfBalance.toLowerCase() + "' && @.type_id == '" + typeOfPayment + "')]." + infoNeedToGet;


        for (int i = 0; i < tempRetryPayOrder; i++) {
            try {
                response = get(ownerToken, gatewayCredit_GetBalanceDetail);
                verifyStatusCode200("CREDIT: Get all balances", gatewayCredit_GetBalanceDetail);
                Assert.assertNotNull(getResponseData(query));
                Assert.assertFalse(getResponseData(query).isEmpty());
                break;
            } catch (AssertionError e) {
                waitConstant(6);
            }
        }
        debugResponse();
        verifyStatusCode200("CREDIT: Get Balance Info: " + infoNeedToGet
                , gatewayCredit_GetBalanceDetail);
        Assert.assertNotNull(getResponseData(query));
        Assert.assertFalse(getResponseData(query).isEmpty());
        return getResponseData(query);
    }

    // Đồng Tốt 365
    public static String getDTBalance_365_AmountTotal(String ownerToken) {
        return getDTBalance(ownerToken, DT_TYPE_365, DT_PAYMENT_PAID, DT_INFO_TOTAL_AMOUNT);
    }

    public static String getDTBalance_365_AmountClosest(String ownerToken) {
        return getDTBalance(ownerToken, DT_TYPE_365, DT_PAYMENT_PAID, DT_INFO_CLOSEST_AMOUNT);
    }

    public static String getDTBalance_365_ExpiredClosest(String ownerToken) {
        return getDTBalance(ownerToken, DT_TYPE_365, DT_PAYMENT_PAID, DT_INFO_CLOSEST_EXPIRED_TIME);
    }

    // Đồng Tốt Expiry
    public static String getDTBalance_Expiry_AmountTotal(String ownerToken) {
        return getDTBalance(ownerToken, DT_TYPE_EXPIRY, DT_PAYMENT_PAID, DT_INFO_TOTAL_AMOUNT);
    }

    public static String getDTBalance_Expiry_AmountClosest(String ownerToken) {
        return getDTBalance(ownerToken, DT_TYPE_EXPIRY, DT_PAYMENT_PAID, DT_INFO_CLOSEST_AMOUNT);
    }

    public static String getDTBalance_Expiry_ExpiredClosest(String ownerToken) {
        return getDTBalance(ownerToken, DT_TYPE_EXPIRY, DT_PAYMENT_PAID, DT_INFO_CLOSEST_EXPIRED_TIME);
    }

    // Đồng Tốt Free
    public static String getDTBalance_Free_AmountTotal(String ownerToken) {
        return getDTBalance(ownerToken, DT_TYPE_FREE, DT_PAYMENT_FREE, DT_INFO_TOTAL_AMOUNT);
    }

    public static String getDTBalance_Free_AmountClosest(String ownerToken) {
        return getDTBalance(ownerToken, DT_TYPE_FREE, DT_PAYMENT_FREE, DT_INFO_CLOSEST_AMOUNT);
    }

    public static String getDTBalance_Free_ExpiredClosest(String ownerToken) {
        return getDTBalance(ownerToken, DT_TYPE_FREE, DT_PAYMENT_FREE, DT_INFO_CLOSEST_EXPIRED_TIME);
    }

    // Verify DT Balance

    /**
     * @param ownerToken
     * @param balance    expected Balance
     * @param dtType     dt_365, dt_free, dt_expiry
     * @author Vu Hoang
     */
    private static void verifyDTBalance(String ownerToken, String balance, String dtType) {
        String actualBalance = null;
        for (int i = 0; i < tempRetryPayOrder; i++) {
            try {
                switch (dtType) {
                    case DT_TYPE_365:
                        actualBalance = getDTBalance_365_AmountTotal(ownerToken);
                        break;
                    case DT_TYPE_FREE:
                        actualBalance = getDTBalance_Free_AmountTotal(ownerToken);
                        break;
                    case DT_TYPE_EXPIRY:
                        actualBalance = getDTBalance_Expiry_AmountTotal(ownerToken);
                        break;
                }

                Assert.assertEquals(
                        balance
                        , actualBalance
                        , "DT " + dtType + " Balance is incorrect"
                );
                break;
            } catch (AssertionError e) {
                // In case, data is not updated yet. It takes a while to update expired_date
                System.out.println("Try to verify DT " + dtType + " Balance at " + i);
                waitConstant(5);
            }
        }

        // All fields are updated when DT balance is updated
        Assert.assertEquals(
                balance
                , actualBalance
                , "DT " + dtType + " Balance is incorrect"
        );
    }

    public static void verifyDTBalance_365(String ownerToken, String balance) {
        verifyDTBalance(ownerToken, balance, DT_TYPE_365);
    }

    public static void verifyDTBalance_Free(String ownerToken, String balance) {
        verifyDTBalance(ownerToken, balance, DT_TYPE_FREE);
    }

    public static void verifyDTBalance_Expiry(String ownerToken, String balance) {
        verifyDTBalance(ownerToken, balance, DT_TYPE_EXPIRY);
    }
}
