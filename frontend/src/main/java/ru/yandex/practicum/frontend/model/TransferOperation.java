package ru.yandex.practicum.frontend.model;

public record TransferOperation(String fromCurrency, String toCurrency, double value, String toLogin) {
}
