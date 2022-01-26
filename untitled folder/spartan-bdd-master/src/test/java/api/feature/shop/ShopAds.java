package api.feature.shop;

import api.configuration.GatewayConfig;
import com.vn.chotot.api.rest_assured.RestAssure;
import com.vn.chotot.exception.FailureHandling;
import io.restassured.response.Response;

import static api.configuration.DataConfig.getTempRetryCreateShop;
import static api.configuration.DataConfig.tempShopID;
import static api.configuration.GatewayConfig.*;
import static com.vn.chotot.api.rest_assured.VerifyRespone.*;
import static com.vn.chotot.configuration.WaitTime.minWaitTime;
import static com.vn.chotot.exception.ExceptionHandler.setExceptionDebug;
import static com.vn.chotot.keywords.selenium.Utils.delayStep;
import static com.vn.chotot.keywords.selenium.Utils.getProjectDir;

public class ShopAds {
    @Deprecated
    public static String createShop(String bodyData) {
        Response response =
                RestAssure.instance().post(global_accessToken, bodyData, GatewayConfig.gatewayCreateShop);

        // Check status code
        String responseCode = getResponseCode(response);
        String responseMsg = getPropertyValue(response, "message");
        if (getTempRetryCreateShop() > 0) {
            for (int i = 0; i < getTempRetryCreateShop(); i++) {
                if (!responseCode.equals("200")) {
                    setExceptionDebug(
                            "Retry To Create Shop "
                                    + (i + 1)
                                    + " time(s): "
                                    + String.format("ERR[%s] MSG[%s]", responseCode, responseMsg));
                    response =
                            RestAssure.instance()
                                    .post(global_accessToken, bodyData, GatewayConfig.gatewayCreateShop);
                    responseCode = getResponseCode(response);
                    responseMsg = getPropertyValue(response, "message");
                    delayStep(minWaitTime);
                } else break;
            }
        }

        // Check status code
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);

        // using for Pos - create data by API
        tempShopID = getPropertyValue(response, "shop_id");
        delayStep(minWaitTime);

        // Return ad_id
        return tempShopID;
    }

    public static void createShopPhotoCover() {
        String imagePath = getProjectDir() + "/images/shops/cover.jpg";
        Response response =
                RestAssure.instance().postImageFile(global_accessToken, gatewayCreateShopCover, imagePath);

        // Check status code
        String responseCode = getResponseCode(response);
        String responseMsg = getPropertyValue(response, "message");
        if (getTempRetryCreateShop() > 0) {
            for (int i = 0; i < getTempRetryCreateShop(); i++) {
                if (!responseCode.equals("200")) {
                    setExceptionDebug(
                            "Retry To Create Shop Cover"
                                    + (i + 1)
                                    + " time(s): "
                                    + String.format("ERR[%s] MSG[%s]", responseCode, responseMsg));
                    response =
                            RestAssure.instance()
                                    .postImageFile(global_accessToken, gatewayCreateShopCover, imagePath);
                    responseCode = getResponseCode(response);
                    responseMsg = getPropertyValue(response, "message");
                    delayStep(minWaitTime);
                } else break;
            }
        }

        // Check status code
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
        delayStep(minWaitTime);
    }

    public static void createShopPhotoAvatar() {
        String imagePath = getProjectDir() + "/images/shops/avatar.jpg";
        Response response =
                RestAssure.instance().postImageFile(global_accessToken, gatewayCreateShopAvatar, imagePath);

        // Check status code
        String responseCode = getResponseCode(response);
        String responseMsg = getPropertyValue(response, "message");
        if (getTempRetryCreateShop() > 0) {
            for (int i = 0; i < getTempRetryCreateShop(); i++) {
                if (!responseCode.equals("200")) {
                    setExceptionDebug(
                            "Retry To Create Shop Avatar "
                                    + (i + 1)
                                    + " time(s): "
                                    + String.format("ERR[%s] MSG[%s]", responseCode, responseMsg));
                    response =
                            RestAssure.instance()
                                    .postImageFile(global_accessToken, gatewayCreateShopAvatar, imagePath);
                    responseCode = getResponseCode(response);
                    responseMsg = getPropertyValue(response, "message");
                    delayStep(minWaitTime);
                } else break;
            }
        }

        // Check status code
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
        delayStep(minWaitTime);
    }
}
