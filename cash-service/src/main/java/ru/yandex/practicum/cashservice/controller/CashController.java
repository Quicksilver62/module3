package ru.yandex.practicum.cashservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.cashservice.model.CashOperation;
import ru.yandex.practicum.cashservice.service.CashService;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class CashController {

    private final CashService cashService;

    @PostMapping("/put")
    public ResponseEntity<?> putCash(@RequestBody CashOperation cashOperation) {
        try {
            String result = cashService.putCash(cashOperation);
            if (result.isEmpty()) {
                return ResponseEntity.status(201).build();
            } else {
                return ResponseEntity.internalServerError().body(result);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Ошибка при выполнении операции: " + e.getMessage());
        }
    }

    @PostMapping("/get")
    public ResponseEntity<?> getCash(@RequestBody CashOperation cashOperation) {
        try {
            String result = cashService.getCash(cashOperation);
            if (result.isEmpty()) {
                return ResponseEntity.status(201).build();
            } else {
                return ResponseEntity.internalServerError().body(result);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Ошибка при выполнении операции: " + e.getMessage());
        }
    }
}
