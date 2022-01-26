package projects.configuaration;

import com.jayway.jsonpath.JsonPath;
import com.vn.chotot.api.google_sheet.SheetResults;
import com.vn.chotot.driver.selenium.DriverFactory;
import com.vn.chotot.logger.Log4jFactory;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static api.configuration.DataConfig.tempRetryResetToDefault;
import static api.configuration.DataConfig.tempRetrySetToTest;
import static com.vn.chotot.api.prometheus.PushGateway.pushExecutionTime;
import static com.vn.chotot.bot.SlackBot.sendMessageToSlackChannelByRunType;
import static com.vn.chotot.bot.customer.SlackBot.sendMessageToSlackAndTags_DeployService;
import static com.vn.chotot.bot.customer.TelegramBot.getGoogleReportURLByFeature;
import static com.vn.chotot.bot.customer.TelegramBot.sendMsgToTelegramAndTags;
import static com.vn.chotot.configuration.Constant.*;
import static com.vn.chotot.helper.DateTime.getDateInStringFormat;
import static com.vn.chotot.helper.DateTime.subtractDateTime;
import static com.vn.chotot.helper.FileHelper.writeContentToFile;
import static com.vn.chotot.helper.Utils.removeElementListString;
import static com.vn.chotot.keywords.selenium.Utils.delayStep;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features"
        , glue = {"projects.utilities", "projects.steps"}
        , tags = "NONE"     // Change to NONE before pushing new code
        , plugin = {"pretty", "json:reportBDD/cucumber.json"}
        , monochrome = true
        , stepNotifications = true
        , strict = true
)
public class TestBDDRunner {
    private static final String REPORT_BDD_PATH =
            System.getProperty("user.dir") + "/reportBDD/cucumber.json";
    private static final Logger log = Log4jFactory.instance().createClassLogger(TestBDDRunner.class);
    private static File REPORT_BDD_FILE = null;
    private static String testServiceName = "UNKNOWN";
    private static List<String> testFeatureList;
    private static String testFeatureName = "N/A";
    private static List<String> testScenarioList;
    private static String testScenarioName;
    private static String testFailedStep = "N/A";
    private static String testFailedReason = "";
    private static String testTagName = "";
    private static String testingType = "API";
    private static String testAuthor = "N/A";
    private static String testTeam = "N/A";
    private static String testStatus = "";
    private static String browserName = "";
    private static String platform = "";
    private static String startTimeStr = "";
    private static String executeTime = "";
    private static String sheetName;
    private static String cucumberTags;

    private static String suiteStatus = "N/A";
    private static String suiteStartTime = "";
    private static String suiteEndTime = "";
    private static String suiteName = "N/A";
    private static SheetResults sheetResults = null;
    private static boolean isCustomer = false;

    static Date startTime;
    static Date endTime;

    //For TelegramBot
    private static List<String> failedScenarios = new ArrayList<>();
    private static List<String> failedReasons = new ArrayList<>();
    public static List<String> skippedScenarios = new ArrayList<>();
    public static List<String> skippedReasons = new ArrayList<>();
    private static int scenarioTotal = 0, scenarioFailedTotal = 0, scenarioSkippedTotal = 0;
    private static String featureTeamOwner = "";
    private static List<String> featureTagName = new ArrayList<>();

