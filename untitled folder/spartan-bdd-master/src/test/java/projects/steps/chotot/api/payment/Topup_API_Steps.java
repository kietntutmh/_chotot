package projects.steps.chotot.api.payment;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import projects.functions.chotot.payment.TopupDongTot_API_Functions;

import static api.configuration.GatewayConfig.global_accessToken;
import static desktop.configuration.LoginConfig.tempUserPhone;

public class Topup_API_Steps extends TopupDongTot_API_Functions {

    @Given("I have {string} Đồng Tốt")
    public void i_have__DT(String dongTotAmount) {
        switch (dongTotAmount){
            case "100k":
            case "100000":
                topupDongTotWithMomo_100k(global_accessToken);
                break;
            case "200k":
            case "200000":
                topupDongTotWithMomo_200k(global_accessToken);
                break;
            case "500k":
            case "500000":
                topupDongTotWithMomo_500k(global_accessToken);
                break;
            case "1000k":
            case "1000000":
                topupDongTotWithMomo_1000k(global_accessToken);
                break;
            case "2000k":
            case "2000000":
                topupDongTotWithMomo_2000k(global_accessToken);
                break;
            case "3000k":
            case "3000000":
                topupDongTotWithMomo_3000k(global_accessToken);
                break;
            default:
                Assert.assertTrue(false, "ĐỒNG TỐT AMOUNT IS INVALID. IT SHOULD BE 100k, 200k, 500k, 1000k, 2000k, 3000k");
                break;
        }
    }

    @Given("I have 100k Đồng Tốt")
    @When("I topup 100k Đồng Tốt")
    public void i_have_100k_DT() {
        topupDongTotWithMomo_100k(tempUserPhone);
    }

    @Given("I have 200k Đồng Tốt")
    public void i_have_200k_DT() {
        topupDongTotWithMomo_200k(tempUserPhone);
    }

    @Given("I have 500k Đồng Tốt")
    @When("I topup 500k Đồng Tốt")
    public void i_have_500k_DT() {
        topupDongTotWithMomo_500k(tempUserPhone);
    }

    @Given("I have 1000k Đồng Tốt")
    public void i_have_1000k_DT() {
        topupDongTotWithMomo_1000k(tempUserPhone);
    }

    @Given("I have 2000k Đồng Tốt")
    public void i_have_2000k_DT() {
        topupDongTotWithMomo_2000k(tempUserPhone);
    }

    @Given("I have 3000k Đồng Tốt")
    @When("I topup 3000k Đồng Tốt")
    public void i_have_3000k_DT() {
        topupDongTotWithMomo_3000k(tempUserPhone);
    }


    @Given("My Đồng Tốt 365 is expired")
    public void my_Đồng_Tốt_is_expired() {
        throw new io.cucumber.java.PendingException();
    }

    @Given("My Đồng Tốt Free is expired")
    public void my_Đồng_Tốt_free_is_expired() {
        throw new io.cucumber.java.PendingException();
    }

    @Given("My Đồng Tốt Expiry is expired")
    public void my_Đồng_Tốt_expiry_is_expired() {
        throw new io.cucumber.java.PendingException();
    }
}
