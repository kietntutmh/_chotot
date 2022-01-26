@SERVICE_FLASHAD
@INSERTAD_MOM-AND-BABY_UI
@TELEGRAM_MABU
Feature: Insert Ad Mom and Baby on UI

  Background: Setup Environment
    Given I register a new Chotot User

  # Private
  @AUTHOR_QUOCTRAN_MABU_UI
  Scenario: As a Private User, I can post a Mom and Baby Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Mom and Baby Ad as a private ad
    Then I should see Manage Ad page displayed

  # Pro
  @AUTHOR_QUOCTRAN_MABU_UI
  Scenario: As a Private User, I can post a Mom and Baby Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Mom and Baby Ad as a Pro ad
    Then I should see Manage Ad page displayed