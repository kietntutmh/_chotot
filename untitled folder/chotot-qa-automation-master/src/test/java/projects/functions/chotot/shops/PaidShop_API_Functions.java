package projects.functions.chotot.shops;

import org.testng.Assert;
import projects.functions.APISupportFunctions;

import java.util.ArrayList;
import java.util.List;

import static api.configuration.DataConfig.newUserPhone;
import static api.configuration.DataConfig.tempAdID;
import static api.configuration.GatewayConfig.*;
import static api.utils.GetAccessToken.getAccessTokenOfNewUser;
import static com.vn.chotot.configuration.Constant.DOMAIN;
import static projects.configuaration.CategoryConfig.*;
import static projects.functions.chotot.CommonFunctions.getSubCategoryIDs;
import static projects.functions.chotot.payment.TopupDongTot_API_Functions.topupDongTotWithMomo_2000k;

public class PaidShop_API_Functions extends APISupportFunctions {
    private static final String IMAGE_PATH = System.getProperty("user.dir") + "/images/shops/";
    private static final String AVATAR_PATH = IMAGE_PATH + "avatar.jpg";
    private static final String COVER_PATH = IMAGE_PATH + "cover.jpg";

    public static String global_shopName = "";
    public static String global_shopDesc = "Chuyên trang Automation Test Description " + System.currentTimeMillis() / 1000;
    public static String global_shopURLPath = "";
    public static String global_shopURL = "https://nha.chotot." + DOMAIN + "/chuyen-trang/";
    public static int global_shopDistrict = 407;    //Quan 1
    public static int global_shopRegion = 13000;    //HCM
    public static String global_shopAddress = "2 Ngô Đức Kế, P. Bến Nghé, Q1";
    public static String global_shopAlias = "";
    public static String global_shopStatus = "";

    private static int amountDTAfterPaidShop = -1;
    private static int amountDTToPaidShop = -1;
    private static int amountDT4BAfterPaidShop = -1;
    private static int amountDT4BToPaidShop = -1;

    private static String shopUserAccountID = "";
    private static String shopUserToken = "";
    private static String shopUserPhone = "";
    private static final int retryGetShopAlias = 5;
    private static final int retryPayShop = 3;
    private static final int retryCheckShopStatus = 5;
    private static final int retryCheckCart = 3;
    private static final int retryCheckShopDashboard = 5;
    private static final int retryCheckUserAds = 5;
    private static final int retryPublishAdToChotot = 5;
    private static final int retryUploadImage = 5;
    private static final int retryHideAd = 10;
    private static final int retryRemoveAd = 10;


    private static int amountDTToExtendShop = -1;           //Fee to extend shops
    private static int amountDTAfterExtendShop = -1;        //Rest Fee after extended a shops
    private static int amountDT4BToExtendShop = -1;
    private static int amountDT4BAfterExtendShop = -1;


    //CHECK USER PROFILER


    //Rest Fee after paid a shops
    public static void setAmountAfterPaidShopDT(int amount) {
        amountDTAfterPaidShop = amount;
    }

    public static int getAmountAfterPaidShopDT() {
        return amountDTAfterPaidShop;
    }

    //Fee to create shops
    public static void setAmountToPaidShopDT(int amount) {
        amountDTToPaidShop = amount;
    }

    public static int getAmountToPaidShopDT() {
        return amountDTToPaidShop;
    }

    public static int getAmountAfterPaidShopDT4B() {
        return amountDT4BAfterPaidShop;
    }

    public static void setAmountToPaidShopDT4B(int amount) {
        amountDT4BToPaidShop = amount;
    }

    public static int getAmountToPaidShopDT4B() {
        return amountDT4BToPaidShop;
    }

    public static int getAmountDTToExtendShop() {
        return amountDTToExtendShop;
    }

    public static void setAmountDTToExtendShop(int amountDTToExtendShop) {
        PaidShop_API_Functions.amountDTToExtendShop = amountDTToExtendShop;
    }

    public static int getAmountDTAfterExtendShop() {
        return amountDTAfterExtendShop;
    }

    public static void setAmountDTAfterExtendShop(int amountDTAfterExtendShop) {
        PaidShop_API_Functions.amountDTAfterExtendShop = amountDTAfterExtendShop;
    }

    public static int getAmountDT4BToExtendShop() {
        return amountDT4BToExtendShop;
    }

    public static void setAmountDT4BToExtendShop(int amountDT4BToExtendShop) {
        PaidShop_API_Functions.amountDT4BToExtendShop = amountDT4BToExtendShop;
    }

    public static int getAmountDT4BAfterExtendShop() {
        return amountDT4BAfterExtendShop;
    }

    public static void setAmountDT4BAfterExtendShop(int amountDT4BAfterExtendShop) {
        PaidShop_API_Functions.amountDT4BAfterExtendShop = amountDT4BAfterExtendShop;
    }

    public static String getShopAlias() {
        return global_shopAlias;
    }

    public static void setShopAlias(String global_shopAlias) {
        PaidShop_API_Functions.global_shopAlias = global_shopAlias;
    }

    public static String getShopUserAccountID() {
        return shopUserAccountID;
    }

    public static void setShopUserAccountID(String accountID) {
        shopUserAccountID = accountID;
    }

    public static String getShopUserToken() {
        return shopUserToken;
    }

    public static String getShopUserPhone() {
        return shopUserPhone;
    }

    //Register new user & get token
    public static String getAccessTokenNewUserShopPTY() {
        shopUserToken = getAccessTokenOfNewUser();
        shopUserAccountID = global_accountID;
        shopUserPhone = newUserPhone;
        return getAccessTokenNewUserShopPTY(shopUserToken);
    }

