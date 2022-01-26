package projects.steps.chotot.api.flashad;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import projects.functions.chotot.flashad.FlashAd_PTY_Functions;

import static api.configuration.DataConfig.tempAdID;
import static api.configuration.GatewayConfig.global_accessToken;
import static projects.functions.chotot.payment.PayOrder_API_Functions.paymentOrderDT4B;
import static projects.functions.chotot.pos.POS_Functions.posBump;
import static projects.functions.chotot.shops.PaidShop_API_Functions.*;

public class FlashAd_PTY_Steps extends FlashAd_PTY_Functions {

    //-------------------- PTY House -------------------------
    @When("I post a PTY House Ad and Chotot is reviewing my Ad")
    public void i_should_ssee_my_new_ad_displayed_on_Filter_active() {
        insertHouseSellPrivate(global_accessToken, "no action", false);
    }

    @Then("I post a PTY House Ad and Chotot refused my Ad")
    public void i_should_ssee_my_new_ad_displayed_on_Filter_actsive() {
        insertHouseSellPrivate(global_accessToken, "refuse", false);
    }

    @Then("I post a PTY House Ad and Chotot accepted my Ad")
    @Then("I post 1st PTY House Ad and Chotot accepted my Ad")
    public void i_should_ssee_my_nsew_ad_displayed_on_Filter_actsive() {
        insertHouseSellPrivate(global_accessToken, "accept", false);
    }

    @Then("I post and pay for 2nd PTY House Ad and Chotot accepted my Ad")
    @Then("I post and pay for 3rd PTY House Ad and Chotot accepted my Ad")
    @Then("I post and pay for a PTY House Ad and Chotot accepted my Ad")
    public void i_shoulsd_ssee_my_nsew_ad_displayed_on_Filter_actsive() {
        insertHouseSellPrivate(global_accessToken, "acceptpay", false);
    }

    @And("I post a PTY House Pro Ad and Chotot is reviewing my Ad")
    public void i_should_ssee_my_new_ad_displayed_osn_Filter_active() {
        insertHouseSellPro(global_accessToken, "no action", false);
    }

    @Then("I post a PTY House Pro Ad and Chotot refused my Ad")
    public void i_should_ssee_mys_new_ad_displayed_on_Filter_actsive() {
        insertHouseSellPro(global_accessToken, "refuse", false);
    }

    @Then("I post a PTY House Pro Ad and Chotot accepted my Ad")
    public void i_should_sssee_mys_nsew_ad_displayed_on_Filter_actsive() {
        insertHouseSellPro(global_accessToken, "accept", false);
    }

    @Then("I post and pay a PTY House Pro Ad and Chotot accepted my Ad")
    public void i_should_sssee_my_nsew_ad_displayed_on_Filter_actsive() {
        insertHouseSellPro(global_accessToken, "acceptpay", false);
    }

    @Then("I post and pay {string} PTY House Pro Ad and Chotot accepted my Ad")
    public void i_should_sssssee_my_nsew_ad_displayed_on_Filter_actsive(String numberOfAd) {
        for (int i = 0; i < Integer.parseInt(numberOfAd); i++) {
            insertHouseSellPro(global_accessToken, "acceptpay", false);
        }
    }

    @And("I post a PTY House Shop Ad and Chotot is reviewing my Ad")
    public void i_should_ssee_my_new_ad_displayed_ossn_Filter_active() {
        insertHouseSellShop(global_accessToken, "no action", false, false);
    }

    @Then("I post a PTY House Shop Ad and Chotot refused my Ad")
    public void i_should_ssee_mys_new_ad_displayed_ons_Filter_actsive() {
        insertHouseSellShop(global_accessToken, "refuse", false, false);
    }

    @Then("I post a PTY House Shop Ad and Chotot accepted my Ad")
    public void i_should_sssee_my_nsew_ad_displayeds_on_Filter_actsive() {
        insertHouseSellShop(global_accessToken, "accept", false, false);
    }

    @And("I post {string} PTY House Shop Ad To Chotot and Chotot is reviewing my Ad")
    public void i_should_ssee_my_new_ad_displayed_ossns_Filter_active(String numberOfAd) {
        for (int i = 0; i < Integer.parseInt(numberOfAd); i++) {
            insertHouseSellShop(global_accessToken, "no action", true, false);
        }
    }

    @Then("I post a PTY House Shop Ad To Chotot and Chotot refused my Ad")
    public void i_should_ssee_mys_nsew_ad_displayed_ons_Filter_actsive() {
        insertHouseSellShop(global_accessToken, "refuse", true, false);
    }

    @Then("I post a PTY House Shop Ad To Chotot and Chotot accepted my Ad")
    public void i_shoulsd_sssee_my_nsew_ad_displayeds_on_Filter_actsive() {
        insertHouseSellShop(global_accessToken, "accept", true, false);
    }

    @Then("I post {string} PTY House Shop Ad To Chotot and Chotot accepted my Ad")
    public void i_shoulssd_sssee_my_nsew_ad_displayeds_on_Filter_actsive(String numberOfAd) {
        for (int i = 0; i < Integer.parseInt(numberOfAd); i++) {
            insertHouseSellShop(global_accessToken, "accept", true, false);
        }
    }

