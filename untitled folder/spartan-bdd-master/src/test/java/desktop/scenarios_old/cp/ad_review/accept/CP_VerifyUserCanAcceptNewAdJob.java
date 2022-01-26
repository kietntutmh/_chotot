package desktop.scenarios_old.cp.ad_review.accept;

import desktop.pages.Dashboard.PrivateDashboard;
import org.testng.annotations.Test;
import projects.functions.chotot._common.CM_Login;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertJob;

import static api.configuration.DataConfig.tempAdSubject;

public class CP_VerifyUserCanAcceptNewAdJob extends CP_AcceptAd_SetAccount {

    @Test(
            groups = {"dev", "uat"},
            description = "Accept Ad - Job, Verify accept a new Job Ad, Quoc Tran, ME")
    public void verifyUserCanAcceptNewAdJob() {
        // Initialize objects
        CM_API_Ads_InsertJob cm_api_ads_insertJob = new CM_API_Ads_InsertJob();
        CM_Login cm_login = new CM_Login();
        PrivateDashboard privateDashboard = new PrivateDashboard();

        // Create new ad using API
        cm_api_ads_insertJob.insertNewAdJob("accept");

        // Go to manage ad
        cm_login.loginAndGoToManageAd(true, false);

        // Verify published ad displayed on tab "Đang Bán"
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "Đang Bán");
    }
}
