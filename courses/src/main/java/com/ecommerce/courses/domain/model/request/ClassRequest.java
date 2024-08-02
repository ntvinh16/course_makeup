package com.ecommerce.courses.domain.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClassRequest {
    @Size(max = 255, message = "CLASS_TITLE_INVALID")
    @NotEmpty(message = "CLASS_TITLE_NOT_EMPTY")
    String title;

    @Size(max = 255, message = "CLASS_DESCRIPTION_INVALID")
    @NotEmpty(message = "CLASS_DESCRIPTION_INVALID")
    String description;
    BigDecimal price;
    BigDecimal priceSale;
    String demoVideoUrl;
    String imageUrl;
    Boolean isFree;
    Set<UUID> lessons = new HashSet<>();
}
