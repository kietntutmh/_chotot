package projects.functions.chotot._common.api.ads.edit;

import com.vn.chotot.data.ExcelProvider;
import projects.functions.chotot._common.api.cp.CM_CP_API_AcceptAd;
import projects.functions.chotot._common.api.cp.CM_CP_API_RejectAd;

import java.util.ArrayList;
import java.util.List;

import static api.configuration.DataConfig.adEditVehicleExcelFile;
import static api.feature.ads.EditAds.editNewAd;
import static api.utils.GetJSONString.extractJSONMapping_EditAd_Vehicle;
import static desktop.configuration.LoginConfig.tempUserPhone;
import static projects.functions.chotot.payment.PayOrder_API_Functions.paymentOrderWithDongTot;
import static projects.functions.chotot.payment.TopupDongTot_API_Functions.topupDongTotWithMomo;

public class CM_API_Ads_EditVEH {
    private final CM_CP_API_AcceptAd cm_api_acceptEditAd;
    private final CM_CP_API_RejectAd cm_api_rejectEditAd;
    private final ExcelProvider excelDataProvider;
    private String jsonData = "";
    private List<String> dataKeys, dataValues;

    public CM_API_Ads_EditVEH() {
        cm_api_acceptEditAd = new CM_CP_API_AcceptAd();
        cm_api_rejectEditAd = new CM_CP_API_RejectAd();
        excelDataProvider = new ExcelProvider();
        dataKeys = new ArrayList<>();
        dataValues = new ArrayList<>();
    }

    // Car

    public String editNewAdCar(String adID) {
        return editNewAdCar(adID, "no action");
    }

    public String editNewAdCar(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditVehicleExcelFile, "Car");
        dataKeys = excelDataProvider.getRowData(1);
        dataValues = excelDataProvider.getRowData(2);

