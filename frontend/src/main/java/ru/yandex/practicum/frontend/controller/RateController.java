package ru.yandex.practicum.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.yandex.practicum.frontend.model.Rate;

import java.util.List;

@Controller
public class RateController {

    @GetMapping("/rates")
    public List<Rate> getRates() {
        // TODO Здесь должна быть логика получения курсов валют
        return List.of(
                new Rate("Рубль", "RUB", 1),
                new Rate("Доллар США", "USD", 75.50),
                new Rate("Юань", "CNY", 11.20)
        );
    }
}
