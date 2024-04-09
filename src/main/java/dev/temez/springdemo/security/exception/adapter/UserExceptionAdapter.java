package dev.temez.springdemo.security.exception.adapter;

import dev.temez.exceptify.annotation.ExceptifyExceptionAdapter;
import dev.temez.exceptify.exception.adapter.ExceptionAdapter;
import dev.temez.exceptify.rest.RestResponse;
import dev.temez.springdemo.security.exception.user.UserAlreadyRegisteredException;
import dev.temez.springdemo.security.exception.user.UserException;
import dev.temez.springdemo.security.exception.user.UserNotFoundException;
import org.jetbrains.annotations.NotNull;

@ExceptifyExceptionAdapter(
    binding = @ExceptifyExceptionAdapter.ExceptionBinding(value = {
        UserNotFoundException.class,
        UserAlreadyRegisteredException.class
    }
    )
)
public class UserExceptionAdapter implements ExceptionAdapter<UserException> {

  @Override
  public @NotNull RestResponse<?> convert(@NotNull UserException exception) {
    return RestResponse
        .badRequest()
        .code(400)
        .error("user", exception.getMessage())
        .build();
  }
}
