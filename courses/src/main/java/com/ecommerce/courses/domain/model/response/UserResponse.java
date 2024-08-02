package com.ecommerce.courses.domain.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    UUID userId;
    String username;
    String email;
    String fullName;
    Date birthday;
    Date createAt;
    Set<RoleResponse> roles;
}
