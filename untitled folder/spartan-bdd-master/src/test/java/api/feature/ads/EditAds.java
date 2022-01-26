package api.feature.ads;

import projects.functions.APISupportFunctions;

import static api.configuration.DataConfig.*;
import static api.configuration.GatewayConfig.gatewayFlashAdEdit;
import static api.configuration.GatewayConfig.global_accessToken;
import static com.vn.chotot.configuration.WaitTime.minWaitTime;
import static com.vn.chotot.keywords.selenium.Utils.delayStep;

public class EditAds extends APISupportFunctions {

    public static String editNewAd(String bodyData) {
        // Add new ad
        delayStep(minWaitTime); // delay to wait for server more stability

        if (getTempRetryEditAd() > 0) {
            for (int i = 0; i < getTempRetryEditAd(); i++) {
                try{
                    response = post(global_accessToken, bodyData, gatewayFlashAdEdit);
                    verifyStatusCode200("API DEAD: CAN'T INSERT AD \n" + getResponseData("$.message"));
                    break;
                }catch (AssertionError assertionError){
                    waitConstant(3);
                }
            }
        }

        debugResponse();
        // Check status code
        verifyStatusCode200("API DEAD: CAN'T EDIT AD \n" + getResponseData("$.message"));

        // using for Pos - create data by API
        tempAdID = getResponseData("$.ad_id");
        tempAdName = getPropertyFromJsonStr(bodyData, "subject");
        isEditedAd = true;

        // Return ad_id
        return tempAdID;
    }
}
