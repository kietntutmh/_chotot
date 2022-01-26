@SERVICE_HIERARCHY
@CUSTOMER
@TAG_VUHOANG_MINHTRAN
@ACCOUNT_HIERARCHY_BUDGET_LIMITATION
Feature: Account Hierarchy API - Amount Per Order Limitation for Payment Page

  #========================================== BIZ LIMITATION > BIZ TOTAL ==========================================
  @AUTHOR_VUHOANG_CUSTOMER_API @DONE
  Scenario: AmountPerOrder is initialized with unlimited
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 0                |
    Then Amount Per Order (Hạn mức trên mỗi Giao Dịch) of my Child Account should be Chưa Cài Đặt


  @AUTHOR_VUHOANG_ME_API @DONE
  Scenario: AmountPerOrder counts on Biz - Child Per Month
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 15000            |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 0                |
    Then Amount Per Order (Hạn mức trên mỗi Giao Dịch) of my Child Account should be "15000"

  @AUTHOR_VUHOANG_ME_API @DONE
  Scenario: AmountPerOrder counts on Child Per Month
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 15000            |
    Then Amount Per Order (Hạn mức trên mỗi Giao Dịch) of my Child Account should be "15000"

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: AmountPerOrder counts on Child Per Month when having both Child Per Month & Biz - Child Per Month
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 14000            |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 15000            |
    Then Amount Per Order (Hạn mức trên mỗi Giao Dịch) of my Child Account should be "15000"


  @AUTHOR_VUHOANG_ME_API @DONE
  Scenario: AmountPerOrder counts on Biz Child Per Month when update Child to 0
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 14000            |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 15000            |
    When I update budget limitation of my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 0                |
    Then Amount Per Order (Hạn mức trên mỗi Giao Dịch) of my Child Account should be "14000"




