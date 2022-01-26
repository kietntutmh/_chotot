package projects.functions.chotot.supportQAManual;

import com.vn.chotot.api.rest_assured.RestAssure;
import com.vn.chotot.data.ExcelProvider;
import io.cucumber.core.internal.gherkin.deps.com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import projects.functions.APISupportFunctions;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertELT;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertPTY;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertVehicle;
import projects.functions.chotot.flashad.FlashAd_ELT_Functions;
import projects.functions.chotot.flashad.FlashAd_VEH_Functions;

import java.util.ArrayList;
import java.util.List;

import static api.configuration.DataConfig.*;
import static api.configuration.GatewayConfig.*;
import static api.feature.ads.InsertAds.insertNewAd;
import static api.feature.cp.AcceptCP.acceptNewAd;
import static api.utils.GetAccessToken.*;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping_NoUploadNewImage;
import static desktop.configuration.LoginConfig.tempUserPhone;
import static projects.functions.chotot.payment.PayOrder_API_Functions.paymentOrder;
import static projects.functions.chotot.payment.TopupDongTot_API_Functions.*;
import static projects.functions.chotot.pos.POS_Functions.posStickyAd;
import static projects.functions.chotot.reward.Reward_API_Functions.addPointToUser;
import static projects.functions.chotot.shops.PaidShop_API_Functions.*;

public class SupportQAManual_Functions extends APISupportFunctions {
    private static String ownerToken = "";
    private static String cpToken = getAccessTokenOfCPUser();
    private static int price = 12312000;
    private static String subject = "Auto Test Create %d Ad(s)";
    private static String region_v2 = "13000";
    private static String ward = "9226";
    private static String area = "13096";
    private static String type = "s";
    private static String body = "Mô tả bằng tiếng Việt có dấu: - Số tầng lầu - Số phòng ngủ, phòng khách, bếp, vệ sinh - Nội thất cơ bản hoặc liệt kê nội thất có sẵn - Thời gian đến các quận trung tâm hay bệnh viện, trường học, siêu thị, chợ gần nhất,";

    public static void insertAdSell_NewUser_Private(int numberAd, String cate, String subcate, String cpAction) {
        ownerToken = standardizeToken(getAccessTokenOfNewUser());
        insertAdSell_Private(numberAd, cate, subcate, cpAction);
    }

    public static void insertAdSell_SpecifiedUser_Private(String phoneNumber, int numberAd, String cate, String subcate, String cpAction) {
        ownerToken = standardizeToken(getAccessTokenOfUser(phoneNumber, "123456"));
        insertAdSell_Private(numberAd, cate, subcate, cpAction);
    }

    private static void insertAdSell_Private(int numberAd, String cate, String subcate, String cpAction) {
        for (int i = 0; i < numberAd; i++) {
            bodyJson = new JsonObject();
            bodyJson.addProperty("category", cate);
            bodyJson.addProperty("subCategory", subcate);
            bodyJson.addProperty("type", type);
            bodyJson.addProperty("ward", ward);
            bodyJson.addProperty("company_ad", "0");
            bodyJson.addProperty("price", price);
            bodyJson.addProperty("condition_ad", "2");      //new
            bodyJson.addProperty("subject", String.format(subject, numberAd));
            bodyJson.addProperty("body", body);
            bodyJson.addProperty("app_id", "desktop_site_flashad");
            bodyJson.addProperty("image_id0", "2676912440016808580.jpg");
            bodyJson.addProperty("uid", "48824");
            bodyJson.addProperty("skip_verify_phone", "1");
            bodyJson.addProperty("region_v2", region_v2);
            bodyJson.addProperty("area_v2", area);
            bodyJson.addProperty("lang", "vi");


            response = RestAssure.instance().post(ownerToken, bodyJson, gatewayFlashAdNew);
            verifyStatusCode200("FLASH AD", gatewayFlashAdNew);
            String ad_id = getResponseData(response, "$.ad_id");

            bodyJson = new JsonObject();
            bodyJson.addProperty("remote_addr", "127.0.0.1");
            bodyJson.addProperty("ad_id", ad_id);
            bodyJson.addProperty("action_id", 1);
            bodyJson.addProperty("subject", String.format(subject, numberAd));
            bodyJson.addProperty("price", price);
            bodyJson.addProperty("region_v2", region_v2);
            bodyJson.addProperty("ward", ward);

            bodyChildJson = new JsonObject();
            bodyChildJson.addProperty("area", area);
            bodyChildJson.addProperty("region_v2", region_v2);
            bodyJson.add("ad_params", bodyChildJson);

            bodyJson.addProperty("category", Integer.parseInt(cate));
            bodyJson.addProperty("type", type);
            bodyJson.addProperty("token", cpToken);

            switch (cpAction.toLowerCase().trim()) {
                case "accept":
                    acceptNewAd(String.valueOf(bodyJson));
                    break;
            }
        }
        System.out.println("\n\nPHOHE: " + tempUserPhone);
        System.out.println("TOKEN: " + ownerToken);
    }

