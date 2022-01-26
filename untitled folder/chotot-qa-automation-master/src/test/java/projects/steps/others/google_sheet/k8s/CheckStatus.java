package projects.steps.others.google_sheet.k8s;

import com.vn.chotot.api.google_sheet.SheetResults;
import com.vn.chotot.logger.Log4jFactory;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.Logger;

import static com.vn.chotot.configuration.Constant.*;

public class CheckStatus {

    final Logger log = Log4jFactory.instance().createClassLogger(getClass());

    @Then("I can get test status that run on k8s in Google Sheet")
    public void i_can_get_test_status_run_on_k8s_in_Google_Sheet() {
        int timeOut = getTimeoutK8S();
        int timeDelay = getTimeDelayK8S();
        int timeFirstDeLay = getTimeFirstDelayK8S();
        boolean isCheckSuite = getCheckSuiteK8S();
        log.info("\n----- k8sPrefixStatus: " + getStatusPrefixK8S());

        // Check status of tests run on k8s or test suites
        SheetResults sheetResults = new SheetResults();

        if (!SHEET_NAME_FIXED.isEmpty()) {
            String sheetName = SHEET_NAME_FIXED;
            //Setup report sheet by a specified sheet based on feature name
            sheetResults.setSheetIDByFeature(sheetName);
        }

        if (isCheckSuite) sheetResults.checkSuiteStatus(timeOut, timeDelay, timeFirstDeLay);
        else sheetResults.checkTestStatusOnK8S(timeOut, timeDelay, timeFirstDeLay);
    }
}
