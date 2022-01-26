package projects.functions.chotot.customer.freePremium;

import io.cucumber.core.internal.gherkin.deps.com.google.gson.JsonArray;
import io.cucumber.core.internal.gherkin.deps.com.google.gson.JsonObject;
import org.junit.Assert;
import projects.functions.APISupportFunctions;

import static api.configuration.GatewayConfig.*;

public class FreeBump_API_Functions extends APISupportFunctions {
    private static final int numberOfRetry = 5;

    private static String accountID = null, CampaignFreeBumpID = null, totalRedeem = null, announcerID = null, userRedeemID = null, promotionID = null;
    private static String adID = null, adTitle = null, startAt = null, expiredAt = null, startAtUpdated = null, expireAtUpdated = null, redeemStatus = null;
    private static String campaignFreeBumpNameFromAnnouncer = null, previousMessageID = null;
    private static String campaignFreeBumpNameFromResponse = "PREMIUM_SERVICE_PROMOTION";


    private static String tokenRedeem = null, buttonRedeem = "", CampaignFreeBumpName = "", appLink = "", webLink = "", campaignFreeBumpRedeemID = "", messageRedeemSuccess = null;
    private static Boolean addRedeems = true, isRedeemSuccess = true;
    private static Boolean isDeletedAnnouncer = false, isSms = false, isDeleted = false, isForceDeactive = false;

    private static String isActiveCampaignFreeBump = null;
    private static String campaignFreeBumpType = "premium_service"; // For free premium service
    private static String serviceType = "bump";
    private static String categoryID = "1000";
    private static String limit = "100";

    // SET & GET


    public static String getIsActiveCampaignFreeBump() {
        return isActiveCampaignFreeBump;
    }

    public static void setIsActiveCampaignFreeBump(String isActiveCampaignFreeBump) {
        FreeBump_API_Functions.isActiveCampaignFreeBump = isActiveCampaignFreeBump;
    }

    public static String getPreviousMessageID() {
        return previousMessageID;
    }

    public static void setPreviousMessageID(String previousMessageID) {
        FreeBump_API_Functions.previousMessageID = previousMessageID;
    }

    public static String getCampaignFreeBumpNameFromAnnouncer() {
        return campaignFreeBumpNameFromAnnouncer;
    }

    public static void setCampaignFreeBumpNameFromAnnouncer(String campaignFreeBumpNameFromAnnouncer) {
        FreeBump_API_Functions.campaignFreeBumpNameFromAnnouncer = campaignFreeBumpNameFromAnnouncer;
    }

    public static String getCampaignFreeBumpNameFromResponse() {
        return campaignFreeBumpNameFromResponse;
    }

    public static void setCampaignFreeBumpNameFromResponse(String campaignFreeBumpNameFromResponse) {
        FreeBump_API_Functions.campaignFreeBumpNameFromResponse = campaignFreeBumpNameFromResponse;
    }

    public static String getRedeemStatus() {
        return redeemStatus;
    }

    public static void setRedeemStatus(String redeemStatus) {
        FreeBump_API_Functions.redeemStatus = redeemStatus;
    }

    public static Boolean getAddRedeems() {
        return addRedeems;
    }

    public static void setAddRedeems(Boolean addRedeems) {
        FreeBump_API_Functions.addRedeems = addRedeems;
    }


    public static Boolean getIsRedeemSuccess() {
        return isRedeemSuccess;
    }

    public static void setIsRedeemSuccess(Boolean isRedeemSuccess) {
        FreeBump_API_Functions.isRedeemSuccess = isRedeemSuccess;
    }

    public static Boolean getIsDeletedAnnouncer() {
        return isDeletedAnnouncer;
    }

    public static void setIsDeletedAnnouncer(Boolean isDeletedAnnouncer) {
        FreeBump_API_Functions.isDeletedAnnouncer = isDeletedAnnouncer;
    }

    public static Boolean getIsSms() {
        return isSms;
    }

    public static void setIsSms(Boolean isSms) {
        FreeBump_API_Functions.isSms = isSms;
    }

    public static Boolean getIsDeleted() {
        return isDeleted;
    }

    public static void setIsDeleted(Boolean isDeleted) {
        FreeBump_API_Functions.isDeleted = isDeleted;
    }

    public static Boolean getIsForceDeactive() {
        return isForceDeactive;
    }

