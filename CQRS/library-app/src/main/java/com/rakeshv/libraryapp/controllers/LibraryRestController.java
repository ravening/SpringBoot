package com.rakeshv.libraryapp.controllers;

import com.rakeshv.libraryapp.aggregates.Library;
import com.rakeshv.libraryapp.commands.RegisterBookCommand;
import com.rakeshv.libraryapp.commands.RegisterLibraryCommand;
import com.rakeshv.libraryapp.entities.BookBean;
import com.rakeshv.libraryapp.entities.LibraryBean;
import com.rakeshv.libraryapp.queries.GetAllBooksQuery;
import com.rakeshv.libraryapp.queries.GetAllLibrariesQuery;
import com.rakeshv.libraryapp.queries.GetBooksQuery;
import com.rakeshv.libraryapp.queries.GetLibraryQuery;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class LibraryRestController {
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @Autowired
    public LibraryRestController(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @PostMapping("/api/library")
    public String addLibrary(@RequestBody LibraryBean library) {
        commandGateway.send(new RegisterLibraryCommand(library.getLibraryId(), library.getName()));
        return "Saved";
    }

    @GetMapping("/api/library/{library}")
    public List<LibraryBean> getLibrary(@PathVariable Integer library) throws InterruptedException, ExecutionException {
//        CompletableFuture<Library> future = queryGateway.query(new GetLibraryQuery(library), Library.class);
//        return future.get();
        return queryGateway.query(new GetLibraryQuery(library), ResponseTypes.multipleInstancesOf(LibraryBean.class)).get();
    }

    @PostMapping("/api/library/{library}/book")
    public String addBook(@PathVariable Integer library, @RequestBody BookBean book) {
        commandGateway.send(new RegisterBookCommand(library, book.getIsbn(), book.getTitle()));
        return "Saved";
    }

    @GetMapping("/api/library/{library}/book")
    public List<BookBean> addBook(@PathVariable Integer library) throws InterruptedException, ExecutionException {
        return queryGateway.query(new GetBooksQuery(library), ResponseTypes.multipleInstancesOf(BookBean.class)).get();
    }

    @GetMapping("/api/library")
    public List<LibraryBean> getLibraries() throws ExecutionException, InterruptedException {
        return queryGateway.query(new GetAllLibrariesQuery(), ResponseTypes.multipleInstancesOf(LibraryBean.class)).get();
    }

    @GetMapping("/api/books")
    public List<BookBean> getBooks() throws ExecutionException, InterruptedException {
        return queryGateway.query(new GetAllBooksQuery(), ResponseTypes.multipleInstancesOf(BookBean.class)).get();
    }
}
