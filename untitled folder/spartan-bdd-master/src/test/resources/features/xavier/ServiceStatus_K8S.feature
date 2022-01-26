Feature: Service status inside k8s
# https://701search.atlassian.net/browse/G1-1863
# https://701search.atlassian.net/browse/G1-2019

  Scenario: Check user can see service status Active with green text on home page
    Given I have an active service (0 < available replicas = total replicas)
    When I go to Xavier home page
    Then I see that service has active status with green text

  Scenario: Check user can see service status Inactive with gray text on home page
    Given I have an inactive service (available replicas = 0 & lifecycle = deprecated)
    When I go to Xavier home page
    Then I see that service has Inactive status with gray text

  Scenario: Check user can see service status Warning with orange text on home page
    Given I have an inactive service (0 < available replicas < total replicas)
    When I go to Xavier home page
    Then I see that service has Warning status with orange text

  Scenario: Check user can see service status Down with red text on home page
    Given I have an inactive service (available = 0 & lifecycle != deprecated)
    When I go to Xavier home page
    Then I see that service has Down status with red text

  Scenario: Check user can see service status UNKNOWN with purple text on home page
    Given I have an Unknown service (No data on prometheus)
    When I go to Xavier home page
    Then I see that service has UNKNOWN status with purple text

  Scenario: Check service status is updated on Home page when changed
    Given I have an Active service (0<available replicas=total replicas)
    When I update service status to Down (available = 0 & lifecycle != deprecated)
    Then Service status is update to red text DOWN on Xavier home page
    When I update service status to Inactive (available replicas = 0 & lifecycle = deprecated)
    Then service status is update to gray text Inactive on Xavier home page