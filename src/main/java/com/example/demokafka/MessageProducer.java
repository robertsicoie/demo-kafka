package com.example.demokafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;


public class MessageProducer {

    private static Logger LOG = LoggerFactory.getLogger(MessageProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @Autowired
    private KafkaTemplate<String, String> objectKafkaTemplate;

    @Value("${kafka.topicName}")
    private String topicName;

    public void send(Message message) {
        ListenableFuture<SendResult<String, String>> future = objectKafkaTemplate.send(topicName, message.toString());
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                LOG.error("Failed to send message '" + message.toString() + "' on topic '" + topicName + "' due to: " + throwable.getMessage(), throwable);
            }

            @Override
            public void onSuccess(SendResult<String, String> stringObjectSendResult) {
                LOG.info("Successfully sent message '" + message.toString() + "' on topic '" + topicName + "'.");
            }
        });
    }

}