    @When("I publish my Shop Ad To Chotot as Free")
    public void i_shoulssd_sssesse_my_nsew_ad_dissplayeds_on_Filter_actsive() {
        moveAdToChototFree(global_accessToken, getShopAlias(), tempAdID);
    }

    @When("I publish my Shop Ad To Chotot as Paid")
    public void i_shoulssd_sssesse_my_nsew_ad_dissplayedss_on_Filter_actsive() {
        moveAdToChototPaid(global_accessToken, tempAdID);
    }


    @Then("I post {string} PTY House Shop Ad and Chotot accepted my Ad")
    public void i_shoulssd_sssee_my_nsew_ad_dispslayeds_on_Filter_actsive(String numberOfAd) {
        for (int i = 0; i < Integer.parseInt(numberOfAd); i++) {
            insertHouseSellShop(global_accessToken, "accept", false, false);
        }
    }

    @When("I post a PTY House Rent Ad and Chotot is reviewing my Ad")
    public void i_should_sssee_my_new_ad_displayed_on_Filter_active() {
        insertHouseRentPrivate(global_accessToken, "no action", false);
    }

    @Then("I post a PTY House Rent Ad and Chotot refused my Ad")
    public void i_should_ssees_my_new_ad_displayed_on_Filter_actsive() {
        insertHouseRentPrivate(global_accessToken, "refuse", false);
    }

    @Then("I post a PTY House Rent Ad and Chotot accepted my Ad")
    public void i_should_ssee_smy_nsew_ad_displayed_on_Filter_actsive() {
        insertHouseRentPrivate(global_accessToken, "accept", false);
    }

    @And("I post a PTY House Pro Rent Ad and Chotot is reviewing my Ad")
    public void i_should_sssee_my_new_ad_displayed_osn_Filter_active() {
        insertHouseRentPro(global_accessToken, "no action", false);
    }

    @Then("I post a PTY House Pro Rent Ad and Chotot refused my Ad")
    public void i_shoulds_ssee_mys_new_ad_displayed_on_Filter_actsive() {
        insertHouseRentPro(global_accessToken, "refuse", false);
    }

    @Then("I post a PTY House Pro Rent Ad and Chotot accepted my Ad")
    public void i_should_ssssee_my_nsew_ad_displayed_on_Filter_actsive() {
        insertHouseRentPro(global_accessToken, "accept", false);
    }

    @And("I post a PTY House Shop Rent Ad and Chotot is reviewing my Ad")
    public void i_shsould_ssee_my_new_ad_displayed_ossn_Filter_active() {
        insertHouseRentShop(global_accessToken, "no action", false, false);
    }

    @Then("I post a PTY House Shop Rent Ad and Chotot refused my Ad")
    public void i_sshould_ssee_mys_new_ad_displayed_ons_Filter_actsive() {
        insertHouseRentShop(global_accessToken, "refuse", false, false);
    }

    @Then("I post a PTY House Shop Rent Ad and Chotot accepted my Ad")
    public void i_sshould_sssee_my_nsew_ad_displayeds_on_Filter_actsive() {
        insertHouseRentShop(global_accessToken, "accept", false, false);
    }

    @And("I post a PTY House Shop Rent Ad To Chotot and Chotot is reviewing my Ad")
    public void i_should_ssees_my_new_ad_displayed_ossns_Filter_active() {
        insertHouseRentShop(global_accessToken, "no action", true, false);
    }

    @Then("I post a PTY House Shop Rent Ad To Chotot and Chotot refused my Ad")
    public void i_should_ssee_mys_nsew_ads_displayed_ons_Filter_actsive() {
        insertHouseRentShop(global_accessToken, "refuse", true, false);
    }

    @Then("I post a PTY House Shop Rent Ad To Chotot and Chotot accepted my Ad")
    public void i_shoulsd_sssee_my_nsew_asd_displayeds_on_Filter_actsive() {
        insertHouseRentShop(global_accessToken, "accept", true, false);
    }

    @Then("I post {string} PTY House Shop Rent Ad To Chotot and Chotot accepted my Ad")
    public void i_shoulssd_sssee_my_nsew_ad_displayseds_on_Filter_actsive(String numberOfAd) {
        for (int i = 0; i < Integer.parseInt(numberOfAd); i++) {
            insertHouseRentShop(global_accessToken, "accept", true, false);
        }
    }

    //-------------------- PTY Apartment -------------------------
    @When("I post a PTY Apartment Ad and Chotot is reviewing my Ad")
    public void i_should_ssee_my_new_ad_displayed_on_Filter_active_Apartment() {
        insertApartmentSellPrivate(global_accessToken, "no action", false);
    }

    @Then("I post a PTY Apartment Ad and Chotot refused my Ad")
    public void i_should_ssee_my_new_ad_displayed_on_Filter_actsive_Apartment() {
        insertApartmentSellPrivate(global_accessToken, "refuse", false);
    }

