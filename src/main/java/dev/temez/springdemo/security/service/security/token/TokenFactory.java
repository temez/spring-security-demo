package dev.temez.springdemo.security.service.security.token;

import org.jetbrains.annotations.NotNull;

public interface TokenFactory {

  @NotNull String generateToken(@NotNull String username);
}
