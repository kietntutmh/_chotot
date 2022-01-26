package projects.functions.chotot._common.api;

import com.vn.chotot.api.rest_assured.RestAssure;
import com.vn.chotot.driver.selenium.DriverFactory;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static api.configuration.GatewayConfig.global_accessToken;
import static api.utils.CheckAccessToken.setAccessToken;
import static com.vn.chotot.api.rest_assured.VerifyRespone.verifyStatusCode;
import static com.vn.chotot.configuration.Constant.DOMAIN;
import static com.vn.chotot.keywords.selenium.Element.getAttributeValueOfListElement;

public class CM_API_CheckStatusCodeImage {

    public static void verifyStatusCodeOfImages() {
        // Get list images on main content
        if (DOMAIN.equalsIgnoreCase("com")) {
            String xpathImage =
                    "//main//img[contains(@src,\"images\") and contains(@src,\"static.chotot\") and @class]";
            List<WebElement> listImages =
                    DriverFactory.instance().getWebDriver().findElements(By.xpath(xpathImage));

            if (!listImages.isEmpty()) {
                // Get all source links
                List<String> listSources = getAttributeValueOfListElement(listImages, "src");

                // Set access token
                setAccessToken();

                // Check status code is 200
                Response response;
                for (String source : listSources) {
                    response = RestAssure.instance().get(global_accessToken, source);
                    verifyStatusCode(response, "200");
                }
            }
        }
    }
}
