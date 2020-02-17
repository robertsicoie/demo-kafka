package com.example.demokafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

@EnableKafka
public class MessageConsumer {

    private static Logger LOG = LoggerFactory.getLogger(MessageConsumer.class);

    @KafkaListener(
            topics = "${kafka.topicName}",
            containerFactory = "objectListenerContainerFactory")
    public void listener(String message,
                         @Header(KafkaHeaders.RECEIVED_PARTITION_ID) Integer partition,
                         @Header(KafkaHeaders.OFFSET) Long offset,
                         Acknowledgment ack){

        LOG.info("Got message '" + message + "' on partition " + partition + ", offset " + offset + ".");

        ack.acknowledge();
        LOG.info("Processed message '" + message + "'.");
    }
}
