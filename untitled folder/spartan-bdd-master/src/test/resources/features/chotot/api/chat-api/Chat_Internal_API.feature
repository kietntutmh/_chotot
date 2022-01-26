@SERVICE_CHAT-API
@TELEGRAM_GAMMA
@TAG_QUOCTRAN_HONG_NGUYENTRAN
@CHAT-API
Feature: New Chat API - Internal

  Background:
    Given Reset test data for new Chat
    And New account "User B" for chat-api
    And "User A" had a "ListIDX" on listing

  #GET internal/user_channel/:user_id/channels?offset=0&limit=20
  @AUTHOR_QUOCTRAN_GAMMA_API  @LIST_CHANNEL @V0.1
  Scenario: As an internal user, I can get all existing channels with chat_type "ad-based"
    Given "User B" create a new chat channel with "ListIDX" of "User A" and chat_type "ad-based"
    Then I can get the existing channel between "User B" and "User A"

#  @AUTHOR_QUOCTRAN_GAMMA_API  @LIST_CHANNEL
#  Scenario: As an internal user, I can get all existing channels with chat_type "user-based"
#    When "User B" create a new chat channel with "ListIDX" of "User A" and chat_type "user-based"
#    Then "I" can get the existing channel between "User B" and "User A"



  #GET internal/user_channel/:user_id/:channel_id
  @AUTHOR_QUOCTRAN_GAMMA_API  @CHANNEL_INFO @V0.1
  Scenario: As an internal user, I can get channel info with chat_type ad-based
    Given "User B" create a new chat channel with "ListIDX" of "User A" and chat_type "ad-based"
    Then I can get the channel info between "User B" and "User A"

#  #GET internal/user_channel/:user_id/:channel_id
#  @AUTHOR_QUOCTRAN_GAMMA_API  @CHANNEL_INFO
#  Scenario: As an internal user, I can get channel info with chat_type user-based
#    Given "User B" create a new chat channel with "ListIDX" of "User A" and chat_type "user-based"
#    Then "I" can get the channel info between "User B" and "User A"



  #PATCH internal/user_channel/:user_id/:channel_id
  @AUTHOR_QUOCTRAN_GAMMA_API  @HIDE_CHANNEL
  Scenario: As an internal user, I can hide an existing channel with chat_type "ad-based"
    Given "User B" create a new chat channel with "ListIDX" of "User A" and chat_type "ad-based"
    When "I" hide the channel between "User B" and "User A"
    Then The existing channel is hidden

#  @AUTHOR_QUOCTRAN_GAMMA_API  @HIDE_CHANNEL
#  Scenario: As an internal user, I can hide an existing channel with chat_type "user-based"
#    Given "User B" create a new chat channel with "ListIDX" of "User A" and chat_type "user-based"
#    When "I" hide the channel between "User B" and "User A"
#    Then The existing channel is hidden



  #PATCH internal/user_channel/:user_id/:channel_id
  @AUTHOR_QUOCTRAN_GAMMA_API  @MUTE_UNMUTE_CHANNEL
  Scenario: As an internal user, I can mute or unmute an existing channel with chat_type "ad-based"
    Given "User B" create a new chat channel with "ListIDX" of "User A" and chat_type "ad-based"
    When "I" mute the channel between "User B" and "User A"
    Then The existing channel is muted
    When "I" unmute the channel between "User B" and "User A"
    Then The existing channel is unmuted

#  @AUTHOR_QUOCTRAN_GAMMA_API  @MUTE_UNMUTE_CHANNEL
#  Scenario: As an internal user, I can mute or unmute an existing channel with chat_type "user-based"
#    Given "User B" create a new chat channel with "ListIDX" of "User A" and chat_type "user-based"
#    When "I" mute the channel between "User B" and "User A"
#    Then The existing channel is muted
#    When "I" unmute the channel between "User B" and "User A"
#    Then The existing channel is unmuted



  #POST  internal/messages
  @AUTHOR_QUOCTRAN_GAMMA_API  @SEND_MESSAGE @V0.1
  Scenario: As an internal user, I can send a chat message to others with chat_type "ad-based"
    Given "User B" create a new chat channel with "ListIDX" of "User A" and chat_type "ad-based"
    Then I can send a text message to the channel between "User B" and "User A"
    And I can send an image message to the channel between "User B" and "User A"

