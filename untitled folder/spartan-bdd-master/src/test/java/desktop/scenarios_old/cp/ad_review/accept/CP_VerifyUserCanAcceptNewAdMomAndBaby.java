package desktop.scenarios_old.cp.ad_review.accept;

import desktop.pages.Dashboard.PrivateDashboard;
import org.testng.annotations.Test;
import projects.functions.chotot._common.CM_Login;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertMomAndBaby;
import projects.functions.chotot._common.cp.CM_CP_AcceptAd;

import static api.configuration.DataConfig.tempAdSubject;

public class CP_VerifyUserCanAcceptNewAdMomAndBaby extends CP_AcceptAd_SetAccount {

    CM_API_Ads_InsertMomAndBaby cm_api_ads_insertMomAndBaby;
    CM_CP_AcceptAd cm_cp_acceptAd;
    CM_Login cm_login;
    PrivateDashboard privateDashboard;

    public void initObjects() {
        cm_api_ads_insertMomAndBaby = new CM_API_Ads_InsertMomAndBaby();
        cm_cp_acceptAd = new CM_CP_AcceptAd();
        cm_login = new CM_Login();
        privateDashboard = new PrivateDashboard();
    }

    @Test(
            groups = {"dev", "uat"},
            description = "Accept Ad - Mom_And_Baby, Verify accept a new Mom And Baby Ad, Quoc Tran, ME")
    public void verifyUserCanAcceptNewAdMomAndBaby() {
        // Initialize objects
        initObjects();

        // Create new ad using API
        cm_api_ads_insertMomAndBaby.insertNewAdMomAndBaby("accept");

        // Go to manage ad
        cm_login.loginAndGoToManageAd(true, false);

        // Verify published ad displayed on tab "Đang Bán"
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "Đang Bán");
    }
}
