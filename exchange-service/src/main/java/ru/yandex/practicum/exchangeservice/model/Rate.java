package ru.yandex.practicum.exchangeservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.yandex.practicum.exchangeservice.enums.Currency;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Rate {

    Currency currency;
    Double value;
}
