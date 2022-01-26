# spartan-bdd
This automation framework is created using Java + Selenium WebDriver + TestNG + Cucumber

#
# Prerequisites
- Java JDK 1.8(recommend) or higher
- Maven 3.5 or higher


# Using
- Log4j for logging
- Automatically get the latest version of web-driver


# Maven cmd
mvn clean -Dcucumber.filter.tags="@INSERTAD_FASHION_API" test


# Tagging
- Eg: Tag for each feature file
@SERVICE_<NAME>
@<FEATURE_NAME>_UI/API
Feature: <Feature name> on UI/API

    @SMOKE (--optional--)
        Scenario:...
    
    @AUTHOR_<AUTHOR_NAME>_<SQUAD>_UI/API
        Scenario...

# Parameters for running from cmd
-DheadlessMode: true - run test in headless mode (non-GUI mode)
-> E.g: -DheadlessMode=true

-DisProduction: true - run test on production domain (.com)
-> E.g: -DisProduction=false

-DtestRunType: Nightly, Develop_Script, Deploy_Service - test run on nightly, in-development or after deploy new code for services
-> E.g: -DtestRunType=Develop_Script


# Parameters for updating results to Google Sheet 
- Link: https://docs.google.com/spreadsheets/d/1lCDJYDOf2reMlVOHXeCGbRw03ol6RivNuouVif1AN3E/edit#gid=799710897

-DinsertNewStatusColumn: true - insert new <Status> column to google sheeet
-> E.g: -DinsertNewStatusColumn=true

-DupdateStatus: true - update result to google sheet
-> E.g: -DupdateStatus=true

-DcreateNewUser: true - create new account based on timestamp
-> E.g: -DcreateNewUser=true


# Parameters for running on k8s
-DrunOnK8S: true - run test on Kubernetes
-> E.g: -DrunOnK8S=true

-Dk8sTimeOut: timeout to run test on k8s 
-> E.g: -Dk8sTimeOut=210

-Dk8sTimeDelay: interval time to check test status on k8s
-> -Dk8sTimeDelay=30

-DcheckSuiteStatus: true - check suite staus on sheet "TEST_SUITE"
-> -DcheckSuiteStatus=true

-DisDebugging: true - keep browser open after running test
-> -DisDebugging=true


# Docker cmd
- Build: docker build -t docker.chotot.org/$DIST_NAME:$VERSION .
-> E.g: docker build -t docker.chotot.org/spartan-bdd:1.0 .

- Push: docker push docker.chotot.org/$DIST_NAME:$VERSION
-> E.g: docker push docker.chotot.org/spartan-bdd:1.0

