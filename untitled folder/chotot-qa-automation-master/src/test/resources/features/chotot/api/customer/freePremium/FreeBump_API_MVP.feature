@SERVICE_POS
@CUSTOMER
@TAG_VUHOANG_THUANLY
@PROMOTION_FREEBUMP
#@PROMOTION_FREEBUMP_REDEEM
Feature: Free Promotion Free Premium - Free Bump API

  Background:
    Given I login my Account with 500k Đồng Tốt
    And I post a PTY House Ad and Chotot accepted my Ad

  @AUTHOR_THUANLY_ME_API @DONE
  Scenario: ChoTot can add a free bump redeem into a campaign
    When ChoTot add a new campaign free bump with status ACTIVE
    Then ChoTot can add a redeem into an active campaign for the user successfully

  @AUTHOR_THUANLY_ME_API @DONE
  Scenario: ChoTot can send an announcement box to user with redeem button
    When ChoTot should be able to add a new campaign free bump with status ACTIVE successfully
    And ChoTot can add a redeem into an active campaign for the user successfully
    Then An announcement box is sent to the user

  @AUTHOR_THUANLY_ME_API @DONE
  Scenario: I can redeem a free bump from promotion
    When ChoTot should be able to add a new campaign free bump with status ACTIVE successfully
    And ChoTot can add a redeem into an active campaign for the user successfully
    Then I click the button redeem to get free bump for my ad
    And I can redeem a free bump successfully

  @AUTHOR_THUANLY_ME_API @DONE
  Scenario: ChoTot can not send an announcement box redeem when campaign inactive
    When ChoTot add a new campaign free bump with status INACTIVE
    And ChoTot can add a redeem into an inactive campaign for the user successfully
    Then I should not be able to see the announcement box for free bump

  @AUTHOR_THUANLY_ME_API @DONE
  Scenario: ChoTot can inactive campaign after send an announcement box
    When ChoTot should be able to add a new campaign free bump with status ACTIVE successfully
    And ChoTot can add a redeem into an active campaign for the user successfully
    And An announcement box is sent to the user
    Then ChoTot inactive the campaign
    And I should not be able to see the announcement box for free bump


