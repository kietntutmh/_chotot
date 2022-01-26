package projects.steps.chotot.api.ads.listing;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import projects.functions.chotot.ads.listing.AdListing_API_Functions;

import java.util.ArrayList;
import java.util.List;

import static projects.configuaration.CategoryConfig.CATEID_VEH_CAR;
import static projects.functions.chotot.CommonFunctions.postAndPayCarAds;

public class AdListing_API_Steps extends AdListing_API_Functions {
    private static List<String> adIdList = new ArrayList<>();

    //Sticky Slot
    @Given("Chotot sets up number of Sticky slots for Ad Listing")
    public void chotot_sets_up_number_of_Sticky_slots_for_Ad_Listing() {
        connectToStickyAdDB();
        getStickSlotConf();
    }

    @When("On Listing Page, I filter ads with region {string}")
    public void i_filter_ads_with_region(String region) {
        setRegionID(region);
    }

    @Then("I post {string} Sell Car Ads and pay POS Tin Ưu Tiên for them")
    public void number_of_sticky_Ads_on_the_top_of_Ad_Listing_shousld_be_corrsssect(String numberOfAd) {
        setCategoryId(CATEID_VEH_CAR);
        setAdTypeSell();
        postAndPayCarAds(Integer.parseInt(numberOfAd), true);
    }

    @Then("The number of Sticky Slots Page {string} should be equal as the available Sticky Ad Slots")
    public void the_number_of_Sticky_Slots_Page_should_be_equal_as_the_available_Sticky_Ad_Slots(String pageNum) {
        verifyStickyAdSlot(Integer.parseInt(pageNum));
    }

    @Given("Ad Limitation per page is {string}")
    public void ad_Limitation_per_page_is(String limitPage) {
        setLimitAdPerPage(Integer.parseInt(limitPage));
    }

    private static String urlNotAnyAd = "";
    @When("I filter Category which doesn't exist any Ad")
    public void i_filter_Category_which_doesn_t_exist_any_Ad() {
        urlNotAnyAd = "https://gateway.chotot.org/v2/public/sticky-ads?region_id=13000&area_id=0&category_id=2010&key_word=&limit=20&offset=0&carbrand=3&carmodel=122&o=0&tagging_key=b&cg=2010&region_v2=13000&st=s,k";
    }

    @Then("The number of Sticky Slots Page {string} should be {string}")
    public void the_number_of_sSticky_Slots_Page_should_be(String pageNum, String adNum) {
        verifyStickyAdSlot(urlNotAnyAd, Integer.parseInt(pageNum), Integer.parseInt(adNum));
    }
}
