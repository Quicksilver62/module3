package ru.yandex.practicum.frontend.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.frontend.config.FeignConfig;
import ru.yandex.practicum.frontend.model.TransferOperation;

@FeignClient(name = "transfer-service", path = "/api", configuration = FeignConfig.class)
public interface TransferClient {

    @PostMapping("/transfer")
    ResponseEntity<?> transfer(@RequestBody TransferOperation transferOperation);
}
