package dev.temez.springdemo.security.service.security;

import dev.temez.springdemo.security.service.user.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserDetailsServiceImpl implements UserDetailsService {

  @NotNull UserService userService;

  @Override
  public UserDetails loadUserByUsername(@NotNull String username) throws UsernameNotFoundException {
    return userService.getUserOptional(username)
        .map(UserDetailsImpl::new)
        .orElseThrow(() -> new UsernameNotFoundException(
            String.format("User with username %s not found", username))
        );
  }
}
