package ru.yandex.practicum.exchangegenerator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.exchangegenerator.model.Rate;
import ru.yandex.practicum.exchangegenerator.service.RateService;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class GeneratorController {

    private final RateService rateService;

    @GetMapping("/rates")
    public List<Rate> getRates() {
        return rateService.getRates();
    }
}
