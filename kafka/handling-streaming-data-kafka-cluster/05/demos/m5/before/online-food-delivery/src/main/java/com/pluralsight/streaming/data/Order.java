package com.pluralsight.streaming.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.pluralsight.streaming.data.model.DeliveryAddress;
import com.pluralsight.streaming.data.model.Serving;
import com.pluralsight.streaming.data.model.ServingStatus;
import org.json.JSONArray;
import org.json.JSONObject;

public class Order implements Serializable {

    private String orderId;

    private List<Serving> servings;

    private DeliveryAddress deliveryAddress;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<Serving> getServings() {
        return servings;
    }

    public void setServings(List<Serving> servings) {
        this.servings = servings;
    }

    public DeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }


    public static DeliveryAddress getDeliveryAddressFromJSON(String orderJSON) {
        JSONObject order = new JSONObject(orderJSON);
        JSONObject delivery = order.getJSONObject("delivery");

        return DeliveryAddress.newBuilder()
                .setNumber(delivery.getInt("number"))
                .setStreet(delivery.getString("street"))
                .setCity(delivery.getString("city"))
                .build();
    }

    public static List<Serving> getServingsFromJSON(String orderJSON) {
        JSONObject order = new JSONObject(orderJSON);
        JSONArray servings = order.getJSONArray("servings");
        List<Serving> serv = new ArrayList<>();
        for (int i = 0; i < servings.length(); i++) {
            JSONObject serving = servings.getJSONObject(i);
            serv.add(Serving.newBuilder()
                    .setFoodType(serving.getString("item"))
                    .setSize(serving.getInt("size"))
                    .setServingStatus(ServingStatus.CREATED)
                    .build());
        }
        return serv;
    }
}
