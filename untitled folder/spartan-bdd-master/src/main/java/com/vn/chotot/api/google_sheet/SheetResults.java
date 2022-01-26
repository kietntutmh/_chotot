package com.vn.chotot.api.google_sheet;

import com.google.api.services.sheets.v4.model.ValueRange;
import com.vn.chotot.exception.FailureHandling;
import com.vn.chotot.logger.Log4jFactory;
import org.apache.logging.log4j.Logger;

import java.util.*;

import static com.vn.chotot.configuration.Constant.*;
import static com.vn.chotot.helper.DateTime.getDateInStringFormat;
import static com.vn.chotot.helper.FileHelper.getProperties;
import static com.vn.chotot.keywords.selenium.Utils.delayStep;
import static com.vn.chotot.keywords.selenium.Utils.verifyEquals;

public class SheetResults {

    private static String SHEET_TEST_SUITE_NAME = "TEST_SUITE_BDD";
    private static ValueRange testValueRangeIssue = null;
    private static ValueRange testValueRangeMethod = null;
    private static ValueRange suiteValueRangeStatus = null;
    private static ValueRange testValueRangeMethodK8S = null;
    private static boolean isFirstAddResultK8S = true;
    final Logger log = Log4jFactory.instance().createClassLogger(getClass());
    static final SheetActions sheetActions = SheetActions.getInstance();
    final String SPREADSHEETID;
    final String RANGE;
    final String SHEETID_SUITE;
    // Column Index
    private final String COL_DOMAIN = "A";
    private final String COL_STATUS = "N";
    private String SHEETID_TYPE;
    String SHEET_DASHBOARD_RANGE;
    Properties prop;

    @Deprecated
    public SheetResults() {
        this("UI");
    }

    public SheetResults(String testingType) {
        prop = getProperties("google-sheet");
        this.SPREADSHEETID = prop.getProperty("spreadsheet.bdd.id");
        this.RANGE = prop.getProperty("range");
        this.SHEETID_SUITE = prop.getProperty("sheet.bdd.id.suite");
        SHEET_TEST_SUITE_NAME = prop.getProperty("sheet.bdd.suite.name");
        try {
            switch (DOMAIN.toLowerCase()) {
                case "com":
                    this.SHEET_DASHBOARD_RANGE = "Dashboard!B8:M8";
                    this.SHEETID_TYPE = prop.getProperty("sheet.bdd.id.com.ui");
                    return;
                default:
                    this.SHEET_DASHBOARD_RANGE = "Dashboard!B14:M14";
                    if (testingType.equalsIgnoreCase("UI"))
                        this.SHEETID_TYPE = prop.getProperty("sheet.bdd.id.org.ui");
                    else if (testingType.equalsIgnoreCase("API"))
                        this.SHEETID_TYPE = prop.getProperty("sheet.bdd.id.org.api");
            }
        } catch (NullPointerException e) {
            log.debug("\n----- Null value: " + e.getMessage());
        }
    }

    public void setSheetIDByFeature(String sheetName) {
        //SCOOBY_BDD => scooby.bdd      HIERARCHY_BDD => hierarchy.bdd
        String sheetNameKey = sheetName.toLowerCase().trim().replace("_", ".");
        this.SHEETID_TYPE = prop.getProperty("sheet." + sheetNameKey);
        System.out.println("SHEETID_TYPE is set to " + SHEETID_TYPE);
    }

    public static boolean getIsFirstAddResultK8S() {
        return isFirstAddResultK8S;
    }

    public static void setIsFirstAddResultK8S(boolean isFirstAddResult) {
        isFirstAddResultK8S = isFirstAddResult;
    }

    public static String extractSheetName(String testingType) {
        switch (DOMAIN.toLowerCase()) {
            case "org":
            case "com":
                return testingType + "_" + DOMAIN.toUpperCase();
            default:
                // co, asia ,me, mobi
                return "DEV_" + DOMAIN.toUpperCase();
        }
    }

    public void updateStatusColumn(String sheetName) {
        List<List<Object>> columnStatus =
                Arrays.asList(
                        Collections.singletonList("Status"),
                        Collections.singletonList(getDateInStringFormat(new Date(), "yyyy/MM/dd HH:mm:ss")));
        sheetActions.updateValues(
                this.SPREADSHEETID,
                sheetName + String.format("!%s1:%s2", COL_STATUS, COL_STATUS),
                "USER_ENTERED",
                columnStatus);
    }

