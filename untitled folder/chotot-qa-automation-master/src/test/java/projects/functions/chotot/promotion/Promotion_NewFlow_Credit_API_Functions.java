package projects.functions.chotot.promotion;

import io.restassured.response.Response;
import org.junit.Assert;
import projects.functions.APISupportFunctions;

import java.util.ArrayList;
import java.util.List;

import static api.configuration.DataConfig.newUserPhone;
import static api.configuration.GatewayConfig.*;
import static api.utils.GetAccessToken.getAccessTokenOfUser;
import static projects.functions.chotot.userGroup.UserGroup_API_Functions.*;

public class Promotion_NewFlow_Credit_API_Functions extends APISupportFunctions {
    private static final int numberOfRetry = 5;

    private static String campaignID = null, dateStart = null, dateEnd = null, uriString = null;
    private static String editName = null, editContent = null, editDes = null, editLegal = null, editDateStart = null, editDateEnd = null, codeUse = null, status = null;
    private static int creditValue, maxRedeem, maxPerAccRedeem, codeQuantity, codeRedeemLimit, freeBalance;
    private static List<String> validPromoCode = new ArrayList<>();
    private static List<String> invalidPromoCode = new ArrayList<>();
    private static List<String> validPromoCodeAfter = new ArrayList<>();

    //GETTER & SETTER
    protected static String getCampaignID() {
        return campaignID;
    }

    protected static void setCampaignID(String campaignID) {
        Promotion_NewFlow_Credit_API_Functions.campaignID = campaignID;
    }

    protected static int getCodeQuantity() {
        return codeQuantity;
    }

    protected static void setCodeQuantity(int codeQuantity) {
        Promotion_NewFlow_Credit_API_Functions.codeQuantity = codeQuantity;
    }

    protected static List<String> getListValidPromoCode() {
        return validPromoCode;
    }

    protected static void setListValidPromoCode(List<String> validPromoCode) {
        Promotion_NewFlow_Credit_API_Functions.validPromoCode = validPromoCode;
    }

    protected static List<String> getListInvalidPromoCode() {
        return invalidPromoCode;
    }

    protected static void setListInvalidPromoCode(List<String> invalidPromoCode) {
        Promotion_NewFlow_Credit_API_Functions.invalidPromoCode = invalidPromoCode;
    }

    protected static int getCreditValue() {
        return creditValue;
    }

    protected static void setCreditValue(int creditValue) {
        Promotion_NewFlow_Credit_API_Functions.creditValue = creditValue;
    }

    protected static int getMaxRedeem() {
        return maxRedeem;
    }

    protected static void setMaxRedeem(int maxRedeem) {
        Promotion_NewFlow_Credit_API_Functions.maxRedeem = maxRedeem;
    }

    protected static int getMaxPerAccRedeem() {
        return maxPerAccRedeem;
    }

    protected static void setMaxPerAccRedeem(int maxPerAccRedeem) {
        Promotion_NewFlow_Credit_API_Functions.maxPerAccRedeem = maxPerAccRedeem;
    }

    protected static int getCodeRedeemLimit() {
        return codeRedeemLimit;
    }

    protected static void setCodeRedeemLimit(int codeRedeemLimit) {
        Promotion_NewFlow_Credit_API_Functions.codeRedeemLimit = codeRedeemLimit;
    }

    protected static int getFreeBalance() {
        return freeBalance;
    }

    protected static void setFreeBalance(int freeBalance) {
        Promotion_NewFlow_Credit_API_Functions.freeBalance = freeBalance;
    }

    protected static String getDateStart() {
        return dateStart;
    }

    protected static void setDateStart(String dateStart) {
        Promotion_NewFlow_Credit_API_Functions.dateStart = dateStart;
    }

    protected static String getDateEnd() {
        return dateEnd;
    }

    protected static void setDateEnd(String dateEnd) {
        Promotion_NewFlow_Credit_API_Functions.dateEnd = dateEnd;
    }

    protected static List<String> getListValidPromoCodeAfter() {
        return validPromoCodeAfter;
    }

    protected static void setListValidPromoCodeAfter(List<String> validPromoCodeAfter) {
        Promotion_NewFlow_Credit_API_Functions.validPromoCodeAfter = validPromoCodeAfter;
    }

    //Functions
    protected static void createCampaignCodePromotionAuto(String dateStart, String dateEnd, int creditValue, int maxRedeem, int maxPerAccRedeem, int codeQuantity, int codeRedeemLimit, String userGroup, String type) {
        initBodyJson();
        bodyJson.addProperty("name", "autoTest");
        bodyJson.addProperty("date_start", dateStart);
        bodyJson.addProperty("date_end", dateEnd);
        bodyJson.addProperty("type", type);
        bodyJson.addProperty("legal_code", "a1b2c3");
        bodyJson.addProperty("user_created", "admin");
        bodyJson.addProperty("description", "description");
        bodyJson.addProperty("content", "content");
        bodyJson.addProperty("free_value", creditValue);
        bodyJson.addProperty("max_redeem", maxRedeem);
        bodyJson.addProperty("max_per_account_redeem", maxPerAccRedeem);
        bodyJson.addProperty("code_type", "auto");
        bodyJson.addProperty("code_quantity", codeQuantity);
        bodyJson.addProperty("code_redeem_limitation", codeRedeemLimit);
        bodyJson.addProperty("user_group", userGroup);

        for (int i = 0; i < numberOfRetry; i++) {
            try {
                response = post(bodyJson, gatewayCrePromotion_Internal_AddCampaign_Promo);
                verifyStatusCode200("CREATE CAMPAIGN", gatewayCrePromotion_Internal_AddCampaign_Promo);
                break;
            } catch (AssertionError error) {
                waitConstant(2);
            }
        }
        debugResponse();
        verifyStatusCode200("CREATE CAMPAIGN", gatewayCrePromotion_Internal_AddCampaign_Promo);

        //
        Assert.assertNotNull("CAMPAIGN ID IS NULL", getResponseData("$.id"));
        Assert.assertEquals("START TIME DOES NOT MATCH", getResponseData("$.date_start"), dateStart);
        Assert.assertEquals("EXPIRED TIME DOES NOT MATCH", getResponseData("$.date_end"), dateEnd);

        //
        setCampaignID(getResponseData("$.id"));
    }

