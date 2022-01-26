package projects.functions.chotot.shops;

import api.utils.GetAccessToken;
import com.vn.chotot.logger.Log4jFactory;
import io.cucumber.core.internal.gherkin.deps.com.google.gson.JsonArray;
import io.cucumber.core.internal.gherkin.deps.com.google.gson.JsonObject;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import projects.functions.APISupportFunctions;

import java.net.Inet4Address;
import java.net.UnknownHostException;

import static api.configuration.DataConfig.newUserID;
import static api.configuration.DataConfig.tempRetryPayOrder;
import static api.configuration.GatewayConfig.*;
import static api.utils.GetAccessToken.*;
import static com.vn.chotot.configuration.Constant.DOMAIN;
import static com.vn.chotot.keywords.selenium.Utils.getProjectDir;
import static desktop.configuration.LoginConfig.tempUserPhone;
import static projects.functions.chotot.utils.GmailAPI.getGmailMessageList;

public class C2CShop_Food_API_Functions extends APISupportFunctions {
    static final Logger log = Log4jFactory.instance().createClassLogger(GetAccessToken.class);

    private static String ownerToken = "";
    private static String cpToken = "";
    private static String ownerPhone = "";
    private static String ownerAccountID = "";

    public static String shopTempID = "";
    public static String shopTempName = "";
    public static String fakeCateID = "";
    public static String adID = "";

    private final static int CATE_VEH_ID = 2000;
    private final static int CATE_PTY_ID = 1000;
    private final static int CATE_ELT_ID = 5000;
    private final static int CATE_FOOD_ID = 7000;
    private final static int CATE_REFRIGERATION_ID = 9000;
    private final static int CATE_PET_ID = 12000;
    private final static int CATE_HOUSEHOLD_ID = 14000;

    private final static String DEFAULT_ADDRESS = "2 Ngô Đức Kế, P. Bến Nghé, Q.1, TPHCM ";
    private final static String DEFAULT_SHOPNAME = "Auto Temp Shop ";
    private final static String DEFAULT_DESCRIPTION = "Auto Temp Shop Description: Update information for Shop ";
    private final static String DEFAULT_URL = "thuc-pham-";
    public final static String CHOTOT_SHOP_URL = "https://www.chotot." + DOMAIN + "/cua-hang/";

    private static String tmp_name = "", tmp_address = "", tmp_desc = "", tmp_cover = "", tmp_avatar = "", tmp_new_url = "";
    private static double tmp_long = 106.69588480000002, tmp_lat = 10.8101632;
    private static String shop_alias = "", shop_name = "", shop_status = "", shop_expired = "", shop_queueId = "", shop_url = "", shop_address = "", shop_desc = "", shop_cover = "", shop_avatar = "", shop_cp_status = ""       //CP approves -> accepted, CP rejects -> rejected
            , shop_remark = "";
    private static boolean shop_verified = false;


    public static String getOwnerToken() {
        return ownerToken;
    }

    public static void setOwnerToken() {
        ownerToken = standardizeToken(getAccessTokenOfNewUser());
        ownerPhone = tempUserPhone;
        ownerAccountID = newUserID;
        global_accountID = ownerAccountID;
        global_accessToken = ownerToken;

        cpToken = getAccessTokenOfCPGrantPermission();
    }

    public static String getOwnerPhone() {
        return ownerPhone;
    }

    public static void setTmp_name(String tmp_name) {
        C2CShop_Food_API_Functions.tmp_name = tmp_name;
    }

    public static void setTmp_address(String tmp_address) {
        C2CShop_Food_API_Functions.tmp_address = tmp_address;
    }

    public static void setTmp_desc(String tmp_desc) {
        C2CShop_Food_API_Functions.tmp_desc = tmp_desc;
    }

    public static String createTempShop(String categoryName) {
        return createTempShop(ownerToken, categoryName, "200");
    }

    public static String createTempShop(String categoryName, String expectedStatusCode) {
        return createTempShop(ownerToken, categoryName, expectedStatusCode);
    }

    public static String createTempShop(String ownerToken, String categoryName, String expectedStatusCode) {
        ownerToken = standardizeToken(ownerToken);
        bodyJson = new JsonObject();
        bodyJson.addProperty("phoneNumber", ownerPhone);
        bodyJson.addProperty("categoryId", getCateIDByName(categoryName));
        response = post(ownerToken, bodyJson, gatewayShopC2C_CreateTempShop);
        verifyStatusCode("PREVIEW SHOP C2C", gatewayShopC2C_CreateTempShop, expectedStatusCode);
        shopTempName = getResponseData(response, "$.name");
        return shopTempName;
    }

    public static void activateNewShop() {
        activateNewShop("200");
    }

    public static void activateNewShop(String expectedCode) {
        JsonObject bodyURL = new JsonObject();
        bodyURL.addProperty("url", getShopURL());

        bodyJson = new JsonObject();
        bodyJson.add("url", bodyURL);

        response = put(ownerToken, bodyJson, String.format(gatewayShopC2C_UpdateNewShop, shop_alias));
        verifyStatusCode(response, expectedCode);
    }

    public static void activateNewShopWithImages() {
        activateNewShopWithImages("200");
    }

    public static void activateNewShopWithImages(String expectedCode) {
        JsonObject bodyURL = new JsonObject();
        bodyURL.addProperty("url", getShopURL());

        bodyJson = new JsonObject();
        bodyJson.addProperty("profileImageUrl", shop_avatar);
        bodyJson.addProperty("coverImageUrl", shop_cover);
        bodyJson.add("url", bodyURL);

//        bodyJson.addProperty("name", shop_name);
//        bodyJson.addProperty("address", shop_address);
//        bodyJson.addProperty("description", shop_desc);
//        bodyJson.addProperty("longitude", tmp_long);
//        bodyJson.addProperty("latitude", tmp_lat);
//        bodyJson.addProperty("cssId", 1);

        response = put(ownerToken, bodyJson, String.format(gatewayShopC2C_UpdateNewShop, shop_alias));
        verifyStatusCode(response, expectedCode);
    }

