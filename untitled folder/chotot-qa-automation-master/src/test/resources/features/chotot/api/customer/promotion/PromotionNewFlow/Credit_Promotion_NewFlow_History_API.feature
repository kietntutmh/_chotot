
Feature: Credit Promotion New Flow Redeem - Promotion Credit API

  Background:
    Given I login my Account

  @AUTHOR_QUANGTRAN_ME_API @DONE
  Scenario: History is logged when creating a promotion code campaign
    When ChoTot add a new Public campaign of promotion campaign for All users
      | Credit Value | Max Redeem | Max Per Account Redeem | Code Quantity | Code Redeem Limitation |
      | 10000        | 10000      | 1000                   | 5             | 1                      |
    Then I see Create Campaign action logged in History

  @AUTHOR_QUANGTRAN_ME_API @DONE
  Scenario: History is logged when editing info of the promotion code campaign
    When The promotion campaign is created
    And I edit info of the promotion code campaign
    Then I see Edit Campaign action logged in History

  @AUTHOR_QUANGTRAN_ME_API @DONE
  Scenario: History is logged when editing status of the promotion code campaign
    When The promotion campaign is created
    And I change the promotion code campaign status from New to Approved
    Then I see change status from New to Approved action logged in History
    When I change the promotion code campaign status from Approved to Inactive
    Then I see change status from Approved to Inactive action logged in History
    And I change the promotion code campaign status from Inactive to Approved

  @AUTHOR_QUANGTRAN_ME_API @DONE
  Scenario: History is logged when redeem code of the promotion code campaign
    When The promotion campaign is created
    Then I can redeem a promotion code with Internal request
    And The credit is received by user
    And I see redeem action logged in History