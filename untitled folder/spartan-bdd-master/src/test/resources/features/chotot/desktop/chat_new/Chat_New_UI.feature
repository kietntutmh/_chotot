@SERVICE_CHOTOT-NEW-CHAT
@TELEGRAM_GAMMA
@TAG_QUOCTRAN_KHOADO
@CHOTOT-NEW-CHAT
Feature: Chotot New Chat UI


#Spec: https://701search.atlassian.net/wiki/spaces/gamma/pages/2154266841/Chat+CT+Beta+1

  Background:
    Given Reset test data of Chat
    Given "User A" had an "AdX" on listing
    And New account "User B"

# -------------------- HAPPY/MAIN CASES --------------------

  @AUTHOR_QUOCTRAN_GAMMA_UI  @SEND_TEXT
  Scenario: As an User, I can send text to chat
    Given "User B" log in Chotot
    When "User B" can go to adview page by listID "AdX" of "User A"
    And "User B" can click on label Chat with Seller
    Then A new chat room with "User A" is created
    When "User B" can send a text message 
    And "User B" can see the sent message displayed in chat detail

  @AUTHOR_QUOCTRAN_GAMMA_UI  @SEND_IMAGE
  Scenario: As an User, I can send image to chat
    Given "User B" log in Chotot
    When "User B" can go to adview page by listID "AdX" of "User A"
    And "User B" can click on label Chat with Seller
    When "User B" send an image to chat using upload icon
    Then "User B" can see uploaded image in chat detail
    When "User B" send an image to chat by pasting an copied image
    Then "User B" can see pasted image in chat detail
    When "User B" click on an image in chat detail
    Then "User B" can see the original image with original quality

  @AUTHOR_QUOCTRAN_GAMMA_UI  @CHAT_TEMPLATE
  Scenario: As an User, I can send message using chat templates
    Given "User B" log in Chotot
    And "User B" can go to adview page by listID "AdX" of "User A"
    When "User B" click on chat template message in adview screen
    Then "User B" can see the selected template displayed in chat detail
    When "User B" click on chat template message in chat detail
    And "User B" can see the selected template displayed in chat detail

  @AUTHOR_QUOCTRAN_GAMMA_UI  @UNREAD_COUNT
  Scenario: As an User, I can see unread count of chat
    Given "User B" log in Chotot
    When "User B" can go to adview page by listID "AdX" of "User A"
    And "User B" can click on label Chat with Seller
    When "User B" can send a text message
    And "User B" can see the sent message displayed in chat panel
    When "User A" log in Chotot
    Then "User A" see Chat icon having unread_count is "1"
    When "User A" click on Chat icon
    Then "User A" can go to chat page
    And "User A" can see the unread_count with "User B" is "1"
    When "User A" read the new message with "User B"
    Then The unread_count with "User B" is "0"

  @AUTHOR_QUOCTRAN_GAMMA_UI  @CHAT_HISTORY
  Scenario: As an User, I can retrieve my chat history when online and offline
    Given "User B" log in Chotot
    And "User A" had some chat messages with some users
    When "User B" go to chat page
    Then "User B" can load chat history with other ones
    When "User B" is offline
    Then "User B" can read the chat history as normal

  @AUTHOR_QUOCTRAN_GAMMA_UI  @ONLINE_OFFLINE_STATUS
  Scenario: As an User, my status online/offline is updated within 5 minutes
    Given "User B" log in Chotot on web
    And "User A" also log in Chotot on web
    When "User B" go to adview page by listID "AdX" of "User A"
    Then "User B" can see online status of "User A"
    When "User A" is offline
    Then "User B" can see offline status of "User A" within 5 minutes

  @AUTHOR_QUOCTRAN_GAMMA_UI  @CHAT_INDICATOR
  Scenario: As an User, I can see chat indicator of a message
    Given "User B" log in Chotot on web
    When "User B" can go to adview page by listID "AdX" of "User A"
    And "User B" can click on label Chat with Seller
    When "User B" can send a text message
    Then "User B" can see status sending and sent under message
    When "User A" read the message of "User B"
    Then "User B" can see status read under message

  @AUTHOR_QUOCTRAN_GAMMA_UI  @LAST_ACTIVE_TIME
  Scenario: As an User, I can see last active time next to chat indicator of an user
    Given "User B" log in Chotot on web
    And "User A" also log in Chotot on web
    When "User B" go to adview page by listID "AdX" of "User A"
    And "User B" can click on label Chat with Seller
    When "User B" send a text message
    Then "User B" can see last active time of "User A" next to chat indicator

  @AUTHOR_QUOCTRAN_GAMMA_UI  @BLOCK_USER
  Scenario: As an User, I can block other one
    Given "User B" log in Chotot
    When "User B" can go to adview page by listID "AdX" of "User A"
    And "User B" can click on label Chat with Seller
    When "User B" send a text message
    And "User A" can see the sent message displayed in chat detail
    When "User B" select block "User A"
    Then Chat detail view is disabled to "User B"
    And "User A" can see a message “Người này đã chặn chức năng nhắn tin với bạn”
    And Chat detail view is disabled to "User A"
    And "User A" can select block "User B"
    When Both of users unlock
    Then Chat back to normal

  @AUTHOR_QUOCTRAN_GAMMA_UI  @JOB_CV
  Scenario: As an User, I can apply job cv via chat
    Given "User B" log in Chotot
    When "User B" can go to adview page by listID "AdX" of "User A"
    And "User B" can click on label "Nộp hồ sơ"
    When "User B" enter full info to form
    Then This form can be sent to recruiter
    When "User B" chat with other job ads
    Then "User B" can select "Nộp hồ sơ" to send info



# -------------------- UNHAPPY CASES --------------------

  @AUTHOR_QUOCTRAN_GAMMA_UI  @INVALID_USER
  Scenario: As an invalid User, I cannot use Chat function
    When I have not verified OTP my Chotot account
    And I log in Chotot and go to Chat page
    Then I get an error message "Ask verify OTP" first
    When I verified OTP
    Then I can use Chat function as normal

  @AUTHOR_QUOCTRAN_GAMMA_UI  @INVALID_LOGIN
  Scenario: As an invalid Login, I cannot use Chat function
    When I have not logged in to Chotot
    And I enter Chat url directly to web browser
    Then I is redirected to Login page

  @AUTHOR_QUOCTRAN_GAMMA_UI  @SLOW_NETWORK
  Scenario: As an User, I cannot use Chat function with slow network condition
    When I use a slow network
    Then I am able to use Chotot chat as normal