    public static void updateNewShop_Name(String expectedCode) {
        String update = "Update Shop After Approved " + getTimeStamp();

        bodyJson = new JsonObject();
        shopTempName += update;
        bodyJson.addProperty("name", shopTempName);

        response = put(ownerToken, bodyJson, String.format(gatewayShopC2C_UpdateNewShop, shop_alias));
        verifyStatusCode(response, expectedCode);
    }

    public static void updateNewShop_URL() {
        updateNewShop_URL("200");
    }

    public static void updateNewShop_URL(String expectedCode) {
        String update = "Update Shop After Approved " + getTimeStamp();
        setShopURLTemp(getShopURL() + update.toLowerCase().trim().replace(" ", "-"));

        JsonObject bodyURL = new JsonObject();
        bodyURL.addProperty("url", getShopURLTemp());

        bodyJson = new JsonObject();
        bodyJson.add("url", bodyURL);

        response = put(ownerToken, bodyJson, String.format(gatewayShopC2C_UpdateNewShop, shop_alias));
        verifyStatusCode(response, expectedCode);
    }

    public static void updateNewShop_Excep_URL_Name() {
        updateNewShop_Excep_URL_Name("200");
    }

    public static void updateNewShop_Excep_URL_Name(String expectedCode) {
        String update = "Update Shop After Approved " + getTimeStamp();
        JsonObject bodyURL = new JsonObject();
        bodyURL.addProperty("url", getShopURL() + update.toLowerCase().trim().replace(" ", "-"));

        bodyJson = new JsonObject();

//        shop_name += update;
//        bodyJson.addProperty("name", shop_name);

        shop_address += update;
        bodyJson.addProperty("address", shop_address);

        shop_desc += update;
        bodyJson.addProperty("description", shop_desc);

        bodyJson.addProperty("profileImageUrl", shop_avatar);
        bodyJson.addProperty("coverImageUrl", shop_cover);
        bodyJson.addProperty("longitude", tmp_long);
        bodyJson.addProperty("latitude", tmp_lat);
        bodyJson.addProperty("cssId", 1);
//        bodyJson.add("url", bodyURL);

        response = put(ownerToken, bodyJson, String.format(gatewayShopC2C_UpdateNewShop, shop_alias));
        verifyStatusCode(response, expectedCode);
    }

    public static void updateNewShop_AfterPaid_All() {
        JsonObject bodyURL = new JsonObject();
        bodyURL.addProperty("url", getShopURL());
        bodyJson = new JsonObject();
        shop_avatar = uploadProfileImage();
        bodyJson.addProperty("profileImageUrl", shop_avatar);
        shop_cover = uploadCoverImage();
        bodyJson.addProperty("coverImageUrl", shop_cover);
        bodyJson.add("url", bodyURL);

        shop_name = shopTempName;
        bodyJson.addProperty("name", shop_name);

        shop_address = DEFAULT_ADDRESS + getTimeStamp();
        bodyJson.addProperty("address", shop_address);

        shop_desc = DEFAULT_DESCRIPTION + getTimeStamp();
        bodyJson.addProperty("description", shop_desc);

        bodyJson.addProperty("longitude", tmp_long);
        bodyJson.addProperty("latitude", tmp_lat);
        bodyJson.addProperty("cssId", 1);
    }

    public static void updateTempShop(String categoryName, String expectedCode) {
        updateTempShop(ownerToken, categoryName, expectedCode);
    }

    public static void updateTempShop(String categoryName) {
        updateTempShop(ownerToken, categoryName, "200");
    }

    @Deprecated  //OLD function
    public static void updateTempShop(String ownerToken, String categoryName, String expectedCode) {
        updateTempShop(ownerToken, categoryName, expectedCode, "update");
    }
    public static void updateTempShop(String ownerToken, String categoryName, String url, String expectedCode) {
        bodyJson = new JsonObject();

        if(tmp_name.isEmpty()){
            tmp_name = DEFAULT_SHOPNAME + getTimeStamp();
        }
        bodyJson.addProperty("name", tmp_name);

        if(tmp_address.isEmpty()){
            tmp_address = DEFAULT_ADDRESS + getTimeStamp();
        }
        bodyJson.addProperty("address", tmp_address);

        if(tmp_desc.isEmpty()){
            tmp_desc = DEFAULT_DESCRIPTION + getTimeStamp();
        }
        bodyJson.addProperty("description", tmp_desc);


        bodyJson.addProperty("phoneNumber", ownerPhone);
        bodyJson.addProperty("categoryId", getCateIDByName(categoryName));

        tmp_cover = uploadCoverImage();
        bodyJson.addProperty("coverImageUrl", tmp_cover);

        tmp_avatar = uploadProfileImage();
        bodyJson.addProperty("profileImageUrl", tmp_avatar);

        bodyJson.addProperty("longitude", tmp_long);
        bodyJson.addProperty("latitude", tmp_lat);

        JsonObject bodyURL = new JsonObject();
        setShopURL(getShopURL() + "-" + url);
        bodyURL.addProperty("url", getShopURL());
        bodyJson.add("url", bodyURL);

        response = put(ownerToken, bodyJson, gatewayShopC2C_UpdateTempShop);
        verifyStatusCode(response, expectedCode);
        debugResponse();
        shopTempName = tmp_name;
    }

    public static void updateTempShopMissingParam(String categoryName, String missedParam) {
        updateTempShopMissingParam(ownerToken, categoryName, missedParam);
    }

