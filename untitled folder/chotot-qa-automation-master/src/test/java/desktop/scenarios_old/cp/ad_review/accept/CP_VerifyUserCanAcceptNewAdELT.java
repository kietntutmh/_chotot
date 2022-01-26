package desktop.scenarios_old.cp.ad_review.accept;

import desktop.pages.Dashboard.PrivateDashboard;
import projects.functions.chotot._common.CM_Login;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertELT;

import static api.configuration.DataConfig.tempAdSubject;

@Deprecated
public class CP_VerifyUserCanAcceptNewAdELT extends CP_AcceptAd_SetAccount {

    //OLD : VUHOANG needs to remove
    @Deprecated
    public void verifyUserCanAcceptNewAdPhone() {
        // Initialize objects
        CM_API_Ads_InsertELT cm_api_ads_insertELT = new CM_API_Ads_InsertELT();
        CM_Login cm_login = new CM_Login();
        PrivateDashboard privateDashboard = new PrivateDashboard();

        // Create new ad using API
        cm_api_ads_insertELT.insertNewAdPhone("accept");

        // Go to manage ad
        cm_login.loginAndGoToManageAd(false, false);

        // Verify published ad displayed on tab "Đang Bán"
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "Đang Bán");
    }
}
