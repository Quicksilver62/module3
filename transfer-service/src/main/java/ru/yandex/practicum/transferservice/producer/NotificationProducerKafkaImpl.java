package ru.yandex.practicum.transferservice.producer;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(value = "spring.kafka.producer.enabled", havingValue = "true", matchIfMissing = true)
public class NotificationProducerKafkaImpl implements NotificationProducer {

    private final KafkaTemplate<UUID, String> kafkaTemplate;

    private final String TOPIC = "notifications";

    @Override
    public void send(String message) {
        ProducerRecord<UUID, String> record = new ProducerRecord<>(TOPIC, UUID.randomUUID(), message);
        kafkaTemplate.send(record);
    }
}
