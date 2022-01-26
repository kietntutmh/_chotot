@SERVICE_PRICER
@CUSTOMER
@TAG_VUHOANG_HUETAN_SON
Feature: New Pricer Sticky internal/stick-ads Ads API - Check price VND, DT, Promotion

  Background:
    Given I download data from Google Sheet "Sticky Ad Price"

  @AUTHOR_VUHOANG_ME_AP
  Scenario: Sticky Ads Internal VND, Check Sticky Ads service price
    Then Sticky Ads Internal Price of all subcates should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: Sticky Ads Internal DT, Check Sticky Ads service price
    Then Sticky Ads Internal Dong Tot Price of all subcates should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: Sticky Ads Internal Promotion, Check Sticky Ads service price
    Then Sticky Ads Internal Promotion Price of all subcates should be correct



