package api.scenarios_old.ads.edit;

import api.base.BaseAPITest;
import desktop.pages.Dashboard.PrivateDashboard;
import projects.functions.chotot._common.CM_Login;
import projects.functions.chotot._common.api.ads.edit.CM_API_Ads_EditServices;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertServices;

import static api.configuration.DataConfig.tempAdID;

public class Services extends BaseAPITest {
    CM_API_Ads_EditServices cm_api_ads_edit;
    CM_API_Ads_InsertServices cm_api_ads_insert;
    PrivateDashboard privateDashboard;
    CM_Login cm_login;

    public void initObjects() {
        cm_api_ads_edit = new CM_API_Ads_EditServices();
        cm_api_ads_insert = new CM_API_Ads_InsertServices();
        privateDashboard = new PrivateDashboard();
        cm_login = new CM_Login();
    }

    public void verifyEditAPI_Services_Service() {
        initObjects();
        cm_api_ads_insert.insertNewServiceAd(true, "Accept");
        cm_api_ads_edit.editNewAdService(tempAdID, "Accept");
    }

    public void verifyEditAPI_Services_Travel() {
        initObjects();
        cm_api_ads_insert.insertNewTravelAd(true, "Accept");
        cm_api_ads_edit.editNewAdTravel(tempAdID, "Accept");
    }
}
