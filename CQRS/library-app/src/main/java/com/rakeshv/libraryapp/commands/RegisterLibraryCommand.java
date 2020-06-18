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
public class RegisterLibraryCommand {
    @TargetAggregateIdentifier
    private Integer libraryId;
    private String name;
}
