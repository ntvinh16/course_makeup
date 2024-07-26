package com.ecommerce.courses.config;

import com.ecommerce.courses.domain.entity.UserEntity;
import com.ecommerce.courses.repository.RoleRepository;
import com.ecommerce.courses.repository.UserRepositoy;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
public class ApplicationInitConfig {

    @Bean
    ApplicationRunner applicationRunner(UserRepositoy userRepositoy, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            var roleAdmin = roleRepository.findByName("ADMIN");
            if(userRepositoy.findByUsername("admin") == null && roleAdmin != null){

                UserEntity user = UserEntity.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("12345678"))
                        .email("admin@course.com")
                        .full_name("admin")
                        .roles(new HashSet<>() {{
                            add(roleAdmin);
                        }})
                        .build();

                userRepositoy.save(user);
            }
        };
    }
}
