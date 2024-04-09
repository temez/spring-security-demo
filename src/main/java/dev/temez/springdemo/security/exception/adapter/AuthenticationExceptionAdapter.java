package dev.temez.springdemo.security.exception.adapter;

import dev.temez.exceptify.annotation.ExceptifyExceptionAdapter;
import dev.temez.exceptify.exception.adapter.ExceptionAdapter;
import dev.temez.exceptify.rest.RestResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@ExceptifyExceptionAdapter(
    binding = @ExceptifyExceptionAdapter.ExceptionBinding(value = {
        BadCredentialsException.class,
        UsernameNotFoundException.class
    }
    )
)
public class AuthenticationExceptionAdapter implements ExceptionAdapter<AuthenticationException> {

  @Override
  public @NotNull RestResponse<?> convert(@NotNull AuthenticationException exception) {
    return RestResponse.badRequest()
        .error("authentication", exception.getMessage())
        .build();
  }
}
