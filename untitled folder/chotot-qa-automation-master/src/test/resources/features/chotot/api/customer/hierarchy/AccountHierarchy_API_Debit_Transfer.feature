@SERVICE_HIERARCHY
@CUSTOMER
@TAG_VUHOANG_TUANCHIEU
@HIERARCHY_DEBIT_TRANSFER_2
Feature: Account Hierarchy API - Debit Approach - Transfer

  # Đồng Tốt 365
  @AUTHOR_VUHOANG_ME_API  @AH_DEBIT_004  @AH_DEBIT_001  @SMOKE
  Scenario: Parent can transfer DT365 to multiple Childs
    Given I login my Biz Account with "100000" Đồng Tốt 365
    When I login "2" Child Accounts to receive DT transfer from Parent
    Then I can transfer "20000" Đồng Tốt 365 to each Child
    And Each Child should receive "20000" Đồng Tốt 365
    Then My Biz Account should have "60000" Đồng Tốt 365

  @AUTHOR_VUHOANG_ME_API  @AH_DEBIT_005
  Scenario: Parent can't transfer DT365 to multiple Childs when DT balance less than total amount
    Given I login my Biz Account with "20000" Đồng Tốt 365
    When I login "2" Child Accounts to receive DT transfer from Parent
    Then I cannot transfer "11000" Đồng Tốt 365 to each Child
    And I got error message to my Biz Account "Không đủ Đồng Tốt để chuyển!"
    Then Each Child should receive "0" Đồng Tốt 365
    Then My Biz Account should have "20000" Đồng Tốt 365

  # Đồng Tốt Expiry
  @AUTHOR_VUHOANG_ME_API  @AH_DEBIT_016  @AH_DEBIT_002   @FAILED
  Scenario: Parent can transfer DTExpiry to multiple Childs
    Given I login my Biz Account with 20k Đồng Tốt Expiry
    When I login "2" Child Accounts to receive DT transfer from Parent
    When I transfer "9000" Đồng Tốt Expiry to each Child
    Then Each Child should have "9000" Đồng Tốt Expiry
    And Each Child should receive "9000" Đồng Tốt Expiry with same Closest expired as Parent
    And My Biz Account should have "2000" Đồng Tốt Expiry


  @AUTHOR_VUHOANG_ME_API  @AH_DEBIT_017  @FAILED
  Scenario: Parent can't transfer DTExpiry to multiple Childs when DT balance less than total amount
    Given I login my Biz Account with 20k Đồng Tốt Expiry
    When I login "2" Child Accounts to receive DT transfer from Parent
    Then I cannot transfer "10001" Đồng Tốt Expiry to each Child
    And I got error message to my Biz Account "Không đủ Đồng Tốt để chuyển!"
    Then Each Child should have "0" Đồng Tốt Expiry
    Then My Biz Account should have "20000" Đồng Tốt Expiry


   # Đồng Tốt Free
  @AUTHOR_VUHOANG_ME_API  @AH_DEBIT_010  @AH_DEBIT_003
  Scenario: Parent can transfer DTFree to multiple Childs
    Given I login my Biz Account with "100000" Đồng Tốt Free
    When I login "2" Child Accounts to receive DT transfer from Parent
    Then I can transfer "20000" Đồng Tốt Free to each Child
    Then Each Child should have "20000" Đồng Tốt Free
    And Each Child should receive "20000" Đồng Tốt Free with same Closest expired as Parent
    And My Biz Account should have "60000" Đồng Tốt Free

  @AUTHOR_VUHOANG_ME_API  @AH_DEBIT_011
  Scenario: Parent can't transfer DTFree to multiple Childs when DT balance less than total amount
    Given I login my Biz Account with "20000" Đồng Tốt Free
    When I login "2" Child Accounts to receive DT transfer from Parent
    Then I cannot transfer "10001" Đồng Tốt Free to each Child
    And I got error message to my Biz Account "Không đủ Đồng Tốt để chuyển!"
    Then Each Child should have "0" Đồng Tốt Free
    Then My Biz Account should have "20000" Đồng Tốt Free


