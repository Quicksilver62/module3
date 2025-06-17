package ru.yandex.practicum.transferservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import ru.yandex.practicum.transferservice.config.FeignConfig;

@FeignClient(name = "blocker-service", url = "/api", configuration = FeignConfig.class)
public interface BlockerClient {

    @GetMapping("/isBlocked/")
    boolean checkBlock();
} 