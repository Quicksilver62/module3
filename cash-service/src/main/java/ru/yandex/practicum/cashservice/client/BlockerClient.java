package ru.yandex.practicum.cashservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "blocker-service", url = "/api")
public interface BlockerClient {

    @GetMapping("/api/isBlocked/")
    boolean checkBlock();
} 