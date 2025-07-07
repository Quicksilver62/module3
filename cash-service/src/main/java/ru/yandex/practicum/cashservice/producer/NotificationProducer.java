package ru.yandex.practicum.cashservice.producer;

public interface NotificationProducer {
    void send(String message);
}
