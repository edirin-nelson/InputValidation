package org.brilloconnetz;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JwtManager {
    static Dotenv dotenv = Dotenv.configure().load();
    private static String SECRET_KEY = dotenv.get("JWT_SECRET_KEY");

    /**
     * Generates a JWT token with user claims.
     *
     * @param user The user whose information will be included in the token.
     * @return The generated JWT token.
     */
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

    /**
     * Verifies the authenticity of a JWT token.
     *
     * @param token The JWT token to be verified.
     * @return true if the token is valid, false if it's not valid or an exception occurs.
     */
    public boolean verifyToken(String token) {
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
}

