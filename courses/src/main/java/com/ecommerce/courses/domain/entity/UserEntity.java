package com.ecommerce.courses.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table(name = "USERS")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "USER_ID")
    UUID userId;

    @Column(name = "USERNAME", nullable = false)
    String username;

    @Column(name = "EMAIL", nullable = false)
    String email;

    @Column(name = "PASSWORD", nullable = false)
    String password;

    @Column(name = "PHONE_NUMBER", nullable = false)
    String phoneNumber;

    @Column(name = "BIRTHDAY")
    Date birthday;

    @Column(name = "FULL_NAME", nullable = false)
    String fullName;

    @Column(name = "CREATE_AT")
    Date createAt = Date.valueOf(LocalDate.now());

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "USER_ROLES",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
    )
    Set<RoleEntity> roles;
}
