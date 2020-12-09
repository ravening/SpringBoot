package com.pluralsight.streaming.servings;

import com.pluralsight.streaming.data.Order;
import com.pluralsight.streaming.data.model.ServingsValue;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;

public class BlockingServingsProducer {

    private static final Logger log = LoggerFactory.getLogger(BlockingServingsProducer.class);
    private static final String SERVINGS_TOPIC = "servings";

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        KafkaProducer<String, ServingsValue> producer = createProducer();

        ServerSocket ss = new ServerSocket(7777);
        addShutdownHook(ss);

        while(true) {
            Socket socket = ss.accept();

            Order order = readOrder(socket);
            produceOrder(producer, order);

            socket.close();
        }
    }

    private static KafkaProducer<String, ServingsValue> createProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "broker-1:9092,broker-2:9093,broker-3:9094");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        props.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");

        KafkaProducer<String, ServingsValue> producer = new KafkaProducer<>(props);

        Thread shutdownHook = new Thread(producer::close);
        Runtime.getRuntime().addShutdownHook(shutdownHook);

        return producer;
    }

    private static void produceOrder(KafkaProducer<String, ServingsValue> producer, Order order) {
        String key = order.getOrderId();
        ServingsValue value = ServingsValue.newBuilder()
                .setDeliveryAddress(order.getDeliveryAddress())
                .setServings(new ArrayList<>(order.getServings()))
                .build();

        ProducerRecord<String, ServingsValue> producerRecord =
                new ProducerRecord<>(SERVINGS_TOPIC, key, value);

        log.info("Sending message with key " + key + " to Kafka");

        producer.send(producerRecord);

        producer.flush();
    }

    private static Order readOrder(Socket socket) throws IOException, ClassNotFoundException {
        InputStream inputStream = socket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        return  (Order) objectInputStream.readObject();
    }

    private static void addShutdownHook(ServerSocket ss) {
        Thread shutdownHook = new Thread(() -> {
            try {
                ss.close();
            } catch (IOException e) {
                log.error("Something went wrong while closing the socket!");
            }
        });
        Runtime.getRuntime().addShutdownHook(shutdownHook);
    }

}
