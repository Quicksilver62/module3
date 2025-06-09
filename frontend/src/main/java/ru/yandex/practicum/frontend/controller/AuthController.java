package ru.yandex.practicum.frontend.controller;

import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.yandex.practicum.frontend.service.AuthService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String registerUser(
            @RequestParam String login,
            @RequestParam String password,
            @RequestParam String confirm_password,
            @RequestParam String name,
            @RequestParam String birthdate,
            Model model) {

        List<String> errors = authService.credentialValidation(login, password, confirm_password, name, birthdate);

        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            model.addAttribute("login", login);
            model.addAttribute("name", name);
            model.addAttribute("birthdate", birthdate);
            return "signup";
        }

        try {
            Response response = authService.keycloakSignUp(login, password, name, birthdate);

            if (response.getStatus() == 201) {
                model.addAttribute("login", login);
                model.addAttribute("name", name);
                model.addAttribute("birthdate", birthdate);
                return "main";
            } else {
                errors.add("Ошибка регистрации: " + response.getStatusInfo().getReasonPhrase());
                model.addAttribute("errors", errors);
                return "signup";
            }
        } catch (Exception e) {
            errors.add("Ошибка регистрации: " + e.getMessage());
            model.addAttribute("errors", errors);
            return "signup";
        }
    }
}