    public static String getAccessTokenNewUserShopPTY(String token) {
        topupDongTotWithMomo_2000k(shopUserPhone);
        createShopPTY(token, shopUserPhone);
        return token;
    }

    public static String getAccessTokenNewUserShopVEH() {
        shopUserToken = getAccessTokenOfNewUser();
        shopUserAccountID = global_accountID;
        shopUserPhone = newUserPhone;
        return getAccessTokenNewUserShopVEH(shopUserToken);
    }

    public static String getAccessTokenNewUserShopVEH(String token) {
        topupDongTotWithMomo_2000k(shopUserPhone);
        createShopVEH(token, shopUserPhone);
        return token;
    }

    public static String getAccessTokenNewUserShopELT() {
        shopUserToken = getAccessTokenOfNewUser();
        shopUserAccountID = global_accountID;
        shopUserPhone = newUserPhone;
        return getAccessTokenNewUserShopELT(shopUserToken);
    }

    public static String getAccessTokenNewUserShopELT(String token) {
        topupDongTotWithMomo_2000k(shopUserPhone);
        createShopELT(token, shopUserPhone);
        return token;
    }

    public static void setShopUserPhone(String shopUserPhone) {
        PaidShop_API_Functions.shopUserPhone = shopUserPhone;
    }

    public static void setShopUserToken(String token) {
        PaidShop_API_Functions.shopUserToken = token;
    }

    //-------------------- SHOP CREATE --------------------
    public static void createShopPTY(String token, String phoneNumber, String duration, boolean isUsingDT4B) {
        createShopCORE(token, phoneNumber, CATEID_PTY, duration, isUsingDT4B);
    }

    public static void createShopPTY(String token, String phoneNumber) {
        createShopCORE(token, phoneNumber, CATEID_PTY, "1", false);
    }

    public static void createShopPTY_DT4B(String token, String phoneNumber) {
        createShopCORE(token, phoneNumber, CATEID_PTY, "1", true);
    }

    public static void createShopVEH(String token, String phoneNumber, String duration, boolean isUsingDT4B) {
        createShopCORE(token, phoneNumber, CATEID_VEH, duration, isUsingDT4B);
    }

    public static void createShopVEH(String token, String phoneNumber) {
        createShopCORE(token, phoneNumber, CATEID_VEH, "1", false);
    }

    public static void createShopVEH_DT4B(String token, String phoneNumber) {
        createShopCORE(token, phoneNumber, CATEID_VEH, "1", true);
    }

    public static void createShopELT(String token, String phoneNumber, String duration, boolean isUsingDT4B) {
        createShopCORE(token, phoneNumber, CATEID_ELT, duration, isUsingDT4B);
    }

    public static void createShopELT(String token, String phoneNumber) {
        createShopCORE(token, phoneNumber, CATEID_ELT, "1", false);
    }

    public static void createShopELT_DT4B(String token, String phoneNumber) {
        createShopCORE(token, phoneNumber, CATEID_ELT, "1", true);
    }

    private static void createShopCORE(String token, String phoneNumber, String cateID, String duration, boolean isUsingDT4B) {
        switch (cateID) {
            case CATEID_PTY:
                if (global_shopName.isEmpty())
                    global_shopName = "Chuyên trang Bất Động Sản Automation Test ";
                if (global_shopURLPath.isEmpty())
                    global_shopURLPath = "pty";
                break;
            case CATEID_VEH:
                if (global_shopName.isEmpty())
                    global_shopName = "Cữa hàng Xe Automation Test ";
                if (global_shopURLPath.isEmpty())
                    global_shopURLPath = "veh";
                break;
            case CATEID_ELT:
                if (global_shopName.isEmpty())
                    global_shopName = "Cửa hàng Điện Tử Test Automation Test ";
                if (global_shopURLPath.isEmpty())
                    global_shopURLPath = "elt";
                break;
        }
        global_shopName += phoneNumber;
        global_shopURLPath += "-" + phoneNumber;
        global_shopURL += global_shopURLPath;

        //1. Upload avavtar
        String avatarURL = createShopAvatar(token);
        Assert.assertNotNull(avatarURL, "FAILED to create Avatar URL of Shop");

        //2. Upload cover
        String coverURL = createShopCover(token);
        Assert.assertNotNull(coverURL, "FAILED to create Cover Image URL of Shop");

        //3. Create shop preview
        createShopReview(token, phoneNumber, cateID);

        //4. Update shop preview
        if (cateID.equalsIgnoreCase(CATEID_VEH)) {
            updateShopReview(token, coverURL, avatarURL, true);
        } else {
            updateShopReview(token, coverURL, avatarURL, false);
        }

        //4. Pay Shop Order
        //Pay Shop
        if (isUsingDT4B) {
            payOrderShop_DT4B(token, cateID, duration);
        } else {
            payOrderShop_DongTot(token, cateID, duration);
        }
        //Set Shop Alias
        getShopAliasAfterPaid();

        //5. Check Shop is active after paid
//        checkShopIsActivated(token);

        //6. Check Shop is pending reviewed by Chotot after paid
        checkShopIsFirstPendingPreview(token);

        //7. Chotot accepts SHOP request
        cpAcceptPaidShop(getShopAlias());

        //8. Check Shop is Approved
        checkShopIsAccepted(token);

        //8. On UI, after pay SHOP, a message is sent to Consumer to update User Profiler from private to Shop
        updateUserProfilerToShop(phoneNumber, shopUserAccountID, cateID);
    }

