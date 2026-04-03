package com.example.base;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.example.base.security.JwtUtil;

class JwtUtilTest {

    JwtUtil jwtUtil = new JwtUtil();

    @Test
    void testGenerateAndValidateToken() {
        // Generate token for username "erron"
        String token = jwtUtil.generateToken("erron");
        assertNotNull(token, "Token should not be null");

        // Extract username
        String username = jwtUtil.extractUsername(token);
        assertEquals("erron", username, "Extracted username should match");

        // Validate token
        boolean isValid = jwtUtil.validateToken(token, "erron");
        assertTrue(isValid, "Token should be valid for correct username");

        // Validate with wrong username
        boolean isValidWrong = jwtUtil.validateToken(token, "wronguser");
        assertFalse(isValidWrong, "Token should be invalid for wrong username");
    }

    @Test
    void testExpiredToken() throws InterruptedException {
        // You can modify JwtUtil to allow short expiration for testing
        // e.g., 1 second
    }
}