    @BeforeClass
    public static void beforeFeature() {
        // Get time start suite
        startTime = new Date(System.currentTimeMillis());
        suiteStartTime = getDateInStringFormat(startTime, "yyyy/MM/dd HH:mm:ss");

        // Set default suite status
        suiteStatus = "PASSED";

        if (REPORT_BDD_FILE == null) {
            REPORT_BDD_FILE = new File(REPORT_BDD_PATH);
        }
        writeContentToFile(REPORT_BDD_PATH, "");    //truncate content of cucumberReport.json
        tempRetrySetToTest();

        if (UPDATE_STATUS) {
            // Add new column Status
            sheetName = extractSheetNameBDD();              //Define SheetName at beginning of the test
            sheetResults = new SheetResults(testingType);   //SheetID is already defined in here

            if (!TEST_RUN_TYPE.equalsIgnoreCase("nightly")) {       //Define SheetName at beginning of the test -> by a specified Sheet
                if (!StringUtils.isEmpty(SHEET_NAME_FIXED)) {
                    sheetName = SHEET_NAME_FIXED;
                    sheetResults.setSheetIDByFeature(sheetName);    //VUHOANG: to send report to a specified Sheet
                }
            }

            //Add new status column
            //In service deploy mode, param "insertStatusColumn" doesn't exist. It is IS_FIRST_POD
            if (INSERT_STATUS_COLUMN) {
                log.info("\n----- INSERT NEW STATUS COLUMN INTO GOOGLE SHEET : " + sheetName);
                sheetResults.insertStatusColumn();
                sheetResults.updateStatusColumn(sheetName);
            }

            log.info("*** Wait for environment more stability !!!!");
            if (getStatusPrefixK8S().equalsIgnoreCase("flashad") && TEST_RUN_TYPE.equalsIgnoreCase("deploy_service")) {
                //delayStep(60);
            } else if (TEST_RUN_TYPE.equalsIgnoreCase("nightly")) {
                delayStep(10);
            }

            if (RUN_ON_K8S) {
                if (IS_FIRST_POD) {    //Insert new Status Column
                    if (!StringUtils.isEmpty(SHEET_NAME_FIXED)) {
                        System.out.println("\n----- INSERT NEW STATUS COLUMN INTO GOOGLE SHEET : " + sheetName);
                        sheetResults.insertStatusColumn();
                    } else {
                        sheetResults.updateStatusValues("K8S_ORG_BDD", getStatusPrefixK8S(), "RUNNING");
                    }
                }
            }
        }
    }

