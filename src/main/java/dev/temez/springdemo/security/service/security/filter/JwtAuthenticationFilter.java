package dev.temez.springdemo.security.service.security.filter;

import dev.temez.springdemo.security.service.security.UserDetailsImpl;
import dev.temez.springdemo.security.service.security.UsernameJwtAuthenticationToken;
import dev.temez.springdemo.security.service.security.token.ClaimsService;
import dev.temez.springdemo.security.service.security.token.TokenValidator;
import dev.temez.springdemo.security.service.user.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  @NotNull TokenValidator tokenValidator;

  @NotNull ClaimsService claimsService;

  @NotNull UserService userService;

  @Override
  protected void doFilterInternal(
      @NotNull HttpServletRequest request,
      @NotNull HttpServletResponse response,
      @NotNull FilterChain filterChain
  ) throws ServletException, IOException {

    String authorizationHeader = request.getHeader("Authorization");

    if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    String jwt = authorizationHeader.substring(7);
    if (!tokenValidator.isValid(jwt)) {
      filterChain.doFilter(request, response);
      return;
    }

    String username = claimsService.extractUsername(jwt);
    UserDetails userDetails = userService.getUserOptional(username)
        .map(UserDetailsImpl::new)
        .orElse(null);

    if (userDetails == null) {
      filterChain.doFilter(request, response);
      return;
    }

    Authentication authentication = new UsernameJwtAuthenticationToken(userDetails);
    authentication.setAuthenticated(true);

    SecurityContextHolder.getContext().setAuthentication(authentication);
    filterChain.doFilter(request, response);
  }
}
