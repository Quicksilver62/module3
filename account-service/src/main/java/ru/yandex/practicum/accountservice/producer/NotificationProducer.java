package ru.yandex.practicum.accountservice.producer;

public interface NotificationProducer {
    void send(String message);
}
