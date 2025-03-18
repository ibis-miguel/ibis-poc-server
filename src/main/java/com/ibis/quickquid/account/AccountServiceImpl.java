package com.ibis.quickquid.account;

import com.ibis.quickquid.person.Person;
import com.ibis.quickquid.person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final PersonRepository personRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, PersonRepository personRepository) {
        this.accountRepository = accountRepository;
        this.personRepository = personRepository;

    }

    @Override
    public Account saveAccount(CreateAccountDto accountDto) {

        Person owner = personRepository.findByFirstNameAndLastName(accountDto.firstName(), accountDto.lastName());
        if (owner == null) {
            owner = personRepository.save(
                    Person.builder()
                            .firstName(accountDto.firstName())
                            .lastName(accountDto.lastName())
                            .build());
        }

        return accountRepository.save(Account.builder()
                .accountNumber(accountDto.accountNumber())
                .accountType(accountDto.accountType())
                .createdAt(accountDto.createdAt())
                .currency(accountDto.currency())
                .balance(accountDto.balance())
                .bankBranch(accountDto.bankName())
                .owner(owner)
                .build());
    }
}
