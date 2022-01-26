@SERVICE_SCOOBY
@TELEGRAM_GAMMA
@TAG_QUOCTRAN_HONG
@SCOOBY
Feature: Scooby API

  Background:
    Given Reset test data of Chat
    Given "User A" had an "AdX" on listing
#    And New account "User B"

  @AUTHOR_QUOCTRAN_GAMMA_API  @CREATE_ROOM
  Scenario: As an User, I can create a new room with other one
    When "User B" create a new chat room with "AdX" of "User A"
#    Then A new room should be created between "User A" and "User B"

  @AUTHOR_QUOCTRAN_GAMMA_API  @CREATE_ROOM
  Scenario: As an User, I can send many messages of a same Ad and see all of them in only a same room
    When "User B" create a new chat room with "AdX" of "User A"
    And "User B" create other room to "AdX" of "User A"
    Then A new room shouldn't be created between "User A" and "User B"

  @AUTHOR_QUOCTRAN_GAMMA_API  @SEND_IMAGE
  Scenario: As an User, I can send an image to chat
    When "User B" create a new chat room with "AdX" of "User A"
    Then "User B" can send an image to "AdX" of "User A"

  @AUTHOR_QUOCTRAN_GAMMA_API  @SEND_MESSAGE
  Scenario: As an User, I cannot send a message with length > 2000 characters
    When "User B" create a new chat room with "AdX" of "User A"
    Then "User B" cannot send a message with length > 1000 characters

  @AUTHOR_QUOCTRAN_GAMMA_API @LIST_ROOM
  Scenario: As an User, I can see all of rooms in the Room List
    Given "User B" had an "AdY" on listing
    And I register another account "User C"
    When "User C" send a new message to "AdX" and "AdY"
    Then "User C" should see the new rooms in the Room List

  @AUTHOR_QUOCTRAN_GAMMA_API  @LIST_ROOM
  Scenario: As an User, I can not see hidden rooms in Room List
    When "User B" send a new message to "AdX" of "User A"
    And "User B" hides the room with "User A"
    Then "User B" shouldn't see the room in the Room List

  @AUTHOR_QUOCTRAN_GAMMA_API  @LIST_ROOM
  Scenario: As an User, I can see hidden rooms in Room List after the User send a new message
    When "User B" send a new message to "AdX" of "User A"
    And "User B" hides the room with "User A"
    When "User A" send a new message to existing room of "User B" again
    Then "User B" should see the room with "User A" in the Room List again

  @AUTHOR_QUOCTRAN_GAMMA_API  @LIST_ROOM
  Scenario: As an User, I can see the blocked room displays on the Room List when other User blocks me
    When "User B" send a new message to "AdX" of "User A"
    And "User B" blocks "User A"
    Then "User B" should see the room with "User A" in the Room List
    And "User A" should see the room with "User B" in the Room List as well

#  For manual only
#  @AUTHOR_QUOCTRAN_GAMMA_API  @LIST_ROOM
#  Scenario: As an User, I can see the muted room displays on the Room List when other User mute me
#    When "User B" send a new message to "AdX" of "User A"
#    And "User B" mute the room with "User A"
#    Then "User B" should see the room with "User A" in the Room List

  @AUTHOR_QUOCTRAN_GAMMA_API  @ROOM_INFO
  Scenario: As an User, I can see the chat room info
    When "User B" send a new message to "AdX" of "User A"
    Then "User B" can see the room info with "User A"

  @AUTHOR_QUOCTRAN_GAMMA_API  @ROOM_INFO
  Scenario: As an User, I cannot see the chat room info of others
    Given "User B" send a new message to "AdX" of "User A"
    And I register another account "User C"
    Then "User C" cannot see the room info of "User A" and "User B"

  @AUTHOR_QUOCTRAN_GAMMA_API  @GET_MESSAGE
  Scenario: As an User, I can see all messages of a room by a valid listId
    When "User B" send some messages to "AdX" of "User A"
      | Message chat 1 |
      | Message chat 2 |
      | Message chat 3 |
    Then "User B" should see all messages of "AdX" displaying in the room
      | Message chat 1 |
      | Message chat 2 |
      | Message chat 3 |

  @AUTHOR_QUOCTRAN_GAMMA_API  @GET_MESSAGE
  Scenario:As an User, I can see all messages of a hidden room by a valid listId
    When "User B" send some messages to "AdX" of "User A"
      | Hello 1 |
      | Hello 2 |
      | Hello 3 |
    When "User B" hides the room with "User A"
    Then "User A" still see all messages of "AdX" displaying in the room
      | Hello 1 |
      | Hello 2 |
      | Hello 3 |

