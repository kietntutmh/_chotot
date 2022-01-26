package projects.steps.chotot.api.listingFee;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import projects.functions.chotot.listingFee.ListingFee_API_Functions;

import static com.vn.chotot.configuration.WaitTime.minWaitTime;
import static com.vn.chotot.keywords.selenium.Utils.delayStep;

public class ListingFee_API_Steps extends ListingFee_API_Functions {
    @Given("I should see my New Ad displayed on Listing Fee Tab KHÁC in filter ĐỢI DUYỆT")
    public void i_should_see_my_new_ad_displayed_on_Other_filtered(){
        checkAdOnListingFee("KHÁC");
        checkAdOnListingFee("ĐỢI DUYỆT");
    }

    @Given("I should see my New Ad displayed on Listing Fee Tab KHÁC")
    public void i_should_see_my_new_ad_displayed_on_Other(){
        checkAdOnListingFee("KHÁC");
    }

    @Given("I should see my New Ad displayed on Listing Fee Tab BỊ TỪ CHỐI")
    public void i_should_see_my_new_ad_displayed_on_refused(){
        delayStep(minWaitTime); // delay to wait for server more stability
        checkAdOnListingFee("BỊ TỪ CHỐI");
    }

    @Given("I should see my New Ad displayed on Listing Fee filter ĐỢI DUYỆT")
    public void i_should_see_my_new_ad_displayed_on_Filter(){
        delayStep(minWaitTime); // delay to wait for server more stability
        checkAdOnListingFee("ĐỢI DUYỆT");
    }

    @Given("I should see my New Ad displayed on Listing Fee Tab CẦN THANH TOÁN")
    public void i_should_see_my_new_ad_displayed_on_pay(){
        delayStep(minWaitTime); // delay to wait for server more stability
        checkAdOnListingFee("CẦN THANH TOÁN");
    }

    @Given("I should see my New Ad displayed on Listing Fee Tab ĐANG BÁN")
    public void i_should_see_my_new_ad_displayed_on_selling(){
        checkAdOnListingFee("ĐANG BÁN");
    }

    @Given("I should see my New Ad displayed on Listing Fee Tab {string}")
    public void i_should_see_my_new_ad_displayed_on_Listing_Fee(String tabName){
        checkAdOnListingFee(tabName);
    }

    @When("My new Second Ad should be free")
    @Then("My new Ad should be free")
    public void my_new_Ad_should_be_free() {
        checkAdIsFree();
    }

    @Then("My new Ad should be charge")
    @When("My new Third Ad should be charge")
    public void my_new_Ad_should_be_charge() {
        checkAdIsCharge();
    }

    @When("My new Ad should be a Pro ad")
    public void my_new_Ad_should_be_proad() {
        checkAdIsProAd();
    }

    @When("My new Ad should be a Private ad")
    public void my_new_Ad_should_be_privatead() {
        checkAdIsPrivateAd();
    }

    @Then("My new Ad should be paid successfully")
    public void my_new_Ad_should_be_paid_successfully() {
        checkAdIsPaid();
    }
}
