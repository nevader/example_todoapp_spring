package com.nevader.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((auth) -> {
                    auth.requestMatchers(HttpMethod.DELETE, "/api/todo/**").hasRole("ADMIN");
                    auth.requestMatchers(HttpMethod.PUT, "/api/todo/**").hasRole("ADMIN");
                    auth.requestMatchers(HttpMethod.POST, "/api/todo/**").hasRole("ADMIN");
                    auth.requestMatchers(HttpMethod.GET, "/api/todo/**").hasAnyRole("ADMIN", "USER");
                    auth.requestMatchers(HttpMethod.PATCH, "/api/todo/**").hasAnyRole("ADMIN", "USER");
                    auth.anyRequest().authenticated();
                });

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("password"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

}
