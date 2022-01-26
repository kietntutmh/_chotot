Feature: Feedback
  #https://701search.atlassian.net/browse/G1-2094

  Scenario: Verify user can open Feedback form on anywhere in Xavier page
    Given I am on Xavier Home page
    When I click on Feedback icon on sidebar
    Then Feedback form displays
    When I click on Feedback icon on sidebar in Detail page
    Then Feedback form displays as well


  Scenario: Verify the sections of Feedback form
    When I am on Feedback form
    Then I can see sections Happy, Unhappy, Suggestion


  Scenario: Verify feedback data is sent to google sheet
    Given I am on Feedback form
    When I input info into desired section and click submit
    Then Data is sent to google sheet
    And Data is shown in corresponding type (Happy/Unhappy/Suggestion)


    #Can we consider feedback is sent to correct section in gsheet according to feedback type (3 types)?
    #A few scenario to consider:
    #Feedback icon when sidebar collapse
    #Feedback icon and text when sidebar is widen
    #User input special characters in textbox, then we should validate and will not allow it
    