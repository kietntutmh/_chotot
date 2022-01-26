@SERVICE_HIERARCHY
@CUSTOMER
@TAG_VUHOANG_SONNGUYEN_MINHTRAN
#@HIERARCHY_LIMITATION  VUHOANG MAINTENANCE
Feature: Account Hierarchy API - Adhoc / Pay with Limitation

#And Chotot set Child Per Month Limitation to ""
#And Chotot set Child Per Transaction Limitation to ""
#And Chotot set Biz Per Transaction Limitation to ""
#And Chotot set Biz Per Child Month Limitation to ""
#And Chotot set Biz Total Limitation to ""

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Limit per Month of Child refunds when Child pays unsuccessfully and over Limitation
    Given I login with my Child Account that is linked to Biz Account
    And DT4B balance is "200000"
    When My Child Budget Limitation Per Month which is set on Child Account is "15100"
    When I post a new Ad and pay for POS Feature Ad with my Child Account But ad is rejected and DT4B is refunded
    Then My child account should pay for premium services "15000" successfully
    Then My child account should pay for premium services "15000" unsuccessfully


  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Biz Account, Set limit budget/month When upgrated to Biz Account
    Given I register a Biz Account and link a Child Account
    And Chotot set Biz Total Limitation to "500000"
    And Chotot set Child Per Month Limitation to "100000"
    And I topup 500k Dong Tot to my Biz Account
    When I use Child Account to post and pay POS for "1" new ads
    When Chotot set Child Per Month Limitation to "200000"
    And I use Child Account to post and pay POS for "12" new ads

