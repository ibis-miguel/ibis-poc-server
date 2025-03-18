package com.ibis.quickquid.transaction;

import com.ibis.quickquid.account.Account;
import com.ibis.quickquid.account.AccountController;
import com.ibis.quickquid.account.AccountRepository;
import com.ibis.quickquid.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }


    @Override
    public List<Transaction> findAllBySender(Account senderAccount) {
        return transactionRepository.findAllBySenderAccount(senderAccount);
    }

    @Override
    public List<Transaction> findAllByReceiver(Account receiverAccount) {
        return transactionRepository.findAllByReceiverAccount(receiverAccount);
    }

    @Override
    public Transaction saveTransaction(CreateTransactionDto transaction) {
        Account sender = accountRepository.findByAccountNumber(transaction.senderAccount());
        Account receiver = accountRepository.findByAccountNumber(transaction.receiverAccount());

        if (sender == null) {
            throw new IllegalArgumentException("Sender account not found.");
        }
        if (receiver == null) {
            throw new IllegalArgumentException("Receiver account not found.");
        }

        if (transaction.amount() <= 0) {
            throw new IllegalArgumentException("Transaction amount must be greater than zero.");
        }
        Status status = sender.getBalance() < transaction.amount() ? Status.FAILED : Status.COMPLETED;

        if (status == Status.COMPLETED) {
            sender.setBalance(sender.getBalance() - transaction.amount());
            receiver.setBalance(receiver.getBalance() + transaction.amount());

            accountRepository.save(sender);
            accountRepository.save(receiver);

        } else if (status == Status.FAILED) {
            throw new IllegalArgumentException("Insufficient funds in sender account.");
        }

        try {
            return transactionRepository.save(
                    Transaction.builder()
                            .amount(transaction.amount())
                            .transactionDate(new Date())
                            .description(transaction.description())
                            .senderAccount(sender)
                            .receiverAccount(receiver)
                            .status(status)
                            .originatingBranch(transaction.originatingBranch())
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException("Transaction failed due to an error in processing the accounts.", e);
        }
    }

    @Override
    public List<DisplayTransactionDto> findAllTransactionsByAccount(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);

        return transactionRepository.findAllTransactionsByAccount(account).stream().map(
                transaction -> {
                    return new DisplayTransactionDto(
                            transaction.getAmount(),
                            transaction.getSenderAccount().getOwner().getFirstName() + " " + transaction.getSenderAccount().getOwner().getLastName(),
                            transaction.getReceiverAccount().getOwner().getFirstName() + " " + transaction.getReceiverAccount().getOwner().getLastName(),
                            transaction.getOriginatingBranch().getBankName() + " " + transaction.getOriginatingBranch().getBranchName(),
                            transaction.getTransactionDate(),
                            transaction.getDescription(),
                            transaction.getStatus()
                    );
                }
        ).collect(Collectors.toList());
    }
}