        // Get JSON of Edit Ad
        jsonData = extractJSONMapping_EditAd_Vehicle(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_VEH_Car(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_VEH_Car(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdCarPro(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditVehicleExcelFile, "Car");
        dataKeys = excelDataProvider.getRowData(6);
        dataValues = excelDataProvider.getRowData(7);

        jsonData = extractJSONMapping_EditAd_Vehicle(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_VEH_Car(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_VEH_Car(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                topupDongTotWithMomo(tempUserPhone);
                paymentOrderWithDongTot();
                // Accept
                cm_api_acceptEditAd.acceptAd_VEH_Car(dataKeys, dataValues);
                break;
        }

        return adEditedID;
    }

    public String editNewAdCarBuy(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditVehicleExcelFile, "Car");
        dataKeys = excelDataProvider.getRowData(11);
        dataValues = excelDataProvider.getRowData(12);

        jsonData = extractJSONMapping_EditAd_Vehicle(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_VEH_Car(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_VEH_Car(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdCarBuyPro(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditVehicleExcelFile, "Car");
        dataKeys = excelDataProvider.getRowData(16);
        dataValues = excelDataProvider.getRowData(17);

        jsonData = extractJSONMapping_EditAd_Vehicle(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_VEH_Car(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_VEH_Car(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdCarChotot(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditVehicleExcelFile, "Car");
        dataKeys = excelDataProvider.getRowData(21);
        dataValues = excelDataProvider.getRowData(22);

        jsonData = extractJSONMapping_EditAd_Vehicle(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_VEH_Car(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_VEH_Car(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdCarShop(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditVehicleExcelFile, "Car");
        dataKeys = excelDataProvider.getRowData(26);
        dataValues = excelDataProvider.getRowData(27);

        jsonData = extractJSONMapping_EditAd_Vehicle(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_VEH_Car(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_VEH_Car(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    // Motobike

    public String editNewAdMotorbike(String adID) {
        return editNewAdMotorbike(adID, "no action");
    }

    public String editNewAdMotorbike(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditVehicleExcelFile, "Motorbike");
        dataKeys = excelDataProvider.getRowData(1);
        dataValues = excelDataProvider.getRowData(2);

        // Get JSON of Edit Ad
        jsonData = extractJSONMapping_EditAd_Vehicle(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_VEH_Motorbike(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_VEH_Motorbike(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdMotorbikePro(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditVehicleExcelFile, "Motorbike");
        dataKeys = excelDataProvider.getRowData(6);
        dataValues = excelDataProvider.getRowData(7);

        jsonData = extractJSONMapping_EditAd_Vehicle(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_VEH_Motorbike(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_VEH_Motorbike(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                topupDongTotWithMomo(tempUserPhone);
                paymentOrderWithDongTot();
                // Accept
                cm_api_acceptEditAd.acceptAd_VEH_Motorbike(dataKeys, dataValues);
                break;
        }

        return adEditedID;
    }

    public String editNewAdMotorbikeBuy(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditVehicleExcelFile, "Motorbike");
        dataKeys = excelDataProvider.getRowData(11);
        dataValues = excelDataProvider.getRowData(12);

        jsonData = extractJSONMapping_EditAd_Vehicle(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_VEH_Motorbike(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_VEH_Motorbike(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdMotorbikeBuyPro(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditVehicleExcelFile, "Motorbike");
        dataKeys = excelDataProvider.getRowData(16);
        dataValues = excelDataProvider.getRowData(17);

        jsonData = extractJSONMapping_EditAd_Vehicle(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_VEH_Motorbike(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_VEH_Motorbike(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdMotorbikeChotot(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditVehicleExcelFile, "Motorbike");
        dataKeys = excelDataProvider.getRowData(21);
        dataValues = excelDataProvider.getRowData(22);

        jsonData = extractJSONMapping_EditAd_Vehicle(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_VEH_Motorbike(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_VEH_Motorbike(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdMotorbikeShop(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditVehicleExcelFile, "Motorbike");
        dataKeys = excelDataProvider.getRowData(26);
        dataValues = excelDataProvider.getRowData(27);

        jsonData = extractJSONMapping_EditAd_Vehicle(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_VEH_Motorbike(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_VEH_Motorbike(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    // Trucks

    public String editNewAdTrucks(String adID) {
        return editNewAdTrucks(adID, "no action");
    }

    public String editNewAdTrucks(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditVehicleExcelFile, "Trucks");
        dataKeys = excelDataProvider.getRowData(1);
        dataValues = excelDataProvider.getRowData(2);

        // Get JSON of Edit Ad
        jsonData = extractJSONMapping_EditAd_Vehicle(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_VEH_Truck(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_VEH_Truck(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdTrucksPro(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditVehicleExcelFile, "Trucks");
        dataKeys = excelDataProvider.getRowData(6);
        dataValues = excelDataProvider.getRowData(7);

        jsonData = extractJSONMapping_EditAd_Vehicle(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_VEH_Truck(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_VEH_Truck(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                topupDongTotWithMomo(tempUserPhone);
                paymentOrderWithDongTot();
                // Accept
                cm_api_acceptEditAd.acceptAd_VEH_Truck(dataKeys, dataValues);
                break;
        }

        return adEditedID;
    }

    public String editNewAdTrucksBuy(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditVehicleExcelFile, "Trucks");
        dataKeys = excelDataProvider.getRowData(11);
        dataValues = excelDataProvider.getRowData(12);

        jsonData = extractJSONMapping_EditAd_Vehicle(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_VEH_Truck(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_VEH_Truck(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdTrucksBuyPro(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditVehicleExcelFile, "Trucks");
        dataKeys = excelDataProvider.getRowData(16);
        dataValues = excelDataProvider.getRowData(17);

        jsonData = extractJSONMapping_EditAd_Vehicle(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_VEH_Truck(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_VEH_Truck(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdTrucksChotot(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditVehicleExcelFile, "Trucks");
        dataKeys = excelDataProvider.getRowData(21);
        dataValues = excelDataProvider.getRowData(22);

        jsonData = extractJSONMapping_EditAd_Vehicle(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_VEH_Truck(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_VEH_Truck(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdTrucksShop(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditVehicleExcelFile, "Trucks");
        dataKeys = excelDataProvider.getRowData(26);
        dataValues = excelDataProvider.getRowData(27);

        jsonData = extractJSONMapping_EditAd_Vehicle(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_VEH_Truck(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_VEH_Truck(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    // Electric_Vehicle

    public String editNewAdElectric_Vehicle(String adID) {
        return editNewAdElectric_Vehicle(adID, "no action");
    }

    public String editNewAdElectric_Vehicle(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditVehicleExcelFile, "Electric_Vehicle");
        dataKeys = excelDataProvider.getRowData(1);
        dataValues = excelDataProvider.getRowData(2);

        // Get JSON of Edit Ad
        jsonData = extractJSONMapping_EditAd_Vehicle(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_VEH_Electric(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_VEH_Electric(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdElectric_VehiclePro(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditVehicleExcelFile, "Electric_Vehicle");
        dataKeys = excelDataProvider.getRowData(6);
        dataValues = excelDataProvider.getRowData(7);

        jsonData = extractJSONMapping_EditAd_Vehicle(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_VEH_Electric(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_VEH_Electric(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdElectric_VehicleBuy(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditVehicleExcelFile, "Electric_Vehicle");
        dataKeys = excelDataProvider.getRowData(11);
        dataValues = excelDataProvider.getRowData(12);

        jsonData = extractJSONMapping_EditAd_Vehicle(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_VEH_Electric(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_VEH_Electric(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdElectric_VehicleBuyPro(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditVehicleExcelFile, "Electric_Vehicle");
        dataKeys = excelDataProvider.getRowData(16);
        dataValues = excelDataProvider.getRowData(17);

        jsonData = extractJSONMapping_EditAd_Vehicle(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_VEH_Electric(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_VEH_Electric(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdElectric_VehicleChotot(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditVehicleExcelFile, "Electric_Vehicle");
        dataKeys = excelDataProvider.getRowData(21);
        dataValues = excelDataProvider.getRowData(22);

        jsonData = extractJSONMapping_EditAd_Vehicle(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_VEH_Electric(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_VEH_Electric(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdElectric_VehicleShop(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditVehicleExcelFile, "Electric_Vehicle");
        dataKeys = excelDataProvider.getRowData(26);
        dataValues = excelDataProvider.getRowData(27);

        jsonData = extractJSONMapping_EditAd_Vehicle(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_VEH_Electric(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_VEH_Electric(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    // Bicycles

    public String editNewAdBicycles(String adID) {
        return editNewAdBicycles(adID, "no action");
    }

    public String editNewAdBicycles(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditVehicleExcelFile, "Bicycles");
        dataKeys = excelDataProvider.getRowData(1);
        dataValues = excelDataProvider.getRowData(2);

        // Get JSON of Edit Ad
        jsonData = extractJSONMapping_EditAd_Vehicle(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_VEH_Bicycles(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_VEH_Bicycles(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdBicyclesPro(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditVehicleExcelFile, "Bicycles");
        dataKeys = excelDataProvider.getRowData(6);
        dataValues = excelDataProvider.getRowData(7);

        jsonData = extractJSONMapping_EditAd_Vehicle(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_VEH_Bicycles(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_VEH_Bicycles(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdBicyclesBuy(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditVehicleExcelFile, "Bicycles");
        dataKeys = excelDataProvider.getRowData(11);
        dataValues = excelDataProvider.getRowData(12);

        jsonData = extractJSONMapping_EditAd_Vehicle(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_VEH_Bicycles(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_VEH_Bicycles(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdBicyclesBuyPro(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditVehicleExcelFile, "Bicycles");
        dataKeys = excelDataProvider.getRowData(16);
        dataValues = excelDataProvider.getRowData(17);

        jsonData = extractJSONMapping_EditAd_Vehicle(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_VEH_Bicycles(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_VEH_Bicycles(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdBicyclesChotot(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditVehicleExcelFile, "Bicycles");
        dataKeys = excelDataProvider.getRowData(21);
        dataValues = excelDataProvider.getRowData(22);

        jsonData = extractJSONMapping_EditAd_Vehicle(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_VEH_Bicycles(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_VEH_Bicycles(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdBicyclesShop(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditVehicleExcelFile, "Bicycles");
        dataKeys = excelDataProvider.getRowData(26);
        dataValues = excelDataProvider.getRowData(27);

        jsonData = extractJSONMapping_EditAd_Vehicle(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_VEH_Bicycles(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_VEH_Bicycles(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    // Vehicles_Parts

    public String editNewAdVehicles_Parts(String adID) {
        return editNewAdVehicles_Parts(adID, "no action");
    }

    public String editNewAdVehicles_Parts(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditVehicleExcelFile, "Vehicles_Parts");
        dataKeys = excelDataProvider.getRowData(1);
        dataValues = excelDataProvider.getRowData(2);

        // Get JSON of Edit Ad
        jsonData = extractJSONMapping_EditAd_Vehicle(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_VEH_Vehicles_Parts(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_VEH_Vehicles_Parts(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdVehicles_PartsPro(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditVehicleExcelFile, "Vehicles_Parts");
        dataKeys = excelDataProvider.getRowData(6);
        dataValues = excelDataProvider.getRowData(7);

        jsonData = extractJSONMapping_EditAd_Vehicle(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_VEH_Vehicles_Parts(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_VEH_Vehicles_Parts(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdVehicles_PartsBuy(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditVehicleExcelFile, "Vehicles_Parts");
        dataKeys = excelDataProvider.getRowData(11);
        dataValues = excelDataProvider.getRowData(12);

        jsonData = extractJSONMapping_EditAd_Vehicle(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_VEH_Vehicles_Parts(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_VEH_Vehicles_Parts(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdVehicles_PartsBuyPro(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditVehicleExcelFile, "Vehicles_Parts");
        dataKeys = excelDataProvider.getRowData(16);
        dataValues = excelDataProvider.getRowData(17);

        jsonData = extractJSONMapping_EditAd_Vehicle(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_VEH_Vehicles_Parts(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_VEH_Vehicles_Parts(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdVehicles_PartsChotot(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditVehicleExcelFile, "Vehicles_Parts");
        dataKeys = excelDataProvider.getRowData(21);
        dataValues = excelDataProvider.getRowData(22);

        jsonData = extractJSONMapping_EditAd_Vehicle(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_VEH_Vehicles_Parts(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_VEH_Vehicles_Parts(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdVehicles_PartsShop(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditVehicleExcelFile, "Vehicles_Parts");
        dataKeys = excelDataProvider.getRowData(26);
        dataValues = excelDataProvider.getRowData(27);

        jsonData = extractJSONMapping_EditAd_Vehicle(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_VEH_Vehicles_Parts(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_VEH_Vehicles_Parts(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    // Other_Vehicles

    public String editNewAdOther_Vehicles(String adID) {
        return editNewAdOther_Vehicles(adID, "no action");
    }

    public String editNewAdOther_Vehicles(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditVehicleExcelFile, "Other_Vehicles");
        dataKeys = excelDataProvider.getRowData(1);
        dataValues = excelDataProvider.getRowData(2);

        // Get JSON of Edit Ad
        jsonData = extractJSONMapping_EditAd_Vehicle(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_VEH_Other_Vehicles(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_VEH_Other_Vehicles(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdOther_VehiclesPro(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditVehicleExcelFile, "Other_Vehicles");
        dataKeys = excelDataProvider.getRowData(6);
        dataValues = excelDataProvider.getRowData(7);

        jsonData = extractJSONMapping_EditAd_Vehicle(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_VEH_Other_Vehicles(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_VEH_Other_Vehicles(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdOther_VehiclesBuy(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditVehicleExcelFile, "Other_Vehicles");
        dataKeys = excelDataProvider.getRowData(11);
        dataValues = excelDataProvider.getRowData(12);

        jsonData = extractJSONMapping_EditAd_Vehicle(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_VEH_Other_Vehicles(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_VEH_Other_Vehicles(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdOther_VehiclesBuyPro(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditVehicleExcelFile, "Other_Vehicles");
        dataKeys = excelDataProvider.getRowData(16);
        dataValues = excelDataProvider.getRowData(17);

        jsonData = extractJSONMapping_EditAd_Vehicle(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_VEH_Other_Vehicles(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_VEH_Other_Vehicles(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdOther_VehiclesChotot(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditVehicleExcelFile, "Other_Vehicles");
        dataKeys = excelDataProvider.getRowData(21);
        dataValues = excelDataProvider.getRowData(22);

        jsonData = extractJSONMapping_EditAd_Vehicle(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_VEH_Other_Vehicles(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_VEH_Other_Vehicles(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdOther_VehiclesShop(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditVehicleExcelFile, "Other_Vehicles");
        dataKeys = excelDataProvider.getRowData(26);
        dataValues = excelDataProvider.getRowData(27);

        jsonData = extractJSONMapping_EditAd_Vehicle(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_VEH_Other_Vehicles(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_VEH_Other_Vehicles(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }
}
