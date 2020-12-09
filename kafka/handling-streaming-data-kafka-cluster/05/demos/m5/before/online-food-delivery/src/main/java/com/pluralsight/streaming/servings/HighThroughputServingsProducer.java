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

import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class HighThroughputServingsProducer {

    private static final Logger log = LoggerFactory.getLogger(HighThroughputServingsProducer.class);
    private static final String SERVINGS_TOPIC = "servings";

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093,localhost:9094");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        props.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");

        //High Throughput Props
        props.put(ProducerConfig.ACKS_CONFIG, "0");
        props.put(ProducerConfig.LINGER_MS_CONFIG, "5");
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, "16384");
        props.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, "10");

        KafkaProducer<String, ServingsValue> producer = new KafkaProducer<>(props);

        Order order = OrderGenerator.generateCreatedOrder();

        ServingsValue value = ServingsValue.newBuilder()
                .setDeliveryAddress(order.getDeliveryAddress())
                .setServings(new ArrayList<>(order.getServings()))
                .build();

        Thread shutdownHook = new Thread(producer::close);
        Runtime.getRuntime().addShutdownHook(shutdownHook);

        long i = 0;
        while(true) {
            ProducerRecord<String, ServingsValue> producerRecord = 
                new ProducerRecord<>(SERVINGS_TOPIC, String.valueOf(i), value);
            producer.send(producerRecord);
            i++;
        }
    }
}
