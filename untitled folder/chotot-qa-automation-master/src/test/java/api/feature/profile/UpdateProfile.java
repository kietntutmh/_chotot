package api.feature.profile;

import com.vn.chotot.api.rest_assured.RestAssure;
import io.restassured.response.Response;
import org.testng.Assert;
import projects.functions.APISupportFunctions;

import java.util.HashMap;
import java.util.Map;

import static api.configuration.DataConfig.newUserPhone;
import static api.configuration.GatewayConfig.*;
import static com.vn.chotot.api.rest_assured.VerifyRespone.getPropertyValue;
import static com.vn.chotot.helper.Utils.convertMapToJsonString;
import static desktop.configuration.LoginConfig.tempUserEmail;

public class UpdateProfile extends APISupportFunctions {

    public static void updateDefaultProfile() {
        updateDefaultProfile(global_accessToken);
    }

    public static void updateDefaultProfile(String userToken) {
        // Set random email
        String email = "auto_test_" + newUserPhone + "@gmail.com";
        tempUserEmail = email;

        initBodyJson();
        bodyJson.addProperty("full_name", "Auto Test");
        bodyJson.addProperty("address", "123 Ngô Đức Kế, Auto QC");
        bodyJson.addProperty("email", email);
        bodyJson.addProperty("favorite", "[]");

        // Post data
        response = put(userToken, bodyJson, gatewayProfile);
        debugResponse();
        verifyStatusCode200("USER PROFILE", gatewayProfile);
        Assert.assertNotNull(getResponseData("$.account_id"));
        newUserPhone = getResponseData("$.phone");      // VUHOANG DEBUG: Should merge tempUserPhone and newUserPhone
    }

    public static void updateProfileEmail(String userToken, String email) {
        // Set default info
        Map<Object, Object> mapData = new HashMap<>();
        mapData.put("email", email);
        String bodyData = convertMapToJsonString(mapData);

        // Post data
        Response response =
                RestAssure.instance().put(userToken, bodyData, gatewayProfile);
        verifyStatusCode(response, "200");
    }

    public static String getAccountID() {
        // Get account id
        response = get(global_accessToken, gatewaySelfProfile);
        verifyStatusCode200("SELF PROFILE", gatewaySelfProfile);
        return getPropertyValue(response, "account_id");
    }
}
