package api.scenarios_old.ads.edit;

import api.base.BaseAPITest;
import desktop.pages.Dashboard.PrivateDashboard;
import projects.functions.chotot._common.CM_Login;
import projects.functions.chotot._common.api.ads.edit.CM_API_Ads_EditELT;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertELT;

import static api.configuration.DataConfig.tempAdID;

public class ELT extends BaseAPITest {
    CM_API_Ads_EditELT cm_api_ads_editELT;
    CM_API_Ads_InsertELT cm_api_ads_insertELT;
    PrivateDashboard privateDashboard;
    CM_Login cm_login;

    public void initObjects() {
        cm_api_ads_editELT = new CM_API_Ads_EditELT();
        cm_api_ads_insertELT = new CM_API_Ads_InsertELT();
        privateDashboard = new PrivateDashboard();
        cm_login = new CM_Login();
    }

    public void verifyEditAPI_ELT_Laptop() {
        initObjects();
        cm_api_ads_insertELT.insertNewAdLaptop("Accept");
        cm_api_ads_editELT.editNewAdLaptop(tempAdID, "Accept");
    }

    public void verifyEditAPI_ELT_Laptop_Pro() {
        initObjects();
        cm_api_ads_insertELT.insertNewAdLaptopPro("Accept");
        cm_api_ads_editELT.editNewAdLaptopPro(tempAdID, "Accept");
    }

    public void verifyEditAPI_ELT_Phone() {
        initObjects();
        cm_api_ads_insertELT.insertNewAdPhone("Accept");
        cm_api_ads_editELT.editNewAdPhone(tempAdID, "Accept");
    }

    public void verifyEditAPI_ELT_Phone_Pro() {
        initObjects();
        cm_api_ads_insertELT.insertNewAdPhonePro("Accept");
        cm_api_ads_editELT.editNewAdPhonePro(tempAdID, "Accept");
    }

    public void verifyEditAPI_ELT_Tablet() {
        initObjects();
        cm_api_ads_insertELT.insertNewAdTablet("Accept");
        cm_api_ads_editELT.editNewAdTablet(tempAdID, "Accept");
    }

    public void verifyEditAPI_ELT_Tablet_Pro() {
        initObjects();
        cm_api_ads_insertELT.insertNewAdTabletPro("Accept");
        cm_api_ads_editELT.editNewAdTabletPro(tempAdID, "Accept");
    }

    public void verifyEditAPI_PC() {
        initObjects();
        cm_api_ads_insertELT.insertNewAdPC("Accept");
        cm_api_ads_editELT.editNewAdPC(tempAdID, "Accept");
    }

    public void verifyEditAPI_PC_Pro() {
        initObjects();
        cm_api_ads_insertELT.insertNewAdPCPro("Accept");
        cm_api_ads_editELT.editNewAdPCPro(tempAdID, "Accept");
    }

    public void verifyEditAPI_Camera() {
        initObjects();
        cm_api_ads_insertELT.insertNewAdCamera("Accept");
        cm_api_ads_editELT.editNewAdCamera(tempAdID, "Accept");
    }

    public void verifyEditAPI_Camera_Pro() {
        initObjects();
        cm_api_ads_insertELT.insertNewAdCameraPro("Accept");
        cm_api_ads_editELT.editNewAdCameraPro(tempAdID, "Accept");
    }

    public void verifyEditAPI_Sound() {
        initObjects();
        cm_api_ads_insertELT.insertNewAdSound("Accept");
        cm_api_ads_editELT.editNewAdSound(tempAdID, "Accept");
    }

    public void verifyEditAPI_Sound_Pro() {
        initObjects();
        cm_api_ads_insertELT.insertNewAdSoundPro("Accept");
        cm_api_ads_editELT.editNewAdSoundPro(tempAdID, "Accept");
    }

    public void verifyEditAPI_Wearable() {
        initObjects();
        cm_api_ads_insertELT.insertNewAdWearable("Accept");
        cm_api_ads_editELT.editNewAdWearable(tempAdID, "Accept");
    }

    public void verifyEditAPI_Wearable_Pro() {
        initObjects();
        cm_api_ads_insertELT.insertNewAdWearablePro("Accept");
        cm_api_ads_editELT.editNewAdWearablePro(tempAdID, "Accept");
    }

    public void verifyEditAPI_Accessories() {
        initObjects();
        cm_api_ads_insertELT.insertNewAdAccessories("Accept");
        cm_api_ads_editELT.editNewAdAccessories(tempAdID, "Accept");
    }

    public void verifyEditAPI_Accessories_Pro() {
        initObjects();
        cm_api_ads_insertELT.insertNewAdAccessoriesPro("Accept");
        cm_api_ads_editELT.editNewAdAccessoriesPro(tempAdID, "Accept");
    }

    public void verifyEditAPI_PC_Component() {
        initObjects();
        cm_api_ads_insertELT.insertNewAdPC_Component("Accept");
        cm_api_ads_editELT.editNewAdPC_Component(tempAdID, "Accept");
    }

    public void verifyEditAPI_PC_Component_Pro() {
        initObjects();
        cm_api_ads_insertELT.insertNewAdPC_ComponentPro("Accept");
        cm_api_ads_editELT.editNewAdPC_ComponentPro(tempAdID, "Accept");
    }
}