    private static void verifyCampaignCodeStatus(String status) {
        uriString = String.format(gatewayCrePromotion_Internal_GetCampaignByID_Promo, getCampaignID());
        for (int i = 0; i < numberOfRetry; i++) {
            try {
                response = get(uriString);
                verifyStatusCode200("CAMPAIGN CODE", gatewayCrePromotion_Internal_GetCampaignByID_Promo);
                break;
            } catch (AssertionError error) {
                waitConstant(2);
            }
        }
        debugResponse();
        verifyStatusCode200("CAMPAIGN CODE", gatewayCrePromotion_Internal_GetCampaignByID_Promo);

        //
        Assert.assertEquals("CAMPAIGN STATUS IS WRONG", status, getResponseData("$.status"));
    }

    protected static void verifyCampaignCodeStatusIsNew() {
        verifyCampaignCodeStatus("new");
    }

    protected static void verifyCampaignCodeStatusIsApproved() {
        verifyCampaignCodeStatus("approved");
    }

    protected static void updateCampaignCodeStatus(String status) {
        uriString = String.format(gatewayCrePromotion_Internal_UpdateStatusCampaign_Promo, getCampaignID());
        initBodyJson();
        bodyJson.addProperty("new_status", status);
        bodyJson.addProperty("action_by", "admin");

        for (int i = 0; i < numberOfRetry; i++) {
            try {
                response = put(bodyJson, uriString);
                verifyStatusCode200("UPDATE STATUS", gatewayCrePromotion_Internal_UpdateStatusCampaign_Promo);
                break;
            } catch (AssertionError error) {
                waitConstant(2);
            }
        }
        debugResponse();
        verifyStatusCode200("UPDATE STATUS", gatewayCrePromotion_Internal_UpdateStatusCampaign_Promo);

        //
        Assert.assertEquals("CAMPAIGN ID IS CHANGED", getCampaignID(), getResponseData("$.id"));
        Assert.assertEquals("STATUS OF CAMPAIGN IS NOT CHANGED", status, getResponseData("$.status"));
    }

    protected static void updateCampaignCodeToApproved() {
        updateCampaignCodeStatus("approved");
    }

    protected static void updateCampaignCodeToNew() {
        updateCampaignCodeStatus("new");
    }

    private static Response getPromoCode() {
        for (int i = 0; i < numberOfRetry; i++) {
            try {
                response = get(gatewayCrePromotion_Internal_GetCode_Promo);
                verifyStatusCode200("GET CODE", gatewayCrePromotion_Internal_GetCode_Promo);
                break;
            } catch (AssertionError error) {
                waitConstant(2);
            }
        }
        debugResponse();
        verifyStatusCode200("GET CODE", gatewayCrePromotion_Internal_GetCode_Promo);
        return response;
    }

    protected static void getValidPromoCodes() {
        response = getPromoCode();
        validPromoCode = getResponseDataList("$..[?((@.campaign_id==" + getCampaignID() + ")&&(@.redeem_remain>0))].code");
        setListValidPromoCode(validPromoCode);
    }

    protected static void verifyPromotionCodeQuantity() {
        getValidPromoCodes();
        Assert.assertEquals("THE NUMBER OF PROMO CODE IS WRONG", String.valueOf(getCodeQuantity()), String.valueOf(getListValidPromoCode().size()));
    }

    protected static void getInvalidPromoCodes() {
        response = getPromoCode();
        invalidPromoCode = getResponseDataList("$..[?((@.campaign_id==" + getCampaignID() + ")&&(@.redeem_remain==0))].code");
        setListInvalidPromoCode(invalidPromoCode);
    }

    protected static void verifyInvalidPromoCodeQuantity() {
        getInvalidPromoCodes();
        Assert.assertEquals("THE NUMBER OF INVALID PROMO CODE IS WRONG", String.valueOf(getCodeQuantity()), String.valueOf(getListInvalidPromoCode().size()));
    }

    protected static void verifyCampaignCodeID(String campaignID) {
        Assert.assertNotNull("CAMPAIGN ID IS NULL", getCampaignID());
        Assert.assertEquals(getCampaignID(), campaignID);
        getValidPromoCodes();
        getInvalidPromoCodes();
    }

    private static Response redeemPromoCodeInternal(List<String> listPromoCode, String phone, String accountID) {
        codeUse = listPromoCode.get(0);
        initBodyJson();
        bodyJson.addProperty("code", codeUse);
        bodyJson.addProperty("account_id", accountID);
        bodyJson.addProperty("phone",phone);
        bodyJson.addProperty("source", "desktop_web");

        for (int i = 0; i < numberOfRetry; i++) {
            try {
                response = post(bodyJson, gatewayCrePromotion_Internal_Redeem_Promo);
                if (response.getStatusCode() == 404) {
                    verifyStatusCode404("REDEEM INTERNAL", gatewayCrePromotion_Internal_Redeem_Promo);
                    break;
                } else if (response.getStatusCode() == 500) {
                    verifyStatusCode500("REDEEM INTERNAL", gatewayCrePromotion_Internal_Redeem_Promo);
                    break;
                }
                verifyStatusCode200("REDEEM INTERNAL", gatewayCrePromotion_Internal_Redeem_Promo);
                break;
            } catch (AssertionError error) {
                waitConstant(2);
            }
        }
        debugResponse();
        if (response.getStatusCode() == 404) {
            verifyStatusCode404("REDEEM INTERNAL", gatewayCrePromotion_Internal_Redeem_Promo);
        } else if (response.getStatusCode() == 500) {
            verifyStatusCode500("REDEEM INTERNAL", gatewayCrePromotion_Internal_Redeem_Promo);
        } else {
            verifyStatusCode200("REDEEM INTERNAL", gatewayCrePromotion_Internal_Redeem_Promo);
        }
        return response;
    }

