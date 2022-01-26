package projects.functions.chotot.listingFee;

import com.vn.chotot.api.rest_assured.RestAssure;
import org.junit.Assert;
import projects.functions.APISupportFunctions;

import java.util.List;

import static api.configuration.DataConfig.tempAdID;
import static api.configuration.DataConfig.tempUID;
import static api.configuration.GatewayConfig.*;
import static projects.functions.chotot.payment.PayOrder_API_Functions.checkOrderExist;
import static projects.functions.chotot.payment.PayOrder_API_Functions.checkOrderNotExist;

public class ListingFee_API_Functions extends APISupportFunctions {
    private static String ownerToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1OTM3NTQ4NTcsImlzcyI6ImNob3RvdCIsInN1YiI6IjEwMDk3NTI1In0.SGUOU36jDDXv6YaVDRgqpaHmthaltzrw-nHc9pcdrvY";
    private static String adID = "";
    private static String uID = "";
    private static int retryCheckUserAds = 10;

    public ListingFee_API_Functions() {
        //Condition to Check Listing Fee API is ownerToken, adID, uID
        Assert.assertFalse(global_accessToken.isEmpty());
        Assert.assertFalse(tempAdID.isEmpty());
        Assert.assertFalse(tempUID.isEmpty());
        ownerToken = global_accessToken;
        adID = tempAdID;
        uID = tempUID;
    }

    public static void checkAdOnListingFee(String tabName) {
        tabName = tabName.trim().toUpperCase();
        String status = "";

        int waitTime = 0;
        while (waitTime < retryCheckUserAds) {
            try {
                response = get(ownerToken, String.format(gatewayPrivateUserAds, tempAdID));
                verifyStatusCode200("LISTING FEE", String.format(gatewayPrivateUserAds, tempAdID));
                status = getResponseData(response, "$.status");
                Assert.assertNotNull(status);
                checkAdOnTab(tabName, status);
                break;
            } catch (AssertionError tryAgain) {
                waitConstant(5);
            }
            waitTime++;
        }
        debugResponse();
        checkAdOnTab(tabName, status);
    }

    /**
     * SPLIT FROM checkAdOnListingFee
     * @param tabName
     * @author VUHOANG
     */
    private static void checkAdOnTab(String tabName, String status){
        String state, paymentStatus;
        switch (tabName) {
            case "ĐANG BÁN":
                state = getResponseData(response, "$.state");       //VUHOANG changed status -> state
                Assert.assertEquals("FAILED: Ad isn't on ĐANG BÁN", "active", status);
                Assert.assertEquals("FAILED: Ad isn't ACCEPTED by CP", "accepted", state );
                break;
            case "CẦN THANH TOÁN":
                state = getResponseData(response, "$.state");
                paymentStatus = getResponseData("$.payment_status");

                Assert.assertEquals("FAILED: Ad is free but expect charge.",
                        "unpaid", paymentStatus);
                Assert.assertEquals("FAILED: Ad is on ĐANG BÁN or Status is: " + status,
                        "inactive", status);
                Assert.assertEquals("FAILED: Ad is FREE & state is not unpaid",
                        "unpaid", state);
                break;
            case "KHÁC":
                Assert.assertEquals("FAILED: Ad is on ĐANG BÁN instead of KHÁC",
                        "inactive", status);
                break;
            case "ĐỢI DUYỆT":
                state = getResponseData(response, "$.state");
                Assert.assertEquals("FAILED: Ad isn't ĐỢI DUYỆT",
                        "pending_review", state);
                break;
            case "BỊ TỪ CHỐI":
                Assert.assertEquals("FAILED: Ad is NOT refused", "refused", status);
                state = getResponseData(response, "$.state");
                Assert.assertEquals("FAILED: Ad isn't refused by CP", "refused", state);
                break;
        }
    }

