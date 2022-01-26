@SERVICE_PROMOTION
@TELEGRAM_ME
@TAG_VUHOANG_QUANGTRAN
@PROMOTION_NEWFLOW_REDEEM
Feature: Credit Promotion New Flow Redeem - Promotion Credit API

  Background:
    Given I login my Account

  @AUTHOR_QUANGTRAN_ME_API @DONE
  Scenario: As an User, I can not redeem a promotion code when the campaign is deactivated
    When ChoTot add a new Public campaign of promotion campaign for All users
      | Credit Value | Max Redeem | Max Per Account Redeem | Code Quantity | Code Redeem Limitation |
      | 10000        | 10000      | 1000                   | 5             | 1                      |
    And I should see the campaign is created with status is New
    And I should see the promotion codes are auto generated
    Then I can not redeem a promotion code when the campaign is deactivated

  @AUTHOR_QUANGTRAN_ME_API @DONE
  Scenario: As an User, I can redeem a promotion code when the campaign is activated (Internal)
    When ChoTot add a new Public campaign of promotion campaign for All users
      | Credit Value | Max Redeem | Max Per Account Redeem | Code Quantity | Code Redeem Limitation |
      | 10000        | 10000      | 10000                  | 5             | 1                      |
    And The promotion campaign is created
    And I activate the promotion campaign
    Then I can redeem a promotion code with Internal request
    And The credit is received by user

  @AUTHOR_QUANGTRAN_ME_API @DONE
  Scenario: As an User, I can redeem a promotion code when the campaign is activated (Private)
    When ChoTot add a new Public campaign of promotion campaign for All users
      | Credit Value | Max Redeem | Max Per Account Redeem | Code Quantity | Code Redeem Limitation |
      | 10000        | 10000      | 10000                  | 5             | 1                      |
    And The promotion campaign is created
    And I activate the promotion campaign
    Then I can redeem a promotion code with Private request
    And The credit is received by user

  @AUTHOR_QUANGTRAN_ME_API @DONE
  Scenario: As an User, I can not redeem a promotion code which is out of quantity
    When ChoTot add a new Public campaign of promotion campaign for All users
      | Credit Value | Max Redeem | Max Per Account Redeem | Code Quantity | Code Redeem Limitation |
      | 10000        | 10000      | 10000                  | 5             | 1                      |
    And The promotion campaign is created
    And I activate the promotion campaign
    And I can redeem a promotion code with Internal request
    And The credit is received by user
    Then I can not redeem a promotion code that is out of quantity

  @AUTHOR_QUANGTRAN_ME_API @DONE
  Scenario: As an User, I can not redeem a promotion code of future campaign
    When ChoTot add a new future promotion campaign
      | Credit Value | Max Redeem | Max Per Account Redeem | Code Quantity | Code Redeem Limitation |
      | 10000        | 10000      | 1000                   | 5             | 1                      |
    And I should see the campaign is created with status is New
    And I should see the promotion codes are auto generated
    And I activate the future promotion campaign
    Then I can not redeem a promotion code of the future campaign

  @AUTHOR_QUANGTRAN_ME_API @DONE
  Scenario: As an User, I can redeem a promotion code if the phone number were wrong
    When ChoTot add a new Public campaign of promotion campaign for All users
      | Credit Value | Max Redeem | Max Per Account Redeem | Code Quantity | Code Redeem Limitation |
      | 10000        | 10000      | 10000                   | 5             | 1                      |
    And I should see the promotion codes are auto generated
    And I activate the promotion campaign
    Then I can redeem a promotion code with the Phone number were wrong
    And The credit is received by user

  @AUTHOR_QUANGTRAN_ME_API @DONE
  Scenario: As an User, I can not redeem a promotion code if the account ID were wrong
    When ChoTot add a new Public campaign of promotion campaign for All users
      | Credit Value | Max Redeem | Max Per Account Redeem | Code Quantity | Code Redeem Limitation |
      | 10000        | 10000      | 10000                   | 5             | 1                      |
    When The promotion campaign is created
    And I should see the promotion codes are auto generated
    When I activate the promotion campaign
    Then I can not redeem a promotion code with the Account ID were wrong
    And The free credit is not received by user

  @AUTHOR_QUANGTRAN_ME_API @DONE
  Scenario: As an User, I can not redeem a promotion code when reaching Max Per Account Redeem
    When ChoTot add a new Public campaign of promotion campaign for All users
      | Credit Value | Max Redeem | Max Per Account Redeem | Code Quantity | Code Redeem Limitation |
      | 10000        | 10000      | 1                      | 5             | 2                      |
    And I should see the promotion codes are auto generated
    And I activate the promotion campaign
    And I can redeem a promotion code with Internal request
    And The credit is received by user
    Then I can not redeem a promotion code when reaching Max Per Account Redeem

  @AUTHOR_QUANGTRAN_ME_API @DONE
  Scenario: As an User, I can not redeem a promotion code when editing start date to a date in the future
    When ChoTot add a new Public campaign of promotion campaign for All users
      | Credit Value | Max Redeem | Max Per Account Redeem | Code Quantity | Code Redeem Limitation |
      | 10000        | 10000      | 1                      | 5             | 2                      |
    And I should see the promotion codes are auto generated
    When I edit Date Start of the promotion campaign to a date in the Future
    Then I can not redeem a promotion code when when editing start date to invalid

  @AUTHOR_QUANGTRAN_ME_API @DONE
  Scenario: As an User, I can not redeem a promotion code when editing end date to a date in the past
    When ChoTot add a new Public campaign of promotion campaign for All users
      | Credit Value | Max Redeem | Max Per Account Redeem | Code Quantity | Code Redeem Limitation |
      | 10000        | 10000      | 1                      | 5             | 2                      |
    And I should see the promotion codes are auto generated
    When I edit Date End of the promotion campaign to a date in the Past
    Then I can not redeem a promotion code when when editing start date to invalid

  @AUTHOR_QUANGTRAN_ME_API @DONE
  Scenario: As an User, I can redeem a promotion code of private promotion campaign
    When ChoTot add a new Private campaign of promotion campaign for All users
      | Credit Value | Max Redeem | Max Per Account Redeem | Code Quantity | Code Redeem Limitation |
      | 10000        | 10000      | 1000                   | 5             | 1                      |
    And I should see the campaign is created with status is New
    And I should see the promotion codes are auto generated
    And I activate the promotion campaign
    Then I can redeem a promotion code with Internal request
    And The credit is received by user