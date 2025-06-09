package ru.yandex.practicum.accountservice.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Currency {

    RUB("Рубль"),
    USD("Доллар США"),
    CNY("Юань");

    private final String title;
}
