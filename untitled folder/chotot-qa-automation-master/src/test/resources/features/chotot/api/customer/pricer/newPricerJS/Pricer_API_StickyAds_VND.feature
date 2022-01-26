@SERVICE_PRICER
@CUSTOMER
@TAG_VUHOANG_SON
#@NEWPRICER_STICKYAD_VND
Feature: New Pricer Sticky Ad /pricer/get-all vnd

  Background:
    Given I download data from Google Sheet "Sticky Ad Price"

  @AUTHOR_VUHOANG_ME_API
  Scenario: /v1/public price.vnd
    Then Sticky Ads Price of all subcates should be correct




