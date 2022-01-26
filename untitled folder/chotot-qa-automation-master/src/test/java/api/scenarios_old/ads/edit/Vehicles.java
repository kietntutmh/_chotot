package api.scenarios_old.ads.edit;

import api.base.BaseAPITest;
import desktop.pages.Dashboard.PrivateDashboard;
import projects.functions.chotot._common.CM_Login;
import projects.functions.chotot._common.api.ads.edit.CM_API_Ads_EditVEH;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertVehicle;

import static api.configuration.DataConfig.tempAdID;

public class Vehicles extends BaseAPITest {
    CM_API_Ads_EditVEH cm_api_ads_editVEH;
    CM_API_Ads_InsertVehicle cm_api_ads_insertVEH;
    PrivateDashboard privateDashboard;
    CM_Login cm_login;

    public void initObjects() {
        cm_api_ads_editVEH = new CM_API_Ads_EditVEH();
        cm_api_ads_insertVEH = new CM_API_Ads_InsertVehicle();
        privateDashboard = new PrivateDashboard();
        cm_login = new CM_Login();
    }

    public void verifyEditAPI_VEH_Car() {
        initObjects();
        cm_api_ads_insertVEH.insertNewAdCar(true, "Accept");
        cm_api_ads_editVEH.editNewAdCar(tempAdID, "Accept");
    }

    public void verifyEditAPI_VEH_Car_Pro() {
        initObjects();
        cm_api_ads_insertVEH.insertNewAdCarForPro(true, "Accept");
        cm_api_ads_editVEH.editNewAdCarPro(tempAdID, "Accept");
    }

    public void verifyEditAPI_VEH_Motorbike() {
        initObjects();
        cm_api_ads_insertVEH.insertNewAdMotorbike(true, "Accept");
        cm_api_ads_editVEH.editNewAdMotorbike(tempAdID, "Accept");
    }

    public void verifyEditAPI_VEH_Motorbike_Pro() {
        initObjects();
        cm_api_ads_insertVEH.insertNewAdMotorbikeForPro(true, "Accept");
        cm_api_ads_editVEH.editNewAdMotorbikePro(tempAdID, "Accept");
    }

    public void verifyEditAPI_VEH_Truck() {
        initObjects();
        cm_api_ads_insertVEH.insertNewAdTrucks(true, "Accept");
        cm_api_ads_editVEH.editNewAdTrucks(tempAdID, "Accept");
    }

    public void verifyEditAPI_VEH_Truck_Pro() {
        initObjects();
        cm_api_ads_insertVEH.insertNewAdTrucksForPro(true, "Accept");
        cm_api_ads_editVEH.editNewAdTrucksPro(tempAdID, "Accept");
    }

    public void verifyEditAPI_VEH_Electric_Vehicle() {
        initObjects();
        cm_api_ads_insertVEH.insertNewAdBicycles(true, "Accept");
        cm_api_ads_editVEH.editNewAdBicycles(tempAdID, "Accept");
    }

    public void verifyEditAPI_VEH_Electric_Vehicle_Pro() {
        initObjects();
        cm_api_ads_insertVEH.insertNewAdBicyclesForPro(true, "Accept");
        cm_api_ads_editVEH.editNewAdBicyclesPro(tempAdID, "Accept");
    }

    public void verifyEditAPI_VEH_Other_Vehicles() {
        initObjects();
        cm_api_ads_insertVEH.insertNewAdOther_Vehicles(true, "Accept");
        cm_api_ads_editVEH.editNewAdOther_Vehicles(tempAdID, "Accept");
    }

    public void verifyEditAPI_VEH_Other_Vehicles_Pro() {
        initObjects();
        cm_api_ads_insertVEH.insertNewAdOther_VehiclesForPro(true, "Accept");
        cm_api_ads_editVEH.editNewAdOther_VehiclesPro(tempAdID, "Accept");
    }
}