    private static Response redeemPromoCodePrivate(String userToken, List<String> listPromoCode) {
        initBodyJson();
        bodyJson.addProperty("code", listPromoCode.get(0));
        bodyJson.addProperty("phone", newUserPhone);
        bodyJson.addProperty("source", "desktop_web");

        for (int i = 0; i < numberOfRetry; i++) {
            try {
                response = post(userToken, bodyJson, gatewayCrePromotion_Private_Redeem_Promo);
                if (response.getStatusCode() == 404) {
                    verifyStatusCode404("REDEEM PRIVATE", gatewayCrePromotion_Private_Redeem_Promo);
                    break;
                }
                verifyStatusCode200("REDEEM PRIVATE", gatewayCrePromotion_Private_Redeem_Promo);
                break;
            } catch (AssertionError error) {
                waitConstant(2);
            }
        }
        debugResponse();
        if (response.getStatusCode() == 404) {
            verifyStatusCode404("REDEEM PRIVATE", gatewayCrePromotion_Private_Redeem_Promo);
        } else {
            verifyStatusCode200("REDEEM PRIVATE", gatewayCrePromotion_Private_Redeem_Promo);
        }
        return response;
    }

    protected static void redeemPromoCodeDeactivate() {
        response = redeemPromoCodeInternal(getListValidPromoCode(), newUserPhone, global_accountID);
        Assert.assertEquals("campaign_not_found", getResponseData("$.message"));
        getValidPromoCodes();
        getInvalidPromoCodes();
    }

    protected static void redeemPromoCodeSuccessInternal() {
        response = redeemPromoCodeInternal(getListValidPromoCode(), newUserPhone, global_accountID);
        Assert.assertTrue("PROMO CODE IS NOT REDEEMED", getResponseDataBoolean("$"));
        getValidPromoCodes();
        getInvalidPromoCodes();
    }

    protected static void redeemPromoCodeWithWrongAccID() {
        response = redeemPromoCodeInternal(getListValidPromoCode(),newUserPhone, "0000000000");

        Assert.assertTrue("PROMO CODE IS NOT REDEEMED", getResponseDataBoolean("$"));
        getValidPromoCodes();
        getInvalidPromoCodes();
    }

    protected static void redeemPromoCodeWithWrongPhone() {
        response = redeemPromoCodeInternal(getListValidPromoCode(),"0000000000", global_accountID);

        Assert.assertTrue("PROMO CODE IS NOT REDEEMED", getResponseDataBoolean("$"));
        getValidPromoCodes();
        getInvalidPromoCodes();
    }

    protected static void verifyRedeemPromoCodeLimitation() {
        response = redeemPromoCodeInternal(getListValidPromoCode(), newUserPhone, global_accountID);
        Assert.assertEquals("MISSING MESSAGE","exceed_redeem_per_account_limit",getResponseData("$.message"));
        Assert.assertEquals("CODE IS NOT 400","400",getResponseData("$.status_code"));
    }

    protected static void redeemPromoCodeSuccessPrivate() {
        response = redeemPromoCodePrivate(global_accessToken, getListValidPromoCode());
        Assert.assertTrue("PROMO CODE IS NOT REDEEMED", getResponseDataBoolean("$"));
        getValidPromoCodes();
        getInvalidPromoCodes();
    }

    protected static void redeemPromoCodeFail() {
        response = redeemPromoCodeInternal(getListInvalidPromoCode(), newUserPhone, global_accountID);
        Assert.assertEquals("code_not_found", getResponseData("$.message"));
        getValidPromoCodes();
        getInvalidPromoCodes();
    }

//    protected static void getFreeCreditAfterRedeem() {
//        for (int i = 0; i < numberOfRetry; i++) {
//            try {
//                response = get(global_accessToken, gatewayCrePromotion_Private_CreditBalance_Promo);
//                verifyStatusCode200("GET CREDIT AFTER REDEEM", gatewayCrePromotion_Private_CreditBalance_Promo);
//                Assert.assertEquals("FREE CREDIT IS WRONG", getCreditValue(), getResponseDataInt("$[?(@.type=='free')].balance"));
//                break;
//            } catch (AssertionError error) {
//                waitConstant(3);
//            }
//        }
//        debugResponse();
//        verifyStatusCode200("GET CREDIT AFTER REDEEM", gatewayCrePromotion_Private_CreditBalance_Promo);
//        Assert.assertEquals("FREE CREDIT IS WRONG", getCreditValue(), getResponseDataInt("$[?(@.type=='free')].balance"));
//        setFreeBalance(getResponseDataInt("$[?(@.type=='free')].balance"));
//    }

    protected static void getFreeCreditNotChangeAfterRedeem(String token) {
        for (int i = 0; i < numberOfRetry; i++) {
            try {
                response = get(token, gatewayCrePromotion_Private_CreditBalance_Promo);
                verifyStatusCode200("GET CREDIT AFTER REDEEM", gatewayCrePromotion_Private_CreditBalance_Promo);
                Assert.assertEquals("FREE CREDIT IS WRONG", -1, getResponseDataInt("$[?(@.type=='free')].balance"));
                break;
            } catch (AssertionError error) {
                waitConstant(3);
            }
        }
        debugResponse();
        verifyStatusCode200("GET CREDIT AFTER REDEEM", gatewayCrePromotion_Private_CreditBalance_Promo);
        Assert.assertEquals("FREE CREDIT IS WRONG", -1, getResponseDataInt("$[?(@.type=='free')].balance"));
        setFreeBalance(getResponseDataInt("$[?(@.type=='free')].balance"));
    }

    protected static void verifyCreditBalance() {
        Assert.assertEquals("FREE CREDIT IS WRONG", getCreditValue(), getFreeBalance());
    }

    protected static void verifyCreditBalanceNotChange() {
        Assert.assertEquals("FREE CREDIT IS WRONG", -1, getFreeBalance());
    }

    protected static void getValidPromoCodesAfter() {
        response = getPromoCode();
        validPromoCode = getResponseDataList("$..[?((@.campaign_id==" + getCampaignID() + ")&&(@.redeem_remain>0))].code");
        setListValidPromoCodeAfter(validPromoCode);
    }

