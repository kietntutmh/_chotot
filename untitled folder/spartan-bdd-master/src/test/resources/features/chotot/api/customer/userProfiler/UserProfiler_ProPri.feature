@SERVICE_USERPROFILER
@CUSTOMER
@TAG_VUHOANG_SONNGUYEN
@USER_PROFILER_PRO  @PROFILER
Feature: User Profiler - Multiple APIs

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: User becomes Pro | v1-internal-profiler-accountId
    Given I login my Account LF
    When I post a PTY House Pro Ad and Chotot accepted my Ad
    Then API v1-internal-profiler-accountId should be PRO with Cate "1020" AdType "s"
    Then API v1-internal-profiler-accountId should be PRIVATE with Cate "1020" AdType "u"
    Then API v1-internal-profiler-accountId should be PRIVATE with Cate "1020" AdType "k"
    Then API v1-internal-profiler-accountId should be PRIVATE with Cate "1020" AdType "h"
    Then API v1-internal-profiler-accountId should be PRIVATE with Cate "1010" AdType "s"


  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: User becomes Pro | POST v1-internal-profiler-accountId
    Given I login my Account LF
    When I post a PTY House Pro Ad and Chotot accepted my Ad
    Then API POST v1-internal-profiler-accountID should be PRO with Cate "1020" AdType "s"
    Then API POST v1-internal-profiler-accountID should be PRIVATE with Cate "1020" AdType "u"
    Then API POST v1-internal-profiler-accountID should be PRIVATE with Cate "1020" AdType "k"
    Then API POST v1-internal-profiler-accountID should be PRIVATE with Cate "1020" AdType "h"
    Then API POST v1-internal-profiler-accountID should be PRIVATE with Cate "1010" AdType "s"


  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: User becomes Pro | v1-private-profiler
    Given I login my Account LF
    When I post a PTY House Pro Ad and Chotot accepted my Ad
    Then API v1-private-profiler should be PRO with Cate "1020" AdType "s"
    Then API v1-private-profiler should be PRIVATE with Cate "1020" AdType "u"
    Then API v1-private-profiler should be PRIVATE with Cate "1020" AdType "k"
    Then API v1-private-profiler should be PRIVATE with Cate "1020" AdType "h"
    Then API v1-private-profiler should be PRIVATE with Cate "1010" AdType "s"


  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: User becomes Pro | v1-public-profiler-check-profiler-accountID
    Given I login my Account LF
    When I post a PTY House Pro Ad and Chotot accepted my Ad
    Then API v1-public-profiler-check-profiler-accountID should be PRO with Cate "1020" AdType "s"
    Then API v1-public-profiler-check-profiler-accountID should be PRIVATE with Cate "1020" AdType "u"
    Then API v1-public-profiler-check-profiler-accountID should be PRIVATE with Cate "1020" AdType "k"
    Then API v1-public-profiler-check-profiler-accountID should be PRIVATE with Cate "1020" AdType "h"
    Then API v1-public-profiler-check-profiler-accountID should be PRIVATE with Cate "1010" AdType "s"


  @AUTHOR_VUHOANG_ME_API  @DONE  @DONE
  Scenario: User becomes Pro | v1-public-profiler-accountId
    Given I login my Account LF
    When I post a PTY House Pro Ad and Chotot accepted my Ad
    Then API v1-public-profiler-accountId should be PRO with Cate "1020" AdType "s"
    Then API v1-public-profiler-accountId should be PRIVATE with Cate "1020" AdType "u"
    Then API v1-public-profiler-accountId should be PRIVATE with Cate "1020" AdType "k"
    Then API v1-public-profiler-accountId should be PRIVATE with Cate "1020" AdType "h"
    Then API v1-public-profiler-accountId should be PRIVATE with Cate "1010" AdType "s"


  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Profile is null |New User| v1-internal-profiler-accountID
    Given I login my Account LF
    Then API v1-internal-profiler-accountId should be NULL with Cate "1000" AdType "s"
    Then API v1-internal-profiler-accountId should be NULL with Cate "1000" AdType "h"
    Then API v1-internal-profiler-accountId should be NULL with Cate "1000" AdType "k"
    Then API v1-internal-profiler-accountId should be NULL with Cate "1000" AdType "u"
    Then API v1-internal-profiler-accountId should be NULL with Cate "1010" AdType "s"
    Then API v1-internal-profiler-accountId should be NULL with Cate "1020" AdType "s"

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Profile is null |New User| v1-internal-profiler-accountID
    Given I login my Account LF
    Then API v1-internal-profiler-accountId should be NULL with Cate "2000" AdType "s"
    Then API v1-internal-profiler-accountId should be NULL with Cate "2000" AdType "k"

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Profile is null |New User| v1-internal-profiler-accountID
    Given I login my Account LF
    Then API v1-internal-profiler-accountId should be NULL with Cate "5000" AdType "s"
    Then API v1-internal-profiler-accountId should be NULL with Cate "5000" AdType "k"
