package ru.yandex.practicum.blockerservice.controller;

import java.util.Random;

@RestController
@RequestMapping("/api/")
public class BlockerController {

    @GetMapping("/isBlocked")
    public Boolean createAccount() {
        return new Random().nextBoolean();
    }
}
