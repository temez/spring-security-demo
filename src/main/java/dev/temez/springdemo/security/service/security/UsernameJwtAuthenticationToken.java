package dev.temez.springdemo.security.service.security;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;


@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UsernameJwtAuthenticationToken extends AbstractAuthenticationToken {

  @NotNull UserDetails principal;

  public UsernameJwtAuthenticationToken(@NotNull UserDetails principal) {
    super(principal.getAuthorities());
    this.principal = principal;
  }

  @Override
  public Object getCredentials() {
    return null;
  }
}
