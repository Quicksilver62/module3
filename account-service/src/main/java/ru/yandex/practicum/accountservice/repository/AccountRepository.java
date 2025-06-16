package ru.yandex.practicum.accountservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.accountservice.enums.Currency;
import ru.yandex.practicum.accountservice.model.UserAccount;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<UserAccount, Long> {

    List<UserAccount> findAllByUsername(String username);

    Optional<UserAccount> findByUsernameAndCurrency(String username, Currency currency);
}
