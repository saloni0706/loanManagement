package com.bank.loanServiceBackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.loanServiceBackend.model.Loan;
import com.bank.loanServiceBackend.model.Repayment;
import com.bank.loanServiceBackend.repo.LoanRepository;
import com.bank.loanServiceBackend.repo.RepaymentRepository;

@Service
public class RepaymentServiceImpl implements RepaymentService {
	@Autowired
	private RepaymentRepository repaymentRepository;

	@Autowired
	private LoanRepository loanRepository;

	@Override
	public Repayment makeRepayment(Repayment repayment) {
	    Loan loan = loanRepository.findById(repayment.getLoan().getId())
	                              .orElseThrow(() -> new RuntimeException("Loan not found"));

	    
	    double newRemaining = loan.getRemainingAmount() - repayment.getAmount();
	    loan.setRemainingAmount(newRemaining);

	    if (newRemaining <= 0) {
	        loan.setStatus("repaid");
	    }

	    loanRepository.save(loan);

	    repayment.setLoan(loan); 
	    return repaymentRepository.save(repayment);
	}

}
