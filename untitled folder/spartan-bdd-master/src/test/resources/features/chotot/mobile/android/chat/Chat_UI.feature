@SERVICE_CHAT-IOS
@TELEGRAM_GAMMA
@TAG_QUOCTRAN
@CHAT-ANDROID
Feature: Chotot Chat android


#Spec: https://701search.atlassian.net/wiki/spaces/gamma/pages/2154266841/Chat+CT+Beta+1

  Background:
    Given Reset test data of Chat
    Given "User A" had an "AdX" on listing
    And New account "User B"

# -------------------- HAPPY/MAIN CASES --------------------

  @AUTHOR_QUOCTRAN_GAMMA_UI  @SEND_TEXT
  Scenario: As an User, I can send text to chat
    Given "User B" log in Chotot android app
    When "User B" can go to adview page by listID "AdX" of "User A"
    And "User B" can click on label Chat with Seller
    Then A new chat room with "User A" is created
    When "User B" can send a text message
    And "User B" can see the sent message displayed in chat detail
    And New message displays on chat room list

  @AUTHOR_QUOCTRAN_GAMMA_UI  @SEND_IMAGE
  Scenario: As an User, I can send image to chat
    Given "User B" log in Chotot android app
    When "User B" can go to adview page by listID "AdX" of "User A"
    And "User B" can click on label Chat with Seller
    When "User B" send an image to chat using upload icon
    Then "User B" can see uploaded image in chat detail
    And New image displays on chat room list
    When "User B" send an image to chat by pasting an copied image
    Then "User B" can see pasted image in chat detail
    When "User B" click on an image in chat detail
    Then "User B" can see the original image with original quality

  @AUTHOR_QUOCTRAN_GAMMA_UI  @CHAT_TEMPLATE
  Scenario: As an User, I can send message using chat templates
    Given "User B" log in Chotot android app
    And "User B" can go to adview page by listID "AdX" of "User A"
    When "User B" click on chat template message in adview screen
    Then "User B" can see the selected template displayed in chat detail


# -------------------- UNHAPPY CASES --------------------

  @AUTHOR_QUOCTRAN_GAMMA_UI  @INVALID_USER
  Scenario: As an invalid User, I cannot use Chat function
    When I have not verified OTP my Chotot account
    And I log in Chotot and go to Chat page
    Then I get an error message "Ask verify OTP" first
    When I verified OTP
    Then I can use Chat function as normal

  @AUTHOR_QUOCTRAN_GAMMA_UI  @SLOW_NETWORK
  Scenario: As an User, I cannot use Chat function with slow network condition
    When I use a slow network
    Then I am able to use Chotot chat as normal



