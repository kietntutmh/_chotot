package projects.steps.chotot.api.flashad;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import projects.functions.chotot.flashad.FlashAd_ELT_Functions;

import static api.configuration.DataConfig.tempAdID;
import static api.configuration.GatewayConfig.global_accessToken;

public class FlashAd_ELT_Steps extends FlashAd_ELT_Functions {

    //Phone
    @Then("I post a ELT Phone Private and Chotot accepted my Ad")
    public void i_shoulsd_ssee_myss_nsew_ad_displayed_ons_Filter_actsive() {
        insertPhoneSellPrivate(global_accessToken, "accept", false);
    }

    @Then("I post {string} ELT Phone Shop Ad To Chotot and Chotot accepted my Ad")
    public void i_shoulsd_ssee_mys_nsew_ad_displayed_ons_Filter_actsive(String numberOfAd) {
        for (int i = 0; i < Integer.parseInt(numberOfAd); i++) {
            insertPhoneSellShop(global_accessToken, "accept", true, false);
        }
    }

    @Then("I post a ELT Phone Shop Ad To Chotot and Chotot accepted my Ad")
    public void i_shoulssd_ssee_mys_nsew_ad_displayed_ons_Filter_actsive() {
        insertPhoneSellShop(global_accessToken, "accept", true, false);
    }

    @Then("I post a ELT Phone Shop Ad To Chotot and Chotot refused my Ad")
    public void i_should_ssee_mys_nsew_ad_displayed_ons_Filter_actsive() {
        insertPhoneSellShop(global_accessToken, "refuse", true, false);
    }

    @Then("I post a ELT Phone Shop Ad To Chotot and Chotot is reviewing my Ad")
    public void i_should_ssee_mys_nsew_ad_displayed_ons_Fislter_actsive() {
        insertPhoneSellShop(global_accessToken, "no action", true, false);
    }


    @When("I post 1st ELT Phone Ad and Chotot accepted my Ad")
    @When("I post 2nd ELT Phone Ad and Chotot accepted my Ad")
    @Then("I post a ELT Phone Shop Ad and Chotot accepted my Ad")
    public void i_shoulssd_ssee_mys_nsew_ad_displaysed_ons_Filter_actsive() {
        insertPhoneSellShop(global_accessToken, "accept", false, false);
    }

    @Then("I post and pay for 3rd ELT Phone Ad and Chotot accepted my Ad")
    public void i_shoulssd_ssee_mys_nssew_ads_displaysed_ons_Filter_actsive() {
        insertPhoneSellShop(global_accessToken, "acceptpay", false, false);
    }

    @Then("I post a ELT Phone Shop Ad and Chotot refused my Ad")
    public void i_should_ssee_mys_nsew_ad_disssplayed_ons_Filter_actsive() {
        insertPhoneSellShop(global_accessToken, "refuse", false, false);
    }

    @Then("I post a ELT Phone Shop Ad and Chotot is reviewing my Ad")
    public void i_should_ssee_mys_nsew_ad_dispslayed_ons_Fislter_actsive() {
        insertPhoneSellShop(global_accessToken, "no action", false, false);
    }


    //Tablet
    @Then("I post a ELT Tablet Shop Ad To Chotot and Chotot accepted my Ad")
    public void i_shoulsd_ssee_mys_nsew_ad_dispslayed_ons_Filter_actsive() {
        insertTabletSellShop(global_accessToken, "accept", true, false);
    }

    @Then("I post a ELT Tablet Shop Ad To Chotot and Chotot refused my Ad")
    public void i_should_ssee_mys_nsew_ad_dissplayed_ons_Filter_actsive() {
        insertTabletSellShop(global_accessToken, "refuse", true, false);
    }

    @Then("I post a ELT Tablet Shop Ad To Chotot and Chotot is reviewing my Ad")
    public void i_should_ssee_mys_nsew_ad_dissplayed_ons_Fislter_actsive() {
        insertTabletSellShop(global_accessToken, "no action", true, false);
    }


