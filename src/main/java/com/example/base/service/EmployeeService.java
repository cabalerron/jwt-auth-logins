package com.example.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.base.model.Employee;
import com.example.base.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Employee register(Employee employee) {
        if (repository.findByUsername(employee.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        return repository.save(employee);
    }

    public Employee authenticate(String username, String rawPassword) {

        // Try to find the employee by username
        Employee employee = repository.findByUsername(username)
                // If not found, throw RuntimeException to trigger your handler
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        // Check if password matches
        if (!passwordEncoder.matches(rawPassword, employee.getPassword())) {
            // If password is incorrect, throw RuntimeException
            throw new RuntimeException("Invalid username or password");
        }

        // Login successful
        return employee;
    }
}
