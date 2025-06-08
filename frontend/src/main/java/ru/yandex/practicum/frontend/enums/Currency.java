package ru.yandex.practicum.frontend.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Currency {

    USD("Доллар США"),
    EUR("Евро"),
    GBP("Фунт стерлингов");

    private final String title;
}
