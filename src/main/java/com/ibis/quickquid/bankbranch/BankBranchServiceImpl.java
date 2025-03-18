package com.ibis.quickquid.bankbranch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankBranchServiceImpl implements BankBranchService{
    private final BankBranchRepository bankBranchRepository;

    @Autowired
    public BankBranchServiceImpl(BankBranchRepository bankBranchRepository){
        this.bankBranchRepository = bankBranchRepository;
    }

    @Override
    public BankBranch saveBankBranch(BankBranch bankBranch) {
        return bankBranchRepository.save(bankBranch);
    }

    @Override
    public List<BankBranch> getAllBanks() {
        return bankBranchRepository.findAll();
    }
}
