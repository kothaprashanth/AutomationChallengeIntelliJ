Feature: DBSSampleAutomation

  Background:
    Given User Launch Chrome Browser
    When user Opens URL "https://www.dbs.com.sg/index/default.page"
    And Clicks on LearnMore Button
    And User verifies Sustainability page and clicks on Singapore
    Then user verifies Table is displayed and export it to Excel
    When user clicks on About Button
    And clicks on Who we are link
    And clicks on Our  Awards and Accolades
    Then user should  navigate to Awards page and validate with "A World First"

    @positiveTest
  Scenario: PositiveSampleFlow

    And User should validate no of awards as "22" print the awards and descriptions in the report

  @negativeTest
  Scenario: NegativeSampleFlow

    And User should validate no of awards as "25" print the awards and descriptions in the report
