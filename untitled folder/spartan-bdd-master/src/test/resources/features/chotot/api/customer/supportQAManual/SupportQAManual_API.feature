@SERVICE_TEST
@CUSTOMER
@TAG_VUHOANG
Feature: Support manual QA

  @AUTHOR_VUHOANG_ME_API
  Scenario: Create User in Script
    Given I register "10000" New Accounts

  @AUTHOR_VUHOANG_ME_API
  Scenario: Insert 30 cate bếp gas new User
    Given Insert "30" Ads of cate "14030" subcate "14030" for new user

  @AUTHOR_VUHOANG_ME_API
  Scenario: Insert 30 cate bếp gas specified User
    Given Insert "30" Ads of cate "14030" subcate "14030" for user "0356316209"

  @AUTHOR_VUHOANG_ME_API   @SUPPORT
  Scenario: Insert 30 cate cây cảnh specified User
    Given Insert "30" Ads of cate "14090" subcate "14090" for user "0356516709"


  @AUTHOR_VUHOANG_ME_API
  Scenario: Create User
    Given I login my account to check price


  @AUTHOR_VUHOANG_ME_API
  Scenario: DEMO
    Given I demo code