    protected static void verifyPromoCodesNotChange(List<String> listPromoCodeBefore, List<String> listPromoCodeAfter) {
        Assert.assertEquals("Quantity of Promotion Code is changed",listPromoCodeBefore.size(), listPromoCodeAfter.size());
        for (int i = 0; i < listPromoCodeBefore.size(); i++) {
            Assert.assertEquals("The Promotion Code is changed",listPromoCodeBefore.get(i), listPromoCodeAfter.get(i));
        }
    }

    protected static void createErrorCampaignPromotionNewFlow(String key) {
        initBodyJson();
        bodyJson.addProperty("name", "autoTest");
        bodyJson.addProperty("type", "public");
        bodyJson.addProperty("legal_code", "a1b2c3");
        bodyJson.addProperty("user_created", "admin");
        bodyJson.addProperty("description", "description");
        bodyJson.addProperty("content", "content");
        bodyJson.addProperty("code_type", "auto");
        bodyJson.addProperty("user_group", "all");
        bodyJson.addProperty("code_quantity", 5);
        bodyJson.addProperty("code_redeem_limitation", 1);

        switch (key) {
            case "free_value":
                bodyJson.addProperty("date_start", getDateStart());
                bodyJson.addProperty("date_end", getDateEnd());
                bodyJson.addProperty("free_value", 0);
                bodyJson.addProperty("max_redeem", 100);
                bodyJson.addProperty("max_per_account_redeem", 100);
                break;
            case "max_redeem":
                bodyJson.addProperty("date_start", getDateStart());
                bodyJson.addProperty("date_end", getDateEnd());
                bodyJson.addProperty("free_value", 1000);
                bodyJson.addProperty("max_redeem", 0);
                bodyJson.addProperty("max_per_account_redeem", 100);
                break;
            case "max_per_account_redeem":
                bodyJson.addProperty("date_start", getDateStart());
                bodyJson.addProperty("date_end", getDateEnd());
                bodyJson.addProperty("free_value", 1000);
                bodyJson.addProperty("max_redeem", 100);
                bodyJson.addProperty("max_per_account_redeem", 0);
                break;
            case "date_start":
                bodyJson.addProperty("date_start", "");
                bodyJson.addProperty("date_end", getDateEnd());
                bodyJson.addProperty("free_value", 1000);
                bodyJson.addProperty("max_redeem", 0);
                bodyJson.addProperty("max_per_account_redeem", 100);
                break;
            case "date_end":
                bodyJson.addProperty("date_start", getDateStart());
                bodyJson.addProperty("date_end", "");
                bodyJson.addProperty("free_value", 1000);
                bodyJson.addProperty("max_redeem", 0);
                bodyJson.addProperty("max_per_account_redeem", 100);
                break;
        }

        for (int i = 0; i < numberOfRetry; i++) {
            try {
                response = post(bodyJson, gatewayCrePromotion_Internal_AddCampaign_Promo);
                verifyStatusCode500("CREATE CAMPAIGN", gatewayCrePromotion_Internal_AddCampaign_Promo);
                break;
            } catch (AssertionError error) {
                waitConstant(2);
            }
        }
        debugResponse();
        verifyStatusCode500("CREATE CAMPAIGN", gatewayCrePromotion_Internal_AddCampaign_Promo);
    }

    protected static void verifyPromoCodeNotGenerate() {
        getValidPromoCodes();
        Assert.assertTrue(getListValidPromoCode().isEmpty());
    }

    protected static void editDateTimeCampaignPromotionNewFLow(String keyDate, String keyTime) {
        uriString = String.format(gatewayCrePromotion_Internal_EditCampaign_Promo, getCampaignID());
        initBodyJson();
        bodyJson.addProperty("status", "approved");
        bodyJson.addProperty("action_by", "admin");

        String temp_date = null;
        if (keyTime.equalsIgnoreCase("future")) {
            temp_date = getPlusDateTimeFromCurrentTime(3);
        } else {
            temp_date = getMinusDateTimeFromCurrentTime(3);
        }

        switch (keyDate) {
            case "date_start":
                bodyJson.addProperty("date_start", temp_date);
                bodyJson.addProperty("date_end", getDateEnd());
                setDateStart(temp_date);
            case "date_end":
                bodyJson.addProperty("date_start", getDateStart());
                bodyJson.addProperty("date_end", temp_date);
                setDateEnd(temp_date);
        }

        for (int i = 0; i < numberOfRetry; i++) {
            try {
                response = put(bodyJson, uriString);
                verifyStatusCode200("EDIT CAMPAIGN PROMOTION", uriString);
                break;
            } catch (AssertionError error) {
                waitConstant(2);
            }
        }
        debugResponse();
        verifyStatusCode200("EDIT CAMPAIGN PROMOTION", uriString);

        Assert.assertEquals("CAMPAIGN IS WRONG", getCampaignID(), getResponseData(".id"));
        Assert.assertEquals("DATE START IS WRONG", getDateStart(), getResponseData(".date_start"));
        Assert.assertEquals("DATE END IS WRONG", getDateEnd(), getResponseData(".date_end"));
    }

    protected static void verifyFailRedeemCodeAfterEdit() {
        response = redeemPromoCodeInternal(getListValidPromoCode(), newUserPhone, global_accountID);
        verifyStatusCode404("REDEEM", gatewayCrePromotion_Internal_Redeem_Promo);
        Assert.assertEquals("MESSAGEE IS WRONG", "campaign_not_found", getResponseData(".message"));
    }