    //Laptop
    @Then("I post a ELT Laptop Shop Ad To Chotot and Chotot accepted my Ad")
    public void i_shsoulsd_ssee_mys_nsew_ad_displayed_ons_Filter_actsive() {
        insertLaptopSellShop(global_accessToken, "accept", true, false);
    }

    @Then("I post a ELT Laptop Shop Ad To Chotot and Chotot refused my Ad")
    public void i_sshould_ssee_mys_nsew_ad_displayed_ons_Filter_actsive() {
        insertLaptopSellShop(global_accessToken, "refuse", true, false);
    }

    @Then("I post a ELT Laptop Shop Ad To Chotot and Chotot is reviewing my Ad")
    public void i_should_ssee_smys_nsew_ad_displayed_ons_Fislter_actsive() {
        insertLaptopSellShop(global_accessToken, "no action", true, false);
    }


    //TVSound
    @Then("I post a ELT TVSound Shop Ad To Chotot and Chotot accepted my Ad")
    public void i_shoulsd_ssee_mys_nsew_ad_displasyed_ons_Filter_actsive() {
        insertTVSoundSellShop(global_accessToken, "accept", true, false);
    }

    @Then("I post a ELT TVSound Shop Ad To Chotot and Chotot refused my Ad")
    public void i_should_ssee_mys_nsew_ad_displayed_sons_Filter_actsive() {
        insertTVSoundSellShop(global_accessToken, "refuse", true, false);
    }

    @Then("I post a ELT TVSound Shop Ad To Chotot and Chotot is reviewing my Ad")
    public void i_should_ssee_mys_nsew_ad_displayed_onss_Fislter_actsive() {
        insertTVSoundSellShop(global_accessToken, "no action", true, false);
    }


    //Camera
    @Then("I post a ELT Camera Shop Ad To Chotot and Chotot accepted my Ad")
    public void i_shoulsd_ssee_mys_nsew_ad_dissplayed_ons_Filter_actsive() {
        insertCameraSellShop(global_accessToken, "accept", true, false);
    }

    @Then("I post a ELT Camera Shop Ad To Chotot and Chotot refused my Ad")
    public void i_should_ssee_mys_nsew_ad_dsisplayed_ons_Filter_actsive() {
        insertCameraSellShop(global_accessToken, "refuse", true, false);
    }

    @Then("I post a ELT Camera Shop Ad To Chotot and Chotot is reviewing my Ad")
    public void i_should_ssese_smys_nsew_ad_displayed_ons_Fislter_actsive() {
        insertCameraSellShop(global_accessToken, "no action", true, false);
    }


    //PCComponent
    @Then("I post a ELT PCComponent Shop Ad To Chotot and Chotot accepted my Ad")
    public void i_shoulsd_ssese_mys_nsew_ad_displayed_ons_Filter_actsive() {
        insertPCComponentSellShop(global_accessToken, "accept", true, false);
    }

    @Then("I post a ELT PCComponent Shop Ad To Chotot and Chotot refused my Ad")
    public void i_should_sssee_mys_nsew_ad_displayed_ons_Filter_actsive() {
        insertPCComponentSellShop(global_accessToken, "refuse", true, false);
    }

    @Then("I post a ELT PCComponent Shop Ad To Chotot and Chotot is reviewing my Ad")
    public void i_should_sssee_mys_nsew_ad_displayed_ons_Fislter_actsive() {
        insertPCComponentSellShop(global_accessToken, "no action", true, false);
    }


    //Accessories
    @Then("I post a ELT Accessories Shop Ad To Chotot and Chotot accepted my Ad")
    public void i_shoulsd_sssese_mys_nsew_ad_displayed_ons_Filter_actsive() {
        insertAccessoriesSellShop(global_accessToken, "accept", true, false);
    }

