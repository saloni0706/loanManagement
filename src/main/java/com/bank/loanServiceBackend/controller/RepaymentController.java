package com.bank.loanServiceBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bank.loanServiceBackend.model.Repayment;
import com.bank.loanServiceBackend.service.RepaymentService;

@RestController
@RequestMapping("/repayments")
public class RepaymentController {

    @Autowired
    private RepaymentService repaymentService;

    @PostMapping("/make")
    public Repayment makeRepayment(@RequestBody Repayment repayment) {
        return repaymentService.makeRepayment(repayment);
    }
}