    @Then("I post a PTY Apartment Ad and Chotot accepted my Ad")
    public void i_should_ssee_my_nsew_ad_displayed_on_Filter_actsive_Apartment() {
        insertApartmentSellPrivate(global_accessToken, "accept", false);
    }

    @And("I post a PTY Apartment Pro Ad and Chotot is reviewing my Ad")
    public void i_should_ssee_my_new_ad_displayed_osn_Filter_active_Apartment() {
        insertApartmentSellPro(global_accessToken, "no action", false);
    }

    @Then("I post a PTY Apartment Pro Ad and Chotot refused my Ad")
    public void i_should_ssee_mys_new_ad_displayed_on_Filter_actsive_Apartment() {
        insertApartmentSellPro(global_accessToken, "refuse", false);
    }

    @Then("I post a PTY Apartment Pro Ad and Chotot accepted my Ad")
    public void i_should_sssee_my_nsew_ad_displayed_on_Filter_actsive_Apartment() {
        insertApartmentSellPro(global_accessToken, "accept", false);
    }

    @And("I post a PTY Apartment Shop Ad and Chotot is reviewing my Ad")
    public void i_should_ssee_my_new_ad_displayed_ossn_Filter_active_Apartment() {
        insertApartmentSellShop(global_accessToken, "no action", false, false);
    }

    @Then("I post a PTY Apartment Shop Ad and Chotot refused my Ad")
    public void i_should_ssee_mys_new_ad_displayed_ons_Filter_actsive_Apartment() {
        insertApartmentSellShop(global_accessToken, "refuse", false, false);
    }

    @Then("I post a PTY Apartment Shop Ad and Chotot accepted my Ad")
    public void i_should_sssee_my_nsew_ad_displayeds_on_Filter_actsive_Apartment() {
        insertApartmentSellShop(global_accessToken, "accept", false, false);
    }

    @And("I post a PTY Apartment Shop Ad To Chotot and Chotot is reviewing my Ad")
    public void i_should_ssee_my_new_ad_displayed_ossns_Filter_active_Apartment() {
        insertApartmentSellShop(global_accessToken, "no action", true, false);
    }

    @Then("I post a PTY Apartment Shop Ad To Chotot and Chotot refused my Ad")
    public void i_should_ssee_mys_nsew_ad_displayed_ons_Filter_actsive_Apartment() {
        insertApartmentSellShop(global_accessToken, "refuse", true, false);
    }

    @Then("I post a PTY Apartment Shop Ad To Chotot and Chotot accepted my Ad")
    public void i_shoulsd_sssee_my_nsew_ad_displayeds_on_Filter_actsive_Apartment() {
        insertApartmentSellShop(global_accessToken, "accept", true, false);
    }


    @When("I post a PTY Apartment Rent Ad and Chotot is reviewing my Ad")
    public void i_should_sssee_my_new_ad_displayed_on_Filter_active_Apartment() {
        insertApartmentRentPrivate(global_accessToken, "no action", false);
    }

    @Then("I post a PTY Apartment Rent Ad and Chotot refused my Ad")
    public void i_should_ssees_my_new_ad_displayed_on_Filter_actsive_Apartment() {
        insertApartmentRentPrivate(global_accessToken, "refuse", false);
    }

    @Then("I post a PTY Apartment Rent Ad and Chotot accepted my Ad")
    public void i_should_ssee_smy_nsew_ad_displayed_on_Filter_actsive_Apartment() {
        insertApartmentRentPrivate(global_accessToken, "accept", false);
    }

    @And("I post a PTY Apartment Pro Rent Ad and Chotot is reviewing my Ad")
    public void i_should_sssee_my_new_ad_displayed_osn_Filter_active_Apartment() {
        insertApartmentRentPro(global_accessToken, "no action", false);
    }

    @Then("I post a PTY Apartment Pro Rent Ad and Chotot refused my Ad")
    public void i_shoulds_ssee_mys_new_ad_displayed_on_Filter_actsive_Apartment() {
        insertApartmentRentPro(global_accessToken, "refuse", false);
    }

    @Then("I post a PTY Apartment Pro Rent Ad and Chotot accepted my Ad")
    public void i_should_ssssee_my_nsew_ad_displayed_on_Filter_actsive_Apartment() {
        insertApartmentRentPro(global_accessToken, "accept", false);
    }

    @And("I post a PTY Apartment Shop Rent Ad and Chotot is reviewing my Ad")
    public void i_shsould_ssee_my_new_ad_displayed_ossn_Filter_active_Apartment() {
        insertApartmentRentShop(global_accessToken, "no action", false, false);
    }

    @Then("I post a PTY Apartment Shop Rent Ad and Chotot refused my Ad")
    public void i_sshould_ssee_mys_new_ad_displayed_ons_Filter_actsive_Apartment() {
        insertApartmentRentShop(global_accessToken, "refuse", false, false);
    }

