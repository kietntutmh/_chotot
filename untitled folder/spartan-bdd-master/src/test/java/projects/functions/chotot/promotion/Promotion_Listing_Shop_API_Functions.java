package projects.functions.chotot.promotion;

import org.junit.Assert;
import projects.functions.APISupportFunctions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static api.configuration.GatewayConfig.*;
import static projects.configuaration.CategoryConfig.*;

public class Promotion_Listing_Shop_API_Functions extends APISupportFunctions {
    private static String userToken;
    private static String userPhone;
    private static String userAccountID;
    private static String userGroupID;
    private static String campaignID;
    private static int startedDate = -1;
    private static int expiredDate = -1;    //Related to Shop Promotion
    private static int reTryCommon = 5;

    public static void setUserToken(String userToken) {
        Promotion_Listing_Shop_API_Functions.userToken = userToken;
    }

    public static String getUserToken() {
        return userToken;
    }

    public static void setUserAccountID(String userAccountID) {
        Promotion_Listing_Shop_API_Functions.userAccountID = userAccountID;
    }

    public static String getUserAccountID() {
        return userAccountID;
    }

    public static String getUserGroupID() {
        return userGroupID;
    }

    public static void setUserGroupID(String userGroupID) {
        Promotion_Listing_Shop_API_Functions.userGroupID = userGroupID;
    }

    public static String getCampaignID() {
        return campaignID;
    }

    public static void setCampaignID(String campaignID) {
        Promotion_Listing_Shop_API_Functions.campaignID = campaignID;
    }

    public static String getUserPhone() {
        return userPhone;
    }

    public static void setUserPhone(String userPhone) {
        Promotion_Listing_Shop_API_Functions.userPhone = userPhone;
    }

    public static String createPromotionGroupByListUser(List<String> userAccountID) {
        String user_list = "";
        for (int i = 0; i < userAccountID.size(); i++) {
            user_list += userAccountID.get(i);
            if (i != userAccountID.size() - 1)
                user_list += ",";
        }
        String jsonBody = "[\n" +
                "  {\n" +
                "    \"name\": \"Automation Test Promotion Group Listing Fee Name \"" + System.currentTimeMillis() / 1000 + ",\n" +
                "      \"description\": \"Automation Test Promotion Group Listing Fee Desc \"" + System.currentTimeMillis() / 1000 + ",\n" +
                "      \"user_list\": [\n" +
                "        " + user_list + "  \n" +
                "      ],\n" +
                "      \"created_by\": 1,\n" +
                "      \"created\": \"10/07/2021\",\n" +
                "      \"approved_by\": 1\n" +
                "  }  \n" +
                "]";
        response = post(jsonBody, gatewayPromotion_CreateGroup);
        verifyStatusCode200("API adds User to Promotion Group is Failed");
        String groupID = getResponseData("$.0");
        Assert.assertNotNull("Can't get Group ID", groupID);

        //Addition Benefit
        setUserGroupID(groupID);

        return groupID;
    }

    public static String createPromotionGroupByUser(String userAccountID) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        String today = dateFormat.format(date);

        String jsonBody = "[\n" +
                "  {\n" +
                "    \"name\": \"Automation Test Promotion Group Listing Fee Name " + System.currentTimeMillis() / 1000 + "\",\n" +
                "      \"description\": \"Automation Test Promotion Group Listing Fee Desc " + System.currentTimeMillis() / 1000 + "\",\n" +
                "      \"user_list\": [\n" +
                "        " + userAccountID + "  \n" +
                "      ],\n" +
                "      \"created_by\": 1,\n" +
                "      \"created\": \"" + today + "\",\n" +
                "      \"approved_by\": 1\n" +
                "  }  \n" +
                "]";
        response = post(jsonBody, gatewayPromotion_CreateGroup);
        verifyStatusCode200("API adds User to Promotion Group is Failed");
        String groupID = getResponseData("$.0");
        Assert.assertNotNull("Can't get Group ID", groupID);

        //Addition Benefit
        setUserGroupID(groupID);
        debugResponse();

        //2. Verify Group is create successfully


