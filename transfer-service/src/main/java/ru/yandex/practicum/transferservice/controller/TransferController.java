package ru.yandex.practicum.transferservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.transferservice.model.TransferOperation;
import ru.yandex.practicum.transferservice.service.TransferService;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;

    @PostMapping("/put")
    public ResponseEntity<?> putCash(@RequestBody TransferOperation transferOperation) {
        try {
            String result = transferService.transfer(transferOperation);
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
