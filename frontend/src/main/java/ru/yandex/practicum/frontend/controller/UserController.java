package ru.yandex.practicum.frontend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.yandex.practicum.frontend.enums.Currency;
import ru.yandex.practicum.frontend.service.CashService;
import ru.yandex.practicum.frontend.service.TransferService;

@Controller
@RequestMapping("/user/{login}")
@RequiredArgsConstructor
public class UserController {

    private final CashService cashService;
    private final TransferService transferService;

    @PostMapping("/cash")
    public String handleCashOperation(@PathVariable String login,
                                      @RequestParam Currency currency,
                                      @RequestParam double value,
                                      @RequestParam String action,
                                      Model model) {
        String error = cashService.handleCashOperation(currency, value, action);
        model.addAttribute("cashErrors", error);
        return "redirect:/main";
    }

    @PostMapping("/cash")
    public String handleTransferOperation(@PathVariable String login,
                                          @RequestParam String from_currency,
                                          @RequestParam String to_currency,
                                          @RequestParam double value,
                                          @RequestParam String to_login,
                                          Model model) {
        String error = transferService.handleTransferOperation(from_currency, to_currency, value, to_login);
        model.addAttribute("transferErrors", error);
        return "redirect:/main";
    }
}
