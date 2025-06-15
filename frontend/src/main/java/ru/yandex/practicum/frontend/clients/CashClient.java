package ru.yandex.practicum.frontend.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.frontend.config.FeignConfig;
import ru.yandex.practicum.frontend.model.CashOperation;

@FeignClient(name = "cash-service", path = "/api", configuration = FeignConfig.class)
public interface CashClient {

    @PostMapping("/put")
    ResponseEntity<?> putCash(@RequestBody CashOperation cashOperation);

    @PostMapping("/get")
    ResponseEntity<?> getCash(@RequestBody CashOperation cashOperation);
}
