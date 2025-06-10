package ru.yandex.practicum.frontend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.yandex.practicum.frontend.clients.AccountClient;
import ru.yandex.practicum.frontend.model.Account;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private static final String ACCOUNT_SERVICE_URL = "http://account-service/api/accounts";

    private final AccountClient accountClient;

    public List<Account> getAccounts() {
        return accountClient.getAccounts();
    }
}
