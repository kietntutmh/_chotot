package desktop.scenarios_old.cp.ad_review.accept;

import desktop.pages.Dashboard.PrivateDashboard;
import org.testng.annotations.Test;
import projects.functions.chotot._common.CM_Login;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertPTY;
import projects.functions.chotot._common.cp.CM_CP_AcceptAd;

import static api.configuration.DataConfig.tempAdSubject;

public class CP_VerifyUserCanAcceptNewAdPTY extends CP_AcceptAd_SetAccount {

    @Test(
            groups = {"dev", "uat"},
            description = "Accept Ad - PTY, Verify accept a new House Ad, Quoc Tran, ME")
    public void verifyUserCanAcceptNewAdHouse() {
        // Initialize objects
        CM_API_Ads_InsertPTY cm_api_ads_insertPTY = new CM_API_Ads_InsertPTY();
        CM_CP_AcceptAd cm_cp_acceptAd = new CM_CP_AcceptAd();
        CM_Login cm_login = new CM_Login();
        PrivateDashboard privateDashboard = new PrivateDashboard();

        // Create new ad using API
        cm_api_ads_insertPTY.insertNewAdHouse(true, "accept");

        // Go to manage ad
        cm_login.loginAndGoToManageAd(true, false);

        // Verify published ad displayed on tab "Đang Bán"
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "Đang Bán");
    }
}
