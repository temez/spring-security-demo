package dev.temez.springdemo.security.exception.user;

import org.jetbrains.annotations.NotNull;

public class UserAlreadyRegisteredException extends UserException {

  public UserAlreadyRegisteredException(@NotNull String message, Object @NotNull ... args) {
    super(message, args);
  }
}
