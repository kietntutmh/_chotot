package projects.steps._common;

import desktop.components.Popup;
import desktop.configuration.LoginConfig;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Assume;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import projects.functions.TestBDDFunctions;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static api.feature.ads.DashboardAds.getPendingUnPaidOtherAdsShop;
import static api.utils.CheckAccessToken.setAccessToken;
import static api.utils.GetAccessToken.getAccessTokenOfNewUser;
import static api.utils.GetAccessToken.getAccessTokenOfUser;
import static com.vn.chotot.configuration.WaitTime.maxWaitTime;
import static com.vn.chotot.configuration.WaitTime.minWaitTime;
import static com.vn.chotot.keywords.selenium.Action.clickJS;
import static com.vn.chotot.keywords.selenium.Action.setText;
import static com.vn.chotot.keywords.selenium.Wait.waitForElementStaleness;
import static projects.functions.chotot.payment.PayOrder_API_Functions.getBalanceDT;

@Deprecated
public class CommonSteps extends TestBDDFunctions {
    public static String tempValueString = "";

    // Condition Steps
    @Given("I am on {string} website")
    public void i_am_on_PROJ_website(String projName) {
        setProjectName(projName);
    }

    @Given("I register a new Chotot User")
    public void i_register_a_new_Chotot_user() {
        setAccessToken();
    }

    @Given("I login as new Chotot User")
    public void i_login_as_new_Chotot_user() {
        setAccessToken();
        // Assign pending_review, unpaid and other values to tempValueString
        tempValueString = getPendingUnPaidOtherAdsShop();
    }


    //Belong to Framework: common steps
    @When("I login with username {string} and password {string}")
    public void i_login_with_username_and_password(String username, String password) {
        WebElement btn_Login = getWebElementBDD("loginbtn");
        setText(getWebElementBDD("username"), username);
        setText(getWebElementBDD("password"), password);
        clickJS(btn_Login);
        waitForElementStaleness(btn_Login, maxWaitTime * 2);

        if (getProjectName().toUpperCase().equals("CHOTOT"))
            new Popup().closePopup(minWaitTime);
    }

    @Deprecated
    @Then("I should see the username and password tool-tip field requires")
    public void i_should_see_the_username_and_password_field_requires() {
        WebElement eleUsername = getWebElementBDD("username");
        WebElement elePassword = getWebElementBDD("password");
        boolean isRequiredPhone = Boolean.parseBoolean(eleUsername.getAttribute("required"));
        boolean isRequiredPassword = Boolean.parseBoolean(elePassword.getAttribute("required"));
        String validationMsgPhone = eleUsername.getAttribute("validationMessage");
        String validationMsgPassword = elePassword.getAttribute("validationMessage");
        if (!isRequiredPhone
                || !isRequiredPassword
                || !validationMsgPhone.equals("Please fill out this field.")
                || !validationMsgPassword.equals("Please fill out this field."))
            throw new IllegalArgumentException(LoginConfig.requiredMessage);
    }

    @Then("I should see the {string} tool-tip field require displays as {string}")
    public void i_should_see_the_tool_tip_field_require_displays_as(String eName, String validationMsg) {
        WebElement eleUsername = getWebElementBDD(eName);
        boolean isRequiredField = Boolean.parseBoolean(eleUsername.getAttribute("required"));
        String validationMsgActual = eleUsername.getAttribute("validationMessage");
        if (!isRequiredField || !validationMsgActual.equals(validationMsg))
            throw new IllegalArgumentException(LoginConfig.requiredMessage);
    }

    @Given("I demo code PASS")
    public void idemo_pass() {
        System.out.println("PASSED DEMO");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Given("I demo code SKIP")
    public void idemo_skip() {
        System.out.println("SKIP DEMO");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assume.assumeTrue("FAILED STEP", false);
    }

    @Given("I demo code")
    public void i_demo_code_method() {
//        getAccessTokenOfUser("0351728390", "123456");
        Assert.assertTrue(false);
    }
}
