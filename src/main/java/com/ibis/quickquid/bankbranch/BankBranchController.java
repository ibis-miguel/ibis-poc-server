package com.ibis.quickquid.bankbranch;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
public class BankBranchController {

    private final BankBranchService bankBranchService;

    @Autowired
    public BankBranchController(BankBranchService bankBranchService){
        this.bankBranchService = bankBranchService;
    }

    @PostMapping
    public ResponseEntity<BankBranch> createBankBranch(@RequestBody BankBranch bankBranch){
        BankBranch savedBankBranch = bankBranchService.saveBankBranch(bankBranch);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBankBranch);
    }

    @GetMapping("/all")
    public ResponseEntity<List<BankBranch>> getAllBanks(){
        List<BankBranch> bankBranches = bankBranchService.getAllBanks();
        return ResponseEntity.ok(bankBranches);
    }
}
