#@SERVICE_PRICER
#@TELEGRAM_ME
#@TAG_VUHOANG_HUETAN_SON
#Feature: Check New Pricer JS with Old Pricer API
#
#  Background:
#    Given Set Pricer Service New JS to "/alchemist"
#
#  @AUTHOR_VUHOANG_ME_API  @DONE
#  Scenario: Pricer JS Bump, Check API /pricing/get-all
#    Given Set Pricer URI Gateway Private V1
#    Then Check API Pricer New JS with URL "/pricing/get-all"
#
#  @AUTHOR_VUHOANG_ME_API  @DONE
#  Scenario: Pricer JS Ad Feature, Check API /get-all
#    Given Set Pricer URI Gateway Public V1
#    Then Check API Pricer New JS with URL "/get-all"
#
#  @AUTHOR_VUHOANG_ME_API  @DONE
#  Scenario: Pricer JS Ad Feature, Check API /ad-features
#    Given Set Pricer URI Gateway Internal V1
#    Then Check API Pricer New JS with URL "/ad-features?category_id=1010&feature=title&duration=7"
#
#  @AUTHOR_VUHOANG_ME_API  @DONE
#  Scenario: Pricer JS Ad Feature, Check API /ad-features
#    Given Set Pricer URI Gateway Internal V1
#    Then Check API Pricer New JS with URL "/ad-features?category_id=1010&feature=price&duration=7"
#
#  @AUTHOR_VUHOANG_ME_API  @DONE
#  Scenario: Pricer JS Ad Feature, Check API /ad-features
#    Given Set Pricer URI Gateway Internal V1
#    Then Check API Pricer New JS with URL "/ad-features?category_id=1010&feature=ribbon&duration=7"
#
#  @AUTHOR_VUHOANG_ME_API  @DONE
#  Scenario: Pricer JS Shop Public, Check API /shops/get-all
#    Given Set Pricer URI Gateway Public V1
#    Then Check API Pricer New JS with URL "/shops/get-all"
#
#  @AUTHOR_VUHOANG_ME_API  @DONE
#  Scenario: Pricer JS Shop Private, Check API /shops/get-all
#    Given Set Pricer URI Gateway Private V1
#    Then Check API Pricer New JS with URL "/shops/get-all"
#
#  @AUTHOR_VUHOANG_ME_API  @DONE
#  Scenario: Pricer JS Shop Create, Check API /shops/get-package
#    Given Set Pricer URI Gateway Internal V1
#    Then Check API Pricer New JS with accountID and URL "/shops/get-package?account_id=%s&category_id=1000&action_type=shop_create&duration=1"
#
#  @AUTHOR_VUHOANG_ME_API  @DONE
#  Scenario: Pricer JS Shop Extend, Check API /shops/get-package
#    Given Set Pricer URI Gateway Internal V1
#    Then Check API Pricer New JS with accountID and URL "/shops/get-package?account_id=%s&category_id=1000&action_type=shop_extend&duration=1"
#
#  @AUTHOR_VUHOANG_ME_API  @DONE
#  Scenario: Pricer JS Sticky Ad, Check API /sticky-ads
#    Given Set Pricer URI Gateway Internal V1
#    Then Check API Pricer New JS with URL "/sticky-ads?region_id=13000&category_id=1010&duration=7&stickyads=1"
#
#  @AUTHOR_VUHOANG_ME_API  @DONE
#  Scenario: Pricer Listing Fee, Check API /get-all/listing-fee
#    Given Set Pricer URI Gateway Internal V1
#    Then Check API Pricer New JS with URL "/get-all/listing-fee"
#
#  @AUTHOR_VUHOANG_ME_API  @DONE
#  Scenario: Pricer Listing Fee, Check API /get-all/listing-fee-pty
#    Given Set Pricer URI Gateway Internal V1
#    Then Check API Pricer New JS with URL "/get-all/listing-fee-pty"
#
#
#
#
#  @AUTHOR_VUHOANG_ME_API @DONE
#  Scenario: Pricer Goldpot Sell Internal, Check API /get-all/listing-fee-pty
#    Given Set Pricer URI Gateway Internal V1
#    Then Check API Pricer New JS with Account ID and URL "/goldpot?account_id=%s&action_type=insert_ad&region_id=13000&category_id=1010&ad_type=s"
#
#  @AUTHOR_VUHOANG_ME_API @DONE
#  Scenario: Pricer Goldpot Sell Private, Check API /get-all/listing-fee-pty
#    Given Set Pricer URI Gateway Private V1
#    Then Check API Pricer New JS with Account ID and URL "/goldpot?account_id=%s&action_type=insert_ad&region_id=13000&category_id=1010&ad_type=s"
#
#  @AUTHOR_VUHOANG_ME_API  @DONE
#  Scenario: Pricer Goldpot Rent Internal, Check API /get-all/listing-fee-pty
#    Given Set Pricer URI Gateway Internal V1
#    Then Check API Pricer New JS with Account ID and URL "/goldpot?account_id=%s&action_type=insert_ad&region_id=13000&category_id=1010&ad_type=u"
#
#  @AUTHOR_VUHOANG_ME_API  @DONE
#  Scenario: Pricer Goldpot Rent Private, Check API /goldpot
#    Given Set Pricer URI Gateway Private V1
#    Then Check API Pricer New JS with Account ID and URL "/goldpot?account_id=%s&action_type=insert_ad&region_id=13000&category_id=1010&ad_type=u"
#
#
#
#
#  @AUTHOR_VUHOANG_ME_API  @DONE
#  Scenario: Pricer Goldpot Shop Internal, Check API /goldpot
#    Given Set Pricer URI Gateway Internal V1
#    Then Check API Pricer New JS with Account ID and URL "/goldpot?account_id=%s&action_type=shop_to_chotot&region_id=13000&category_id=1010&ad_type=s"
#
#  @AUTHOR_VUHOANG_ME_API  @DONE
#  Scenario: Pricer Goldpot Shop Private, Check API /goldpot
#    Given Set Pricer URI Gateway Private V1
#    Then Check API Pricer New JS with Account ID and URL "/goldpot?account_id=%s&action_type=shop_to_chotot&region_id=13000&category_id=1010&ad_type=s"
#
#  @AUTHOR_VUHOANG_ME_API  @DONE
#  Scenario: Pricer Rent Shop Internal, Check API /goldpot
#    Given Set Pricer URI Gateway Internal V1
#    Then Check API Pricer New JS with Account ID and URL "/goldpot?account_id=%s&action_type=shop_to_chotot&region_id=13000&category_id=1010&ad_type=u"
#
#  @AUTHOR_VUHOANG_ME_API  @DONE
#  Scenario: Pricer Rent Shop Private, Check API /goldpot
#    Given Set Pricer URI Gateway Private V1
#    Then Check API Pricer New JS with Account ID and URL "/goldpot?account_id=%s&action_type=shop_to_chotot&region_id=13000&category_id=1010&ad_type=u"
#
#
##Wrong Response JSON:
#
#
#  @AUTHOR_VUHOANG_ME_API  @FAILED
#  Scenario: Pricer Sell Private, Check API /pricer
#    Given Set Pricer URI Gateway Private V1
#    Then Check API Pricer New JS with Account ID and URL "?account_id=%s&action_type=insert_ad&region_id=13000&category_id=1010&ad_type=s"
#
#  @AUTHOR_VUHOANG_ME_API  @FAILED
#  Scenario: Pricer Sell Internal, Check API /pricer
#    Given Set Pricer URI Gateway Internal V1
#    Then Check API Pricer New JS with Account ID and URL "?account_id=%s&action_type=insert_ad&region_id=13000&category_id=1010&ad_type=s"
#
#  @AUTHOR_VUHOANG_ME_API  @DONE
#  Scenario: Pricer Rent Private, Check API /pricer
#    Given Set Pricer URI Gateway Private V1
#    Then Check API Pricer New JS with Account ID and URL "?account_id=%s&action_type=insert_ad&region_id=13000&category_id=1010&ad_type=u"
#
#  @AUTHOR_VUHOANG_ME_API  @DONE
#  Scenario: Pricer Rent Internal, Check API /pricer
#    Given Set Pricer URI Gateway Internal V1
#    Then Check API Pricer New JS with Account ID and URL "?account_id=%s&action_type=insert_ad&region_id=13000&category_id=1010&ad_type=u"
#
#
#
#
#  @AUTHOR_VUHOANG_ME_API  @DONE
#  Scenario: Pricer Shop Private, Check API /pricer
#    Given Set Pricer URI Gateway Private V1
#    Then Check API Pricer New JS with Account ID and URL "?account_id=%s&action_type=shop_to_chotot&region_id=13000&category_id=1010&ad_type=s"
#
#  @AUTHOR_VUHOANG_ME_API  @DONE
#  Scenario: Pricer Shop Internal, Check API /pricer
#    Given Set Pricer URI Gateway Internal V1
#    Then Check API Pricer New JS with Account ID and URL "?account_id=%s&action_type=shop_to_chotot&region_id=13000&category_id=1010&ad_type=s"
#
#  @AUTHOR_VUHOANG_ME_API  @DONE
#  Scenario: Pricer Shop Rent Private, Check API /pricer
#    Given Set Pricer URI Gateway Private V1
#    Then Check API Pricer New JS with Account ID and URL "?account_id=%s&action_type=shop_to_chotot&region_id=13000&category_id=1010&ad_type=u"
#
#  @AUTHOR_VUHOANG_ME_API  @DONE
#  Scenario: Pricer Shop Rent Internal, Check API /pricer
#    Given Set Pricer URI Gateway Internal V1
#    Then Check API Pricer New JS with Account ID and URL "?account_id=%s&action_type=shop_to_chotot&region_id=13000&category_id=1010&ad_type=u"
#
#
#
#  @AUTHOR_VUHOANG_ME_API  @FAILED
#  Scenario: Pricer Service Sell Private, Check API /services
#    Given Set Pricer URI Gateway Private V2
#    Then Check API Pricer New JS with URL "/services?region_id=13000&category_id=1010&ad_type=s"
#
#  @AUTHOR_VUHOANG_ME_API  @FAILED
#  Scenario: Pricer Service Rent Private, Check API /services
#    Given Set Pricer URI Gateway Private V2
#    Then Check API Pricer New JS with URL "/services?region_id=13000&category_id=1010&ad_type=u"
#
#
#
#  @AUTHOR_VUHOANG_ME_API  @FAILED
#  Scenario: Pricer Service Sell User, Check API /services
#    Given Set Pricer URI Gateway Private V2
#    Then Check API Pricer New JS with Account ID and URL "/services?account_id=%s&region_id=13000&category_id=1010&ad_type=u"
#
#  @AUTHOR_VUHOANG_ME_API  @FAILED
#  Scenario: Pricer Service Rent User, Check API /services
#    Given Set Pricer URI Gateway Private V2
#    Then Check API Pricer New JS with Account ID and URL "/services?account_id=%s&region_id=13000&category_id=1010&ad_type=s"
#
#
#
#
#
#
#
#
#
#
#
#
#
