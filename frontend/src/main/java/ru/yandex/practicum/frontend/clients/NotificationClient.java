package ru.yandex.practicum.frontend.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import ru.yandex.practicum.frontend.config.FeignConfig;

@FeignClient(name = "notification-service", path = "/api", configuration = FeignConfig.class)
public interface NotificationClient {

    @GetMapping("/auth")
    ResponseEntity<?> notifyUserAuth();

    @GetMapping("/cash")
    ResponseEntity<?> notifyUserCash();
}
