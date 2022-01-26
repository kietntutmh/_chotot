package projects.steps.chotot.api;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import projects.functions.chotot.CommonFunctions;

import static api.configuration.DataConfig.defaultPassword;
import static api.configuration.DataConfig.newUserPhone;
import static api.configuration.GatewayConfig.global_accessToken;
import static api.utils.GetAccessToken.getAccessTokenOfNewUser;
import static api.utils.GetAccessToken.getAccessTokenOfUser;
import static projects.functions.chotot.payment.TopupDongTot_API_Functions.*;

//Author: Vu Hoang
public class Common_API_Steps extends CommonFunctions {
    @Given("I login my Account")
    @Given("I login my Account as a Seller")
    @Given("I login my Account as a Buyer")
    public void login_private_() {
        getAccessTokenOfNewUser();
    }

    @Given("I login my account {string}")
    @Given("I login my Account {string}")
    public void i_login_my_account(String phone) {
        getAccessTokenOfUser(phone, defaultPassword);
    }

    @Given("I login my account {string} with password {string}")
    public void i_login_my_account(String phone, String password) {
        getAccessTokenOfUser(phone, password);
    }

    @When("I topup 100k Dong Tot to my account {string}")
    public void i_topup_DT4B_to_the_account(String phone) {
        if (phone.isEmpty()) {
            if (global_accessToken.isEmpty()) {
                getAccessTokenOfUser(newUserPhone, defaultPassword);
            }
            topupDongTotWithMomo(newUserPhone, 100000);
        } else {
            getAccessTokenOfUser(phone, defaultPassword);
            topupDongTotWithMomo(phone, 100000);
        }
    }

    @When("I topup 200k Dong Tot to my account {string}")
    public void i_topup_2DT4B_to_the_account(String phone) {
        if (phone.isEmpty()) {
            if (global_accessToken.isEmpty()) {
                getAccessTokenOfUser(newUserPhone, defaultPassword);
            }
            topupDongTotWithMomo(newUserPhone, 200000);
        } else {
            getAccessTokenOfUser(phone, defaultPassword);
            topupDongTotWithMomo(phone, 200000);
        }
    }

    @When("I topup 500k Dong Tot to my account {string}")
    public void i_topusp_2DT4B_to_the_account(String phone) {
        if (phone.isEmpty()) {
            if (global_accessToken.isEmpty()) {
                getAccessTokenOfUser(newUserPhone, defaultPassword);
            }
            topupDongTotWithMomo(newUserPhone, 500000);
        } else {
            getAccessTokenOfUser(phone, defaultPassword);
            topupDongTotWithMomo(phone, 500000);
        }
    }

    @When("I topup 1000k Dong Tot to my account {string}")
    public void i_topusp_2DTs4B_to_the_account(String phone) {
        if (phone.isEmpty()) {
            if (global_accessToken.isEmpty()) {
                getAccessTokenOfUser(newUserPhone, defaultPassword);
            }
            topupDongTotWithMomo(newUserPhone, 1000000);
        } else {
            getAccessTokenOfUser(phone, defaultPassword);
            topupDongTotWithMomo(phone, 1000000);
        }
    }

    @When("I topup 2000k Dong Tot to my account {string}")
    public void i_topsusp_2DTs4B_to_the_account(String phone) {
        if (phone.isEmpty()) {
            if (global_accessToken.isEmpty()) {
                getAccessTokenOfUser(newUserPhone, defaultPassword);
            }
            topupDongTotWithMomo(newUserPhone, 2000000);
        } else {
            getAccessTokenOfUser(phone, defaultPassword);
            topupDongTotWithMomo(phone, 2000000);
        }
    }

    @When("I topup 3000k Dong Tot to my account {string}")
    public void i_topusps_2DTs4B_to_the_account(String phone) {
        if (phone.isEmpty()) {
            if (global_accessToken.isEmpty())
                getAccessTokenOfUser(newUserPhone, defaultPassword);
            topupDongTotWithMomo(newUserPhone, 3000000);
        } else {
            getAccessTokenOfUser(phone, defaultPassword);
            topupDongTotWithMomo(phone, 3000000);
        }
    }

    @When("I topup 20k Dong Tot to my account {string}")
    public void i_topup_ss2DT4B_to_the_account(String phone) {
        if (phone.isEmpty()) {
            if (global_accessToken.isEmpty()) {
                getAccessTokenOfUser(newUserPhone, defaultPassword);
            }
            topupDongTotWithMomo(newUserPhone, 20000);
        } else {
            getAccessTokenOfUser(phone, defaultPassword);
            topupDongTotWithMomo(phone, 20000);
        }
    }

    @When("I topup 50k Dong Tot to my account {string}")
    public void i_topup_2DT4B_tos_the_account(String phone) {
        if (phone.isEmpty()) {
            if (global_accessToken.isEmpty()) {
                getAccessTokenOfUser(newUserPhone, defaultPassword);
            }
            topupDongTotWithMomo(newUserPhone, 50000);
        } else {
            getAccessTokenOfUser(phone, defaultPassword);
            topupDongTotWithMomo(phone, 50000);
        }
    }

    @When("I topup 500k Dong Tot")
    public void i_topup_2DT4sB_to_the_account() {
        topupDongTotWithMomo_500k(newUserPhone);
    }

    @When("I topup 200k Dong Tot")
    public void i_topup_22DT4sB_to_the_account() {
        topupDongTotWithMomo_200k(newUserPhone);
    }

    @When("I topup 100k Dong Tot")
    public void i_topup_2DT24sB_to_the_account() {
        topupDongTotWithMomo_100k(newUserPhone);
    }

    @When("I topup 20k Dong Tot")
    public void i_topup_2DTs24sB_to_the_account() {
        topupDongTotWithMomo_20k(newUserPhone);
    }

    @When("I topup 50k Dong Tot")
    public void i_topup_2DTs2s4sB_to_the_account() {
        topupDongTotWithMomo_50k(newUserPhone);
    }


    @Given("Check Kafka Server")
    public void check_Kafka_Server() {
        response = get("https://gateway.chotot.org/v1/internal/user_ads/10210836/list_ads/214605?is_latest=true");
//        https://gateway.chotot.org/v1/internal/pricer/pricing/single?account_id=10211313&bump_type=bump&category_id=6020
    }

    @Given("I create data test")
    public void checssk_Kafka_Server() {

    }


}
