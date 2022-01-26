package projects.functions.chotot._common.api.cp;

import org.testng.Assert;

import java.util.List;

import static api.feature.cp.RejectCP.rejectNewAd;
import static api.utils.GetJSONString.*;
import static projects.configuaration.CategoryConfig.*;

public class CM_CP_API_RejectAd {
    String bodyDataToAcceptAd = "";

    public void rejectAd_PTY_House(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_PTY_House(listKeys, listValues);
        rejectNewAd(data);
    }

    public void rejectAd_PTY_Apartment(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_PTY_Apartment(listKeys, listValues);
        rejectNewAd(data);
    }

    public void rejectAd_PTY_Land(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_PTY_Land(listKeys, listValues);
        rejectNewAd(data);
    }

    public void rejectAd_PTY_Office(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_PTY_House(listKeys, listValues);
        rejectNewAd(data);
    }

    public void rejectAd_PTY_Room(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_PTY_Room(listKeys, listValues);
        rejectNewAd(data);
    }
    // ELT

    public void rejectAd_ELT_Laptop(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_ELT_Laptop(listKeys, listValues);
        rejectNewAd(data);
    }

    public void rejectAd_ELT_Phone(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_ELT_Phone(listKeys, listValues);
        rejectNewAd(data);
    }

    public void rejectAd_ELT_Tablet(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_ELT_Tablet(listKeys, listValues);
        rejectNewAd(data);
    }

    public void rejectAd_ELT_PC(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_ELT_PC(listKeys, listValues);
        rejectNewAd(data);
    }

    public void rejectAd_ELT_Camera(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_ELT_Camera(listKeys, listValues);
        rejectNewAd(data);
    }

    public void rejectAd_ELT_Sound(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_ELT_Sound(listKeys, listValues);
        rejectNewAd(data);
    }

    public void rejectAd_ELT_Wearable(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_ELT_Wearable(listKeys, listValues);
        rejectNewAd(data);
    }

    public void rejectAd_ELT_Accessories(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_ELT_Accessories(listKeys, listValues);
        rejectNewAd(data);
    }

    public void rejectAd_ELT_PC_Component(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_ELT_PC_Component(listKeys, listValues);
        rejectNewAd(data);
    }
    // VEH

    public void rejectAd_VEH_Car(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_VEH_Car(listKeys, listValues);
        rejectNewAd(data);
    }

    public void rejectAd_VEH_Motorbike(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_VEH_Motorbike(listKeys, listValues);
        rejectNewAd(data);
    }

    public void rejectAd_VEH_Truck(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_VEH_Truck(listKeys, listValues);
        rejectNewAd(data);
    }

    public void rejectAd_VEH_Electric(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_VEH_Electric(listKeys, listValues);
        rejectNewAd(data);
    }

    public void rejectAd_VEH_Bicycles(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_VEH_Bicycles(listKeys, listValues);
        rejectNewAd(data);
    }

    public void rejectAd_VEH_Vehicles_Parts(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_VEH_Vehicles_Parts(listKeys, listValues);
        rejectNewAd(data);
    }

    public void rejectAd_VEH_Other_Vehicles(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_VEH_Other_Vehicles(listKeys, listValues);
        rejectNewAd(data);
    }
    // Entertainment

    public void rejectAd_Entertainment_Instrument(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_Entertainment_Instrument(listKeys, listValues);
        rejectNewAd(data);
    }

    public void rejectAd_Entertainment_Sport(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_Entertainment_Sport(listKeys, listValues);
        rejectNewAd(data);
    }

    public void rejectAd_Entertainment_Book(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_Entertainment_Book(listKeys, listValues);
        rejectNewAd(data);
    }

    public void rejectAd_Entertainment_Collectibles(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_Entertainment_Collectibles(listKeys, listValues);
        rejectNewAd(data);
    }

    public void rejectAd_Entertainment_Gaming(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_Entertainment_Gaming(listKeys, listValues);
        rejectNewAd(data);
    }

    public void rejectAd_Entertainment_Hobby(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_Entertainment_Hobby(listKeys, listValues);
        rejectNewAd(data);
    }
    // Fashion

    public void rejectAd_Fashion_Clothes(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_Fashion_Clothes(listKeys, listValues);
        rejectNewAd(data);
    }

    public void rejectAd_Fashion_Watch(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_Fashion_Watch(listKeys, listValues);
        rejectNewAd(data);
    }

    public void rejectAd_Fashion_Handbag(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_Fashion_Handbag(listKeys, listValues);
        rejectNewAd(data);
    }

    public void rejectAd_Fashion_Shoes(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_Fashion_Shoes(listKeys, listValues);
        rejectNewAd(data);
    }

    public void rejectAd_Fashion_Perfume(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_Fashion_Perfume(listKeys, listValues);
        rejectNewAd(data);
    }

    public void rejectAd_Fashion_Accessories(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_Fashion_Accessories(listKeys, listValues);
        rejectNewAd(data);
    }
    // Service
    // Service

    public void rejectAd_Services_Service(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_Services_Service(listKeys, listValues);
        rejectNewAd(data);
    }

    public void rejectAd_Services_Travel(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_Services_Travel(listKeys, listValues);
        rejectNewAd(data);
    }

    public void rejectAd_Job(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_Job(listKeys, listValues);
        rejectNewAd(data);
    }

    // Jobs
    public void rejectAd_MomAndBaby(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_MomAndBaby(listKeys, listValues);
        rejectNewAd(data);
    }

    //VUHOANG NEW GEN
    public void rejectAd_PTY(List<String> dataKeys, List<String> dataValues, String subCateID) {
        Assert.assertTrue(dataKeys.size() > 0, "dataKeys is null");
        Assert.assertTrue(dataValues.size() > 0, "dataValues is null");
        switch (subCateID) {
            case CATEID_PTY_HOUSE:
                bodyDataToAcceptAd = extractJSONCP_PTY_House(dataKeys, dataValues);
                break;
            case CATEID_PTY_APARTMENT:
                bodyDataToAcceptAd = extractJSONCP_PTY_Apartment(dataKeys, dataValues);
                break;
            case CATEID_PTY_LAND:
                bodyDataToAcceptAd = extractJSONCP_PTY_Land(dataKeys, dataValues);
                break;
            case CATEID_PTY_OFFICE:
                bodyDataToAcceptAd = extractJSONCP_PTY_Office(dataKeys, dataValues);
                break;
            case CATEID_PTY_ROOMFORRENT:
                bodyDataToAcceptAd = extractJSONCP_PTY_Room(dataKeys, dataValues);
                break;
        }
        Assert.assertFalse(bodyDataToAcceptAd.isEmpty(), "body of rejectAd is null with cate: " + subCateID);
        rejectNewAd(bodyDataToAcceptAd);
    }
}
