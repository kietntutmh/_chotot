@SERVICE_HIERARCHY
@CUSTOMER
@TAG_VUHOANG_MINHTRAN
@REWARD
Feature: Reward Điểm Tốt to Đồng Tốt API: Pay Order
# 5 points 1000 Dong Tot

  Background:
    Given I login my account to pay Đồng Tốt FREE

  @AUTHOR_VUHOANG_ME_API
  Scenario: As an Customer, my Đồng Tốt is charged when I pay for POS with not enough Đồng Tốt Free
#    Given I have 5k Đồng Tốt Free
    And I have 200k Đồng Tốt
    When I pay Đồng Tốt for Premium Service of "2" new Ads that are published
    Then My Dong Tot balance should be matched with total paid