package dev.temez.springdemo.security.controller.v1;

import dev.temez.exceptify.rest.RestResponse;
import dev.temez.springdemo.security.model.User;
import dev.temez.springdemo.security.service.user.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserController {

  @NotNull UserService userService;

  @GetMapping(value = "/{username}", consumes = "application/json", produces = "application/json")
  ResponseEntity<RestResponse<User>> getUser(@PathVariable @NotNull String username) {
    return RestResponse.ok(userService.getUser(username)).toResponseEntity();
  }
}
