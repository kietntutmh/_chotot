@SERVICE_PRICER
@TELEGRAM_ME
@TAG_VUHOANG_SON
#@NEWPRICER_SHOPS
Feature: New Pricer Shop /shops/get-all API

  Background:
    Given I download data from Google Sheet "Shop Price"

  @AUTHOR_VUHOANG_ME_API
  Scenario: /v1/public shop_create vnd, all categories
    Then Shop Create Price VND of all categories should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: /v1/public shop_create credit, all categories
    Then Shop Create Price DongTot of all categories should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: /v1/public shop_create promotion, all categories
    Then Shop Create Price Promotion of all categories should be correct


  @AUTHOR_VUHOANG_ME_API
  Scenario: /v1/public shop_extend vnd, all categories
    Then Shop Extend Price VND of all categories should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: /v1/public shop_extend credit, all categories
    Then Shop Extend Price DongTot of all categories should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: /v1/public shop_extend promotion, all categories
    Then Shop Extend Price Promotion of all categories should be correct



  @AUTHOR_VUHOANG_ME_API
  Scenario: /v1/private shop_create vnd, all categories
    Given I login my account to check price
    Then Shop Private Create Price VND of all categories should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: /v1/private shop_create credit, all categories
    Given I login my account to check price
    Then Shop Private Create Price DongTot of all categories should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: /v1/private shop_create promotion, all categories
    Given I login my account to check price
    Then Shop Private Create Price Promotion of all categories should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: /v1/private shop_extend vnd, all categories
    Given I login my account to check price
    Then Shop Private Extend Price VND of all categories should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: /v1/private shop_extend credit, all categories
    Given I login my account to check price
    Then Shop Private Extend Price DongTot of all categories should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: /v1/private shop_extend promotion, all categories
    Given I login my account to check price
    Then Shop Private Extend Price Promotion of all categories should be correct



