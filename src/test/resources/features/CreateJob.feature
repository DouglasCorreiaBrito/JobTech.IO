@SignUpFeature
Feature: create a job opportunity

  Scenario: company made a get to /jobs
    When the company made a post to /jobs
    Then the company receives status code 201