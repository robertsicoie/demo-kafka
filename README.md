Spring boot with Apache Kafka
===
Simple producer and consumer to demonstrate Kafka basic features. 

Prerequisites
---
Download and run Apache Kafka, create the `demo-topic` topic. For details see [this page](https://kafka.apache.org/quickstart).

Run
---
Build the project: 
```
mvn clean package
```
Then start the consumer
```bash
java -Dserver.port=8081 -jar target/demo-kafka-0.0.1-SNAPSHOT.jar -c
```
You can start a second consumer in the same consumer group
```bash
java -Dserver.port=8082 -jar target/demo-kafka-0.0.1-SNAPSHOT.jar -c
```

Run the publisher code
```bash
java -jar target/demo-kafka-0.0.1-SNAPSHOT.jar -p message1 message2 message3
``` 