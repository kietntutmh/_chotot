@SERVICE_USERPROFILER
@CUSTOMER
@TAG_VUHOANG_SONNGUYEN
@USER_PROFILER_SHOP_EE  @PROFILER
Feature: User Profiler - Shop Expire, Extend API


#Shop EXPIRED
  @AUTHOR_VUHOANG_ME_API @DONE
  Scenario: User becomes Pro |Shop PTY is expired
    Given I login my Account with PTY Shop LF
    When My PTY Shop is expired
    Then API v1-internal-profiler-accountId should be PRO with Cate "1000" AdType "s"
    Then API v1-internal-profiler-accountId should be PRO with Cate "1000" AdType "h"
    Then API v1-internal-profiler-accountId should be PRO with Cate "1000" AdType "k"
    Then API v1-internal-profiler-accountId should be PRO with Cate "1000" AdType "u"
    Then API v1-internal-profiler-accountId should be PRO with Cate "1050" AdType "u"
    Then API POST v1-internal-profiler-accountID should be PRO with Cate "1010" AdType "s"
    Then API POST v1-internal-profiler-accountID should be PRO with Cate "1010" AdType "u"
    Then API v1-private-profiler should be PRO with Cate "1020" AdType "s"
    Then API v1-private-profiler should be PRO with Cate "1020" AdType "u"
    Then API v1-public-profiler-accountId should be PRO with Cate "1030" AdType "s"
    Then API v1-public-profiler-accountId should be PRO with Cate "1030" AdType "u"
    Then API v1-public-profiler-check-profiler-accountID should be PRO with Cate "1040" AdType "s"
    Then API v1-public-profiler-check-profiler-accountID should be PRO with Cate "1040" AdType "u"


  @AUTHOR_VUHOANG_ME_API @DONE
  Scenario: User becomes Pro |Shop VEH is expired
    Given I login my account with VEH Shop
    When My VEH Shop is expired
    Then API v1-internal-profiler-accountId should be PRO with Cate "2000" AdType "s"
    Then API v1-internal-profiler-accountId should be PRO with Cate "2090" AdType "k"
    Then API POST v1-internal-profiler-accountID should be PRO with Cate "2010" AdType "k"
    Then API v1-private-profiler should be PRO with Cate "2020" AdType "k"
    Then API v1-public-profiler-accountId should be PRO with Cate "2030" AdType "s"
    Then API v1-public-profiler-check-profiler-accountID should be PRO with Cate "2050" AdType "k"
    Then API v1-public-profiler-check-profiler-accountID should be PRO with Cate "2080" AdType "s"


  @AUTHOR_VUHOANG_ME_API @DONE
  Scenario: User becomes Pro |Shop ELT is expired
    Given I login my account with ELT Shop
    When My ELT Shop is expired
    Then API v1-internal-profiler-accountId should be PRO with Cate "5000" AdType "s"
    Then API v1-internal-profiler-accountId should be PRO with Cate "5010" AdType "k"
    Then API POST v1-internal-profiler-accountID should be PRO with Cate "5020" AdType "k"
    Then API v1-private-profiler should be PRO with Cate "5030" AdType "k"
    Then API v1-public-profiler-accountId should be PRO with Cate "5050" AdType "s"
    Then API v1-public-profiler-check-profiler-accountID should be PRO with Cate "5040" AdType "k"
    Then API v1-public-profiler-check-profiler-accountID should be PRO with Cate "5060" AdType "s"


    #Shop EXTENDED
  @AUTHOR_VUHOANG_ME_API @DONE
  Scenario: User becomes Shop |Shop PTY is extended
    Given I login my Account with PTY Shop LF
    When My PTY Shop is expired
    When My PTY Shop is extended
    Then API v1-internal-profiler-accountId should be SHOP with Cate "1000" AdType "s"
    Then API v1-internal-profiler-accountId should be SHOP with Cate "1000" AdType "h"
    Then API v1-internal-profiler-accountId should be SHOP with Cate "1000" AdType "k"
    Then API v1-internal-profiler-accountId should be SHOP with Cate "1000" AdType "u"
    Then API v1-internal-profiler-accountId should be SHOP with Cate "1050" AdType "u"
    Then API POST v1-internal-profiler-accountID should be SHOP with Cate "1010" AdType "s"
    Then API POST v1-internal-profiler-accountID should be SHOP with Cate "1010" AdType "u"
    Then API v1-private-profiler should be SHOP with Cate "1020" AdType "s"
    Then API v1-private-profiler should be SHOP with Cate "1020" AdType "u"
    Then API v1-public-profiler-accountId should be SHOP with Cate "1030" AdType "s"
    Then API v1-public-profiler-accountId should be SHOP with Cate "1030" AdType "u"
    Then API v1-public-profiler-check-profiler-accountID should be SHOP with Cate "1040" AdType "s"
    Then API v1-public-profiler-check-profiler-accountID should be SHOP with Cate "1040" AdType "u"


  @AUTHOR_VUHOANG_ME_API @DONE
  Scenario: User becomes Shop |Shop VEH is extended
    Given I login my account with VEH Shop
    When My VEH Shop is expired
    When My VEH Shop is extended
    Then API v1-internal-profiler-accountId should be SHOP with Cate "2000" AdType "s"
    Then API v1-internal-profiler-accountId should be SHOP with Cate "2090" AdType "k"
    Then API POST v1-internal-profiler-accountID should be SHOP with Cate "2010" AdType "k"
    Then API v1-private-profiler should be SHOP with Cate "2020" AdType "k"
    Then API v1-public-profiler-accountId should be SHOP with Cate "2030" AdType "s"
    Then API v1-public-profiler-check-profiler-accountID should be SHOP with Cate "2050" AdType "k"
    Then API v1-public-profiler-check-profiler-accountID should be SHOP with Cate "2080" AdType "s"


  @AUTHOR_VUHOANG_ME_API @DONE
  Scenario: User becomes Shop |Shop ELT is extended
    Given I login my account with ELT Shop
    When My ELT Shop is expired
    When My ELT Shop is extended
    Then API v1-internal-profiler-accountId should be SHOP with Cate "5000" AdType "s"
    Then API v1-internal-profiler-accountId should be SHOP with Cate "5010" AdType "k"
    Then API POST v1-internal-profiler-accountID should be SHOP with Cate "5020" AdType "k"
    Then API v1-private-profiler should be SHOP with Cate "5030" AdType "k"
    Then API v1-public-profiler-accountId should be SHOP with Cate "5050" AdType "s"
    Then API v1-public-profiler-check-profiler-accountID should be SHOP with Cate "5040" AdType "k"
    Then API v1-public-profiler-check-profiler-accountID should be SHOP with Cate "5060" AdType "s"









