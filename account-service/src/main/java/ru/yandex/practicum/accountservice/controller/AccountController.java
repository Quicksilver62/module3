package ru.yandex.practicum.accountservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.accountservice.enums.Currency;
import ru.yandex.practicum.accountservice.model.Account;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @GetMapping("/create")
    public UUID createAccount() {
        return UUID.randomUUID();
    }

    @GetMapping("/")
    public List<Account> accounts() {
        return List.of(new Account(Currency.CNY, true, 0.0));
    }
}
