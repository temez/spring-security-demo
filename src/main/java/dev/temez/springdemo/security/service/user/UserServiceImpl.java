package dev.temez.springdemo.security.service.user;

import dev.temez.springdemo.security.exception.user.UserNotFoundException;
import dev.temez.springdemo.security.model.User;
import dev.temez.springdemo.security.repository.UserRepository;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {

  @NotNull UserRepository userRepository;

  @Override
  public @NotNull User getUser(@NotNull String username) throws UserNotFoundException {
    return userRepository.findUserByUsername(username)
        .orElseThrow(() -> new UserNotFoundException("User with username %s not found!", username));
  }

  @Override
  public @NotNull User getUser(long id) throws UserNotFoundException {
    return userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("User with id %d not found!", id));
  }

  @Override
  public @NotNull Optional<User> getUserOptional(@NotNull String username) {
    return userRepository.findUserByUsername(username);
  }

  @Override
  public @NotNull Optional<User> getUserOptional(long id) {
    return userRepository.findById(id);
  }

  @Override
  public @NotNull User save(@NotNull User user) {
    return userRepository.save(user);
  }
}
