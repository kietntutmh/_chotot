@SERVICE_HIERARCHY
@CUSTOMER
@TAG_QUANGTRAN
@HIERARCHY_DELETE_CHILD
Feature: Account Hierarchy API - Delete Child

  @AUTHOR_QUANGTRAN_ME_API  @DONE
  Scenario: As parent account, I will see status of the deleted child account is DELETED
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 15000                  | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | -                      | 0                |
    When I delete child account from parent account
    Then The status of child account should be DELETED

  @AUTHOR_QUANGTRAN_ME_API  @DONE
  Scenario: As parent account, I can add deleted child account again
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 15000                  | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | -                      | 0                |
    When I delete child account from parent account
    And The status of child account should be DELETED
    When I add deleted child account to same parent account
    Then The status of child account should be ACTIVE

  @AUTHOR_QUANGTRAN_ME_API  @DONE
  Scenario: As an user, I can add deleted child account to other parent account
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 15000                  | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | -                      | 0                |
    When I delete child account from parent account
    And The status of child account should be DELETED
    When I login my other Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 15000                  | 0                |
    And I add deleted child account to other parent account
    Then The status of child account should be ACTIVE

  @AUTHOR_QUANGTRAN_ME_API  @DONE
  Scenario: As child account, I can not use Dong Tot biz after deleted
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 15000                  | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | -                      | 0                |
    When I delete child account from parent account
    Then I can't pay DT4B by my Child Account with announce "Không tìm thấy tài khoản Doanh Nghiệp cho tài khoản này"

  @AUTHOR_QUANGTRAN_ME_API  @DONE
  Scenario: As child account, I can use Dong Tot biz after added again to same parent account
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 15000                  | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | -                      | 0                |
    When I delete child account from parent account
    When I add deleted child account to same parent account
    Then I pay for "1" new Ads that costs "15000" by my Child Account

  @AUTHOR_QUANGTRAN_ME_API  @DONE
  Scenario: As child account, I can use Dong Tot biz after added again to other parent account
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 15000                  | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | -                      | 0                |
    And I delete child account from parent account
    When I login my other Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 15000                  | 0                |
    And I add deleted child account to other parent account
    Then I pay for "1" new Ads that costs "15000" by my Child Account

  @AUTHOR_QUANGTRAN_ME_API
  Scenario: As deleted child account, I see the budget will apply new limitation of new parent account
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 15000                  | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | -                      | 0                |
    And I pay for "1" new Ads that costs "15000" by my Child Account
    And I can't pay more by my Child Account with announce "Vượt quá tổng hạn mức giao dịch của doanh nghiệp, hạn mức còn lại là: 0"
    And I delete child account from parent account
    When I login my other Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 45000                  | 0                |
    And I add deleted child account to other parent account
    Then I pay for "1" new Ads that costs "45000" by my Child Account

  @AUTHOR_QUANGTRAN_ME_API  @DONE
  Scenario: As deleted child account, I see the budget will apply old limitation when added to the same parent account
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 15000                  | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | -                      | 0                |
    And I pay for "1" new Ads that costs "15000" by my Child Account
    And I can't pay more by my Child Account with announce "Vượt quá tổng hạn mức giao dịch của doanh nghiệp, hạn mức còn lại là: 0"
    And I delete child account from parent account
    And I add deleted child account to same parent account
    Then I can't pay more by my Child Account with announce "Vượt quá tổng hạn mức giao dịch của doanh nghiệp, hạn mức còn lại là: 0"

  @AUTHOR_QUANGTRAN_ME_API  @DONE
  Scenario: As deleted child account, I cannot be added to old parent account if already added to new parent account
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 15000                  | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | -                      | 0                |
    And I delete child account from parent account
    When I login my other Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 15000                  | 0                |
    And I add deleted child account to other parent account
    Then I can not add child account to old parent account

  @AUTHOR_QUANGTRAN_ME_API  @DONE
  Scenario: As parent account, I can delete child account with status Inactive
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 15000                  | 0                |
    When I login my Child Account with status is Inactive
    And I delete child account from parent account

  @AUTHOR_QUANGTRAN_ME_API  @DONE
  Scenario: As parent account, I can delete and add again child account with status Inactive
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 15000                  | 0                |
    When I login my Child Account with status is Inactive
    And I delete child account from parent account
    Then I add deleted Inactive child account to same parent account

  @AUTHOR_QUANGTRAN_ME_API  @DONE
  Scenario: As child account with status Inactive, I can be deleted and added to other parent account
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 15000                  | 0                |
    When I login my Child Account with status is Inactive
    And I delete child account from parent account
    When I login my other Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 15000                  | 0                |
    Then I add deleted Inactive child account to other parent account

  @AUTHOR_QUANGTRAN_ME_API  @DONE
  Scenario: As deleted Inactive child account, I cannot be added to old parent account if already added to new parent account
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 15000                  | 0                |
    When I login my Child Account with status is Inactive
    And I delete child account from parent account
    When I login my other Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 15000                  | 0                |
    And I add deleted Inactive child account to other parent account
    Then I can not add child account to old parent account