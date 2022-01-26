@SERVICE_FLASHAD
@INSERTAD_JOBS_UI
@TELEGRAM_MABU
Feature: Insert Ad Jobs on UI

  Background: Setup Environment
    Given I register a new Chotot User

  @SMOKE @AUTHOR_QUOCTRAN_MABU_API
  Scenario: All InsertAd Job APIs are working
    Then All of InsertAd Job APIs should be working

  # Private
  @AUTHOR_QUOCTRAN_MABU_UI @CI1
  Scenario: As a Private User, I can post a Recruitment Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Recruitment Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI1
  Scenario: As a Private User, I can post a Find_Job Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Find_Job Ad as a private ad
    Then I should see Manage Ad page displayed

  # Pro
  @AUTHOR_QUOCTRAN_MABU_UI @CI2
  Scenario: As a Private User, I can post a Recruitment Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Recruitment Ad as a Pro ad
    Then I should see Manage Ad page displayed