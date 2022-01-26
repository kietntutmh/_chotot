package desktop.scenarios_old.cp.ad_review.reject;

import desktop.pages.Dashboard.PrivateDashboard;
import projects.functions.chotot._common.CM_Login;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertVehicle;

import static api.configuration.DataConfig.tempAdSubject;

public class CP_VerifyUserCanRejectNewAdVehicle extends CP_RejectAd_SetAccount {

    public void verifyUserCanRejectNewAdCar() {
        // Initialize objects
        CM_API_Ads_InsertVehicle cm_api_ads_insertVehicle = new CM_API_Ads_InsertVehicle();
        CM_Login cm_login = new CM_Login();
        PrivateDashboard privateDashboard = new PrivateDashboard();

        // Create new ad using API
        cm_api_ads_insertVehicle.insertNewAdCar(true, "reject");

        // Go to manage ad
        cm_login.loginAndGoToManageAd(true, false);

        // Verify rejected ad displayed on tab "BỊ TỪ CHỐI"
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");
    }
}
