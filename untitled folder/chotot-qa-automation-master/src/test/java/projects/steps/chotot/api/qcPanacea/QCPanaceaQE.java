package projects.steps.chotot.api.qcPanacea;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import projects.functions.APISupportFunctions;

public class QCPanaceaQE extends APISupportFunctions {

    @Given("Call API Ad Listing")
    public void call_API_Ad_Listing() {
        // URL:
        url = "https://gateway.chotot.org/v1/public/ad-listing?region_v2=13000&st=s,k&limit=20&w=1&key_param_included=true";
        //
        response = get(url);
        debugResponse();
        verifyStatusCode200("Adlisting", url);
        query = "$.ads[*][?(@.list_id == 93240)].ad_id";
        System.out.println(getResponseData(query));
//        query = "$..ad_id";
        System.out.println(getResponseDataList(query));
    }

    @Then("Print query {string}")
    public void print_query(String queryStr) {
        System.out.println("----------------- QC Panacea - QE: Practice JSON -----------------");
        System.out.println("JSONBody is: " + getBodyString(response) + "\n");
        System.out.println("JSONPath is: " + queryStr + "\n");
        System.out.println("Result is: " + getResponseData(response, queryStr) + "\n");
        System.out.println("------------------------------------------------------------------");
    }
}
