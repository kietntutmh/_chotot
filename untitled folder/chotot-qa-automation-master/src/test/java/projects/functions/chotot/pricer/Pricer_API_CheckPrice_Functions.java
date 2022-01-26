package projects.functions.chotot.pricer;

import com.google.api.services.sheets.v4.model.ValueRange;
import com.vn.chotot.api.google_sheet.SheetActions;
import org.testng.Assert;
import projects.functions.APISupportFunctions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static api.configuration.GatewayConfig.*;
import static api.utils.GetAccessToken.getAccessTokenOfNewUser;
import static projects.configuaration.CategoryConfig.*;

public class Pricer_API_CheckPrice_Functions extends APISupportFunctions {
    private static final boolean COLLECT_PRICERRESULT_TOFILE = true;

    private static String userToken = "";
    private static String userAccountID = "";
    private static List<String> regionList = null;
    private static List<String> subCateIdList = null;
    private static List<String> subCateNameList = null;
    private static List<String> failedSubCateList = new ArrayList<>();
    private static List<String> cateShopIdList = null;
    private static String expectedPrice = "";
    private static String expectedPriceVND = "";
    private static String expectedPriceDongTot = "";
    private static String actualPrice = "";
    private static String failedMsg = "";

    //Ad-feature
    private final static List<String> adFeature_Features = Arrays.asList("title", "ribbon", "price");
    private final static List<String> adFeature_Durations = Arrays.asList("7");

    private final static List<String> bump_Types = Arrays.asList("bump", "3days_bump", "7days_bump", "timer_bump");

    private final static List<Integer> shopCreate_Durations = Arrays.asList(1, 2, 3, 6, 12);    //5000 : 1 2 3 6, 1000,2000: 1 3 6 12
    private final static List<Integer> shopExtend_Durations = Arrays.asList(1, 2, 3, 6, 12);    //5000 : 1 2 3 6, 1000,2000: 1 3 6 12
    private final static List<Integer> stickyAds_Durations = Arrays.asList(1, 2, 3, 5, 7);
    private final static List<Integer> stickyAds = Arrays.asList(1);

    private final static List<String> priceTypes = Arrays.asList("vnd", "credit", "promotion");

    public static void setFailedMsg(String msg) {
        failedMsg = msg;
    }

    private static ValueRange valueRange = null;
    private static List<List<Object>> allValue = null;

    private static String sheetName = "";

    //Pricer NEW JS

    //Getter & Setter
    public static String getUserToken() {
        return userToken;
    }

    public static void setUserToken(String userToken) {
        Pricer_API_CheckPrice_Functions.userToken = userToken;
    }

    public static void setUserAccountID(String userID) {
        Pricer_API_CheckPrice_Functions.userAccountID = userID;
    }

    public static String getUserAccountID() {
        return Pricer_API_CheckPrice_Functions.userAccountID;
    }

    public static String getSheetName() {
        return sheetName;
    }

    public static void setSheetName(String sheetName) {
        Pricer_API_CheckPrice_Functions.sheetName = sheetName;
    }

    //=========================== BUMP: DONG TOT, VND ============================
    //Functions
    public static void verifyBumpPrice_VND() {
        verifyBumpPrice(getUserToken(), "bump", "vnd");
    }

    public static void verifyBump3DaysPrice_VND() {
        verifyBumpPrice(getUserToken(), "3days_bump", "vnd");
    }

    public static void verifyBump7DaysPrice_VND() {
        verifyBumpPrice(getUserToken(), "7days_bump", "vnd");
    }

    public static void verifyBumpTimerPrice_VND() {
        verifyBumpPrice(getUserToken(), "timer_bump", "vnd");
    }

    //Verify Bump Dong Tot
    public static void verifyBumpPrice_DongTot() {
        verifyBumpPrice(getUserToken(), "bump", "dongtot");
    }

    public static void verifyBump3DaysPrice_DongTot() {
        verifyBumpPrice(getUserToken(), "3days_bump", "dongtot");
    }

    public static void verifyBump7DaysPrice_DongTot() {
        verifyBumpPrice(getUserToken(), "7days_bump", "dongtot");
    }

    public static void verifyBumpTimerPrice_DongTot() {
        verifyBumpPrice(getUserToken(), "timer_bump", "dongtot");
    }

    private static void verifyBumpPrice(String token, String bumpType, String priceType) {
        String subCateId = "";
        boolean isPassed = true;

        query = "$.bump_price." + bumpType.toLowerCase() + ".%s";

        response = get(token, gatewayPricer_Private_GetAllPrice_Bump);
        verifyStatusCode200("API get Bump Price");
        debugResponse();

        for (int i = 0; i < subCateIdList.size(); i++) {
            subCateId = subCateIdList.get(i);
            actualPrice = getResponseData(response, String.format(query, subCateId));           //Only get 1 price to check VND & dongtot
            expectedPrice = getCSVData_Bump_Price(subCateId, bumpType, priceType);
            checkExpectedPriceEmpty();


            //If expectedPrice exists & actualPrice doesn't exist, then Chưa khai báo giá
            if (expectedPrice == null) {      //ExpectedPrice doesn't exist
                if (actualPrice == null) {
                    continue;
                } else {
                    if (isPassed) isPassed = false;
                    failedMsg += String.format("Bump Price Cate[%s] Type[%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                    ""
                            , subCateId, priceType, actualPrice);
                }
            } else {
                if (actualPrice == null) {   //CSV != null, API == null
                    if (isPassed) isPassed = false;
                    failedMsg += String.format("Bump Price Cate[%s] Type[%s]: API[Chưa khai báo giá] <> CSV[%s]\n" +
                                    ""
                            , subCateId, priceType, expectedPrice);
                } else {   //CSV != null, API != null
                    try {
                        Assert.assertEquals(actualPrice, expectedPrice);
                    } catch (AssertionError error) {
                        if (isPassed) isPassed = false;
                        failedMsg += String.format("Bump Price Cate[%s] Type[%s]: API[%s] <> CSV[%s]\n" +
                                        ""
                                , subCateId, priceType, actualPrice, expectedPrice);
                    }
                }
            }
        }
        writeResultToFileText("Bump Price " + priceType.toUpperCase() + " is incorrect with subcates: \n");
        Assert.assertTrue(isPassed, "Bump Price " + priceType.toUpperCase() + " is incorrect with subcates: \n" +
                "" + failedMsg);
    }

    //=========================== BUMP UNIT: DONG TOT, VND ============================
    //Verify Bump UNIT VND
    public static void verifyBumpPriceUnit_VND() {
        verifyBumpPriceUnit(getUserToken(), "bump", "vnd");
    }

    public static void verifyBump3DaysPriceUnit_VND() {
        verifyBumpPriceUnit(getUserToken(), "3days_bump", "vnd");
    }

    public static void verifyBump7DaysPriceUnit_VND() {
        verifyBumpPriceUnit(getUserToken(), "7days_bump", "vnd");
    }

    public static void verifyBumpTimerPriceUnit_VND() {
        verifyBumpPriceUnit(getUserToken(), "timer_bump", "vnd");
    }

    //Verify Bump UNIT VND
    public static void verifyBumpPriceUnit_DongTot() {
        verifyBumpPriceUnit(getUserToken(), "bump", "credit");
    }

    public static void verifyBump3DaysPriceUnit_DongTot() {
        verifyBumpPriceUnit(getUserToken(), "3days_bump", "credit");
    }

    public static void verifyBump7DaysPriceUnit_DongTot() {
        verifyBumpPriceUnit(getUserToken(), "7days_bump", "credit");
    }

    public static void verifyBumpTimerPriceUnit_DongTot() {
        verifyBumpPriceUnit(getUserToken(), "timer_bump", "credit");
    }

    //Verify Bump UNIT Promotion
    public static void verifyBumpPriceUnit_Promotion() {
        verifyBumpPriceUnit(getUserToken(), "bump", "Promotion");
    }

    public static void verifyBump3DaysPriceUnit_Promotion() {
        verifyBumpPriceUnit(getUserToken(), "3days_bump", "Promotion");
    }

    public static void verifyBump7DaysPriceUnit_Promotion() {
        verifyBumpPriceUnit(getUserToken(), "7days_bump", "Promotion");
    }

    public static void verifyBumpTimerPriceUnit_Promotion() {
        verifyBumpPriceUnit(getUserToken(), "timer_bump", "Promotion");
    }

    private static void verifyBumpPriceUnit(String token, String bumpType, String priceType) {
        String subCateId = "";
        boolean isPassed = true;

        query = "$.bump_price_unit." + bumpType.toLowerCase() + ".%s." + priceType.toLowerCase();

        response = get(token, gatewayPricer_Private_GetAllPrice_Bump);
        verifyStatusCode200("API get Bump Price");
        debugResponse();

        for (int i = 0; i < subCateIdList.size(); i++) {
            subCateId = subCateIdList.get(i);
            actualPrice = getResponseData(response, String.format(query, subCateId));           //Only get 1 price to check VND & dongtot
            expectedPrice = getCSVData_Bump_Price(subCateId, bumpType, priceType);
            checkExpectedPriceEmpty();


            //If expectedPrice exists & actualPrice doesn't exist, then Chưa khai báo giá
            if (expectedPrice == null) {      //ExpectedPrice doesn't exist
                if (actualPrice == null) {
                    continue;
                } else {
                    if (isPassed) isPassed = false;
                    failedMsg += String.format("Bump Price UNIT Cate[%s] Type[%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                    ""
                            , subCateId, priceType, actualPrice);
                }
            } else {
                if (actualPrice == null) {   //CSV != null, API == null
                    if (isPassed) isPassed = false;
                    failedMsg += String.format("Bump Price UNIT Cate[%s] Type[%s]: API[Chưa khai báo giá] <> CSV[%s]\n" +
                                    ""
                            , subCateId, priceType, expectedPrice);
                } else {   //CSV != null, API != null
                    try {
                        Assert.assertEquals(actualPrice, expectedPrice);
                    } catch (AssertionError error) {
                        if (isPassed) isPassed = false;
                        failedMsg += String.format("Bump Price UNIT Cate[%s] Type[%s]: API[%s] <> CSV[%s]\n" +
                                        ""
                                , subCateId, priceType, actualPrice, expectedPrice);
                    }
                }
            }
        }
        writeResultToFileText("Bump Price " + priceType.toUpperCase() + " is incorrect with subcates: \n");
        Assert.assertTrue(isPassed, "Bump Price " + priceType.toUpperCase() + " is incorrect with subcates: \n" +
                "" + failedMsg);
    }


    public static void verifyAdFeature_VND() {
        verifyAdFeaturePrice("vnd");
    }

    public static void verifyAdFeature_DongTot() {
        verifyAdFeaturePrice("DongTot");
    }

    public static void verifyAdFeature_Promotion() {
        verifyAdFeaturePrice("Promotion");
    }

    private static void verifyAdFeaturePrice(String priceType) {
        String subCateId = "", price = "";
        boolean isPassed = true;

        //Init query
        switch (priceType.toLowerCase()) {
            case "vnd":
                price = "vnd";
                break;
            case "dongtot":
            case "credit":
                price = "credit";
                break;
            case "promotion":
                price = "promotion";
                break;
        }

        //get all price:
        response = get(gatewayPricer_Public_GetAllPrice);
        debugResponse();
        verifyStatusCode200("API PRICER get-all AdFeature is Dead");

        //filter to compare price
        String queryCampainId = "", queryPriceCampain = "";
        for (int i = 0; i < subCateIdList.size(); i++) {
            subCateId = subCateIdList.get(i);
            for (String feature : adFeature_Features) {
                for (String duration : adFeature_Durations) {

                    //Ad Feature disables : cate 2010, feature price
                    if (subCateId.equalsIgnoreCase("2010")
                            && feature.equalsIgnoreCase("price")
                            && duration.equalsIgnoreCase("7"))
                        continue;


                    List<String> campaignIds, priceCampaign = new ArrayList<>(), priceAll = new ArrayList<>();

                    //Get campaignIds
                    queryCampainId = "$.ad_features[?(" +
                            "@.category_id == " + subCateId + " " +
                            "&& @.duration == " + duration + " " +
                            "&& @.feature == '" + feature + "'" +
                            ")].campaign_id";
                    campaignIds = getResponseDataList(queryCampainId);

                    //Get list of prices that are with campaign
                    for (String campaignId : campaignIds) {
                        queryPriceCampain = "$.ad_features[?(" +
                                "@.category_id == " + subCateId + " " +
                                "&& @.duration == " + duration + " " +
                                "&& @.feature == '" + feature + "'" +
                                "&& @.campaign_id == '" + campaignId + "'" +
                                ")].price." + price;
                        priceCampaign.add(String.valueOf(getResponseDataInt(queryPriceCampain)).trim());
                    }

                    //Get all prices
                    query = "$.ad_features[?(" +
                            "@.category_id == " + subCateId + " " +
                            "&& @.duration == " + duration + " " +
                            "&& @.feature == '" + feature + "'" +
                            ")].price." + price;

                    List<Integer> priceIntAll = getResponseDataListInt(query);
                    for (int priceInt : priceIntAll) {
                        priceAll.add(String.valueOf(priceInt).trim());
                    }

                    //Get data from API
                    priceAll.removeAll(priceCampaign);

                    if (priceAll.size() == 0) {
                        actualPrice = null;
                    } else if (priceAll.size() > 1) {
                        if (isPassed) isPassed = false;
                        failedMsg += String.format("Ad Feature Type[%s] Duration[%s] Price[%s] Cate[%s] DB[Duplicated Data][%s]\n" +
                                "", feature, duration, price, subCateId, priceAll.toString());

                        continue;
                    } else {   //priceAll == 1
                        actualPrice = priceAll.get(0);
                    }

                    //Get data from CSV
                    expectedPrice = getCSVData_AdFeature_Price(subCateId, feature, duration, priceType);
                    checkExpectedPriceEmpty();      //CSV empty => = 0, to compare ok

                    //If expectedPrice exists & actualPrice doesn't exist, then Chưa khai báo giá
                    if (expectedPrice == null) {      //ExpectedPrice doesn't exist
                        if (actualPrice == null) {
                            continue;
                        } else {
                            if (isPassed)
                                isPassed = false;
                            failedMsg += String.format("Ad Feature Cate[%s] Duration[%s] Feature[%s]: API[%s] <> CSV[NOT FOUND] \n" +
                                            ""
                                    , subCateId, duration, feature, actualPrice);
                        }
                    } else {
                        if (actualPrice == null) {   //CSV != null, API == null
                            if (isPassed)
                                isPassed = false;
                            failedMsg += String.format("Ad Feature Cate[%s] Duration[%s] Feature[%s]: API[Chưa khai báo giá] <> CSV[%s] \n" +
                                            ""
                                    , subCateId, duration, feature, expectedPrice);
                        } else {   //CSV != null, API != null
                            try {
                                Assert.assertEquals(actualPrice, expectedPrice);
                            } catch (AssertionError error) {
                                if (isPassed)
                                    isPassed = false;
                                failedMsg += String.format("Ad Feature Cate[%s] Duration[%s] Feature[%s]: API[%s] <> CSV[%s] \n" +
                                                ""
                                        , subCateId, duration, feature, actualPrice, expectedPrice);
                            }
                        }
                    }
                }
            }
        }
        writeResultToFileText("Ad Feature Price GetAll PUBLIC " + priceType.toUpperCase() + " FAILED: \n");
        Assert.assertTrue(isPassed, "Ad Feature Price GetAll PUBLIC " + priceType.toUpperCase() + " FAILED: \n" +
                "" + failedMsg);
    }


    public static void verifyAdFeatureInternal_VND() {
        verifyAdFeatureInternalPrice("vnd");
    }

    public static void verifyAdFeatureInternal_DongTot() {
        verifyAdFeatureInternalPrice("credit");
    }

    public static void verifyAdFeatureInternal_Promotion() {
        verifyAdFeatureInternalPrice("promotion");
    }

    private static void verifyAdFeatureInternalPrice(String priceType) {
        boolean isPassed = true;

        //Init query
        switch (priceType.toLowerCase()) {
            case "vnd":
                query = "$.price.vnd";
                break;
            case "dongtot":
            case "credit":
                query = "$.price.credit";
                break;
            case "promotion":
                query = "$.price.promotion";     //Failed, API get all doesn't response
                break;
        }

        //Init data

        //get all price:
        for (String subCateId : subCateIdList) {
            for (String feature : adFeature_Features) {
                for (String duration : adFeature_Durations) {

                    //Ad Feature disables : cate 2010, feature price
                    if (subCateId.equalsIgnoreCase("2010")
                            && feature.equalsIgnoreCase("price")
                            && duration.equalsIgnoreCase("7"))
                        continue;

                    response = get(String.format(gatewayPricer_Internal_AdFeature, subCateId, feature, duration));

                    actualPrice = getResponseData(response, query);
                    expectedPrice = getCSVData_AdFeature_Price(subCateId, feature, duration, priceType);
                    checkExpectedPriceEmpty();     //If expectedPrice is empty -> return 0

                    //If expectedPrice exists & actualPrice doesn't exist, then Chưa khai báo giá
                    if (expectedPrice == null) {      //ExpectedPrice doesn't exist
                        if (actualPrice == null) {
                            continue;
                        } else {
                            if (isPassed)
                                isPassed = false;

                            if (isNullActualPrice()) //API null, CSV not found -> price type is NOT AVAILABLE
                                failedMsg += String.format("AD Feature Internal Cate[%s] Duration[%s] Feature[%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                                ""
                                        , subCateId, duration, feature, actualPrice);
                        }
                    } else {
                        if (!getResponseCode().startsWith("200")) {    //API response error
                            if (isPassed)
                                isPassed = false;
                            failedMsg += String.format("Ad Feature Internal Cate[%s] Duration[%s] Feature[%s]: API[Không tim thấy giá dịch vụ]\n" +
                                            ""
                                    , subCateId, duration, feature);
                            continue;
                        } else if (actualPrice == null) {   //CSV != null, API == null
                            if (isPassed)
                                isPassed = false;
                            failedMsg += String.format("Ad Feature Internal Cate[%s] Duration[%s] Feature[%s]: API[Chưa khai báo giá] <> CSV[%s] \n" +
                                            ""
                                    , subCateId, duration, feature, expectedPrice);
                        } else {   //CSV != null, API != null
                            try {
                                Assert.assertEquals(actualPrice, expectedPrice);
                            } catch (AssertionError error) {
                                if (isPassed)
                                    isPassed = false;
                                failedMsg += String.format("Ad Feature Internal Cate[%s] Duration[%s] Feature[%s]: API[%s] <> CSV[%s]\n" +
                                                ""
                                        , subCateId, duration, feature, actualPrice, expectedPrice);
                            }
                        }
                    }
                }
            }
        }
        writeResultToFileText("Ad Feature Internal Price [" + priceType.toUpperCase() + "] FAILED");
        Assert.assertTrue(isPassed, "Ad Feature Internal Price [" + priceType.toUpperCase() + "] FAILED: \n" +
                "" + failedMsg);
    }

    //STICKY ADS
    public static void verifyStickyAdsPrice_VND() {
        verifyStickyAdsPrice("vnd");
    }

    public static void verifyStickyAdsPrice_DongTot() {
        verifyStickyAdsPrice("dongtot");
    }

    public static void verifyStickyAdsPrice_Promotion() {
        verifyStickyAdsPrice("promotion");
    }

    private static void verifyStickyAdsPrice(String priceType) {
        String subCateId = "", stickyad = "", price = "";
        boolean isPassed = true;

        //Init query
        switch (priceType.toLowerCase()) {
            case "vnd":
                price = ".vnd";
                break;
            case "dongtot":
            case "credit":
                price = ".credit";
                break;
            case "promotion":
                price = ".promotion";
                break;
        }
        //get all price:
        response = get(gatewayPricer_Public_GetAllPrice);
        verifyStatusCode200("API GET-ALL STICKYADS PUBLIC DEAD");

        String queryCampainId = "", queryPriceCampain = "";
        //filter to compare price
        for (int i = 0; i < subCateIdList.size(); i++) {
            subCateId = subCateIdList.get(i);
            for (String region : regionList) {
                for (int duration : stickyAds_Durations) {
                    for (int stickad : stickyAds) {
                        List<String> campaignIds, priceCampaign = new ArrayList<>(), priceAll = new ArrayList<>();

                        //Get campaignIds
                        queryCampainId = "$.sticky_ads[*][?(@.category_id == " + subCateId + " " +
                                "&& @.stickyads == " + stickad + " " +
                                "&& @.duration == " + duration + " " +
                                "&& @.region_id == " + region + ")].campaign_id";
                        campaignIds = getResponseDataList(queryCampainId);

                        //Get list of prices that are with campaign
                        for (String campaignId : campaignIds) {
                            queryPriceCampain = "$.sticky_ads[*][?(@.category_id == " + subCateId + " " +
                                    "&& @.stickyads == " + stickad + " " +
                                    "&& @.duration == " + duration + " " +
                                    "&& @.region_id == " + region +
                                    "&& @.campaign_id == '" + campaignId + "'" +
                                    ")].price" + price;
                            priceCampaign.add(String.valueOf(getResponseDataInt(queryPriceCampain)).trim());
                        }

                        query = "$.sticky_ads[*][?(@.category_id == " + subCateId + " " +
                                "&& @.stickyads == " + stickad + " " +
                                "&& @.duration == " + duration + " " +
                                "&& @.region_id == " + region + ")]" + price;

                        List<Integer> priceIntAll = getResponseDataListInt(query);
                        for (int priceInt : priceIntAll) {
                            priceAll.add(String.valueOf(priceInt).trim());
                        }

                        //Get data from API
                        priceAll.removeAll(priceCampaign);

                        if (priceAll.size() == 0) {
                            actualPrice = null;
                        } else if (priceAll.size() > 1) {
                            if (isPassed) isPassed = false;
                            failedMsg += String.format("Sticky Ad Cate[%s] Duration[%s] Region[%s] DB[Duplicated Data][%s]\n" +
                                    "", subCateId, duration, region, priceAll.toString());

                            continue;
                        } else {   //priceAll == 1
                            actualPrice = priceAll.get(0);
                        }

                        //Get data from CSV
                        expectedPrice = getCSVData_StickyAds_Price(subCateId, stickyad, region, duration, priceType);
                        checkExpectedPriceEmpty();     //If expectedPrice is empty -> return 0

                        //If expectedPrice exists & actualPrice doesn't exist, then Chưa khai báo giá
                        if (expectedPrice == null) {      //ExpectedPrice doesn't exist
                            if (actualPrice == null) {
                                continue;
                            } else {
                                if (isPassed)
                                    isPassed = false;

                                if (isNullActualPrice()) //API null, CSV not found -> price type is NOT AVAILABLE
                                    failedMsg += String.format("Cate [%s] Duration[%s] Region[%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                                    ""
                                            , subCateId, duration, region, actualPrice);
                            }
                        } else {
                            if (actualPrice == null) {   //CSV != null, API == null
                                if (isPassed)
                                    isPassed = false;
                                failedMsg += String.format("Cate[%s] Duration[%s] Region[%s]: API[Chưa khai báo giá] <> CSV[%s]\n" +
                                                ""
                                        , subCateId, duration, region, expectedPrice);
                            } else {   //CSV != null, API != null
                                try {
                                    Assert.assertEquals(actualPrice, expectedPrice);
                                } catch (AssertionError error) {
                                    if (isPassed)
                                        isPassed = false;
                                    failedMsg += String.format("Cate[%s] Duration[%s] Region[%s]: API[%s] <> CSV[%s]\n" +
                                                    ""
                                            , subCateId, duration, region, actualPrice, expectedPrice);
                                }
                            }
                        }
                    } //End for Sticky ad
                }
            }
        }
        writeResultToFileText("Sticky Ads Price " + priceType.toUpperCase() + " FAILED: \n");
        Assert.assertTrue(isPassed, "Sticky Ads Price " + priceType.toUpperCase() + " FAILED: \n" +
                "" + failedMsg);
    }


    public static void verifyStickyAdsInternalPrice_VND() {
        verifyStickyAdsInternalPrice("vnd");
    }

    public static void verifyStickyAdsInternalPrice_DongTot() {
        verifyStickyAdsInternalPrice("credit");
    }

    public static void verifyStickyAdsInternalPrice_Promotion() {
        verifyStickyAdsInternalPrice("promotion");
    }

    private static void verifyStickyAdsInternalPrice(String priceType) {
        String subCateId = "";
        boolean isPassed = true;

        //Init query
        switch (priceType.toLowerCase()) {
            case "vnd":
                query = "$.price.vnd";
                break;
            case "dongtot":
            case "credit":
                query = "$.price.credit";
                break;
            case "promotion":
                query = "$.price.promotion";
                break;
        }
        int reTestAPI = 3;

        //filter to compare price
        for (int i = 0; i < subCateIdList.size(); i++) {
            subCateId = subCateIdList.get(i);
            for (String region : regionList) {
                for (int duration : stickyAds_Durations) {
                    for (int stickad : stickyAds) {
                        response = get(String.format(gatewayPricer_StickyAds_Internal, region, subCateId, duration, stickad));
                        try {
                            verifyStatusCode200("API GET-ALL STICKYADS");
                        } catch (AssertionError e) {
                            failedMsg += String.format("API [%s] IS DEAD\n" +
                                            ""
                                    , String.format(gatewayPricer_StickyAds_Internal, region, subCateId, duration, stickad));
                            continue;
                        }


                        actualPrice = getResponseData(query);
                        expectedPrice = getCSVData_StickyAds_Price(subCateId, String.valueOf(stickad), region, duration, priceType);
                        checkExpectedPriceEmpty();     //If expectedPrice is empty -> return 0

                        //If expectedPrice exists & actualPrice doesn't exist, then Chưa khai báo giá
                        if (expectedPrice == null) {      //ExpectedPrice doesn't exist
                            if (actualPrice == null) {
                                continue;
                            } else {
                                if (isPassed)
                                    isPassed = false;

                                failedMsg += String.format("[%s] Duration[%s] Region[%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                                ""
                                        , subCateId, duration, region, actualPrice);
                            }
                        } else {
                            if (actualPrice == null) {   //CSV != null, API == null
                                if (isPassed)
                                    isPassed = false;
                                failedMsg += String.format("Cate[%s] Duration[%s] Region[%s]: API[Chưa khai báo giá]\n" +
                                                ""
                                        , subCateId, duration, region);
                            } else {   //CSV != null, API != null
                                try {
                                    Assert.assertEquals(actualPrice, expectedPrice);
                                } catch (AssertionError error) {
                                    if (isPassed)
                                        isPassed = false;
                                    failedMsg += String.format("Cate[%s] Duration[%s] Region[%s]: API[%s] <> CSV[%s]\n" +
                                                    ""
                                            , subCateId, duration, region, actualPrice, expectedPrice);
                                }
                            }
                        }
                    }
                }
            }
        }
        writeResultToFileText("Sticky Ads Price " + priceType.toUpperCase() + " FAILED: \n");
        Assert.assertTrue(isPassed, "Sticky Ads Price " + priceType.toUpperCase() + " FAILED: \n" +
                "" + failedMsg);
    }


    //------------ LISTING FEE PRICER GOLDPOT: PRIVATE & INTERNAL------------

