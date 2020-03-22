@SignUpFeature
Feature: apply for a job opportunity

  Scenario: candidate made a post to /job-application
    When the candidate made a post to /job-application
    Then the candidate receives status code 201

  Scenario: candidate made a get to /job-application
    When the candidate made a get to /job-application
    Then the candidate receives status code 200
    And the candidate receives a list of job applications

  Scenario: candidate made a get to /job-application with id
    When the candidate made a get to /job-application with id
    Then the candidate receives status code 200 by get by id
    And the candidate receives a job application


  Scenario: candidate made a put to /job-application
   When the candidate made a put to /job-application
    Then the candidate receives status code 200 with put

  Scenario: candidate made a del to /jobs
   When the candidate made a delete to /job-application
   Then the candidate receives status code 200 with delete