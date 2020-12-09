package com.pluralsight.kafka.consumer;

import com.pluralsight.kafka.consumer.model.InternalUser;
import com.pluralsight.kafka.consumer.model.PreferredProduct;
import com.pluralsight.kafka.consumer.service.UserDB;
import com.pluralsight.kafka.model.Product;
import com.pluralsight.kafka.model.User;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class SuggestionEngine {

    private UserDB userDB = new UserDB();

    public void processSuggestions(User user, Product product) {
        log.info("InternalUser with ID: " + user.getUserId() +
                " showed interest over " + product.getProductType() + " " +
                "of color " + product.getColor() + " and design " + product.getDesignType());

        // Retrieve preferences from Database
        InternalUser internalUser = userDB.findByID(user.getUserId().toString());

        // Update internalUser preferences
        internalUser.getPreferences()
                .add(new PreferredProduct(product.getColor().toString(), product.getProductType().toString(), product.getDesignType().toString()));

        // Generate list of suggestions
        internalUser.setSuggestions(generateSugestions(internalUser.getPreferences()));

        // Store the suggestions in the database / send them to a kafka topic
        userDB.save(internalUser);
    }

    /**
     * @return hardcoded suggestions
     */
    private List<String> generateSugestions(List<PreferredProduct> preferences) {
        return Arrays.asList("TSHIRT,BLUE",
                "DESIGN,ORANGE,ROCKET",
                "TSHIRT,PURPLE,CAR");
    }

}
