@SERVICE_CHAT-API
@TELEGRAM_GAMMA
@TAG_QUOCTRAN_HONG_NGUYENTRAN
@CHAT-API
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




