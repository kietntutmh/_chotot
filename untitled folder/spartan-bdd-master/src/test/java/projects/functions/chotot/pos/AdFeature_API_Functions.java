package projects.functions.chotot.pos;

import api.configuration.GatewayConfig;
import com.vn.chotot.api.rest_assured.RestAssure;
import com.vn.chotot.logger.Log4jFactory;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import projects.functions.APISupportFunctions;

import java.time.Instant;

import static api.configuration.GatewayConfig.gatewayAdListing_GetListID;
import static api.configuration.GatewayConfig.gatewayPOS_AdFeature_getAds;
import static projects.configuaration.CategoryConfig.*;

public class AdFeature_API_Functions extends APISupportFunctions {
    static final Logger log = Log4jFactory.instance().createClassLogger(AdFeature_API_Functions.class);
    private static String ownerToken = "";
    private static String ownerPhone = "";
    private static String adID = "";
    private static final int retryCheckPOS = 5;
    private static final int retryCheckAdListingPOS = 3;
    private static final int retryCheckAdFeature = 7;

    public static String adFeatureID_ribbon = "";
    public static String adFeatureID_price = "";
    public static String adFeatureID_title = "";

    public static String getAdFeatureIDTitle() {
        return adFeatureID_title;
    }

    public static String getAdFeatureIDRibbon() {
        return adFeatureID_ribbon;
    }

    public static String getAdFeatureIDPrice() {
        return adFeatureID_price;
    }

    public static String getOwnerToken() {
        return ownerToken;
    }

    public static void setOwnerToken(String ownerToken) {
        AdFeature_API_Functions.ownerToken = ownerToken;
    }

    public static String getOwnerPhone() {
        return ownerPhone;
    }

    public static void setOwnerPhone(String ownerPhone) {
        AdFeature_API_Functions.ownerPhone = ownerPhone;
    }

    public static String getAdID() {
        return adID;
    }

    public static void setAdID(String adID) {
        AdFeature_API_Functions.adID = adID;
    }


    public static void verifyAdFeatureID_Title() {
        Assert.assertNotNull(adFeatureID_title, "CAN'T GET TITLE - AD FEATURE ID");
    }

    public static void verifyAdFeatureID_Price() {
        Assert.assertNotNull(adFeatureID_price, "CAN'T GET PRICE - AD FEATURE ID");
    }

    public static void verifyAdFeatureID_Price_NotDisplay() {
        Assert.assertNull(adFeatureID_price, "PRICE - AD FEATURE ID still displays");
    }

    public static void verifyAdFeatureID_Ribbon() {
        Assert.assertNotNull(adFeatureID_ribbon, "CAN'T GET RIBBON - AD FEATURE ID");
    }

    public static void verifyAdFeatureID_Ribbon_NotDisplay() {
        Assert.assertNull(adFeatureID_ribbon, "RIBBON - AD FEATURE ID still displays");
    }

    //get adFeatureId: ribbon, price, title of each subcate
    private static void getAdFeatureID(String subCate) {
        response = RestAssure.instance().get(String.format(GatewayConfig.gatewayGetAdFeatureID, subCate));
        verifyStatusCode200("CAN'T GET AD FEATURE ID of Sub Cate: " + subCate);
        debugResponse();

        // save data
        String query = "$.ribbon[0].id";
        adFeatureID_ribbon = getResponseData(response, query);
        query = "$.price[0].id";
        adFeatureID_price = getResponseData(response, query);
        query = "$.title[0].id";
        adFeatureID_title = getResponseData(response, query);
    }

    public static void getAdFeatureID_PTY_Apartment() {
        getAdFeatureID(CATEID_PTY_APARTMENT);
    }

    public static void getAdFeatureID_PTY_House() {
        getAdFeatureID(CATEID_PTY_HOUSE);
    }

    public static void getAdFeatureID_PTY_Land() {
        getAdFeatureID(CATEID_PTY_LAND);
    }

