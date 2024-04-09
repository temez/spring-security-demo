package dev.temez.springdemo.security.service.user;

import dev.temez.springdemo.security.exception.user.UserNotFoundException;
import dev.temez.springdemo.security.model.User;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public interface UserService {

  @NotNull User getUser(@NotNull String username) throws UserNotFoundException;

  @NotNull User getUser(long id) throws UserNotFoundException;

  @NotNull Optional<User> getUserOptional(@NotNull String username);

  @NotNull Optional<User> getUserOptional(long id);

  @NotNull User save(@NotNull User user);
}
