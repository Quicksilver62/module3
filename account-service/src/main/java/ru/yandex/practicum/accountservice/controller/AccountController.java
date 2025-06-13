package ru.yandex.practicum.accountservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.accountservice.enums.Currency;
import ru.yandex.practicum.accountservice.model.dto.AccountDto;
import ru.yandex.practicum.accountservice.service.AccountService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/create/{currency}")
    public AccountDto createAccount(OAuth2AuthenticationToken authentication, @PathVariable Currency currency) {
        OAuth2User principal = authentication.getPrincipal();
        String username = principal.getAttribute("preferred_username");
        return accountService.createAccount(username, currency);
    }

    @GetMapping
    public List<AccountDto> accounts(OAuth2AuthenticationToken authentication) {
        OAuth2User principal = authentication.getPrincipal();
        String username = principal.getAttribute("preferred_username");
        return accountService.getAccounts(username);
    }
}
