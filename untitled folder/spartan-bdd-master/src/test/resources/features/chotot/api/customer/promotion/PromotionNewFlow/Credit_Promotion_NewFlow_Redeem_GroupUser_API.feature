@SERVICE_PROMOTION
@TELEGRAM_ME
@TAG_VUHOANG_QUANGTRAN
@PROMOTION_NEWFLOW_USERGROUP_REDEEM
Feature: Credit Promotion New Flow Redeem - Promotion Credit API

  Background:
    Given I create a new User Group
    When I add "3" new users into my User Group
    And The users should be added to the User Group
    And The users should contain in the User Group

    # Redeem by User Group
  @AUTHOR_QUANGTRAN_ME_API @DONE
  Scenario: User is in specific user group can redeem a promotion code of PRIVATE promotion campaign
    When ChoTot add a new Private campaign of promotion campaign for a Group of User
      | Credit Value | Max Redeem | Max Per Account Redeem | Code Quantity | Code Redeem Limitation |
      | 10000        | 10000      | 1000                   | 5             | 1                      |
    And I should see the campaign is created with status is New
    And I should see the promotion codes are auto generated
    And I activate the promotion campaign
    Then User in group can redeem a promotion code
    And The credit is received by user in group

  @AUTHOR_QUANGTRAN_ME_API @DONE
  Scenario: User is not in specific user group can not redeem a promotion code of PRIVATE promotion campaign
    When ChoTot add a new Private campaign of promotion campaign for a Group of User
      | Credit Value | Max Redeem | Max Per Account Redeem | Code Quantity | Code Redeem Limitation |
      | 10000        | 10000      | 1000                   | 5             | 1                      |
    And I should see the campaign is created with status is New
    And I should see the promotion codes are auto generated
    And I activate the promotion campaign
    Then User is not in group can not redeem a promotion code
    And The free credit is not received by user is not in group

  @AUTHOR_QUANGTRAN_ME_API @DONE
  Scenario: User is not in specific user group can redeem a promotion code of PUBLIC promotion campaign
    When ChoTot add a new Public campaign of promotion campaign for a Group of User
      | Credit Value | Max Redeem | Max Per Account Redeem | Code Quantity | Code Redeem Limitation |
      | 10000        | 10000      | 1000                   | 5             | 1                      |
    And I should see the campaign is created with status is New
    And I should see the promotion codes are auto generated
    And I activate the promotion campaign
    Then User is not in group can redeem a promotion code
    And The credit is received by user is not in group

  @AUTHOR_QUANGTRAN_ME_API @DONE
  Scenario: User is in specific user group can redeem a promotion code of PUBLIC promotion campaign
    When ChoTot add a new Public campaign of promotion campaign for a Group of User
      | Credit Value | Max Redeem | Max Per Account Redeem | Code Quantity | Code Redeem Limitation |
      | 10000        | 10000      | 1000                   | 5             | 1                      |
    And I should see the campaign is created with status is New
    And I should see the promotion codes are auto generated
    And I activate the promotion campaign
    Then User in group can redeem a promotion code
    And The credit is received by user in group

        # AUTO Redeem by User Group
  Scenario: As an user in group, I can receive DT Free by Auto-Redeem of PRIVATE-GROUP promotion campaign
    When ChoTot add a new Private campaign of promotion campaign for a Group of User
      | Credit Value | Max Redeem | Max Per Account Redeem | Code Quantity | Code Redeem Limitation |
      | 10000        | 10000      | 1000                   | 1             | 1                      |
    And I should see the campaign is created with status is New
    And I should see the promotion codes are auto generated
    And I activate the promotion campaign
    Then Redeem promotion code for group of user by Auto Redeem
    And The credit is received by all users in group

  Scenario: As an user in group, I can receive DT Free by Auto-Redeem of PUBLIC-GROUP promotion campaign
    When ChoTot add a new Public campaign of promotion campaign for a Group of User
      | Credit Value | Max Redeem | Max Per Account Redeem | Code Quantity | Code Redeem Limitation |
      | 10000        | 10000      | 1000                   | 1             | 1                      |
    And I should see the campaign is created with status is New
    And I should see the promotion codes are auto generated
    And I activate the promotion campaign
    Then Redeem promotion code for group of user by Auto Redeem
    And The credit is received by all users in group

  Scenario: As an user is not in group, I can not receive DT Free by Auto-Redeem of PRIVATE-GROUP promotion campaign
    When ChoTot add a new Private campaign of promotion campaign for a Group of User
      | Credit Value | Max Redeem | Max Per Account Redeem | Code Quantity | Code Redeem Limitation |
      | 10000        | 10000      | 1000                   | 1             | 1                      |
    And I should see the campaign is created with status is New
    And I should see the promotion codes are auto generated
    And I activate the promotion campaign
    When I login my Account
    Then Redeem promotion code for group of user by Auto Redeem
    And The free credit is not received by user is not in group
    And The credit is received by all users in group

  Scenario: As an user is not in group, I can not receive DT Free by Auto-Redeem of PUBLIC-GROUP promotion campaign
    When ChoTot add a new Public campaign of promotion campaign for a Group of User
      | Credit Value | Max Redeem | Max Per Account Redeem | Code Quantity | Code Redeem Limitation |
      | 10000        | 10000      | 10000                  | 1             | 1                      |
    And I should see the campaign is created with status is New
    And I should see the promotion codes are auto generated
    And I activate the promotion campaign
    When I login my Account
    Then Redeem promotion code for group of user by Auto Redeem
    And The free credit is not received by user is not in group
    And The credit is received by all users in group

  Scenario: As an user in group, I can not receive DT Free by Auto-Redeem of INACTIVATED PRIVATE-GROUP promotion campaign
    When ChoTot add a new Private campaign of promotion campaign for a Group of User
      | Credit Value | Max Redeem | Max Per Account Redeem | Code Quantity | Code Redeem Limitation |
      | 10000        | 10000      | 1000                   | 1             | 1                      |
    And I should see the campaign is created with status is New
    Then Auto Redeem for group of user is FAILED
    And The credit is not received by all users in group

  Scenario: As an user in group, I can not receive DT Free by Auto-Redeem of DEACTIVATED PRIVATE-GROUP promotion campaign
    When ChoTot add a new Private campaign of promotion campaign for a Group of User
      | Credit Value | Max Redeem | Max Per Account Redeem | Code Quantity | Code Redeem Limitation |
      | 10000        | 10000      | 1000                   | 1             | 1                      |
    And I should see the campaign is created with status is New
    And I activate the promotion campaign
    And I deactivate the promotion campaign
    Then Auto Redeem for group of user is FAILED
    And The credit is not received by all users in group

  Scenario: As an user in group, I can receive DT Free by Auto-Redeem of RE-ACTIVATED PRIVATE-GROUP promotion campaign
    When ChoTot add a new Private campaign of promotion campaign for a Group of User
      | Credit Value | Max Redeem | Max Per Account Redeem | Code Quantity | Code Redeem Limitation |
      | 10000        | 10000      | 1000                   | 1             | 1                      |
    And I should see the campaign is created with status is New
    And I activate the promotion campaign
    And I deactivate the promotion campaign
    And I activate the promotion campaign
    Then Redeem promotion code for group of user by Auto Redeem
    And The credit is received by all users in group

  Scenario: As an user, I can not receive DT Free by Auto-Redeem of PUBLIC-ALL promotion campaign
    When ChoTot add a new Public campaign of promotion campaign for All users
      | Credit Value | Max Redeem | Max Per Account Redeem | Code Quantity | Code Redeem Limitation |
      | 10000        | 10000      | 10000                  | 1             | 1                      |
    And I should see the promotion codes are auto generated
    And I activate the promotion campaign
    When I login my Account
    Then Redeem promotion code for group of user by Auto Redeem
    And The free credit via auto redeem is not received by user

  Scenario: As an user, I can not receive DT Free by Auto-Redeem of PRIVATE-ALL promotion campaign
    When ChoTot add a new Private campaign of promotion campaign for All users
      | Credit Value | Max Redeem | Max Per Account Redeem | Code Quantity | Code Redeem Limitation |
      | 10000        | 10000      | 1000                   | 1             | 1                      |
    And I should see the promotion codes are auto generated
    And I activate the promotion campaign
    When I login my Account
    Then Redeem promotion code for group of user by Auto Redeem
    And The free credit via auto redeem is not received by user