@SERVICE_USER_ADS
@CUSTOMER
@TAG_VUHOANG_HUE_SONNGUYEN_THAM
Feature: Listing Fee API - PTY

  Background:
    Given I register a new Chotot User
    And I deposit Dong Tot via Momo

  @AUTHOR_VUHOANG_ME_API  @SMOKE
  Scenario: SMOKE TEST PTY, 2nd PRIVATE AD is charge
    Given I post "2" new House Ad using api successfully
    Then My new Ad should be charge

  #----------- Selling : Insert Ad -----------
  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Private User, First Ad is free
    Given I post a new House Ad using api successfully
    Then I should see my New Ad displayed on Listing Fee Tab KHÁC
    And I should see my New Ad displayed on Listing Fee filter ĐỢI DUYỆT
    And My new Ad should be free

  @AUTHOR_VUHOANG_ME_API @DONE
  Scenario: Private User, Second House Ad is charged
    Given I post "2" new House Ad using api successfully
    Then I should see my New Ad displayed on Listing Fee Tab CẦN THANH TOÁN
    And My new Ad should be charge

  @AUTHOR_VUHOANG_ME_API @DONE
  Scenario: Private User, Second Apartment Ad is charged
    Given I post "2" new Apartment Ad using api successfully
    Then I should see my New Ad displayed on Listing Fee Tab CẦN THANH TOÁN
    And My new Ad should be charge

  @AUTHOR_VUHOANG_ME_API @DONE
  Scenario: Private User, Second Land Ad is charged
    Given I post "2" new Land Ad using api successfully
    Then I should see my New Ad displayed on Listing Fee Tab CẦN THANH TOÁN
    And My new Ad should be charge

  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Private User, Second Ad is free when First Ad is rejected
    Given I post a new Land Ad as a Private ad API and be rejected
    When I post a new Land Ad using api successfully
    Then I should see my New Ad displayed on Listing Fee Tab KHÁC
    And I should see my New Ad displayed on Listing Fee filter ĐỢI DUYỆT
    And My new Ad should be free

  #----------- Buying : Insert Ad -----------
  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Private User, Buy Apartment Ad is free
    Given I post a new Apartment Ad as a private ad API for buying
    Then I should see my New Ad displayed on Listing Fee Tab KHÁC
    And I should see my New Ad displayed on Listing Fee filter ĐỢI DUYỆT
    And My new Ad should be free

  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Private User, Buy House Ad is free
    Given I post a new House Ad as a private ad API for buying
    Then I should see my New Ad displayed on Listing Fee Tab KHÁC
    And I should see my New Ad displayed on Listing Fee filter ĐỢI DUYỆT
    And My new Ad should be free

  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Private User, Buy Land Ad is free
    And I post a new Land Ad as a Private ad API for buying
    Then I should see my New Ad displayed on Listing Fee Tab KHÁC
    And I should see my New Ad displayed on Listing Fee filter ĐỢI DUYỆT
    And My new Ad should be free

  #----------- Paying -----------
  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Private User, Second Apartment Ad displays on ĐỢI DUYỆT after paid
    Given I post "2" new Apartment Ad using api successfully
    When I pay for my New Ad
    Then I should see my New Ad displayed on Listing Fee Tab KHÁC
    And I should see my New Ad displayed on Listing Fee filter ĐỢI DUYỆT

  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Private User, Second House Ad displays on ĐỢI DUYỆT after paid
    Given I post "2" new House Ad using api successfully
    When I pay for my New Ad
    Then I should see my New Ad displayed on Listing Fee Tab KHÁC
    And I should see my New Ad displayed on Listing Fee filter ĐỢI DUYỆT

  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Private User, Second Land Ad displays on ĐỢI DUYỆT after paid
    Given I post "2" new Land Ad using api successfully
    When I pay for my New Ad
    Then I should see my New Ad displayed on Listing Fee Tab KHÁC
    And I should see my New Ad displayed on Listing Fee filter ĐỢI DUYỆT

  #----------- Selling : Edit Ad &n rejected ad-----------
  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Private User, Ad displays on BỊ TỪ CHỐI after rejected
    Given I post a new Land Ad as a Private ad API and be rejected
    Then I should see my New Ad displayed on Listing Fee Tab BỊ TỪ CHỐI

  @AUTHOR_VUHOANG_ME_API     @DONE
  Scenario: Private User, Ad displays on BỊ TỪ CHỐI after rejected incase edit ad
    Given I post a new Land Ad as a Private ad API and be rejected
    When I edit my new Land Ad as a Private ad API and be rejected
    Then I should see my New Ad displayed on Listing Fee Tab BỊ TỪ CHỐI

  #----------- Refund -----------
  @AUTHOR_VUHOANG_ME_API     @DONE
  Scenario: Private User, Refund listing fee after ad rejected
    Given I post a new Apartment Ad using api successfully
    When I post a new Apartment Ad and pay for Listing Fee But ad is rejected and DT is refunded
    Then I should see my New Ad displayed on Listing Fee Tab BỊ TỪ CHỐI
    And I should see the POS refund in my Order History