    @Then("I post a PTY Apartment Shop Rent Ad and Chotot accepted my Ad")
    public void i_sshould_sssee_my_nsew_ad_displayeds_on_Filter_actsive_Apartment() {
        insertApartmentRentShop(global_accessToken, "accept", false, false);
    }

    @And("I post a PTY Apartment Shop Rent Ad To Chotot and Chotot is reviewing my Ad")
    public void i_should_ssees_my_new_ad_displaysed_ossns_sFilter_active() {
        insertApartmentRentShop(global_accessToken, "no action", true, false);
    }

    @Then("I post a PTY Apartment Shop Rent Ad To Chotot and Chotot refused my Ad")
    public void i_should_ssee_mys_nsew_ads_displayed_ons_Filtser_actsive_Apartment() {
        insertApartmentRentShop(global_accessToken, "refuse", true, false);
    }

    @Then("I post a PTY Apartment Shop Rent Ad To Chotot and Chotot accepted my Ad")
    public void i_shoulsd_sssee_my_nsew_asd_displayeds_on_Filter_actssive_Apartment() {
        insertApartmentRentShop(global_accessToken, "accept", true, false);
    }

    @Then("I post {string} PTY Apartment Shop Ad To Chotot and Chotot accepted my Ad")
    public void i_shoulssd_sssee_my_nsew_ad_displayseds_son_Filter_actsive(String numberOfAd) {
        for (int i = 0; i < Integer.parseInt(numberOfAd); i++) {
            insertApartmentSellShop(global_accessToken, "accept", true, false);
        }
    }


    //-------------------- PTY Land -------------------------
    @When("I post a PTY Land Ad and Chotot is reviewing my Ad")
    public void i_should_ssee_my_new_ad_displayed_on_Filter_active_Land() {
        insertLandSellPrivate(global_accessToken, "no action", false);
    }

    @Then("I post a PTY Land Ad and Chotot refused my Ad")
    public void i_should_ssee_my_new_ad_displayed_on_Filter_actsive_Land() {
        insertLandSellPrivate(global_accessToken, "refuse", false);
    }

    @Then("I post a PTY Land Ad and Chotot accepted my Ad")
    public void i_should_ssee_my_nsew_ad_displayed_on_Filter_actsive_Land() {
        insertLandSellPrivate(global_accessToken, "accept", false);
    }

    @And("I post a PTY Land Pro Ad and Chotot is reviewing my Ad")
    public void i_should_ssee_my_new_ad_displayed_osn_Filter_active_Land() {
        insertLandSellPro(global_accessToken, "no action", false);
    }

    @Then("I post a PTY Land Pro Ad and Chotot refused my Ad")
    public void i_should_ssee_mys_new_ad_displayed_on_Filter_actsive_Land() {
        insertLandSellPro(global_accessToken, "refuse", false);
    }

    @Then("I post a PTY Land Pro Ad and Chotot accepted my Ad")
    public void i_should_sssee_my_nsew_ad_displayed_on_Filter_actsive_Land() {
        insertLandSellPro(global_accessToken, "accept", false);
    }

    @And("I post a PTY Land Shop Ad and Chotot is reviewing my Ad")
    public void i_should_ssee_my_new_ad_displayed_ossn_Filter_active_Land() {
        insertLandSellShop(global_accessToken, "no action", false, false);
    }

    @Then("I post a PTY Land Shop Ad and Chotot refused my Ad")
    public void i_should_ssee_mys_new_ad_displayed_ons_Filter_actsive_Land() {
        insertLandSellShop(global_accessToken, "refuse", false, false);
    }

    @Then("I post a PTY Land Shop Ad and Chotot accepted my Ad")
    public void i_should_sssee_my_nsew_ad_displayeds_on_Filter_actsive_Land() {
        insertLandSellShop(global_accessToken, "accept", false, false);
    }

    @And("I post a PTY Land Shop Ad To Chotot and Chotot is reviewing my Ad")
    public void i_should_ssee_my_new_ad_displayed_ossns_Filter_active_Land() {
        insertLandSellShop(global_accessToken, "no action", true, false);
    }

    @Then("I post a PTY Land Shop Ad To Chotot and Chotot refused my Ad")
    public void i_should_ssee_mys_nsew_ad_displayed_ons_Filter_actsive_Land() {
        insertLandSellShop(global_accessToken, "refuse", true, false);
    }

    @Then("I post a PTY Land Shop Ad To Chotot and Chotot accepted my Ad")
    public void i_shoulsd_sssee_my_nsew_ad_displayeds_on_Filter_actsive_Land() {
        insertLandSellShop(global_accessToken, "accept", true, false);
    }


    @When("I post a PTY Land Rent Ad and Chotot is reviewing my Ad")
    public void i_should_sssee_my_new_ad_displayed_on_Filter_active_Land() {
        insertLandRentPrivate(global_accessToken, "no action", false);
    }

    @Then("I post a PTY Land Rent Ad and Chotot refused my Ad")
    public void i_should_ssees_my_new_ad_displayed_on_Filter_actsive_Land() {
        insertLandRentPrivate(global_accessToken, "refuse", false);
    }

