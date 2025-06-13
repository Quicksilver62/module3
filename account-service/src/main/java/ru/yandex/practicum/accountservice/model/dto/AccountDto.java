package ru.yandex.practicum.accountservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.yandex.practicum.accountservice.enums.Currency;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    private Currency currency;
    private boolean exists;
    private double value;
}
