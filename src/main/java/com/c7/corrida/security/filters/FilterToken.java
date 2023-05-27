package com.c7.corrida.security.filters;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.c7.corrida.entities.User;
import com.c7.corrida.security.filters.exceptions.JWTExceptions;
import com.c7.corrida.services.UserService;
import com.c7.corrida.services.token.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FilterToken extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;
    String token;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader != null){
            this.token = authorizationHeader.replace("Bearer ", "");
            try {
                String subject = this.tokenService.getSubject(token);
                User user = userService.loadUserByUsername(subject);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        user, null, user.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }catch (TokenExpiredException e){
                JWTExceptions.expiredError(response);
                return;
            }
        }

        // Pass the request
        filterChain.doFilter(request, response);
    }
}
