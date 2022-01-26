@SERVICE_SCOOBY
@TELEGRAM_GAMMA
@TAG_QUOCTRAN_HONG
@SCOOBY
Feature: Chat Admin API

  Background:
    Given "User A" had an "AdX" on listing
    And New account "User B"
    And "User B" send a new message to "AdX" of "User A"

  @AUTHOR_QUOCTRAN_GAMMA_API @BAN_UNBAN
  Scenario: As an CP user, I can ban and unban an user
    When I ban "User B" in CP
    Then "User B" cannot send a new message to "AdX" of "User A" after banning
    When I unban "User B" in CP
    Then "User B" send a new message to "AdX" of "User A"

  @AUTHOR_QUOCTRAN_GAMMA_API @BANNED_KW
  Scenario: As an user, I can be automatically banned when enter a Ban Keyword
    When "User B" send a banned keyword "auto_scooby_banned_kw" to "AdX" of "User A"
    Then "User B" is banned automatically and cannot send other message to "AdX" of "User A"

  @AUTHOR_QUOCTRAN_GAMMA_API @COMBINED_KW
  Scenario: As an user, I can be automatically banned when enter a Combined Keyword
    When "User B" send a message "auto_banned1" to "AdX" of "User A"
    And "User B" send a message "auto_banned2" to "AdX" of "User A"
    Then "User B" is banned automatically and cannot send other message "auto_banned3" to "AdX" of "User A"

  @AUTHOR_QUOCTRAN_GAMMA_API @COMBINED_KW
  Scenario: As an user, I can be automatically banned when enter an advanced Combined Keyword
    When "User B" send a message "tét1" to "AdX" of "User A"
    And "User B" send a message "Tét23" to "AdX" of "User A"
    Then "User B" is banned automatically and cannot send other message "Tét3" to "AdX" of "User A"

#  For manual only
#  @AUTHOR_QUOCTRAN_GAMMA_API @CHAT_ADMIN
#  Scenario: As an CP user, I can manually ban and unban an user
#    Given I am Chat admin module and select Hammer
#    When I enter account id of "User B" and reason
#    And I click Ban button
#    Then "User B" cannot send new message to "AdX" of "User A"
#    When I click Unban button
#    Then "User B" send new message to "AdX" of "User A"




