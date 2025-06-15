package ru.yandex.practicum.frontend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.frontend.clients.CashClient;
import ru.yandex.practicum.frontend.enums.Currency;
import ru.yandex.practicum.frontend.model.CashOperation;

@Service
@RequiredArgsConstructor
public class CashService {

    private final CashClient cashClient;

    public String handleCashOperation(Currency currency, double value, String action) {
        CashOperation cashOperation = new CashOperation(currency, value, action);
        ResponseEntity<?> response;
        try {
            if (action.equals("PUT")) {
               response = cashClient.putCash(cashOperation);
            }
            else if (action.equals("GET")) {
                response = cashClient.getCash(cashOperation);
            }
            else {
                return "Не определено действие с наличными";
            }

            if (response.getStatusCode().is2xxSuccessful()) {
                return "";
            }
            else {
                return response.getBody() != null
                        ? response.getBody().toString()
                        : "Ошибка без описания (статус: " + response.getStatusCode() + ")";
            }
        } catch (Exception e) {
            return "Ошибка при вызове cash-service: " + e.getMessage();
        }
    }
}