#  @AUTHOR_VUHOANG_ME_API  @AH_DEBIT_011  @CODING
#  Scenario: Parent can't transfer and got error when DTFree is expired
#    Given I login my Biz Account with "20000" Đồng Tốt Free but expired  # dang code steps
#    When I login "2" Child Accounts to receive DT transfer from Parent
#    Then I cannot transfer "10000" Đồng Tốt Free to each Child
#    And I got error message to my Biz Account "Không đủ Đồng Tốt để chuyển!"
#    Then Each Child should have "0" Đồng Tốt Free
#    Then My Biz Account should have "20000" Đồng Tốt Free

  # Adhoc
  @AUTHOR_VUHOANG_ME_API  @AH_DEBIT_019  @AH_DEBIT_021
  Scenario: Parent can't transfer DT to users who are not Childs
    Given I login my Biz Account with "20000" Đồng Tốt 365
    When I login my another one that is not my Child Accounts
    Then I cannot transfer "10000" Đồng Tốt 365 to users who are not my Childs
    And I got error message to my Biz Account "Tính năng chuyển Đồng Tốt không hỗ trợ cho nhóm người gửi và người nhận này!"

  @AUTHOR_VUHOANG_ME_API  @AH_DEBIT_020
  Scenario: Parent can't transfer DT to other Parent
    Given I login my Biz Account with "20000" Đồng Tốt 365
    When I login my another Biz Account to receive DT Transfer
    Then I cannot transfer "10000" Đồng Tốt 365 to my another Biz Account
    And I got error message to my Biz Account "Tính năng chuyển Đồng Tốt không hỗ trợ cho nhóm người gửi và người nhận này!"

  @AUTHOR_VUHOANG_ME_API  @AH_DEBIT_026   @FAILED
  Scenario: Parent can't transfer DT to inactive or deleted Child
    Given I login my Biz Account with "20000" Đồng Tốt 365
    When I login "1" Child Accounts to receive DT transfer from Parent
    But I delete my Child Account to stop receiving DT Transfer
    Then I cannot transfer "10000" Đồng Tốt 365 to each Child
    And I got error message to my Biz Account "Tính năng chuyển Đồng Tốt không hỗ trợ cho nhóm người gửi và người nhận này!"

  @AUTHOR_VUHOANG_ME_API  @AH_DEBIT_029
  Scenario: Child can't transfer DT to another Child & parent & other Users
    Given I login my Biz Account with "20000" Đồng Tốt 365
    When I login "1" Child Accounts to receive DT transfer from Parent
    When I transfer "10000" Đồng Tốt 365 to each Child
    Then I cannot transfer "10000" Đồng Tốt 365 from Child Account to Parent
    And I got error message to my Child Account "Tính năng chuyển Đồng Tốt không hỗ trợ cho nhóm người gửi và người nhận này!"

  @AUTHOR_VUHOANG_ME_API  @AH_DEBIT_027
  Scenario: Parent can transfer DT to Childs who are unlinked then linked to the Parent
    Given I login my Biz Account with "20000" Đồng Tốt 365
    When I login "1" Child Accounts to receive DT transfer from Parent
    When I transfer "1000" Đồng Tốt 365 to each Child
    When I delete my Child Account to stop receiving DT Transfer
    When I add again the Child Account that I deleted
    Then I can transfer "1000" Đồng Tốt 365 to each Child

  @AUTHOR_VUHOANG_ME_API  @AH_DEBIT_023  @FAILED
  Scenario: Parent can't transfer DT when Parent is inactive
    Given I login my Biz Account with "20000" Đồng Tốt 365
    When I login "1" Child Accounts to receive DT transfer from Parent
    When I inactivate my Child Account
    Then I cannot transfer "10000" Đồng Tốt 365 to users who are not my Childs


  @AUTHOR_VUHOANG_ME_API  @AH_DEBIT_024  @FAILED
  Scenario: Parent can't transfer DT when Parent is expired
    Given I login my Biz Account with "20000" Đồng Tốt 365
    When I login "1" Child Accounts to receive DT transfer from Parent
    When I expires my Child Account
    Then I cannot transfer "10000" Đồng Tốt 365 to users who are not my Childs


#  @AUTHOR_VUHOANG_ME_API  @VUHOANG
#  Scenario: Test
#    Given I login my Account "0345255926"
##    Given I login my Account "0846334962"
#    Then I topup 20k Dong Tot Bank Transfer
#    Then I topup 20k Dong Tot Bank Transfer
##    Then I topup "10000000" Dong Tot Free
##Then I topup 500k Đồng Tốt
