package com.example.base.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.base.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByUsername(String username);
}