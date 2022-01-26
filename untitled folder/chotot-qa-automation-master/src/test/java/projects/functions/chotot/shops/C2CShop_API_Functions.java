//package projects.functions.chotot.shops;
//
//import org.testng.Assert;
//
//import static api.configuration.DataConfig.newUserPhone;
//import static api.configuration.GatewayConfig.*;
//import static api.utils.GetAccessToken.getAccessTokenOfNewUser;
//import static projects.configuaration.CategoryConfig.*;
//import static projects.configuaration.CategoryConfig.CATEID_VEH;
//import static projects.functions.chotot.payment.TopupDongTot_API_Functions.topupDongTotWithMomo_2000k;
//
//public class C2CShop_API_Functions extends PaidShop_API_Functions {
//
//    private static final String IMAGE_PATH = System.getProperty("user.dir") + "/images/shops/";
//    private static final String AVATAR_PATH = IMAGE_PATH + "avatar.jpg";
//    private static final String COVER_PATH = IMAGE_PATH + "cover.jpg";
//    private static final int retryGetShopAlias = 5;
//    private static final int retryPayShop = 3;
//    private static final int retryCheckShopStatus = 5;
//    private static final int retryCheckCart = 3;
//    private static final int retryCheckShopDashboard = 5;
//    private static final int retryCheckUserAds = 5;
//    private static final int retryPublishAdToChotot = 5;
//    private static final int retryUploadImage = 5;
//    private static final int retryHideAd = 10;
//    private static final int retryRemoveAd = 10;
//    private static int amountDTAfterPaidShopC2C = -1;
//    private static int amountDTToPaidShopC2C = -1;
//    private static int amountDT4BAfterPaidShopC2C = -1;
//    private static int amountDT4BToPaidShopC2C = -1;
//    private static String shopC2CUserAccountID = "";
//    private static String shopC2CUserToken = "";
//    private static String shopC2CUserPhone = "";
//    private static int amountDTToExtendShopC2C = -1;           //Fee to extend shops
//    private static int amountDTAfterExtendShopC2C = -1;        //Rest Fee after extended a shops
//    private static int amountDT4BToExtendShoC2Cp = -1;
//    private static int amountDT4BAfterExtendShopC2C = -1;
//
//    public static String getShopC2CUserAccountID() {
//        return shopC2CUserAccountID;
//    }
//
//    public static void setShopC2CUserAccountID(String shopC2CUserAccountID) {
//        C2CShop_API_Functions.shopC2CUserAccountID = shopC2CUserAccountID;
//    }
//
//    public static String getShopC2CUserToken() {
//        return shopC2CUserToken;
//    }
//
//    public static void setShopC2CUserToken(String shopC2CUserToken) {
//        C2CShop_API_Functions.shopC2CUserToken = shopC2CUserToken;
//    }
//
//    public static String getShopC2CUserPhone() {
//        return shopC2CUserPhone;
//    }
//
//    public static void setShopC2CUserPhone(String shopC2CUserPhone) {
//        C2CShop_API_Functions.shopC2CUserPhone = shopC2CUserPhone;
//    }
//
//    public static int getAmountDTAfterPaidShopC2C() {
//        return amountDTAfterPaidShopC2C;
//    }
//
//    public static void setAmountDTAfterPaidShopC2C(int amountDTAfterPaidShopC2C) {
//        C2CShop_API_Functions.amountDTAfterPaidShopC2C = amountDTAfterPaidShopC2C;
//    }
//
//    public static int getAmountDTToPaidShopC2C() {
//        return amountDTToPaidShopC2C;
//    }
//
//    public static void setAmountDTToPaidShopC2C(int amountDTToPaidShopC2C) {
//        C2CShop_API_Functions.amountDTToPaidShopC2C = amountDTToPaidShopC2C;
//    }
//
//    public static int getAmountDT4BAfterPaidShopC2C() {
//        return amountDT4BAfterPaidShopC2C;
//    }
//
//    public static void setAmountDT4BAfterPaidShopC2C(int amountDT4BAfterPaidShopC2C) {
//        C2CShop_API_Functions.amountDT4BAfterPaidShopC2C = amountDT4BAfterPaidShopC2C;
//    }
//
//    public static int getAmountDT4BToPaidShopC2C() {
//        return amountDT4BToPaidShopC2C;
//    }
//
//    public static void setAmountDT4BToPaidShopC2C(int amountDT4BToPaidShopC2C) {
//        C2CShop_API_Functions.amountDT4BToPaidShopC2C = amountDT4BToPaidShopC2C;
//    }
//
//    public static int getAmountDTToExtendShopC2C() {
//        return amountDTToExtendShopC2C;
//    }
//
//    public static void setAmountDTToExtendShopC2C(int amountDTToExtendShopC2C) {
//        C2CShop_API_Functions.amountDTToExtendShopC2C = amountDTToExtendShopC2C;
//    }
//
//    public static int getAmountDTAfterExtendShopC2C() {
//        return amountDTAfterExtendShopC2C;
//    }
//
//    public static void setAmountDTAfterExtendShopC2C(int amountDTAfterExtendShopC2C) {
//        C2CShop_API_Functions.amountDTAfterExtendShopC2C = amountDTAfterExtendShopC2C;
//    }
//
//    public static int getAmountDT4BToExtendShoC2Cp() {
//        return amountDT4BToExtendShoC2Cp;
//    }
//
//    public static void setAmountDT4BToExtendShoC2Cp(int amountDT4BToExtendShoC2Cp) {
//        C2CShop_API_Functions.amountDT4BToExtendShoC2Cp = amountDT4BToExtendShoC2Cp;
//    }
//
//    public static int getAmountDT4BAfterExtendShopC2C() {
//        return amountDT4BAfterExtendShopC2C;
//    }
//
//    public static void setAmountDT4BAfterExtendShopC2C(int amountDT4BAfterExtendShopC2C) {
//        C2CShop_API_Functions.amountDT4BAfterExtendShopC2C = amountDT4BAfterExtendShopC2C;
//    }
//
//
//    //Register new user & get token
//    public static String getAccessTokenNewUserShopPET() {
//        shopC2CUserToken = getAccessTokenOfNewUser();
//        shopC2CUserAccountID = global_accountID;
//        shopC2CUserPhone = newUserPhone;
//        return getAccessTokenNewUserShopPET(shopC2CUserToken);
//    }
//
//    public static String getAccessTokenNewUserShopPET(String token) {
//        topupDongTotWithMomo_2000k(shopC2CUserPhone);
//        createShopPET(token, shopC2CUserPhone);
//        return token;
//    }
//
//    public static void createShopPET(String token, String phoneNumber) {
//        createShopCORE(token, phoneNumber, CATEID_PET, "1", false);
//    }
//
//    private static void createShopCORE(String token, String phoneNumber, String cateID, String duration, boolean isUsingDT4B) {
//        switch (cateID) {
//            case CATEID_PET:
//                if (global_shopName.isEmpty())
//                    global_shopName = "Cửa Hàng Thú Cưng Automation Test ";
//                if (global_shopURLPath.isEmpty())
//                    global_shopURLPath = "pet";
//                break;
//            case CATEID_FOOD:
//                if (global_shopName.isEmpty())
//                    global_shopName = "Cửa Hàng Ăn Uống Automation Test ";
//                if (global_shopURLPath.isEmpty())
//                    global_shopURLPath = "food";
//                break;
//            case CATEID_REFRIGERATION:
//                if (global_shopName.isEmpty())
//                    global_shopName = "Cửa Hàng Điện Lạnh Automation Test ";
//                if (global_shopURLPath.isEmpty())
//                    global_shopURLPath = "refrigeration";
//                break;
//            case CATEID_HOUSEHOLD:
//                if (global_shopName.isEmpty())
//                    global_shopName = "Cửa Hàng Gia Dụng Automation Test ";
//                if (global_shopURLPath.isEmpty())
//                    global_shopURLPath = "household";
//                break;
//        }
//        global_shopName += phoneNumber;
//        global_shopURLPath += "-" + phoneNumber;
//        global_shopURL += global_shopURLPath;
//
//        //1. Upload avavtar
//        String avatarURL = createShopAvatar(token);
//        Assert.assertNotNull(avatarURL, "FAILED to create Avatar URL of Shop");
//
//        //2. Upload cover
//        String coverURL = createShopCover(token);
//        Assert.assertNotNull(coverURL, "FAILED to create Cover Image URL of Shop");
//
//        //3. Create shop preview
//        createShopReview(token, phoneNumber, cateID);
//
//        //4. Update shop preview
//        if (cateID.equalsIgnoreCase(CATEID_VEH)) {
//            updateShopReview(token, coverURL, avatarURL, true);
//        } else {
//            updateShopReview(token, coverURL, avatarURL, false);
//        }
//
//      /*  //4. Pay Shop Order
//        //Pay Shop
//        if (isUsingDT4B) {
//            payOrderShop_DT4B(token, cateID, duration);
//        } else {
//            payOrderShop_DongTot(token, cateID, duration);
//        }
//        //Set Shop Alias
//        getShopAliasAfterPaid();
//
//        //5. Check Shop is active after paid
////        checkShopIsActivated(token);
//
//        //6. Check Shop is pending reviewed by Chotot after paid
//        checkShopIsFirstPendingPreview(token);
//*/
//        //7. Chotot accepts SHOP request
//        cpAcceptPaidShop(getShopAlias());
//
//        //8. Check Shop is Approved
//        checkShopIsAccepted(token);
//
//        //8. On UI, after pay SHOP, a message is sent to Consumer to update User Profiler from private to Shop
//        updateUserProfilerToShop(phoneNumber, shopC2CUserAccountID, cateID);
//    }
//
//    private static String createShopAvatar(String token) {
//        for (int i = 0; i < retryUploadImage; i++) {
//            try {
//                response = postImage(token, gatewayShop_CreateAvatar, AVATAR_PATH);
//                verifyStatusCode200("API DEAD: POST SHOP AVATAR", gatewayShop_CreateAvatar);
//            } catch (AssertionError error) {
//                waitConstant(1);
//            }
//        }
//        return null;
//    }
//
//    private static String createShopCover(String token) {
//        for (int i = 0; i < retryUploadImage; i++) {
//            try {
//                response = postImage(token, gatewayShop_CreateCover, COVER_PATH);
//                verifyStatusCode200("API DEAD", gatewayShop_CreateCover);
//            } catch (AssertionError error) {
//                waitConstant(1);
//            }
//        }
//        return null;
//    }
//
//    private static String createShopReview(String token, String phoneNumber, String categoryID) {
//        initBodyJson();
//        bodyJson.addProperty("categoryId", Integer.parseInt(categoryID));
//        bodyJson.addProperty("phoneNumber", phoneNumber);
//        response = post(token, bodyJson, gatewayShop_CreatePreview);
//        verifyStatusCode200("API DEAD: CREATE SHOP TO PREVIEW");
//        debugResponse();
//
//        Assert.assertEquals(getResponseData("$.status"), "init", "SHOP PREVIEW IS CREATED BUT WRONG STATUS");
//        Assert.assertEquals(getResponseData("$.categoryId"), categoryID, "SHOP PREVIEW IS CREATED BUT WRONG CATEID");
//        Assert.assertEquals(getResponseData("$.phoneNumber"), phoneNumber, "SHOP PREVIEW IS CREATED BUT WRONG PHONE NUMBER");
//        return getResponseData("$.cssId");
//    }
//
//    private static String updateShopReview(String token, String coverURL, String avatarURL, Boolean isVEHShop) {
//        initBodyJson();
//        bodyJson.addProperty("name", global_shopName);
//        bodyJson.addProperty("url", global_shopURLPath);
//
//        if (!isVEHShop) { // VEH shop doesn't allow for workingAreas
//            initBodyJsonForArray();
//            initBodyArray();
//            bodyJsonForArray.addProperty("regionID", global_shopRegion);
//            bodyJsonForArray.addProperty("areaId", global_shopDistrict);
//            bodyJsonForArray.addProperty("areaName", "Quận 1");
//            bodyJsonArray.add(bodyJsonForArray);
//            bodyJson.add("workingAreas", bodyJsonArray);
//        } else {
//            // workingAreas is automatically generated from the location by BE
//        }
//        bodyJson.addProperty("address", global_shopAddress);
//        bodyJson.addProperty("description", global_shopDesc);
//
//        initBodyJsonForArray();
//        initBodyArray();
//
//        bodyJsonForArray.addProperty("url", global_shopURLPath);
//        bodyJsonForArray.addProperty("allowColumnsEdit", true);
//        bodyJsonArray.add(bodyJsonForArray);
//        bodyJson.add("urls", bodyJsonArray);
//
//        bodyJson.addProperty("coverImageUrl", coverURL);
//        bodyJson.addProperty("profileImageUrl", avatarURL);
//
//        response = put(token, bodyJson, gatewayShop_CreatePreview);
//        verifyStatusCode200("API DEAD: UPDATE SHOP TO PREVIEW");
//        debugResponse();
//
//        return getResponseData("$.name");
//    }
//
//    private static void checkShopIsAccept(String token) {
//        int retry = 0;
//        while (retry < retryCheckShopStatus) {
//            try {
//                response = get(token, gatewayShop_Status);
//                verifyStatusCode200("API DEAD: CHECK SHOP STATUS");
//                Assert.assertEquals("$.[0].staus", "accepted", "Shop ISN'T ACTIVE");
//                Assert.assertEquals("$.inVerified", "true", "SHOP ISN'T VERIFIED");
//                break;
//            } catch (AssertionError assertionError) {
//                waitConstant(4);
//                retry++;
//            }
//        }
//        debugResponse();
//        Assert.assertEquals("$.[0].staus", "accepted", "Shop ISN'T ACTIVE");
//        Assert.assertEquals("$.inVerified", "true", "SHOP ISN'T VERIFIED");
//    }
//    private static void checkShopIsAccepted(String token) {
//        int retry = 0;
//        while (retry < retryCheckShopStatus) {
//            try {
//                response = get(token, gatewayShop_Status);
//                verifyStatusCode200("API CHECK SHOP STATUS IS DEAD");
//                Assert.assertEquals(getResponseData("$[0].status"), "accepted", "SHOP ISN'T ACTIVE");
//                Assert.assertEquals(getResponseData("$[0].isVerified"), "true", "SHOP ISN'T VERIFIED");
//                break;
//            } catch (AssertionError assertionError) {
//                waitConstant(4);
//                retry++;
//            }
//        }
//        debugResponse();
//        Assert.assertEquals(getResponseData("$[0].status"), "accepted", "SHOP ISN'T ACTIVE");
//        Assert.assertEquals(getResponseData("$[0].isVerified"), "true", "SHOP ISN'T VERIFIED");
//    }
//
//}
