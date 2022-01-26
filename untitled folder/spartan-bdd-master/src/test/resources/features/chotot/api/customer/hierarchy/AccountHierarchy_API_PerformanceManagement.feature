@SERVICE_HIERARCHY
@CUSTOMER
@TAG_VUHOANG_THUANLY
@HIERARCHY_PERFORMANCE_MANAGEMENT
Feature: Account Hierarchy API - Performance Management

  Background:
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 0                |
    And I post a new House Ad, topup and user Sticky Ad
    And I register "3" Child Account with "1" House Ad Listing Fee that is linked to my Biz Account

  @AUTHOR_THUANLY_ME_API @DONE
  Scenario: Parent accounts should be able to see total spending of child
    When The parent filter Biz cost by child from 1st day of the month to Today
    Then The parent should be able to view total spending of Biz Account within filter time

  @AUTHOR_THUANLY_ME_API @DONE
  Scenario: Parent accounts should be able to filter Biz cost by child
    When The parent filter Biz cost by child from 1st day of the month to Today
    Then The parent should be able to view total spending of Biz Account within filter time




