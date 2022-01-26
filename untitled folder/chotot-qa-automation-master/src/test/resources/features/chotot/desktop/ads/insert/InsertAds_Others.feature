@SERVICE_FLASHAD
@INSERTAD_OTHERS_UI
@TELEGRAM_MABU
Feature: Insert Ad Others on UI

  Background: Setup Environment
    Given I register a new Chotot User

  @SMOKE @AUTHOR_QUOCTRAN_MABU_API
  Scenario: All InsertAd Others APIs are working
    Then All of InsertAd Others APIs should be working

  # Private
  @AUTHOR_QUOCTRAN_MABU_UI
  Scenario: As a Private User, I can post a Others Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Others Ad as a private ad
    Then I should see Manage Ad page displayed

  # Pro
  @AUTHOR_QUOCTRAN_MABU_UI
  Scenario: As a Private User, I can post a Others Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Others Ad as a Pro ad
    Then I should see Manage Ad page displayed