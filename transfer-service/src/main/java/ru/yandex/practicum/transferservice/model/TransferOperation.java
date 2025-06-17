package ru.yandex.practicum.transferservice.model;

public record TransferOperation(String fromCurrency, String toCurrency, double value, String toLogin) {
}
