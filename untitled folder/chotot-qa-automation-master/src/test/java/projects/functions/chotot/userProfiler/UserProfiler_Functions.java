package projects.functions.chotot.userProfiler;

import org.testng.Assert;
import projects.functions.APISupportFunctions;

import java.util.Arrays;
import java.util.List;

import static api.configuration.GatewayConfig.*;
import static projects.configuaration.CategoryConfig.*;

public class UserProfiler_Functions extends APISupportFunctions {
    // Variables
    private static final int retryToGetUserProfiler = 5;
    private static boolean isNewProfiler = false;

    private static List<String> cateIDs = Arrays.asList("1000", "1010", "1020");
    private static List<String> adTypes = Arrays.asList("s", "u", "k", "h");

    private static String reasonId = "", reasonName = "", shortDetail = "", longDetail = "";

    private static void initReasonData() {
        reasonId = "";
        reasonName = "automation_reason_";
        shortDetail = "AUTOMATION TEST SHORT DETAIL";
        longDetail = "AUTOMATION TEST LONG DETAIL";
    }

    protected static String getReasonName() {
        return reasonName;
    }

    protected static void setReasonName(String reasonName) {
        UserProfiler_Functions.reasonName = reasonName;
    }

    protected static String getShortDetail() {
        return shortDetail;
    }

    protected static void setShortDetail(String shortDetail) {
        UserProfiler_Functions.shortDetail = shortDetail;
    }

    protected static String getLongDetail() {
        return longDetail;
    }

    protected static void setLongDetail(String longDetail) {
        UserProfiler_Functions.longDetail = longDetail;
    }

    protected static String getReasonId() {
        return reasonId;
    }

    protected static void setReasonId(String reasonId) {
        UserProfiler_Functions.reasonId = reasonId;
    }

    // Functions
    public static String setToTestNewProfiler(String url) {
        if (isNewProfiler) {
            return url.replace("profiler", "new-profiler");
        }
        return url;
    }

    public static void checkUserProfilerIsPrivate(String accountID, String categoryID, String adType) {
        checkUserProfiler(accountID, categoryID, adType, "PRIVATE");
    }

    public static void checkUserProfilerIsPro(String accountID, String categoryID, String adType) {
        checkUserProfiler(accountID, categoryID, adType, "PRO");
    }

    public static void checkUserProfilerIsShop(String accountID, String categoryID, String adType) {
        checkUserProfiler(accountID, categoryID, adType, "SHOP");
    }

    public static void checkUserProfiler(String accountID, String categoryID, String adType, String profile) {
        //Standardize input data
        profile = profile.trim().toLowerCase();
        String url = String.format(gatewayUserProfilerPublic, accountID, categoryID, adType);
        url = setToTestNewProfiler(url);        //VUHOANG DEBUG
        String queryCateID = "$.profiler." + categoryID + "[*][?(@.ad_type == '" + adType + "')].user_type";

        //Testing
        for (int i = 0; i < retryToGetUserProfiler; i++) {
            try {
                response = get(url);
                verifyStatusCode200("API DEAD: " + url);
                Assert.assertEquals(getResponseDataList(queryCateID).get(0)
                        , profile
                        , "FAILED: USER PROFILE IS INCORRECT.");
                break;
            } catch (AssertionError error) {
                waitConstant(2);
            }
        }
        debugResponse();
        Assert.assertEquals(getResponseDataList(queryCateID).get(0)
                , profile
                , "FAILED: USER PROFILE IS INCORRECT.");
    }

    //---------- NEXT USER PROFILER --------------
    public static void checkUserProfilerNextIsPrivate(String accountID, String categoryID, String adType) {
        checkUserProfilerNext(accountID, categoryID, adType, "PRIVATE");
    }

    public static void checkUserProfilerNextIsPro(String accountID, String categoryID, String adType) {
        checkUserProfilerNext(accountID, categoryID, adType, "PRO");
    }

