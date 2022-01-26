@SERVICE_PRICER
@TELEGRAM_ME
@TAG_VUHOANG_SON
#@NEWPRICER_LISTINGFEE
Feature: ListingFee /v1/public/pricer/get-all Pricer API

  Background:
    Given I download data from Google Sheet "Listing Fee Price"
    And I login my account to check price

  #---------------- PRIVATE ----------------
  @AUTHOR_VUHOANG_ME_API
  Scenario: /v1/public/pricer/get-all, Insert Ad PTY
    Then Listing Fee Pricer GetAll Internal PTY Sell Ad Internal Cate "1010, 1020, 1040" which are applied for "Hồ Chí Minh, Đồng Nai, Bà Rịa - VT, Tây Ninh, Bình Phước, Bình Dương" should be correct

#  @AUTHOR_VUHOANG_ME_API
#  Scenario: /v1/public/pricer/get-all, Insert Shop Ad
#    Then Listing Fee Pricer GetAll Internal PTY Sell Ad Shop Internal Cate "1010, 1020, 1040" which are applied for "Đồng Nai, Bà Rịa - VT, Tây Ninh, Bình Phước, Bình Dương" should be correct


  @AUTHOR_VUHOANG_ME_API
  Scenario: /v1/public/pricer/get-all, Insert Ad PTY Region 13000
    Then Listing Fee Pricer GetAll Internal PTY For Rent Ad which are applied for only region 13000 should be correct

#  @AUTHOR_VUHOANG_ME_API
#  Scenario: /v1/public/pricer/get-all, ShopToChotot Region 13000
#    Then Listing Fee Pricer GetAll Internal PTY For Rent Ad Shop which are applied for only region 13000 should be correct


  @AUTHOR_VUHOANG_ME_API
  Scenario: /v1/public/pricer/get-all, Insert Ad VEH
    Then Listing Fee Pricer GetAll Internal VEH Sell Ad Internal Cate "2010, 2020, 2050" which are applied for "All regions" should be correct

#  @AUTHOR_VUHOANG_ME_API
#  Scenario: /v1/public/pricer/get-all, Insert SHOP Ad VEH
#    Then Listing Fee Pricer GetAll Internal VEH Sell Ad Shop Internal Cate "2010, 2020, 2050" which are applied for "All regions" should be correct


  @AUTHOR_VUHOANG_ME_API
  Scenario: /v1/public/pricer/get-all, Insert Ad ELT
    Then Listing Fee Pricer GetAll Internal ELT Sell Ad Internal Cate "5030, 5010, 5020" which are applied for "All regions" should be correct

#  @AUTHOR_VUHOANG_ME_API
#  Scenario: Listing Fee ELT GetAll Shop Internal, Cates applied for All Regions
#    Then Listing Fee Pricer GetAll Internal ELT Sell Ad Shop Internal Cate "5030, 5010, 5020" which are applied for "All regions" should be correct


