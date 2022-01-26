@SERVICE_PRICER
@TELEGRAM_ME
@TAG_VUHOANG_SON
#@NEWPRICER_LISTINGFEE_INTERNAL
Feature: New Pricer ListingFee v1/internal/pricer/get-all/listing-fee

  Background:
    Given I download data from Google Sheet "Listing Fee Price"
    And I login my account to check price

  #---------------- PRIVATE ----------------
  @AUTHOR_VUHOANG_ME_API
  Scenario: get-all/listing-fee-pty, Insert Ad PTY
    Then Listing Fee Pricer GetAllListing Internal PTY Sell Ad Internal Cate "1010, 1020, 1040" which are applied for "Hồ Chí Minh, Đồng Nai, Bà Rịa - VT, Tây Ninh, Bình Phước, Bình Dương" should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: get-all/listing-fee-pty, Insert Shop Ad PTY
    Then Listing Fee Pricer GetAllListing Internal PTY Sell Ad Shop Internal Cate "1010, 1020, 1040" which are applied for "Hồ Chí Minh, Đồng Nai, Bà Rịa - VT, Tây Ninh, Bình Phước, Bình Dương" should be correct


  @AUTHOR_VUHOANG_ME_API
  Scenario: get-all/listing-fee-pty, Insert Ad Rent PTY Region 13000
    Then Listing Fee Pricer GetAllListing Internal PTY For Rent Ad which are applied for only region 13000 should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: get-all/listing-fee-pty, Insert Shop Ad PTY Region 13000
    Then Listing Fee Pricer GetAllListing Internal PTY For Rent Ad Shop which are applied for only region 13000 should be correct

#
#  @AUTHOR_VUHOANG_ME_API
#  Scenario: Listing Fee VEH GetAllListing Internal, Cates applied for All Regions
#    Then Listing Fee Pricer GetAllListing Internal VEH Sell Ad Internal Cate "2010, 2020, 2050" which are applied for "All regions" should be correct
#  @AUTHOR_VUHOANG_ME_API
#  Scenario: Listing Fee VEH GetAllListing Shop Internal, Cates applied for All Regions
#    Then Listing Fee Pricer GetAllListing Internal VEH Sell Ad Shop Internal Cate "2010, 2020, 2050" which are applied for "All regions" should be correct
#
#
#  @AUTHOR_VUHOANG_ME_API
#  Scenario: Listing Fee ELT GetAllListing Internal, Cates applied for All Regions
#    Then Listing Fee Pricer GetAllListing Internal ELT Sell Ad Internal Cate "5030, 5010, 5020" which are applied for "All regions" should be correct
#  @AUTHOR_VUHOANG_ME_API
#  Scenario: Listing Fee ELT GetAllListing Shop Internal, Cates applied for All Regions
#    Then Listing Fee Pricer GetAllListing Internal ELT Sell Ad Shop Internal Cate "5030, 5010, 5020" which are applied for "All regions" should be correct


