package com.rakeshv.springrestassured.book.controller;

import com.rakeshv.springrestassured.book.model.Book;
import com.rakeshv.springrestassured.book.model.BookRequest;
import com.rakeshv.springrestassured.book.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BooksController {

    private final BookService bookService;

    @GetMapping
    public List<Book> getAllBooks(@RequestParam(value = "amount", defaultValue = "500") int amount) {
        return bookService.getAllBooks(amount);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PostMapping
    public ResponseEntity<Void> createNewBook(
            @Valid @RequestBody BookRequest bookRequest, UriComponentsBuilder uriComponentsBuilder) {

        Long bookId = bookService.createNewBook(bookRequest);

        UriComponents uriComponents =
                uriComponentsBuilder.path("/api/books/{id}").buildAndExpand(bookId);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

}
