package projects.steps.chotot.api.ads.paramEditing;

import com.vn.chotot.logger.Log4jFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.Logger;
import projects.functions.chotot.ads.paramEditing.AdParamEditing_API_Functions;

import static api.configuration.DataConfig.setIsTestingModeration;
import static api.configuration.DataConfig.setTempAccountCPAPIIndex;
import static api.utils.GetAccessToken.getAccessTokenOfCPUser;
import static api.utils.GetAccessToken.getAccessTokenOfNewUser;

public class AdParamEditing_API_Steps extends AdParamEditing_API_Functions {

  static final Logger log =
      Log4jFactory.instance().createClassLogger(AdParamEditing_API_Steps.class);

  @Given("New user is created to test Ad Editing Params")
  public void new_user_is_created_to_test_ad_editing() {
    setTempAccountCPAPIIndex(4);
    getAccessTokenOfNewUser();
    getAccessTokenOfCPUser();
    setIsTestingModeration(true);
//    setIsUploadingImage(true);
  }

  @When("User post a new private ad {string} to test ad param editing")
  public void new_private_ad_to_test_ad_param_editing(String cate_id) {
      postPrivateAdByCateID(cate_id, "none");
  }

  @Then("New ad is processed up to 2 minutes")
  public void new_ad_is_processed_up_to_2_mins() {
    checkProcessingTimeAdParamEditing();
  }
}