    public static void checkUserProfilerNext(String accountID, String categoryID, String adType, String profile) {
        //Standardize input data
        profile = profile.trim().toLowerCase();
        String url = String.format(gatewayUserProfilerPublic, accountID, categoryID, adType);
        url = setToTestNewProfiler(url);        //VUHOANG DEBUG
        String queryCateID = "$.profiler." + categoryID + "[*][?(@.ad_type == '" + adType + "')].user_type_next";

        //Testing
        for (int i = 0; i < retryToGetUserProfiler; i++) {
            try {
                response = get(url);
                verifyStatusCode200("API DEAD: " + url);
                Assert.assertEquals(getResponseDataList(queryCateID).get(0)
                        , profile
                        , "FAILED: NEXT USER PROFILE IS INCORRECT.");
                break;
            } catch (AssertionError error) {
                waitConstant(2);
            }
        }
        debugResponse();
        Assert.assertEquals(getResponseDataList(queryCateID).get(0)
                , profile
                , "FAILED: NEXT USER PROFILE IS INCORRECT.");
    }

    public static void verifyUserProfilerAPI_InternalProfiler(String accountID, String bigCategory, String expectedValue) {
        boolean isPassed = true;
        String failedMsg = "";
        String url = String.format(gatewayUserProfiler, accountID);
        url = setToTestNewProfiler(url);        //VUHOANG DEBUG
        List<String> subCateIDs = null;
        List<String> adTypes = null;
        switch (bigCategory) {
            case CATEID_PTY:
                subCateIDs = CATEID_PTY_ALL;
                adTypes = AD_TYPE_PTY_ALL;
                break;
            case CATEID_VEH:
                subCateIDs = CATEID_VEH_ALL;
                adTypes = AD_TYPE_VEH_ALL;
                break;
            case CATEID_ELT:
                subCateIDs = CATEID_ELT_ALL;
                adTypes = AD_TYPE_ELT_ALL;
                break;
        }
        Assert.assertNotNull(subCateIDs, "Category Input isn't correct");
        for (String subCategory : subCateIDs) {
            for (String adType : adTypes) {
                query = "$." + subCategory + "." + adType;
                for (int i = 0; i < retryToGetUserProfiler; i++) {
                    try {
                        response = get(url);
                        verifyStatusCode200("API DEAD: " + url);
                        Assert.assertEquals(getResponseData(query), expectedValue.toLowerCase(), "FAILED: " + url + "\n");
                        break;
                    } catch (AssertionError error) {
                        waitConstant(3);
                    }
                }
                if (!getResponseData(query).equalsIgnoreCase(expectedValue)) {
                    if (isPassed) {
                        isPassed = false;
                    }
                    failedMsg += "\n" + query;
                }
            }
        }
        Assert.assertTrue(isPassed, "FAILED: " + url + "\n");
    }

    public static void verifyUserProfilerAPI_MultipleProfiler(String accountID, String bigCategory, String expectedValue) {
        boolean isPassed = true;
        String failedMsg = "", query = "", bodyJSonStr = "";
        String url = String.format(gatewayUserProfiler, accountID);
        List<String> subCateIDs = null;
        List<String> adTypes = null;
        switch (bigCategory) {
            case CATEID_PTY:
                subCateIDs = CATEID_PTY_ALL;
                adTypes = AD_TYPE_PTY_ALL;
                break;
            case CATEID_VEH:
                subCateIDs = CATEID_VEH_ALL;
                adTypes = AD_TYPE_VEH_ALL;
                break;
            case CATEID_ELT:
                subCateIDs = CATEID_ELT_ALL;
                adTypes = AD_TYPE_ELT_ALL;
                break;
        }
        Assert.assertNotNull(subCateIDs, "Category Input isn't correct");
        for (String subCategory : subCateIDs) {
            for (String adType : adTypes) {
                query = "$." + accountID + ".profiler." + subCategory + "[?(@.ad_type == '" + adType + "' && @.category == " + subCategory + ")].user_type";
                System.out.println(query);
                bodyJSonStr = "{\n" +
                        "    \"params\": [\n" +
                        "        {\n" +
                        "            \"ad_type\": \"" + adType + "\",\n" +
                        "            \"cat_id\": \"" + subCategory + "\",\n" +
                        "            \"account_id\": \"" + accountID + "\"\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}";
                for (int i = 0; i < retryToGetUserProfiler; i++) {
                    try {
                        response = post(bodyJSonStr, url);
                        verifyStatusCode200("API DEAD: " + url);
                        Assert.assertEquals(getResponseData(query), expectedValue.toLowerCase(), "FAILED: " + url + "\n");
                        break;
                    } catch (AssertionError error) {
                        waitConstant(3);
                    }
                }
                if (!getResponseData(query).equalsIgnoreCase(expectedValue)) {
                    if (isPassed) {
                        isPassed = false;
                    }
                    failedMsg += "\n" + query;
                }
            }
        }
        Assert.assertTrue(isPassed, "FAILED: " + url + "\n");
    }


