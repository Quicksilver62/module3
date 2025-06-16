package ru.yandex.practicum.cashservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.cashservice.client.AccountClient;
import ru.yandex.practicum.cashservice.client.BlockerClient;
import ru.yandex.practicum.cashservice.client.NotificationClient;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final AccountClient accountClient;
    private final BlockerClient blockerClient;
    private final NotificationClient notificationClient;

//    public Transaction processTransaction(Transaction transaction) {
//        try {
//            // Шаг 1: Проверка счета
//            Account account = accountClient.ge(transaction.getUserId());
//            if (transaction.getType() == TransactionType.WITHDRAW && account.getValue() < transaction.getAmount()) {
//                transaction.setStatus(TransactionStatus.FAILED);
//                transaction.setErrorMessage("Insufficient funds");
//                return transaction;
//            }
//
//            // Шаг 2: Проверка блокировки
//            boolean isBlocked = blockerClient.checkBlock(transaction.getUserId());
//            if (isBlocked) {
//                transaction.setStatus(TransactionStatus.FAILED);
//                transaction.setErrorMessage("Account is blocked");
//                notificationClient.sendNotification(new Notification(
//                    transaction.getUserId(),
//                    "Transaction failed: Account is blocked"
//                ));
//                return transaction;
//            }
//
//            // Шаг 3: Выполнение транзакции
//            if (transaction.getType() == TransactionType.DEPOSIT) {
//                account.setValue(account.getValue() + transaction.getAmount());
//            } else {
//                account.setValue(account.getValue() - transaction.getAmount());
//            }
//
//            transaction.setStatus(TransactionStatus.COMPLETED);
//            return transaction;
//
//        } catch (Exception e) {
//            // Компенсирующие действия при ошибке
//            transaction.setStatus(TransactionStatus.FAILED);
//            transaction.setErrorMessage("Transaction failed: " + e.getMessage());
//            notificationClient.sendNotification(new Notification(
//                transaction.getUserId(),
//                "Transaction failed: " + e.getMessage()
//            ));
//            return transaction;
//        }
//    }
} 