    public static void checkAdIsFree() {
        //Check that no any Order is created
        int waitTime = 0;
        while (waitTime < retryCheckUserAds) {
            try {
                response = get(ownerToken, String.format(gatewayPrivateUserAds, tempAdID));
                verifyStatusCode200("Listing Fee", String.format(gatewayPrivateUserAds, tempAdID));
                Assert.assertEquals("FAILED: Ad is charge but free", "free", getResponseData(response, "$.payment_status") );
                break;
            } catch (AssertionError tryAgain) {
                waitConstant(3);
            }
            waitTime++;
        }
        debugResponse();
        Assert.assertEquals("FAILED: Ad is charge but free", "free", getResponseData(response, "$.payment_status") );
        checkOrderNotExist();
    }

    public static void checkAdIsCharge() {
        int waitTime = 0;
        while (waitTime < retryCheckUserAds) {
            try {
                response = get(ownerToken, String.format(gatewayPrivateUserAds, tempAdID));
                verifyStatusCode200("LISTING FEE", String.format(gatewayPrivateUserAds, tempAdID));
                break;
            } catch (AssertionError tryAgain) {
                waitConstant(10);
            }
            waitTime++;
       }
        Assert.assertEquals("Check Ad is charge FAILED","unpaid", getResponseData(response, "$.state"));
        Assert.assertEquals("Check Ad is charge FAILED","unpaid", getResponseData(response, "$.payment_status"));
        checkOrderExist();
    }

    public static void checkAdIsPaid() {

    }

    public static void checkAdIsProAd() {
        int waitTime = 0;
        while (waitTime < retryCheckUserAds) {
            try {
                response = get(ownerToken, String.format(gatewayPrivateUserAds, tempAdID));
                verifyStatusCode200("LISTING FEE", String.format(gatewayPrivateUserAds, tempAdID));
                break;
            } catch (AssertionError tryAgain) {
                waitConstant(10);
            }
            waitTime++;
        }
        Assert.assertEquals("FAILED: AD is not Pro ad", "pro", getResponseData(response, "$.profiler"));
    }

    public static void checkAdIsPrivateAd() {
        int waitTime = 0;
        while (waitTime < retryCheckUserAds) {
            try {
                response = get(ownerToken, String.format(gatewayPrivateUserAds, tempAdID));
                verifyStatusCode200("LISTING FEE", String.format(gatewayPrivateUserAds, tempAdID));
                break;
            } catch (AssertionError tryAgain) {
                waitConstant(10);
            }
            waitTime++;
        }
        Assert.assertEquals("FAILED: AD is not Pro ad", "private", getResponseData(response, "$.profiler"));
    }

    //Còn sai : đang check số tiền pay không phải số refund
    public static void checkOrderHistory_POSOrder_Refund(String ownerToken, int amount) {
        int actualOrderValue = -1;
        int tryGetOrder = 5;
        int j = 0;
        int refund = -1;
        while (j < tryGetOrder) {
            try {
                response = RestAssure.instance().get(ownerToken, gatewayOrderHistory_ListPaid);
                verifyStatusCode200("API FAILED: CAN'T GET ORDER HISTORY");
                query = "$[?(@.order_type == 'topup' " +
//                        "&& @.order_status == 'paid' " +
                        "&& @.payment_method == 'compensation')][0].order_value";     //VUHOANG DEBUG: WRONG

                refund = getResponseDataListInt(response, query).get(0);
                Assert.assertTrue(refund > -1);
                actualOrderValue = refund;
                break;
            } catch (AssertionError assertionError) {
            } catch (IndexOutOfBoundsException indexOutOfBoundsException){
                //getResponseDataListInt(response, query).get(0) returns null
            }
            waitConstant(7);
            j++;
        }
        debugResponse();
        Assert.assertTrue("DOESN'T REFUND DT: " + refund, refund > -1);
        Assert.assertEquals(String.valueOf(actualOrderValue), amount, "POS order is paid but doesn't display in Order history");
    }

    public static String getOwnerToken() {
        return ownerToken;
    }
}