    public static void verifyAPIGet_InternalProfiler_PTYShop() {
        for (String cateID : cateIDs) {
            for (String adType : adTypes) {
                for (int i = 0; i < retryToGetUserProfiler; i++) {
                    try {
                        response = get(String.format(gatewayUserProfiler_Get_InternalProfiler, global_accountID));
                        verifyStatusCode200("API " + gatewayUserProfiler_Get_InternalProfiler + " IS DEAD");
                        Assert.assertEquals(getResponseData("$." + cateID + "." + adType + ""), "shop");
                        break;
                    } catch (AssertionError error) {
                        waitConstant(3);
                    }
                }
                Assert.assertEquals(getResponseData("$." + cateID + "." + adType + ""), "shop");
            }
        }
    }

    public static void verifyAPIGet_InternalProfiler_PTY(String profile, String cateID, String adType) {

        for (int i = 0; i < retryToGetUserProfiler; i++) {
            try {
                response = get(String.format(gatewayUserProfiler_Get_InternalProfiler, global_accountID));
                verifyStatusCode200("API " + gatewayUserProfiler_Get_InternalProfiler + " IS DEAD");
                if (profile.equalsIgnoreCase("null")) {
                    Assert.assertNull(getResponseData("$." + cateID + "." + adType + ""));
                } else {
                    Assert.assertEquals(getResponseData("$." + cateID + "." + adType + ""), profile.toLowerCase());
                }
                break;
            } catch (AssertionError error) {
                waitConstant(3);
            }
        }
        debugResponse();
        if (profile.equalsIgnoreCase("null")) {
            Assert.assertNull(getResponseData("$." + cateID + "." + adType + ""));
        } else {
            Assert.assertEquals(getResponseData("$." + cateID + "." + adType + ""), profile.toLowerCase());
        }
    }

    public static void verifyAPIGet_InternalProfilerPost_PTYShop() {
        String bodyStr = "";
        for (String cateID : cateIDs) {
            for (String adType : adTypes) {
                bodyStr = "{\n" +
                        "    \"params\": [\n" +
                        "        {\n" +
                        "            \"ad_type\": \"" + adType + "\",\n" +
                        "            \"cat_id\": \"" + cateID + "\",\n" +
                        "            \"account_id\": \"" + global_accountID + "\"\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}";
                for (int i = 0; i < retryToGetUserProfiler; i++) {
                    try {
                        response = post(bodyStr, gatewayUserProfiler_Get_InternalProfilerPost);
                        verifyStatusCode200("API " + gatewayUserProfiler_Get_InternalProfilerPost + " IS DEAD");
                        Assert.assertEquals(getResponseData("$." + global_accountID + ".profiler." + cateID + "[0].user_type"), "shop");
                        break;
                    } catch (AssertionError error) {
                        waitConstant(3);
                    }
                }
                Assert.assertEquals(getResponseData("$." + global_accountID + ".profiler." + cateID + "[0].user_type"), "shop");
            }
        }
    }