    //Is used to check status after pay shop, cp accepts shop
    public static void createShop_CheckStatus(String token, String phoneNumber, String cateID, boolean isPaid, boolean isCPAccepted) {
        switch (cateID) {
            case CATEID_PTY:
                global_shopName = "Chuyên trang Bất Động Sản Automation Test ";
                if (global_shopURLPath.isEmpty())
                    global_shopURLPath = "pty";
                break;
            case CATEID_VEH:
                global_shopName = "Cửa hàng Xe Automation Test ";
                if (global_shopURLPath.isEmpty())
                    global_shopURLPath = "veh";
                break;
            case CATEID_ELT:
                global_shopName = "Cửa hàng Điện Tử Test Automation Test ";
                if (global_shopURLPath.isEmpty())
                    global_shopURLPath = "elt";
                break;
        }
        global_shopName += phoneNumber;
        global_shopURLPath += "-" + phoneNumber;
        global_shopURL += global_shopURLPath;

        //1. Upload avavtar
        String avatarURL = createShopAvatar(token);
        Assert.assertNotNull(avatarURL, "FAILED to create Avatar URL of Shop");

        //2. Upload cover
        String coverURL = createShopCover(token);
        Assert.assertNotNull(coverURL, "FAILED to create Cover Image URL of Shop");

        //3. Create shop preview
        createShopReview(token, phoneNumber, cateID);

        //4. Update shop preview
        if (cateID.equalsIgnoreCase(CATEID_VEH)) {
            updateShopReview(token, coverURL, avatarURL, true);
        } else {
            updateShopReview(token, coverURL, avatarURL, false);
        }

        //4. Pay Shop Order
        //Pay Shop
        if (isPaid) {
            payOrderShop_DongTot(token, cateID, "1");
            //Set Shop Alias
            getShopAliasAfterPaid();

            //5. Check Shop is active after paid
//        checkShopIsActivated(token);

            //6. Check Shop is pending reviewed by Chotot after paid
            checkShopIsFirstPendingPreview(token);

            if (isCPAccepted) {
                //7. Chotot accepts SHOP request
                cpAcceptPaidShop(getShopAlias());

                //8. Check Shop is Approved
                checkShopIsAccepted(token);
            }
        }
    }


    private static String createShopReview(String token, String phoneNumber, String categoryID) {
        initBodyJson();
        bodyJson.addProperty("categoryId", Integer.parseInt(categoryID));
        bodyJson.addProperty("phoneNumber", phoneNumber);
        response = post(token, bodyJson, gatewayShop_CreatePreview);
        verifyStatusCode200("API DIE: CREATE SHOP TO PREVIEW");
        debugResponse();
        Assert.assertEquals(getResponseData("$.status"), "init", "SHOP PREVIEW IS CREATED BUT WRONG STATUS");
        Assert.assertEquals(getResponseData("$.categoryId"), categoryID, "SHOP PREVIEW IS CREATED BUT WRONG CATEID");
        Assert.assertEquals(getResponseData("$.phoneNumber"), phoneNumber, "SHOP PREVIEW IS CREATED BUT WRONG PHONE NUMBER");
        return getResponseData("$.cssId");
    }

    private static String updateShopReview(String token, String coverURL, String avatarURL, boolean isVEHShop) {
        initBodyJson();
        bodyJson.addProperty("name", global_shopName);
        bodyJson.addProperty("url", global_shopURLPath);

        if (!isVEHShop) {     //VEH SHOP doesn't allow for workingAreas
            initBodyJsonForArray();
            initBodyArray();
            bodyJsonForArray.addProperty("regionId", global_shopRegion);
            bodyJsonForArray.addProperty("areaId", global_shopDistrict);
            bodyJsonForArray.addProperty("areaName", "Quận 1");
            bodyJsonArray.add(bodyJsonForArray);
            bodyJson.add("workingAreas", bodyJsonArray);
        } else {
            // workingAreas is automatically generated from the location by BE
        }

        bodyJson.addProperty("address", global_shopAddress);
        bodyJson.addProperty("description", global_shopDesc);

        initBodyJsonForArray();
        initBodyArray();
        bodyJsonForArray.addProperty("url", global_shopURLPath);
        bodyJsonForArray.addProperty("allowColumnsEdit", true);
        bodyJsonArray.add(bodyJsonForArray);
        bodyJson.add("urls", bodyJsonArray);

        bodyJson.addProperty("coverImageUrl", coverURL);
        bodyJson.addProperty("profileImageUrl", avatarURL);

        response = put(token, bodyJson, gatewayShop_CreatePreview);
        verifyStatusCode200("API DIE: UPDATE SHOP TO PREVIEW");
        debugResponse();

        return getResponseData("$.name");
    }

    private static String createShopAvatar(String token) {
        for (int i = 0; i < retryUploadImage; i++) {
            try {
                response = postImage(token, gatewayShop_CreateAvatar, AVATAR_PATH);
                verifyStatusCode200("API DIE: POST SHOP AVATAR");
                return getResponseData(response, "$.profileImageUrl");
            } catch (AssertionError error) {
                waitConstant(1);
            }
        }
        return null;
    }

    private static String createShopCover(String token) {
        for (int i = 0; i < retryUploadImage; i++) {
            try {
                response = postImage(token, gatewayShop_CreateCover, COVER_PATH);
                verifyStatusCode200("API DIE: POST SHOP COVER");
                return getResponseData(response, "$.coverImageUrl");
            } catch (AssertionError error) {
                waitConstant(1);
            }
        }
        return null;
    }


