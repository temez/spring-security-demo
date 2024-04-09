package dev.temez.springdemo.security;

import org.jetbrains.annotations.NotNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
    scanBasePackages = {
        "dev.temez.springdemo.security",
        "dev.temez.exceptify"
    })
public class SecurityDemoApplication {

  public static void main(String @NotNull [] args) {
    SpringApplication.run(SecurityDemoApplication.class, args);
  }
}
