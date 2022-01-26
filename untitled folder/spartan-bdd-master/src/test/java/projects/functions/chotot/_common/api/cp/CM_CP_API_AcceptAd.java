package projects.functions.chotot._common.api.cp;

import org.testng.Assert;

import java.util.List;

import static api.feature.cp.AcceptCP.acceptNewAd;
import static api.utils.GetJSONString.*;
import static projects.configuaration.CategoryConfig.*;

public class CM_CP_API_AcceptAd {
    String bodyDataToAcceptAd = "";

    // PTY
    public void acceptAd_PTY_House(List<String> listKeys, List<String> listValues) {
        bodyDataToAcceptAd = extractJSONCP_PTY_House(listKeys, listValues);
        acceptNewAd(bodyDataToAcceptAd);
    }

    public void acceptAd_PTY_Apartment(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_PTY_Apartment(listKeys, listValues);
        acceptNewAd(data);
    }

    public void acceptAd_PTY_Land(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_PTY_Land(listKeys, listValues);
        acceptNewAd(data);
    }

    public void acceptAd_PTY_Office(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_PTY_Office(listKeys, listValues);
        acceptNewAd(data);
    }

    public void acceptAd_PTY_Room(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_PTY_Room(listKeys, listValues);
        acceptNewAd(data);
    }

    // ELT
    public void acceptAd_ELT_Laptop(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_ELT_Laptop(listKeys, listValues);
        acceptNewAd(data);
    }

    public void acceptAd_ELT_Phone(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_ELT_Phone(listKeys, listValues);
        acceptNewAd(data);
    }

    public void acceptAd_ELT_Tablet(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_ELT_Tablet(listKeys, listValues);
        acceptNewAd(data);
    }

    public void acceptAd_ELT_PC(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_ELT_PC(listKeys, listValues);
        acceptNewAd(data);
    }

    public void acceptAd_ELT_Camera(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_ELT_Camera(listKeys, listValues);
        acceptNewAd(data);
    }

    public void acceptAd_ELT_Sound(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_ELT_Sound(listKeys, listValues);
        acceptNewAd(data);
    }

    public void acceptAd_ELT_Wearable(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_ELT_Wearable(listKeys, listValues);
        acceptNewAd(data);
    }

    public void acceptAd_ELT_Accessories(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_ELT_Accessories(listKeys, listValues);
        acceptNewAd(data);
    }

    public void acceptAd_ELT_PC_Component(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_ELT_PC_Component(listKeys, listValues);
        acceptNewAd(data);
    }

    // VEH
    public void acceptAd_VEH_Car(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_VEH_Car(listKeys, listValues);
        acceptNewAd(data);
    }

    public void acceptAd_VEH_Motorbike(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_VEH_Motorbike(listKeys, listValues);
        acceptNewAd(data);
    }

    public void acceptAd_VEH_Truck(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_VEH_Truck(listKeys, listValues);
        acceptNewAd(data);
    }

    public void acceptAd_VEH_Electric(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_VEH_Electric(listKeys, listValues);
        acceptNewAd(data);
    }

    public void acceptAd_VEH_Bicycles(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_VEH_Bicycles(listKeys, listValues);
        acceptNewAd(data);
    }

    public void acceptAd_VEH_Vehicles_Parts(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_VEH_Vehicles_Parts(listKeys, listValues);
        acceptNewAd(data);
    }

    public void acceptAd_VEH_Other_Vehicles(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_VEH_Other_Vehicles(listKeys, listValues);
        acceptNewAd(data);
    }

    // Entertainment
    public void acceptAd_Entertainment_Instrument(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_Entertainment_Instrument(listKeys, listValues);
        acceptNewAd(data);
    }

    public void acceptAd_Entertainment_Sport(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_Entertainment_Sport(listKeys, listValues);
        acceptNewAd(data);
    }

    public void acceptAd_Entertainment_Book(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_Entertainment_Book(listKeys, listValues);
        acceptNewAd(data);
    }

    public void acceptAd_Entertainment_Collectibles(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_Entertainment_Collectibles(listKeys, listValues);
        acceptNewAd(data);
    }

    public void acceptAd_Entertainment_Gaming(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_Entertainment_Gaming(listKeys, listValues);
        acceptNewAd(data);
    }

    public void acceptAd_Entertainment_Hobby(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_Entertainment_Hobby(listKeys, listValues);
        acceptNewAd(data);
    }

    // Fashion
    public void acceptAd_Fashion_Clothes(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_Fashion_Clothes(listKeys, listValues);
        acceptNewAd(data);
    }

    public void acceptAd_Fashion_Watch(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_Fashion_Watch(listKeys, listValues);
        acceptNewAd(data);
    }

    public void acceptAd_Fashion_Shoes(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_Fashion_Shoes(listKeys, listValues);
        acceptNewAd(data);
    }

    public void acceptAd_Fashion_Handbag(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_Fashion_Handbag(listKeys, listValues);
        acceptNewAd(data);
    }

    public void acceptAd_Fashion_Perfume(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_Fashion_Perfume(listKeys, listValues);
        acceptNewAd(data);
    }

    public void acceptAd_Fashion_Accessories(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_Fashion_Accessories(listKeys, listValues);
        acceptNewAd(data);
    }

    // Service
    public void acceptAd_Services_Service(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_Services_Service(listKeys, listValues);
        acceptNewAd(data);
    }

    public void acceptAd_Services_Travel(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_Services_Travel(listKeys, listValues);
        acceptNewAd(data);
    }

