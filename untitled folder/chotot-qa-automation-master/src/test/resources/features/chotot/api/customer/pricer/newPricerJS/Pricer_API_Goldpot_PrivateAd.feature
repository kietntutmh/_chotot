@SERVICE_PRICER
@TELEGRAM_ME
@TAG_VUHOANG_SON
#@NEWPRICER_GOLDPOT
Feature: New Pricer Goldpot /pricer/goldpot with /v1/internal and /v1/private

  Background:
    Given I download data from Google Sheet "Listing Fee Price"
    And I login my account to check price

  #---------------- PRIVATE ----------------
  #VUHOANG DEBUG
  @AUTHOR_VUHOANG_ME_API
  Scenario: v1/internal/pricer/goldpot, Insert Ad PTY
    Then Listing Fee Pricer Goldpot Internal PTY Sell Ad Private Cate "1010, 1020, 1040" which are applied for "HCM, Đồng Nai, Bà Rịa - VT, Tây Ninh, Bình Phước, Bình Dương" should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: v1/private/pricer/goldpot, Insert Ad PTY
    Then Listing Fee Pricer Goldpot Private PTY Sell Ad Private Cate "1010, 1020, 1040" which are applied for "HCM, Đồng Nai, Bà Rịa - VT, Tây Ninh, Bình Phước, Bình Dương" should be correct


  @AUTHOR_VUHOANG_ME_API
  Scenario: v1/internal/pricer/goldpot, Insert Rent Ad PTY region 13000
    Then Listing Fee Pricer Goldpot Internal PTY For Rent Ad which are applied for only region 13000 should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: v1/private/pricer/goldpot, Insert Rent Ad PTY region 13000
    Then Listing Fee Pricer Goldpot Private PTY For Rent Ad which are applied for only region 13000 should be correct



  @AUTHOR_VUHOANG_ME_API
  Scenario: v1/internal/pricer/goldpot, Insert Ad VEH
    Then Listing Fee Pricer Goldpot Internal VEH Sell Ad Private Cate "2010, 2020, 2050" which are applied for "All regions" should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: v1/private/pricer/goldpot, Insert Ad VEH
    Then Listing Fee Pricer Goldpot Private VEH Sell Ad Private Cate "2010, 2020, 2050" which are applied for "All regions" should be correct


  @AUTHOR_VUHOANG_ME_API
  Scenario: v1/internal/pricer/goldpot, Insert Ad ELT
    Then Listing Fee Pricer Goldpot Internal ELT Sell Ad Private Cate "5030, 5010, 5020" which are applied for "All regions" should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: v1/private/pricer/goldpot, Insert Ad ELT
    Then Listing Fee Pricer Goldpot Private ELT Sell Ad Private Cate "5030, 5010, 5020" which are applied for "All regions" should be correct