    public static void getAdFeatureID_PTY_Office() {
        getAdFeatureID(CATEID_PTY_OFFICE);
    }

    public static void getAdFeatureID_PTY_RoomForRent() {
        getAdFeatureID(CATEID_PTY_ROOMFORRENT);
    }

    public static void getAdFeatureID_VEH_MotorBike() {
        getAdFeatureID(CATEID_VEH_MOTORBIKE);
    }

    public static void getAdFeatureID_VEH_Truck() {
        getAdFeatureID(CATEID_VEH_TRUCK);
    }

    public static void getAdFeatureID_VEH_ElectricVehicles() {
        getAdFeatureID(CATEID_VEH_ELECTRIC_VEHICLE);
    }

    public static void getAdFeatureID_VEH_Bicycles() {
        getAdFeatureID(CATEID_VEH_BICYCLES);
    }

    public static void getAdFeatureID_VEH_OtherVehicles() {
        getAdFeatureID(CATEID_VEH_OTHER_VEHICLE);
    }

    public static void getAdFeatureID_VEH_VehicleParts() {
        getAdFeatureID(CATEID_VEH_VEHICLE_PARTS);
    }

    public static void getAdFeatureID_Job_Job() {
        getAdFeatureID(CATEID_JOB_JOB);
    }

    public static void getAdFeatureID_VEH_Car() {
        getAdFeatureID(CATEID_VEH_CAR);
    }

    public static void getAdFeatureID_ELT_Phone() {
        getAdFeatureID(CATEID_ELT_PHONE);
    }

    public static void getAdFeatureID_ELT_Tablet() {
        getAdFeatureID(CATEID_ELT_TABLET);
    }

    public static void getAdFeatureID_ELT_Laptop() {
        getAdFeatureID(CATEID_ELT_LAPTOP);
    }

    public static void getAdFeatureID_ELT_PC() {
        getAdFeatureID(CATEID_ELT_PC);
    }

    public static void getAdFeatureID_ELT_Camera() {
        getAdFeatureID(CATEID_ELT_CAMERA);
    }

    public static void getAdFeatureID_ELT_TiviSound() {
        getAdFeatureID(CATEID_ELT_TVSOUND);
    }

    public static void getAdFeatureID_ELT_Wearable() {
        getAdFeatureID(CATEID_ELT_WEARABLE);
    }

    public static void getAdFeatureID_ELT_Accessories() {
        getAdFeatureID(CATEID_ELT_ACCESSORIES);
    }

    public static void getAdFeatureID_ELT_PCComponent() {
        getAdFeatureID(CATEID_ELT_PCCOMPONENT);
    }

    public static void getAdFeatureID_JOB_LookingJob() {
        getAdFeatureID(CATEID_JOB_LOOKINGJOB);
    }

    public static void getAdFeatureID_PET_Chicken() {
        getAdFeatureID(CATEID_PET_CHICKEN);
    }

    public static void getAdFeatureID_PET_Dog() {
        getAdFeatureID(CATEID_PET_DOG);
    }

    public static void getAdFeatureID_PET_Bird() {
        getAdFeatureID(CATEID_PET_BIRD);
    }

    public static void getAdFeatureID_PET_Cat() {
        getAdFeatureID(CATEID_PET_CAT);
    }

    public static void getAdFeatureID_PET_Others() {
        getAdFeatureID(CATEID_PET_OTHERS);
    }

    public static void getAdFeatureID_PET_Accessories() {
        getAdFeatureID(CATEID_PET_ACCESSORIES);
    }

    public static void getAdFeatureID_Other_Food() {
        getAdFeatureID(CATEID_OTHER_FOOD);
    }

    public static void getAdFeatureID_FUN_Refrigerator() {
        getAdFeatureID(CATEID_FUN_REFRIGERATOR);
    }

    public static void getAdFeatureID_FUN_Cooler() {
        getAdFeatureID(FUN_COOLER);
    }

    public static void getAdFeatureID_FUN_WashingMachine() {
        getAdFeatureID(FUN_WASHINGMACHINE);
    }

