package ru.yandex.practicum.frontend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.frontend.enums.Currency;
import ru.yandex.practicum.frontend.service.AccountService;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final AccountService accountService;

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

//    @GetMapping
//    public String getUserPage(@PathVariable String login, Model model) {
//        model.addAttribute("login", login);
//        model.addAttribute("name", name);
//        model.addAttribute("birthdate", birthdate);
//        model.addAttribute("accounts", accounts);
//        model.addAttribute("currency", Currency.values());
//        model.addAttribute("users", users);
//
//        return "main";
//    }
//
//    @PostMapping("/editPassword")
//    public String editPassword(
//            @PathVariable String login,
//            @RequestParam String password,
//            @RequestParam String confirmPassword,
//            Model model
//    ) {
//        model.addAttribute("passwordErrors", errors);
//        return "redirect:/user/" + login;
//    }
//
//    @PostMapping("/editUserAccounts")
//    public String editUserAccounts(
//            @PathVariable String login,
//            @RequestParam(required = false) String name,
//            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthdate,
//            @RequestParam(required = false) List<Currency> account,
//            Model model
//    ) {
//        // TODO Логика обновления данных пользователя и счетов
//        return "redirect:/user/" + login;
//    }
//
//    @PostMapping("/cash")
//    public String handleCashOperation(
//            @PathVariable String login,
//            @RequestParam Currency currency,
//            @RequestParam double value,
//            @RequestParam String action,
//            Model model
//    ) {
//        // TODO Логика пополнения счета
//        model.addAttribute("cashErrors", errors);
//        return "redirect:/user/" + login;
//    }
//
//    @PostMapping("/transfer")
//    public String handleTransfer(
//            @PathVariable String login,
//            @RequestParam Currency from_currency,
//            @RequestParam Currency to_currency,
//            @RequestParam double value,
//            @RequestParam(required = false) String to_login,
//            Model model
//    ) {
//        model.addAttribute("transferErrors", errors);
//        model.addAttribute("transferOtherErrors", errors);
//        return "redirect:/user/" + login;
//    }
}
