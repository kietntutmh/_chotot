@SERVICE_HIERARCHY
@CUSTOMER
@TAG_VUHOANG_TUANCHIEU
@HIERARCHY_PAYORDER
Feature: Account Hierarchy API - Pay Order and Order History

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Child Account has own Dong Tot
    Given I login my Biz Account with "20000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 0                |
    And I topup "100000" Dong Tot to my Child Account
    Then Child Account's Dong Tot should be "100000"

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Child Account can't see Total Dong Tot of Biz Account
    Given I login my Biz Account with "20000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 0                |
    Then Child Account can't see Total DT4B of Biz Account

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Biz Account uses its own Dong Tot
    Given I login my Biz Account with "20000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 0                |
    Then I post and pay POS for "1" new Ads that each of them costs "15000" by my Biz Account

  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Child Account can't pay when DT4B is not enough
    Given I login my Biz Account with "20000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 0                |
    And I post and pay POS for "1" new Ads that each of them costs "15000" by my Child Account
    Then I can't pay for POS with "15000" by using my Child Account


  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: DT4B is refunded to Biz Account when Child Account pay unsuccessfully
    Given I login my Biz Account with "100000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 0                |
    When I pay for a Pro Ad by my Child Account and Chotot refused my Ad
    Then I should see Dong Tot refunded to my Biz Account in Biz Order History
    Then Biz Account's Dong Tot should be "100000"


  @AUTHOR_VUHOANG_ME_API @DONE
  Scenario: Child Account sees Order History after pay DT4B
    Given I login my Biz Account with "100000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 0                |
    When I use Child Account to pay POS by DT4B
    Then I should see the POS order of my Child Account in Child Order History is "15000"


  @AUTHOR_VUHOANG_ME_API @DONE
  Scenario: Biz Account see Order History of Childs
    Given I login my Biz Account with "100000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 0                |
    When I use Child Account to pay POS by DT4B
    Then I should see payments of Child Account in Biz Order History
