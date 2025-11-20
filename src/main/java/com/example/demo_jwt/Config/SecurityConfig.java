package com.example.demo_jwt.Config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.demo_jwt.Jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class SecurityConfig {
        private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final AuthenticationProvider authProvider;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception
    {
        return http
                .csrf(csrf->csrf.disable())
                .cors(cors->cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(
                        authRequest -> authRequest
                                .requestMatchers("/auth/**").permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(sessionManager->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // Permitir todos los orígenes (solo para desarrollo)
        // En producción, especifica los dominios exactos:
        // configuration.setAllowedOrigins(Arrays.asList("https://tudominio.com"));
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));

        // Permitir métodos HTTP
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));

        // Permitir headers
        configuration.setAllowedHeaders(Arrays.asList("*"));

        // Permitir credenciales (cookies, headers de autorización)
        configuration.setAllowCredentials(true);

        // Headers expuestos al cliente
        configuration.setExposedHeaders(Arrays.asList("Authorization"));

        // Tiempo de cache para preflight requests
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }


    

}
