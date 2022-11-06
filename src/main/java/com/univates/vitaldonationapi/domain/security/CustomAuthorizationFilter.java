package com.univates.vitaldonationapi.domain.security;

import com.univates.vitaldonationapi.domain.exception.GlobalExceptionHandler;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {
        try {
            if (!request.getServletPath().equals("/login")
                    && (!request.getServletPath().equals("/api/users") || !request.getMethod().equals(HttpMethod.POST.name()))
                    && !request.getServletPath().equals("/api/users/refresh-token")) {
                SecurityContextHolder.getContext().setAuthentication(TokenManager.authenticateToken(request));
            }
        } catch (Exception e) {
            GlobalExceptionHandler.responseException(response, e);
        }

        filterChain.doFilter(request, response);
    }
}

