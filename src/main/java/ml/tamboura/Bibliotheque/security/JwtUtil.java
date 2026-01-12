package ml.tamboura.Bibliotheque.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import ml.tamboura.Bibliotheque.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    private static final String SECRET_KEY =
            "00cd44c3b1e8118891dba4d8c2dccaf04b421e163833a69e6d71425651ae3242204203662552a6acf92c5224a9a332c8700d292918ba942c3c457b39097ca936";

    private static final long EXPIRATION_TIME = 86400000; // 24h

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // 🔐 Génération du token
    public String generateToken(User user) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole().name());

        return Jwts.builder()
                .setClaims(claims)                 // 👈 D’ABORD les claims custom
                .setSubject(user.getEmail())       // 👈 ensuite subject
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // 📤 EXTRACTION USERNAME (EMAIL)
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // 📤 EXTRACTION RÔLE
    public String extractRole(String token) {
        return extractClaim(token, claims -> claims.get("role", String.class));
    }

    // 📤 EXTRACTION GÉNÉRIQUE
    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            throw new RuntimeException("Token JWT invalide ou expiré");
        }
    }

    // ✅ VALIDATION
    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername())
                && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        Date expiration = extractClaim(token, Claims::getExpiration);
        return expiration.before(new Date());
    }
}
