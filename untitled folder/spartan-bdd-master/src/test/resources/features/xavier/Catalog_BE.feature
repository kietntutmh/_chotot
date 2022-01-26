Feature: Catalog-BE
#https://701search.atlassian.net/browse/G1-1713

  Scenario: Check static data on Xavier FE when a private gitlab repo has component.yml file
    Given I had a private gitlab repo on Chotot
    When I push/ approve MR (on master branch) that contains component.yml file
    Then I should see static data displayed on Xavier FE

  Scenario: Check static data on Xavier FE when a private gitlab repo has no component.yml file
    Given I had a private gitlab repo on Chotot that has no component.yml file
    When I push/ approve MR (on master branch) that not includes component.yml file
    Then I should see static data displayed on Xavier FE

  Scenario: Check static data on Xavier FE when a private gitlab repo updates component.yml file
    Given I had a private gitlab repo on Chotot
    When I push/ approve MR (on master branch) to update the existing component.yml file
    Then I should see static data displayed on Xavier FE

  Scenario: Check static data on Xavier FE when a private gitlab repo deletes the existing component.yml file
    Given I had a private gitlab repo on Chotot that has component.yml file
    When I push/ approve MR (on master branch) to delete the existing component.yml file
    Then I should see static data still display on Xavier FE and should be alerted (?)

  Scenario: Check static data on Xavier FE when a private gitlab repo has component.yaml file that missing info
    Given I had a private gitlab repo on Chotot
    When I push/ approve MR (on master branch) that contains component.yaml file missing info
    Then I should see static data are missed show "N/A" on Xavier FE

  Scenario: Check static data on Xavier FE when a private gitlab repo has component.yaml file contains special data
    Given I had a private gitlab repo on Chotot
    When I push/ approve MR (on master branch) that contains component.yaml file with special data (description, groups)
    Then I should see static data displayed on Xavier FE

  Scenario: Check static data on Xavier FE when a public gitlab repo has component.yaml file
    Given I had a public gitlab repo on Chotot
    When I push/ approve MR (on master branch) that contains component.yaml file
    Then I should see static data displayed on Xavier FE

  Scenario: Check static data on Xavier FE when a public gitlab repo has no component.yaml file
    Given I had a public gitlab repo on Chotot that has no component.yaml file
    When I push/ approve MR (on master branch) that not includes component.yaml file
    Then I should not see static data displayed on Xavier FE

  Scenario: Check static data on Xavier FE when a public gitlab repo updates component.yaml file
    Given I had a public gitlab repo on Chotot
    When I push/ approve MR (on master branch) to update the existing component.yaml file
    Then I should see static data displayed on Xavier FE

  Scenario: Check static data on Xavier FE when a public gitlab repo deletes the existing component.yaml file
    Given I had a public gitlab repo on Chotot that has component.yaml file
    When I push/ approve MR (on master branch) to delete the existing component.yaml file
    Then I should see static data still display on Xavier FE and should be alerted (?)

  Scenario: Check static data on Xavier FE when a public gitlab repo has component.yaml file that missing info
    Given I had a public gitlab repo on Chotot
    When I push/ approve MR (on master branch) that contains component.yaml file missing info
    Then I should see static data are missed show "N/A" on Xavier FE

  Scenario: Check static data on Xavier FE when a public gitlab repo has component.yaml file contains special data
    Given I had a public gitlab repo on Chotot
    When I push/ approve MR (on master branch) that contains component.yaml file with special data  (description, groups)
    Then I should see static data displayed on Xavier FE
