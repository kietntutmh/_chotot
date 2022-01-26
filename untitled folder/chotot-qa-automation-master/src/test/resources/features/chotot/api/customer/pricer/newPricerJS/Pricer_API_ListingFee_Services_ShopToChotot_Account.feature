@SERVICE_PRICER
@TELEGRAM_ME
@TAG_VUHOANG_SON_THUAN_TUAN
#@NEWPRICER_SERVICE_STCT_AC
Feature: New Pricer Service Account /v2/private/pricer/services?region&category?ad_type  insert_ads

  Background:
    Given I download data from Google Sheet "Listing Fee Price"
    And I login my account to check price

  @AUTHOR_VUHOANG_ME_API
  Scenario: shop_to_chotot, PTY Sell SHOP Ad With Account ID
    Given I login my account with Shop PTY to check price
    Then Listing Fee Services my PTY Sell Ad Shop Dashboard To Chotot Cate "1010, 1020, 1040" which are applied for "Hồ Chí Minh, Đồng Nai, Bà Rịa - VT, Tây Ninh, Bình Phước, Bình Dương" should be correct for a specified account

  @AUTHOR_VUHOANG_ME_API
  Scenario: shop_to_chotot, PTY Rent SHOP Ad Region 13000
    Given I login my account with Shop PTY to check price
    Then Listing Fee my PTY For Rent Ad Shop Dashboard To Chotot which are applied for only region 13000 should be correct for a specified account

  @AUTHOR_VUHOANG_ME_API
  Scenario: shop_to_chotot, VEH Sell SHOP Ad With Account ID
    Given I login my account with Shop VEH to check price
    Then Listing Fee Services my VEH Sell Ad Shop Dashboard To Chotot Cate "2010, 2020, 2050" which are applied for "All Regions" should be correct for a specified account

  @AUTHOR_VUHOANG_ME_API
  Scenario: shop_to_chotot, ELT Sell SHOP Ad With Account ID
    Given I login my account with Shop ELT to check price
    Then Listing Fee Services my ELT Sell Ad Shop Dashboard To Chotot Cate "5030, 5010, 5020" which are applied for "All Regions" should be correct for a specified account
