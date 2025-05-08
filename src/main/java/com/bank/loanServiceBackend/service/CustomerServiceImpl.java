package com.bank.loanServiceBackend.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.bank.loanServiceBackend.model.Customer;
import com.bank.loanServiceBackend.model.Loan;
import com.bank.loanServiceBackend.model.Repayment;
import com.bank.loanServiceBackend.repo.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService, UserDetailsService {
     @Autowired
     private CustomerRepository repo;
 	@Autowired
 	private BCryptPasswordEncoder passwordEncoder;
 	
     @Override
     public Customer saveCustomer(Customer customer) {
         if (repo.existsByEmail(customer.getEmail())) {
             throw new RuntimeException("Email already exists");
         }
         //encoding password
         String password = customer.getPassword();
         String encodedPassword = passwordEncoder.encode(password);
         customer.setPassword(encodedPassword);
         
         return repo.save(customer);
     }
     
    
     @Override
     public UserDetails loadUserByUsername(String email) {

         Customer user = repo.findByEmail(email)
                 .orElseThrow(() -> new UsernameNotFoundException("User not found"));

         return org.springframework.security.core.userdetails.User
                 .withUsername(user.getEmail())
                 .password(user.getPassword())
                 .roles(user.getRole())
                 .build();
     }

	@Override
	public Optional<Customer> getCustomerById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
