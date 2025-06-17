package ru.yandex.practicum.transferservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.transferservice.config.FeignConfig;
import ru.yandex.practicum.transferservice.model.TransferOperation;

@FeignClient(name = "exchange-service", path = "/api", configuration = FeignConfig.class)
public interface ExchangeClient {

    @PostMapping("/exchange")
    Double exchange(@RequestBody TransferOperation transferOperation);
}
