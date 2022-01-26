@SERVICE_DT_BURNOUT
@CUSTOMER
@TAG_VUHOANG_QUANGTRAN
@DT_BURN_OUT
Feature: Dong Tot Burn Out API

  Background:
    Given I login my Account to burn Dong Tot

  @AUTHOR_VUHOANG_ME_API  @SMOKE
  Scenario: Đồng Tốt will paid in turn DTF > DTBT > DT365
    Given I topup "3000" Dong Tot Free
    And I topup 10k Dong Tot Bank Transfer
    And I topup 20k Dong Tot 365
    When I post a PTY House Ad and Chotot is reviewing my Ad
    And I pay for Bump by Đồng Tốt
    Then My Dong Tot Free balance should be "0"
    Then My Dong Tot Bank Transfer balance should be "0"
    And My Dong Tot Paid balance should be "18000"


  @AUTHOR_VUHOANG_ME_API
  Scenario: DTF, User can pay by DTF
    Given I topup "15000" Dong Tot Free
    Then My Dong Tot Free balance should be "15000"
    And I topup 20k Dong Tot Bank Transfer
    And I topup 20k Dong Tot 365
    When I post a PTY House Ad and Chotot accepted my Ad
    And I pay for Bump by Đồng Tốt
    Then My Dong Tot Free balance should be "0"
    Then My Dong Tot Paid balance should be "40000"


  @AUTHOR_VUHOANG_ME_API
  Scenario: DTF FIFO, DTF belongs to The code which is redeemed first will be used first
    Given I topup "15000" Dong Tot Free from Code A
    Given I topup "15000" Dong Tot Free from Code B that has closer expired date than code A
    When I post a PTY House Ad and Chotot accepted my Ad
    And I pay for Bump by Đồng Tốt
    Then My Dong Tot Free balance of Code A should be "0"
    Then My Dong Tot Free balance of Code B should be "15000"


  @AUTHOR_VUHOANG_ME_API
  Scenario: DTF Expired, User can't use expired DTF
    Given I topup "15000" Dong Tot Free from Code A but expired
    Given I topup "15000" Dong Tot Free from Code B that has closer expired date than code A
    When I post a PTY House Ad and Chotot accepted my Ad
    And I pay for Bump by Đồng Tốt
    Then My Dong Tot Free balance of Code A should be "15000"
    Then My Dong Tot Free balance of Code B should be "0"


  @AUTHOR_VUHOANG_ME_API
  Scenario: Đồng Tốt will paid in turn DTBT > DT365 when DTF expired
    Given I topup "100000" Dong Tot Free but expired
    And I topup 10k Dong Tot Bank Transfer
    And I topup 20k Dong Tot 365
    When I post a PTY House Ad and Chotot is reviewing my Ad
    And I pay for Bump by Đồng Tốt
    Then My Dong Tot Free balance should be "0"
    And My Dong Tot Paid balance should be "15000"
