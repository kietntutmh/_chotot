package api.scenarios_old.ads.edit;

import api.base.BaseAPITest;
import desktop.pages.Dashboard.PrivateDashboard;
import org.testng.annotations.Test;
import projects.functions.chotot._common.CM_Login;
import projects.functions.chotot._common.api.ads.edit.CM_API_Ads_EditPTY;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertPTY;

import static api.configuration.DataConfig.tempAdID;

public class PTY extends BaseAPITest {
    CM_API_Ads_EditPTY cm_api_ads_editPTY;
    CM_API_Ads_InsertPTY cm_api_ads_insertPTY;
    PrivateDashboard privateDashboard;
    CM_Login cm_login;

    public void initObjects() {
        cm_api_ads_editPTY = new CM_API_Ads_EditPTY();
        cm_api_ads_insertPTY = new CM_API_Ads_InsertPTY();
        privateDashboard = new PrivateDashboard();
        cm_login = new CM_Login();
    }

    public void verifyEditAPI_PTY_House() {
        initObjects();
        cm_api_ads_insertPTY.insertNewAdHouse("Accept");
        cm_api_ads_editPTY.editNewAdHouse(tempAdID, "Accept");
    }

    public void verifyEditAPI_PTY_House_Pro() {
        initObjects();
        cm_api_ads_insertPTY.insertNewAdHousePro("Accept");
        cm_api_ads_editPTY.editNewAdHousePro(tempAdID, "Accept");
    }

    public void verifyEditAPI_PTY_Apartment() {
        initObjects();
        cm_api_ads_insertPTY.insertNewAdApartment("Accept");
        cm_api_ads_editPTY.editNewAdApartment(tempAdID, "Accept");
    }

    public void verifyEditAPI_PTY_Apartment_Pro() {
        initObjects();
        cm_api_ads_insertPTY.insertNewAdApartmentPro("Accept");
        cm_api_ads_editPTY.editNewAdApartmentPro(tempAdID, "Accept");
    }

    @Test(description = "Edit Ad - PTY, Verify edit Land ad successfully, VU HOANG, SRE")
    public void verifyEditAPI_PTY_Land() {
        initObjects();
        cm_api_ads_insertPTY.insertNewAdLand("Accept");
        cm_api_ads_editPTY.editNewAdLand(tempAdID, "Accept");
    }

    @Test(description = "Edit Ad - PTY, Verify edit Land Pro ad successfully, VU HOANG, SRE")
    public void verifyEditAPI_PTY_Land_Pro() {
        initObjects();
        cm_api_ads_insertPTY.insertNewAdLandPro("Accept");
        cm_api_ads_editPTY.editNewAdLandPro(tempAdID, "Accept");
    }

    @Test(description = "Edit Ad - PTY, Verify edit Office ad successfully, VU HOANG, SRE")
    public void verifyEditAPI_PTY_Office() {
        initObjects();
        cm_api_ads_insertPTY.insertNewAdOffice("Accept");
        cm_api_ads_editPTY.editNewAdOffice(tempAdID, "Accept");
    }

    @Test(description = "Edit Ad - PTY, Verify edit Office Pro ad successfully, VU HOANG, SRE")
    public void verifyEditAPI_PTY_Office_Pro() {
        initObjects();
        cm_api_ads_insertPTY.insertNewAdOfficePro("Accept");
        cm_api_ads_editPTY.editNewAdOfficePro(tempAdID, "Accept");
    }

    @Test(description = "Edit Ad - PTY, Verify edit Office ad successfully, VU HOANG, SRE")
    public void verifyEditAPI_PTY_Room() {
        initObjects();
        cm_api_ads_insertPTY.insertNewAdRoomForLease("Accept");
        cm_api_ads_editPTY.editNewAdRoom(tempAdID, "Accept");
    }

    @Test(description = "Edit Ad - PTY, Verify edit Office Pro ad successfully, VU HOANG, SRE")
    public void verifyEditAPI_PTY_Room_Pro() {
        initObjects();
        cm_api_ads_insertPTY.insertNewAdRoomForLeasePro("Accept");
        cm_api_ads_editPTY.editNewAdRoomPro(tempAdID, "Accept");
    }
}