    public static void verifyAPIGet_InternalProfilerPost_PTY(String profile, String cateID, String adType) {
        String bodyStr = "";
        bodyStr = "{\n" +
                "    \"params\": [\n" +
                "        {\n" +
                "            \"ad_type\": \"" + adType + "\",\n" +
                "            \"cat_id\": \"" + cateID + "\",\n" +
                "            \"account_id\": \"" + global_accountID + "\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        for (int i = 0; i < retryToGetUserProfiler; i++) {
            try {
                response = post(bodyStr, gatewayUserProfiler_Get_InternalProfilerPost);
                verifyStatusCode200("API " + gatewayUserProfiler_Get_InternalProfilerPost + " IS DEAD");
                Assert.assertEquals(getResponseData("$." + global_accountID + ".profiler." + cateID + "[0].user_type"), profile.toLowerCase());
                break;
            } catch (AssertionError error) {
                waitConstant(3);
            }
        }
        debugResponse();
        Assert.assertEquals(getResponseData("$." + global_accountID + ".profiler." + cateID + "[0].user_type"), profile.toLowerCase());
    }

    public static void verifyAPIGet_PublicProfiler_PTYShop() {
        for (String cateID : cateIDs) {
            for (String adType : adTypes) {
                for (int i = 0; i < retryToGetUserProfiler; i++) {
                    try {
                        response = get(String.format(gatewayUserProfiler_Get_PublicProfiler, global_accountID, cateID, adType));
                        verifyStatusCode200("API PublicProfiler IS DEAD");
                        Assert.assertEquals(getResponseData("$.profiler." + cateID + "[0].user_type"), "shop");
                        break;
                    } catch (AssertionError error) {
                        waitConstant(3);
                    }
                }
                Assert.assertEquals(getResponseData("$.profiler." + cateID + "[0].user_type"), "shop");
            }
        }
    }


    public static void verifyAPIGet_PublicProfiler_PTY(String cateID, String adType, String profile) {
        for (int i = 0; i < retryToGetUserProfiler; i++) {
            try {
                response = get(String.format(gatewayUserProfiler_Get_PublicProfiler, global_accountID, cateID, adType));
                verifyStatusCode200("API PublicProfiler IS DEAD");
                Assert.assertEquals(getResponseData("$.profiler." + cateID + "[0].user_type"), profile.toLowerCase());
                break;
            } catch (AssertionError error) {
                waitConstant(3);
            }
        }
        Assert.assertEquals(getResponseData("$.profiler." + cateID + "[0].user_type"), profile.toLowerCase());
    }

    public static void verifyAPIGet_PublicCheckProfiler_PTYShop() {
        for (String cateID : cateIDs) {
            for (String adType : adTypes) {
                for (int i = 0; i < retryToGetUserProfiler; i++) {
                    try {
                        response = get(String.format(gatewayUserProfiler_Get_PublicCheckProfiler, global_accountID, cateID, adType));
                        verifyStatusCode200("API Public Check Profiler IS DEAD");
                        Assert.assertEquals(getResponseData("$.current.user_type"), "shop");
                        break;
                    } catch (AssertionError error) {
                        waitConstant(3);
                    }
                }
                debugResponse();
                Assert.assertEquals(getResponseData("$.current.user_type"), "shop");
            }
        }
    }

    public static void verifyAPIGet_PublicCheckProfiler_PTY(String cateID, String adType, String profile) {
        for (int i = 0; i < retryToGetUserProfiler; i++) {
            try {
                response = get(String.format(gatewayUserProfiler_Get_PublicCheckProfiler, global_accountID, cateID, adType));
                verifyStatusCode200("API Public Check Profiler IS DEAD");
                Assert.assertEquals(getResponseData("$.current.user_type"), profile.toLowerCase());
                break;
            } catch (AssertionError error) {
                waitConstant(3);
            }
        }
        debugResponse();
        Assert.assertEquals(getResponseData("$.current.user_type"), profile.toLowerCase());
    }

    public static void verifyAPIGet_PrivateProfiler_PTYShop(String token) {
        for (String cateID : cateIDs) {
            for (String adType : adTypes) {
                String bodyStr = "{\n" +
                        "    \"params\": [\n" +
                        "        {\n" +
                        "            \"ad_type\": \"" + adType + "\",\n" +
                        "            \"cat_id\": " + cateID + "\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}";
                for (int i = 0; i < retryToGetUserProfiler; i++) {
                    try {
                        response = post(token, bodyStr, gatewayUserProfiler_Post_PrivateProfiler);
                        verifyStatusCode200("API " + gatewayUserProfiler_Post_PrivateProfiler + " IS DEAD");
                        Assert.assertEquals(getResponseData("$." + global_accountID + ".profiler." + cateID + "[0].user_type"), "shop");
                        break;
                    } catch (AssertionError error) {
                        waitConstant(3);
                    }
                }
                Assert.assertEquals(getResponseData("$." + global_accountID + ".profiler." + cateID + "[0].user_type"), "shop");
            }
        }
    }

    public static void verifyAPIGet_PrivateProfiler_PTY(String token, String cateID, String adType, String profile) {
        String bodyStr = "{\n" +
                "    \"params\": [\n" +
                "        {\n" +
                "            \"ad_type\": \"" + adType + "\",\n" +
                "            \"cat_id\": " + cateID + "\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        for (int i = 0; i < retryToGetUserProfiler; i++) {
            try {
                response = post(token, bodyStr, gatewayUserProfiler_Post_PrivateProfiler);
                verifyStatusCode200("API " + gatewayUserProfiler_Post_PrivateProfiler + " IS DEAD");
                Assert.assertEquals(getResponseData("$." + global_accountID + ".profiler." + cateID + "[0].user_type"), profile.toLowerCase());
                break;
            } catch (AssertionError error) {
                waitConstant(3);
            }
        }
        debugResponse();
        Assert.assertEquals(getResponseData("$." + global_accountID + ".profiler." + cateID + "[0].user_type"), profile.toLowerCase());
    }

    //REASON
    protected static String userProfiler_Reason_Create() {
        initReasonData();
        String timeStamp = getTimeStamp();
        String reasonName = getReasonName() + timeStamp;
        String shortDetail = getShortDetail() + " " + timeStamp;
        String longDetail = getLongDetail() + " " + timeStamp;
        initBodyJson();
        bodyJson.addProperty("short_detail", shortDetail);
        bodyJson.addProperty("long_detail", longDetail);
        bodyJson.addProperty("reason_name", reasonName);
        for (int i = 0; i < retryToGetUserProfiler; i++) {
            try {
                response = post(bodyJson, gatewayUserProfiler_Reason_Create);
                verifyStatusCode200("USER PROFILER", "REASON CREATE");
                Assert.assertEquals(getResponseData("$.name"), reasonName);
                break;
            } catch (AssertionError e) {
                waitConstant(3);
            }
        }
        verifyStatusCode200("USER PROFILER", "REASON CREATE");
        Assert.assertEquals(getResponseData("$.name"), reasonName);
        Assert.assertEquals(getResponseData("$.long_detail"), longDetail);
        Assert.assertEquals(getResponseData("$.short_detail"), shortDetail);
        Assert.assertEquals(getResponseDataBoolean("$.is_deleted"), false);
        setShortDetail(shortDetail);
        setLongDetail(longDetail);
        setReasonId(getResponseData("$._id"));
        setReasonName(reasonName);
        return reasonName;
    }

    protected static void userProfiler_Reason_UpdateAll(boolean isUpdateReasonName, boolean isUpdateShortDetail, boolean isUpdateLongDetail) {
        String url = String.format(gatewayUserProfiler_Reason_Update, reasonName);
        initBodyJson();
        String newReasonName = "", newShortDetail = "", newLongDetail = "";
        if (isUpdateReasonName) {
            newReasonName = getReasonName() + "update";
            bodyJson.addProperty("reason_name", newReasonName);
        }
        if (isUpdateShortDetail) {
            newShortDetail = getShortDetail() + "update";
            bodyJson.addProperty("short_detail", newShortDetail);
        }
        if (isUpdateLongDetail) {
            newLongDetail = getLongDetail() + "update";
            bodyJson.addProperty("long_detail", newLongDetail);
        }

        for (int i = 0; i < retryToGetUserProfiler; i++) {
            try {
                response = put(bodyJson, url);
                verifyStatusCode200("USER PROFILER", "REASON UPDATE ALL");
                Assert.assertEquals(getResponseData("$._id"), getReasonId());
                break;
            } catch (AssertionError e) {
                waitConstant(3);
            }
        }
        verifyStatusCode200("USER PROFILER", "REASON UPDATE");
        Assert.assertNotEquals(getResponseData("$._id"), getReasonId());
        Assert.assertEquals(getResponseDataBoolean("$.is_deleted"), false);
        Assert.assertEquals(getResponseData("$.name"), newReasonName);
        Assert.assertEquals(getResponseData("$.long_detail"), newLongDetail);
        Assert.assertEquals(getResponseData("$.short_detail"), newShortDetail);
        setReasonName(newReasonName);
        setShortDetail(newShortDetail);
        setLongDetail(newLongDetail);
        setReasonId(getResponseData("$._id"));
    }

    protected static void userProfiler_Reason_Remove() {
        for (int i = 0; i < retryToGetUserProfiler; i++) {
            try {
                response = delete(String.format(gatewayUserProfiler_Reason_Create, reasonName));
                verifyStatusCode200("USER PROFILER", "REASON REMOVE");
                Assert.assertEquals(getResponseData("$._id"), getReasonId());
                break;
            } catch (AssertionError e) {
                waitConstant(3);
            }
        }
        verifyStatusCode200("USER PROFILER", "REASON REMOVE");
        Assert.assertEquals(getResponseData("$._id"), getReasonId());
        Assert.assertEquals(getResponseDataBoolean("$.is_deleted"), true);
        Assert.assertEquals(getResponseData("$.name"), getReasonName());
        Assert.assertEquals(getResponseData("$.long_detail"), getLongDetail());
        Assert.assertEquals(getResponseData("$.short_detail"), getShortDetail());
    }

    protected static void userProfiler_Reason_VerifyDeleted(boolean isDeleted) {
        for (int i = 0; i < retryToGetUserProfiler; i++) {
            try {
                response = get(String.format(gatewayUserProfiler_Reason_GetByName, reasonName));
                verifyStatusCode200("USER PROFILER", "REASON REMOVE");
                Assert.assertEquals(getResponseData("$._id"), getReasonId());
                break;
            } catch (AssertionError e) {
                waitConstant(3);
            }
        }
        verifyStatusCode200("USER PROFILER", "REASON ISDELETED?");
        Assert.assertEquals(getResponseData("$._id"), getReasonId());
        Assert.assertEquals(getResponseDataBoolean("$.is_deleted"), isDeleted);
        Assert.assertEquals(getResponseData("$.name"), getReasonName());
        Assert.assertEquals(getResponseData("$.long_detail"), getLongDetail());
        Assert.assertEquals(getResponseData("$.short_detail"), getShortDetail());
    }

    protected static void userProfiler_Reason_VerifyDeleted_GetAll(boolean isDeleted) {
        for (int i = 0; i < retryToGetUserProfiler; i++) {
            try {
                response = get(gatewayUserProfiler_Reason_GetAll);
                verifyStatusCode200("USER PROFILER", "REASON ISDELETED? WHEN GET ALL");
                Assert.assertEquals(getResponseData("$[?(@._id == '" + getReasonId() + "')].name"), getReasonName());
                break;
            } catch (AssertionError e) {
                waitConstant(3);
            }
        }
        verifyStatusCode200("USER PROFILER", "REASON ISDELETED? WHEN GET ALL");
        Assert.assertEquals(getResponseData("$[?(@._id == '" + getReasonId() + "')].name"), getReasonName());
        Assert.assertEquals(getResponseDataBoolean("$[?(@._id == '" + getReasonId() + "')].is_deleted"), isDeleted);
        Assert.assertEquals(getResponseData("$[?(@._id == '" + getReasonId() + "')].short_detail"), getShortDetail());
        Assert.assertEquals(getResponseData("$[?(@._id == '" + getReasonId() + "')].long_detail"), getLongDetail());
    }

}
