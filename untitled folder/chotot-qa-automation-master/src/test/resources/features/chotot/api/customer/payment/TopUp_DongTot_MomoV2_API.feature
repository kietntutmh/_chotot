@SERVICE_TOPUP_MOMO_V2
@TELEGRAM_ME
@TAG_VUHOANG_QUANGTRAN
@TOPUP_MOMO_V2
Feature: Top up Dong Tot API - Momo V2

  @AUTHOR_QUANGTRAN_ME_API @DONE
  Scenario: User can top up 100k Dong Tot via Momo
    Given I login my Account
    When I topup 100k Đồng Tốt
    Then My Dong Tot balance should be "100000"

  @AUTHOR_QUANGTRAN_ME_API @DONE
  Scenario: User can top up 500k Dong Tot via Momo
    Given I login my Account
    When I topup 500k Đồng Tốt
    Then My Dong Tot balance should be "500000"

  @AUTHOR_QUANGTRAN_ME_API @DONE
  Scenario: User can top up 3000k Dong Tot via Momo
    Given I login my Account
    When I topup 3000k Đồng Tốt
    Then My Dong Tot balance should be "3000000"