    public void insertStatusColumn() {
        System.out.println("SPREADSHEETID: " + SPREADSHEETID);
        System.out.println("SHEETID_TYPE: " + SHEETID_TYPE);

        //VUHOANG DEBUG: SHEETID_TYPE -> has to change to sheet ID
        sheetActions.insertNewEmptyColumn(this.SPREADSHEETID, this.SHEETID_TYPE, 13, 14);
    }

    public String getExistingBugID(String sheetName, String testMethod) {
        // Get existing row index of test method
        int rowIndex = getRowIndexOfTestMethod(sheetName, testMethod, false);
        if (rowIndex > -1) {
            if (testValueRangeIssue == null) {
                String COL_BUGID = "M";
                testValueRangeIssue =
                        sheetActions.getValues(
                                this.SPREADSHEETID, sheetName + String.format("!%s1:%s1000", COL_BUGID, COL_BUGID));
            }
            List<List<Object>> allValue = testValueRangeIssue.getValues();
            String bugID;
            // Get existing bug id
            try {
                bugID = allValue.get(rowIndex).get(0).toString();
                log.info("Existing issues of test method \"" + testMethod + "\" is: " + bugID);
                return bugID;
            } catch (NullPointerException | IndexOutOfBoundsException ex) {
                log.info("No existing issues of test method \"" + testMethod + "\"");
                return "";
            }
        }
        return "";
    }

    public void updateTestNote(String sheetName, String testMethod, String note) {
        // Get existing row index of test method
        int rowIndex = getRowIndexOfTestMethod(sheetName, testMethod, false);
        log.info("--- Failed reason: " + note);
        sheetActions.batchUpdateNote(this.SPREADSHEETID, this.SHEETID_TYPE, rowIndex, rowIndex + 1, 13, 14, note);
    }

    public void updateTestStatus(String sheetName, String testMethod, List<List<Object>> values) {
        // Get existing row index of test method
        int rowIndex = getRowIndexOfTestMethod(sheetName, testMethod, false);
        if (rowIndex > -1) {
            // Overwrite existing row
            sheetActions.batchUpdateValues(
                    this.SPREADSHEETID,
                    sheetName
                            + String.format("!%s%d:%s%d", COL_DOMAIN, rowIndex + 1, COL_STATUS, rowIndex + 1),
                    "USER_ENTERED",
                    values); // sheetName + "!A" + (rowIndex + 1) + ":N" + (rowIndex + 1)
        } else {
            // Add new row
            sheetActions.appendValues(
                    this.SPREADSHEETID, sheetName + "!" + this.RANGE, "USER_ENTERED", values);
        }
    }

    //VUHOANG DEBUG: need to revise
    public void updateTestStatusForK8S(
            String sheetName, String testMethod, List<List<Object>> values) {
        // Get existing row index of test method
        int rowIndex = getRowIndexOfTestMethod(sheetName, testMethod, true);
        if (rowIndex > -1) {
            // Overwrite existing row
            sheetActions.batchUpdateValues(
                    this.SPREADSHEETID,
                    sheetName
                            + String.format("!%s%d:%s%d", COL_DOMAIN, rowIndex + 1, COL_STATUS, rowIndex + 1),
                    "USER_ENTERED",
                    values);
        } else {
            // Add new row
            sheetActions.appendValues(
                    this.SPREADSHEETID, sheetName + "!" + this.RANGE, "USER_ENTERED", values);
        }
    }

