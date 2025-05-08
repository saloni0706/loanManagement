package com.bank.loanServiceBackend.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.loanServiceBackend.model.Customer;
import com.bank.loanServiceBackend.model.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> 
 {
	List<Loan> findByCustomer(Customer customer);
	List<Loan> findByStatusNot(String status);
	long countByStatus(String status);
	List<Loan> findByStatus(String status);

}
