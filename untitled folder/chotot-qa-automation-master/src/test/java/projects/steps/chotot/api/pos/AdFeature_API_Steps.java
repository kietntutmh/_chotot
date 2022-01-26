package projects.steps.chotot.api.pos;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import projects.functions.chotot._common.api.ads.insert.*;
import projects.functions.chotot.pos.AdFeature_API_Functions;

import static api.configuration.DataConfig.newUserPhone;
import static api.configuration.DataConfig.tempAdID;
import static api.configuration.GatewayConfig.global_accessToken;
import static api.utils.GetAccessToken.getAccessTokenOfNewUser;
import static projects.functions.chotot.payment.PayOrder_API_Functions.paymentOrder;
import static projects.functions.chotot.payment.TopupDongTot_API_Functions.topupDongTotWithMomo_500k;
import static projects.functions.chotot.pos.POS_Functions.*;

@Deprecated
public class AdFeature_API_Steps extends AdFeature_API_Functions {

    @Given("I register a new Chotot User and deposit {string} Dong Tot via Momo")
    public void iRegisterANewChototUserAndDepositDongToViaMomo(String amount) {
        setOwnerToken(getAccessTokenOfNewUser());
        setOwnerPhone(newUserPhone);
        topupDongTotWithMomo_500k(getOwnerPhone());
    }

    @And("I add and pay for GIÁ TỐT label to my ad")
    public void iAddGoodPriceLabelServicesForMyAdAndPay() {
        setAdID(tempAdID);
        posAdFeaturePrice(getOwnerToken(), getAdID(), getAdFeatureIDPrice());
        paymentOrder();
    }

    @And("I add and pay for HOT label to my ad")
    public void iAddedAndPaidHOTLabelServicesForMyAd() {
        setAdID(tempAdID);
        posAdFeatureRibbon(getOwnerToken(), getAdID(), getAdFeatureIDRibbon());
        paymentOrder();
    }

    @And("I add and pay for TITLE label to my ad")
    public void iAddedAndPaidTITLELabelServisscesForMyAd() {
        setAdID(tempAdID);
        posAdFeatureTitle(getOwnerToken(), getAdID(), getAdFeatureIDTitle());
        paymentOrder();
    }

    @And("I add and pay for TITLE label to my PTY Shop {string} ad")
    public void iAddedAndPaidTITLELabelServicesForMyAd(String ad) {
        switch (ad.trim().toLowerCase()) {
            case "house":
                getAdFeatureID_PTY_House();
                break;
            case "apartment":
                getAdFeatureID_PTY_Apartment();
                break;
            case "office":
                getAdFeatureID_PTY_Office();
                break;
            case "land":
                getAdFeatureID_PTY_Land();
                break;
            case "room":
                getAdFeatureID_PTY_RoomForRent();
                break;
        }

        setAdID(tempAdID);
        posAdFeatureTitle(global_accessToken, getAdID(), getAdFeatureIDTitle());
        paymentOrder();
    }

    @And("I add and pay for TITLE label to my Shop ad")
    public void iAddedAndPaidTITLELabelSservicesForMyAd() {
        setAdID(tempAdID);
        setOwnerToken(global_accessToken);
        posAdFeatureTitle(getOwnerToken(), getAdID(), getAdFeatureIDTitle());
        paymentOrder();
    }

