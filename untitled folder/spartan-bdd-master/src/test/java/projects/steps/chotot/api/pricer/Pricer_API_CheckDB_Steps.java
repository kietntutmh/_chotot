package projects.steps.chotot.api.pricer;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import projects.functions.chotot.pricer.Pricer_API_CheckPrice_Functions;

public class Pricer_API_CheckDB_Steps extends Pricer_API_CheckPrice_Functions {
    @Given("Get data")
    public void getdata(){
        getStaticDataToTest();
    }

    @Then("Bump API Get-All should load same data with database")
    public void bump_API_VND_should_load_same_data_with_database() {
        verifyApiDb_Bump_GetAll();
    }

    @Then("Ad Feature API Get-All should load same data with database")
    public void busmp_API_VNDs_shousld_load_same_data_with_database() {
        verifyApiDb_AdFeature_GetAll();
    }

    @Then("Check all Pricer APIs with static category {string}")
    public void check_all_Pricer_APIs_with_static_category(String cate) {
        verifyApiDb_allAPIs(cate);
    }

}
