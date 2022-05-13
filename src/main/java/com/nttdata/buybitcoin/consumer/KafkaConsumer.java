package com.nttdata.buybitcoin.consumer;

import com.nttdata.buybitcoin.events.KafkaEvent;
import com.nttdata.buybitcoin.util.Topic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@EnableKafka
@Component
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = Topic.ACCEPTED_BUY, containerFactory = "kafkaListenerContainerFactory", groupId = "group_1")
    public void requestBuyConsumer(KafkaEvent<?> event) {
        LOGGER.info("[KafkaConsumer][requestBuyConsumer][start]" + event.toString());
        LOGGER.info("[KafkaConsumer][requestBuyConsumer][generatedTransactionId]" + UUID.randomUUID().toString());
        LOGGER.info("[KafkaConsumer][requestBuyConsumer][end]");
    }

}
