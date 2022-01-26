Feature: Available Common Steps of Chotot

  @AUTHOR_VUHOANG_GAMMA_UI
  Scenario: Private Dashboard
    Then I should see my New Ad displays on Private Dashboard

    Scenario: Setup sheet name for Google Sheet Report, not to use auto-detect sheetname
      Given Setup sheet name "" for Google Sheet Report
