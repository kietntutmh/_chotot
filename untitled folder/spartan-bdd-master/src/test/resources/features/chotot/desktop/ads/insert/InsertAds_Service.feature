@SERVICE_FLASHAD
@INSERTAD_SERVICE_UI
@TELEGRAM_MABU
Feature: Insert Ad Service on UI

  Background: Setup Environment
    Given I register a new Chotot User

  @SMOKE @AUTHOR_QUOCTRAN_MABU_API
  Scenario: All InsertAd Service APIs are working
    Then All of InsertAd Service APIs should be working

  # Private
  @AUTHOR_QUOCTRAN_MABU_UI @CI1
  Scenario: As a Private User, I can post a Service Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Service Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI1
  Scenario: As a Private User, I can post a Travel Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Travel Ad as a private ad
    Then I should see Manage Ad page displayed

  # Pro
  @AUTHOR_QUOCTRAN_MABU_UI @CI2
  Scenario: As a Private User, I can post a Service Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Service Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI2
  Scenario: As a Private User, I can post a Travel Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Travel Ad as a Pro ad
    Then I should see Manage Ad page displayed

