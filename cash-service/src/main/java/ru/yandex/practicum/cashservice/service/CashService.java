package ru.yandex.practicum.cashservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.cashservice.client.AccountClient;
import ru.yandex.practicum.cashservice.client.BlockerClient;
import ru.yandex.practicum.cashservice.client.NotificationClient;
import ru.yandex.practicum.cashservice.model.Account;
import ru.yandex.practicum.cashservice.model.CashOperation;

@Service
@RequiredArgsConstructor
public class CashService {

    private final AccountClient accountClient;
    private final BlockerClient blockerClient;
    private final NotificationClient notificationClient;

    public String putCash(CashOperation cashOperation) {
        try {
            if (blockerClient.checkBlock()) {
                return "Операция заблокирована";
            }
            Account account = accountClient.getAccount(cashOperation.currency().name());
            account.setValue(account.getValue() + cashOperation.value());
            accountClient.updateAccount(account);
            notificationClient.sendNotification("Зачислено %s %s".formatted(cashOperation.value(), cashOperation.currency()));
        } catch (Exception e) {
            notificationClient.sendNotification("Произошла ошибка при выполнении транзакции");
        }
        return "";
    }

    public String getCash(CashOperation cashOperation) {
        try {
            Account account = accountClient.getAccount(cashOperation.currency().name());
            if (blockerClient.checkBlock()) {
                return "Операция заблокирована";
            }
            if (account.getValue() < cashOperation.value()) {
                return "Недостаточно средств";
            }
            account.setValue(account.getValue() - cashOperation.value());
            accountClient.updateAccount(account);
            notificationClient.sendNotification("Снято %s %s".formatted(cashOperation.value(), cashOperation.currency()));
        } catch (Exception e) {
            notificationClient.sendNotification("Произошла ошибка при выполнении транзакции");
        }
        return "";
    }
}
