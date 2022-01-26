@SERVICE_HIERARCHY
@CUSTOMER
@TAG_VUHOANG
@REWARD
Feature: Reward Điểm Tốt to Đồng Tốt API
# 5 points 1000 Dong Tot


  Background:
    Given I login my account to redeem Điểm Tốt to Đồng Tốt

  @AUTHOR_VUHOANG_ME_API  @DONE
#  Scenario: Điểm Tốt to Đồng Tốt, 25 points to 5000
#    Given I have "500" points in my Account
#    When I redeem Code 5000 with 25 points
#    Then My point number should be "475"   #After generated a new code, API call Insert record (Code on UI) basing on reward ID (diem tru)
#    When I redeem my Code to Đồng Tốt
#    Then My Dong Tot balance should be "5000" after trading Voucher
#
#  @AUTHOR_VUHOANG_ME_API  @DONE
#  Scenario: Redeem Voucher 5k to Dong Tot
#    Given I login my account "0964155768" with password "123123"
#    Then My Dong Tot Free balance should be "10000" after trading Voucher
  
  
  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Redeem Voucher 5k to Dong Tot
    Given I redeem my points to "100" voucher 5000
    And I topup 100k Dong Tot to my account ""
    When I redeem all my Codes to Đồng Tốt
    Then My Dong Tot Free balance should be "5000" after trading Voucher
    Then My Dong Tot Free should be expired after "1" month
    Then My Dong Tot balance should be "100000" after trading Voucher


  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Redeem 3 Voucher 5k to Dong Tot
    Given I redeem my points to "3" voucher 5000
    And I topup 100k Dong Tot to my account ""
    When I redeem all my Codes to Đồng Tốt
    Then My Dong Tot Free balance should be "15000" after trading Voucher
    Then My Dong Tot Free should be expired after "1" month
    Then My Dong Tot balance should be "100000" after trading Voucher


  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Redeem Voucher 10k to Dong Tot
    Given I redeem my points to "1" voucher 10000
    And I topup 100k Dong Tot to my account ""
    When I redeem all my Codes to Đồng Tốt
    Then My Dong Tot Free balance should be "10000" after trading Voucher
    Then My Dong Tot Free should be expired after "1" month
    Then My Dong Tot balance should be "100000" after trading Voucher

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Redeem Voucher 15k to Dong Tot
    Given I redeem my points to "1" voucher 15000
    And I topup 100k Dong Tot to my account ""
    When I redeem all my Codes to Đồng Tốt
    Then My Dong Tot Free balance should be "15000" after trading Voucher
    Then My Dong Tot Free should be expired after "1" month
    Then My Dong Tot balance should be "100000" after trading Voucher




