package api.feature.ads;

import com.vn.chotot.api.rest_assured.RestAssure;
import com.vn.chotot.exception.FailureHandling;
import io.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

import static api.configuration.GatewayConfig.gatewayShop_HideAdOnDashboard;
import static api.configuration.GatewayConfig.global_accessToken;
import static com.vn.chotot.api.rest_assured.VerifyRespone.verifyStatusCode;
import static com.vn.chotot.helper.Utils.convertMapToJsonString;

public class HideAds {

    public static void hideExistingAd(String adID, String shopAlias) {
        // Body data
        Map<Object, Object> mapData = new HashMap<>();
        mapData.put("reason", "2");
        mapData.put("status", "hidden");
        if (!StringUtils.isEmpty(shopAlias)) mapData.put("shop_alias", shopAlias);
        String bodyData = convertMapToJsonString(mapData);

        // Hide ad
        String url = String.format(gatewayShop_HideAdOnDashboard, adID);
        Response response = RestAssure.instance().put(global_accessToken, bodyData, url);

        // Check status code
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
    }
}
