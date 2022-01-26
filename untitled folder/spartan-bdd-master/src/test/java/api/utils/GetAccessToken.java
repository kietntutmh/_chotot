package api.utils;

import api.configuration.GatewayConfig;
import com.vn.chotot.api.rest_assured.RestAssure;
import com.vn.chotot.data.ExcelProvider;
import com.vn.chotot.logger.Log4jFactory;
import io.cucumber.java.bs.A;
import io.restassured.response.Response;
import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import projects.functions.APISupportFunctions;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static api.configuration.DataConfig.loginExcelFile;
import static api.configuration.DataConfig.loginSheetName;
import static api.configuration.DataConfig.*;
import static api.configuration.GatewayConfig.*;
import static api.feature.profile.UpdateProfile.updateDefaultProfile;
import static api.feature.register.Register.createNewAccountWithDefaultPassword;
import static api.feature.register.Register.verifyOTP;
import static com.vn.chotot.api.rest_assured.VerifyRespone.getPropertyValue;
import static com.vn.chotot.exception.ExceptionHandler.setExceptionDebug;
import static com.vn.chotot.helper.Utils.convertMapToJsonString;
import static com.vn.chotot.helper.Utils.sha1Decode;
import static desktop.configuration.CPConfig.checkTempAccountCPUsed;
import static desktop.configuration.CPConfig.getTempAccountCPIndex;
import static desktop.configuration.LoginConfig.*;

public class GetAccessToken extends APISupportFunctions {

    static final Logger log = Log4jFactory.instance().createClassLogger(GetAccessToken.class);
    public static String current_phone = "";
    public static String previous_phone = "";
    public static ArrayList<User> listNewUser = new ArrayList<>();

    public static class User {
        public String userPhone;
        public String accountID;
        public String accountOID;

        User(String userPhone, String accountID, String accountOID) {
            this.userPhone = userPhone;
            this.accountID = accountID;
            this.accountOID = accountOID;
        }
    }

    private static User switchToOtherUser(int indexUser) {
        return listNewUser.get(indexUser);
    }

    public static void switchLoginDataToOtherUser(int indexUser) {
        User newUser = switchToOtherUser(indexUser);
        current_phone = newUser.userPhone;
        global_accountID = newUser.accountID;
        global_accountOID = newUser.accountOID;
        getAccessTokenOfUser(current_phone, defaultPassword, false);
    }

    public static String getAccessTokenOfExistingUser() {
        // Set cert flag
        RestAssure.instance().restAssuredConfig(GatewayConfig.allowAllCertFlag);

        // Setup test data
        ExcelProvider excelProvider = new ExcelProvider();
        excelProvider.getExcelFileSheet(loginExcelFile, loginSheetName);

        // Get data in excel
        String phone;
        String password;

        if (checkTempAccountAPIUsed() & getTempAccountAPIIndex() > 0) {
            phone = excelProvider.getCellData(getTempAccountAPIIndex(), 0);
            password = excelProvider.getCellData(getTempAccountAPIIndex(), 1);
            log.info("\n----- Get access token of temp user phone (API): " + phone);
        } else if (checkTempAccountUsed() & getTempAccountLoginIndex() > 0) {
            phone = excelProvider.getCellData(getTempAccountLoginIndex(), 0);
            password = excelProvider.getCellData(getTempAccountLoginIndex(), 1);
            log.info("\n----- Get access token of temp user phone (UI): " + phone);
        } else {
            phone = excelProvider.getCellData(getMainAccountAPIIndex(), 0);
            password = excelProvider.getCellData(getMainAccountAPIIndex(), 1);
            log.info("\n----- Get access token of default user phone (API): " + phone);
        }
        setExceptionDebug("Get access token of existing phone: " + phone);

        // return existing access_token
        if (phone.equalsIgnoreCase(previous_phone)) {
            if (!global_accessToken.isEmpty())
                return global_accessToken;
        }

        // Post login data
//        String body = "\n{\n\"phone\": \"" + phone + "\",\n" + "\t\"password\": \"" + password + "\"}";
        initBodyJson();
        bodyJson.addProperty("phone", phone);
        bodyJson.addProperty("password", password);
        response = post(bodyJson, gatewayLogin);

        // Check status code
        try {
            verifyStatusCode200("LOGIN", gatewayLogin);

            // Set access_token
            String access_token = getResponseData("$.access_token");
            global_accessToken = "Bearer " + access_token;

            // Using for CP API
            global_accountID = getResponseData("$.profile.account_id");
            global_accountOID = getResponseData("$.profile.account_oid");
//            global_accountID = getPropertyValue(response, "profile.account_id");
//            global_accountOID = getPropertyValue(response, "profile.account_oid");
            tempUserPhone = phone;
            current_phone = phone;
            return global_accessToken;
        } catch (AssertionError e) {
            return null;
        }
    }

