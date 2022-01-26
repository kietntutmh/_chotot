Feature: Login Functionality of Chotot

  Scenario: : Steps to setup environment (REQUIRED)
    Given I am on "Chotot" website
    And I register a new Chotot User


  Scenario: Condition Steps (REQUIRED before Action Steps)
    Given I am on the web page "Login Page"
    Then I should be on the web page "Home Page"


  Scenario: Action Steps
    When I login with username "vuhoang" and password "123456"
    When I enter "Element Name" as "Element Value"
    When I click on "Element Name"
    When I move to and click on "Element Name"


  Scenario: Verify Steps
    Then I should be on the web page "Home Page"
    Then I should see all elements of the web page "Home Page"
    Then I should see "Validation Err" displays
    Then I should see "Validation Err" displays with text "Exact text on the page"
    Then I should see "Validation Err" displays with text containing "Text contains in another on the page"
    Then I should see "Validation Err" displays with text inner as "Text on the page"
    Then I should see "Validation Err" has the attribute "Attribute Name" as "Attribute Value"

    Then I should see the "username" tool-tip field require displays as "Please fill out this field."

  Scenario: Verify Steps of Chotot
    Then I should see the username and password tool-tip field requires



