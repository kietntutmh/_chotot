@SERVICE_HIERARCHY
@CUSTOMER
@TAG_VUHOANG_TUANCHIEU
@HIERARCHY_DEBIT_TRANSFER_1
Feature: Account Hierarchy API - Debit Approach - Order History

  @AUTHOR_VUHOANG_ME_API  @AH_DEBIT_043
  Scenario: An order of Parent is created when transfers DT365 successfully
    Given I login my Biz Account with "100000" Đồng Tốt 365
    When I login "2" Child Accounts to receive DT transfer from Parent
    When I transfer "10000" Đồng Tốt 365 to each Child
    Then An Order of Transfering "20000" Đồng Tốt 365 should be created on my Biz Account with status SUCCESS
    And Order Details of Transfering "10000" Đồng Tốt 365 to each Child should be correct

  @AUTHOR_VUHOANG_ME_API  @AH_DEBIT_044
  Scenario: An order of Parent is created when transfers DTExpiry successfully
    Given I login my Biz Account with 20k Đồng Tốt Expiry
    When I login "2" Child Accounts to receive DT transfer from Parent
    When I transfer "5000" Đồng Tốt Expiry to each Child
    Then An Order of Transfering "10000" Đồng Tốt Expiry should be created on my Biz Account with status SUCCESS
    And Order Details of Transfering "5000" Đồng Tốt Expiry to each Child should be correct

  @AUTHOR_VUHOANG_ME_API  @AH_DEBIT_045
  Scenario: An order of Parent is created when transfers DTExpiry successfully
    Given I login my Biz Account with "10000" Đồng Tốt Free
    When I login "2" Child Accounts to receive DT transfer from Parent
    When I transfer "5000" Đồng Tốt Free to each Child
    Then An Order of Transfering "10000" Đồng Tốt Free should be created on my Biz Account with status SUCCESS
    And Order Details of Transfering "5000" Đồng Tốt Free to each Child should be correct


  @AUTHOR_VUHOANG_ME_API  @AH_DEBIT_054  @AH_DEBIT_061  @AH_DEBIT_062  @AH_DEBIT_063
  Scenario: An order of Child is created when receives DTExpiry transfer successfully
    Given I login my Biz Account with "20000" Đồng Tốt 365
    When I login "2" Child Accounts to receive DT transfer from Parent
    When I transfer "5000" Đồng Tốt 365 to each Child
    Then An Order of Receiving "5000" Đồng Tốt 365 should be created in Child Order History

  @AUTHOR_VUHOANG_ME_API  @AH_DEBIT_055
  Scenario: An order of Child is created when receives DTExpiry transfer successfully
    Given I login my Biz Account with 20k Đồng Tốt Expiry
    When I login "2" Child Accounts to receive DT transfer from Parent
    When I transfer "5000" Đồng Tốt Expiry to each Child
    Then An Order of Receiving "5000" Đồng Tốt Expiry should be created in Child Order History

  @AUTHOR_VUHOANG_ME_API  @AH_DEBIT_056
  Scenario: An order of Child is created when receives DTFree transfer successfully
    Given I login my Biz Account with "20000" Đồng Tốt Free
    When I login "2" Child Accounts to receive DT transfer from Parent
    When I transfer "5000" Đồng Tốt Free to each Child
    Then An Order of Receiving "5000" Đồng Tốt Free should be created in Child Order History

  @AUTHOR_VUHOANG_ME_API  @AH_DEBIT_043
  Scenario: An order of Parent is created when transfers DT365 successfully
    Given I login my Biz Account with "100000" Đồng Tốt 365
    When I login "2" Child Accounts to receive DT transfer from Parent
    When I transfer "10000" Đồng Tốt 365 to each Child
    Then An Order of Transfering "20000" Đồng Tốt 365 should be created on my Biz Account with status SUCCESS
    And Order Details of Transfering "10000" Đồng Tốt 365 to each Child should be correct


