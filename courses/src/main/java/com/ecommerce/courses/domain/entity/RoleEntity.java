package com.ecommerce.courses.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table(name = "ROLES")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ROLE_ID")
    UUID roleId;

    @Column(name = "NAME", nullable = false, unique = true)
    String name;

    @Column(name = "DESCRIPTION")
    String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ROLE_PERMISSIONS",
            joinColumns = @JoinColumn(name = "ROLE_ID"),
            inverseJoinColumns = @JoinColumn(name = "PERMISSION_ID")
    )
    Set<PermissionEntity> permissions = new HashSet<>();
}
