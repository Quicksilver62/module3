package ru.yandex.practicum.exchangegenerator.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.exchangegenerator.enums.Currency;
import ru.yandex.practicum.exchangegenerator.model.Rate;
import ru.yandex.practicum.exchangegenerator.producer.ExchangeProducer;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Service
@RequiredArgsConstructor
public class RateService {

    private final ExchangeProducer exchangeProducer;

    private List<Rate> rates = List.of(
            new Rate(Currency.RUB, 1.0),
            new Rate(Currency.USD, randomRate()),
            new Rate(Currency.CNY, randomRate())
    );

    @Scheduled(cron = "0 * * * * *")
    public void calculateRates() {
        rates = List.of(
                new Rate(Currency.RUB, 1.0),
                new Rate(Currency.USD, randomRate()),
                new Rate(Currency.CNY, randomRate())
        );
        rates.forEach(rate -> exchangeProducer
                .send(rate.getCurrency().name(), rate.getValue().toString()));
        System.out.println("Новые курсы: " + rates);
    }

    private double randomRate() {
        return ThreadLocalRandom.current().nextDouble(1.0, 200.0);
    }
}
