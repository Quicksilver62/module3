package ru.yandex.practicum.propertyprinter.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.bus.BusProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/texts")
@RefreshScope
public class PrinterController {

    @Value("${text-to-print}")
    private String textToPrint;

    private final BusProperties busProps;

    public PrinterController(BusProperties busProps) {
        this.busProps = busProps;
    }

    @PostConstruct
    public void init() {
        System.out.println("Приложению присвоен busId : " + busProps.getId());
    }

    @GetMapping
    public String getText() {
        var message = busProps.getId() + ":\n" + textToPrint;
        System.out.println(message);
        return message;
    }
}
