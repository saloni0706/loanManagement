package com.bank.loanServiceBackend.service;

import java.util.Optional;

import com.bank.loanServiceBackend.model.Customer;

public interface CustomerService {
public Customer saveCustomer(Customer customer);
Optional<Customer> getCustomerById(Long id);
}