    public static String getAccessTokenOfNewUser() {
        // Create and get the phone of new user
        String phone = createNewAccountWithDefaultPassword();
        initBodyJson();
        bodyJson.addProperty("phone", phone);
        bodyJson.addProperty("password", defaultPassword);
        for (int i = 0; i < 5; i++) {
            try {
                response = post(bodyJson, gatewayLogin);
                verifyStatusCode200(response, "LOGIN", gatewayLogin);
                Assert.assertNotNull(getResponseData("$.access_token"));
                Assert.assertFalse(getResponseData("$.access_token").isEmpty());
                break;
            } catch (AssertionError e){
                waitConstant(3);
            }
        }
        verifyStatusCode200(response, "LOGIN", gatewayLogin);
        Assert.assertFalse(getResponseData("$.access_token").isEmpty());
        global_accessToken = "Bearer " + getResponseData("$.access_token");
        global_accountID = getResponseData("$.profile.account_id");
        global_accountOID = getResponseData("$.profile.account_oid");

        // Verify OTP
        verifyOTP();

        // Update Profile
        updateDefaultProfile(global_accessToken);

        // Setup global variables
        newUserPhone = phone;
        newUserID = global_accountID;
        tempUserPhone = phone;

        // Need to remove
        try {
            User newUser = new User(phone, global_accountID, global_accountOID);        // Scobby
            listNewUser.add(newUser);
        } catch (Exception e){
            // Vuhoang makes sure Scobby won't stuck other features when the above script failed
        }

        return global_accessToken;
    }

    @Deprecated     // OLD FUNCTION
    public static String getAccessTokenOfNewUser_OLD() {
        // Set cert flag
        RestAssure.instance().restAssuredConfig(GatewayConfig.allowAllCertFlag);

        // Create and get the phone of new user
        String phone = createNewAccountWithDefaultPassword();

        // Create body data
        String body = "\n{\n\"phone\": \"" + phone + "\",\n" + "\t\"password\": \"" + defaultPassword + "\"}";

        setExceptionDebug("Get access token of new phone: " + phone);
        log.info("\n----- Get access token of new user phone: " + phone);

        // Post login data
        Response response = RestAssure.instance().post(body, GatewayConfig.gatewayLogin);

        // Check status code
        try {
            verifyStatusCode200(response, "LOGIN", gatewayLogin);

            // Set access_token
            String access_token = getPropertyValue(response, "access_token");
            global_accessToken = "Bearer " + access_token;

            // Using for CP
            global_accountID = getPropertyValue(response, "profile.account_id");
            global_accountOID = getPropertyValue(response, "profile.account_oid");

            // Verify OTP in maximum 15 time to prevent outdated OTP
            verifyOTP();

            updateDefaultProfile(global_accessToken);
            newUserPhone = phone;
            newUserID = global_accountID;
            tempUserPhone = phone;

            // Add new user to listUSer
            User newUser = new User(phone, global_accountID, global_accountOID);        //VUHOANG NEEDS CHECK
            listNewUser.add(newUser);

            return global_accessToken;
        } catch (AssertionError e) {
            return null;
        }
    }

