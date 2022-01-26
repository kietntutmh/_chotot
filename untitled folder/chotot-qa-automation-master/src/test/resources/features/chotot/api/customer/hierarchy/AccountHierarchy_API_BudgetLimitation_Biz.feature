@SERVICE_HIERARCHY
@CUSTOMER
@TAG_VUHOANG_MINHTRAN
@ACCOUNT_HIERARCHY_BUDGET_LIMITATION
Feature: Account Hierarchy API - Budget Limitation Biz

  #========================================== BIZ LIMITATION > BIZ TOTAL ==========================================
  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Biz Total, Child can't pay > Limitation
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 15000        | 0                      | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | -                      | 0                |
    And I pay for "1" new Ads that costs "15000" by my Child Account
    Then I can't pay more by my Child Account with announce "Vượt quá tổng hạn mức giao dịch của doanh nghiệp, hạn mức còn lại là: 0"
    When I login my another Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | -                      | 0                |
    Then I can't pay more by my Child Account with announce "Vượt quá tổng hạn mức giao dịch của doanh nghiệp, hạn mức còn lại là: 0"

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Biz Total, Child can pay after updated Limitation as possible
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 14999        | 0                      | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | -                      | 0                |
    Then I can't pay more by my Child Account with announce "Vượt quá tổng hạn mức giao dịch của doanh nghiệp, hạn mức còn lại là: 14,999"
    When I update budget limitation of my Biz Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 15000        | 0                      | 0                |
    Then I can pay again by my Child Account

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Biz Total, Child can't pay after updated Limitation as impossible
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 15000        | 0                      | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | -                      | 0                |
    Then I can pay for "1" new Ads that costs "15000" by my Child Account
    When I update budget limitation of my Biz Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 14000        | 0                      | 0                |
    Then I can't pay more by my Child Account with announce "Vượt quá tổng hạn mức giao dịch của doanh nghiệp, hạn mức còn lại là: 0"


  #========================================== BIZ LIMITATION > BIZ CHILD PER MONTH ==========================================
  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Biz Child Per Month, Child can't pay > Limitation
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 15000                  | 0                |
    And I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | -                      | 0                |
    When I pay for "1" new Ads that costs "15000" by my Child Account
    Then I can't pay more by my Child Account with announce "Vượt quá tổng hạn mức giao dịch của doanh nghiệp, hạn mức còn lại là: 0"
    When I login my another Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | -                      | 0                |
    When I pay for "1" new Ads that costs "15000" by my Child Account
    Then I can't pay more by my Child Account with announce "Vượt quá tổng hạn mức giao dịch của doanh nghiệp, hạn mức còn lại là: 0"

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Biz Child Per Month, Child can pay after updated Limitation as possible
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 14999                  | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | -                      | 0                |
    Then I can't pay more by my Child Account with announce "Vượt quá tổng hạn mức giao dịch của doanh nghiệp, hạn mức còn lại là: 14,999"
    When I update budget limitation of my Biz Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 15000                  | 0                |
    Then I can pay again by my Child Account

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Biz Child Per Month, Child can't pay after updated Limitation as impossible
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 30000                  | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | -                      | 0                |
    Then I can pay for "1" new Ads that costs "15000" by my Child Account
    When I update budget limitation of my Biz Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 29999                  | 0                |
    Then I can't pay more by my Child Account with announce "Vượt quá tổng hạn mức giao dịch của doanh nghiệp, hạn mức còn lại là: 14,999"


  #========================================== BIZ LIMITATION > BIZ CHILD PER ORDER ==========================================
  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Biz Child Per Order, Child can't pay > Limitation
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 14999            |
    And I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | -                      | 0                |
    Then I can't pay more by my Child Account with announce "Vượt quá hạn mức mỗi giao dịch của doanh nghiệp, hạn mức giao dịch tối đa là: 14,999"

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Biz Child Per Order, Child can pay after updated Limitation as possible
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 14999            |
    And I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | -                      | 0                |
    Then I can't pay more by my Child Account with announce "Vượt quá hạn mức mỗi giao dịch của doanh nghiệp, hạn mức giao dịch tối đa là: 14,999"
    When I update budget limitation of my Biz Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 15000            |
    Then I can pay again by my Child Account
    Then I can pay for "2" new Ads that each of them costs "15000" by my Child Account

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Biz Child Per Order, Child can't pay after updated Limitation as impossible
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 15000            |
    And I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | -                      | 0                |
    Then I can pay for "2" new Ads that each of them costs "15000" by my Child Account
    When I update budget limitation of my Biz Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 14999            |
    Then I can't pay more by my Child Account with announce "Vượt quá hạn mức mỗi giao dịch của doanh nghiệp, hạn mức giao dịch tối đa là: 14,999"



 #========================================== CHILD PER MONTH > CHILD PER MONTH ==========================================
  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Child Per Month, BizChildPerMonth doesn't effect when ChildTotal is possible
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 100000       | 14999                  | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 15001        | -                      | 0                |
    Then I can pay for "1" new Ads that costs "15000" by my Child Account
    Then I can't pay more by my Child Account with announce "Vượt quá tổng hạn mức giao dịch của doanh nghiệp, hạn mức còn lại là: 1"

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Child Per Month, Child can pay after updated Limitation as possible
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 100000       | 0                      | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 15000        | -                      | 0                |
    When I can pay for "1" new Ads that costs "15000" by my Child Account
    Then I can't pay more by my Child Account with announce "Vượt quá tổng hạn mức giao dịch của doanh nghiệp, hạn mức còn lại là: 0"
    When I update budget limitation of my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 30000        | -                      | 0                |
    Then I can pay again by my Child Account

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Child Per Month, Child can't pay after updated Limitation as impossible
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 100000       | 0                      | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 30000        | -                      | 0                |
    Then I can pay for "1" new Ads that costs "15000" by my Child Account
    When I update budget limitation of my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 14999        | -                      | 0                |
    Then I can't pay more by my Child Account with announce "Vượt quá tổng hạn mức giao dịch của doanh nghiệp, hạn mức còn lại là: 0"

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Test search Order History button
    Given I login my Biz Account with "3000000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 0                |
    When I login "80" Child Account and pay "2" orders cost "15000" each order
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | -                      | 0                |