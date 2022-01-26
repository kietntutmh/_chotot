@SERVICE_CHAT-API
@TELEGRAM_GAMMA
@TAG_QUOCTRAN_HONG_NGUYENTRAN
@CHAT-API
Feature: New Chat API - Private

  Background:
    Given Reset test data for new Chat
    And New account "User B" for chat-api
    And "User A" had a "ListIDX" on listing

  @AUTHOR_QUOCTRAN_GAMMA_API  @CHAT_SPAM
  Scenario: As an user, I cannot send >5 message in 1 second
    Given "User B" create a new chat channel with "ListIDX" of "User A" and chat_type "ad-based"
    When "User B" can send "5" text message to "User A" in 1 second
    And "User B" send a text message to "User A" again
    Then "User B" cannot send the message and got error code 429

  @AUTHOR_QUOCTRAN_GAMMA_API  @CHAT_SPAM
  Scenario: As an buyer, I cannot send >1 images in 1 second per chat room
    Given "User B" create a new chat channel with "ListIDX" of "User A" and chat_type "ad-based"
    When "User B" can send "1" images to "User A" in 1 second
    And "User B" send an image to "User A" again
    Then "User B" cannot send the image and got error code 429

  @AUTHOR_QUOCTRAN_GAMMA_API  @CHAT_SPAM
  Scenario: As an buyer, I cannot send >5 images in 30 minutes for all chat room
    When "User B" can send "5" duplicate images to "5" list room
    And "User B" send that image to old room again
    Then "User B" cannot send the image and got error code 429

  @AUTHOR_QUOCTRAN_GAMMA_API  @CHAT_SPAM
  Scenario: As an user, I cannot create >4 channels per second
    When "User B" create 4 new channels in 1 second
    And "User B" create a new channel again
    Then "User B" cannot create the channel and got error code 429

  @AUTHOR_QUOCTRAN_GAMMA_API  @CHAT_SPAM
  Scenario: As an user, I cannot create >30 channels per hour
    When "User B" create 30 new channels in 1 hour
    And "User B" create a new channel again
    Then "User B" cannot create the channel and got error code 429

  # Refer to list ban kw in CP
  @AUTHOR_QUOCTRAN_GAMMA_API  @CHAT_PROFANITY
  Scenario: As an user, I cannot send a stopword in chat
    When "User B" send a stopword "090xxx1234" to "User A"
    Then This message cannot be sent
    When "User B" send a normal message to "User A"
    Then This message can be sent as normal
    And A warning message is sent to slack channel ct-cs-chat