    @Then("I post a ELT Accessories Shop Ad To Chotot and Chotot refused my Ad")
    public void i_should_sssese_mys_nsew_ad_displayed_ons_Filter_actsive() {
        insertAccessoriesSellShop(global_accessToken, "refuse", true, false);
    }

    @Then("I post a ELT Accessories Shop Ad To Chotot and Chotot is reviewing my Ad")
    public void i_should_ssssee_mys_nsew_ad_displayed_ons_Fislter_actsive() {
        insertAccessoriesSellShop(global_accessToken, "no action", true, false);
    }


    //Wearable
    @Then("I post a ELT Wearable Shop Ad To Chotot and Chotot accepted my Ad")
    public void i_shoulssd_sssese_mys_nsew_ad_displayed_ons_Filter_actsive() {
        insertWearableSellShop(global_accessToken, "accept", true, false);
    }

    @Then("I post a ELT Wearable Shop Ad To Chotot and Chotot refused my Ad")
    public void i_should_ssseses_mys_nsew_ad_displayed_ons_Filter_actsive() {
        insertWearableSellShop(global_accessToken, "refuse", true, false);
    }

    @Then("I post a ELT Wearable Shop Ad To Chotot and Chotot is reviewing my Ad")
    public void i_should_ssssee_mys_nsew_ad_dissplayed_ons_Fislter_actsive() {
        insertWearableSellShop(global_accessToken, "no action", true, false);
    }

    //PC
    @Then("I post a ELT PC Shop Ad To Chotot and Chotot accepted my Ad")
    public void i_shoulsssd_sssese_mys_nsew_ad_displayed_ons_Filter_actsive() {
        insertPCSellShop(global_accessToken, "accept", true, false);
    }

    @Then("I post a ELT PC Shop Ad To Chotot and Chotot refused my Ad")
    public void i_should_sssseses_mys_nsew_ad_displayed_ons_Filter_actsive() {
        insertPCSellShop(global_accessToken, "refuse", true, false);
    }

    @Then("I post a ELT PC Shop Ad To Chotot and Chotot is reviewing my Ad")
    public void i_should_sssssee_mys_nsew_ad_dissplayed_ons_Fislter_actsive() {
        insertPCSellShop(global_accessToken, "no action", true, false);
    }


    //I edit my PTY "Phone" Sell Shop Ad To Chotot and Chotot "accepted" my Ad"
    @When("I edit my ELT {string} Sell Shop Ad To Chotot and Chotot {string} my Ad")
    public void i_edit_a_PTY_House_Ad_and_Chotot_is_reviewing_mys_Ad_region_hcm(String category, String actionStr) {
        editStepGlobal(category, actionStr, true);
    }


    @When("I edit my ELT {string} Sell Shop Ad and Chotot {string} my Ad")
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
            case "phone":
                editPhoneSellShop(global_accessToken, tempAdID, action, adToChotot, false);
                break;
            case "tablet":
                editTabletSellShop(global_accessToken, tempAdID, action, adToChotot, false);
                break;
            case "laptop":
                editLaptopSellShop(global_accessToken, tempAdID, action, adToChotot, false);
                break;
            case "pc":
                editPCSellShop(global_accessToken, tempAdID, action, adToChotot, false);
                break;
            case "wearable":
                editWearableSellShop(global_accessToken, tempAdID, action, adToChotot, false);
                break;
            case "pccomponent":
                editPCComponentSellShop(global_accessToken, tempAdID, action, adToChotot, false);
                break;
            case "accessories":
                editAccessoriesSellShop(global_accessToken, tempAdID, action, adToChotot, false);
                break;
            case "camera":
                editCameraSellShop(global_accessToken, tempAdID, action, adToChotot, false);
                break;
            case "tvsound":
                editTVSoundSellShop(global_accessToken, tempAdID, action, adToChotot, false);
                break;
            default:
                Assert.assertTrue(false, "Doesn't exist this category");
                break;
        }
    }
}
