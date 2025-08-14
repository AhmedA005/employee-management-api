package org.example.efinance.jwt;

import org.example.efinance.services.impl.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final UserService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(UserService userDetailsService, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authz -> authz

                        .requestMatchers("/", "/index.html", "/favicon.ico", "/css/**", "/js/**", "/images/**").permitAll()
                        // Public endpoints
                        .requestMatchers("/auth/**").permitAll().requestMatchers("/actuator/health").permitAll()

                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/swagger-ui.html").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers("/swagger-resources/**").permitAll()
                        .requestMatchers("/webjars/**").permitAll()

                        // Employee endpoints - method-specific access
                        .requestMatchers(HttpMethod.GET, "/employees", "/employees/*", "/employees/*/*").hasAnyRole("USER", "HR_MANAGER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/employees").hasAnyRole("HR_MANAGER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/employees/*").hasAnyRole("HR_MANAGER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/employees/*").hasAnyRole("HR_MANAGER", "ADMIN")

                        // Job endpoints
                        .requestMatchers(HttpMethod.GET, "/jobs", "/jobs/*").hasAnyRole("USER", "HR_MANAGER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/jobs").hasAnyRole("HR_MANAGER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/jobs/*").hasAnyRole("HR_MANAGER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/jobs/*").hasAnyRole("HR_MANAGER", "ADMIN")

                        // Department endpoints
                        .requestMatchers(HttpMethod.GET, "/departments", "/departments/*").hasAnyRole("USER", "HR_MANAGER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/departments").hasAnyRole("HR_MANAGER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/departments/*").hasAnyRole("HR_MANAGER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/departments/*").hasAnyRole("HR_MANAGER", "ADMIN")


                        // Admin endpoints
                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        // All other requests need authentication
                        .anyRequest().authenticated())
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