    public static void updateTempShopMissingParam(String ownerToken, String categoryName, String missedParam) {
        bodyJson = new JsonObject();
        tmp_name = DEFAULT_SHOPNAME + getTimeStamp();
        bodyJson.addProperty("name", tmp_name);

        tmp_address = DEFAULT_ADDRESS + getTimeStamp();
        bodyJson.addProperty("address", tmp_address);

        tmp_desc = DEFAULT_DESCRIPTION + getTimeStamp();
        bodyJson.addProperty("description", tmp_desc);

        bodyJson.addProperty("phoneNumber", ownerPhone);

        bodyJson.addProperty("categoryId", getCateIDByName(categoryName));

        tmp_cover = uploadCoverImage();
//        bodyJson.addProperty("coverImageUrl", tmp_cover);

        tmp_avatar = uploadProfileImage();
//        bodyJson.addProperty("profileImageUrl", tmp_avatar);

        bodyJson.addProperty("longitude", tmp_long);
        bodyJson.addProperty("latitude", tmp_lat);

        JsonObject bodyURL = new JsonObject();
        setShopURL(getShopURL() + "-updated");
        bodyURL.addProperty("url", getShopURL());
        bodyJson.add("url", bodyURL);

        //Remove param
        switch (missedParam.trim().toUpperCase().replace(" ", "")) {
            case "PROFILE":
            case "AVATAR":
                bodyJson.remove("profileImageUrl");
                break;
            case "COVER":
                bodyJson.remove("coverImageUrl");
                break;
            case "NAME":
                bodyJson.remove("name");
                break;
            case "ADDRESS":
                bodyJson.remove("address");
                break;
            case "DESC":
            case "DESCRIPTION":
                bodyJson.remove("description");
                break;
            case "PHONE":
            case "PHONENUMBER":
                bodyJson.remove("phoneNumber");
                break;
            case "CATE":
            case "CATEGORY":
                bodyJson.remove("categoryId");
                break;
            case "LONG":
            case "LONGITUDE":
                bodyJson.remove("longitude");
                break;
            case "LAT":
            case "LATITUDE":
                bodyJson.remove("latitude");
                break;

        }
        response = put(ownerToken, bodyJson, gatewayShopC2C_UpdateTempShop);
        debugResponse(response);
//        verifyStatusCode(response, "400");
        shopTempName = tmp_name;
    }

    private static void setShopURLTemp(String url) {
        tmp_new_url = url;
    }

    private static void setShopURL(String url) {
        shop_url = url;
    }

    private static String getShopURL() {
        if (shop_url.isEmpty()) {
            shop_url = DEFAULT_URL + tmp_name.trim().toLowerCase().replace(" ", "-");
            return shop_url;
        } else
            return shop_url;
    }

    private static String getShopURLTemp() {
        return tmp_new_url;
    }

    private static void setShopAlias(String alias) {
        shop_alias = alias;
    }

    public static void updateTempShop_CategoryID(String categoryId) {
        bodyJson = new JsonObject();
        tmp_name = DEFAULT_SHOPNAME + getTimeStamp();

        bodyJson.addProperty("categoryId", categoryId);

        response = put(ownerToken, bodyJson, gatewayShopC2C_UpdateTempShop);
        verifyStatusCode(response, "200");
        shopTempName = tmp_name;
    }

    public static String uploadCoverImage() {
        String imagePath = getProjectDir() + "/images/shops/food/cover.jpg";
        response = postImageShop(ownerToken, gatewayShopC2C_UpdateTempShop_CoverPhoto, imagePath);
        verifyStatusCode(response, "200");
        query = "$.coverImageUrl";
        return getResponseData(response, query);
    }

    public static String uploadProfileImage() {
        String imagePath = getProjectDir() + "/images/shops/food/avatar.jpg";
        response = postImageShop(ownerToken, gatewayShopC2C_UpdateTempShop_AvatarPhoto, imagePath);
        verifyStatusCode(response, "200");
        query = "$.profileImageUrl";
        return getResponseData(response, query);
    }

    public static int getNumberOfTempShop() {
        ownerToken = standardizeToken(ownerToken);
        response = get(ownerToken, String.format(gatewayShopC2C_Alias_Shop, shop_alias));
        verifyStatusCode(response, "200");
        query = "$.name";
        try {
            return getResponseDataList(response, query).size();
        } catch (ClassCastException strToListex) {
            return 1;
        }
    }

    public static int getShopUrlId() {
        response = get(ownerToken, String.format(gatewayShopC2C_Alias_Shop, shop_alias));
        verifyStatusCode(response, "200");
        String query = String.format("$.urls[?(@.url == '%s')].id", getShopURL());

        //retry
        int i = 0;
        while (i < 7) {
            try {
                return Integer.parseInt(getResponseData(response, query).replace("[", "").replace("]", ""));
            } catch (NumberFormatException e) {
                waitConstant(2);
                i++;
            }
        }
        return Integer.parseInt(getResponseData(response, query).replace("[", "").replace("]", ""));
    }

    public static int getShopUrlIdTemp() {
        response = get(ownerToken, String.format(gatewayShopC2C_Alias_Shop, shop_alias));
        verifyStatusCode(response, "200");
        String query = String.format("$.urls[?(@.url == '%s')].id", getShopURLTemp());

        //retry
        int i = 0;
        while (i < 7) {
            try {
                return Integer.parseInt(getResponseData(response, query).replace("[", "").replace("]", ""));
            } catch (NumberFormatException e) {
                waitConstant(2);
                i++;
            }
        }
        return Integer.parseInt(getResponseData(response, query).replace("[", "").replace("]", ""));
    }

