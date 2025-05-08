package com.bank.loanServiceBackend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.loanServiceBackend.model.Customer;
import com.bank.loanServiceBackend.model.Loan;
import com.bank.loanServiceBackend.repo.CustomerRepository;
import com.bank.loanServiceBackend.repo.LoanRepository;
import com.bank.loanServiceBackend.service.LoanService;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;
      @Autowired
      private CustomerRepository customerRepository;
      @Autowired
      private LoanRepository loanRepository;
    @PostMapping("/apply")
    public Loan applyForLoan(@RequestBody Loan loan) {
    	Loan existingLoan = loanService.applyForLoan(loan);
        return existingLoan;
    }
    
    @GetMapping("/customers/{id}/loan-history")
    public List<Loan> getLoanHistory(@PathVariable Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer == null) return new ArrayList<>();

        return loanRepository.findByCustomer(customer);
    }
    @GetMapping("/loans/pending")
    public List<Loan> getPendingLoans() {
        return loanRepository.findByStatusNot("repaid");
    }
   

    @GetMapping("/loans/overdue")
    public List<Loan> getOverdueLoans() {
        return loanService.getOverdueLoans();
    }

    @GetMapping("/admin/dashboard")
    public Map<String, Long> getDashboard() {
        return loanService.getDashboardStats();
    }

    @GetMapping("/loans/status/{status}")
    public List<Loan> getLoansByStatus(@PathVariable String status) {
        return loanRepository.findByStatus(status);
    }


}
