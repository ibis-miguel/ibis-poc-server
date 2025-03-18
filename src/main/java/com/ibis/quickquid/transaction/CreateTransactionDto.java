package com.ibis.quickquid.transaction;

import com.ibis.quickquid.bankbranch.BankBranch;
import com.ibis.quickquid.enums.Status;

import java.util.Date;

public record CreateTransactionDto(
        Double amount,
        Date transactionDate,
        String description,
        String senderAccount,
        String receiverAccount,
        Status Status,
        BankBranch originatingBranch
) {
}
