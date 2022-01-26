@SERVICE_USERPROFILER
@CUSTOMER
@TAG_VUHOANG_SONNGUYEN
@USER_PROFILER_REASON @PROFILER
Feature: User Profiler - Reason

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Check get Reason by Name
    Given Create a User Profiler new reason
    Then The User Profiler reason should exist when Get by name

  @AUTHOR_VUHOANG_ME_API @DONE
  Scenario: Check get Reason by All
    Given Create a User Profiler new reason
    Then The User Profiler reason should exist when Get all reasons

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Update Reason by Name
    Given Create a User Profiler new reason
    When Update the User Profiler reason with name, short detail, long detail
    Then The User Profiler reason should exist when Get by name
    Then The User Profiler reason should exist when Get all reasons

  @AUTHOR_VUHOANG_ME_API @DONE
  Scenario: Remove Reason by Name
    Given Create a User Profiler new reason
    When Remove the User Profiler reason of User Profiler
    Then The User Profiler reason should be deleted when Get all reasons
    Then The User Profiler reason should be deleted when Get by name