    protected static void createCampaignPromotionUserGroup(String dateStart, String dateEnd, int creditValue, int maxRedeem, int maxPerAccRedeem, int codeQuantity, int codeRedeemLimit, String userGroup, String type) {
        initBodyJson();
        bodyJson.addProperty("name", "autoGroupTest");
        bodyJson.addProperty("date_start", dateStart);
        bodyJson.addProperty("date_end", dateEnd);
        bodyJson.addProperty("type", type);
        bodyJson.addProperty("legal_code", "a1b2c3");
        bodyJson.addProperty("user_created", "admin");
        bodyJson.addProperty("description", "description");
        bodyJson.addProperty("content", "content");
        bodyJson.addProperty("free_value", creditValue);
        bodyJson.addProperty("max_redeem", maxRedeem);
        bodyJson.addProperty("max_per_account_redeem", maxPerAccRedeem);
        bodyJson.addProperty("code_type", "auto");
        bodyJson.addProperty("code_quantity", codeQuantity);
        bodyJson.addProperty("code_redeem_limitation", codeRedeemLimit);
        bodyJson.addProperty("user_group", userGroup);

        for (int i = 0; i < numberOfRetry; i++) {
            try {
                response = post(bodyJson, gatewayCrePromotion_Internal_AddCampaign_Promo);
                verifyStatusCode200("CREATE CAMPAIGN", gatewayCrePromotion_Internal_AddCampaign_Promo);
                break;
            } catch (AssertionError error) {
                waitConstant(2);
            }
        }
        debugResponse();
        verifyStatusCode200("CREATE CAMPAIGN", gatewayCrePromotion_Internal_AddCampaign_Promo);

        //
        Assert.assertNotNull("CAMPAIGN ID IS NULL", getResponseData("$.id"));
        Assert.assertEquals("START TIME DOES NOT MATCH", getResponseData("$.date_start"), dateStart);
        Assert.assertEquals("EXPIRED TIME DOES NOT MATCH", getResponseData("$.date_end"), dateEnd);

        //
        setCampaignID(getResponseData("$.id"));
    }

    protected static Response getAllHistory(String type) {
        for (int i = 0; i < numberOfRetry; i++) {
            try {
                response = get(gatewayCrePromotion_Internal_GetAllHistory_Promo);
                verifyStatusCode200("GET ALL HISTORY", gatewayCrePromotion_Internal_GetAllHistory_Promo);
                switch (type) {
                    case "create":
                        Assert.assertEquals("ACTION ON TABLE SHOULD BE CAMPAIGNS", "campaigns", getResponseData("$.data[?(@.after_update.id==" + getCampaignID() + ")].action_on_table"));
                        break;
                    case "update":
                        Assert.assertEquals("ACTION ON TABLE SHOULD BE CAMPAIGNS", "campaigns", getResponseData("$.data[?(@.before_update.id==" + getCampaignID() + ")].action_on_table"));
                        break;
                    case "status":
                        Assert.assertEquals("ACTION ON TABLE SHOULD BE CAMPAIGNS", "campaigns", getResponseData("$.data[?((@.after_update.id==" + getCampaignID() + ")&&(@.after_update.status=='" + status + "'))].action_on_table"));
                        break;
                    case "redeem":
                        Assert.assertEquals("ACTION ON TABLE SHOULD BE REDEEM", "redeem", getResponseData("$.data[?((@.after_update.campaign_id==" + getCampaignID() + ")&&(@.action_type=='create'))].action_on_table"));
                        break;
                }
                break;
            } catch (AssertionError error) {
                waitConstant(2);
            }
        }
        debugResponse();
        verifyStatusCode200("GET ALL HISTORY", gatewayCrePromotion_Internal_GetAllHistory_Promo);
        switch (type) {
            case "create":
                Assert.assertEquals("ACTION ON TABLE SHOULD BE CAMPAIGNS", "campaigns", getResponseData("$.data[?(@.after_update.id==" + getCampaignID() + ")].action_on_table"));
                break;
            case "update":
                Assert.assertEquals("ACTION ON TABLE SHOULD BE CAMPAIGNS", "campaigns", getResponseData("$.data[?(@.before_update.id==" + getCampaignID() + ")].action_on_table"));
                break;
            case "status":
                Assert.assertEquals("ACTION ON TABLE SHOULD BE CAMPAIGNS", "campaigns", getResponseData("$.data[?((@.after_update.id==" + getCampaignID() + ")&&(@.after_update.status=='" + status + "'))].action_on_table"));
                break;
            case "redeem":
                Assert.assertEquals("ACTION ON TABLE SHOULD BE REDEEM", "redeem", getResponseData("$.data[?((@.after_update.campaign_id==" + getCampaignID() + ")&&(@.action_type=='create'))].action_on_table"));
                break;
        }
        return response;
    }

    protected static void verifyHistoryCampaignCreate(String campaignID) {
        response = getAllHistory("create");

        //
        Assert.assertEquals("ACTION ON TABLE SHOULD BE CAMPAIGNS", "campaigns", getResponseData("$.data[?(@.after_update.id==" + campaignID + ")].action_on_table"));
        Assert.assertEquals("ACTION TYPE SHOULD BE CREATE", "create", getResponseData("$.data[?(@.after_update.id==" + campaignID + ")].action_type"));
        Assert.assertEquals("ACTION BY IS WRONG", "admin", getResponseData("$.data[?(@.after_update.id==" + campaignID + ")].action_by"));
        //
        Assert.assertEquals("CAMPAIGN ID IS WRONG", campaignID, getResponseData("$.data[?(@.after_update.id==" + campaignID + ")].after_update.id"));
        Assert.assertEquals("DATE OF START IS WRONG", getDateStart(), getResponseData("$.data[?(@.after_update.id==" + campaignID + ")]..date_start"));
        Assert.assertEquals("DATE OF END IS WRONG", getDateEnd(), getResponseData("$.data[?(@.after_update.id==" + campaignID + ")]..date_end"));
        Assert.assertEquals("STATUS IS WRONG", "new", getResponseData("$.data[?(@.after_update.id==" + campaignID + ")]..status"));
        Assert.assertEquals("DESCRIPTION IS WRONG", "description", getResponseData("$.data[?(@.after_update.id==" + campaignID + ")]..description"));
        Assert.assertEquals("CONTENT IS WRONG", "content", getResponseData("$.data[?(@.after_update.id==" + campaignID + ")]..content"));
        Assert.assertEquals("LEGAL CODE IS WRONG", "a1b2c3", getResponseData("$.data[?(@.after_update.id==" + campaignID + ")]..legal_code"));
        Assert.assertEquals("TYPE IS WRONG", "public", getResponseData("$.data[?(@.after_update.id==" + campaignID + ")]..type"));
        Assert.assertEquals("NAME IS WRONG", "autoTest", getResponseData("$.data[?(@.after_update.id==" + campaignID + ")]..name"));
        //
        Assert.assertEquals("BEFORE UPDATE SHOULD BE NULL", "null", String.valueOf(getResponseData("$.data[?(@.after_update.id==" + campaignID + ")].before_update")));
    }

