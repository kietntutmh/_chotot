package projects.steps.chotot.api.flashad;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import projects.functions.chotot.flashad.FlashAd_Core_Functions;
import projects.functions.chotot.flashad.FlashAd_PET_Functions;

import static api.configuration.GatewayConfig.global_accessToken;


public class FlashAd_PET_Steps extends FlashAd_PET_Functions {

    // ------------ DOG ------------ //

    @Then("I post a PET Dog Private Ad and Chotot is reviewing my Ad")
    public void i_post_a_PET_Dog_Private_Ad_and_Chotot_is_reviewing_my_Ad() {
        insertDogSellPrivate(global_accessToken, "no action", false);
    }
    @Then("I post a PET Dog Private Ad and Chotot accepted my Ad")
    public void i_post_a_PET_Dog_Private_Ad_and_Chotot_accepted_my_Ad() {
        insertDogSellPrivate(global_accessToken, "accept", false);
    }
    @Then("I post a PET Dog Private Ad and Chotot refused my Ad")
    public void i_post_a_PET_Dog_Private_Ad_and_Chotot_refused_my_Ad() {
        insertDogSellPrivate(global_accessToken, "refuse", false);
    }
    @Then("I post a PET Dog Pro Ad and Chotot is reviewing my Ad")
    public void i_post_a_PET_Dog_Pro_Ad_and_Chotot_is_reviewing_my_Ad() {
        insertDogSellPro(global_accessToken, "no action", false);
    }
    @Then("I post a PET Dog Pro Ad and Chotot accepted my Ad")
    public void i_post_a_PET_Dog_Pro_Ad_and_Chotot_accepted_my_Ad() {
        insertDogSellPro(global_accessToken, "accept", false);
    }
    @Then("I post a PET Dog Pro Ad and Chotot refused my Ad")
    public void i_post_a_PET_Dog_Pro_Ad_and_Chotot_refused_my_Ad() {
        insertDogSellPro(global_accessToken, "refuse", false);
    }
}
//package projects.steps.chotot.api.flashad;
//
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import org.testng.Assert;
//
//import static api.configuration.DataConfig.tempAdID;
//import static api.configuration.GatewayConfig.global_accessToken;
//
//public class FlashAd_PET_Steps extends FlashAd_PET_Functions {
//
//}
