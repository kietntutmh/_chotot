@SERVICE_PRICER
@TELEGRAM_ME
@TAG_VUHOANG_SON_THUAN_TUAN
#@NEWPRICER_SERVICE_IA
Feature: New Pricer Service /v2/private/pricer/services?region&category?ad_type  insert_ads

  Background:
    Given I download data from Google Sheet "Listing Fee Price"
    And I login my account to check price

  @AUTHOR_VUHOANG_ME_API
  Scenario: insert_ads, Insert PTY Ad Private
    Then Listing Fee Services PTY Sell Ad Private Cate "1010, 1020, 1040" which are applied for "Hồ Chí Minh, Đồng Nai, Bà Rịa - VT, Tây Ninh, Bình Phước, Bình Dương" should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: insert_ads, Insert PTY Ad Rent Region 13000 Private
    Then Listing Fee PTY For Rent Ad which are applied for only region 13000 should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: insert_ads, Insert VEH Ad Private
    Then Listing Fee Services VEH Sell Ad Private Cate "2010, 2020, 2050" which are applied for "All regions" should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: insert_ads, Insert ELT Ad Private
    Then Listing Fee Services ELT Sell Ad Private Cate "5030, 5010, 5020" which are applied for "All regions" should be correct
