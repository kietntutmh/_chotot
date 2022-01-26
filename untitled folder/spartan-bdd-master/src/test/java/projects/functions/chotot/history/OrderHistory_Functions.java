package projects.functions.chotot.history;

import com.vn.chotot.api.rest_assured.RestAssure;
import com.vn.chotot.exception.FailureHandling;
import com.vn.chotot.logger.Log4jFactory;
import io.restassured.response.Response;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import projects.functions.APISupportFunctions;

import static api.configuration.GatewayConfig.gatewayOrderHistory_GetDetail;
import static com.vn.chotot.configuration.WaitTime.maxWaitTime;
import static com.vn.chotot.keywords.selenium.Utils.delayStep;
import static com.vn.chotot.keywords.selenium.Utils.verifyEquals;

public class OrderHistory_Functions extends APISupportFunctions {
    public static String query = "";
    static final Logger log = Log4jFactory.instance().createClassLogger(OrderHistory_Functions.class);
    private static int tempRetryGetOrderDetail = 3;

    @Deprecated
    public static void verifyOrderDetailHaveAdFeature(String orderID,String label){
        Response response = null;
        // Retry to check
        for (int i = 0; i < tempRetryGetOrderDetail; i++) {
            // Wait for order indexing
            delayStep(maxWaitTime * 3);

            // Post data
            response = RestAssure.instance().get(String.format(gatewayOrderHistory_GetDetail, orderID));
            verifyStatusCode200("ORDER-HISTORY", String.format(gatewayOrderHistory_GetDetail, orderID));

            query = "$.services[0].service_status";
            String actualServiceStatus = getResponseData(response, query);

            if (compareStatusCode(response, "200") && actualServiceStatus.contentEquals("delivering")) {
                log.info(getResponseBodyAsString(response));
                break;
            } else {
                debugResponse(response);
                log.debug("\n----- Retry verify order detail " + (i + 1) + " time(s)!!!!!");
            }
        }

        query = "$.services[0].type";
        String actualServiceType = getResponseData(response, query);
        verifyEquals(actualServiceType,"ad_feature", FailureHandling.STOP_ON_FAILURE);

        query = "$.services[0].service_status";
        String actualServiceStatus = getResponseData(response, query);
        if(actualServiceStatus.isEmpty()){
            Assert.assertEquals(actualServiceStatus,"");
        }else{
            Assert.assertEquals(actualServiceStatus,"delivering");
        }

        query = "$.services[0].service_name";
        String actualServiceName = getResponseData(response, query);
        // lable should be Nhãn HOT, Nhãn GIÁ TỐT
        verifyEquals(actualServiceName,label,FailureHandling.STOP_ON_FAILURE);
    }
}
