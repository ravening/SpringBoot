package com.rakeshv.springrestassured.job.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;

import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesRegex;
import static org.hamcrest.Matchers.notNullValue;

public class JobStepDefinition  { //extends SpringBootTestLoader {
    private static final String BASE_URL = "http://localhost:8080";
    private static Response response;
    RequestSpecification httpRequest;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = BASE_URL;
    }
    @Given("search a job by id {int}")
    public void searchJobById(int id) {
        RequestSpecification httpRequest = RestAssured.given();

        response = httpRequest
                .pathParam("jobId", id)
                .contentType("application/json")
                .auth()
                .basic("user", "password")
                .when()
                .get("/api/v1/jobs/{jobId}")
                .then()
                .statusCode(200)
                .extract().response()
        ;

    }

    @Then("job title should contain {string}")
    public void jobTitleShouldContain(String jobTitle) {

        response
                .then()
                .body("id", equalTo(4))
                .body("jobTitle", containsStringIgnoringCase(jobTitle))
        ;
    }

    @Given("I search for page {int} with size {int}")
    public void iSearchForPageWithSize(int pageNumber, int size) {
        RequestSpecification httpRequest = RestAssured.given();

        response = httpRequest
                .auth()
                .basic("user", "password")
                .queryParam("page", pageNumber)
                .queryParam("size", size)
                .when()
                .get("/api/v1/jobs")
                .then()
                .extract().response();
    }

    @Then("I get job list of size {int} in page {int}")
    public void iGetJobListOfSize(int size, int pageNumber) {

        response
                .then()
                .statusCode(200)
                .body("totalElements", equalTo(1000))
                .body("pageNumber", equalTo(pageNumber))
                .body("totalPages", equalTo(20))
                .body("isFirst", equalTo(false))
                .body("isLast", equalTo(false))
                .body("hasNext", equalTo(true))
                .body("hasPrevious", equalTo(true))
                .body("data.size()", equalTo(size));
        ;
    }

    @When("I add a new job post")
    public void iAddANewJobPost() {
        RequestSpecification httpRequest = RestAssured.given();
        response = httpRequest.contentType("application/json")
                .body("""
                          {
                            "jobTitle": "Senior Backend Engineer (Java)",
                            "description": "My Company is looking for an experienced Java Backend developer.",
                            "jobType": "FULL_TIME",
                            "datePosted": "2024-03-24",
                            "jobLink": "https://mycompany.breezy.hr/p/22b17727d5f4-senior-backend-engineer-java"
                          }
                        """
                )
                .auth()
                .basic("user", "password")
                .when()
                .post("/api/v1/jobs")
                .then()
                .extract().response()
                ;
    }

    @Then("I get back the newly added job successfully")
    public void iGetBackTheNewlyAddedJobSuccessfully() {
        response
                .then()
                .statusCode(201)
                .header("Location", matchesRegex(".*/api/v1/jobs/[0-9]+$"))
                .body("id", notNullValue())
                .body("jobTitle", equalTo("Senior Backend Engineer (Java)"))
                .body("jobLink", equalTo("https://mycompany.breezy.hr/p/22b17727d5f4-senior-backend-engineer-java"))
                ;
    }

    @Given("A new job description to update")
    public void iAmTryingToUpdateNewJobWithId() {
        httpRequest = RestAssured.given();
        httpRequest.contentType("application/json")
                .body("""
                            {
                              "jobTitle": "AI Engineer",
                              "description": "XYZ inc. Is looking for an experienced Electrical Engineer.",
                              "jobType": "CONTRACTOR",
                              "datePosted": "2023-09-18",
                              "jobLink": "https://xyz.bamboohr.hr/p/22b177hg675-electrical-engineer"
                            }
                          """
                )
                ;
    }

    @When("I try to update the job with id {int}")
    public void iTryToUpdateJobWithId(int id) {
        response = httpRequest
                .when()
                .pathParam("jobId", id)
                .put("/api/v1/jobs/{jobId}")
                ;
    }

    @Then("The job with id {int} is updated successfully")
    public void theJobWithIdIsUpdatedSuccessfully(int id) {
        response
                .then()
                .statusCode(200)
                .body("id", equalTo(id))
                .body("jobTitle", containsStringIgnoringCase("ai engineer"))
                ;

    }

    @When("I try to delete the job listing by id {int}")
    public void iTryToDeleteJobListingById(int id) {
        httpRequest = RestAssured.given();
        httpRequest.contentType("application/json")
                .when()
                .delete("/api/v1/jobs/{jobId}", id)
                ;
    }

    @Then("The job with id {int} is deleted successfully")
    public void theJobWithIdIsDeletedSuccessfully(int id) {
        httpRequest.then()
                .statusCode(204)

        ;
    }
}
