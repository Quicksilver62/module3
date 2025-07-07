package ru.yandex.practicum.transferservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.transferservice.client.AccountClient;
import ru.yandex.practicum.transferservice.client.BlockerClient;
import ru.yandex.practicum.transferservice.client.ExchangeClient;
import ru.yandex.practicum.transferservice.model.Account;
import ru.yandex.practicum.transferservice.model.TransferOperation;
import ru.yandex.practicum.transferservice.producer.NotificationProducer;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferService {

    private final AccountClient accountClient;
    private final BlockerClient blockerClient;
    private final ExchangeClient exchangeClient;
    private final NotificationProducer notificationProducer;

    public String transfer(TransferOperation transferOperation) {
        // Шаг 1: Проверка блокировки
        if (blockerClient.checkBlock()) {
            return "Операция заблокирована";
        }

        // Шаг 2: Получение аккаунтов (компенсируемые операции)
        Account userAccount;
        Account targetAccount;

        try {
            userAccount = accountClient.getAccount(transferOperation.fromCurrency());
            targetAccount = accountClient.getAccountsByUsername(transferOperation.toLogin())
                    .stream()
                    .filter(acc -> transferOperation.toCurrency().equals(acc.getCurrency().name()))
                    .findFirst()
                    .orElse(null);

            if (targetAccount == null) {
                return "Аккаунт пользователя %s не найден".formatted(transferOperation.toLogin());
            }
        } catch (Exception e) {
            return "Ошибка при получении информации об аккаунтах";
        }

        // Шаг 3: Проверка баланса (не требует компенсации)
        if (userAccount.getValue() < transferOperation.value()) {
            return "Недостаточно средств";
        }

        // Шаг 4: Конвертация валюты (компенсируемая операция)
        Double valueInTargetCurrency;
        try {
            valueInTargetCurrency = exchangeClient.exchange(transferOperation);
        } catch (Exception e) {
            return "Ошибка конвертации валюты";
        }

        // Шаг 5: Подготовка данных для компенсации
        final double originalSenderAmount = userAccount.getValue();
        final double originalReceiverAmount = targetAccount.getValue();
        final String transactionId = UUID.randomUUID().toString();

        try {
            // Шаг 6: Списание средств (компенсируемая операция)
            userAccount.setValue(originalSenderAmount - transferOperation.value());
            accountClient.updateAccount(userAccount);

            // Шаг 7: Зачисление средств (компенсируемая операция)
            targetAccount.setValue(originalReceiverAmount + valueInTargetCurrency);
            accountClient.updateAccount(targetAccount);

            // Шаг 8: Уведомление (последний неудаляемый шаг)
            notificationProducer.send("Переведено %s %s пользователю %s".formatted(
                    transferOperation.value(), transferOperation.fromCurrency(), transferOperation.toLogin()));

            return "";

        } catch (Exception e) {
            // Компенсирующие транзакции
            try {
                // Восстановление баланса отправителя
                userAccount.setValue(originalSenderAmount);
                accountClient.updateAccount(userAccount);

                // Восстановление баланса получателя (если нужно)
                if (targetAccount.getValue() != originalReceiverAmount) {
                    targetAccount.setValue(originalReceiverAmount);
                    accountClient.updateAccount(targetAccount);
                }
            } catch (Exception ex) {
                log.error("Ошибка при откате транзакции {}: {}", transactionId, ex.getMessage());
                notificationProducer.send("Системная ошибка. Обратитесь в поддержку. ID: " + transactionId);
            }

            return "Произошла ошибка при выполнении транзакции. Изменения отменены.";
        }
    }
}
