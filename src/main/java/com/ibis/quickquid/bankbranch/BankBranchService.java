package com.ibis.quickquid.bankbranch;

import java.util.List;

public interface BankBranchService {
    BankBranch saveBankBranch(BankBranch bankBranch);
    List<BankBranch> getAllBanks();
}