    public static void setIsForceDeactive(Boolean isForceDeactive) {
        FreeBump_API_Functions.isForceDeactive = isForceDeactive;
    }

    public static String getStartAtUpdated() {
        return startAtUpdated;
    }

    public static void setStartAtUpdated(String startAtUpdated) {
        FreeBump_API_Functions.startAtUpdated = startAtUpdated;
    }

    public static String getExpireAtUpdated() {
        return expireAtUpdated;
    }

    public static void setExpireAtUpdated(String expireAtUpdated) {
        FreeBump_API_Functions.expireAtUpdated = expireAtUpdated;
    }

    public static String getPromotionID() {
        return promotionID;
    }

    public static void setPromotionID(String promotionID) {
        FreeBump_API_Functions.promotionID = promotionID;
    }

    public static String getMessageRedeemSuccess() {
        return messageRedeemSuccess;
    }

    public static void setMessageRedeemSuccess(String messageRedeemSuccess) {
        FreeBump_API_Functions.messageRedeemSuccess = messageRedeemSuccess;
    }

    public static String getCampaignFreeBumpRedeemID() {
        return campaignFreeBumpRedeemID;
    }

    public static void setCampaignFreeBumpRedeemID(String campaignFreeBumpRedeemID) {
        FreeBump_API_Functions.campaignFreeBumpRedeemID = campaignFreeBumpRedeemID;
    }

    public static int getNumberOfRetry() {
        return numberOfRetry;
    }

    public static String getExpiredAt() {
        return expiredAt;
    }

    public static void setExpiredAt(String expiredAt) {
        FreeBump_API_Functions.expiredAt = expiredAt;
    }

    public static String getButtonRedeem() {
        return buttonRedeem;
    }

    public static void setButtonRedeem(String buttonRedeem) {
        FreeBump_API_Functions.buttonRedeem = buttonRedeem;
    }

    public static String getCampaignFreeBumpName() {
        return CampaignFreeBumpName;
    }

    public static void setCampaignFreeBumpName(String campaignFreeBumpName) {
        FreeBump_API_Functions.CampaignFreeBumpName = campaignFreeBumpName;
    }

    public static String getAppLink() {
        return appLink;
    }

    public static void setAppLink(String appLink) {
        FreeBump_API_Functions.appLink = appLink;
    }

    public static String getWebLink() {
        return webLink;
    }

    public static void setWebLink(String webLink) {
        FreeBump_API_Functions.webLink = webLink;
    }

    public static String getServiceType() {
        return serviceType;
    }

    public static void setServiceType(String serviceType) {
        FreeBump_API_Functions.serviceType = serviceType;
    }

    public static String getAdTitle() {
        return adTitle;
    }

    public static void setAdTitle(String adTitle) {
        FreeBump_API_Functions.adTitle = adTitle;
    }

    public static String getUserRedeemID() {
        return userRedeemID;
    }

    public static void setUserRedeemID(String userRedeemID) {
        FreeBump_API_Functions.userRedeemID = userRedeemID;
    }

    public static String getAnnouncerID() {
        return announcerID;
    }

    public static void setAnnouncerID(String announcerID) {
        FreeBump_API_Functions.announcerID = announcerID;
    }

    public static String getAdID() {
        return adID;
    }

    public static void setAdID(String adID) {
        FreeBump_API_Functions.adID = adID;
    }

    public static String getTotalRedeem() {
        return totalRedeem;
    }

    public static void setTotalRedeem(String totalRedeem) {
        FreeBump_API_Functions.totalRedeem = totalRedeem;
    }

    public static String getAccountID() {
        return accountID;
    }

    public static void setAccountID(String accountID) {
        FreeBump_API_Functions.accountID = accountID;
    }

    public static String getCategoryID() {
        return categoryID;
    }

    public static void setCategoryID(String categoryID) {
        FreeBump_API_Functions.categoryID = categoryID;
    }

    public static String getLimit() {
        return limit;
    }

    public static void setLimit(String limit) {
        FreeBump_API_Functions.limit = limit;
    }

    public static String getStartAt() {
        return startAt;
    }

    public static void setStartAt(String startAt) {
        FreeBump_API_Functions.startAt = startAt;
    }

    public static String getTokenRedeem() {
        return tokenRedeem;
    }

    public static void setTokenRedeem(String tokenRedeem) {
        FreeBump_API_Functions.tokenRedeem = tokenRedeem;
    }

