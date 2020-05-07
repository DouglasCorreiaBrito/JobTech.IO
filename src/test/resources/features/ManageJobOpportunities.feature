@SignUpFeature
Feature: manage job opportunities

  Scenario: company made a post to /jobs
    When the company made a post to /jobs
    Then the company receives status code 201

  Scenario: company made a get to /jobs
    When the company made a get to /jobs
    Then the company receives status code 200
    And the company receives a list of job opportunities

  Scenario: company made a get to /jobs with id
    When the company made a get to /jobs with id
    Then the company receives status code 200 by get by id
    And the company receives a job opportunity

  Scenario: company made a get to /jobs filtered by title
    When the company made a get to /jobs filtered by title
    Then the company receives status code 200 by get by title
    And the company receives a job opportunity with that title

  Scenario: company made a put to /jobs
    When the company made a put to /jobs
    Then the company receives status code 200 with put

  Scenario: company made a del to /jobs
    When the company made a delete to /jobs
    Then the company receives status code 200 with delete