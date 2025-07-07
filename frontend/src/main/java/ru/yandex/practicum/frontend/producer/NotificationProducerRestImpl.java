package ru.yandex.practicum.frontend.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import ru.yandex.practicum.frontend.clients.NotificationClient;

@RequiredArgsConstructor
@ConditionalOnProperty(value = "spring.kafka.producer.enabled", havingValue = "false", matchIfMissing = true)
public class NotificationProducerRestImpl implements NotificationProducer {

    private final NotificationClient notificationClient;

    @Override
    public void send(String message) {
        notificationClient.sendNotification(message);
    }
}
