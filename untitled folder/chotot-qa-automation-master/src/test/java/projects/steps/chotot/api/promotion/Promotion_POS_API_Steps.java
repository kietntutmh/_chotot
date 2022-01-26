package projects.steps.chotot.api.promotion;

import io.cucumber.java.en.Given;
import projects.functions.chotot.promotion.Promotion_POS_API_Functions;

import static api.utils.GetAccessToken.getAccessTokenOfNewUser;

public class Promotion_POS_API_Steps extends Promotion_POS_API_Functions {
    @Given("I login my account to get Promotion")
    public static void loginToGetPromotion(){
        setUserToken(getAccessTokenOfNewUser());
    }
}
