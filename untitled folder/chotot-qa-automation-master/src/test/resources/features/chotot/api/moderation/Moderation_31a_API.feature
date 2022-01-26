@SERVICE_PAPA-DUP-IMAGE-V2
@PAPA-DUP-IMAGE-V2
@PAPA-ANALYZER
@TAG_QUOCTRAN_TUANTRAN
Feature: Moderation - RR31a API

  Background:
    Given New user is created to test RR31a

  # 3030, 4010, 7010
  @AUTHOR_QUOCTRAN_GAMMA_API @PRIVATE-MAIN
  Scenario: 31a Auto duplicate (image) - Private user, new ad is rejected when having an accepted ad on listing
    Given User had an accepted ad "7010" for testing moderation RR31a
    When User post a new ad "7010" with the same image as existing ad
    Then New ad is rejected by RR31a

  # 3030, 4010, 7010
  @AUTHOR_QUOCTRAN_GAMMA_API @PRIVATE-MAIN
  Scenario: 31a Auto duplicate (image) - Private user, new ad is rejected when having a pending ad
    Given User had an accepted ad "3030" for testing moderation RR31a
    And User had a pending ad "3030" for testing moderation RR31a
    When User post a new ad "3030" with the same image as existing ad
    Then New ad is rejected by RR31a

  # 3030, 4010, 7010
  @AUTHOR_QUOCTRAN_GAMMA_API @PRIVATE-MAIN
  Scenario: 31a Auto duplicate (image) - Private user, new ad is rejected when having a hidden ad
    Given User had an accepted ad "3030" for testing moderation RR31a
    And User had a hidden ad "3030" for testing moderation RR31a
    When User post a new ad "3030" with the same image as existing ad
    Then New ad is rejected by RR31a

  # 3030, 7010
  @AUTHOR_QUOCTRAN_GAMMA_API @PRO-MAIN
  Scenario: 31a Auto duplicate (image) - Pro user, new ad is rejected when having an accepted ad on listing
    Given User had an accepted ad pro "7010" for testing moderation RR31a
    When User post a new ad pro "7010" with the same image as existing ad
    Then New ad is rejected by RR31a

  # 3030, 7010
  @AUTHOR_QUOCTRAN_GAMMA_API @PRO-MAIN
  Scenario: 31a Auto duplicate (image) - Pro user, new ad is rejected when having a pending ad
    Given User had an accepted ad pro "3030" for testing moderation RR31a
    And User had a pending ad pro "3030" for testing moderation RR31a
    When User post a new ad pro "3030" with the same image as existing ad
    Then New ad is rejected by RR31a

  # 3030, 7010
  @AUTHOR_QUOCTRAN_GAMMA_API @PRO-MAIN
  Scenario: 31a Auto duplicate (image) - Pro user, new ad is rejected when having a hidden ad
    Given User had an accepted ad pro "3030" for testing moderation RR31a
    And User had a hidden ad pro "3030" for testing moderation RR31a
    When User post a new ad pro "3030" with the same image as existing ad
    Then New ad is rejected by RR31a

  # 5030
  @AUTHOR_QUOCTRAN_GAMMA_API @SHOP-ELT
  Scenario: 31a Auto duplicate (image) - Private user, new ad is NOT rejected when having an accepted ad on listing of Shop ELT
    Given User had an shop ELT for testing moderation RR31a
    And User had an accepted ad "5030" for testing moderation RR31a
    When User post a new ad "5030" with the same image as existing ad
    Then New ad is rejected by RR31a

  # 3030
  @AUTHOR_QUOCTRAN_GAMMA_API @NEGATIVE-3RD-AD
  Scenario: 31a Auto duplicate (image) - Pro user, 3rd new ad is rejected when having a hidden ad
    Given User had an accepted ad pro "3030" for testing moderation RR31a
    And User had a hidden ad pro "3030" for testing moderation RR31a
    When User post a new ad pro "3030" with the same image as existing ad
    And User post a new ad pro "3030" with the same image as existing ad
    Then New ad is rejected by RR31a

  # 1020
  @AUTHOR_QUOCTRAN_GAMMA_API @NEGATIVE-PAID
  Scenario: 31a Auto duplicate (image) - Private user, new paid ad is NOT rejected when having an accepted ad on listing
    Given User had an accepted ad "1020" for testing moderation RR31a
    When User post a new paid ad "1020" with the same image as existing ad
    Then New paid ad is NOT rejected by RR31a

  # 4010, 3030
  @AUTHOR_QUOCTRAN_GAMMA_API @NEGATIVE-BUY-AD
  Scenario: 31a Auto duplicate (image) - Private user, new buy ad is NOT rejected when having an accepted ad on listing
    Given User had an accepted ad "3030" for testing moderation RR31a
    When User post a new buy ad "3030" with the same image as existing ad
    Then New buy ad is NOT rejected by RR31a

  # 1020
  @AUTHOR_QUOCTRAN_GAMMA_API @NEGATIVE-SHOP-PTY
  Scenario: 31a Auto duplicate (image) - Private user, new ad is NOT rejected when having an accepted ad on listing of Shop PTY
    Given User had an shop PTY for testing moderation RR31a
    And User had an accepted ad "1020" for testing moderation RR31a
    When User post a new ad "1020" with the same image as existing ad
    Then New ad is NOT rejected by RR31a

