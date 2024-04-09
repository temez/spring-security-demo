package dev.temez.springdemo.security.service.authentication;

import dev.temez.springdemo.security.api.request.AuthenticationRequest;
import dev.temez.springdemo.security.api.request.RegistrationRequest;
import dev.temez.springdemo.security.exception.user.UserAlreadyRegisteredException;
import dev.temez.springdemo.security.model.User;
import dev.temez.springdemo.security.service.user.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthenticationServiceImpl implements AuthenticationService {

  @NotNull UserService userService;

  @NotNull AuthenticationManager authenticationManager;

  @NotNull PasswordEncoder passwordEncoder;

  @Override
  public void authenticate(@NotNull AuthenticationRequest request) throws AuthenticationException {
    Authentication authentication = new UsernamePasswordAuthenticationToken(
        request.getUsername(),
        request.getPassword()
    );

    authenticationManager.authenticate(authentication);
  }

  @Override
  public void register(@NotNull RegistrationRequest request) throws UserAlreadyRegisteredException {
    userService.getUserOptional(request.getUsername())
        .ifPresent(user -> {
          throw new UserAlreadyRegisteredException(
              "User with username %s already registered",
              request.getUsername()
          );
        });

    String encodedPassword = passwordEncoder.encode(request.getPassword());

    User user = userService.save(
        User.builder()
            .username(request.getUsername())
            .password(encodedPassword)
            .build()
    );
    log.debug("Registered user with id: {}, username: {}", user.getId(), user.getUsername());
  }
}
