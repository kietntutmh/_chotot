@SERVICE_PRICER
@TELEGRAM_ME
@TAG_VUHOANG_SON
#@NEWPRICER_STICKYAD_PROMOTION
Feature: New Pricer Sticky Ad /pricer/get-all Promotion

  Background:
    Given I download data from Google Sheet "Sticky Ad Price"

  @AUTHOR_VUHOANG_ME_API
  Scenario: /v1/public price.promotion
    Then Sticky Ads Promotion Price of all subcates should be correct



