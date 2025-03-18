package com.ibis.quickquid.transaction;


import com.ibis.quickquid.bankbranch.BankBranch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController (TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody CreateTransactionDto transaction){
        Transaction savedTransaction = transactionService.saveTransaction(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTransaction);
    }

    @GetMapping("/account")
    public ResponseEntity<List<DisplayTransactionDto>> getAllTransactionsByAccount(@RequestParam String accountNumber) {
        List<DisplayTransactionDto> transactions = transactionService.findAllTransactionsByAccount(accountNumber);
        return ResponseEntity.ok(transactions);
    }

}
