package projects.utilities;

import com.vn.chotot.data.ExcelProvider;
import com.vn.chotot.driver.selenium.DriverFactory;
import com.vn.chotot.logger.Log4jFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.Logger;
import org.junit.Assume;
import org.testng.Assert;

import java.util.Date;
import java.util.stream.Collectors;

import static api.configuration.DataConfig.*;
import static api.configuration.GatewayConfig.*;
import static api.utils.GetAccessToken.*;
import static com.vn.chotot.api.rest_assured.RestAssure.setRequestURL;
import static com.vn.chotot.configuration.Constant.*;
import static com.vn.chotot.configuration.WaitTime.maxWaitTime;
import static com.vn.chotot.exception.ExceptionHandler.getExceptionDebug;
import static com.vn.chotot.exception.ExceptionHandler.resetExceptionDebug;
import static com.vn.chotot.helper.DateTime.getDateInStringFormat;
import static com.vn.chotot.keywords.selenium.Page.getPageURL;
import static desktop.configuration.BaseConfig.numberPopupDisplayed;
import static desktop.configuration.BaseConfig.showPOS;
import static desktop.configuration.LoginConfig.setTempAccountLoginIndex;
import static projects.steps._common.CommonSteps.tempValueString;

public class Hooks {

  public static ExcelProvider excelDataProvider = new ExcelProvider();
  private static boolean _skipScenarioFlag = false;
  private final Logger log = Log4jFactory.instance().createClassLogger(getClass());
  private String testServiceName = "UNKNOWN";
  private String testScenarioName = "";
  private String testTagName = "";
  private String testingType = ""; // UI, API
  private String testAuthor = "";
  private String testTeam = "";
  private String featureFile = "";

  @Before
  public void setUpTestMethod(Scenario scenario) {
    featureFile = getFeatureFileName(scenario);
    getScenarioParams(scenario);
    // Check if Smoke Test is failed
    if (_skipScenarioFlag) Assume.assumeTrue("SMOKE Test is failed", false);
    setupWebDriverBeforeTest();
  }

  @After
  public void tearDownTestMethod(Scenario scenario) {
//    if (scenario.isFailed()) {
//      log.error("\n----- TEST FAILED: " + testScenarioName);
//      String imagePath = takeFailureScreenshot();
//      log.info("Screenshot Path: " + imagePath);
//
//      // Send info to Telegram bot
//      if (UPDATE_STATUS) {
//        sendMessageAndScreenshotToBot(testScenarioName, testAuthor, testTeam, imagePath);
//      }
//      // JIRA API
//      // if (POST_ISSUE_TO_JIRA) uploadIssueToJira(iTestResult, imagePath);
//    }
        setupWebDriverAfterTest();

        //For Specified Project --------------------
        reInitVarChotot();
  }

  // Smoke Testing is always API Testing -> Need a function for UI
  @Before("@SMOKE")
  public void beforeSmokeTesting(Scenario scenario) {
    _skipScenarioFlag = false;
    setupWebDriverBeforeTest();
    getScenarioParams(scenario);
  }

  @After("@SMOKE")
  public void afterSmokeTesting(Scenario scenario) {
    if (scenario.isFailed()) _skipScenarioFlag = true;
    setupWebDriverAfterTest();
  }
  private String takeFailureScreenshot() {
    String failedScreenshot =
        getDateInStringFormat(new Date(), "yyyyMMdd.HHmmss")
            + "_"
            + testScenarioName.replace(" ", "")
            + ".jpg";
    if (testingType.equals("UI")) return DriverFactory.instance().takeScreenshot(failedScreenshot);

    return null;
  }

  public void reInitVarChotot() {
    // Reset value to check popup closed for the next execution
    numberPopupDisplayed = 0;

    // Reset account id, access token, new phone
    global_accountID = "";
    global_accountOID = "";
    global_accessTokenCP = "";
    newUserPhone = "";
    showPOS = false;
    tempValueString = "";
    listNewUser.clear();

    // Check existing user
    if (!previous_phone.equals(current_phone) || testingType.equalsIgnoreCase("UI")) {
      global_accessToken = "";
    }
    previous_phone = current_phone;

    // Reset Retry_Ad
    tempRetryResetToDefault();

    // Reset exception debug for the next execution
    resetExceptionDebug();

    // Reset temp account login
    setTempAccountLoginIndex(-1);
    setTempAccountAPIIndex(-1);

    // Reset debug info
    setRequestURL("");
  }


  private void getScenarioParams(Scenario scenario) {
    try {
      testScenarioName = scenario.getName();

      // Split tag: @AUTHOR_VUHOANG_ME_API
      testTagName =
          scenario.getSourceTagNames().stream()
              .map(String::toUpperCase)
              .filter(tag -> tag.startsWith("@AUTHOR"))
              .collect(Collectors.joining())
              .replace("@", "")
              .trim();

      testAuthor = testTagName.split("_")[1];
      testTeam = testTagName.split("_")[2];
      testingType = testTagName.split("_")[3];

      // GET Scenario Name
      testScenarioName = scenario.getName();

      // Split tag: @SERVICE_ABC
      testServiceName =
          scenario.getSourceTagNames().stream()
              .map(String::toUpperCase)
              .filter(tag -> tag.startsWith("@SERVICE"))
              .collect(Collectors.joining())
              .replace("@SERVICE_", "")
              .trim();
    } catch (Exception e) { // Missing any Tag @AUTHOR_ , @SERVICE_ , Scenario Name
      // Smoke Test
      try {
        // Split tag: @SMOKE
        testTagName =
            scenario.getSourceTagNames().stream()
                .map(String::toUpperCase)
                .filter(tag -> tag.startsWith("@SMOKE"))
                .collect(Collectors.joining());
        testingType = "API";
      } catch (Exception ex) {
        log.info("Not define Tag_Smoke");
      }
      log.info("Not define Tag_Author, Tag_Service");
    }
  }

  // Determine which testing type is applied. If it's API, FW calls setTempAccountAPIIndex
  private void setupWebDriverBeforeTest() {
    Assert.assertNotEquals(testingType, "Undefined");
    if (testingType.toUpperCase().trim().contains("API")) {
      // Reset temp account login
      setTempAccountAPIIndex(-1);
    } else {
      // Init Driver for UI & Undefined
      DriverFactory.instance().createWebDriver("chrome");
    }
  }

  private void setupWebDriverAfterTest() {
    Assert.assertNotEquals(testingType, "Undefined");
    if (testingType.equalsIgnoreCase("UI")) {
      if (!IS_DEBUGGING) DriverFactory.instance().disposeWebDriver();
    }
  }

  private String getFeatureFileName(Scenario scenario) {
    String uri = scenario.getUri().getPath();
    String featureFile = uri.substring(uri.lastIndexOf("/") + 1).replace(".feature", "");
    return featureFile;
  }
}
