package com.ecommerce.courses.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Entity
@Data
@Table(name = "PERMISSIONS")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "PERMISSION_ID")
    UUID permissionId;

    @Column(name = "NAME", nullable = false, unique = true)
    String name;
}
