package org.brilloconnetz;

import org.brilloconnetz.input_validation_app.util.JwtUtil;
import org.brilloconnetz.input_validation_app.domain.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class JwtUtilTest {

    @Test
    public void testValidTokenVerification() {
        // Generate a valid token for testing
        User user = new User("edirin", "edirin@gmail.com", "Edirin123!", LocalDate.now());
        String jwt = JwtUtil.generateToken(user);

        // Verify the token
        assertTrue(JwtUtil.verifyToken(jwt));
    }

    @Test
    public void testInvalidTokenVerification() {
        // Generate an invalid token for testing
        String invalidToken = "invalid.token.string";

        // Verify the token
        assertFalse(JwtUtil.verifyToken(invalidToken));
    }
}
