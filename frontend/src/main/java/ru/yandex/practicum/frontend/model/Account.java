package ru.yandex.practicum.frontend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.yandex.practicum.frontend.enums.Currency;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private Currency currency;
    private boolean exists;
    private double value;
}
