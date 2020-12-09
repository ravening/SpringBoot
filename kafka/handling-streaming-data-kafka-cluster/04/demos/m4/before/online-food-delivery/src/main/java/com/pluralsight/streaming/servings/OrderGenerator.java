package com.pluralsight.streaming.servings;

import java.util.Arrays;

import com.pluralsight.streaming.servings.Order;
import com.pluralsight.streaming.servings.model.DeliveryAddress;
import com.pluralsight.streaming.servings.model.Serving;
import com.pluralsight.streaming.servings.model.ServingStatus;

public class OrderGenerator {

    public static Order generateCreatedOrder() {
        return  generateOrder();
    }

    public static Order generateCookingOrder() {
        Order order = generateOrder();

        order.getServings().forEach(serving -> serving.setServingStatus(ServingStatus.COOKING));

        return order;
    }

    public static Order generateDoneOrder() {
        Order order = generateOrder();

        order.getServings().forEach(serving -> serving.setServingStatus(ServingStatus.DONE));

        return order;
    }

    private static Order generateOrder() {
        DeliveryAddress deliveryAddress = DeliveryAddress.newBuilder()
                .setCity("London")
                .setNumber(22)
                .setStreet("Orange St")
                .build();

        Serving omlete = Serving.newBuilder()
                .setFoodType("Pizza")
                .setSize(2)
                .setServingStatus(ServingStatus.CREATED)
                .build();

        Serving fries = Serving.newBuilder()
                .setFoodType("Fries")
                .setSize(4)
                .setServingStatus(ServingStatus.CREATED)
                .build();

        Order order = new Order();

        order.setOrderId("1");
        order.setServings(Arrays.asList(omlete, fries));
        order.setDeliveryAddress(deliveryAddress);
        return order;
    }
}
