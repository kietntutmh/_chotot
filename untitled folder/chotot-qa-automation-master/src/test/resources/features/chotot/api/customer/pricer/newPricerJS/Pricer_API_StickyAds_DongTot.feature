@SERVICE_PRICER
@TELEGRAM_ME
@TAG_VUHOANG_SON
#@NEWPRICER_STICKYAD_DT
Feature: New Pricer Sticky Ad /pricer/get-all Dong Tot

  Background:
    Given I download data from Google Sheet "Sticky Ad Price"

  @AUTHOR_VUHOANG_ME_API
  Scenario: /v1/public price.credit
    Then Sticky Ads Dong Tot Price of all subcates should be correct