    private static void checkShopIsAccepted(String token) {
        int retry = 0;
        while (retry < retryCheckShopStatus) {
            try {
                response = get(token, gatewayShop_Status);
                verifyStatusCode200("API CHECK SHOP STATUS IS DEAD");
                Assert.assertEquals(getResponseData("$[0].status"), "accepted", "SHOP ISN'T ACTIVE");
                Assert.assertEquals(getResponseData("$[0].isVerified"), "true", "SHOP ISN'T VERIFIED");
                break;
            } catch (AssertionError assertionError) {
                waitConstant(4);
                retry++;
            }
        }
        debugResponse();
        Assert.assertEquals(getResponseData("$[0].status"), "accepted", "SHOP ISN'T ACTIVE");
        Assert.assertEquals(getResponseData("$[0].isVerified"), "true", "SHOP ISN'T VERIFIED");
    }

    private static void checkShopIsFirstPendingPreview(String token) {
        int retry = 0;
        while (retry < retryCheckShopStatus) {
            try {
                response = get(token, gatewayShop_Status);
                verifyStatusCode200("API CHECK SHOP STATUS IS DEAD");
                Assert.assertEquals(getResponseData("$[0].status"), "first_pending_review", "SHOP ISN'T ACTIVE");
                break;
            } catch (AssertionError assertionError) {
                waitConstant(3);
                retry++;
            }
        }
        debugResponse();
        Assert.assertEquals(getResponseData("$[0].isVerified"), "false", "SHOP ISN'T VERIFIED");
    }

    public static void cpAcceptPaidShop(String shopAlias) {
        initBody();
        bodyJson.addProperty("isVerified", true);
        bodyJson.addProperty("adminId", 60);
        bodyJson.addProperty("remark", "Automation approves Shop");
        response = put(bodyJson, String.format(getGatewayShop_ApproveShop_ByAlias, shopAlias));
        verifyStatusCode200("API: CP accepts PAID SHOP is dead");
    }

    /**
     * @param categoryID (not this version)        if cateID, then upadte all of subcateID. Else if subcateID, then update only the SubcateID
     * @author VU HOANG
     */
    public static void updateUserProfilerToShop(String phoneNumber, String accountID, String categoryID) {
        boolean validation = false; //First Check API update profiler works right

        initBody();
        bodyJson.addProperty("phone", phoneNumber);
        bodyJson.addProperty("user_type", "shop");
        bodyJson.addProperty("reason", "set_by_reviewer");
        bodyJson.addProperty("ad_type", "s");

        initBodyChild();
        bodyChildJson.addProperty("reviewer_id", 2);
        bodyChildJson.addProperty("reviewer_name", "VU HOANG");
        bodyJson.add("params", bodyChildJson);

        //Get all subcateIDs of categoryID to update/set profiler to shop
        List<String> subCateIDs = getSubCategoryIDs(categoryID);
        for (String subCateID : subCateIDs) {
            //Update profiler to "SHOP" for all subCateIDs of cateID
            bodyJson.addProperty("cat", subCateID);

            response = put(bodyJson, String.format(gatewayUserProfiler_UpdateProfiler, accountID));
            verifyStatusCode200("API set/update user profiler to Shop is dead");

            //Check first Request, make sure API works right
            if (!validation) {
                Assert.assertEquals(getResponseData("$.phone"), phoneNumber, "PHONE is incorrect");
                Assert.assertEquals(getResponseData("$.category"), subCateID, "CATEID is incorrect");
                Assert.assertEquals(getResponseData("$.user_type"), "shop", "USER TYPE is incorrect");
                Assert.assertEquals(getResponseData("$.ad_type"), "s", "AD TYPE isn't s (Sell ad)");
                validation = true;
            }
        }
    }

    public static void updateUserProfilerToShop_BySubCate(String phoneNumber, String accountID, String subCateID) {
        initBody();
        bodyJson.addProperty("phone", phoneNumber);
        bodyJson.addProperty("user_type", "shop");
        bodyJson.addProperty("reason", "set_by_reviewer");
        bodyJson.addProperty("ad_type", "s");

        initBodyChild();
        bodyChildJson.addProperty("reviewer_id", 2);
        bodyChildJson.addProperty("reviewer_name", "VU HOANG");
        bodyJson.add("params", bodyChildJson);

        //Get all subcateIDs of categoryID to update/set profiler to shop
        //Update profiler to "SHOP" for all subCateIDs of cateID
        bodyJson.addProperty("cat", subCateID);

        response = put(bodyJson, String.format(gatewayUserProfiler_UpdateProfiler, accountID));
        verifyStatusCode200("API set/update user profiler to Shop is dead");

        //Check first Request, make sure API works right
        Assert.assertEquals(getResponseData("$.phone"), phoneNumber, "PHONE is incorrect");
        Assert.assertEquals(getResponseData("$.category"), subCateID, "CATEID is incorrect");
        Assert.assertEquals(getResponseData("$.user_type"), "shop", "USER TYPE is incorrect");
        Assert.assertEquals(getResponseData("$.ad_type"), "s", "AD TYPE isn't s (Sell ad)");
    }

    //----------------- CREATE SHOP -----------------
    //Create Shop DONG TOT
    public static void payOrderShop_PTY_DongTot(String token, String duration) {
        payOrderShop_DongTot(token, CATEID_PTY, duration);
    }

    public static void payOrderShop_VEH_DongTot(String token, String duration) {
        payOrderShop_DongTot(token, CATEID_VEH, duration);
    }

    public static void payOrderShop_ELT_DongTot(String token, String duration) {
        payOrderShop_DongTot(token, CATEID_ELT, duration);
    }

