package ru.yandex.practicum.exchangeservice.model;

public record TransferOperation(String fromCurrency, String toCurrency, double value, String toLogin) {
}
