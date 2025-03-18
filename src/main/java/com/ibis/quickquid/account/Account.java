package com.ibis.quickquid.account;

import com.ibis.quickquid.bankbranch.BankBranch;
import com.ibis.quickquid.enums.AccountType;
import com.ibis.quickquid.person.Person;
import jakarta.persistence.*;
import lombok.*;

import java.util.Currency;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String accountNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(nullable = false)
    private Date createdAt;

    @Column(nullable = false)
    private Currency currency;

    @Column(nullable = false)
    private Double balance;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person owner;

    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    private BankBranch bankBranch;

}
