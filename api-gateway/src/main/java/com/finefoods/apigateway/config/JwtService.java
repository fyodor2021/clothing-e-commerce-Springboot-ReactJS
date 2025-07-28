package com.finefoods.apigateway.config;

import com.finefoods.apigateway.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY = "f3af48e5b98dcb9dfe45dc11abaf9bdc1ada1ed26923c80c02950b61cda30ed3";
    public void validateToken(String token) {
        Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token);
    }
    public String  generateToken(
            Map<String, Object> storedClaims
    ){
        Map<String, Object> claims = new HashMap<>();
        claims.put("firstName", storedClaims.get("firstName"));
        claims.put("lastName",  storedClaims.get("lastName"));
        claims.put("ROLE",  storedClaims.get("ROLE"));
        int iat = ((Number) storedClaims.get("iat")).intValue();
        Object expObject = storedClaims.get("exp");
        return Jwts.builder()
                .setClaims(storedClaims)
                .setSubject(storedClaims.get("sub").toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() +  1000L * 60 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
