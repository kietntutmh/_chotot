@SERVICE_POS
@CUSTOMER
@TAG_VUHOANG_THUANLY
@PROMOTION_FREEBUMP_CAMPAIGN

Feature: Free Promotion Free Premium - Campaign

  @AUTHOR_THUANLY_ME_API @DONE
  Scenario: Chotot can add a new campaign for free bump
    Given ChoTot add a new campaign free bump with status ACTIVE
    Then ChoTot should be able to add a new campaign free bump with status ACTIVE successfully

  @AUTHOR_THUANLY_ME_API @DONE
  Scenario: Chotot can update status of the campaign
    Given ChoTot add a new campaign free bump with status INACTIVE
    When ChoTot update campaign status to ACTIVE
    Then ChoTot should be able to update the status successfully

  @AUTHOR_THUANLY_ME_API @DONE
  Scenario: ChoTot can edit the time of the campaign is inactive
    Given ChoTot add a new campaign free bump with status INACTIVE
    When ChoTot add 2 days to start time or 2 days to expired time of inactive campaign
    Then ChoTot should be able to edit the time successfully
