package projects.steps._common;

import io.cucumber.java.en.When;
import projects.functions.TestBDDFunctions;

import static com.vn.chotot.keywords.selenium.Action.*;

@Deprecated
public class ActionSteps extends TestBDDFunctions {
    @When("I enter {string} as {string}")
    public void i_enter_value(String eName, String eValue) {
        setText(getWebElementBDD(eName), eValue);
    }

    @When("I click on {string}")
    public void i_click_on(String eName) {
        clickJS(getWebElementBDD(eName));
    }

    @When("I move to and click on {string}")
    public void i_move_to_and_click_on(String eName) {
        moveAndClickJS(getWebElementBDD(eName));
    }
}
