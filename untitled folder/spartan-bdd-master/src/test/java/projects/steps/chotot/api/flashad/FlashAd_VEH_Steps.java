package projects.steps.chotot.api.flashad;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import projects.functions.chotot.flashad.FlashAd_VEH_Functions;

import static api.configuration.DataConfig.tempAdID;
import static api.configuration.GatewayConfig.global_accessToken;

public class FlashAd_VEH_Steps extends FlashAd_VEH_Functions {

    //Car
    @Then("I post {string} VEH Car Shop Ad To Chotot and Chotot accepted my Ad")
    public void i_shoulsd_ssee_mys_nsew_ad_displayed_ons_Filter_actsive(String numberOfAd) {
        for (int i = 0; i < Integer.parseInt(numberOfAd); i++) {
            insertCarSellShop(global_accessToken, "accept", true, false);
            waitConstant(2);
        }
    }

    @Then("I post a VEH Car Shop Ad To Chotot and Chotot accepted my Ad")
    public void i_shoulssd_ssee_mys_nsew_ad_displayed_ons_Filter_actsive() {
        insertCarSellShop(global_accessToken, "accept", true, false);
    }

    @Then("I post a VEH Car Shop Ad To Chotot and Chotot refused my Ad")
    public void i_should_ssee_mys_nsew_ad_displayed_ons_Filter_actsive() {
        insertCarSellShop(global_accessToken, "refuse", true, false);
    }

    @Then("I post a VEH Car Shop Ad To Chotot and Chotot is reviewing my Ad")
    public void i_should_ssee_mys_nsew_ad_displayed_ons_Fislter_actsive() {
        insertCarSellShop(global_accessToken, "no action", true, false);
    }



    @Then("I post a VEH Car Shop Ad and Chotot accepted my Ad")
    public void i_shoulssd_ssee_mys_nsew_ad_displaysed_ons_Filter_actsive() {
        insertCarSellShop(global_accessToken, "accept", false, false);
    }

    @Then("I post a VEH Car Shop Ad and Chotot refused my Ad")
    public void i_should_ssee_mys_nsew_ad_disssplayed_ons_Filter_actsive() {
        insertCarSellShop(global_accessToken, "refuse", false, false);
    }

    @Then("I post a VEH Car Shop Ad and Chotot is reviewing my Ad")
    public void i_should_ssee_mys_nsew_ad_dispslayed_ons_Fislter_actsive() {
        insertCarSellShop(global_accessToken, "no action", false, false);
    }


    @Then("I post a VEH Car Ad and Chotot accepted my Ad")
    @Then("I post 1st VEH Car Ad and Chotot accepted my Ad")
    public void i_shoulssd_ssee_mys_nsew_ad_displasysed_ons_Filter_actsive() {
        insertCarSellPrivate(global_accessToken, "accept", false);
    }

    @Then("I post and pay for 2nd VEH Car Ad and Chotot accepted my Ad")
    @Then("I post and pay for 3rd VEH Car Ad and Chotot accepted my Ad")
    @Then("I post and pay for a VEH Car Ad and Chotot accepted my Ad")
    public void i_shoulssd_ssee_mys_nsew_ad_displasysed_onss_Filter_actsive() {
        insertCarSellPrivate(global_accessToken, "acceptpay", false);
    }

    @Then("I post a VEH Car Ad and Chotot refused my Ad")
    public void i_should_ssee_mys_nsssew_ad_disssplayed_ons_Filter_actsive() {
        insertCarSellPrivate(global_accessToken, "refuse", false);
    }

    @Then("I post a VEH Car Ad and Chotot is reviewing my Ad")
    public void i_should_ssee_mys_nssssew_ad_dispslayed_ons_Fislter_actsive() {
        insertCarSellPrivate(global_accessToken, "no action", false);
    }



    //Truck
    @Then("I post a VEH Truck Shop Ad To Chotot and Chotot accepted my Ad")
    public void i_shoulsd_ssee_mys_nsew_ad_dispslayed_ons_Filter_actsive() {
        insertTruckSellShop(global_accessToken, "accept", true, false);
    }

    @Then("I post a VEH Truck Shop Ad To Chotot and Chotot refused my Ad")
    public void i_should_ssee_mys_nsew_ad_dissplayed_ons_Filter_actsive() {
        insertTruckSellShop(global_accessToken, "refuse", true, false);
    }

    @Then("I post a VEH Truck Shop Ad To Chotot and Chotot is reviewing my Ad")
    public void i_should_ssee_mys_nsew_ad_dissplayed_ons_Fislter_actsive() {
        insertTruckSellShop(global_accessToken, "no action", true, false);
    }


    //Motorbike
    @Then("I post a VEH Motorbike Shop Ad To Chotot and Chotot accepted my Ad")
    public void i_shsoulsd_ssee_mys_nsew_ad_displayed_ons_Filter_actsive() {
        insertMotorbikeSellShop(global_accessToken, "accept", true, false);
    }

    @Then("I post a VEH Motorbike Shop Ad To Chotot and Chotot refused my Ad")
    public void i_sshould_ssee_mys_nsew_ad_displayed_ons_Filter_actsive() {
        insertMotorbikeSellShop(global_accessToken, "refuse", true, false);
    }

    @Then("I post a VEH Motorbike Shop Ad To Chotot and Chotot is reviewing my Ad")
    public void i_should_ssee_smys_nsew_ad_displayed_ons_Fislter_actsive() {
        insertMotorbikeSellShop(global_accessToken, "no action", true, false);
    }


    //OtherVehicle
    @Then("I post a VEH Other Vehicle Shop Ad To Chotot and Chotot accepted my Ad")
    public void i_shoulsd_ssee_mys_nsew_ad_displasyed_ons_Filter_actsive() {
        insertOtherVehicleSellShop(global_accessToken, "accept", true, false);
    }

