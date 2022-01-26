package projects.functions.chotot.listingFee;

import org.junit.Assert;
import projects.functions.APISupportFunctions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static api.configuration.GatewayConfig.*;
import static projects.configuaration.CategoryConfig.*;

public class ListingFee_API_All_Functions extends APISupportFunctions {
    private static String userToken, userPhone, userAccountID;
    private static String adPaymentStatus;

    private static final String AD_STATE_PENDING_REVIEW = "pending_review";
    private static final String AD_STATE_REFUSED = "refused";
    private static final String AD_STATE_ACCEPTED = "accepted";
    private static final String AD_STATE_SHOP_ACCEPTED = "shop_accepted";
    private static final String AD_STATE_UNPAID = "unpaid";

    private static final String AD_PAYMENT_STATUS_FREE = "free";
    private static final String AD_PAYMENT_STATUS_PAID = "paid";
    private static final String AD_PAYMENT_STATUS_UNPAID = "unpaid";
    private static final String QUEUE_SHOP = "shop";
    private static final String QUEUE_UNPAID = "unpaid";
    private static final String QUEUE_CHOTOT = "chotot";

    private static String statusExpectAfterRefused;

    private static final int retryCheckPrivateDashboard = 5;
    private static final int retryCheckShopDashboard = 5;

    public static String getUserToken() {
        return userToken;
    }

    public static String getUserPhone() {
        return userPhone;
    }

    public static String getUserAccountID() {
        return userAccountID;
    }

    public static void setUserToken(String userToken) {
        ListingFee_API_All_Functions.userToken = userToken;
    }

    public static void setUserPhone(String userPhone) {
        ListingFee_API_All_Functions.userPhone = userPhone;
    }

    public static void setUserAccountID(String userAccountID) {
        ListingFee_API_All_Functions.userAccountID = userAccountID;
    }

    public static String getAdPaymentStatus() {
        if (adPaymentStatus == null)
            return AD_PAYMENT_STATUS_FREE;
        else
            return adPaymentStatus;
    }

    public static void setAdPaymentStatus_Free() {
        ListingFee_API_All_Functions.adPaymentStatus = AD_PAYMENT_STATUS_FREE;
    }

    public static void setAdPaymentStatus_Paid() {
        ListingFee_API_All_Functions.adPaymentStatus = AD_PAYMENT_STATUS_PAID;
    }

    public static void setAdPaymentStatus_Unpaid() {
        ListingFee_API_All_Functions.adPaymentStatus = AD_PAYMENT_STATUS_UNPAID;
    }

    public static void setStatusExpectAfterRefused() {
        ListingFee_API_All_Functions.statusExpectAfterRefused = "refused";
    }

    //---------------------------- CHECK AD ON SPECIFIED TAB ----------------------------

    public static void checkAdOnShopDashboard(String ownerToken, String shopAlias, String adID, String tabName) {
        String adType = "";
        List<String> adIDOnDashboard = new ArrayList<>();
        switch (tabName) {
            case "TIN CHỢ TỐT":
                adType = "accepted";
                break;

            case "TIN CHUYÊN TRANG":
                adType = "shop_alias";
                break;

            case "TIN KHÁC":
                adType = "other";
                break;

            case "TIN KHÁC - BỊ TỪ CHỐI":
                adType = "refused";
                break;

            case "TIN KHÁC - TIN ĐÃ ẨN":
                adType = "hidden";
                break;

            case "TIN KHÁC - TIN ĐỢI DUYỆT":
                adType = "other";
                break;
        }

        //Set Header & query
        setHeaderMap("ad-type", adType);
        query = "$." + adType + "[*][?(@.info.ad_id == " + adID + ")]";

        //Call API
        for (int i = 0; i < retryCheckShopDashboard; i++) {
            try {
                response = get(ownerToken, String.format(gatewayShopDashboard, shopAlias), getHeaderMap());
                verifyStatusCode200("API THEIA CHECK SHOP DASHBOARD IS DEAD");
                adIDOnDashboard = getResponseDataList(response, query);
                Assert.assertNotNull("Ad " + adID + " doesn't display on Shop Dashboard: " + tabName, adIDOnDashboard);
                Assert.assertTrue("Ad " + adID + " doesn't display on Shop Dashboard: " + tabName, adIDOnDashboard.size() == 1);
                break;
            } catch (AssertionError error) {
                waitConstant(5);
            }
        }
        debugResponse();
        verifyStatusCode200("API THEIA CHECK SHOP DASHBOARD IS DEAD");
        Assert.assertNotNull("Ad " + adID + " doesn't display on Shop Dashboard: " + tabName, adIDOnDashboard);
        Assert.assertTrue("Ad " + adID + " doesn't display on Shop Dashboard: " + tabName, adIDOnDashboard.size() == 1);
    }


