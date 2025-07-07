package ru.yandex.practicum.accountservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.accountservice.client.NotificationClient;
import ru.yandex.practicum.accountservice.enums.Currency;
import ru.yandex.practicum.accountservice.mapper.AccountMapper;
import ru.yandex.practicum.accountservice.model.UserAccount;
import ru.yandex.practicum.accountservice.model.dto.AccountDto;
import ru.yandex.practicum.accountservice.producer.NotificationProducer;
import ru.yandex.practicum.accountservice.repository.AccountRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final NotificationProducer notificationProducer;

    public List<AccountDto> getAccounts(String username) {
        var accounts = accountRepository.findAllByUsername(username)
                .stream()
                .map(accountMapper::toAccountDto)
                .collect(Collectors.toList());
        notificationProducer.send("Found accounts for user %s".formatted(username));
        return accounts;
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
            notificationProducer.send("Account for user %s in %s created".formatted(
                    username, currency.name()));
            return accountMapper.toAccountDto(dbAccount);
        }
        throw new RuntimeException("Account already exists");
    }

    public AccountDto getAccount(String username, Currency currency) {
        var optUserAccount = accountRepository.findByUsernameAndCurrency(username, currency);
        if (optUserAccount.isPresent()) {
            notificationProducer.send("Found account for user %s in %s".formatted(
                    username, currency.name()));
            return accountMapper.toAccountDto(optUserAccount.get());
        }
        throw new RuntimeException("Account with currency " + currency + " not found");
    }

    public AccountDto updateAccount(String username, AccountDto accountDto) {
        var optUserAccount = accountRepository.findByUsernameAndCurrency(username, accountDto.getCurrency());
        if (optUserAccount.isPresent()) {
            var userAccount = optUserAccount.get();
            userAccount.setValue(accountDto.getValue());
            accountRepository.save(userAccount);
            notificationProducer.send("Account for user %s updated".formatted(username));
        }
        throw new RuntimeException("Account with currency " + accountDto.getCurrency() + " not found");
    }
}
