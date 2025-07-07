package ru.yandex.practicum.exchangeservice.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.exchangeservice.clients.ExchangeClient;
import ru.yandex.practicum.exchangeservice.enums.Currency;
import ru.yandex.practicum.exchangeservice.model.Rate;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnProperty(value = "spring.kafka.consumer.enabled", havingValue = "true", matchIfMissing = true)
public class KafkaConsumer {

    private final ExchangeClient exchangeClient;

    private final List<Rate> rates = new CopyOnWriteArrayList<>();

    private static final String TOPIC = "exchange";
    private static final String CONSUMER_GROUP_ID = "exchange-consumer";

    @KafkaListener(topics = TOPIC, groupId = CONSUMER_GROUP_ID,
            containerFactory = "kafkaListenerContainerFactory")
    @RetryableTopic(
            backoff = @Backoff(delay = 1000, multiplier = 2),
            attempts = "5",
            exclude = {ClassCastException.class}
    )
    public void listen(ConsumerRecord<String, String> record) {
        try {
            Currency currency = Currency.valueOf(record.key());
            Double rateValue = Double.parseDouble(record.value());

            Rate newRate = Rate.builder()
                    .currency(currency)
                    .value(rateValue)
                    .build();

            rates.removeIf(rate -> rate.getCurrency() == currency);
            rates.add(newRate);

            log.info("Updated rates: {}", rates);

        } catch (IllegalArgumentException e) {
            log.error("Invalid currency key or rate value: key={}, value={}", record.key(), record.value(), e);
        }
    }

    public List<Rate> getRates() {
        if (rates.isEmpty()) {
            return exchangeClient.getRates();
        }
        return rates;
    }
}
