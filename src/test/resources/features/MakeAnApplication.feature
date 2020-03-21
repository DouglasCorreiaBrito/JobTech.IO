@SignUpFeature
Feature: apply for a job opportunity

  Scenario: candidate made a post to /job-application
    When the candidate made a post to /job-application
    Then the candidate receives status code 201