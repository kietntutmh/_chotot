package desktop.scenarios_old.cp.ad_review.accept;

import desktop.pages.Dashboard.PrivateDashboard;
import org.testng.annotations.Test;
import projects.functions.chotot._common.CM_Login;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertEntertainment;
import projects.functions.chotot._common.cp.CM_CP_AcceptAd;

import static api.configuration.DataConfig.tempAdSubject;

public class CP_VerifyUserCanAcceptNewAdEntertainment extends CP_AcceptAd_SetAccount {

    @Test(
            groups = {"dev", "uat"},
            description = "Accept Ad - Entertainment, Verify accept a new Instrument Ad, Quoc Tran, ME")
    public void verifyUserCanAcceptNewAdInstrument() {
        // Initialize objects
        CM_API_Ads_InsertEntertainment cm_api_ads_insertEntertainment =
                new CM_API_Ads_InsertEntertainment();
        CM_CP_AcceptAd cm_cp_acceptAd = new CM_CP_AcceptAd();
        CM_Login cm_login = new CM_Login();
        PrivateDashboard privateDashboard = new PrivateDashboard();

        // Create new ad using API
        cm_api_ads_insertEntertainment.insertNewAdInstrument(true, "accept");

        // Go to manage ad
        cm_login.loginAndGoToManageAd(false, false);

        // Verify published ad displayed on tab "Đang Bán"
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "Đang Bán");
    }
}
