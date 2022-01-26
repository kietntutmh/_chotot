package projects.functions.chotot.reward;

import org.testng.Assert;
import projects.functions.APISupportFunctions;

import java.util.ArrayList;
import java.util.List;

import static api.configuration.GatewayConfig.*;
import static api.utils.GetAccessToken.getAccessTokenOfNewUser;
import static desktop.configuration.LoginConfig.tempUserPhone;
import static projects.functions.chotot.hierarchy.AccountHierarchy_Functions.checkTopupBalanceDTFree;

public class Reward_API_Functions extends APISupportFunctions {
    private static String userToken;
    private static String userAccountID;
    private static String userPhone;
    private static int userPoint;
    private static int userPointRedeem;
    private static int userPointAfterRedeem;
    private static String userCode;
    public static List<String> allMyCodes = new ArrayList<>();

    public static void setNewUserInfo() {
        getAccessTokenOfNewUser();
        setUserToken(global_accessToken);
        setUserAccountID(global_accountID);
        setUserPhone(tempUserPhone);
        setUserPoint(0);
    }

    public static String getUserToken() {
        return userToken;
    }

    public static void setUserToken(String userToken) {
        Reward_API_Functions.userToken = userToken;
    }

    public static String getUserAccountID() {
        return userAccountID;
    }

    public static void setUserAccountID(String userAccountID) {
        Reward_API_Functions.userAccountID = userAccountID;
    }

    public static String getUserPhone() {
        return userPhone;
    }

    public static void setUserPhone(String userPhone) {
        Reward_API_Functions.userPhone = userPhone;
    }

    public static int getUserPoint() {
        return userPoint;
    }

    public static void setUserPoint(int userPoint) {
        Reward_API_Functions.userPoint = userPoint;
    }

    public static String getUserCode() {
        return userCode;
    }

    public static void setUserCode(String userCode) {
        Reward_API_Functions.userCode = userCode;
    }

    public static int getUserPointRedeem() {
        return userPointRedeem;
    }

    public static void setUserPointRedeem(int userPointRedeem) {
        Reward_API_Functions.userPointRedeem = userPointRedeem;
    }

    public static int getUserPointAfterRedeem() {
        return userPointAfterRedeem;
    }

    public static void setUserPointAfterRedeem(int userPointAfterRedeem) {
        Reward_API_Functions.userPointAfterRedeem = userPointAfterRedeem;
    }

    //
    public static void addPointToUser(int point) {
        addPointToUser(getUserAccountID(), point);
    }

    public static void addPointToUser(String userAccountID, int point) {
        initBodyJson();
        bodyJson.addProperty("account_id", Integer.valueOf(userAccountID));
        bodyJson.addProperty("message", "Add point");
        bodyJson.addProperty("point", point);
        for (int i = 0; i < 5; i++) {
            try {
                response = post(bodyJson, gatewayReward_AddPoint_Internal);
                verifyStatusCode200("API Add point to User ID: " + userAccountID);
                break;
            } catch (AssertionError error) {
                waitConstant(3);
            }
        }
        verifyStatusCode200("API Add point to User ID: " + userAccountID);
        Assert.assertEquals(getResponseData(response, "$.message"), "ok",
                "ADD " + point + " TO ACCOUNT " + userAccountID + " UNSUCCESSFULLY");
        debugResponse();
    }

    public static void verifyPointReward(int expectedPoint) {
        verifyPointReward(getUserToken(), expectedPoint);
    }

    public static void verifyPointReward(String userToken, int expectedPoint) {
        response = get(userToken, gatewayReward_GetPoint_Private);
        verifyStatusCode200("API get point of User ID: " + userAccountID);
        int actualPoint = getResponseDataInt(response, "$.point");
        Assert.assertEquals(actualPoint, expectedPoint,
                String.format("FAILED CHECK USER POINT USER[%s] POINT[%d] ACTUAL_POINT[%d]", getUserAccountID(), expectedPoint, actualPoint));
    }

    //----------- Generate Code -----------
    public static String generateCode5k(String accountID) {
        return generateCode(accountID, 5000);
    }

    public static String generateCode10k(String accountID) {
        return generateCode(accountID, 10000);
    }

    public static String generateCode15k(String accountID) {
        return generateCode(accountID, 15000);
    }

    public static String generateCode(String accountID, int DongTot) {
        initBodyJson();
        bodyJson.addProperty("accountId", accountID);
        bodyJson.addProperty("dongtot", DongTot);
        response = post(bodyJson, gatewayReward_GenerateCode);
        debugResponse();
        verifyStatusCode200("API GENERATE CODE IS DEAD");
        String code = getResponseData(response, "$.code.code");
        setUserCode(code);
        System.out.println("DEBUG: " + code);
        return code;
    }

    public static void redeemCodeToDongTot(String userToken, String userCode) {
        initBodyJson();
        bodyJson.addProperty("code", userCode);
        bodyJson.addProperty("ip_address", "0.0.0.0");
        response = post(userToken, bodyJson, gatewayReward_RedeemCodeToDT);
        debugResponse();
        verifyStatusCode200("API REDEEM CODE TO DONG TOT IS DEAD");
    }

    public static void redeemDongTotFree(String dongTotFreeAmount) {
        addPointToUser(1000000);
        verifyPointReward(1000000);


        if (dongTotFreeAmount.toLowerCase().contains("k")) {
            dongTotFreeAmount = dongTotFreeAmount.toLowerCase().trim();
            dongTotFreeAmount.replace(" ", "").replace("k", "000");
        }

        int dongTotFree = Integer.parseInt(dongTotFreeAmount);
        int numberOfRest = 0;

        Assert.assertTrue((dongTotFree % 5000) == 0, "Đồng Tốt Free phải chia hết cho 5000");

        //Get number of Code 15k
        int numberOf15k = dongTotFree / 15000;
        numberOfRest = dongTotFree % 15000;

        int numberOf10k = numberOfRest / 10000;
        numberOfRest = numberOfRest % 10000;

        int numberOf5k = numberOfRest / 5000;

        //Generate Code 15k
        if (numberOf15k > 0) {
            for (int i = 0; i < numberOf15k; i++) {
                allMyCodes.add(generateCode15k(getUserAccountID()));
            }
        }

        //Generate Code 10k
        if (numberOf10k > 0) {
            for (int i = 0; i < numberOf10k; i++) {
                allMyCodes.add(generateCode10k(getUserAccountID()));
            }
        }

        //Generate Code 5k
        if (numberOf5k > 0) {
            for (int i = 0; i < numberOf5k; i++) {
                allMyCodes.add(generateCode5k(getUserAccountID()));
            }
        }

        //Redeem my all codes to Dong Tot Free
        for (String code : allMyCodes) {
            redeemCodeToDongTot(global_accessToken, code);
        }

        checkTopupBalanceDTFree(global_accessToken, dongTotFree);
    }

}
