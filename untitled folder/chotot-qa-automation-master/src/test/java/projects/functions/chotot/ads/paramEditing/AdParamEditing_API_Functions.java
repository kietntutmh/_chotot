package projects.functions.chotot.ads.paramEditing;

import com.vn.chotot.exception.FailureHandling;
import com.vn.chotot.logger.Log4jFactory;
import io.restassured.response.Response;
import org.apache.logging.log4j.Logger;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertFashion;

import java.util.Arrays;
import java.util.List;

import static api.configuration.DataConfig.tempAdID;
import static api.configuration.GatewayConfig.*;
import static com.vn.chotot.keywords.selenium.Utils.delayStep;
import static com.vn.chotot.keywords.selenium.Utils.verifyNotEquals;
import static io.restassured.RestAssured.given;
import static projects.functions.APISupportFunctions.getResponseBodyAsString;
import static projects.functions.APISupportFunctions.getResponseData;

public class AdParamEditing_API_Functions {
    final Logger log = Log4jFactory.instance().createClassLogger(AdParamEditing_API_Functions.class);
    Response response = null;

    static CM_API_Ads_InsertFashion cm_api_ads_insertFashion = new CM_API_Ads_InsertFashion();

    public void postPrivateAdByCateID(String cate_id, String cp_action) {
        log.info("CATEGORY_ID: " + cate_id);
        switch (cate_id) {
            case "3030":
                cm_api_ads_insertFashion.insertNewAdClothes(cp_action);
                break;
            default:
                break;
        }
    }

    public List<String> getWaitEditingAd() {
        response = given().header("token", global_accessTokenCP).get(getGatewayCPHost() + gatewayCPWaitEditingAd);
        log.info(getResponseBodyAsString(response));
        String action_id = getResponseData(response, "$.action_id");
        String ad_id = getResponseData(response, "$.ad_id");
        return Arrays.asList(ad_id, action_id);
    }

    public String getActionQueue(int action_id) {
        response = given().params("ad_id", tempAdID,"action_id", action_id).get(getGatewayCPHost() +gatewayCPLoadAction);
        String action_queue = getResponseData(response, "$.actions.queue");
        log.info("Action queue: " + action_queue);
        return action_queue;
    }

    public void checkProcessingTimeAdParamEditing() {
        // Check in 120 seconds
        for(int i=1;i<=12;i++){
            if(getActionQueue( 1).equals("mamaphp_waitediting")) {
                log.info(String.format("Ad editing param for <%s> is in progress %d seconds", tempAdID, i*10));
            } else {
                break;
            }
            delayStep(10);
        }
        verifyNotEquals(getActionQueue( 1), "mamaphp_waitediting", FailureHandling.STOP_ON_FAILURE);
    }

}