#  For manual only
#  @AUTHOR_QUOCTRAN_GAMMA_API @GET_MESSAGE
#  Scenario: As an User, I can see all messages of a muted room by a valid listId
#    When "User B" send some messages to "AdX" of "User A"
#      | Hello 1 |
#      | Hello 2 |
#      | Hello 3 |
#    When "User B" mutes the room with "User A"
#    Then "User A" should see all messages of "AdX" displaying in the room

  @AUTHOR_QUOCTRAN_GAMMA_API @UNREAD_MESSAGE
  Scenario: As an User, I can see number of unread message
    When "User B" send some messages to "AdX" of "User A"
      | Hello 1 |
      | Hello 2 |
      | Hello 3 |
    Then "User A" should see "3" unread messages

  @AUTHOR_QUOCTRAN_GAMMA_API  @UNREAD_MESSAGE
  Scenario: As an User, I can see number of unread message after getting messages
    When "User B" send some messages to "AdX" of "User A"
      | Hello 1 |
      | Hello 2 |
      | Hello 3 |
    When "User A" read all unread messages of "AdX" in the room
    And "User B" send some messages to "AdX" of "User A" again
      | Hello 4 |
      | Hello 5 |
      | Hello 6 |
    Then "User A" should see "3" unread messages

  @AUTHOR_QUOCTRAN_GAMMA_API  @UNREAD_MESSAGE
  Scenario: As an User, I can see number of unread message after hiding the room
    When "User B" send some messages to "AdX" of "User A"
      | Hello 1 |
      | Hello 2 |
      | Hello 3 |
    And "User A" read all unread messages of "AdX" in the room
    When "User A" hides the room with "User B"
    When "User B" send some messages to "AdX" of "User A"
      | Hello 4 |
      | Hello 5 |
      | Hello 6 |
    Then "User A" should see "3" unread messages

  @AUTHOR_QUOCTRAN_GAMMA_API  @BLOCK_UNBLOCK_USER
  Scenario: As an User, I can not send messages to a blocked room
    When "User B" send a new message to "AdX" of "User A"
    And "User B" blocks "User A"
    Then "User B" cannot send a new message to "AdX" of "User A"
    And "User A" cannot send a new message to existing room of "User B"
    When "User B" unblocks "User A"
    Then "User B" can send a new message to "AdX" of "User A" again
    And "User B" can send a new message to existing room of "User A" again

#  @AUTHOR_QUOCTRAN_GAMMA_API  @MESSAGE_TEMPLATE
#  Scenario: As an User, I can see an existing message template
#    When "User B" send a new message to "AdX" of "User A"
#    Then "User B" can see an existing message template from "AdX"
#    And "User B" can see existing template in the room with "User A"

  @AUTHOR_QUOCTRAN_GAMMA_API  @REPORT_USER
  Scenario: As an User, I can report an existing chat user
    When "User B" send a new message to "AdX" of "User A"
    Then "User B" can report "User A"

  @AUTHOR_QUOCTRAN_GAMMA_API  @GET_USER
  Scenario: As an User, I can get public and private information of an user
    When "User B" send a new message to "AdX" of "User A"
    Then "User B" can get public information of "User A"
    And "User B" can get private information of "User B"

  @AUTHOR_QUOCTRAN_GAMMA_API  @REGISTER_USER
  Scenario: As an User, I can check register of me
    Then "User B" can register
    And "User B" can check register

  @AUTHOR_QUOCTRAN_GAMMA_API  @CHAT_OFFER
  Scenario: As an User, I can send an offer to chat
    Then "User B" send an offer to "AdX" of "User A"