        return groupID;
    }

    public static String setPromotionGroup_FreeListing_PTY_House(String groupID, String userAccountID, int numberOfFreeAd) {
        return setPromotionGroup_FreeListing(groupID, userAccountID, CATEID_PTY_HOUSE, numberOfFreeAd, true);
    }

    public static String setPromotionGroup_FreeListing_VEH_Car(String groupID, String userAccountID, int numberOfFreeAd) {
        return setPromotionGroup_FreeListing(groupID, userAccountID, CATEID_VEH_CAR, numberOfFreeAd, true);
    }

    public static String setPromotionGroup_FreeListing_ELT_Phone(String groupID, String userAccountID, int numberOfFreeAd) {
        return setPromotionGroup_FreeListing(groupID, userAccountID, CATEID_ELT_PHONE, numberOfFreeAd, true);
    }

    //Create campaign
    public static String setPromotionGroup_FreeListing(String groupID, String userAccountID, String categoryID, int numberOfFreeAd, boolean isActive) {
        Calendar calendar = Calendar.getInstance();
        Date today = new Date();
        calendar.setTime(today);
        calendar.add(Calendar.HOUR, -1);
        startedDate = (int) (calendar.getTimeInMillis() / 1000);

        calendar.add(Calendar.DAY_OF_MONTH, +100);
        expiredDate = (int) (calendar.getTimeInMillis() / 1000);

        return setPromotionGroup_FreeListing_CORE(groupID, userAccountID, categoryID, numberOfFreeAd, isActive, true);
    }

    public static String setPromotionGroup_FreeListing_NotStarted(String groupID, String userAccountID, String categoryID, int numberOfFreeAd, boolean isActive) {
        Calendar calendar = Calendar.getInstance();
        Date today = new Date();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_MONTH, +1);
        startedDate = (int) (calendar.getTimeInMillis() / 1000);
        return setPromotionGroup_FreeListing_CORE(groupID, userAccountID, categoryID, numberOfFreeAd, isActive, false);
    }

    public static String setPromotionGroup_FreeListing_StartedOnToday(String groupID, String userAccountID, String categoryID, int numberOfFreeAd, boolean isActive) {
        Calendar calendar = Calendar.getInstance();
        Date today = new Date();
        calendar.setTime(today);
        startedDate = (int) (calendar.getTimeInMillis() / 1000);
        waitConstant(5);
        return setPromotionGroup_FreeListing_CORE(groupID, userAccountID, categoryID, numberOfFreeAd, isActive, true);
    }

    public static String setPromotionGroup_FreeListing_Expired(String groupID, String userAccountID, String categoryID, int numberOfFreeAd, boolean isActive) {
        Calendar calendar = Calendar.getInstance();
        Date today = new Date();
        calendar.setTime(today);
        calendar.add(Calendar.MINUTE, -15);
        expiredDate = (int) (calendar.getTimeInMillis() / 1000);

        calendar.add(Calendar.DAY_OF_MONTH, -2);
        startedDate = (int) (calendar.getTimeInMillis() / 1000);

        String result = setPromotionGroup_FreeListing_CORE(groupID, userAccountID, categoryID, numberOfFreeAd, isActive, false);
        waitConstant(10);

        return result;
    }

    private static String setPromotionGroup_FreeListing_CORE(String groupID, String userAccountID, String categoryID, int numberOfFreeAd, boolean isActive, boolean hasLimit) {
        if (expiredDate < 0) {
            expiredDate = 1693050296;
        }

        categoryID = "1000";

        String jsonBody =
                "{\n" +
                        "    \"condition\": {\n" +
                        "        \"user_group_id\": \"" + groupID + "\",\n" +
                        "        \"category_id\": " + categoryID + "\n" +
                        "    },\n" +
                        "    \"limit\": " + numberOfFreeAd + ",\n" +
                        "    \"expired_at\": " + expiredDate + ",\n" +         //August 26, 2023
                        "    \"start_at\": " + startedDate + ",\n" +
                        "    \"is_active\": " + isActive + ",\n" +
                        "    \"type\": \"free_listing\",\n" +
                        "    \"rank\": 10\n" +
                        "}";
        response = post(jsonBody, gatewayPromotion_AddGroupCampaign);
        verifyStatusCode200("API adds Campaign for Group is dead");

        String campaignId = getResponseData("$.id");
        Assert.assertEquals("CAMPAIGN ISN'T CORRECT", String.valueOf(isActive), getResponseData("$.is_active"));
        Assert.assertEquals("NUMBER OF FREE AD ISN'T CORRECT", String.valueOf(numberOfFreeAd), getResponseData("$.limit"));
        Assert.assertEquals("TYPE ISN'T FREE_LISTING", "free_listing", getResponseData("$.type"));
        Assert.assertEquals("CATE IS INCORRECT", String.valueOf(categoryID), getResponseData("$.condition.category_id"));
        Assert.assertEquals("USER GROUP ID IS INCORRECT", String.valueOf(groupID), getResponseData("$.condition.user_group_id"));
        debugResponse();

        //2. Check campaign works currently
        for (int i = 0; i < reTryCommon; i++) {
            try {
                response = get(String.format(gatewayPromotion_CheckCampaign_FreeListing, userAccountID, categoryID));
                verifyStatusCode200("API checks Campaign for Group is dead");
                Assert.assertEquals("CAMPAIGN [" + campaignId + "] starts when not yet on STARTED day", getResponseDataBoolean("$.has_limit"), hasLimit);
                break;
            } catch (AssertionError assertionError) {
                waitConstant(3);
            }
        }
        debugResponse();
        return campaignId;
    }


    public static void verifyFreeSlotAvailable(String userAccountID, int categoryID) {
        //2. Check campaign works currently
        for (int i = 0; i < reTryCommon; i++) {
            try {
                response = get(String.format(gatewayPromotion_CheckCampaign_FreeListing, userAccountID, categoryID));
                verifyStatusCode200("API checks Campaign for Group is dead");
                Assert.assertTrue("Free slot isn't available", getResponseDataBoolean("$.has_limit"));
                break;
            } catch (AssertionError assertionError) {
                waitConstant(5);
            }
        }
        debugResponse();
        Assert.assertTrue("Free slot isn't available", getResponseDataBoolean("$.has_limit"));
    }

    public static void verifyFreeSlotNotAvailable(String userAccountID, int categoryID) {
        //2. Check campaign works currently
        for (int i = 0; i < reTryCommon; i++) {
            try {
                response = get(String.format(gatewayPromotion_CheckCampaign_FreeListing, userAccountID, categoryID));
                verifyStatusCode200("API checks Campaign for Group is dead");
                Assert.assertFalse("Free slot is still available", getResponseDataBoolean("$.has_limit"));
                break;
            } catch (AssertionError assertionError) {
                waitConstant(3);
            }
        }
        debugResponse();
        Assert.assertFalse("Free slot is still available", getResponseDataBoolean("$.has_limit"));
    }

    public static void announceShopPromotion(String userAccountID, String announceName, String announceTitle, String announceContent) {
        String body =
                "[\n" +
                        "  {\n" +
                        "    \"name\": \"" + announceName + "\",\n" +
                        "    \"title\": \"" + announceTitle + "\",\n" +
                        "    \"content\": \"" + announceContent + "\",\n" +
                        "    \"page\": [\n" +
                        "      \"private_dashboard\"\n" +
                        "    ],\n" +
                        "    \"page_type\": [\n" +
                        "      \"dashboard\"\n" +
                        "    ],\n" +
                        "    \"platform\": [\n" +
                        "      \"oneweb\",\n" +
                        "      \"ios\",\n" +
                        "      \"android\"\n" +
                        "    ],\n" +
                        "    \"type\": \"defined\",\n" +
                        "    \"position\": \"annoucement_box\",\n" +
                        "    \"icon\": \"https://static.chotot.com/storage/chotot-icons-me/dongtot.png\",\n" +
                        "    \"background\": \"#f7fece\",\n" +
                        "    \"user_group\": \"default\",\n" +
                        "    \"priority\": \"1\",\n" +
                        "    \"show_once\": false,\n" +
                        "    \"button_text\": \"NHẬN NGAY\",\n" +
                        "    \"web_link\": \"https://gateway.chotot.org/v1/private/promotion/redeem?token=U2FsdGVkX1%2FRbegTxU1uKuGlvqDZZ%2Br91fiS8qBosh7CPlPBBCVNzQN00%2BG%2FWTtxA3AC8avH7S5czRGJ6Q4qeA%3D%3D\",\n" +
                        "    \"app_link\": \"https://gateway.chotot.org/v1/private/promotion/redeem?token=U2FsdGVkX1%2FRbegTxU1uKuGlvqDZZ%2Br91fiS8qBosh7CPlPBBCVNzQN00%2BG%2FWTtxA3AC8avH7S5czRGJ6Q4qeA%3D%3D\",\n" +
                        "    \"status\": \"testing\",\n" +
                        "    \"accounts_test\": [\n" +
                        "      \"" + userAccountID + "\"\n" +
                        "    ]\n" +
                        "  }\n" +
                        "]";


        response = post(bodyJsonArray, gatewayShop_Announce);
    }


}
