package ru.yandex.practicum.accountservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.yandex.practicum.accountservice.model.UserAccount;
import ru.yandex.practicum.accountservice.model.dto.AccountDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {

    AccountDto toAccountDto(UserAccount account);
}