#  @AUTHOR_QUOCTRAN_GAMMA_API  @SEND_MESSAGE
#  Scenario: As an internal user, I can send a chat message to others with chat_type "user-based"
#    Given "User B" create a new chat channel with "ListIDX" of "User A" and chat_type "user-based"
#    Then "I" can send a text message to the channel between "User B" and "User A"
#    And "I" can send an image message to the channel between "User B" and "User A"



  #POST  internal/messages
  @AUTHOR_QUOCTRAN_GAMMA_API  @UPDATE_MESSAGE
  Scenario: As an internal user, I can edit a chat message of other with chat_type "ad-based"
    Given "User B" create a new chat channel with "ListIDX" of "User A" and chat_type "ad-based"
    When "User B" can send a text message to "User A"
    Then "I" can edit the text message between "User B" and "User A"

  @AUTHOR_QUOCTRAN_GAMMA_API  @UPDATE_MESSAGE
  Scenario: As an internal user, I can edit a chat message of me with chat_type "ad-based"
    When "I" can send a text message to "ListIDX" of "User A" and chat_type "ad-based"
    Then "I" can edit the text message between "me" and "User A"

#  @AUTHOR_QUOCTRAN_GAMMA_API  @UPDATE_MESSAGE
#  Scenario: As an internal user, I can delete a chat message of other with chat_type "user-based"
#    Given "User B" create a new chat channel with "ListIDX" of "User A" and chat_type "user-based"
#    When "User B" can send a text message to "User A"
#    Then "I" can delete the text message between "User B" and "User A"
#    When "User B" can send an image message to "User A"
#    Then "I" can delete the image message with "User A"

#  @AUTHOR_QUOCTRAN_GAMMA_API  @UPDATE_MESSAGE
#  Scenario: As an internal user, I can delete a chat message of me with chat_type "user-based"
#    When "I" can send a text message to "User A" and chat_type "user-based"
#    Then "I" can delete the text message between "me" and "User A"
#    When "I" can send an image message to "User A"
#    Then "I" can delete the image message with "User A"



  #GET internal/users/:user_id/unread_count
  @AUTHOR_QUOCTRAN_GAMMA_API  @UNREAD_COUNT @V0.1
  Scenario: As an internal user, I can get total unread count of an existing channels
    Given "User B" create a new chat channel with "ListIDX" of "User A" and chat_type "ad-based"
    When "User B" can send a text message to "User A"
    And "User B" can send a text message to "User A" again
    Then I gets the total unread count of "User A" is "2"



  #PUT internal/set_read
  @AUTHOR_QUOCTRAN_GAMMA_API  @SET_READ @V0.1
  Scenario: As an internal user, I can set read on an existing channel with chat_type "ad-based"
    Given "User B" create a new chat channel with "ListIDX" of "User A" and chat_type "ad-based"
    When "User B" can send a text message to "User A"
    And "User B" can send an image message to "User A"
    Then "User A" gets the total unread count is "2"
    When I sets read to the channel between "User A" and "User B"
    Then "User A" gets the total unread count is "0"

#  @AUTHOR_QUOCTRAN_GAMMA_API  @SET_READ
#  Scenario: As an internal user, I can set read on an existing channel with chat_type "user-based"
#    Given "User B" create a new chat channel with "ListIDX" of "User A" and chat_type "user-based"
#    When "User B" can send a text message to "User A"
#    And "User B" can send an image message to "User A"
#    Then "User A" gets the total unread count is "2"
#    When "User A" sets read to the channel with "User B"
#    Then "User A" gets the total unread count is "0"



  #PUT internal/users/:user_id
  @AUTHOR_QUOCTRAN_GAMMA_API  @INSERT_UPDATE_USER @V0.1
  Scenario: As an internal user, I can insert or update an user
    Then I can insert "User A"
    And I can update "User A"



  #GET internal/users/:user_id
  @AUTHOR_QUOCTRAN_GAMMA_API  @USER_INFO @V0.1
  Scenario: As an internal user, I can get info of an user
    When I can update "User A"
    Then I can get info of "User A"



  #PATCH internal/ban_users/:user_id
  @AUTHOR_QUOCTRAN_GAMMA_API  @BAN_UNBAN_USER
  Scenario: As an internal user, I can ban or unban an user
    When "I" ban "User B"
    Then "User B" cannot send message to "User A"
    When "I" unban "User B"
    Then "User B" can send a text message to "User A"



  #GET internal/ban_users/:user_id
  @AUTHOR_QUOCTRAN_GAMMA_API  @GET_BANNED_UNBANNED_USER
  Scenario: As an internal user, I can get banned or unbanned user
    When "I" ban "User B"
    Then "I" can get banned "User B"
    When "I" unban "User B"
    Then "I" can get unbanned "User B"