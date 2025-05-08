package com.bank.loanServiceBackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.loanServiceBackend.model.Repayment;

public interface RepaymentRepository extends JpaRepository<Repayment, Long> {
	// Custom query methods can be defined here if needed

}