    public static String getCampaignFreeBumpType() {
        return campaignFreeBumpType;
    }

    public static void setCampaignFreeBumpType(String campaignFreeBumpType) {
        FreeBump_API_Functions.campaignFreeBumpType = campaignFreeBumpType;
    }

    public static String getCampaignFreeBumpID() {
        return CampaignFreeBumpID;
    }

    public static void setCampaignFreeBumpID(String campaignFreeBumpID) {
        FreeBump_API_Functions.CampaignFreeBumpID = campaignFreeBumpID;
    }

    // Functions
    public static void addCampaignFreeBumpActive() {
        addCampaignFreeBump(true);
    }

    public static void addCampaignFreeBumpInactive() {
        addCampaignFreeBump(false);
    }

    private static void addCampaignFreeBump(boolean isActive) {
        String startTime = getTimeStamp();
        String expiredTime = getTimeStampFromCurrentTime(7);

        initBodyJson();
        JsonObject bodyCondition = new JsonObject();
        bodyCondition.addProperty("account_id", 10000001); // Not use for adding campaign :10145199
        bodyCondition.addProperty("category_id", 1000); // Not use for adding campaign
        bodyJson.add("condition", bodyCondition);

        bodyJson.addProperty("limit", 40); // Not use for adding campaign
        bodyJson.addProperty("start_at", Integer.parseInt(startTime));
        bodyJson.addProperty("expired_at", Integer.parseInt(expiredTime));
        bodyJson.addProperty("is_active", isActive);
        bodyJson.addProperty("type", "premium_service");
        bodyJson.addProperty("rank", Integer.parseInt("10")); // Not use for adding campaign

        for (int i = 0; i < numberOfRetry; i++) {
            try {
                response = post(bodyJson, gatewayFreePremium_AddCampaign_FreeBump);
                verifyStatusCode200("PROMOTION CAMPAIGN", "ADD CAMPAIGN FREE PREMIUM");
                break;
            } catch (AssertionError error) {
                waitConstant(2);
            }
        }
        debugResponse();
        verifyStatusCode200("PROMOTION CAMPAIGN", "ADD CAMPAIGN FREE PREMIUM");

        // Verify response
        Assert.assertNotNull("CAMPAIGN ID IS NULL", getResponseData("$.id"));
        Assert.assertEquals("START TIME DOES NOT MATCH", getResponseData("$.start_at"), startTime);
        Assert.assertEquals("EXPIRED TIME DOES NOT MATCH", getResponseData("$.expired_at"), expiredTime);

        // SET DATA FOR NEXT STEPS
        setCampaignFreeBumpID(getResponseData("$.id"));
        setCategoryID(getResponseData("$.condition.category_id"));
        setAccountID(getResponseData("$.condition.account_id"));
        setStartAt(getResponseData("$.start_at"));
        setExpiredAt(getResponseData("$.expired_at"));
        setIsActiveCampaignFreeBump(getResponseData("$.is_active"));
        setLimit(getResponseData("$.limit"));
        setCampaignFreeBumpType(getResponseData("$.type"));
    }

    protected static void verifyCampaignFreeBump(String statusOfCampaign) {
        Assert.assertNotNull("CAMPAIGN ID IS NULL", getCampaignFreeBumpID());
        Assert.assertEquals("CATEGORY DOES NOT MATCH", "1000", getCategoryID());
        Assert.assertEquals("ACCOUNT ID DOES NOT MATCH", "10000001", getAccountID());
        Assert.assertNotNull("START TIME IS NULL", getStartAt());
        Assert.assertNotNull("EXPIRED TIME IS NULL", getExpiredAt());
        Assert.assertEquals("STATUS OF CAMPAIGN", statusOfCampaign, getIsActiveCampaignFreeBump());
        Assert.assertEquals("LIMIT DOES NOT MATCH", "40", getLimit());
        Assert.assertEquals("CAMPAIGN TYPE DOES NOT MATCH", "premium_service", getCampaignFreeBumpType());
    }

