@SERVICE_HIERARCHY
@CUSTOMER
@TAG_QUANGTRAN
@HIERARCHY_BUDGET_RESET
Feature: Account Hierarchy API - Budget Reset

  @AUTHOR_QUANGTRAN_CUSTOMER_API  @DONE
  Scenario: Default value of reset date is 1
    Given I login my Biz Account with "20000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 0                |
    Then Default value of reset date should be 1

  @AUTHOR_QUANGTRAN_CUSTOMER_API  @DONE
  Scenario: As a parent account, I can update the reset date
    Given I login my Biz Account with "20000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 0                |
    Then I can update the reset date successfully

  @AUTHOR_QUANGTRAN_CUSTOMER_API  @DONE
  Scenario: The reset date is not changed to default value after re-activating parent account
    Given I login my Biz Account with "20000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 0                |
    When I can update the reset date successfully
    And I Deactivate biz account
    And I Reactivate biz account
    Then Reset date is not changed

  @AUTHOR_QUANGTRAN_CUSTOMER_API  @DONE
  Scenario: As Child Account, I see the remaining budget is reset when updating Reset Date to current day
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 45000                  | 0                |
    And I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | -                      | 0                |
    When I pay for "1" new Ads that costs "15000" by my Child Account
    And I update Reset Date to current day
      | Child Amount Per Month |
      | 45000                  |
    And I pay for "1" new Ads that costs "45000" by my Child Account
    And I can't pay more by my Child Account with announce "Vượt quá tổng hạn mức giao dịch của doanh nghiệp, hạn mức còn lại là: 0"

  @AUTHOR_QUANGTRAN_CUSTOMER_API  @DONE
  Scenario: As Parent Account, I see the remaining budget of child account is reset when updating Reset Date to current day
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 15000                  | 0                |
    And I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | -                      | 0                |
    When I pay for "1" new Ads that costs "15000" by my Child Account
    And I update Reset Date to current day
      | Child Amount Per Month |
      | 15000                  |
    Then The remaining budget is reset

  @AUTHOR_QUANGTRAN_CUSTOMER_API  @DONE
  Scenario: As Child Account, I see the remaining budget is not reset when updating Reset Date to a date in the future
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 15000                  | 0                |
    And I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | -                      | 0                |
    When I pay for "1" new Ads that costs "15000" by my Child Account
    And I update Reset Date to a date in the future
      | Child Amount Per Month |
      | 15000                  |
    Then I can't pay more by my Child Account with announce "Vượt quá tổng hạn mức giao dịch của doanh nghiệp, hạn mức còn lại là: 0"

  @AUTHOR_QUANGTRAN_CUSTOMER_API  @DONE
  Scenario: As Child Account, I see the remaining budget is not reset when updating Reset Date to a date in the past
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 15000                  | 0                |
    And I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | -                      | 0                |
    When I pay for "1" new Ads that costs "15000" by my Child Account
    And I update Reset Date to a date in the past
      | Child Amount Per Month |
      | 15000                  |
    Then I can't pay more by my Child Account with announce "Vượt quá tổng hạn mức giao dịch của doanh nghiệp, hạn mức còn lại là: 0"

  @AUTHOR_QUANGTRAN_CUSTOMER_API  @DONE
  Scenario: As Parent Account, I see the remaining budget of child account is not reset when updating Reset Date to a date in the future
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 15000                  | 0                |
    And I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | -                      | 0                |
    When I pay for "1" new Ads that costs "15000" by my Child Account
    And I update Reset Date to a date in the future
      | Child Amount Per Month |
      | 15000                  |
    Then The remaining budget is not reset

  @AUTHOR_QUANGTRAN_CUSTOMER_API  @DONE
  Scenario: As Parent Account, I see the remaining budget of child account is not reset when updating Reset Date to a date in the past
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 15000                  | 0                |
    And I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | -                      | 0                |
    When I pay for "1" new Ads that costs "15000" by my Child Account
    And I update Reset Date to a date in the past
      | Child Amount Per Month |
      | 15000                  |
    Then The remaining budget is not reset

  @AUTHOR_QUANGTRAN_CUSTOMER_API  @DONE
  Scenario: As Parent Account, I see the end_time is updated to next month when updating the Reset Date to a date in the past
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 15000                  | 0                |
    When I update Reset Date to a date in the past
      | Child Amount Per Month |
      | 15000                  |
    Then The start time is updated
    And The end time is updated to next month

  @AUTHOR_QUANGTRAN_CUSTOMER_API  @DONE
  Scenario: As Parent Account, I see the end_time is updated when updating the Reset Date to a date in the future
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 15000                  | 0                |
    When I update Reset Date to a date in the future
      | Child Amount Per Month |
      | 15000                  |
    Then The start time is updated
    And The end time is updated to next day in month