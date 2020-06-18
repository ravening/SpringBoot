package com.rakeshv.libraryapp.events;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class BookCreatedEvent {
    @TargetAggregateIdentifier
    private final Integer libraryId;
    private final String isbn;
    private final String title;
}
