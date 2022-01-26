@SERVICE_HIERARCHY
@CUSTOMER
@TAG_VUHOANG_TUANCHIEU
@ACCOUNT_HIERARCHY_BUDGET_LIMITATION
Feature: Account Hierarchy API - Budget Limitation Child

 #========================================== CHILD PER ORDER > BIZ TOTAL & CHILD PER ORDER ==========================================
  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: ChildPerOrder, BizPerOrder doesn't affect on ChildPerOrder
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 100000       | 0                      | 14999            |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | -                      | 15000            |
    Then I can pay for "2" new Ads that each of them costs "15000" by my Child Account

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: ChildPerOrder, Child can't pay when > ChildPerOrder
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 100000       | 0                      | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | -                      | 14999            |
    Then I can't pay more by my Child Account with announce "Vượt quá hạn mức mỗi giao dịch của doanh nghiệp, hạn mức giao dịch tối đa là: 14,999"

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: ChildPerOrder, Child can't pay after ChildPerOrder decreases less budget
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 100000       | 0                      | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | -                      | 15000            |
    Then I can pay for "2" new Ads that costs "15000" by my Child Account
    When I update budget limitation of my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | -                      | 14999            |
    Then I can't pay more by my Child Account with announce "Vượt quá hạn mức mỗi giao dịch của doanh nghiệp, hạn mức giao dịch tối đa là: 14,999"

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: ChildPerOrder, Child can pay after ChildPerOrder increases more budget when overs ChildPerOrder
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 100000       | 0                      | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | -                      | 14999            |
    Then I can't pay more by my Child Account with announce "Vượt quá hạn mức mỗi giao dịch của doanh nghiệp, hạn mức giao dịch tối đa là: 14,999"
    When I update budget limitation of my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | -                      | 15000            |
    Then I can pay again by my Child Account


  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: ChildPerOrder, Child can't pay when <= ChildPerOrder but > BizTotal
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 14999        | 0                      | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | -                      | 15000            |
    Then I can't pay more by my Child Account with announce "Vượt quá tổng hạn mức giao dịch của doanh nghiệp, hạn mức còn lại là: 14,999"

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: ChildPerOrder, Child can pay after increased more budget for BizTotal when <= ChildPerOrder but > BizTotal
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 14999        | 0                      | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | -                      | 15000            |
    Then I can't pay more by my Child Account with announce "Vượt quá tổng hạn mức giao dịch của doanh nghiệp, hạn mức còn lại là: 14,999"
    When I update budget limitation of my Biz Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 15000        | 0                      | 0                |
    Then I can pay again by my Child Account


  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: ChildPerOrder, Child can't pay when <= BizTotal but > ChildPerOrder
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 15000        | 0                      | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | -                      | 14999            |
    Then I can't pay more by my Child Account with announce "Vượt quá hạn mức mỗi giao dịch của doanh nghiệp, hạn mức giao dịch tối đa là: 14,999"

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: ChildPerOrder, Child can pay after increased more budget for ChildPerOrder when <= BizTotal but > ChildPerOrder
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 15000        | 0                      | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | -                      | 14999            |
    Then I can't pay more by my Child Account with announce "Vượt quá hạn mức mỗi giao dịch của doanh nghiệp, hạn mức giao dịch tối đa là: 14,999"
    When I update budget limitation of my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | -                      | 15000            |
    Then I can pay again by my Child Account
    Then I can't pay more by my Child Account with announce "Vượt quá tổng hạn mức giao dịch của doanh nghiệp, hạn mức còn lại là: 0"


#========================================== CHILD PER ORDER > CHILD PER MONTH & CHILD PER ORDER ==========================================
  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: ChildPerOrder, Child can pay after increased more budget for ChildPerOrder when <= ChildPerMonth but > ChildPerOrder
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 100000       | 0                      | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 15000        | -                      | 14999            |
    Then I can't pay more by my Child Account with announce "Vượt quá hạn mức mỗi giao dịch của doanh nghiệp, hạn mức giao dịch tối đa là: 14,999"
    When I update budget limitation of my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 15000        | -                      | 15000            |
    Then I can pay again by my Child Account
    Then I can't pay more by my Child Account with announce "Vượt quá tổng hạn mức giao dịch của doanh nghiệp, hạn mức còn lại là: 0"

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: ChildPerOrder, Child can pay after increased more budget for ChildPerMonth when <= ChildPerOrder but > ChildPerMonth
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 100000       | 0                      | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 14999        | -                      | 15000            |
    Then I can't pay more by my Child Account with announce "Vượt quá tổng hạn mức giao dịch của doanh nghiệp, hạn mức còn lại là: 14,999"
    When I update budget limitation of my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 15001        | -                      | 15000            |
    Then I can pay again by my Child Account
    Then I can't pay more by my Child Account with announce "Vượt quá tổng hạn mức giao dịch của doanh nghiệp, hạn mức còn lại là: 1"


#========================================== CHILD PER ORDER > BIZ CHILD PER MONTH & CHILD PER ORDER ==========================================
  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: ChildPerOrder, Child can pay after increased more budget for ChildPerOrder when <= BizChildPerMonth but > ChildPerOrder
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 100000       | 15000                  | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | -                      | 14999            |
    Then I can't pay more by my Child Account with announce "Vượt quá hạn mức mỗi giao dịch của doanh nghiệp, hạn mức giao dịch tối đa là: 14,999"
    When I update budget limitation of my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | -                      | 15000            |
    Then I can pay again by my Child Account
    Then I can't pay more by my Child Account with announce "Vượt quá tổng hạn mức giao dịch của doanh nghiệp, hạn mức còn lại là: 0"

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: ChildPerOrder, Child can pay after increased more budget for BizChildPerMonth when <= ChildPerOrder but > BizChildPerMonth
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 100000       | 14999                  | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | -                      | 15000            |
    Then I can't pay more by my Child Account with announce "Vượt quá tổng hạn mức giao dịch của doanh nghiệp, hạn mức còn lại là: 14,999"
    When I update budget limitation of my Biz Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 100000       | 15001                  | 0                |
    Then I can pay again by my Child Account
    Then I can't pay more by my Child Account with announce "Vượt quá tổng hạn mức giao dịch của doanh nghiệp, hạn mức còn lại là: 1"