    protected static void verifyHistoryCampaignEdit(String campaignID) {
        response = getAllHistory("update");

        //
        Assert.assertEquals("ACTION ON TABLE SHOULD BE CAMPAIGNS", "campaigns", getResponseData("$.data[?((@.after_update.id==" + campaignID + ")&&!(@.action_type=='create'))].action_on_table"));
        Assert.assertEquals("ACTION TYPE SHOULD BE UPDATE", "update", getResponseData("$.data[?((@.after_update.id==" + campaignID + ")&&!(@.action_type=='create'))].action_type"));
        Assert.assertEquals("ACTION BY IS WRONG", "admin", getResponseData("$.data[?((@.after_update.id==" + campaignID + ")&&!(@.action_type=='create'))].action_by"));
        // After Update
        Assert.assertEquals("CAMPAIGN ID AFTER UPDATE IS WRONG", campaignID, getResponseData("$.data[?((@.after_update.id==" + campaignID + ")&&!(@.action_type=='create'))].after_update.id"));
        Assert.assertEquals("DATE OF START AFTER UPDATE IS WRONG", editDateStart, getResponseData("$.data[?((@.after_update.id==" + campaignID + ")&&!(@.action_type=='create'))].after_update.date_start"));
        Assert.assertEquals("DATE OF END AFTER UPDATE IS WRONG", editDateEnd, getResponseData("$.data[?((@.after_update.id==" + campaignID + ")&&!(@.action_type=='create'))].after_update.date_end"));
        Assert.assertEquals("DESCRIPTION AFTER UPDATE IS WRONG", editDes, getResponseData("$.data[?((@.after_update.id==" + campaignID + ")&&!(@.action_type=='create'))].after_update.description"));
        Assert.assertEquals("CONTENT AFTER UPDATE IS WRONG", editContent, getResponseData("$.data[?((@.after_update.id==" + campaignID + ")&&!(@.action_type=='create'))].after_update.content"));
        Assert.assertEquals("LEGAL CODE AFTER UPDATE IS WRONG", editLegal, getResponseData("$.data[?((@.after_update.id==" + campaignID + ")&&!(@.action_type=='create'))].after_update.legal_code"));
        Assert.assertEquals("USER CREATED AFTER UPDATE IS WRONG", "admin", getResponseData("$.data[?((@.after_update.id==" + campaignID + ")&&!(@.action_type=='create'))].after_update.user_created"));
        Assert.assertEquals("NAME AFTER UPDATE IS WRONG", editName, getResponseData("$.data[?((@.after_update.id==" + campaignID + ")&&!(@.action_type=='create'))].after_update.name"));
        //Before Update
        Assert.assertEquals("CAMPAIGN ID BEFORE UPDATE IS WRONG", campaignID, getResponseData("$.data[?((@.after_update.id==" + campaignID + ")&&!(@.action_type=='create'))].before_update.id"));
        Assert.assertEquals("DATE OF START BEFORE UPDATE IS WRONG", getDateStart(), getResponseData("$.data[?((@.after_update.id==" + campaignID + ")&&!(@.action_type=='create'))].before_update.date_start"));
        Assert.assertEquals("DATE OF END BEFORE UPDATE IS WRONG", getDateEnd(), getResponseData("$.data[?((@.after_update.id==" + campaignID + ")&&!(@.action_type=='create'))].before_update.date_end"));
        Assert.assertEquals("DESCRIPTION BEFORE UPDATE IS WRONG", "description", getResponseData("$.data[?((@.after_update.id==" + campaignID + ")&&!(@.action_type=='create'))].before_update.description"));
        Assert.assertEquals("CONTENT BEFORE UPDATE IS WRONG", "content", getResponseData("$.data[?((@.after_update.id==" + campaignID + ")&&!(@.action_type=='create'))].before_update.content"));
        Assert.assertEquals("LEGAL CODE BEFORE UPDATE IS WRONG", "a1b2c3", getResponseData("$.data[?((@.after_update.id==" + campaignID + ")&&!(@.action_type=='create'))].before_update.legal_code"));
        Assert.assertEquals("USER CREATED BEFORE UPDATE IS WRONG", "admin", getResponseData("$.data[?((@.after_update.id==" + campaignID + ")&&!(@.action_type=='create'))].before_update.user_created"));
        Assert.assertEquals("NAME BEFORE UPDATE IS WRONG", "autoTest", getResponseData("$.data[?((@.after_update.id==" + campaignID + ")&&!(@.action_type=='create'))].before_update.name"));
    }

    protected static void editPromotionCodeCampaignInfo(String campaignID) {
        uriString = String.format(gatewayCrePromotion_Internal_EditCampaign_Promo, getCampaignID());
        editName = "Name Updated";
        editContent = "Content Updated";
        editDes = "Description Updated";
        editLegal = "CODEUPDATED";
        editDateStart = getMinusDateTimeFromCurrentTime(3);
        editDateEnd = getPlusDateTimeFromCurrentTime(4);

        initBodyJson();
        bodyJson.addProperty("name", editName);
        bodyJson.addProperty("content", editContent);
        bodyJson.addProperty("description", editDes);
        bodyJson.addProperty("legal_code", editLegal);
        bodyJson.addProperty("date_end", editDateEnd);
        bodyJson.addProperty("date_start", editDateStart);
        bodyJson.addProperty("action_by", "admin");

        for (int i = 0; i < numberOfRetry; i++) {
            try {
                response = put(bodyJson, uriString);
                verifyStatusCode200("EDIT CAMPAIGN PROMOTION", uriString);
                break;
            } catch (AssertionError error) {
                waitConstant(2);
            }
        }
        debugResponse();
        verifyStatusCode200("EDIT CAMPAIGN PROMOTION", uriString);

        Assert.assertEquals("CAMPAIGN ID IS WRONG", campaignID, getResponseData(".id"));
        Assert.assertEquals("DATE START IS WRONG", editDateStart, getResponseData(".date_start"));
        Assert.assertEquals("DATE END IS WRONG", editDateEnd, getResponseData(".date_end"));
    }