    // Jobs
    public void acceptAd_Job(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_Job(listKeys, listValues);
        acceptNewAd(data);
    }

    // Jobs
    public void acceptAd_MomAndBaby(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_MomAndBaby(listKeys, listValues);
        acceptNewAd(data);
    }

    public void acceptAd_Food(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_Food(listKeys, listValues);
        acceptNewAd(data);
    }

    public void acceptAd_Pet_Chicken(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_Pet_Chicken(listKeys, listValues);
        acceptNewAd(data);
    }

    public void acceptAd_Pet_Dog(List<String> listKeys, List<String> listValues) {
        String data = extractJSONCP_Pet_Dog(listKeys, listValues);
        acceptNewAd(data);
    }

    public void acceptAd_Pet_Bird(List<String> dataKeys, List<String> dataValues) {
        String data = extractJSONCP_Pet_Dog(dataKeys, dataValues);
        acceptNewAd(data);
    }

    public void acceptAd_Pet_Cat(List<String> dataKeys, List<String> dataValues) {
        String data = extractJSONCP_Pet_Cat(dataKeys, dataValues);
        acceptNewAd(data);
    }

    public void acceptAd_Pet_OtherPets(List<String> dataKeys, List<String> dataValues) {
        String data = extractJSONCP_Pet_Others(dataKeys, dataValues);
        acceptNewAd(data);
    }

    public void acceptAd_Pet_Accessories(List<String> dataKeys, List<String> dataValues) {
        String data = extractJSONCP_Pet_Accessories(dataKeys, dataValues);
        acceptNewAd(data);
    }

    public void acceptAd_Refrigerator(List<String> dataKeys, List<String> dataValues) {
        String data = extractJSONCP_ElectricAppliances_Refrigerator(dataKeys, dataValues);
        acceptNewAd(data);
    }

    public void acceptAd_Cooler(List<String> dataKeys, List<String> dataValues) {
        String data = extractJSONCP_ElectricAppliances_Cooler(dataKeys, dataValues);
        acceptNewAd(data);
    }

    public void acceptAd_Washing_Machine(List<String> dataKeys, List<String> dataValues) {
        String data = extractJSONCP_ElectricAppliances_WashingMachine(dataKeys, dataValues);
        acceptNewAd(data);
    }

    public void acceptAd_Household_Table_Chair(List<String> dataKeys, List<String> dataValues) {
        String data = extractJSONCP_Household_Table_Chair(dataKeys, dataValues);
        acceptNewAd(data);
    }

    public void acceptAd_Household_Drawer_Shelf(List<String> dataKeys, List<String> dataValues) {
        String data = extractJSONCP_Household_Drawer_Shelf(dataKeys, dataValues);
        acceptNewAd(data);
    }

    public void acceptAd_Bed_Bedding(List<String> dataKeys, List<String> dataValues) {
        String data = extractJSONCP_Household_Bed_Bedding(dataKeys, dataValues);
        acceptNewAd(data);
    }

    public void acceptAd_Kitchen_Utensil_Dinnerware(List<String> dataKeys, List<String> dataValues) {
        String data = extractJSONCP_Kitchen_Utensil_Dinnerware(dataKeys, dataValues);
        acceptNewAd(data);
    }

    public void acceptAd_Kitchen_Appliance(List<String> dataKeys, List<String> dataValues) {
        String data = extractJSONCP_Household_KitchenAppliance(dataKeys, dataValues);
        acceptNewAd(data);
    }

    public void acceptAd_Fan(List<String> dataKeys, List<String> dataValues) {
        String data = extractJSONCP_Household_Fan(dataKeys, dataValues);
        acceptNewAd(data);
    }

    public void acceptAd_Lighting(List<String> dataKeys, List<String> dataValues) {
        String data = extractJSONCP_Household_Lighting(dataKeys, dataValues);
        acceptNewAd(data);
    }

    public void acceptAd_Ornamental_Plant_Decoration(List<String> dataKeys, List<String> dataValues) {
        String data = extractJSONCP_Household_Decoration(dataKeys, dataValues);
        acceptNewAd(data);
    }

    public void acceptAd_Bathware(List<String> dataKeys, List<String> dataValues) {
        String data = extractJSONCP_Household_Bathware(dataKeys, dataValues);
        acceptNewAd(data);
    }

    public void acceptAdOther_Household_Items(List<String> dataKeys, List<String> dataValues) {
        String data = extractJSONCP_Household_Others(dataKeys, dataValues);
        acceptNewAd(data);
    }

    public void acceptAd_OfficeEquipment(List<String> dataKeys, List<String> dataValues) {
        String data = extractJSONCP_Requisite_OfficeEquipment(dataKeys, dataValues);
        acceptNewAd(data);
    }

    public void acceptAd_SpecializedItem(List<String> dataKeys, List<String> dataValues) {
        String data = extractJSONCP_Requisite_SpecializedItem(dataKeys, dataValues);
        acceptNewAd(data);
    }

    //VUHOANG NEW GEN
    public void acceptAd_PTY(List<String> dataKeys, List<String> dataValues, String subCateID) {
        Assert.assertTrue(dataKeys.size() > 0, "dataKeys is null");
        Assert.assertTrue(dataValues.size() > 0, "dataValues is null");
        switch (subCateID){
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
        Assert.assertFalse(bodyDataToAcceptAd.isEmpty(), "body of acceptAd is null with cate: " + subCateID);
        acceptNewAd(bodyDataToAcceptAd);
    }
}
