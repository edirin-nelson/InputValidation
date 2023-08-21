package org.brilloconnetz.inputValidationApp.util;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.brilloconnetz.inputValidationApp.domain.User;

import java.security.Key;

public class JwtUtil {
    static Dotenv dotenv = Dotenv.configure().load();
    private static final String SECRET_KEY = dotenv.get("JWT_SECRET_KEY");

    // Generate a JWT token with user claims
    public static String generateToken(User user) {
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes()); // Create a key using the secret key

        return Jwts.builder()
                .claim("username", user.getUsername())
                .claim("email", user.getEmail())
                .claim("password", user.getPassword())
                .claim("dateOfBirth", user.getDateOfBirth())
                .signWith(key) // Sign the token with the key
                .compact();
    }

    // Verify the authenticity of a JWT token
    public static boolean verifyToken(String token) {
        try {
            Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes()); // Create a key using the secret key

            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token); // Parse and verify the token's claims

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Process a token by generating and verifying it, and printing the result
    public static String processToken(User user) {
        String jwt = generateToken(user);

        boolean verificationResult = verifyToken(jwt);

        // Return the verification result as a string
        if (verificationResult) {
            return "Verification passed";
        } else {
            return "Verification failed";
        }
    }
}