    public static void getAdFeatureID_HouseHold_TableChair() {
        getAdFeatureID(CATEID_HOUSEHOLD_TABLECHAIR);
    }

    public static void getAdFeatureID_HouseHold_DrawerShelf() {
        getAdFeatureID(CATEID_HOUSEHOLD_DRAWERSHELF);
    }

    public static void getAdFeatureID_HouseHold_Bed() {
        getAdFeatureID(CATEID_HOUSEHOLD_BED);
    }

    public static void getAdFeatureID_HouseHold_KitchenAppliance() {
        getAdFeatureID(CATEID_HOUSEHOLD_KITCHENAPPLIANCE);
    }

    public static void getAdFeatureID_HouseHold_KitchenDinnerware() {
        getAdFeatureID(CATEID_HOUSEHOLD_KITCHENUTENSILDINNERWARE);
    }

    public static void getAdFeatureID_HouseHold_Fan() {
        getAdFeatureID(CATEID_HOUSEHOLD_FAN);
    }

    public static void getAdFeatureID_HouseHold_Lighting() {
        getAdFeatureID(CATEID_HOUSEHOLD_LIGHTING);
    }

    public static void getAdFeatureID_HouseHold_PlantDecoration() {
        getAdFeatureID(CATEID_HOUSEHOLD_PLANTDECORATION);
    }

    public static void getAdFeatureID_HouseHold_Bathware() {
        getAdFeatureID(CATEID_HOUSEHOLD_BATHWARE);
    }

    public static void getAdFeatureID_HouseHold_OtherItems() {
        getAdFeatureID(CATEID_HOUSEHOLD_OTHERITEMS);
    }

    public static void getAdFeatureID_MonAndBaby() {
        getAdFeatureID(CATEID_MOMANDBABY);
    }

    public static void getAdFeatureID_Fashion_Clothes() {
        getAdFeatureID(CATEID_FASHION_CLOTHES);
    }

    public static void getAdFeatureID_Fashion_Watch() {
        getAdFeatureID(CATEID_FASHION_WATCH);
    }

    public static void getAdFeatureID_Fashion_Shoes() {
        getAdFeatureID(CATEID_FASHION_SHOES);
    }

    public static void getAdFeatureID_Fashion_Handbag() {
        getAdFeatureID(CATEID_FASHION_HANDBAG);
    }

    public static void getAdFeatureID_Fashion_Perfume() {
        getAdFeatureID(CATEID_FASHION_PERFUME);
    }

    public static void getAdFeatureID_Fashion_Accessories() {
        getAdFeatureID(CATEID_FASHION_ACCESSORIES);
    }

    public static void getAdFeatureID_ENT_Instrument() {
        getAdFeatureID(ENT_INSTRUMENT);
    }

    public static void getAdFeatureID_ENT_Book() {
        getAdFeatureID(ENT_BOOK);
    }

    public static void getAdFeatureID_ENT_Sport() {
        getAdFeatureID(ENT_SPORT);
    }

    public static void getAdFeatureID_ENT_Gaming() {
        getAdFeatureID(ENT_GAMING);
    }

    public static void getAdFeatureID_ENT_Hobby() {
        getAdFeatureID(ENT_HOBBY);
    }

    public static void getAdFeatureID_Requisite_OfficeEquipment() {
        getAdFeatureID(CATEID_REQUISITE_OFFICEEQUIPMENT);
    }

    public static void getAdFeatureID_Requisite_SpecializedItem() {
        getAdFeatureID(CATEID_REQUISITE_SPECIALIZEDITEM);
    }

    public static void getAdFeatureID_Service_Service() {
        getAdFeatureID(CATEID_SERVICE_SERVICE);
    }

    public static void getAdFeatureID_Service_Travel() {
        getAdFeatureID(CATEID_SERVICE_TRAVEL);
    }

    public static void getAdFeatureID_ENT_Collectibles() {
        getAdFeatureID(ENT_COLLECTIBLES);
    }

