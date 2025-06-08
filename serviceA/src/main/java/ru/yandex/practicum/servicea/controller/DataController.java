package ru.yandex.practicum.servicea.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
public class DataController {

    private final RestTemplate restTemplate;

    @GetMapping("/get-data")
    public String getData() {
        // Отправляем запрос через Gateway (порт 8080) к сервису B
        String url = "http://localhost:8080/api/hello";
        String responseBody = restTemplate.getForObject(url, String.class);
        return "Ответ от сервиса B: " + responseBody;
    }
}