    //=========================================
    private static List<String> dataKeys = new ArrayList<>(), dataValues = new ArrayList<>();
    private static final String DEFAULT_SUBJECT = "Automation Bán phone";
    private static final String DEFAULT_TYPE = "sell";
    private static final String CATE_PHONE = "5010";
    private static final String SUBCATE_PHONE = "5010";
    private static final String DEFAULT_REGION = "13000";       //TPHCM
    private static final String DEFAULT_DISTRICT = "13096";     //Q1
    private static final String DEFAULT_WARD = "9219";          //P.Ben Nghe
    private static final String OWNER_PRIVATE = "0";          //Ad private
    private static final String OWNER_PRO = "1";          //Ad pro

    private static String adCate, adPhone, adPassword;
    private static boolean isAdPro = false, isAdNew = true;
    private static String valueSubject, valueBody;

    public static void insertAdByParam() {
        valueSubject = StringUtils.isEmpty(System.getProperty("subject")) ? "" : System.getProperty("subject");
        valueBody = StringUtils.isEmpty(System.getProperty("body")) ? "" : System.getProperty("body");
        adCate = StringUtils.isEmpty(System.getProperty("cate")) ? "mobile" : System.getProperty("cate");
        isAdPro = Boolean.parseBoolean(System.getProperty("pro"));
        adPhone = StringUtils.isEmpty(System.getProperty("phone")) ? "" : System.getProperty("phone");
        adPassword = StringUtils.isEmpty(System.getProperty("password")) ? defaultPassword : System.getProperty("password");
        isAdNew = Boolean.parseBoolean(System.getProperty("isAdNew"));
        insertNewAdETL(isAdPro, adCate, adPhone, adPassword);
    }

    private static String insertNewAdETL(boolean isAdPro, String cate, String phone, String password) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        int rowKeyNum = 1;
        int rowValueNum = 2;
        String userID = "";

        switch (cate.toUpperCase()) {
            case "LAPTOP":
                cate = "Laptop";
                if (isAdPro) {
                    rowKeyNum = 17;
                    rowValueNum = 18;
                }
                break;
            case "TABLET":
                cate = "Tablet";
                if (isAdPro) {
                    rowKeyNum = 12;
                    rowValueNum = 13;
                }
                break;
            case "PC":
                cate = "PC";
                if (isAdPro) {
                    rowKeyNum = 12;
                    rowValueNum = 13;
                }
                break;
            case "PCCOM":
                cate = "PC_Component";
                if (isAdPro) {
                    rowKeyNum = 12;
                    rowValueNum = 13;
                }
                break;
            case "CAMERA":
                cate = "Camera";
                if (isAdPro) {
                    rowKeyNum = 12;
                    rowValueNum = 13;
                }
                break;
            case "SOUND":
                cate = "Sound";
                if (isAdPro) {
                    rowKeyNum = 12;
                    rowValueNum = 13;
                }
                break;
            case "WEARABLE":
            case "WATCH":
                cate = "Wearable";
                if (isAdPro) {
                    rowKeyNum = 12;
                    rowValueNum = 13;
                }
                break;
            case "ACCESSORIES":
                cate = "Accessories";
                if (isAdPro) {
                    rowKeyNum = 12;
                    rowValueNum = 13;
                }
                break;
            case "PHONE":
            case "MOBILE":
            default:
                cate = "Phone";
                if (isAdPro) {
                    rowKeyNum = 12;
                    rowValueNum = 13;
                }
                break;
        }
        excelDataProvider.getExcelFileSheet(adELTExcelFile, cate);

        // Set access token
        if (phone.isEmpty()) {
            getAccessTokenOfNewUser();
            phone = tempUserPhone;
            userID = global_accountID;
        } else {
            getAccessTokenOfUser(phone, defaultPassword);
            userID = global_accountID;
        }


        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(rowKeyNum);
        dataValues = excelDataProvider.getRowData(rowValueNum);

        //setup subject & body
        setSubject();
        setBody();
        setNewOld();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad phone data
        insertNewAd(data);

