package api.feature.cp;

import io.restassured.response.Response;
import org.testng.Assert;
import projects.functions.APISupportFunctions;

import static api.configuration.DataConfig.getTempRetryCPActionAd;
import static api.configuration.DataConfig.tempListID;
import static api.configuration.GatewayConfig.*;
import static api.utils.CheckAccessToken.setAccessTokenOfCP;
import static com.vn.chotot.api.rest_assured.VerifyRespone.getPropertyValue;
import static com.vn.chotot.configuration.WaitTime.maxWaitTime;
import static com.vn.chotot.keywords.selenium.Utils.delayStep;

public class AcceptCP extends APISupportFunctions {
    public static void acceptNewAd(String bodyData) {
        // Set token
        if (getTempRetryCPActionAd() > 0) {
            for (int i = 0; i < getTempRetryCPActionAd(); i++) {
                try{
                    setAccessTokenOfCP();
                    response = post(global_accessTokenCP, bodyData, getGatewayCPHost() + gatewayCPAcceptAd);
                    verifyStatusCode200(response, "CP", gatewayCPAcceptAd);
                    Assert.assertTrue(getResponseDataInt("$.list_id") > -1);
                    break;
                }catch (AssertionError error){
                    waitConstant(3);
                }
            }
        }
        debugResponse();
        verifyStatusCode200(response, "CP", gatewayCPAcceptAd);
        Assert.assertTrue(getResponseDataInt("$.list_id") > -1);
        tempListID = getPropertyValue(response, "list_id");
        waitForListIDExistedOnListing(tempListID);
    }

    private static void waitForListIDExistedOnListing(String listID) {
        if (global_accessToken != "") {
          Response res;
          String code;
          for (int i = 0; i < 10; i++) {
            res = get(global_accessToken, gatewayAdListing_GetListID + "/" + listID);
            code = getResponseCode(res);
            if(code.equals("200")){
                System.out.println(String.format("ListID <%s> is shown on listing !!!", listID));
                return;
            }
            System.out.println(String.format("ListID <%s> is NOT shown on listing !!!", listID));
            delayStep(maxWaitTime);
          }
        }
    }
}
