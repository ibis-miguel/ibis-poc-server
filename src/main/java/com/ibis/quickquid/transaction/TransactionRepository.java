package com.ibis.quickquid.transaction;

import com.ibis.quickquid.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllBySenderAccount(Account senderAccount);
    List<Transaction> findAllByReceiverAccount(Account receiverAccount);

    @Query("SELECT t FROM Transaction t WHERE t.senderAccount = :account OR t.receiverAccount = :account")
    List<Transaction> findAllTransactionsByAccount(@Param("account") Account account);

}
