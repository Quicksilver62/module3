package ru.yandex.practicum.blockerservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/api/")
public class BlockerController {

    @GetMapping("/isBlocked")
    public Boolean isBlocked() {
        return new Random().nextBoolean();
    }
}