    protected static void verifyHistoryCampaignCodeStatus(String campaignID, String statusBefore, String statusAfter) {
        status = statusAfter;
        for (int i = 0; i < numberOfRetry; i++) {
            try {
                response = getAllHistory("status");
                //
                Assert.assertEquals("ACTION ON TABLE SHOULD BE CAMPAIGNS", "campaigns", getResponseData("$.data[?((@.after_update.id==" + campaignID + ")&&(@.after_update.status=='" + statusAfter + "'))].action_on_table"));
                Assert.assertEquals("ACTION TYPE SHOULD BE CREATE", "update", getResponseData("$.data[?((@.after_update.id==" + campaignID + ")&&(@.after_update.status=='" + statusAfter + "'))].action_type"));
                Assert.assertEquals("ACTION BY IS WRONG", "admin", getResponseData("$.data[?((@.after_update.id==" + campaignID + ")&&(@.after_update.status=='" + statusAfter + "'))].action_by"));
                // After Update
                Assert.assertEquals("STATUS AFTER UPDATE IS WRONG", statusAfter, getResponseData("$.data[?((@.after_update.id==" + campaignID + ")&&(@.after_update.status=='" + statusAfter + "'))].after_update.status"));
                // Before Update
                Assert.assertEquals("STATUS BEFORE UPDATE IS WRONG", statusBefore, getResponseData("$.data[?((@.after_update.id==" + campaignID + ")&&(@.after_update.status=='" + statusAfter + "'))].before_update.status"));
                break;
            } catch (AssertionError error) {
                waitConstant(2);
            }
        }
    }

    protected static void verifyHistoryCampaignCodeRedeem(String campaignID) {
        for (int i = 0; i < numberOfRetry; i++) {
            try {
                response = getAllHistory("redeem");
                // Redeem Create Log
                Assert.assertEquals("ACTION BY IS WRONG", global_accountID, getResponseData("$.data[?((@.after_update.campaign_id==" + campaignID + ")&&(@.action_type=='create'))].action_by"));
                Assert.assertEquals("ACTION ON TABLE SHOULD BE REDEEM", "redeem", getResponseData("$.data[?((@.after_update.campaign_id==" + campaignID + ")&&(@.action_type=='create'))].action_on_table"));
                Assert.assertEquals("ACTION TYPE SHOULD BE CREATE", "create", getResponseData("$.data[?((@.after_update.campaign_id==" + campaignID + ")&&(@.action_type=='create'))].action_type"));
                Assert.assertEquals("ACCOUNT ID IS WRONG", global_accountID, getResponseData("$.data[?((@.after_update.campaign_id==" + campaignID + ")&&(@.action_type=='create'))]..account_id"));
                Assert.assertEquals("CAMPAIGN ID IS WRONG", campaignID, getResponseData("$.data[?((@.after_update.campaign_id==" + campaignID + ")&&(@.action_type=='create'))]..campaign_id"));
                Assert.assertEquals("STATUS SHOULD BE NEW", "new", getResponseData("$.data[?((@.after_update.campaign_id==" + campaignID + ")&&(@.action_type=='create'))]..status"));
                Assert.assertEquals("TYPE SHOULD BE MANUAL", "manual", getResponseData("$.data[?((@.after_update.campaign_id==" + campaignID + ")&&(@.action_type=='create'))]..type"));
                Assert.assertEquals("CODE USE IS WRONG", codeUse, getResponseData("$.data[?((@.after_update.campaign_id==" + campaignID + ")&&(@.action_type=='create'))]..code"));
                // Redeem Update Log
                Assert.assertEquals("ACTION BY SHOULD BE SYSTEM", "system", getResponseData("$.data[?((@.after_update.campaign_id==" + campaignID + ")&&(@.action_type=='update'))].action_by"));
                Assert.assertEquals("ACTION ON TABLE SHOULD BE REDEEM", "redeem", getResponseData("$.data[?((@.after_update.campaign_id==" + campaignID + ")&&(@.action_type=='update'))].action_on_table"));
                Assert.assertEquals("ACTION TYPE SHOULD BE UPDATE", "update", getResponseData("$.data[?((@.after_update.campaign_id==" + campaignID + ")&&(@.action_type=='update'))].action_type"));
                Assert.assertEquals("ACCOUNT ID IS WRONG", global_accountID, getResponseData("$.data[?((@.after_update.campaign_id==" + campaignID + ")&&(@.action_type=='update'))]..account_id"));
                Assert.assertEquals("CAMPAIGN ID IS WRONG", campaignID, getResponseData("$.data[?((@.after_update.campaign_id==" + campaignID + ")&&(@.action_type=='update'))]..campaign_id"));
                Assert.assertEquals("CODE USE IS WRONG", codeUse, getResponseData("$.data[?((@.after_update.campaign_id==" + campaignID + ")&&(@.action_type=='update'))]..code"));
                Assert.assertEquals("STATUS SHOULD BE COMPLETED", "completed", getResponseData("$.data[?((@.after_update.campaign_id==" + campaignID + ")&&(@.action_type=='update'))]..status"));
                Assert.assertEquals("TYPE SHOULD BE MANUAL", "manual", getResponseData("$.data[?((@.after_update.campaign_id==" + campaignID + ")&&(@.action_type=='update'))]..type"));
                Assert.assertEquals("REDEEM ID IS WRONG", getResponseData("$.data[?((@.after_update.campaign_id==" + campaignID + ")&&(@.action_type=='update'))].before_update.id"), getResponseData("$.data[?((@.after_update.campaign_id==" + campaignID + ")&&(@.action_type=='update'))].after_update.id"));
                // Code Update Log
                Assert.assertEquals("ACTION BY IS WRONG", global_accountID, getResponseData("$.data[?((@.action_by==" + global_accountID + ")&&(@.action_on_table=='code'))].action_by"));
                Assert.assertEquals("ACTION ON TABLE SHOULD BE CODE", "code", getResponseData("$.data[?((@.action_by==" + global_accountID + ")&&(@.action_on_table=='code'))].action_on_table"));
                Assert.assertEquals("ACTION TYPE SHOULD BE UPDATE", "update", getResponseData("$.data[?((@.action_by==" + global_accountID + ")&&(@.action_on_table=='code'))].action_type"));
                Assert.assertEquals("CODE ID IS WRONG", getResponseData("$.data[?((@.action_by==" + global_accountID + ")&&(@.action_on_table=='code'))].before_update.id"), getResponseData("$.data[?((@.action_by==" + global_accountID + ")&&(@.action_on_table=='code'))].after_update.id"));
                Assert.assertEquals("REDEEM REMAIN BEFORE UPDATE SHOULD BE 1", "1", String.valueOf(getResponseData("$.data[?((@.action_by==" + global_accountID + ")&&(@.action_on_table=='code'))].before_update.redeem_remain")));
                Assert.assertEquals("REDEEM REMAIN AFTER UPDATE SHOULD BE 0", "0", String.valueOf(getResponseData("$.data[?((@.action_by==" + global_accountID + ")&&(@.action_on_table=='code'))].after_update.redeem_remain")));
                break;
            } catch (Exception error) {
                waitConstant(2);
            }
        }
    }

