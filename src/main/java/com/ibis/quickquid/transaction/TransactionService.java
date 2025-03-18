package com.ibis.quickquid.transaction;

import com.ibis.quickquid.account.Account;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {
    List<Transaction> findAllBySender(Account senderAccountNumber);
    List<Transaction> findAllByReceiver(Account receiverAccountNumber);
    Transaction saveTransaction(CreateTransactionDto transaction);
    List<DisplayTransactionDto> findAllTransactionsByAccount(String accountNumber);
}
