package ru.yandex.practicum.cashservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.yandex.practicum.cashservice.config.FeignConfig;

@FeignClient(name = "blocker-service", url = "/api", configuration = FeignConfig.class)
public interface BlockerClient {

    @GetMapping("/isBlocked/")
    boolean checkBlock();
} 