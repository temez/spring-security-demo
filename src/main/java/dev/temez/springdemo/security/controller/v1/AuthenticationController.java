package dev.temez.springdemo.security.controller.v1;

import dev.temez.exceptify.rest.RestResponse;
import dev.temez.springdemo.security.api.request.AuthenticationRequest;
import dev.temez.springdemo.security.api.request.RegistrationRequest;
import dev.temez.springdemo.security.api.response.TokenResponse;
import dev.temez.springdemo.security.service.authentication.AuthenticationService;
import dev.temez.springdemo.security.service.security.token.TokenFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/authentication")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthenticationController {

  @NotNull AuthenticationService authenticationService;

  @NotNull TokenFactory tokenFactory;

  @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
  ResponseEntity<RestResponse<TokenResponse>> authenticate(
      @NotNull @RequestBody AuthenticationRequest request) {
    authenticationService.authenticate(request);
    String token = tokenFactory.generateToken(request.getUsername());
    return RestResponse.ok(new TokenResponse(token)).toResponseEntity();
  }

  @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
  ResponseEntity<RestResponse<TokenResponse>> register(
      @NotNull @RequestBody RegistrationRequest request) {
    authenticationService.register(request);
    String token = tokenFactory.generateToken(request.getUsername());
    return RestResponse.ok(new TokenResponse(token)).toResponseEntity();
  }
}
