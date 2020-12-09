package com.pluralsight.streaming.servings;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;

import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import com.pluralsight.streaming.data.Order;
import com.pluralsight.streaming.data.model.ServingsValue;

public class ServingsProducer {

    private static final Logger log = LoggerFactory.getLogger(ServingsProducer.class);
    private static final String SERVINGS_TOPIC = "servings";

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "broker-1:9092,broker-2:9093,broker-3:9094");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        props.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");
        
        KafkaProducer<String, ServingsValue> producer = new KafkaProducer<>(props);

        Thread shutdownHook = new Thread(producer::close);
        Runtime.getRuntime().addShutdownHook(shutdownHook);

        Order order = OrderGenerator.generateDoneOrder();

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

        log.info("Successfully produced 1 message to " + SERVINGS_TOPIC + " topic");

    }
}
