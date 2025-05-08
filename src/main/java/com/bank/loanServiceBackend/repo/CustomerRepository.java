package com.bank.loanServiceBackend.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.loanServiceBackend.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	// Add custom query methods if needed
	// For example:
	// List<Customer> findByLastName(String lastName);
	// Optional<Customer> findByEmail(String email);

	boolean existsByEmail(String email);

	Optional<Customer> findByEmail(String email);
}
