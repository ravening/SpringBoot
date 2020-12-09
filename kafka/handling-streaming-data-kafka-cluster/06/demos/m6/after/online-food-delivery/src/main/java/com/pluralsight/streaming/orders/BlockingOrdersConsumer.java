package com.pluralsight.streaming.orders;


import com.pluralsight.streaming.data.Order;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.time.Duration;
import java.util.*;

import static com.pluralsight.streaming.data.Order.getDeliveryAddressFromJSON;
import static com.pluralsight.streaming.data.Order.getServingsFromJSON;

public class BlockingOrdersConsumer {

    private static final Logger log = LoggerFactory.getLogger(BlockingOrdersConsumer.class);
    private static final String ORDERS_TOPIC = "orders";

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "broker-1:9092,broker-2:9093,broker-3:9094");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "orders.consumer");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        Thread haltedHook = new Thread(consumer::close);
        Runtime.getRuntime().addShutdownHook(haltedHook);

        consumer.subscribe(Collections.singletonList(ORDERS_TOPIC));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            records.forEach(record -> processOrder(record.value()));
            consumer.commitSync();
        }
    }

    private static String generateOrderId() {
        return UUID.randomUUID().toString();
    }

    private static void processOrder(String orderJSON) {
        log.info("New order received: " + orderJSON);

        String orderId = generateOrderId();

        Order order = new Order();

        order.setOrderId(orderId);
        order.setDeliveryAddress(getDeliveryAddressFromJSON(orderJSON));
        order.setServings(getServingsFromJSON(orderJSON));

        log.info("Processing a new order with id " + orderId);

        sendSynchronous(order);
    }

    private static void sendSynchronous(Order order) {
        try(Socket socket = new Socket("localhost", 7777)) {
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(order);
        } catch (IOException e) {
            log.info("Something went wrong while trying to open the socket");
        }
    }
}
