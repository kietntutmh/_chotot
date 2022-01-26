@SERVICE_FLASHAD
@INSERTAD_FASHION_UI
Feature: Insert Ad Fashion on UI

  Background: Setup Environment
    Given I register a new Chotot User

  @SMOKE @AUTHOR_QUOCTRAN_MABU_API
  Scenario: All InsertAd Fashion APIs are working
    Then All of InsertAd Fashion APIs should be working

  # Private
  @AUTHOR_QUOCTRAN_MABU_UI @CI1
  Scenario: As a Private User, I can post a Clothes Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Clothes Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI1
  Scenario: As a Private User, I can post a Watch Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Watch Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI2
  Scenario: As a Private User, I can post a Shoe Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Shoe Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI2
  Scenario: As a Private User, I can post a Handbag Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Handbag Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI3
  Scenario: As a Private User, I can post a Perfume Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Perfume Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI3
  Scenario: As a Private User, I can post a Accessories Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Accessories Ad as a private ad
    Then I should see Manage Ad page displayed

  # Pro
  @AUTHOR_QUOCTRAN_MABU_UI @CI4
  Scenario: As a Private User, I can post a Clothes Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Clothes Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI4
  Scenario: As a Private User, I can post a Watch Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Watch Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI5
  Scenario: As a Private User, I can post a Shoe Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Shoe Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI5
  Scenario: As a Private User, I can post a Handbag Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Handbag Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI6
  Scenario: As a Private User, I can post a Perfume Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Perfume Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI6
  Scenario: As a Private User, I can post a Accessories Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Accessories Ad as a Pro ad
    Then I should see Manage Ad page displayed

