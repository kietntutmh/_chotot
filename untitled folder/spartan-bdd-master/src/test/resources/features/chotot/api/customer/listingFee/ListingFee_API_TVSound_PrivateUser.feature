@SERVICE_USER_ADS
@CUSTOMER
@TAG_VUHOANG_HUE_SONNGUYEN_THAM
Feature: Listing Fee API - TV Sound Private User

  Background:
    Given I register a new Chotot User
    And I deposit Dong Tot via Momo

  @AUTHOR_VUHOANG_ME_API  @SMOKE
  Scenario: SMOKE TEST TVSound, 3rd PRIVATE AD is charge
    Given I post a new TV Sound Ad as a Private ad API
    Given I post a new TV Sound Ad as a Private ad API
    Given I post a new TV Sound Ad as a Private ad API
    And My new Ad should be charge

  #----------- Selling : Insert Ad -----------
  @AUTHOR_VUHOANG_ME_API  @LISTING_FEE_1  @SMOKE
  Scenario: Private User, First Ad is free
    Given I post a new TV Sound Ad as a Private ad API
    Then I should see my New Ad displayed on Listing Fee Tab KHÁC
    And I should see my New Ad displayed on Listing Fee filter ĐỢI DUYỆT
    And My new Ad should be free

  @AUTHOR_VUHOANG_ME_API  @LISTING_FEE_1
  Scenario: Private User, First Ad displays on ĐANG BÁN after accepted
    Given I post a new TV Sound Ad as a Private ad API and be accepted
    Then I should see my New Ad displayed on Listing Fee Tab ĐANG BÁN

  @AUTHOR_VUHOANG_ME_API   @LISTING_FEE_2
  Scenario: Private User, Second Ad is free
    Given I post a new TV Sound Ad as a Private ad API
    When I post a new TV Sound Ad as a Private ad API
    Then I should see my New Ad displayed on Listing Fee Tab KHÁC
    And I should see my New Ad displayed on Listing Fee filter ĐỢI DUYỆT
    And My new Second Ad should be free

  @AUTHOR_VUHOANG_ME_API   @LISTING_FEE_2
  Scenario: Private User, Third Ad is charge
    Given I post a new TV Sound Ad as a Private ad API
    Given I post a new TV Sound Ad as a Private ad API
    Given I post a new TV Sound Ad as a Private ad API
    Then I should see my New Ad displayed on Listing Fee Tab CẦN THANH TOÁN
    And My new Ad should be charge

  @AUTHOR_VUHOANG_ME_API   @LISTING_FEE_3
  Scenario: Private User, Third Ad is free when First Ad is rejected
    Given I post a new TV Sound Ad as a Private ad API and be rejected
    When I post a new TV Sound Ad as a Private ad API
    And I post a new TV Sound Ad as a Private ad API
    Then I should see my New Ad displayed on Listing Fee Tab KHÁC
    And I should see my New Ad displayed on Listing Fee filter ĐỢI DUYỆT
    And My new Ad should be free

  @AUTHOR_VUHOANG_ME_API   @LISTING_FEE_3
  Scenario: Private User, Third Ad is free when Second Ad is rejected
    Given I post a new TV Sound Ad as a Private ad API
    When I post a new TV Sound Ad as a Private ad API and be rejected
    When I post a new TV Sound Ad as a Private ad API
    Then I should see my New Ad displayed on Listing Fee Tab KHÁC
    And I should see my New Ad displayed on Listing Fee filter ĐỢI DUYỆT
    And My new Ad should be free

  #----------- Buying : Insert Ad -----------
  @AUTHOR_VUHOANG_ME_API   @LISTING_FEE_4
  Scenario: Private User, Buy Ad is free
    Given I post a new TV Sound Ad as a Private ad API for buying
    When I post a new TV Sound Ad as a Private ad API for buying
    When I post a new TV Sound Ad as a Private ad API for buying
    When I post a new TV Sound Ad as a Private ad API for buying
    When I post a new TV Sound Ad as a Private ad API for buying
    Then I should see my New Ad displayed on Listing Fee Tab KHÁC
    And I should see my New Ad displayed on Listing Fee filter ĐỢI DUYỆT
    And My new Ad should be free

  #----------- Paying -----------
  @AUTHOR_VUHOANG_ME_API   @LISTING_FEE_4
  Scenario: Private User, Third Ad displays on ĐỢI DUYỆT after paid
    Given I post a new TV Sound Ad as a Private ad API
    When I post a new TV Sound Ad as a Private ad API
    And I post a new TV Sound Ad as a Private ad API
    When I pay for my New Ad
    Then I should see my New Ad displayed on Listing Fee Tab KHÁC
    And I should see my New Ad displayed on Listing Fee filter ĐỢI DUYỆT

  #----------- Selling : Edit Ad &n rejected ad-----------
  @AUTHOR_VUHOANG_ME_API   @LISTING_FEE_7
  Scenario: Private User, Ad displays on BỊ TỪ CHỐI after rejected
    Given I post a new TV Sound Ad as a Private ad API and be rejected
    Then I should see my New Ad displayed on Listing Fee Tab BỊ TỪ CHỐI

  @AUTHOR_VUHOANG_ME_API     @LISTING_FEE_7
  Scenario: Private User, Ad displays on BỊ TỪ CHỐI after rejected
    Given I post a new TV Sound Ad as a Private ad API and be rejected
    When I edit my new TV Sound Ad as a Private ad API and be rejected
    Then I should see my New Ad displayed on Listing Fee Tab BỊ TỪ CHỐI
  #----------------------------------------------
    #After 3 published ad, user becomes a Pro user
    #Belong to insert Ad

