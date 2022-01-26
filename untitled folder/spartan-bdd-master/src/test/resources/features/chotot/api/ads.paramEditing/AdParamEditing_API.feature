@SERVICE_AD-EDITOR-CONTROLLER
@TAG_QUOCTRAN_TUANTRAN
@AD-EDITOR-CONTROLLER
Feature: Ad Param Editing

  Background:
    Given New user is created to test Ad Editing Params

  # 3030
  @AUTHOR_QUOCTRAN_GAMMA_API @DONE
  Scenario: Ad Param Editing, Verify the maximum processing time of a new ad is up to 2 minutes with test rule
    When User post a new private ad "3030" to test ad param editing
    Then New ad is processed up to 2 minutes

   # 3030
  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: Ad Param Editing, Verify edited ad param is properly with test rule
    When User post a new private ad "3030" to test ad param editing
    Then Edited ad param is proper

  # 3030
  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: Ad Param Editing, Verify the maximum processing time of a new ad is up to 2 minutes with ML rules
    When User post a new private ad "3030" to test ad param editing
    Then New ad is processed up to 2 minutes

   # 3030
  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: Ad Param Editing, Verify edited ad param is properly with ML rules
    When User post a new private ad "3030" to test ad param editing
    Then Edited ad param is proper

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: Ad Param Editing, Verify insert ad flow when <ad editor controller> service is down
    When <ad editor controller> service is down
    Then User still inserts ad normally

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: Ad Param Editing, Verify <ad editor controller> service works normally when input many ads
    When Having many new ads
    Then <ad editor controller> service still works normally

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: Ad Param Editing, Verify <ad editor controller> service works normally when decreasing reviewer accounts
    When Decreasing reviewer accounts
    And Insert many ads
    Then <ad editor controller> service still works normally