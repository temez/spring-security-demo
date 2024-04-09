package dev.temez.springdemo.security.service.security.token;

import io.jsonwebtoken.Claims;
import org.jetbrains.annotations.NotNull;


public interface ClaimsService {

  @NotNull String extractUsername(@NotNull String token);

  @NotNull Claims extractClaims(@NotNull String token);

}
