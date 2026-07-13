package com.example.MyProject11.util;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    // Secret key (minimum 32 characters)
    private static final String SECRET =
            "mysecretkeymysecretkeymysecretkey12345";

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    // Generate JWT Token
    public String generateToken(String username) {

        return Jwts.builder()

                .subject(username)

                .issuedAt(new Date())

                .expiration(new Date(System.currentTimeMillis()
                        + 1000 * 60 * 60)) // 1 hour

                .signWith(getSignKey(), SignatureAlgorithm.HS256)

                .compact();
    }

    // Extract Username
    public String extractUsername(String token) {

        return extractClaims(token).getSubject();

    }

    // Extract Expiry Date
    public Date extractExpiration(String token) {

        return extractClaims(token).getExpiration();

    }

    // Extract Claims
    private Claims extractClaims(String token) {

        return Jwts.parser()

                .verifyWith((javax.crypto.SecretKey) getSignKey())

                .build()

                .parseSignedClaims(token)

                .getPayload();

    }

    // Token Expired?
    public boolean isTokenExpired(String token) {

        return extractExpiration(token).before(new Date());

    }

    // Validate Token
    public boolean validateToken(String token,
                                 String username) {

        return username.equals(extractUsername(token))
                && !isTokenExpired(token);

    }

}