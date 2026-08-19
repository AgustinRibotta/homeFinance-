package com.homeFinance.homeFinance.service.imp;

import com.homeFinance.homeFinance.dto.response.UserResponse;
import com.homeFinance.homeFinance.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {

  @Value("${jwt.secret}")
  private String secretKey;

  @Value("${jwt.expiration}")
  private long expirationTimeInMillis;

  @Override
  public String generateToken(UserResponse user) {
    Claims claims = Jwts.claims().setSubject(user.email());
    claims.put("userId", user.id().toString());
    claims.put("householdId", user.household().id().toString());

    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + expirationTimeInMillis))
        .signWith(getSigningKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  private SecretKey getSigningKey() {
    return Keys.hmacShaKeyFor(secretKey.getBytes());
  }
}
