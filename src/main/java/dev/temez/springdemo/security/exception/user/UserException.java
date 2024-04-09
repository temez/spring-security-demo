package dev.temez.springdemo.security.exception.user;

import dev.temez.exceptify.exception.impl.ExceptifyException;
import org.jetbrains.annotations.NotNull;

public abstract class UserException extends ExceptifyException {

  public UserException(@NotNull String message) {
    super(message);
  }

  public UserException(@NotNull String message, Object @NotNull ... args) {
    super(message, args);
  }
}
