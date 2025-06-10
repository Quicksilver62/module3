package ru.yandex.practicum.frontend.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import ru.yandex.practicum.frontend.model.Account;

import java.util.List;

@FeignClient(name = "gateway", path = "/api/accounts")
public interface AccountClient {

    @GetMapping
    List<Account> getAccounts();
}
