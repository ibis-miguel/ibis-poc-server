package com.ibis.quickquid.configs;


import com.ibis.quickquid.account.AccountController;
import com.ibis.quickquid.account.AccountRepository;
import com.ibis.quickquid.account.AccountService;
import com.ibis.quickquid.account.AccountServiceImpl;
import com.ibis.quickquid.bankbranch.BankBranchController;
import com.ibis.quickquid.bankbranch.BankBranchRepository;
import com.ibis.quickquid.bankbranch.BankBranchService;
import com.ibis.quickquid.bankbranch.BankBranchServiceImpl;
import com.ibis.quickquid.person.PersonController;
import com.ibis.quickquid.person.PersonRepository;
import com.ibis.quickquid.person.PersonService;
import com.ibis.quickquid.person.PersonServiceImpl;
import com.ibis.quickquid.transaction.TransactionController;
import com.ibis.quickquid.transaction.TransactionRepository;
import com.ibis.quickquid.transaction.TransactionService;
import com.ibis.quickquid.transaction.TransactionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    @Bean
    public TransactionServiceImpl transactionService(TransactionRepository transactionRepository, AccountRepository accountRepository){
        return new TransactionServiceImpl(transactionRepository, accountRepository);
    }

    @Bean
    public TransactionController transactionController(TransactionService transactionService){
        return new TransactionController(transactionService);
    }

    @Bean
    public PersonServiceImpl personService(PersonRepository personRepository){
        return new PersonServiceImpl(personRepository);
    }

    @Bean
    public PersonController personController(PersonService personService){
        return new PersonController(personService);
    }

    @Bean
    public AccountServiceImpl accountService(AccountRepository accountRepository, PersonRepository personRepository){
        return new AccountServiceImpl(accountRepository, personRepository);
    }

    @Bean
    public AccountController accountController(AccountService accountService){
        return new AccountController(accountService);
    }

    @Bean
    public BankBranchServiceImpl bankBranchService(BankBranchRepository bankBranchRepository){
        return new BankBranchServiceImpl(bankBranchRepository);
    }

    @Bean
    public BankBranchController bankBranchController(BankBranchService bankBranchService){
        return new BankBranchController(bankBranchService);
    }
}
