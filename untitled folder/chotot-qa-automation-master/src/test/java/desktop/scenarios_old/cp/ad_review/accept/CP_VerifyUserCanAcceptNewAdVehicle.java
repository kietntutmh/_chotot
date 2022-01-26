package desktop.scenarios_old.cp.ad_review.accept;

import desktop.pages.Dashboard.PrivateDashboard;
import org.testng.annotations.Test;
import projects.functions.chotot._common.CM_Login;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertVehicle;

import static api.configuration.DataConfig.tempAdSubject;

public class CP_VerifyUserCanAcceptNewAdVehicle extends CP_AcceptAd_SetAccount {

    @Test(
            groups = {"dev", "uat"},
            description = "Accept Ad - Vehicle, Verify accept a new Car Ad, Quoc Tran, ME")
    public void verifyUserCanAcceptNewAdCar() {
        // Initialize objects
        CM_API_Ads_InsertVehicle cm_api_ads_insertVehicle = new CM_API_Ads_InsertVehicle();
        CM_Login cm_login = new CM_Login();
        PrivateDashboard privateDashboard = new PrivateDashboard();

        // Create new ad using API
        cm_api_ads_insertVehicle.insertNewAdCar(true, "accept");

        // Go to manage ad
        cm_login.loginAndGoToManageAd(true, false);

        // Verify published ad displayed on tab "Đang Bán"
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "Đang Bán");
    }

    @Test(description = "Accept Ad - Vehicle, Verify accept a new Motorbike Ad, Vu Hoang, ME")
    public void verifyUserCanAcceptNewAdMotorbike() {
        // Initialize objects
        CM_API_Ads_InsertVehicle cm_api_ads_insertVehicle = new CM_API_Ads_InsertVehicle();
        CM_Login cm_login = new CM_Login();
        PrivateDashboard privateDashboard = new PrivateDashboard();

        // Create new ad using API
        cm_api_ads_insertVehicle.insertNewAdMotorbike(true, "accept");

        // Go to manage ad
        cm_login.loginAndGoToManageAd(true, false);

        // Verify published ad displayed on tab "Đang Bán"
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "Đang Bán");
    }
}