    //PTY /pricer
    public static void verifyListingFeePrice_PricerGoldpot_SellAd_PTY_AllRegions(String cateIDs, String token, String accountID) {
        String subCateId = "";
        boolean isPassed = true;
        //Get all subcates which are applied for all regions
        String[] subCateAppliedAllRegion = cateIDs.replace(" ", "").trim().split(",");
        subCateIdList = new ArrayList<>();
        for (int i = 0; i < subCateAppliedAllRegion.length - 1; i++) {
            subCateIdList.add(subCateAppliedAllRegion[i]);
        }

        //PTY:
        regionList = new ArrayList<>();
        regionList.add("2010");     //Bà Rịa Vũng Tàu
        regionList.add("2011");     //Bình Dương
        regionList.add("2012");     //Bình Phước
        regionList.add("2013");     //Đồng Nai
        regionList.add("2014");     //Tây Ninh
        regionList.add("13000");     //HCM

        //Check price of each subcate
        for (int i = 0; i < subCateIdList.size(); i++) {
            subCateId = subCateIdList.get(i);

            for (String regionId : regionList) {
                if (token.isEmpty())
                    response = get(String.format(gatewayPricer_ListingFee_Private_PricerGoldpotInternal, accountID, regionId, subCateId));
                else
                    response = get(token, String.format(gatewayPricer_ListingFee_Private_PricerGoldpotPrivate, accountID, regionId, subCateId));

                try {
                    verifyStatusCode200("API Listing Fee /pricer of cate[" + subCateId + "] dead");
                } catch (AssertionError a) {
                    isPassed = false;
                    failedMsg += String.format("Cate[%s] Region[%s]: API[ERROR]]\n" +
                                    ""
                            , subCateId, regionId);
                    continue;
                }

                String actionType = getResponseData("$.action_type");

                //Each subcate -> check 3 types of price: vnd, dongtot, promotion
                for (String priceType : priceTypes) {
                    switch (priceType.toLowerCase()) {
                        case "vnd":
                            query = "$.price.vnd";
                            break;
                        case "dongtot":
                        case "credit":
                            query = "$.price.credit";
                            break;
                        case "promotion":
                            query = "$.price.promotion";
                            break;
                    }

                    //Check if Sell ad of cate is free -> skip
                    actualPrice = getResponseData(query);

                    expectedPrice = getPriceCSVByCate_ListingFee_SellAd_Pricer(subCateId, regionId, priceType, actionType);
                    checkExpectedPriceEmpty(priceType);     //If expectedPrice is empty -> return 0
                    //Get price insertad -> null, then get insert_ad
                    if (expectedPrice == null) {
                        String actionTypeCSV = changeActionType(actionType);
                        expectedPrice = getPriceCSVByCate_ListingFee_SellAd_Pricer(subCateId, regionId, priceType, actionTypeCSV);
                        checkExpectedPriceEmpty(priceType);
                    }

                    //COMPARE PRICE
                    if (expectedPrice == null) {      //ExpectedPrice doesn't exist
                        if (actualPrice == null) {
                            continue;
                        } else {
                            if (isPassed)
                                isPassed = false;

                            if (isNullActualPrice()) //API null, CSV not found -> price type is NOT AVAILABLE
                                failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                                ""
                                        , subCateId, regionId, priceType, actualPrice);
                        }
                    } else {
                        if (actualPrice == null) {   //CSV != null, API == null
                            if (isPassed)
                                isPassed = false;
                            failedMsg += String.format("Cate[%s] Region[%s]: API[Chưa khai báo giá] <> CSV[%s]\n" +
                                            ""
                                    , subCateId, regionId, priceType, expectedPrice);
                        } else {   //CSV != null, API != null
                            try {
                                Assert.assertEquals(actualPrice, expectedPrice);
                            } catch (AssertionError error) {
                                if (isPassed)
                                    isPassed = false;
                                failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[%s]\n" +
                                                ""
                                        , subCateId, regionId, priceType, actualPrice, expectedPrice);
                            }
                        }
                    }//End if action
                }
            }
        }
        String msg = "API LISTING FEE PTY ALL REGIONS GOLDPOT /pricer internal & private: \n" +
                "";
        writeResultToFileText(msg);
        Assert.assertTrue(isPassed, msg + failedMsg);
    }

    public static void verifyListingFeePrice_PricerGoldpot_SellAdShop_PTY_AllRegions(String cateIDs, String token, String accountID) {
        String subCateId = "";
        boolean isPassed = true;
        //Get all subcates which are applied for all regions
        String[] subCateAppliedAllRegion = cateIDs.replace(" ", "").trim().split(",");
        subCateIdList = new ArrayList<>();
        for (int i = 0; i < subCateAppliedAllRegion.length - 1; i++) {
            subCateIdList.add(subCateAppliedAllRegion[i]);
        }

        //PTY:
        regionList = new ArrayList<>();
        regionList.add("2010");     //Bà Rịa Vũng Tàu
        regionList.add("2011");     //Bình Dương
        regionList.add("2012");     //Bình Phước
        regionList.add("2013");     //Đồng Nai
        regionList.add("2014");     //Tây Ninh
        regionList.add("13000");     //HCM

        //Check price of each subcate
        for (int i = 0; i < subCateIdList.size(); i++) {
            subCateId = subCateIdList.get(i);

            for (String regionId : regionList) {
                if (token.isEmpty())
                    response = get(String.format(gatewayPricer_ListingFee_Shop_PricerGoldpotInternal, accountID, regionId, subCateId));
                else
                    response = get(token, String.format(gatewayPricer_ListingFee_Shop_PricerGoldpotPrivate, accountID, regionId, subCateId));

                try {
                    verifyStatusCode200("API Listing Fee /pricer of cate[" + subCateId + "] dead");
                } catch (AssertionError a) {
                    isPassed = false;
                    if (getResponseData(response, "$.message").contains("Không tìm thấy giá dịch vụ")) {
                        failedMsg += String.format("Cate[%s] Region[%s]: API[Không tìm thấy khai báo giá dịch vụ]]\n" +
                                        ""
                                , subCateId, regionId);
                    } else {
                        failedMsg += String.format("Cate[%s] Region[%s]: API[ERROR]]\n" +
                                        ""
                                , subCateId, regionId);
                    }
                    continue;
                }
                String actionType = getResponseData("$.action_type");


                //Each subcate -> check 3 types of price: vnd, dongtot, promotion
                for (String priceType : priceTypes) {
                    query = "$.price." + priceType.toLowerCase();

                    //Check if Sell ad of cate is free -> skip
                    actualPrice = getResponseData(query);
                    expectedPrice = getPriceCSVByCate_ListingFee_SellAd_Pricer(subCateId, regionId, priceType, actionType);

                    checkExpectedPriceEmpty();     //If expectedPrice is empty -> return 0

                    //COMPARE PRICE
                    if (expectedPrice == null) {      //ExpectedPrice doesn't exist
                        if (actualPrice == null) {
                            continue;
                        } else {
                            if (isPassed)
                                isPassed = false;

                            if (isNullActualPrice()) //API null, CSV not found -> price type is NOT AVAILABLE
                                failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                                ""
                                        , subCateId, regionId, priceType, actualPrice);
                        }
                    } else {
                        if (actualPrice == null) {   //CSV != null, API == null
                            if (isPassed)
                                isPassed = false;
                            failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[Chưa khai báo giá] <> CSV[%s]\n" +
                                            ""
                                    , subCateId, regionId, priceType, expectedPrice);
                        } else {   //CSV != null, API != null
                            try {
                                Assert.assertEquals(actualPrice, expectedPrice);
                            } catch (AssertionError error) {
                                if (isPassed)
                                    isPassed = false;
                                failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[%s]\n" +
                                                ""
                                        , subCateId, regionId, priceType, actualPrice, expectedPrice);
                            }
                        }
                    }//End if action
                }
            }
        }
        String msg = "API LISTING FEE PTY ALL REGIONS SHOP GOLDPOT /pricer Shop internal & private: \n" +
                "";
        writeResultToFileText(msg);
        Assert.assertTrue(isPassed, msg + failedMsg);
    }

    public static void verifyListingFeePrice_PricerGoldpot_RentAd_PTY_13000(String token, String accountID) {
        String subCateId = "";
        boolean isPassed = true;

        //PTY:
        String regionId = "13000";
        subCateIdList = getAllSubcateId(CATEID_PTY);
        //Check price of each subcate
        for (int i = 0; i < subCateIdList.size(); i++) {
            subCateId = subCateIdList.get(i);

            if (token.isEmpty())
                response = get(String.format(gatewayPricer_ListingFee4Rent_Private_PricerGoldpotInternal, accountID, regionId, subCateId));
            else
                response = get(token, String.format(gatewayPricer_ListingFee4Rent_Private_PricerGoldpotPrivate, accountID, regionId, subCateId));

            verifyStatusCode200("API Listing Fee /pricer of cate[" + subCateId + "] dead");
            String actionType = getResponseData("$.action_type");

            //Each subcate -> check 3 types of price: vnd, dongtot, promotion
            for (String priceType : priceTypes) {
                switch (priceType.toLowerCase()) {
                    case "vnd":
                        query = "$.price.vnd";
                        break;
                    case "dongtot":
                    case "credit":
                        query = "$.price.credit";
                        break;
                    case "promotion":
                        query = "$.price.promotion";
                        break;
                }

                //Check if Sell ad of cate is free -> skip
                actualPrice = getResponseData(query);

                expectedPrice = getPriceCSVByCate_ListingFee_RentAd_Pricer(subCateId, regionId, priceType, actionType);
                checkExpectedPriceEmpty();     //If expectedPrice is empty -> return 0
                if (expectedPrice == null) {
                    String actionTypeCSV = changeActionType(actionType);
                    expectedPrice = getPriceCSVByCate_ListingFee_RentAd_Pricer(subCateId, regionId, priceType, actionTypeCSV);
                    checkExpectedPriceEmpty(priceType);
                }

                //COMPARE PRICE
                if (expectedPrice == null) {      //ExpectedPrice doesn't exist
                    if (actualPrice == null) {
                        continue;
                    } else {
                        if (isPassed)
                            isPassed = false;

                        failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                        ""
                                , subCateId, regionId, priceType, actualPrice);
                    }
                } else {
                    if (actualPrice == null) {   //CSV != null, API == null
                        if (isPassed)
                            isPassed = false;
                        failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[Chưa khai báo giá] <> CSV[%s]\n" +
                                        ""
                                , subCateId, regionId, priceType, expectedPrice);
                    } else {   //CSV != null, API != null
                        try {
                            Assert.assertEquals(actualPrice, expectedPrice);
                        } catch (AssertionError error) {
                            if (isPassed)
                                isPassed = false;
                            failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[%s]\n" +
                                            ""
                                    , subCateId, regionId, priceType, actualPrice, expectedPrice);
                        }
                    }
                }//End if action
            }
        }
        String msg = "API LISTING FEE PTY RENT 13000 GOLDPOT/pricer internal & private: \n" +
                "";
        writeResultToFileText(msg);
        Assert.assertTrue(isPassed, msg + failedMsg);
    }

    public static void verifyListingFeePrice_PricerGoldpot_RentAdShop_PTY_13000(String token, String accountID) {
        String subCateId = "";
        boolean isPassed = true;

        //PTY:
        String regionId = "13000";
        subCateIdList = getAllSubcateId(CATEID_PTY);
        //Check price of each subcate
        for (int i = 0; i < subCateIdList.size(); i++) {
            subCateId = subCateIdList.get(i);

            if (token.isEmpty())
                response = get(String.format(gatewayPricer_ListingFee4Rent_Shop_PricerGoldpotInternal, accountID, regionId, subCateId));
            else
                response = get(token, String.format(gatewayPricer_ListingFee4Rent_Shop_PricerGoldpotPrivate, accountID, regionId, subCateId));

            verifyStatusCode200("API Listing Fee /pricer of cate[" + subCateId + "] dead");
            String actionType = "$.action_type";

            //Each subcate -> check 3 types of price: vnd, dongtot, promotion
            for (String priceType : priceTypes) {
                switch (priceType.toLowerCase()) {
                    case "vnd":
                        query = "$.price.vnd";
                        break;
                    case "dongtot":
                    case "credit":
                        query = "$.price.credit";
                        break;
                    case "promotion":
                        query = "$.price.promotion";
                        break;
                }

                //Check if Sell ad of cate is free -> skip
                actualPrice = getResponseData(query);
                try {
                    Assert.assertNotNull(actualPrice);
                } catch (AssertionError a) {
                    if (isPassed)
                        isPassed = false;
                    failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[Chưa khai báo giá]\n" +
                                    ""
                            , subCateId, regionId, priceType);
                    continue;
                }

                expectedPrice = getPriceCSVByCate_ListingFee_RentAd_Pricer(subCateId, regionId, priceType, actionType);
                checkExpectedPriceEmpty();     //If expectedPrice is empty -> return 0
                try {
                    Assert.assertNotNull(expectedPrice);
                } catch (AssertionError ex) {
                    if (isPassed)
                        isPassed = false;

                    if (isNullActualPrice()) //API null, CSV not found -> price type is NOT AVAILABLE
                        failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                        ""
                                , subCateId, regionId, priceType, actualPrice);
                    continue;
                }

                //Compare price
                try {
                    Assert.assertEquals(actualPrice, expectedPrice);
                } catch (AssertionError error) {
                    if (isPassed)
                        isPassed = false;
                    failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[%s]\n" +
                                    ""
                            , subCateId, regionId, priceType, actualPrice, expectedPrice);
                }
            }
        }
        String msg = "API LISTING FEE PTY RENT 13000 GOLDPOT /pricer internal & private: \n" +
                "";
        writeResultToFileText(msg);
        Assert.assertTrue(isPassed, msg + failedMsg);
    }


    //VEH /pricer
    public static void verifyListingFeePrice_PricerGoldpot_SellAd_VEH_AllRegions(String cateIDs, String token, String accountID) {
        String subCateId = "";
        boolean isPassed = true;

        //Get all subcates which are applied for all regions
        String[] subCateAppliedAllRegion = cateIDs.replace(" ", "").trim().split(",");
        subCateIdList = new ArrayList<>();
        for (int i = 0; i < subCateAppliedAllRegion.length - 1; i++) {
            subCateIdList.add(subCateAppliedAllRegion[i]);
        }

        //Check price of each subcate
        for (int i = 0; i < subCateIdList.size(); i++) {
            subCateId = subCateIdList.get(i);

            for (String regionId : regionList) {
                if (token.isEmpty())
                    response = get(String.format(gatewayPricer_ListingFee_Private_PricerGoldpotInternal, accountID, regionId, subCateId));
                else
                    response = get(token, String.format(gatewayPricer_ListingFee_Private_PricerGoldpotPrivate, accountID, regionId, subCateId));

                verifyStatusCode200("API Listing Fee /pricer of cate[" + subCateId + "] dead");
                String actionType = getResponseData("$.action_type");


                //Each subcate -> check 3 types of price: vnd, dongtot, promotion
                for (String priceType : priceTypes) {
                    switch (priceType.toLowerCase()) {
                        case "vnd":
                            query = "$.price.vnd";
                            break;
                        case "dongtot":
                        case "credit":
                            query = "$.price.credit";
                            break;
                        case "promotion":
                            query = "$.price.promotion";
                            break;
                    }

                    //Check if Sell ad of cate is free -> skip

                    actualPrice = getResponseData(query);

                    expectedPrice = getPriceCSVByCate_ListingFee_SellAd_Private_AllRegions_Pricer(subCateId, priceType, actionType);
                    checkExpectedPriceEmpty(priceType);
                    checkExpectedPriceEmpty();     //If expectedPrice is empty -> return 0
                    if (expectedPrice == null) {
                        String actionTypeCSV = changeActionType(actionType);
                        expectedPrice = getPriceCSVByCate_ListingFee_SellAd_Private_AllRegions_Pricer(subCateId, priceType, actionType);
                        checkExpectedPriceEmpty(priceType);
                    }

                    //COMPARE PRICE
                    if (expectedPrice == null) {      //ExpectedPrice doesn't exist
                        if (actualPrice == null) {
                            continue;
                        } else {
                            if (isPassed)
                                isPassed = false;

                            failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                            ""
                                    , subCateId, regionId, priceType, actualPrice);
                        }
                    } else {
                        if (actualPrice == null) {   //CSV != null, API == null
                            if (isPassed)
                                isPassed = false;
                            failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[Chưa khai báo giá] <> CSV[%s]\n" +
                                            ""
                                    , subCateId, regionId, priceType, expectedPrice);
                        } else {   //CSV != null, API != null
                            try {
                                Assert.assertEquals(actualPrice, expectedPrice);
                            } catch (AssertionError error) {
                                if (isPassed)
                                    isPassed = false;

                                failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[%s]\n" +
                                                ""
                                        , subCateId, regionId, priceType, actualPrice, expectedPrice);
                            }
                        }
                    }//End if action
                }
            }
        }
        String msg = "API LISTING FEE VEH GOLPOT SELL AD ALL REGION/pricer internal & private: \n" +
                "";
        writeResultToFileText(msg);
        Assert.assertTrue(isPassed, msg + failedMsg);
    }

    public static void verifyListingFeePrice_PricerGoldpot_SellAdShop_VEH_AllRegions(String cateIDs, String token, String accountID) {
        String subCateId = "";
        boolean isPassed = true;

        //Get all subcates which are applied for all regions
        String[] subCateAppliedAllRegion = cateIDs.replace(" ", "").trim().split(",");
        subCateIdList = new ArrayList<>();
        for (int i = 0; i < subCateAppliedAllRegion.length - 1; i++) {
            subCateIdList.add(subCateAppliedAllRegion[i]);
        }

        //Check price of each subcate
        for (int i = 0; i < subCateIdList.size(); i++) {
            subCateId = subCateIdList.get(i);

//            for (String regionId : regionList) {
            if (token.isEmpty())
                response = get(String.format(gatewayPricer_ListingFee_Shop_PricerGoldpotInternal, accountID, subCateId));
            else
                response = get(token, String.format(gatewayPricer_ListingFee_Shop_PricerGoldpotPrivate, accountID, subCateId));

            verifyStatusCode200("API Listing Fee /pricer of cate[" + subCateId + "] dead");

            String actionType = getResponseData("$.action_type");


            //Each subcate -> check 3 types of price: vnd, dongtot, promotion
            for (String priceType : priceTypes) {
                switch (priceType.toLowerCase()) {
                    case "vnd":
                        query = "$.price.vnd";
                        break;
                    case "dongtot":
                    case "credit":
                        query = "$.price.credit";
                        break;
                    case "promotion":
                        query = "$.price.promotion";
                        break;
                }


                actualPrice = getResponseData(query);
                expectedPrice = getPriceCSVByCate_ListingFee_SellAd_Private_AllRegions_Pricer(subCateId, priceType, actionType);
                checkExpectedPriceEmpty();     //If expectedPrice is empty -> return 0
                //COMPARE PRICE
                if (expectedPrice == null) {      //ExpectedPrice doesn't exist
                    if (actualPrice == null) {
                        continue;
                    } else {
                        if (isPassed)
                            isPassed = false;

                        failedMsg += String.format("Cate[%s] Price[%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                        ""
                                , subCateId, priceType, actualPrice);
                    }
                } else {
                    if (actualPrice == null) {   //CSV != null, API == null
                        if (isPassed)
                            isPassed = false;
                        failedMsg += String.format("Cate[%s] Price[%s]: API[Chưa khai báo giá] <> CSV[%s]\n" +
                                        ""
                                , subCateId, priceType, expectedPrice);
                    } else {   //CSV != null, API != null
                        try {
                            Assert.assertEquals(actualPrice, expectedPrice);
                        } catch (AssertionError error) {
                            if (isPassed)
                                isPassed = false;
                            failedMsg += String.format("Cate[%s] Price[%s]: API[%s] <> CSV[%s]\n" +
                                            ""
                                    , subCateId, priceType, actualPrice, expectedPrice);
                        }
                    }
                }//End if action
            }
//            }
        }
        String msg = "API LISTING FEE VEH GOLDPOT SELL AD SHOP /pricer internal & private: \n" +
                "";
        writeResultToFileText(msg);
        Assert.assertTrue(isPassed, msg + failedMsg);
    }


    //ELT /pricer
    public static void verifyListingFeePrice_PricerGoldpot_SellAd_ELT_AllRegions(String cateIDs, String token, String accountID) {
        String subCateId = "";
        boolean isPassed = true;

        //Get all subcates which are applied for all regions
        String[] subCateAppliedAllRegion = cateIDs.replace(" ", "").trim().split(",");
        subCateIdList = new ArrayList<>();
        for (int i = 0; i < subCateAppliedAllRegion.length - 1; i++) {
            subCateIdList.add(subCateAppliedAllRegion[i]);
        }

        //Check price of each subcate
        for (int i = 0; i < subCateIdList.size(); i++) {
            subCateId = subCateIdList.get(i);

            for (String regionId : regionList) {
                if (token.isEmpty())
                    response = get(String.format(gatewayPricer_ListingFee_Private_PricerGoldpotInternal, accountID, regionId, subCateId));
                else
                    response = get(token, String.format(gatewayPricer_ListingFee_Private_PricerGoldpotPrivate, accountID, regionId, subCateId));

                verifyStatusCode200("API Listing Fee /pricer of cate[" + subCateId + "] dead");
                String actionType = getResponseData("$.action_type");


                //Each subcate -> check 3 types of price: vnd, dongtot, promotion
                for (String priceType : priceTypes) {
                    switch (priceType.toLowerCase()) {
                        case "vnd":
                            query = "$.price.vnd";
                            break;
                        case "dongtot":
                        case "credit":
                            query = "$.price.credit";
                            break;
                        case "promotion":
                            query = "$.price.promotion";
                            break;
                    }

                    //Check if Sell ad of cate is free -> skip
                    actualPrice = getResponseData(query);

                    expectedPrice = getPriceCSVByCate_ListingFee_SellAd_Private_AllRegions_Pricer(subCateId, priceType, actionType);
                    checkExpectedPriceEmpty();     //If expectedPrice is empty -> return 0
                    if (expectedPrice == null) {
                        String actionTypeCSV = changeActionType(actionType);
                        expectedPrice = getPriceCSVByCate_ListingFee_SellAd_Private_AllRegions_Pricer(subCateId, priceType, actionTypeCSV);
                        checkExpectedPriceEmpty(priceType);
                    }

                    //COMPARE PRICE
                    if (expectedPrice == null) {      //ExpectedPrice doesn't exist
                        if (actualPrice == null) {
                            continue;
                        } else {
                            if (isPassed)
                                isPassed = false;

                            failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                            ""
                                    , subCateId, regionId, priceType, actualPrice);
                        }
                    } else {
                        if (actualPrice == null) {   //CSV != null, API == null
                            if (isPassed)
                                isPassed = false;
                            failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[Chưa khai báo giá] <> CSV[%s]\n" +
                                            ""
                                    , subCateId, regionId, priceType, expectedPrice);
                        } else {   //CSV != null, API != null
                            try {
                                Assert.assertEquals(actualPrice, expectedPrice);
                            } catch (AssertionError error) {
                                if (isPassed)
                                    isPassed = false;
                                failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[%s]\n" +
                                                ""
                                        , subCateId, regionId, priceType, actualPrice, expectedPrice);
                            }
                        }
                    }//End if action
                }
            }
        }
        String msg = "API LISTING FEE ELT SELL ALL REGION GOLPOT /pricer internal & private: \n" +
                "";
        writeResultToFileText(msg);
        Assert.assertTrue(isPassed, msg + failedMsg);
    }

    public static void verifyListingFeePrice_PricerGoldpot_SellAdShop_ELT_AllRegions(String cateIDs, String token, String accountID) {
        String subCateId = "";
        boolean isPassed = true;

        //Get all subcates which are applied for all regions
        String[] subCateAppliedAllRegion = cateIDs.replace(" ", "").trim().split(",");
        subCateIdList = new ArrayList<>();
        for (int i = 0; i < subCateAppliedAllRegion.length - 1; i++) {
            subCateIdList.add(subCateAppliedAllRegion[i]);
        }

        //Check price of each subcate
        for (int i = 0; i < subCateIdList.size(); i++) {
            subCateId = subCateIdList.get(i);

            if (token.isEmpty())
                response = get(String.format(gatewayPricer_ListingFee_Shop_PricerGoldpotInternal, accountID, subCateId));
            else
                response = get(token, String.format(gatewayPricer_ListingFee_Shop_PricerGoldpotPrivate, accountID, subCateId));

            verifyStatusCode200("API Listing Fee /pricer of cate[" + subCateId + "] dead");
            String actionType = getResponseData("$.action_type");


            //Each subcate -> check 3 types of price: vnd, dongtot, promotion
            for (String priceType : priceTypes) {
                switch (priceType.toLowerCase()) {
                    case "vnd":
                        query = "$.price.vnd";
                        break;
                    case "dongtot":
                    case "credit":
                        query = "$.price.credit";
                        break;
                    case "promotion":
                        query = "$.price.promotion";
                        break;
                }

                //Check if Sell ad of cate is free -> skip
                actualPrice = getResponseData(query);

                expectedPrice = getPriceCSVByCate_ListingFee_SellAd_Private_AllRegions_Pricer(subCateId, priceType, actionType);
                checkExpectedPriceEmpty();     //If expectedPrice is empty -> return 0
                if (expectedPrice == null) {      //ExpectedPrice doesn't exist
                    if (actualPrice == null) {
                        continue;
                    } else {
                        if (isPassed)
                            isPassed = false;

                        failedMsg += String.format("Cate[%s] Price[%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                        ""
                                , subCateId, priceType, actualPrice);
                    }
                } else {
                    if (actualPrice == null) {   //CSV != null, API == null
                        if (isPassed)
                            isPassed = false;
                        failedMsg += String.format("Cate[%s] Duration[%s] : API[Chưa khai báo giá] <> CSV[%s]\n" +
                                        ""
                                , subCateId, priceType, expectedPrice);
                    } else {   //CSV != null, API != null
                        try {
                            Assert.assertEquals(actualPrice, expectedPrice);
                        } catch (AssertionError error) {
                            if (isPassed)
                                isPassed = false;
                            failedMsg += String.format("Cate[%s] Price[%s]: API[%s] <> CSV[%s]\n" +
                                            ""
                                    , subCateId, priceType, actualPrice, expectedPrice);
                        }
                    }
                }//End if action
            }
        }
        String msg = "API LISTING FEE ELT SHOP ALL REGION/pricer internal & private: \n" +
                "";
        writeResultToFileText(msg);
        Assert.assertTrue(isPassed, msg + failedMsg);
    }


    //------------ LISTING FEE PRICER: PRIVATE & INTERNAL------------

    //PTY /pricer
    public static void verifyListingFeePrice_Pricer_SellAd_PTY_AllRegions(String cateIDs, String token, String accountID) {
        String subCateId = "";
        boolean isPassed = true;
        //Get all subcates which are applied for all regions
        String[] subCateAppliedAllRegion = cateIDs.replace(" ", "").trim().split(",");
        subCateIdList = new ArrayList<>();
        for (int i = 0; i < subCateAppliedAllRegion.length - 1; i++) {
            subCateIdList.add(subCateAppliedAllRegion[i]);
        }

        //PTY:
        regionList = new ArrayList<>();
        regionList.add("2010");     //Bà Rịa Vũng Tàu
        regionList.add("2011");     //Bình Dương
        regionList.add("2012");     //Bình Phước
        regionList.add("2013");     //Đồng Nai
        regionList.add("2014");     //Tây Ninh
        regionList.add("13000");     //HCM

        //Check price of each subcate
        for (int i = 0; i < subCateIdList.size(); i++) {
            subCateId = subCateIdList.get(i);

            for (String regionId : regionList) {
                if (token.isEmpty())
                    response = get(String.format(gatewayPricer_ListingFee_Private_PricerInternal, accountID, regionId, subCateId));
                else
                    response = get(token, String.format(gatewayPricer_ListingFee_Private_PricerPrivate, accountID, regionId, subCateId));

                try {
                    verifyStatusCode200("API Listing Fee /pricer of cate[" + subCateId + "] dead");
                } catch (AssertionError a) {
                    isPassed = false;
                    if (getResponseData(response, "$.message").contains("Không tìm thấy giá dịch vụ")) {
                        failedMsg += String.format("Cate[%s] Region[%s]: API[Không tìm thấy khai báo giá dịch vụ]]\n" +
                                        ""
                                , subCateId, regionId);
                    } else {
                        failedMsg += String.format("Cate[%s] Region[%s]: API[ERROR]]\n" +
                                        ""
                                , subCateId, regionId);
                    }
                    continue;
                }
                String actionType = "$.action_type";


                //Each subcate -> check 3 types of price: vnd, dongtot, promotion
                for (String priceType : priceTypes) {
                    switch (priceType.toLowerCase()) {
                        case "vnd":
                            query = "$.price.vnd";
                            break;
                        case "dongtot":
                        case "credit":
                            query = "$.price.credit";
                            break;
                        case "promotion":
                            query = "$.price.promotion";
                            break;
                    }

                    //Check if Sell ad of cate is free -> skip
                    actualPrice = getResponseData(query);
                    try {
                        Assert.assertNotNull(actualPrice);
                    } catch (AssertionError a) {
                        if (isPassed)
                            isPassed = false;
                        failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[Chưa khai báo giá]\n" +
                                        ""
                                , subCateId, regionId, priceType);
                        continue;
                    }

                    expectedPrice = getPriceCSVByCate_ListingFee_SellAd_Pricer(subCateId, regionId, priceType, actionType);
                    checkExpectedPriceEmpty();     //If expectedPrice is empty -> return 0
                    try {
                        Assert.assertNotNull(expectedPrice);
                    } catch (AssertionError ex) {
                        if (isPassed)
                            isPassed = false;

                        if (isNullActualPrice()) //API null, CSV not found -> price type is NOT AVAILABLE
                            failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                            ""
                                    , subCateId, regionId, priceType, actualPrice);
                        continue;
                    }

                    //Compare price
                    try {
                        Assert.assertEquals(actualPrice, expectedPrice);
                    } catch (AssertionError error) {
                        if (isPassed)
                            isPassed = false;
                        failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[%s]\n" +
                                        ""
                                , subCateId, regionId, priceType, actualPrice, expectedPrice);
                    }
                }
            }
        }
        String msg = "API LISTING FEE PTY ALL REGION/pricer internal & private: \n" +
                "";
        writeResultToFileText(msg);
        Assert.assertTrue(isPassed, msg + failedMsg);
    }

    public static void verifyListingFeePrice_Pricer_SellAdShop_PTY_AllRegions(String cateIDs, String token, String accountID) {
        String subCateId = "";
        boolean isPassed = true;
        //Get all subcates which are applied for all regions
        String[] subCateAppliedAllRegion = cateIDs.replace(" ", "").trim().split(",");
        subCateIdList = new ArrayList<>();
        for (int i = 0; i < subCateAppliedAllRegion.length - 1; i++) {
            subCateIdList.add(subCateAppliedAllRegion[i]);
        }

        //PTY:
        regionList = new ArrayList<>();
        regionList.add("2010");     //Bà Rịa Vũng Tàu
        regionList.add("2011");     //Bình Dương
        regionList.add("2012");     //Bình Phước
        regionList.add("2013");     //Đồng Nai
        regionList.add("2014");     //Tây Ninh
        regionList.add("13000");     //HCM

        //Check price of each subcate
        for (int i = 0; i < subCateIdList.size(); i++) {
            subCateId = subCateIdList.get(i);

            for (String regionId : regionList) {
                if (token.isEmpty())
                    response = get(String.format(gatewayPricer_ListingFee_Shop_PricerInternal, accountID, regionId, subCateId));
                else
                    response = get(token, String.format(gatewayPricer_ListingFee_Shop_PricerPrivate, accountID, regionId, subCateId));

                try {
                    verifyStatusCode200("API Listing Fee /pricer of cate[" + subCateId + "] dead");
                } catch (AssertionError a) {
                    isPassed = false;
                    if (getResponseData(response, "$.message").contains("Không tìm thấy giá dịch vụ")) {
                        failedMsg += String.format("Cate[%s] Region[%s]: API[Không tìm thấy khai báo giá dịch vụ]]\n" +
                                        ""
                                , subCateId, regionId);
                    } else {
                        failedMsg += String.format("Cate[%s] Region[%s]: API[ERROR]]\n" +
                                        ""
                                , subCateId, regionId);
                    }
                    continue;
                }
                String actionType = "$.action_type";


                //Each subcate -> check 3 types of price: vnd, dongtot, promotion
                for (String priceType : priceTypes) {
                    switch (priceType.toLowerCase()) {
                        case "vnd":
                            query = "$.price.vnd";
                            break;
                        case "dongtot":
                        case "credit":
                            query = "$.price.credit";
                            break;
                        case "promotion":
                            query = "$.price.promotion";
                            break;
                    }

                    //Check if Sell ad of cate is free -> skip
                    actualPrice = getResponseData(query);
                    try {
                        Assert.assertNotNull(actualPrice);
                    } catch (AssertionError a) {
                        if (isPassed)
                            isPassed = false;
                        failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[Chưa khai báo giá]\n" +
                                        ""
                                , subCateId, regionId, priceType);
                        continue;
                    }

                    expectedPrice = getPriceCSVByCate_ListingFee_SellAd_Pricer(subCateId, regionId, priceType, actionType);
                    checkExpectedPriceEmpty();     //If expectedPrice is empty -> return 0
                    try {
                        Assert.assertNotNull(expectedPrice);
                    } catch (AssertionError ex) {
                        if (isPassed)
                            isPassed = false;

                        if (isNullActualPrice()) //API null, CSV not found -> price type is NOT AVAILABLE
                            failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                            ""
                                    , subCateId, regionId, priceType, actualPrice);
                        continue;
                    }

                    //Compare price
                    try {
                        Assert.assertEquals(actualPrice, expectedPrice);
                    } catch (AssertionError error) {
                        if (isPassed)
                            isPassed = false;
                        failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[%s]\n" +
                                        ""
                                , subCateId, regionId, priceType, actualPrice, expectedPrice);
                    }
                }
            }
        }
        String msg = "API LISTING FEE PTY SHOP SELL AD ALL REGION/pricer Shop internal & private: \n" +
                "";
        writeResultToFileText(msg);
        Assert.assertTrue(isPassed, msg + failedMsg);
    }

    public static void verifyListingFeePrice_Pricer_RentAd_PTY_13000(String token, String accountID) {
        String subCateId = "";
        boolean isPassed = true;

        //PTY:
        String regionId = "13000";
        subCateIdList = getAllSubcateId(CATEID_PTY);
        //Check price of each subcate
        for (int i = 0; i < subCateIdList.size(); i++) {
            subCateId = subCateIdList.get(i);

            if (token.isEmpty())
                response = get(String.format(gatewayPricer_ListingFee4Rent_Private_PricerInternal, accountID, regionId, subCateId));
            else
                response = get(token, String.format(gatewayPricer_ListingFee4Rent_Private_PricerPrivate, accountID, regionId, subCateId));

            verifyStatusCode200("API Listing Fee /pricer of cate[" + subCateId + "] dead");
            String actionType = "$.action_type";

            //Each subcate -> check 3 types of price: vnd, dongtot, promotion
            for (String priceType : priceTypes) {
                switch (priceType.toLowerCase()) {
                    case "vnd":
                        query = "$.price.vnd";
                        break;
                    case "dongtot":
                    case "credit":
                        query = "$.price.credit";
                        break;
                    case "promotion":
                        query = "$.price.promotion";
                        break;
                }

                //Check if Sell ad of cate is free -> skip
                actualPrice = getResponseData(query);
                try {
                    Assert.assertNotNull(actualPrice);
                } catch (AssertionError a) {
                    if (isPassed)
                        isPassed = false;
                    failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[Chưa khai báo giá]\n" +
                                    ""
                            , subCateId, regionId, priceType);
                    continue;
                }

                expectedPrice = getPriceCSVByCate_ListingFee_RentAd_Pricer(subCateId, regionId, priceType, actionType);
                checkExpectedPriceEmpty();     //If expectedPrice is empty -> return 0

                try {
                    Assert.assertNotNull(expectedPrice);
                } catch (AssertionError ex) {
                    if (isPassed)
                        isPassed = false;

                    if (isNullActualPrice()) //API null, CSV not found -> price type is NOT AVAILABLE
                        failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                        ""
                                , subCateId, regionId, priceType, actualPrice);
                    continue;
                }

                //Compare price
                try {
                    Assert.assertEquals(actualPrice, expectedPrice);
                } catch (AssertionError error) {
                    if (isPassed)
                        isPassed = false;
                    failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[%s]\n" +
                                    ""
                            , subCateId, regionId, priceType, actualPrice, expectedPrice);
                }
            }
        }
        String msg = "API LISTING FEE PTY RENT 13000/pricer internal & private: \n" +
                "";
        writeResultToFileText(msg);
        Assert.assertTrue(isPassed, msg + failedMsg);
    }

    public static void verifyListingFeePrice_Pricer_RentAdShop_PTY_13000(String token, String accountID) {
        String subCateId = "";
        boolean isPassed = true;

        //PTY:
        String regionId = "13000";
        subCateIdList = getAllSubcateId(CATEID_PTY);
        //Check price of each subcate
        for (int i = 0; i < subCateIdList.size(); i++) {
            subCateId = subCateIdList.get(i);

            if (token.isEmpty())
                response = get(String.format(gatewayPricer_ListingFee4Rent_Shop_PricerInternal, accountID, regionId, subCateId));
            else
                response = get(token, String.format(gatewayPricer_ListingFee4Rent_Shop_PricerPrivate, accountID, regionId, subCateId));

            verifyStatusCode200("API Listing Fee /pricer of cate[" + subCateId + "] dead");
            String actionType = getResponseData("$.action_type");

            //Each subcate -> check 3 types of price: vnd, dongtot, promotion
            for (String priceType : priceTypes) {
                switch (priceType.toLowerCase()) {
                    case "vnd":
                        query = "$.price.vnd";
                        break;
                    case "dongtot":
                    case "credit":
                        query = "$.price.credit";
                        break;
                    case "promotion":
                        query = "$.price.promotion";
                        break;
                }

                //Check if Sell ad of cate is free -> skip
                actualPrice = getResponseData(query);

                expectedPrice = getPriceCSVByCate_ListingFee_RentAd_Pricer(subCateId, regionId, priceType, actionType);
                checkExpectedPriceEmpty();     //If expectedPrice is empty -> return 0

                //COMPARE PRICE
                if (expectedPrice == null) {      //ExpectedPrice doesn't exist
                    if (actualPrice == null) {
                        continue;
                    } else {
                        if (isPassed)
                            isPassed = false;

                        failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                        ""
                                , subCateId, regionId, priceType, actualPrice);
                    }
                } else {
                    if (actualPrice == null) {   //CSV != null, API == null
                        if (isPassed)
                            isPassed = false;
                        failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[Chưa khai báo giá] <> CSV[%s]\n" +
                                        ""
                                , subCateId, regionId, priceType, expectedPrice);
                    } else {   //CSV != null, API != null
                        try {
                            Assert.assertEquals(actualPrice, expectedPrice);
                        } catch (AssertionError error) {
                            if (isPassed)
                                isPassed = false;
                            failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[%s]\n" +
                                            ""
                                    , subCateId, regionId, priceType, actualPrice, expectedPrice);
                        }
                    }
                }//End if action
            }
        }
        String msg = "API LISTING FEE PTY SHOP RENT /pricer internal & private: \n" +
                "";
        writeResultToFileText(msg);
        Assert.assertTrue(isPassed, msg + failedMsg);
    }


    //VEH /pricer
    public static void verifyListingFeePrice_Pricer_SellAd_VEH_AllRegions(String cateIDs, String token, String accountID) {
        String subCateId = "";
        boolean isPassed = true;

        //Get all subcates which are applied for all regions
        String[] subCateAppliedAllRegion = cateIDs.replace(" ", "").trim().split(",");
        subCateIdList = new ArrayList<>();
        for (int i = 0; i < subCateAppliedAllRegion.length - 1; i++) {
            subCateIdList.add(subCateAppliedAllRegion[i]);
        }

        //Check price of each subcate
        for (int i = 0; i < subCateIdList.size(); i++) {
            subCateId = subCateIdList.get(i);

            for (String regionId : regionList) {
                if (token.isEmpty())
                    response = get(String.format(gatewayPricer_ListingFee_Private_PricerInternal, accountID, regionId, subCateId));
                else
                    response = get(token, String.format(gatewayPricer_ListingFee_Private_PricerPrivate, accountID, regionId, subCateId));

                verifyStatusCode200("API Listing Fee /pricer of cate[" + subCateId + "] dead");
                String actionType = "$.action_type";


                //Each subcate -> check 3 types of price: vnd, dongtot, promotion
                for (String priceType : priceTypes) {
                    switch (priceType.toLowerCase()) {
                        case "vnd":
                            query = "$.price.vnd";
                            break;
                        case "dongtot":
                        case "credit":
                            query = "$.price.credit";
                            break;
                        case "promotion":
                            query = "$.price.promotion";
                            break;
                    }

                    //Check if Sell ad of cate is free -> skip

                    actualPrice = getResponseData(query);
                    try {
                        Assert.assertNotNull(actualPrice);
                    } catch (AssertionError a) {
                        if (isPassed)
                            isPassed = false;
                        failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[Chưa khai báo giá]\n" +
                                        ""
                                , subCateId, regionId, priceType);
                        continue;
                    }

                    expectedPrice = getPriceCSVByCate_ListingFee_SellAd_Private_AllRegions_Pricer(subCateId, priceType, actionType);
                    checkExpectedPriceEmpty();     //If expectedPrice is empty -> return 0
                    try {
                        Assert.assertNotNull(expectedPrice);
                    } catch (AssertionError ex) {
                        if (isPassed)
                            isPassed = false;

                        if (isNullActualPrice()) //API null, CSV not found -> price type is NOT AVAILABLE
                            failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                            ""
                                    , subCateId, regionId, priceType, actualPrice);
                        continue;
                    }

                    //Compare price
                    try {
                        Assert.assertEquals(actualPrice, expectedPrice);
                    } catch (AssertionError error) {
                        if (isPassed)
                            isPassed = false;
                        failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[%s]\n" +
                                        ""
                                , subCateId, regionId, priceType, actualPrice, expectedPrice);
                    }
                }
            }
        }
        String msg = "API LISTING FEE VEH SELL AD/pricer internal & private: \n" +
                "";
        writeResultToFileText(msg);
        Assert.assertTrue(isPassed, msg + failedMsg);
    }

    public static void verifyListingFeePrice_Pricer_SellAdShop_VEH_AllRegions(String cateIDs, String token, String accountID) {
        String subCateId = "";
        boolean isPassed = true;

        //Get all subcates which are applied for all regions
        String[] subCateAppliedAllRegion = cateIDs.replace(" ", "").trim().split(",");
        subCateIdList = new ArrayList<>();
        for (int i = 0; i < subCateAppliedAllRegion.length - 1; i++) {
            subCateIdList.add(subCateAppliedAllRegion[i]);
        }

        //Check price of each subcate
        for (int i = 0; i < subCateIdList.size(); i++) {
            subCateId = subCateIdList.get(i);

            for (String regionId : regionList) {
                if (token.isEmpty())
                    response = get(String.format(gatewayPricer_ListingFee_Shop_PricerInternal, accountID, regionId, subCateId));
                else
                    response = get(token, String.format(gatewayPricer_ListingFee_Shop_PricerPrivate, accountID, regionId, subCateId));

                verifyStatusCode200("API Listing Fee /pricer of cate[" + subCateId + "] dead");
                String actionType = "$.action_type";


                //Each subcate -> check 3 types of price: vnd, dongtot, promotion
                for (String priceType : priceTypes) {
                    switch (priceType.toLowerCase()) {
                        case "vnd":
                            query = "$.price.vnd";
                            break;
                        case "dongtot":
                        case "credit":
                            query = "$.price.credit";
                            break;
                        case "promotion":
                            query = "$.price.promotion";
                            break;
                    }


                    actualPrice = getResponseData(query);
                    try {
                        Assert.assertNotNull(actualPrice);
                    } catch (AssertionError a) {
                        if (isPassed)
                            isPassed = false;
                        failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[Chưa khai báo giá]\n" +
                                        ""
                                , subCateId, regionId, priceType);
                        continue;
                    }

                    expectedPrice = getPriceCSVByCate_ListingFee_SellAd_Private_AllRegions_Pricer(subCateId, priceType, actionType);
                    checkExpectedPriceEmpty();     //If expectedPrice is empty -> return 0

                    try {
                        Assert.assertNotNull(expectedPrice);
                    } catch (AssertionError ex) {
                        if (isPassed)
                            isPassed = false;

                        if (isNullActualPrice()) //API null, CSV not found -> price type is NOT AVAILABLE
                            failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                            ""
                                    , subCateId, regionId, priceType, actualPrice);
                        continue;
                    }

                    //Compare price
                    try {
                        Assert.assertEquals(actualPrice, expectedPrice);
                    } catch (AssertionError error) {
                        if (isPassed)
                            isPassed = false;
                        failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[%s]\n" +
                                        ""
                                , subCateId, regionId, priceType, actualPrice, expectedPrice);
                    }
                }
            }
        }
        String msg = "API LISTING FEE VEH SHOP SELL AD/pricer internal & private: \n" +
                "";
        writeResultToFileText(msg);
        Assert.assertTrue(isPassed, msg + failedMsg);
    }

    //ELT /pricer
    public static void verifyListingFeePrice_Pricer_SellAd_ELT_AllRegions(String cateIDs, String token, String accountID) {
        String subCateId = "";
        boolean isPassed = true;

        //Get all subcates which are applied for all regions
        String[] subCateAppliedAllRegion = cateIDs.replace(" ", "").trim().split(",");
        subCateIdList = new ArrayList<>();
        for (int i = 0; i < subCateAppliedAllRegion.length - 1; i++) {
            subCateIdList.add(subCateAppliedAllRegion[i]);
        }

        //Check price of each subcate
        for (int i = 0; i < subCateIdList.size(); i++) {
            subCateId = subCateIdList.get(i);

            for (String regionId : regionList) {
                if (token.isEmpty())
                    response = get(String.format(gatewayPricer_ListingFee_Private_PricerInternal, accountID, regionId, subCateId));
                else
                    response = get(token, String.format(gatewayPricer_ListingFee_Private_PricerPrivate, accountID, regionId, subCateId));

                verifyStatusCode200("API Listing Fee /pricer of cate[" + subCateId + "] dead");
                String actionType = "$.action_type";


                //Each subcate -> check 3 types of price: vnd, dongtot, promotion
                for (String priceType : priceTypes) {
                    switch (priceType.toLowerCase()) {
                        case "vnd":
                            query = "$.price.vnd";
                            break;
                        case "dongtot":
                        case "credit":
                            query = "$.price.credit";
                            break;
                        case "promotion":
                            query = "$.price.promotion";
                            break;
                    }

                    //Check if Sell ad of cate is free -> skip
                    actualPrice = getResponseData(query);
                    try {
                        Assert.assertNotNull(actualPrice);
                    } catch (AssertionError a) {
                        if (isPassed)
                            isPassed = false;
                        failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[Chưa khai báo giá]\n" +
                                        ""
                                , subCateId, regionId, priceType);
                        continue;
                    }

                    expectedPrice = getPriceCSVByCate_ListingFee_SellAd_Private_AllRegions_Pricer(subCateId, priceType, actionType);
                    checkExpectedPriceEmpty();     //If expectedPrice is empty -> return 0

                    try {
                        Assert.assertNotNull(expectedPrice);
                    } catch (AssertionError ex) {
                        if (isPassed)
                            isPassed = false;

                        if (isNullActualPrice()) //API null, CSV not found -> price type is NOT AVAILABLE
                            failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                            ""
                                    , subCateId, regionId, priceType, actualPrice);
                        continue;
                    }

                    //Compare price
                    try {
                        Assert.assertEquals(actualPrice, expectedPrice);
                    } catch (AssertionError error) {
                        if (isPassed)
                            isPassed = false;
                        failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[%s]\n" +
                                        ""
                                , subCateId, regionId, priceType, actualPrice, expectedPrice);
                    }
                }
            }
        }
        String msg = "API LISTING FEE ELT SELL AD /pricer internal & private: \n" +
                "";
        writeResultToFileText(msg);
        Assert.assertTrue(isPassed, msg + failedMsg);
    }

    public static void verifyListingFeePrice_Pricer_SellAdShop_ELT_AllRegions(String cateIDs, String token, String accountID) {
        String subCateId = "";
        boolean isPassed = true;

        //Get all subcates which are applied for all regions
        String[] subCateAppliedAllRegion = cateIDs.replace(" ", "").trim().split(",");
        subCateIdList = new ArrayList<>();
        for (int i = 0; i < subCateAppliedAllRegion.length - 1; i++) {
            subCateIdList.add(subCateAppliedAllRegion[i]);
        }

        //Check price of each subcate
        for (int i = 0; i < subCateIdList.size(); i++) {
            subCateId = subCateIdList.get(i);

            for (String regionId : regionList) {
                if (token.isEmpty())
                    response = get(String.format(gatewayPricer_ListingFee_Shop_PricerInternal, accountID, regionId, subCateId));
                else
                    response = get(token, String.format(gatewayPricer_ListingFee_Shop_PricerPrivate, accountID, regionId, subCateId));

                verifyStatusCode200("API Listing Fee /pricer of cate[" + subCateId + "] dead");
                String actionType = "$.action_type";


                //Each subcate -> check 3 types of price: vnd, dongtot, promotion
                for (String priceType : priceTypes) {
                    switch (priceType.toLowerCase()) {
                        case "vnd":
                            query = "$.price.vnd";
                            break;
                        case "dongtot":
                        case "credit":
                            query = "$.price.credit";
                            break;
                        case "promotion":
                            query = "$.price.promotion";
                            break;
                    }

                    //Check if Sell ad of cate is free -> skip
                    actualPrice = getResponseData(query);
                    try {
                        Assert.assertNotNull(actualPrice);
                    } catch (AssertionError a) {
                        if (isPassed)
                            isPassed = false;
                        failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[Chưa khai báo giá]\n" +
                                        ""
                                , subCateId, regionId, priceType);
                        continue;
                    }

                    expectedPrice = getPriceCSVByCate_ListingFee_SellAd_Private_AllRegions_Pricer(subCateId, priceType, actionType);
                    checkExpectedPriceEmpty();     //If expectedPrice is empty -> return 0

                    try {
                        Assert.assertNotNull(expectedPrice);
                    } catch (AssertionError ex) {
                        if (isPassed)
                            isPassed = false;

                        if (isNullActualPrice()) //API null, CSV not found -> price type is NOT AVAILABLE
                            failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                            ""
                                    , subCateId, regionId, priceType, actualPrice);
                        continue;
                    }

                    //Compare price
                    try {
                        Assert.assertEquals(actualPrice, expectedPrice);
                    } catch (AssertionError error) {
                        if (isPassed)
                            isPassed = false;
                        failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[%s]\n" +
                                        ""
                                , subCateId, regionId, priceType, actualPrice, expectedPrice);
                    }
                }
            }
        }
        String msg = "API LISTING FEE ELT SHOP SELL AD/pricer internal & private: \n" +
                "";
        writeResultToFileText(msg);
        Assert.assertTrue(isPassed, msg + failedMsg);
    }

    //------------ LISTING FEE GETALL_LISTING------------
    public static void verifyListingFeePrice_PricerGetAllListing_SellAd_AllRegions_PTY(String cateIDs) {
        String subCateId = "";
        boolean isPassed = true;

        //Get all subcates which are applied for all regions
        String[] subCateAppliedAllRegion = cateIDs.replace(" ", "").trim().split(",");
        subCateIdList = new ArrayList<>();
        for (int i = 0; i < subCateAppliedAllRegion.length - 1; i++) {
            subCateIdList.add(subCateAppliedAllRegion[i]);
        }


        //set region for PTY
        regionList = new ArrayList<>();
        regionList.add("2010");     //Bà Rịa Vũng Tàu
        regionList.add("2011");     //Bình Dương
        regionList.add("2012");     //Bình Phước
        regionList.add("2013");     //Đồng Nai
        regionList.add("2014");     //Tây Ninh
        regionList.add("13000");     //HCM
        response = get(gatewayPricer_ListingFee_PTY_PricerGetAllListing_Internal);
        verifyStatusCode200("API Listing Fee /pricer/get-all/listing-fee is dead");

        //Check price of each subcate
        for (int i = 0; i < subCateIdList.size(); i++) {
            subCateId = subCateIdList.get(i);
            for (String regionID : regionList) {
                String actionType =
                        getResponseData(
                                "$.[?(@.category_id == " + subCateId + " " +
                                        "&& @.ad_type == 's' " +
                                        "&& @.region_id == " + regionID + " " +
                                        "&& @.action_type == 'insertad')]" +
                                        ".action_type");

                if (actionType == null) {
                    actionType =
                            getResponseData(
                                    "$.[?(@.category_id == " + subCateId + " " +
                                            "&& @.ad_type == 's' " +
                                            "&& @.region_id == " + regionID + " " +
                                            "&& @.action_type == 'insert_ad')]" +
                                            ".action_type");
                }

                //Each subcate -> check 3 types of price: vnd, dongtot, promotion
                for (String priceType : priceTypes) {
                    query = "$.[?(@.category_id == " + subCateId + " " +
                            "&& @.ad_type == 's' " +
                            "&& @.region_id == " + regionID + " " +
                            "&& @.action_type == '" + actionType + "')].price." + priceType;

                    //Check if Sell ad of cate is free -> skip
                    actualPrice = getResponseData(query);
                    if (actualPrice == null) {
                        query = query.replace(actionType, changeActionType(actionType));
                        actualPrice = getResponseData(query);
                    }

                    expectedPrice = getPriceCSVByCate_ListingFee_SellAd_Pricer(subCateId, regionID, priceType, actionType);
                    checkExpectedPriceEmpty();     //If expectedPrice is empty -> return 0

                    //Get price insertad -> null, then get insert_ad
                    if (expectedPrice == null) {
                        String actionTypeCSV = changeActionType(actionType);
                        expectedPrice = getPriceCSVByCate_ListingFee_SellAd_Pricer(subCateId, regionID, priceType, actionTypeCSV);
                        checkExpectedPriceEmpty(priceType);
                    }


                    //COMPARE PRICE
                    if (expectedPrice == null) {      //ExpectedPrice doesn't exist
                        if (actualPrice == null) {
                            continue;
                        } else {
                            if (isPassed)
                                isPassed = false;

                            if (isNullActualPrice()) //API null, CSV not found -> price type is NOT AVAILABLE
                                failedMsg += String.format("Cate[%s] Region[%s] [%s] [%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                                ""
                                        , subCateId, regionID, actionType, priceType, actualPrice);
                        }
                    } else {
                        if (actualPrice == null) {   //CSV != null, API == null
                            if (isPassed)
                                isPassed = false;
                            failedMsg += String.format("Cate[%s] Region[%s] [%s] [%s]: API[Chưa khai báo giá] <> CSV[%s]\n" +
                                            ""
                                    , subCateId, regionID, actionType, priceType, expectedPrice);
                        } else {   //CSV != null, API != null
                            try {
                                Assert.assertEquals(actualPrice, expectedPrice);
                            } catch (AssertionError error) {
                                if (isPassed)
                                    isPassed = false;
                                failedMsg += String.format("Cate[%s] Region[%s] [%s] [%s]: API[%s] <> CSV[%s]\n" +
                                                ""
                                        , subCateId, regionID, actionType, priceType, actualPrice, expectedPrice);
                            }
                        }
                    }//End if action
                }
            }
        }
        String msg = "API LISTING FEE PTY /v1/internal/pricer/get-all/listing-fee-pty: \n" +
                "" +
                "Cate\tRegion\tAction\tPriceType\tPriceAPI\tPriceCSV \n" +
                "";
        writeResultToFileText(msg);
        Assert.assertTrue(isPassed, msg + failedMsg);
    }

    public static void verifyListingFeePrice_PricerGetAllListing_SellAdShop_AllRegions_PTY(String cateIDs) {
        String subCateId = "";
        boolean isPassed = true;

        //Get all subcates which are applied for all regions
        String[] subCateAppliedAllRegion = cateIDs.replace(" ", "").trim().split(",");
        subCateIdList = new ArrayList<>();
        for (int i = 0; i < subCateAppliedAllRegion.length - 1; i++) {
            subCateIdList.add(subCateAppliedAllRegion[i]);
        }

        //set region for PTY
        regionList = new ArrayList<>();
        regionList.add("2010");     //Bà Rịa Vũng Tàu
        regionList.add("2011");     //Bình Dương
        regionList.add("2012");     //Bình Phước
        regionList.add("2013");     //Đồng Nai
        regionList.add("2014");     //Tây Ninh
        regionList.add("13000");     //HCM
        response = get(gatewayPricer_ListingFee_PTY_PricerGetAllListing_Internal);
        verifyStatusCode200("API Listing Fee /pricer/get-all/listing-fee is dead");

        //Check price of each subcate
        for (int i = 0; i < subCateIdList.size(); i++) {
            subCateId = subCateIdList.get(i);
            for (String regionID : regionList) {
                String actionType =
                        getResponseData(
                                "$.[?(@.category_id == " + subCateId + " " +
                                        "&& @.ad_type == 's' " +
                                        "&& @.region_id == " + regionID + " " +
                                        "&& @.action_type == 'shop_to_chotot')]" +
                                        ".action_type");

                //Each subcate -> check 3 types of price: vnd, dongtot, promotion
                for (String priceType : priceTypes) {
                    query = "$.[?(@.category_id == " + subCateId + " " +
                            "&& @.ad_type == 's' " +
                            "&& @.region_id == " + regionID + " " +
                            "&& @.action_type == '" + actionType + "')].price." + priceType;

                    //Check if Sell ad of cate is free -> skip
                    actualPrice = getResponseData(query);
                    expectedPrice = getPriceCSVByCate_ListingFee_SellAd_Pricer(subCateId, regionID, priceType, actionType);
                    checkExpectedPriceEmpty();     //If expectedPrice is empty -> return 0

                    //COMPARE PRICE
                    if (expectedPrice == null) {      //ExpectedPrice doesn't exist
                        if (actualPrice == null) {
                            continue;
                        } else {
                            if (isPassed)
                                isPassed = false;

                            if (isNullActualPrice()) //API null, CSV not found -> price type is NOT AVAILABLE
                                failedMsg += String.format("Cate[%s] Region[%s] [%s] [%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                                ""
                                        , subCateId, regionID, actionType, priceType, actualPrice);
                        }
                    } else {
                        if (actualPrice == null) {   //CSV != null, API == null
                            if (isPassed)
                                isPassed = false;
                            failedMsg += String.format("Cate[%s] Region[%s] [%s] [%s]: API[Chưa khai báo giá] <> CSV[%s]\n" +
                                            ""
                                    , subCateId, regionID, actionType, priceType, expectedPrice);
                        } else {   //CSV != null, API != null
                            try {
                                Assert.assertEquals(actualPrice, expectedPrice);
                            } catch (AssertionError error) {
                                if (isPassed)
                                    isPassed = false;
                                failedMsg += String.format("Cate[%s] Region[%s] [%s] [%s]: API[%s] <> CSV[%s]\n" +
                                                ""
                                        , subCateId, regionID, actionType, priceType, actualPrice, expectedPrice);
                            }
                        }
                    }//End if action
                }
            }
        }
        String msg = "API LISTING FEE PTY SHOP_TO_CHOTOT /v1/internal/pricer/get-all/listing-fee-pty: \n" +
                "" +
                "Cate\tRegion\tAction\tPriceType\tPriceAPI\tPriceCSV \n" +
                "";
        writeResultToFileText(msg);
        Assert.assertTrue(isPassed, msg + failedMsg);
    }

    public static void verifyListingFeePrice_PricerGetAllListing_RentAd_Region13000_PTY() {
        String subCateId = "";
        boolean isPassed = true;

        //Get all subcates which are applied for 13000
        subCateIdList = getAllSubcateId(CATEID_PTY);
        String regionID = "13000";

        response = get(gatewayPricer_ListingFee_PTY_PricerGetAllListing_Internal);
        verifyStatusCode200("API Listing Fee /pricer/get-all/listing-fee is dead");

        //Check price of each subcate
        for (int i = 0; i < subCateIdList.size(); i++) {
            subCateId = subCateIdList.get(i);
            String actionType =
                    getResponseData(
                            "$.[?(@.category_id == " + subCateId + " " +
                                    "&& @.ad_type == 'u' " +
                                    "&& @.region_id == " + regionID + " " +
                                    "&& @.action_type == 'insertad')]" +
                                    ".action_type");

            if (actionType == null) {
                actionType =
                        getResponseData(
                                "$.[?(@.category_id == " + subCateId + " " +
                                        "&& @.ad_type == 'u' " +
                                        "&& @.region_id == " + regionID + " " +
                                        "&& @.action_type == 'insert_ad')]" +
                                        ".action_type");
            }

            //Each subcate -> check 3 types of price: vnd, dongtot, promotion
            for (String priceType : priceTypes) {
                query = "$.[?(@.category_id == " + subCateId + " " +
                        "&& @.ad_type == 'u' " +
                        "&& @.region_id == " + regionID + " " +
                        "&& @.action_type == '" + actionType + "')].price." + priceType;

                //Check if Sell ad of cate is free -> skip
                actualPrice = getResponseData(query);
                //Check if Sell ad of cate is free -> skip
                actualPrice = getResponseData(query);
                if (actualPrice == null) {
                    query = query.replace(actionType, changeActionType(actionType));
                    actualPrice = getResponseData(query);
                }

                expectedPrice = getPriceCSVByCate_ListingFee_RentAd_Pricer(subCateId, regionID, priceType, actionType);
                checkExpectedPriceEmpty();
                //Get price insertad -> null, then get insert_ad
                if (expectedPrice == null) {
                    String actionTypeCSV = changeActionType(actionType);
                    expectedPrice = getPriceCSVByCate_ListingFee_RentAd_Pricer(subCateId, regionID, priceType, actionTypeCSV);
                    checkExpectedPriceEmpty(priceType);
                }

                //COMPARE PRICE
                if (expectedPrice == null) {      //ExpectedPrice doesn't exist
                    if (actualPrice == null) {
                        continue;
                    } else {
                        if (isPassed)
                            isPassed = false;

                        failedMsg += String.format("Cate[%s] Region[%s] [%s] [%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                        ""
                                , subCateId, regionID, actionType, priceType, actualPrice);
                    }
                } else {
                    if (actualPrice == null) {   //CSV != null, API == null
                        if (isPassed)
                            isPassed = false;
                        failedMsg += String.format("Cate[%s] Region[%s] [%s] [%s]: API[Chưa khai báo giá] <> CSV[%s]\n" +
                                        ""
                                , subCateId, regionID, actionType, priceType, expectedPrice);
                    } else {   //CSV != null, API != null
                        try {
                            Assert.assertEquals(actualPrice, expectedPrice);
                        } catch (AssertionError error) {
                            if (isPassed)
                                isPassed = false;
                            failedMsg += String.format("Cate[%s] Region[%s] [%s] [%s]: API[%s] <> CSV[%s]\n" +
                                            ""
                                    , subCateId, regionID, actionType, priceType, actualPrice, expectedPrice);
                        }
                    }
                }
            }
        }
        String msg = "API LISTING FEE RENT PTY REGION 13000 /pricer/get-all/listing-fee-pty internal: \n" +
                "" +
                "Cate\tRegion\tAction\tPriceType\tPriceAPI\tPriceCSV \n" +
                "";
        writeResultToFileText(msg);
        Assert.assertTrue(isPassed, msg + failedMsg);
    }

    public static void verifyListingFeePrice_PricerGetAllListing_RentAdShop_Region13000_PTY() {
        String subCateId = "";
        boolean isPassed = true;

        //Get all subcates which are applied for 13000
        subCateIdList = getAllSubcateId(CATEID_PTY);
        String regionID = "13000";

        response = get(gatewayPricer_ListingFee_PTY_PricerGetAllListing_Internal);
        verifyStatusCode200("API Listing Fee /pricer/get-all/listing-fee is dead");

        //Check price of each subcate
        for (int i = 0; i < subCateIdList.size(); i++) {
            subCateId = subCateIdList.get(i);
            String actionType =
                    getResponseData(
                            "$.[?(@.category_id == " + subCateId + " " +
                                    "&& @.ad_type == 'u' " +
                                    "&& @.region_id == " + regionID + " " +
                                    "&& @.action_type == 'shop_to_chotot')]" +
                                    ".action_type");


            //Each subcate -> check 3 types of price: vnd, dongtot, promotion
            for (String priceType : priceTypes) {
                query = "$.[?(@.category_id == " + subCateId + " " +
                        "&& @.ad_type == 'u' " +
                        "&& @.region_id == " + regionID + " " +
                        "&& @.action_type == '" + actionType + "')].price." + priceType;

                //Check if Sell ad of cate is free -> skip
                actualPrice = getResponseData(query);

                expectedPrice = getPriceCSVByCate_ListingFee_RentAd_Pricer(subCateId, regionID, priceType, actionType);
                checkExpectedPriceEmpty();

                //COMPARE PRICE
                if (expectedPrice == null) {      //ExpectedPrice doesn't exist
                    if (actualPrice == null) {
                        continue;
                    } else {
                        if (isPassed)
                            isPassed = false;

                        failedMsg += String.format("Cate[%s] Region[%s] [%s] [%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                        ""
                                , subCateId, regionID, actionType, priceType, actualPrice);
                    }
                } else {
                    if (actualPrice == null) {   //CSV != null, API == null
                        if (isPassed)
                            isPassed = false;
                        failedMsg += String.format("Cate[%s] Region[%s] [%s] [%s]: API[Chưa khai báo giá] <> CSV[%s]\n" +
                                        ""
                                , subCateId, regionID, actionType, priceType, expectedPrice);
                    } else {   //CSV != null, API != null
                        try {
                            Assert.assertEquals(actualPrice, expectedPrice);
                        } catch (AssertionError error) {
                            if (isPassed)
                                isPassed = false;
                            failedMsg += String.format("Cate[%s] Region[%s] [%s] [%s]: API[%s] <> CSV[%s]\n" +
                                            ""
                                    , subCateId, regionID, actionType, priceType, actualPrice, expectedPrice);
                        }
                    }
                }
            }
        }
        String msg = "API LISTING FEE RENT SHOP PTY REGION 13000 /pricer/get-all/listing-fee-pty internal: \n" +
                "" +
                "Cate\tRegion\tAction\tPriceType\tPriceAPI\tPriceCSV \n" +
                "";
        writeResultToFileText(msg);
        Assert.assertTrue(isPassed, msg + failedMsg);
    }


    public static void verifyListingFeePrice_PricerGetAllListing_SellAd_AllRegions(String cateIDs) {
        String subCateId = "";
        boolean isPassed = true;

        //Get all subcates which are applied for all regions
        String[] subCateAppliedAllRegion = cateIDs.replace(" ", "").trim().split(",");
        subCateIdList = new ArrayList<>();
        for (int i = 0; i < subCateAppliedAllRegion.length - 1; i++) {
            subCateIdList.add(subCateAppliedAllRegion[i]);
        }


        response = get(gatewayPricer_ListingFee_PricerGetAllListing_Internal);
        verifyStatusCode200("API Listing Fee /pricer/get-all/listing-fee is dead");

        //Check price of each subcate
        for (int i = 0; i < subCateIdList.size(); i++) {
            subCateId = subCateIdList.get(i);
            String actionType =
                    getResponseData(response,
                            "$.[?(@.category_id == " + subCateId + " " +
                                    "&& @.ad_type == 's' " +
                                    "&& (@.action_type == 'insert_ad' || @.action_type == 'insertad'))]" +
                                    ".action_type");

            //Each subcate -> check 3 types of price: vnd, dongtot, promotion
            for (String priceType : priceTypes) {
                switch (priceType.toLowerCase()) {
                    case "vnd":
                        query = "$.[?(@.category_id == " + subCateId + " " +
                                "&& @.ad_type == 's' " +
                                "&& (@.action_type == 'insert_ad' || @.action_type == 'insertad'))].price.vnd";
                        break;
                    case "dongtot":
                    case "credit":
                        query = "$.[?(@.category_id == " + subCateId + " " +
                                "&& @.ad_type == 's' " +
                                "&& (@.action_type == 'insert_ad' || @.action_type == 'insertad'))].price.credit";
                        break;
                    case "promotion":
                        query = "$.[?(@.category_id == " + subCateId + " " +
                                "&& @.ad_type == 's' " +
                                "&& (@.action_type == 'insert_ad' || @.action_type == 'insertad'))].price.promotion";
                        break;
                }

                //Check if Sell ad of cate is free -> skip
                actualPrice = getResponseData(query);
                try {
                    Assert.assertNotNull(actualPrice);
                } catch (AssertionError a) {
                    if (isPassed)
                        isPassed = false;
                    failedMsg += String.format("Cate[%s] [%s] [%s]: API[Chưa khai báo giá]\n" +
                                    ""
                            , subCateId, actionType, priceType);
                    continue;
                }

                expectedPrice = getPriceCSVByCate_ListingFee_SellAd_Private_AllRegions_Pricer(subCateId, priceType, actionType);
                checkExpectedPriceEmpty();
                try {
                    Assert.assertNotNull(expectedPrice);
                } catch (AssertionError ex) {
                    isPassed = false;

                    if (isNullActualPrice()) //API null, CSV not found -> price type is NOT AVAILABLE
                        failedMsg += String.format("Cate[%s] [%s] [%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                        ""
                                , subCateId, actionType, priceType, actualPrice);
                    continue;
                }

                //Compare price
                try {
                    Assert.assertEquals(actualPrice, expectedPrice);
                } catch (AssertionError error) {
                    if (isPassed)
                        isPassed = false;
                    failedMsg += String.format("Cate[%s] [%s] [%s]: API[%s] <> CSV[%s]\n" +
                                    ""
                            , subCateId, actionType, priceType, actualPrice, expectedPrice);
                }
            }
        }
        String msg = "API LISTING FEE PTY VEH ELT /pricer/get-all/listing-fee internal: \n" +
                "" +
                "Cate\tAction\tPriceType\tPriceAPI\tPriceCSV \n" +
                "";
        writeResultToFileText(msg);
        Assert.assertTrue(isPassed, msg + failedMsg);
    }

    public static void verifyListingFeePrice_PricerGetAllListing_SellAdShop_AllRegions(String cateIDs) {
        String subCateId = "";
        boolean isPassed = true;

        //Get all subcates which are applied for all regions
        String[] subCateAppliedAllRegion = cateIDs.replace(" ", "").trim().split(",");
        subCateIdList = new ArrayList<>();
        for (int i = 0; i < subCateAppliedAllRegion.length - 1; i++) {
            subCateIdList.add(subCateAppliedAllRegion[i]);
        }

        response = get(gatewayPricer_ListingFee_PricerGetAllListing_Internal);
        verifyStatusCode200("API Listing Fee /pricer/get-all/listing-fee is dead");

        //Check price of each subcate
        for (int i = 0; i < subCateIdList.size(); i++) {
            subCateId = subCateIdList.get(i);
            String actionType =
                    getResponseData(response,
                            "$.[?(@.category_id == " + subCateId + " " +
                                    "&& @.ad_type == 's' " +
                                    "&& @.action_type == 'shop_to_chotot')]" +
                                    ".action_type");

            //Each subcate -> check 3 types of price: vnd, dongtot, promotion
            for (String priceType : priceTypes) {
                switch (priceType.toLowerCase()) {
                    case "vnd":
                        query = "$.[?(@.category_id == " + subCateId + " " +
                                "&& @.ad_type == 's' " +
                                "&& @.action_type == 'shop_to_chotot')].price.vnd";
                        break;
                    case "dongtot":
                    case "credit":
                        query = "$.[?(@.category_id == " + subCateId + " " +
                                "&& @.ad_type == 's' " +
                                "&& @.action_type == 'shop_to_chotot')].price.credit";
                        break;
                    case "promotion":
                        query = "$.[?(@.category_id == " + subCateId + " " +
                                "&& @.ad_type == 's' " +
                                "&& @.action_type == 'shop_to_chotot')].price.promotion";
                        break;
                }

                //Check if Sell ad of cate is free -> skip
                actualPrice = getResponseData(query);
                try {
                    Assert.assertNotNull(actualPrice);
                } catch (AssertionError a) {
                    if (isPassed)
                        isPassed = false;
                    failedMsg += String.format("Cate[%s] [%s] [%s]: API[Chưa khai báo giá]\n" +
                                    ""
                            , subCateId, actionType, priceType);
                    continue;
                }

                expectedPrice = getPriceCSVByCate_ListingFee_SellAd_Private_AllRegions_Pricer(subCateId, priceType, actionType);
                checkExpectedPriceEmpty();
                try {
                    Assert.assertNotNull(expectedPrice);
                } catch (AssertionError ex) {
                    if (isPassed)
                        isPassed = false;

                    if (isNullActualPrice()) //API null, CSV not found -> price type is NOT AVAILABLE
                        failedMsg += String.format("Cate[%s] [%s] [%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                        ""
                                , subCateId, priceType, actionType, actualPrice);
                    continue;
                }

                //Compare price
                try {
                    Assert.assertEquals(actualPrice, expectedPrice);
                } catch (AssertionError error) {
                    if (isPassed)
                        isPassed = false;
                    failedMsg += String.format("Cate[%s] [%s] [%s]: API[%s] <> CSV[%s]\n" +
                                    ""
                            , subCateId, priceType, actionType, actualPrice, expectedPrice);
                }
            }
        }
        String msg = "API LISTING FEE SELL AD SHOP ALL REGIONS/pricer/get-all internal: \n" +
                "";
        writeResultToFileText(msg);
        Assert.assertTrue(isPassed, msg + failedMsg);
    }


    //------------ LISTING FEE GETALL------------

    public static void verifyListingFeePrice_PricerGetAll_SellAd_AllRegions_PTY(String cateIDs) {
        String subCateId = "";
        boolean isPassed = true;

        //Get all subcates which are applied for all regions
        String[] subCateAppliedAllRegion = cateIDs.replace(" ", "").trim().split(",");
        subCateIdList = new ArrayList<>();
        for (int i = 0; i < subCateAppliedAllRegion.length - 1; i++) {
            subCateIdList.add(subCateAppliedAllRegion[i]);
        }

        //set region for PTY
        regionList = new ArrayList<>();
        regionList.add("2010");     //Bà Rịa Vũng Tàu
        regionList.add("2011");     //Bình Dương
        regionList.add("2012");     //Bình Phước
        regionList.add("2013");     //Đồng Nai
        regionList.add("2014");     //Tây Ninh
        regionList.add("13000");     //HCM


//        response = get(gatewayPricer_ListingFee_PricerGetAll_Public);
        response = get(gatewayPricer_ListingFee_PricerGetAll_Public.replace(".org", ".com"));
        verifyStatusCode200("API Listing Fee /pricer/get-all is dead");


        //Check price of each subcate
        for (int i = 0; i < subCateIdList.size(); i++) {
            subCateId = subCateIdList.get(i);
            for (String regionID : regionList) {
                String actionType =
                        getResponseData(response,
                                "$.insert_ads_with_regions[*][?(@.category_id == " + subCateId + " " +
                                        "&& @.ad_type == 's' " +
                                        "&& @.region_id == " + regionID + " " +
                                        "&& (@.action_type == 'insert_ad' || @.action_type == 'insertad'))]" +
                                        ".action_type");

                //Each subcate -> check 3 types of price: vnd, dongtot, promotion
                for (String priceType : priceTypes) {
                    switch (priceType.toLowerCase()) {
                        case "vnd":
                            query = "$.insert_ads_with_regions[*][?(@.category_id == " + subCateId + " " +
                                    "&& @.ad_type == 's' " +
                                    "&& @.region_id == " + regionID + " " +
                                    "&& (@.action_type == 'insert_ad' || @.action_type == 'insertad'))].price.vnd";
                            break;
                        case "dongtot":
                        case "credit":
                            query = "$.insert_ads_with_regions[*][?(@.category_id == " + subCateId + " " +
                                    "&& @.ad_type == 's' " +
                                    "&& @.region_id == " + regionID + " " +
                                    "&& (@.action_type == 'insert_ad' || @.action_type == 'insertad'))].price.credit";
                            break;
                        case "promotion":
                            query = "$.insert_ads_with_regions[*][?(@.category_id == " + subCateId + " " +
                                    "&& @.ad_type == 's' " +
                                    "&& @.region_id == " + regionID + " " +
                                    "&& (@.action_type == 'insert_ad' || @.action_type == 'insertad'))].price.promotion";
                            break;
                    }

                    //Check if Sell ad of cate is free -> skip
                    actualPrice = getResponseData(query);

                    expectedPrice = getPriceCSVByCate_ListingFee_SellAd_Pricer(subCateId, regionID, priceType, actionType);
                    checkExpectedPriceEmpty(priceType);

                    //Get price insertad -> null, then get insert_ad
                    if (expectedPrice == null) {
                        String actionTypeCSV = "";
                        if (actionType.equals("insert_ad")) {
                            actionTypeCSV = "insertad";
                        } else {
                            actionTypeCSV = "insert_ad";
                        }
                        expectedPrice = getPriceCSVByCate_ListingFee_SellAd_Pricer(subCateId, regionID, priceType, actionTypeCSV);
                        checkExpectedPriceEmpty(priceType);
                    }

                    if (expectedPrice == null) {      //ExpectedPrice doesn't exist
                        if (actualPrice == null) {
                            continue;
                        } else {
                            if (isPassed)
                                isPassed = false;

                            if (isNullActualPrice()) //API null, CSV not found -> price type is NOT AVAILABLE
                                failedMsg += String.format("Cate[%s] Region[%s] [%s] [%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                                ""
                                        , subCateId, regionID, actionType, priceType, actualPrice);
                        }
                    } else {
                        if (actualPrice == null) {   //CSV != null, API == null
                            if (isPassed)
                                isPassed = false;
                            failedMsg += String.format("Cate[%s] Region[%s] [%s] [%s]: API[Chưa khai báo giá]\n" +
                                            ""
                                    , subCateId, regionID, actionType, priceType);
                        } else {   //CSV != null, API != null
                            try {
                                Assert.assertEquals(actualPrice, expectedPrice);
                            } catch (AssertionError error) {
                                if (isPassed)
                                    isPassed = false;
                                failedMsg += String.format("Cate[%s] Region[%s] [%s] [%s]: API[%s] <> CSV[%s]\n" +
                                                ""
                                        , subCateId, regionID, actionType, priceType, actualPrice, expectedPrice);
                            }
                        }
                    }//End if action
                }
            }
        }
        String msg = "API LISTING FEE PTY SELL AD /pricer/get-all/listing-fee-pty internal: \n" +
                "" +
                "Cate\tRegion\tAction\tPriceType\tPriceAPI\tPriceCSV \n" +
                "";
        writeResultToFileText(msg);
        Assert.assertTrue(isPassed, msg + failedMsg);
    }

    @Deprecated
    private static void verifyListingFeePrice_PricerGetAll_SellAdShop_AllRegions_PTY(String cateIDs) {
        String subCateId = "";
        boolean isPassed = true;

        //Get all subcates which are applied for all regions
        String[] subCateAppliedAllRegion = cateIDs.replace(" ", "").trim().split(",");
        subCateIdList = new ArrayList<>();
        for (int i = 0; i < subCateAppliedAllRegion.length - 1; i++) {
            subCateIdList.add(subCateAppliedAllRegion[i]);
        }

        //set region for PTY
        regionList = new ArrayList<>();
        regionList.add("2010");     //Bà Rịa Vũng Tàu
        regionList.add("2011");     //Bình Dương
        regionList.add("2012");     //Bình Phước
        regionList.add("2013");     //Đồng Nai
        regionList.add("2014");     //Tây Ninh
        regionList.add("13000");     //HCM
        response = get(gatewayPricer_ListingFee_PricerGetAll_Public);
        verifyStatusCode200("API Listing Fee /pricer/get-all is dead");

        //Check price of each subcate
        for (int i = 0; i < subCateIdList.size(); i++) {
            subCateId = subCateIdList.get(i);
            for (String regionID : regionList) {
                String actionQuery = "$.insert_ads_with_regions[*][?(@.category_id == " + subCateId + " " +
                        "&& @.ad_type == 's' " +
                        "&& @.region_id == " + regionID + " " +
                        "&& @.action_type == 'shop_to_chotot')]" +
                        ".action_type";
                String actionType = getResponseData(actionQuery);
                if (actionType == null) {
                    System.out.println("DEBUG: " + actionQuery);
                }
                //Each subcate -> check 3 types of price: vnd, dongtot, promotion
                for (String priceType : priceTypes) {
                    switch (priceType.toLowerCase()) {
                        case "vnd":
                            query = "$.insert_ads_with_regions[*][?(@.category_id == " + subCateId + " " +
                                    "&& @.ad_type == 's' " +
                                    "&& @.region_id == " + regionID + " " +
                                    "&& @.action_type == 'shop_to_chotot')].price.vnd";
                            break;
                        case "dongtot":
                        case "credit":
                            query = "$.insert_ads_with_regions[*][?(@.category_id == " + subCateId + " " +
                                    "&& @.ad_type == 's' " +
                                    "&& @.region_id == " + regionID + " " +
                                    "&& @.action_type == 'shop_to_chotot')].price.credit";
                            break;
                        case "promotion":
                            query = "$.insert_ads_with_regions[*][?(@.category_id == " + subCateId + " " +
                                    "&& @.ad_type == 's' " +
                                    "&& @.region_id == " + regionID + " " +
                                    "&& @.action_type == 'shop_to_chotot')].price.promotion";
                            break;
                    }

                    //Check if Sell ad of cate is free -> skip
                    actualPrice = getResponseData(query);
                    expectedPrice = getPriceCSVByCate_ListingFee_SellAd_Pricer(subCateId, regionID, priceType, actionType);
                    checkExpectedPriceEmpty(priceType);

                    if (expectedPrice == null) {      //ExpectedPrice doesn't exist
                        if (actualPrice == null) {
                            continue;
                        } else {
                            if (isPassed)
                                isPassed = false;

                            if (isNullActualPrice()) //API null, CSV not found -> price type is NOT AVAILABLE
                                failedMsg += String.format("Cate[%s] Region[%s] [%s] [%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                                ""
                                        , subCateId, regionID, actionType, priceType, actualPrice);
                        }
                    } else {
                        if (actualPrice == null) {   //CSV != null, API == null
                            if (isPassed)
                                isPassed = false;
                            failedMsg += String.format("Cate[%s] Region[%s] [%s] [%s]: API[Chưa khai báo giá]\n" +
                                            ""
                                    , subCateId, regionID, actionType, priceType);
                        } else {   //CSV != null, API != null
                            try {
                                Assert.assertEquals(actualPrice, expectedPrice);
                            } catch (AssertionError error) {
                                if (isPassed)
                                    isPassed = false;
                                failedMsg += String.format("Cate[%s] Region[%s] [%s] [%s]: API[%s] <> CSV[%s]\n" +
                                                ""
                                        , subCateId, regionID, actionType, priceType, actualPrice, expectedPrice);
                            }
                        }
                    }//End if action
                }
            }
        }
        String msg = "API LISTING FEE PTY SHOP SELL/pricer/get-all/listing-fee-pty internal: \n" +
                "" +
                "Cate\tRegion\tAction\tPriceType\tPriceAPI\tPriceCSV \n" +
                "";
        writeResultToFileText(msg);
        Assert.assertTrue(isPassed, msg + failedMsg);
    }

    public static void verifyListingFeePrice_PricerGetAll_RentAd_Region13000_PTY() {
        String subCateId = "";
        boolean isPassed = true;

        //Get all subcates which are applied for 13000
        subCateIdList = getAllSubcateId(CATEID_PTY);
        String regionID = "13000";

        response = get(gatewayPricer_ListingFee_PricerGetAll_Public);
        verifyStatusCode200("API Listing Fee /pricer/get-all is dead");

        //Check price of each subcate
        for (int i = 0; i < subCateIdList.size(); i++) {
            subCateId = subCateIdList.get(i);
            String actionType =
                    getResponseData(response,
                            "$.insert_ads_with_regions[*][?(@.category_id == " + subCateId + " " +
                                    "&& @.ad_type == 'u' " +
                                    "&& @.region_id == " + regionID + " " +
                                    "&& (@.action_type == 'insert_ad' || @.action_type == 'insert_ad'))]" +
                                    ".action_type");

            //Each subcate -> check 3 types of price: vnd, dongtot, promotion
            for (String priceType : priceTypes) {
                switch (priceType.toLowerCase()) {
                    case "vnd":
                        query = "$.insert_ads_with_regions[*][?(@.category_id == " + subCateId + " " +
                                "&& @.ad_type == 'u' " +
                                "&& @.region_id == " + regionID + " " +
                                "&& (@.action_type == 'insert_ad' || @.action_type == 'insert_ad'))].price.vnd";
                        break;
                    case "dongtot":
                    case "credit":
                        query = "$.insert_ads_with_regions[*][?(@.category_id == " + subCateId + " " +
                                "&& @.ad_type == 'u' " +
                                "&& @.region_id == " + regionID + " " +
                                "&& (@.action_type == 'insert_ad' || @.action_type == 'insert_ad'))].price.credit";
                        break;
                    case "promotion":
                        query = "$.insert_ads_with_regions[*][?(@.category_id == " + subCateId + " " +
                                "&& @.ad_type == 'u' " +
                                "&& @.region_id == " + regionID + " " +
                                "&& (@.action_type == 'insert_ad' || @.action_type == 'insert_ad'))].price.promotion";
                        break;
                }

                //Check if Sell ad of cate is free -> skip
                actualPrice = getResponseData(query);
                expectedPrice = getPriceCSVByCate_ListingFee_RentAd_Pricer(subCateId, regionID, priceType, actionType);
                checkExpectedPriceEmpty(priceType);

                //Get price insertad -> null, then get insert_ad
                if (expectedPrice == null) {
                    String actionTypeCSV = "";
                    if (actionType.equals("insert_ad")) {
                        actionTypeCSV = "insertad";
                    } else {
                        actionTypeCSV = "insert_ad";
                    }
                    expectedPrice = getPriceCSVByCate_ListingFee_RentAd_Pricer(subCateId, regionID, priceType, actionTypeCSV);
                    checkExpectedPriceEmpty(priceType);
                }

                //If expectedPrice exists & actualPrice doesn't exist, then Chưa khai báo giá
                if (expectedPrice == null) {      //ExpectedPrice doesn't exist
                    if (actualPrice == null) {
                        continue;
                    } else {
                        if (isPassed)
                            isPassed = false;

                        if (isNullActualPrice()) //API null, CSV not found -> price type is NOT AVAILABLE
                            failedMsg += String.format("Cate[%s] Region[%s] [%s] [%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                            ""
                                    , subCateId, regionID, actionType, priceType, actualPrice);
                    }
                } else {
                    if (actualPrice == null) {   //CSV != null, API == null
                        if (isPassed)
                            isPassed = false;
                        failedMsg += String.format("Cate[%s] Region[%s] [%s] [%s]: API[Chưa khai báo giá]\n" +
                                        ""
                                , subCateId, regionID, actionType, priceType);
                    } else {   //CSV != null, API != null
                        try {
                            Assert.assertEquals(actualPrice, expectedPrice);
                        } catch (AssertionError error) {
                            if (isPassed)
                                isPassed = false;
                            failedMsg += String.format("Cate[%s] Region[%s] [%s] [%s]: API[%s] <> CSV[%s]\n" +
                                            ""
                                    , subCateId, regionID, actionType, priceType, actualPrice, expectedPrice);
                        }
                    }
                }
            }
        }
        String msg = "API LISTING FEE PTY RENT /pricer/get-all/listing-fee-pty internal: \n" +
                "" +
                "Cate\tRegion\tAction\tPriceType\tPriceAPI\tPriceCSV \n" +
                "";
        writeResultToFileText(msg);
        Assert.assertTrue(isPassed, msg + failedMsg);
    }

    public static void verifyListingFeePrice_PricerGetAll_RentAdShop_Region13000_PTY() {
        String subCateId = "";
        boolean isPassed = true;

        //Rent: all subcates with only 13000
        subCateIdList = getAllSubcateId(CATEID_PTY);
        String regionID = "13000";

        response = get(gatewayPricer_ListingFee_PricerGetAll_Public);
        verifyStatusCode200("API Listing Fee /pricer/get-all is dead");

        //Check price of each subcate
        for (int i = 0; i < subCateIdList.size(); i++) {
            subCateId = subCateIdList.get(i);
            String actionType =
                    getResponseData(response,
                            "$.insert_ads_with_regions[*][?(@.category_id == " + subCateId + " " +
                                    "&& @.ad_type == 'u' " +
                                    "&& @.region_id == " + regionID + " " +
                                    "&& @.action_type == 'shop_to_chotot')]" +
                                    ".action_type");

            //Each subcate -> check 3 types of price: vnd, dongtot, promotion
            for (String priceType : priceTypes) {
                switch (priceType.toLowerCase()) {
                    case "vnd":
                        query = "$.insert_ads_with_regions[*][?(@.category_id == " + subCateId + " " +
                                "&& @.ad_type == 'u' " +
                                "&& @.region_id == " + regionID + " " +
                                "&& @.action_type == 'shop_to_chotot')].price.vnd";
                        break;
                    case "dongtot":
                    case "credit":
                        query = "$.insert_ads_with_regions[*][?(@.category_id == " + subCateId + " " +
                                "&& @.ad_type == 'u' " +
                                "&& @.region_id == " + regionID + " " +
                                "&& @.action_type == 'shop_to_chotot')].price.credit";
                        break;
                    case "promotion":
                        query = "$.insert_ads_with_regions[*][?(@.category_id == " + subCateId + " " +
                                "&& @.ad_type == 'u' " +
                                "&& @.region_id == " + regionID + " " +
                                "&& @.action_type == 'shop_to_chotot')].price.promotion";
                        break;
                }

                //Check if Sell ad of cate is free -> skip
                actualPrice = getResponseData(query);
                try {
                    Assert.assertNotNull(actualPrice);
                } catch (AssertionError a) {
                    if (isPassed)
                        isPassed = false;
                    failedMsg += String.format("Cate[%s] Region[%s] [%s] [%s]: API[Chưa khai báo giá]\n" +
                                    ""
                            , subCateId, regionID, actionType, priceType);
                    continue;
                }

                expectedPrice = getPriceCSVByCate_ListingFee_RentAd_Pricer(subCateId, regionID, priceType, actionType);
                checkExpectedPriceEmpty();
                try {
                    Assert.assertNotNull(expectedPrice);
                } catch (AssertionError ex) {
                    if (isPassed)
                        isPassed = false;

                    if (isNullActualPrice()) //API null, CSV not found -> price type is NOT AVAILABLE
                        failedMsg += String.format("Cate[%s] Region[%s] [%s] [%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                        ""
                                , subCateId, regionID, actionType, priceType, actualPrice);
                    continue;
                }

                //Compare price
                try {
                    Assert.assertEquals(actualPrice, expectedPrice);
                } catch (AssertionError error) {
                    if (isPassed)
                        isPassed = false;
                    failedMsg += String.format("Cate[%s] Region[%s] [%s] [%s]: API[%s] <> CSV[%s]\n" +
                                    ""
                            , subCateId, regionID, actionType, priceType, actualPrice, expectedPrice);
                }
            }
        }
        String msg = "API LISTING FEE PTY RENT SHOP/pricer/get-all/listing-fee-pty internal: \n" +
                "" +
                "Cate\tRegion\tAction\tPriceType\tPriceAPI\tPriceCSV \n" +
                "";
        writeResultToFileText(msg);
        Assert.assertTrue(isPassed, msg + failedMsg);
    }


    public static void verifyListingFeePrice_PricerGetAll_SellAd_AllRegions(String cateIDs) {
        String subCateId = "";
        boolean isPassed = true;

        //Get all subcates which are applied for all regions
        String[] subCateAppliedAllRegion = cateIDs.replace(" ", "").trim().split(",");
        subCateIdList = new ArrayList<>();
        for (int i = 0; i < subCateAppliedAllRegion.length - 1; i++) {
            subCateIdList.add(subCateAppliedAllRegion[i]);
        }

        response = get(gatewayPricer_ListingFee_PricerGetAll_Public);
        verifyStatusCode200("API Listing Fee /pricer/get-all is dead");

        //Check price of each subcate
        for (int i = 0; i < subCateIdList.size(); i++) {
            subCateId = subCateIdList.get(i);

            String actionType =
                    getResponseData(
                            "$.insert_ads[*][?(@.category_id == " + subCateId + " " +
                                    "&& @.ad_type == 's' " +
                                    "&& (@.action_type == 'insertad'))]" +
                                    ".action_type");

            if (actionType == null) {
                actionType =
                        getResponseData(
                                "$.insert_ads[*][?(@.category_id == " + subCateId + " " +
                                        "&& @.ad_type == 's' " +
                                        "&& @.action_type == 'insert_ad')]" +
                                        ".action_type");
            }

            //Each subcate -> check 3 types of price: vnd, dongtot, promotion
            for (String priceType : priceTypes) {
                switch (priceType.toLowerCase()) {
                    case "vnd":
                        query = "$.insert_ads[*][?(@.category_id == " + subCateId + " " +
                                "&& @.ad_type == 's' " +
                                "&& @.action_type == '" + actionType + "')].price.vnd";
                        break;
                    case "dongtot":
                    case "credit":
                        query = "$.insert_ads[*][?(@.category_id == " + subCateId + " " +
                                "&& @.ad_type == 's' " +
                                "&& @.action_type == '" + actionType + "')].price.credit";
                        break;
                    case "promotion":
                        query = "$.insert_ads[*][?(@.category_id == " + subCateId + " " +
                                "&& @.ad_type == 's' " +
                                "&& @.action_type == '" + actionType + "')].price.promotion";
                        break;
                }

                //Check if Sell ad of cate is free -> skip
                actualPrice = getResponseData(query);
                //actualPrice of insert_ad OR insertad is null. Get actualPrice again
                if (actualPrice == null) {
                    query = query.replace(actionType, changeActionType(actionType));
                    actualPrice = getResponseData(query);
                }

                expectedPrice = getPriceCSVByCate_ListingFee_SellAd_Private_AllRegions_Pricer(subCateId, priceType, actionType);
                checkExpectedPriceEmpty();
                //Get price insertad -> null, then get insert_ad
                if (expectedPrice == null) {

                    String actionTypeCSV = changeActionType(actionType);
                    expectedPrice = getPriceCSVByCate_ListingFee_SellAd_Private_AllRegions_Pricer(subCateId, priceType, actionTypeCSV);
                    checkExpectedPriceEmpty(priceType);
                }

                //If expectedPrice exists & actualPrice doesn't exist, then Chưa khai báo giá
                if (expectedPrice == null) {      //ExpectedPrice doesn't exist
                    if (actualPrice == null) {
                        continue;
                    } else {
                        isPassed = false;

                        if (isNullActualPrice()) //API null, CSV not found -> price type is NOT AVAILABLE
                            failedMsg += String.format("Cate[%s] [%s] [%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                            ""
                                    , subCateId, actionType, priceType, actualPrice);
                    }
                } else {
                    if (actualPrice == null) {   //CSV != null, API == null
                        if (isPassed)
                            isPassed = false;
                        failedMsg += String.format("Cate[%s] [%s] [%s]: API[Chưa khai báo giá] <> CSV[%s]\n" +
                                        ""
                                , subCateId, actionType, priceType, expectedPrice);
                    } else {   //CSV != null, API != null
                        try {
                            Assert.assertEquals(actualPrice, expectedPrice);
                        } catch (AssertionError error) {
                            if (isPassed)
                                isPassed = false;
                            failedMsg += String.format("Cate[%s] [%s] [%s]: API[%s] <> CSV[%s]\n" +
                                            ""
                                    , subCateId, actionType, priceType, actualPrice, expectedPrice);
                        }
                    }
                }
            }
        }
        String msg = "API LISTING FEE SELL AD /pricer/get-all: \n" +
                "" +
                "Cate\tAction\tPriceType\tPriceAPI\tPriceCSV \n" +
                "";
        writeResultToFileText(msg);
        Assert.assertTrue(isPassed, msg + failedMsg);
    }

    public static void verifyListingFeePrice_PricerGetAll_SellAdShop_AllRegions(String cateIDs) {
        String subCateId = "";
        boolean isPassed = true;

        //Get all subcates which are applied for all regions
        String[] subCateAppliedAllRegion = cateIDs.replace(" ", "").trim().split(",");
        subCateIdList = new ArrayList<>();
        for (int i = 0; i < subCateAppliedAllRegion.length - 1; i++) {
            subCateIdList.add(subCateAppliedAllRegion[i]);
        }

        response = get(gatewayPricer_ListingFee_PricerGetAll_Public);
        verifyStatusCode200("API Listing Fee /pricer/get-all is dead");

        //Check price of each subcate
        for (int i = 0; i < subCateIdList.size(); i++) {
            subCateId = subCateIdList.get(i);

            String actionType =
                    getResponseData(response,
                            "$.insert_ads[*][?(@.category_id == " + subCateId + " " +
                                    "&& @.ad_type == 's' " +
                                    "&& @.action_type == 'shop_to_chotot')]" +
                                    ".action_type");


            //Each subcate -> check 3 types of price: vnd, dongtot, promotion
            for (String priceType : priceTypes) {
                switch (priceType.toLowerCase()) {
                    case "vnd":
                        query = "$.insert_ads[*][?(@.category_id == " + subCateId + " " +
                                "&& @.ad_type == 's' " +
                                "&& @.action_type == 'shop_to_chotot')].price.vnd";
                        break;
                    case "dongtot":
                    case "credit":
                        query = "$.insert_ads[*][?(@.category_id == " + subCateId + " " +
                                "&& @.ad_type == 's' " +
                                "&& @.action_type == 'shop_to_chotot')].price.credit";
                        break;
                    case "promotion":
                        query = "$.insert_ads[*][?(@.category_id == " + subCateId + " " +
                                "&& @.ad_type == 's' " +
                                "&& @.action_type == 'shop_to_chotot')].price.promotion";
                        break;
                }

                //Check if Sell ad of cate is free -> skip
                actualPrice = getResponseData(query);
                try {
                    Assert.assertNotNull(actualPrice);
                } catch (AssertionError a) {
                    if (isPassed)
                        isPassed = false;
                    failedMsg += String.format("Cate[%s] [%s] [%s]: API[Chưa khai báo giá]\n" +
                                    ""
                            , subCateId, actionType, priceType);
                    continue;
                }

                expectedPrice = getPriceCSVByCate_ListingFee_SellAd_Private_AllRegions_Pricer(subCateId, priceType, actionType);
                checkExpectedPriceEmpty();
                try {
                    Assert.assertNotNull(expectedPrice);
                } catch (AssertionError ex) {
                    if (isPassed)
                        isPassed = false;

                    if (isNullActualPrice()) //API null, CSV not found -> price type is NOT AVAILABLE
                        failedMsg += String.format("Cate[%s] [%s] [%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                        ""
                                , subCateId, priceType, actionType, actualPrice);
                    continue;
                }

                //Compare price
                try {
                    Assert.assertEquals(actualPrice, expectedPrice);
                } catch (AssertionError error) {
                    if (isPassed)
                        isPassed = false;
                    failedMsg += String.format("Cate[%s] [%s] [%s]: API[%s] <> CSV[%s]\n" +
                                    ""
                            , subCateId, priceType, actionType, actualPrice, expectedPrice);
                }
            }
        }
        String msg = "API LISTING FEE SHOP SELL AD ALL REGIONs /pricer/get-all internal: \n" +
                "";
        writeResultToFileText(msg);
        Assert.assertTrue(isPassed, msg + failedMsg);
    }


    //------------ LISTING FEE SERVICES------------

    //Listing Fee PTY
    public static void verifyListingFeePrice_SellAd_PTY_AllRegions(String cateIDs) {
        verifyListingFeePrice_SellAd_PTY_AllRegions(cateIDs, "insert_ads");
    }

    public static void verifyListingFeePrice_SellAd_PTY_AllRegions(String cateIDs, String fieldName) {
        String subCateId = "";
        boolean isPassed = true;

        //Get all subcates which are applied for all regions
        String[] subCateAppliedAllRegion = cateIDs.replace(" ", "").trim().split(",");
        subCateIdList = new ArrayList<>();
        for (int i = 0; i < subCateAppliedAllRegion.length - 1; i++) {
            subCateIdList.add(subCateAppliedAllRegion[i]);
        }

        //PTY:
        regionList = new ArrayList<>();
        regionList.add("2010");     //Bà Rịa Vũng Tàu
        regionList.add("2011");     //Bình Dương
        regionList.add("2012");     //Bình Phước
        regionList.add("2013");     //Đồng Nai
        regionList.add("2014");     //Tây Ninh
        regionList.add("13000");     //HCM

        String actionType = null;
        //Check price of each subcate
        for (int i = 0; i < subCateIdList.size(); i++) {
            subCateId = subCateIdList.get(i);

            for (String regionId : regionList) {
                response = get(userToken, String.format(gatewayPricer_ListingFee_ByRegion, regionId, subCateId));
                try {
                    verifyStatusCode200("API Listing Fee /pricer of cate[" + subCateId + "] dead");
                } catch (AssertionError a) {
                    isPassed = false;
                    if (getResponseData(response, "$.message").contains("Không tìm thấy giá dịch vụ")) {
                        failedMsg += String.format("Cate[%s] Region[%s]: API[Không tìm thấy khai báo giá dịch vụ]]\n" +
                                        ""
                                , subCateId, regionId);
                    } else {
                        failedMsg += String.format("Cate[%s] Region[%s]: API[ERROR]]\n" +
                                        ""
                                , subCateId, regionId);
                    }
                    continue;
                }

                //Each subcate -> check 3 types of price: vnd, dongtot, promotion
                for (String priceType : priceTypes) {
                    actionType = "insertad";
                    query = "$." + fieldName + "[*][?(@.action_type == '" + actionType + "' && @.ad_type == 's')].price." + priceType.toLowerCase();

                    //Check if Sell ad of cate is free -> skip
                    actualPrice = getResponseData(query);
                    if (actualPrice == null) {
                        query = query.replace(actionType, changeActionType(actionType));
                        actualPrice = getResponseData(query);
                    }

                    expectedPrice = getPriceCSVByCate_ListingFee_SellAd_Private(subCateId, regionId, priceType, actionType);
                    checkExpectedPriceEmpty();
                    //Get price insertad -> null, then get insert_ad
                    if (expectedPrice == null) {
                        String actionTypeCSV = changeActionType(actionType);
                        expectedPrice = getPriceCSVByCate_ListingFee_SellAd_Private(subCateId, regionId, priceType, actionTypeCSV);
                        checkExpectedPriceEmpty(priceType);
                    }

                    //COMPARE PRICE
                    if (expectedPrice == null) {      //ExpectedPrice doesn't exist
                        if (actualPrice == null) {
                            continue;
                        } else {
                            if (isPassed)
                                isPassed = false;

                            failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                            ""
                                    , subCateId, regionId, priceType, actualPrice);
                        }
                    } else {
                        if (actualPrice == null) {   //CSV != null, API == null
                            if (isPassed)
                                isPassed = false;
                            failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[Chưa khai báo giá] <> CSV[%s]\n" +
                                            ""
                                    , subCateId, regionId, priceType, expectedPrice);
                        } else {   //CSV != null, API != null
                            try {
                                Assert.assertEquals(actualPrice, expectedPrice);
                            } catch (AssertionError error) {
                                if (isPassed)
                                    isPassed = false;
                                failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[%s]\n" +
                                                ""
                                        , subCateId, regionId, priceType, actualPrice, expectedPrice);
                            }
                        }
                    }//End if action
                }
            }
        }
        String msg = "Listing Fee Private All Region of Cate PTY FAILED: \n" +
                "";
        writeResultToFileText(msg);
        Assert.assertTrue(isPassed, msg + failedMsg);
    }

    public static void verifyListingFeePrice_RentAd_PTY_13000() {
        verifyListingFeePrice_RentAd_PTY_13000("insert_ads");
    }

    public static void verifyListingFeePrice_RentAd_PTY_13000(String fieldName) {
        String subCateId = "", regionId = "13000", actionType = null;
        boolean isPassed = true;

        //get subcateIdList by cateID
        subCateIdList = getAllSubcateId(CATEID_PTY);

        for (int i = 0; i < subCateIdList.size(); i++) {
            subCateId = subCateIdList.get(i);
            response = get(userToken, String.format(gatewayPricer_ListingFee4Rent, regionId, subCateId));
            verifyStatusCode200("API get Listing Fee Price of cate[" + subCateId + "]");

            //Each subcate -> check 3 types of price: vnd, dongtot, promotion
            for (String priceType : priceTypes) {
                actionType = "insertad";
                query = "$." + fieldName + "[*][?(@.action_type == '" + actionType + "' && @.ad_type == 'u')].price." + priceType.toLowerCase();


                //Check if Sell ad of cate is free -> skip
                actualPrice = getResponseData(query);
                if (actualPrice == null) {
                    query = query.replace(actionType, changeActionType(actionType));
                    actualPrice = getResponseData(query);
                }

                expectedPrice = getPriceCSVByCate_ListingFee4Rent_SellAd_Private(subCateId, regionId, priceType, actionType);
                checkExpectedPriceEmpty();
                //Get price insertad -> null, then get insert_ad
                if (expectedPrice == null) {
                    String actionTypeCSV = changeActionType(actionType);
                    expectedPrice = getPriceCSVByCate_ListingFee4Rent_SellAd_Private(subCateId, regionId, priceType, actionTypeCSV);
                    checkExpectedPriceEmpty(priceType);
                }

                //COMPARE PRICE
                if (expectedPrice == null) {      //ExpectedPrice doesn't exist
                    if (actualPrice == null) {
                        continue;
                    } else {
                        if (isPassed)
                            isPassed = false;

                        failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                        ""
                                , subCateId, regionId, priceType, actualPrice);
                    }
                } else {
                    if (actualPrice == null) {   //CSV != null, API == null
                        if (isPassed)
                            isPassed = false;
                        failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[Chưa khai báo giá] <> CSV[%s]\n" +
                                        ""
                                , subCateId, regionId, priceType, expectedPrice);
                    } else {   //CSV != null, API != null
                        try {
                            Assert.assertEquals(actualPrice, expectedPrice);
                        } catch (AssertionError error) {
                            if (isPassed)
                                isPassed = false;
                            failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[%s]\n" +
                                            ""
                                    , subCateId, regionId, priceType, actualPrice, expectedPrice);
                        }
                    }
                }//End if action
            }
        }
        String msg = String.format("Listing Fee PTY 4 Rent Private Region[%s] FAILED: \n" +
                "", regionId);
        writeResultToFileText(msg);
        Assert.assertTrue(isPassed, msg + failedMsg);
    }


    //Listing Fee VEH
    public static void verifyListingFeePrice_SellAd_VEH_AllRegions(String cateIDs) {
        verifyListingFeePrice_SellAd_VEH_AllRegions(cateIDs, "insert_ads");
    }

    public static void verifyListingFeePrice_SellAd_VEH_AllRegions(String cateIDs, String fieldName) {
        String subCateId = "", actionType = null;
        boolean isPassed = true;

        //Get all subcates which are applied for all regions
        //2010, 2020, 2050
        String[] subCateAppliedAllRegion = cateIDs.replace(" ", "").trim().split(",");
        subCateIdList = new ArrayList<>();
        for (int i = 0; i < subCateAppliedAllRegion.length - 1; i++) {
            subCateIdList.add(subCateAppliedAllRegion[i]);
        }

        //Check price of each subcate
        for (int i = 0; i < subCateIdList.size(); i++) {
            subCateId = subCateIdList.get(i);

            response = get(userToken, String.format(gatewayPricer_ListingFee, subCateId));
            verifyStatusCode200("API get Listing Fee All Regions of cate VEH [" + subCateId + "]");

            //Each subcate -> check 3 types of price: vnd, dongtot, promotion
            for (String priceType : priceTypes) {
                actionType = "insertad";
                query = "$." + fieldName + "[*][?(@.action_type == '" + actionType + "' && @.ad_type == 's')].price." + priceType.toLowerCase();

                //Check if Sell ad of cate is free -> skip
                actualPrice = getResponseData(query);
                if (actualPrice == null) {
                    query = query.replace(actionType, changeActionType(actionType));
                    actualPrice = getResponseData(query);
                }

                expectedPrice = getPriceCSVByCate_ListingFee_SellAd_Private_AllRegions(subCateId, priceType, actionType);
                checkExpectedPriceEmpty();
                //Get price insertad -> null, then get insert_ad
                if (expectedPrice == null) {
                    String actionTypeCSV = changeActionType(actionType);
                    expectedPrice = getPriceCSVByCate_ListingFee_SellAd_Private_AllRegions(subCateId, priceType, actionTypeCSV);
                    checkExpectedPriceEmpty(priceType);
                }

                //COMPARE PRICE
                if (expectedPrice == null) {      //ExpectedPrice doesn't exist
                    if (actualPrice == null) {
                        continue;
                    } else {
                        if (isPassed)
                            isPassed = false;

                        failedMsg += String.format("Cate[%s] Price[%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                        ""
                                , subCateId, priceType, actualPrice);
                    }
                } else {
                    if (actualPrice == null) {   //CSV != null, API == null
                        if (isPassed)
                            isPassed = false;
                        failedMsg += String.format("Cate[%s] Price[%s]: API[Chưa khai báo giá] <> CSV[%s]\n" +
                                        ""
                                , subCateId, priceType, expectedPrice);
                    } else {   //CSV != null, API != null
                        try {
                            Assert.assertEquals(actualPrice, expectedPrice);
                        } catch (AssertionError error) {
                            if (isPassed)
                                isPassed = false;
                            failedMsg += String.format("Cate[%s] Price[%s]: API[%s] <> CSV[%s]\n" +
                                            ""
                                    , subCateId, priceType, actualPrice, expectedPrice);
                        }
                    }
                }//End if action
            }
        }
        String msg = "Listing Fee Private All Region of Cate PTY FAILED: \n" +
                "";
        writeResultToFileText(msg);
        Assert.assertTrue(isPassed, msg + failedMsg);
    }

    //Listing Fee PTY Shop
    public static void verifyListingFeePrice_SellAdShop_PTY_AllRegions(String cateIDs) {
        String subCateId = "", actionType = null;
        boolean isPassed = true;


        //Get all subcates which are applied for all regions
        String[] subCateAppliedAllRegion = cateIDs.replace(" ", "").trim().split(",");
        subCateIdList = new ArrayList<>();
        for (int i = 0; i < subCateAppliedAllRegion.length - 1; i++) {
            subCateIdList.add(subCateAppliedAllRegion[i]);
        }

        //PTY:
        regionList = new ArrayList<>();
        regionList.add("2010");     //Bà Rịa Vũng Tàu
        regionList.add("2011");     //Bình Dương
        regionList.add("2012");     //Bình Phước
        regionList.add("2013");     //Đồng Nai
        regionList.add("2014");     //Tây Ninh
        regionList.add("13000");     //HCM


        //Check price of each subcate
        for (int i = 0; i < subCateIdList.size(); i++) {
            subCateId = subCateIdList.get(i);

            for (String regionId : regionList) {
                response = get(userToken, String.format(gatewayPricer_ListingFee_ByRegion, regionId, subCateId));

                try {
                    verifyStatusCode200("API Listing Fee /pricer of cate[" + subCateId + "] dead");
                } catch (AssertionError a) {
                    isPassed = false;
                    if (getResponseData(response, "$.message").contains("Không tìm thấy giá dịch vụ")) {
                        failedMsg += String.format("Cate[%s] Region[%s]: API[Không tìm thấy khai báo giá dịch vụ]]\n" +
                                        ""
                                , subCateId, regionId);
                    } else {
                        failedMsg += String.format("Cate[%s] Region[%s]: API[ERROR]]\n" +
                                        ""
                                , subCateId, regionId);
                    }
                    continue;
                }

                //Each subcate -> check 3 types of price: vnd, dongtot, promotion
                for (String priceType : priceTypes) {
                    actionType = "shop_to_chotot";
                    query = "$.shop_to_chotot[*][?(@.action_type == '" + actionType + "' && @.ad_type == 's')].price." + priceType.toLowerCase();


                    //Check if Sell ad of cate is free -> skip
                    actualPrice = getResponseData(query);

                    expectedPrice = getPriceCSVByCate_ListingFee_SellAd_Pro(subCateId, regionId, priceType);
                    checkExpectedPriceEmpty();

                    //COMPARE PRICE
                    if (expectedPrice == null) {      //ExpectedPrice doesn't exist
                        if (actualPrice == null) {
                            continue;
                        } else {
                            if (isPassed)
                                isPassed = false;

                            if (isNullActualPrice()) //API null, CSV not found -> price type is NOT AVAILABLE
                                failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                                ""
                                        , subCateId, regionId, priceType, actualPrice);
                        }
                    } else {
                        if (actualPrice == null) {   //CSV != null, API == null
                            if (isPassed)
                                isPassed = false;
                            failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[Chưa khai báo giá]\n" +
                                            ""
                                    , subCateId, regionId, priceType);
                        } else {   //CSV != null, API != null
                            try {
                                Assert.assertEquals(actualPrice, expectedPrice);
                            } catch (AssertionError error) {
                                if (isPassed)
                                    isPassed = false;
                                failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[%s]\n" +
                                                ""
                                        , subCateId, regionId, priceType, actualPrice, expectedPrice);
                            }
                        }
                    }//End if action
                }
            }
        }
        String msg = "Listing Fee Pro All Region of Cate PTY FAILED: \n" +
                "";
        writeResultToFileText(msg);
        Assert.assertTrue(isPassed, msg + failedMsg);
    }

    public static void verifyListingFeePrice_RentAdShop_PTY_13000() {
        String subCateId = "", regionId = "13000";
        boolean isPassed = true;
        String actionType = "";

        //get subcateIdList by cateID
        subCateIdList = getAllSubcateId(CATEID_PTY);

        for (int i = 0; i < subCateIdList.size(); i++) {
            subCateId = subCateIdList.get(i);
            response = get(userToken, String.format(gatewayPricer_ListingFee4Rent, regionId, subCateId));
            verifyStatusCode200("API get Listing Fee Rent Pro Price of cate[" + subCateId + "]");

            //Each subcate -> check 3 types of price: vnd, dongtot, promotion
            for (String priceType : priceTypes) {
                actionType = "shop_to_chotot";
                query = "$.shop_to_chotot[*][?(@.action_type == '" + actionType + "' && @.ad_type == 'u')].price." + priceType.toLowerCase();

                //Check if Sell ad of cate is free -> skip
                actualPrice = getResponseData(query);

                expectedPrice = getPriceCSVByCate_ListingFee4Rent_SellAd_Pro(subCateId, regionId, priceType);
                checkExpectedPriceEmpty();

                //COMPARE PRICE
                if (expectedPrice == null) {      //ExpectedPrice doesn't exist
                    if (actualPrice == null) {
                        continue;
                    } else {
                        if (isPassed)
                            isPassed = false;

                        if (isNullActualPrice()) //API null, CSV not found -> price type is NOT AVAILABLE
                            failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                            ""
                                    , subCateId, regionId, priceType, actualPrice);
                    }
                } else {
                    if (actualPrice == null) {   //CSV != null, API == null
                        if (isPassed)
                            isPassed = false;
                        failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[Chưa khai báo giá]\n" +
                                        ""
                                , subCateId, regionId, priceType);
                    } else {   //CSV != null, API != null
                        try {
                            Assert.assertEquals(actualPrice, expectedPrice);
                        } catch (AssertionError error) {
                            if (isPassed)
                                isPassed = false;
                            failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[%s]\n" +
                                            ""
                                    , subCateId, regionId, priceType, actualPrice, expectedPrice);
                        }
                    }
                }//End if action
            }
        }
        String msg = String.format("Listing Fee PTY 4 Rent Shop To Chotot Region[%s] FAILED: \n" +
                "", regionId);
        writeResultToFileText(msg);
        Assert.assertTrue(isPassed, msg + failedMsg);
    }


    //Listing Fee VEH Shop
    public static void verifyListingFeePrice_SellAdShop_VEH_AllRegions(String cateIDs) {
        String subCateId = "", actionType = "";
        boolean isPassed = true;


        //Get all subcates which are applied for all regions
        //2010, 2020, 2050
        String[] subCateAppliedAllRegion = cateIDs.replace(" ", "").trim().split(",");
        subCateIdList = new ArrayList<>();
        for (int i = 0; i < subCateAppliedAllRegion.length - 1; i++) {
            subCateIdList.add(subCateAppliedAllRegion[i]);
        }

        //Check price of each subcate
        for (int i = 0; i < subCateIdList.size(); i++) {
            subCateId = subCateIdList.get(i);

            for (String regionId : regionList) {
                response = get(userToken, String.format(gatewayPricer_ListingFee_ByRegion, regionId, subCateId));
                verifyStatusCode200("Check price of /service with shop_to_chotot [" + subCateId + "]");

                //Each subcate -> check 3 types of price: vnd, dongtot, promotion
                for (String priceType : priceTypes) {
                    actionType = "shop_to_chotot";
                    query = "$.shop_to_chotot[*][?(@.action_type == '" + actionType + "' && @.ad_type == 's')].price." + priceType.toLowerCase();


                    //Check if Sell ad of cate is free -> skip
                    actualPrice = getResponseData(query);

                    expectedPrice = getPriceCSVByCate_ListingFee_SellAd_Pro_AllRegions(subCateId, priceType);
                    checkExpectedPriceEmpty();

                    //COMPARE PRICE
                    if (expectedPrice == null) {      //ExpectedPrice doesn't exist
                        if (actualPrice == null) {
                            continue;
                        } else {
                            if (isPassed)
                                isPassed = false;

                            if (isNullActualPrice()) //API null, CSV not found -> price type is NOT AVAILABLE
                                failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                                ""
                                        , subCateId, regionId, priceType, actualPrice);
                        }
                    } else {
                        if (actualPrice == null) {   //CSV != null, API == null
                            if (isPassed)
                                isPassed = false;
                            failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[Chưa khai báo giá]\n" +
                                            ""
                                    , subCateId, regionId, priceType);
                        } else {   //CSV != null, API != null
                            try {
                                Assert.assertEquals(actualPrice, expectedPrice);
                            } catch (AssertionError error) {
                                if (isPassed)
                                    isPassed = false;
                                failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[%s]\n" +
                                                ""
                                        , subCateId, regionId, priceType, actualPrice, expectedPrice);
                            }
                        }
                    }//End if action
                }
            }
        }
        String msg = "Check price of /service with shop_to_chotot VEH FAILED: \n" +
                "";
        writeResultToFileText(msg);
        Assert.assertTrue(isPassed, msg + failedMsg);
    }


    //Listing Fee ELT Shop
    @Deprecated // DELETE function
    public static void verifyListingFeePrice_SellAdShop_ELT_AllRegions(String cateIDs) {
        String subCateId = "";
        boolean isPassed = true;


        //Get all subcates which are applied for all regions
        //2010, 2020, 2050
        String[] subCateAppliedAllRegion = cateIDs.replace(" ", "").trim().split(",");
        subCateIdList = new ArrayList<>();
        for (int i = 0; i < subCateAppliedAllRegion.length - 1; i++) {
            subCateIdList.add(subCateAppliedAllRegion[i]);
        }

        //Check price of each subcate
        for (int i = 0; i < subCateIdList.size(); i++) {
            subCateId = subCateIdList.get(i);

            for (String regionId : regionList) {
                System.out.println("Check subcate ID[" + subCateId + "] RegionID[" + regionId + "]");
                response = get(userToken, String.format(gatewayPricer_ListingFee, regionId, subCateId));
                try {
                    verifyStatusCode200("API get Listing Fee All Regions of cate VEH [" + subCateId + "]");
                } catch (AssertionError es) {
                    if (isPassed)
                        isPassed = false;
                    failedMsg += String.format("** Cate[%s] Region[%s]: API[ERROR]\n" +
                            "", subCateId, regionId);
                    continue;
                }


                //Each subcate -> check 3 types of price: vnd, dongtot, promotion
                for (String priceType : priceTypes) {
                    switch (priceType.toLowerCase()) {
                        case "vnd":
                            query = "$.insert_ads[*][?(@.action_type == 'shop_to_chotot' && @.ad_type == 's')].price.vnd";
                            break;
                        case "dongtot":
                        case "credit":
                            query = "$.insert_ads[*][?(@.action_type == 'shop_to_chotot' && @.ad_type == 's')].price.credit";
                            break;
                        case "promotion":
                            query = "$.insert_ads[*][?(@.action_type == 'shop_to_chotot' && @.ad_type == 's')].price.promotion";
                            break;
                    }

                    actualPrice = getResponseData(query);
                    try {
                        Assert.assertNotNull(actualPrice);
                    } catch (AssertionError a) {
                        if (isPassed)
                            isPassed = false;
                        failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[Chưa khai báo giá]\n" +
                                        ""
                                , subCateId, regionId, priceType);
                        continue;
                    }
                    expectedPrice = getPriceCSVByCate_ListingFee_SellAd_Pro_AllRegions(subCateId, priceType);
                    checkExpectedPriceEmpty();
                    try {
                        Assert.assertNotNull(expectedPrice);
                    } catch (AssertionError ex) {
                        if (isPassed)
                            isPassed = false;

                        if (isNullActualPrice()) //API null, CSV not found -> price type is NOT AVAILABLE
                            failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                            ""
                                    , subCateId, regionId, priceType, actualPrice);
                        continue;
                    }

                    //Compare price
                    try {
                        Assert.assertEquals(actualPrice, expectedPrice);
                    } catch (AssertionError error) {
                        if (isPassed)
                            isPassed = false;
                        failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[%s]\n" +
                                        ""
                                , subCateId, regionId, priceType, actualPrice, expectedPrice);
                    }
                }

            }
        }
        String msg = "Listing Fee PRO All Region of Cate ELT FAILED: \n" +
                "";
        writeResultToFileText(msg);
        Assert.assertTrue(isPassed, msg + failedMsg);
    }


    //verifyListingFeePrice_SellAdShopSpecified_PTY_AllRegions
    public static void verifyListingFeePrice_AdSpecified_AllRegions(String cateIDs, String accountID, boolean isSellAd, String fieldName, boolean isFullRegion) {
        String subCateId = "", actionType = "", adType = "s";
        boolean isPassed = true, isShopAd = false;


        //Get all subcates which are applied for all regions
        if (!cateIDs.isEmpty()) {     //all Categories
            String[] subCateAppliedAllRegion = cateIDs.replace(" ", "").trim().split(",");
            subCateIdList = new ArrayList<>();
            for (int i = 0; i < subCateAppliedAllRegion.length - 1; i++) {
                subCateIdList.add(subCateAppliedAllRegion[i]);
            }
        }
        
        //AdType
        if(isSellAd){
            adType = "s";
        } else {
            adType = "u";
        }
            

        //regionList:
        if (!isFullRegion) {
            regionList = new ArrayList<>();
            if(isSellAd){
                regionList.add("2010");     //Bà Rịa Vũng Tàu
                regionList.add("2011");     //Bình Dương
                regionList.add("2012");     //Bình Phước
                regionList.add("2013");     //Đồng Nai
                regionList.add("2014");     //Tây Ninh
                regionList.add("13000");     //HCM
            } else {
                //Rent ad is applied only for 13000
                regionList.add("13000");     //HCM
            }
        }



        //Check price of each subcate
        for (int i = 0; i < subCateIdList.size(); i++) {
            subCateId = subCateIdList.get(i);
            if(adType.equalsIgnoreCase("u")){
                //only apply for PTY
                if(!subCateId.startsWith("10"))
                    continue;
            }
            for (String regionId : regionList) {
                response = get(userToken, String.format(gatewayPricer_ListingFee_Service_SpecifiedUser, accountID, regionId, subCateId, adType));
                for (String priceType : priceTypes) {
                    switch (fieldName.toLowerCase()) {
                        case "shop_to_chotot":
                            actionType = "shop_to_chotot";
                            isShopAd = true;
                            break;
                        case "insert_ads":
                        case "insert_ads_with_regions":
                        default:
                            actionType = "insertad";
                            isShopAd = false;
                            break;
                    }

                    if(subCateId.startsWith("10")){
                        query = "$." + fieldName + "[*][?(" +
                                "@.action_type == '" + actionType + "' " +
                                " && @.ad_type == '" + adType + "'" +
                                " && @.region_id == '" + regionId + "'" +
                                " && @.category_id == '" + subCateId + "'" +
                                ")].price." + priceType.toLowerCase();
                    }else {
                        query = "$." + fieldName + "[*][?(" +
                                "@.action_type == '" + actionType + "' " +
                                " && @.ad_type == '" + adType + "'" +
                                " && @.category_id == '" + subCateId + "'" +
                                ")].price." + priceType.toLowerCase();
                    }

                    //Check if Sell ad of cate is free -> skip
                    actualPrice = getResponseData(query);
                    if (!isShopAd) {
                        if (actualPrice == null) {
                            query = query.replace(actionType, changeActionType(actionType));
                            actualPrice = getResponseData(query);
                        }
                    }

                    //Test: insert_ads_with_regions is null, then get price from insert_ads
                    /*
                    if (!isShopAd) {
                        if (actualPrice == null) {
                            if(fieldName.equalsIgnoreCase("insert_ads_with_regions")){
                                query = query.replace(actionType, changeActionType(actionType));
                                actualPrice = getResponseData(query);

                                //Major: if insert_ads_with_regions price is null then get value of insert_ads
                                query = query.replace("insert_ads", "insert_ads_with_regions");
                                actualPrice = getResponseData(query);
                                if(actualPrice == null){
                                    query = query.replace(actionType, changeActionType(actionType));
                                    actualPrice = getResponseData(query);
                                }

                            } else {
                                //Major: if insert_ads, price is null. Response "Chua khai bao gia"
                                query = query.replace(actionType, changeActionType(actionType));
                                actualPrice = getResponseData(query);
                            }
                        }
                    }
                    */

                    if (!isShopAd) {
                        if(isFullRegion){      //Full Region
                            expectedPrice = getPriceCSVByCate_ListingFee_SellAd_Private_AllRegions(subCateId, priceType, actionType);
                            checkExpectedPriceEmpty();
                            if (expectedPrice == null) {
                                String actionTypeCSV = changeActionType(actionType);
                                expectedPrice = getPriceCSVByCate_ListingFee_SellAd_Private_AllRegions(subCateId, priceType, actionTypeCSV);
                                checkExpectedPriceEmpty(priceType);
                            }
                        }else{
                            expectedPrice = getPriceCSVByCate_ListingFee_SellAd_Private(subCateId, regionId, priceType, actionType);
                            checkExpectedPriceEmpty();
                            if (expectedPrice == null) {
                                String actionTypeCSV = changeActionType(actionType);
                                expectedPrice = getPriceCSVByCate_ListingFee_SellAd_Private(subCateId, regionId, priceType, actionTypeCSV);
                                checkExpectedPriceEmpty(priceType);
                            }
                        }
                    } else {
                        if(isFullRegion){ //Full Region
                            expectedPrice = getPriceCSVByCate_ListingFee_SellAd_Pro_AllRegions(subCateId, priceType);
                            checkExpectedPriceEmpty();
                        } else {
                            expectedPrice = getPriceCSVByCate_ListingFee_SellAd_Pro(subCateId, regionId, priceType);
                            checkExpectedPriceEmpty();
                        }

                    }

                    //COMPARE PRICE
                    if (expectedPrice == null) {      //ExpectedPrice doesn't exist
                        if (actualPrice == null) {
                            continue;
                        } else {
                            if (isPassed)
                                isPassed = false;
                            failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                            ""
                                    , subCateId, regionId, priceType, actualPrice);
                        }
                    } else {
                        if (actualPrice == null) {   //CSV != null, API == null
                            if (isPassed)
                                isPassed = false;
                            failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[Chưa khai báo giá] <> CSV[%s]\n" +
                                            ""
                                    , subCateId, regionId, priceType, expectedPrice);
                        } else {   //CSV != null, API != null
                            try {
                                Assert.assertEquals(actualPrice, expectedPrice);
                            } catch (AssertionError error) {
                                if (isPassed)
                                    isPassed = false;
                                failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[%s]\n" +
                                                ""
                                        , subCateId, regionId, priceType, actualPrice, expectedPrice);
                            }
                        }
                    }//End if action
                }
            }
        }
        String msg = "API Pricer SERVICES with [" + fieldName.toUpperCase() + "] FAILED: \n" +
                "";
        writeResultToFileText(msg);
        Assert.assertTrue(isPassed, msg + failedMsg);
    }

    public static void verifyListingFeePrice_SellAdShopSpecified_PTY_13000(String accountID) {
        String subCateId = "";
        boolean isPassed = true;


        //Get all sub-categories of PTY
        subCateIdList = getAllSubcateId(CATEID_PTY);
        String regionId = "13000";

        //Check price of each subcate
        for (int i = 0; i < subCateIdList.size(); i++) {
            subCateId = subCateIdList.get(i);

            response = get(userToken, String.format(gatewayPricer_ListingFee_SpecifiedUser, accountID, regionId, subCateId));
            verifyStatusCode200("API get Listing Fee Pro 13000 of cate[" + subCateId + "]");
            debugResponse();

            //Each subcate -> check 3 types of price: vnd, dongtot, promotion
            for (String priceType : priceTypes) {
                switch (priceType.toLowerCase()) {
                    case "vnd":
                        query = "$.insert_ads_with_regions[*][?(@.action_type == 'shop_to_chotot' && @.ad_type == 's')].price.vnd";
                        break;
                    case "dongtot":
                    case "credit":
                        query = "$.insert_ads_with_regions[*][?(@.action_type == 'shop_to_chotot' && @.ad_type == 's')].price.credit";
                        break;
                    case "promotion":
                        query = "$.insert_ads_with_regions[*][?(@.action_type == 'shop_to_chotot' && @.ad_type == 's')].price.promotion";
                        break;
                }

                actualPrice = getResponseData(query);
                try {
                    Assert.assertNotNull(actualPrice);
                } catch (AssertionError a) {
                    if (isPassed)
                        isPassed = false;
                    failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[Chưa khai báo giá]\n" +
                                    ""
                            , subCateId, regionId, priceType);
                    continue;
                }
                expectedPrice = getPriceCSVByCate_ListingFee_SellAd_Pro(subCateId, regionId, priceType);
                checkExpectedPriceEmpty();
                try {
                    Assert.assertNotNull(expectedPrice);
                } catch (AssertionError ex) {
                    if (isPassed)
                        isPassed = false;

                    if (isNullActualPrice()) //API null, CSV not found -> price type is NOT AVAILABLE
                        failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                        ""
                                , subCateId, regionId, priceType, actualPrice);
                    continue;
                }

                //Compare price
                try {
                    Assert.assertEquals(actualPrice, expectedPrice);
                } catch (AssertionError error) {
                    if (isPassed)
                        isPassed = false;
                    failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[%s]\n" +
                                    ""
                            , subCateId, regionId, priceType, actualPrice, expectedPrice);

                }
            }
        }
        String msg = "Listing Fee Private Region 13000 of Cate PTY FAILED: \n" +
                "";
        writeResultToFileText(msg);
        Assert.assertTrue(isPassed, msg + failedMsg);
    }


    //Listing Fee VEH Shop Specified
    public static void verifyListingFeePrice_SellAdShopSpecified_VEH_AllRegions(String cateIDs, String accountID) {
        String subCateId = "";
        boolean isPassed = true;


        //Get all subcates which are applied for all regions
        //2010, 2020, 2050
        String[] subCateAppliedAllRegion = cateIDs.replace(" ", "").trim().split(",");
        subCateIdList = new ArrayList<>();
        for (int i = 0; i < subCateAppliedAllRegion.length - 1; i++) {
            subCateIdList.add(subCateAppliedAllRegion[i]);
        }

        //Check price of each subcate
        for (int i = 0; i < subCateIdList.size(); i++) {
            subCateId = subCateIdList.get(i);

            for (String regionId : regionList) {
                System.out.println("Check subcate ID[" + subCateId + "] RegionID[" + regionId + "]");
                response = get(userToken, String.format(gatewayPricer_ListingFee_SpecifiedUser, accountID, regionId, subCateId));
                verifyStatusCode200("API get Listing Fee PRO All Regions of cate VEH [" + subCateId + "]");

                //Each subcate -> check 3 types of price: vnd, dongtot, promotion
                for (String priceType : priceTypes) {
                    switch (priceType.toLowerCase()) {
                        case "vnd":
                            query = "$.insert_ads[*][?(@.action_type == 'shop_to_chotot' && @.ad_type == 's')].price.vnd";
                            break;
                        case "dongtot":
                        case "credit":
                            query = "$.insert_ads[*][?(@.action_type == 'shop_to_chotot' && @.ad_type == 's')].price.credit";
                            break;
                        case "promotion":
                            query = "$.insert_ads[*][?(@.action_type == 'shop_to_chotot' && @.ad_type == 's')].price.promotion";
                            break;
                    }

                    actualPrice = getResponseData(query);
                    try {
                        Assert.assertNotNull(actualPrice);
                    } catch (AssertionError a) {
                        if (isPassed)
                            isPassed = false;
                        failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[Chưa khai báo giá]\n" +
                                        ""
                                , subCateId, regionId, priceType);
                        continue;
                    }
                    expectedPrice = getPriceCSVByCate_ListingFee_SellAd_Pro_AllRegions(subCateId, priceType);
                    checkExpectedPriceEmpty();
                    try {
                        Assert.assertNotNull(expectedPrice);
                    } catch (AssertionError ex) {
                        if (isPassed)
                            isPassed = false;

                        if (isNullActualPrice()) //API null, CSV not found -> price type is NOT AVAILABLE
                            failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                            ""
                                    , subCateId, regionId, priceType, actualPrice);
                        continue;
                    }

                    //Compare price
                    try {
                        Assert.assertEquals(actualPrice, expectedPrice);
                    } catch (AssertionError error) {
                        if (isPassed)
                            isPassed = false;
                        failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[%s]\n" +
                                        ""
                                , subCateId, regionId, priceType, actualPrice, expectedPrice);

                    }
                }
            }
        }
        String msg = "Listing Fee Private All Region of Cate VEH FAILED: \n" +
                "";
        writeResultToFileText(msg);
        Assert.assertTrue(isPassed, msg + failedMsg);
    }

    public static void verifyListingFeePrice_SellAdShopSpecified_VEH_13000(String accountID) {
        String subCateId = "";
        boolean isPassed = true;


        //Get all sub-categories of PTY
        subCateIdList = getAllSubcateId(CATEID_VEH);
        String regionId = "13000";

        //Check price of each subcate
        for (int i = 0; i < subCateIdList.size(); i++) {
            subCateId = subCateIdList.get(i);

            response = get(userToken, String.format(gatewayPricer_ListingFee_SpecifiedUser, accountID, regionId, subCateId));
            verifyStatusCode200("API get Listing Fee All Regions of cate[" + subCateId + "]");
            debugResponse();

            //Each subcate -> check 3 types of price: vnd, dongtot, promotion
            for (String priceType : priceTypes) {
                switch (priceType.toLowerCase()) {
                    case "vnd":
                        query = "$.insert_ads[*][?(@.action_type == 'shop_to_chotot' && @.ad_type == 's')].price.vnd";
                        break;
                    case "dongtot":
                    case "credit":
                        query = "$.insert_ads[*][?(@.action_type == 'shop_to_chotot' && @.ad_type == 's')].price.credit";
                        break;
                    case "promotion":
                        query = "$.insert_ads[*][?(@.action_type == 'shop_to_chotot' && @.ad_type == 's')].price.promotion";
                        break;
                }

                actualPrice = getResponseData(query);
                try {
                    Assert.assertNotNull(actualPrice);
                } catch (AssertionError a) {
                    if (isPassed)
                        isPassed = false;
                    failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[Chưa khai báo giá]\n" +
                                    ""
                            , subCateId, regionId, priceType);
                    continue;
                }
                expectedPrice = getPriceCSVByCate_ListingFee_SellAd_Pro(subCateId, regionId, priceType);
                checkExpectedPriceEmpty();
                try {
                    Assert.assertNotNull(expectedPrice);
                } catch (AssertionError ex) {
                    if (isPassed)
                        isPassed = false;

                    if (isNullActualPrice()) //API null, CSV not found -> price type is NOT AVAILABLE
                        failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                        ""
                                , subCateId, regionId, priceType, actualPrice);
                    continue;
                }

                //Compare price
                try {
                    Assert.assertEquals(actualPrice, expectedPrice);
                } catch (AssertionError error) {
                    if (isPassed)
                        isPassed = false;
                    failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[%s]\n" +
                                    ""
                            , subCateId, regionId, priceType, actualPrice, expectedPrice);
                }
            }

        }
        String msg = "Listing Fee Private All Region of Cate VEH FAILED: \n" +
                "";
        writeResultToFileText(msg);
        Assert.assertTrue(isPassed, msg + failedMsg);
    }


    //Listing Fee ELT Shop Specified
    public static void verifyListingFeePrice_SellAdShopSpecified_ELT_AllRegions(String cateIDs, String accountID) {
        String subCateId = "";
        boolean isPassed = true;


        //Get all subcates which are applied for all regions
        //2010, 2020, 2050
        String[] subCateAppliedAllRegion = cateIDs.replace(" ", "").trim().split(",");
        subCateIdList = new ArrayList<>();
        for (int i = 0; i < subCateAppliedAllRegion.length - 1; i++) {
            subCateIdList.add(subCateAppliedAllRegion[i]);
        }

        //Check price of each subcate
        for (int i = 0; i < subCateIdList.size(); i++) {
            subCateId = subCateIdList.get(i);

            for (String regionId : regionList) {
                System.out.println("Check subcate ID[" + subCateId + "] RegionID[" + regionId + "]");
                response = get(userToken, String.format(gatewayPricer_ListingFee_SpecifiedUser, accountID, regionId, subCateId));
                try {
                    verifyStatusCode200("API get Listing Fee All Regions of cate VEH [" + subCateId + "]");
                } catch (AssertionError es) {
                    isPassed = false;
                    failedMsg += String.format("** Cate[%s] Region[%s]: API[ERROR]\n" +
                            "", subCateId, regionId);
                    continue;
                }


                //Each subcate -> check 3 types of price: vnd, dongtot, promotion
                for (String priceType : priceTypes) {
                    switch (priceType.toLowerCase()) {
                        case "vnd":
                            query = "$.insert_ads[*][?(@.action_type == 'shop_to_chotot' && @.ad_type == 's')].price.vnd";
                            break;
                        case "dongtot":
                        case "credit":
                            query = "$.insert_ads[*][?(@.action_type == 'shop_to_chotot' && @.ad_type == 's')].price.credit";
                            break;
                        case "promotion":
                            query = "$.insert_ads[*][?(@.action_type == 'shop_to_chotot' && @.ad_type == 's')].price.promotion";
                            break;
                    }

                    actualPrice = getResponseData(query);
                    try {
                        Assert.assertNotNull(actualPrice);
                    } catch (AssertionError a) {
                        if (isPassed)
                            isPassed = false;
                        failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[Chưa khai báo giá]\n" +
                                        ""
                                , subCateId, regionId, priceType);
                        continue;
                    }
                    expectedPrice = getPriceCSVByCate_ListingFee_SellAd_Pro_AllRegions(subCateId, priceType);
                    checkExpectedPriceEmpty();
                    try {
                        Assert.assertNotNull(expectedPrice);
                    } catch (AssertionError ex) {
                        if (isPassed)
                            isPassed = false;

                        if (isNullActualPrice()) //API null, CSV not found -> price type is NOT AVAILABLE
                            failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                            ""
                                    , subCateId, regionId, priceType, actualPrice);
                        continue;
                    }

                    //Compare price
                    try {
                        Assert.assertEquals(actualPrice, expectedPrice);
                    } catch (AssertionError error) {
                        if (isPassed)
                            isPassed = false;
                        failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[%s]\n" +
                                        ""
                                , subCateId, regionId, priceType, actualPrice, expectedPrice);
                    }
                }

            }
        }
        String msg = "Listing Fee PRO All Region of Cate ELT FAILED: \n" +
                "";
        writeResultToFileText(msg);
        Assert.assertTrue(isPassed, msg + failedMsg);
    }

    public static void verifyListingFeePrice_SellAdShopSpecified_ELT_13000(String accountID) {
        String subCateId = "";
        boolean isPassed = true;


        //Get all sub-categories of PTY
        subCateIdList = getAllSubcateId(CATEID_ELT);
        String regionId = "13000";

        //Check price of each subcate
        for (int i = 0; i < subCateIdList.size(); i++) {
            subCateId = subCateIdList.get(i);

            response = get(userToken, String.format(gatewayPricer_ListingFee_SpecifiedUser, accountID, regionId, subCateId));
            verifyStatusCode200("API get Listing Fee Pro ELT All Regions of cate[" + subCateId + "]");
            debugResponse();

            //Each subcate -> check 3 types of price: vnd, dongtot, promotion
            for (String priceType : priceTypes) {
                switch (priceType.toLowerCase()) {
                    case "vnd":
                        query = "$.insert_ads[*][?(@.action_type == 'shop_to_chotot' && @.ad_type == 's')].price.vnd";
                        break;
                    case "dongtot":
                    case "credit":
                        query = "$.insert_ads[*][?(@.action_type == 'shop_to_chotot' && @.ad_type == 's')].price.credit";
                        break;
                    case "promotion":
                        query = "$.insert_ads[*][?(@.action_type == 'shop_to_chotot' && @.ad_type == 's')].price.promotion";
                        break;
                }
                actualPrice = getResponseData(query);
                try {
                    Assert.assertNotNull(actualPrice);
                } catch (AssertionError a) {
                    if (isPassed)
                        isPassed = false;
                    failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[Chưa khai báo giá]\n" +
                                    ""
                            , subCateId, regionId, priceType);
                    continue;
                }
                expectedPrice = getPriceCSVByCate_ListingFee_SellAd_Pro(subCateId, regionId, priceType);
                checkExpectedPriceEmpty();
                try {
                    Assert.assertNotNull(expectedPrice);
                } catch (AssertionError ex) {
                    if (isPassed)
                        isPassed = false;

                    if (isNullActualPrice()) //API null, CSV not found -> price type is NOT AVAILABLE
                        failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                        ""
                                , subCateId, regionId, priceType, actualPrice);
                    continue;
                }

                //Compare price
                try {
                    Assert.assertEquals(actualPrice, expectedPrice);
                } catch (AssertionError error) {
                    if (isPassed)
                        isPassed = false;
                    failedMsg += String.format("Cate[%s] Region[%s] Price[%s]: API[%s] <> CSV[%s]\n" +
                                    ""
                            , subCateId, regionId, priceType, actualPrice, expectedPrice);

                }
            }
        }
        String msg = "Listing Fee Pro All Region of Cate ELT FAILED: \n" +
                "";
        writeResultToFileText(msg);
        Assert.assertTrue(isPassed, msg + failedMsg);
    }


    public static void verifyListingInvalid_SellAd() {
        response = get(getUserToken(), gatewayPricer_Shop);

        List<Integer> failCa = getResponseDataListInt(response,
                "$.shops[*][?(@.price.vnd == 9.900 || @.price.vnd == 123.456 || @.price.vnd == 11.001)].category_id");
        List<Integer> failCaType = getResponseDataListInt(response,
                "$.shops[*][?(@.price.vnd == 9.900 || @.price.vnd == 123.456 || @.price.vnd == 11.001)].action_type");
        for (int i = 0; i < failCa.size(); i++) {
            duplicates.add("Shop VND - " + failCaType.get(i) + " : " + failCa.get(i));
        }

        failCa = getResponseDataListInt(response,
                "$.shops[*][?(@.credit.vnd == 9.900 || @.credit.vnd == 123.456 || @.credit.vnd == 11.001)].category_id");
        failCaType = getResponseDataListInt(response,
                "$.shops[*][?(@.credit.vnd == 9.900 || @.credit.vnd == 123.456 || @.credit.vnd == 11.001)].action_type");
        for (int i = 0; i < failCa.size(); i++) {
            duplicates.add("Shop CREDIT - " + failCaType.get(i) + " : " + failCa.get(i));
        }

        //Ad Feature
        responseTemp2 = get(gatewayPricer_Public_GetAllPrice);
        List<Integer> failCateAdFeature = getResponseDataListInt(responseTemp2,
                "$.ad_features[*][?(@.price.vnd == 9.900 || @.price.vnd == 123.456 || @.price.vnd == 11.001)].category_id");
        List<Integer> failType = getResponseDataListInt(responseTemp2,
                "$.ad_features[*][?(@.price.vnd == 9.900 || @.price.vnd == 123.456 || @.price.vnd == 11.001)].feature");
        for (int i = 0; i < failCateAdFeature.size(); i++) {
            duplicates.add("Ad Feature - " + failType.get(i) + " : " + failCateAdFeature.get(i));
        }


        //Check Sticky Ads
        List<Integer> failCate = getResponseDataListInt(responseTemp2,
                "$.sticky_ads[*][?(@.price.vnd == 9.900 || @.price.vnd == 123.456 || @.price.vnd == 11.001)].category_id");
        for (int cateId : failCate) {
            duplicates.add("Sticky Ads - " + cateId);
        }

        //get all Bump price
        responseTemp1 = get(getUserToken(), gatewayPricer_Private_GetAllPrice_Bump);
        for (String subcateId : subCateIdList) {
            //Check bump
            String bumpPrice = String.valueOf(getResponseDataInt(responseTemp1, "$.bump_price.bump." + subcateId));
            checkPriceHashKey(bumpPrice, subcateId, "bump");

            String bump3Days = String.valueOf(getResponseDataInt(responseTemp1, "$.bump_price.3days_bump." + subcateId));
            checkPriceHashKey(bump3Days, subcateId, "bump3Days");

            String bump7Days = String.valueOf(getResponseDataInt(responseTemp1, "$.bump_price.7days_bump." + subcateId));
            checkPriceHashKey(bump7Days, subcateId, "bump7Days");

            String bumpTimer = String.valueOf(getResponseDataInt(responseTemp1, "$.bump_price.timer_bump." + subcateId));
            checkPriceHashKey(bumpTimer, subcateId, "bumpTimer");
        }


        //Results
        System.out.println("RESULT: ");
        for (String prints : duplicates) {
            System.out.println(prints);
        }
    }

    private static List<String> expectPrice = Arrays.asList("9.900", "123.456", "11.001");
    private static List<String> duplicates = new ArrayList<>();

    private static void checkPriceHashKey(String price, String subcateId, String posType) {
        if (expectPrice.contains(price))
            duplicates.add(posType + " - " + subcateId + " : " + price);

    }


    //---------- Shop Create Get all public ----------

    public static void verifyShopCreatePrice_VND() {
        verifyShopPrice("shop_create", "vnd", "");
    }

    public static void verifyShopCreatePrice_DongTot() {
        verifyShopPrice("shop_create", "credit", "");
    }

    public static void verifyShopCreatePrice_Promotion() {
        verifyShopPrice("shop_create", "promotion", "");
    }

    public static void verifyShopExtendPrice_VND() {
        verifyShopPrice("shop_extend", "vnd", "");
    }

    public static void verifyShopExtendPrice_DongTot() {
        verifyShopPrice("shop_extend", "credit", "");
    }

    public static void verifyShopExtendPrice_Promotion() {
        verifyShopPrice("shop_extend", "promotion", "");
    }

    //================ SHOP PRIVATE ================

    public static void verifyShopCreatePrice_Private_VND(String token) {
        verifyShopPrice("shop_create", "vnd", token);
    }

    public static void verifyShopCreatePrice_Private_DT(String token) {
        verifyShopPrice("shop_create", "credit", token);
    }

    public static void verifyShopCreatePrice_Private_Promotion(String token) {
        verifyShopPrice("shop_create", "promotion", token);
    }

    public static void verifyShopExtendPrice_Private_VND(String token) {
        verifyShopPrice("shop_extend", "vnd", token);
    }

    public static void verifyShopExtendPrice_Private_DT(String token) {
        verifyShopPrice("shop_extend", "credit", token);
    }

    public static void verifyShopExtendPrice_Private_Promotion(String token) {
        verifyShopPrice("shop_extend", "promotion", token);
    }

    /**
     * @param shopAction shop_create  shop_extend
     * @param priceType  vnd  promotion credit
     */
    private static void verifyShopPrice(String shopAction, String priceType, String token) {
        String cateShopId = "";
        boolean isPassed = true;
        boolean isShopELT = false;      //false: PTY, VEH -> duration = 1 3 6 12, true -> duration = 1 2 3 6

        //Init query
        String tempQuery = "$.shops[*]" +
                "[?(@.action_type == '" + shopAction + "' " +
                "&& @.duration == %s " +
                "&& @.category_id == %s" +
                ")].price." + priceType.toLowerCase();

        //Query to get duration of the cateShopId
        if (!token.isEmpty()) {
            response = get(token, gatewayPricer_Shop_Private);
        } else {
            response = get(gatewayPricer_Shop);
        }
        verifyStatusCode200("API Get-all Shop Private is DEAD");

        //Get Duration of Shop Package
        String tempQueryDuration = "$.packages.%s." + shopAction + "[*]" +
                "[?(@.action_type == '" + shopAction + "' " +
                "&& @.category_id == %s" +
                ")].duration";
        List<Integer> durationPackage;
        String queryDuration = "";

        for (int i = 0; i < cateShopIdList.size(); i++) {
            cateShopId = cateShopIdList.get(i);
            if (cateShopId.equalsIgnoreCase("5000")) {
                isShopELT = true;
            } else {
                isShopELT = false;
            }

            //For duration for each ShopCateId
            for (int duration : shopCreate_Durations) {
                if (isShopELT) {
                    if (duration != 1 && duration != 2 && duration != 3 && duration != 6) {
                        continue;
                    }
                } else {
                    if (duration != 1 && duration != 3 && duration != 6 && duration != 12) {
                        continue;
                    }
                }


                query = String.format(tempQuery, duration, cateShopId);

                //Get actualPrice in Shop
                actualPrice = getResponseData(query);         //Get Actual price by vnd, Dongtot, Promotion
                //If actualPrice is null in Shop -> check Shop Package

                if (actualPrice == null) {   //CSV != null, API == null
                    //SHOP IS SHOP PACKAGE
                    //1. Setup query to get all durations of Shop package
                    queryDuration = String.format(tempQueryDuration, cateShopId, cateShopId);
                    //1. Get Duration list
                    durationPackage = getResponseDataListInt(queryDuration);

                    if (durationPackage == null) {    //Not exist Price_Object > actualPrice = null
                        actualPrice = null;
                    } else {       //Exists duration > check actualPrice
                        if (durationPackage.contains(duration)) {
                            query = query.replace("$.shops", String.format("$.packages.%s." + shopAction + "", cateShopId.trim()));
                            actualPrice = getResponseData(query);
                        }
                    }
                }

                expectedPrice = getCSVData_ShopCreate_Price(cateShopId, duration, priceType);
                checkExpectedPriceEmpty();

                //If expectedPrice exists & actualPrice doesn't exist, then Chưa khai báo giá
                if (expectedPrice == null) {      //ExpectedPrice doesn't exist
                    if (actualPrice == null) {
                        continue;
                    } else {
                        if (isPassed) isPassed = false;
                        failedMsg += String.format("%s Cate[%s] Duration[%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                        ""
                                , shopAction, cateShopId, duration, actualPrice);
                    }
                } else {
                    if (actualPrice == null) {   //CSV != null, API == null
                        System.out.println("QUERY: " + query);
                        if (isPassed) isPassed = false;
                        failedMsg += String.format("%s Cate[%s] Duration[%s]: API[Chưa khai báo giá] <> CSV[%s]\n" +
                                        ""
                                , shopAction, cateShopId, duration, expectedPrice);
                    } else {   //CSV != null, API != null
                        try {
                            Assert.assertEquals(actualPrice, expectedPrice);
                        } catch (AssertionError error) {
                            if (isPassed) isPassed = false;
                            failedMsg += String.format("Cate[%s] Duration[%s]: API[%s] <> CSV[%s]\n" +
                                            ""
                                    , shopAction, cateShopId, duration, actualPrice, expectedPrice);
                        }
                    }
                }
            }
        }
        writeResultToFileText(shopAction.toUpperCase() + " Price" + priceType.toUpperCase() + " FAILED: \n");
        Assert.assertTrue(isPassed, shopAction.toUpperCase() + " Price" + priceType.toUpperCase() + " FAILED: \n" +
                "" + failedMsg);
    }


    //---------- Shop Private Extend public ----------

    //---------- Shop Create Get all private ----------

    public static void verifyShopPrivateCreatePrice_VND() {
        verifyShopPrivateCreatePrice(getUserToken(), "vnd");
    }

    public static void verifyShopPrivateCreatePrice_DongTot() {
        verifyShopPrivateCreatePrice(getUserToken(), "dongtot");
    }

    public static void verifyShopPrivateCreatePrice_Promotion() {
        verifyShopPrivateCreatePrice(getUserToken(), "promotion");
    }

    private static void verifyShopPrivateCreatePrice(String token, String priceType) {
        String cateShopId = "";
        boolean isPassed = true;

        //Init query
        String tempQuery = "";
        switch (priceType.toLowerCase()) {
            case "vnd":
                tempQuery = "$.shops[*][?(@.action_type == 'shop_create' && @.duration == %s && @.category_id == '%s')].price.vnd";
                break;
            case "dongtot":
            case "credit":
                tempQuery = "$.shops[*][?(@.action_type == 'shop_create' && @.duration == %s && @.category_id == '%s')].price.credit";
                break;
            case "promotion":
                tempQuery = "$.shops[*][?(@.action_type == 'shop_create' && @.duration == %s && @.category_id == '%s')].price.promotion";
                break;
        }

        //Query to get duration of the cateShopId
        response = get(token, gatewayPricer_Shop_Private);
        verifyStatusCode200("API Get-all Shop Private is DEAD");

        for (int i = 0; i < cateShopIdList.size(); i++) {
            cateShopId = cateShopIdList.get(i);

            //For duration for each ShopCateId
            for (int duration : shopCreate_Durations) {
                query = String.format(tempQuery, duration, cateShopId);

                actualPrice = getResponseData(response, query);         //Get Actual price by vnd, Dongtot, Promotion
                try {
                    Assert.assertNotNull(actualPrice);
                } catch (AssertionError es) {
                    if (isPassed)
                        isPassed = false;
                    failedMsg += String.format("Cate[%s] Duration[%s]: API[Chưa khai báo giá]\n" +
                                    ""
                            , cateShopId, duration, actualPrice);
                    continue;
                }

                expectedPrice = getCSVData_ShopCreate_Price(cateShopId, duration, priceType);
                checkExpectedPriceEmpty();
                //Check price of CateID & Duration exists on CSV
                try {
                    Assert.assertNotNull(expectedPrice);
                } catch (AssertionError ex) {
                    if (isPassed)
                        isPassed = false;

                    if (isNullActualPrice()) //API null, CSV not found -> price type is NOT AVAILABLE
                        failedMsg += String.format("Shop Cate[%s] Duration[%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                        ""
                                , cateShopId, duration, actualPrice);
                    continue;
                }

                //Compare price of API with price of CSV
                try {
                    Assert.assertEquals(actualPrice, expectedPrice);
                } catch (AssertionError error) {
                    if (isPassed)
                        isPassed = false;
                    failedMsg += String.format("Shop Cate[%s] Duration[%s]: API[%s] <> CSV[%s]\n" +
                                    ""
                            , cateShopId, duration, actualPrice, expectedPrice);
                }
            }
        }
        writeResultToFileText("Shop Create Private Price" + priceType.toUpperCase() + " FAILED: \n");
        Assert.assertTrue(isPassed, "Shop Create Private Price" + priceType.toUpperCase() + " FAILED: \n" +
                "" + failedMsg);

    }


    //Get subcate ID
    public static List<String> getAllSubcateId() {
        response = get(gatewayCategories_GetAllSubCate);
        verifyStatusCode200("API get all Subcate ID.");
        query = "$.categories[*].subcategories[*].id";
        return getResponseDataList(response, query);
    }

    public static List<String> getAllSubcateId(String parentCateID) {
        response = get(gatewayCategories_GetAllSubCate);
        verifyStatusCode200("API get all Subcate ID of CateID:" + parentCateID);
        query = "$.categories[*].subcategories[*][?(@.parent == '" + parentCateID + "')].id";
        return getResponseDataList(response, query);
    }

    public static List<String> getAllSubcateName() {
        response = get(gatewayCategories_GetAllSubCate);
        verifyStatusCode200("API get all Subcate Name.");
        query = "$.categories[*].subcategories[*].name";
        return getResponseDataList(response, query);
    }

    private static List<String> getAllCateShopId() {
        response = get(gatewayShop_GetShopCate);
        verifyStatusCode200("API get Shop CateID");
        debugResponse();

        List<String> cateList = new ArrayList<>();

        //get cate from body
        List<String> cateFromBody = new ArrayList<>();
        String[] cateId = getBodyString(response).split("\"\\:\\{\"preFix\"\\:");
        for (int i = 0; i < cateId.length - 1; i++) {
            cateFromBody.add(cateId[i].substring(cateId[i].length() - 5).replace("\"", ""));
        }

        //Get list of active shopcate
        List<Boolean> cateShopActiveList = getResponseDataListBoolean(response, "$..isActive");

        for (int i = 0; i < cateFromBody.size(); i++) {
            if (cateShopActiveList.get(i).toString().equalsIgnoreCase("true")) {
                cateList.add(cateFromBody.get(i));
            }
        }
        return cateList;
    }

    private static List<String> getAllRegions() {
        response = get(gatewayRegion_GetAll);
        debugResponse();
        List<String> regions = new ArrayList<>();

        List<String> cateFromBody = getResponseDataList(response, "$.regions");
        for (Object body : cateFromBody) {
            regions.add(body.toString().split("=\\{name=")[0].replace("{", ""));
        }
        return regions;
    }

    //READ DATA FROM CSV
    public static void downloadCSVToLocal(String sheetName) {
        final String googleSheetID = "1HV2d4F3IeeykJvdIMIPM9hEjI71K6MrIOhkiMLfcu9Y";        //auto-jobs@chotot.vn
        valueRange = SheetActions.getInstance().getValues(
                googleSheetID,
                sheetName + String.format("!%s1:%s1000", "A", "H")
        );
        allValue = valueRange.getValues();
        setSheetName(sheetName);
    }

    public static void getStaticDataToTest() {
        regionList = getAllRegions();
        subCateIdList = getAllSubcateId();
        subCateNameList = getAllSubcateName();
//        failedSubCateList = new ArrayList<>();
//        cateShopIdList = getAllCateShopId();      //VUHOANG MAINTENANCE: only get charge shop
        cateShopIdList = Arrays.asList("1000", "2000", "5000");
    }

    //CSV
    private static List<String> csvCateCol, csvShopCateCol, csvDurationCol, csvActionTypeCol, csvPriceVNDCol, csvPriceDTCol, csvPricePromotionCol, csvBumpType, csvAdFeatureType, csvRegionCol, csvStickyAdCol, csvCampionCol, csvLisitngFeeTypeCol, csvAdTypeCol;

    private static void initCsvColData() {
        csvCateCol = new ArrayList<>();
        csvShopCateCol = new ArrayList<>();
        csvDurationCol = new ArrayList<>();
        csvActionTypeCol = new ArrayList<>();
        csvPriceVNDCol = new ArrayList<>();
        csvPriceDTCol = new ArrayList<>();
        csvPricePromotionCol = new ArrayList<>();
        csvBumpType = new ArrayList<>();
        csvAdFeatureType = new ArrayList<>();
        csvRegionCol = new ArrayList<>();
        csvStickyAdCol = new ArrayList<>();
        csvCampionCol = new ArrayList<>();
        csvLisitngFeeTypeCol = new ArrayList<>();
        csvAdTypeCol = new ArrayList<>();
    }

    private static void loadCsvData_ShopPricer() {
        initCsvColData();
        for (int i = 1; i < allValue.size(); i++) {
            csvShopCateCol.add(getAllValueByIndex(i, 0));
            csvActionTypeCol.add(getAllValueByIndex(i, 1));
            csvDurationCol.add(getAllValueByIndex(i, 2));
            csvPriceVNDCol.add(getAllValueByIndex(i, 5));
            csvPriceDTCol.add(getAllValueByIndex(i, 6));
            csvPricePromotionCol.add(getAllValueByIndex(i, 7));
        }
    }

    private static void loadCsvData_BumpPricer() {
        initCsvColData();
        for (int i = 1; i < allValue.size(); i++) {
            csvBumpType.add(getAllValueByIndex(i, 0));
            csvCateCol.add(getAllValueByIndex(i, 1));
//            csvCampionCol.add(getAllValueByIndex(i, 2));
            csvPriceVNDCol.add(getAllValueByIndex(i, 3));
            csvPriceDTCol.add(getAllValueByIndex(i, 4));
            csvPricePromotionCol.add(getAllValueByIndex(i, 5));
        }
    }

    private static String getAllValueByIndex(int rowIndex, int colIndex) {
        try {
            return allValue.get(rowIndex).get(colIndex).toString().trim();
        } catch (IndexOutOfBoundsException ex) {

        }
        return "";
    }

    private static void loadCsvData_AdFeaturePricer() {
        initCsvColData();
        for (int i = 1; i < allValue.size(); i++) {         //CHUA XONG, GOOGLE THIEU TAB
            csvCateCol.add(getAllValueByIndex(i, 0));
            csvDurationCol.add(getAllValueByIndex(i, 2));
            csvAdFeatureType.add(getAllValueByIndex(i, 3));
            csvPriceVNDCol.add(getAllValueByIndex(i, 5));
            csvPriceDTCol.add(getAllValueByIndex(i, 6));
            csvPricePromotionCol.add(getAllValueByIndex(i, 7));
        }
    }

    private static void loadCsvData_StickyAdsPricer() {
        initCsvColData();
        for (int i = 1; i < allValue.size(); i++) {
            csvCateCol.add(getAllValueByIndex(i, 0));
            csvStickyAdCol.add(getAllValueByIndex(i, 1));
            csvDurationCol.add(getAllValueByIndex(i, 2));
            csvRegionCol.add(getAllValueByIndex(i, 3));
            csvPriceVNDCol.add(getAllValueByIndex(i, 5));
            csvPriceDTCol.add(getAllValueByIndex(i, 6));
            csvPricePromotionCol.add(getAllValueByIndex(i, 7));
        }
    }

    private static void loadCsvData_ListingFeePricer() {
        initCsvColData();
        for (int i = 1; i < allValue.size(); i++) {
            csvCateCol.add(getAllValueByIndex(i, 0));
            csvLisitngFeeTypeCol.add(getAllValueByIndex(i, 1));
            csvRegionCol.add(getAllValueByIndex(i, 2));
            csvAdTypeCol.add(getAllValueByIndex(i, 3));
            csvPriceVNDCol.add(getAllValueByIndex(i, 5));
            csvPriceDTCol.add(getAllValueByIndex(i, 6));
            csvPricePromotionCol.add(getAllValueByIndex(i, 7));
        }
    }

    //SHOP CREATE
    private static String getCSVData_ShopCreate_Price(String shopCateId, Object duration, String priceType) {
        loadCsvData_ShopPricer();
        for (int i = 0; i < csvShopCateCol.size(); i++) {
            if (csvShopCateCol.get(i).equalsIgnoreCase(shopCateId)
                    && csvDurationCol.get(i).equalsIgnoreCase(String.valueOf(duration))
                    && csvActionTypeCol.get(i).equalsIgnoreCase("shop_create")) {
                switch (priceType.toLowerCase()) {
                    case "vnd":
                        return csvPriceVNDCol.get(i);
                    case "dongtot":
                    case "credit":
                        return csvPriceDTCol.get(i);
                    case "promotion":
                        return csvPricePromotionCol.get(i);
                }
            }
        }
        for (int i = 0; i < csvShopCateCol.size(); i++) {
            if (csvShopCateCol.get(i).equalsIgnoreCase(shopCateId)
                    && csvDurationCol.get(i).equalsIgnoreCase(String.valueOf(duration))
                    && csvActionTypeCol.get(i).equalsIgnoreCase("shop_create")) {
                switch (priceType.toLowerCase()) {
                    case "vnd":
                        return csvPriceVNDCol.get(i);
                    case "dongtot":
                    case "credit":
                        return csvPriceDTCol.get(i);
                    case "promotion":
                        return csvPricePromotionCol.get(i);
                }
            }
        }
        return null;
    }

    //SHOP EXTEND
    private static String getCSVData_ShopExtend_Price(String shopCateId, Object duration, String priceType) {
        loadCsvData_ShopPricer();
        for (int i = 0; i < csvShopCateCol.size(); i++) {
            if (csvShopCateCol.get(i).equalsIgnoreCase(shopCateId)
                    && csvDurationCol.get(i).equalsIgnoreCase(String.valueOf(duration))
                    && csvActionTypeCol.get(i).equalsIgnoreCase("shop_extend")) {
                switch (priceType.toLowerCase()) {
                    case "vnd":
                        return csvPriceVNDCol.get(i);
                    case "dongtot":
                    case "credit":
                        return csvPriceDTCol.get(i);
                    case "promotion":
                        return csvPricePromotionCol.get(i);
                }
            }
        }
        return null;
    }

    //BUMP
    private static String getCSVData_Bump_Price(String cateId, String bumpType, String priceType) {
        loadCsvData_BumpPricer();
        for (int i = 0; i < csvCateCol.size(); i++) {
            if (csvCateCol.get(i).equalsIgnoreCase(cateId)
                    && csvBumpType.get(i).equalsIgnoreCase(String.valueOf(bumpType))) {
                switch (priceType.toLowerCase()) {
                    case "vnd":
                        return csvPriceVNDCol.get(i);
                    case "dongtot":
                    case "credit":
                        return csvPriceDTCol.get(i);
                    case "promotion":
                        return csvPricePromotionCol.get(i);
                }
            }
        }
        return null;
    }

    //AD FEATURE
    private static String getCSVData_AdFeature_Price(String cateId, String adFeatureType, Object duration, String priceType) {
        loadCsvData_AdFeaturePricer();
        for (int i = 0; i < csvCateCol.size(); i++) {
            if (csvCateCol.get(i).equalsIgnoreCase(cateId)
                    && csvAdFeatureType.get(i).equalsIgnoreCase(adFeatureType)
                    && csvDurationCol.get(i).equalsIgnoreCase(String.valueOf(duration))) {
                switch (priceType.toLowerCase()) {
                    case "vnd":
                        return csvPriceVNDCol.get(i);
                    case "dongtot":
                    case "credit":
                        return csvPriceDTCol.get(i);
                    case "promotion":
                        return csvPricePromotionCol.get(i);
                }
            }
        }
        return null;
    }

    private static String getCSVData_StickyAds_Price(String subCateId, String stickyad, String region, Object duration, String priceType) {
        loadCsvData_StickyAdsPricer();
        for (int i = 0; i < csvCateCol.size(); i++) {
            if (csvCateCol.get(i).equalsIgnoreCase(subCateId)
                    && csvRegionCol.get(i).equalsIgnoreCase(region)
                    && csvDurationCol.get(i).equalsIgnoreCase(String.valueOf(duration))
                    && csvStickyAdCol.get(i).equalsIgnoreCase(stickyad)
            ) {
                switch (priceType.toLowerCase()) {
                    case "vnd":
                        return csvPriceVNDCol.get(i);
                    case "dongtot":
                    case "credit":
                        return csvPriceDTCol.get(i);
                    case "promotion":
                        return csvPricePromotionCol.get(i);
                }
            }
        }
        return null;
    }

    private static String getPriceCSVByCate_ListingFee_SellAd_Private(String subCateId, String region, String priceType, String actionType) {
        loadCsvData_ListingFeePricer();
        for (int i = 0; i < csvCateCol.size(); i++) {
            if (csvLisitngFeeTypeCol.get(i).equalsIgnoreCase(actionType))
                continue;

            if (csvCateCol.get(i).equalsIgnoreCase(subCateId)
                    && csvRegionCol.get(i).equalsIgnoreCase(region)
                    && csvAdTypeCol.get(i).equalsIgnoreCase("s")
            ) {
                switch (priceType.toLowerCase()) {
                    case "vnd":
                        return csvPriceVNDCol.get(i);
                    case "dongtot":
                    case "credit":
                        return csvPriceDTCol.get(i);
                    case "promotion":
                        return csvPricePromotionCol.get(i);
                }
            }
        }
        return null;
    }

    private static String getPriceCSVByCate_ListingFee_SellAd_Private_AllRegions(String subCateId, String priceType, String actionType) {
        loadCsvData_ListingFeePricer();
        for (int i = 0; i < csvCateCol.size(); i++) {
            if (csvLisitngFeeTypeCol.get(i).equalsIgnoreCase(actionType))
                continue;

            if (csvCateCol.get(i).equalsIgnoreCase(subCateId)
                    && csvRegionCol.get(i).equalsIgnoreCase("")
                    && csvAdTypeCol.get(i).equalsIgnoreCase("s")
            ) {
                switch (priceType.toLowerCase()) {
                    case "vnd":
                        return csvPriceVNDCol.get(i);
                    case "dongtot":
                    case "credit":
                        return csvPriceDTCol.get(i);
                    case "promotion":
                        return csvPricePromotionCol.get(i);
                }
            }
        }
        return null;
    }


    private static String getPriceCSVByCate_ListingFee_SellAd_Private_AllRegions_Pricer(String subCateId, String priceType, String actionType) {
        loadCsvData_ListingFeePricer();
        for (int i = 0; i < csvCateCol.size(); i++) {
//            if (csvLisitngFeeTypeCol.get(i).equalsIgnoreCase("shop_to_chotot"))
//                continue;

            if (csvLisitngFeeTypeCol.get(i).equalsIgnoreCase(actionType)
                    && csvCateCol.get(i).equalsIgnoreCase(subCateId)
                    && csvRegionCol.get(i).equalsIgnoreCase("")
                    && csvAdTypeCol.get(i).equalsIgnoreCase("s")
            ) {
                switch (priceType.toLowerCase()) {
                    case "vnd":
                        return csvPriceVNDCol.get(i);
                    case "dongtot":
                    case "credit":
                        return csvPriceDTCol.get(i);
                    case "promotion":
                        return csvPricePromotionCol.get(i);
                }
            }
        }
        return null;
    }

    private static String getPriceCSVByCate_ListingFee_SellAd_Pricer(String subCateId, String region, String priceType, String actionType) {
        loadCsvData_ListingFeePricer();
        for (int i = 0; i < csvCateCol.size(); i++) {
            if (csvLisitngFeeTypeCol.get(i).equalsIgnoreCase(actionType)
                    && csvCateCol.get(i).equalsIgnoreCase(subCateId)
                    && csvRegionCol.get(i).equalsIgnoreCase(region)
                    && csvAdTypeCol.get(i).equalsIgnoreCase("s")
            ) {
                switch (priceType.toLowerCase()) {
                    case "vnd":
                        return csvPriceVNDCol.get(i);
                    case "dongtot":
                    case "credit":
                        return csvPriceDTCol.get(i);
                    case "promotion":
                        return csvPricePromotionCol.get(i);
                }
            }
        }
        return null;
    }

    private static String getPriceCSVByCate_ListingFee_RentAd_Pricer(String subCateId, String region, String priceType, String actionType) {
        loadCsvData_ListingFeePricer();
        for (int i = 0; i < csvCateCol.size(); i++) {
            if (csvLisitngFeeTypeCol.get(i).equalsIgnoreCase(actionType)
                    && csvCateCol.get(i).equalsIgnoreCase(subCateId)
                    && csvRegionCol.get(i).equalsIgnoreCase(region)
                    && csvAdTypeCol.get(i).equalsIgnoreCase("u")
            ) {
                switch (priceType.toLowerCase()) {
                    case "vnd":
                        return csvPriceVNDCol.get(i);
                    case "dongtot":
                    case "credit":
                        return csvPriceDTCol.get(i);
                    case "promotion":
                        return csvPricePromotionCol.get(i);
                }
            }
        }
        return null;
    }

    private static String getPriceCSVByCate_ListingFee_SellAd_Pro(String subCateId, String region, String priceType) {
        loadCsvData_ListingFeePricer();
        for (int i = 0; i < csvCateCol.size(); i++) {
            if (csvLisitngFeeTypeCol.get(i).equalsIgnoreCase("shop_to_chotot")) {
                if (csvCateCol.get(i).equalsIgnoreCase(subCateId)
                        && csvRegionCol.get(i).equalsIgnoreCase(region)
                        && csvAdTypeCol.get(i).equalsIgnoreCase("s")
                ) {
                    switch (priceType.toLowerCase()) {
                        case "vnd":
                            return csvPriceVNDCol.get(i);
                        case "dongtot":
                        case "credit":
                            return csvPriceDTCol.get(i);
                        case "promotion":
                            return csvPricePromotionCol.get(i);
                    }
                }
            }
        }
        return null;
    }

    private static String getPriceCSVByCate_ListingFee_SellAd_Pro_AllRegions(String subCateId, String priceType) {
        loadCsvData_ListingFeePricer();
        for (int i = 0; i < csvCateCol.size(); i++) {
            if (csvLisitngFeeTypeCol.get(i).equalsIgnoreCase("shop_to_chotot")) {
                if (csvCateCol.get(i).equalsIgnoreCase(subCateId)
                        && csvRegionCol.get(i).equalsIgnoreCase("")
                        && csvAdTypeCol.get(i).equalsIgnoreCase("s")
                ) {
                    switch (priceType.toLowerCase()) {
                        case "vnd":
                            return csvPriceVNDCol.get(i);
                        case "dongtot":
                        case "credit":
                            return csvPriceDTCol.get(i);
                        case "promotion":
                            return csvPricePromotionCol.get(i);
                    }
                }
            }
        }
        return null;
    }


    private static String getPriceCSVByCate_ListingFee4Rent_SellAd_Private(String subCateId, String region, String priceType, String actionType) {
        loadCsvData_ListingFeePricer();
        for (int i = 0; i < csvCateCol.size(); i++) {
            if (csvLisitngFeeTypeCol.get(i).equalsIgnoreCase(actionType))
                continue;

            if (!csvAdTypeCol.get(i).equalsIgnoreCase("u"))
                continue;

            if (csvCateCol.get(i).equalsIgnoreCase(subCateId)
                    && csvRegionCol.get(i).equalsIgnoreCase(region)
                    && csvAdTypeCol.get(i).equalsIgnoreCase("u")    //No need
            ) {
                switch (priceType.toLowerCase()) {
                    case "vnd":
                        return csvPriceVNDCol.get(i);
                    case "dongtot":
                    case "credit":
                        return csvPriceDTCol.get(i);
                    case "promotion":
                        return csvPricePromotionCol.get(i);
                }
            }
        }
        return null;
    }

    private static String getPriceCSVByCate_ListingFee4Rent_SellAd_Pro(String subCateId, String region, String priceType) {
        loadCsvData_ListingFeePricer();
        for (int i = 0; i < csvCateCol.size(); i++) {
            if (csvLisitngFeeTypeCol.get(i).equalsIgnoreCase("shop_to_chotot")) {
                if (csvCateCol.get(i).equalsIgnoreCase(subCateId)
                        && csvRegionCol.get(i).equalsIgnoreCase(region)
                        && csvAdTypeCol.get(i).equalsIgnoreCase("u")
                ) {
                    switch (priceType.toLowerCase()) {
                        case "vnd":
                            return csvPriceVNDCol.get(i);
                        case "dongtot":
                        case "credit":
                            return csvPriceDTCol.get(i);
                        case "promotion":
                            return csvPricePromotionCol.get(i);
                    }
                }
            }
        }
        return null;
    }


    private static void writeResultToFileText(String title) {
        if (COLLECT_PRICERRESULT_TOFILE) {
            String FILE_PATH = System.getProperty("user.dir") + "/data/pricer/CheckPrice_Pricer_Results.txt";
            PrintWriter writer = null;
            try {
                writer = new PrintWriter(new BufferedWriter(new FileWriter(FILE_PATH, true)));  //APPEND
                writer.println("=======================================================================");
                writer.println("URL : " + getURL());
                writer.println("SHEET NAME: " + getSheetName().toUpperCase());
                writer.println(title);
                writer.println(failedMsg);
                writer.println("\n");
                writer.flush();
                writer.close();
            } catch (Exception e) {
            }
        }
    }

    private static void writeDiffKeysToFileText(String actualURL, String expectedURL, List<String> diffKeys) {
        if (COLLECT_PRICERRESULT_TOFILE) {
            String FILE_PATH = System.getProperty("user.dir") + "/data/pricer/CheckPrice_Pricer_Results.txt";
            PrintWriter writer = null;
            try {
                writer = new PrintWriter(new BufferedWriter(new FileWriter(FILE_PATH, true)));  //APPEND
                writer.println("=======================================================================");
                writer.println("CHECK DIFFERENT KEYS of RESPONSE BODY");
                writer.println("ACTUAL URL: " + actualURL);
                writer.println("EXPECTED URL : " + expectedURL);
                writer.println("DIFFERENT KEYS: " + diffKeys);
                writer.println("\n");
                writer.flush();
                writer.close();
            } catch (Exception e) {
            }
        }
    }

    private static void checkExpectedPriceEmpty() {
        checkExpectedPriceEmpty("");
    }

    private static void checkExpectedPriceEmpty(String priceType) {
        if (expectedPrice != null) {
            if (expectedPrice.isEmpty()) {
                expectedPrice = "0";
            }
        }

        if (priceType.equalsIgnoreCase("promotion")) {
            if (expectedPrice == null || expectedPrice.isEmpty()) {
                expectedPrice = "0";
            }
        }
    }


    private static boolean isNullActualPrice() {
        return !actualPrice.isEmpty();
    }


    //MongoDB
    public static void verifyApiDb_allAPIs(String cate) {
        boolean isPassed = true;
        verifyApiDb_ListingFee(cate);
        verifyApiDb_ListingFeeRent(cate);
        verifyApiDb_ListingFee_Internal(cate);
        verifyApiDb_ListingFeeRent_Internal(cate);
        verifyApiDb_ListingFee_GoldPot(cate);
        verifyApiDb_ListingFeeRent_GoldPot(cate);
        verifyApiDb_ListingFee_Pricer(cate);
        verifyApiDb_ListingFeeRent_Pricer(cate);
        verifyApiDb_ListingFee_Services(cate);
        verifyApiDb_ListingFeeRent_Services(cate);
        verifyApiDb_ShopCreate();
        verifyApiDb_ShopExtend();
        verifyApiDb_ShopExtend_Private();
        verifyApiDb_ShopCreate_Private();
        verifyApiDb_ShopExtend_Package();
        verifyApiDb_ShopCreate_Package();
//        verifyApiDb_StickyAds();
//        verifyApiDb_StickyAds_Internal();
    }


    public static boolean verifyApiDb_ShopCreate_Package() {
        boolean isPassed = true;
        failedMsg += "SHOP CREATE PACKAGE 1000: \n";
        failedMsg += "URL: " + String.format(gatewayPricer_ShopPackage_Create, getUserAccountID(), 1000, 3) + "\n";

        response = get(String.format(gatewayPricer_ShopPackage_Create, getUserAccountID(), 1000, 3));
        verifyStatusCode200("API Get-all Shop Private is DEAD");

        connectToDBPricing("shop_create");
        for (String price : priceTypes) {
            //Get API
            query = "$.price." + price;

            //Listing Fee
            initQuery();
            addQuery("category_id", 1000);
            addQuery("action_type", "shop_create");
            addQuery("duration", 3);

            try {
                expectedPrice = getPriceFromDB(price);
            } catch (NoSuchElementException a) {
                if (!actualPrice.isEmpty()) {
                    if (isPassed)
                        isPassed = false;
                    failedMsg += String.format("[%s]: DB[NOT FOUND] <> API[%s]\n" +
                            "", price);
                }
            }

            try {
                Assert.assertEquals(actualPrice, expectedPrice);
            } catch (AssertionError ex) {
                if (isPassed)
                    isPassed = false;
                failedMsg += String.format("[%s]: DB[%s] <> API[%s]\n" +
                        "", price, expectedPrice, actualPrice);
            }
        }
        failedMsg += "============================================\n\n";
        return isPassed;
    }

    public static boolean verifyApiDb_ShopExtend_Package() {
        boolean isPassed = true;
        failedMsg += "SHOP EXTEND PACKAGE 1000: \n";
        failedMsg += "URL: " + String.format(gatewayPricer_ShopPackage_Extend, getUserAccountID(), 1000, 3) + "\n";

        response = get(String.format(gatewayPricer_ShopPackage_Extend, getUserAccountID(), 1000, 3));
        verifyStatusCode200("API Get-all Shop Private is DEAD");

        connectToDBPricing("shop_extend");
        for (String price : priceTypes) {
            //Get API
            query = "$.price." + price;

            //Listing Fee
            initQuery();
            addQuery("category_id", 1000);
            addQuery("action_type", "shop_extend");
            addQuery("duration", 3);

            try {
                expectedPrice = getPriceFromDB(price);
            } catch (NoSuchElementException a) {
                if (!actualPrice.isEmpty()) {
                    if (isPassed)
                        isPassed = false;
                    failedMsg += String.format("[%s]: DB[NOT FOUND] <> API[%s]\n" +
                            "", price);
                }
            }

            try {
                Assert.assertEquals(actualPrice, expectedPrice);
            } catch (AssertionError ex) {
                if (isPassed)
                    isPassed = false;
                failedMsg += String.format("[%s]: DB[%s] <> API[%s]\n" +
                        "", price, expectedPrice, actualPrice);
            }
        }
        failedMsg += "============================================\n\n";
        return isPassed;
    }

    public static boolean verifyApiDb_ShopExtend_Private() {
        boolean isPassed = true;
        failedMsg += "SHOP EXTEND PRIVATE 1000: \n";
        failedMsg += "URL: " + gatewayPricer_Shop_Private + "\n";

        response = get(getUserToken(), gatewayPricer_Shop_Private);
        verifyStatusCode200("API Get-all Shop Private is DEAD");

        connectToDBPricing("shop_extend");
        for (String price : priceTypes) {
            //Get API
            query = "$.shops[*][?(@.action_type == 'shop_extend' && @.duration == 3 && @.category_id == '1000')].price." + price;

            //Listing Fee
            initQuery();
            addQuery("category_id", 1000);
            addQuery("action_type", "shop_extend");
            addQuery("duration", 3);

            try {
                expectedPrice = getPriceFromDB(price);
            } catch (NoSuchElementException a) {
                if (!actualPrice.isEmpty()) {
                    if (isPassed)
                        isPassed = false;
                    failedMsg += String.format("[%s]: DB[NOT FOUND] <> API[%s]\n" +
                            "", price);
                }
            }

            try {
                Assert.assertEquals(actualPrice, expectedPrice);
            } catch (AssertionError ex) {
                if (isPassed)
                    isPassed = false;
                failedMsg += String.format("[%s]: DB[%s] <> API[%s]\n" +
                        "", price, expectedPrice, actualPrice);
            }
        }
        failedMsg += "============================================\n\n";
        return isPassed;
    }

    public static boolean verifyApiDb_ShopCreate_Private() {
        boolean isPassed = true;
        failedMsg += "SHOP CREATE PRIVATE 1000: \n";
        failedMsg += "URL: " + gatewayPricer_Shop_Private + "\n";

        response = get(getUserToken(), gatewayPricer_Shop_Private);
        verifyStatusCode200("API Get-all Shop Private is DEAD");

        connectToDBPricing("shop_create");
        for (String price : priceTypes) {
            //Get API
            query = "$.shops[*][?(@.action_type == 'shop_create' && @.duration == 3 && @.category_id == '1000')].price." + price;

            //Listing Fee
            initQuery();
            addQuery("category_id", 1000);
            addQuery("action_type", "shop_create");
            addQuery("duration", 3);

            try {
                expectedPrice = getPriceFromDB(price);
            } catch (NoSuchElementException a) {
                if (!actualPrice.isEmpty()) {
                    if (isPassed)
                        isPassed = false;
                    failedMsg += String.format("[%s]: DB[NOT FOUND] <> API[%s]\n" +
                            "", price);
                }
            }

            try {
                Assert.assertEquals(actualPrice, expectedPrice);
            } catch (AssertionError ex) {
                if (isPassed)
                    isPassed = false;
                failedMsg += String.format("[%s]: DB[%s] <> API[%s]\n" +
                        "", price, expectedPrice, actualPrice);
            }
        }
        failedMsg += "============================================\n\n";
        return isPassed;
    }

    public static boolean verifyApiDb_ShopExtend() {
        boolean isPassed = true;
        failedMsg += "SHOP EXTEND 1000: \n";
        failedMsg += "URL: " + gatewayPricer_Shop + "\n";

        response = get(gatewayPricer_Shop);
        verifyStatusCode200("API SHOP");

        connectToDBPricing("shop_extend");
        for (String price : priceTypes) {
            //Get API
            query = "$.shops[*][?(@.action_type == 'shop_extend' && @.duration == 3 && @.category_id == '1000')].price." + price;

            //Listing Fee
            initQuery();
            addQuery("category_id", 1000);
            addQuery("action_type", "shop_extend");
            addQuery("duration", 3);

            try {
                expectedPrice = getPriceFromDB(price);
            } catch (NoSuchElementException a) {
                if (!actualPrice.isEmpty()) {
                    if (isPassed)
                        isPassed = false;
                    failedMsg += String.format("[%s]: DB[NOT FOUND] <> API[%s]\n" +
                            "", price);
                }
            }

            try {
                Assert.assertEquals(actualPrice, expectedPrice);
            } catch (AssertionError ex) {
                if (isPassed)
                    isPassed = false;
                failedMsg += String.format("[%s]: DB[%s] <> API[%s]\n" +
                        "", price, expectedPrice, actualPrice);
            }
        }
        failedMsg += "============================================\n\n";
        return isPassed;
    }

    public static boolean verifyApiDb_ShopCreate() {
        boolean isPassed = true;
        failedMsg += "SHOP CREATE 1000: \n";
        failedMsg += "URL: " + gatewayPricer_Shop + "\n";

        response = get(gatewayPricer_Shop);
        verifyStatusCode200("API SHOP");

        connectToDBPricing("shop_create");
        for (String price : priceTypes) {
            //Get API
            query = "$.shops[*][?(@.action_type == 'shop_create' && @.duration == 3 && @.category_id == '1000')].price." + price;

            //Listing Fee
            initQuery();
            addQuery("category_id", 1000);
            addQuery("action_type", "shop_create");
            addQuery("duration", 3);

            try {
                expectedPrice = getPriceFromDB(price);
            } catch (NoSuchElementException a) {
                if (!actualPrice.isEmpty()) {
                    if (isPassed)
                        isPassed = false;
                    failedMsg += String.format("[%s]: DB[NOT FOUND] <> API[%s]\n" +
                            "", price);
                }
            }

            try {
                Assert.assertEquals(actualPrice, expectedPrice);
            } catch (AssertionError ex) {
                if (isPassed)
                    isPassed = false;
                failedMsg += String.format("[%s]: DB[%s] <> API[%s]\n" +
                        "", price, expectedPrice, actualPrice);
            }
        }
        failedMsg += "============================================\n\n";
        return isPassed;
    }

    public static boolean verifyApiDb_ListingFeeRent_Services(String cate) {
        boolean isPassed = true;
        failedMsg += "Listing Fee SERVICES SEll Ad 1010 13000: \n";
        failedMsg += "URL: " + String.format(gatewayPricer_ListingFee, 13000, cate) + "\n";

        response = get(userToken, String.format(gatewayPricer_ListingFee4Rent, 13000, cate));
        verifyStatusCode200("API Listing Fee /pricer/get-all is dead");

        connectToDBPricing("insert_ads");
        for (String price : priceTypes) {
            //Get API
            query = "$.insert_ads_with_regions[*][?((@.action_type == 'insertad' || @.action_type == 'insert_ad') && @.ad_type == 'u')].price." + price;

            //Listing Fee
            initQuery();
            addQuery("category_id", Integer.valueOf(cate));
            addQuery("action_type", "insert_ad");
            addQuery("ad_type", "u");
            addQuery("region", 13000);

            try {
                expectedPrice = getPriceFromDB(price);
            } catch (NoSuchElementException a) {
                if (!actualPrice.isEmpty()) {
                    if (isPassed)
                        isPassed = false;
                    failedMsg += String.format("[%s]: DB[NOT FOUND] <> API[%s]\n" +
                            "", price);
                }
            }

            try {
                Assert.assertEquals(actualPrice, expectedPrice);
            } catch (AssertionError ex) {
                if (isPassed)
                    isPassed = false;
                failedMsg += String.format("[%s]: DB[%s] <> API[%s]\n" +
                        "", price, expectedPrice, actualPrice);
            }
        }
        failedMsg += "============================================\n\n";
        return isPassed;
    }

    public static boolean verifyApiDb_ListingFee_Services(String cate) {
        boolean isPassed = true;
        failedMsg += "Listing Fee SERVICES SEll Ad 1010 13000: \n";
        failedMsg += "URL: " + String.format(gatewayPricer_ListingFee, 13000, cate) + "\n";


        response = get(userToken, String.format(gatewayPricer_ListingFee, 13000, cate));
        verifyStatusCode200("API Listing Fee /pricer/get-all is dead");

        connectToDBPricing("insert_ads");
        for (String price : priceTypes) {
            //Get API
            query = "$.insert_ads_with_regions[*][?((@.action_type == 'insertad' || @.action_type == 'insert_ad') && @.ad_type == 's')].price." + price;

            //Listing Fee
            initQuery();
            addQuery("category_id", Integer.valueOf(cate));
            addQuery("action_type", "insert_ad");
            addQuery("ad_type", "s");
            addQuery("region", 13000);

            try {
                expectedPrice = getPriceFromDB(price);
            } catch (NoSuchElementException a) {
                if (!actualPrice.isEmpty()) {
                    if (isPassed)
                        isPassed = false;
                    failedMsg += String.format("[%s]: DB[NOT FOUND] <> API[%s]\n" +
                            "", price);
                }
            }

            try {
                Assert.assertEquals(actualPrice, expectedPrice);
            } catch (AssertionError ex) {
                if (isPassed)
                    isPassed = false;
                failedMsg += String.format("[%s]: DB[%s] <> API[%s]\n" +
                        "", price, expectedPrice, actualPrice);
            }
        }
        failedMsg += "============================================\n\n";
        return isPassed;
    }

    public static boolean verifyApiDb_ListingFeeRent_Pricer(String cate) {
        boolean isPassed = true;
        failedMsg += "Listing Fee PRICER RENT Ad 1010 13000: \n";
        String URL = String.format(gatewayPricer_ListingFee4Rent_Private_PricerPrivate, getUserAccountID(), 13000, cate);
        failedMsg += "URL: " + URL + "\n";

        response = get(getUserToken(), URL);
        verifyStatusCode200("API Listing Fee /pricer/get-all is dead");

        connectToDBPricing("insert_ads");
        for (String price : priceTypes) {
            //Get API
            query = "$.price." + price;

            //Listing Fee
            initQuery();
            addQuery("category_id", Integer.valueOf(cate));
            addQuery("action_type", "insert_ad");
            addQuery("ad_type", "u");
            addQuery("region", 13000);

            try {
                expectedPrice = getPriceFromDB(price);
            } catch (NoSuchElementException a) {
                if (!actualPrice.isEmpty()) {
                    if (isPassed)
                        isPassed = false;
                    failedMsg += String.format("[%s]: DB[NOT FOUND] <> API[%s]\n" +
                            "", price);
                }
            }

            try {
                Assert.assertEquals(actualPrice, expectedPrice);
            } catch (AssertionError ex) {
                if (isPassed)
                    isPassed = false;
                failedMsg += String.format("[%s]: DB[%s] <> API[%s]\n" +
                        "", price, expectedPrice, actualPrice);
            }
        }
        failedMsg += "============================================\n\n";
        return isPassed;
    }

    public static boolean verifyApiDb_ListingFee_Pricer(String cate) {
        boolean isPassed = true;
        failedMsg += "Listing Fee PRICER SEll Ad 1010 13000: \n";
        String URL = String.format(gatewayPricer_ListingFee_Private_PricerInternal, getUserAccountID(), 13000, cate);
        failedMsg += "URL: " + URL + "\n";

        response = get(getUserToken(), URL);
        verifyStatusCode200("API Listing Fee /pricer/get-all is dead");

        connectToDBPricing("insert_ads");
        for (String price : priceTypes) {
            //Get API
            query = "$.price." + price;

            //Listing Fee
            initQuery();
            addQuery("category_id", Integer.valueOf(cate));
            addQuery("action_type", "insert_ad");
            addQuery("ad_type", "s");
            addQuery("region", 13000);

            try {
                expectedPrice = getPriceFromDB(price);
            } catch (NoSuchElementException a) {
                if (!actualPrice.isEmpty()) {
                    if (isPassed)
                        isPassed = false;
                    failedMsg += String.format("[%s]: DB[NOT FOUND] <> API[%s]\n" +
                            "", price);
                }
            }

            try {
                Assert.assertEquals(actualPrice, expectedPrice);
            } catch (AssertionError ex) {
                if (isPassed)
                    isPassed = false;
                failedMsg += String.format("[%s]: DB[%s] <> API[%s]\n" +
                        "", price, expectedPrice, actualPrice);
            }
        }
        failedMsg += "============================================\n\n";
        return isPassed;
    }

    public static boolean verifyApiDb_ListingFeeRent_GoldPot(String cate) {
        boolean isPassed = true;
        failedMsg += "Listing Fee GOLDPOT Rent Ad 1010 13000: \n";
        failedMsg += "URL: " + gatewayPricer_ListingFee_PTY_PricerGetAllListing_Internal + "\n";

        response = get(gatewayPricer_ListingFee_PTY_PricerGetAllListing_Internal);
        verifyStatusCode200("API Listing Fee /pricer/get-all is dead");

        connectToDBPricing("insert_ads");
        for (String price : priceTypes) {
            //Get API
            query = "$.price." + price;

            //Listing Fee
            initQuery();
            addQuery("category_id", Integer.valueOf(cate));
            addQuery("action_type", "insert_ad");
            addQuery("ad_type", "u");
            addQuery("region", 13000);

            try {
                expectedPrice = getPriceFromDB(price);
            } catch (NoSuchElementException a) {
                if (!actualPrice.isEmpty()) {
                    if (isPassed)
                        isPassed = false;
                    failedMsg += String.format("[%s]: DB[NOT FOUND] <> API[%s]\n" +
                            "", price);
                }
            }

            try {
                Assert.assertEquals(actualPrice, expectedPrice);
            } catch (AssertionError ex) {
                if (isPassed)
                    isPassed = false;
                failedMsg += String.format("[%s]: DB[%s] <> API[%s]\n" +
                        "", price, expectedPrice, actualPrice);
            }
        }
        failedMsg += "============================================\n\n";
        return isPassed;
    }

    public static boolean verifyApiDb_ListingFee_GoldPot(String cate) {
        boolean isPassed = true;
        failedMsg += "Listing Fee GOLDPOT Sell Ad 1010 13000: \n";
        failedMsg += "URL: " + gatewayPricer_ListingFee_PTY_PricerGetAllListing_Internal + "\n";

        response = get(gatewayPricer_ListingFee_PTY_PricerGetAllListing_Internal);
        verifyStatusCode200("API Listing Fee /pricer/get-all is dead");

        connectToDBPricing("insert_ads");
        for (String price : priceTypes) {
            //Get API
            query = "$.price." + price;

            //Listing Fee
            initQuery();
            addQuery("category_id", Integer.valueOf(cate));
            addQuery("action_type", "insert_ad");
            addQuery("ad_type", "s");
            addQuery("region", 13000);

            try {
                expectedPrice = getPriceFromDB(price);
            } catch (NoSuchElementException a) {
                if (!actualPrice.isEmpty()) {
                    if (isPassed)
                        isPassed = false;
                    failedMsg += String.format("[%s]: DB[NOT FOUND] <> API[%s]\n" +
                            "", price);
                }
            }

            try {
                Assert.assertEquals(actualPrice, expectedPrice);
            } catch (AssertionError ex) {
                if (isPassed)
                    isPassed = false;
                failedMsg += String.format("[%s]: DB[%s] <> API[%s]\n" +
                        "", price, expectedPrice, actualPrice);
            }
        }
        failedMsg += "============================================\n\n";
        return isPassed;
    }

    public static boolean verifyApiDb_ListingFeeRent_Internal(String cate) {
        boolean isPassed = true;
        failedMsg += "Listing Fee (Internal) Rent Ad 1010 13000: \n";
        failedMsg += "URL: " + gatewayPricer_ListingFee_PTY_PricerGetAllListing_Internal + "\n";

        response = get(gatewayPricer_ListingFee_PTY_PricerGetAllListing_Internal);
        verifyStatusCode200("API Listing Fee /pricer/get-all is dead");

        connectToDBPricing("insert_ads");
        for (String price : priceTypes) {
            //Get API
            query = "$.[?(@.category_id == " + cate + " " +
                    "&& @.ad_type == 'u' " +
                    "&& @.region_id == 13000 " +
                    "&& (@.action_type == 'insert_ad' || @.action_type == 'insertad'))].price." + price;

            //Listing Fee
            initQuery();
            addQuery("category_id", Integer.valueOf(cate));
            addQuery("action_type", "insert_ad");
            addQuery("ad_type", "u");
            addQuery("region", 13000);

            try {
                expectedPrice = getPriceFromDB(price);
            } catch (NoSuchElementException a) {
                if (!actualPrice.isEmpty()) {
                    if (isPassed)
                        isPassed = false;
                    failedMsg += String.format("[%s]: DB[NOT FOUND] <> API[%s]\n" +
                            "", price);
                }
            }

            try {
                Assert.assertEquals(actualPrice, expectedPrice);
            } catch (AssertionError ex) {
                if (isPassed)
                    isPassed = false;
                failedMsg += String.format("[%s]: DB[%s] <> API[%s]\n" +
                        "", price, expectedPrice, actualPrice);
            }
        }
        failedMsg += "============================================\n\n";
        return isPassed;
    }

    public static boolean verifyApiDb_ListingFee_Internal(String cate) {
        boolean isPassed = true;
        failedMsg += "Listing Fee (Internal) Sell Ad 1010 13000: \n";
        failedMsg += "URL: " + gatewayPricer_ListingFee_PTY_PricerGetAllListing_Internal + "\n";

        response = get(gatewayPricer_ListingFee_PTY_PricerGetAllListing_Internal);
        verifyStatusCode200("API Listing Fee /pricer/get-all is dead");

        connectToDBPricing("insert_ads");
        for (String price : priceTypes) {
            //Get API
            query = "$.[?(@.category_id == " + cate + " " +
                    "&& @.ad_type == 's' " +
                    "&& @.region_id == 13000 " +
                    "&& (@.action_type == 'insert_ad' || @.action_type == 'insertad'))].price." + price;

            //Listing Fee
            initQuery();
            addQuery("category_id", Integer.valueOf(cate));
            addQuery("action_type", "insert_ad");
            addQuery("ad_type", "s");
            addQuery("region", 13000);

            try {
                expectedPrice = getPriceFromDB(price);
            } catch (NoSuchElementException a) {
                if (!actualPrice.isEmpty()) {
                    if (isPassed)
                        isPassed = false;
                    failedMsg += String.format("[%s]: DB[NOT FOUND] <> API[%s]\n" +
                            "", price);
                }
            }

            try {
                Assert.assertEquals(actualPrice, expectedPrice);
            } catch (AssertionError ex) {
                if (isPassed)
                    isPassed = false;
                failedMsg += String.format("[%s]: DB[%s] <> API[%s]\n" +
                        "", price, expectedPrice, actualPrice);
            }
        }
        failedMsg += "============================================\n\n";
        return isPassed;
    }

    public static boolean verifyApiDb_ListingFeeRent(String cate) {
        boolean isPassed = true;
        failedMsg += "Listing Fee Rent Ad 1010 13000: \n";
        failedMsg += "URL: " + gatewayPricer_ListingFee_PricerGetAll_Public + "\n";

        response = get(gatewayPricer_ListingFee_PricerGetAll_Public);
        verifyStatusCode200("API Listing Fee /pricer/get-all is dead");

        connectToDBPricing("insert_ads");
        for (String price : priceTypes) {
            //Get API
            query = "$.insert_ads_with_regions[*][?(@.category_id == " + cate + " " +
                    "&& @.ad_type == 'u' " +
                    "&& @.region_id == 13000 " +
                    "&& (@.action_type == 'insert_ad' || @.action_type == 'insertad'))].price." + price;

            //Listing Fee
            initQuery();
            addQuery("category_id", Integer.valueOf(cate));
            addQuery("action_type", "insert_ad");
            addQuery("ad_type", "u");
            addQuery("region", 13000);

            try {
                expectedPrice = getPriceFromDB(price);
            } catch (NoSuchElementException a) {
                if (!actualPrice.isEmpty()) {
                    if (isPassed)
                        isPassed = false;
                    failedMsg += String.format("[%s]: DB[NOT FOUND] <> API[%s]\n" +
                            "", price);
                }
            }

            try {
                Assert.assertEquals(actualPrice, expectedPrice);
            } catch (AssertionError ex) {
                if (isPassed)
                    isPassed = false;
                failedMsg += String.format("[%s]: DB[%s] <> API[%s]\n" +
                        "", price, expectedPrice, actualPrice);
            }
        }
        failedMsg += "============================================\n\n";
        return isPassed;
    }

    public static boolean verifyApiDb_ListingFee(String cate) {
        boolean isPassed = true;
        failedMsg += "Listing Fee Sell Ad 1010 13000: \n";
        failedMsg += "URL: " + gatewayPricer_ListingFee_PricerGetAll_Public + "\n";

        response = get(gatewayPricer_ListingFee_PricerGetAll_Public);
        verifyStatusCode200("API Listing Fee /pricer/get-all is dead");

        connectToDBPricing("insert_ads");
        for (String price : priceTypes) {
            //Get API
            query = "$.insert_ads_with_regions[*][?(@.category_id == " + cate + " " +
                    "&& @.ad_type == 's' " +
                    "&& @.region_id == 13000 " +
                    "&& (@.action_type == 'insert_ad' || @.action_type == 'insertad'))].price." + price;

            //Listing Fee
            initQuery();
            addQuery("category_id", Integer.valueOf(cate));
            addQuery("action_type", "insert_ad");
            addQuery("ad_type", "s");
            addQuery("region", 13000);

            try {
                expectedPrice = getPriceFromDB(price);
            } catch (NoSuchElementException a) {
                if (!actualPrice.isEmpty()) {
                    if (isPassed)
                        isPassed = false;
                    failedMsg += String.format("[%s]: DB[NOT FOUND] <> API[%s]\n" +
                            "", price);
                }
            }

            try {
                Assert.assertEquals(actualPrice, expectedPrice);
            } catch (AssertionError ex) {
                if (isPassed)
                    isPassed = false;
                failedMsg += String.format("[%s]: DB[%s] <> API[%s]\n" +
                        "", price, expectedPrice, actualPrice);
            }
        }
        failedMsg += "============================================\n\n";
        return isPassed;
    }


    public static void verifyApiDb_AdFeature_GetAll() {
        boolean isPassed = true;
        failedMsg += "URL: " + gatewayPricer_Public_GetAllPrice + "\n";

        response = get(gatewayPricer_Public_GetAllPrice);
        verifyStatusCode200("API get Ad Feature Price");
        debugResponse();

        connectToDBPricing("ad_features");
        String queryCampainId = "";
        String queryPriceCampain = "";
        for (int i = 0; i < subCateIdList.size(); i++) {
            String subCateId = subCateIdList.get(i);
            for (String feature : adFeature_Features) {
                for (String duration : adFeature_Durations) {
                    for (String price : priceTypes) {
                        List<String> campaignIds, priceCampaign = new ArrayList<>(), priceAll = new ArrayList<>();

                        //Get campaignIds
                        queryCampainId = "$.ad_features[?(" +
                                "@.category_id == " + subCateId + " " +
                                "&& @.duration == " + duration + " " +
                                "&& @.feature == '" + feature + "'" +
                                ")].campaign_id";
                        campaignIds = getResponseDataList(queryCampainId);

                        //Get list of prices that are with campaign
                        for (String campaignId : campaignIds) {
                            queryPriceCampain = "$.ad_features[?(" +
                                    "@.category_id == " + subCateId + " " +
                                    "&& @.duration == " + duration + " " +
                                    "&& @.feature == '" + feature + "'" +
                                    "&& @.campaign_id == '" + campaignId + "'" +
                                    ")].price." + price;
                            priceCampaign.add(String.valueOf(getResponseDataInt(queryPriceCampain)).trim());
                        }
//                        priceCampaign = priceCampaign.stream().map(String :: trim).collect(Collectors.toList());

                        //Get all prices
                        query = "$.ad_features[?(" +
                                "@.category_id == " + subCateId + " " +
                                "&& @.duration == " + duration + " " +
                                "&& @.feature == '" + feature + "'" +
                                ")].price." + price;

                        List<Integer> priceIntAll = getResponseDataListInt(query);
                        for (int priceInt : priceIntAll) {
                            priceAll.add(String.valueOf(priceInt).trim());
                        }

                        //Get data from API
                        priceAll.removeAll(priceCampaign);

                        if (priceAll.size() == 0) {
                            actualPrice = null;
                        } else if (priceAll.size() > 1) {
                            if (isPassed) isPassed = false;
                            failedMsg += String.format("Ad Feature Type[%s] Price[%s] Cate[%s] DB[Duplicated Data][%s]\n" +
                                    "", feature, price, subCateId, priceAll.toString());

                            continue;
                        } else {   //priceAll == 1
                            actualPrice = priceAll.get(0);
                        }

                        //Get data from DB
                        initQuery();
                        addQuery("category_id", Integer.valueOf(subCateId));
                        addQuery("feature", feature);
                        addQuery("duration", Integer.parseInt(duration));

                        try {
                            expectedPrice = getPriceFromDB(price);
                        } catch (NoSuchElementException e) {
                            expectedPrice = null;
                        }


                        //If expectedPrice exists & actualPrice doesn't exist, then Chưa khai báo giá
                        if (expectedPrice == null) {      //ExpectedPrice doesn't exist
                            if (actualPrice == null) {
                                continue;
                            } else {
                                if (isPassed) isPassed = false;
                                failedMsg += String.format("Ad Feature Type[%s] Price[%s] Cate[%s] DB[NOT FOUND] <> API[%s]\n" +
                                        "", feature, price, subCateId, actualPrice);
                            }
                        } else {
                            if (actualPrice == null) {   //CSV != null, API == null
                                if (isPassed) isPassed = false;
                                failedMsg += String.format("Ad Feature Type[%s] Price[%s] Cate[%s] DB[%s] <> API[NOT FOUND]\n" +
                                        "", feature, price, subCateId, expectedPrice);
                            } else {   //CSV != null, API != null
                                try {
                                    Assert.assertEquals(actualPrice, expectedPrice);
                                } catch (AssertionError error) {
                                    if (isPassed) isPassed = false;
                                    failedMsg += String.format("Ad Feature Type[%s] Price[%s] Cate[%s] DB[%s] <> API[%s]\n" +
                                            "", feature, price, subCateId, expectedPrice, actualPrice);
                                    System.out.println("QUERY: " + query);
                                }
                            }
                        }
                    }
                }
            }
        }
        writeResultToFileText("Ad Feature COMPARE API <> Database: \n");
        Assert.assertTrue(isPassed, "AD FEATURE API <> Database FAILED: \n" +
                "" + failedMsg);
    }

    public static void verifyApiDb_Bump_GetAll() {
        boolean isPassed = true;

        query = "$.bump_price_unit.%s.%s";
        response = get(getUserToken(), gatewayPricer_Private_GetAllPrice_Bump);
        verifyStatusCode200("API get Bump Price");

        for (String bumpType : bump_Types) {
            connectToDBPricing(bumpType);

            for (int i = 0; i < subCateIdList.size(); i++) {
                String subCateId = subCateIdList.get(i);

                for (String price : priceTypes) {
                    //Get data from API
                    actualPrice = getResponseData(response, String.format(query, bumpType, subCateId) + "." + price);

                    //Get data from DB
                    setQuery("category_id", Integer.valueOf(subCateId));
                    expectedPrice = getPriceFromDB(price);

                    try {
                        Assert.assertEquals(actualPrice, expectedPrice);
                    } catch (AssertionError ex) {
                        if (isPassed)
                            isPassed = false;
                        failedMsg += String.format("Bump Type[%s] Price[%s] Cate[%s] DB[%s] <> API[%s]\n" +
                                "", bumpType, price, subCateId, expectedPrice, actualPrice);
                    }
                }
            }
        }

        Assert.assertTrue(isPassed, "BUMP API FAILED: \n" +
                "" + failedMsg);
    }

    private static void connectToDBPricing(String collection) {
        setDatabase("pricing");
        setCollection(collection);
    }

    private static String getPriceFromDB(String priceType) {
        switch (priceType.toUpperCase()) {
            case "VND":
                return getPriceVND();
            case "CREDIT":
            case "DONGTOT":
                return getPriceCredit();
            case "PROMOTION":
                return getPricePromotion();
        }
        return null;
    }

    //--------------------------------- CHECK NEW PRICER JS --------------------------------------------
    private static String gatewayPricerNew;
    private static String gatewayPricer = "";
    private static final String serviceNamePricerOld = "/pricer";
    private static String serviceNamePricerNew;
    private static boolean isPrivate = false;

    public static void setGatewayPricerURI_PrivateV1() {
        gatewayPricer = gatewayPricer_PrivateV1;
        isPrivate = true;
    }

    public static void setGatewayPricerURI_PrivateV2() {
        gatewayPricer = gatewayPricer_PrivateV2;
        isPrivate = true;
    }

    public static void setGatewayPricerURI_InternalV1() {
        gatewayPricer = gatewayPricer_InternalV1;
    }

    public static void setGatewayPricerURI_PublicV1() {
        gatewayPricer = gatewayPricer_Public;
    }

    public static void setServiceNamePricerNew(String serviceNamePricerNew) {
        Pricer_API_CheckPrice_Functions.serviceNamePricerNew = serviceNamePricerNew;
    }


    public static void comparePricerAPIJS(String url) {
        comparePricerAPIJS(url, false);
    }

    public static void comparePricerAPIJS(String url, boolean isUseAccountIDParam) {
        String URL = gatewayPricer + url;
        Assert.assertNotNull(serviceNamePricerNew, "NO SETUP URL SERIVCE FOR PRICER NEW");
        String URL_NEW = URL.replace(serviceNamePricerOld, serviceNamePricerNew);

        String token = getAccessTokenOfNewUser();

        if (isUseAccountIDParam) {
            URL = String.format(URL, global_accountID);
            URL_NEW = String.format(URL_NEW, global_accountID);
        }

        if (isPrivate) {
            responseTemp1 = get(token, URL);
            responseTemp2 = get(token, URL_NEW);
        } else {
            responseTemp1 = get(URL);
            responseTemp2 = get(URL_NEW);
        }

        debugResponse(responseTemp1);
        debugResponse(responseTemp2);

        verifyStatusCode200(responseTemp1, "Pricer", URL);
        verifyStatusCode200(responseTemp2, "Pricer", URL_NEW);
        Assert.assertTrue(compareResponseBody(responseTemp1, responseTemp2), "\n" + URL_NEW + "\n is differnt from [" + URL + "]");
    }


    //userType : private, pro, shop
    public static void compareResBody_Public(String urlA, String urlB) {
        responseTemp1 = get(urlA);
        responseTemp2 = get(urlB);

        verifyStatusCode200(responseTemp1, "Pricer", urlA);
        verifyStatusCode200(responseTemp2, "Pricer", urlB);

        Assert.assertTrue(compareResponseBody(responseTemp1, responseTemp2), "\n" + urlA + "\n is differnt from [" + urlB + "]");
    }

    public static void compareResBodyKey_Public(String urlA, String urlB) {
//        String token = getAccessTokenOfNewUser();

//        if(isUseAccountIDParam){
//            URL = String.format(URL, global_accountID);
//            URL_NEW = String.format(URL_NEW, global_accountID);
//        }
        responseTemp1 = get(urlA);
        responseTemp2 = get(urlB);

        verifyStatusCode200(responseTemp1, "Pricer", urlA);
        verifyStatusCode200(responseTemp2, "Pricer", urlB);
        List<String> diffKeys = getResBodyDiffKey(getBodyString(responseTemp1), getBodyString(responseTemp2));

        //Write test report when failed
        if (diffKeys.size() != 0) {
            writeDiffKeysToFileText(urlA, urlB, diffKeys);
        }

        Assert.assertTrue(diffKeys.size() == 0
                , "Compare Res Body keys, different keys are: " + diffKeys + "\n");

    }

    //---------
    //---------- Shop Extend public ----------
    @Deprecated
    private static void verifyShopExtendPrice(String priceType) {
        String cateShopId = "";
        boolean isPassed = true;

        //Init query
        String tempQuery = "";
        switch (priceType.toLowerCase()) {
            case "vnd":
                tempQuery = "$.shops[*][?(@.action_type == 'shop_extend' && @.duration == %s && @.category_id == '%s')].price.vnd";
                break;
            case "dongtot":
            case "credit":
                tempQuery = "$.shops[*][?(@.action_type == 'shop_extend' && @.duration == %s && @.category_id == '%s')].price.credit";
                break;
            case "promotion":
                tempQuery = "$.shops[*][?(@.action_type == 'shop_extend' && @.duration == %s && @.category_id == '%s')].price.promotion";
                break;
        }

        //Query to get duration of the cateShopId
        response = get(gatewayPricer_Shop);
        verifyStatusCode200("API Get-all Shop Private is DEAD");

        for (int i = 0; i < cateShopIdList.size(); i++) {
            cateShopId = cateShopIdList.get(i);

            if (cateShopId.equalsIgnoreCase("5000"))
                continue;

            //For duration for each ShopCateId
            for (int duration : shopExtend_Durations) {
                query = String.format(tempQuery, duration, cateShopId);

                //SHOP PACKAGE
                if (cateShopId.equalsIgnoreCase("5000")) {
                    query = query.replace("$.shops", "$.packages.5000.shop_extend");
                }

                actualPrice = getResponseData(response, query);         //Get Actual price by vnd, Dongtot, Promotion
                expectedPrice = getCSVData_ShopExtend_Price(cateShopId, duration, priceType);
                checkExpectedPriceEmpty();

                //If expectedPrice exists & actualPrice doesn't exist, then Chưa khai báo giá
                if (expectedPrice == null) {      //ExpectedPrice doesn't exist
                    if (actualPrice == null) {
                        continue;
                    } else {
                        if (isPassed) isPassed = false;
                        failedMsg += String.format("Shop Cate[%s] Duration[%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                        ""
                                , cateShopId, duration, actualPrice);
                    }
                } else {
                    if (actualPrice == null) {   //CSV != null, API == null
                        if (isPassed) isPassed = false;
                        failedMsg += String.format("Cate[%s] Duration[%s]: API[Chưa khai báo giá] <> CSV[%s]\n" +
                                        ""
                                , cateShopId, duration, expectedPrice);
                    } else {   //CSV != null, API != null
                        try {
                            Assert.assertEquals(actualPrice, expectedPrice);
                        } catch (AssertionError error) {
                            if (isPassed) isPassed = false;
                            failedMsg += String.format("Shop Cate[%s] Duration[%s]: API[%s] <> CSV[%s]\n" +
                                            ""
                                    , cateShopId, duration, actualPrice, expectedPrice);
                        }
                    }
                }
            }
        }
        writeResultToFileText("Shop Extend " + priceType.toUpperCase() + " Price of Sell Ad is incorrect with cates: \n");
        Assert.assertTrue(isPassed, "Shop Extend " + priceType.toUpperCase() + " Price of Sell Ad is incorrect with cates: \n" +
                "" + failedMsg);
    }

    public static void verifyShopPrivateExtendPrice_VND() {
        verifyShopPrivateExtendPrice(getUserToken(), "vnd");
    }

    public static void verifyShopPrivateExtendPrice_DongTot() {
        verifyShopPrivateExtendPrice(getUserToken(), "dongtot");
    }

    public static void verifyShopPrivateExtendPrice_Promotion() {
        verifyShopPrivateExtendPrice(getUserToken(), "promotion");
    }

    private static void verifyShopPrivateExtendPrice(String token, String priceType) {
        String cateShopId = "";
        boolean isPassed = true;

        //Init query
        String tempQuery = "";
        switch (priceType.toLowerCase()) {
            case "vnd":
                tempQuery = "$.shops[*][?(@.action_type == 'shop_extend' && @.duration == %s && @.category_id == '%s')].price.vnd";
                break;
            case "dongtot":
            case "credit":
                tempQuery = "$.shops[*][?(@.action_type == 'shop_extend' && @.duration == %s && @.category_id == '%s')].price.credit";
                break;
            case "promotion":
                tempQuery = "$.shops[*][?(@.action_type == 'shop_extend' && @.duration == %s && @.category_id == '%s')].price.promotion";
                break;
        }

        //Query to get duration of the cateShopId
        response = get(token, gatewayPricer_Shop_Private);
        verifyStatusCode200("API Get-all Shop Private is DEAD");

        for (int i = 0; i < cateShopIdList.size(); i++) {
            cateShopId = cateShopIdList.get(i);

            //For duration for each ShopCateId
            for (int duration : shopExtend_Durations) {
                query = String.format(tempQuery, duration, cateShopId);
                actualPrice = getResponseData(response, query);         //Get Actual price by vnd, Dongtot, Promotion

                try {
                    Assert.assertNotNull(actualPrice);
                } catch (AssertionError es) {
                    if (isPassed)
                        isPassed = false;
                    failedMsg += String.format("Cate[%s] Duration[%s]: API[Chưa khai báo giá]\n" +
                                    ""
                            , cateShopId, duration, actualPrice);
                    continue;
                }

                expectedPrice = getCSVData_ShopExtend_Price(cateShopId, duration, priceType);
                checkExpectedPriceEmpty();
                //Check price of CateID & Duration exists on CSV
                try {
                    Assert.assertNotNull(expectedPrice);
                } catch (AssertionError ex) {
                    if (isPassed)
                        isPassed = false;

                    if (isNullActualPrice()) //API null, CSV not found -> price type is NOT AVAILABLE
                        failedMsg += String.format("Cate[%s] Duration[%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                        ""
                                , cateShopId, duration, actualPrice);
                    continue;
                }

                //Compare price of API with price of CSV
                try {
                    Assert.assertEquals(actualPrice, expectedPrice);
                } catch (AssertionError error) {
                    if (isPassed)
                        isPassed = false;
                    failedMsg += String.format("Cate[%s] Duration[%s]: API[%s] <> CSV[%s]\n" +
                                    ""
                            , cateShopId, duration, actualPrice, expectedPrice);
                }
            }
        }
        writeResultToFileText("Shop Private Extend " + priceType.toUpperCase() + " Price of Sell Ad is incorrect with cates: \n");
        Assert.assertTrue(isPassed, "Shop Private Extend " + priceType.toUpperCase() + " Price of Sell Ad is incorrect with cates: \n" +
                "" + failedMsg);
    }


    //---------- Shop Package Create ----------
    public static void verifyShopPackageCreatePrice() {
        String cateShopId = "";
        boolean isPassed = true;

        verifyStatusCode200("API Get-all Shop Private is DEAD");
        for (int i = 0; i < cateShopIdList.size(); i++) {
            cateShopId = cateShopIdList.get(i);
            for (int duration : shopCreate_Durations) {
                response = get(String.format(gatewayPricer_ShopPackage_Create, userAccountID, cateShopId, duration));
                verifyStatusCode200("API Get package price SHOP is DEAD");

                for (String priceType : priceTypes) {
                    actualPrice = getResponseData("$.price." + priceType);
                    try {
                        Assert.assertNotNull(actualPrice);
                    } catch (AssertionError es) {
                        if (isPassed)
                            isPassed = false;
                        failedMsg += String.format("Cate[%s] Duration[%s]: API[Chưa khai báo giá]\n" +
                                        ""
                                , cateShopId, duration);
                        continue;
                    }

                    expectedPrice = getCSVData_ShopCreate_Price(cateShopId, duration, priceType);
                    checkExpectedPriceEmpty();
                    //Check price of CateID & Duration exists on CSV
                    try {
                        Assert.assertNotNull(expectedPrice);
                    } catch (AssertionError ex) {
                        if (isPassed)
                            isPassed = false;

                        if (isNullActualPrice()) //API null, CSV not found -> price type is NOT AVAILABLE
                            failedMsg += String.format("Cate[%s] Duration[%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                            ""
                                    , cateShopId, duration, actualPrice);
                        continue;
                    }

                    //Compare price of API with price of CSV
                    try {
                        Assert.assertEquals(actualPrice, expectedPrice);
                    } catch (AssertionError error) {
                        if (isPassed)
                            isPassed = false;
                        failedMsg += String.format("Cate[%s] Duration[%s]: API[%s] <> CSV[%s]\n" +
                                        ""
                                , cateShopId, duration, actualPrice, expectedPrice);
                    }
                }
            }
        }
        writeResultToFileText("Shop Package Create Pricer FAILED AccountID[" + userAccountID + "]: \n");
        Assert.assertTrue(isPassed, "Shop Package Create Pricer FAILED AccountID[" + userAccountID + "]: \n" +
                "" + failedMsg);
    }

    public static void verifyShopPackageExtendPrice() {
        String cateShopId = "";
        boolean isPassed = true;

        verifyStatusCode200("API Get-all Shop Private is DEAD");
        for (int i = 0; i < cateShopIdList.size(); i++) {
            cateShopId = cateShopIdList.get(i);
            for (int duration : shopCreate_Durations) {
                response = get(String.format(gatewayPricer_ShopPackage_Extend, userAccountID, cateShopId, duration));
                verifyStatusCode200("API Get package price SHOP is DEAD");

                for (String priceType : priceTypes) {
                    actualPrice = getResponseData("$.price." + priceType);
                    try {
                        Assert.assertNotNull(actualPrice);
                    } catch (AssertionError es) {
                        if (isPassed)
                            isPassed = false;
                        failedMsg += String.format("Cate[%s] Duration[%s]: API[Chưa khai báo giá]\n" +
                                        ""
                                , cateShopId, duration);
                        continue;
                    }

                    expectedPrice = getCSVData_ShopExtend_Price(cateShopId, duration, priceType);
                    checkExpectedPriceEmpty();
                    //Check price of CateID & Duration exists on CSV
                    try {
                        Assert.assertNotNull(expectedPrice);
                    } catch (AssertionError ex) {
                        if (isPassed)
                            isPassed = false;

                        if (isNullActualPrice()) //API null, CSV not found -> price type is NOT AVAILABLE
                            failedMsg += String.format("Cate[%s] Duration[%s]: API[%s] <> CSV[NOT FOUND]\n" +
                                            ""
                                    , cateShopId, duration, actualPrice);
                        continue;
                    }

                    //Compare price of API with price of CSV
                    try {
                        Assert.assertEquals(actualPrice, expectedPrice);
                    } catch (AssertionError error) {
                        if (isPassed)
                            isPassed = false;
                        failedMsg += String.format("Cate[%s] Duration[%s]: API[%s] <> CSV[%s]\n" +
                                        ""
                                , cateShopId, duration, actualPrice, expectedPrice);
                    }
                }
            }
        }
        writeResultToFileText("Shop Package Extend Pricer FAILED AccountID[" + userAccountID + "]: \n");
        Assert.assertTrue(isPassed, "Shop Package Extend Pricer FAILED AccountID[" + userAccountID + "]: \n" +
                "" + failedMsg);
    }


    private static String changeActionType(String currentAction) {
        if (currentAction.equalsIgnoreCase("insert_ad"))
            return "insertad";
        else
            return "insert_ad";
    }

}
