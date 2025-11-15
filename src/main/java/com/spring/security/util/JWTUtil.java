package com.spring.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

import static io.jsonwebtoken.Jwts.SIG.HS256;

@Component
public class JWTUtil {

    private static final long EXPIRATION_TIME = 1000*60*60;
    private static final String SECRET = "my-super-secret-key-that is-long-enough-123455889!@#";
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(final String userName){
        return Jwts.builder()
                .subject(userName)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY, HS256)
                .compact();
    }
    public String getUserNameFromJwtToken(final String token){
        return getClaims(token).getSubject();
    }

    private static Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getPayload();
    }

    public boolean validateToken(final String userName, final UserDetails userDetails, final String token) {
        return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(final String token) {
        return getClaims(token).getExpiration().before(new Date());
    }
}
