package ru.yandex.practicum.notificationservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class NotificationController {

    @GetMapping("/auth")
    public ResponseEntity<?> auth() {
        System.out.println("Регистрация прошла успешно");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/cash")
    public ResponseEntity<?> cash() {
        System.out.println("Операция с наличными прошла успешно");
        return ResponseEntity.ok().build();
    }
}
