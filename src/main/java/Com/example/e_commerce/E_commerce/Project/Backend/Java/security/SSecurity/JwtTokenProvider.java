package Com.example.e_commerce.E_commerce.Project.Backend.Java.security.SSecurity;

import Com.example.e_commerce.E_commerce.Project.Backend.Java.security.entity.Customer;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
@Component
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    public String generateToken(Customer customer) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration);

        // Convert secret to Base64 encoded string
        String base64EncodedSecret = Base64.getEncoder().encodeToString(jwtSecret.getBytes());

        return Jwts.builder()
            .setSubject(customer.getUsername())
            .claim("roles", customer.getRole().name())
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(Keys.hmacShaKeyFor(base64EncodedSecret.getBytes()))
            .compact();
    }

    public String getUsernameFromToken(String token) {
        String base64EncodedSecret = Base64.getEncoder().encodeToString(jwtSecret.getBytes());

        Claims claims = Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(base64EncodedSecret.getBytes()))
            .build()
            .parseClaimsJws(token)
            .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            String base64EncodedSecret = Base64.getEncoder().encodeToString(jwtSecret.getBytes());
            Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(base64EncodedSecret.getBytes()))
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
