package api.feature.chat;

import com.vn.chotot.api.rest_assured.RestAssure;
import com.vn.chotot.exception.FailureHandling;
import io.restassured.response.Response;

import static api.configuration.DataConfig.getTempRetryCPActionAd;
import static api.configuration.DataConfig.getTempRetrySendChatMessage;
import static api.configuration.GatewayConfig.*;
import static com.vn.chotot.api.rest_assured.VerifyRespone.*;
import static com.vn.chotot.configuration.WaitTime.minWaitTime;
import static com.vn.chotot.exception.ExceptionHandler.setExceptionDebug;
import static com.vn.chotot.keywords.selenium.Utils.delayStep;
import static com.vn.chotot.keywords.selenium.Utils.verifyMatch;

public class SendMessage {

    public static Response sendMessageWithFilterKeyword(String bodyData) {
        delayStep(minWaitTime);
        Response response = RestAssure.instance().post(global_accessToken, bodyData, gatewayChatSendMessage);

        String responseCode = getResponseCode(response);
        String responseMsg = getPropertyValue(response, "message");

        if (getTempRetryCPActionAd() > 0) {
            for (int i = 0; i < getTempRetrySendChatMessage(); i++) {
                if (!responseCode.equals("200")) {
                    setExceptionDebug("Retry Send Chat Message " + (i + 1) + " time(s): " + String.format("ERR[%s] MSG[%s]", responseCode, responseMsg));
                    response = RestAssure.instance().post(global_accessTokenCP, bodyData, gatewayChatSendMessage);
                    delayStep(minWaitTime);
                }
                break;
            }
        }

        // Check status code
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);

        return response;
    }

    public void verifyFilterKeywordMaskAsStar(String bodyData) {
        Response response = sendMessageWithFilterKeyword(bodyData);

        String result = getPropertyValue(response, "result.text");

        verifyMatch(result, "***", false, FailureHandling.CONTINUE_ON_FAILURE);
    }
}