    protected static void redeemPromoCodeByUserGroup(String key) {
        if (key.equalsIgnoreCase("group")) {
            response = redeemPromoCodeInternal(getListValidPromoCode()
                    , getUserGroupPhones().get(0), String.valueOf(getUserGroupAccIDs().get(0)));
        } else {
            response = redeemPromoCodeInternal(getListValidPromoCode()
                    , newUserPhone, global_accountID);
        }
        Assert.assertTrue("PROMO CODE IS NOT REDEEMED", getResponseDataBoolean("$"));
        getValidPromoCodes();
        getInvalidPromoCodes();
    }

    protected static void redeemPromoCodeFailByUserGroup(String phone, String accID) {
        response = redeemPromoCodeInternal(getListValidPromoCode(), phone, accID);
        Assert.assertEquals("REDEEM SHOULD BE FAIL", "campaign_not_found_for_user", getResponseData(".message"));
        Assert.assertEquals("STATUS SHOULD BE 400", "400", getResponseData(".status_code"));
        getValidPromoCodes();
        getInvalidPromoCodes();
    }

    protected static void getFreeCreditAfterRedeem(String token) {
        for (int i = 0; i < numberOfRetry; i++) {
            try {
                response = get(token, gatewayCrePromotion_Private_CreditBalance_Promo);
                verifyStatusCode200("GET CREDIT AFTER REDEEM", gatewayCrePromotion_Private_CreditBalance_Promo);
                Assert.assertEquals("TYPE IS WRONG", "free", getResponseData("$..type"));
                break;
            } catch (AssertionError error) {
                waitConstant(3);
            }
        }
        debugResponse();
        verifyStatusCode200("GET CREDIT AFTER REDEEM", gatewayCrePromotion_Private_CreditBalance_Promo);
        Assert.assertEquals("TYPE IS WRONG", "free", getResponseData("$..type"));
        setFreeBalance(getResponseDataInt("$[?(@.type=='free')].balance"));
    }

    protected static void autoRedeemPromoCode(String campaignID) {
        uriString = String.format(gatewayCrePromotion_Internal_AutoRedeem_Promo, campaignID);
        for (int i = 0; i < numberOfRetry; i++) {
            try {
                response = put(uriString);
                verifyStatusCode200("AUTO REDEEM FAILED", uriString);
                break;
            } catch (AssertionError error) {
                waitConstant(2);
            }
        }
        debugResponse();
        verifyStatusCode200("AUTO REDEEM FAILED", uriString);
    }

    protected static void autoRedeemPromoCodeFail(String campaignID) {
        uriString = String.format(gatewayCrePromotion_Internal_AutoRedeem_Promo, campaignID);
        for (int i = 0; i < numberOfRetry; i++) {
            try {
                response = put(uriString);
                verifyStatusCode200("AUTO REDEEM FAILED", uriString);
                Assert.assertEquals("MESSAGE IS WRONG", "Có lỗi không xác định, vui lòng thử lại", getResponseData(".message"));
                Assert.assertEquals("ERROR IS WRONG", "campaign_not_found", getResponseData(".errorDetail"));
                Assert.assertEquals("STATUS IS WRONG", "false", String.valueOf(getResponseData(".isSuccess")));
                break;
            } catch (AssertionError error) {
                waitConstant(2);
            }
        }
        debugResponse();
        verifyStatusCode200("AUTO REDEEM FAILED", uriString);
        Assert.assertEquals("MESSAGE IS WRONG", "Có lỗi không xác định, vui lòng thử lại", getResponseData(".message"));
        Assert.assertEquals("ERROR IS WRONG", "campaign_not_found", getResponseData(".errorDetail"));
        Assert.assertEquals("STATUS IS WRONG", "false", String.valueOf(getResponseData(".isSuccess")));
    }

    protected static void verifyFreeCreditUserGroup(List<String> groupPhones) {
        for (String groupPhone : groupPhones) {
            String tempToken = getAccessTokenOfUser(groupPhone, "123456");
            getFreeCreditAfterRedeem(tempToken);
            verifyCreditBalance();
        }
    }

    protected static void verifyFreeCreditNotReceiveUserGroup(List<String> groupPhones) {
        for (String groupPhone : groupPhones) {
            String tempToken = getAccessTokenOfUser(groupPhone, "123456");
            getFreeCreditNotChangeAfterRedeem(tempToken);
            verifyCreditBalanceNotChange();
        }
    }
}
