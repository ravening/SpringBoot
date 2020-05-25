package com.rakeshv.libraryapp.aggregates;

import com.rakeshv.libraryapp.commands.RegisterBookCommand;
import com.rakeshv.libraryapp.commands.RegisterLibraryCommand;
import com.rakeshv.libraryapp.events.BookCreatedEvent;
import com.rakeshv.libraryapp.events.LibraryCreatedEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Aggregate
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Library {
    @AggregateIdentifier
    private Integer libraryId;
    private String name;
    private List<String> isbnBooks;

    @CommandHandler
    public Library(RegisterLibraryCommand command) {
        Assert.notNull(command.getLibraryId(), "Library Id should not be null");
        Assert.notNull(command.getName(), "Library name should not be null");

        AggregateLifecycle.apply(new LibraryCreatedEvent(command.getLibraryId(), command.getName()));
    }

    @CommandHandler
    public void addBook(RegisterBookCommand cmd) {
        Assert.notNull(cmd.getLibraryId(), "ID should not be null");
        Assert.notNull(cmd.getIsbn(), "Book ISBN should not be null");

        AggregateLifecycle.apply(new BookCreatedEvent(cmd.getLibraryId(), cmd.getIsbn(), cmd.getTitle()));
    }

    @EventSourcingHandler
    private void handleCreatedEvent(LibraryCreatedEvent event) {
        libraryId = event.getLibraryId();
        name = event.getName();
        isbnBooks = new ArrayList<>();
    }

    @EventSourcingHandler
    private void addBook(BookCreatedEvent event) {
        isbnBooks.add(event.getIsbn());
    }
}
