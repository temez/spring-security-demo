package dev.temez.springdemo.security.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(schema = "spring_security_demo", name = "users")
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@SuppressWarnings({"JpaDataSourceORMInspection"})
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  long id;

  @Column(nullable = false, unique = true)
  @NotNull String username;

  @Column(nullable = false, unique = true)
  @NotNull String email;

  @Column(nullable = false, updatable = false)
  @NotNull String password;


}
