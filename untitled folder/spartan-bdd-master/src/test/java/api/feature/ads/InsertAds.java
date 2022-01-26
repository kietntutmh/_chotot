package api.feature.ads;

import api.configuration.GatewayConfig;
import com.vn.chotot.logger.Log4jFactory;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import projects.functions.APISupportFunctions;

import static api.configuration.DataConfig.*;
import static api.configuration.GatewayConfig.global_accessToken;
import static com.vn.chotot.configuration.WaitTime.minWaitTime;
import static com.vn.chotot.keywords.selenium.Utils.delayStep;

public class InsertAds extends APISupportFunctions{

    static final Logger log = Log4jFactory.instance().createClassLogger(InsertAds.class);

    public static String insertNewAd(String bodyData, String userToken) {
        return insertNewAd(bodyData, userToken, "200");
    }

    public static String insertNewAd(String bodyData) {
        return insertNewAd(bodyData, global_accessToken, "200");
    }

    public static String insertNewAd(String bodyData, String userToken, String expectedResponseCode) {
        // Add new ad
        delayStep(minWaitTime); // delay to wait for server more stability

        if (getTempRetryInsertAd() > 0) {
            for (int i = 0; i < getTempRetryInsertAd(); i++) {
                try{
                    response = post(userToken, bodyData, GatewayConfig.gatewayFlashAdNew);
                    verifyStatusCode200("API DEAD: CAN'T INSERT AD \n" + getResponseData("$.message")); //VUHOANG MAINTAIN (add more log.debug)
                    //                    log.debug("Retry Insert AD in " + (i + 1) + " time(s)");
                    tempAdID = getResponseData("$.ad_id");
                    Assert.assertFalse(tempAdID.isEmpty(), "NO NEW AD IS CREATED");
                    break;
                }catch (AssertionError e){
                    waitConstant(3);
                }
            }
        }

        debugResponse();
        // Check status code
        verifyStatusCode200("API DEAD: CAN'T INSERT AD \n" + getResponseData("$.message"));
        Assert.assertFalse(tempAdID.isEmpty());

        // using for Pos - create data by API
        tempUID = getResponseData("$.uid");
        tempAdName = getPropertyFromJsonStr(bodyData, "subject");

//        log.info("RESPONSE INSERT AD: " + getResponseBodyAsString(response));

        // Return ad_id
        return tempAdID;
    }
}
