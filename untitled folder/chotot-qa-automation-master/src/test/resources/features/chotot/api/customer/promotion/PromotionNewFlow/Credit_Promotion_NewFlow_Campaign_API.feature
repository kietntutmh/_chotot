@SERVICE_PROMOTION
@TELEGRAM_ME
@TAG_VUHOANG_QUANGTRAN
@PROMOTION_NEWFLOW_CAMPAIGN
Feature: Credit Promotion New Flow Campaign - Promotion Credit API

  Background:
    Given I login my Account

  @AUTHOR_QUANGTRAN_ME_API @DONE
  Scenario: ChoTot can create a promotion campaign
    When ChoTot add a new Public campaign of promotion campaign for All users
      | Credit Value | Max Redeem | Max Per Account Redeem | Code Quantity | Code Redeem Limitation |
      | 10000        | 10000      | 1000                   | 5             | 1                      |
    Then I should see the campaign is created with status is New
    And I should see the promotion codes are auto generated

  @AUTHOR_QUANGTRAN_ME_API @DONE
  Scenario: When re-activating, the campaign is not auto-generate more promotion codes
    When ChoTot add a new Public campaign of promotion campaign for All users
      | Credit Value | Max Redeem | Max Per Account Redeem | Code Quantity | Code Redeem Limitation |
      | 10000        | 10000      | 1000                   | 5             | 1                      |
    And I should see the promotion codes are auto generated
    And I activate the promotion campaign
    And I deactivate the promotion campaign
    Then I should see there is no more promotion codes are generated

  @AUTHOR_QUANGTRAN_ME_API @DONE
  Scenario: As an User, I can not create a promotion campaign with some values are invalid
    Then I can not create a promotion campaign with Free Value is invalid
    And I can not create a promotion campaign with Max Redeem is invalid
    And I can not create a promotion campaign with Max Per Account Redeem is invalid
    And I can not create a promotion campaign with Date Start is invalid
    And I can not create a promotion campaign with Date End is invalid

  @AUTHOR_QUANGTRAN_ME_API @DONE
  Scenario: Promotion Code is not generated with Code Quantity is 0
    When ChoTot add a new Public campaign of promotion campaign for All users
      | Credit Value | Max Redeem | Max Per Account Redeem | Code Quantity | Code Redeem Limitation |
      | 10000        | 10000      | 1000                   | 0             | 1                      |
    Then I should see the promotion codes are not generated

  @AUTHOR_QUANGTRAN_ME_API @DONE
  Scenario: There is no valid code generated with Code Redeem Limitation is 0
    When ChoTot add a new Public campaign of promotion campaign for All users
      | Credit Value | Max Redeem | Max Per Account Redeem | Code Quantity | Code Redeem Limitation |
      | 10000        | 10000      | 1000                   | 5             | 0                      |
    Then I should see there is no valid code generated