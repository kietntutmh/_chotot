@SERVICE_HIERARCHY
@CUSTOMER
@TAG_VUHOANG_MINHTRAN
@HIERARCHY_MASLOW_EXPIRED
Feature: Account Hierarchy API - Maslow Notification

  Background:
    Given I login with my Biz Account that has DT4B balance "100000"

  @AUTHOR_VUHOANG_ME_API
  Scenario: Biz Notification, when added a new Child
    When I login with my Child Account that is linked to my Biz Account
    Then I should see a notification sent to my Biz Account

  @AUTHOR_VUHOANG_ME_API
  Scenario: Biz Notification, when be upgraded to Biz
    Then I should see a notification sent to my Biz Account

  @AUTHOR_VUHOANG_ME_API
  Scenario:  Biz Notification, when Child pays DT4B
    When I pay for premium service Feature Ad with my Child Account
    Then I should see a notification sent to my Biz Account