    protected static void addCampaignFreeBumpRedeem(String accountID, String adID, String adTitle) {
        initBodyJson();
        JsonObject bodyJsonRedeem = new JsonObject();
        bodyJsonRedeem.addProperty("account_id", Integer.parseInt(accountID));
        bodyJsonRedeem.addProperty("ad_id", Integer.parseInt(adID));
        bodyJsonRedeem.addProperty("service_type", serviceType);
        bodyJsonRedeem.addProperty("ad_title", adTitle);
        bodyJsonRedeem.addProperty("total_lead", 1);
        bodyJsonRedeem.addProperty("total_free", 1);

        JsonArray arrRedeems = new JsonArray();
        arrRedeems.add(bodyJsonRedeem);

        bodyJson.add("redeems", arrRedeems);

        for (int i = 0; i < numberOfRetry; i++) {
            try {
                response = post(bodyJson, String.format(gatewayFreePremium_AddRedeem_FreeBump, CampaignFreeBumpID));
                verifyStatusCode200("PROMOTION CAMPAIGN", "ADD REDEEM TO CAMPAIGN");
                break;
            } catch (AssertionError error) {
                waitConstant(2);
            }
        }
        debugResponse();
        verifyStatusCode200("PROMOTION CAMPAIGN", "ADD REDEEM TO CAMPAIGN");

        // SET DATA FOR NEXT STEPS
        setAddRedeems(getResponseDataBoolean("$.is_success"));
    }

    protected static void checkRedeemFreeBump() {
        Assert.assertTrue("ADD REDEEM FAIL", addRedeems);
    }

    protected static void getCampaignFreeBumpInfo(String campaignID) {
        for (int i = 0; i < numberOfRetry; i++) {
            try {
                response = get(String.format(gatewayFreePremium_GetCampaignInfo_FreeBump, campaignID));
                verifyStatusCode200("PROMOTION CAMPAIGN", "CHECK REDEEM IS ADDED");
                break;
            } catch (AssertionError error) {
                waitConstant(2);
            }
        }
        debugResponse();
        verifyStatusCode200("PROMOTION CAMPAIGN", "CHECK REDEEM IS ADDED");

        // SET DATA FOR NEXT STEPS
        setTotalRedeem(getResponseData("$.total"));
        setUserRedeemID(getResponseData("$.data.[*].id"));
        setAccountID(getResponseData("$.data.[*].account_id"));
        setAdID(getResponseData("$..[*].announcer_id"));
        setCampaignFreeBumpType(getResponseData("$..[*].announcer_id"));
        setRedeemStatus(getResponseData("$.data.[*].redeem_status"));
        setIsDeleted(getResponseDataBoolean("$.data.[*].premium_service.is_deleted_announcer"));
        setIsSms(getResponseDataBoolean("$.data.[*].premium_service.is_sms"));
        setServiceType(getResponseData("$.data.[*].premium_service.service_type"));
    }

    protected static void checkRedeemIsSendToUser() {
        String campaignRedeemIDs = getCampaignFreeBumpRedeemID();
        String campaignIDs = getCampaignFreeBumpID();
        String accountID = getAccountID();
        String webLink = getWebLink();
        String appLink = getAppLink();
        String campaignName = getCampaignFreeBumpName();
        String buttonRedeems = getButtonRedeem();

        Assert.assertEquals("CAMPAIGN NAME DOES NOT MATCH", "PREMIUM_SERVICE_PROMOTION", campaignName);
        Assert.assertNotNull(webLink);
        Assert.assertNotNull(appLink);
        Assert.assertEquals("ACCOUNT ID DOES NOT MATCH", accountID, global_accountID);
        Assert.assertEquals("CAMPAIGN REDEEM ID DOES NOT MATCH", campaignRedeemIDs, campaignIDs);
        Assert.assertEquals("BUTTON REDEEM DOES NOT MATCH", buttonRedeems, "SỬ DỤNG NGAY");
    }

    protected static void checkRedeemNotSendToUser() {
        String campaignName = getCampaignFreeBumpName();
        Assert.assertNotEquals("ANNOUNCEMENT BOX FREE BUMP SENT", "PREMIUM_SERVICE_PROMOTION", campaignName);
    }

    protected static void getAnnouncementBoxInactiveCampaignFreeBump(String userToken) {
        for (int i = 0; i < numberOfRetry; i++) {
            try {
                response = get(userToken, gatewayFreePremium_AnnouncerWeb);
                verifyStatusCode200("PROMOTION CAMPAIGN", "GET TOKEN REDEEM");
                break;
            } catch (AssertionError error) {
                waitConstant(2);
            }
        }
        debugResponse();
        verifyStatusCode200("PROMOTION CAMPAIGN", "GET TOKEN REDEEM");
        // SET DATA FOR NEXT STEPS
        setCampaignFreeBumpName(getResponseData("$.annoucement_box.name"));
    }

