@SERVICE_CHAT-API
@TELEGRAM_GAMMA
@TAG_QUOCTRAN_HONG_NGUYENTRAN
@CHAT-API
Feature: New Chat API - Private

  Background:
    Given Reset test data for new Chat
    And New account "User B" for chat-api
    And "User A" had a "ListIDX" on listing


  #POST private/channels
  @AUTHOR_QUOCTRAN_GAMMA_API  @CREATE_CHANNEL @V0.1
  Scenario: As an private user, I can create a channel with chat_type "ad-based"
    When "User B" create a new chat channel with "User A", list_id empty and chat_type "ad-based"
    Then An error message is returned for missing list_id
    When "User B" create a new chat channel with "ListIDX" of "User A" and chat_type "ad-based"
    Then A new channel should be created between "User B" and "User A"
    When "User B" recreate a new chat channel with "ListIDX" of "User A" and chat_type "ad-based"
    Then A new channel should not be created between "User B" and "User A"

#  @AUTHOR_QUOCTRAN_GAMMA_API  @CREATE_CHANNEL
#  Scenario: As an private user, I can create a channel with chat_type "user-based"
#    When "User B" create a new chat channel with "User A", list_id empty and chat_type "user-based"
#    Then An error message is returned for missing list_id
#    When "User B" create a new chat channel with "ListIDX" of "User A" and chat_type "user-based"
#    Then A new channel should be created between "User B" and "User A"
#    When "User B" recreate a new chat channel with "ListIDX" of "User A" and chat_type "user-based"
#    Then A new channel should not be created between "User B" and "User A"



  #GET private/channels?offset=0&limit=20
  @AUTHOR_QUOCTRAN_GAMMA_API  @LIST_CHANNEL @V0.1
  Scenario: As an private user, I can get all existing channels with chat_type "ad-based"
    Given "User B" create a new chat channel with "ListIDX" of "User A" and chat_type "ad-based"
    Then "User B" can get the existing channel with "User A"
    Then "User A" can get the existing channel with "User B"

#  @AUTHOR_QUOCTRAN_GAMMA_API  @LIST_CHANNEL
#  Scenario: As an private user, I can get all existing channels with chat_type "user-based"
#    Given "User B" create a new chat channel with "ListIDX" of "User A" and chat_type "user-based"
#    Then "User B" can get the existing channel with "User A"



  #GET private/channels/:channel_id
  @AUTHOR_QUOCTRAN_GAMMA_API  @CHANNEL_INFO @V0.1
  Scenario: As an private user, I can get a channel info with chat_type ad-based
    Given "User B" create a new chat channel with "ListIDX" of "User A" and chat_type "ad-based"
    Then "User B" can get the channel info with "User A"
    Then "User A" can get the channel info with "User B"

#  #GET private/channels/:channel_id
#  @AUTHOR_QUOCTRAN_GAMMA_API  @CHANNEL_INFO
#  Scenario: As an private user, I can get a channel info with chat_type user-based
#    Given "User B" create a new chat channel with "ListIDX" of "User A" and chat_type "user-based"
#    Then "User B" can get the channel info with "User A"



  #PATCH private/channels/:channel_id
  @AUTHOR_QUOCTRAN_GAMMA_API  @HIDE_CHANNEL
  Scenario: As an private user, I can hide an existing channel with chat_type "ad-based"
    Given "User B" create a new chat channel with "ListIDX" of "User A" and chat_type "ad-based"
    When "User B" hide the channel with "User A"
    Then The existing channel is hidden

#  @AUTHOR_QUOCTRAN_GAMMA_API  @HIDE_CHANNEL
#  Scenario: As an private user, I can hide an existing channel with chat_type "user-based"
#    Given "User B" create a new chat channel with "ListIDX" of "User A" and chat_type "user-based"
#    When "User B" hide the channel with "User A"
#    Then The existing channel is hidden



  #PATCH private/channels/:channel_id
  @AUTHOR_QUOCTRAN_GAMMA_API  @MUTE_UNMUTE_CHANNEL
  Scenario: As an private user, I can mute or unmute an existing channel with chat_type "ad-based"
    Given "User B" create a new chat channel with "ListIDX" of "User A" and chat_type "ad-based"
    When "User B" mute the channel with "User A"
    Then The existing channel is muted
    When "User B" unmute the channel with "User A"
    Then The existing channel is unmuted

#  @AUTHOR_QUOCTRAN_GAMMA_API  @MUTE_UNMUTE_CHANNEL
#  Scenario: As an private user, I can mute or unmute an existing channel with chat_type "user-based"
#    Given "User B" create a new chat channel with "ListIDX" of "User A" and chat_type "user-based"
#    When "User B" mute the channel with "User A"
#    Then The existing channel is muted
#    When "User B" unmute the channel with "User A"
#    Then The existing channel is unmuted



  #POST private/messages
  @AUTHOR_QUOCTRAN_GAMMA_API  @SEND_MESSAGE @V0.1
  Scenario: As an private user, I can send a chat message to other with chat_type "ad-based"
    Given "User B" create a new chat channel with "ListIDX" of "User A" and chat_type "ad-based"
    Then "User B" can send a text message to "User A"
    And "User B" can send an image message to "User A"
    Then "User A" can send a text message to "User B"
    And "User A" can send an image message to "User B"

#  @AUTHOR_QUOCTRAN_GAMMA_API  @SEND_MESSAGE
#  Scenario: As an private user, I can send a chat message to other with chat_type "user-based"
#    Given "User B" create a new chat channel with "ListIDX" of "User A" and chat_type "user-based"
#    Then "User B" can send a text message to "User A"
#    And "User B" can send an image message to "User A"



  #POST private/messages
  @AUTHOR_QUOCTRAN_GAMMA_API  @UPDATE_MESSAGE
  Scenario: As an private user, I can edit a chat message with chat_type "ad-based"
    Given "User B" create a new chat channel with "ListIDX" of "User A" and chat_type "ad-based"
    And "User B" can send a text message to "User A"
    Then "User B" can edit the text message with "User A"

