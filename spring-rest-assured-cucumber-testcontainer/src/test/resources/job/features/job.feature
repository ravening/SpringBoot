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

  @job
  Scenario: Creating a new job post
    When I add a new job post
    Then I get back the newly added job successfully

  @job
  Scenario: Updating the job posting
    Given A new job description to update
    When I try to update the job with id 3
    Then The job with id 3 is updated successfully
    
  @job
  Scenario: I should be able to delete a job successfully
    When I try to delete the job listing by id 50
    Then The job with id 50 is deleted successfully