    @Deprecated
    public static void acceptNewShopByQueueID() {
        //Add profile info
        JsonObject urlElementJson = new JsonObject();
        urlElementJson.addProperty("id", getShopUrlId());
        urlElementJson.addProperty("url", DEFAULT_URL + getTimeStamp());
        urlElementJson.addProperty("allowColumnsEdit", (String) null);

        JsonArray urlArrayJson = new JsonArray();
        urlArrayJson.add(urlElementJson);

        JsonObject bodyProfileJson = new JsonObject();
        bodyProfileJson.addProperty("name", shop_name);
        bodyProfileJson.addProperty("address", tmp_address);
        bodyProfileJson.addProperty("description", "New Description of profile Shop");
        bodyProfileJson.add("urls", urlArrayJson);

        bodyJson = new JsonObject();
        bodyJson.addProperty("queue_id", Integer.parseInt(shop_queueId));
        bodyJson.addProperty("reviewer_id", 5);
        shop_remark = "Auto Test CP approve new Shop without updated anything";
        bodyJson.addProperty("remark", shop_remark);
        bodyJson.add("profile", bodyProfileJson);

        response = post(cpToken, bodyJson, gatewayShopC2C_Accept_Shop);
        System.out.println("DEBUG Final: " + getResponseBodyAsString(response));
    }

    public static void rejectNewShopByAlias_NewUrl() {
        bodyJson = new JsonObject();
        bodyJson.addProperty("adminId", 9);
        shop_remark = "Chotot rejected Shop New URL " + getTimeStamp();
        bodyJson.addProperty("remark", shop_remark);
        bodyJson.addProperty("reasonId", 2);

        JsonObject urlElementJson = new JsonObject();
        urlElementJson.addProperty("id", getShopUrlIdTemp());
        urlElementJson.addProperty("url", getShopURLTemp());
        JsonArray urlArrayJson = new JsonArray();
        urlArrayJson.add(urlElementJson);
        bodyJson.add("urls", urlArrayJson);

        response = put(cpToken, bodyJson, String.format(gatewayShopC2C_Reject_Shop_ByAlias, shop_alias));
        verifyStatusCode(response, "200");

//        updateShopStatusRejected();
    }

    public static void rejectNewShopByAlias_NewName() {
        bodyJson = new JsonObject();
        bodyJson.addProperty("adminId", 9);
        shop_remark = "Chotot rejected Shop New URL " + getTimeStamp();
        bodyJson.addProperty("remark", shop_remark);
        bodyJson.addProperty("reasonId", 2);

        JsonObject urlElementJson = new JsonObject();
        urlElementJson.addProperty("id", getShopUrlId());
        urlElementJson.addProperty("url", getShopURL());
        JsonArray urlArrayJson = new JsonArray();
        urlArrayJson.add(urlElementJson);
        bodyJson.add("urls", urlArrayJson);

        response = put(cpToken, bodyJson, String.format(gatewayShopC2C_Reject_Shop_ByAlias, shop_alias));
        verifyStatusCode(response, "200");

//        updateShopStatusRejected();       //When New shops Name is rejected, the shops is still existed with status "accepted"
    }

    public static void rejectNewShopByAlias() {
        bodyJson = new JsonObject();
        bodyJson.addProperty("adminId", 9);
        shop_remark = "Chotot rejected Shop " + getTimeStamp();
        bodyJson.addProperty("remark", shop_remark);
        bodyJson.addProperty("reasonId", 1);

        JsonObject urlElementJson = new JsonObject();
        urlElementJson.addProperty("id", getShopUrlId());
        urlElementJson.addProperty("url", getShopURLTemp());
        JsonArray urlArrayJson = new JsonArray();
        urlArrayJson.add(urlElementJson);
        bodyJson.add("urls", urlArrayJson);

        response = put(cpToken, bodyJson, String.format(gatewayShopC2C_Reject_Shop_ByAlias, shop_alias));
        verifyStatusCode(response, "200");

        updateShopStatusRejected();
    }

    public static void approveNewShopByAlias_NewURL() {
        bodyJson = new JsonObject();
        bodyJson.addProperty("adminId", 60);
        shop_remark = "Chotot CP approves new Shop URL";
        bodyJson.addProperty("remark", shop_remark);

        JsonObject urlElementJson = new JsonObject();
        urlElementJson.addProperty("id", getShopUrlId());
        urlElementJson.addProperty("url", getShopURLTemp());
        urlElementJson.addProperty("allowColumnsEdit", false);

        JsonArray urlArrayJson = new JsonArray();
        urlArrayJson.add(urlElementJson);
        bodyJson.add("urls", urlArrayJson);

        response = put(cpToken, bodyJson, String.format(gatewayShopC2C_Approve_Shop_ByAlias, shop_alias));
        verifyStatusCode(response, "200");

        setShopURL(getShopURLTemp());       //Update Temp_Shop_URL to Shop_URL
        updateShopStatusAccepted();
    }

    public static void approveNewShopByAlias_NewName() {
        shop_name = shopTempName;
        bodyJson = new JsonObject();
        bodyJson.addProperty("adminId", 60);
        shop_remark = "Chotot CP approves new Shop NAME";
        bodyJson.addProperty("remark", shop_remark);
        bodyJson.addProperty("name", shop_name);

        response = put(cpToken, bodyJson, String.format(gatewayShopC2C_Approve_Shop_ByAlias, shop_alias));
        verifyStatusCode(response, "200");
        updateShopStatusAccepted();
    }

    public static void approveNewShopByAlias() {
        bodyJson = new JsonObject();
        bodyJson.addProperty("adminId", 60);
        shop_remark = "Chotot CP approves new Shop Remark";
        bodyJson.addProperty("remark", shop_remark);

        response = put(cpToken, bodyJson, String.format(gatewayShopC2C_Approve_Shop_ByAlias, shop_alias));
        verifyStatusCode(response, "200");

        updateShopStatusAccepted();
    }

