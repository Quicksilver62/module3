package ru.yandex.practicum.cashservice.model;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Transaction {
    private String id;
    private String userId;
    private BigDecimal amount;
    private TransactionType type;
    private TransactionStatus status;
    private String errorMessage;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
} 