#  @AUTHOR_QUOCTRAN_GAMMA_API  @UPDATE_MESSAGE
#  Scenario: As an private user, I can delete a chat message with chat_type "user-based"
#    Given "User B" create a new chat channel with "ListIDX" of "User A" and chat_type "user-based"
#    And "User B" can send a text message to "User A"
#    Then "User B" can delete the text message with "User A"
#    When "User B" can send an image message to "User A"
#    Then "User B" can delete the image message with "User A"



  #GET private/unread_count
  @AUTHOR_QUOCTRAN_GAMMA_API  @UNREAD_COUNT @V0.1
  Scenario: As an private user, I can get total unread count of an existing channels
    Given "User B" create a new chat channel with "ListIDX" of "User A" and chat_type "ad-based"
    When "User B" can send a text message to "User A"
    And "User B" can send a text message to "User A" again
    Then "User A" gets the total unread count is "2"



  #GET private/channels/:channel_id/messages?after_ts=123&limit=20
  #GET private/channels/:channel_id/messages?before_ts=1234&limit=20
  @AUTHOR_QUOCTRAN_GAMMA_API  @GET_MESSAGES @V0.1
  Scenario: As an private user, I can get chat text messages by date time with chat_type "ad-based"
    Given "User B" create a new chat channel with "ListIDX" of "User A" and chat_type "ad-based"
    When "User B" can send a text message to "User A"
    And "User B" can send a text message to "User A" again
    Then "User A" can get "text" messages of "User B"
    When "User B" can send an image message to "User A"
    And "User B" can send an image message to "User A" again
    Then "User A" can get "image" messages of "User B"

#  @AUTHOR_QUOCTRAN_GAMMA_API  @GET_MESSAGES
#  Scenario: As an private user, I can get chat text messages by date time with chat_type "user-based"
#    Given "User B" create a new chat channel with "ListIDX" of "User A" and chat_type "user-based"
#    When "User B" can send a text message to "User A"
#    And "User B" can send a text message to "User A" again
#    Then "User A" can get "text" messages of "User B"
#    When "User B" can send an image message to "User A"
#    And "User B" can send an image message to "User A" again
#    Then "User A" can get "image" messages of "User B"



  #GET private/channels/:channel_id/changes?after_ts=123&limit=20
  @AUTHOR_QUOCTRAN_GAMMA_API  @GET_CHANGES @V0.1
  Scenario: As an private user, I can get changes when create/edit/delete a chat message with chat_type "ad-based"
    Given "User B" create a new chat channel with "ListIDX" of "User A" and chat_type "ad-based"
    When "User B" can send a text message to "User A"
    Then "User A" can get changes of "created" "text" message
#    When "User B" edit the text message with "User A"
#    Then "User A" can get changes of "edited" message
#    When "User B" delete the text message with "User A"
#    Then "User A" can get changes of "deleted" message
    When "User B" can send an image message to "User A"
    Then "User A" can get changes of "created" "image" message
#    When "User B" edit the image message with "User A"
#    Then "User A" can get changes of "edited" message
#    When "User B" delete the image message with "User A"
#    Then "User A" can get changes of "deleted" message

#  @AUTHOR_QUOCTRAN_GAMMA_API  @GET_CHANGES
#  Scenario: As an private user, I can get changes when create/edit/delete a chat message with chat_type "user-based"
#    Given "User B" create a new chat channel with "ListIDX" of "User A" and chat_type "user-based"
#    When "User B" can send a text message to "User A"
#    Then "User A" can get changes of "created" message
#    When "User B" edit the text message with "User A"
#    Then "User A" can get changes of "edited" message
#    When "User B" delete the text message with "User A"
#    Then "User A" can get changes of "deleted" message
#    When "User B" can send an image message to "User A"
#    Then "User A" can get changes of "created" message
#    When "User B" edit the image message with "User A"
#    Then "User A" can get changes of "edited" message
#    When "User B" delete the image message with "Us er A"
#    Then "User A" can get changes of "deleted" message



  #GET private/me @USER_INFO
  @AUTHOR_QUOCTRAN_GAMMA_API  @USER_INFO @V0.1
  Scenario: As an private user, I can get info of myself
    Then "User A" can get info of "User A"



  #GET public/users/:user_id/online_status
  @AUTHOR_QUOCTRAN_GAMMA_API  @USER_ONLINE_STATUS
  Scenario: As an public user, I can get user online status
    When "User A" is online
    Then "User B" can get online status of "User A"



  #PATCH private/block
  @AUTHOR_QUOCTRAN_GAMMA_API  @BLOCK_UNBLOCK_USER
  Scenario: As an private user, I can block or unblock an user
    Given "User B" create a new chat channel with "ListIDX" of "User A" and chat_type "ad-based"
    When "User A" block "User B"
    Then "User B" cannot send message to "User A"
    And "User A" cannot send message to "User B"
    When "User A" unblock "User B"
    Then "User B" can send a text message to "User A"
    And "User A" can send a text message to "User B"


    
  #PATCH private/block
  @AUTHOR_QUOCTRAN_GAMMA_API  @GET_BLOCKED_UNBLOCKED_USER
  Scenario: As an private user, I can get blocked or unblocked user
    Given "User B" create a new chat channel with "ListIDX" of "User A" and chat_type "ad-based"
    When "User A" block "User B"
    Then "User A" can get blocked "User B"
    When "User A" unblock "User B"
    Then "User A" can get unblocked "User B"

