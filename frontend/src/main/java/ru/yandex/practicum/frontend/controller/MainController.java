package ru.yandex.practicum.frontend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.frontend.enums.Currency;
import ru.yandex.practicum.frontend.service.AccountService;
import ru.yandex.practicum.frontend.service.CashService;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final AccountService accountService;
    private final CashService cashService;

    @GetMapping("/main")
    public String mainPage(Model model, OAuth2AuthenticationToken authentication) {
        OAuth2User principal = authentication.getPrincipal();

        String username = principal.getAttribute("preferred_username");
        String name = principal.getAttribute("name");
        String birthdate = principal.getAttribute("birthdate");

        model.addAttribute("login", username);
        model.addAttribute("name", name);
        model.addAttribute("birthdate", birthdate);
        model.addAttribute("currency", Currency.values());

        var account = accountService.getAccounts();

        model.addAttribute("accounts", account);

        return "main";
    }

    @PostMapping("/cash")
    public String handleCashOperation(@RequestParam Currency currency,
                                      @RequestParam double value,
                                      @RequestParam String action,
                                      Model model) {
        String error = cashService.handleCashOperation(currency, value, action);
        model.addAttribute("cashErrors", error);
        return "redirect:/main";
    }
}
