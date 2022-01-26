@SERVICE_USER_GROUP
@CUSTOMER
@TAG_VUHOANG
@USERGROUP
Feature: User Group API - Management

  @AUTHOR_VUHOANG_ME_API
  Scenario: User can create a User Group in an internal tool
    Given I create a new User Group
    When I add "10" new users into my User Group
    Then The users should be added to the User Group
    Then The users should contain in the User Group

  @AUTHOR_VUHOANG_ME_API
  Scenario: User can create a User Group in an internal tool
    Given I create a new User Group
    Then The new User Group should display in Group List