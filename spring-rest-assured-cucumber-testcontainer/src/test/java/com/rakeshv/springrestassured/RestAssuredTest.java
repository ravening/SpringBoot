package com.rakeshv.springrestassured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestAssuredTest {

//    @Test
    void registrationSuccessful() throws JSONException {
        RestAssured.baseURI = "https://bookstore.toolsqa.com";
        RequestSpecification request = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        MyUser myUser = new MyUser("TOOLSQA-TestUser123", "Test@123");
        requestParams.put("userName", "TOOLSQA-Test");
        requestParams.put("password", "Test@@123");

        request.header("Content-Type", "application/json");
        System.out.println(myUser.getString());
        request.body(myUser.getString());
        Response response = request.post("/Account/v1/User");

        System.out.println(response.asString());
        assertEquals(response.getStatusCode(), 201);
    }

    @Test
    void listBooks() {
        RestAssured.baseURI = "https://bookstore.toolsqa.com";
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        request.when()
                .get("/BookStore/v1/Books")
                .then()
                .statusCode(200)
//                .log()
//                .everything(true)
                .body("books.isbn", hasItems("9781449325862"))
                .body("books", hasSize(8))
        ;
    }

    @Test
    void getBookByIsbn() {
        RestAssured.baseURI = "https://bookstore.toolsqa.com";
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request
                .queryParam("ISBN", "9781449325862")
                .log().all()
                .when()
                .get("/BookStore/v1/Book")

                .then()
//               .contentType("application/json")
                .statusCode(200)
                .log().everything(true)
               .body("title", equalTo("Git Pocket Guide"))
               .body("pages", equalTo(234))
               .body("subTitle", containsString("Working"))

                ;
    }
}


record MyUser(String userName, String password) {
    String getString() {
        return """
                {
                    "userName": "%s",
                    "password": "%s"
                }
                """.formatted(userName, password);
    }
}