    @Then("I post a PTY Land Rent Ad and Chotot accepted my Ad")
    public void i_should_ssee_smy_nsew_ad_displayed_on_Filter_actsive_Land() {
        insertLandRentPrivate(global_accessToken, "accept", false);
    }

    @And("I post a PTY Land Pro Rent Ad and Chotot is reviewing my Ad")
    public void i_should_sssee_my_new_ad_displayed_osn_Filter_active_Land() {
        insertLandRentPro(global_accessToken, "no action", false);
    }

    @Then("I post a PTY Land Pro Rent Ad and Chotot refused my Ad")
    public void i_shoulds_ssee_mys_new_ad_displayed_on_Filter_actsive_Land() {
        insertLandRentPro(global_accessToken, "refuse", false);
    }

    @Then("I post a PTY Land Pro Rent Ad and Chotot accepted my Ad")
    public void i_should_ssssee_my_nsew_ad_displayed_on_Filter_actsive_Land() {
        insertLandRentPro(global_accessToken, "accept", false);
    }

    @And("I post a PTY Land Shop Rent Ad and Chotot is reviewing my Ad")
    public void i_shsould_ssee_my_new_ad_displayed_ossn_Filter_active_Land() {
        insertLandRentShop(global_accessToken, "no action", false, false);
    }

    @Then("I post a PTY Land Shop Rent Ad and Chotot refused my Ad")
    public void i_sshould_ssee_mys_new_ad_displayed_ons_Filter_actsive_Land() {
        insertLandRentShop(global_accessToken, "refuse", false, false);
    }

    @Then("I post a PTY Land Shop Rent Ad and Chotot accepted my Ad")
    public void i_sshould_sssee_my_nsew_ad_displayeds_on_Filter_actsive_Land() {
        insertLandRentShop(global_accessToken, "accept", false, false);
    }

    @And("I post a PTY Land Shop Rent Ad To Chotot and Chotot is reviewing my Ad")
    public void i_should_ssees_my_new_ad_displaysed_ossns_Filter_active() {
        insertLandRentShop(global_accessToken, "no action", true, false);
    }

    @Then("I post a PTY Land Shop Rent Ad To Chotot and Chotot refused my Ad")
    public void i_should_ssee_mys_nsew_ads_displayed_ons_Filtser_actsive_Land() {
        insertLandRentShop(global_accessToken, "refuse", true, false);
    }

    @Then("I post a PTY Land Shop Rent Ad To Chotot and Chotot accepted my Ad")
    public void i_shoulsd_sssee_my_nsew_asd_displayeds_on_Filter_actssive_Land() {
        insertLandRentShop(global_accessToken, "accept", true, false);
    }


    //-------------------- PTY Office -------------------------
    @When("I post a PTY Office Ad and Chotot is reviewing my Ad")
    public void i_should_ssee_my_new_ad_displayed_on_Filter_active_Office() {
        insertOfficeSellPrivate(global_accessToken, "no action", false);
    }

    @Then("I post a PTY Office Ad and Chotot refused my Ad")
    public void i_should_ssee_my_new_ad_displayed_on_Filter_actsive_Office() {
        insertOfficeSellPrivate(global_accessToken, "refuse", false);
    }

    @Then("I post a PTY Office Ad and Chotot accepted my Ad")
    public void i_should_ssee_my_nsew_ad_displayed_on_Filter_actsive_Office() {
        insertOfficeSellPrivate(global_accessToken, "accept", false);
    }

    @And("I post a PTY Office Pro Ad and Chotot is reviewing my Ad")
    public void i_should_ssee_my_new_ad_displayed_osn_Filter_active_Office() {
        insertOfficeSellPro(global_accessToken, "no action", false);
    }

    @Then("I post a PTY Office Pro Ad and Chotot refused my Ad")
    public void i_should_ssee_mys_new_ad_displayed_on_Filter_actsive_Office() {
        insertOfficeSellPro(global_accessToken, "refuse", false);
    }

    @Then("I post a PTY Office Pro Ad and Chotot accepted my Ad")
    public void i_should_sssee_my_nsew_ad_displayed_on_Filter_actsive_Office() {
        insertOfficeSellPro(global_accessToken, "accept", false);
    }

    @And("I post a PTY Office Shop Ad and Chotot is reviewing my Ad")
    public void i_should_ssee_my_new_ad_displayed_ossn_Filter_active_Office() {
        insertOfficeSellShop(global_accessToken, "no action", false, false);
    }

    @Then("I post a PTY Office Shop Ad and Chotot refused my Ad")
    public void i_should_ssee_mys_new_ad_displayed_ons_Filter_actsive_Office() {
        insertOfficeSellShop(global_accessToken, "refuse", false, false);
    }

    @Then("I post a PTY Office Shop Ad and Chotot accepted my Ad")
    public void i_should_sssee_my_nsew_ad_displayeds_on_Filter_actsive_Office() {
        insertOfficeSellShop(global_accessToken, "accept", false, false);
    }

