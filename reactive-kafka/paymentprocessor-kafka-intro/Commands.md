# Create a topic
```bash
./bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1 --topic unconfirmed-transactions 
```

# Consume messages on topic
```bash
./bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic unconfirmed-transactions --from-beginning
```

# Delete a topic
```bash
./bin/kafka-topics.sh --zookeeper localhost:2181 --delete --topic unconfirmed-transactions
```
