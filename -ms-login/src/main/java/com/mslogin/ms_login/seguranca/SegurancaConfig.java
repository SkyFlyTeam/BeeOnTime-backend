package com.mslogin.ms_login.seguranca;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SegurancaConfig {

    @Autowired
    private JwtAuthFiltro jwtAuthFiltro;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/auth/login").permitAll() // ✅ Allow login & registration
                                .anyRequest().authenticated() // Protect other routes
                )
                .addFilterBefore(jwtAuthFiltro, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    // Função para testar requisições sem a autenticação jwt
    //Essa função faz com que a aplicação receba requisições de qualquer ip
    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //     return http
    //             .csrf(csrf -> csrf.disable())
    //             .authorizeHttpRequests(auth -> auth
    //                             .anyRequest().permitAll() // Disable authentication for all routes
    //             )
    //             .build();
    // }


    //Função para configurar o cors
    // @Bean
    // public CorsConfigurationSource corsConfigurationSource() {
    //     CorsConfiguration configuration = new CorsConfiguration();
    //     configuration.setAllowedOrigins(List.of("http://localhost:3000")); // Frontend URL
    //     configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
    //     configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
    //     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //     source.registerCorsConfiguration("/**", configuration);
    //     return source;
    // }


}
