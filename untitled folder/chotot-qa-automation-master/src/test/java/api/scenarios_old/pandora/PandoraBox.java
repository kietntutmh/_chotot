package api.scenarios_old.pandora;

import api.base.BaseAPITest;
import com.vn.chotot.api.rest_assured.RestAssure;
import com.vn.chotot.data.ExcelProvider;
import com.vn.chotot.exception.FailureHandling;
import desktop.base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import projects.functions.chotot._common.cp.CM_CP_AcceptAd;

import java.util.List;

import static api.configuration.DataConfig.*;
import static api.configuration.GatewayConfig.*;
import static api.feature.ads.InsertAds.insertNewAd;
import static api.utils.GetAccessToken.getAccessTokenOfExistingUser;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping;
import static com.vn.chotot.api.rest_assured.VerifyRespone.getResponseData;
import static com.vn.chotot.api.rest_assured.VerifyRespone.verifyStatusCode;
import static com.vn.chotot.keywords.selenium.Utils.verifyMatch;
import static desktop.configuration.CPConfig.setTempAccountCPIndex;

public class PandoraBox extends BaseAPITest {
    private Response getResponse(String basePath) {
        RestAssured.baseURI = gatewayHost;
        if (basePath.toUpperCase().contains("PRIVATE")) { // Private
            setTempAccountAPIAndGetToken(8);
            return RestAssure.instance().get(getAccessTokenOfExistingUser(), basePath);
        } else { // public , internal
            return RestAssure.instance().get(basePath);
        }
    }

//    @BeforeMethod
    public void setup() {
        setTempAccountAPIAndGetToken(8);
        getAccessTokenOfExistingUser();
    }

    @Test(description = "PANDORA, Verify Ad Listing, Vu Hoang, Pandora")
    public void verify_adListing() {
        // 1. Insert Ad Fashion
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adFashionExcelFile, "Clothes");
        List<String> fashionDataKeys = excelDataProvider.getRowData(1);
        List<String> fashionDataValues = excelDataProvider.getRowData(2);
        String data = "";
        int count = 0;
        while (count < 10) {
            try {
                data = extractAndUpdateSubjectJSONMapping(fashionDataKeys, fashionDataValues);
                break;
            } catch (Exception ex) {
                count++;
            }
        }
        insertNewAd(data);

        // 2. CP: approve Ad
        setTempAccountCPIndex(2);
        new BaseTest().setUpTestMethod("chrome");
        CM_CP_AcceptAd cm_cp_acceptAd = new CM_CP_AcceptAd();
        cm_cp_acceptAd.acceptNewAd(tempAdID);
        new BaseTest().tearDownTestMethod();

        // 3. Set accessToken to get response
        Response response = getResponse(gatewayAdListing);

        // Verify status code
        verifyStatusCode(response, "200", FailureHandling.CONTINUE_ON_FAILURE);

