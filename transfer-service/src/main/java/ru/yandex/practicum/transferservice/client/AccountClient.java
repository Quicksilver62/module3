package ru.yandex.practicum.transferservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.transferservice.config.FeignConfig;
import ru.yandex.practicum.transferservice.model.Account;

import java.util.List;

@FeignClient(name = "account-service", path = "/api", configuration = FeignConfig.class)
public interface AccountClient {

    @GetMapping("/account")
    Account getAccount(@PathVariable String currency);

    @GetMapping("/account")
    List<Account> getAccountsByUsername(@RequestParam String username);

    @PostMapping("/update")
    ResponseEntity<?> updateAccount(@RequestBody Account account);
}