    @Then("I post a VEH Other Vehicle Shop Ad To Chotot and Chotot refused my Ad")
    public void i_should_ssee_mys_nsew_ad_displayed_sons_Filter_actsive() {
        insertOtherVehicleSellShop(global_accessToken, "refuse", true, false);
    }

    @Then("I post a VEH Other Vehicle Shop Ad To Chotot and Chotot is reviewing my Ad")
    public void i_should_ssee_mys_nsew_ad_displayed_onss_Fislter_actsive() {
        insertOtherVehicleSellShop(global_accessToken, "no action", true, false);
    }

    //VehiclePart
    @Then("I post a VEH Vehicle Part Shop Ad To Chotot and Chotot accepted my Ad")
    public void i_shoulsd_sssese_mys_nsew_ad_displayed_ons_Filter_actsive() {
        insertVehiclePartSellShop(global_accessToken, "accept", true, false);
    }

    @Then("I post a VEH Vehicle Part Shop Ad To Chotot and Chotot refused my Ad")
    public void i_should_sssese_mys_nsew_ad_displayed_ons_Filter_actsive() {
        insertVehiclePartSellShop(global_accessToken, "refuse", true, false);
    }

    @Then("I post a VEH Vehicle Part Shop Ad To Chotot and Chotot is reviewing my Ad")
    public void i_should_ssssee_mys_nsew_ad_displayed_ons_Fislter_actsive() {
        insertVehiclePartSellShop(global_accessToken, "no action", true, false);
    }


    //ElectricVehicle
    @Then("I post a VEH Electric Vehicle Shop Ad To Chotot and Chotot accepted my Ad")
    public void i_shoulssd_sssese_mys_nsew_ad_displayed_ons_Filter_actsive() {
        insertElectricVehicleSellShop(global_accessToken, "accept", true, false);
    }

    @Then("I post a VEH Electric Vehicle Shop Ad To Chotot and Chotot refused my Ad")
    public void i_should_ssseses_mys_nsew_ad_displayed_ons_Filter_actsive() {
        insertElectricVehicleSellShop(global_accessToken, "refuse", true, false);
    }

    @Then("I post a VEH Electric Vehicle Shop Ad To Chotot and Chotot is reviewing my Ad")
    public void i_should_ssssee_mys_nsew_ad_dissplayed_ons_Fislter_actsive() {
        insertElectricVehicleSellShop(global_accessToken, "no action", true, false);
    }

    //Bicycles
    @Then("I post a VEH Bicycles Shop Ad To Chotot and Chotot accepted my Ad")
    public void i_shoulsssd_sssese_mys_nsew_ad_displayed_ons_Filter_actsive() {
        insertBicyclesSellShop(global_accessToken, "accept", true, false);
    }

    @Then("I post a VEH Bicycles Shop Ad To Chotot and Chotot refused my Ad")
    public void i_should_sssseses_mys_nsew_ad_displayed_ons_Filter_actsive() {
        insertBicyclesSellShop(global_accessToken, "refuse", true, false);
    }

    @Then("I post a VEH Bicycles Shop Ad To Chotot and Chotot is reviewing my Ad")
    public void i_should_sssssee_mys_nsew_ad_dissplayed_ons_Fislter_actsive() {
        insertBicyclesSellShop(global_accessToken, "no action", true, false);
    }


    //I edit my PTY "Car" Sell Shop Ad To Chotot and Chotot "accepted" my Ad"
    @When("I edit my VEH {string} Sell Shop Ad To Chotot and Chotot {string} my Ad")
    public void i_edit_a_PTY_House_Ad_and_Chotot_is_reviewing_mys_Ad_region_hcm(String category, String actionStr) {
        editStepGlobal(category, actionStr, true);
    }


    @When("I edit my VEH {string} Sell Shop Ad and Chotot {string} my Ad")
    public void i_edit_a_PTY_House_Ad_and_Chostot_is_reviewing_mys_Ad_region_hcm(String category, String actionStr) {
        editStepGlobal(category, actionStr, false);
    }

    private void editStepGlobal(String category, String actionStr, boolean adToChotot) {
        String action = null;
        switch (actionStr.toLowerCase().trim()) {
            case "accepted":
                action = "accept";
                break;
            case "refused":
                action = "refuse";
                break;
            case "is reviewing":
            default:
                action = "no action";
                break;
        }
        Assert.assertNotNull(action, "Action [" + actionStr + "] is invalid");
        setSubject(getSubject() + " EDIT");


        switch (category.toLowerCase()) {
            case "car":
                editCarSellShop(global_accessToken, tempAdID, action, adToChotot, false);
                break;
            case "truck":
                editTruckSellShop(global_accessToken, tempAdID, action, adToChotot, false);
                break;
            case "motorbike":
                editMotorbikeSellShop(global_accessToken, tempAdID, action, adToChotot, false);
                break;
            case "bicycles":
                editBicyclesSellShop(global_accessToken, tempAdID, action, adToChotot, false);
                break;
            case "electricvehicle":
            case "electric vehicle":
                editElectricVehicleSellShop(global_accessToken, tempAdID, action, adToChotot, false);
                break;
            case "othervehicle":
            case "other vehicle":
                editOtherVehicleSellShop(global_accessToken, tempAdID, action, adToChotot, false);
                break;
            case "vehiclepart":
            case "vehicle part":
                editVehiclePartSellShop(global_accessToken, tempAdID, action, adToChotot, false);
                break;
            default:
                Assert.assertTrue(false, "Doesn't exist this category");
                break;
        }
    }
}