        // Verify json
        String actualID = getResponseData(response, "$.ads[0].ad_id");
        verifyMatch(actualID, tempAdID, false, FailureHandling.STOP_ON_FAILURE);
    }

    @Test(description = "PANDORA, Verify Ad Listing Alpha, Vu Hoang, Pandora")
    public void verify_adListingAlpha() {
        Response response = getResponse(gatewayAdListingAlpha);
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
    }

    @Test(description = "PANDORA, Verify Ad Listing Exporter, Vu Hoang, Pandora")
    public void verify_adListingExporter() {
        Response response = getResponse(gatewayAdListingExporter);
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
    }

    @Test(description = "PANDORA, Verify Credit Private, Vu Hoang, Pandora")
    public void verify_creditPrivate() {
        Response response = getResponse(gatewayCreditPrivate);
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
    }

    @Test(description = "PANDORA, Verify Credit Internal, Vu Hoang, Pandora", enabled = false)
    public void verify_creditInternal() {
        Response response = getResponse(gatewayCreditInternal);
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
    }

    @Test(description = "PANDORA, Verify Order Private V1, Vu Hoang, Pandora")
    public void verify_orderPrivateV1() {
        Response response = getResponse(gatewayOrderPrivateV1);
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
    }

    @Test(description = "PANDORA, Verify Order Internal, Vu Hoang, Pandora")
    public void verify_orderInternal() {
        Response response = getResponse(gatewayOderInternal);
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
    }

    @Test(description = "PANDORA, Verify Order Private V2, Vu Hoang, Pandora")
    public void verify_orderPrivateV2() {
        Response response = getResponse(gatewayOrderPrivateV2);
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
    }

    @Test(description = "PANDORA, Verify Shoot, Vu Hoang, Pandora")
    public void verify_shoot() {
        Response response = getResponse(gatewayShoot);
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
    }

    @Test(description = "PANDORA, Verify Schedule Bump, Vu Hoang, Pandora")
    public void verify_scheduleBump() {
        Response response = getResponse(gatewayScheduleBump);
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
    }

    @Test(description = "PANDORA, Verify Sticky Ads Internal, Vu Hoang, Pandora")
    public void verify_stickyAdsInternal() {
        Response response = getResponse(gatewayStickyAdsInternal);
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
    }

    @Test(description = "PANDORA, Verify Sticky Ads Public V1, Vu Hoang, Pandora")
    public void verify_stickyAdsPublicV1() {
        Response response = getResponse(gatewayStickyAdsPublicV1);
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
    }

    @Test(description = "PANDORA, Verify Sticky Ads Public V2, Vu Hoang, Pandora")
    public void verify_stickyAdsPublicV2() {
        Response response = getResponse(gatewayStickyAdsPublicV2);
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
    }

    @Test(description = "PANDORA, Verify Packages Private, Vu Hoang, Pandora", enabled = false)
    public void verify_packagesPrivate() {
        Response response = getResponse(gatewayPackagesPrivate);
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
    }

    @Test(description = "PANDORA, Verify Packages Public, Vu Hoang, Pandora", enabled = false)
    public void verify_packagesPublic() {
        Response response = getResponse(gatewayPackagesPublic);
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
    }

    @Test(description = "PANDORA, Verify Packages Internal, Vu Hoang, Pandora", enabled = false)
    public void verify_packagesInternal() {
        Response response = getResponse(gatewayPackagesInternal);
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
    }

    @Test(description = "PANDORA, Verify Pricer Private V1, Vu Hoang, Pandora")
    public void verify_pricerPrivateV1() {
        Response response = getResponse(gatewayPricerPrivateV1);
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
    }

    @Test(description = "PANDORA, Verify Pricer Private V2, Vu Hoang, Pandora")
    public void verify_pricerPrivateV2() {
        Response response = getResponse(gatewayPricerPrivateV2);
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
    }

    @Test(description = "PANDORA, Verify Pricer Internal V1, Vu Hoang, Pandora")
    public void verify_pricerInternalV1() {
        Response response = getResponse(gatewayPricerInternalV1);
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
    }

    @Test(description = "PANDORA, Verify Pricer Internal V2, Vu Hoang, Pandora")
    public void verify_pricerInternalV2() {
        Response response = getResponse(gatewayPricerInternalV2);
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
    }

    @Test(description = "PANDORA, Verify Cart Private, Vu Hoang, Pandora")
    public void verify_cartPrivate() {
        Response response = getResponse(gatewayCartPrivate);
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
    }

    @Test(description = "PANDORA, Verify Cart Internal, Vu Hoang, Pandora")
    public void verify_cartInternal() {
        Response response = getResponse(gatewayCartInternal);
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
    }

    @Test(description = "PANDORA, Verify Ad Features Public, Vu Hoang, Pandora")
    public void verify_adFeaturesPublic() {
        Response response = getResponse(gatewayAdFeaturesPublic);
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
    }

    @Test(description = "PANDORA, Verify Ad Features Internal, Vu Hoang, Pandora")
    public void verify_adFeaturesInternal() {
        Response response = getResponse(gatewayAdFeaturesInternal);
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
    }

    @Test(description = "PANDORA, Verify Chat Inages Private, Vu Hoang, Pandora")
    public void verify_chatImagesPrivate() {
        Response response = getResponse(gatewayChatImagesPrivate);
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
    }

    @Test(description = "PANDORA, Verify Chat Public v2, Vu Hoang, Pandora")
    public void verify_chatPublicV2() {
        Response response = getResponse(gatewayChatPublic);
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
    }

    @Test(description = "PANDORA, Verify Reason Public, Vu Hoang, Pandora")
    public void verify_reasonPublic() {
        Response response = getResponse(gatewayReasonPublic);
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
    }

    @Test(description = "PANDORA, Verify Reason Internal, Vu Hoang, Pandora")
    public void verify_reasonInternal() {
        Response response = getResponse(gatewayReasonInternal);
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
    }

    @Test(description = "PANDORA, Verify Search Suggestion Public, Vu Hoang, Pandora")
    public void verify_searchSuggestionPublic() {
        Response response = getResponse(gatewaySearchSuggestionPublic);
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
    }

    @Test(description = "PANDORA, Verify Search Suggestion Internal, Vu Hoang, Pandora")
    public void verify_searchSuggestionInternal() {
        Response response = getResponse(gatewaySearchSuggestionInternal);
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
    }
}
