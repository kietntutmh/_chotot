package api.feature.cp;

import projects.functions.APISupportFunctions;

import static api.configuration.DataConfig.getTempRetryCPActionAd;
import static api.configuration.GatewayConfig.*;
import static api.utils.CheckAccessToken.setAccessTokenOfCP;

public class RejectCP extends APISupportFunctions {
    public static void rejectNewAd(String bodyData) {
//        String responseCode = getResponseCode(response);
//        String responseMsg = getPropertyValue(response, "message");

        if (getTempRetryCPActionAd() > 0) {
            for (int i = 0; i < getTempRetryCPActionAd(); i++) {
                try{
                    setAccessTokenOfCP();
                    response = post(global_accessTokenCP, bodyData, getGatewayCPHost() + gatewayCPRejectAd);
                    verifyStatusCode200("REJECT AD FAILED");
                    break;
                }catch (AssertionError assertionError){
                    waitConstant(3);
                }
            }
        }
        debugResponse();
        // Check status code
        verifyStatusCode200("REJECT AD FAILED");
    }
}
