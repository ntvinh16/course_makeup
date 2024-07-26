package com.ecommerce.courses.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Entity
@Table(name = "USER_ROLES")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRoleEntity {
    @Id
    @Column(name = "USER_ID", nullable = false)
    UUID user_id;

    @Column(name = "ROLE_ID", nullable = false)
    UUID role_id;
}
