package com.JwtAuthentication.jwtAuthService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class MyConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.builder().username("badhon").password(passwordEncoder().encode("abc")).roles("ADMIN").build();
        UserDetails user2 = User.builder().username("durgesh").password(passwordEncoder().encode("abc")).roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user1, user2);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}