    private static void payOrderShop_DongTot(String token, String categoryId, String duration) {
        initBodyJson();
        bodyJson.addProperty("unit", "credit");
        bodyJson.addProperty("source", "desktop_web");
        bodyJson.addProperty("order_type", "shop_create");
        bodyJson.addProperty("title", "Thanh toán dịch vụ");
        bodyJson.addProperty("ip", "192.168.1.197");
        bodyJson.addProperty("gateway", "credit");
        bodyJson.addProperty("platform", "global");
        bodyJson.addProperty("category_id", categoryId);
        bodyJson.addProperty("duration", duration);

        int retry = 0;
        while (retry < retryPayShop) {
            try {
                response = post(token, bodyJson, gatewayPayOrderWithDongTot);
                verifyStatusCode200("API CREATE SHOP DIE");
                break;
            } catch (AssertionError assertionError) {
                waitConstant(3);
                retry++;
            }
        }
        debugResponse();

        //get balance to pay shops
        Assert.assertEquals(getResponseData(response, "$.status"), "ok", "CREATE SHOP ISN'T SUCCESSFUL");
        setAmountToPaidShopDT(getResponseDataInt(response, "$.amount"));

        //get balance after paid shops
        response = get(token, gatewayCheckDongTot);
        verifyStatusCode200("API GET BALANCE DIE");
        setAmountAfterPaidShopDT(getResponseDataInt(response, "$[0].balance"));
    }

    //Create Shop DONG TOT 4B
    public static void payOrderShop_PTY_DT4B(String token, String duration) {
        payOrderShop_DT4B(token, CATEID_PTY, duration);
    }

    public static void payOrderShop_VEH_DT4B(String token, String duration) {
        payOrderShop_DT4B(token, CATEID_VEH, duration);
    }

    public static void payOrderShop_ELT_DT4B(String token, String duration) {
        payOrderShop_DT4B(token, CATEID_ELT, duration);
    }

    private static void payOrderShop_DT4B(String token, String categoryId, String duration) {
        initBodyJson();
        bodyJson.addProperty("unit", "credit");
        bodyJson.addProperty("source", "desktop_web");
        bodyJson.addProperty("order_type", "shop_create");
        bodyJson.addProperty("title", "Thanh toán dịch vụ");
        bodyJson.addProperty("ip", "192.168.1.197");
        bodyJson.addProperty("gateway", "credit_biz");
        bodyJson.addProperty("platform", "global");
        bodyJson.addProperty("category_id", categoryId);
        bodyJson.addProperty("duration", duration);

        response = post(token, bodyJson, gatewayPayOrderWithDongTot);
        debugResponse();
        verifyStatusCode200("API CREATE SHOP DIE");

        //get balance to pay shops
        Assert.assertEquals(getResponseData(response, "$.status"), "ok", "CREATE SHOP ISN'T SUCCESSFUL");
        setAmountToPaidShopDT(getResponseDataInt(response, "$.amount"));

        //get balance after paid shops
        response = get(token, gatewayCheckDongTot);
        verifyStatusCode200("API GET BALANCE DIE");
        setAmountAfterPaidShopDT(getResponseDataInt(response, "$[0].balance"));
    }


    //----------------- EXTEND SHOP -----------------

    public static void extendShopPTY(String token, String shopAlias, int duration, boolean isUsingDT4B){
        extendPaidShop(token, shopAlias, duration, CATEID_PTY, isUsingDT4B);
    }

    public static void extendShopVEH(String token, String shopAlias, int duration, boolean isUsingDT4B){
        extendPaidShop(token, shopAlias, duration, CATEID_VEH, isUsingDT4B);
    }

    public static void extendShopELT(String token, String shopAlias, int duration, boolean isUsingDT4B){
        extendPaidShop(token, shopAlias, duration, CATEID_ELT, isUsingDT4B);
    }

    /**
     * @param categoryID to define the shops extend package ID to buy
     * @Author: Vu Hoang
     * @Description: CORE of EXTENDING SHOP PTY, ELT, VEH
     * Review: April 16, 2021
     */
    private static void extendPaidShop(String token, String shopAlias, int duration, String categoryID, boolean isUsingDT4B) {
        //1. Buy Package: Shop Extend
        switch (categoryID.toLowerCase()) {
            case CATEID_PTY:
                buyShopExtendPackagePTY(token, shopAlias, duration);
                break;
            case CATEID_ELT:
                buyShopExtendPackageELT(token, shopAlias, duration);
                break;
            case CATEID_VEH:
                buyShopExtendPackageVEH(token, shopAlias, duration);
                break;
            default:
                Assert.assertTrue(false, "Package Category ID doesn't exist");
                break;
        }

        //2. Pay Extend Package: Shop PTY Extend
        initBodyJson();
        bodyJson.addProperty("unit", "credit");
        bodyJson.addProperty("source", "desktop_web");
        bodyJson.addProperty("order_type", "cart");
        bodyJson.addProperty("title", "Thanh toán dịch vụ");
        bodyJson.addProperty("ip", "192.168.1.197");
        bodyJson.addProperty("duration", duration);

        if (isUsingDT4B) {
            bodyJson.addProperty("gateway", "credit_biz");
        } else {
            bodyJson.addProperty("gateway", "credit");
        }

        //FOR ELT SHOP
        if (categoryID.equalsIgnoreCase(CATEID_ELT)) {
            bodyJson.addProperty("cart_id", "shoppackage");
        }

        response = post(token, bodyJson, gatewayPayOrderWithDongTot);
        debugResponse();
        verifyStatusCode200("API DEAD: PAY FOR SHOP EXTEND");

        //3. get balance to pay shops
        Assert.assertEquals(getResponseData(response, "$.status"), "ok", "CREATE SHOP ISN'T SUCCESSFUL");
        setAmountDTToExtendShop(getResponseDataInt(response, "$.amount"));

        //4. get balance after paid shops
        if (!isUsingDT4B) {
            try{
                response = get(token, gatewayCheckDongTot);
                verifyStatusCode200("API GET BALANCE IS DEAD");
                //DT4B equals total_amount_parent - amountDTToExtend
                setAmountDTAfterExtendShop(getResponseDataInt(response, "$[0].balance"));
            }catch (AssertionError error){
                setAmountDTAfterExtendShop(-1);
            }
        }
    }

