@SERVICE_CHAT-NOTIFICATION
@TELEGRAM_GAMMA
@TAG_QUOCTRAN_KHOADO_NGUYEN
@CHAT-NOTIFICATION
Feature: Chotot Chat Notification


#Spec: https://701search.atlassian.net/wiki/spaces/gamma/pages/2167931709/Chat+CT+Beta+2

  Background:
    Given Reset test data of Chat
    Given "User A" had an "AdX" on listing
    And New account "User B"
    And User does not block notification on web

  @AUTHOR_QUOCTRAN_GAMMA_UI @OPEN_CHOTOT
  Scenario: As an User, I can click on a new chat notification on web and m-site when opening chotot
    Given "User A" log in Chotot
    And "User A" is on home page
    When "User B" send a new message to "AdX" of "User A"
    Then "User A" can see a new notification at home page
    When "User A" click on that notification
    Then "User A" can go directly to chat page
    And "User A" can see new message with "User B"
    When "User A" back to Chat page without selecting any room
    And "User B" send new message to "User A" again
    Then "User A" can see new notification message with "User B"
    When "User A" focus on chat room with "User B"
    And "User B" send new message to "User A" again
    Then "User A" does not see new notification with "User B"
    And New message is shown directly on chat detail

  @AUTHOR_QUOCTRAN_GAMMA_UI @NOT_OPEN_CHOTOT
  Scenario: As an User, I can click on a new chat notification on web and m-site when not opening chotot
    Given "User A" log in Chotot
    And "User A" turn off the current browser
    When "User B" send a new message to "AdX" of "User A"
    Then "User A" can see a new notification at home page
    When "User A" click on that notification
    Then "User A" can go directly to chat page
    And "User A" can see new message with "User B"
    When "User A" back to Chat page without selecting any room
    And "User B" send new message to "User A" again
    Then "User A" can see new notification message with "User B"
    When "User A" focus on chat room with "User B"
    And "User B" send new message to "User A" again
    Then "User A" does not see new notification with "User B"
    And New message is shown directly on chat detail







