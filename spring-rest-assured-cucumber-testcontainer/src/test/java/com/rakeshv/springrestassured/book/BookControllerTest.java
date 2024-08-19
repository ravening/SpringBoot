package com.rakeshv.springrestassured.book;

import com.rakeshv.springrestassured.common.config.SecurityConfig;
import com.rakeshv.springrestassured.book.controller.BooksController;
import com.rakeshv.springrestassured.book.model.Book;
import com.rakeshv.springrestassured.book.model.BookRequest;
import com.rakeshv.springrestassured.book.service.BookService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@WebMvcTest(BooksController.class)
@Import(SecurityConfig.class)
public class BookControllerTest {

    @MockBean
    private BookService bookService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    void shouldRetrieveBookWithoutAuthentication() {
        when(bookService.getAllBooks(42))
                .thenReturn(List.of(Book.builder().id(42L).isbn("42").title("Rest assured with spring boot").author("duke").build()));

        RestAssuredMockMvc.given()
                .auth()
                .none()
                .param("amount", "42")
                .when()
                .get("/api/books")
                .then()
                .statusCode(200)
                .body("$.size()", equalTo(1))
                .body("[0].id", equalTo(42))
                .body("[0].title", containsStringIgnoringCase("rest assured with spring boot"))
                .body("[0].author", equalTo("duke"))
                .body("[0].isbn", equalTo("42"))
                .log().all()
        ;
    }

    @Test
    void shouldAllowBookRetrievalWithoutAuthenticationShort() {

        Mockito.when(bookService.getAllBooks(anyInt()))
                .thenReturn(List.of(new Book(42L, "42", "REST Assured With Spring Boot", "Duke")));

        RestAssuredMockMvc.when()
                .get("/api/books")
                .then()
                .statusCode(200)
                .body("$.size()", Matchers.equalTo(1))
                .body("[0].id", Matchers.equalTo(42))
                .body("[0].isbn", Matchers.equalTo("42"))
                .body("[0].author", Matchers.equalTo("Duke"))
                .body("[0].title", Matchers.equalTo("REST Assured With Spring Boot"));
    }

    @Test
    void shouldAllowBookCreationForAuthenticatedAdminUsers() {

        Mockito.when(bookService.createNewBook(any(BookRequest.class))).thenReturn(42L);

        RestAssuredMockMvc.given()
                .auth()
                .with(SecurityMockMvcRequestPostProcessors.user("duke").roles("ADMIN"))
                .contentType("application/json")
                .body(
                        "{\"title\": \"Effective Java\", \"isbn\":\"978-0-13-468599-1 \", \"author\":\"Joshua Bloch\"}")
                .when()
                .post("/api/books")
                .then()
                .statusCode(201)
                .header("Location", Matchers.containsString("/api/books/42"));
    }

    @Test
    @WithMockUser(
            username = "duke",
            roles = {"USER", "EDITOR"})
    void shouldBlockBookCreationForNonAdminUsers() {

        RestAssuredMockMvc.given()
                .contentType("application/json")
                .body(
                        "{\"title\": \"Effective Java\", \"isbn\":\"978-0-13-468599-1 \", \"author\":\"Joshua Bloch\"}")
                .when()
                .post("/api/books")
                .then()
                .statusCode(403);
    }
}