    protected static void getTokenUserRedeem(String userToken) {
        for (int i = 0; i < numberOfRetry; i++) {
            try {
                response = get(userToken, gatewayFreePremium_AnnouncerWeb);
                if (getResponseData("$.annoucement_box.name").equals(campaignFreeBumpNameFromResponse)) {
                    verifyStatusCode200("PROMOTION CAMPAIGN", "GET TOKEN REDEEM");
                    break;
                } else {
                    setPreviousMessageID(getResponseData("$.annoucement_box._id"));
                    response = get(userToken, String.format(gatewayFreePremium_CloseAnnouncerWeb,
                            getPreviousMessageID())); // previousMessageID to close the other announcement box
                    verifyStatusCode200("PROMOTION CAMPAIGN", "GET TOKEN REDEEM");
                }
            } catch (AssertionError error) {
                waitConstant(2);
            }
        }
        debugResponse();
        verifyStatusCode200("PROMOTION CAMPAIGN", "GET TOKEN REDEEM");
        Assert.assertEquals("CAMPAIGN NAME DOES NOT MATCH", campaignFreeBumpNameFromResponse,
                getResponseData("$.annoucement_box.name"));

        //Get user token redeem from url
        String tokenRedeem = getResponseData("$.annoucement_box.web_link");
        setTokenRedeem(tokenRedeem.substring(81, 117));
        // Get promotion ID from url
        String promotionRedeemID = getResponseData("$.annoucement_box.web_link");
        setPromotionID(promotionRedeemID.substring(50, 74));

        //Get data for next step
        setCampaignFreeBumpRedeemID(getResponseData("$.annoucement_box.campaign_id"));
        setAccountID(getResponseData("$.annoucement_box.account_id"));
        setWebLink(getResponseData("$.annoucement_box.web_link"));
        setAppLink(getResponseData("$.annoucement_box.app_link"));
        setCampaignFreeBumpName(getResponseData("$.annoucement_box.name"));
        setButtonRedeem(getResponseData("$.annoucement_box.button_text"));
    }

    protected static void checkRedeemFreeBumpSuccess() {
        Boolean redeemSuccess = getIsRedeemSuccess();
        String redeemMessageSuccess = getMessageRedeemSuccess();

        Assert.assertTrue("REDEEM FAIL", redeemSuccess);
        Assert.assertEquals("REDEEM FAIL", "Redeem success", redeemMessageSuccess);
    }

    protected static void redeemFreeBump(String tokenRedeem, String promotionID) {
        initBodyJson();
        bodyJson.addProperty("token", tokenRedeem);
        for (int i = 0; i < numberOfRetry; i++) {
            try {
                response = post(bodyJson, String.format(gatewayFreePremium_RedeemEndUser_FreeBump, promotionID));
                verifyStatusCode200("PROMOTION CAMPAIGN", "REDEEM FOR END USERS");
                break;
            } catch (AssertionError error) {
                waitConstant(2);
            }
        }
        debugResponse();
        verifyStatusCode200("PROMOTION CAMPAIGN", "REDEEM FOR END USERS");

        // SET DATA FOR NEXT STEPS
        setIsRedeemSuccess(getResponseDataBoolean("$.is_success"));
        setMessageRedeemSuccess(getResponseData("$.message"));
    }

    protected static void activateCampaignFreeBump() {
        String campaignID = getCampaignFreeBumpID();
        updateCampaignFreeBumpStatus(campaignID, true);
    }

    protected static void deactivateCampaignFreeBump() {
        String campaignID = getCampaignFreeBumpID();
        updateCampaignFreeBumpStatus(campaignID, false);
    }

