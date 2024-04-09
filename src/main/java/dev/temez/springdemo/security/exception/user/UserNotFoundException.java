package dev.temez.springdemo.security.exception.user;

import org.jetbrains.annotations.NotNull;

public class UserNotFoundException extends UserException {

  public UserNotFoundException(@NotNull String message, Object @NotNull ... args) {
    super(message, args);
  }
}
