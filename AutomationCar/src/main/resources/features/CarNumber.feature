Feature: Checking car Registration NUmber

  @Car
  Scenario: Checking car Registration NUmber

    Given Load Car Input Values From Text File
    When Selenium Main Method execute
    Then Assert OutPut Text File values With capture Values
    And Assert failed  scenario
