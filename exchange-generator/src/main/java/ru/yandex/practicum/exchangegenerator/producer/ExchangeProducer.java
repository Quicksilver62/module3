package ru.yandex.practicum.exchangegenerator.producer;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExchangeProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final String TOPIC = "notifications";

    public void send(String key, String message) {
        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, key, message);
        kafkaTemplate.send(record);
    }
}
