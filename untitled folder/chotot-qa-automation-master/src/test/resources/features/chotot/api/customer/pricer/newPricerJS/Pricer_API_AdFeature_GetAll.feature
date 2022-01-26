@SERVICE_PRICER
@CUSTOMER
@TAG_VUHOANG
#@NEWPRICER_ADFEATURE
Feature: New Pricer Ad Feature /pricer/get-all

  Background:
    Given I download data from Google Sheet "Label Price"

  @AUTHOR_VUHOANG_ME_API
  Scenario: /v1/public price.vnd
    Then Ad Feature Price of all subcates should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: /v1/public price.credit
    Then Ad Feature Price Dong Tot of all subcates should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: /v1/public price.promotion
    Then Ad Feature Price Promotion of all subcates should be correct


  @AUTHOR_VUHOANG_ME_API
  Scenario: /v1/internal price.vnd
    Given I login my account to check price
    Then Ad Feature Internal Price of all subcates should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: /v1/internal price.credit
    Given I login my account to check price
    Then Ad Feature Internal Price Dong Tot of all subcates should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: /v1/internal price.promotion
    Given I login my account to check price
    Then Ad Feature Internal Price Promotion of all subcates should be correct