    private static void buyShopExtendPackagePTY(String token, String shopAlias, int duration) {
        String body = "{\n" +
                "    \"target\": \"" + shopAlias + "\",\n" +
                "    \"type\": \"shop_extend\",\n" +
                "    \"params\": {\n" +
                "        \"shop_alias\": \"" + shopAlias + "\",\n" +
                "        \"duration\": " + duration + "\n" +
                "    }\n" +
                "}";

        response = post(token, body, gatewayShop_Extend_PTY_BuyPackage);
        debugResponse();
        verifyStatusCode200("API ADD PACKAGE EXTEND SHOP PTY IS DEAD");
    }

    private static void buyShopExtendPackageVEH(String token, String shopAlias, int duration) {
        String body = "{\n" +
                "    \"target\": \"" + shopAlias + "\",\n" +
                "    \"type\": \"shop_extend\",\n" +
                "    \"params\": {\n" +
                "        \"shop_alias\": \"" + shopAlias + "\",\n" +
                "        \"duration\": " + duration + "\n" +
                "    }\n" +
                "}";

        response = post(token, body, gatewayShop_Extend_VEH_BuyPackage);
        debugResponse();
        verifyStatusCode200("API ADD PACKAGE EXTEND SHOP VEH IS DEAD");
    }

    private static void buyShopExtendPackageELT(String token, String shopAlias, int duration) {
        String body = "{\n" +
                "    \"target\": \"" + shopAlias + "\",\n" +
                "    \"type\": \"shop_extend\",\n" +
                "    \"params\": {\n" +
                "        \"shop_alias\": \"" + shopAlias + "\",\n" +
                "        \"duration\": " + duration + ",\n" +
                "        \"packages\": [\n" +
                "            {\n" +
                "                \"params\": 240,\n" +
                "                \"price\": 50000,\n" +
                "                \"priceUnit\": {\n" +
                "                    \"vnd\": 50000,\n" +
                "                    \"credit\": 50000\n" +
                "                },\n" +
                "                \"type\": \"topup\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";

        response = post(token, body, gatewayShop_Extend_ELT_BuyPackage);
        debugResponse();
        verifyStatusCode200("API ADD PACKAGE EXTEND SHOP ELT IS DEAD");
    }
//----------------------- edit account hierarchy : remove het methods extend shop ----------------------------------

    //----------------- AD: SHOP TO CHOTOT -----------------
    //
    public static void shopToChotot() {
        shopToChotot(global_accessToken, tempAdID);
    }

    public static void shopToChotot(String token) {
        shopToChotot(token, tempAdID);
    }

    public static void shopToChotot(String token, String adID) {
        initBodyJson();
        bodyJson.addProperty("ad_id", Integer.parseInt(adID));
        bodyJson.addProperty("new_state", "accepted");
        bodyJson.addProperty("shop_alias", getShopAlias());
        bodyJson.addProperty("needs_update_list_time", 0);
        bodyJson.addProperty("remote_addr", "192.168.1.1");
        response = post(token, bodyJson, gatewayShop_AdShopToChotot);
        verifyStatusCode200("API PUBLISH AD FROM SHOP TO CHOTOT DIE");
        STOP();
    }

    public static void getShopAliasAfterPaid() {
        getShopAliasAfterPaid(global_accountID);
    }

    public static void getShopAliasAfterPaid(String accountID) {
        String alias = null;
        int i = 0;
        while (i < retryGetShopAlias) {
            response = get(String.format(gatewayShop_GetAlias, accountID));
            verifyStatusCode200("API GET SHOP ALIAS OF ACC[" + accountID + "] DIE");
            try {
                alias = getResponseData(response, "$[0].alias");
                Assert.assertNotNull(alias);
                break;
            } catch (AssertionError assertionError) {
                waitConstant(3);
                i++;
            }
        }
        debugResponse();
        Assert.assertNotNull(alias, "CAN'T GET SHOPALIAS");
        setShopAlias(alias);
    }


    public static void checkCartEmpty(String token) {
        int loop = 0;
        List<Object> serviceInCart = null;
        while (loop < retryCheckShopStatus) {
            try {
                response = get(token, gatewayCart_GetInfo);
                verifyStatusCode200("API GET CART INFO FAILED");
                serviceInCart = getResponseDataListObj(response, "$.services");
                Assert.assertTrue(serviceInCart.size() == 0, "CART IS NOT EMPTY");
                break;
            } catch (AssertionError a) {
                waitConstant(3);
                loop++;
            }
        }
        debugResponse();
        Assert.assertTrue(serviceInCart.size() == 0, "CART IS NOT EMPTY");
    }

    @Deprecated
    public static void checkCartNotEmpty(String token) {
        int loop = 0;
        List<Object> serviceInCart = null;
        while (loop < retryCheckShopStatus) {
            try {
                response = get(token, gatewayCart_GetInfo);
                verifyStatusCode200("API GET CART INFO FAILED");
                serviceInCart = getResponseDataListObj(response, "$.services");
                Assert.assertTrue(serviceInCart.size() > 0, "CART IS EMPTY");
                break;
            } catch (AssertionError a) {
                waitConstant(3);
                loop++;
            }
        }
        debugResponse();
        Assert.assertTrue(serviceInCart.size() > 0, "CART IS EMPTY");
    }

