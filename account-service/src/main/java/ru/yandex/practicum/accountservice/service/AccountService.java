package ru.yandex.practicum.accountservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.accountservice.enums.Currency;
import ru.yandex.practicum.accountservice.mapper.AccountMapper;
import ru.yandex.practicum.accountservice.model.UserAccount;
import ru.yandex.practicum.accountservice.model.dto.AccountDto;
import ru.yandex.practicum.accountservice.repository.AccountRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public List<AccountDto> getAccounts(String username) {
        return accountRepository.findAllByUsername(username)
                .stream()
                .map(accountMapper::toAccountDto)
                .collect(Collectors.toList());
    }

    public AccountDto createAccount(String username, Currency currency) {
        var accounts = accountRepository.findAllByUsername(username)
                .stream()
                .filter(account -> account.getCurrency().equals(currency))
                .toList();
        if (accounts.isEmpty()) {
            var userAccount = UserAccount.builder()
                    .username(username)
                    .currency(currency)
                    .exists(true)
                    .value(0.0)
                    .build();
            var dbAccount = accountRepository.save(userAccount);
            return accountMapper.toAccountDto(dbAccount);
        }
        throw new RuntimeException("Account already exists");
    }
}