    public void checkTestStatusOnK8S(int timeOut, int timeDelay, int timeFirstDelay) {
        long maxWaitTime = System.currentTimeMillis() + timeOut * 1000;
        long currentWaitTime = System.currentTimeMillis();
        boolean check = true;
        boolean firstRun = true;

        if (timeFirstDelay > 0) {
            log.info("\n----- Delay before running test in " + timeFirstDelay + " seconds");
            delayStep(timeFirstDelay);
        }

        // Count running values
        int countRunning = 0;
        while ((currentWaitTime < maxWaitTime) && check) {
            // Wait for test run
            log.info("\n----- Delay test in " + timeDelay + " seconds");
            delayStep(timeDelay);
            currentWaitTime = System.currentTimeMillis();

            // Count null values
            countRunning = 0;

            // Get all value of suite info
            //VUHOANG add specified sheet
            String sheetScope = "";
            if (!SHEET_NAME_FIXED.isEmpty()) {
                sheetScope = SHEET_NAME_FIXED + String.format("!%s1:%s1000", COL_STATUS, COL_STATUS);
            } else {
                sheetScope = String.format("K8S_%s!%s1:%s1000", DOMAIN + "_BDD", COL_STATUS, COL_STATUS);
            }

            ValueRange valueRange =
                    sheetActions.getValues(
                            this.SPREADSHEETID,
                            sheetScope);

            List<List<Object>> allValue = valueRange.getValues();
            int totalRow = valueRange.getValues().size();
            for (int i = 2; i < totalRow; i++) {
                // Get cell value
                String cellValue = allValue.get(i).get(0).toString();

                // Check existing null value
                if (getStatusPrefixK8S().isEmpty()) {
                    if (cellValue.equalsIgnoreCase("running")) {
                        countRunning++;
                        continue;
                    } else if (cellValue.equalsIgnoreCase("FAILED") && !firstRun) {
                        log.debug(String.format("--- Row [%s] has status %s", String.valueOf(i + 1), "FAILED"));
                        check = false;
                        break;
                    }
                } else {
                    String prefixStatus = getStatusPrefixK8S() + "-";
                    if (cellValue.equalsIgnoreCase(prefixStatus + "running")) {
                        countRunning++;
                        continue;
                    } else if (cellValue.equalsIgnoreCase(prefixStatus + "FAILED") && !firstRun) {
                        check = false;
                        log.debug(String.format("--- Row [%s] has status %s", String.valueOf(i + 1), prefixStatus + "FAILED"));
                        break;
                    }
                }
                firstRun = false;
            }
            // If check=false then exiting failed test after running on k8s
            verifyEquals(check, true, FailureHandling.STOP_ON_FAILURE);
        }
        // If countNull>0, all test are not run completely, mark step failed
        if (!verifyEquals(countRunning, 0, FailureHandling.WARNING)) {
            String message = String.format("--- The test of service <%s> do not complete run in %d seconds.\n--- Total running time: %d"
                    , getStatusPrefixK8S(), timeOut, countRunning);
//            sendMessageToTelegramGroupDebug(message);
        }
    }

    public void updateStatusValues(String sheetName, String prefix, String value) {
        ValueRange valueRange =
                sheetActions.getValues(
                        this.SPREADSHEETID,
                        String.format("%s!%s3:%s", sheetName, COL_STATUS, COL_STATUS));
        List<List<Object>> allValue = valueRange.getValues();       // BUG: NULL. When Sheet is empty, this step will be a bug
        String newValue = "";

        for (int i = 0; i < allValue.size(); i++) {
            // Get cell value
            String cellValue = allValue.get(i).get(0).toString().toUpperCase();
            if (!prefix.isEmpty()) {
                if (cellValue.contains(prefix.toUpperCase())) {
                    newValue = prefix + "-" + value;
                    allValue.set(i, Arrays.asList(newValue.toUpperCase()));
                }
            }
        }
        // Overwrite existing row
        sheetActions.batchUpdateValues(
                this.SPREADSHEETID,
                String.format("%s!%s3:%s", sheetName, COL_STATUS, COL_STATUS),
                "USER_ENTERED",
                allValue);
    }

