package ru.yandex.practicum.frontend.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import ru.yandex.practicum.frontend.model.Account;

import java.util.List;

@FeignClient(name = "gateway-service", path = "/api/accounts")
public interface AccountClient {

    @GetMapping
    List<Account> getAccounts(@RequestHeader("Authorization") String token);
}
