package com.supricode.security.config;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.filters.CorsFilter;
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

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
//    private final SimpleCORSFilter simpleCORSFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
       http
               .cors()
               .and()
               .csrf()
               .disable()
               .authorizeHttpRequests()
               .requestMatchers("/api/v1/auth/**").permitAll()
               .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
               .anyRequest().authenticated()
               .and()
               .sessionManagement()
               .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               .and()
               .authenticationProvider(authenticationProvider)
               .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//               .csrf().disable() // Disable CSRF protection
//               .addFilterBefore(simpleCORSFilter, UsernamePasswordAuthenticationFilter.class) // Add the custom CORS filter
//               .authorizeHttpRequests()
//               .requestMatchers("/api/v1/auth/**").permitAll() // Allow all requests to the authentication endpoints
//               .anyRequest().authenticated() // All other requests need to be authenticated
//               .and()
//               .sessionManagement()
//               .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Use stateless session management
//               .and()
//               .authenticationProvider(authenticationProvider) // Configure the custom authentication provider
//               .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // Add JWT filter before the default authentication filter

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
