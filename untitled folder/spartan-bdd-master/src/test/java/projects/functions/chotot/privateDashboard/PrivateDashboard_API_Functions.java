package projects.functions.chotot.privateDashboard;

import com.vn.chotot.api.rest_assured.RestAssure;
import com.vn.chotot.exception.FailureHandling;
import org.testng.Assert;
import projects.functions.APISupportFunctions;

import java.util.List;

import static api.configuration.GatewayConfig.*;
import static com.vn.chotot.keywords.selenium.Utils.verifyEquals;

public class PrivateDashboard_API_Functions extends APISupportFunctions {
    private static String adID = "";
    private static String accountID = "";

    public static String getAdID() {
        return adID;
    }

    public static void setAdID(String adID) {
        PrivateDashboard_API_Functions.adID = adID;
    }

    public static void checkAdOnPrivateDashboard(String adID) {
        waitConstant(10);
        response = get(global_accessToken,
                String.format(gatewayPrivateDashboard_UserAds_GetAdList, global_accountID, adID));
        verifyStatusCode200("PRIVATE DASHBOARD", String.format(gatewayPrivateDashboard_UserAds_GetAdList, global_accountID, adID));
        List<String> adList = getResponseDataList(response, String.format("$[?(@.ad_id == '%s')].account_id", adID));
        Assert.assertEquals(adList.size(), 1, "Check only one new ad is created");
        Assert.assertEquals(String.valueOf(adList.get(0)), global_accountID, "Check account ID of Ad is correct");
    }

    public static void checkAdNotOnPrivateDashboard(String adID) {
        waitConstant(10);
        response = RestAssure.instance().get(global_accessToken,
                String.format(gatewayPrivateDashboard_UserAds_GetAdList, global_accountID, adID));
        verifyStatusCode404("PRIVATE DASHBOARD", String.format(gatewayPrivateDashboard_UserAds_GetAdList, global_accountID, adID));
    }

    public static boolean checkNewAdOnUserDashboard(String adID) {
        response = RestAssure.instance().get(global_accessToken,
                String.format(gatewayPrivateDashboard_UserAds_GetAdList, global_accountID, adID));
        verifyStatusCode200("PRIVATE DASHBOARD", String.format(gatewayPrivateDashboard_UserAds_GetAdList, global_accountID, adID));
        List<String> adList = getResponseDataList(response, String.format("$[?(@.ad_id == '%s')].account_id", adID));
        return verifyEquals(adList.size(), 1, FailureHandling.WARNING);
    }
}