    public static String getAccessTokenOfCPUser() {
        // Setup test data
        ExcelProvider excelProvider = new ExcelProvider();
        excelProvider.getExcelFileSheet(loginExcelFile, loginCPSheetName);

        // Get data in excel
        String userName;
        String password;

        // Check temp account
        if (checkTempAccountCPAPIUsed() & getTempAccountCPAPIIndex() > 0) {
            userName = excelProvider.getCellData(getTempAccountCPAPIIndex(), 0);
            password = excelProvider.getCellData(getTempAccountCPAPIIndex(), 1);
            log.info("\n----- Get access token of temp CP user (API): " + userName);
        } else if (checkTempAccountCPUsed() & getTempAccountCPIndex() > 0) {
            userName = excelProvider.getCellData(getTempAccountCPIndex(), 0);
            password = excelProvider.getCellData(getTempAccountCPIndex(), 1);
            log.info("\n----- Get access token of temp CP user (UI): " + userName);
        } else {
            userName = excelProvider.getCellData(getMainAccountCPAPIIndex(), 0);
            password = excelProvider.getCellData(getMainAccountCPAPIIndex(), 1);
            log.info("\n----- Get access token of main CP user: " + userName);
        }

        // Set cert flag
        RestAssure.instance().restAssuredConfig(allowAllCertFlag);

        // Hash password
        String passwordSHA1 = sha1Decode(password);

        // Set body
        Map<Object, Object> mapData = new HashMap<>();
        mapData.put("username", userName);
        mapData.put("passwd", passwordSHA1);
        mapData.put("remote_addr", getGatewayCPRemoteAddress());
        String body = convertMapToJsonString(mapData);

        Response response = RestAssure.instance().post(body, getGatewayCPHost() + gatewayCPLogin);
        log.info("Message Login CP:" + getPropertyValue(response, "message"));

        try {
            verifyStatusCode200(response, "LOGIN CP", gatewayCPLogin);

            // Set access_token to global
            global_accessTokenCP = getPropertyValue(response, "token");
            global_accountPhoneCP = userName;
            global_accountPasswordCP = password;
            return global_accessTokenCP;
        } catch (AssertionError e) {
            return null;
        }
    }

    public static String getAccessTokenOfUser(String phone, String password) {
        return getAccessTokenOfUser(phone, password, false);
    }

    public static String getAccessTokenOfUser(String phone, String password, boolean isUpdateProfile) {
        // Set cert flag
        RestAssure.instance().restAssuredConfig(GatewayConfig.allowAllCertFlag);

        // Create body data
        String body =
                "\n{\n\"phone\": \"" + phone + "\",\n" + "\t\"password\": \"" + password + "\"}";

        log.info("\n----- Get access token of new user phone: " + phone);

        for (int i = 0; i < 5; i++) {
            try {
                response = post(body, gatewayLogin);
                verifyStatusCode200(response, "LOGIN CP", gatewayCPLogin);
                // Set access_token
                String access_token = getPropertyValue(response, "access_token");
                global_accessToken = "Bearer " + access_token;

                // Using for CP
                if (isUpdateProfile) {
                    updateDefaultProfile(global_accessToken);
                }

                newUserID = getPropertyValue(response, "profile.account_id");
                global_accountID = newUserID;
                newUserPhone = phone;
                tempUserPhone = phone;
                return global_accessToken;
            } catch (AssertionError e) {
                return null;
            }
        }
        return null;
    }

    public static String getAccessTokenOfCPGrantPermission() {
        String userName = "vuhoang12";
        String password = defaultPassword;

        // Set cert flag
        RestAssure.instance().restAssuredConfig(allowAllCertFlag);

        // Hash password
        String passwordSHA1 = sha1Decode(password);

        // Set body
        Map<Object, Object> mapData = new HashMap<>();
        mapData.put("username", userName);
        mapData.put("passwd", passwordSHA1);
        mapData.put("remote_addr", getGatewayCPRemoteAddress());
        String body = convertMapToJsonString(mapData);

        Response response = RestAssure.instance().post(body, getGatewayCPHost() + gatewayCPLogin);
        log.info("Message Login CP:" + getPropertyValue(response, "message"));

        try {
            verifyStatusCode200(response, "LOGIN CP", gatewayCPLogin);
            // Set access_token to global
            global_accessTokenCP = getPropertyValue(response, "token");
            return global_accessTokenCP;
        } catch (AssertionError e) {
            return null;
        }
    }

    public static String standardizeToken(String inputToken) {
        if (!inputToken.startsWith("Bearer"))
            return "Bearer " + inputToken.trim();
        else
            return inputToken.trim();
    }

    public static String getBasicAuthorization(String username, String password) {
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.UTF_8));
        return "Basic " + new String(encodedAuth);
    }
}
