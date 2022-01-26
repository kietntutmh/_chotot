@SERVICE_USER_ADS
@CUSTOMER
@TAG_VUHOANG_HUE_SONNGUYEN_THAM
Feature: Listing Fee API - PTY

  Background:
    Given I register a new Chotot User
    And I deposit Dong Tot via Momo

  @AUTHOR_VUHOANG_ME_API  @SMOKE
  Scenario: SMOKE TEST PTY, First PRO AD is charge
    Given I post a new House Pro Ad using api successfully
    And My new Ad should be charge

  #----------- PRO User -----------
  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Pro User, First Apartment Pro Ad is charge
    Given I post a new Apartment Pro Ad using api successfully
    Then I should see my New Ad displayed on Listing Fee Tab CẦN THANH TOÁN
    And My new Ad should be charge
    And My new Ad should be a Pro ad

  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Pro User, First House Pro Ad is charge
    Given I post a new House Pro Ad using api successfully
    Then I should see my New Ad displayed on Listing Fee Tab CẦN THANH TOÁN
    And My new Ad should be charge
    And My new Ad should be a Pro ad

  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Pro User, First Land Pro Ad is charge
    Given I post a new Land Pro Ad using api successfully
    Then I should see my New Ad displayed on Listing Fee Tab CẦN THANH TOÁN
    And My new Ad should be charge
    And My new Ad should be a Pro ad

  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Pro User, Pro Buying Ad is free
    Given I post a new House Ad as a Pro ad API for buying
    Then I should see my New Ad displayed on Listing Fee Tab KHÁC
    And I should see my New Ad displayed on Listing Fee filter ĐỢI DUYỆT
    And My new Ad should be free

  #----------- Obligated Pro User -----------
  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Obligated Pro User, First Obligate Pro House Ad is charge
    Given I post "3" new House Ads as Private ads API and be accepted
    When I post a new House Pro Ad using api successfully
    Then I should see my New Ad displayed on Listing Fee Tab CẦN THANH TOÁN
    And My new Ad should be charge
    And My new Ad should be a Pro ad

  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Obligated Pro User, Obligated Pro Buying Ad is free
    Given I post "3" new House Ads as Private ads API and be accepted
    When I post a new House Ad as a Pro ad API for buying
    Then I should see my New Ad displayed on Listing Fee Tab KHÁC
    And I should see my New Ad displayed on Listing Fee filter ĐỢI DUYỆT
    And My new Ad should be free