    public static void checkAdNotOnShopDashboard(String ownerToken, String shopAlias, String adID, String tabName) {
        String adType = "";
        List<String> adIDOnDashboard = new ArrayList<>();
        List<String> adTypes = null;
        switch (tabName) {
            case "TIN CHỢ TỐT":
                adType = "accepted";
                break;

            case "TIN CHUYÊN TRANG":
                adType = "shop_alias";
                break;

            case "TIN KHÁC":
                adType = "other";
                break;

            case "TIN KHÁC - BỊ TỪ CHỐI":
                adType = "refused";
                break;

            case "TIN KHÁC - TIN ĐÃ ẨN":
                adType = "hidden";
                break;

            case "TIN KHÁC - TIN ĐỢI DUYỆT":
                adType = "other";
                break;

            default:
                adTypes = Arrays.asList("accepted", "shop_alias", "other", "refused", "hidden"); //To check ad isn't on any Tab
                break;
        }

        //Set Header & query
        setHeaderMap("ad-type", adType);
        query = "$." + adType + "[*][?(@.info.ad_id == " + adID + ")]";

        //Call API
        for (int i = 0; i < retryCheckShopDashboard; i++) {
            try {
                response = get(ownerToken, String.format(gatewayShopDashboard, shopAlias), getHeaderMap());
                verifyStatusCode200("API THEIA CHECK SHOP DASHBOARD IS DEAD");
                adIDOnDashboard = getResponseDataList(response, query);
                Assert.assertTrue("Ad " + adID + " still displays on Shop Dashboard: ", adIDOnDashboard.size() == 0);
                break;
            } catch (AssertionError error) {
                waitConstant(5);
            }
        }
        debugResponse();
        verifyStatusCode200("API THEIA CHECK SHOP DASHBOARD IS DEAD");
        Assert.assertTrue("Ad " + adID + " still displays on Shop Dashboard: ", adIDOnDashboard.size() == 0);
    }


    public static void checkAdOnPrivateDashboard(String ownerToken, String adID, String tabName) {
        tabName = tabName.trim().toUpperCase();
        String status = "";
        for (int i = 0; i < retryCheckPrivateDashboard; i++) {
            try {
                response = get(ownerToken, String.format(gatewayPrivateUserAds, adID));
                verifyStatusCode200("User Ads", String.format(gatewayPrivateUserAds, adID));
                status = getResponseData(response, "$.status");
                Assert.assertNotNull(status);
                checkAdOnTab(tabName, status);
                break;
            } catch (AssertionError tryAgain) {
                waitConstant(2);
            }
        }
        checkAdOnTab(tabName, status);
    }


