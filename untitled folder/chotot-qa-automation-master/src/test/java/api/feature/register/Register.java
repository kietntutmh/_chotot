package api.feature.register;

import api.configuration.GatewayConfig;
import com.vn.chotot.logger.Log4jFactory;
import org.apache.logging.log4j.Logger;
import projects.functions.APISupportFunctions;

import java.util.HashMap;
import java.util.Map;

import static api.configuration.DataConfig.*;
import static api.configuration.GatewayConfig.gatewayVerifyOTP;
import static api.configuration.GatewayConfig.global_accessToken;
import static api.feature.profile.UpdateProfile.updateDefaultProfile;
import static api.utils.GetAccessToken.getAccessTokenOfNewUser;
import static com.vn.chotot.api.rest_assured.VerifyRespone.getPropertyValue;
import static com.vn.chotot.exception.ExceptionHandler.setExceptionDebug;
import static com.vn.chotot.helper.Utils.convertMapToJsonString;

public class Register extends APISupportFunctions {

    static final Logger log = Log4jFactory.instance().createClassLogger(Register.class);

    public static String createNewAccountWithDefaultPassword() {
        // Post data with max retry times is 20 -> TO FIX concurrent issues
        for (int i = 1; i <= 12; i++) {
            // Get unique phone
            String uniquePhone = getUniquePhone();

            // Create register data
            Map<Object, Object> mapData = new HashMap<>();
            mapData.put("phone", uniquePhone);
            mapData.put("password", defaultPassword);
            mapData.put("disable_otp", true);
            String bodyData = convertMapToJsonString(mapData);

            // Post data
            response = post(bodyData, GatewayConfig.gatewayRegister);

            // Check status code
            try {
                verifyStatusCode200("REGISTER FAILED");
                break;
            } catch (AssertionError error) {
                waitConstant(3);
            }
        }
        verifyStatusCode200("REGISTER API FAILED");

        // Return the phone of new user
        String phone = getResponseData("$.profile.phone");
        newUserPhone = phone;
        log.info("\n----- The phone number of new user: " + phone);

        // Set debug log
        setExceptionDebug("New user phone (API): " + newUserPhone);

        // Get info of Account
        tempAccountID = getPropertyValue(response, "profile.account_id");

        return phone;
    }

    private static String getUniquePhone() {
        String currentTime = String.valueOf(System.currentTimeMillis());
        String uniqueNumber = currentTime.substring(currentTime.length() - 7);
        uniqueNumber = uniqueNumber.length() < 7 ? uniqueNumber + "1" : uniqueNumber;
        int prefixPhone = (int) (Math.random() * 6) + 3;
        return "03" + prefixPhone + uniqueNumber;
    }

    public static void verifyOTP() {
        for (int i = 0; i < getTempRetryRegister(); i++) {
            try {
                initBodyJson();
                bodyJson.addProperty("otp", defaultOTP);
                response = post(global_accessToken, bodyJson, gatewayVerifyOTP);
                verifyStatusCode200("API VERIFY OTP DEAD");
                break;
            } catch (AssertionError error) {
                waitConstant(3);

                //Send an incorrect OTP to refresh OTP
                initBodyJson();
                bodyJson.addProperty("otp", "111111");
                response = post(global_accessToken, bodyJson, gatewayVerifyOTP);
                log.debug("Retry Register " + (i + 1) + " time(s)");
            }
        }
        verifyStatusCode200("API VERIFY OTP DEAD");
        debugResponse();
    }

    public static void createNewRandomUser() {
        getAccessTokenOfNewUser();
        updateDefaultProfile();
    }
}
