package com.car_blog.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

@Configuration 
public class SecurityConfig {
    
    // (A @Bean method is usually only found in @Configuration)
    // (Spring runs the @Bean method and lists it by return Type. This is how @Service finds it for its constructor.)
    // (If Spring has two @Bean methods returning the same type, it gets confused and fails at startup.)
    
    // This is found and called by UserService.register()
    @Bean
    PasswordEncoder createBCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.GET, "/api/cars/**", "/uploads/**").permitAll()
                .requestMatchers("/api/users/register").permitAll()
                .anyRequest().authenticated())
            .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
