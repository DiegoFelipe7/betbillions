package com.betbillion.authservice.infrastructure.drivers.security.config;

import com.betbillion.authservice.infrastructure.drivers.security.jwt.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.logout.DelegatingServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.SecurityContextServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.WebSessionServerLogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@RequiredArgsConstructor
public class MainSecurity {
    private final SecurityContextRepository securityContextRepository;

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http, JwtFilter jwtFilter) {

        return http
                .csrf().disable()
                .authorizeExchange(exchangeSpec -> {
                    exchangeSpec.pathMatchers("/api/auth/**").permitAll()
                            .anyExchange().permitAll();
                })
                .addFilterAfter(jwtFilter, SecurityWebFiltersOrder.FIRST)
                .securityContextRepository(securityContextRepository)
                .formLogin().disable()
                .logout().disable()
                .httpBasic().disable()
                .build();
    }

}
