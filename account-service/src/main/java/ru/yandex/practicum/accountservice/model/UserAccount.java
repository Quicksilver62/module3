package ru.yandex.practicum.accountservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.yandex.practicum.accountservice.enums.Currency;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAccount {

    @Id
    private Integer id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private Currency currency;

    private Boolean exists;

    private Double value;
}