    public static void approveNewShopByAlias_UpdateParam(String updatedParam) {
        String updateApprove = " APPROVE UPDATED PARAM";
        bodyJson = new JsonObject();

        switch (updatedParam.trim().toUpperCase()) {
            case "URL":
                JsonObject urlElementJson = new JsonObject();
                urlElementJson.addProperty("id", getShopUrlId());

                setShopURL(getShopURL() + updateApprove.replace(" ", "-").toLowerCase());
                urlElementJson.addProperty("url", getShopURL());
                urlElementJson.addProperty("allowColumnsEdit", true);

                JsonArray urlArrayJson = new JsonArray();
                urlArrayJson.add(urlElementJson);

                bodyJson.add("urls", urlArrayJson);
                break;
            case "NAME":
                shop_name += updateApprove;
                bodyJson.addProperty("name", shop_name);
                break;
            case "ADDRESS":
                shop_address += updateApprove;
                bodyJson.addProperty("address", shop_address);
                break;
            case "DESC":
            case "DESCRIPTION":
                shop_desc += updateApprove;
                bodyJson.addProperty("description", shop_desc);
                break;

        }
        bodyJson.addProperty("adminId", 60);
        shop_remark = "Chotot CP approves new Shop " + updateApprove;
        bodyJson.addProperty("remark", shop_remark);

        response = put(cpToken, bodyJson, String.format(gatewayShopC2C_Approve_Shop_ByAlias, shop_alias));
        verifyStatusCode(response, "200");

        updateShopStatusAccepted();
    }

    public static void approveNewShopByAlias_UpdateInfo(boolean isUpdateUrl) {
        bodyJson = new JsonObject();
        String updateApprove = " APPROVE UPDATED";
        //Add profile info
        shop_name += updateApprove;
        bodyJson.addProperty("name", shop_name);

        shop_address += updateApprove;
        bodyJson.addProperty("address", shop_address);

        shop_desc += updateApprove;
        bodyJson.addProperty("description", shop_desc);
        bodyJson.addProperty("adminId", 60);

        shop_remark = "Chotot CP approves new Shop " + updateApprove;
        bodyJson.addProperty("remark", shop_remark);

        if (isUpdateUrl) {
            JsonObject urlElementJson = new JsonObject();
            urlElementJson.addProperty("id", getShopUrlId());

            setShopURL(getShopURL() + updateApprove.replace(" ", "-").toLowerCase());
            urlElementJson.addProperty("url", getShopURL());
            urlElementJson.addProperty("allowColumnsEdit", true);

            JsonArray urlArrayJson = new JsonArray();
            urlArrayJson.add(urlElementJson);

            bodyJson.add("urls", urlArrayJson);
        }
        response = put(cpToken, bodyJson, String.format(gatewayShopC2C_Approve_Shop_ByAlias, shop_alias));
        verifyStatusCode(response, "200");

        updateShopStatusAccepted();
    }

    public static void denyEditURL() {
        bodyJson = new JsonObject();
        bodyJson.addProperty("adminId", 9);
        bodyJson.addProperty("allowColumnsEdit", false);
        response = put(cpToken, bodyJson, String.format(gatewayShopC2C_Accept_EditUrl, shop_alias));
        verifyStatusCode(response, "200");
    }

    public static void denyEditName() {
        bodyJson = new JsonObject();
        bodyJson.addProperty("adminId", 9);
        bodyJson.addProperty("allowColumnsEdit", false);
        response = put(cpToken, bodyJson, String.format(gatewayShopC2C_Accept_EditName, shop_alias));
        verifyStatusCode(response, "200");
    }

    public static void allowEditURL() {
        bodyJson = new JsonObject();
        bodyJson.addProperty("adminId", 9);
        bodyJson.addProperty("allowColumnsEdit", true);
        response = put(cpToken, bodyJson, String.format(gatewayShopC2C_Accept_EditUrl, shop_alias));
        verifyStatusCode(response, "200");
    }

    public static void allowEditName() {
        bodyJson = new JsonObject();
        bodyJson.addProperty("adminId", 9);
        bodyJson.addProperty("allowColumnsEdit", true);
        response = put(cpToken, bodyJson, String.format(gatewayShopC2C_Accept_EditName, shop_alias));
        verifyStatusCode(response, "200");
    }

    private static void updateShopStatusAccepted() {
        shop_cp_status = "accepted";
    }

    private static void updateShopStatusRejected() {
        shop_cp_status = "rejected";
    }

    public static void userCloseShop(String expectedCode) {
        response = put(ownerToken, String.format(gatewayShopC2C_User_CloseShop, shop_alias));
        verifyStatusCode(response, expectedCode);
    }

    public static String createFreeTokenToExtend(String months) {
        return createFreeTokenToExtend(months, "200");
    }

    public static String createFreeTokenToExtend(String months, String expectedCode) {
//        response = postWithoutBody(ownerToken, String.format(gatewayShopC2C_CS_ExtentShopFreeToken, ownerAccountID, shop_alias, months));
        initBodyJson();
        bodyJson.addProperty("account_id", ownerAccountID);
        bodyJson.addProperty("month", Integer.parseInt(months));
        response = post(ownerToken, bodyJson, gatewayShopC2C_CS_ExtentShopFreeTokenByJson);
        verifyStatusCode(response, "TOKEN-EXTEND-C2CSHOP", gatewayShopC2C_CS_ExtentShopFreeTokenByJson, expectedCode);
        Assert.assertEquals(getResponseData(response, "$.action"), "FREE_EXTEND", "New created token isn't Free extend token");
        Assert.assertEquals(getResponseData(response, "$.alias"), shop_alias, "Shop alias is wrong");
        Assert.assertEquals(getResponseData(response, "$.accountId"), ownerAccountID, "Owner Account ID is wrong");

        double daysToExpire = Double.parseDouble(getResponseData(response, "$.daysToExpire"));
        Assert.assertEquals(daysToExpire / 30, Double.parseDouble(months), "Check Days to Expire: Expected[" + months + "] But Actual[" + (daysToExpire / 30) + "]");

        String freeToken = getResponseData(response, "$.token");
        Assert.assertNotNull(freeToken, "Can't get Free Token from Json.");
        return freeToken;
    }