    public int getRowIndexOfTestMethod(String sheetName, String testMethod, boolean isK8SSheet) {
        // Get all value of test methods
        List<List<Object>> allValue;
        int totalRow;
        // For main test sheet
        String COL_METHODNAME = "F";
        if (!isK8SSheet) {
            if (testValueRangeMethod == null)
                testValueRangeMethod =
                        sheetActions.getValues(
                                this.SPREADSHEETID,
                                sheetName + String.format("!%s:%s", COL_METHODNAME, COL_METHODNAME));
            allValue = testValueRangeMethod.getValues();
            totalRow = testValueRangeMethod.getValues().size();
        }
        // For K8S sheet
        else {
            if ((testValueRangeMethodK8S == null && getIsFirstAddResultK8S())
                    || testValueRangeMethodK8S != null && !getIsFirstAddResultK8S()) {
                testValueRangeMethodK8S =
                        sheetActions.getValues(
                                this.SPREADSHEETID,
                                sheetName + String.format("!%s:%s", COL_METHODNAME, COL_METHODNAME));
            }
            allValue = Objects.requireNonNull(testValueRangeMethodK8S).getValues();
            totalRow = testValueRangeMethodK8S.getValues().size();
        }
        // Check existing test methods
        for (int i = 2; i < totalRow; i++) {
            try {
                if (allValue.get(i).get(0).equals(testMethod)) return i;
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        }
        // No existing test method
        return -1;
    }

    public void getSummaryResults() {
        List<Object> testSummary =
                sheetActions.getValues(this.SPREADSHEETID, this.SHEET_DASHBOARD_RANGE).getValues().get(0);
        int notRunUI =
                Integer.parseInt(testSummary.get(1).toString())
                        - Integer.parseInt(testSummary.get(2).toString())
                        - Integer.parseInt(testSummary.get(3).toString())
                        - Integer.parseInt(testSummary.get(5).toString());
        int notRunAPI =
                Integer.parseInt(testSummary.get(7).toString())
                        - Integer.parseInt(testSummary.get(8).toString())
                        - Integer.parseInt(testSummary.get(9).toString())
                        - Integer.parseInt(testSummary.get(11).toString());
        String text =
                "\n\t UI | API\n"
                        + "Total:   "
                        + testSummary.get(1)
                        + " | "
                        + testSummary.get(7)
                        + "\n"
                        + "Passed:  "
                        + testSummary.get(2)
                        + " | "
                        + testSummary.get(8)
                        + "\n"
                        + "Failed:  "
                        + testSummary.get(3)
                        + " | "
                        + testSummary.get(9)
                        + "\n"
                        + "Skipped: "
                        + testSummary.get(5)
                        + " | "
                        + testSummary.get(11)
                        + "\n"
                        + "Not Run: "
                        + notRunUI
                        + " | "
                        + notRunAPI;
        log.info(text);
    }

    /**
     * ========= Functions for SUITE =========
     */
    public void updateSuiteInfo(String suiteName, List<List<Object>> values) {
        // Get existing suites name
        ValueRange suiteValueRangeInfo =
                sheetActions.getValues(this.SPREADSHEETID, SHEET_TEST_SUITE_NAME + "!A1:D1000");
        List<List<Object>> allValue = suiteValueRangeInfo.getValues();
        int totalRow = suiteValueRangeInfo.getValues().size();
        for (int i = 2; i < totalRow; i++) {
            try {
                // Overwrite existing values
                if (allValue.get(i).get(0).equals(suiteName)) {
                    sheetActions.batchUpdateValues(
                            this.SPREADSHEETID,
                            SHEET_TEST_SUITE_NAME + "!A" + (i + 1) + ":E" + (i + 1),
                            "USER_ENTERED",
                            values);
                    return;
                }
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        }
        // Add new row
        sheetActions.appendValues(
                this.SPREADSHEETID, SHEET_TEST_SUITE_NAME + "!A3:D1000", "USER_ENTERED", values);
    }

    public void checkSuiteStatus(int timeOut, int timeDelay, int timeFirstDelay) {
        long maxWaitTime = System.currentTimeMillis() + timeOut * 1000;
        long currentWaitTime = System.currentTimeMillis();
        boolean check = true;
        boolean firstRun = true;

        if (timeFirstDelay > 0) {
            log.info("\n----- Delay before running test in " + timeFirstDelay + " seconds");
            delayStep(timeFirstDelay);
        }

        while ((currentWaitTime < maxWaitTime) && check) {
            // Wait for test run
            log.info("\n----- Delay test in " + timeDelay + " seconds");
            delayStep(timeDelay);
            currentWaitTime = System.currentTimeMillis();

            // Count null values
            int countNull = 0;

            // Get all value of status column
            if (suiteValueRangeStatus == null) {
                suiteValueRangeStatus =
                        sheetActions.getValues(this.SPREADSHEETID, SHEET_TEST_SUITE_NAME + "!D1:D1000");
            }
            List<List<Object>> allValue = suiteValueRangeStatus.getValues();
            int totalRow = suiteValueRangeStatus.getValues().size();
            String cellValue;
            for (int i = 2; i < totalRow; i++) {
                // Get cell value
                try {
                    cellValue = allValue.get(i).get(0).toString();
                } catch (IndexOutOfBoundsException e) {
                    continue;
                }
                // Check existing null value
                if (getStatusPrefixK8S().isEmpty()) {
                    if (cellValue.equalsIgnoreCase("null")) {
                        countNull++;
                        continue;
                    } else if (cellValue.equalsIgnoreCase("FAILED") && !firstRun && (countNull == 0)) {
                        check = false;
                        break;
                    }
                } else {
                    String prefixStatus = getStatusPrefixK8S() + "-";
                    if (cellValue.equalsIgnoreCase(prefixStatus + "null")) {
                        countNull++;
                        continue;
                    } else if (cellValue.equalsIgnoreCase(prefixStatus + "FAILED")
                            && !firstRun
                            && (countNull == 0)) {
                        check = false;
                        break;
                    }
                }
                firstRun = false;
            }
            // If check=false then exiting failed test after running on k8s
            verifyEquals(check, true, FailureHandling.STOP_ON_FAILURE);
        }
    }

}
