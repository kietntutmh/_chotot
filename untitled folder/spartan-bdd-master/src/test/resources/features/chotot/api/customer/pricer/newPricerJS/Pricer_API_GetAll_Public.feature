@SERVICE_PRICER
@TELEGRAM_ME
@TAG_VUHOANG_SON
#@NEWPRICER
Feature: New Pricer API GetAll Public - Compare STG with PROD

  #---------------- LISTING FEE /public/pricer/get-all ----------------
  @AUTHOR_VUHOANG_ME_API
  Scenario: Check Response Body format
    Then Compare Response Body Keys between 2 public APIs "https://gateway.chotot.org/v1/public/pricer/get-all" and "https://gateway.chotot.com/v1/public/pricer/get-all"
    Then Compare Response Body Structure between 2 public APIs "https://gateway.chotot.org/v1/public/pricer/get-all" and "https://gateway.chotot.com/v1/public/pricer/get-all"

  @AUTHOR_VUHOANG_ME_API
  Scenario: /v1/public/pricer/get-all, Insert PTY Ad
    Given I download data from Google Sheet "Listing Fee Price"
    Then Listing Fee Pricer GetAll Internal PTY Sell Ad Internal Cate "1010, 1020, 1040" which are applied for "Hồ Chí Minh, Đồng Nai, Bà Rịa - VT, Tây Ninh, Bình Phước, Bình Dương" should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: /v1/public/pricer/get-all, Insert PTY Ad Region 13000
    Given I download data from Google Sheet "Listing Fee Price"
    Then Listing Fee Pricer GetAll Internal PTY For Rent Ad which are applied for only region 13000 should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: /v1/public/pricer/get-all, Insert VEH Ad
    Given I download data from Google Sheet "Listing Fee Price"
    Then Listing Fee Pricer GetAll Internal VEH Sell Ad Internal Cate "2010, 2020, 2050" which are applied for "All regions" should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: /v1/public/pricer/get-all, Insert ELT Ad
    Given I download data from Google Sheet "Listing Fee Price"
    Then Listing Fee Pricer GetAll Internal ELT Sell Ad Internal Cate "5030, 5010, 5020" which are applied for "All regions" should be correct


    #---------------- LISTING FEE /v1/public/pricer/shops/get-all ----------------
  @AUTHOR_VUHOANG_ME_API
  Scenario: Check Response Body format
    Then Compare Response Body Keys between 2 public APIs "https://gateway.chotot.org/v1/public/pricer/shops/get-all" and "https://gateway.chotot.com/v1/public/pricer/shops/get-all"
    Then Compare Response Body Structure between 2 public APIs "https://gateway.chotot.org/v1/public/pricer/shops/get-all" and "https://gateway.chotot.com/v1/public/pricer/shops/get-all"

  @AUTHOR_VUHOANG_ME_API
  Scenario: /v1/public/pricer/shops/get-all, Shop Create VND
    Given I download data from Google Sheet "Shop Price"
    Then Shop Create Price VND of all categories should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: /v1/public/pricer/shops/get-all, Shop Create Dong Tot
    Given I download data from Google Sheet "Shop Price"
    Then Shop Create Price DongTot of all categories should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: /v1/public/pricer/shops/get-all, Shop Create Promotion
    Given I download data from Google Sheet "Shop Price"
    Then Shop Create Price Promotion of all categories should be correct


  @AUTHOR_VUHOANG_ME_API
  Scenario: /v1/public/pricer/shops/get-all, Shop Extend VND
    Given I download data from Google Sheet "Shop Price"
    Then Shop Extend Price VND of all categories should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: /v1/public/pricer/shops/get-all, Shop Extend Dong Tot
    Given I download data from Google Sheet "Shop Price"
    Then Shop Extend Price DongTot of all categories should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: /v1/public/pricer/shops/get-all, Shop Extend Promotion
    Given I download data from Google Sheet "Shop Price"
    Then Shop Extend Price Promotion of all categories should be correct




