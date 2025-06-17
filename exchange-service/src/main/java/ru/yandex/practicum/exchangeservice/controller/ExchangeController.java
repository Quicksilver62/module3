package ru.yandex.practicum.exchangeservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.exchangeservice.model.TransferOperation;
import ru.yandex.practicum.exchangeservice.service.ExchangeService;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class ExchangeController {

    private final ExchangeService exchangeService;

    @GetMapping("/exchange")
    public Double calculateExchangePrice(@RequestBody TransferOperation transferOperation) {
        return exchangeService.calculateExchangePrice(transferOperation);
    }
}
