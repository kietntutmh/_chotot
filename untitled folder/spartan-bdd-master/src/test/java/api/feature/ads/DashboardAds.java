package api.feature.ads;

import com.vn.chotot.api.rest_assured.RestAssure;
import com.vn.chotot.exception.FailureHandling;
import com.vn.chotot.logger.Log4jFactory;
import io.restassured.response.Response;
import org.apache.logging.log4j.Logger;

import static api.configuration.DataConfig.getTempRetry;
import static api.configuration.GatewayConfig.*;
import static com.vn.chotot.api.rest_assured.VerifyRespone.*;
import static com.vn.chotot.configuration.WaitTime.minWaitTime;
import static com.vn.chotot.keywords.selenium.Utils.delayStep;
import static com.vn.chotot.keywords.selenium.Utils.verifyEquals;
import static desktop.configuration.LoginConfig.tempUserPhone;

public class DashboardAds {
    static final Logger log = Log4jFactory.instance().createClassLogger(InsertAds.class);

    public static boolean checkAdOnDashboard(String adID) {
        Response response = null;
        String responseCode = "";

        if (getTempRetry() > 0) {
            for (int i = 0; i < getTempRetry(); i++) {
                if (!responseCode.equals("200")) {
                    response = RestAssure.instance().get(global_accessToken, String.format(gatewayAdDashboard, adID));
                    responseCode = getResponseCode(response);
                    log.info("Ad ID [" + adID + "] doesn't display on Dashboard of User[" + tempUserPhone + "]");
                    delayStep(minWaitTime);
                } else break;
            }
        }

        return verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
    }

    public static boolean checkAdStatusOnPrivateDashboard(String adID, String stateTab, FailureHandling failureHandling) {
        Response response = null;
        String responseCode = "";


        if (getTempRetry() > 0) {
            for (int i = 0; i <= getTempRetry(); i++) {
                if (!responseCode.equals("200")) {
                    response = RestAssure.instance().get(global_accessToken, String.format(gatewayAdDashboard, adID));
                    responseCode = getResponseCode(response);
                    log.info(
                            "Ad ID ["
                                    + adID
                                    + "] doesn't display on Dashboard of User["
                                    + tempUserPhone
                                    + "] \n");
                    delayStep(minWaitTime);
                } else break;
            }
        }

        String statusExpect = "\"status\":\"";
        switch (stateTab.toUpperCase()) {
            case "ĐANG BÁN":
                statusExpect += "active";
                break;
            case "BỊ TỪ CHỐI":
                statusExpect += "refused";
                break;
            case "KHÁC":
                statusExpect += "inactive";
                break;
            case "CẦN THANH TOÁN":
            default:
                break;
        }
        statusExpect +="\"";
        String bodyAdInfo = getResponseBodyAsString(response);
        log.info("Body json: " + bodyAdInfo);
        return verifyEquals(bodyAdInfo.contains(statusExpect), true, failureHandling);
    }

    public static boolean checkAdOnDashboard(String adID, String stateTab) {
        return checkAdStatusOnPrivateDashboard(adID, stateTab, FailureHandling.STOP_ON_FAILURE);
    }

    public static boolean checkAdOnDashboard(String adID, String stateTab, FailureHandling failureHandling) {
        return checkAdStatusOnPrivateDashboard(adID, stateTab, failureHandling);
    }

    public static String getPendingUnPaidOtherAdsShop() {
        Response response = RestAssure.instance().get(global_accessToken, gatewayAdsPrivate);

        // Check status code
        String pending_review_ads = getPropertyValue(response, "counting.pending_review");
        String other_ads = getPropertyValue(response, "counting.other");
        String unpaid_ads = getPropertyValue(response, "counting.unpaid");

        return String.format("%s, %s, %s", pending_review_ads, other_ads, unpaid_ads);
    }
}
