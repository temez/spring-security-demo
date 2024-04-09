package dev.temez.springdemo.security.repository;

import dev.temez.springdemo.security.model.User;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

  @NotNull Optional<User> findUserByUsername(@NotNull String username);

  @NotNull Optional<User> findUserByUsernameOrEmail(@NotNull String username,
                                                    @NotNull String email);

  boolean existsById(long id);

  boolean existsByUsername(@NotNull String username);
}
