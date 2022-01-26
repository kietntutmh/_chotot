@SERVICE_UAC
@CUSTOMER
@TAG_TUANCHIEU

Feature: UAC - User Profile - Update

  @AUTHOR_TUANCHIEU_CUSTOMTER_API
  Scenario: As a user, I can update all info successful
    Given I login my Account
    Then I update my profile is successful with valid value

  @AUTHOR_TUANCHIEU_CUSTOMTER_API
  Scenario: As a user, I update failed with invalid name
    Given I login my Account
    Then I update my profile failed with name have special character
    And I update my profile failed with name is phone number

  @AUTHOR_TUANCHIEU_CUSTOMTER_API
  Scenario: As a user, I update my profile failed with email invalid
    Given I login my Account
    Then I update failed with email format incorrect

  @AUTHOR_TUANCHIEU_CUSTOMTER_API
  Scenario: As a user, I update email failed with account have email before
    Given I login my Account
    Then I update email failed with account have email before

  @AUTHOR_TUANCHIEU_CUSTOMTER_API
  Scenario: As a user, I update my profile failed with gender param is invalid
    Given I login my Account
    Then I update my profile failed with gender param invalid

  @AUTHOR_TUANCHIEU_CUSTOMTER_API
  Scenario: As a user, I update my profile failed with birthday param is invalid
    Given I login my Account
    Then I update my profile failed with birthday param is different YYYY-MM-DD format

  @AUTHOR_TUANCHIEU_CUSTOMTER_API
  Scenario: As a user, I update my profile failed with gender param is invalid
    Given I login my Account
    Then I update my profile failed with gender param invalid