    public static void checkCartOrder(String token, int expectOrderAmount) {
        int loop = 0;
        List<Object> serviceInCart = null;
        while (loop < retryCheckShopStatus) {
            try {
                response = get(token, gatewayCart_GetInfo);
                verifyStatusCode200("API GET CART INFO FAILED");
                serviceInCart = getResponseDataListObj(response, "$.services");
                Assert.assertEquals(serviceInCart.size(), expectOrderAmount, "ORDER IN CART IS NOT EQUAL AS " + expectOrderAmount);
                break;
            } catch (AssertionError a) {
                waitConstant(3);
                loop++;
            }
        }

        debugResponse();
        Assert.assertEquals(serviceInCart.size(), expectOrderAmount, "ORDER IN CART IS NOT EQUAL AS " + expectOrderAmount);

        ///v2/private/cart/info
        ///v2/private/cart/info?cart_id=shoppackage
    }

    public static void verifyAdOnShopDashboardAndListing(String ownerToken, String shopAlias, String adID) {
        //1. Check Ad is on Listing
        List<String> listIdOfAd = new ArrayList<>();
        for (int i = 0; i < retryCheckShopDashboard; i++) {
            try {
                response = get(ownerToken, String.format(gatewayShop_ShopDashboard, shopAlias));
                verifyStatusCode200("API GET SHOP DASHBOARD IS DEAD");
                listIdOfAd = getResponseDataList("$.default[*][?(@.info.ad_id == '" + adID + "')].info.list_id");
                Assert.assertTrue(listIdOfAd.size() > 0, "AD " + adID + " isn't displayed on TIN CHỢ TỐT and TIN CHUYÊN TRANG");
                break;
            } catch (AssertionError assertionError) {
                waitConstant(3);
            }
        }
        debugResponse();
        Assert.assertTrue(listIdOfAd.size() > 0, "AD " + adID + " isn't displayed on TIN CHỢ TỐT and TIN CHUYÊN TRANG");
    }

    public static void verifyAdOnShopDashboard(String ownerToken, String shopAlias, String adID) {
        //1. Check Ad is NOT on Listing
        List<String> listIdOfAd = new ArrayList<>();
        for (int i = 0; i < retryCheckShopDashboard; i++) {
            try {
                response = get(ownerToken, String.format(gatewayShop_ShopDashboard, shopAlias));
                verifyStatusCode200("API GET SHOP DASHBOARD IS DEAD");
                listIdOfAd = getResponseDataList("$.default[*][?(@.info.ad_id == '" + adID + "')].info.list_id");
                Assert.assertTrue(listIdOfAd.size() == 0, "AD " + adID + " isn't displayed on TIN CHUYÊN TRANG");       //On Dashboard but not on Listing
                break;
            } catch (AssertionError assertionError) {
                waitConstant(10);
            }
        }
        debugResponse();
        Assert.assertTrue(listIdOfAd.size() == 0, "AD " + adID + " isn't displayed on TIN CHUYÊN TRANG");
    }

    public static void verifyAdNotOnShopDashboard(String ownerToken, String shopAlias, String adID) {
        List<String> listIdOfAd = new ArrayList<>();
        for (int i = 0; i < retryCheckShopDashboard; i++) {
            try {
                response = get(ownerToken, String.format(gatewayShop_ShopDashboard, shopAlias));
                verifyStatusCode200("API GET SHOP DASHBOARD IS DEAD");
                listIdOfAd = getResponseDataList("$.default[*][?(@.info.ad_id == '" + adID + "')].info.list_id");
                Assert.assertTrue(listIdOfAd.size() > 0, "AD " + adID + " displayed on TIN CHUYÊN TRANG");       //On Dashboard but not on Listing
                break;
            } catch (AssertionError assertionError) {
                waitConstant(3);
            }
        }
        debugResponse();
        Assert.assertTrue(listIdOfAd.size() == 0, "AD " + adID + " displayed on TIN CHUYÊN TRANG");
    }

    public static void expirePaidShop(String ownerToken) {
        response = put(ownerToken, String.format(gatewayShop_Expire, global_shopAlias));
        verifyStatusCode200("API DEAD: EXPIRE PAID SHOP");
        debugResponse();
    }

    public static void moveAdToChototFree(String userToken, String shopAlias, String adIDToChotot) {
        initBody();
        bodyJson.addProperty("ad_id", adIDToChotot);
        bodyJson.addProperty("new_state", "accepted");
        bodyJson.addProperty("shop_alias", shopAlias);
        bodyJson.addProperty("needs_update_list_time", 0);
        bodyJson.addProperty("remote_addr", "192.168.1.1");

        for (int i = 0; i < retryPublishAdToChotot; i++) {
            try {
                response = post(userToken, bodyJson, gatewayShop_AdShopToChotot);
                verifyStatusCode200("API: move ad from Shop Dashboard to Chotot (Private) is dead");
                Assert.assertEquals(getResponseData("$.status"), "ok", "Move ad from Shop Dashboard to Chotot failed");
                break;
            } catch (AssertionError assertionError) {
                waitConstant(3);
            }
        }
        debugResponse();
        Assert.assertEquals(getResponseData("$.status"), "ok", "Move ad from Shop Dashboard to Chotot failed");
    }

    public static void moveAdToChototPaid(String userToken, String adIDToChotot) {
        //1. Add Cart
        initBody();
        bodyJson.addProperty("type", "shop_to_chotot");
        bodyJson.addProperty("target", adIDToChotot);

        int i = 0;
        while (i < retryPublishAdToChotot) {
            try {
                response = post(userToken, bodyJson, gatewayCart_AddService);

                verifyStatusCode200("API move SHOP Ad to Chotot is dead");
                Assert.assertNotEquals(getResponseData("$.code"), "pricer_error",
                        "Shop Ad to Chotot failed at Pricer: " + getResponseData("$.message"));
                break;
            } catch (AssertionError assertionError) {
                waitConstant(3);
            }
            i++;
        }
        debugResponse();
        Assert.assertNotEquals(getResponseData("$.code"), "pricer_error",
                "Shop Ad to Chotot failed at Pricer: " + getResponseData("$.message"));
    }

