package com.bank.loanServiceBackend.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.loanServiceBackend.config.JwtUtil;
import com.bank.loanServiceBackend.dto.AuthRequest;
import com.bank.loanServiceBackend.dto.AuthResponse;
import com.bank.loanServiceBackend.model.Customer;
import com.bank.loanServiceBackend.service.CustomerService;

@RestController
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private JwtUtil jwtUtil;

	//saving user
	@PostMapping("/addUser")
	public Customer saveUser(@RequestBody Customer customer) {
		Customer savedCustomer = customerService.saveCustomer(customer);
		return savedCustomer;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthRequest request) {
		authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		String token = jwtUtil.generateToken(request.getUsername());
		return ResponseEntity.ok(new AuthResponse(token));
	}

	@GetMapping("/customers/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
		Optional<Customer> customer = customerService.getCustomerById(id);

		if (customer.isPresent()) {
			return new ResponseEntity<>(customer.get(), HttpStatus.OK);  
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);           
		}
	}

	@PutMapping("/customers/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
		Optional<Customer> existingCustomer = customerService.getCustomerById(id);

		if (existingCustomer.isPresent()) {
			Customer customer = existingCustomer.get();
			customer.setName(updatedCustomer.getName());
			customer.setEmail(updatedCustomer.getEmail());

			customer.setPhone(updatedCustomer.getPhone());


			Customer savedCustomer = customerService.saveCustomer(customer);
			return ResponseEntity.ok(savedCustomer);
		} else {
			return ResponseEntity.notFound().build();
		}
	}




}
