package projects.functions.chotot;

import org.junit.Assert;
import projects.functions.APISupportFunctions;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertVehicle;

import java.util.ArrayList;
import java.util.List;

import static api.configuration.DataConfig.*;
import static api.configuration.GatewayConfig.gatewayCategories_GetAllSubCate;
import static api.configuration.GatewayConfig.global_accessToken;
import static api.utils.GetAccessToken.getAccessTokenOfNewUser;
import static projects.configuaration.CategoryConfig.*;
import static projects.functions.chotot.payment.PayOrder_API_Functions.paymentOrder;
import static projects.functions.chotot.payment.TopupDongTot_API_Functions.topupDongTotWithMomo_3000k;
import static projects.functions.chotot.pos.POS_Functions.posStickyAd;

public class CommonFunctions extends APISupportFunctions {
    @Deprecated
    public static List<String> postAndPayCarAds(int numberOfAd, boolean isUsingNewUser) {
        if (isUsingNewUser)
            getAccessTokenOfNewUser();
        else
            Assert.assertNotNull("Global access token is null", global_accessToken);

        topupDongTotWithMomo_3000k(newUserPhone);
        tempAdIDList = new ArrayList<>();
        for (int i = 0; i < numberOfAd; i++) {
            CM_API_Ads_InsertVehicle.instance().insertNewAdCar_NoUploadNewImage("accept");
            posStickyAd(global_accessToken, tempAdID);
            paymentOrder(global_accessToken);
            tempAdIDList.add(tempAdID);
        }
        return tempAdIDList;
    }

    //GET CATEGORY
    public static List<String> getSubCategoryIDs_VEH() {
        return getSubCategoryIDs(CATEID_VEH);
    }

    public static List<String> getSubCategoryIDs_PTY() {
        return getSubCategoryIDs(CATEID_PTY);
    }

    public static List<String> getSubCategoryIDs_ELT() {
        return getSubCategoryIDs(CATEID_ELT);
    }

    public static List<String> getSubCategoryIDs(String categoryID) {
        response = get(gatewayCategories_GetAllSubCate);
        verifyStatusCode200("API get all Subcate ID.");
        query = "$.categories[*].subcategories[?(@.parent == " + categoryID + ")].id";
        return getResponseDataList(response, query);
    }
}
