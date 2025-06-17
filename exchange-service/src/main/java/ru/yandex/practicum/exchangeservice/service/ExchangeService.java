package ru.yandex.practicum.exchangeservice.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.exchangeservice.clients.ExchangeClient;
import ru.yandex.practicum.exchangeservice.model.Rate;
import ru.yandex.practicum.exchangeservice.model.TransferOperation;

import java.util.List;

import static ru.yandex.practicum.exchangeservice.enums.Currency.RUB;

@Getter
@Service
@RequiredArgsConstructor
public class ExchangeService {

    private final ExchangeClient exchangeClient;

    public Double calculateExchangePrice(TransferOperation transferOperation) {
        var rates = exchangeClient.getRates();
        double result = transferOperation.value();
        if (transferOperation.fromCurrency().equals(transferOperation.toCurrency())) {
            return transferOperation.value();
        }
        if (!RUB.name().equals(transferOperation.fromCurrency())) {
            var rate = currencyToRub(rates, transferOperation.fromCurrency());
            result = result * rate;
        }
        if (!RUB.name().equals(transferOperation.toCurrency())) {
            var rate = currencyToRub(rates, transferOperation.toCurrency());
            result = result / rate;
        }
        return result;
    }

    private Double currencyToRub(List<Rate> rates, String currency) {
        return rates.stream()
                .filter(rate -> rate.getCurrency().name().equals(currency))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid currency: " + currency))
                .getValue();
    }
}