    public static void extendShop(String months) {
        extendShop(months, "200");
    }

    public static void extendShop(String months, String expectedCode) {
        String freeToken = createFreeTokenToExtend(months);
        response = put(ownerToken, String.format(gatewayShopC2C_CS_ExtentShop, freeToken));
        verifyStatusCode(response, expectedCode);
    }

    public static void expireShop() {
        expireShop("200");
    }

    public static void expireShop(String expectedCode) {
        response = put(cpToken, String.format(gatewayShopC2C_CS_ExpireShop, shop_alias));
        verifyStatusCode(response, expectedCode);
    }

    public static void csBlockShop() {
        csBlockShop("200");
    }

    public static void csBlockShop(String expectedCode) {
        bodyJson = new JsonObject();
        bodyJson.addProperty("adminId", 643);
        bodyJson.addProperty("remark", "CS Blocks Shop");
        bodyJson.addProperty("blockReason", "Auto Test Block Shop");

        response = put(cpToken, bodyJson, String.format(gatewayShopC2C_CS_BlockShop, shop_alias));

        verifyStatusCode(response, expectedCode);
    }

    public static void csUnBlockShop() {
        csUnBlockShop("200");
    }

    public static void csUnBlockShop(String expectedCode) {
        bodyJson = new JsonObject();
        bodyJson.addProperty("adminId", 643);
        bodyJson.addProperty("remark", "CS Blocks Shop");
        bodyJson.addProperty("blockReason", "Auto Test Block Shop");

        response = put(cpToken, bodyJson, String.format(gatewayShopC2C_CS_BlockShop, shop_alias));

        verifyStatusCode(response, expectedCode);
    }

    public static void checkTempShopExist(String shopName) {
        response = get(ownerToken, gatewayShopC2C_GetTempShop);
        verifyStatusCode(response, "200");
        query = "$.name";
        String actualShopName = getResponseData(response, query);
        Assert.assertEquals(shopName.trim().toUpperCase(), actualShopName.trim().toUpperCase());
    }

    public static void checkTempShopInactive(String shopName) {
        response = get(ownerToken, gatewayShopC2C);
        verifyStatusCode(response, "200");
        System.out.println("DEBUG: " + getResponseBodyAsString(response));
        Assert.assertNull(getResponseData(response, String.format("$.[?(@.name = '%s')]", shopName)));     //VUHOANG DEBUG, not yet define the field to get
    }

    public static void checkShopActiveAfterReject(String categoryName) {
//        response = get(ownerToken, gatewayShopC2C);
//        verifyStatusCode(response, "200");
        checkShopActiveAfterApprove(categoryName);
    }

    public static void checkShopActiveAfterApprove(String categoryName) {
        response = get(ownerToken, gatewayShopC2C);
        verifyStatusCode(response, "200");

        String shop_name_actual = getResponseData(response, "$[0].name");
        String shop_address_actual = getResponseData(response, "$[0].address");
        String shop_desc_actual = getResponseData(response, "$[0].description");
        String shop_profile_actual = getResponseData(response, "$[0].profileImageUrl");
        String shop_cover_actual = getResponseData(response, "$[0].coverImageUrl");
        String shop_cp_status_actual = getResponseData(response, "$[0].status");
        String shop_phone_actual = getResponseData(response, "$[0].phoneNumber");
        String shop_alias_actual = getResponseData(response, "$[0].alias");
        String shop_remark_actual = getResponseData(response, "$[0].remark");
        String shop_long_actual = getResponseData(response, "$[0].longitude");
        String shop_lat_actual = getResponseData(response, "$[0].latitude");
        String shop_url_actual = getResponseData(response, "$[0].urls[0].url");
        String shop_category_actual = getResponseData(response, "$[0].shopsCategoriesRelationships[0]");
        String shop_owners_actual = getResponseData(response, "$[0].owners[0]");

        setScenarioPassedByAssertToDefault();

        assertAndCollectError(shop_name_actual, shop_name, "Shop Name");
        assertAndCollectError(shop_address_actual, shop_address, "Shop Address");
        assertAndCollectError(shop_desc_actual, shop_desc, "Shop Desc");
//        assertAndCollectError(shop_profile_actual, shop_avatar.replace(":raw", ":shopava"), "Shop Profile");
//        assertAndCollectError(shop_cover_actual, shop_cover.replace(":raw", ":shopcover"), "Shop Cover");
        assertAndCollectError(shop_cp_status_actual, shop_cp_status, "Shop Status");
        assertAndCollectError(shop_phone_actual, ownerPhone, "Shop Phone");
        assertAndCollectError(shop_alias_actual, shop_alias, "Shop Alias");
        assertAndCollectError(shop_remark_actual, shop_remark, "Shop Remark");
        assertAndCollectError(shop_long_actual, shop_long_actual, "Shop Long");
        assertAndCollectError(shop_lat_actual, shop_lat_actual, "Shop Lat");
        assertAndCollectError(shop_url_actual, getShopURL(), "Shop Url");
        assertAndCollectError(shop_category_actual, getCateIDByName(categoryName), "Shop Category");
        assertAndCollectError(shop_owners_actual, ownerAccountID, "Shop Owner ID");
        Assert.assertTrue(getScenarioPassedByAssert(), getAssertFailedMsg());
    }