    @AfterClass
    public static void afterFeature() {
        // Get time end suite
        endTime = new Date(System.currentTimeMillis());
        suiteEndTime = getDateInStringFormat(endTime, "yyyy/MM/dd HH:mm:ss");

        // Get execution time
        long executionTimeSeconds = subtractDateTime(endTime, startTime);
        log.info(String.format("!!! Execution time: %d seconds", executionTimeSeconds));

        if (UPDATE_STATUS) {
            // Update status row
            testFeatureList = getJsonDataList("$[*].name");
            List<List<Object>> dataRow;
            List<List<Object>> dataSuite;
            SheetResults sheetResultsAfterClass = new SheetResults(testingType);  //testingType is "API" by default (1)
            log.info("testFeatureList: " + testFeatureList);

            //Wait until cucumber.json is exported successfully
            int i = 0;
            while (i < 3) {
                if (REPORT_BDD_FILE.length() > 0)
                    break;
                else {
                    i++;
                    delayStep(1);
                }
            }

            for (String featureName : testFeatureList) {
                testingType = defineTestingType(featureName);     //Defined by Feature Name
                testServiceName =
                        getJsonDataList(
                                String.format(
                                        "$[%d].tags[?(@.name =~ /@SERVICE_.*?/i)].name",
                                        testFeatureList.indexOf(featureName))) // 1 Feature 1 Service
                                .get(0)
                                .replace("@SERVICE_", "");

                //Init Google Report Object
                sheetResultsAfterClass = new SheetResults(testingType);   //SHEETID_TYPE is init & defined
                if (!StringUtils.isEmpty(SHEET_NAME_FIXED)) {
                    if (!TEST_RUN_TYPE.equalsIgnoreCase("nightly")) {
                        sheetName = SHEET_NAME_FIXED;
                        sheetResultsAfterClass.setSheetIDByFeature(sheetName);    //SHEETID_TYPE is init & defined by a specified Sheet Name
                    } else {
                        sheetResultsAfterClass.setSheetIDByFeature(sheetName);    //SheetName is defined in BeforeTest by extractSheetNameBDD
                    }
                } else {
                    sheetResultsAfterClass.setSheetIDByFeature(sheetName);        //SheetName is defined in BeforeTest by extractSheetNameBDD
                }

                // Detect Team Name from Tag @TELEGRAM. Replace @TELEGRAM_ by @WG_
                try {
                    featureTeamOwner = getJsonDataList(
                            String.format(
                                    "$[%d].tags[?(@.name == '@CUSTOMER')].name",
                                    testFeatureList.indexOf(featureName))) // 1 Feature 1 Service
                            .get(0)
                            .replace("@", "");
                    isCustomer = true;
                } catch (Exception ex) {
                    try {
                        featureTeamOwner = getJsonDataList(
                                String.format(
                                        "$[%d].tags[?(@.name =~ /@TELEGRAM_.*?/i)].name",
                                        testFeatureList.indexOf(featureName))) // 1 Feature 1 Service
                                .get(0)
                                .replace("@TELEGRAM_", "");
                    } catch (Exception exx) {
                        featureTeamOwner = "GAMMA";   //For TSs which isn't updated tag "@TELEGRAM_TEAM" yet
                    }
                }

                try {
                    String tagNames = getJsonDataList(
                            String.format(
                                    "$[%d].tags[?(@.name =~ /@TAG_.*?/i)].name",
                                    testFeatureList.indexOf(featureName))) // 1 Feature 1 Service
                            .get(0)
                            .replace("@TAG_", "");
                    Collections.addAll(featureTagName, tagNames.split("_"));
                } catch (Exception ex) {
                    ////For TSs which isn't updated tag "@TAG" yet
                }

                // Suite variables
                suiteName = (DOMAIN + "_" + TEST_RUN_TYPE.toUpperCase() + "_" + testServiceName + "_" + cucumberTags).toUpperCase();

                testScenarioList =
                        getJsonDataList(
                                String.format("$[%d].elements[*].name", testFeatureList.indexOf(featureName)));

                //For Telegram Message
                scenarioTotal = removeElementListString(testScenarioList, "").size();  // remove setup scenarios

                for (String scenarioName : testScenarioList) {
                    dataRow = getGoogleSheetData(featureName, scenarioName);

                    //UPDATE STATUS OF TEST
                    if (!dataRow.isEmpty()) {
                        if (!RUN_ON_K8S) {
                            log.info("\n----- START UPDATE RESULT TO GOOGLE SHEET FOR SCENARIO: " + scenarioName + " ON SHEET: " + sheetName);
                            sheetResultsAfterClass.updateTestStatus(sheetName, scenarioName, dataRow);        //UPDATE STATUS CELL
                        } else {
                            log.info("\n----- START UPDATE RESULT TO GOOGLE SHEET -- K8S -- FOR SCENARIO: " + scenarioName);
                            if (!StringUtils.isEmpty(SHEET_NAME_FIXED)) {
                                sheetResultsAfterClass.updateTestStatus(sheetName, scenarioName, dataRow);
                            } else {
                                dataRow = checkPrefixStatusK8S(dataRow);    //Use for check service is running, to get status
                                sheetResultsAfterClass.updateTestStatusForK8S("K8S_" + DOMAIN + "_BDD", scenarioName, dataRow);
                            }
                        }

                        //UPDATE ERROR on COMMENT of Status Cell
                        if (dataRow.toString().contains("FAILED") || dataRow.toString().contains("SKIPPED")) {
                            sheetResultsAfterClass.updateTestNote(sheetName, scenarioName, testFailedReason);
                        }
                    }
                }

                sendResultToBotByFeature(featureName, featureTeamOwner, featureTagName);
            }//end For feature

            // Update STATUS TITLE of STATUS COLUMN
            // Unless sheetResultsAfterClass is init in for loop, sheetResultsAfterClass is still not null by (1)
            if (RUN_ON_K8S) {
                if (!StringUtils.isEmpty(SHEET_NAME_FIXED)) {
                    sheetName = SHEET_NAME_FIXED;
                    sheetResultsAfterClass.setSheetIDByFeature(sheetName);    //VUHOANG DEBUG: considering no need
                    sheetResultsAfterClass.updateStatusColumn(sheetName);
                } else {
                    sheetResultsAfterClass.updateStatusColumn("K8S_" + DOMAIN + "_BDD");
                }
            } else {
                if (!StringUtils.isEmpty(SHEET_NAME_FIXED)) {
                    sheetName = SHEET_NAME_FIXED;
                    sheetResultsAfterClass.setSheetIDByFeature(sheetName);    //VUHOANG DEBUG: considering no need
                    sheetResultsAfterClass.updateStatusColumn(sheetName);
                }
                sheetResultsAfterClass.updateStatusColumn(sheetName);   //Make sure applied sheetName = SHEET_NAME_FIXED
            }


            // Update Suite
            dataSuite = getSuiteData();
            if (!dataSuite.isEmpty()) {
                sheetResultsAfterClass.updateSuiteInfo(suiteName, dataSuite);
            }

            // Push info to pushgateway
            if (testServiceName.indexOf("IRIS") > 0 || testServiceName.indexOf("SENDY") > 0) {
                pushExecutionTime(executionTimeSeconds);
            }
        }

        tempRetryResetToDefault();    //Reset temp loop value
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


    // ======== FUNCTIONALITIES =========

    // Each Feature will get each data block. Each row of the block is info of a scenario
    private static List<List<Object>> getGoogleSheetData(String featureName, String scenarioName) {
        List<List<Object>> listDataRow = new ArrayList<>();
        List<Object> dataRow;

        int featureIndex = testFeatureList.indexOf(featureName);
        int scenarioIndex = testScenarioList.indexOf(scenarioName);

        if (!getJsonData(String.format("$[%d].elements[%d].type", featureIndex, scenarioIndex))
                .toUpperCase()
                .equals("BACKGROUND")) {
            testFeatureName = featureName;
            testScenarioName = scenarioName;
            testingType = defineTestingType(testFeatureName);
            platform = System.getProperty("os.name");
            browserName = DriverFactory.instance().getExecutedBrowser();
            testTagName =
                    getJsonDataList(
                            String.format(
                                    "$[%d].elements[%d].tags[?(@.name =~ /@AUTHOR_.*?/i)].name",
                                    featureIndex, scenarioIndex))
                            .get(0);
            testAuthor = testTagName.split("_")[1];     //Get Test Author from @AUTHOR_NAME_TEAM_TYPE
            testTeam = testTagName.split("_")[2];       //Get Test Team


            //Get status of each Scenario
            List<String> statusList =
                    getJsonDataList(
                            String.format(
                                    "$[%d].elements[%d].steps[*].result.status", featureIndex, scenarioIndex));
            if (statusList.contains("failed")) {
                //For Google Sheet
                testStatus = "FAILED";
                testFailedStep =
                        getJsonDataList(
                                String.format(
                                        "$[%d].elements[%d].steps[?(@.result.status == 'failed')].name",
                                        featureIndex, scenarioIndex))
                                .get(0);
                testFailedReason =
                        getJsonDataList(
                                String.format(
                                        "$[%d].elements[%d].steps[?(@.result.status == 'failed')].result.error_message",
                                        featureIndex, scenarioIndex))
                                .get(0)
                                .split("\n")[0];
                suiteStatus = "FAILED";

                //For Telegram Message
                setFailedScenarios(testScenarioName);
                addScenarioFailedTotal();
                setFailedReasons(testFailedReason);

            } else if (statusList.contains("skipped")) {
                testStatus = "SKIPPED";
                testFailedStep =
                        getJsonDataList(
                                String.format(
                                        "$[%d].elements[%d].steps[?(@.result.status == 'skipped')].name",
                                        featureIndex, scenarioIndex))
                                .get(0);
                try {
                    testFailedReason =
                            getJsonDataList(
                                    String.format(
                                            "$[%d].elements[%d].steps[?(@.result.status == 'skipped')].result.error_message",
                                            featureIndex, scenarioIndex))
                                    .get(0)
                                    .split("\n")[0];
                } catch (NullPointerException | IndexOutOfBoundsException e) {
                    testFailedReason = "null";
                }

                //For Telegram Message
                setSkippedScenarios(testScenarioName);
                addScenarioSkippedTotal();
                setSkippedReasons(testFailedReason);

            } else {
                testStatus = "PASSED";
                testFailedStep = "N/A";
                testFailedReason = "";
            }

            startTimeStr =
                    getJsonData(
                            String.format("$[%d].elements[%d].start_timestamp", featureIndex, scenarioIndex));
            startTimeStr = convertDate(startTimeStr);

            List<Object> durations =
                    getJsonDataListObj(
                            String.format(
                                    "$[%d].elements[%d].steps[*].result.duration", featureIndex, scenarioIndex));
            long durationTotal = 0l;
            if (!durations.isEmpty()) {
                durationTotal = durations.stream().mapToLong(o -> Long.parseLong((o.toString()))).sum();
            }
            durationTotal = durationTotal / 1000000;
            DateFormat dateFormat = new SimpleDateFormat("mm:ss");
            executeTime = dateFormat.format(new Date(durationTotal));

            // Put params into dataRow
            dataRow =
                    Arrays.asList(
                            DOMAIN,
                            testServiceName,
                            testFeatureName,
                            testFailedStep, // Manual Test Case
                            testingType,
                            testScenarioName, // Test Method
                            testAuthor,
                            testTeam,
                            browserName,
                            platform,
                            startTimeStr,
                            executeTime,
                            testFailedReason, // Issue reason
                            testStatus);
            log.info(testScenarioName + " : " + dataRow);
            listDataRow.add(dataRow);
        }
        log.info("*** Google sheet data: ");
        System.out.println(listDataRow);
        return listDataRow;
    }

    private static String getJsonData(String query) {
        try {
            return JsonPath.parse(REPORT_BDD_FILE).read(query, String.class);
        } catch (IOException e) {
            log.info("Report doesn't exist: " + REPORT_BDD_FILE);
        } catch (Exception e) {
        }
        return null;
    }

    private static List<String> getJsonDataList(String query) {
        try {
            return JsonPath.parse(REPORT_BDD_FILE).read(query, List.class);
        } catch (IOException e) {
            log.info("Report doesn't exist: " + REPORT_BDD_FILE);
        } catch (Exception e) {
        }
        return null;
    }

    private static List<Object> getJsonDataListObj(String query) {
        try {
            return JsonPath.parse(REPORT_BDD_FILE).read(query, List.class);
        } catch (IOException e) {
            log.info("Report doesn't exist: " + REPORT_BDD_FILE);
        } catch (Exception e) {
        }
        return null;
    }

    private static String convertDate(String dateStr) {
        dateStr = dateStr.trim().split("\\.")[0] + "Z";
        String NEW_FORMAT = "yyyy/MM/dd HH:mm:ss";
        String OLD_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            Date date = sdf.parse(dateStr);
            sdf.setTimeZone(TimeZone.getTimeZone("GMT+7"));
            sdf.applyPattern(NEW_FORMAT);
            return sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String extractSheetNameBDD() {
        getTestingTypeForSheetName();   //get testingType
        switch (DOMAIN.toLowerCase()) {
            case "org":
            case "com":
                return testingType + "_" + DOMAIN.toUpperCase() + "_BDD";
            default:
                return "API_ORG_BDD";
        }
    }

    private static String getTestingTypeForSheetName() {
        try {
            if (!CUCUMBER_TAGS.isEmpty()) {
                cucumberTags = CUCUMBER_TAGS.replace(" ", "_").replace("@", "");
                if (CUCUMBER_TAGS.toUpperCase().contains("_UI")) testingType = "UI";
            }
        } catch (NullPointerException ex) {
            //Use default testingType
        }
        return testingType;
    }

    private static String defineTestingType(String featureName) {
        if (featureName.toUpperCase().contains("API")) {
            testingType = "API";
        } else testingType = "UI";
        return testingType;
    }

    private static List<List<Object>> checkPrefixStatusK8S(List<List<Object>> listData) {
        if (!getStatusPrefixK8S().isEmpty()) {
            List<Object> tempList = listData.get(0);
            int statusIndex = tempList.size() - 1;
            String tempStatus = getStatusPrefixK8S() + "-" + tempList.get(statusIndex);
            tempList.set(statusIndex, tempStatus);
            listData.set(0, tempList);
        }
        return listData;
    }

    private static List<List<Object>> getSuiteData() {
        List<List<Object>> listData = new ArrayList<>();
        List<Object> data;
        data = Arrays.asList(suiteName, suiteStartTime, suiteEndTime, suiteStatus);
        listData.add(data);
        log.info("\n----- Execution time of the test suite: " + listData);
        return listData;
    }

    private static void sendResultToBotByFeature(String featureName, String featureTeamOwner, List<String> featureTagNameList) {
        // Set message to send to telegram
        String serviceName = "";
        try {
            serviceName = System.getProperty("k8sPrefixStatus").toUpperCase();
        } catch (NullPointerException e) {
            serviceName = testServiceName;
        }

        //ONLY TAG WHEN SCRIPTS ARE FAILED
        boolean testPassed = true;
        if (scenarioFailedTotal > 0 || scenarioSkippedTotal > 0) {
            testPassed = false;
        }

        String message = "Automated test for service: *" + serviceName + "*\n"
                + "Start time: *" + suiteStartTime + "*\n"
                + "> Feature: ```" + featureName + "```\n"
//                + "     Mode: *" + TEST_RUN_TYPE + "*\n";
                ;

        // TEST RESULT
        if (testPassed) {
            message += "> Test Status: *PASSED*\n";

        } else {
            if (scenarioSkippedTotal > 0) {
                // SMOKE TEST IS FAILED
                message += "> Smoke Failed: ```" + scenarioSkippedTotal + "/" + scenarioTotal + "```\n";
            } else {
                message += "> TS Failed: ```" + scenarioFailedTotal + "/" + scenarioTotal + "```\n";
//                if (failedScenarios.size() > 0)
                for (int i = 0; i < failedScenarios.size(); i++) {
                    message = message
                            + "- " + failedScenarios.get(i) + "\n"
                    ;
                }
            }
        }


        if (!testPassed) {
            message += "\n> GG Report: " + getGoogleReportURLByFeature(featureName) + "\n";
        }
        message += "---------------------------------------\n";

        // CUSTOMER: Tag & Send Report when Test Failed
        if(isCustomer){
            if (testPassed) {
                sendMsgToTelegramAndTags(featureTeamOwner, message, null);
                sendMessageToSlackAndTags_DeployService(message, null);
            } else {
                sendMsgToTelegramAndTags(featureTeamOwner, message, featureTagNameList);
                sendMessageToSlackAndTags_DeployService(message, featureTagNameList);
            }
        } else {
            sendMessageToSlackChannelByRunType(message, featureTeamOwner);
        }
    }

    public static void setFailedScenarios(String failedScenario) {
        failedScenarios.add(failedScenario);
    }

    public static void setFailedReasons(String failedReason) {
        failedReasons.add(failedReason);
    }

    public static void addScenarioFailedTotal() {
        scenarioFailedTotal += 1;
    }

    public static void addScenarioSkippedTotal() {
        scenarioSkippedTotal += 1;
    }

    public static void setSkippedScenarios(String skippedScenario) {
        skippedScenarios.add(skippedScenario);
    }

    public static void setSkippedReasons(String skippedReason) {
        skippedReasons.add(skippedReason);
    }
}
