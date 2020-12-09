package com.pluralsight.kafka.streams;

import com.pluralsight.kafka.streams.model.Order;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class FraudDetectionApplication {

    private static Logger LOG = LoggerFactory.getLogger(FraudDetectionApplication.class);

    public static void main(String[] args) {

        // Topics:
        //     "payment" -> "validated-payments"

        // Message key:
        //     String transactionId
        // Message value ( order ):
        //     String userId
        //     Integer nbOfItems
        //     Float totalAmount

        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "fraud-detection-application");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9093, localhost:9094");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, SpecificAvroSerde.class);
        props.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");

        StreamsBuilder streamsBuilder = new StreamsBuilder();

        KStream<String, Order> stream = streamsBuilder.stream("payments");

        stream
                .peek(FraudDetectionApplication::printOnEnter)
                .filter((transactionId, order) -> !order.getUserId().toString().equals(""))
                .filter((transactionId, order) -> order.getNbOfItems() < 1000)
                .filter((transactionId, order) -> order.getTotalAmount() < 10000)
                .mapValues((order) -> {
                    order.setUserId(String.valueOf(order.getUserId()).toUpperCase());
                    return order;
                })
                .peek(FraudDetectionApplication::printOnExit)
                .to("validated-payments");

        Topology topology = streamsBuilder.build();

        KafkaStreams streams = new KafkaStreams(topology, props);

        streams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }

    private static void printOnEnter(String transactionId, Order order) {
        LOG.info("\n*******************************************");
        LOG.info("ENTERING stream transaction with ID < " + transactionId + " >, " +
                "of user < " + order.getUserId() + " >, total amount < " + order.getTotalAmount() +
                " > and nb of items < " + order.getNbOfItems() + " >");
    }

    private static void printOnExit(String transactionId, Order order) {
        LOG.info("EXITING from stream transaction with ID < " + transactionId + " >, " +
                "of user < " + order.getUserId() + " >, total amount < " + order.getTotalAmount() +
                " > and number of items < " + order.getNbOfItems() + " >");
    }
}
