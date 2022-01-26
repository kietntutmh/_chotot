Feature: Detail page

  Scenario: Check user can click on Gitlab link to go to repository
    Given I am on detail page of Xavier
    When I click on Gitlab link without Git login info
    Then I cannot see repo page
    When I click on Git link with Git login info
    Then I can see repo page

  Scenario: Check user can click on Github link to go to repository
    Given I am on detail page of Xavier
    When I click on Github link without Git login info
    Then I cannot see repo page
    When I click on Git link with Git login info
    Then I can see repo page

  Scenario: Check user can click on Graylog link to go to log page
    Given I am on detail page of Xavier
    When I click on Graylog link without Graylog login info
    Then I cannot see log page
    When I click on Graylog link with Graylog login info
    Then I can see log page

  Scenario: Check user can click on Jaeger link to go to log page of enabled tracing service (has info in component.yaml file)
    Given I am on detail page of Xavier of an enabled tracing service
    When I click on Jaeger link without Jaeger login info
    Then I cannot see log page
    When I click on Jaeger link with Jaeger login info
    Then I can see log page

  Scenario: Check user can click on Jaeger link to go to log page of unabled tracing service  (has no info in component.yaml file)
    When I am on detail page of Xavier of an unabled tracing service
    Then I cannot see Jaeger link

  # https://701search.atlassian.net/browse/G1-2007
  Scenario: Check user can see namespace on Runtime section in Detail page
    When I am on detail page of Xavier
    Then I can see namespace on Runtime section

  # https://701search.atlassian.net/browse/G1-2006
  Scenario: Check user can see platform on Runtime section in Detail page
    When I am on detail page of Xavier
    Then I can see platform on Runtime section

  # https://701search.atlassian.net/browse/G1-2139
  Scenario: Check user can route to document page
    Given I am on Detail page
    When I click on Document icon in Runtime section
    Then I am routed to Document page

  Scenario: I can see Grafana Serverless link for CloudRun/CloudFunction service
    Given I am on Detail page of a CloudRun/CloudFunction
    When I click on Grafana Serverless link
    Then I can see Grafana page of that service

  Scenario: I can see more dynamic data for CloudRun service
    When I am on Detail page of CloudRun service
    Then I see data of Instance Time, Request Count, Latency Count in Runtime section

  Scenario: I can see more dynamic data for CloudFunction service
    When I am on Detail page of CloudRun service
    Then I see data of Active Instances in Runtime section

