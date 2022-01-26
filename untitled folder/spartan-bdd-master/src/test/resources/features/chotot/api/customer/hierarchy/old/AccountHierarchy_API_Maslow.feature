@SERVICE_HIERARCHY
@CUSTOMER
@TAG_VUHOANG_MINHTRAN
@HIERARCHY_MASLOW
Feature: Account Hierarchy API - Maslow

  #------------- Account Management : Link/Unlink -------------

#  @AUTHOR_VUHOANG_ME_API  @DONE
#  Scenario: Chotot upgrades an existing account to Biz Account
#    Given I register "2" Chotot Users to register Biz, Child accounts
#    When Chotot upgrades my account to Biz Account
#    Then My account should be Biz Account
#
#  @AUTHOR_VUHOANG_ME_API  @DONE
#  Scenario: Biz Account can link to an active account to be Child Account
#    Given I register "2" Chotot Users to register Biz, Child accounts
#    When Chotot links my Biz Account to my Child Account
#    Then My Child Account should be linked to my Biz Account
#    And My Biz Account should be linked to my Child Account
#
#  @AUTHOR_VUHOANG_ME_API  @DONE
#  Scenario: Biz Account can not link to an inactive/blocked/delted account to be Child Account
#    Given I register a new account that is upgraded to Biz Account
#    When Chotot links my Biz Account to my inactive Child Account
#    Then My Biz Account should not be linked to my Child Account
#
#  @AUTHOR_VUHOANG_ME_API  @DONE
#  Scenario: Biz Account can link to many active account to be Child Account
#    Given I register a new Biz Account that isn't linked to any Child Account
#    And I register "3" Child Accounts
#    When Chotot links my Biz Account to all my Child Accounts
#    Then Each of My Child Account should be linked to my Biz Account
#    And My Biz Account should be linked to all of my Child Accounts
#    And My Biz Account should contain my "3" Child Accounts
#
#  @AUTHOR_VUHOANG_ME_API @DONE
#  Scenario: Biz Account can't link to a same child Account on 2 times
#    Given I register a new account that is upgraded to Biz Account
#    And I register a Child Account
#    When Chotot links my Biz Account to my Child Account on 2 times
#    Then My Child Account should not be linked to my Biz Account on 2nd times and error responses
#
#  @AUTHOR_VUHOANG_ME_API  @DONE
#  Scenario: Biz Account can unlink to Child Accounts
#    Given I register a new account that is upgraded to Biz Account but not link with any Child Account
#    And I register "2" Child Accounts which are linked to my Biz Account
#    When Chotot unlinks my Biz Account to my Child Accounts
#    Then My Child Accounts should be unlinked
#    Then My Child Accounts should become Normal Account
#    And My Biz Account should be unlinked to all of my Child Accounts
#    And My Biz Account should have "0" Active Child Accounts
#
#  @AUTHOR_VUHOANG_ME_API  @DONE  @LOI_CODE
#  Scenario: Biz Account can link to an Child Account that was unlinked before
#    Given I register a new account that is upgraded to Biz Account but not link with any Child Account
#    And I register a Child Account that is linked to my Biz Account
#    When Chotot unlinks my Biz Account to my Child Accounts
#    And Chotot links my Biz Account to my Child Account again
#    Then My Child Account should be linked to my Biz Account
#    And My Biz Account should be linked to my Child Account
#
#  @AUTHOR_VUHOANG_ME_API @DONE
#  Scenario: Child Account can't link to another Child Account as Biz Account. Biz can't link to Biz
#    Given "Unlink" API isn't finished yet. Wait for Customer Dev to upload the code
#    Given I register a Child Account A that is linked to my Biz Account A
#    And I register a Child Account B that is linked to my Biz Account B
#    Then My Child Account A should not be able to link to my Child Account B
#    And My Biz Account A should not be able to link to my Biz Account B
#
#  @AUTHOR_VUHOANG_ME_API @DONE
#  Scenario: Biz Account can't link to a Child Account of other Biz Account
#    Given I register a Child Account A that is linked to my Biz Account A
#    And I register a Child Account B that is linked to my Biz Account B
#    Then My Biz Account A should not be able to link to my Child Account B
#    And My Biz Account B should not be able to link to my Child Account A
#
#  @AUTHOR_VUHOANG_ME_API @DONE
#  Scenario: Child Account can not link to a Biz Account
#    Given I register a new account that is upgraded to Biz Account
#    And I register a Child Account
#    Then My Child Account should not able to be linked to my Biz Account
#
#  #------------- Account Management -------------
#  @AUTHOR_VUHOANG_ME_API  @DONE
#  Scenario: Check the limitation of Child Account amount linked to Biz Account
#    Given I register a new Biz Account that isn't linked to any Child Account
#    When I register "30" Child Accounts which are linked to my Biz Account
#    Then All of my Child Accounts should be linked to my Biz Account
#    And My Biz Account should be linked to all of my Child Accounts
#    And My Biz Account should contain my "30" Child Accounts
#
#
#  @AUTHOR_VUHOANG_ME_API  @DONE
#  Scenario: Biz Account can view the list of Child Accounts including linked & unlinked accounts
#    Given I register a new account that is upgraded to Biz Account but not link with any Child Account
#    And I register a Child Account A that is linked to my Biz Account
#    And I register a Child Account B that is linked to my Biz Account
#    When Chotot unlinks my Biz Account to my Child Account A
#    Then My Biz Account should have "1" Active Child Accounts
#    And My Biz Account should have "1" Deleted Child Accounts
#    Then My Child Account A should be unlinked
#    Then My Child Account B should be linked
#
#  @AUTHOR_VUHOANG_ME_API  @DONE
#    Scenario: Admin Tool, Biz Parent List displays correct info
#    Given I register a new account that is upgraded to Biz Account but not link with any Child Account
#    And I register "3" Child Account that is linked to my Biz Account
#    Then My Biz Account should display in Biz Parent List with "3" Childs

  # ----------Multiple transaction---------------
  # for one account
  @AUTHOR_VUHOANG_ME_API  @HIERARCHY_TS_053
  Scenario: Pay Order, Child Account try to pay more than 1 transaction at the same time
      # Expected tính toán đúng và đủ balance, Lưu đủ history

  #for Multiple child account
  @AUTHOR_VUHOANG_ME_API  @HIERARCHY_TS_054
  Scenario: Pay Order, More than 1 child Account try to pay at the same time
      # Expected tính toán đúng và đủ balance, Lưu đủ history

  # for biz account
  @AUTHOR_VUHOANG_ME_API  @HIERARCHY_TS_055
  Scenario: Pay Order, Biz Account try to pay more than 1 transaction at the same time
     # Expected tính toán đúng và đủ balance, Lưu đủ history

  #  Scenario: Order History, Biz Account can view DT4B history of the organization


#  @AUTHOR_VUHOANG_ME_API  @PHRASE2
#  Scenario: Order History, Child Account can view Child history after unlinked from Biz Account
#    Given I register my Biz Account that has DT4B balance "100000"
#    When I use Child Account to pay for premium services with "15000" by DT4B
#    When Chotot unlinks my Child Account from my Biz Account
#    Then I should see the POS order of my Child Account in Child Order History is "15000"

      #  @AUTHOR_VUHOANG_ME_API  @PHRASE2
#  Scenario: Order History, Biz Account can view DT4B history of Unlinked account
#    Given I login with my Biz Account that has DT4B balance "100000"
#    When I use Child Account to pay for premium services with "15000"
#    When Chotot unlinks my Child Account from my Biz Account
#    Then I should see the payment of my Child Account in Biz History is "15000"

  #  @AUTHOR_VUHOANG_ME_API
#  Scenario: Child Account can unlink from Biz Account
#    Given I register a new account that is upgraded to Biz Account but not link with any Child Account
#    And I register a Child Account that is linked to my Biz Account
#    When Chotot unlinks my Child Account from my Biz Account
#    Then My Child Accounts should be unlinked
#    And My Biz Account should be linked to my Child Account

