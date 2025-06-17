package ru.yandex.practicum.exchangeservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import ru.yandex.practicum.exchangeservice.config.FeignConfig;
import ru.yandex.practicum.exchangeservice.model.Rate;

import java.util.List;

@FeignClient(name = "exchange-generator", path = "/api", configuration = FeignConfig.class)
public interface ExchangeClient {

    @GetMapping("/rates")
    List<Rate> getRates();
}
