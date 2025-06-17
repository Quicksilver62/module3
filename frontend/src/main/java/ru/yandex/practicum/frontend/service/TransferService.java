package ru.yandex.practicum.frontend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.frontend.clients.TransferClient;
import ru.yandex.practicum.frontend.model.TransferOperation;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final TransferClient transferClient;

    public String handleTransferOperation(String fromCurrency, String toCurrency, Double value, String toLogin) {
        TransferOperation transferOperation = new TransferOperation(fromCurrency, toCurrency, value, toLogin);
        try {
            ResponseEntity<?> response = transferClient.transfer(transferOperation);
            if (response.getStatusCode().is2xxSuccessful()) {
                return "";
            }
            else {
                return response.getBody() != null
                        ? response.getBody().toString()
                        : "Ошибка (статус: " + response.getStatusCode() + ")";
            }
        } catch (Exception e) {
            return "Ошибка при вызове transfer-service: " + e.getMessage();
        }
    }
}
