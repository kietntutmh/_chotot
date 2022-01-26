package com.vn.chotot.configuration;

import com.vn.chotot.logger.Log4jFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;

public class Constant {

    static final Logger log = Log4jFactory.instance().createClassLogger(Constant.class);

    // Core
    public static final String SELENIUM_HOST = System.getProperty("seleniumHubHost");
    public static final String BROWSER_NAME = System.getProperty("browser");
    public static final String DRIVER_VERSION = System.getProperty("driverVersion");
    public static final String HEADLESS_MODE = System.getProperty("headlessMode");

    // Execution
    public static final Boolean RUN_ON_K8S = Boolean.valueOf(System.getProperty("runOnK8S"));
    public static final String TEST_RUN_TYPE = StringUtils.isEmpty(System.getProperty("testRunType")) ? "Develop_Script" : System.getProperty("testRunType");
    public static final String DOMAIN = StringUtils.isEmpty(System.getProperty("domain")) ? "org" : System.getProperty("domain");
    public static final Boolean INSERT_STATUS_COLUMN = Boolean.parseBoolean(System.getProperty("insertNewStatusColumn"));
    public static final String CUCUMBER_TAGS = System.getProperty("cucumber.filter.tags");
    public static final Boolean IS_CHECKING_POS = Boolean.valueOf(System.getProperty("isCheckingPOS"));
    public static final Boolean IS_FIRST_POD = Boolean.valueOf(System.getProperty("isFirstPod"));
    public static final Boolean IS_DEBUGGING = Boolean.valueOf(System.getProperty("isDebugging"));
    public static final String GG_TOKEN = StringUtils.isEmpty(System.getenv("GG_TOKEN")) ? "null" : System.getenv("GG_TOKEN");

    // Test flow
    // public static final Boolean CREATE_NEW_USER = Boolean.valueOf(System.getProperty("createNewUser"));
    public static final Boolean CREATE_NEW_USER = true;
    public static final String EXPECTED_USER_PHONE = StringUtils.isEmpty(System.getProperty("expectedUserPhone")) ? "" : System.getProperty("expectedUserPhone");
    public static final Boolean RANDOM_TESTCASE = false; // To execute each test case with skipping random case

    // Google sheet
    public static final Boolean UPDATE_STATUS = Boolean.valueOf(System.getProperty("updateStatus"));
    public static final String SHEET_NAME_FIXED = System.getProperty("sheetNameFixed");
    public static Boolean isFirstRunSuite = true; // To check test suite is run on first time

    private Constant() { }

    // For K8S
    public static String getStatusPrefixK8S() {
        return StringUtils.isEmpty(System.getProperty("k8sPrefixStatus")) ? "": System.getProperty("k8sPrefixStatus").toUpperCase();
    }

    public static boolean getCheckSuiteK8S() {
        // Check suite or test
        boolean k8sCheckSuiteStatus = Boolean.parseBoolean(System.getProperty("k8sCheckSuiteStatus"));
        log.info("\n----- k8sCheckSuiteStatus: ", k8sCheckSuiteStatus);
        return k8sCheckSuiteStatus;
    }

    public static int getTimeoutK8S() {
        int time = 180; // default
        try {
            time = Integer.parseInt(System.getProperty("k8sTimeOut"));
        } catch (NumberFormatException e) {
        }
        log.info("\n----- k8sTimeOut: " + time);
        return time;
    }

    public static int getTimeDelayK8S() {
        int time = 10; // default
        try {
            time = Integer.parseInt(System.getProperty("k8sTimeDelay"));
        } catch (NumberFormatException e) {
        }
        log.info("\n----- k8sTimeDelay: " + time);
        return time;
    }

    public static int getTimeFirstDelayK8S() {
        int time = 120; // default
        try {
            time = Integer.parseInt(System.getProperty("k8sTimeFirstDelay"));
        } catch (NumberFormatException e) {
        }
        log.info("\n----- k8sTimeFirstDelay: " + time);
        return time;
    }
}
