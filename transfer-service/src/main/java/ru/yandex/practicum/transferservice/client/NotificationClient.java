package ru.yandex.practicum.transferservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.transferservice.config.FeignConfig;

@FeignClient(name = "notification-service", path = "/api", configuration = FeignConfig.class)
public interface NotificationClient {

    @PostMapping("/notify")
    void sendNotification(@RequestBody String message);
} 