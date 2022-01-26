@SERVICE_USERPROFILER
@CUSTOMER
@TAG_VUHOANG_SONNGUYEN
@USER_PROFILER_IA @PROFILER
Feature: User Profiler - Insert Ad API

  @AUTHOR_VUHOANG_ME_API @DONE
  Scenario: User becomes PRO |After 3 VEH AA
    Given I login my Account with 500k Đồng Tốt
    When I post 1st VEH Car Ad and Chotot accepted my Ad
    When I post and pay for 2nd VEH Car Ad and Chotot accepted my Ad
    When I post and pay for 3rd VEH Car Ad and Chotot accepted my Ad
    Then My NEXT User Profiler of Category "2010" - Ad Type "s" should be PRO
    Then My NEXT User Profiler of Category "2010" - Ad Type "k" should be PRIVATE
    Then My NEXT User Profiler of Category "2020" - Ad Type "s" should be PRIVATE


  @AUTHOR_VUHOANG_ME_API @DONE
  Scenario: User becomes PRO |After 3 ELT AA
    Given I login my Account with 500k Đồng Tốt
    When I post 1st ELT Phone Ad and Chotot accepted my Ad
    When I post 2nd ELT Phone Ad and Chotot accepted my Ad
    When I post and pay for 3rd ELT Phone Ad and Chotot accepted my Ad
    Then My NEXT User Profiler of Category "5010" - Ad Type "s" should be PRO
    Then My NEXT User Profiler of Category "5010" - Ad Type "k" should be PRIVATE
    Then My NEXT User Profiler of Category "5020" - Ad Type "s" should be PRIVATE