    //Insert Ad
    @When("I post a new Apartment Ad and Cho Tot accept my Ad for ad feature")
    public void iPostANewApartmentAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertPTY.instance().insertNewAdApartment_NoUploadNewImage("accept");
        getAdFeatureID_PTY_Apartment();
        setAdID(tempAdID);
    }

    @When("I post a new House Ad and Cho Tot accept my Ad for ad feature")
    public void iPostANewHouseAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertPTY.instance().insertNewAdHouse_NoUploadNewImage("accept");
        getAdFeatureID_PTY_House();
        setAdID(tempAdID);
    }

    @When("I post a new Land Ad and Cho Tot accept my Ad for ad feature")
    public void iInsertANewLandAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertPTY.instance().insertNewAdLand_NoUploadNewImage("accept");
        getAdFeatureID_PTY_Land();
        setAdID(tempAdID);
    }

    @When("I post a new Office Ad and Cho Tot accept my Ad for ad feature")
    public void iPostANewOfficeEquipmentAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertPTY.instance().insertNewAdOffice_NoUploadNewImage("accept");
        getAdFeatureID_PTY_Office();
        setAdID(tempAdID);
    }

    @When("I post a new Room For Lease Ad and Cho Tot accept my Ad for ad feature")
    public void iPostANewRoomForLeaseAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertPTY.instance().insertNewAdRoomForLease_NoUploadNewImage("accept");
        getAdFeatureID_PTY_RoomForRent();
        setAdID(tempAdID);
    }

    @When("I post a new Room For Hire Ad and Cho Tot accept my Ad for ad feature")
    public void iPostANewRoomForLeaseAdAndChoTotAcceptMyAsd() {
        CM_API_Ads_InsertPTY.instance().insertNewAdRoomForLease_NoUploadNewImage("accept");
        getAdFeatureID_PTY_RoomForRent();
        setAdID(tempAdID);
    }

    @When("I post a new Motorbike Ad and Cho Tot accept my Ad for ad feature")
    public void iPostANewMotorbikeAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertVehicle.instance().insertNewAdMotorbike_NoUploadNewImage("accept");
        getAdFeatureID_VEH_MotorBike();
        setAdID(tempAdID);
    }

    @When("I post a new Truck Ad and Cho Tot accept my Ad for ad feature")
    public void iPostANewTruckAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertVehicle.instance().insertNewAdTrucks_NoUploadNewImage("accept");
        getAdFeatureID_VEH_Truck();
        setAdID(tempAdID);
    }

    @When("I post a new Electric Vehicles Ad and Cho Tot accept my Ad for ad feature")
    public void iPostANewElectricVehiclesAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertVehicle.instance().insertNewAdElectric_Vehicle_NoUploadNewImage("accept");
        getAdFeatureID_VEH_ElectricVehicles();
        setAdID(tempAdID);
    }

    @When("I post a new Bicycles Ad and Cho Tot accept my Ad for ad feature")
    public void iPostANewBicyclesAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertVehicle.instance().insertNewAdBicycles_NoUploadNewImage("accept");
        getAdFeatureID_VEH_Bicycles();
        setAdID(tempAdID);
    }

    @When("I post a new Other Vehicles Ad and Cho Tot accept my Ad for ad feature")
    public void iPostANewOtherVehiclesAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertVehicle.instance().insertNewAdOther_Vehicles_NoUploadNewImage("accept");
        getAdFeatureID_VEH_OtherVehicles();
        setAdID(tempAdID);
    }

    @When("I post a new Vehicles Parts Ad and Cho Tot accept my Ad for ad feature")
    public void iPostANewVehiclesPartsAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertVehicle.instance().insertNewAdVehicles_Parts_NoUploadNewImage("accept");
        getAdFeatureID_VEH_VehicleParts();
        setAdID(tempAdID);
    }

    @When("I post a new Job Ad and Cho Tot accept my Ad for ad feature")
    public void iPostANewJobAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertJob.instance().instance().insertNewAdJob_NoUploadNewImage("accept");
        getAdFeatureID_Job_Job();
        setAdID(tempAdID);
    }

    @When("I post a new Car Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewCarAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertVehicle.instance().insertNewAdCar_NoUploadNewImage("accept");
        getAdFeatureID_VEH_Car();
        setAdID(tempAdID);
    }

    @When("I post a new Phone Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewPhoneAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertELT.instance().insertNewAdPhoneAd_NoUploadNewImage("accept");
        getAdFeatureID_ELT_Phone();
        setAdID(tempAdID);
    }

    @When("I post a new Tablet Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewTabletAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertELT.instance().insertNewAdTabletAd_NoUploadNewImage("accept");
        getAdFeatureID_ELT_Tablet();
        setAdID(tempAdID);
    }

    @When("I post a new Laptop Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewLaptopAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertELT.instance().insertNewAdLaptop_NoUploadNewImage("accept");
        getAdFeatureID_ELT_Laptop();
        setAdID(tempAdID);
    }

    @When("I post a new Desktop Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewDesktopAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertELT.instance().insertNewAdPC_NoUploadNewImage("accept");
        getAdFeatureID_ELT_PC();
        setAdID(tempAdID);
    }

    @When("I post a new Camera Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewCameraAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertELT.instance().insertNewAdCamera_NoUploadNewImage("accept");
        getAdFeatureID_ELT_Camera();
        setAdID(tempAdID);
    }

    @When("I post a new TV Sound Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewTVSoundAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertELT.instance().insertNewAdTVSound_NoUploadNewImage("accept");
        getAdFeatureID_ELT_TiviSound();
        setAdID(tempAdID);
    }

    @When("I post a new PC Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewPCAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertELT.instance().insertNewAdPC_NoUploadNewImage("accept");
        getAdFeatureID_ELT_PC();
        setAdID(tempAdID);
    }

    @When("I post a new Wearable Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewWearableAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertELT.instance().insertNewAdWearable_NoUploadNewImage("accept");
        getAdFeatureID_ELT_Wearable();
        setAdID(tempAdID);
    }

    @When("I post a new ELT Accessories Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewAccessoriesAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertELT.instance().insertNewAdAccessories_NoUploadNewImage("accept");
        getAdFeatureID_ELT_Accessories();
        setAdID(tempAdID);
    }

    @When("I post a new PC Component Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewPCComponentAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertELT.instance().insertNewAdPCComponent_NoUploadNewImage("accept");
        getAdFeatureID_ELT_PCComponent();
        setAdID(tempAdID);
    }

    @When("I post a new Looking Job Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewLookingJobAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertJob.instance().instance().insertNewAdLookingJob_NoUploadNewImage("accept");
        getAdFeatureID_JOB_LookingJob();
        setAdID(tempAdID);
    }

    @When("I post a new Chicken Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewChickenAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertPets.instance().insertNewAd_Chicken_NoUploadNewImage("accept");
        getAdFeatureID_PET_Chicken();
        setAdID(tempAdID);
    }

    @When("I post a new Dog Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewDogAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertPets.instance().insertNewAd_Dog_NoUploadNewImage("accept");
        getAdFeatureID_PET_Dog();
        setAdID(tempAdID);
    }

    @When("I post a new Bird Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewBirdAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertPets.instance().insertNewAd_Bird_NoUploadNewImage("accept");
        getAdFeatureID_PET_Bird();
        setAdID(tempAdID);
    }

    @When("I post a new Cat Ad and Cho Tot accept my Ad for ad feature")
    public void iPostANewCatAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertPets.instance().insertNewAd_Cat_NoUploadNewImage("accept");
        getAdFeatureID_PET_Cat();
        setAdID(tempAdID);
    }

    @When("I post a new Other Pets Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewOtherPetsAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertPets.instance().insertNewAd_OtherPet_NoUploadNewImage("accept");
        getAdFeatureID_PET_Others();
        setAdID(tempAdID);
    }

    @When("I post a new Pet Accessories Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewPetAccessoriesAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertPets.instance().insertNewAdAccessories_NoUploadNewImage("accept");
        getAdFeatureID_PET_Accessories();
        setAdID(tempAdID);
    }

    @When("I post a new Food Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewFoodAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertOther.instance().insertNewAdFood_NoUploadNewImage("accept");
        getAdFeatureID_Other_Food();
        setAdID(tempAdID);
    }

    @When("I post a new Refrigerator Fridge Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewRefrigeratorAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertElectricAppliances.instance().insertNewAdRefrigerator_NoUploadNewImage("accept");
        getAdFeatureID_FUN_Refrigerator();
        setAdID(tempAdID);
    }

    @When("I post a new Air Conditioner Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewCoolerAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertElectricAppliances.instance().insertNewAdCooler_NoUploadNewImage("accept");
        getAdFeatureID_FUN_Cooler();
        setAdID(tempAdID);
    }

    @When("I post a new Washing Machine Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewWashingMachineAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertElectricAppliances.instance().insertNewAdWashing_Machine_NoUploadNewImage("accept");
        getAdFeatureID_FUN_WashingMachine();
        setAdID(tempAdID);
    }

    @When("I post a new Household Table Chair Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewHouseholdTableChairAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertHousehold.instance().insertNewAdHousehold_Table_Chair_NoUploadNewImage("accept");
        getAdFeatureID_HouseHold_TableChair();
        setAdID(tempAdID);
    }

    @When("I post a new Household Drawer Shelf Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewHouseholdDrawerShelfAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertHousehold.instance().insertNewAdHousehold_Drawer_Shelf_NoUploadNewImage("accept");
        getAdFeatureID_HouseHold_DrawerShelf();
        setAdID(tempAdID);
    }

    @When("I post a new Household Bed Bedding Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewHouseholdBedBeddingAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertHousehold.instance().insertNewAdHousehold_Bed_NoUploadNewImage("accept");
        getAdFeatureID_HouseHold_Bed();
        setAdID(tempAdID);
    }

    @When("I post a new Household Kitchen Appliance Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewKitchenApplianceAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertHousehold.instance().insertNewAdKitchen_Appliance_NoUploadNewImage("accept");
        getAdFeatureID_HouseHold_KitchenAppliance();
        setAdID(tempAdID);
    }

    @When("I post a new Kitchen Utensil Dinnerware Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewKitchenUtensilDinnerwareAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertHousehold.instance().insertNewAdHousehold_Dinnerware_NoUploadNewImage("accept");
        getAdFeatureID_HouseHold_KitchenDinnerware();
        setAdID(tempAdID);
    }

    @When("I post a new Household Fan Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewFanAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertHousehold.instance().insertNewAdFan_NoUploadNewImage("accept");
        getAdFeatureID_HouseHold_Fan();
        setAdID(tempAdID);
    }

    @When("I post a new Household Lighting Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewLightingAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertHousehold.instance().insertNewAdLighting_NoUploadNewImage("accept");
        getAdFeatureID_HouseHold_Lighting();
        setAdID(tempAdID);
    }

    @When("I post a new Household Plant Decoration Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewPlantDecorationAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertHousehold.instance().insertNewAdOrnamental_Plant_Decoration_NoUploadNewImage("accept");
        getAdFeatureID_HouseHold_PlantDecoration();
        setAdID(tempAdID);
    }

    @When("I post a new Household Bathware Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewSanitarywaresBathroomsAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertHousehold.instance().insertNewAdBathware_NoUploadNewImage("accept");
        getAdFeatureID_HouseHold_Bathware();
        setAdID(tempAdID);
    }

    @When("I post a new Other Household Items Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewOtherHouseholdItemsAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertHousehold.instance().insertNewAdOther_Household_Items_NoUploadNewImage("accept");
        getAdFeatureID_HouseHold_OtherItems();
        setAdID(tempAdID);
    }

    @When("I post a new Mom And Baby Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewMomAndBabyAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertMomAndBaby.instance().insertNewAdMomAndBaby_NoUploadNewImage("accept");
        getAdFeatureID_MonAndBaby();
        setAdID(tempAdID);
    }

    @When("I post a new Clothes Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewClothesAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertFashion.instance().insertNewAdClothes_NoUploadNewImage("accept");
        getAdFeatureID_Fashion_Clothes();
        setAdID(tempAdID);
    }

    @When("I post a new Watch Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewWatchAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertFashion.instance().insertNewWatchAd_NoUploadNewImage("accept");
        getAdFeatureID_Fashion_Watch();
        setAdID(tempAdID);
    }

    @When("I post a new Shoes Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewShoesAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertFashion.instance().insertNewAdShoes_NoUploadNewImage("accept");
        getAdFeatureID_Fashion_Shoes();
        setAdID(tempAdID);
    }

    @When("I post a new Handbag Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewHandbagAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertFashion.instance().insertNewAdHandbag_NoUploadNewImage("accept");
        getAdFeatureID_Fashion_Handbag();
        setAdID(tempAdID);
    }

    @When("I post a new Perfume Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewPerfumeAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertFashion.instance().insertNewAdPerfume_NoUploadNewImage("accept");
        getAdFeatureID_Fashion_Perfume();
        setAdID(tempAdID);
    }

    @When("I post a new Fashion Accessories Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewFashionAccessoriesAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertFashion.instance().insertNewAdAccessories_NoUploadNewImage("accept");
        getAdFeatureID_Fashion_Accessories();
        setAdID(tempAdID);
    }

    @When("I post a new Instrument Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewInstrumentAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertEntertainment.instance().insertNewAdInstrument_NoUploadNewImage("accept");
        getAdFeatureID_ENT_Instrument();
        setAdID(tempAdID);
    }

    @When("I post a new Book Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewBookAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertEntertainment.instance().insertNewAdBook_NoUploadNewImage("accept");
        getAdFeatureID_ENT_Book();
        setAdID(tempAdID);
    }

    @When("I post a new Sport Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewSportAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertEntertainment.instance().insertNewAdSport_NoUploadNewImage("accept");
        getAdFeatureID_ENT_Sport();
        setAdID(tempAdID);
    }

    @When("I post a new Gaming Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewGamingAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertEntertainment.instance().insertNewAdGaming_NoUploadNewImage("accept");
        getAdFeatureID_ENT_Gaming();
        setAdID(tempAdID);
    }

    @When("I post a new Hobby Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewHobbyAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertEntertainment.instance().insertNewAdHobby_NoUploadNewImage("accept");
        getAdFeatureID_ENT_Hobby();
        setAdID(tempAdID);
    }

    @When("I post a new Office Equipment Requisite Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewOfficeEquipmentRequisiteAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertIndustrial.instance().insertNewAdOfficeEquipment_NoUploadNewImage("accept");
        getAdFeatureID_Requisite_OfficeEquipment();
        setAdID(tempAdID);
    }

    @When("I post a new Specialized Item Requisite Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewSpecializedItemRequisiteAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertIndustrial.instance().insertNewAdSpecializedItem_NoUploadNewImage("accept");
        getAdFeatureID_Requisite_SpecializedItem();
        setAdID(tempAdID);
    }

    @When("I post a new Service Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewServiceAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertServices.instance().insertNewServiceAd_NoUploadNewImage("accept");
        getAdFeatureID_Service_Service();
        setAdID(tempAdID);
    }

    @When("I post a new Travel Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewTravelAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertServices.instance().insertNewTravelAd_NoUploadNewImage("accept");
        getAdFeatureID_Service_Travel();
        setAdID(tempAdID);
    }

    @When("I post a new Collectibles Ad and Cho Tot accept my Ad for ad feature")
    public void iAddANewCollectiblesAdAndChoTotAcceptMyAd() {
        CM_API_Ads_InsertEntertainment.instance().insertNewAdCollectibles_NoUploadNewImage("accept");
        getAdFeatureID_ENT_Collectibles();
        setAdID(tempAdID);
    }

    @Then("I should see all types of Ad Feature display")
    public void i_should_see_Ad_Feature_displays() {
        verifyAdFeatureID_Title();
        verifyAdFeatureID_Ribbon();
        verifyAdFeatureID_Price();
    }

    @Then("I should see Ad Feature Title displays")
    public void i_should_see_Ad_Feature_Title_displays() {
        verifyAdFeatureID_Title();
    }

    @Then("I should see Ad Feature Ribbon displays")
    public void i_should_see_Ad_Feature_Ribbon_displays() {
        verifyAdFeatureID_Ribbon();
    }

    @Then("I should see Ad Feature Price displays")
    public void i_should_see_Ad_Feature_Price_displays() {
        verifyAdFeatureID_Price();
    }

    @Then("I should not see Ad Feature Price displays")
    public void i_should_not_see_Ad_Feature_Price_displays() {
        verifyAdFeatureID_Price_NotDisplay();
    }

    @Then("I should not see Ad Feature Ribbon displays")
    public void i_should_not_see_Ad_Feature_Ribbon_displays() {
        verifyAdFeatureID_Ribbon_NotDisplay();
    }

    @Then("I should see my ad being added Nhãn GIÁ TỐT")
    public void i_should_see_my_ad_being_added_nhan_good_price() {
        verifyAdFeature_GoodPriceLabel(getAdID());
    }

    @Then("I should see my ad being added Nhãn HOT")
    public void i_should_see_my_ad_being_added_nhan_hot() {
        verifyAdFeature_HotLabel(getAdID());
    }

    @Then("I should see my Ad is added TITLE In Đậm and not added Nhãn GIÁ TỐT, Nhãn HOT")
    public void i_should_see_my_ad_being_added_title_() {
        verifyAdFeature_BoldTitle(getAdID());
    }

}
