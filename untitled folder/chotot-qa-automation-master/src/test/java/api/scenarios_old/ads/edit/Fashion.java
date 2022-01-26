package api.scenarios_old.ads.edit;

import api.base.BaseAPITest;
import desktop.pages.Dashboard.PrivateDashboard;
import projects.functions.chotot._common.CM_Login;
import projects.functions.chotot._common.api.ads.edit.CM_API_Ads_EditFashion;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertFashion;

import static api.configuration.DataConfig.tempAdID;

public class Fashion extends BaseAPITest {
    CM_API_Ads_EditFashion cm_api_ads_edit;
    CM_API_Ads_InsertFashion cm_api_ads_insert;
    PrivateDashboard privateDashboard;
    CM_Login cm_login;

    public void initObjects() {
        cm_api_ads_edit = new CM_API_Ads_EditFashion();
        cm_api_ads_insert = new CM_API_Ads_InsertFashion();
        privateDashboard = new PrivateDashboard();
        cm_login = new CM_Login();
    }

    public void verifyEditAPI_Fashion_Clothes() {
        initObjects();
        cm_api_ads_insert.insertNewAdClothes(true, "Accept");
        cm_api_ads_edit.editNewAdClothes(tempAdID, "Accept");
    }

    public void verifyEditAPI_Fashion_Watch() {
        initObjects();
        cm_api_ads_insert.insertNewAdWatch(true, "Accept");
        cm_api_ads_edit.editNewAdWatch(tempAdID, "Accept");
    }

    public void verifyEditAPI_Fashion_Shoes() {
        initObjects();
        cm_api_ads_insert.insertNewAdShoes(true, "Accept");
        cm_api_ads_edit.editNewAdShoes(tempAdID, "Accept");
    }

    public void verifyEditAPI_Fashion_Handbag() {
        initObjects();
        cm_api_ads_insert.insertNewAdHandbag(true, "Accept");
        cm_api_ads_edit.editNewAdHandbag(tempAdID, "Accept");
    }

    public void verifyEditAPI_Fashion_Perfume() {
        initObjects();
        cm_api_ads_insert.insertNewAdPerfume(true, "Accept");
        cm_api_ads_edit.editNewAdPerfume(tempAdID, "Accept");
    }

    public void verifyEditAPI_Fashion_Accessories() {
        initObjects();
        cm_api_ads_insert.insertNewAdAccessories(true, "Accept");
        cm_api_ads_edit.editNewAdAccessories(tempAdID, "Accept");
    }
}
