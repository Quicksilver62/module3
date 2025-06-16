package ru.yandex.practicum.cashservice.model;

import ru.yandex.practicum.cashservice.enums.Currency;

public record CashOperation(Currency currency, double value, String action) {
}