    @Deprecated
    private static void checkAdOnTab(String tabName, String status) {
        String statusExpect = "", paymentExpect = "", stateExpect = "";
        switch (tabName) {
            case "ĐANG BÁN":
                statusExpect = "active";
                stateExpect = AD_STATE_ACCEPTED;
                paymentExpect = getAdPaymentStatus();
                break;

            case "CẦN THANH TOÁN":
                if (statusExpectAfterRefused == null) {
                    statusExpect = "inactive";
                } else {
                    statusExpect = statusExpectAfterRefused;        //After refused, status is changed to refused
                }
                stateExpect = AD_STATE_UNPAID;
                paymentExpect = AD_PAYMENT_STATUS_UNPAID;
                break;

            case "ĐỢI DUYỆT":
                if (statusExpectAfterRefused == null) {
                    statusExpect = "inactive";
                } else {
                    statusExpect = statusExpectAfterRefused;        //After refused, status is changed to refused
                }
                stateExpect = AD_STATE_PENDING_REVIEW;
                paymentExpect = getAdPaymentStatus();
                break;

            case "BỊ TỪ CHỐI":
                statusExpect = "refused";
                stateExpect = AD_STATE_REFUSED;
                paymentExpect = getAdPaymentStatus();
                break;

            case "BỊ TỪ CHỐI - CẦN THANH TOÁN":    //Edit refused charge Ad
                statusExpect = "refused";
                stateExpect = AD_STATE_UNPAID;
                paymentExpect = getAdPaymentStatus();
                break;

            case "BỊ TỪ CHỐI - SAU KHI THANH TOÁN":    //Ad is charge but be refused after paid
                statusExpect = "refused";
                stateExpect = AD_STATE_REFUSED;
                paymentExpect = "unpaid";
                break;

            case "ĐỢI DUYỆT - SAU KHI BỊ TỪ CHỐI":    //Ad is charge but be refused after paid
                if (statusExpectAfterRefused == null) {
                    statusExpect = "refused";
                } else {
                    statusExpect = statusExpectAfterRefused;        //After refused, status is changed to refused
                }
                stateExpect = AD_STATE_PENDING_REVIEW;
                paymentExpect = getAdPaymentStatus();
                break;
        }

        //Check status
        Assert.assertEquals("FAILED: Check Ad Status", statusExpect, status);

        //Check state
        Assert.assertEquals("FAILED: Check Ad State", stateExpect, getResponseData("$.state"));

        //Check Payment Status
        Assert.assertEquals("FAILED: Check Ad Payment Status", paymentExpect, getResponseData("$.payment_status"));
    }

    @Deprecated
    public static void checkUserProfilerLF(String userAccountID, String categoryID, String adType, String profiler) {
        String expectProfiler = "";
        for (int i = 0; i < 5; i++) {
            try {
                response = get(String.format(gatewayUserProfiler, userAccountID));
                verifyStatusCode200("API USER PRIFILER FAILED");
                expectProfiler = getResponseData("$." + categoryID + "." + adType);
                Assert.assertEquals("FAILED: CHECK USER PROFILER", profiler, expectProfiler);
                break;
            } catch (AssertionError error) {
                waitConstant(3);
            }
        }
        debugResponse();
        Assert.assertEquals("FAILED: CHECK USER PROFILER", profiler, expectProfiler);
    }

    public static void checkRemainingFreeAdPTY(String accountID, String shopAlias, int freeAdSlot) {
        checkRemainingFreeAdPTY(accountID, shopAlias, CATEID_PTY, freeAdSlot);
    }

    public static void checkRemainingFreeAdVEH(String accountID, String shopAlias, int freeAdSlot) {
        checkRemainingFreeAdPTY(accountID, shopAlias, CATEID_VEH, freeAdSlot);
    }

    public static void checkRemainingFreeAdELT(String accountID, String shopAlias, int freeAdSlot) {
        checkRemainingFreeAdPTY(accountID, shopAlias, CATEID_ELT, freeAdSlot);
    }

    private static void checkRemainingFreeAdPTY(String accountID, String shopAlias, String categoryID, int freeAdSlot) {
        for (int i = 0; i < 7; i++) {
            try {
                response = get(String.format(getGatewayShop_RemainingFreeAd, accountID, shopAlias, categoryID));
                verifyStatusCode200("API USER ADS, CHECK REMAINING FREE SLOT IS DEAD");
                Assert.assertEquals("The remaining free ad of Shop " + categoryID + " of User " + accountID + " is incorrect"
                        , getResponseDataInt("$.remainingFreeAds")
                        , freeAdSlot);
                break;
            } catch (AssertionError assertionError) {
                waitConstant(5);
            }
        }
        debugResponse();
        Assert.assertEquals("The remaining free ad of Shop " + categoryID + " of User " + accountID + " is incorrect"
                , getResponseDataInt("$.remainingFreeAds")
                , freeAdSlot);
    }
}
