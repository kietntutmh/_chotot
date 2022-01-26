Feature: Service status on GCP
# https://701search.atlassian.net/browse/G1-2017

  Scenario: Check user can see service status Active of Cloud Function with green text on home page
    Given I have an active service of Cloud Function(active instance > 0)
    When I go to Xavier home page
    Then I see that service has active status with green text

  Scenario: Check user can see GCP service status UNKNOWN of Cloud Function with purple text on home page
    Given I have an Unknown service of Cloud Function(No data on prometheus)
    When I go to Xavier home page
    Then I see that service has UNKNOWN status with purple text

  Scenario: Check user can see service status Active of Cloud Run with green text on home page
    Given I have an active service of Cloud Run(has Runtime data on Prometheus)
    When I go to Xavier home page
    Then I see that service has active status with green text

  Scenario: Check user can see GCP service status UNKNOWN of Cloud Run with purple text on home page
    Given I have an Unknown service of Cloud Run(No data on prometheus)
    When I go to Xavier home page
    Then I see that service has UNKNOWN status with purple text