package projects.steps._common;

import com.vn.chotot.exception.FailureHandling;
import desktop.configuration.LoginConfig;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;
import projects.functions.TestBDDFunctions;

import static com.vn.chotot.configuration.Constant.DOMAIN;
import static com.vn.chotot.configuration.WaitTime.maxWaitTime;
import static com.vn.chotot.configuration.WaitTime.minWaitTime;
import static com.vn.chotot.keywords.selenium.Action.getAttributeValue;
import static com.vn.chotot.keywords.selenium.Element.verifyElementText;
import static com.vn.chotot.keywords.selenium.Element.verifyElementVisible;
import static com.vn.chotot.keywords.selenium.Page.openURL;
import static com.vn.chotot.keywords.selenium.Utils.verifyMatch;

@Deprecated
public class VerifySteps extends TestBDDFunctions {
    //Verify Page
    @Given("I am on the web page {string}")
    public void i_am_on_the_web_page(String pageName) {
        setPageObjNameBDD(pageName);
        String url = getElementObjBDD("url");
        if(url.contains("%s"))
            url = String.format(url, DOMAIN);
        openURL(url);
    }

    @Then("I should be on the web page {string}")
    public void i_should_be_on_the_web_page(String pageName) {
        setPageObjNameBDD(pageName);
    }

    @Then("I should see all elements of the web page {string}")
    public void i_should_see_the_web_page(String pageName) {
        setPageObjNameBDD(pageName);
        for (String eLocator : getAllVisibleElementNamesBDD()) {
            verifyElementVisible(getWebElementBDD(eLocator), minWaitTime, FailureHandling.CONTINUE_ON_FAILURE);
        }
    }

    //Verify Element
    @Then("I should see {string}")
    public void i_should_see(String string) {
    }

    @Then("I should see {string} displays")
    public void i_should_see_displays(String eName) {
        verifyElementVisible(getWebElementBDD(eName), maxWaitTime);
    }

    @Then("I should see {string} displays with text {string}")
    public void i_should_see_displays_with_text(String eName, String eText) {
        WebElement element = getWebElementBDD(eName);
        verifyElementVisible(element, maxWaitTime);
        verifyElementText(element, true, eText.trim(), FailureHandling.STOP_ON_FAILURE);
    }

    @Then("I should see {string} displays with text containing {string}")
    public void i_should_see_displays_with_text_containing(String eName, String eTextContain) {
        WebElement element = getWebElementBDD(eName);
        verifyElementVisible(element, maxWaitTime);
        verifyElementText(element, true, eTextContain, FailureHandling.STOP_ON_FAILURE);
    }

    @Then("I should see {string} displays with text inner as {string}")
    public void i_should_see_displays_with_text_inner_as(String eName, String eTextInner) {
        WebElement element = getWebElementBDD(eName);
        String expectedText = getAttributeValue(element, "innerText", minWaitTime);
        verifyMatch(expectedText, eTextInner,false, FailureHandling.STOP_ON_FAILURE);
    }

    @Then("I should see {string} has the attribute {string} as {string}")
    public void i_should_see_has_the_attribute_as(String eName, String attName, String attValue) {
        WebElement element = getWebElementBDD(eName);
        if(!element.getAttribute(attName).trim().equals(attValue.trim()))
            throw new IllegalArgumentException(LoginConfig.requiredMessage);
    }

}