    @And("I post a PTY Office Shop Ad To Chotot and Chotot is reviewing my Ad")
    public void i_should_ssee_my_new_ad_displayed_ossns_Filter_active_Office() {
        insertOfficeSellShop(global_accessToken, "no action", true, false);
    }

    @Then("I post a PTY Office Shop Ad To Chotot and Chotot refused my Ad")
    public void i_should_ssee_mys_nsew_ad_displayed_ons_Filter_actsive_Office() {
        insertOfficeSellShop(global_accessToken, "refuse", true, false);
    }

    @Then("I post a PTY Office Shop Ad To Chotot and Chotot accepted my Ad")
    public void i_shoulsd_sssee_my_nsew_ad_displayeds_on_Filter_actsive_Office() {
        insertOfficeSellShop(global_accessToken, "accept", true, false);
    }


    @When("I post a PTY Office Rent Ad and Chotot is reviewing my Ad")
    public void i_should_sssee_my_new_ad_displayed_on_Filter_active_Office() {
        insertOfficeRentPrivate(global_accessToken, "no action", false);
    }

    @Then("I post a PTY Office Rent Ad and Chotot refused my Ad")
    public void i_should_ssees_my_new_ad_displayed_on_Filter_actsive_Office() {
        insertOfficeRentPrivate(global_accessToken, "refuse", false);
    }

    @Then("I post a PTY Office Rent Ad and Chotot accepted my Ad")
    public void i_should_ssee_smy_nsew_ad_displayed_on_Filter_actsive_Office() {
        insertOfficeRentPrivate(global_accessToken, "accept", false);
    }

    @And("I post a PTY Office Pro Rent Ad and Chotot is reviewing my Ad")
    public void i_should_sssee_my_new_ad_displayed_osn_Filter_active_Office() {
        insertOfficeRentPro(global_accessToken, "no action", false);
    }

    @Then("I post a PTY Office Pro Rent Ad and Chotot refused my Ad")
    public void i_shoulds_ssee_mys_new_ad_displayed_on_Filter_actsive_Office() {
        insertOfficeRentPro(global_accessToken, "refuse", false);
    }

    @Then("I post a PTY Office Pro Rent Ad and Chotot accepted my Ad")
    public void i_should_ssssee_my_nsew_ad_displayed_on_Filter_actsive_Office() {
        insertOfficeRentPro(global_accessToken, "accept", false);
    }

    @And("I post a PTY Office Shop Rent Ad and Chotot is reviewing my Ad")
    public void i_shsould_ssee_my_new_ad_displayed_ossn_Filter_active_Office() {
        insertOfficeRentShop(global_accessToken, "no action", false, false);
    }

    @Then("I post a PTY Office Shop Rent Ad and Chotot refused my Ad")
    public void i_sshould_ssee_mys_new_ad_displayed_ons_Filter_actsive_Office() {
        insertOfficeRentShop(global_accessToken, "refuse", false, false);
    }

    @Then("I post a PTY Office Shop Rent Ad and Chotot accepted my Ad")
    public void i_sshould_sssee_my_nsew_ad_displayeds_on_Filter_actsive_Office() {
        insertOfficeRentShop(global_accessToken, "accept", false, false);
    }

    @And("I post a PTY Office Shop Rent Ad To Chotot and Chotot is reviewing my Ad")
    public void i_should_ssees_my_new_ad_displaysed_ossnss_Filter_active() {
        insertOfficeRentShop(global_accessToken, "no action", true, false);
    }

    @Then("I post a PTY Office Shop Rent Ad To Chotot and Chotot refused my Ad")
    public void i_should_ssee_mys_nsew_ads_displayed_ons_Filtser_actsive_Office() {
        insertOfficeRentShop(global_accessToken, "refuse", true, false);
    }

    @Then("I post a PTY Office Shop Rent Ad To Chotot and Chotot accepted my Ad")
    public void i_shoulsd_sssee_my_nsew_asd_displayeds_on_Filter_actssive_Office() {
        insertOfficeRentShop(global_accessToken, "accept", true, false);
    }


    //-------------------- PTY Room -------------------------
    @When("I post a PTY Room Rent Ad and Chotot is reviewing my Ad")
    public void i_should_sssee_my_new_ad_displayed_on_Filter_active_RoomRent() {
        insertRoomRentPrivate(global_accessToken, "no action", false);
    }

    @Then("I post a PTY Room Rent Ad and Chotot refused my Ad")
    public void i_should_ssees_my_new_ad_displayed_on_Filter_actsive_RoomRent() {
        insertRoomRentPrivate(global_accessToken, "refuse", false);
    }

    @Then("I post a PTY Room Rent Ad and Chotot accepted my Ad")
    public void i_should_ssee_smy_nsew_ad_displayed_on_Filter_actsive_RoomRent() {
        insertRoomRentPrivate(global_accessToken, "accept", false);
    }

    @And("I post a PTY Room Pro Rent Ad and Chotot is reviewing my Ad")
    public void i_should_sssee_my_new_ad_displayed_osn_Filter_active_RoomRent() {
        insertRoomRentPro(global_accessToken, "no action", false);
    }

