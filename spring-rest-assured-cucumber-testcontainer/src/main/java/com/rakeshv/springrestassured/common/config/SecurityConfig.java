package com.rakeshv.springrestassured.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(
                        requests ->
                                requests.requestMatchers(HttpMethod.GET, "/api/books")
                                        .permitAll()
                                        .requestMatchers(HttpMethod.GET, "/api/books/*")
                                        .permitAll()
                                        .requestMatchers(HttpMethod.POST, "/api/books")
                                        .hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.POST, "/api/v1/jobs")
                                        .hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.GET, "/api/v1/jobs")
                                        .permitAll()
                                        .requestMatchers(HttpMethod.GET, "/api/v1/jobs**")
                                        .permitAll()
//                                        .hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.PUT, "/api/v1/jobs/*")
                                        .permitAll()
                                        .requestMatchers(HttpMethod.DELETE, "/api/v1/jobs/*")
                                        .permitAll()
                                        .anyRequest()
                                        .authenticated()
                );

        return httpSecurity.build();
    }
}
