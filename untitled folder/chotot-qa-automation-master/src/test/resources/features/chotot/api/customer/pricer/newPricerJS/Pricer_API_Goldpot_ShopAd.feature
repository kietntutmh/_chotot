@SERVICE_PRICER
@TELEGRAM_ME
@TAG_VUHOANG_SON
#@NEWPRICER_GOLDPOT_SHOP
Feature: New Pricer Goldpot SHOP /pricer/goldpot with /v1/internal and /v1/private

  Background:
    Given I download data from Google Sheet "Listing Fee Price"
    And I login my account to check price


  @AUTHOR_VUHOANG_ME_API
  Scenario: v1/private/pricer/goldpot, Insert SHOP Ad PTY
    Then Listing Fee Pricer Goldpot PTY Sell Ad Private Shop Dashboard To Chotot Cate "1010, 1020, 1040" which are applied for "HCM, Đồng Nai, Bà Rịa - VT, Tây Ninh, Bình Phước, Bình Dương" should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: v1/internal/pricer/goldpot, Insert SHOP Ad PTY
    Then Listing Fee Pricer Goldpot PTY Sell Ad Internal Shop Dashboard To Chotot Cate "1010, 1020, 1040" which are applied for "HCM, Đồng Nai, Bà Rịa - VT, Tây Ninh, Bình Phước, Bình Dương" should be correct


  @AUTHOR_VUHOANG_ME_API
  Scenario: v1/private/pricer/goldpot, Insert SHOP Ad PTY Rent Region 13000
    Then Listing Fee PTY For Rent Ad Private Shop Dashboard To Chotot which are applied for only region 13000 should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: v1/internal/pricer/goldpot, Insert SHOP Ad PTY Rent Region 13000
    Then Listing Fee PTY For Rent Ad Internal Shop Dashboard To Chotot which are applied for only region 13000 should be correct


  @AUTHOR_VUHOANG_ME_API
  Scenario: v1/private/pricer/goldpot, Insert SHOP Ad VEH
    Then Listing Fee Pricer Goldpot VEH Sell Ad Private Shop Dashboard To Chotot Cate "2010, 2020, 2050" which are applied for "All regions" should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: v1/internal/pricer/goldpot, Insert SHOP Ad VEH
    Then Listing Fee Pricer Goldpot VEH Sell Ad Internal Shop Dashboard To Chotot Cate "2010, 2020, 2050" which are applied for "All regions" should be correct


  @AUTHOR_VUHOANG_ME_API
  Scenario: v1/internal/pricer/goldpot, Insert SHOP Ad ELT
    Then Listing Fee Pricer Goldpot ELT Sell Ad Private Shop Dashboard To Chotot Cate "5030, 5010, 5020" which are applied for "All regions" should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: v1/private/pricer/goldpot, Insert SHOP Ad ELT
    Then Listing Fee Pricer Goldpot ELT Sell Ad Internal Shop Dashboard To Chotot Cate "5030, 5010, 5020" which are applied for "All regions" should be correct

