package api.feature.deeplink;

import api.configuration.GatewayConfig;
import api.feature.ads.InsertAds;
import com.vn.chotot.api.rest_assured.RestAssure;
import com.vn.chotot.exception.FailureHandling;
import com.vn.chotot.logger.Log4jFactory;
import io.restassured.response.Response;
import org.apache.logging.log4j.Logger;

import static com.vn.chotot.api.rest_assured.VerifyRespone.getPropertyValue;
import static com.vn.chotot.api.rest_assured.VerifyRespone.verifyStatusCode;
import static com.vn.chotot.configuration.WaitTime.minWaitTime;
import static com.vn.chotot.keywords.selenium.Utils.delayStep;

public class DeepLink {
    static Logger log = Log4jFactory.instance().createClassLogger(InsertAds.class);

    public static Response checkDeepLink(String bodyData) {
        // Get info deep link response
        delayStep(minWaitTime); // delay to wait for server more stability
        Response response = RestAssure.instance().get(GatewayConfig.gatewayDeepLink);

        // Check status code
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);

        // Get info response
        String region = getPropertyValue(response, "region");
        String category = getPropertyValue(response, "category");
        String adType = getPropertyValue(response, "adType");
        String regionObj = getPropertyValue(response, "regionObj");
        String regionObjV2 = getPropertyValue(response, "regionObjV2");
        String success = getPropertyValue(response, "success");
        String paramObj = getPropertyValue(response, "paramObj");

        return response;
    }
}
