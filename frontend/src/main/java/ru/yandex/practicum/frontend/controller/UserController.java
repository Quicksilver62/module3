package ru.yandex.practicum.frontend.controller;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.yandex.practicum.frontend.enums.Currency;

@Controller
@RequestMapping("/user/{login}")
public class UserController {

    @PostMapping("/account")
    public String createAccount(
            @PathVariable String login,
            @RequestParam String currency,
            Model model,
            OAuth2AuthenticationToken authentication
    ) {
        OAuth2User principal = authentication.getPrincipal();

        String username = principal.getAttribute("preferred_username");
        String name = principal.getAttribute("name");
        String birthdate = principal.getAttribute("birthdate");

        model.addAttribute("login", username);
        model.addAttribute("name", name);
        model.addAttribute("birthdate", birthdate);
        model.addAttribute("currency", Currency.values());

        return "main";
    }
}
