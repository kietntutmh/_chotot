package desktop.scenarios_old.cp.ad_review.accept;

import desktop.pages.Dashboard.PrivateDashboard;
import org.testng.annotations.Test;
import projects.functions.chotot._common.CM_Login;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertFashion;

import static api.configuration.DataConfig.tempAdSubject;

public class CP_VerifyUserCanAcceptNewAdFashion extends CP_AcceptAd_SetAccount {

    @Test(
            groups = {"dev", "uat"},
            description = "Accept Ad - Fashion, Verify accept a new Clothes Ad, Quoc Tran, ME")
    public void verifyUserCanAcceptNewAdClothes() {
        // Initialize objects
        CM_API_Ads_InsertFashion cm_api_ads_insertFashion = new CM_API_Ads_InsertFashion();
        CM_Login cm_login = new CM_Login();
        PrivateDashboard privateDashboard = new PrivateDashboard();

        // Create new ad using API
        cm_api_ads_insertFashion.insertNewAdClothes(true, "accept");

        // Go to manage ad
        cm_login.loginAndGoToManageAd(true, false);

        // Verify published ad displayed on tab "Đang Bán"
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "Đang Bán");
    }

    @Test(
            description = "Accept Ad - Fashion, Verify accept a new Watch Ad, Vu Hoang, ME")
    public void verifyUserCanAcceptNewAdWatch() {
        // Initialize objects
        CM_API_Ads_InsertFashion cm_api_ads_insertFashion = new CM_API_Ads_InsertFashion();
        CM_Login cm_login = new CM_Login();
        PrivateDashboard privateDashboard = new PrivateDashboard();

        // Create new ad using API
        cm_api_ads_insertFashion.insertNewAdWatch(true, "accept");

        // Go to manage ad
        cm_login.loginAndGoToManageAd(false, false);

        // Verify published ad displayed on tab "Đang Bán"
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "Đang Bán");
    }
}
