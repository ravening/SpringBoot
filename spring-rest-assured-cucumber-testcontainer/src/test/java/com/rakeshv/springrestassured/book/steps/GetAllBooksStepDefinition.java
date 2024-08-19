package com.rakeshv.springrestassured.book.steps;

import com.rakeshv.springrestassured.book.model.Book;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GetAllBooksStepDefinition {
    private static final String BASE_URL = "https://bookstore.toolsqa.com";
    private static Response response;
    private String bookId;

    @Given("^A list of books are available$")
    public void aListOfBooksAreAvailable() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification httpRequest = RestAssured.given();

        response = httpRequest.get("/BookStore/v1/Books")
                .then()
                .statusCode(200)
                .extract().response();
        ;
        String string = response.asString();
        List<Book> books = JsonPath.from(string).getList("books", Book.class);
        assertTrue(!books.isEmpty());
        System.out.println(books.get(0));
        bookId = books.get(0).getIsbn();
    }

    @Given("^I retrieve the book by isbn and it is found$")
    public void iRetrieveTheBookByIsbnAndItIsFound() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification httpRequest = RestAssured.given();

        response = httpRequest
                .queryParam("ISBN", bookId)
                .get("/BookStore/v1/Book");

        String string = response.asString();
        System.out.println(string);
    }
}
