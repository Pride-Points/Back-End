package com.pridepoints.api.utilities.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class AutenticacaoEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        if(authException.getClass().equals(BadCredentialsException.class)){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//            não autenticado
        }else{
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
//            Realizou a requisição, porem não tem permissão o suficiente
        }

    }
}
