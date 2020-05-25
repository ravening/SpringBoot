package com.rakeshv.libraryapp.services;

import com.rakeshv.libraryapp.entities.BookBean;
import com.rakeshv.libraryapp.entities.BookEntity;
import com.rakeshv.libraryapp.events.BookCreatedEvent;
import com.rakeshv.libraryapp.queries.GetAllBooksQuery;
import com.rakeshv.libraryapp.queries.GetBooksQuery;
import com.rakeshv.libraryapp.repositories.BookRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class BookRepositoryService {
    private final BookRepository bookRepository;

    public BookRepositoryService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventHandler
    public void addBook(BookCreatedEvent event) throws Exception {
        BookEntity book = new BookEntity();
        book.setIsbn(event.getIsbn());
        book.setLibraryId(event.getLibraryId());
        book.setTitle(event.getTitle());
        bookRepository.save(book);
    }

    @QueryHandler
    public List<BookBean> getBooks(GetBooksQuery query) {
        return bookRepository.findByLibraryId(query.getLibraryId()).stream().map(toBook()).collect(Collectors.toList());
    }

    @QueryHandler
    public List<BookBean> getAllBooks(GetAllBooksQuery query) {
        return bookRepository.findAll().stream().map(toBook()).collect(Collectors.toList());
    }

    private Function<BookEntity, BookBean> toBook() {
        return e -> {
            BookBean book = new BookBean();
            book.setIsbn(e.getIsbn());
            book.setTitle(e.getTitle());
            return book;
        };
    }
}
