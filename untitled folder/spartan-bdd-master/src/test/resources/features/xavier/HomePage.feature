Feature: Home page

  #https://701search.atlassian.net/browse/G1-2020
  Scenario: Check Home page loads existing services less then 1 second
    When I go to Xavier Home page
    Then I see that the services take less than 1 second to load

  #https://701search.atlassian.net/browse/G1-2099
  Scenario: Check Status update is reduced from 2 minutes to 10 seconds
    When I go to Xavier Home page
    Then I see that the services status take 10 seconds to update

  #https://701search.atlassian.net/browse/G1-2018
  Scenario: Verify user can filter service status
    Given I am on Xavier Home page
    When I click on status icon in filter section
    Then I see that the list of services is updated corresponding to filtered status

  #https://701search.atlassian.net/browse/G1-2087
  Scenario: Verify user can click on a link and an icon to go to Github repository
    Given I am on Xavier Home page
    When I click on Github icon of a service
    Then I can go to Github repository page of selected service

  #https://701search.atlassian.net/browse/G1-2086
  Scenario: Verify user can click on a Helm chart icon to go to helm page
    Given I am on Xavier Home page
    When I click on Helm chart icon of a service
    Then I can go to Helm chart page of selected service

  #https://701search.atlassian.net/browse/G1-2085
  Scenario: Verify user can click on a Graylog to go to log page
    Given I am on Xavier Home page
    When I click on Graylog icon of a service
    Then I can go to Graylog page of selected service

  #https://701search.atlassian.net/browse/G1-2084
  Scenario: Verify user can click on a Jaeger icon to go to tracing page
    Given I am on Xavier Home page
    When I click on Jaeger icon of a service
    Then I can go to Jaeger page of selected service

  #https://701search.atlassian.net/browse/G1-1868
  Scenario: Verify user can click a SonarQube link to go to Sonar dashboard
    Given I am on Xavier Home page
    When I click on SonarQube icon of a service
    Then I can go to SonarQube page of selected service

  #https://701search.atlassian.net/browse/G1-1867
  Scenario: Verify user can click a Grafana icon to go to service's metric dashboard
    Given I am on Xavier Home page
    When I click on Grafana icon of a service
    Then I can go to Grafana page of selected service