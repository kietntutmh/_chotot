package projects.functions.chotot.utils;

import java.util.ArrayList;
import java.util.List;

public class GetSubCateID {
    public static List<String> getAllCateID() {
        List<String> subCateList = new ArrayList<>();
        subCateList.add(getPTY_Apartment_CateId());
        subCateList.add(getPTY_House_CateId());
        subCateList.add(getPTY_Land_CateId());
        subCateList.add(getPTY_Office_CateId());
        subCateList.add(getPTY_Room_CateId());
        subCateList.add(getVEH_Car_CateId());
        subCateList.add(getVEH_Motorbike_CateId());
        subCateList.add(getVEH_Trucks_CateId());
        subCateList.add(getVEH_ElectricVeh_CateId());
        subCateList.add(getVEH_Bicycles_CateId());
        subCateList.add(getVEH_OtherVeh_CateId());
        subCateList.add(getVEH_Accessories_CateId());
        subCateList.add(getELT_Mobile_CateId());
        subCateList.add(getELT_Tablet_CateId());
        subCateList.add(getELT_Laptop_CateId());
        subCateList.add(getELT_PC_CateId());
        subCateList.add(getELT_Camera_CateId());
        subCateList.add(getELT_Sound_CateId());
        subCateList.add(getELT_Wearable_CateId());
        subCateList.add(getELT_Accessories_CateId());
        subCateList.add(getELT_PCComponent_CateId());
        subCateList.add(getJob_Recruitment_CateId());
        subCateList.add(getJob_FindJob_CateId());
        subCateList.add(getPets_Chicken_CateId());
        subCateList.add(getPets_Dog_CateId());
        subCateList.add(getPets_Bird_CateId());
        subCateList.add(getPets_Cat_CateId());
        subCateList.add(getPets_Others_CateId());
        subCateList.add(getPets_Accessories_CateId());
        subCateList.add(getFood_Food_CateId());
        subCateList.add(getRefrigeration_Fridge_CateId());
        subCateList.add(getRefrigeration_AirConditioner_CateId());
        subCateList.add(getRefrigeration_WashingMachine_CateId());
        subCateList.add(getFurniture_Table_CateId());
        subCateList.add(getFurniture_Cabinet_CateId());
        subCateList.add(getFurniture_Bed_CateId());
        subCateList.add(getFurniture_Stove_CateId());
        subCateList.add(getFurniture_KitchenTools_CateId());
        subCateList.add(getFurniture_Fan_CateId());
        subCateList.add(getFurniture_Light_CateId());
        subCateList.add(getFurniture_Tree_CateId());
        subCateList.add(getFurniture_Bathrooms_CateId());
        subCateList.add(getFurniture_Furniture_CateId());
        subCateList.add(getMomAndBaby_MomAndBaby_CateId());
        subCateList.add(getFashion_Clothes_CateId());
        subCateList.add(getFashion_Watch_CateId());
        subCateList.add(getFashion_Shoes_CateId());
        subCateList.add(getFashion_Handbag_CateId());
        subCateList.add(getFashion_Perfume_CateId());
        subCateList.add(getFashion_Accessories_CateId());
        subCateList.add(getEntertainment_Instrutment_CateId());
        subCateList.add(getEntertainment_Book_CateId());
        subCateList.add(getEntertainment_Sport_CateId());
        subCateList.add(getEntertainment_Collectibles_CateId());
        subCateList.add(getEntertainment_Gaming_CateId());
        subCateList.add(getEntertainment_Others_CateId());
        subCateList.add(getIndustries_Office_CateId());
        subCateList.add(getIndustries_Industry_CateId());
        subCateList.add(getServices_Service_CateId());
        subCateList.add(getServices_Travel_CateId());
        return subCateList;
    }

    public static List<String> getAllCateName() {
        List<String> subCateList = new ArrayList<>();
        subCateList.add("PTY - Apartment");
        subCateList.add("PTY - House");
        subCateList.add("PTY - Land");
        subCateList.add("PTY - Office");
        subCateList.add("PTY - Room");
        subCateList.add("VEH - Car");
        subCateList.add("VEH - Motorbike");
        subCateList.add("VEH - Trucks");
        subCateList.add("VEH - ElectricVeh");
        subCateList.add("VEH - Bicycles");
        subCateList.add("VEH - OtherVeh");
        subCateList.add("VEH - Accessories");
        subCateList.add("ELT - Mobile");
        subCateList.add("ELT - Tablet");
        subCateList.add("ELT - Laptop");
        subCateList.add("ELT - PC");
        subCateList.add("ELT - Camera");
        subCateList.add("ELT - Sound");
        subCateList.add("ELT - Wearable");
        subCateList.add("ELT - Accessories");
        subCateList.add("ELT - PCComponent");
        subCateList.add("Job - Recruitment");
        subCateList.add("Job - FindJob");
        subCateList.add("Pets - Chicken");
        subCateList.add("Pets - Dog");
        subCateList.add("Pets - Bird");
        subCateList.add("Pets - Cat");
        subCateList.add("Pets - Others");
        subCateList.add("Pets - Accessories");
        subCateList.add("Food - Food");
        subCateList.add("Refrigeration - Fridge");
        subCateList.add("Refrigeration - AirConditioner");
        subCateList.add("Refrigeration - WashingMachine");
        subCateList.add("Furniture - Table");
        subCateList.add("Furniture - Cabinet");
        subCateList.add("Furniture - Bed");
        subCateList.add("Furniture - Stove");
        subCateList.add("Furniture - KitchenTools");
        subCateList.add("Furniture - Fan");
        subCateList.add("Furniture - Light");
        subCateList.add("Furniture - Tree");
        subCateList.add("Furniture - Bathrooms");
        subCateList.add("Furniture - Furniture");
        subCateList.add("MomAndBaby - MomAndBaby");
        subCateList.add("Fashion - Clothes");
        subCateList.add("Fashion - Watch");
        subCateList.add("Fashion - Shoes");
        subCateList.add("Fashion - Handbag");
        subCateList.add("Fashion - Perfume");
        subCateList.add("Fashion - Accessories");
        subCateList.add("Entertainment - Instrutment");
        subCateList.add("Entertainment - Book");
        subCateList.add("Entertainment - Sport");
        subCateList.add("Entertainment - Collectibles");
        subCateList.add("Entertainment - Gaming");
        subCateList.add("Entertainment - Others");
        subCateList.add("Industries - Office");
        subCateList.add("Industries - Industry");
        subCateList.add("Services - Service");
        subCateList.add("Services - Travel");
        return subCateList;
    }