    public static void removeAdOutOfListing(String token, String shopAlias, String adID) {
//        response = deleteNoBody(token, String.format(gatewayShop_DeleteAd, adID));
//        debugResponse();

        String body =
                "{\n" +
                        "  \"ads\": [\n" +
                        "    {\n" +
                        "      \"ad_id\": " + adID + ",\n" +
                        "      \"new_state\": \"shop_accepted\",\n" +
                        "      \"needs_update_list_time\": 0\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"shop_alias\": \"" + shopAlias + "\",\n" +
                        "  \"remote_addr\": \"192.168.1.1\"\n" +
                        "}";
        for (int i = 0; i < retryRemoveAd; i++) {
            try {
                response = post(token, body, gatewayShop_AdChototToShop);
                verifyStatusCode200("API move Shop ad from Listing to Shop is dead");
                Assert.assertEquals(getResponseData("$.status"), "ok");
                break;
            } catch (AssertionError assertionError) {
                waitConstant(5);
            }
        }
        debugResponse();
        Assert.assertEquals(getResponseData("$.status"), "ok");
    }

    public static void hideAdOnShopDashboard(String token, String shopAlias, String adID) {
        initBody();
        bodyJson.addProperty("reason", "2");
        bodyJson.addProperty("status", "hidden");
        bodyJson.addProperty("shop_alias", shopAlias);

        for (int i = 0; i < retryHideAd; i++) {
            try {
                response = put(token, bodyJson, String.format(gatewayShop_HideAdOnDashboard, adID));
                verifyStatusCode200("API hide Shop ad on Shop Dashboard is dead");
                Assert.assertEquals(getResponseData("$.ok"), "true");
                Assert.assertEquals(getResponseData("$.message"), "Ẩn tin đăng thành công.");
                break;
            } catch (AssertionError assertionError) {
                waitConstant(10);
            }
        }
        debugResponse();
        Assert.assertEquals(getResponseData("$.ok"), "true");
        Assert.assertEquals(getResponseData("$.message"), "Ẩn tin đăng thành công.");
    }

    public static void unhideAd(String token, String shopAlias, String adID) {
        initBody();
        bodyJson.addProperty("status", "active");
        bodyJson.addProperty("shop_alias", shopAlias);

        for (int i = 0; i < retryHideAd; i++) {
            try {
                response = put(token, bodyJson, String.format(gatewayShop_UnHideAd, adID));
                verifyStatusCode200("API unhide Shop ad on Shop Dashboard is dead");
                Assert.assertEquals(getResponseData("$.ok"), "true");
                Assert.assertEquals(getResponseData("$.message"), "Hiện tin đăng thành công.");
                break;
            } catch (AssertionError assertionError) {
                waitConstant(10);
            }
        }
        debugResponse();
        Assert.assertEquals(getResponseData("$.ok"), "true");
        Assert.assertEquals(getResponseData("$.message"), "Hiện tin đăng thành công.");
    }

    //-------------------------- Shop Ad --------------------------
    private static String adStatus = "";
    private static String adPaymentStatus = "";
    private static String adProfiler = "";
    private static String queueName = "";
    private static String queueSuggestedName = "";

    public static void getShopAdInfo(String adID) {
        for (int i = 0; i < retryCheckUserAds; i++) {
            try {
                response = get(String.format(gatewayPublicUserAds, adID));
                verifyStatusCode200("Get Ad info from User-Ads API public : DEAD");
                Assert.assertFalse(getResponseDataBoolean("$.is_private_ad"), "Verify Ad is NOT Private Ad");
                Assert.assertTrue(getResponseDataBoolean("$.company_ad"), "Verify Ad is Company Ad");
                break;
            } catch (AssertionError ex) {
                waitConstant(3);
            }
        }
        Assert.assertTrue(getResponseDataBoolean("$.company_ad"), "Verify Ad is Company Ad");

        adStatus = getResponseData("$.status");
        adPaymentStatus = getResponseData("$.payment_status");
        adProfiler = getResponseData("$.profiler");
        queueName = getResponseData("$.queue");
        queueSuggestedName = getResponseData("$.suggested_adqueue");
    }

    public static void verifyShopAdStatus(String adID, String status) {
        if (adStatus.isEmpty())
            getShopAdInfo(adID);
        Assert.assertTrue(adStatus.equalsIgnoreCase(status),
                "Verify Ad Status is " + status);
    }

    public static void verifyShopAdPaymentStatus(String adID, String paymentStatus) {
        if (adPaymentStatus.isEmpty())
            getShopAdInfo(adID);
        Assert.assertTrue(adPaymentStatus.equalsIgnoreCase(paymentStatus),
                "Verify Ad Payment Status is " + paymentStatus);
    }

    public static void verifyShopAdInQueue(String adID, String queueNameIn) {
        if (queueName.isEmpty())
            getShopAdInfo(adID);
        Assert.assertTrue(queueName.equalsIgnoreCase(queueNameIn),
                "Verify Ad is in Queue " + queueNameIn);
    }

    public static void verifyShopAdInQueueSuggested(String adID, String queueNameIn) {   //To know where the ad is (Shop Dashboard or Listing)
        if (queueName.isEmpty())
            getShopAdInfo(adID);
        Assert.assertTrue(queueName.equalsIgnoreCase(queueNameIn),
                "Verify Ad is in Queue " + queueNameIn);
    }

}

