    public static void verifyTempShopSettings(String categoryName) {
        ownerToken = standardizeToken(ownerToken);
        response = get(ownerToken, gatewayShopC2C_GetTempShop);
        verifyStatusCode(response, "200");
        Assert.assertEquals(getCateIDByName(categoryName), getResponseDataInt(response, "$.categoryId"), "Category is incorrect");
        Assert.assertEquals(ownerPhone, getResponseData(response, "$.phoneNumber"), "Phone Number is incorrect");

        Assert.assertTrue(shopTempName.startsWith(ownerAccountID), "Shop Name doesn't start with owner ID");
        Assert.assertTrue(shopTempName.endsWith(String.valueOf(CATE_FOOD_ID)), "Shop Name doesn't end with Food Cate ID");
    }

    public static void verifyTempShopSettings_AfterUpdate(String categoryName) {
        ownerToken = standardizeToken(ownerToken);
        response = get(ownerToken, gatewayShopC2C_GetTempShop);
        verifyStatusCode(response, "200");
        Assert.assertEquals(getCateIDByName(categoryName), getResponseDataInt(response, "$.categoryId"), "Category is incorrect");
        Assert.assertEquals(ownerPhone, getResponseData(response, "$.phoneNumber"), "Phone Number is incorrect");
        Assert.assertEquals(shopTempName, getResponseData(response, "$.name"), "Shop name is incorrect");
        Assert.assertEquals(tmp_address, getResponseData(response, "$.address"), "Address is incorrect");
        Assert.assertEquals(tmp_desc, getResponseData(response, "$.description"), "Description is incorrect");
        Assert.assertEquals(tmp_cover, getResponseData(response, "$.coverImageUrl"), "Cover Image is incorrect");
        Assert.assertEquals(tmp_avatar, getResponseData(response, "$.profileImageUrl"), "Avatar Image is incorrect");
    }

    public static void verifyTempShopSettings_CategoryID(String categoryID) {
        ownerToken = standardizeToken(ownerToken);
        response = get(ownerToken, gatewayShopC2C_GetTempShop);
        verifyStatusCode(response, "200");
        String actualCateID = getResponseData(response, "$.categoryId");
        Assert.assertNotEquals(String.valueOf(categoryID), actualCateID, "Category is updated to fake one: " + actualCateID);
    }

    public static void moveNewShopToQueueNew() {

    }

    public static void moveNewShopToQueueContact() {

    }

    public static void moveNewShopToQueueEdit() {

    }

    public static void verifyNewShopNotInQueue() {
        shop_queueId = getQueueIDFromShopAlias(shop_alias);
        Assert.assertNull(shop_queueId, String.format("Check Shop Alias[%s] doesn't exist in Queue: Failed", shop_alias));

        response = get(cpToken, gatewayShopC2C_Queue_GetShop);
    }

    public static void verifyNewShopInQueue() {
        verifyNewShopInQueue("200");
    }

    public static void verifyNewShopInQueue(String expectedStatusCode) {
        shop_queueId = getQueueIDFromShopAlias(shop_alias);
        Assert.assertNotNull(shop_queueId, String.format("Check Shop Alias[%s] exists in Queue: Failed", shop_alias));

        response = get(cpToken, gatewayShopC2C_Queue_GetShop);
    }

    public static String getQueueIDFromShopAlias(String shopAlias) {
        response = get(cpToken, String.format(gatewayShopC2C_Queue_SearchShop, shopAlias));
        shop_queueId = getResponseData(response, "$[0].id");
        return shop_queueId;
    }

    public static void payForTempShopC2C(String categoryName, boolean isFreeShop) {
        payForTempShopC2C(ownerToken, categoryName, isFreeShop, "200");
    }

    public static void payForTempShopC2C(String categoryName, boolean isFreeShop, String expectedStatusCode) {
        payForTempShopC2C(ownerToken, categoryName, isFreeShop, expectedStatusCode);
    }

