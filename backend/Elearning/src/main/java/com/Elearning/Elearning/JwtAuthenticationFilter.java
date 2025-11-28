package com.Elearning.Elearning.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            String token = authHeader.substring(7);

            try {
                // Validate token and get the claims
                Jws<Claims> jws = jwtUtil.validateToken(token);
                Claims claims = jws.getBody();

                // Make the claims available to controllers
                request.setAttribute("claims", claims);

            } catch (JwtException ex) {
                // Token is invalid → we do NOT stop the request
                // Let Spring Security handle the "authenticated()" protection
            }
        }

        // Continue the filter chain
        filterChain.doFilter(request, response);
    }
}
