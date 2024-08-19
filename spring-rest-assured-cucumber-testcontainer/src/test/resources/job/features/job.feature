Feature: API related to job postings
Description: Test the api for job postings

  @job
  Scenario: Should get a job by id
    Given search a job by id 4
    Then job title should contain "software engineer"


  @job
  Scenario: List jobs by page number and size
    Given I search for page 2 with size 50
    Then I get job list of size 50 in page 2
