package org.brilloconnetz;

import org.brilloconnetz.JwtManager;
import org.brilloconnetz.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class JwtManagerTest {

    @Test
    public void testValidTokenVerification() {
        // Generate a valid token for testing
        User user = new User("edirin", "edirin@gmail.com", "Edirin123!", LocalDate.now());
        String jwt = JwtManager.generateToken(user);

        // Verify the token
        assertTrue(JwtManager.verifyToken(jwt));
    }

    @Test
    public void testInvalidTokenVerification() {
        // Generate an invalid token for testing
        String invalidToken = "invalid.token.string";

        // Verify the token
        assertFalse(JwtManager.verifyToken(invalidToken));
    }
}