package ru.yandex.practicum.frontend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.frontend.clients.AccountClient;
import ru.yandex.practicum.frontend.model.Account;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountClient accountClient;

    public List<Account> getAccounts() {
        return accountClient.getAccounts();
    }
}
