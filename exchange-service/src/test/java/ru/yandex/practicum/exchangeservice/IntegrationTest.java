package ru.yandex.practicum.exchangeservice;

import org.apache.kafka.clients.producer.MockProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@EmbeddedKafka
public class IntegrationTest {

    @Test
    public void testConsumer(){
        try (var mockProducer = new MockProducer<>(true, new StringSerializer(), new StringSerializer())) {
            mockProducer.send(new ProducerRecord<>("output-topic", "Yandex", "Practicum"));

            assertEquals(1, mockProducer.history().size());
            assertEquals("Practicum", mockProducer.history().getFirst().value());
        }
    }
}
