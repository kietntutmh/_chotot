@SERVICE_STICKYADS
@CUSTOMER
@TAG_VUHOANG_MINHTRAN
@AD_LISTING
Feature: Sticky Ad Slot in Ad Listing API

   Background:
     Given Chotot sets up number of Sticky slots for Ad Listing
     And Ad Limitation per page is "20"

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: As an User, I can see Tin Ưu Tiên displays fully in Sticky Ad Slots
    Given On Listing Page, I filter ads with region "13000"
    When I post "1" Sell Car Ads and pay POS Tin Ưu Tiên for them
    Then The number of Sticky Slots Page "1" should be equal as the available Sticky Ad Slots
    And The number of Sticky Slots Page "2" should be equal as the available Sticky Ad Slots

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: As an User, I can see not any Tin Ưu Tiên displays
    When I filter Category which doesn't exist any Ad
    Then The number of Sticky Slots Page "1" should be "0"
    Then The number of Sticky Slots Page "2" should be "0"

