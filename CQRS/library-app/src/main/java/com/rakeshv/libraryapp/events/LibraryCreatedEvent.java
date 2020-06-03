package com.rakeshv.libraryapp.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LibraryCreatedEvent {
    @TargetAggregateIdentifier
    private Integer libraryId;
    private String name;
}
