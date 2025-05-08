package com.bank.loanServiceBackend.service;

import java.util.List;

import java.util.Map;
import com.bank.loanServiceBackend.model.Loan;

public interface LoanService {
	public  Loan applyForLoan(Loan loan);

	public List<Loan> getOverdueLoans();

	public Map<String, Long> getDashboardStats();
}
