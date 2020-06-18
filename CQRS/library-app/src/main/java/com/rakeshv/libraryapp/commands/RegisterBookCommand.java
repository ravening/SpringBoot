package com.rakeshv.libraryapp.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterBookCommand {
    @TargetAggregateIdentifier
    private Integer libraryId;
    private String isbn;
    private String title;
}
