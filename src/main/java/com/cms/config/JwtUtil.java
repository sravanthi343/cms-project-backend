package com.cms.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
<<<<<<< HEAD

=======
>>>>>>> 06559c1 (Initial commit for Render deployment)
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
<<<<<<< HEAD

    @Value("${jwt.secret:cms-jwt-secret-key-college-management-2024-SECURE-LONG-KEY}")
=======
    @Value("${jwt.secret:cms-jwt-secret-key-college-2024-secure}")
>>>>>>> 06559c1 (Initial commit for Render deployment)
    private String secret;

    @Value("${jwt.expiration:86400000}")
    private long expiration;

<<<<<<< HEAD
    private Key signingKey() {
        // Ensure key is at least 256 bits for HS256
        byte[] keyBytes = secret.getBytes();
        if (keyBytes.length < 32) {
            // Pad to 32 bytes if needed
            byte[] padded = new byte[32];
            System.arraycopy(keyBytes, 0, padded, 0, keyBytes.length);
            return Keys.hmacShaKeyFor(padded);
        }
        return Keys.hmacShaKeyFor(keyBytes);
    }
=======
    private Key key() { return Keys.hmacShaKeyFor(secret.getBytes()); }
>>>>>>> 06559c1 (Initial commit for Render deployment)

    public String generateToken(String userId, String role) {
        return Jwts.builder()
                .setSubject(userId)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
<<<<<<< HEAD
                .signWith(signingKey(), SignatureAlgorithm.HS256)
=======
                .signWith(key(), SignatureAlgorithm.HS256)
>>>>>>> 06559c1 (Initial commit for Render deployment)
                .compact();
    }

    public String extractUserId(String token) {
<<<<<<< HEAD
        return parseClaims(token).getSubject();
    }

    public String extractRole(String token) {
        return (String) parseClaims(token).get("role");
=======
        return Jwts.parserBuilder().setSigningKey(key()).build()
                .parseClaimsJws(token).getBody().getSubject();
>>>>>>> 06559c1 (Initial commit for Render deployment)
    }

    public boolean validateToken(String token) {
        try {
<<<<<<< HEAD
            parseClaims(token);
=======
            Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token);
>>>>>>> 06559c1 (Initial commit for Render deployment)
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
<<<<<<< HEAD

    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signingKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
=======
>>>>>>> 06559c1 (Initial commit for Render deployment)
}
