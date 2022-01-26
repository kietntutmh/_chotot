@SERVICE_FLASHAD
@INSERTAD_PET_UI
@TELEGRAM_MABU
Feature: Insert Ad Pet on UI

  Background: Setup Environment
    Given I register a new Chotot User

  @SMOKE @AUTHOR_QUOCTRAN_MABU_API
  Scenario: All InsertAd Pet APIs are working
    Then All of InsertAd Pet APIs should be working

  # Private
  @AUTHOR_QUOCTRAN_MABU_UI @CI1
  Scenario: As a Private User, I can post a Rooster Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Rooster Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI1
  Scenario: As a Private User, I can post a Dog Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Dog Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI2
  Scenario: As a Private User, I can post a Bird Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Bird Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI2
  Scenario: As a Private User, I can post a Cat Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Cat Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI3
  Scenario: As a Private User, I can post a Other_Pets Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Other_Pets Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI3
  Scenario: As a Private User, I can post a Food_Service Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Food_Service Ad as a private ad
    Then I should see Manage Ad page displayed

  # Pro
  @AUTHOR_QUOCTRAN_MABU_UI @CI4
  Scenario: As a Private User, I can post a Rooster Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Rooster Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI4
  Scenario: As a Private User, I can post a Dog Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Dog Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI5
  Scenario: As a Private User, I can post a Bird Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Bird Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI5
  Scenario: As a Private User, I can post a Cat Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Cat Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI6
  Scenario: As a Private User, I can post a Other_Pets Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Other_Pets Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI6
  Scenario: As a Private User, I can post a Food_Service Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Food_Service Ad as a Pro ad
    Then I should see Manage Ad page displayed