    public static String getPTY_Apartment_CateId() {
        return "1010";
    }

    public static String getPTY_House_CateId() {
        return "1020";
    }

    public static String getPTY_Land_CateId() {
        return "1040";
    }

    public static String getPTY_Office_CateId() {
        return "1030";
    }

    public static String getPTY_Room_CateId() {
        return "1050";
    }

    public static String getVEH_Car_CateId() {
        return "2010";
    }

    public static String getVEH_Motorbike_CateId() {
        return "2020";
    }

    public static String getVEH_Trucks_CateId() {
        return "2050";
    }

    public static String getVEH_ElectricVeh_CateId() {
        return "2090";
    }

    public static String getVEH_Bicycles_CateId() {
        return "2060";
    }

    public static String getVEH_OtherVeh_CateId() {
        return "2080";
    }

    public static String getVEH_Accessories_CateId() {
        return "2030";
    }

    public static String getELT_Mobile_CateId() {
        return "5010";
    }

    public static String getELT_Tablet_CateId() {
        return "5040";
    }

    public static String getELT_Laptop_CateId() {
        return "5030";
    }

    public static String getELT_PC_CateId() {
        return "5070";
    }

    public static String getELT_Camera_CateId() {
        return "5050";
    }

    public static String getELT_Sound_CateId() {
        return "5020";
    }

    public static String getELT_Wearable_CateId() {
        return "5090";
    }

    public static String getELT_Accessories_CateId() {
        return "5060";
    }

    public static String getELT_PCComponent_CateId() {
        return "5080";
    }

    public static String getJob_Recruitment_CateId() {
        return "13010";
    }

    public static String getJob_FindJob_CateId() {
        return "13020";
    }

    public static String getPets_Chicken_CateId() {
        return "12010";
    }

    public static String getPets_Dog_CateId() {
        return "12020";
    }

    public static String getPets_Bird_CateId() {
        return "12030";
    }

    public static String getPets_Cat_CateId() {
        return "12050";
    }

    public static String getPets_Others_CateId() {
        return "12060";
    }

    public static String getPets_Accessories_CateId() {
        return "12040";
    }

    public static String getFood_Food_CateId() {
        return "7010";
    }

    public static String getRefrigeration_Fridge_CateId() {
        return "9030";
    }

    public static String getRefrigeration_AirConditioner_CateId() {
        return "9060";
    }

    public static String getRefrigeration_WashingMachine_CateId() {
        return "9070";
    }

    public static String getFurniture_Table_CateId() {
        return "14070";
    }

    public static String getFurniture_Cabinet_CateId() {
        return "14080";
    }

    public static String getFurniture_Bed_CateId() {
        return "14030";
    }

    public static String getFurniture_Stove_CateId() {
        return "14010";
    }

    public static String getFurniture_KitchenTools_CateId() {
        return "14020";
    }

    public static String getFurniture_Fan_CateId() {
        return "14050";
    }

    public static String getFurniture_Light_CateId() {
        return "14060";
    }

    public static String getFurniture_Tree_CateId() {
        return "14090";
    }

    public static String getFurniture_Bathrooms_CateId() {
        return "14040";
    }

    public static String getFurniture_Furniture_CateId() {
        return "14110";
    }

    public static String getMomAndBaby_MomAndBaby_CateId() {
        return "11010";
    }

    public static String getFashion_Clothes_CateId() {
        return "3030";
    }

    public static String getFashion_Watch_CateId() {
        return "3050";
    }

    public static String getFashion_Shoes_CateId() {
        return "3060";
    }

    public static String getFashion_Handbag_CateId() {
        return "3070";
    }

    public static String getFashion_Perfume_CateId() {
        return "3080";
    }

    public static String getFashion_Accessories_CateId() {
        return "3090";
    }

    public static String getEntertainment_Instrutment_CateId() {
        return "4040";
    }

    public static String getEntertainment_Book_CateId() {
        return "4070";
    }

    public static String getEntertainment_Sport_CateId() {
        return "4020";
    }

    public static String getEntertainment_Collectibles_CateId() {
        return "4010";
    }

    public static String getEntertainment_Gaming_CateId() {
        return "4050";
    }

    public static String getEntertainment_Others_CateId() {
        return "4060";
    }

    public static String getIndustries_Office_CateId() {
        return "8010";
    }

    public static String getIndustries_Industry_CateId() {
        return "8030";
    }

    public static String getServices_Service_CateId() {
        return "6020";
    }

    public static String getServices_Travel_CateId() {
        return "6030";
    }

}
