@SERVICE_FLASHAD
@TELEGRAM_MABU
@INSERTAD_PTY_UI
Feature: Insert Ad PTY - Shop on UI

  Background: Setup Environment
    Given I login with Chotot User with Shop PTY

  @SMOKE @AUTHOR_QUOCTRAN_MABU_API
  Scenario: All InsertAd PTY APIs are working
    Then All of InsertAd PTY APIs should be working

  @AUTHOR_QUOCTRAN_MABU_UI @CI6
  Scenario: As a Shop, I can post a House Ad on my Shop successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new House Ad as a Shop ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI6
  Scenario: As a Shop, I can post a House Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new House Ad as a Chotot ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI7
  Scenario: As a Shop, I can post a Apartment Ad on my Shop successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Apartment Ad as a Shop ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI7
  Scenario: As a Shop, I can post a Apartment Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Apartment Ad as a Chotot ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI8
  Scenario: As a Shop, I can post a Land Ad on my Shop successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Land Ad as a Shop ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI8
  Scenario: As a Shop, I can post a Land Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Land Ad as a Chotot ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI9
  Scenario: As a Shop, I can post a Office Ad on my Shop successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Office Ad as a Shop ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI9
  Scenario: As a Shop, I can post a Office Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Office Ad as a Chotot ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI10
  Scenario: As a Shop, I can post a Room Ad on my Shop successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Room Ad as a Shop ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI10
  Scenario: As a Shop, I can post a Room Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Room Ad as a Chotot ad
    Then I should see Manage Ad page displayed