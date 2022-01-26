@SERVICE_USERPROFILER
@CUSTOMER
@TAG_VUHOANG
@USER_PROFILER_SHOP @PROFILER
Feature: User Profiler - Shop flow API

    #PTY - Paid for a new Shop - full flows
  @AUTHOR_VUHOANG_ME_API @DONE
  Scenario: User becomes Shop |Paid Shop PTY| v1-internal-profiler-accountID
    Given I login my Account with PTY Shop LF
    Then API v1-internal-profiler-accountID should be SHOP
    Then API v1-internal-profiler-accountId should be NULL with Cate "2000" AdType "s"
    Then API v1-internal-profiler-accountId should be NULL with Cate "5000" AdType "k"

  @AUTHOR_VUHOANG_ME_API @DONE
  Scenario: User becomes Shop |Paid Shop PTY| POST v1-internal-profiler-accountID
    Given I login my Account with PTY Shop LF
    Then API POST v1-internal-profiler-accountID should be SHOP
    Then API POST v1-internal-profiler-accountID should be NULL with Cate "2000" AdType "s"
    Then API POST v1-internal-profiler-accountID should be NULL with Cate "5000" AdType "k"

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: User becomes Shop |Paid Shop PTY| v1-private-profiler
    Given I login my Account with PTY Shop LF
    Then API v1-private-profiler should be SHOP
    Then API v1-private-profiler should be NULL with Cate "2000" AdType "s"
    Then API v1-private-profiler should be NULL with Cate "5000" AdType "k"

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: User becomes Shop |Paid Shop PTY| v1-public-profiler-accountId
    Given I login my Account with PTY Shop LF
    Then API v1-public-profiler-accountId should be SHOP

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: User becomes Shop |Paid Shop PTY| v1-public-profiler-check-profiler-accountID
    Given I login my Account with PTY Shop LF
    Then API v1-public-profiler-check-profiler-accountID should be SHOP

    #ELT - Paid for a new Shop - smoke
  @AUTHOR_VUHOANG_ME_API @DONE
  Scenario: User becomes Shop |Paid Shop VEH| v1-internal-profiler-accountID
    Given I login my Account with VEH Shop LF
    Then API v1-internal-profiler-accountId should be SHOP with Cate "2000" AdType "s"
    Then API v1-internal-profiler-accountId should be SHOP with Cate "2000" AdType "k"
    Then API v1-internal-profiler-accountId should be SHOP with Cate "2010" AdType "s"
    Then API v1-internal-profiler-accountId should be SHOP with Cate "2020" AdType "s"
    Then API v1-internal-profiler-accountId should be SHOP with Cate "2030" AdType "s"
    Then API v1-internal-profiler-accountId should be SHOP with Cate "2050" AdType "s"
    Then API v1-internal-profiler-accountId should be SHOP with Cate "2060" AdType "s"
    Then API v1-internal-profiler-accountId should be SHOP with Cate "2080" AdType "s"
    Then API v1-internal-profiler-accountId should be SHOP with Cate "2090" AdType "s"
    Then API v1-public-profiler-check-profiler-accountID should be SHOP with Cate "2000" AdType "s"


  @AUTHOR_VUHOANG_ME_API @DONE
  Scenario: User becomes Shop |Paid Shop ELT| v1-internal-profiler-accountID
    Given I login my Account with ELT Shop LF
    Then API v1-internal-profiler-accountId should be SHOP with Cate "5000" AdType "s"
    Then API v1-internal-profiler-accountId should be SHOP with Cate "5000" AdType "k"
    Then API v1-internal-profiler-accountId should be SHOP with Cate "5010" AdType "s"
    Then API v1-internal-profiler-accountId should be SHOP with Cate "5020" AdType "s"
    Then API v1-internal-profiler-accountId should be SHOP with Cate "5030" AdType "s"
    Then API v1-internal-profiler-accountId should be SHOP with Cate "5040" AdType "s"
    Then API v1-internal-profiler-accountId should be SHOP with Cate "5050" AdType "s"
    Then API v1-internal-profiler-accountId should be SHOP with Cate "5060" AdType "s"
    Then API v1-internal-profiler-accountId should be SHOP with Cate "5070" AdType "s"
    Then API v1-internal-profiler-accountId should be SHOP with Cate "5080" AdType "s"
    Then API v1-internal-profiler-accountId should be SHOP with Cate "5090" AdType "s"
    Then API v1-public-profiler-check-profiler-accountID should be SHOP with Cate "5000" AdType "s"


  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Profile is null |Create Shop PTY Review| v1-internal-profiler-accountID
    Given I login my Account LF
    When I create "PTY" Shop with my current Account LF but don't pay
    Then API v1-internal-profiler-accountId should be NULL with Cate "1000" AdType "s"
    Then API v1-internal-profiler-accountId should be NULL with Cate "1000" AdType "h"
    Then API v1-internal-profiler-accountId should be NULL with Cate "1000" AdType "k"
    Then API v1-internal-profiler-accountId should be NULL with Cate "1000" AdType "u"
    Then API v1-internal-profiler-accountId should be NULL with Cate "1010" AdType "s"
    Then API v1-internal-profiler-accountId should be NULL with Cate "1020" AdType "s"

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Profile is null |Create Shop VEH Review| v1-internal-profiler-accountID
    Given I login my Account LF
    When I create "VEH" Shop with my current Account LF but don't pay
    Then API v1-internal-profiler-accountId should be NULL with Cate "2000" AdType "s"
    Then API v1-internal-profiler-accountId should be NULL with Cate "2000" AdType "k"

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Profile is null |Create Shop ELT Review| v1-internal-profiler-accountID
    Given I login my Account LF
    When I create "ELT" Shop with my current Account LF but don't pay
    Then API v1-internal-profiler-accountId should be NULL with Cate "5000" AdType "s"
    Then API v1-internal-profiler-accountId should be NULL with Cate "5000" AdType "k"



    #Pay for new Shop but Chotot is reviewing
  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Profile is SHOP |Shop PTY is reviewing| v1-internal-profiler-accountID
    Given I login my Account LF
    When I create "PTY" Shop with my current Account LF but Chotot is reviewing
    Then API v1-internal-profiler-accountId should be SHOP with Cate "1000" AdType "s"
    Then API v1-internal-profiler-accountId should be SHOP with Cate "1000" AdType "h"
    Then API v1-internal-profiler-accountId should be SHOP with Cate "1000" AdType "k"
    Then API v1-internal-profiler-accountId should be SHOP with Cate "1000" AdType "u"
    Then API v1-internal-profiler-accountId should be SHOP with Cate "1010" AdType "s"
    Then API v1-internal-profiler-accountId should be SHOP with Cate "1020" AdType "s"

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Profile is SHOP |Shop PTY is reviewing| v1-internal-profiler-accountID
    Given I login my Account LF
    When I create "VEH" Shop with my current Account LF but Chotot is reviewing
    Then API v1-internal-profiler-accountId should be SHOP with Cate "2000" AdType "s"
    Then API v1-internal-profiler-accountId should be SHOP with Cate "2000" AdType "k"

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Profile is SHOP |Shop PTY is reviewing| v1-internal-profiler-accountID
    Given I login my Account LF
    When I create "ELT" Shop with my current Account LF but Chotot is reviewing
    Then API v1-internal-profiler-accountId should be SHOP with Cate "5000" AdType "s"
    Then API v1-internal-profiler-accountId should be SHOP with Cate "5000" AdType "k"









