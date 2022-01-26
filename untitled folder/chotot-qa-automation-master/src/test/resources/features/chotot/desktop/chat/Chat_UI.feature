@SERVICE_CHOTOT-CHAT
@TELEGRAM_GAMMA
@TAG_QUOCTRAN_KHOADO
@CHOTOT-CHAT
Feature: Chotot Chat UI

  Background:
    Given Reset test data of Chat
    Given "User A" had an "AdX" on listing
    And New account "User B"

  @AUTHOR_QUOCTRAN_GAMMA_UI  @SEND_TEXT_IMAGE
  Scenario: As an User, I can send text and image to chat
    Given I login with new account Chotot
    When I can go to adview page by listID "AdX" of "User A"
    And I can click on Chat label of seller
    Then I can send text message to chat
    Then I can send an image to chat

  @AUTHOR_QUOCTRAN_GAMMA_UI  @CHAT_TEMPLATE
  Scenario: As an User, I can send message using chat templates
    Given I login with new account Chotot
    And I can go to adview page by listID "AdX" of "User A"
    When I click on chat template message in adview screen
    Then I can go to chat page by click on Tiếp tục chat
    And I can see the sent message in chat detail
    When I click on chat template message in chat detail screen
    And I can see the sent message in chat detail

  @AUTHOR_QUOCTRAN_GAMMA_UI  @REPORT_BLOCK_REMOVE
  Scenario: As an User, I can report, block and remove a chat
    Given I login with new account Chotot
    When I can go to adview page by listID "AdX" of "User A"
    And I can click on Chat label of seller
    Then I can report this user with fraud reason
    When I block chat with this user
    Then I cannot send text message to chat
    When I unblock chat with this user
    Then I can send text message to chat
    Then I can remove chat with this user

  @AUTHOR_QUOCTRAN_GAMMA_UI  @OFFER_USER-PROFILE
  Scenario: As an User, I can send an offer to chat and go to user profile page
    Given I login with new account Chotot
    When I can go to adview page by listID "AdX" of "User A"
    And I can click on Chat label of seller
    Then I can send an offer to chat "1000"
    Then I can go to user profile page

  @AUTHOR_QUOCTRAN_GAMMA_UI  @APPOINTMENT
  Scenario: As an User, I can send an appointment to chat
    Given I login with new account Chotot
    When I can go to adview page by listID "AdX" of "User A"
    And I can click on Chat label of seller
    When I can send an appointment to chat with location "2 Đường Ngô Đức Kế"
    Then New Chat appointment is created