package ru.yandex.practicum.frontend.model;

import ru.yandex.practicum.frontend.enums.Currency;

public record CashOperation(Currency currency, double value, String action) {
}
