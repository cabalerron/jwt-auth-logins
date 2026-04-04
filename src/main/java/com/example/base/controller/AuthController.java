package com.example.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.base.dto.AuthRequest;
import com.example.base.dto.AuthResponse;
import com.example.base.model.Employee;
import com.example.base.security.JwtUtil;
import com.example.base.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JwtUtil jwtUtil;

    // Registration endpoint
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody AuthRequest request) {
        Employee employee = new Employee();
        employee.setUsername(request.getUsername());
        employee.setPassword(request.getPassword()); // plain, service will hash
        Employee saved = employeeService.register(employee);

        return ResponseEntity.ok("Employee registered successfully: " + saved.getUsername());
    }

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {

        // Authenticate the user; RuntimeException will be thrown if login fails
        Employee emp = employeeService.authenticate(request.getUsername(), request.getPassword());

        // Generate JWT token after successful login
        String token = jwtUtil.generateToken(emp.getUsername());

        // Return token in response
        return ResponseEntity.ok(new AuthResponse(token, null));
    }
}
