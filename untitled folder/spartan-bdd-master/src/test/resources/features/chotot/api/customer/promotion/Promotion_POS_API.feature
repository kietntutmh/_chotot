#@SERVICE_USER_ADS
#@TELEGRAM_ME
#@TAG_VUHOANG_SONNGUYEN_THAM
#@PROMOTION

Feature: Promotion POS API - Pay POS > 200k adding 1 free month

  Background:
    Given I login my account to get Promotion


  @AUTHOR_VUHOANG_ME_API
  Scenario: As an User, I get an extend month when I pay Sticky Ad > 200k for my Private Ad

  @AUTHOR_VUHOANG_ME_API
  Scenario: As an User, I get an extend month when I pay Sticky Ad > 200k for my Pro Ad

  @AUTHOR_VUHOANG_ME_API
  Scenario: As an User, I get an extend month when I pay Sticky Ad > 200k for my Shop Ad


  @AUTHOR_VUHOANG_ME_API
  Scenario: As an User, I get an extend month when I pay Sticky Ad > 200k for my Private Ad using Dong Tot 4 Biz

  @AUTHOR_VUHOANG_ME_API
  Scenario: As an User, I get an extend month when I pay Sticky Ad > 200k for my Pro Ad using Dong Tot 4 Biz

  @AUTHOR_VUHOANG_ME_API
  Scenario: As an User, I get an extend month when I pay Sticky Ad > 200k for my Shop Ad using Dong Tot 4 Biz



  #Complex Cases
  @AUTHOR_VUHOANG_ME_API
  Scenario: As an User, I get an extend month when I pay POS > 2000k

  @AUTHOR_VUHOANG_ME_API
  Scenario: As an User, I don't get an extend month when I pay POS <= 200k

  @AUTHOR_VUHOANG_ME_API
  Scenario: As an User, I get an extend month when I pay Sticky Ad > 200k for a same Ad on 2nd time

  @AUTHOR_VUHOANG_ME_API
  Scenario: As an User, I get an extend month for each pay when I pay same Sticky Ad > 200k for a same Ad on many times

  @AUTHOR_VUHOANG_ME_API
  Scenario: As an User, I get an extend month for each Sticky Ad type when I pay some types of Sticky Ad

  @AUTHOR_VUHOANG_ME_API
  Scenario: As an User, I get an extend month for each service when I pay Sticky Ad, Ad Feature and Sticky Ad

  @AUTHOR_VUHOANG_ME_API
  Scenario: As an User, I get an extend month when I pay Sticky Ad > 200k in the last month of the year

  @AUTHOR_VUHOANG_ME_API
  Scenario: As an User, I get an extend month when I pay Sticky Ad > 200k for 2 Ads of a same Category

  @AUTHOR_VUHOANG_ME_API
  Scenario: As an User, I don't get an extend month for every service I bought when I pay some Premium Services with total > 200k at the same time


  #Complex Cases with refund
  @AUTHOR_VUHOANG_ME_API
  Scenario: As an User, I get an extend month for Ad Feature but other services when I pay Bump, Ad Feature and Sticky Ad but only Ad Feature successes

  @AUTHOR_VUHOANG_ME_API
  Scenario: As an User, I don't get an extend month for Ad Feature when I pay Ad Feature unsuccessfully

  @AUTHOR_VUHOANG_ME_API
  Scenario: As an User, I don't get an extend month for Sticky Ad when I pay Sticky Ad unsuccessfully

  @AUTHOR_VUHOANG_ME_API
  Scenario: As an User, I still get an extend month when I pay Sticky Ad unsuccessfully and pay Sticky Ad again

  @AUTHOR_VUHOANG_ME_API
  Scenario: As an User, My expired day of Sticky Ad doesn't change when I pay Sticky Ad unsuccessfully