    protected static void updateCampaignFreeBumpStatus(String campaignFreeBumpID, Boolean statusCampaignFreeBump) {
        initBodyJson();
        bodyJson.addProperty("is_active", statusCampaignFreeBump);

        for (int i = 0; i < numberOfRetry; i++) {
            try {
                response = put(bodyJson, String.format(gatewayFreePremium_UpdateStatusCampaign_FreeBump, campaignFreeBumpID));
                verifyStatusCode200("CAMPAIGN", "UPDATE STATUS OF THE CAMPAIGN");
                Assert.assertTrue("UPDATE CAMPAIGN FAIL", getResponseDataBoolean("$.success"));
                break;
            } catch (AssertionError error) {
                waitConstant(2);
            }
        }
        debugResponse();
        verifyStatusCode200("CAMPAIGN", "UPDATE STATUS OF THE CAMPAIGN");
        Assert.assertTrue("UPDATE CAMPAIGN FAIL", getResponseDataBoolean("$.success"));

        // SET DATA FOR NEXT STEPS
        setCampaignFreeBumpID(getResponseData("$.updated.id"));
        setCategoryID(getResponseData("$.updated.condition.category_id"));
        setAccountID(getResponseData("$.updated.condition.account_id"));
        setStartAt(getResponseData("$.updated.start_at"));
        setExpiredAt(getResponseData("$.updated.expired_at"));
        setIsActiveCampaignFreeBump(getResponseData("$.updated.is_active"));
        setLimit(getResponseData("$.updated.limit"));
        setCampaignFreeBumpType(getResponseData("$.updated.type"));
    }

    protected static void updateCampaignFreeBumpTime(int startDays, int expireDays, Boolean statusCampaignFreeBump, String campaignID) {
        String startTime = String.valueOf(Integer.parseInt(getStartAt()) + (86400 * startDays));
        String expiredTime = String.valueOf(Integer.parseInt(getExpiredAt()) + (86400 * expireDays));

        initBodyJson();
        JsonObject bodyCondition = new JsonObject();
        bodyCondition.addProperty("account_id", 10000001); // Not use for adding campaign :10145199
        bodyCondition.addProperty("category_id", 1000); // Not use for adding campaign
        bodyJson.add("condition", bodyCondition);

        bodyJson.addProperty("limit", 40); // Not use for adding campaign
        bodyJson.addProperty("start_at", Integer.parseInt(startTime));
        bodyJson.addProperty("expired_at", Integer.parseInt(expiredTime));
        bodyJson.addProperty("is_active", statusCampaignFreeBump);
        bodyJson.addProperty("type", "premium_service");
        bodyJson.addProperty("rank", Integer.parseInt("1")); // Not use for adding campaign

        for (int i = 0; i < numberOfRetry; i++) {
            try {
                response = put(bodyJson, String.format(gatewayFreePremium_UpdateCampaign_FreeBump, campaignID));
                verifyStatusCode200("CAMPAIGN", "UPDATE CAMPAIGN TIME");
                Assert.assertTrue("UPDATE CAMPAIGN FAIL", getResponseDataBoolean("$.success"));
                break;
            } catch (AssertionError error) {
                waitConstant(2);
            }
        }
        debugResponse();
        verifyStatusCode200("CAMPAIGN", "UPDATE CAMPAIGN TIME");
        Assert.assertTrue("UPDATE CAMPAIGN FAIL", getResponseDataBoolean("$.success"));

        // SET DATA FOR NEXT STEPS
        setCampaignFreeBumpID(getResponseData("$.updated.id"));
        setCategoryID(getResponseData("$.updated.condition.category_id"));
        setAccountID(getResponseData("$.updated.condition.account_id"));
        setStartAtUpdated(getResponseData("$.updated.start_at"));
        setExpireAtUpdated(getResponseData("$.updated.expired_at"));
        setIsActiveCampaignFreeBump(getResponseData("$.updated.is_active"));
        setLimit(getResponseData("$.updated.limit"));
        setCampaignFreeBumpType(getResponseData("$.updated.type"));
    }

    protected static void verifyCampaignFreeBumpUpdateSuccess(Boolean statusOfCampaign) {

        Assert.assertNotEquals("START TIME DOES NOT CHANGE", getStartAtUpdated(), getStartAt());
        Assert.assertNotEquals("EXIPRE TIME DOES NOT CHANGE", getExpireAtUpdated(), getExpiredAt());
        Assert.assertNotNull("CAMPAIGN ID IS NULL", getCampaignFreeBumpID());
        Assert.assertEquals("CATEGORY DOES NOT MATCH", "1000", getCategoryID());
        Assert.assertEquals("ACCOUNT ID DOES NOT MATCH", "10000001", getAccountID());
        Assert.assertEquals("STATUS OF CAMPAIGN DOES NOT MATCH", statusOfCampaign, getIsActiveCampaignFreeBump());
        Assert.assertEquals("LIMIT DOES NOT MATCH", "40", getLimit());
        Assert.assertEquals("CAMPAIGN TYPE DOES NOT MATCH", "premium_service", getCampaignFreeBumpType());
    }
}
