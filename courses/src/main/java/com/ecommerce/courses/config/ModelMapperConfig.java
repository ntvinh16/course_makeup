package com.ecommerce.courses.config;

import com.ecommerce.courses.domain.entity.PermissionEntity;
import com.ecommerce.courses.domain.entity.RoleEntity;
import com.ecommerce.courses.domain.model.response.RoleResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.stream.Collectors;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);


        modelMapper.createTypeMap(RoleEntity.class, RoleResponse.class)
                .addMappings(mapper -> {
                    mapper.map(src -> {
                        if (src.getPermissions() != null) {
                            return src.getPermissions().stream()
                                    .map(PermissionEntity::getName)
                                    .collect(Collectors.toSet());
                        }
                        return Collections.emptySet(); // Return an empty set if permissions is null
                    }, RoleResponse::setPermissions);
                });

        return modelMapper;
    }

}
