@SERVICE_HIERARCHY
@CUSTOMER
@TAG_VUHOANG_MINHTRAN
@ACCOUNT_HIERARCHY_BUDGET_LIMITATION   
Feature: Account Hierarchy API - Remaining Budget

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Remaining Budget is initialized with unlimited
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 0                |
    Then Remaining Amount (Hạn mức còn lại) of my Child Account should be Chưa Cài Đặt

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Remaining Budget doesn't changes when it's not set and after Child pays
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 0                |
    And I pay for "1" new Ads that costs "15000" by my Child Account
    Then Remaining Amount (Hạn mức còn lại) of my Child Account should be Chưa Cài Đặt

  #---------------- Biz Limitation: Per Month ----------------

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Biz Per Child, Remaining Budget displays correct amount after Child paid
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 30000                  | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 0                |
    And I pay for "1" new Ads that costs "15000" by my Child Account
    Then Remaining Amount (Hạn mức còn lại) of my Child Account should be "15000"


  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Child Per Month, Remaining Budget is 0 when Limitation is set after Child paid some DT4B overs Limitation
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 0                |
    And I pay for "2" new Ads that costs "30000" by my Child Account
    When I update budget limitation of my Biz Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 15000                  | 0                |
    Then Remaining Amount (Hạn mức còn lại) of my Child Account should be "0"


  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Biz Per Child, Remaining Budget is 0 when Limitation is set after Child paid some DT4B equals to Limitation
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 15000                  | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 0                |
    And I pay for "1" new Ads that costs "15000" by my Child Account
    When I update budget limitation of my Biz Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 15000                  | 0                |
    Then Remaining Amount (Hạn mức còn lại) of my Child Account should be "0"


  #---------------- Child Limitation: Per Month ----------------

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Child Per Month, Remaining Budget displays correct amount after Child paid
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 30000        | 0                      | 0                |
    And I pay for "1" new Ads that costs "15000" by my Child Account
    Then Remaining Amount (Hạn mức còn lại) of my Child Account should be "15000"


  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Child Per Month, Remaining Budget is 0 when Limitation is set after Child paid some DT4B overs Limitation
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 0                |
    And I pay for "2" new Ads that costs "30000" by my Child Account
    When I update budget limitation of my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 15000        | 0                      | 0                |
    Then Remaining Amount (Hạn mức còn lại) of my Child Account should be 0


  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Child Per Month, Remaining Budget is 0 when Limitation is set after Child paid some DT4B equals to Limitation
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 0                |
    And I pay for "1" new Ads that costs "15000" by my Child Account
    When I update budget limitation of my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 15000        | 0                      | 0                |
    Then Remaining Amount (Hạn mức còn lại) of my Child Account should be 0


  #---------------- Child & Biz mixed Limitation: Per Month ----------------
  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Both Biz and Child, Remaining Budget counts on Child Limitation when set for both Biz and Child
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 12000                  | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 30000        | 0                      | 0                |
    And I pay for "1" new Ads that costs "15000" by my Child Account
    Then Remaining Amount (Hạn mức còn lại) of my Child Account should be "15000"


  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Child Per Moth, Remaining Budget is correct when reached on Child Limitation and re-setup again
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 15000        | 0                      | 0                |
    And I pay for "1" new Ads that costs "15000" by my Child Account
    When I update budget limitation of my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 30000        | 0                      | 0                |
    Then Remaining Amount (Hạn mức còn lại) of my Child Account should be "15000"


  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Both Biz and Child, Remaining Budget only counts on Child Limitation Per Month
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 50000        | 0                      | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 0                |
    And I pay for "1" new Ads that costs "15000" by my Child Account
    When I login my another Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 0                |
    And I pay for "1" new Ads that costs "15000" by my Child Account
    Then Remaining Amount (Hạn mức còn lại) of my Child Account should be "20000"


  @AUTHOR_VUHOANG_ME_API  @FAILED
  Scenario: Both Biz and Child, Remaining Budget only counts on Child Limitation Per Month & doesn't count on Biz Total
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 1            | 200000                 | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 0                |
    Then Remaining Amount (Hạn mức còn lại) of my Child Account should be "200000"


  @AUTHOR_VUHOANG_ME_API  @FAILED
  Scenario: Both Biz and Child, Remaining Budget only counts on Child Limitation Per Month & doesn't count on Biz Total
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 1            | 0                      | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 200000       | 0                      | 0                |
    Then Remaining Amount (Hạn mức còn lại) of my Child Account should be "200000"