    @Then("I post a PTY Room Pro Rent Ad and Chotot refused my Ad")
    public void i_shoulds_ssee_mys_new_ad_displayed_on_Filter_actsive_RoomRent() {
        insertRoomRentPro(global_accessToken, "refuse", false);
    }

    @Then("I post a PTY Room Pro Rent Ad and Chotot accepted my Ad")
    public void i_should_ssssee_my_nsew_ad_displayed_on_Filter_actsive_RoomRent() {
        insertRoomRentPro(global_accessToken, "accept", false);
    }

    @Then("I post {string} PTY Room Shop Rent Ad To Chotot and Chotot accepted my Ad")
    public void i_should_sssssee_my_nsew_ad_displayed_on_Filter_actsive_RoomRent(String numberOfAd) {
        for (int i = 0; i < Integer.parseInt(numberOfAd); i++) {
            insertRoomRentShop(global_accessToken, "accept", true, false);
        }
    }

    @And("I post a PTY Room Shop Rent Ad and Chotot is reviewing my Ad")
    public void i_shsould_ssee_my_new_ad_displayed_ossn_Filter_active_RoomRent() {
        insertRoomRentShop(global_accessToken, "no action", false, false);
    }

    @Then("I post a PTY Room Shop Rent Ad and Chotot refused my Ad")
    public void i_sshould_ssee_mys_new_ad_displayed_ons_Filter_actsive_RoomRent() {
        insertRoomRentShop(global_accessToken, "refuse", false, false);
    }

    @Then("I post a PTY Room Shop Rent Ad and Chotot accepted my Ad")
    public void i_sshould_sssee_my_nsew_ad_displayeds_on_Filter_actsive_RoomRent() {
        insertRoomRentShop(global_accessToken, "accept", false, false);
    }

    @And("I post a PTY Room Shop Rent Ad To Chotot and Chotot is reviewing my Ad")
    public void i_should_sseess_my_new_ad_displaysed_ossns_Filter_active() {
        insertRoomRentShop(global_accessToken, "no action", true, false);
    }

    @Then("I post a PTY Room Shop Rent Ad To Chotot and Chotot refused my Ad")
    public void i_should_ssee_mys_nsew_ads_displayed_ons_Filtser_actsive_RoomRent() {
        insertRoomRentShop(global_accessToken, "refuse", true, false);
    }

    @Then("I post a PTY Room Shop Rent Ad To Chotot and Chotot accepted my Ad")
    public void i_shoulsd_sssee_my_nsew_asd_displayeds_on_Filter_actssive_RoomRent() {
        insertRoomRentShop(global_accessToken, "accept", true, false);
    }


    //SUPPORT FOR NATIONWIDE
    @When("I post a PTY {string} Ad and Chotot {string} my Ad - region {string}")
    public void i_post_a_PTY_House_Ad_and_Chotot_is_reviewing_my_Ad_region_hcm(String category, String actionStr, String region) {
        String action = null;
        switch (actionStr.toLowerCase().trim()) {
            case "is reviewing":
                action = "no action";
                break;
            case "accepted":
                action = "accepted";
                break;
            case "refused":
                action = "refused";
                break;
            case "refused after i pay":
                action = "refusepay";
                break;
        }
        Assert.assertNotNull(action, "Action [" + actionStr + "] is invalid");


        switch (region.toLowerCase().trim()) {
            case "binh duong":
            case "bình dương":
                setRegion_BinhDuong();
                break;
            case "hn":
            case "ha noi":
            case "hà nội":
                setRegion_HaNoi();
                break;
            case "ha tinh":
            case "hà tĩnh":
                setRegion_HaTinh();
                break;
            case "bac can":
            case "bắc cạn":
                setRegion_BacCan();
                break;
            case "lai chau":
            case "lai châu":
                setRegion_LaiChau();
                break;

            case "ho chi minh":
            case "hcm":
            case "hồ chí minh":
            default:
                setRegion_HCM();
                break;
        }

        switch (category.toLowerCase()) {
            case "house":
                insertHouseSellPrivate(global_accessToken, action, false);
                break;
            case "apartment":
                insertApartmentSellPrivate(global_accessToken, action, false);
                break;
            case "land":
                insertLandSellPrivate(global_accessToken, action, false);
                break;
            case "office":
                insertOfficeSellPrivate(global_accessToken, action, false);
                break;
            case "room":
                insertRoomRentPrivate(global_accessToken, action, false);
                break;
        }
    }


