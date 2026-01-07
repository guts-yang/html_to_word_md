package com.example.htmlconverter.config;

import com.example.htmlconverter.model.User;
import com.example.htmlconverter.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            String username = "gutsyang";
            if (userRepository.findByUsername(username).isEmpty()) {
                User user = new User();
                user.setUsername(username);
                user.setPassword(passwordEncoder.encode("123456"));
                user.setRole("ROLE_USER");
                userRepository.save(user);
                System.out.println("Default user created: " + username);
            }
        };
    }
}