        // Return new ad_id

        System.out.println("\n\n================ NEW AD INFORMATION ================");
        System.out.println("AD ID: " + tempAdID);
        System.out.println("AD SUBJECT: " + dataValues.get(dataKeys.indexOf("subject")));
        System.out.println("AD BODY: " + dataValues.get(dataKeys.indexOf("body")));
        if (isAdNew)
            System.out.println("AD STATUS: Mới");
        else
            System.out.println("AD STATUS: Đã qua sử dụng");

        System.out.println("\nPHONE NUMBER: " + phone);
        System.out.println("PASSWORD: " + password);
        System.out.println("ACCOUNT ID: " + userID);
        System.out.println("=====================================================\n\n\n");
        return tempAdID;
    }

    private static void setSubject() {
        if (!valueSubject.isEmpty()) {
            for (String key : dataKeys) {
                if (key.equalsIgnoreCase("subject")) {
                    dataValues.set(dataKeys.indexOf(key), valueSubject);
                    break;
                }
            }
        }
    }

    private static void setBody() {
        if (!valueBody.isEmpty()) {
            for (String key : dataKeys) {
                if (key.equalsIgnoreCase("body")) {
                    dataValues.set(dataKeys.indexOf(key), valueBody);
                    break;
                }
            }
        }
    }

    private static void setNewOld() {
        for (String key : dataKeys) {
            if (key.equalsIgnoreCase("elt_condition")) {
                if (isAdNew) {
                    dataValues.set(dataKeys.indexOf(key), "1");     //NEW
                } else {
                    dataValues.set(dataKeys.indexOf(key), "2");     //OLD
                }
                break;
            }
        }
    }

    public static void getAllCateSubcate() {
        response = RestAssure.instance().get(gatewayCategories_GetAllSubCate);
        verifyStatusCode200("GET CATEGORY", gatewayCategories_GetAllSubCate);
        List<String> cateIdList = getResponseDataList(response, "$.categories[*].id");
        List<String> cateNameList = getResponseDataList(response, "$.categories[*].name");

        List<String> subCateIdList = getResponseDataList(response, "$.categories[*].subcategories[*].id");
        List<String> subCateNameList = getResponseDataList(response, "$.categories[*].subcategories[*].name");
        List<String> subCateParentIdList = getResponseDataList(response, "$.categories[*].subcategories[*].parent");


        //Display
        for (int i = 0; i < subCateIdList.size(); i++) {
            String subcateID = subCateIdList.get(i);
            String subcateName = subCateNameList.get(i);
            String parentID = subCateParentIdList.get(i);
            int cateIDIndex = cateIdList.indexOf(parentID);
            String cateName = cateNameList.get(cateIDIndex);

            System.out.printf("\n%s\t%s\t%s\t%s", parentID, cateName, subcateID, subcateName);
        }
    }

    public static void vuhoangdebuga() {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date now = new Date();
//        String oldDate = sdf.format(now);
//
//        Calendar c = Calendar.getInstance();
//        try{
//            c.setTime(sdf.parse(oldDate));
//        }catch(ParseException e){
//            e.printStackTrace();
//        }
//        c.add(Calendar.DAY_OF_MONTH, 1);
//        String newDate = sdf.format(c.getTime());
//        System.out.println("Date after Addition: "+newDate);
//        CM_API_Ads_InsertELT cm = new CM_API_Ads_InsertELT();
//        String adID = cm.insertNewAdLaptop();
//        System.out.println(adID);

        getAccessTokenOfNewUser();
        System.out.println(global_accessToken);

    }

    //===================== SHOP ELT INSERT 30 ADS =========================
    public static void insertAdByParam_Shop_InsertAds() {
        //Get param from command line
        String cpAction = StringUtils.isEmpty(System.getProperty("cpAction")) ? "accept" : System.getProperty("cpAction");
        boolean shopToChotot = Boolean.parseBoolean(System.getProperty("shopToChotot"));
        String numberOfAd = StringUtils.isEmpty(System.getProperty("numberOfAd")) ? "30" : System.getProperty("numberOfAd");
        String phone = StringUtils.isEmpty(System.getProperty("phone")) ? "" : System.getProperty("phone");
        boolean isCreateNewShop = Boolean.parseBoolean(System.getProperty("isCreateNewShop"));

        String category = StringUtils.isEmpty(System.getProperty("category")) ? "ELT" : System.getProperty("category");
        String shopUserToken;

        //Function
        CM_API_Ads_InsertELT cm_ad_elt = new CM_API_Ads_InsertELT();
        CM_API_Ads_InsertVehicle cm_ad_veh = new CM_API_Ads_InsertVehicle();
        CM_API_Ads_InsertPTY cm_ad_pty = new CM_API_Ads_InsertPTY();


        if (phone.isEmpty()) {
            if (category.equalsIgnoreCase("ELT")) {
                shopUserToken = getAccessTokenNewUserShopELT();
            } else if (category.equalsIgnoreCase("PTY")) {
                shopUserToken = getAccessTokenNewUserShopPTY();
            } else {
                shopUserToken = getAccessTokenNewUserShopVEH();
            }
            phone = tempUserPhone;
        } else {
            setShopUserPhone(phone);
            if (isCreateNewShop) {
                if (category.equalsIgnoreCase("ELT")) {
                    shopUserToken = getAccessTokenNewUserShopELT(getAccessTokenOfUser(phone, defaultPassword));
                } else if (category.equalsIgnoreCase("PTY")) {
                    shopUserToken = getAccessTokenNewUserShopPTY(getAccessTokenOfUser(phone, defaultPassword));
                } else {
                    shopUserToken = getAccessTokenNewUserShopVEH(getAccessTokenOfUser(phone, defaultPassword));
                }
            } else {
                shopUserToken = getAccessTokenOfUser(phone, defaultPassword);
                setShopUserToken(shopUserToken);
            }
        }

        List<String> adIDs = new ArrayList<>();
        if (shopUserToken != null && !shopUserToken.isEmpty())
            global_accessToken = shopUserToken;
        for (int i = 0; i < Integer.parseInt(numberOfAd); i++) {
            if (category.equalsIgnoreCase("ELT")) {
                adIDs.add(cm_ad_elt.insertNewAdPhoneShop(cpAction.toLowerCase(), shopToChotot, false));
            } else if (category.equalsIgnoreCase("PTY")) {

                adIDs.add(cm_ad_pty.insertNewAdHouseShop(cpAction.toLowerCase(), shopToChotot, false));
            } else {
                adIDs.add(cm_ad_veh.insertNewAdMotorbikeShop(cpAction.toLowerCase(), shopToChotot, false));
            }
        }

        System.out.println("\n\n================ USER SHOP " + category.toUpperCase() + " INFO ================");
        System.out.println("USER PHONE: " + phone);
        System.out.println("ACCOUNT ID: " + global_accountID);
        System.out.println("AD IDs: " + adIDs);
        System.out.println("=====================================================\n\n\n");
    }

    public static void topupForSpecifiedAccount() {
        String phone = StringUtils.isEmpty(System.getProperty("phone")) ? "" : System.getProperty("cpAction");

        String money = StringUtils.isEmpty(System.getProperty("money")) ? "100000" : System.getProperty("money");
        Assert.assertTrue(!phone.isEmpty(), "PHONE IS REQUIRED");
        topupDongTotWithMomo(phone, Integer.parseInt(money));

    }

    public static void insertAdWithStickyAd() {
        String phone = StringUtils.isEmpty(System.getProperty("phone")) ? "" : System.getProperty("phone");
        String adID = StringUtils.isEmpty(System.getProperty("adID")) ? "" : System.getProperty("adID");
        String category = StringUtils.isEmpty(System.getProperty("category")) ? "" : System.getProperty("category");
        int numberOfNewAd = Integer.parseInt(System.getProperty("numberOfNewAd"));
        int duration = Integer.parseInt(System.getProperty("duration"));
        boolean isBuyStickyAd = Boolean.parseBoolean(System.getProperty("isBuyStickyAd"));

        FlashAd_VEH_Functions veh = new FlashAd_VEH_Functions();
        FlashAd_ELT_Functions elt = new FlashAd_ELT_Functions();

        List<String> adIDs = new ArrayList<>();

        if (phone.isEmpty()) {
            ownerToken = standardizeToken(getAccessTokenOfNewUser());
            phone = tempUserPhone;
        } else {
            ownerToken = standardizeToken(getAccessTokenOfUser(phone, "123456"));
        }

        topupDongTotWithMomo_500k(phone);
        System.out.println("-------------------- TOPUP: 500k DONE");

        if (adID.isEmpty()) {
            for (int i = 0; i < numberOfNewAd; i++) {
                switch (category.toUpperCase().trim()) {
                    case "BICYCLES":
                    case "XE DAP":
                        //adID empty = insert new Ad
                        adID = veh.insertBicyclesSellPrivate(ownerToken, "accept", false);
                        System.out.println("-------------------- INSERT NEW AD: DONE: " + adID);
                        break;
                    case "TABLET":
                        adID = elt.insertTabletSellPrivate(ownerToken, "accept", false);
                        System.out.println("-------------------- INSERT NEW AD: DONE: " + adID);
                        break;
                }
                adIDs.add(adID);
            }
        }

        if (isBuyStickyAd) {
            posStickyAd(ownerToken, adID, duration);
            paymentOrder();
            System.out.println("-------------------- BUY STICKY FOR NEW AD: DONE: " + adID);
        }

        System.out.println("\n\n================ STICKY AD SUPPORT ================");
        System.out.println("PHONE: " + phone);
        System.out.println("CATEGORY: " + category.toUpperCase().trim());
        System.out.println("STICKY AD DURATION: " + duration);
        System.out.println("AD IDs: " + adIDs);
        System.out.println("=====================================================\n\n\n");

    }


    public static void registerAndTopup() {
        String phone = StringUtils.isEmpty(System.getProperty("phone")) ? "" : System.getProperty("phone");

        // Register new user
        if (!phone.isEmpty()) {
            getAccessTokenOfUser(phone, defaultPassword);
        } else {
            getAccessTokenOfNewUser();
            phone = newUserPhone;
        }

        // Topup Dong Tot 365
        String dt365 = StringUtils.isEmpty(System.getProperty("dt365")) ? "" : System.getProperty("dt365");
        try {
            if (!dt365.isEmpty()) {
                switch (dt365.toUpperCase()) {
                    case "100K":
                        topupDongTotWithMomo_100k(phone);
                        break;
                    case "200K":
                        topupDongTotWithMomo_200k(phone);
                        break;
                    case "1000K":
                        topupDongTotWithMomo_1000k(phone);
                        break;
                    case "2000K":
                        topupDongTotWithMomo_2000k(phone);
                        break;
                    case "3000K":
                        topupDongTotWithMomo_3000k(phone);
                        break;
                    case "500K":
                    default:
                        topupDongTotWithMomo_500k(phone);
                        break;
                }
            }
        } catch (Exception | AssertionError e) {
            dt365 = "NẠP THẤT BẠI";
        }


        // Topup Dong Tot FREE
        String dtFree = StringUtils.isEmpty(System.getProperty("dtFree")) ? "" : System.getProperty("dtFree");
        try {
            if (!dtFree.isEmpty()) {
                dtFree = dtFree.toLowerCase().replace("k", "000");
                topupDongTotFree_ByRedeemPromotionCode(global_accountID, phone, Integer.parseInt(dtFree));
            }
        } catch (Exception | AssertionError e) {
            dtFree = "NẠP THẤT BẠI";
        }


        // Topup Dong Tot Bank Transfer
        String dtBT = StringUtils.isEmpty(System.getProperty("dtBT")) ? "" : System.getProperty("dtBT");
        try {
            if (!dtBT.isEmpty()) {
                switch (dtBT.toUpperCase()) {
                    case "10K":
                        topupDongTotExpiry_10k(global_accountID, phone);
                        break;
                    default:
                        topupDongTotExpiry_20k(global_accountID, phone);
                        break;
                }
            }
        } catch (Exception | AssertionError e) {
            dtBT = "NẠP THẤT BẠI";
        }

        System.out.println("\n\n================ REGISTER NEW USER & TOPUP DT ================");
        System.out.println("PHONE: " + phone);
        System.out.println("ACCOUNT ID: " + global_accountID);
        System.out.println("DONG TOT 365: " + dt365);
        System.out.println("DONG TOT FREE: " + dtFree.replace("000", "k"));
        System.out.println("DONG TOT BANK TRANSFER: " + dtBT);
        System.out.println("=========================================================\n\n\n");
    }

    public static void addDiemTotToUser(){
        String phone = StringUtils.isEmpty(System.getProperty("phone")) ? "" : System.getProperty("phone");
        String point = StringUtils.isEmpty(System.getProperty("point")) ? "" : System.getProperty("point");

        if (!phone.isEmpty() && !point.isEmpty()) {
            getAccessTokenOfUser(phone, defaultPassword);
            addPointToUser(global_accountID, Integer.parseInt(point.trim()));
            System.out.println("\n\n================ ADDED "+point+" DIEM TOT TO "+phone+" ================");
            System.out.println("=========================================================\n\n\n");
        }else{
            System.out.println("PHONE & POINT MUST BE INPUTTED");
        }
    }
}
