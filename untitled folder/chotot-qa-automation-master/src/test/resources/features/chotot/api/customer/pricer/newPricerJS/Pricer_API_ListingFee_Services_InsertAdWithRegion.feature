@SERVICE_PRICER
@TELEGRAM_ME
@TAG_VUHOANG_SON_THUAN_TUAN
#@NEWPRICER_SERVICE_IAWR
Feature: New Pricer Service /v2/private/pricer/services?region&category?ad_type  insert_ads

  Background:
    Given I download data from Google Sheet "Listing Fee Price"
    And I login my account to check price


  @AUTHOR_VUHOANG_ME_API
  Scenario: insert_ads_with_regions, PTY Sell Ad with Account
    Then Listing Fee Services my PTY Sell Ad Private Region Dashboard To Chotot Cate "1010, 1020, 1040" which are applied for "Hồ Chí Minh, Đồng Nai, Bà Rịa - VT, Tây Ninh, Bình Phước, Bình Dương" should be correct for a specified account

  @AUTHOR_VUHOANG_ME_API
  Scenario: insert_ads_with_regions, PTY Sell Ad with Account
    Then Listing Fee my PTY For Rent Ad Private Region Dashboard To Chotot which are applied for only region 13000 should be correct for a specified account

  @AUTHOR_VUHOANG_ME_API
  Scenario: insert_ads_with_regions, VEH Sell Ad with Account
    Then Listing Fee Services my VEH Sell Ad Private Region Dashboard To Chotot Cate "2010, 2020, 2050" which are applied for "All Regions" should be correct for a specified account

  @AUTHOR_VUHOANG_ME_API
  Scenario: insert_ads_with_regions, ELT Sell Ad with Account
    Then Listing Fee Services my ELT Sell Ad Private Region Dashboard To Chotot Cate "5030, 5010, 5020" which are applied for "All Regions" should be correct for a specified account


