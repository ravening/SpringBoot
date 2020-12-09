package com.pluralsight.kafka.consumer.model;

import com.pluralsight.kafka.consumer.enums.UserId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InternalUser {

    public InternalUser(UserId userId) {
        this.userId = userId;
        this.preferences = new ArrayList<>();
        this.suggestions = new ArrayList<>();
    }

    private UserId userId;

    private List<PreferredProduct> preferences;

    private List<String> suggestions;
}
