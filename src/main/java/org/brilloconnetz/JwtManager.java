package org.brilloconnetz;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JwtManager {
    static Dotenv dotenv = Dotenv.configure().load();
    private static final String SECRET_KEY = dotenv.get("JWT_SECRET_KEY");

    public static String generateToken(User user) {
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        return Jwts.builder()
                .claim("username", user.getUsername())
                .claim("email", user.getEmail())
                .claim("password", user.getPassword())
                .claim("dateOfBirth", user.getDateOfBirth())
                .signWith(key)
                .compact();
    }

    public static boolean verifyToken(String token) {
        try {
            Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void processToken(User user) {
        String jwt = generateToken(user);

        boolean verificationResult = verifyToken(jwt);
        System.out.println("-------------------");
        if (verificationResult) {
            System.out.println("Verification passed");
        } else {
            System.out.println("Verification failed");
        }
        System.out.println("-------------------");
    }
}