    private static String getEpochCurrentSec(){
        return String.valueOf(Instant.now().getEpochSecond());
    }

    public static void verifyAdFeature_GoodPriceLabel(String adID) {
        response = RestAssure.instance().get(gatewayPOS_AdFeature_getAds + getEpochCurrentSec());
        verifyStatusCode200("API Get Ads of Ad Feature is dead");

        query = "$[*][?(@.ad_id == '" + adID + "')].ad_features[?(@.name == 'price')].name";
        Assert.assertEquals(getResponseData(response, query), "price", "Ad Feature GOOD PRICE isn't enabled");

        boolean isPassed = true;
        String failedMsg = "";
        try {
            query = "$[*][?(@.ad_id == '" + adID + "')].ad_features[?(@.name == 'ribbon')].name";
            Assert.assertNull(getResponseData(response, query));
            isPassed = false;
            failedMsg += "\nAd Feature HOT LABEL is enabled";
        } catch (AssertionError ase) {
        }

        try{
            query = "$[*][?(@.ad_id == '" + adID + "')].ad_features[?(@.name == 'title')].name";
            Assert.assertNull(getResponseData(response, query));
            isPassed = false;
            failedMsg += "\nAd Feature BOLD TITLE is enabled";
        }catch (AssertionError a){
        }

        Assert.assertTrue(isPassed, failedMsg);
    }

    public static void verifyAdFeature_HotLabel(String adID) {
        response = RestAssure.instance().get(gatewayPOS_AdFeature_getAds + getEpochCurrentSec());
        verifyStatusCode200("API Get Ads of Ad Feature is dead");

        query = "$[*][?(@.ad_id == '" + adID + "')].ad_features[?(@.name == 'ribbon')].name";
        Assert.assertEquals(getResponseData(response, query), "ribbon", "Ad Feature HOT LABEL isn't enabled");

        boolean isPassed = true;
        String failedMsg = "";
        try {
            query = "$[*][?(@.ad_id == '" + adID + "')].ad_features[?(@.name == 'price')].name";
            Assert.assertNull(getResponseData(response, query));
            isPassed = false;
            failedMsg += "\nAd Feature GOOD PRICE is enabled";
        } catch (AssertionError ase) {
        }

        try{
            query = "$[*][?(@.ad_id == '" + adID + "')].ad_features[?(@.name == 'title')].name";
            Assert.assertNull(getResponseData(response, query));
            isPassed = false;
            failedMsg += "\nAd Feature BOLD TITLE is enabled";
        }catch (AssertionError a){
        }

        Assert.assertTrue(isPassed, failedMsg);
    }

    public static void verifyAdFeature_BoldTitle(String adID) {
        boolean isPassed = true;
        int i = 0;
        while (i < retryCheckAdFeature){
            try{
                response = RestAssure.instance().get(gatewayPOS_AdFeature_getAds + getEpochCurrentSec());
                verifyStatusCode200("API Get Ads of Ad Feature is dead");
                debugResponse();
                query = "$[*][?(@.ad_id == '" + adID + "')].ad_features[?(@.name == 'title')].name";
                Assert.assertEquals(getResponseData(response, query), "title", "Ad Feature BOLD TITLE isn't enabled");
                isPassed = true;
                break;
            }catch (AssertionError es){
                isPassed = false;
                i++;
            }
        }

        String failedMsg = "";
        try {
            query = "$[?(@.ad_id == '" + adID + "')].ad_features[?(@.name == 'price')].name";
            Assert.assertNull(getResponseData(response, query));
            failedMsg += "\nAd Feature GOOD PRICE is enabled";
        } catch (AssertionError ase) {
            isPassed = false;
        }

        try{
            query = "$[?(@.ad_id == '" + adID + "')].ad_features[?(@.name == 'ribbon')].name";
            Assert.assertNull(getResponseData(response, query));
            failedMsg += "\nAd Feature HOT LABEL is enabled";
        }catch (AssertionError a){
            isPassed = false;
        }

        Assert.assertTrue(isPassed, failedMsg);
    }

