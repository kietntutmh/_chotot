#@SERVICE_SHOPS
#@TELEGRAM_ME
#@TAG_VUHOANG_SONNGUYEN_THAM
#@PROMOTION_INSERT
Feature: Shop PTY Package Promotion - Insert Ad with Paid/Free Subcate, Region & adType

  Background:
    Given I am back to Chợ Tốt Website to get Shop Promotion

  #--------------- Limitation Affection ---------------
  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Ad on Shop BEFORE promotion. Shop_To_Chotot is PAID
    Given I login my account to get Promotion for Shop PTY Ad
    When I post "1" Shop House Ad to Shop Dashboard
    When Chotot promotes "1" Free Listing for my Shop PTY
    Then I should be able to publish to Chotot "1" of my Ad as Paid
    Then My Free Slot should be still available

  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Ad on Shop AFTER promotion. Shop_To_Chotot is PAID
    Given I login my account to get Promotion for Shop PTY Ad
    When Chotot promotes "1" Free Listing for my Shop PTY
    When I post "1" Shop House Ad to Shop Dashboard
    Then I should be able to publish to Chotot "1" of my Ad as Paid
    Then My Free Slot should be still available

  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Ad is posted AFTER promotion. Ad_To_Chotot is FREE
    Given I login my account to get Promotion for Shop PTY Ad
    When Chotot promotes "2" Free Listing for my Shop PTY
    Then I should be able to post "2" Free Shop House Ad to Chotot


  @AUTHOR_VUHOANG_ME_API     @DONE
  Scenario: OutOfFree, Ad on Shop BEFORE promotion. Shop_To_Chotot is PAID
    Given I login my account to get Promotion for Shop PTY Ad
    And I post "1" Shop House Ad to Shop Dashboard
    When Chotot promotes "1" Free Listing for my Shop PTY
    When I post "1" Free Shop House Ad to Chotot
    Then I should publish Ad to Chotot as Paid

  @AUTHOR_VUHOANG_ME_API    @DONE
  Scenario: OutOfFree, Ad is posted AFTER promotion. Shop_To_Chotot is PAID
    Given I login my account to get Promotion for Shop PTY Ad
    When Chotot promotes "2" Free Listing for my Shop PTY
    When I post "2" Shop House Ad to Shop Dashboard
    And I post "2" Free Shop House Ad to Chotot
    Then I should publish Ad to Chotot as Paid

  @AUTHOR_VUHOANG_ME_API    @DONE
  Scenario: OutOfFree, Ad is posted AFTER promotion. Ad_To_Chotot is PAID
    Given I login my account to get Promotion for Shop PTY Ad
    When Chotot promotes "1" Free Listing for my Shop PTY
    When I post "1" Free Shop House Ad to Chotot
    Then I shouldn't be able to post Free Shop House Ad


  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Ad_To_ShopDashboard isn't affected by Promotion and it's always free
    Given I login my account to get Promotion for Shop PTY Ad
    When Chotot promotes "1" Free Listing for my Shop PTY
    When I post "2" Shop House Ad to Shop Dashboard
    Then My Free Slot should be still available


  #--------------- Insert Ad by regionId & category ---------------
  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Ad_To_Chotot is free with all Paid Regions
    Given I login my account to get Promotion for Shop PTY Ad
    When Chotot promotes "6" Free Listing for my Shop PTY
    Then I should be able to post Free Shop House Ad to Chotot with region TP.Hồ Chí Minh
    Then I should be able to post Free Shop House Ad to Chotot with region Đồng Nai
    Then I should be able to post Free Shop House Ad to Chotot with region Bà Rịa Vũng Tàu
    Then I should be able to post Free Shop House Ad to Chotot with region Tây Ninh
    Then I should be able to post Free Shop House Ad to Chotot with region Bình Phước
    Then I should be able to post Free Shop House Ad to Chotot with region Bình Dương
    Then My Free Slot should be unavailable
#    Then I should be able to post Free Shop House Ad to Chotot with region Hà Nội
    Then I shouldn't be able to post Free Shop House Ad

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Ad_To_Chotot is free with all SubCategory
    Given I login my account to get Promotion for Shop PTY Ad
    When Chotot promotes "5" Free Listing for my Shop PTY
    Then I should be able to post "1" Free Shop House Ad to Chotot
    Then I should be able to post "1" Free Shop Land Ad to Chotot
    Then I should be able to post "1" Free Shop Apartment Ad to Chotot
    Then I should be able to post "1" Free Shop Office Ad to Chotot
    Then I should be able to post "1" Free Shop Room Lease Ad to Chotot

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Rent Ad is FREE AFTER Promotion
    Given I login my account to get Promotion for Shop PTY Ad
    When Chotot promotes "1" Free Listing for my Shop PTY
    Then I should be able to post "1" Free Rent Shop House Ad to Chotot




