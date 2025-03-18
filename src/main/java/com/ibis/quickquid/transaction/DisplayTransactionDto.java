package com.ibis.quickquid.transaction;

import com.ibis.quickquid.enums.Status;

import java.util.Date;

public record DisplayTransactionDto(
        Double amount,
        String sender,
        String receiver,
        String bank,
        Date date,
        String description,
        Status status
) {
}
