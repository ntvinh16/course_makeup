package com.ecommerce.courses.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Entity
@Table(name = "ROLE_PERMISSIONS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RolePermissionEntity {
    @Id
    @Column(name = "ROLE_ID", nullable = false)
    UUID role_id;

    @Column(name = "PERMISSION_ID", nullable = false)
    UUID permission_id;
}