    @When("I edit a PTY {string} Ad and Chotot {string} my Ad - region {string}")
    public void i_edit_a_PTY_House_Ad_and_Chotot_is_reviewing_my_Ad_region_hcm(String category, String actionStr, String region) {
        String action = null;
        switch (actionStr.toLowerCase().trim()) {
            case "is reviewing":
                action = "no action";
                break;
            case "accepted":
                action = "accepted";
                break;
            case "refused":
                action = "refused";
                break;
        }
        Assert.assertNotNull(action, "Action [" + actionStr + "] is invalid");


        switch (region.toLowerCase().trim()) {
            case "binh duong":
            case "bình dương":
                setRegion_BinhDuong();
                break;
            case "hn":
            case "ha noi":
            case "hà nội":
                setRegion_HaNoi();
                break;
            case "ha tinh":
            case "hà tĩnh":
                setRegion_HaTinh();
                break;
            case "bac can":
            case "bắc cạn":
                setRegion_BacCan();
                break;
            case "lai chau":
            case "lai châu":
                setRegion_LaiChau();
                break;

            case "ho chi minh":
            case "hcm":
            case "hồ chí minh":
            default:
                setRegion_HCM();
                break;
        }

        setSubject(getSubject() + " EDIT");

        switch (category.toLowerCase()) {
            case "house":
                editHouseSellPrivate(global_accessToken, tempAdID, action, false);
                break;
            case "apartment":
                editApartmentSellPrivate(global_accessToken, tempAdID, action, false);
                break;
            case "land":
                editLandSellPrivate(global_accessToken, tempAdID, action, false);
                break;
            case "office":
                editOfficeSellPrivate(global_accessToken, tempAdID, action, false);
                break;
            case "room":
                editRoomRentPrivate(global_accessToken, tempAdID, action, false);
                break;
        }
    }

    //I edit my PTY "House" Sell Shop Ad To Chotot and Chotot "accepted" my Ad"
    @When("I edit my PTY {string} Sell Shop Ad To Chotot and Chotot {string} my Ad")
    public void i_edit_a_PTY_House_Ad_and_Chotot_is_reviewing_mys_Ad_region_hcm(String category, String actionStr) {
        editStepGlobalSell(category, actionStr, true);
    }

    //I edit my PTY "House" Rent Shop Ad To Chotot and Chotot "accepted" my Ad"
    @When("I edit my PTY {string} Rent Shop Ad To Chotot and Chotot {string} my Ad")
    public void i_edit_a_PTY_House_Asd_and_Chotot_is_reviewing_mys_Ad_region_hcm(String category, String actionStr) {
        editStepGlobalRent(category, actionStr, true);
    }

    //I edit my PTY "House" Sell Shop Ad and Chotot "accepted" my Ad"
    @When("I edit my PTY {string} Sell Shop Ad and Chotot {string} my Ad")
    public void i_edit_a_PTY_House_Ad_and_Chotot_is_reviewing_mys_Ad_regison_hcm(String category, String actionStr) {
        editStepGlobalSell(category, actionStr, false);
    }

    //I edit my PTY "House" Rent Shop Ad To Chotot and Chotot "accepted" my Ad"
    @When("I edit my PTY {string} Rent Shop Ad and Chotot {string} my Ad")
    public void i_edit_a_PTY_House_Asd_and_Chsotot_is_reviewing_mys_Ad_region_hcm(String category, String actionStr) {
        editStepGlobalRent(category, actionStr, false);
    }

    private void editStepGlobalRent(String category, String actionStr, boolean adToChotot) {
        String action = null;
        switch (actionStr.toLowerCase().trim()) {
            case "accepted":
                action = "accepted";
                break;
            case "refused":
                action = "refused";
                break;
            case "is reviewing":
            default:
                action = "no action";
                break;
        }
        Assert.assertNotNull(action, "Action [" + actionStr + "] is invalid");
        setSubject(getSubject() + " EDIT");


        switch (category.toLowerCase().trim()) {
            case "house":
                editHouseRentShop(global_accessToken, tempAdID, action, adToChotot, false);
                break;
            case "apartment":
                editApartmentRentShop(global_accessToken, tempAdID, action, adToChotot, false);
                break;
            case "land":
                editLandRentShop(global_accessToken, tempAdID, action, adToChotot, false);
                break;
            case "office":
                editOfficeRentShop(global_accessToken, tempAdID, action, adToChotot, false);
                break;
            case "room":
                editRoomRentShop(global_accessToken, tempAdID, action, adToChotot, false);
                break;
            default:
                Assert.assertTrue(false, "Doesn't exist this category");
                break;
        }
    }

    private void editStepGlobalSell(String category, String actionStr, boolean adToChotot) {
        String action = null;
        switch (actionStr.toLowerCase().trim()) {
            case "accepted":
                action = "accepted";
                break;
            case "refused":
                action = "refused";
                break;
            case "is reviewing":
            default:
                action = "no action";
                break;
        }
        Assert.assertNotNull(action, "Action [" + actionStr + "] is invalid");
        setSubject(getSubject() + " EDIT");


        switch (category.toLowerCase()) {
            case "house":
                editHouseSellShop(global_accessToken, tempAdID, action, adToChotot, false);
                break;
            case "apartment":
                editApartmentSellShop(global_accessToken, tempAdID, action, adToChotot, false);
                break;
            case "land":
                editLandSellShop(global_accessToken, tempAdID, action, adToChotot, false);
                break;
            case "office":
                editOfficeSellShop(global_accessToken, tempAdID, action, adToChotot, false);
                break;
            default:
                Assert.assertTrue(false, "Doesn't exist this category");
                break;
        }
    }
}
