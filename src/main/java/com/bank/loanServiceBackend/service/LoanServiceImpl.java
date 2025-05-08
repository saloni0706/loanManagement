package com.bank.loanServiceBackend.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.loanServiceBackend.model.Loan;
import com.bank.loanServiceBackend.repo.LoanRepository;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public Loan applyForLoan(Loan loan) {
       
        if (loan.getLoanAmount() <= 500000) {
            loan.setStatus("approved");
        } else {
            loan.setStatus("rejected");
            }      
        double interest = (loan.getLoanAmount() * loan.getInterestRate() * loan.getDurationMonths()) / (100 * 12);
        loan.setTotalInterest(interest);
        loan.setTotalPayableAmount(loan.getLoanAmount() + interest);

        return loanRepository.save(loan);
    }
    
    
    public List<Loan> getOverdueLoans() {
        List<Loan> allLoans = loanRepository.findAll();
        List<Loan> overdueLoans = new ArrayList<>();

        for (Loan loan : allLoans) {
            LocalDate dueDate = loan.getCreatedAt().plusMonths(loan.getDurationMonths());
            if (LocalDate.now().isAfter(dueDate) && loan.getRemainingAmount() > 0) {
                loan.setStatus("overdue");
                loanRepository.save(loan);
                overdueLoans.add(loan);
            }
        }
        return overdueLoans;
    }
    
   
    public Map<String, Long> getDashboardStats() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("totalLoans", loanRepository.count());
        stats.put("approvedLoans", loanRepository.countByStatus("approved"));
        stats.put("repaidLoans", loanRepository.countByStatus("repaid"));
        stats.put("overdueLoans", loanRepository.countByStatus("overdue"));
        return stats;
    }


}

