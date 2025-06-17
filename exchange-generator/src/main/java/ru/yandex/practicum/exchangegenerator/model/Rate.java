package ru.yandex.practicum.exchangegenerator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import ru.yandex.practicum.exchangegenerator.enums.Currency;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Rate {

    Currency currency;
    Double value;
}
