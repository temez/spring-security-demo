package dev.temez.springdemo.security.service.authentication;

import dev.temez.springdemo.security.api.request.AuthenticationRequest;
import dev.temez.springdemo.security.api.request.RegistrationRequest;
import dev.temez.springdemo.security.exception.user.UserAlreadyRegisteredException;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.AuthenticationException;

public interface AuthenticationService {

  void authenticate(@NotNull AuthenticationRequest request) throws AuthenticationException;

  void register(@NotNull RegistrationRequest request) throws UserAlreadyRegisteredException;
}
