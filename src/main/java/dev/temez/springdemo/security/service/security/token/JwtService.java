package dev.temez.springdemo.security.service.security.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class JwtService implements ClaimsService, TokenFactory, TokenValidator {

  @NonFinal
  @Value("${jwt.access-token.secret}")
  String secret;

  @NonFinal
  @Value("${jwt.access-token.lifetime}")
  int lifetime;

  @Override
  public @NotNull String extractUsername(@NotNull String token) {
    return extractClaims(token).getSubject();
  }

  @Override
  public @NotNull Claims extractClaims(@NotNull String token) {
    return Jwts.parser()
        .verifyWith(getSignKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }

  @Override
  public @NotNull String generateToken(@NotNull String username) {
    return Jwts.builder()
        .subject(username)
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + lifetime))
        .signWith(getSignKey())
        .compact();
  }

  @NotNull
  private SecretKey getSignKey() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
  }

  @Override
  public boolean isValid(@NotNull String token) {
    try {
      Claims claims = extractClaims(token);

      boolean expired = claims.getExpiration()
          .before(new Date(System.currentTimeMillis()));
      return !expired;
    } catch (JwtException ignored) {
      return false;
    }
  }
}
