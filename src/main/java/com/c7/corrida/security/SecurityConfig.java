package com.c7.corrida.security;

import com.c7.corrida.security.filters.FilterToken;
import com.c7.corrida.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Autowired
    private UserService userService;

    @Autowired
    private FilterToken filterToken;

    @Bean
    public AuthenticationManager authenticationManager
            (AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.
                csrf().disable()
                .authorizeHttpRequests(
                authorizeConfig -> {
                    authorizeConfig.requestMatchers("/").permitAll();
                    authorizeConfig.requestMatchers("/users").permitAll();
                    authorizeConfig.requestMatchers(HttpMethod.POST,"/users").permitAll();
                    authorizeConfig.requestMatchers("/users/**").hasAuthority("ADMIN");
                    authorizeConfig.requestMatchers("/challenge").permitAll();
                    authorizeConfig.requestMatchers("/challenge/**").hasAuthority("ADMIN");
                    authorizeConfig.requestMatchers("/material").permitAll();
                    authorizeConfig.requestMatchers("/material/**").hasAuthority("ADMIN");
                    authorizeConfig.requestMatchers("/category/**").hasAuthority("ADMIN");
                    authorizeConfig.requestMatchers(HttpMethod.POST, "/login").permitAll();
                    authorizeConfig.anyRequest().authenticated();
                }
        )
                .addFilterBefore(filterToken, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

}
