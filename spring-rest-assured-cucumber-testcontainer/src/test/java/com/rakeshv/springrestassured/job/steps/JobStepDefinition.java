package com.rakeshv.springrestassured.job.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;

import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.equalTo;

public class JobStepDefinition extends SpringBootTestLoader {
    private static final String BASE_URL = "http://localhost:8080";
    private static Response response;
    private String bookId;

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
}