    public static void payForTempShopC2C(String ownerToken, String categoryName, boolean isFreeShop, String expectedStatusCode) {
        bodyJson = new JsonObject();
        bodyJson.addProperty("source", "desktop_web");
        if (isFreeShop) {
            bodyJson.addProperty("gateway", "freeshop");
        } else {
        }
        bodyJson.addProperty("order_type", "shop_create");
        bodyJson.addProperty("category_id", getCateIDByName(categoryName));
        bodyJson.addProperty("duration", 1);
        bodyJson.addProperty("shop_name", 1);
        bodyJson.addProperty("shop_address", 1);
        bodyJson.addProperty("title", tmp_name);
        try {
            bodyJson.addProperty("ip", Inet4Address.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            bodyJson.addProperty("ip", "192.168.1.1");
        }
        bodyJson.addProperty("return_url", "https://pay.chotot.org/checkout/response");

        for(int i = 0; i < tempRetryPayOrder; i++){
            try{
                response = post(ownerToken, bodyJson, gatewayShopC2C_TempShop_PayShop);
                verifyStatusCode(response, expectedStatusCode);
                break;
            }catch (AssertionError es){
                waitConstant(3);
            }
        }
        debugResponse();
        verifyStatusCode(response, expectedStatusCode);

        //Update info for New shops
        setShopAlias(getResponseData(response, "$.shop_alias"));
        shop_name = getResponseData(response, "$.shop_name");
        shop_status = getResponseData(response, "$.shop_status");
        shop_verified = Boolean.parseBoolean(getResponseData(response, "$.is_verified"));
        shop_expired = getResponseData(response, "$.expired_date");
        shop_url = getShopURL();
        shop_address = tmp_address;
        shop_cover = tmp_cover;
        shop_avatar = tmp_avatar;
        shop_desc = tmp_desc;
    }

    public static void checkShopURLDuplicated() {
        response = head(String.format(gatewayShopC2C_CheckShop_ExistURL, getShopURL()));
        verifyStatusCode(response, "200");
    }

    public static void checkShopURLCreated() {
        response = get(String.format(gatewayShopC2C_CheckShop_ByURL, getShopURL()));
        verifyStatusCode(response, "200");
    }

    public static void navigateToShopURL() {
        navigateToShopURL(false);
    }

    public static void navigateToShopURL(boolean isNewUser) {
        navigateToShopURL(isNewUser, "200");
    }

    public static void navigateToShopURL(boolean isNewUser, String expectedCode) {
        response = get(CHOTOT_SHOP_URL + getShopURL());
        verifyStatusCode(response, expectedCode);
    }

    public static void checkNewAdOnC2CShopDashboard(String adID) {
        response = get(ownerToken, String.format(gatewayShopC2C_CheckShop_ByURL, shop_url));
        debugResponse(response);
        verifyStatusCode(response, "200");
        String ad_id = getResponseData(response, "$.shopAds.ads[0].ad_id");
        Assert.assertEquals(adID, ad_id, "Check New ad be created and displayed on Shop Dashboard. Ad ID: " + adID);
    }

    public static void checkNewAdNotOnC2CShopDashboard(String adID) {
        response = get(ownerToken, String.format(gatewayShopC2C_CheckShop_ByURL, shop_url));
        debugResponse(response);
        verifyStatusCode(response, "200");
        String ad_id = getResponseData(response, "$.shopAds.ads[0].ad_id");
        Assert.assertNotEquals(adID, ad_id, "Check New ad be created and displayed on Shop Dashboard. Ad ID: " + adID);
    }

    public static String getC2CShop_InQueues_NewCount() {
        response = get(gatewayShopC2C_Queue_GetShopCount);
        String query = "$.new";
        verifyStatusCode(response, "200");
        return getResponseData(response, query);
    }

    public static String getC2CShop_InQueues_ContactCount() {
        response = get(gatewayShopC2C_Queue_GetShopCount);
        String query = "$.contact";
        verifyStatusCode(response, "200");
        return getResponseData(response, query);
    }

    public static String getC2CShop_InQueues_EditCount() {
        response = get(gatewayShopC2C_Queue_GetShopCount);
        String query = "$.edit";
        verifyStatusCode(response, "200");
        return getResponseData(response, query);
    }

    public static void checkAllowEditURL(boolean status) {
        response = get(ownerToken, String.format(gatewayShopC2C_Alias_Shop, shop_alias));
        verifyStatusCode(response, "200");
        boolean allowColumnsEdit = Boolean.parseBoolean(getResponseData(response, String.format("$.urls[?(@.url == '%s')].allowColumnsEdit", shop_url)));
        Assert.assertEquals(status, allowColumnsEdit, "Check AllowEditURL is " + status + " Failed");
    }

    public static void checkAllowEditName(boolean status) {
        response = get(ownerToken, String.format(gatewayShopC2C_Alias_Shop, shop_alias));
        response = get(ownerToken, String.format(gatewayShopC2C_Alias_Shop, shop_alias));
        verifyStatusCode(response, "200");
        boolean allowColumnsEdit = Boolean.parseBoolean(getResponseData(response, "$.allowColumnsEdit"));
        Assert.assertEquals(status, allowColumnsEdit, "Check AllowEditName is " + status + " Failed");
    }

    public static void checkShopStatus(String status) {
        response = get(ownerToken, gatewayShopC2C);
        debugResponse(response);
        verifyStatusCode(response, "200");
        String statusActual = getResponseData(response, "$[0].status");
        Assert.assertEquals(status.toLowerCase().trim(), statusActual, String.format("Compare Status of Shop: Expect[%s] but Actual[%s]", status, statusActual));
    }

    public static void checkShopIsBlocked() {
        response = get(ownerToken, gatewayShopC2C);
        debugResponse(response);
        verifyStatusCode(response, "200");
        String statusActual = getResponseData(response, "$[0].status");
        Assert.assertNull(statusActual);
    }

    public static void checkShopIsUnblocked() {
        response = get(ownerToken, gatewayShopC2C);
        debugResponse(response);
        verifyStatusCode(response, "200");
        String statusActual = getResponseData(response, "$[0].status");
        Assert.assertNotNull(statusActual);
    }

    //Using for C2C Shop
    private static int getCateIDByName(String categoryName) {
        switch (categoryName.trim().toUpperCase()) {
            case "FOOD":
                return CATE_FOOD_ID;
        }
        return -1;
    }

    public static void checkExtendedShopConfirmEmail(String email) {
        getGmailMessageList(email);
    }




    public static void approveNewShopByAliasUpdateInfo(String shopName, String shopAddress, String shopDesc, String shopUrl) {
        bodyJson = new JsonObject();

        //Add profile info
        bodyJson.addProperty("name", shopName);
        bodyJson.addProperty("address", shopAddress);
        bodyJson.addProperty("description", shopDesc);
        bodyJson.addProperty("adminId", 60);

        shop_remark = "Chotot CP approves new Shop Update approved";
        bodyJson.addProperty("remark", shop_remark);

        if (!shopUrl.equalsIgnoreCase(getShopURL())) {
            JsonObject urlElementJson = new JsonObject();
            urlElementJson.addProperty("id", getShopUrlId());

            setShopURL(shopUrl);
            urlElementJson.addProperty("url", getShopURL());
            urlElementJson.addProperty("allowColumnsEdit", true);

            JsonArray urlArrayJson = new JsonArray();
            urlArrayJson.add(urlElementJson);

            bodyJson.add("urls", urlArrayJson);
        }
        response = put(cpToken, bodyJson, String.format(gatewayShopC2C_Approve_Shop_ByAlias, shop_alias));
        verifyStatusCode(response, "200");

        updateShopStatusAccepted();
    }
}
