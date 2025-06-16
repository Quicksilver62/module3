package ru.yandex.practicum.cashservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.cashservice.model.Account;
import ru.yandex.practicum.cashservice.config.FeignConfig;

@FeignClient(name = "account-service", path = "/api", configuration = FeignConfig.class)
public interface AccountClient {

    @GetMapping("/account")
    Account getAccount(@PathVariable String currency);

    @PostMapping("/update")
    ResponseEntity<?> updateAccount(@RequestBody Account account);
}