package projects.steps.chotot.api.promotion;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import projects.functions.chotot.promotion.Promotion_Announce_Credit_API_Functions;

import static api.configuration.DataConfig.newUserID;
import static api.configuration.DataConfig.newUserPhone;
import static api.utils.GetAccessToken.getAccessTokenOfNewUser;

public class Promotion_Announce_Credit_API_Steps extends Promotion_Announce_Credit_API_Functions {
    @Given("I login my account to get Announce Promotion")
    public void i_login_announce(){
        setUserToken(getAccessTokenOfNewUser());
        setUserAccountID(newUserID);
        setUserPhone(newUserPhone);
    }

    @Given("I get an announcement from Chotot")
    @Given("I should get an announcement from Chotot")
    public void i_get_an_announcement_from_Chotot(DataTable table) {
//        setAPName(dataTable.row(1).get(0));
//        setAPTitle(dataTable.row(1).get(0));
//        announceTitle = dataTable.row(1).get(1);
//        announceContent = dataTable.row(1).get(2);
//        announceShopPromotion(getUserAccountID(), announceName, announceTitle, announceContent);
    }

    @When("Chotot creates AP Đồng Tốt Campaign")
    public void chotot_creates_Announce_Promotion_Campaign(DataTable table) {
        setAPCredit(Integer.parseInt(table.row(1).get(0)));
        setAPLimitTotal(Integer.parseInt(table.row(1).get(1)));
        setAPLimitPerAccount(Integer.parseInt(table.row(1).get(2)));
        createAPCampaign(getAPLimitTotal(), getAPLimitPerAccount(), "credit");
        addAPCredit(getAPCredit(), 0);
//        generateAPCode(getUserPhone());
    }

    @When("Chotot assigns Announce Promotion to me")
    public void chotot_assigns_AP_to_me() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("I should have {string} Đồng Tốt from AP")
    public void i_should_have_dt(String dongTot) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
