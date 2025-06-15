package ru.yandex.practicum.frontend.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import ru.yandex.practicum.frontend.config.FeignConfig;
import ru.yandex.practicum.frontend.model.Account;

import java.util.List;

@FeignClient(name = "account-service", path = "/api/accounts", configuration = FeignConfig.class)
public interface AccountClient {

    @GetMapping
    List<Account> getAccounts();
}