    @Deprecated
    public static void verifyAdFeature_GoodPriceLabel_OnListing(String adID) {
        String adFeatureName = "";
        query = "$.ad.ad_features[?(@.name == 'price')].name";
        int i = 0;
        while (i < retryCheckAdListingPOS) {
            response = RestAssure.instance().get(String.format(gatewayAdListing_GetListID, adID));
            verifyStatusCode200("API FAILED: CAN'T GET LIST ID OF AD_ID [" + adID + "]");
            adFeatureName = getResponseData(response, query);
            try {
                Assert.assertEquals(adFeatureName, "price", "Ad Feature GIÁ TỐT isn't enabled");
                break;
            } catch (AssertionError ex) {
                waitConstant(3);
                i++;
            }
        }
        Assert.assertEquals(adFeatureName, "price", "Ad Feature GIÁ TỐT isn't enabled");

        //Check ribbon, title are null
        query = "$.ad.ad_features[?(@.name == 'ribbon')].name";
        Assert.assertNull(getResponseData(response, query), "Ad Feature HOT is added. It shouldn't be added");
        query = "$.ad.ad_features[?(@.name == 'title')].name";
        Assert.assertNull(getResponseData(response, query), "Ad Feature BOLD TITLE is added. It shouldn't be added");
    }

    @Deprecated
    public static void verifyAdFeature_HotLabel_OnListing(String adID) {
        String adFeatureName = "";
        query = "$.ad.ad_features[?(@.name == 'ribbon')].name";
        int i = 0;
        while (i < retryCheckAdListingPOS) {
            response = RestAssure.instance().get(String.format(gatewayAdListing_GetListID, adID));
            verifyStatusCode200("API FAILED: CAN'T GET LIST ID OF AD_ID [" + adID + "]");
            adFeatureName = getResponseData(response, query);
            try {
                Assert.assertEquals(adFeatureName, "ribbon", "Ad Feature NHÃN HOT isn't enabled");
                break;
            } catch (AssertionError ex) {
                waitConstant(3);
                i++;
            }
        }
        Assert.assertEquals(adFeatureName, "ribbon", "Ad Feature NHÃN HOT isn't enabled");

        //Check price, title are null
        query = "$.ad.ad_features[?(@.name == 'price')].name";
        Assert.assertNull(getResponseData(response, query), "Ad Feature GIÁ TỐT is added. It shouldn't be added");
        query = "$.ad.ad_features[?(@.name == 'title')].name";
        Assert.assertNull(getResponseData(response, query), "Ad Feature BOLD TITLE is added. It shouldn't be added");
    }

    @Deprecated
    public static void verifyAdFeature_BoldTitle_OnListing(String adID) {
        String adFeatureName = "";
        query = "$.ad.ad_features[?(@.name == 'title')].name";
        int i = 0;
        while (i < retryCheckAdListingPOS) {
            response = RestAssure.instance().get(String.format(gatewayAdListing_GetListID, adID));
            verifyStatusCode200("API FAILED: CAN'T GET LIST ID OF AD_ID [" + adID + "]");
            adFeatureName = getResponseData(response, query);
            try {
                Assert.assertEquals(adFeatureName, "title", "Ad Feature TITLE IN ĐẬM isn't enabled");
                break;
            } catch (AssertionError ex) {
                waitConstant(3);
                i++;
            }
        }
        debugResponse();
        Assert.assertEquals(adFeatureName, "title", "Ad Feature TITLE IN ĐẬM isn't enabled");

        //Check price, title are null
        query = "$.ad.ad_features[?(@.name == 'price')].name";
        Assert.assertNull(getResponseData(response, query), "Ad Feature GIÁ TỐT is added. It shouldn't be added");
        query = "$.ad.ad_features[?(@.name == 'ribbon')].name";
        Assert.assertNull(getResponseData(response, query), "Ad Feature HOT Label is added. It shouldn't be added");
    }

}
