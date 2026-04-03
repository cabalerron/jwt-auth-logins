package com.example.base.service;

import java.util.Optional;

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
        employee.setPassword(passwordEncoder.encode(employee.getPassword())); // hash password
        return repository.save(employee);
    }

    public Optional<Employee> authenticate(String username, String rawPassword) {
        Optional<Employee> employee = repository.findByUsername(username);
        if (employee.isPresent() && passwordEncoder.matches(rawPassword, employee.get().getPassword())) {
            return employee;
        }
        return Optional.empty();
    }
}