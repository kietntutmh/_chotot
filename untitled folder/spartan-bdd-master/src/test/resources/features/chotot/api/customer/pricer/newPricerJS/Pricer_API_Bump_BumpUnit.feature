@SERVICE_PRICER
@CUSTOMER
@TAG_VUHOANG
#@NEWPRICER_BUMP
Feature: New Pricer API Bump & Bump UNIT API /v1/private/pricer/pricing/get-all

  Background:
    Given I download data from Google Sheet "Bump Price"
    And I login my account to check price

    #------------- Check Vnd ------------
  @AUTHOR_VUHOANG_ME_API
  Scenario: bump_price.bump.VND
    Then Bump Price of all subcates should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: bump_price.bump3days.VND
    Then Bump 3 days Price of all subcates should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: bump_price.bump7days.VND
    Then Bump 7 days Price of all subcates should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: bump_price.timer_bump.VND
    Then Bump Timer Price of all subcates should be correct

    #------------- Check Dong Tot ------------
  @AUTHOR_VUHOANG_ME_API
  Scenario: bump_price.bump.credit
    Then Bump Price Dong Tot of all subcates should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: bump_price.bump3days.credit
    Then Bump 3 days Price Dong Tot of all subcates should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: bump_price.bump7days.credit
    Then Bump 7 days Price Dong Tot of all subcates should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: bump_price.timer_bump.credit
    Then Bump Timer Price Dong Tot of all subcates should be correct


#------------- BUMP PRICE UNIT : VND------------
  @AUTHOR_VUHOANG_ME_API
  Scenario: bump_price_unit.bump.VND
    Then Bump Price UNIT VND of all subcates should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: bump_price_unit.bump3days.VND
    Then Bump 3 days Price UNIT VND of all subcates should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: bump_price_unit.bump7days.VND
    Then Bump 7 days Price UNIT VND of all subcates should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: bump_price_unit.timer_bump.VND
    Then Bump Timer Price UNIT VND of all subcates should be correct

    #------------- BUMP PRICE UNIT : Dong Tot------------
  @AUTHOR_VUHOANG_ME_API
  Scenario: bump_price_unit.bump.DongTot
    Then Bump Price UNIT DongTot of all subcates should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: bump_price_unit.bump3days.DongTot
    Then Bump 3 days Price UNIT DongTot of all subcates should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: bump_price_unit.bump7days.DongTot
    Then Bump 7 days Price UNIT DongTot of all subcates should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: bump_price_unit.timer_bump.DongTot
    Then Bump Timer Price UNIT DongTot of all subcates should be correct

 #------------- BUMP PRICE UNIT : Promotion------------
  @AUTHOR_VUHOANG_ME_API
  Scenario: bump_price_unit.bump.promotion
    Then Bump Price UNIT Promotion of all subcates should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: bump_price_unit.bump3days.promotion
    Then Bump 3 days Price UNIT Promotion of all subcates should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: bump_price_unit.bump7days.promotion
    Then Bump 7 days Price UNIT Promotion of all subcates should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: bump_price_unit.timer_bump.promotion
    Then Bump Timer Price UNIT Promotion of all subcates should be correct

