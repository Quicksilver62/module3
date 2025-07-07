package ru.yandex.practicum.notificationservice.consumer;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(value = "spring.kafka.consumer.enabled", havingValue = "true", matchIfMissing = true)
public class KafkaConsumer {

    private static final String TOPIC = "notifications";
    private static final String CONSUMER_GROUP_ID = "notification-consumer";

    @KafkaListener(topics = TOPIC, groupId = CONSUMER_GROUP_ID,
            containerFactory = "kafkaListenerContainerFactory")
    @RetryableTopic(
            backoff = @Backoff(delay = 1000, multiplier = 2),
            attempts = "5",
            exclude = {ClassCastException.class}
    )
    public void listen(@Payload final String message) {
        System.out.println(message);
    }
}
