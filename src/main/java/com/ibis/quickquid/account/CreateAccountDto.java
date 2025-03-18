package com.ibis.quickquid.account;

import com.ibis.quickquid.bankbranch.BankBranch;
import com.ibis.quickquid.enums.AccountType;

import java.util.Currency;
import java.util.Date;

public record CreateAccountDto(
        String accountNumber,
        AccountType accountType,
        Date createdAt,
        Currency currency,
        String firstName,
        String lastName,
        BankBranch bankName,
        Double balance
) {
}
