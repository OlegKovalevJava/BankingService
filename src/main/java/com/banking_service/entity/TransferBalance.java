package com.banking_service.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferBalance {

    private Long senderId;
    private Long recipientId;
    private BigDecimal amount;
}
